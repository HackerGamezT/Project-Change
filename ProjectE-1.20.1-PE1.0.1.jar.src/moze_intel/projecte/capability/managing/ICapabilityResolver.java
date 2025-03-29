/*    */ package moze_intel.projecte.capability.managing;
/*    */ 
/*    */ import net.minecraft.core.Direction;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.ICapabilityProvider;
/*    */ import net.minecraftforge.common.util.LazyOptional;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public interface ICapabilityResolver<CAPABILITY>
/*    */   extends ICapabilityProvider
/*    */ {
/*    */   @NotNull
/*    */   Capability<CAPABILITY> getMatchingCapability();
/*    */   
/*    */   @NotNull
/*    */   <T> LazyOptional<T> getCapabilityUnchecked(@NotNull Capability<T> paramCapability, @Nullable Direction paramDirection);
/*    */   
/*    */   @NotNull
/*    */   default <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction side) {
/* 21 */     if (capability == getMatchingCapability()) {
/* 22 */       return getCapabilityUnchecked(capability, side);
/*    */     }
/* 24 */     return LazyOptional.empty();
/*    */   }
/*    */   
/*    */   void invalidate(@NotNull Capability<?> paramCapability, @Nullable Direction paramDirection);
/*    */   
/*    */   void invalidateAll();
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\capability\managing\ICapabilityResolver.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */