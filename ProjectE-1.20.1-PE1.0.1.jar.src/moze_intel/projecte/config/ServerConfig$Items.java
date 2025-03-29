/*     */ package moze_intel.projecte.config;
/*     */ 
/*     */ import moze_intel.projecte.config.value.CachedBooleanValue;
/*     */ import net.minecraftforge.common.ForgeConfigSpec;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Items
/*     */ {
/*     */   public final CachedBooleanValue pickaxeAoeVeinMining;
/*     */   public final CachedBooleanValue harvBandGrass;
/*     */   public final CachedBooleanValue disableAllRadiusMining;
/*     */   public final CachedBooleanValue enableTimeWatch;
/*     */   public final CachedBooleanValue opEvertide;
/*     */   
/*     */   private Items(IPEConfig config, ForgeConfigSpec.Builder builder) {
/*  87 */     builder.push("items");
/*  88 */     this.pickaxeAoeVeinMining = CachedBooleanValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/*  89 */         .comment("Instead of vein mining the ore you right click with your Dark/Red Matter Pick/Star it vein mines all ores in an AOE around you like it did in ProjectE before version 1.4.4.")
/*  90 */         .define("pickaxeAoeVeinMining", false));
/*  91 */     this.harvBandGrass = CachedBooleanValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/*  92 */         .comment("Allows the Harvest Goddess Band to passively grow tall grass, flowers, etc, on top of grass blocks.")
/*  93 */         .define("harvBandGrass", false));
/*  94 */     this.disableAllRadiusMining = CachedBooleanValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/*  95 */         .comment("If set to true, disables all radius-based mining functionality (right click of tools)")
/*  96 */         .define("disableAllRadiusMining", false));
/*  97 */     this.enableTimeWatch = CachedBooleanValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/*  98 */         .comment("Enable Watch of Flowing Time")
/*  99 */         .define("enableTimeWatch", true));
/* 100 */     this.opEvertide = CachedBooleanValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 101 */         .comment("Allow the Evertide amulet to place water in dimensions that water evaporates. For example: The Nether.")
/* 102 */         .define("opEvertide", false));
/* 103 */     builder.pop();
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\config\ServerConfig$Items.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */