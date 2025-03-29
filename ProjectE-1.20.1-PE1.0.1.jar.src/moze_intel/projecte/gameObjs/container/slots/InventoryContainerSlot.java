/*    */ package moze_intel.projecte.gameObjs.container.slots;
/*    */ 
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import net.minecraftforge.items.SlotItemHandler;
/*    */ 
/*    */ public class InventoryContainerSlot
/*    */   extends SlotItemHandler implements IInsertableSlot {
/*    */   public InventoryContainerSlot(IItemHandler itemHandler, int index, int x, int y) {
/* 10 */     super(itemHandler, index, x, y);
/*    */   }
/*    */ 
/*    */   
/*    */   public int m_5866_(ItemStack stack) {
/* 15 */     return Math.min(m_6641_(), stack.m_41741_());
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\slots\InventoryContainerSlot.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */