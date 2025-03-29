/*    */ package moze_intel.projecte.gameObjs.container.slots;
/*    */ 
/*    */ import net.minecraft.world.Container;
/*    */ import net.minecraft.world.inventory.Slot;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ 
/*    */ public class InsertableSlot
/*    */   extends Slot
/*    */   implements IInsertableSlot
/*    */ {
/*    */   public InsertableSlot(Container inventory, int index, int x, int y) {
/* 13 */     super(inventory, index, x, y);
/*    */   }
/*    */ 
/*    */   
/*    */   public int m_5866_(ItemStack stack) {
/* 18 */     return Math.min(m_6641_(), stack.m_41741_());
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\slots\InsertableSlot.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */