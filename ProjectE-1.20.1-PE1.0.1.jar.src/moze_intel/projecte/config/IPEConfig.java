/*    */ package moze_intel.projecte.config;
/*    */ 
/*    */ import moze_intel.projecte.config.value.CachedValue;
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ import net.minecraftforge.fml.config.ModConfig;
/*    */ 
/*    */ public interface IPEConfig
/*    */ {
/*    */   String getFileName();
/*    */   
/*    */   ForgeConfigSpec getConfigSpec();
/*    */   
/*    */   default boolean isLoaded() {
/* 14 */     return getConfigSpec().isLoaded();
/*    */   }
/*    */ 
/*    */   
/*    */   ModConfig.Type getConfigType();
/*    */ 
/*    */   
/*    */   void clearCache(boolean paramBoolean);
/*    */ 
/*    */   
/*    */   <T> void addCachedValue(CachedValue<T> paramCachedValue);
/*    */ 
/*    */   
/*    */   default boolean addToContainer() {
/* 28 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\config\IPEConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */