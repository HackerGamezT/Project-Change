/*     */ package moze_intel.projecte.impl;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import java.math.BigInteger;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import moze_intel.projecte.api.ItemInfo;
/*     */ import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
/*     */ import moze_intel.projecte.utils.ItemHelper;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import net.minecraftforge.items.IItemHandlerModifiable;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class null
/*     */   implements IKnowledgeProvider
/*     */ {
/*  83 */   final Set<ItemInfo> immutableKnowledge = (Set<ItemInfo>)ImmutableSet.copyOf(toCopy.getKnowledge());
/*  84 */   final IItemHandlerModifiable immutableInputLocks = ItemHelper.immutableCopy(toCopy.getInputAndLocks());
/*     */ 
/*     */   
/*     */   public boolean hasFullKnowledge() {
/*  88 */     return toCopy.hasFullKnowledge();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFullKnowledge(boolean fullKnowledge) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearKnowledge() {}
/*     */ 
/*     */   
/*     */   public boolean hasKnowledge(@NotNull ItemInfo info) {
/* 101 */     return toCopy.hasKnowledge(info);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addKnowledge(@NotNull ItemInfo info) {
/* 106 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removeKnowledge(@NotNull ItemInfo info) {
/* 111 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Set<ItemInfo> getKnowledge() {
/* 117 */     return this.immutableKnowledge;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public IItemHandler getInputAndLocks() {
/* 123 */     return (IItemHandler)this.immutableInputLocks;
/*     */   }
/*     */ 
/*     */   
/*     */   public BigInteger getEmc() {
/* 128 */     return toCopy.getEmc();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEmc(BigInteger emc) {}
/*     */ 
/*     */   
/*     */   public void sync(@NotNull ServerPlayer player) {
/* 137 */     toCopy.sync(player);
/*     */   }
/*     */ 
/*     */   
/*     */   public void syncEmc(@NotNull ServerPlayer player) {
/* 142 */     toCopy.syncEmc(player);
/*     */   }
/*     */ 
/*     */   
/*     */   public void syncKnowledgeChange(@NotNull ServerPlayer player, ItemInfo change, boolean learned) {
/* 147 */     toCopy.syncKnowledgeChange(player, change, learned);
/*     */   }
/*     */ 
/*     */   
/*     */   public void syncInputAndLocks(@NotNull ServerPlayer player, List<Integer> slotsChanged, IKnowledgeProvider.TargetUpdateType updateTargets) {
/* 152 */     toCopy.syncInputAndLocks(player, slotsChanged, updateTargets);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void receiveInputsAndLocks(Map<Integer, ItemStack> changes) {}
/*     */ 
/*     */   
/*     */   public CompoundTag serializeNBT() {
/* 161 */     return (CompoundTag)toCopy.serializeNBT();
/*     */   }
/*     */   
/*     */   public void deserializeNBT(CompoundTag nbt) {}
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\impl\TransmutationOffline$1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */