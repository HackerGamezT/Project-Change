/*    */ package moze_intel.projecte.config.value;
/*    */ 
/*    */ import java.util.function.DoubleSupplier;
/*    */ import moze_intel.projecte.config.IPEConfig;
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ 
/*    */ 
/*    */ public class CachedDoubleValue
/*    */   extends CachedValue<Double>
/*    */   implements DoubleSupplier
/*    */ {
/*    */   private boolean resolved;
/*    */   private double cachedValue;
/*    */   
/*    */   private CachedDoubleValue(IPEConfig config, ForgeConfigSpec.ConfigValue<Double> internal) {
/* 16 */     super(config, internal);
/*    */   }
/*    */   
/*    */   public static CachedDoubleValue wrap(IPEConfig config, ForgeConfigSpec.ConfigValue<Double> internal) {
/* 20 */     return new CachedDoubleValue(config, internal);
/*    */   }
/*    */   
/*    */   public double getOrDefault() {
/* 24 */     if (this.resolved || isLoaded()) {
/* 25 */       return get();
/*    */     }
/* 27 */     return ((Double)this.internal.getDefault()).doubleValue();
/*    */   }
/*    */   
/*    */   public double get() {
/* 31 */     if (!this.resolved) {
/*    */       
/* 33 */       this.cachedValue = ((Double)this.internal.get()).doubleValue();
/* 34 */       this.resolved = true;
/*    */     } 
/* 36 */     return this.cachedValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getAsDouble() {
/* 41 */     return get();
/*    */   }
/*    */   
/*    */   public void set(double value) {
/* 45 */     this.internal.set(Double.valueOf(value));
/* 46 */     this.cachedValue = value;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean clearCachedValue(boolean checkChanged) {
/* 51 */     if (!this.resolved)
/*    */     {
/* 53 */       return false;
/*    */     }
/* 55 */     double oldCachedValue = this.cachedValue;
/* 56 */     this.resolved = false;
/*    */     
/* 58 */     return (checkChanged && oldCachedValue != get());
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\config\value\CachedDoubleValue.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */