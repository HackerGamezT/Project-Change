/*     */ package moze_intel.projecte.gameObjs.block_entities;
/*     */ 
/*     */ import java.util.Optional;
/*     */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*     */ import moze_intel.projecte.api.capabilities.item.IItemEmcHolder;
/*     */ import moze_intel.projecte.capability.managing.BasicCapabilityResolver;
/*     */ import moze_intel.projecte.capability.managing.ICapabilityResolver;
/*     */ import moze_intel.projecte.capability.managing.SidedItemHandlerResolver;
/*     */ import moze_intel.projecte.gameObjs.container.slots.SlotPredicates;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.common.util.NonNullLazy;
/*     */ import net.minecraftforge.common.util.NonNullSupplier;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import net.minecraftforge.items.IItemHandlerModifiable;
/*     */ import net.minecraftforge.items.wrapper.CombinedInvWrapper;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class RelayItemHandlerProvider
/*     */   extends SidedItemHandlerResolver
/*     */ {
/*     */   private final ICapabilityResolver<IItemHandler> automationOutput;
/*     */   private final ICapabilityResolver<IItemHandler> automationInput;
/*     */   private final ICapabilityResolver<IItemHandler> joined;
/*     */   
/*     */   protected RelayItemHandlerProvider() {
/* 203 */     NonNullLazy<IItemHandler> automationInput = NonNullLazy.of(() -> new WrappedItemHandler((IItemHandlerModifiable)RelayMK1BlockEntity.this.input, WrappedItemHandler.WriteMode.IN));
/* 204 */     NonNullLazy<IItemHandler> automationOutput = NonNullLazy.of(() -> new WrappedItemHandler((IItemHandlerModifiable)RelayMK1BlockEntity.this.output, WrappedItemHandler.WriteMode.IN_OUT)
/*     */         {
/*     */           @NotNull
/*     */           public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
/* 208 */             return SlotPredicates.EMC_HOLDER.test(stack) ? super.insertItem(slot, stack, simulate) : stack;
/*     */           }
/*     */ 
/*     */           
/*     */           @NotNull
/*     */           public ItemStack extractItem(int slot, int amount, boolean simulate) {
/* 214 */             ItemStack stack = getStackInSlot(slot);
/* 215 */             if (!stack.m_41619_()) {
/* 216 */               Optional<IItemEmcHolder> holderCapability = stack.getCapability(PECapabilities.EMC_HOLDER_ITEM_CAPABILITY).resolve();
/* 217 */               if (holderCapability.isPresent()) {
/* 218 */                 IItemEmcHolder emcHolder = holderCapability.get();
/* 219 */                 if (emcHolder.getNeededEmc(stack) == 0L) {
/* 220 */                   return super.extractItem(slot, amount, simulate);
/*     */                 }
/* 222 */                 return ItemStack.f_41583_;
/*     */               } 
/*     */             } 
/* 225 */             return super.extractItem(slot, amount, simulate);
/*     */           }
/*     */         });
/* 228 */     this.automationInput = BasicCapabilityResolver.getBasicItemHandlerResolver((NonNullSupplier)automationInput);
/* 229 */     this.automationOutput = BasicCapabilityResolver.getBasicItemHandlerResolver((NonNullSupplier)automationOutput);
/* 230 */     this.joined = BasicCapabilityResolver.getBasicItemHandlerResolver(() -> new CombinedInvWrapper(new IItemHandlerModifiable[] { (IItemHandlerModifiable)automationInput.get(), (IItemHandlerModifiable)automationOutput.get() }));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ICapabilityResolver<IItemHandler> getResolver(@Nullable Direction side) {
/* 236 */     if (side == null)
/* 237 */       return this.joined; 
/* 238 */     if (side.m_122434_().m_122478_()) {
/* 239 */       return this.automationOutput;
/*     */     }
/* 241 */     return this.automationInput;
/*     */   }
/*     */ 
/*     */   
/*     */   public void invalidateAll() {
/* 246 */     this.joined.invalidateAll();
/* 247 */     this.automationInput.invalidateAll();
/* 248 */     this.automationOutput.invalidateAll();
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\block_entities\RelayMK1BlockEntity$RelayItemHandlerProvider.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */