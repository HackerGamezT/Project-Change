/*    */ package moze_intel.projecte.config.value;
/*    */ 
/*    */ import java.util.function.IntSupplier;
/*    */ import moze_intel.projecte.config.IPEConfig;
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ 
/*    */ 
/*    */ public class CachedIntValue
/*    */   extends CachedValue<Integer>
/*    */   implements IntSupplier
/*    */ {
/*    */   private boolean resolved;
/*    */   private int cachedValue;
/*    */   
/*    */   private CachedIntValue(IPEConfig config, ForgeConfigSpec.ConfigValue<Integer> internal) {
/* 16 */     super(config, internal);
/*    */   }
/*    */   
/*    */   public static CachedIntValue wrap(IPEConfig config, ForgeConfigSpec.ConfigValue<Integer> internal) {
/* 20 */     return new CachedIntValue(config, internal);
/*    */   }
/*    */   
/*    */   public int getOrDefault() {
/* 24 */     if (this.resolved || isLoaded()) {
/* 25 */       return get();
/*    */     }
/* 27 */     return ((Integer)this.internal.getDefault()).intValue();
/*    */   }
/*    */   
/*    */   public int get() {
/* 31 */     if (!this.resolved) {
/*    */       
/* 33 */       this.cachedValue = ((Integer)this.internal.get()).intValue();
/* 34 */       this.resolved = true;
/*    */     } 
/* 36 */     return this.cachedValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getAsInt() {
/* 41 */     return get();
/*    */   }
/*    */   
/*    */   public void set(int value) {
/* 45 */     this.internal.set(Integer.valueOf(value));
/* 46 */     this.cachedValue = value;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean clearCachedValue(boolean checkChanged) {
/* 51 */     if (!this.resolved)
/*    */     {
/* 53 */       return false;
/*    */     }
/* 55 */     int oldCachedValue = this.cachedValue;
/* 56 */     this.resolved = false;
/*    */     
/* 58 */     return (checkChanged && oldCachedValue != get());
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\config\value\CachedIntValue.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */