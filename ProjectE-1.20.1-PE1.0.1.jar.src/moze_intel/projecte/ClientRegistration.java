/*     */ package moze_intel.projecte;
/*     */ 
/*     */ import java.util.Objects;
/*     */ import moze_intel.projecte.gameObjs.blocks.ProjectETNT;
/*     */ import moze_intel.projecte.gameObjs.gui.PEContainerScreen;
/*     */ import moze_intel.projecte.gameObjs.registration.impl.BlockRegistryObject;
/*     */ import moze_intel.projecte.gameObjs.registration.impl.ContainerTypeRegistryObject;
/*     */ import moze_intel.projecte.gameObjs.registries.PEBlockEntityTypes;
/*     */ import moze_intel.projecte.gameObjs.registries.PEBlocks;
/*     */ import moze_intel.projecte.gameObjs.registries.PEContainerTypes;
/*     */ import moze_intel.projecte.gameObjs.registries.PEEntityTypes;
/*     */ import moze_intel.projecte.gameObjs.registries.PEItems;
/*     */ import moze_intel.projecte.rendering.ChestRenderer;
/*     */ import moze_intel.projecte.rendering.EntitySpriteRenderer;
/*     */ import moze_intel.projecte.rendering.LayerYue;
/*     */ import moze_intel.projecte.rendering.NovaRenderer;
/*     */ import moze_intel.projecte.rendering.TransmutationRenderingOverlay;
/*     */ import moze_intel.projecte.utils.ClientKeyHelper;
/*     */ import moze_intel.projecte.utils.ItemHelper;
/*     */ import net.minecraft.client.gui.screens.MenuScreens;
/*     */ import net.minecraft.client.gui.screens.Screen;
/*     */ import net.minecraft.client.multiplayer.ClientLevel;
/*     */ import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
/*     */ import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*     */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*     */ import net.minecraft.client.renderer.entity.player.PlayerRenderer;
/*     */ import net.minecraft.client.renderer.item.ItemProperties;
/*     */ import net.minecraft.client.renderer.item.ItemPropertyFunction;
/*     */ import net.minecraft.core.registries.Registries;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.inventory.MenuType;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraft.world.level.block.entity.BlockEntityType;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.client.event.EntityRenderersEvent;
/*     */ import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
/*     */ import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
/*     */ import net.minecraftforge.client.event.ScreenEvent;
/*     */ import net.minecraftforge.client.gui.overlay.IGuiOverlay;
/*     */ import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.EventPriority;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.ModList;
/*     */ import net.minecraftforge.fml.common.Mod;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
/*     */ import net.minecraftforge.registries.RegisterEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "projecte", value = {Dist.CLIENT}, bus = Mod.EventBusSubscriber.Bus.MOD)
/*     */ public class ClientRegistration
/*     */ {
/*  61 */   public static final ResourceLocation ACTIVE_OVERRIDE = PECore.rl("active");
/*  62 */   public static final ResourceLocation MODE_OVERRIDE = PECore.rl("mode");
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void registerContainers(RegisterEvent event) {
/*  66 */     event.register(Registries.f_256798_, helper -> {
/*     */           registerScreen(PEContainerTypes.RM_FURNACE_CONTAINER, moze_intel.projecte.gameObjs.gui.GUIRMFurnace::new);
/*     */           registerScreen(PEContainerTypes.DM_FURNACE_CONTAINER, moze_intel.projecte.gameObjs.gui.GUIDMFurnace::new);
/*     */           registerScreen(PEContainerTypes.CONDENSER_CONTAINER, moze_intel.projecte.gameObjs.gui.AbstractCondenserScreen.MK1::new);
/*     */           registerScreen(PEContainerTypes.CONDENSER_MK2_CONTAINER, moze_intel.projecte.gameObjs.gui.AbstractCondenserScreen.MK2::new);
/*     */           registerScreen(PEContainerTypes.ALCH_CHEST_CONTAINER, moze_intel.projecte.gameObjs.gui.AlchChestScreen::new);
/*     */           registerScreen(PEContainerTypes.ALCH_BAG_CONTAINER, moze_intel.projecte.gameObjs.gui.AlchBagScreen::new);
/*     */           registerScreen(PEContainerTypes.ETERNAL_DENSITY_CONTAINER, moze_intel.projecte.gameObjs.gui.GUIEternalDensity::new);
/*     */           registerScreen(PEContainerTypes.TRANSMUTATION_CONTAINER, moze_intel.projecte.gameObjs.gui.GUITransmutation::new);
/*     */           registerScreen(PEContainerTypes.RELAY_MK1_CONTAINER, moze_intel.projecte.gameObjs.gui.GUIRelay.GUIRelayMK1::new);
/*     */           registerScreen(PEContainerTypes.RELAY_MK2_CONTAINER, moze_intel.projecte.gameObjs.gui.GUIRelay.GUIRelayMK2::new);
/*     */           registerScreen(PEContainerTypes.RELAY_MK3_CONTAINER, moze_intel.projecte.gameObjs.gui.GUIRelay.GUIRelayMK3::new);
/*     */           registerScreen(PEContainerTypes.COLLECTOR_MK1_CONTAINER, moze_intel.projecte.gameObjs.gui.AbstractCollectorScreen.MK1::new);
/*     */           registerScreen(PEContainerTypes.COLLECTOR_MK2_CONTAINER, moze_intel.projecte.gameObjs.gui.AbstractCollectorScreen.MK2::new);
/*     */           registerScreen(PEContainerTypes.COLLECTOR_MK3_CONTAINER, moze_intel.projecte.gameObjs.gui.AbstractCollectorScreen.MK3::new);
/*     */           registerScreen(PEContainerTypes.MERCURIAL_EYE_CONTAINER, moze_intel.projecte.gameObjs.gui.GUIMercurialEye::new);
/*     */         });
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void clientSetup(FMLClientSetupEvent evt) {
/*  87 */     if (ModList.get().isLoaded("jei"))
/*     */     {
/*  89 */       MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, event -> {
/*     */             Screen patt5095$temp = event.getCurrentScreen();
/*     */             
/*     */             if (patt5095$temp instanceof PEContainerScreen) {
/*     */               PEContainerScreen<?> screen = (PEContainerScreen)patt5095$temp;
/*     */               
/*     */               if (event.getNewScreen() instanceof mezz.jei.api.runtime.IRecipesGui) {
/*     */                 screen.switchingToJEI = true;
/*     */               }
/*     */             } 
/*     */           });
/*     */     }
/* 101 */     evt.enqueueWork(() -> {
/*     */           addPropertyOverrides(ACTIVE_OVERRIDE, (), new ItemLike[] { 
/*     */                 (ItemLike)PEItems.GEM_OF_ETERNAL_DENSITY, (ItemLike)PEItems.VOID_RING, (ItemLike)PEItems.ARCANA_RING, (ItemLike)PEItems.ARCHANGEL_SMITE, (ItemLike)PEItems.BLACK_HOLE_BAND, (ItemLike)PEItems.BODY_STONE, (ItemLike)PEItems.HARVEST_GODDESS_BAND, (ItemLike)PEItems.IGNITION_RING, (ItemLike)PEItems.LIFE_STONE, (ItemLike)PEItems.MIND_STONE, 
/*     */                 (ItemLike)PEItems.SOUL_STONE, (ItemLike)PEItems.WATCH_OF_FLOWING_TIME, (ItemLike)PEItems.ZERO_RING });
/*     */           addPropertyOverrides(MODE_OVERRIDE, (), new ItemLike[] { (ItemLike)PEItems.ARCANA_RING, (ItemLike)PEItems.SWIFTWOLF_RENDING_GALE });
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void registerKeybindings(RegisterKeyMappingsEvent event) {
/* 114 */     ClientKeyHelper.registerKeyBindings(event);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void registerOverlays(RegisterGuiOverlaysEvent event) {
/* 119 */     event.registerAbove(VanillaGuiOverlay.CROSSHAIR.id(), "transmutation_result", (IGuiOverlay)new TransmutationRenderingOverlay());
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
/* 125 */     event.registerBlockEntityRenderer((BlockEntityType)PEBlockEntityTypes.ALCHEMICAL_CHEST.get(), context -> new ChestRenderer(context, PECore.rl("textures/block/alchemical_chest.png"), ()));
/* 126 */     event.registerBlockEntityRenderer((BlockEntityType)PEBlockEntityTypes.CONDENSER.get(), context -> new ChestRenderer(context, PECore.rl("textures/block/condenser_mk1.png"), ()));
/* 127 */     event.registerBlockEntityRenderer((BlockEntityType)PEBlockEntityTypes.CONDENSER_MK2.get(), context -> new ChestRenderer(context, PECore.rl("textures/block/condenser_mk2.png"), ()));
/* 128 */     event.registerBlockEntityRenderer((BlockEntityType)PEBlockEntityTypes.DARK_MATTER_PEDESTAL.get(), moze_intel.projecte.rendering.PedestalRenderer::new);
/*     */ 
/*     */     
/* 131 */     event.registerEntityRenderer((EntityType)PEEntityTypes.WATER_PROJECTILE.get(), context -> new EntitySpriteRenderer(context, PECore.rl("textures/entity/water_orb.png")));
/* 132 */     event.registerEntityRenderer((EntityType)PEEntityTypes.LAVA_PROJECTILE.get(), context -> new EntitySpriteRenderer(context, PECore.rl("textures/entity/lava_orb.png")));
/* 133 */     event.registerEntityRenderer((EntityType)PEEntityTypes.MOB_RANDOMIZER.get(), context -> new EntitySpriteRenderer(context, PECore.rl("textures/entity/randomizer.png")));
/* 134 */     event.registerEntityRenderer((EntityType)PEEntityTypes.LENS_PROJECTILE.get(), context -> new EntitySpriteRenderer(context, PECore.rl("textures/entity/lens_explosive.png")));
/* 135 */     event.registerEntityRenderer((EntityType)PEEntityTypes.FIRE_PROJECTILE.get(), context -> new EntitySpriteRenderer(context, PECore.rl("textures/entity/fireball.png")));
/* 136 */     event.registerEntityRenderer((EntityType)PEEntityTypes.SWRG_PROJECTILE.get(), context -> new EntitySpriteRenderer(context, PECore.rl("textures/entity/lightning.png")));
/* 137 */     event.registerEntityRenderer((EntityType)PEEntityTypes.NOVA_CATALYST_PRIMED.get(), context -> { Objects.requireNonNull((ProjectETNT)PEBlocks.NOVA_CATALYST.getBlock()); return (EntityRenderer)new NovaRenderer(context, (ProjectETNT)PEBlocks.NOVA_CATALYST.getBlock()::m_49966_);
/* 138 */         }); event.registerEntityRenderer((EntityType)PEEntityTypes.NOVA_CATACLYSM_PRIMED.get(), context -> { Objects.requireNonNull((ProjectETNT)PEBlocks.NOVA_CATACLYSM.getBlock()); return (EntityRenderer)new NovaRenderer(context, (ProjectETNT)PEBlocks.NOVA_CATACLYSM.getBlock()::m_49966_);
/* 139 */         }); event.registerEntityRenderer((EntityType)PEEntityTypes.HOMING_ARROW.get(), net.minecraft.client.renderer.entity.TippableArrowRenderer::new);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void addLayers(EntityRenderersEvent.AddLayers event) {
/* 144 */     for (String skinName : event.getSkins()) {
/* 145 */       PlayerRenderer skin = (PlayerRenderer)event.getSkin(skinName);
/* 146 */       if (skin != null) {
/* 147 */         skin.m_115326_((RenderLayer)new LayerYue(skin));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void addPropertyOverrides(ResourceLocation override, ItemPropertyFunction propertyGetter, ItemLike... itemProviders) {
/* 153 */     for (ItemLike itemProvider : itemProviders) {
/* 154 */       ItemProperties.register(itemProvider.m_5456_(), override, propertyGetter);
/*     */     }
/*     */   }
/*     */   
/*     */   private static <C extends net.minecraft.world.inventory.AbstractContainerMenu, U extends Screen & net.minecraft.client.gui.screens.inventory.MenuAccess<C>> void registerScreen(ContainerTypeRegistryObject<C> type, MenuScreens.ScreenConstructor<C, U> factory) {
/* 159 */     MenuScreens.m_96206_((MenuType)type.get(), factory);
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\ClientRegistration.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */