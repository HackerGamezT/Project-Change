/*    */ package moze_intel.projecte.capability.managing;
/*    */ 
/*    */ import net.minecraft.core.Direction;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.ForgeCapabilities;
/*    */ import net.minecraftforge.common.util.LazyOptional;
/*    */ import net.minecraftforge.common.util.NonNullLazy;
/*    */ import net.minecraftforge.common.util.NonNullSupplier;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public abstract class BasicCapabilityResolver<CAPABILITY>
/*    */   implements ICapabilityResolver<CAPABILITY> {
/*    */   private final NonNullSupplier<CAPABILITY> supplier;
/*    */   private LazyOptional<CAPABILITY> cachedCapability;
/*    */   
/*    */   public static ICapabilityResolver<IItemHandler> getBasicItemHandlerResolver(NonNullSupplier<IItemHandler> supplier) {
/* 19 */     return new BasicCapabilityResolver<IItemHandler>(supplier)
/*    */       {
/*    */         @NotNull
/*    */         public Capability<IItemHandler> getMatchingCapability() {
/* 23 */           return ForgeCapabilities.ITEM_HANDLER;
/*    */         }
/*    */       };
/*    */   }
/*    */   
/*    */   public static ICapabilityResolver<IItemHandler> getBasicItemHandlerResolver(IItemHandler handler) {
/* 29 */     return new BasicCapabilityResolver<IItemHandler>(handler)
/*    */       {
/*    */         @NotNull
/*    */         public Capability<IItemHandler> getMatchingCapability() {
/* 33 */           return ForgeCapabilities.ITEM_HANDLER;
/*    */         }
/*    */       };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected BasicCapabilityResolver(CAPABILITY constant) {
/* 42 */     this.supplier = (() -> constant);
/*    */   }
/*    */   
/*    */   protected BasicCapabilityResolver(NonNullSupplier<CAPABILITY> supplier) {
/* 46 */     this.supplier = (supplier instanceof NonNullLazy) ? supplier : (NonNullSupplier<CAPABILITY>)NonNullLazy.of(supplier);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public <T> LazyOptional<T> getCapabilityUnchecked(@NotNull Capability<T> capability, @Nullable Direction side) {
/* 52 */     if (this.cachedCapability == null || !this.cachedCapability.isPresent())
/*    */     {
/* 54 */       this.cachedCapability = LazyOptional.of(this.supplier);
/*    */     }
/* 56 */     return this.cachedCapability.cast();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void invalidate(@NotNull Capability<?> capability, @Nullable Direction side) {
/* 62 */     invalidateAll();
/*    */   }
/*    */ 
/*    */   
/*    */   public void invalidateAll() {
/* 67 */     if (this.cachedCapability != null && this.cachedCapability.isPresent()) {
/* 68 */       this.cachedCapability.invalidate();
/* 69 */       this.cachedCapability = null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\capability\managing\BasicCapabilityResolver.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */