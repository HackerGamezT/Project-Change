/*    */ package moze_intel.projecte.config.value;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import moze_intel.projecte.config.IPEConfig;
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class CachedResolvableConfigValue<TYPE, REAL>
/*    */   extends CachedValue<REAL>
/*    */   implements Supplier<TYPE>
/*    */ {
/*    */   @Nullable
/*    */   private TYPE cachedValue;
/*    */   
/*    */   protected CachedResolvableConfigValue(IPEConfig config, ForgeConfigSpec.ConfigValue<REAL> internal) {
/* 21 */     super(config, internal);
/*    */   }
/*    */   
/*    */   protected abstract TYPE resolve(REAL paramREAL);
/*    */   
/*    */   protected abstract REAL encode(TYPE paramTYPE);
/*    */   
/*    */   @NotNull
/*    */   public TYPE getOrDefault() {
/* 30 */     if (this.cachedValue != null || isLoaded()) {
/* 31 */       return get();
/*    */     }
/* 33 */     return resolve((REAL)this.internal.getDefault());
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public TYPE get() {
/* 39 */     if (this.cachedValue == null)
/*    */     {
/* 41 */       this.cachedValue = resolve((REAL)this.internal.get());
/*    */     }
/* 43 */     return this.cachedValue;
/*    */   }
/*    */   
/*    */   public void set(TYPE value) {
/* 47 */     this.internal.set(encode(value));
/* 48 */     this.cachedValue = value;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean clearCachedValue(boolean checkChanged) {
/* 53 */     if (this.cachedValue == null)
/*    */     {
/* 55 */       return false;
/*    */     }
/* 57 */     TYPE oldCachedValue = this.cachedValue;
/* 58 */     this.cachedValue = null;
/*    */     
/* 60 */     return (checkChanged && !oldCachedValue.equals(get()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\config\value\CachedResolvableConfigValue.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */