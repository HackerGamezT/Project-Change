/*     */ package moze_intel.projecte.api.capabilities;
/*     */ 
/*     */ import java.math.BigInteger;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import moze_intel.projecte.api.ItemInfo;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.common.util.INBTSerializable;
/*     */ import net.minecraftforge.items.IItemHandler;
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
/*     */ public interface IKnowledgeProvider
/*     */   extends INBTSerializable<CompoundTag>
/*     */ {
/*     */   boolean hasFullKnowledge();
/*     */   
/*     */   void setFullKnowledge(boolean paramBoolean);
/*     */   
/*     */   void clearKnowledge();
/*     */   
/*     */   default boolean hasKnowledge(@NotNull ItemStack stack) {
/*  47 */     return (!stack.m_41619_() && hasKnowledge(ItemInfo.fromStack(stack)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean hasKnowledge(@NotNull ItemInfo paramItemInfo);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default boolean addKnowledge(@NotNull ItemStack stack) {
/*  66 */     return (!stack.m_41619_() && addKnowledge(ItemInfo.fromStack(stack)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean addKnowledge(@NotNull ItemInfo paramItemInfo);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default boolean removeKnowledge(@NotNull ItemStack stack) {
/*  85 */     return (!stack.m_41619_() && removeKnowledge(ItemInfo.fromStack(stack)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean removeKnowledge(@NotNull ItemInfo paramItemInfo);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   Set<ItemInfo> getKnowledge();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   IItemHandler getInputAndLocks();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   BigInteger getEmc();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setEmc(BigInteger paramBigInteger);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void sync(@NotNull ServerPlayer paramServerPlayer);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void syncEmc(@NotNull ServerPlayer paramServerPlayer);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void syncKnowledgeChange(@NotNull ServerPlayer paramServerPlayer, ItemInfo paramItemInfo, boolean paramBoolean);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void syncInputAndLocks(@NotNull ServerPlayer paramServerPlayer, List<Integer> paramList, TargetUpdateType paramTargetUpdateType);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void receiveInputsAndLocks(Map<Integer, ItemStack> paramMap);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum TargetUpdateType
/*     */   {
/* 160 */     NONE,
/*     */ 
/*     */ 
/*     */     
/* 164 */     IF_NEEDED,
/*     */ 
/*     */ 
/*     */     
/* 168 */     ALL;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\capabilities\IKnowledgeProvider.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */