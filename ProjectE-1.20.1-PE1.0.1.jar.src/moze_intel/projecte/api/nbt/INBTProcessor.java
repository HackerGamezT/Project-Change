/*    */ package moze_intel.projecte.api.nbt;
/*    */ 
/*    */ import moze_intel.projecte.api.ItemInfo;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface INBTProcessor
/*    */ {
/*    */   String getName();
/*    */   
/*    */   String getDescription();
/*    */   
/*    */   default boolean isAvailable() {
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default boolean hasPersistentNBT() {
/* 44 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default boolean usePersistentNBT() {
/* 56 */     return hasPersistentNBT();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   long recalculateEMC(@NotNull ItemInfo paramItemInfo, long paramLong) throws ArithmeticException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   default CompoundTag getPersistentNBT(@NotNull ItemInfo info) {
/* 82 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\nbt\INBTProcessor.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */