/*    */ package moze_intel.projecte.capability.managing;
/*    */ 
/*    */ import net.minecraft.core.Direction;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.ForgeCapabilities;
/*    */ import net.minecraftforge.common.util.LazyOptional;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public abstract class SidedItemHandlerResolver
/*    */   implements ICapabilityResolver<IItemHandler>
/*    */ {
/*    */   protected abstract ICapabilityResolver<IItemHandler> getResolver(@Nullable Direction paramDirection);
/*    */   
/*    */   @NotNull
/*    */   public Capability<IItemHandler> getMatchingCapability() {
/* 18 */     return ForgeCapabilities.ITEM_HANDLER;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public <T> LazyOptional<T> getCapabilityUnchecked(@NotNull Capability<T> capability, @Nullable Direction side) {
/* 24 */     return getResolver(side).getCapabilityUnchecked(capability, side);
/*    */   }
/*    */ 
/*    */   
/*    */   public void invalidate(@NotNull Capability<?> capability, @Nullable Direction side) {
/* 29 */     getResolver(side).invalidate(capability, side);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\capability\managing\SidedItemHandlerResolver.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */