/*     */ package moze_intel.projecte.config;
/*     */ 
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
/*     */ public class Cooldown
/*     */ {
/*     */   public final Pedestal pedestal;
/*     */   public final Player player;
/*     */   
/*     */   private Cooldown(IPEConfig config, ForgeConfigSpec.Builder builder) {
/* 151 */     builder.push("cooldown");
/* 152 */     builder.comment(new String[] { "Cooldown (in ticks) for various features in ProjectE. A cooldown of -1 will disable the functionality.", "A cooldown of 0 will allow the actions to happen every tick. Use caution as a very low value on features that run automatically could cause TPS issues."
/*     */         
/* 154 */         }).push("cooldown");
/* 155 */     this.pedestal = new Pedestal(config, builder);
/* 156 */     this.player = new Player(config, builder);
/* 157 */     builder.pop();
/*     */   }
/*     */   
/*     */   public static class Player
/*     */   {
/*     */     public final CachedIntValue projectile;
/*     */     public final CachedIntValue gemChest;
/*     */     public final CachedIntValue repair;
/*     */     public final CachedIntValue heal;
/*     */     public final CachedIntValue feed;
/*     */     
/*     */     private Player(IPEConfig config, ForgeConfigSpec.Builder builder) {
/* 169 */       builder.comment("Cooldown for various items in regards to a player.")
/* 170 */         .push("player");
/* 171 */       this.projectile = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 172 */           .comment("A cooldown for firing projectiles")
/* 173 */           .defineInRange("projectile", 0, -1, 2147483647));
/* 174 */       this.gemChest = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 175 */           .comment("A cooldown for Gem Chestplate explosion")
/* 176 */           .defineInRange("gemChest", 0, -1, 2147483647));
/* 177 */       this.repair = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 178 */           .comment("Delay between Talisman of Repair trying to repair player items while in a player's inventory.")
/* 179 */           .defineInRange("repair", 20, -1, 2147483647));
/* 180 */       this.heal = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 181 */           .comment("Delay between heal attempts while in a player's inventory. (Soul Stone, Life Stone, Gem Helmet)")
/* 182 */           .defineInRange("heal", 20, -1, 2147483647));
/* 183 */       this.feed = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 184 */           .comment("Delay between feed attempts while in a player's inventory. (Body Stone, Life Stone, Gem Helmet)")
/* 185 */           .defineInRange("feed", 20, -1, 2147483647));
/* 186 */       builder.pop();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Pedestal
/*     */   {
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
/*     */     public final CachedIntValue zero;
/*     */     
/*     */     private Pedestal(IPEConfig config, ForgeConfigSpec.Builder builder) {
/* 205 */       builder.comment("Cooldown for various items within the pedestal.")
/* 206 */         .push("pedestal");
/* 207 */       this.archangel = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 208 */           .comment("Delay between Archangel Smite shooting arrows while in the pedestal.")
/* 209 */           .defineInRange("archangel", 40, -1, 2147483647));
/* 210 */       this.body = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 211 */           .comment("Delay between Body Stone healing 0.5 shanks while in the pedestal.")
/* 212 */           .defineInRange("body", 10, -1, 2147483647));
/* 213 */       this.evertide = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 214 */           .comment("Delay between Evertide Amulet trying to start rain while in the pedestal.")
/* 215 */           .defineInRange("evertide", 20, -1, 2147483647));
/* 216 */       this.harvest = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 217 */           .comment("Delay between Harvest Goddess trying to grow and harvest while in the pedestal.")
/* 218 */           .defineInRange("harvest", 10, -1, 2147483647));
/* 219 */       this.ignition = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 220 */           .comment("Delay between Ignition Ring trying to light entities on fire while in the pedestal.")
/* 221 */           .defineInRange("ignition", 40, -1, 2147483647));
/* 222 */       this.life = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 223 */           .comment("Delay between Life Stone healing both food and hunger by 0.5 shank/heart while in the pedestal.")
/* 224 */           .defineInRange("life", 5, -1, 2147483647));
/* 225 */       this.repair = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 226 */           .comment("Delay between Talisman of Repair trying to repair player items while in the pedestal.")
/* 227 */           .defineInRange("repair", 20, -1, 2147483647));
/* 228 */       this.swrg = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 229 */           .comment("Delay between SWRG trying to smite mobs while in the pedestal.")
/* 230 */           .defineInRange("swrg", 70, -1, 2147483647));
/* 231 */       this.soul = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 232 */           .comment("Delay between Soul Stone healing 0.5 hearts while in the pedestal.")
/* 233 */           .defineInRange("soul", 10, -1, 2147483647));
/* 234 */       this.volcanite = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 235 */           .comment("Delay between Volcanite Amulet trying to stop rain while in the pedestal.")
/* 236 */           .defineInRange("volcanite", 20, -1, 2147483647));
/* 237 */       this.zero = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 238 */           .comment("Delay between Zero Ring trying to extinguish entities and freezing ground while in the pedestal.")
/* 239 */           .defineInRange("zero", 40, -1, 2147483647));
/* 240 */       builder.pop();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\config\ServerConfig$Cooldown.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */