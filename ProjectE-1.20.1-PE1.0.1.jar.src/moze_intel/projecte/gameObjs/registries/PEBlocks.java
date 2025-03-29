/*     */ package moze_intel.projecte.gameObjs.registries;
/*     */ 
/*     */ import java.util.function.BiFunction;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.ToIntFunction;
/*     */ import moze_intel.projecte.gameObjs.EnumCollectorTier;
/*     */ import moze_intel.projecte.gameObjs.EnumFuelType;
/*     */ import moze_intel.projecte.gameObjs.EnumMatterType;
/*     */ import moze_intel.projecte.gameObjs.EnumRelayTier;
/*     */ import moze_intel.projecte.gameObjs.blocks.AlchemicalChest;
/*     */ import moze_intel.projecte.gameObjs.blocks.Collector;
/*     */ import moze_intel.projecte.gameObjs.blocks.Condenser;
/*     */ import moze_intel.projecte.gameObjs.blocks.CondenserMK2;
/*     */ import moze_intel.projecte.gameObjs.blocks.InterdictionTorchEntityBlock;
/*     */ import moze_intel.projecte.gameObjs.blocks.MatterBlock;
/*     */ import moze_intel.projecte.gameObjs.blocks.MatterFurnace;
/*     */ import moze_intel.projecte.gameObjs.blocks.Pedestal;
/*     */ import moze_intel.projecte.gameObjs.blocks.ProjectETNT;
/*     */ import moze_intel.projecte.gameObjs.blocks.Relay;
/*     */ import moze_intel.projecte.gameObjs.blocks.TransmutationStone;
/*     */ import moze_intel.projecte.gameObjs.items.blocks.CollectorItem;
/*     */ import moze_intel.projecte.gameObjs.items.blocks.ItemFuelBlock;
/*     */ import moze_intel.projecte.gameObjs.items.blocks.RelayItem;
/*     */ import moze_intel.projecte.gameObjs.registration.impl.BlockDeferredRegister;
/*     */ import moze_intel.projecte.gameObjs.registration.impl.BlockRegistryObject;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.world.item.BlockItem;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.StandingAndWallBlockItem;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.SoundType;
/*     */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
/*     */ import net.minecraft.world.level.material.MapColor;
/*     */ import net.minecraft.world.level.material.PushReaction;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PEBlocks
/*     */ {
/*  44 */   public static final BlockDeferredRegister BLOCKS = new BlockDeferredRegister("projecte");
/*     */   
/*  46 */   public static final BlockRegistryObject<AlchemicalChest, BlockItem> ALCHEMICAL_CHEST = BLOCKS.register("alchemical_chest", () -> new AlchemicalChest(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283947_).m_280658_(NoteBlockInstrument.BASEDRUM).m_60999_().m_60913_(10.0F, 3600000.0F)));
/*  47 */   public static final BlockRegistryObject<Block, ItemFuelBlock> ALCHEMICAL_COAL = registerFuelBlock("alchemical_coal_block", EnumFuelType.ALCHEMICAL_COAL, MapColor.f_283913_);
/*  48 */   public static final BlockRegistryObject<Block, ItemFuelBlock> MOBIUS_FUEL = registerFuelBlock("mobius_fuel_block", EnumFuelType.MOBIUS_FUEL, MapColor.f_283913_);
/*  49 */   public static final BlockRegistryObject<Block, ItemFuelBlock> AETERNALIS_FUEL = registerFuelBlock("aeternalis_fuel_block", EnumFuelType.AETERNALIS_FUEL, MapColor.f_283779_);
/*  50 */   public static final BlockRegistryObject<Collector, CollectorItem> COLLECTOR = registerCollector("collector_mk1", EnumCollectorTier.MK1, state -> 7);
/*  51 */   public static final BlockRegistryObject<Collector, CollectorItem> COLLECTOR_MK2 = registerCollector("collector_mk2", EnumCollectorTier.MK2, state -> 11); public static final BlockRegistryObject<Condenser, BlockItem> CONDENSER; public static final BlockRegistryObject<CondenserMK2, BlockItem> CONDENSER_MK2;
/*  52 */   public static final BlockRegistryObject<Collector, CollectorItem> COLLECTOR_MK3 = registerCollector("collector_mk3", EnumCollectorTier.MK3, state -> 15); public static final BlockRegistryObject<Pedestal, BlockItem> DARK_MATTER_PEDESTAL; static {
/*  53 */     CONDENSER = registerCondenser("condenser_mk1", Condenser::new, block -> new BlockItem((Block)block, new Item.Properties()));
/*  54 */     CONDENSER_MK2 = registerCondenser("condenser_mk2", CondenserMK2::new, block -> new BlockItem((Block)block, (new Item.Properties()).m_41486_()));
/*  55 */     DARK_MATTER_PEDESTAL = BLOCKS.register("dm_pedestal", () -> new Pedestal(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283927_).m_280658_(NoteBlockInstrument.BASEDRUM).m_60999_().m_60913_(1000000.0F, 3000000.0F).m_60953_(())), block -> new BlockItem((Block)block, (new Item.Properties()).m_41486_()));
/*  56 */   } public static final BlockRegistryObject<MatterFurnace, BlockItem> DARK_MATTER_FURNACE = registerFurnace("dm_furnace", EnumMatterType.DARK_MATTER, 1000000.0F, 3000000.0F);
/*  57 */   public static final BlockRegistryObject<MatterFurnace, BlockItem> RED_MATTER_FURNACE = registerFurnace("rm_furnace", EnumMatterType.RED_MATTER, 2000000.0F, 6000000.0F);
/*  58 */   public static final BlockRegistryObject<MatterBlock, BlockItem> DARK_MATTER = registerMatterBlock("dark_matter_block", EnumMatterType.DARK_MATTER, 1000000.0F, 3000000.0F);
/*  59 */   public static final BlockRegistryObject<MatterBlock, BlockItem> RED_MATTER = registerMatterBlock("red_matter_block", EnumMatterType.RED_MATTER, 2000000.0F, 6000000.0F);
/*  60 */   public static final BlockRegistryObject.WallOrFloorBlockRegistryObject<InterdictionTorchEntityBlock.InterdictionTorch, InterdictionTorchEntityBlock.InterdictionTorchWall, StandingAndWallBlockItem> INTERDICTION_TORCH = BLOCKS.registerWallOrFloorItem("interdiction_torch", moze_intel.projecte.gameObjs.blocks.InterdictionTorchEntityBlock.InterdictionTorch::new, moze_intel.projecte.gameObjs.blocks.InterdictionTorchEntityBlock.InterdictionTorchWall::new, BlockBehaviour.Properties.m_284310_().m_278166_(PushReaction.DESTROY).m_60910_().m_60966_().m_60978_(0.0F).m_60953_(state -> 14).m_60977_());
/*  61 */   public static final BlockRegistryObject<ProjectETNT, BlockItem> NOVA_CATALYST = registerExplosive("nova_catalyst", moze_intel.projecte.gameObjs.entity.EntityNovaCatalystPrimed::new);
/*  62 */   public static final BlockRegistryObject<ProjectETNT, BlockItem> NOVA_CATACLYSM = registerExplosive("nova_cataclysm", moze_intel.projecte.gameObjs.entity.EntityNovaCataclysmPrimed::new);
/*  63 */   public static final BlockRegistryObject<TransmutationStone, BlockItem> TRANSMUTATION_TABLE = BLOCKS.register("transmutation_table", () -> new TransmutationStone(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283947_).m_280658_(NoteBlockInstrument.BASEDRUM).m_60999_().m_60913_(10.0F, 30.0F)));
/*  64 */   public static final BlockRegistryObject<Relay, RelayItem> RELAY = registerRelay("relay_mk1", EnumRelayTier.MK1, state -> 7);
/*  65 */   public static final BlockRegistryObject<Relay, RelayItem> RELAY_MK2 = registerRelay("relay_mk2", EnumRelayTier.MK2, state -> 11);
/*  66 */   public static final BlockRegistryObject<Relay, RelayItem> RELAY_MK3 = registerRelay("relay_mk3", EnumRelayTier.MK3, state -> 15);
/*     */   
/*     */   private static BlockRegistryObject<Block, ItemFuelBlock> registerFuelBlock(String name, EnumFuelType fuelType, MapColor mapColor) {
/*  69 */     return BLOCKS.registerDefaultProperties(name, () -> new Block(BlockBehaviour.Properties.m_284310_().m_284180_(mapColor).m_280658_(NoteBlockInstrument.BASEDRUM).m_60999_().m_60913_(0.5F, 1.5F)), (block, properties) -> new ItemFuelBlock(block, properties, fuelType));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static BlockRegistryObject<Collector, CollectorItem> registerCollector(String name, EnumCollectorTier collectorTier, ToIntFunction<BlockState> lightLevel) {
/*  75 */     return BLOCKS.registerDefaultProperties(name, () -> new Collector(collectorTier, BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283761_).m_280658_(NoteBlockInstrument.PLING).m_60918_(SoundType.f_56744_).m_60999_().m_60913_(0.3F, 0.9F).m_60953_(lightLevel)), CollectorItem::new);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static <CONDENSER extends Condenser> BlockRegistryObject<CONDENSER, BlockItem> registerCondenser(String name, Function<BlockBehaviour.Properties, CONDENSER> condenserFunction, Function<CONDENSER, BlockItem> itemCreator) {
/*  82 */     return BLOCKS.register(name, () -> (Condenser)condenserFunction.apply(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283947_).m_280658_(NoteBlockInstrument.BASEDRUM).m_60999_().m_60913_(10.0F, 3600000.0F)), itemCreator);
/*     */   }
/*     */ 
/*     */   
/*     */   private static BlockRegistryObject<Relay, RelayItem> registerRelay(String name, EnumRelayTier relayTier, ToIntFunction<BlockState> lightLevel) {
/*  87 */     return BLOCKS.registerDefaultProperties(name, () -> new Relay(relayTier, BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283927_).m_280658_(NoteBlockInstrument.BASEDRUM).m_60999_().m_60913_(10.0F, 30.0F).m_60953_(lightLevel)), RelayItem::new);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static BlockRegistryObject<ProjectETNT, BlockItem> registerExplosive(String name, ProjectETNT.TNTEntityCreator tntEntityCreator) {
/*  93 */     return BLOCKS.register(name, () -> new ProjectETNT(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283816_).m_60978_(0.0F).m_60966_().m_60918_(SoundType.f_56740_).m_278183_().m_60924_(()), tntEntityCreator));
/*     */   }
/*     */ 
/*     */   
/*     */   private static BlockRegistryObject<MatterFurnace, BlockItem> registerFurnace(String name, EnumMatterType matterType, float hardness, float resistance) {
/*  98 */     return BLOCKS.register(name, () -> new MatterFurnace(BlockBehaviour.Properties.m_284310_().m_60999_().m_60913_(hardness, resistance).m_284180_(matterType.getMapColor()).m_280658_(NoteBlockInstrument.BASEDRUM).m_60953_(()), matterType), block -> new BlockItem((Block)block, (new Item.Properties()).m_41486_()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static BlockRegistryObject<MatterBlock, BlockItem> registerMatterBlock(String name, EnumMatterType matterType, float hardness, float resistance) {
/* 104 */     return BLOCKS.register(name, () -> new MatterBlock(BlockBehaviour.Properties.m_284310_().m_60999_().m_60913_(hardness, resistance).m_284180_(matterType.getMapColor()).m_280658_(NoteBlockInstrument.BASEDRUM).m_60953_(()), matterType), block -> new BlockItem((Block)block, (new Item.Properties()).m_41486_()));
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registries\PEBlocks.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */