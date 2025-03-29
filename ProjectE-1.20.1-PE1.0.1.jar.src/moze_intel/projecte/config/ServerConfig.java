/*     */ package moze_intel.projecte.config;
/*     */ 
/*     */ import moze_intel.projecte.config.value.CachedBooleanValue;
/*     */ import moze_intel.projecte.config.value.CachedDoubleValue;
/*     */ import moze_intel.projecte.config.value.CachedFloatValue;
/*     */ import moze_intel.projecte.config.value.CachedIntValue;
/*     */ import net.minecraftforge.common.ForgeConfigSpec;
/*     */ import net.minecraftforge.fml.config.ModConfig;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ServerConfig
/*     */   extends BasePEConfig
/*     */ {
/*     */   private final ForgeConfigSpec configSpec;
/*     */   public final Difficulty difficulty;
/*     */   public final Items items;
/*     */   public final Effects effects;
/*     */   public final Misc misc;
/*     */   public final Cooldown cooldown;
/*     */   
/*     */   ServerConfig() {
/*  24 */     ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
/*  25 */     builder.comment("All of the config options in this file are server side and will be synced from server to client. ProjectE uses one \"server\" config file for all worlds, for convenience in going from one world to another, but makes it be a \"server\" config file so that forge will automatically sync it when we connect to a multiplayer server.")
/*     */ 
/*     */       
/*  28 */       .push("server");
/*  29 */     this.difficulty = new Difficulty(this, builder);
/*  30 */     this.items = new Items(this, builder);
/*  31 */     this.effects = new Effects(this, builder);
/*  32 */     this.misc = new Misc(this, builder);
/*  33 */     this.cooldown = new Cooldown(this, builder);
/*  34 */     builder.pop();
/*  35 */     this.configSpec = builder.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFileName() {
/*  40 */     return "server";
/*     */   }
/*     */ 
/*     */   
/*     */   public ForgeConfigSpec getConfigSpec() {
/*  45 */     return this.configSpec;
/*     */   }
/*     */ 
/*     */   
/*     */   public ModConfig.Type getConfigType() {
/*  50 */     return ModConfig.Type.SERVER;
/*     */   }
/*     */   
/*     */   public static class Difficulty
/*     */   {
/*     */     public final CachedBooleanValue offensiveAbilities;
/*     */     public final CachedFloatValue katarDeathAura;
/*     */     public final CachedDoubleValue covalenceLoss;
/*     */     public final CachedBooleanValue covalenceLossRounding;
/*     */     
/*     */     private Difficulty(IPEConfig config, ForgeConfigSpec.Builder builder) {
/*  61 */       builder.push("difficulty");
/*  62 */       this.offensiveAbilities = CachedBooleanValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/*  63 */           .comment("Set to false to disable Gem Armor offensive abilities (helmet zap and chestplate explosion)")
/*  64 */           .define("offensiveAbilities", false));
/*  65 */       this.katarDeathAura = CachedFloatValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/*  66 */           .comment("Amount of damage Katar 'C' key deals")
/*  67 */           .defineInRange("katarDeathAura", 1000.0D, 0.0D, 2.147483647E9D));
/*  68 */       this.covalenceLoss = CachedDoubleValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/*  69 */           .comment("Adjusting this ratio changes how much EMC is received when burning a item. For example setting this to 0.5 will return half of the EMC cost.")
/*  70 */           .defineInRange("covalenceLoss", 1.0D, 0.1D, 1.0D));
/*  71 */       this.covalenceLossRounding = CachedBooleanValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/*  72 */           .comment("How rounding occurs when Covalence Loss results in a burn value less than 1 EMC. If true the value will be rounded up to 1. If false the value will be rounded down to 0.")
/*  73 */           .define("covalenceLossRounding", true));
/*  74 */       builder.pop();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Items
/*     */   {
/*     */     public final CachedBooleanValue pickaxeAoeVeinMining;
/*     */     public final CachedBooleanValue harvBandGrass;
/*     */     public final CachedBooleanValue disableAllRadiusMining;
/*     */     public final CachedBooleanValue enableTimeWatch;
/*     */     public final CachedBooleanValue opEvertide;
/*     */     
/*     */     private Items(IPEConfig config, ForgeConfigSpec.Builder builder) {
/*  87 */       builder.push("items");
/*  88 */       this.pickaxeAoeVeinMining = CachedBooleanValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/*  89 */           .comment("Instead of vein mining the ore you right click with your Dark/Red Matter Pick/Star it vein mines all ores in an AOE around you like it did in ProjectE before version 1.4.4.")
/*  90 */           .define("pickaxeAoeVeinMining", false));
/*  91 */       this.harvBandGrass = CachedBooleanValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/*  92 */           .comment("Allows the Harvest Goddess Band to passively grow tall grass, flowers, etc, on top of grass blocks.")
/*  93 */           .define("harvBandGrass", false));
/*  94 */       this.disableAllRadiusMining = CachedBooleanValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/*  95 */           .comment("If set to true, disables all radius-based mining functionality (right click of tools)")
/*  96 */           .define("disableAllRadiusMining", false));
/*  97 */       this.enableTimeWatch = CachedBooleanValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/*  98 */           .comment("Enable Watch of Flowing Time")
/*  99 */           .define("enableTimeWatch", true));
/* 100 */       this.opEvertide = CachedBooleanValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 101 */           .comment("Allow the Evertide amulet to place water in dimensions that water evaporates. For example: The Nether.")
/* 102 */           .define("opEvertide", false));
/* 103 */       builder.pop();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Effects
/*     */   {
/*     */     public final CachedIntValue timePedBonus;
/*     */     public final CachedDoubleValue timePedMobSlowness;
/*     */     public final CachedBooleanValue interdictionMode;
/*     */     
/*     */     private Effects(IPEConfig config, ForgeConfigSpec.Builder builder) {
/* 114 */       builder.push("effects");
/* 115 */       this.timePedBonus = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 116 */           .comment("Bonus ticks given by the Watch of Flowing Time while in the pedestal. 0 = effectively no bonus.")
/* 117 */           .defineInRange("timePedBonus", 18, 0, 256));
/* 118 */       this.timePedMobSlowness = CachedDoubleValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 119 */           .comment("Factor the Watch of Flowing Time slows down mobs by while in the pedestal. Set to 1.0 for no slowdown.")
/* 120 */           .defineInRange("timePedMobSlowness", 0.1D, 0.0D, 1.0D));
/* 121 */       this.interdictionMode = CachedBooleanValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 122 */           .comment("If true the Interdiction Torch only affects hostile mobs and projectiles. If false it affects all non blacklisted living entities.")
/* 123 */           .define("interdictionMode", true));
/* 124 */       builder.pop();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Misc
/*     */   {
/*     */     public final CachedBooleanValue unsafeKeyBinds;
/*     */     public final CachedBooleanValue hwylaTOPDisplay;
/*     */     
/*     */     private Misc(IPEConfig config, ForgeConfigSpec.Builder builder) {
/* 134 */       builder.push("misc");
/* 135 */       this.unsafeKeyBinds = CachedBooleanValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 136 */           .comment("False requires your hand be empty for Gem Armor Offensive Abilities to be readied or triggered")
/* 137 */           .define("unsafeKeyBinds", false));
/* 138 */       this.hwylaTOPDisplay = CachedBooleanValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 139 */           .comment("Shows the EMC value of blocks when looking at them in Hwyla or TOP")
/* 140 */           .define("hwylaTOPDisplay", true));
/* 141 */       builder.pop();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Cooldown
/*     */   {
/*     */     public final Pedestal pedestal;
/*     */     public final Player player;
/*     */     
/*     */     private Cooldown(IPEConfig config, ForgeConfigSpec.Builder builder) {
/* 151 */       builder.push("cooldown");
/* 152 */       builder.comment(new String[] { "Cooldown (in ticks) for various features in ProjectE. A cooldown of -1 will disable the functionality.", "A cooldown of 0 will allow the actions to happen every tick. Use caution as a very low value on features that run automatically could cause TPS issues."
/*     */           
/* 154 */           }).push("cooldown");
/* 155 */       this.pedestal = new Pedestal(config, builder);
/* 156 */       this.player = new Player(config, builder);
/* 157 */       builder.pop();
/*     */     }
/*     */     
/*     */     public static class Player
/*     */     {
/*     */       public final CachedIntValue projectile;
/*     */       public final CachedIntValue gemChest;
/*     */       public final CachedIntValue repair;
/*     */       public final CachedIntValue heal;
/*     */       public final CachedIntValue feed;
/*     */       
/*     */       private Player(IPEConfig config, ForgeConfigSpec.Builder builder) {
/* 169 */         builder.comment("Cooldown for various items in regards to a player.")
/* 170 */           .push("player");
/* 171 */         this.projectile = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 172 */             .comment("A cooldown for firing projectiles")
/* 173 */             .defineInRange("projectile", 0, -1, 2147483647));
/* 174 */         this.gemChest = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 175 */             .comment("A cooldown for Gem Chestplate explosion")
/* 176 */             .defineInRange("gemChest", 0, -1, 2147483647));
/* 177 */         this.repair = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 178 */             .comment("Delay between Talisman of Repair trying to repair player items while in a player's inventory.")
/* 179 */             .defineInRange("repair", 20, -1, 2147483647));
/* 180 */         this.heal = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 181 */             .comment("Delay between heal attempts while in a player's inventory. (Soul Stone, Life Stone, Gem Helmet)")
/* 182 */             .defineInRange("heal", 20, -1, 2147483647));
/* 183 */         this.feed = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 184 */             .comment("Delay between feed attempts while in a player's inventory. (Body Stone, Life Stone, Gem Helmet)")
/* 185 */             .defineInRange("feed", 20, -1, 2147483647));
/* 186 */         builder.pop();
/*     */       }
/*     */     }
/*     */     
/*     */     public static class Pedestal
/*     */     {
/*     */       public final CachedIntValue archangel;
/*     */       public final CachedIntValue body;
/*     */       public final CachedIntValue evertide;
/*     */       public final CachedIntValue harvest;
/*     */       public final CachedIntValue ignition;
/*     */       public final CachedIntValue life;
/*     */       public final CachedIntValue repair;
/*     */       public final CachedIntValue swrg;
/*     */       public final CachedIntValue soul;
/*     */       public final CachedIntValue volcanite;
/*     */       public final CachedIntValue zero;
/*     */       
/*     */       private Pedestal(IPEConfig config, ForgeConfigSpec.Builder builder) {
/* 205 */         builder.comment("Cooldown for various items within the pedestal.")
/* 206 */           .push("pedestal");
/* 207 */         this.archangel = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 208 */             .comment("Delay between Archangel Smite shooting arrows while in the pedestal.")
/* 209 */             .defineInRange("archangel", 40, -1, 2147483647));
/* 210 */         this.body = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 211 */             .comment("Delay between Body Stone healing 0.5 shanks while in the pedestal.")
/* 212 */             .defineInRange("body", 10, -1, 2147483647));
/* 213 */         this.evertide = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 214 */             .comment("Delay between Evertide Amulet trying to start rain while in the pedestal.")
/* 215 */             .defineInRange("evertide", 20, -1, 2147483647));
/* 216 */         this.harvest = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 217 */             .comment("Delay between Harvest Goddess trying to grow and harvest while in the pedestal.")
/* 218 */             .defineInRange("harvest", 10, -1, 2147483647));
/* 219 */         this.ignition = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 220 */             .comment("Delay between Ignition Ring trying to light entities on fire while in the pedestal.")
/* 221 */             .defineInRange("ignition", 40, -1, 2147483647));
/* 222 */         this.life = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 223 */             .comment("Delay between Life Stone healing both food and hunger by 0.5 shank/heart while in the pedestal.")
/* 224 */             .defineInRange("life", 5, -1, 2147483647));
/* 225 */         this.repair = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 226 */             .comment("Delay between Talisman of Repair trying to repair player items while in the pedestal.")
/* 227 */             .defineInRange("repair", 20, -1, 2147483647));
/* 228 */         this.swrg = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 229 */             .comment("Delay between SWRG trying to smite mobs while in the pedestal.")
/* 230 */             .defineInRange("swrg", 70, -1, 2147483647));
/* 231 */         this.soul = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 232 */             .comment("Delay between Soul Stone healing 0.5 hearts while in the pedestal.")
/* 233 */             .defineInRange("soul", 10, -1, 2147483647));
/* 234 */         this.volcanite = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 235 */             .comment("Delay between Volcanite Amulet trying to stop rain while in the pedestal.")
/* 236 */             .defineInRange("volcanite", 20, -1, 2147483647));
/* 237 */         this.zero = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 238 */             .comment("Delay between Zero Ring trying to extinguish entities and freezing ground while in the pedestal.")
/* 239 */             .defineInRange("zero", 40, -1, 2147483647));
/* 240 */         builder.pop(); } } } public static class Player { public final CachedIntValue projectile; public final CachedIntValue gemChest; public final CachedIntValue repair; public final CachedIntValue heal; public final CachedIntValue feed; private Player(IPEConfig config, ForgeConfigSpec.Builder builder) { builder.comment("Cooldown for various items in regards to a player.").push("player"); this.projectile = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder.comment("A cooldown for firing projectiles").defineInRange("projectile", 0, -1, 2147483647)); this.gemChest = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder.comment("A cooldown for Gem Chestplate explosion").defineInRange("gemChest", 0, -1, 2147483647)); this.repair = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder.comment("Delay between Talisman of Repair trying to repair player items while in a player's inventory.").defineInRange("repair", 20, -1, 2147483647)); this.heal = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder.comment("Delay between heal attempts while in a player's inventory. (Soul Stone, Life Stone, Gem Helmet)").defineInRange("heal", 20, -1, 2147483647)); this.feed = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder.comment("Delay between feed attempts while in a player's inventory. (Body Stone, Life Stone, Gem Helmet)").defineInRange("feed", 20, -1, 2147483647)); builder.pop(); } } public static class Pedestal { private Pedestal(IPEConfig config, ForgeConfigSpec.Builder builder) { builder.comment("Cooldown for various items within the pedestal.").push("pedestal"); this.archangel = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder.comment("Delay between Archangel Smite shooting arrows while in the pedestal.").defineInRange("archangel", 40, -1, 2147483647)); this.body = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder.comment("Delay between Body Stone healing 0.5 shanks while in the pedestal.").defineInRange("body", 10, -1, 2147483647)); this.evertide = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder.comment("Delay between Evertide Amulet trying to start rain while in the pedestal.").defineInRange("evertide", 20, -1, 2147483647)); this.harvest = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder.comment("Delay between Harvest Goddess trying to grow and harvest while in the pedestal.").defineInRange("harvest", 10, -1, 2147483647)); this.ignition = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder.comment("Delay between Ignition Ring trying to light entities on fire while in the pedestal.").defineInRange("ignition", 40, -1, 2147483647)); this.life = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder.comment("Delay between Life Stone healing both food and hunger by 0.5 shank/heart while in the pedestal.").defineInRange("life", 5, -1, 2147483647)); this.repair = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder.comment("Delay between Talisman of Repair trying to repair player items while in the pedestal.").defineInRange("repair", 20, -1, 2147483647)); this.swrg = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder.comment("Delay between SWRG trying to smite mobs while in the pedestal.").defineInRange("swrg", 70, -1, 2147483647)); this.soul = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder.comment("Delay between Soul Stone healing 0.5 hearts while in the pedestal.").defineInRange("soul", 10, -1, 2147483647)); this.volcanite = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder.comment("Delay between Volcanite Amulet trying to stop rain while in the pedestal.").defineInRange("volcanite", 20, -1, 2147483647)); this.zero = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder.comment("Delay between Zero Ring trying to extinguish entities and freezing ground while in the pedestal.").defineInRange("zero", 40, -1, 2147483647)); builder.pop(); }
/*     */ 
/*     */     
/*     */     public final CachedIntValue archangel;
/*     */     public final CachedIntValue body;
/*     */     public final CachedIntValue evertide;
/*     */     public final CachedIntValue harvest;
/*     */     public final CachedIntValue ignition;
/*     */     public final CachedIntValue life;
/*     */     public final CachedIntValue repair;
/*     */     public final CachedIntValue swrg;
/*     */     public final CachedIntValue soul;
/*     */     public final CachedIntValue volcanite;
/*     */     public final CachedIntValue zero; }
/*     */ 
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\config\ServerConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */