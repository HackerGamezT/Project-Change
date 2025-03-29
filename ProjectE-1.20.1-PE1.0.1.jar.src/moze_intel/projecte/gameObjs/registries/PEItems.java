/*     */ package moze_intel.projecte.gameObjs.registries;
/*     */ 
/*     */ import java.util.function.Function;
/*     */ import moze_intel.projecte.gameObjs.EnumFuelType;
/*     */ import moze_intel.projecte.gameObjs.EnumMatterType;
/*     */ import moze_intel.projecte.gameObjs.items.AlchemicalBag;
/*     */ import moze_intel.projecte.gameObjs.items.AlchemicalFuel;
/*     */ import moze_intel.projecte.gameObjs.items.CataliticLens;
/*     */ import moze_intel.projecte.gameObjs.items.DestructionCatalyst;
/*     */ import moze_intel.projecte.gameObjs.items.DiviningRod;
/*     */ import moze_intel.projecte.gameObjs.items.EvertideAmulet;
/*     */ import moze_intel.projecte.gameObjs.items.GemEternalDensity;
/*     */ import moze_intel.projecte.gameObjs.items.HyperkineticLens;
/*     */ import moze_intel.projecte.gameObjs.items.KleinStar;
/*     */ import moze_intel.projecte.gameObjs.items.MercurialEye;
/*     */ import moze_intel.projecte.gameObjs.items.PhilosophersStone;
/*     */ import moze_intel.projecte.gameObjs.items.RepairTalisman;
/*     */ import moze_intel.projecte.gameObjs.items.Tome;
/*     */ import moze_intel.projecte.gameObjs.items.TransmutationTablet;
/*     */ import moze_intel.projecte.gameObjs.items.VolcaniteAmulet;
/*     */ import moze_intel.projecte.gameObjs.items.armor.DMArmor;
/*     */ import moze_intel.projecte.gameObjs.items.armor.GemChest;
/*     */ import moze_intel.projecte.gameObjs.items.armor.GemFeet;
/*     */ import moze_intel.projecte.gameObjs.items.armor.GemHelmet;
/*     */ import moze_intel.projecte.gameObjs.items.armor.GemLegs;
/*     */ import moze_intel.projecte.gameObjs.items.armor.RMArmor;
/*     */ import moze_intel.projecte.gameObjs.items.rings.Arcana;
/*     */ import moze_intel.projecte.gameObjs.items.rings.ArchangelSmite;
/*     */ import moze_intel.projecte.gameObjs.items.rings.BlackHoleBand;
/*     */ import moze_intel.projecte.gameObjs.items.rings.BodyStone;
/*     */ import moze_intel.projecte.gameObjs.items.rings.HarvestGoddess;
/*     */ import moze_intel.projecte.gameObjs.items.rings.Ignition;
/*     */ import moze_intel.projecte.gameObjs.items.rings.LifeStone;
/*     */ import moze_intel.projecte.gameObjs.items.rings.MindStone;
/*     */ import moze_intel.projecte.gameObjs.items.rings.SWRG;
/*     */ import moze_intel.projecte.gameObjs.items.rings.SoulStone;
/*     */ import moze_intel.projecte.gameObjs.items.rings.TimeWatch;
/*     */ import moze_intel.projecte.gameObjs.items.rings.VoidRing;
/*     */ import moze_intel.projecte.gameObjs.items.rings.Zero;
/*     */ import moze_intel.projecte.gameObjs.items.tools.PEAxe;
/*     */ import moze_intel.projecte.gameObjs.items.tools.PEHammer;
/*     */ import moze_intel.projecte.gameObjs.items.tools.PEHoe;
/*     */ import moze_intel.projecte.gameObjs.items.tools.PEKatar;
/*     */ import moze_intel.projecte.gameObjs.items.tools.PEMorningStar;
/*     */ import moze_intel.projecte.gameObjs.items.tools.PEPickaxe;
/*     */ import moze_intel.projecte.gameObjs.items.tools.PEShears;
/*     */ import moze_intel.projecte.gameObjs.items.tools.PEShovel;
/*     */ import moze_intel.projecte.gameObjs.items.tools.PESword;
/*     */ import moze_intel.projecte.gameObjs.items.tools.RedMatterSword;
/*     */ import moze_intel.projecte.gameObjs.registration.impl.ItemDeferredRegister;
/*     */ import moze_intel.projecte.gameObjs.registration.impl.ItemRegistryObject;
/*     */ import moze_intel.projecte.utils.text.ILangEntry;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.world.item.ArmorItem;
/*     */ import net.minecraft.world.item.DyeColor;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.Rarity;
/*     */ 
/*     */ public class PEItems
/*     */ {
/*  61 */   public static final ItemDeferredRegister ITEMS = new ItemDeferredRegister("projecte");
/*     */   
/*  63 */   public static final ItemRegistryObject<PhilosophersStone> PHILOSOPHERS_STONE = ITEMS.registerNoStack("philosophers_stone", PhilosophersStone::new);
/*  64 */   public static final ItemRegistryObject<RepairTalisman> REPAIR_TALISMAN = ITEMS.registerNoStack("repair_talisman", RepairTalisman::new);
/*  65 */   public static final ItemRegistryObject<Item> LOW_COVALENCE_DUST = ITEMS.register("low_covalence_dust");
/*  66 */   public static final ItemRegistryObject<Item> MEDIUM_COVALENCE_DUST = ITEMS.register("medium_covalence_dust");
/*  67 */   public static final ItemRegistryObject<Item> HIGH_COVALENCE_DUST = ITEMS.register("high_covalence_dust");
/*     */   
/*  69 */   public static final ItemRegistryObject<AlchemicalBag> WHITE_ALCHEMICAL_BAG = registerBag(DyeColor.WHITE);
/*  70 */   public static final ItemRegistryObject<AlchemicalBag> ORANGE_ALCHEMICAL_BAG = registerBag(DyeColor.ORANGE);
/*  71 */   public static final ItemRegistryObject<AlchemicalBag> MAGENTA_ALCHEMICAL_BAG = registerBag(DyeColor.MAGENTA);
/*  72 */   public static final ItemRegistryObject<AlchemicalBag> LIGHT_BLUE_ALCHEMICAL_BAG = registerBag(DyeColor.LIGHT_BLUE);
/*  73 */   public static final ItemRegistryObject<AlchemicalBag> YELLOW_ALCHEMICAL_BAG = registerBag(DyeColor.YELLOW);
/*  74 */   public static final ItemRegistryObject<AlchemicalBag> LIME_ALCHEMICAL_BAG = registerBag(DyeColor.LIME);
/*  75 */   public static final ItemRegistryObject<AlchemicalBag> PINK_ALCHEMICAL_BAG = registerBag(DyeColor.PINK);
/*  76 */   public static final ItemRegistryObject<AlchemicalBag> GRAY_ALCHEMICAL_BAG = registerBag(DyeColor.GRAY);
/*  77 */   public static final ItemRegistryObject<AlchemicalBag> LIGHT_GRAY_ALCHEMICAL_BAG = registerBag(DyeColor.LIGHT_GRAY);
/*  78 */   public static final ItemRegistryObject<AlchemicalBag> CYAN_ALCHEMICAL_BAG = registerBag(DyeColor.CYAN);
/*  79 */   public static final ItemRegistryObject<AlchemicalBag> PURPLE_ALCHEMICAL_BAG = registerBag(DyeColor.PURPLE);
/*  80 */   public static final ItemRegistryObject<AlchemicalBag> BLUE_ALCHEMICAL_BAG = registerBag(DyeColor.BLUE);
/*  81 */   public static final ItemRegistryObject<AlchemicalBag> BROWN_ALCHEMICAL_BAG = registerBag(DyeColor.BROWN);
/*  82 */   public static final ItemRegistryObject<AlchemicalBag> GREEN_ALCHEMICAL_BAG = registerBag(DyeColor.GREEN);
/*  83 */   public static final ItemRegistryObject<AlchemicalBag> RED_ALCHEMICAL_BAG = registerBag(DyeColor.RED);
/*  84 */   public static final ItemRegistryObject<AlchemicalBag> BLACK_ALCHEMICAL_BAG = registerBag(DyeColor.BLACK);
/*     */   
/*  86 */   public static final ItemRegistryObject<KleinStar> KLEIN_STAR_EIN = registerKleinStar(KleinStar.EnumKleinTier.EIN);
/*  87 */   public static final ItemRegistryObject<KleinStar> KLEIN_STAR_ZWEI = registerKleinStar(KleinStar.EnumKleinTier.ZWEI);
/*  88 */   public static final ItemRegistryObject<KleinStar> KLEIN_STAR_DREI = registerKleinStar(KleinStar.EnumKleinTier.DREI);
/*  89 */   public static final ItemRegistryObject<KleinStar> KLEIN_STAR_VIER = registerKleinStar(KleinStar.EnumKleinTier.VIER);
/*  90 */   public static final ItemRegistryObject<KleinStar> KLEIN_STAR_SPHERE = registerKleinStar(KleinStar.EnumKleinTier.SPHERE);
/*  91 */   public static final ItemRegistryObject<KleinStar> KLEIN_STAR_OMEGA = registerKleinStar(KleinStar.EnumKleinTier.OMEGA);
/*     */   
/*  93 */   public static final ItemRegistryObject<AlchemicalFuel> ALCHEMICAL_COAL = registerAlchemicalFuel(EnumFuelType.ALCHEMICAL_COAL);
/*  94 */   public static final ItemRegistryObject<AlchemicalFuel> MOBIUS_FUEL = registerAlchemicalFuel(EnumFuelType.MOBIUS_FUEL);
/*  95 */   public static final ItemRegistryObject<AlchemicalFuel> AETERNALIS_FUEL = registerAlchemicalFuel(EnumFuelType.AETERNALIS_FUEL);
/*  96 */   public static final ItemRegistryObject<Item> DARK_MATTER = ITEMS.registerFireImmune("dark_matter");
/*  97 */   public static final ItemRegistryObject<Item> RED_MATTER = ITEMS.registerFireImmune("red_matter"); public static final ItemRegistryObject<PEPickaxe> DARK_MATTER_PICKAXE; public static final ItemRegistryObject<PEAxe> DARK_MATTER_AXE; public static final ItemRegistryObject<PEShovel> DARK_MATTER_SHOVEL; public static final ItemRegistryObject<PESword> DARK_MATTER_SWORD; public static final ItemRegistryObject<PEHoe> DARK_MATTER_HOE; public static final ItemRegistryObject<PEShears> DARK_MATTER_SHEARS; public static final ItemRegistryObject<PEHammer> DARK_MATTER_HAMMER; public static final ItemRegistryObject<PEPickaxe> RED_MATTER_PICKAXE; public static final ItemRegistryObject<PEAxe> RED_MATTER_AXE; public static final ItemRegistryObject<PEShovel> RED_MATTER_SHOVEL;
/*     */   static {
/*  99 */     DARK_MATTER_PICKAXE = ITEMS.registerNoStackFireImmune("dm_pick", properties -> new PEPickaxe(EnumMatterType.DARK_MATTER, 2, properties));
/* 100 */     DARK_MATTER_AXE = ITEMS.registerNoStackFireImmune("dm_axe", properties -> new PEAxe(EnumMatterType.DARK_MATTER, 2, properties));
/* 101 */     DARK_MATTER_SHOVEL = ITEMS.registerNoStackFireImmune("dm_shovel", properties -> new PEShovel(EnumMatterType.DARK_MATTER, 2, properties));
/* 102 */     DARK_MATTER_SWORD = ITEMS.registerNoStackFireImmune("dm_sword", properties -> new PESword(EnumMatterType.DARK_MATTER, 2, 9, properties));
/* 103 */     DARK_MATTER_HOE = ITEMS.registerNoStackFireImmune("dm_hoe", properties -> new PEHoe(EnumMatterType.DARK_MATTER, 2, properties));
/* 104 */     DARK_MATTER_SHEARS = ITEMS.registerNoStackFireImmune("dm_shears", properties -> new PEShears(EnumMatterType.DARK_MATTER, 2, properties));
/* 105 */     DARK_MATTER_HAMMER = ITEMS.registerNoStackFireImmune("dm_hammer", properties -> new PEHammer(EnumMatterType.DARK_MATTER, 2, properties));
/*     */     
/* 107 */     RED_MATTER_PICKAXE = ITEMS.registerNoStackFireImmune("rm_pick", properties -> new PEPickaxe(EnumMatterType.RED_MATTER, 3, properties));
/* 108 */     RED_MATTER_AXE = ITEMS.registerNoStackFireImmune("rm_axe", properties -> new PEAxe(EnumMatterType.RED_MATTER, 3, properties));
/* 109 */     RED_MATTER_SHOVEL = ITEMS.registerNoStackFireImmune("rm_shovel", properties -> new PEShovel(EnumMatterType.RED_MATTER, 3, properties));
/* 110 */   } public static final ItemRegistryObject<RedMatterSword> RED_MATTER_SWORD = ITEMS.registerNoStackFireImmune("rm_sword", RedMatterSword::new); public static final ItemRegistryObject<PEHoe> RED_MATTER_HOE; public static final ItemRegistryObject<PEShears> RED_MATTER_SHEARS; public static final ItemRegistryObject<PEHammer> RED_MATTER_HAMMER; public static final ItemRegistryObject<PEKatar> RED_MATTER_KATAR; public static final ItemRegistryObject<PEMorningStar> RED_MATTER_MORNING_STAR; public static final ItemRegistryObject<DMArmor> DARK_MATTER_HELMET; public static final ItemRegistryObject<DMArmor> DARK_MATTER_CHESTPLATE; public static final ItemRegistryObject<DMArmor> DARK_MATTER_LEGGINGS; public static final ItemRegistryObject<DMArmor> DARK_MATTER_BOOTS; public static final ItemRegistryObject<RMArmor> RED_MATTER_HELMET; public static final ItemRegistryObject<RMArmor> RED_MATTER_CHESTPLATE; public static final ItemRegistryObject<RMArmor> RED_MATTER_LEGGINGS; public static final ItemRegistryObject<RMArmor> RED_MATTER_BOOTS; static {
/* 111 */     RED_MATTER_HOE = ITEMS.registerNoStackFireImmune("rm_hoe", properties -> new PEHoe(EnumMatterType.RED_MATTER, 3, properties));
/* 112 */     RED_MATTER_SHEARS = ITEMS.registerNoStackFireImmune("rm_shears", properties -> new PEShears(EnumMatterType.RED_MATTER, 3, properties));
/* 113 */     RED_MATTER_HAMMER = ITEMS.registerNoStackFireImmune("rm_hammer", properties -> new PEHammer(EnumMatterType.RED_MATTER, 3, properties));
/* 114 */     RED_MATTER_KATAR = ITEMS.registerNoStackFireImmune("rm_katar", properties -> new PEKatar(EnumMatterType.RED_MATTER, 4, properties));
/* 115 */     RED_MATTER_MORNING_STAR = ITEMS.registerNoStackFireImmune("rm_morning_star", properties -> new PEMorningStar(EnumMatterType.RED_MATTER, 4, properties));
/*     */     
/* 117 */     DARK_MATTER_HELMET = ITEMS.registerNoStackFireImmune("dm_helmet", properties -> new DMArmor(ArmorItem.Type.HELMET, properties));
/* 118 */     DARK_MATTER_CHESTPLATE = ITEMS.registerNoStackFireImmune("dm_chestplate", properties -> new DMArmor(ArmorItem.Type.CHESTPLATE, properties));
/* 119 */     DARK_MATTER_LEGGINGS = ITEMS.registerNoStackFireImmune("dm_leggings", properties -> new DMArmor(ArmorItem.Type.LEGGINGS, properties));
/* 120 */     DARK_MATTER_BOOTS = ITEMS.registerNoStackFireImmune("dm_boots", properties -> new DMArmor(ArmorItem.Type.BOOTS, properties));
/*     */     
/* 122 */     RED_MATTER_HELMET = ITEMS.registerNoStackFireImmune("rm_helmet", properties -> new RMArmor(ArmorItem.Type.HELMET, properties));
/* 123 */     RED_MATTER_CHESTPLATE = ITEMS.registerNoStackFireImmune("rm_chestplate", properties -> new RMArmor(ArmorItem.Type.CHESTPLATE, properties));
/* 124 */     RED_MATTER_LEGGINGS = ITEMS.registerNoStackFireImmune("rm_leggings", properties -> new RMArmor(ArmorItem.Type.LEGGINGS, properties));
/* 125 */     RED_MATTER_BOOTS = ITEMS.registerNoStackFireImmune("rm_boots", properties -> new RMArmor(ArmorItem.Type.BOOTS, properties));
/*     */   }
/* 127 */   public static final ItemRegistryObject<GemHelmet> GEM_HELMET = ITEMS.registerNoStackFireImmune("gem_helmet", GemHelmet::new);
/* 128 */   public static final ItemRegistryObject<GemChest> GEM_CHESTPLATE = ITEMS.registerNoStackFireImmune("gem_chestplate", GemChest::new);
/* 129 */   public static final ItemRegistryObject<GemLegs> GEM_LEGGINGS = ITEMS.registerNoStackFireImmune("gem_leggings", GemLegs::new);
/* 130 */   public static final ItemRegistryObject<GemFeet> GEM_BOOTS = ITEMS.registerNoStackFireImmune("gem_boots", GemFeet::new);
/*     */   
/* 132 */   public static final ItemRegistryObject<Item> IRON_BAND = ITEMS.register("iron_band");
/* 133 */   public static final ItemRegistryObject<BlackHoleBand> BLACK_HOLE_BAND = ITEMS.registerNoStackFireImmune("black_hole_band", BlackHoleBand::new);
/* 134 */   public static final ItemRegistryObject<ArchangelSmite> ARCHANGEL_SMITE = ITEMS.registerNoStackFireImmune("archangel_smite", ArchangelSmite::new);
/* 135 */   public static final ItemRegistryObject<HarvestGoddess> HARVEST_GODDESS_BAND = ITEMS.registerNoStackFireImmune("harvest_goddess_band", HarvestGoddess::new);
/* 136 */   public static final ItemRegistryObject<Ignition> IGNITION_RING = ITEMS.registerNoStackFireImmune("ignition_ring", Ignition::new);
/* 137 */   public static final ItemRegistryObject<Zero> ZERO_RING = ITEMS.registerNoStackFireImmune("zero_ring", Zero::new);
/* 138 */   public static final ItemRegistryObject<SWRG> SWIFTWOLF_RENDING_GALE = ITEMS.registerNoStackFireImmune("swiftwolf_rending_gale", SWRG::new);
/* 139 */   public static final ItemRegistryObject<TimeWatch> WATCH_OF_FLOWING_TIME = ITEMS.registerNoStackFireImmune("watch_of_flowing_time", TimeWatch::new);
/* 140 */   public static final ItemRegistryObject<EvertideAmulet> EVERTIDE_AMULET = ITEMS.registerNoStackFireImmune("evertide_amulet", EvertideAmulet::new);
/* 141 */   public static final ItemRegistryObject<VolcaniteAmulet> VOLCANITE_AMULET = ITEMS.registerNoStackFireImmune("volcanite_amulet", VolcaniteAmulet::new);
/* 142 */   public static final ItemRegistryObject<GemEternalDensity> GEM_OF_ETERNAL_DENSITY = ITEMS.registerNoStackFireImmune("gem_of_eternal_density", GemEternalDensity::new);
/* 143 */   public static final ItemRegistryObject<MercurialEye> MERCURIAL_EYE = ITEMS.registerNoStackFireImmune("mercurial_eye", MercurialEye::new);
/* 144 */   public static final ItemRegistryObject<VoidRing> VOID_RING = ITEMS.registerNoStackFireImmune("void_ring", VoidRing::new); public static final ItemRegistryObject<Arcana> ARCANA_RING; static {
/* 145 */     ARCANA_RING = ITEMS.registerNoStackFireImmune("arcana_ring", properties -> new Arcana(properties.m_41497_(Rarity.RARE)));
/* 146 */   } public static final ItemRegistryObject<BodyStone> BODY_STONE = ITEMS.registerNoStackFireImmune("body_stone", BodyStone::new);
/* 147 */   public static final ItemRegistryObject<SoulStone> SOUL_STONE = ITEMS.registerNoStackFireImmune("soul_stone", SoulStone::new);
/* 148 */   public static final ItemRegistryObject<MindStone> MIND_STONE = ITEMS.registerNoStackFireImmune("mind_stone", MindStone::new);
/* 149 */   public static final ItemRegistryObject<LifeStone> LIFE_STONE = ITEMS.registerNoStackFireImmune("life_stone", LifeStone::new); public static final ItemRegistryObject<DiviningRod> LOW_DIVINING_ROD; public static final ItemRegistryObject<DiviningRod> MEDIUM_DIVINING_ROD; public static final ItemRegistryObject<DiviningRod> HIGH_DIVINING_ROD;
/*     */   static {
/* 151 */     LOW_DIVINING_ROD = ITEMS.registerNoStack("divining_rod_1", properties -> new DiviningRod(properties, new ILangEntry[] { (ILangEntry)PELang.DIVINING_RANGE_3 }));
/* 152 */     MEDIUM_DIVINING_ROD = ITEMS.registerNoStack("divining_rod_2", properties -> new DiviningRod(properties, new ILangEntry[] { (ILangEntry)PELang.DIVINING_RANGE_3, (ILangEntry)PELang.DIVINING_RANGE_16 }));
/* 153 */     HIGH_DIVINING_ROD = ITEMS.registerNoStack("divining_rod_3", properties -> new DiviningRod(properties, new ILangEntry[] { (ILangEntry)PELang.DIVINING_RANGE_3, (ILangEntry)PELang.DIVINING_RANGE_16, (ILangEntry)PELang.DIVINING_RANGE_64 }));
/*     */   }
/* 155 */   public static final ItemRegistryObject<DestructionCatalyst> DESTRUCTION_CATALYST = ITEMS.registerNoStack("destruction_catalyst", DestructionCatalyst::new);
/* 156 */   public static final ItemRegistryObject<HyperkineticLens> HYPERKINETIC_LENS = ITEMS.registerNoStackFireImmune("hyperkinetic_lens", HyperkineticLens::new);
/* 157 */   public static final ItemRegistryObject<CataliticLens> CATALYTIC_LENS = ITEMS.registerNoStackFireImmune("catalytic_lens", CataliticLens::new); public static final ItemRegistryObject<Tome> TOME_OF_KNOWLEDGE;
/*     */   static {
/* 159 */     TOME_OF_KNOWLEDGE = ITEMS.registerNoStack("tome", properties -> new Tome(properties.m_41497_(Rarity.EPIC)));
/* 160 */   } public static final ItemRegistryObject<TransmutationTablet> TRANSMUTATION_TABLET = ITEMS.registerNoStackFireImmune("transmutation_tablet", TransmutationTablet::new);
/*     */   
/*     */   private static ItemRegistryObject<AlchemicalBag> registerBag(DyeColor color) {
/* 163 */     return ITEMS.registerNoStack(color.m_41065_() + "_alchemical_bag", properties -> new AlchemicalBag(properties, color));
/*     */   }
/*     */   
/*     */   private static ItemRegistryObject<KleinStar> registerKleinStar(KleinStar.EnumKleinTier tier) {
/* 167 */     return ITEMS.registerNoStack("klein_star_" + tier.name, properties -> {
/*     */           if (tier == KleinStar.EnumKleinTier.OMEGA) {
/*     */             properties = properties.m_41497_(Rarity.EPIC);
/*     */           }
/*     */           return new KleinStar(properties, tier);
/*     */         });
/*     */   }
/*     */   
/*     */   private static ItemRegistryObject<AlchemicalFuel> registerAlchemicalFuel(EnumFuelType fuelType) {
/* 176 */     return ITEMS.register(fuelType.m_7912_(), properties -> {
/*     */           if (fuelType == EnumFuelType.AETERNALIS_FUEL) {
/*     */             properties = properties.m_41497_(Rarity.RARE);
/*     */           }
/*     */           return new AlchemicalFuel(properties, fuelType);
/*     */         });
/*     */   }
/*     */   
/*     */   public static AlchemicalBag getBag(DyeColor color) {
/* 185 */     switch (color) { default: throw new IncompatibleClassChangeError();case EIN: case ZWEI: case DREI: case VIER: case SPHERE: case OMEGA: case null: case null: case null: case null: case null: case null: case null: case null: case null: case null: break; }  return 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 201 */       (AlchemicalBag)BLACK_ALCHEMICAL_BAG.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public static KleinStar getStar(KleinStar.EnumKleinTier tier) {
/* 206 */     switch (tier) { default: throw new IncompatibleClassChangeError();case EIN: case ZWEI: case DREI: case VIER: case SPHERE: case OMEGA: break; }  return 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 212 */       (KleinStar)KLEIN_STAR_OMEGA.get();
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registries\PEItems.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */