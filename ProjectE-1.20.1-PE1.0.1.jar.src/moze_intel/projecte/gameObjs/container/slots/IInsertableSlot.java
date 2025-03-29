/*    */ package moze_intel.projecte.gameObjs.container.slots;
/*    */ 
/*    */ import moze_intel.projecte.utils.ItemHelper;
/*    */ import net.minecraft.world.inventory.Slot;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.items.ItemHandlerHelper;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IInsertableSlot
/*    */ {
/*    */   private Slot self() {
/* 15 */     return (Slot)this;
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
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   default ItemStack insertItem(@NotNull ItemStack stack, boolean simulate) {
/* 34 */     Slot self = self();
/* 35 */     if (stack.m_41619_() || !self.m_5857_(stack))
/*    */     {
/* 37 */       return stack;
/*    */     }
/* 39 */     ItemStack current = self.m_7993_();
/* 40 */     int needed = self.m_5866_(stack) - current.m_41613_();
/* 41 */     if (needed <= 0)
/*    */     {
/* 43 */       return stack;
/*    */     }
/* 45 */     if (current.m_41619_() || ItemHandlerHelper.canItemStacksStack(current, stack)) {
/* 46 */       int toAdd = Math.min(stack.m_41613_(), needed);
/* 47 */       if (!simulate)
/*    */       {
/*    */         
/* 50 */         self.m_5852_(stack.m_255036_(current.m_41613_() + toAdd));
/*    */       }
/* 52 */       return ItemHelper.size(stack, stack.m_41613_() - toAdd);
/*    */     } 
/*    */     
/* 55 */     return stack;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\slots\IInsertableSlot.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */