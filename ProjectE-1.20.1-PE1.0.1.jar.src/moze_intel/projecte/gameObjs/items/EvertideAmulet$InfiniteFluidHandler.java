/*     */ package moze_intel.projecte.gameObjs.items;
/*     */ 
/*     */ import moze_intel.projecte.capability.BasicItemCapability;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.material.Fluid;
/*     */ import net.minecraft.world.level.material.Fluids;
/*     */ import net.minecraftforge.common.capabilities.Capability;
/*     */ import net.minecraftforge.common.capabilities.ForgeCapabilities;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.capability.IFluidHandler;
/*     */ import net.minecraftforge.fluids.capability.IFluidHandlerItem;
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
/*     */ class InfiniteFluidHandler
/*     */   extends BasicItemCapability<IFluidHandlerItem>
/*     */   implements IFluidHandlerItem
/*     */ {
/*     */   @NotNull
/*     */   public ItemStack getContainer() {
/* 150 */     return getStack();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTanks() {
/* 155 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public FluidStack getFluidInTank(int tank) {
/* 161 */     return (tank == 0) ? new FluidStack((Fluid)Fluids.f_76193_, 2147483647) : FluidStack.EMPTY;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTankCapacity(int tank) {
/* 166 */     return (tank == 0) ? Integer.MAX_VALUE : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
/* 171 */     return stack.getFluid().m_205067_(FluidTags.f_13131_);
/*     */   }
/*     */ 
/*     */   
/*     */   public int fill(FluidStack resource, IFluidHandler.FluidAction action) {
/* 176 */     if (resource.getFluid().m_205067_(FluidTags.f_13131_)) {
/* 177 */       return resource.getAmount();
/*     */     }
/* 179 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public FluidStack drain(FluidStack resource, IFluidHandler.FluidAction action) {
/* 185 */     if (resource.getFluid().m_205067_(FluidTags.f_13131_)) {
/* 186 */       return resource;
/*     */     }
/* 188 */     return FluidStack.EMPTY;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public FluidStack drain(int maxDrain, IFluidHandler.FluidAction action) {
/* 194 */     return new FluidStack((Fluid)Fluids.f_76193_, maxDrain);
/*     */   }
/*     */ 
/*     */   
/*     */   public Capability<IFluidHandlerItem> getCapability() {
/* 199 */     return ForgeCapabilities.FLUID_HANDLER_ITEM;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\EvertideAmulet$InfiniteFluidHandler.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */