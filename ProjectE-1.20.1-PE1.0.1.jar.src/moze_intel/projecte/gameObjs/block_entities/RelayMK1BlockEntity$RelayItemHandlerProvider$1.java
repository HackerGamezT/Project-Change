/*     */ package moze_intel.projecte.gameObjs.block_entities;
/*     */ 
/*     */ import java.util.Optional;
/*     */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*     */ import moze_intel.projecte.api.capabilities.item.IItemEmcHolder;
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
/*     */   extends WrappedItemHandler
/*     */ {
/*     */   null(IItemHandlerModifiable compose, WrappedItemHandler.WriteMode mode) {
/* 204 */     super(compose, mode);
/*     */   }
/*     */   @NotNull
/*     */   public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
/* 208 */     return SlotPredicates.EMC_HOLDER.test(stack) ? super.insertItem(slot, stack, simulate) : stack;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack extractItem(int slot, int amount, boolean simulate) {
/* 214 */     ItemStack stack = getStackInSlot(slot);
/* 215 */     if (!stack.m_41619_()) {
/* 216 */       Optional<IItemEmcHolder> holderCapability = stack.getCapability(PECapabilities.EMC_HOLDER_ITEM_CAPABILITY).resolve();
/* 217 */       if (holderCapability.isPresent()) {
/* 218 */         IItemEmcHolder emcHolder = holderCapability.get();
/* 219 */         if (emcHolder.getNeededEmc(stack) == 0L) {
/* 220 */           return super.extractItem(slot, amount, simulate);
/*     */         }
/* 222 */         return ItemStack.f_41583_;
/*     */       } 
/*     */     } 
/* 225 */     return super.extractItem(slot, amount, simulate);
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\block_entities\RelayMK1BlockEntity$RelayItemHandlerProvider$1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */