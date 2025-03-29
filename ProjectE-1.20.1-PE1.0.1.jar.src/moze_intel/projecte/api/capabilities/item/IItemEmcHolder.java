/*    */ package moze_intel.projecte.api.capabilities.item;
/*    */ 
/*    */ import moze_intel.projecte.api.capabilities.block_entity.IEmcStorage;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ public interface IItemEmcHolder
/*    */ {
/*    */   long insertEmc(@NotNull ItemStack paramItemStack, long paramLong, IEmcStorage.EmcAction paramEmcAction);
/*    */   
/*    */   long extractEmc(@NotNull ItemStack paramItemStack, long paramLong, IEmcStorage.EmcAction paramEmcAction);
/*    */   
/*    */   long getStoredEmc(@NotNull ItemStack paramItemStack);
/*    */   
/*    */   long getMaximumEmc(@NotNull ItemStack paramItemStack);
/*    */   
/*    */   default long getNeededEmc(@NotNull ItemStack stack) {
/* 73 */     return Math.max(0L, getMaximumEmc(stack) - getStoredEmc(stack));
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\capabilities\item\IItemEmcHolder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */