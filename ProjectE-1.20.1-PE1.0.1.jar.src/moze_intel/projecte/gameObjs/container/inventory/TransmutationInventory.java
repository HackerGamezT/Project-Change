/*     */ package moze_intel.projecte.gameObjs.container.inventory;
/*     */ 
/*     */ import java.math.BigInteger;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Optional;
/*     */ import java.util.stream.Collectors;
/*     */ import moze_intel.projecte.PECore;
/*     */ import moze_intel.projecte.api.ItemInfo;
/*     */ import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
/*     */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*     */ import moze_intel.projecte.api.capabilities.block_entity.IEmcStorage;
/*     */ import moze_intel.projecte.api.capabilities.item.IItemEmcHolder;
/*     */ import moze_intel.projecte.api.event.PlayerAttemptLearnEvent;
/*     */ import moze_intel.projecte.emc.FuelMapper;
/*     */ import moze_intel.projecte.emc.nbt.NBTManager;
/*     */ import moze_intel.projecte.utils.EMCHelper;
/*     */ import moze_intel.projecte.utils.MathUtils;
/*     */ import moze_intel.projecte.utils.PlayerHelper;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.common.util.NonNullSupplier;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.items.IItemHandlerModifiable;
/*     */ import net.minecraftforge.items.ItemStackHandler;
/*     */ import net.minecraftforge.items.wrapper.CombinedInvWrapper;
/*     */ 
/*     */ public class TransmutationInventory
/*     */   extends CombinedInvWrapper {
/*     */   public final Player player;
/*     */   public final IKnowledgeProvider provider;
/*     */   private final IItemHandlerModifiable inputLocks;
/*     */   private final IItemHandlerModifiable learning;
/*     */   public final IItemHandlerModifiable outputs;
/*     */   private static final int LOCK_INDEX = 8;
/*     */   private static final int FUEL_START = 12;
/*  43 */   public int learnFlag = 0;
/*  44 */   public int unlearnFlag = 0;
/*  45 */   public String filter = "";
/*  46 */   public int searchpage = 0;
/*  47 */   private List<ItemInfo> knowledge = Collections.emptyList();
/*     */   
/*     */   public TransmutationInventory(Player player) {
/*  50 */     super(new IItemHandlerModifiable[] { (IItemHandlerModifiable)((IKnowledgeProvider)player.getCapability(PECapabilities.KNOWLEDGE_CAPABILITY).orElseThrow(NullPointerException::new)).getInputAndLocks(), (IItemHandlerModifiable)new ItemStackHandler(2), (IItemHandlerModifiable)new ItemStackHandler(16) });
/*     */     
/*  52 */     this.player = player;
/*  53 */     this.provider = (IKnowledgeProvider)player.getCapability(PECapabilities.KNOWLEDGE_CAPABILITY).orElseThrow(NullPointerException::new);
/*  54 */     this.inputLocks = this.itemHandler[0];
/*  55 */     this.learning = this.itemHandler[1];
/*  56 */     this.outputs = this.itemHandler[2];
/*  57 */     if (!isServer()) {
/*  58 */       updateClientTargets();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isServer() {
/*  63 */     return !(this.player.m_9236_()).f_46443_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleKnowledge(ItemStack stack) {
/*  71 */     if (!stack.m_41619_()) {
/*  72 */       handleKnowledge(ItemInfo.fromStack(stack));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleKnowledge(ItemInfo info) {
/*  80 */     ItemInfo cleanedInfo = NBTManager.getPersistentInfo(info);
/*     */     
/*  82 */     if (!this.provider.hasKnowledge(cleanedInfo) && !MinecraftForge.EVENT_BUS.post((Event)new PlayerAttemptLearnEvent(this.player, info, cleanedInfo)) && 
/*  83 */       this.provider.addKnowledge(cleanedInfo))
/*     */     {
/*  85 */       this.provider.syncKnowledgeChange((ServerPlayer)this.player, cleanedInfo, true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void itemLearned() {
/*  94 */     this.learnFlag = 300;
/*  95 */     this.unlearnFlag = 0;
/*  96 */     updateClientTargets();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleUnlearn(ItemStack stack) {
/* 104 */     if (!stack.m_41619_()) {
/* 105 */       handleUnlearn(ItemInfo.fromStack(stack));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleUnlearn(ItemInfo info) {
/* 113 */     ItemInfo cleanedInfo = NBTManager.getPersistentInfo(info);
/* 114 */     if (this.provider.hasKnowledge(cleanedInfo) && this.provider.removeKnowledge(cleanedInfo))
/*     */     {
/* 116 */       this.provider.syncKnowledgeChange((ServerPlayer)this.player, cleanedInfo, false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void itemUnlearned() {
/* 124 */     this.unlearnFlag = 300;
/* 125 */     this.learnFlag = 0;
/* 126 */     updateClientTargets();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkForUpdates() {
/* 133 */     long matterEmc = EMCHelper.getEmcValue(this.outputs.getStackInSlot(0));
/* 134 */     long fuelEmc = EMCHelper.getEmcValue(this.outputs.getStackInSlot(12));
/* 135 */     if (BigInteger.valueOf(Math.max(matterEmc, fuelEmc)).compareTo(getAvailableEmc()) > 0) {
/* 136 */       updateClientTargets();
/*     */     }
/*     */   }
/*     */   
/*     */   public void updateClientTargets() {
/* 141 */     if (isServer()) {
/*     */       return;
/*     */     }
/* 144 */     this
/*     */ 
/*     */       
/* 147 */       .knowledge = (List<ItemInfo>)this.provider.getKnowledge().stream().filter(EMCHelper::doesItemHaveEmc).sorted(Collections.reverseOrder(Comparator.comparing(EMCHelper::getEmcValue))).collect(Collectors.toList());
/* 148 */     for (int i = 0; i < this.outputs.getSlots(); i++) {
/* 149 */       this.outputs.setStackInSlot(i, ItemStack.f_41583_);
/*     */     }
/*     */     
/* 152 */     int pagecounter = 0;
/* 153 */     int desiredPage = this.searchpage * 12;
/* 154 */     ItemInfo lockInfo = null;
/* 155 */     BigInteger availableEMC = getAvailableEmc();
/* 156 */     if (!this.inputLocks.getStackInSlot(8).m_41619_()) {
/* 157 */       lockInfo = NBTManager.getPersistentInfo(ItemInfo.fromStack(this.inputLocks.getStackInSlot(8)));
/*     */ 
/*     */ 
/*     */       
/* 161 */       long reqEmc = EMCHelper.getEmcValue(lockInfo);
/* 162 */       if (availableEMC.compareTo(BigInteger.valueOf(reqEmc)) < 0) {
/*     */         return;
/*     */       }
/*     */       
/* 166 */       Iterator<ItemInfo> iter = this.knowledge.iterator();
/* 167 */       while (iter.hasNext()) {
/* 168 */         ItemInfo info = iter.next();
/* 169 */         if (EMCHelper.getEmcValue(info) > reqEmc || info.equals(lockInfo) || !doesItemMatchFilter(info)) {
/* 170 */           iter.remove(); continue;
/* 171 */         }  if (pagecounter < desiredPage) {
/* 172 */           pagecounter++;
/* 173 */           iter.remove();
/*     */         } 
/*     */       } 
/*     */     } else {
/* 177 */       Iterator<ItemInfo> iter = this.knowledge.iterator();
/* 178 */       while (iter.hasNext()) {
/* 179 */         ItemInfo info = iter.next();
/* 180 */         if (availableEMC.compareTo(BigInteger.valueOf(EMCHelper.getEmcValue(info))) < 0 || !doesItemMatchFilter(info)) {
/* 181 */           iter.remove(); continue;
/* 182 */         }  if (pagecounter < desiredPage) {
/* 183 */           pagecounter++;
/* 184 */           iter.remove();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 189 */     int matterCounter = 0;
/* 190 */     int fuelCounter = 0;
/*     */     
/* 192 */     if (lockInfo != null && this.provider.hasKnowledge(lockInfo)) {
/* 193 */       ItemStack lockStack = lockInfo.createStack();
/* 194 */       if (FuelMapper.isStackFuel(lockStack)) {
/* 195 */         this.outputs.setStackInSlot(12, lockStack);
/* 196 */         fuelCounter++;
/*     */       } else {
/* 198 */         this.outputs.setStackInSlot(0, lockStack);
/* 199 */         matterCounter++;
/*     */       } 
/*     */     } 
/*     */     
/* 203 */     for (ItemInfo info : this.knowledge) {
/* 204 */       ItemStack stack = info.createStack();
/* 205 */       if (FuelMapper.isStackFuel(stack)) {
/* 206 */         if (fuelCounter < 4) {
/* 207 */           this.outputs.setStackInSlot(12 + fuelCounter, stack);
/* 208 */           fuelCounter++;
/*     */         }  continue;
/* 210 */       }  if (matterCounter < 12) {
/* 211 */         this.outputs.setStackInSlot(matterCounter, stack);
/* 212 */         matterCounter++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean doesItemMatchFilter(ItemInfo info) {
/* 221 */     if (this.filter.isEmpty()) {
/* 222 */       return true;
/*     */     }
/*     */     try {
/* 225 */       return info.createStack().m_41786_().getString().toLowerCase(Locale.ROOT).contains(this.filter);
/* 226 */     } catch (Exception e) {
/* 227 */       PECore.LOGGER.error("Failed to check filter", e);
/*     */       
/* 229 */       return true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeIntoOutputSlot(int slot, ItemStack item) {
/* 237 */     long emcValue = EMCHelper.getEmcValue(item);
/* 238 */     if (emcValue > 0L && BigInteger.valueOf(emcValue).compareTo(getAvailableEmc()) <= 0 && this.provider.hasKnowledge(item)) {
/* 239 */       this.outputs.setStackInSlot(slot, item);
/*     */     } else {
/* 241 */       this.outputs.setStackInSlot(slot, ItemStack.f_41583_);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addEmc(BigInteger value) {
/* 249 */     int compareToZero = value.compareTo(BigInteger.ZERO);
/* 250 */     if (compareToZero == 0) {
/*     */       return;
/*     */     }
/* 253 */     if (compareToZero < 0) {
/*     */       
/* 255 */       removeEmc(value.negate());
/*     */       return;
/*     */     } 
/* 258 */     List<Integer> inputLocksChanged = new ArrayList<>();
/*     */     
/* 260 */     for (int slotIndex = 0; slotIndex < this.inputLocks.getSlots(); slotIndex++) {
/* 261 */       if (slotIndex != 8) {
/*     */ 
/*     */         
/* 264 */         ItemStack stack = this.inputLocks.getStackInSlot(slotIndex);
/* 265 */         if (!stack.m_41619_()) {
/* 266 */           Optional<IItemEmcHolder> holderCapability = stack.getCapability(PECapabilities.EMC_HOLDER_ITEM_CAPABILITY).resolve();
/* 267 */           if (holderCapability.isPresent()) {
/* 268 */             IItemEmcHolder emcHolder = holderCapability.get();
/* 269 */             long shrunkenValue = MathUtils.clampToLong(value);
/* 270 */             long actualInserted = emcHolder.insertEmc(stack, shrunkenValue, IEmcStorage.EmcAction.EXECUTE);
/* 271 */             if (actualInserted > 0L) {
/* 272 */               inputLocksChanged.add(Integer.valueOf(slotIndex));
/* 273 */               value = value.subtract(BigInteger.valueOf(actualInserted));
/* 274 */               if (value.compareTo(BigInteger.ZERO) == 0) {
/*     */                 
/* 276 */                 syncChangedSlots(inputLocksChanged, IKnowledgeProvider.TargetUpdateType.ALL); return;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 283 */     syncChangedSlots(inputLocksChanged, IKnowledgeProvider.TargetUpdateType.NONE);
/*     */ 
/*     */     
/* 286 */     updateEmcAndSync(this.provider.getEmc().add(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeEmc(BigInteger value) {
/* 293 */     int compareToZero = value.compareTo(BigInteger.ZERO);
/* 294 */     if (compareToZero == 0) {
/*     */       return;
/*     */     }
/* 297 */     if (compareToZero < 0) {
/*     */       
/* 299 */       addEmc(value.negate());
/*     */       return;
/*     */     } 
/* 302 */     BigInteger currentEmc = this.provider.getEmc();
/*     */ 
/*     */     
/* 305 */     if (value.compareTo(currentEmc) > 0) {
/*     */ 
/*     */ 
/*     */       
/* 309 */       List<Integer> inputLocksChanged = new ArrayList<>();
/* 310 */       BigInteger toRemove = value.subtract(currentEmc);
/* 311 */       value = currentEmc;
/* 312 */       for (int slotIndex = 0; slotIndex < this.inputLocks.getSlots(); slotIndex++) {
/* 313 */         if (slotIndex != 8) {
/*     */ 
/*     */           
/* 316 */           ItemStack stack = this.inputLocks.getStackInSlot(slotIndex);
/* 317 */           if (!stack.m_41619_()) {
/* 318 */             Optional<IItemEmcHolder> holderCapability = stack.getCapability(PECapabilities.EMC_HOLDER_ITEM_CAPABILITY).resolve();
/* 319 */             if (holderCapability.isPresent()) {
/* 320 */               IItemEmcHolder emcHolder = holderCapability.get();
/* 321 */               long shrunkenToRemove = MathUtils.clampToLong(toRemove);
/* 322 */               long actualExtracted = emcHolder.extractEmc(stack, shrunkenToRemove, IEmcStorage.EmcAction.EXECUTE);
/* 323 */               if (actualExtracted > 0L) {
/* 324 */                 inputLocksChanged.add(Integer.valueOf(slotIndex));
/* 325 */                 toRemove = toRemove.subtract(BigInteger.valueOf(actualExtracted));
/* 326 */                 if (toRemove.compareTo(BigInteger.ZERO) == 0) {
/*     */ 
/*     */                   
/* 329 */                   syncChangedSlots(inputLocksChanged, IKnowledgeProvider.TargetUpdateType.IF_NEEDED);
/* 330 */                   if (currentEmc.compareTo(BigInteger.ZERO) > 0) {
/* 331 */                     updateEmcAndSync(BigInteger.ZERO);
/*     */                   }
/*     */                   return;
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 340 */       syncChangedSlots(inputLocksChanged, IKnowledgeProvider.TargetUpdateType.NONE);
/*     */     } 
/* 342 */     updateEmcAndSync(currentEmc.subtract(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void syncChangedSlots(List<Integer> slotsChanged, IKnowledgeProvider.TargetUpdateType updateTargets) {
/* 349 */     this.provider.syncInputAndLocks((ServerPlayer)this.player, slotsChanged, updateTargets);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateEmcAndSync(BigInteger emc) {
/* 356 */     if (emc.compareTo(BigInteger.ZERO) < 0)
/*     */     {
/* 358 */       emc = BigInteger.ZERO;
/*     */     }
/* 360 */     this.provider.setEmc(emc);
/* 361 */     this.provider.syncEmc((ServerPlayer)this.player);
/* 362 */     PlayerHelper.updateScore((ServerPlayer)this.player, PlayerHelper.SCOREBOARD_EMC, emc);
/*     */   }
/*     */   
/*     */   public IItemHandlerModifiable getHandlerForSlot(int slot) {
/* 366 */     return getHandlerFromIndex(getIndexForSlot(slot));
/*     */   }
/*     */   
/*     */   public int getIndexFromSlot(int slot) {
/* 370 */     for (IItemHandlerModifiable h : this.itemHandler) {
/* 371 */       if (slot >= h.getSlots()) {
/* 372 */         slot -= h.getSlots();
/*     */       }
/*     */     } 
/* 375 */     return slot;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BigInteger getAvailableEmc() {
/* 382 */     BigInteger emc = this.provider.getEmc();
/* 383 */     for (int i = 0; i < this.inputLocks.getSlots(); i++) {
/* 384 */       if (i != 8) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 389 */         ItemStack stack = this.inputLocks.getStackInSlot(i);
/* 390 */         if (!stack.m_41619_()) {
/* 391 */           Optional<IItemEmcHolder> emcHolder = stack.getCapability(PECapabilities.EMC_HOLDER_ITEM_CAPABILITY).resolve();
/* 392 */           if (emcHolder.isPresent())
/* 393 */             emc = emc.add(BigInteger.valueOf(((IItemEmcHolder)emcHolder.get()).getStoredEmc(stack))); 
/*     */         } 
/*     */       } 
/*     */     } 
/* 397 */     return emc;
/*     */   }
/*     */   
/*     */   public int getKnowledgeSize() {
/* 401 */     return this.knowledge.size();
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\inventory\TransmutationInventory.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */