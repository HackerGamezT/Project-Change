/*    */ package moze_intel.projecte.config.value;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import java.util.function.Predicate;
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.config.IPEConfig;
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ 
/*    */ public abstract class CachedValue<T>
/*    */ {
/*    */   private final IPEConfig config;
/*    */   protected final ForgeConfigSpec.ConfigValue<T> internal;
/*    */   private Set<IConfigValueInvalidationListener> invalidationListeners;
/*    */   
/*    */   protected CachedValue(IPEConfig config, ForgeConfigSpec.ConfigValue<T> internal) {
/* 17 */     this.config = config;
/* 18 */     this.internal = internal;
/* 19 */     this.config.addCachedValue(this);
/*    */   }
/*    */   
/*    */   public boolean hasInvalidationListeners() {
/* 23 */     return (this.invalidationListeners != null && !this.invalidationListeners.isEmpty());
/*    */   }
/*    */   
/*    */   public void addInvalidationListener(IConfigValueInvalidationListener listener) {
/* 27 */     if (this.invalidationListeners == null) {
/* 28 */       this.invalidationListeners = new HashSet<>();
/*    */     }
/* 30 */     if (!this.invalidationListeners.add(listener)) {
/* 31 */       PECore.LOGGER.warn("Duplicate invalidation listener added");
/*    */     }
/*    */   }
/*    */   
/*    */   public void removeInvalidationListener(IConfigValueInvalidationListener listener) {
/* 36 */     if (this.invalidationListeners == null) {
/* 37 */       PECore.LOGGER.warn("Unable to remove specified invalidation listener, no invalidation listeners have been added.");
/* 38 */     } else if (!this.invalidationListeners.remove(listener)) {
/* 39 */       PECore.LOGGER.warn("Unable to remove specified invalidation listener.");
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean removeInvalidationListenersMatching(Predicate<IConfigValueInvalidationListener> checker) {
/* 44 */     return (this.invalidationListeners != null && !this.invalidationListeners.isEmpty() && this.invalidationListeners.removeIf(checker));
/*    */   }
/*    */   
/*    */   protected abstract boolean clearCachedValue(boolean paramBoolean);
/*    */   
/*    */   public final void clearCache(boolean unloading) {
/* 50 */     if (hasInvalidationListeners()) {
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 55 */       if (!unloading && isLoaded() && clearCachedValue(true)) {
/* 56 */         this.invalidationListeners.forEach(Runnable::run);
/*    */       }
/*    */     } else {
/* 59 */       clearCachedValue(false);
/*    */     } 
/*    */   }
/*    */   
/*    */   protected boolean isLoaded() {
/* 64 */     return this.config.isLoaded();
/*    */   }
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface IConfigValueInvalidationListener extends Runnable {}
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\config\value\CachedValue.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */