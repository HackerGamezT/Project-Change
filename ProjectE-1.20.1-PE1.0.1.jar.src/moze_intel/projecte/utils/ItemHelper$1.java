/*     */ package moze_intel.projecte.utils;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.world.item.ItemStack;
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
/*     */   implements IItemHandlerModifiable
/*     */ {
/*     */   public void setStackInSlot(int slot, @NotNull ItemStack stack) {}
/*     */   
/*     */   public int getSlots() {
/* 134 */     return list.size();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack getStackInSlot(int slot) {
/* 140 */     return list.get(slot);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
/* 146 */     return stack;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack extractItem(int slot, int amount, boolean simulate) {
/* 152 */     return ItemStack.f_41583_;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSlotLimit(int slot) {
/* 157 */     return getStackInSlot(slot).m_41741_();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isItemValid(int slot, @NotNull ItemStack stack) {
/* 162 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\project\\utils\ItemHelper$1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */