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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Player
/*     */ {
/*     */   public final CachedIntValue projectile;
/*     */   public final CachedIntValue gemChest;
/*     */   public final CachedIntValue repair;
/*     */   public final CachedIntValue heal;
/*     */   public final CachedIntValue feed;
/*     */   
/*     */   private Player(IPEConfig config, ForgeConfigSpec.Builder builder) {
/* 169 */     builder.comment("Cooldown for various items in regards to a player.")
/* 170 */       .push("player");
/* 171 */     this.projectile = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 172 */         .comment("A cooldown for firing projectiles")
/* 173 */         .defineInRange("projectile", 0, -1, 2147483647));
/* 174 */     this.gemChest = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 175 */         .comment("A cooldown for Gem Chestplate explosion")
/* 176 */         .defineInRange("gemChest", 0, -1, 2147483647));
/* 177 */     this.repair = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 178 */         .comment("Delay between Talisman of Repair trying to repair player items while in a player's inventory.")
/* 179 */         .defineInRange("repair", 20, -1, 2147483647));
/* 180 */     this.heal = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 181 */         .comment("Delay between heal attempts while in a player's inventory. (Soul Stone, Life Stone, Gem Helmet)")
/* 182 */         .defineInRange("heal", 20, -1, 2147483647));
/* 183 */     this.feed = CachedIntValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder
/* 184 */         .comment("Delay between feed attempts while in a player's inventory. (Body Stone, Life Stone, Gem Helmet)")
/* 185 */         .defineInRange("feed", 20, -1, 2147483647));
/* 186 */     builder.pop();
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\config\ServerConfig$Cooldown$Player.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */