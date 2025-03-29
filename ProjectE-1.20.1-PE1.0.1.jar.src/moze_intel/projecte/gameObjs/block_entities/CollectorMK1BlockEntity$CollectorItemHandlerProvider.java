/*     */ package moze_intel.projecte.gameObjs.block_entities;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class CollectorItemHandlerProvider
/*     */   extends SidedItemHandlerResolver
/*     */ {
/*     */   private final ICapabilityResolver<IItemHandler> automationAuxSlots;
/*     */   private final ICapabilityResolver<IItemHandler> automationInput;
/*     */   private final ICapabilityResolver<IItemHandler> joined;
/*     */   
/*     */   protected CollectorItemHandlerProvider() {
/* 326 */     NonNullLazy<IItemHandler> automationInput = NonNullLazy.of(() -> new WrappedItemHandler((IItemHandlerModifiable)CollectorMK1BlockEntity.this.input, WrappedItemHandler.WriteMode.IN)
/*     */         {
/*     */           @NotNull
/*     */           public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
/* 330 */             return SlotPredicates.COLLECTOR_INV.test(stack) ? super.insertItem(slot, stack, simulate) : stack;
/*     */           }
/*     */         });
/* 333 */     NonNullLazy<IItemHandler> automationAuxSlots = NonNullLazy.of(() -> new WrappedItemHandler((IItemHandlerModifiable)CollectorMK1BlockEntity.this.auxSlots, WrappedItemHandler.WriteMode.OUT)
/*     */         {
/*     */           @NotNull
/*     */           public ItemStack extractItem(int slot, int count, boolean simulate) {
/* 337 */             if (slot == 1) {
/* 338 */               return super.extractItem(slot, count, simulate);
/*     */             }
/* 340 */             return ItemStack.f_41583_;
/*     */           }
/*     */         });
/* 343 */     this.automationInput = BasicCapabilityResolver.getBasicItemHandlerResolver((NonNullSupplier)automationInput);
/* 344 */     this.automationAuxSlots = BasicCapabilityResolver.getBasicItemHandlerResolver((NonNullSupplier)automationAuxSlots);
/* 345 */     this.joined = BasicCapabilityResolver.getBasicItemHandlerResolver(() -> new CombinedInvWrapper(new IItemHandlerModifiable[] { (IItemHandlerModifiable)automationInput.get(), (IItemHandlerModifiable)automationAuxSlots.get() }));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ICapabilityResolver<IItemHandler> getResolver(@Nullable Direction side) {
/* 351 */     if (side == null)
/* 352 */       return this.joined; 
/* 353 */     if (side.m_122434_().m_122478_()) {
/* 354 */       return this.automationAuxSlots;
/*     */     }
/* 356 */     return this.automationInput;
/*     */   }
/*     */ 
/*     */   
/*     */   public void invalidateAll() {
/* 361 */     this.joined.invalidateAll();
/* 362 */     this.automationInput.invalidateAll();
/* 363 */     this.automationAuxSlots.invalidateAll();
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\block_entities\CollectorMK1BlockEntity$CollectorItemHandlerProvider.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */