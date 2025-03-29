/*     */ package moze_intel.projecte.gameObjs;
/*     */ 
/*     */ import moze_intel.projecte.PECore;
/*     */ import moze_intel.projecte.utils.LazyTagLookup;
/*     */ import net.minecraft.core.registries.Registries;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.tags.BlockTags;
/*     */ import net.minecraft.tags.ItemTags;
/*     */ import net.minecraft.tags.TagKey;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.entity.BlockEntityType;
/*     */ import net.minecraftforge.common.Tags;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PETags
/*     */ {
/*     */   public static void init() {
/*  27 */     Items.init();
/*  28 */     Blocks.init();
/*  29 */     Entities.init();
/*  30 */     BlockEntities.init();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Items
/*     */   {
/*     */     private static void init() {}
/*     */ 
/*     */ 
/*     */     
/*  41 */     public static final TagKey<Item> ALCHEMICAL_BAGS = tag("alchemical_bags");
/*     */ 
/*     */ 
/*     */     
/*  45 */     public static final TagKey<Item> COLLECTOR_FUEL = tag("collector_fuel");
/*  46 */     public static final LazyTagLookup<Item> COLLECTOR_FUEL_LOOKUP = LazyTagLookup.create(ForgeRegistries.ITEMS, COLLECTOR_FUEL);
/*     */ 
/*     */ 
/*     */     
/*  50 */     public static final TagKey<Item> NBT_WHITELIST = tag("nbt_whitelist");
/*     */ 
/*     */ 
/*     */     
/*  54 */     public static final TagKey<Item> COVALENCE_DUST = tag("covalence_dust");
/*     */     
/*  56 */     public static final TagKey<Item> CURIOS_BELT = curiosTag("belt");
/*  57 */     public static final TagKey<Item> CURIOS_KLEIN_STAR = curiosTag("klein_star");
/*  58 */     public static final TagKey<Item> CURIOS_NECKLACE = curiosTag("necklace");
/*  59 */     public static final TagKey<Item> CURIOS_RING = curiosTag("ring");
/*     */ 
/*     */     
/*  62 */     public static final TagKey<Item> TOOLS_HAMMERS = forgeTag("tools/hammers");
/*  63 */     public static final TagKey<Item> TOOLS_KATARS = forgeTag("tools/katars");
/*  64 */     public static final TagKey<Item> TOOLS_MORNING_STARS = forgeTag("tools/morning_stars");
/*     */     
/*  66 */     public static final TagKey<Item> ARMORS_HELMETS_DARK_MATTER = forgeTag("armors/armors/dark_matter");
/*  67 */     public static final TagKey<Item> ARMORS_CHESTPLATES_DARK_MATTER = forgeTag("armors/chestplates/dark_matter");
/*  68 */     public static final TagKey<Item> ARMORS_LEGGINGS_DARK_MATTER = forgeTag("armors/leggings/dark_matter");
/*  69 */     public static final TagKey<Item> ARMORS_BOOTS_DARK_MATTER = forgeTag("armors/boots/dark_matter");
/*     */     
/*  71 */     public static final TagKey<Item> TOOLS_SWORDS_DARK_MATTER = forgeTag("tools/swords/dark_matter");
/*  72 */     public static final TagKey<Item> TOOLS_AXES_DARK_MATTER = forgeTag("tools/axes/dark_matter");
/*  73 */     public static final TagKey<Item> TOOLS_PICKAXES_DARK_MATTER = forgeTag("tools/pickaxes/dark_matter");
/*  74 */     public static final TagKey<Item> TOOLS_SHOVELS_DARK_MATTER = forgeTag("tools/shovels/dark_matter");
/*  75 */     public static final TagKey<Item> TOOLS_HOES_DARK_MATTER = forgeTag("tools/hoes/dark_matter");
/*  76 */     public static final TagKey<Item> TOOLS_HAMMERS_DARK_MATTER = forgeTag("tools/hammers/dark_matter");
/*     */     
/*  78 */     public static final TagKey<Item> ARMORS_HELMETS_RED_MATTER = forgeTag("armors/armors/red_matter");
/*  79 */     public static final TagKey<Item> ARMORS_CHESTPLATES_RED_MATTER = forgeTag("armors/chestplates/red_matter");
/*  80 */     public static final TagKey<Item> ARMORS_LEGGINGS_RED_MATTER = forgeTag("armors/leggings/red_matter");
/*  81 */     public static final TagKey<Item> ARMORS_BOOTS_RED_MATTER = forgeTag("armors/boots/red_matter");
/*     */     
/*  83 */     public static final TagKey<Item> TOOLS_SWORDS_RED_MATTER = forgeTag("tools/swords/red_matter");
/*  84 */     public static final TagKey<Item> TOOLS_AXES_RED_MATTER = forgeTag("tools/axes/red_matter");
/*  85 */     public static final TagKey<Item> TOOLS_PICKAXES_RED_MATTER = forgeTag("tools/pickaxes/red_matter");
/*  86 */     public static final TagKey<Item> TOOLS_SHOVELS_RED_MATTER = forgeTag("tools/shovels/red_matter");
/*  87 */     public static final TagKey<Item> TOOLS_HOES_RED_MATTER = forgeTag("tools/hoes/red_matter");
/*  88 */     public static final TagKey<Item> TOOLS_HAMMERS_RED_MATTER = forgeTag("tools/hammers/red_matter");
/*  89 */     public static final TagKey<Item> TOOLS_KATARS_RED_MATTER = forgeTag("tools/katars/red_matter");
/*  90 */     public static final TagKey<Item> TOOLS_MORNING_STARS_RED_MATTER = forgeTag("tools/morning_stars/red_matter");
/*     */ 
/*     */     
/*  93 */     public static final LazyTagLookup<Item> ORES_LOOKUP = LazyTagLookup.create(ForgeRegistries.ITEMS, Tags.Items.ORES);
/*  94 */     public static final LazyTagLookup<Item> RAW_ORES_LOOKUP = LazyTagLookup.create(ForgeRegistries.ITEMS, Tags.Items.RAW_MATERIALS);
/*     */     
/*     */     private static TagKey<Item> tag(String name) {
/*  97 */       return ItemTags.create(PECore.rl(name));
/*     */     }
/*     */     
/*     */     private static TagKey<Item> curiosTag(String name) {
/* 101 */       return ItemTags.create(new ResourceLocation("curios", name));
/*     */     }
/*     */     
/*     */     private static TagKey<Item> forgeTag(String name) {
/* 105 */       return ItemTags.create(new ResourceLocation("forge", name));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Blocks
/*     */   {
/*     */     private static void init() {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 120 */     public static final TagKey<Block> BLACKLIST_HARVEST = tag("blacklist/harvest");
/*     */ 
/*     */ 
/*     */     
/* 124 */     public static final TagKey<Block> BLACKLIST_TIME_WATCH = tag("blacklist/time_watch");
/*     */     
/* 126 */     public static final TagKey<Block> FARMING_OVERRIDE = tag("farming_override");
/*     */     
/* 128 */     public static final TagKey<Block> NEEDS_DARK_MATTER_TOOL = tag("needs_dark_matter_tool");
/* 129 */     public static final TagKey<Block> NEEDS_RED_MATTER_TOOL = tag("needs_red_matter_tool");
/*     */     
/* 131 */     public static final TagKey<Block> MINEABLE_WITH_PE_KATAR = tag("mineable/katar");
/* 132 */     public static final TagKey<Block> MINEABLE_WITH_PE_HAMMER = tag("mineable/hammer");
/* 133 */     public static final TagKey<Block> MINEABLE_WITH_PE_MORNING_STAR = tag("mineable/morning_star");
/* 134 */     public static final TagKey<Block> MINEABLE_WITH_PE_SHEARS = tag("mineable/shears");
/* 135 */     public static final TagKey<Block> MINEABLE_WITH_PE_SWORD = tag("mineable/sword");
/*     */     
/* 137 */     public static final TagKey<Block> MINEABLE_WITH_HAMMER = forgeTag("mineable/hammer");
/* 138 */     public static final TagKey<Block> MINEABLE_WITH_KATAR = forgeTag("mineable/katar");
/* 139 */     public static final TagKey<Block> MINEABLE_WITH_MORNING_STAR = forgeTag("mineable/morning_star");
/*     */ 
/*     */     
/*     */     private static TagKey<Block> tag(String name) {
/* 143 */       return BlockTags.create(PECore.rl(name));
/*     */     }
/*     */     
/*     */     private static TagKey<Block> forgeTag(String name) {
/* 147 */       return BlockTags.create(new ResourceLocation("forge", name));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Entities
/*     */   {
/*     */     private static void init() {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 162 */     public static final TagKey<EntityType<?>> BLACKLIST_SWRG = tag("blacklist/swrg");
/*     */ 
/*     */ 
/*     */     
/* 166 */     public static final TagKey<EntityType<?>> BLACKLIST_INTERDICTION = tag("blacklist/interdiction");
/*     */ 
/*     */ 
/*     */     
/* 170 */     public static final TagKey<EntityType<?>> RANDOMIZER_PEACEFUL = tag("randomizer/peaceful");
/*     */ 
/*     */ 
/*     */     
/* 174 */     public static final TagKey<EntityType<?>> RANDOMIZER_HOSTILE = tag("randomizer/hostile");
/*     */     
/*     */     private static TagKey<EntityType<?>> tag(String name) {
/* 177 */       return TagKey.m_203882_(Registries.f_256939_, PECore.rl(name));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class BlockEntities
/*     */   {
/*     */     private static void init() {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 192 */     public static final TagKey<BlockEntityType<?>> BLACKLIST_TIME_WATCH = tag("blacklist/time_watch");
/* 193 */     public static final LazyTagLookup<BlockEntityType<?>> BLACKLIST_TIME_WATCH_LOOKUP = LazyTagLookup.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BLACKLIST_TIME_WATCH);
/*     */     
/*     */     private static TagKey<BlockEntityType<?>> tag(String name) {
/* 196 */       return TagKey.m_203882_(Registries.f_256922_, PECore.rl(name));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\PETags.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */