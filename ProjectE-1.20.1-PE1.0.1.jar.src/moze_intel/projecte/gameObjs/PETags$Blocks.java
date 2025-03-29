/*     */ package moze_intel.projecte.gameObjs;
/*     */ 
/*     */ import moze_intel.projecte.PECore;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.tags.BlockTags;
/*     */ import net.minecraft.tags.TagKey;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Blocks
/*     */ {
/*     */   private static void init() {}
/*     */   
/* 120 */   public static final TagKey<Block> BLACKLIST_HARVEST = tag("blacklist/harvest");
/*     */ 
/*     */ 
/*     */   
/* 124 */   public static final TagKey<Block> BLACKLIST_TIME_WATCH = tag("blacklist/time_watch");
/*     */   
/* 126 */   public static final TagKey<Block> FARMING_OVERRIDE = tag("farming_override");
/*     */   
/* 128 */   public static final TagKey<Block> NEEDS_DARK_MATTER_TOOL = tag("needs_dark_matter_tool");
/* 129 */   public static final TagKey<Block> NEEDS_RED_MATTER_TOOL = tag("needs_red_matter_tool");
/*     */   
/* 131 */   public static final TagKey<Block> MINEABLE_WITH_PE_KATAR = tag("mineable/katar");
/* 132 */   public static final TagKey<Block> MINEABLE_WITH_PE_HAMMER = tag("mineable/hammer");
/* 133 */   public static final TagKey<Block> MINEABLE_WITH_PE_MORNING_STAR = tag("mineable/morning_star");
/* 134 */   public static final TagKey<Block> MINEABLE_WITH_PE_SHEARS = tag("mineable/shears");
/* 135 */   public static final TagKey<Block> MINEABLE_WITH_PE_SWORD = tag("mineable/sword");
/*     */   
/* 137 */   public static final TagKey<Block> MINEABLE_WITH_HAMMER = forgeTag("mineable/hammer");
/* 138 */   public static final TagKey<Block> MINEABLE_WITH_KATAR = forgeTag("mineable/katar");
/* 139 */   public static final TagKey<Block> MINEABLE_WITH_MORNING_STAR = forgeTag("mineable/morning_star");
/*     */ 
/*     */   
/*     */   private static TagKey<Block> tag(String name) {
/* 143 */     return BlockTags.create(PECore.rl(name));
/*     */   }
/*     */   
/*     */   private static TagKey<Block> forgeTag(String name) {
/* 147 */     return BlockTags.create(new ResourceLocation("forge", name));
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\PETags$Blocks.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */