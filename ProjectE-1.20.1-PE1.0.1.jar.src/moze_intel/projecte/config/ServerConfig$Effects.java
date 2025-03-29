/*     */ package moze_intel.projecte.config;
/*     */ 
/*     */ import moze_intel.projecte.config.value.CachedBooleanValue;
/*     */ import moze_intel.projecte.config.value.CachedDoubleValue;
/*     */ import moze_intel.projecte.config.value.CachedIntValue;
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
/*     */ public class Effects
/*     */ {
/*     */   public final CachedIntValue timePedBonus;
/*     */   public final CachedDoubleValue timePedMobSlowness;
/*     */   public final CachedBooleanValue interdictionMode;
/*     */   
/*     */   private Effects(IPEConfig config, ForgeConfigSpec.Builder builder) {
/* 114 */     builder.push("effects");
/* 115 */     this.timePedBonus = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 116 */         .comment("Bonus ticks given by the Watch of Flowing Time while in the pedestal. 0 = effectively no bonus.")
/* 117 */         .defineInRange("timePedBonus", 18, 0, 256));
/* 118 */     this.timePedMobSlowness = CachedDoubleValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 119 */         .comment("Factor the Watch of Flowing Time slows down mobs by while in the pedestal. Set to 1.0 for no slowdown.")
/* 120 */         .defineInRange("timePedMobSlowness", 0.1D, 0.0D, 1.0D));
/* 121 */     this.interdictionMode = CachedBooleanValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 122 */         .comment("If true the Interdiction Torch only affects hostile mobs and projectiles. If false it affects all non blacklisted living entities.")
/* 123 */         .define("interdictionMode", true));
/* 124 */     builder.pop();
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\config\ServerConfig$Effects.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */