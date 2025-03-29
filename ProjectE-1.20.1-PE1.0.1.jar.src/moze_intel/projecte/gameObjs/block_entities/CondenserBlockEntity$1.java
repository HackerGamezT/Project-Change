/*     */ package moze_intel.projecte.gameObjs.block_entities;
/*     */ 
/*     */ import moze_intel.projecte.gameObjs.container.slots.SlotPredicates;
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
/*     */ class null
/*     */   extends WrappedItemHandler
/*     */ {
/*     */   null(IItemHandlerModifiable compose, WrappedItemHandler.WriteMode mode) {
/*  89 */     super(compose, mode);
/*     */   }
/*     */   @NotNull
/*     */   public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
/*  93 */     return (SlotPredicates.HAS_EMC.test(stack) && !CondenserBlockEntity.this.isStackEqualToLock(stack)) ? super.insertItem(slot, stack, simulate) : stack;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack extractItem(int slot, int max, boolean simulate) {
/*  99 */     if (!getStackInSlot(slot).m_41619_() && CondenserBlockEntity.this.isStackEqualToLock(getStackInSlot(slot))) {
/* 100 */       return super.extractItem(slot, max, simulate);
/*     */     }
/* 102 */     return ItemStack.f_41583_;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\block_entities\CondenserBlockEntity$1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */