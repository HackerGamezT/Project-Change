/*    */ package moze_intel.projecte.config;
/*    */ 
/*    */ import moze_intel.projecte.config.value.CachedBooleanValue;
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ import net.minecraftforge.fml.config.ModConfig;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommonConfig
/*    */   extends BasePEConfig
/*    */ {
/*    */   private final ForgeConfigSpec configSpec;
/*    */   public final CachedBooleanValue debugLogging;
/*    */   public final CachedBooleanValue craftableTome;
/*    */   public final CachedBooleanValue fullKleinStars;
/*    */   
/*    */   CommonConfig() {
/* 19 */     ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
/* 20 */     builder.comment("Note: The majority of config options are in the server config file. If you do not see the server config file, try opening up a single player world. ProjectE uses one \"server\" config file for all worlds, for convenience in going from one world to another, but makes it be a \"server\" config file so that forge will automatically sync it when we connect to a multiplayer server.")
/*    */ 
/*    */       
/* 23 */       .push("common");
/* 24 */     this.debugLogging = CachedBooleanValue.wrap(this, (ForgeConfigSpec.ConfigValue)builder
/* 25 */         .comment("Enable more verbose debug logging")
/* 26 */         .define("debugLogging", false));
/* 27 */     this.craftableTome = CachedBooleanValue.wrap(this, (ForgeConfigSpec.ConfigValue)builder
/* 28 */         .comment("The Tome of Knowledge can be crafted.")
/* 29 */         .define("craftableTome", false));
/* 30 */     this.fullKleinStars = CachedBooleanValue.wrap(this, (ForgeConfigSpec.ConfigValue)builder
/* 31 */         .comment("Require full omega klein stars in the tome of knowledge and gem armor recipes. This is the same behavior that EE2 had.")
/* 32 */         .define("fullKleinStars", false));
/* 33 */     builder.pop();
/* 34 */     this.configSpec = builder.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getFileName() {
/* 39 */     return "common";
/*    */   }
/*    */ 
/*    */   
/*    */   public ForgeConfigSpec getConfigSpec() {
/* 44 */     return this.configSpec;
/*    */   }
/*    */ 
/*    */   
/*    */   public ModConfig.Type getConfigType() {
/* 49 */     return ModConfig.Type.COMMON;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\config\CommonConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */