/*    */ package moze_intel.projecte.config;
/*    */ 
/*    */ import moze_intel.projecte.config.value.CachedBooleanValue;
/*    */ import moze_intel.projecte.config.value.CachedDoubleValue;
/*    */ import moze_intel.projecte.config.value.CachedFloatValue;
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Difficulty
/*    */ {
/*    */   public final CachedBooleanValue offensiveAbilities;
/*    */   public final CachedFloatValue katarDeathAura;
/*    */   public final CachedDoubleValue covalenceLoss;
/*    */   public final CachedBooleanValue covalenceLossRounding;
/*    */   
/*    */   private Difficulty(IPEConfig config, ForgeConfigSpec.Builder builder) {
/* 61 */     builder.push("difficulty");
/* 62 */     this.offensiveAbilities = CachedBooleanValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 63 */         .comment("Set to false to disable Gem Armor offensive abilities (helmet zap and chestplate explosion)")
/* 64 */         .define("offensiveAbilities", false));
/* 65 */     this.katarDeathAura = CachedFloatValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 66 */         .comment("Amount of damage Katar 'C' key deals")
/* 67 */         .defineInRange("katarDeathAura", 1000.0D, 0.0D, 2.147483647E9D));
/* 68 */     this.covalenceLoss = CachedDoubleValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 69 */         .comment("Adjusting this ratio changes how much EMC is received when burning a item. For example setting this to 0.5 will return half of the EMC cost.")
/* 70 */         .defineInRange("covalenceLoss", 1.0D, 0.1D, 1.0D));
/* 71 */     this.covalenceLossRounding = CachedBooleanValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 72 */         .comment("How rounding occurs when Covalence Loss results in a burn value less than 1 EMC. If true the value will be rounded up to 1. If false the value will be rounded down to 0.")
/* 73 */         .define("covalenceLossRounding", true));
/* 74 */     builder.pop();
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\config\ServerConfig$Difficulty.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */