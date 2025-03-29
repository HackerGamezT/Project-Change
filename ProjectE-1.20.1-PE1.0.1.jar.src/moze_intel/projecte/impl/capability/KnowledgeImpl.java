/*     */ package moze_intel.projecte.impl.capability;
/*     */ import java.math.BigInteger;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import moze_intel.projecte.api.ItemInfo;
/*     */ import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
/*     */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*     */ import moze_intel.projecte.api.event.PlayerKnowledgeChangeEvent;
/*     */ import moze_intel.projecte.capability.managing.SerializableCapabilityResolver;
/*     */ import moze_intel.projecte.emc.EMCMappingHandler;
/*     */ import moze_intel.projecte.emc.nbt.NBTManager;
/*     */ import moze_intel.projecte.network.PacketHandler;
/*     */ import moze_intel.projecte.network.packets.IPEPacket;
/*     */ import moze_intel.projecte.network.packets.to_client.knowledge.KnowledgeSyncChangePKT;
/*     */ import moze_intel.projecte.network.packets.to_client.knowledge.KnowledgeSyncEmcPKT;
/*     */ import moze_intel.projecte.network.packets.to_client.knowledge.KnowledgeSyncInputsAndLocksPKT;
/*     */ import moze_intel.projecte.network.packets.to_client.knowledge.KnowledgeSyncPKT;
/*     */ import moze_intel.projecte.utils.EMCHelper;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.ListTag;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.common.capabilities.Capability;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import net.minecraftforge.items.IItemHandlerModifiable;
/*     */ import net.minecraftforge.items.ItemStackHandler;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public final class KnowledgeImpl {
/*     */   public static IKnowledgeProvider getDefault() {
/*  43 */     return new DefaultImpl(null);
/*     */   }
/*     */   
/*     */   public static class DefaultImpl
/*     */     implements IKnowledgeProvider {
/*     */     @Nullable
/*     */     private final Player player;
/*  50 */     private final Set<ItemInfo> knowledge = new HashSet<>();
/*  51 */     private final ItemStackHandler inputLocks = new ItemStackHandler(9);
/*  52 */     private BigInteger emc = BigInteger.ZERO;
/*     */     private boolean fullKnowledge = false;
/*     */     
/*     */     private DefaultImpl(@Nullable Player player) {
/*  56 */       this.player = player;
/*     */     }
/*     */     
/*     */     private void fireChangedEvent() {
/*  60 */       if (this.player != null && !(this.player.m_9236_()).f_46443_) {
/*  61 */         MinecraftForge.EVENT_BUS.post((Event)new PlayerKnowledgeChangeEvent(this.player));
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean hasFullKnowledge() {
/*  67 */       return this.fullKnowledge;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setFullKnowledge(boolean fullKnowledge) {
/*  72 */       boolean changed = (this.fullKnowledge != fullKnowledge);
/*  73 */       this.fullKnowledge = fullKnowledge;
/*  74 */       if (changed) {
/*  75 */         fireChangedEvent();
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public void clearKnowledge() {
/*  81 */       boolean hasKnowledge = (this.fullKnowledge || !this.knowledge.isEmpty());
/*  82 */       this.knowledge.clear();
/*  83 */       this.fullKnowledge = false;
/*  84 */       if (hasKnowledge)
/*     */       {
/*  86 */         fireChangedEvent();
/*     */       }
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     private ItemInfo getIfPersistent(@NotNull ItemInfo info) {
/*  92 */       if (!info.hasNBT() || EMCMappingHandler.hasEmcValue(info))
/*     */       {
/*     */         
/*  95 */         return null;
/*     */       }
/*  97 */       ItemInfo cleanedInfo = NBTManager.getPersistentInfo(info);
/*  98 */       if (cleanedInfo.hasNBT() && !EMCMappingHandler.hasEmcValue(cleanedInfo))
/*     */       {
/*     */         
/* 101 */         return cleanedInfo;
/*     */       }
/* 103 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean hasKnowledge(@NotNull ItemInfo info) {
/* 108 */       if (this.fullKnowledge) {
/*     */ 
/*     */         
/* 111 */         ItemInfo persistentInfo = getIfPersistent(info);
/* 112 */         return (persistentInfo == null || this.knowledge.contains(persistentInfo));
/*     */       } 
/* 114 */       return this.knowledge.contains(NBTManager.getPersistentInfo(info));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean addKnowledge(@NotNull ItemInfo info) {
/* 119 */       if (this.fullKnowledge) {
/* 120 */         ItemInfo persistentInfo = getIfPersistent(info);
/* 121 */         if (persistentInfo == null)
/*     */         {
/* 123 */           return false;
/*     */         }
/*     */ 
/*     */         
/* 127 */         return tryAdd(persistentInfo);
/*     */       } 
/* 129 */       if (info.getItem() instanceof moze_intel.projecte.gameObjs.items.Tome) {
/* 130 */         if (info.hasNBT())
/*     */         {
/* 132 */           info = ItemInfo.fromItem((ItemLike)info.getItem());
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 137 */         this.knowledge.add(info);
/* 138 */         this.fullKnowledge = true;
/* 139 */         fireChangedEvent();
/* 140 */         return true;
/*     */       } 
/* 142 */       return tryAdd(NBTManager.getPersistentInfo(info));
/*     */     }
/*     */     
/*     */     private boolean tryAdd(@NotNull ItemInfo cleanedInfo) {
/* 146 */       if (this.knowledge.add(cleanedInfo)) {
/* 147 */         fireChangedEvent();
/* 148 */         return true;
/*     */       } 
/* 150 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean removeKnowledge(@NotNull ItemInfo info) {
/* 155 */       if (this.fullKnowledge) {
/* 156 */         if (info.getItem() instanceof moze_intel.projecte.gameObjs.items.Tome) {
/*     */           
/* 158 */           if (info.hasNBT())
/*     */           {
/* 160 */             info = ItemInfo.fromItem((ItemLike)info.getItem());
/*     */           }
/* 162 */           this.knowledge.remove(info);
/* 163 */           this.fullKnowledge = false;
/* 164 */           fireChangedEvent();
/* 165 */           return true;
/*     */         } 
/*     */ 
/*     */         
/* 169 */         ItemInfo persistentInfo = getIfPersistent(info);
/* 170 */         return (persistentInfo != null && tryRemove(persistentInfo));
/*     */       } 
/* 172 */       return tryRemove(NBTManager.getPersistentInfo(info));
/*     */     }
/*     */     
/*     */     private boolean tryRemove(@NotNull ItemInfo cleanedInfo) {
/* 176 */       if (this.knowledge.remove(cleanedInfo)) {
/* 177 */         fireChangedEvent();
/* 178 */         return true;
/*     */       } 
/* 180 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Set<ItemInfo> getKnowledge() {
/* 186 */       if (this.fullKnowledge) {
/* 187 */         Set<ItemInfo> allKnowledge = EMCMappingHandler.getMappedItems();
/*     */         
/* 189 */         allKnowledge.addAll(this.knowledge);
/* 190 */         return Collections.unmodifiableSet(allKnowledge);
/*     */       } 
/* 192 */       return Collections.unmodifiableSet(this.knowledge);
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public IItemHandlerModifiable getInputAndLocks() {
/* 198 */       return (IItemHandlerModifiable)this.inputLocks;
/*     */     }
/*     */ 
/*     */     
/*     */     public BigInteger getEmc() {
/* 203 */       return this.emc;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setEmc(BigInteger emc) {
/* 208 */       this.emc = emc;
/*     */     }
/*     */ 
/*     */     
/*     */     public void sync(@NotNull ServerPlayer player) {
/* 213 */       PacketHandler.sendTo((IPEPacket)new KnowledgeSyncPKT(serializeNBT()), player);
/*     */     }
/*     */ 
/*     */     
/*     */     public void syncEmc(@NotNull ServerPlayer player) {
/* 218 */       PacketHandler.sendTo((IPEPacket)new KnowledgeSyncEmcPKT(getEmc()), player);
/*     */     }
/*     */ 
/*     */     
/*     */     public void syncKnowledgeChange(@NotNull ServerPlayer player, ItemInfo change, boolean learned) {
/* 223 */       PacketHandler.sendTo((IPEPacket)new KnowledgeSyncChangePKT(change, learned), player);
/*     */     }
/*     */ 
/*     */     
/*     */     public void syncInputAndLocks(@NotNull ServerPlayer player, List<Integer> slotsChanged, IKnowledgeProvider.TargetUpdateType updateTargets) {
/* 228 */       if (!slotsChanged.isEmpty()) {
/* 229 */         int slots = this.inputLocks.getSlots();
/* 230 */         Map<Integer, ItemStack> stacksToSync = new HashMap<>();
/* 231 */         for (Iterator<Integer> iterator = slotsChanged.iterator(); iterator.hasNext(); ) { int slot = ((Integer)iterator.next()).intValue();
/* 232 */           if (slot >= 0 && slot < slots)
/*     */           {
/* 234 */             stacksToSync.put(Integer.valueOf(slot), this.inputLocks.getStackInSlot(slot));
/*     */           } }
/*     */         
/* 237 */         if (!stacksToSync.isEmpty())
/*     */         {
/* 239 */           PacketHandler.sendTo((IPEPacket)new KnowledgeSyncInputsAndLocksPKT(stacksToSync, updateTargets), player);
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void receiveInputsAndLocks(Map<Integer, ItemStack> changes) {
/* 246 */       int slots = this.inputLocks.getSlots();
/* 247 */       for (Map.Entry<Integer, ItemStack> entry : changes.entrySet()) {
/* 248 */         int slot = ((Integer)entry.getKey()).intValue();
/* 249 */         if (slot >= 0 && slot < slots)
/*     */         {
/* 251 */           this.inputLocks.setStackInSlot(slot, entry.getValue());
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public CompoundTag serializeNBT() {
/* 258 */       CompoundTag properties = new CompoundTag();
/* 259 */       properties.m_128359_("transmutationEmc", this.emc.toString());
/*     */       
/* 261 */       ListTag knowledgeWrite = new ListTag();
/* 262 */       for (ItemInfo i : this.knowledge) {
/* 263 */         knowledgeWrite.add(i.write(new CompoundTag()));
/*     */       }
/*     */       
/* 266 */       properties.m_128365_("knowledge", (Tag)knowledgeWrite);
/* 267 */       properties.m_128365_("inputlock", (Tag)this.inputLocks.serializeNBT());
/* 268 */       properties.m_128379_("fullknowledge", this.fullKnowledge);
/* 269 */       return properties;
/*     */     }
/*     */ 
/*     */     
/*     */     public void deserializeNBT(CompoundTag properties) {
/* 274 */       String transmutationEmc = properties.m_128461_("transmutationEmc");
/* 275 */       this.emc = transmutationEmc.isEmpty() ? BigInteger.ZERO : new BigInteger(transmutationEmc);
/*     */       
/* 277 */       ListTag list = properties.m_128437_("knowledge", 10); int i;
/* 278 */       for (i = 0; i < list.size(); i++) {
/* 279 */         ItemInfo info = ItemInfo.read(list.m_128728_(i));
/* 280 */         if (info != null) {
/* 281 */           this.knowledge.add(info);
/*     */         }
/*     */       } 
/*     */       
/* 285 */       for (i = 0; i < this.inputLocks.getSlots(); i++) {
/* 286 */         this.inputLocks.setStackInSlot(i, ItemStack.f_41583_);
/*     */       }
/* 288 */       this.inputLocks.deserializeNBT(properties.m_128469_("inputlock"));
/* 289 */       this.fullKnowledge = properties.m_128471_("fullknowledge");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public final boolean pruneStaleKnowledge() {
/* 295 */       List<ItemInfo> toAdd = new ArrayList<>();
/* 296 */       boolean hasRemoved = this.knowledge.removeIf(info -> {
/*     */             ItemInfo persistentInfo = NBTManager.getPersistentInfo(info);
/*     */ 
/*     */             
/*     */             if (!info.equals(persistentInfo)) {
/*     */               if (EMCHelper.doesItemHaveEmc(persistentInfo)) {
/*     */                 toAdd.add(persistentInfo);
/*     */               }
/*     */               
/*     */               return true;
/*     */             } 
/*     */             
/*     */             return !EMCHelper.doesItemHaveEmc(info);
/*     */           });
/*     */       
/* 311 */       return (this.knowledge.addAll(toAdd) || hasRemoved);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Provider
/*     */     extends SerializableCapabilityResolver<IKnowledgeProvider> {
/* 317 */     public static final ResourceLocation NAME = PECore.rl("knowledge");
/*     */     
/*     */     public Provider(Player player) {
/* 320 */       super((INBTSerializable)new KnowledgeImpl.DefaultImpl(player));
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Capability<IKnowledgeProvider> getMatchingCapability() {
/* 326 */       return PECapabilities.KNOWLEDGE_CAPABILITY;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\impl\capability\KnowledgeImpl.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */