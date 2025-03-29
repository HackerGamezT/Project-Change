/*    */ package moze_intel.projecte.config;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import moze_intel.projecte.config.value.CachedValue;
/*    */ 
/*    */ public abstract class BasePEConfig
/*    */   implements IPEConfig {
/*  9 */   private final List<CachedValue<?>> cachedConfigValues = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void clearCache(boolean unloading) {
/* 13 */     for (CachedValue<?> cachedConfigValue : this.cachedConfigValues) {
/* 14 */       cachedConfigValue.clearCache(unloading);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public <T> void addCachedValue(CachedValue<T> configValue) {
/* 20 */     this.cachedConfigValues.add(configValue);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\config\BasePEConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */