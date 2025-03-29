/*    */ package moze_intel.projecte.config;
/*    */ 
/*    */ import moze_intel.projecte.config.value.CachedBooleanValue;
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ import net.minecraftforge.fml.config.ModConfig;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClientConfig
/*    */   extends BasePEConfig
/*    */ {
/*    */   private final ForgeConfigSpec configSpec;
/*    */   public final CachedBooleanValue tagToolTips;
/*    */   public final CachedBooleanValue emcToolTips;
/*    */   public final CachedBooleanValue shiftEmcToolTips;
/*    */   public final CachedBooleanValue shiftLearnedToolTips;
/*    */   public final CachedBooleanValue statToolTips;
/*    */   public final CachedBooleanValue pedestalToolTips;
/*    */   public final CachedBooleanValue pulsatingOverlay;
/*    */   
/*    */   ClientConfig() {
/* 23 */     ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
/*    */     
/* 25 */     builder.push("client");
/* 26 */     this.tagToolTips = CachedBooleanValue.wrap(this, (ForgeConfigSpec.ConfigValue)builder
/* 27 */         .comment("Show item tags in tooltips (useful for custom EMC registration)")
/* 28 */         .define("tagToolTips", false));
/* 29 */     this.emcToolTips = CachedBooleanValue.wrap(this, (ForgeConfigSpec.ConfigValue)builder
/* 30 */         .comment("Show the EMC value as a tooltip on items and blocks")
/* 31 */         .define("emcToolTips", true));
/* 32 */     this.shiftEmcToolTips = CachedBooleanValue.wrap(this, (ForgeConfigSpec.ConfigValue)builder
/* 33 */         .comment("Requires holding shift to display the EMC value as a tooltip on items and blocks. Note: this does nothing if emcToolTips is disabled.")
/* 34 */         .define("shiftEmcToolTips", false));
/* 35 */     this.shiftLearnedToolTips = CachedBooleanValue.wrap(this, (ForgeConfigSpec.ConfigValue)builder
/* 36 */         .comment("Requires holding shift to display the learned/unlearned text as a tooltip on items and blocks. Note: this does nothing if emcToolTips is disabled.")
/* 37 */         .define("shiftLearnedToolTips", true));
/* 38 */     this.statToolTips = CachedBooleanValue.wrap(this, (ForgeConfigSpec.ConfigValue)builder
/* 39 */         .comment("Show stats as tooltips for various ProjectE blocks")
/* 40 */         .define("statToolTips", true));
/* 41 */     this.pedestalToolTips = CachedBooleanValue.wrap(this, (ForgeConfigSpec.ConfigValue)builder
/* 42 */         .comment("Show DM pedestal functions in item tooltips")
/* 43 */         .define("pedestalToolTips", true));
/* 44 */     this.pulsatingOverlay = CachedBooleanValue.wrap(this, (ForgeConfigSpec.ConfigValue)builder
/* 45 */         .comment("The Philosopher's Stone overlay softly pulsates")
/* 46 */         .define("pulsatingOverlay", false));
/* 47 */     builder.pop();
/* 48 */     this.configSpec = builder.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getFileName() {
/* 53 */     return "client";
/*    */   }
/*    */ 
/*    */   
/*    */   public ForgeConfigSpec getConfigSpec() {
/* 58 */     return this.configSpec;
/*    */   }
/*    */ 
/*    */   
/*    */   public ModConfig.Type getConfigType() {
/* 63 */     return ModConfig.Type.CLIENT;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\config\ClientConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */