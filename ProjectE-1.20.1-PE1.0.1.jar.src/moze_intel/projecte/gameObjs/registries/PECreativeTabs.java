/*     */ package moze_intel.projecte.gameObjs.registries;
/*     */ 
/*     */ import java.util.Objects;
/*     */ import java.util.function.Consumer;
/*     */ import moze_intel.projecte.gameObjs.items.rings.Arcana;
/*     */ import moze_intel.projecte.gameObjs.registration.impl.CreativeTabDeferredRegister;
/*     */ import moze_intel.projecte.gameObjs.registration.impl.CreativeTabRegistryObject;
/*     */ import moze_intel.projecte.utils.text.ILangEntry;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.resources.ResourceKey;
/*     */ import net.minecraft.world.item.CreativeModeTab;
/*     */ import net.minecraft.world.item.CreativeModeTabs;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
/*     */ 
/*     */ public class PECreativeTabs {
/*  18 */   public static final CreativeTabDeferredRegister CREATIVE_TABS = new CreativeTabDeferredRegister("projecte", PECreativeTabs::addToExistingTabs);
/*     */   static {
/*  20 */     PROJECTE = CREATIVE_TABS.registerMain((ILangEntry)PELang.PROJECTE, (ItemLike)PEItems.PHILOSOPHERS_STONE, builder -> builder.m_257501_(()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final CreativeTabRegistryObject PROJECTE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void addArmor(Consumer<ItemLike> output) {
/* 141 */     output.accept(PEItems.DARK_MATTER_HELMET);
/* 142 */     output.accept(PEItems.DARK_MATTER_CHESTPLATE);
/* 143 */     output.accept(PEItems.DARK_MATTER_LEGGINGS);
/* 144 */     output.accept(PEItems.DARK_MATTER_BOOTS);
/*     */     
/* 146 */     output.accept(PEItems.RED_MATTER_HELMET);
/* 147 */     output.accept(PEItems.RED_MATTER_CHESTPLATE);
/* 148 */     output.accept(PEItems.RED_MATTER_LEGGINGS);
/* 149 */     output.accept(PEItems.RED_MATTER_BOOTS);
/*     */     
/* 151 */     output.accept(PEItems.GEM_HELMET);
/* 152 */     output.accept(PEItems.GEM_CHESTPLATE);
/* 153 */     output.accept(PEItems.GEM_LEGGINGS);
/* 154 */     output.accept(PEItems.GEM_BOOTS);
/*     */   }
/*     */   
/*     */   private static void addToExistingTabs(BuildCreativeModeTabContentsEvent event) {
/* 158 */     ResourceKey<CreativeModeTab> tabKey = event.getTabKey();
/* 159 */     if (tabKey == CreativeModeTabs.f_256788_) {
/* 160 */       addToExistingTab(event, new ItemLike[] { (ItemLike)PEBlocks.ALCHEMICAL_COAL, (ItemLike)PEBlocks.MOBIUS_FUEL, (ItemLike)PEBlocks.AETERNALIS_FUEL, (ItemLike)PEBlocks.DARK_MATTER, (ItemLike)PEBlocks.RED_MATTER });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 167 */     else if (tabKey == CreativeModeTabs.f_256791_) {
/* 168 */       addToExistingTab(event, new ItemLike[] { (ItemLike)PEBlocks.INTERDICTION_TORCH, (ItemLike)PEBlocks.TRANSMUTATION_TABLE, (ItemLike)PEBlocks.ALCHEMICAL_CHEST, (ItemLike)PEBlocks.CONDENSER, (ItemLike)PEBlocks.CONDENSER_MK2, (ItemLike)PEBlocks.COLLECTOR, (ItemLike)PEBlocks.COLLECTOR_MK2, (ItemLike)PEBlocks.COLLECTOR_MK3, (ItemLike)PEBlocks.RELAY, (ItemLike)PEBlocks.RELAY_MK2, (ItemLike)PEBlocks.RELAY_MK3, (ItemLike)PEBlocks.DARK_MATTER_PEDESTAL, (ItemLike)PEBlocks.DARK_MATTER_FURNACE, (ItemLike)PEBlocks.RED_MATTER_FURNACE });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 188 */     else if (tabKey == CreativeModeTabs.f_257028_) {
/* 189 */       addToExistingTab(event, new ItemLike[] { (ItemLike)PEBlocks.ALCHEMICAL_CHEST, (ItemLike)PEBlocks.CONDENSER, (ItemLike)PEBlocks.CONDENSER_MK2, (ItemLike)PEBlocks.COLLECTOR, (ItemLike)PEBlocks.COLLECTOR_MK2, (ItemLike)PEBlocks.COLLECTOR_MK3, (ItemLike)PEBlocks.RELAY, (ItemLike)PEBlocks.RELAY_MK2, (ItemLike)PEBlocks.RELAY_MK3, (ItemLike)PEBlocks.DARK_MATTER_PEDESTAL, (ItemLike)PEBlocks.DARK_MATTER_FURNACE, (ItemLike)PEBlocks.RED_MATTER_FURNACE, (ItemLike)PEBlocks.NOVA_CATALYST, (ItemLike)PEBlocks.NOVA_CATACLYSM });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 210 */     else if (tabKey == CreativeModeTabs.f_256869_) {
/* 211 */       addToExistingTab(event, new ItemLike[] { (ItemLike)PEItems.DARK_MATTER_PICKAXE, (ItemLike)PEItems.DARK_MATTER_AXE, (ItemLike)PEItems.DARK_MATTER_SHOVEL, (ItemLike)PEItems.DARK_MATTER_HOE, (ItemLike)PEItems.DARK_MATTER_SHEARS, (ItemLike)PEItems.DARK_MATTER_HAMMER, (ItemLike)PEItems.RED_MATTER_PICKAXE, (ItemLike)PEItems.RED_MATTER_AXE, (ItemLike)PEItems.RED_MATTER_SHOVEL, (ItemLike)PEItems.RED_MATTER_HOE, (ItemLike)PEItems.RED_MATTER_SHEARS, (ItemLike)PEItems.RED_MATTER_HAMMER, (ItemLike)PEItems.RED_MATTER_MORNING_STAR });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 228 */       addToExistingTab(event, new ItemLike[] { (ItemLike)PEItems.PHILOSOPHERS_STONE, (ItemLike)PEItems.REPAIR_TALISMAN, (ItemLike)PEItems.TOME_OF_KNOWLEDGE, (ItemLike)PEItems.TRANSMUTATION_TABLET, (ItemLike)PEItems.DESTRUCTION_CATALYST, (ItemLike)PEItems.BLACK_HOLE_BAND, (ItemLike)PEItems.HARVEST_GODDESS_BAND, (ItemLike)PEItems.IGNITION_RING, (ItemLike)PEItems.ZERO_RING, (ItemLike)PEItems.SWIFTWOLF_RENDING_GALE, (ItemLike)PEItems.WATCH_OF_FLOWING_TIME, (ItemLike)PEItems.EVERTIDE_AMULET, (ItemLike)PEItems.VOLCANITE_AMULET, (ItemLike)PEItems.GEM_OF_ETERNAL_DENSITY, (ItemLike)PEItems.MERCURIAL_EYE, (ItemLike)PEItems.VOID_RING });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 249 */       for (byte i = 0; i < ((Arcana)PEItems.ARCANA_RING.m_5456_()).getModeCount(); i = (byte)(i + 1)) {
/* 250 */         ItemStack stack = new ItemStack((ItemLike)PEItems.ARCANA_RING);
/* 251 */         stack.m_41784_().m_128344_("Mode", i);
/* 252 */         event.m_246342_(stack);
/*     */       } 
/*     */       
/* 255 */       addToExistingTab(event, new ItemLike[] { (ItemLike)PEItems.BODY_STONE, (ItemLike)PEItems.SOUL_STONE, (ItemLike)PEItems.MIND_STONE, (ItemLike)PEItems.LIFE_STONE, (ItemLike)PEItems.LOW_DIVINING_ROD, (ItemLike)PEItems.MEDIUM_DIVINING_ROD, (ItemLike)PEItems.HIGH_DIVINING_ROD });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 266 */       addToExistingTab(event, new ItemLike[] { (ItemLike)PEItems.KLEIN_STAR_EIN, (ItemLike)PEItems.KLEIN_STAR_ZWEI, (ItemLike)PEItems.KLEIN_STAR_DREI, (ItemLike)PEItems.KLEIN_STAR_VIER, (ItemLike)PEItems.KLEIN_STAR_SPHERE, (ItemLike)PEItems.KLEIN_STAR_OMEGA });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 275 */       addToExistingTab(event, new ItemLike[] { (ItemLike)PEItems.WHITE_ALCHEMICAL_BAG, (ItemLike)PEItems.ORANGE_ALCHEMICAL_BAG, (ItemLike)PEItems.MAGENTA_ALCHEMICAL_BAG, (ItemLike)PEItems.LIGHT_BLUE_ALCHEMICAL_BAG, (ItemLike)PEItems.YELLOW_ALCHEMICAL_BAG, (ItemLike)PEItems.LIME_ALCHEMICAL_BAG, (ItemLike)PEItems.PINK_ALCHEMICAL_BAG, (ItemLike)PEItems.GRAY_ALCHEMICAL_BAG, (ItemLike)PEItems.LIGHT_GRAY_ALCHEMICAL_BAG, (ItemLike)PEItems.CYAN_ALCHEMICAL_BAG, (ItemLike)PEItems.PURPLE_ALCHEMICAL_BAG, (ItemLike)PEItems.BLUE_ALCHEMICAL_BAG, (ItemLike)PEItems.BROWN_ALCHEMICAL_BAG, (ItemLike)PEItems.GREEN_ALCHEMICAL_BAG, (ItemLike)PEItems.RED_ALCHEMICAL_BAG, (ItemLike)PEItems.BLACK_ALCHEMICAL_BAG });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 293 */     else if (tabKey == CreativeModeTabs.f_256797_) {
/* 294 */       addToExistingTab(event, new ItemLike[] { (ItemLike)PEItems.DARK_MATTER_SWORD, (ItemLike)PEItems.RED_MATTER_SWORD, (ItemLike)PEItems.RED_MATTER_KATAR });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 300 */       Objects.requireNonNull(event); addArmor(event::m_246326_);
/*     */       
/* 302 */       addToExistingTab(event, new ItemLike[] { (ItemLike)PEItems.ARCHANGEL_SMITE, (ItemLike)PEItems.HYPERKINETIC_LENS, (ItemLike)PEItems.CATALYTIC_LENS });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 309 */     else if (tabKey == CreativeModeTabs.f_256968_) {
/* 310 */       addToExistingTab(event, new ItemLike[] { (ItemLike)PEItems.LOW_COVALENCE_DUST, (ItemLike)PEItems.MEDIUM_COVALENCE_DUST, (ItemLike)PEItems.HIGH_COVALENCE_DUST, (ItemLike)PEItems.ALCHEMICAL_COAL, (ItemLike)PEItems.MOBIUS_FUEL, (ItemLike)PEItems.AETERNALIS_FUEL, (ItemLike)PEItems.DARK_MATTER, (ItemLike)PEItems.RED_MATTER, (ItemLike)PEItems.IRON_BAND });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void addToExistingTab(BuildCreativeModeTabContentsEvent event, ItemLike... items) {
/* 327 */     for (ItemLike item : items)
/* 328 */       event.m_246326_(item); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registries\PECreativeTabs.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */