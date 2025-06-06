/*    */ package moze_intel.projecte.gameObjs.registries;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.block_entities.AlchBlockEntityChest;
/*    */ import moze_intel.projecte.gameObjs.block_entities.CollectorMK1BlockEntity;
/*    */ import moze_intel.projecte.gameObjs.block_entities.CollectorMK2BlockEntity;
/*    */ import moze_intel.projecte.gameObjs.block_entities.CollectorMK3BlockEntity;
/*    */ import moze_intel.projecte.gameObjs.block_entities.CondenserBlockEntity;
/*    */ import moze_intel.projecte.gameObjs.block_entities.CondenserMK2BlockEntity;
/*    */ import moze_intel.projecte.gameObjs.block_entities.DMFurnaceBlockEntity;
/*    */ import moze_intel.projecte.gameObjs.block_entities.RMFurnaceBlockEntity;
/*    */ import moze_intel.projecte.gameObjs.block_entities.RelayMK1BlockEntity;
/*    */ import moze_intel.projecte.gameObjs.block_entities.RelayMK2BlockEntity;
/*    */ import moze_intel.projecte.gameObjs.block_entities.RelayMK3BlockEntity;
/*    */ import moze_intel.projecte.gameObjs.container.AlchBagContainer;
/*    */ import moze_intel.projecte.gameObjs.container.AlchChestContainer;
/*    */ import moze_intel.projecte.gameObjs.container.CollectorMK1Container;
/*    */ import moze_intel.projecte.gameObjs.container.CollectorMK2Container;
/*    */ import moze_intel.projecte.gameObjs.container.CollectorMK3Container;
/*    */ import moze_intel.projecte.gameObjs.container.CondenserContainer;
/*    */ import moze_intel.projecte.gameObjs.container.CondenserMK2Container;
/*    */ import moze_intel.projecte.gameObjs.container.DMFurnaceContainer;
/*    */ import moze_intel.projecte.gameObjs.container.EternalDensityContainer;
/*    */ import moze_intel.projecte.gameObjs.container.MercurialEyeContainer;
/*    */ import moze_intel.projecte.gameObjs.container.RMFurnaceContainer;
/*    */ import moze_intel.projecte.gameObjs.container.RelayMK1Container;
/*    */ import moze_intel.projecte.gameObjs.container.RelayMK2Container;
/*    */ import moze_intel.projecte.gameObjs.container.RelayMK3Container;
/*    */ import moze_intel.projecte.gameObjs.container.TransmutationContainer;
/*    */ import moze_intel.projecte.gameObjs.registration.INamedEntry;
/*    */ import moze_intel.projecte.gameObjs.registration.impl.ContainerTypeDeferredRegister;
/*    */ import moze_intel.projecte.gameObjs.registration.impl.ContainerTypeRegistryObject;
/*    */ 
/*    */ public class PEContainerTypes
/*    */ {
/* 35 */   public static final ContainerTypeDeferredRegister CONTAINER_TYPES = new ContainerTypeDeferredRegister("projecte");
/*    */   
/* 37 */   public static final ContainerTypeRegistryObject<RMFurnaceContainer> RM_FURNACE_CONTAINER = CONTAINER_TYPES.register((INamedEntry)PEBlocks.RED_MATTER_FURNACE, RMFurnaceBlockEntity.class, RMFurnaceContainer::new);
/* 38 */   public static final ContainerTypeRegistryObject<DMFurnaceContainer> DM_FURNACE_CONTAINER = CONTAINER_TYPES.register((INamedEntry)PEBlocks.DARK_MATTER_FURNACE, DMFurnaceBlockEntity.class, DMFurnaceContainer::new);
/* 39 */   public static final ContainerTypeRegistryObject<CondenserContainer> CONDENSER_CONTAINER = CONTAINER_TYPES.register((INamedEntry)PEBlocks.CONDENSER, CondenserBlockEntity.class, CondenserContainer::new);
/* 40 */   public static final ContainerTypeRegistryObject<CondenserMK2Container> CONDENSER_MK2_CONTAINER = CONTAINER_TYPES.register((INamedEntry)PEBlocks.CONDENSER_MK2, CondenserMK2BlockEntity.class, CondenserMK2Container::new);
/* 41 */   public static final ContainerTypeRegistryObject<AlchChestContainer> ALCH_CHEST_CONTAINER = CONTAINER_TYPES.register((INamedEntry)PEBlocks.ALCHEMICAL_CHEST, AlchBlockEntityChest.class, AlchChestContainer::new);
/* 42 */   public static final ContainerTypeRegistryObject<AlchBagContainer> ALCH_BAG_CONTAINER = CONTAINER_TYPES.register("alchemical_bag", AlchBagContainer::fromNetwork);
/* 43 */   public static final ContainerTypeRegistryObject<EternalDensityContainer> ETERNAL_DENSITY_CONTAINER = CONTAINER_TYPES.register((INamedEntry)PEItems.GEM_OF_ETERNAL_DENSITY, EternalDensityContainer::fromNetwork);
/* 44 */   public static final ContainerTypeRegistryObject<TransmutationContainer> TRANSMUTATION_CONTAINER = CONTAINER_TYPES.register((INamedEntry)PEBlocks.TRANSMUTATION_TABLE, TransmutationContainer::fromNetwork);
/* 45 */   public static final ContainerTypeRegistryObject<RelayMK1Container> RELAY_MK1_CONTAINER = CONTAINER_TYPES.register((INamedEntry)PEBlocks.RELAY, RelayMK1BlockEntity.class, RelayMK1Container::new);
/* 46 */   public static final ContainerTypeRegistryObject<RelayMK2Container> RELAY_MK2_CONTAINER = CONTAINER_TYPES.register((INamedEntry)PEBlocks.RELAY_MK2, RelayMK2BlockEntity.class, RelayMK2Container::new);
/* 47 */   public static final ContainerTypeRegistryObject<RelayMK3Container> RELAY_MK3_CONTAINER = CONTAINER_TYPES.register((INamedEntry)PEBlocks.RELAY_MK3, RelayMK3BlockEntity.class, RelayMK3Container::new);
/* 48 */   public static final ContainerTypeRegistryObject<CollectorMK1Container> COLLECTOR_MK1_CONTAINER = CONTAINER_TYPES.register((INamedEntry)PEBlocks.COLLECTOR, CollectorMK1BlockEntity.class, CollectorMK1Container::new);
/* 49 */   public static final ContainerTypeRegistryObject<CollectorMK2Container> COLLECTOR_MK2_CONTAINER = CONTAINER_TYPES.register((INamedEntry)PEBlocks.COLLECTOR_MK2, CollectorMK2BlockEntity.class, CollectorMK2Container::new);
/* 50 */   public static final ContainerTypeRegistryObject<CollectorMK3Container> COLLECTOR_MK3_CONTAINER = CONTAINER_TYPES.register((INamedEntry)PEBlocks.COLLECTOR_MK3, CollectorMK3BlockEntity.class, CollectorMK3Container::new);
/* 51 */   public static final ContainerTypeRegistryObject<MercurialEyeContainer> MERCURIAL_EYE_CONTAINER = CONTAINER_TYPES.register((INamedEntry)PEItems.MERCURIAL_EYE, MercurialEyeContainer::fromNetwork);
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registries\PEContainerTypes.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */