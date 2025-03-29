/*    */ package moze_intel.projecte.gameObjs.block_entities;
/*    */ 
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.items.IItemHandlerModifiable;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WrappedItemHandler
/*    */   implements IItemHandlerModifiable
/*    */ {
/*    */   private final IItemHandlerModifiable compose;
/*    */   private final WriteMode mode;
/*    */   
/*    */   public WrappedItemHandler(IItemHandlerModifiable compose, WriteMode mode) {
/* 17 */     this.compose = compose;
/* 18 */     this.mode = mode;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSlots() {
/* 23 */     return this.compose.getSlots();
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack getStackInSlot(int slot) {
/* 29 */     return this.compose.getStackInSlot(slot);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
/* 35 */     if (this.mode == WriteMode.IN || this.mode == WriteMode.IN_OUT) {
/* 36 */       return this.compose.insertItem(slot, stack, simulate);
/*    */     }
/* 38 */     return stack;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack extractItem(int slot, int amount, boolean simulate) {
/* 44 */     if (this.mode == WriteMode.OUT || this.mode == WriteMode.IN_OUT) {
/* 45 */       return this.compose.extractItem(slot, amount, simulate);
/*    */     }
/* 47 */     return ItemStack.f_41583_;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSlotLimit(int slot) {
/* 52 */     return this.compose.getSlotLimit(slot);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isItemValid(int slot, @NotNull ItemStack stack) {
/* 57 */     return this.compose.isItemValid(slot, stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setStackInSlot(int slot, @NotNull ItemStack stack) {
/* 62 */     this.compose.setStackInSlot(slot, stack);
/*    */   }
/*    */   
/*    */   public enum WriteMode {
/* 66 */     IN,
/* 67 */     OUT,
/* 68 */     IN_OUT,
/* 69 */     NONE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\block_entities\WrappedItemHandler.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */