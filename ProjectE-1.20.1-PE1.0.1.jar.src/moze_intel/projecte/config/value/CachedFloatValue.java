/*    */ package moze_intel.projecte.config.value;
/*    */ 
/*    */ import moze_intel.projecte.config.IPEConfig;
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CachedFloatValue
/*    */   extends CachedValue<Double>
/*    */ {
/*    */   private boolean resolved;
/*    */   private float cachedValue;
/*    */   
/*    */   private CachedFloatValue(IPEConfig config, ForgeConfigSpec.ConfigValue<Double> internal) {
/* 15 */     super(config, internal);
/*    */   }
/*    */   
/*    */   public static CachedFloatValue wrap(IPEConfig config, ForgeConfigSpec.ConfigValue<Double> internal) {
/* 19 */     return new CachedFloatValue(config, internal);
/*    */   }
/*    */   
/*    */   public float getOrDefault() {
/* 23 */     if (this.resolved || isLoaded()) {
/* 24 */       return get();
/*    */     }
/* 26 */     return clampInternal((Double)this.internal.getDefault());
/*    */   }
/*    */   
/*    */   public float get() {
/* 30 */     if (!this.resolved) {
/*    */ 
/*    */       
/* 33 */       this.cachedValue = clampInternal((Double)this.internal.get());
/* 34 */       this.resolved = true;
/*    */     } 
/* 36 */     return this.cachedValue;
/*    */   }
/*    */   
/*    */   private float clampInternal(Double val) {
/* 40 */     if (val == null)
/* 41 */       return 0.0F; 
/* 42 */     if (val.doubleValue() > 3.4028234663852886E38D)
/* 43 */       return Float.MAX_VALUE; 
/* 44 */     if (val.doubleValue() < -3.4028234663852886E38D)
/*    */     {
/*    */       
/* 47 */       return -3.4028235E38F;
/*    */     }
/* 49 */     return val.floatValue();
/*    */   }
/*    */   
/*    */   public void set(float value) {
/* 53 */     this.internal.set(Double.valueOf(value));
/* 54 */     this.cachedValue = value;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean clearCachedValue(boolean checkChanged) {
/* 59 */     if (!this.resolved)
/*    */     {
/* 61 */       return false;
/*    */     }
/* 63 */     float oldCachedValue = this.cachedValue;
/* 64 */     this.resolved = false;
/*    */     
/* 66 */     return (checkChanged && oldCachedValue != get());
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\config\value\CachedFloatValue.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */