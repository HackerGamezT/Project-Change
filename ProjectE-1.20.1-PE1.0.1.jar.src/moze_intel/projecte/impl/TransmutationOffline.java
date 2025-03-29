/*     */ package moze_intel.projecte.impl;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.math.BigInteger;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import moze_intel.projecte.PECore;
/*     */ import moze_intel.projecte.api.ItemInfo;
/*     */ import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
/*     */ import moze_intel.projecte.impl.capability.KnowledgeImpl;
/*     */ import moze_intel.projecte.utils.ItemHelper;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.NbtIo;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.storage.LevelResource;
/*     */ import net.minecraftforge.fml.util.thread.SidedThreadGroups;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import net.minecraftforge.items.IItemHandlerModifiable;
/*     */ import net.minecraftforge.server.ServerLifecycleHooks;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class TransmutationOffline {
/*  33 */   private static final IKnowledgeProvider NOT_FOUND_PROVIDER = immutableCopy(KnowledgeImpl.getDefault());
/*     */   
/*  35 */   private static final Map<UUID, IKnowledgeProvider> cachedKnowledgeProviders = new HashMap<>();
/*     */   
/*     */   public static void cleanAll() {
/*  38 */     cachedKnowledgeProviders.clear();
/*     */   }
/*     */   
/*     */   public static void clear(UUID playerUUID) {
/*  42 */     cachedKnowledgeProviders.remove(playerUUID);
/*     */   }
/*     */   
/*     */   static IKnowledgeProvider forPlayer(UUID playerUUID) {
/*  46 */     if (!cachedKnowledgeProviders.containsKey(playerUUID) && 
/*  47 */       !cacheOfflineData(playerUUID)) {
/*  48 */       cachedKnowledgeProviders.put(playerUUID, NOT_FOUND_PROVIDER);
/*     */     }
/*     */ 
/*     */     
/*  52 */     return cachedKnowledgeProviders.get(playerUUID);
/*     */   }
/*     */   
/*     */   private static boolean cacheOfflineData(UUID playerUUID) {
/*  56 */     Preconditions.checkState((Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER), "CRITICAL: Trying to read filesystem on client!!");
/*  57 */     MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
/*  58 */     File playerData = server.m_129843_(LevelResource.f_78176_).toFile();
/*  59 */     if (playerData.exists()) {
/*  60 */       File player = new File(playerData, playerUUID.toString() + ".dat");
/*  61 */       if (player.exists() && player.isFile()) {
/*  62 */         try { FileInputStream in = new FileInputStream(player); 
/*  63 */           try { CompoundTag playerDat = NbtIo.m_128939_(in);
/*  64 */             CompoundTag knowledgeProvider = playerDat.m_128469_("ForgeCaps").m_128469_(KnowledgeImpl.Provider.NAME.toString());
/*     */             
/*  66 */             IKnowledgeProvider provider = KnowledgeImpl.getDefault();
/*  67 */             provider.deserializeNBT((Tag)knowledgeProvider);
/*  68 */             cachedKnowledgeProviders.put(playerUUID, immutableCopy(provider));
/*     */             
/*  70 */             PECore.debugLog("Caching offline data for UUID: {}", new Object[] { playerUUID });
/*  71 */             boolean bool = true;
/*  72 */             in.close(); return bool; } catch (Throwable throwable) { try { in.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (IOException e)
/*  73 */         { PECore.LOGGER.warn("Failed to cache offline data for API calls for UUID: {}", playerUUID); }
/*     */       
/*     */       }
/*     */     } 
/*     */     
/*  78 */     return false;
/*     */   }
/*     */   
/*     */   private static IKnowledgeProvider immutableCopy(final IKnowledgeProvider toCopy) {
/*  82 */     return new IKnowledgeProvider() {
/*  83 */         final Set<ItemInfo> immutableKnowledge = (Set<ItemInfo>)ImmutableSet.copyOf(toCopy.getKnowledge());
/*  84 */         final IItemHandlerModifiable immutableInputLocks = ItemHelper.immutableCopy(toCopy.getInputAndLocks());
/*     */ 
/*     */         
/*     */         public boolean hasFullKnowledge() {
/*  88 */           return toCopy.hasFullKnowledge();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void setFullKnowledge(boolean fullKnowledge) {}
/*     */ 
/*     */ 
/*     */         
/*     */         public void clearKnowledge() {}
/*     */ 
/*     */         
/*     */         public boolean hasKnowledge(@NotNull ItemInfo info) {
/* 101 */           return toCopy.hasKnowledge(info);
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean addKnowledge(@NotNull ItemInfo info) {
/* 106 */           return false;
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean removeKnowledge(@NotNull ItemInfo info) {
/* 111 */           return false;
/*     */         }
/*     */ 
/*     */         
/*     */         @NotNull
/*     */         public Set<ItemInfo> getKnowledge() {
/* 117 */           return this.immutableKnowledge;
/*     */         }
/*     */ 
/*     */         
/*     */         @NotNull
/*     */         public IItemHandler getInputAndLocks() {
/* 123 */           return (IItemHandler)this.immutableInputLocks;
/*     */         }
/*     */ 
/*     */         
/*     */         public BigInteger getEmc() {
/* 128 */           return toCopy.getEmc();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void setEmc(BigInteger emc) {}
/*     */ 
/*     */         
/*     */         public void sync(@NotNull ServerPlayer player) {
/* 137 */           toCopy.sync(player);
/*     */         }
/*     */ 
/*     */         
/*     */         public void syncEmc(@NotNull ServerPlayer player) {
/* 142 */           toCopy.syncEmc(player);
/*     */         }
/*     */ 
/*     */         
/*     */         public void syncKnowledgeChange(@NotNull ServerPlayer player, ItemInfo change, boolean learned) {
/* 147 */           toCopy.syncKnowledgeChange(player, change, learned);
/*     */         }
/*     */ 
/*     */         
/*     */         public void syncInputAndLocks(@NotNull ServerPlayer player, List<Integer> slotsChanged, IKnowledgeProvider.TargetUpdateType updateTargets) {
/* 152 */           toCopy.syncInputAndLocks(player, slotsChanged, updateTargets);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void receiveInputsAndLocks(Map<Integer, ItemStack> changes) {}
/*     */ 
/*     */         
/*     */         public CompoundTag serializeNBT() {
/* 161 */           return (CompoundTag)toCopy.serializeNBT();
/*     */         }
/*     */         
/*     */         public void deserializeNBT(CompoundTag nbt) {}
/*     */       };
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\impl\TransmutationOffline.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */