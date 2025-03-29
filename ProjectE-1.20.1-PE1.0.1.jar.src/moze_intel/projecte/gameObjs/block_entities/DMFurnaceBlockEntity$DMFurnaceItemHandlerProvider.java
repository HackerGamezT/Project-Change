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
/*     */ class DMFurnaceItemHandlerProvider
/*     */   extends SidedItemHandlerResolver
/*     */ {
/*     */   private final ICapabilityResolver<IItemHandler> joined;
/*     */   private final ICapabilityResolver<IItemHandler> automationInput;
/*     */   private final ICapabilityResolver<IItemHandler> automationOutput;
/*     */   private final ICapabilityResolver<IItemHandler> automationSides;
/*     */   
/*     */   protected DMFurnaceItemHandlerProvider() {
/* 321 */     NonNullLazy<IItemHandler> automationInput = NonNullLazy.of(() -> new WrappedItemHandler((IItemHandlerModifiable)DMFurnaceBlockEntity.this.inputInventory, WrappedItemHandler.WriteMode.IN)
/*     */         {
/*     */           @NotNull
/*     */           public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
/* 325 */             return !DMFurnaceBlockEntity.this.getSmeltingResult(stack).m_41619_() ? super.insertItem(slot, stack, simulate) : stack;
/*     */           }
/*     */         });
/* 328 */     NonNullLazy<IItemHandlerModifiable> automationFuel = NonNullLazy.of(() -> new WrappedItemHandler((IItemHandlerModifiable)DMFurnaceBlockEntity.this.fuelInv, WrappedItemHandler.WriteMode.IN)
/*     */         {
/*     */           @NotNull
/*     */           public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
/* 332 */             return SlotPredicates.FURNACE_FUEL.test(stack) ? super.insertItem(slot, stack, simulate) : stack;
/*     */           }
/*     */         });
/* 335 */     NonNullLazy<IItemHandler> automationOutput = NonNullLazy.of(() -> new WrappedItemHandler((IItemHandlerModifiable)DMFurnaceBlockEntity.this.outputInventory, WrappedItemHandler.WriteMode.OUT));
/* 336 */     this.joined = BasicCapabilityResolver.getBasicItemHandlerResolver(() -> new CombinedInvWrapper(new IItemHandlerModifiable[] { (IItemHandlerModifiable)automationInput.get(), (IItemHandlerModifiable)automationFuel.get(), (IItemHandlerModifiable)automationOutput.get() }));
/*     */     
/* 338 */     this.automationInput = BasicCapabilityResolver.getBasicItemHandlerResolver((NonNullSupplier)automationInput);
/* 339 */     this.automationOutput = BasicCapabilityResolver.getBasicItemHandlerResolver((NonNullSupplier)automationOutput);
/* 340 */     this.automationSides = BasicCapabilityResolver.getBasicItemHandlerResolver(() -> new CombinedInvWrapper(new IItemHandlerModifiable[] { (IItemHandlerModifiable)automationFuel.get(), (IItemHandlerModifiable)automationOutput.get() }));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ICapabilityResolver<IItemHandler> getResolver(@Nullable Direction side) {
/* 346 */     if (side == null)
/* 347 */       return this.joined; 
/* 348 */     if (side == Direction.UP)
/* 349 */       return this.automationInput; 
/* 350 */     if (side == Direction.DOWN) {
/* 351 */       return this.automationOutput;
/*     */     }
/* 353 */     return this.automationSides;
/*     */   }
/*     */ 
/*     */   
/*     */   public void invalidateAll() {
/* 358 */     this.joined.invalidateAll();
/* 359 */     this.automationInput.invalidateAll();
/* 360 */     this.automationOutput.invalidateAll();
/* 361 */     this.automationSides.invalidateAll();
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\block_entities\DMFurnaceBlockEntity$DMFurnaceItemHandlerProvider.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */