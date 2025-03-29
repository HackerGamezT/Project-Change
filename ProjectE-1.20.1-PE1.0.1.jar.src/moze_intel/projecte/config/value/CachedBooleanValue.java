/*    */ package moze_intel.projecte.config.value;
/*    */ 
/*    */ import java.util.function.BooleanSupplier;
/*    */ import moze_intel.projecte.config.IPEConfig;
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ 
/*    */ 
/*    */ public class CachedBooleanValue
/*    */   extends CachedValue<Boolean>
/*    */   implements BooleanSupplier
/*    */ {
/*    */   private boolean resolved;
/*    */   private boolean cachedValue;
/*    */   
/*    */   private CachedBooleanValue(IPEConfig config, ForgeConfigSpec.ConfigValue<Boolean> internal) {
/* 16 */     super(config, internal);
/*    */   }
/*    */   
/*    */   public static CachedBooleanValue wrap(IPEConfig config, ForgeConfigSpec.ConfigValue<Boolean> internal) {
/* 20 */     return new CachedBooleanValue(config, internal);
/*    */   }
/*    */   
/*    */   public boolean getOrDefault() {
/* 24 */     if (this.resolved || isLoaded()) {
/* 25 */       return get();
/*    */     }
/* 27 */     return ((Boolean)this.internal.getDefault()).booleanValue();
/*    */   }
/*    */   
/*    */   public boolean get() {
/* 31 */     if (!this.resolved) {
/*    */       
/* 33 */       this.cachedValue = ((Boolean)this.internal.get()).booleanValue();
/* 34 */       this.resolved = true;
/*    */     } 
/* 36 */     return this.cachedValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean getAsBoolean() {
/* 41 */     return get();
/*    */   }
/*    */   
/*    */   public void set(boolean value) {
/* 45 */     this.internal.set(Boolean.valueOf(value));
/* 46 */     this.cachedValue = value;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean clearCachedValue(boolean checkChanged) {
/* 51 */     if (!this.resolved)
/*    */     {
/* 53 */       return false;
/*    */     }
/* 55 */     boolean oldCachedValue = this.cachedValue;
/* 56 */     this.resolved = false;
/*    */     
/* 58 */     return (checkChanged && oldCachedValue != get());
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\config\value\CachedBooleanValue.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */