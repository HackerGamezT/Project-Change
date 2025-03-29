/*     */ package moze_intel.projecte;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.brigadier.builder.ArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.logging.LogUtils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import moze_intel.projecte.api.capabilities.IAlchBagProvider;
/*     */ import moze_intel.projecte.api.capabilities.block_entity.IEmcStorage;
/*     */ import moze_intel.projecte.api.capabilities.item.IAlchBagItem;
/*     */ import moze_intel.projecte.api.capabilities.item.IAlchChestItem;
/*     */ import moze_intel.projecte.api.capabilities.item.IExtraFunction;
/*     */ import moze_intel.projecte.api.capabilities.item.IItemCharge;
/*     */ import moze_intel.projecte.api.capabilities.item.IItemEmcHolder;
/*     */ import moze_intel.projecte.api.capabilities.item.IModeChanger;
/*     */ import moze_intel.projecte.api.capabilities.item.IPedestalItem;
/*     */ import moze_intel.projecte.api.capabilities.item.IProjectileShooter;
/*     */ import moze_intel.projecte.api.nss.AbstractNSSTag;
/*     */ import moze_intel.projecte.config.CustomEMCParser;
/*     */ import moze_intel.projecte.config.PEModConfig;
/*     */ import moze_intel.projecte.config.ProjectEConfig;
/*     */ import moze_intel.projecte.emc.EMCMappingHandler;
/*     */ import moze_intel.projecte.emc.json.NSSSerializer;
/*     */ import moze_intel.projecte.emc.mappers.recipe.CraftingMapper;
/*     */ import moze_intel.projecte.emc.nbt.NBTManager;
/*     */ import moze_intel.projecte.gameObjs.PETags;
/*     */ import moze_intel.projecte.gameObjs.blocks.ProjectETNT;
/*     */ import moze_intel.projecte.gameObjs.customRecipes.FullKleinStarsCondition;
/*     */ import moze_intel.projecte.gameObjs.customRecipes.TomeEnabledCondition;
/*     */ import moze_intel.projecte.gameObjs.items.rings.Arcana;
/*     */ import moze_intel.projecte.gameObjs.registries.PEArgumentTypes;
/*     */ import moze_intel.projecte.gameObjs.registries.PEBlockEntityTypes;
/*     */ import moze_intel.projecte.gameObjs.registries.PEBlocks;
/*     */ import moze_intel.projecte.gameObjs.registries.PEContainerTypes;
/*     */ import moze_intel.projecte.gameObjs.registries.PECreativeTabs;
/*     */ import moze_intel.projecte.gameObjs.registries.PEEntityTypes;
/*     */ import moze_intel.projecte.gameObjs.registries.PEItems;
/*     */ import moze_intel.projecte.gameObjs.registries.PERecipeSerializers;
/*     */ import moze_intel.projecte.gameObjs.registries.PESoundEvents;
/*     */ import moze_intel.projecte.handlers.InternalAbilities;
/*     */ import moze_intel.projecte.handlers.InternalTimers;
/*     */ import moze_intel.projecte.impl.IMCHandler;
/*     */ import moze_intel.projecte.impl.TransmutationOffline;
/*     */ import moze_intel.projecte.network.PacketHandler;
/*     */ import moze_intel.projecte.network.ThreadCheckUUID;
/*     */ import moze_intel.projecte.network.ThreadCheckUpdate;
/*     */ import moze_intel.projecte.network.commands.EMCCMD;
/*     */ import moze_intel.projecte.network.commands.KnowledgeCMD;
/*     */ import moze_intel.projecte.network.commands.RemoveEmcCMD;
/*     */ import moze_intel.projecte.network.commands.ResetEmcCMD;
/*     */ import moze_intel.projecte.network.commands.SetEmcCMD;
/*     */ import moze_intel.projecte.network.commands.ShowBagCMD;
/*     */ import moze_intel.projecte.utils.WorldHelper;
/*     */ import net.minecraft.commands.CommandBuildContext;
/*     */ import net.minecraft.commands.Commands;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.BlockSource;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.core.RegistryAccess;
/*     */ import net.minecraft.core.cauldron.CauldronInteraction;
/*     */ import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
/*     */ import net.minecraft.core.dispenser.DispenseItemBehavior;
/*     */ import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
/*     */ import net.minecraft.core.dispenser.ShearsDispenseItemBehavior;
/*     */ import net.minecraft.core.registries.Registries;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.server.ReloadableServerResources;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.server.packs.resources.PreparableReloadListener;
/*     */ import net.minecraft.server.packs.resources.ResourceManager;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.BaseFireBlock;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.CampfireBlock;
/*     */ import net.minecraft.world.level.block.DispenserBlock;
/*     */ import net.minecraft.world.level.block.LayeredCauldronBlock;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.block.state.properties.BlockStateProperties;
/*     */ import net.minecraft.world.level.block.state.properties.Property;
/*     */ import net.minecraft.world.level.material.Fluid;
/*     */ import net.minecraft.world.level.material.Fluids;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.common.capabilities.ForgeCapabilities;
/*     */ import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
/*     */ import net.minecraftforge.common.crafting.CraftingHelper;
/*     */ import net.minecraftforge.event.AddReloadListenerEvent;
/*     */ import net.minecraftforge.event.RegisterCommandsEvent;
/*     */ import net.minecraftforge.event.TagsUpdatedEvent;
/*     */ import net.minecraftforge.event.server.ServerStartingEvent;
/*     */ import net.minecraftforge.event.server.ServerStoppedEvent;
/*     */ import net.minecraftforge.eventbus.api.IEventBus;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.capability.IFluidHandler;
/*     */ import net.minecraftforge.fml.ModContainer;
/*     */ import net.minecraftforge.fml.ModLoadingContext;
/*     */ import net.minecraftforge.fml.common.Mod;
/*     */ import net.minecraftforge.fml.config.ModConfig;
/*     */ import net.minecraftforge.fml.event.config.ModConfigEvent;
/*     */ import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
/*     */ import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
/*     */ import net.minecraftforge.fml.loading.FMLEnvironment;
/*     */ import net.minecraftforge.registries.RegisterEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ import org.slf4j.Logger;
/*     */ 
/*     */ @Mod("projecte")
/*     */ @EventBusSubscriber(modid = "projecte")
/*     */ public class PECore {
/*     */   public static final String MODID = "projecte";
/*     */   public static final String MODNAME = "ProjectE";
/* 124 */   public static final GameProfile FAKEPLAYER_GAMEPROFILE = new GameProfile(UUID.fromString("590e39c7-9fb6-471b-a4c2-c0e539b2423d"), "[ProjectE]");
/* 125 */   public static final Logger LOGGER = LogUtils.getLogger();
/*     */   
/* 127 */   public static final List<String> uuids = new ArrayList<>(); public static ModContainer MOD_CONTAINER;
/*     */   @Nullable
/*     */   private EmcUpdateData emcUpdateResourceManager;
/*     */   
/*     */   public static void debugLog(String msg, Object... args) {
/* 132 */     if (!FMLEnvironment.production || ProjectEConfig.common.debugLogging.get()) {
/* 133 */       LOGGER.info(msg, args);
/*     */     } else {
/* 135 */       LOGGER.debug(msg, args);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static ResourceLocation rl(String path) {
/* 140 */     return new ResourceLocation("projecte", path);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PECore() {
/* 147 */     MOD_CONTAINER = ModLoadingContext.get().getActiveContainer();
/*     */     
/* 149 */     IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
/* 150 */     modEventBus.addListener(this::commonSetup);
/* 151 */     modEventBus.addListener(this::imcQueue);
/* 152 */     modEventBus.addListener(IMCHandler::handleMessages);
/* 153 */     modEventBus.addListener(this::onConfigLoad);
/* 154 */     modEventBus.addListener(this::registerCapabilities);
/* 155 */     modEventBus.addListener(this::registerRecipeSerializers);
/* 156 */     PEArgumentTypes.ARGUMENT_TYPES.register(modEventBus);
/* 157 */     PEBlockEntityTypes.BLOCK_ENTITY_TYPES.register(modEventBus);
/* 158 */     PEBlocks.BLOCKS.register(modEventBus);
/* 159 */     PEContainerTypes.CONTAINER_TYPES.register(modEventBus);
/* 160 */     PECreativeTabs.CREATIVE_TABS.register(modEventBus);
/* 161 */     PEEntityTypes.ENTITY_TYPES.register(modEventBus);
/* 162 */     PEItems.ITEMS.register(modEventBus);
/* 163 */     PERecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);
/* 164 */     PESoundEvents.SOUND_EVENTS.register(modEventBus);
/* 165 */     MinecraftForge.EVENT_BUS.addListener(this::addReloadListeners);
/* 166 */     MinecraftForge.EVENT_BUS.addListener(this::tagsUpdated);
/* 167 */     MinecraftForge.EVENT_BUS.addListener(this::registerCommands);
/* 168 */     MinecraftForge.EVENT_BUS.addListener(this::serverStarting);
/* 169 */     MinecraftForge.EVENT_BUS.addListener(this::serverQuit);
/* 170 */     MinecraftForge.EVENT_BUS.addListener(PEPermissions::registerPermissionNodes);
/*     */ 
/*     */     
/* 173 */     ProjectEConfig.register();
/*     */   }
/*     */   
/*     */   private void registerRecipeSerializers(RegisterEvent event) {
/* 177 */     event.register(Registries.f_256764_, helper -> {
/*     */           CraftingHelper.register(TomeEnabledCondition.SERIALIZER);
/*     */           CraftingHelper.register(FullKleinStarsCondition.SERIALIZER);
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   private void registerCapabilities(RegisterCapabilitiesEvent event) {
/* 185 */     event.register(IAlchBagProvider.class);
/* 186 */     event.register(IKnowledgeProvider.class);
/* 187 */     event.register(InternalTimers.class);
/* 188 */     event.register(InternalAbilities.class);
/* 189 */     event.register(CommonInternalAbilities.class);
/* 190 */     event.register(IAlchBagItem.class);
/* 191 */     event.register(IAlchChestItem.class);
/* 192 */     event.register(IExtraFunction.class);
/* 193 */     event.register(IItemCharge.class);
/* 194 */     event.register(IItemEmcHolder.class);
/* 195 */     event.register(IModeChanger.class);
/* 196 */     event.register(IPedestalItem.class);
/* 197 */     event.register(IProjectileShooter.class);
/* 198 */     event.register(IEmcStorage.class);
/*     */   }
/*     */   
/*     */   private void commonSetup(FMLCommonSetupEvent event) {
/* 202 */     (new ThreadCheckUpdate()).start();
/*     */     
/* 204 */     EMCMappingHandler.loadMappers();
/* 205 */     CraftingMapper.loadMappers();
/* 206 */     NBTManager.loadProcessors();
/*     */     
/* 208 */     event.enqueueWork(() -> {
/*     */           PETags.init();
/*     */           
/*     */           PacketHandler.register();
/*     */           
/*     */           registerDispenseBehavior((DispenseItemBehavior)new ShearsDispenseItemBehavior(), new ItemLike[] { (ItemLike)PEItems.DARK_MATTER_SHEARS, (ItemLike)PEItems.RED_MATTER_SHEARS, (ItemLike)PEItems.RED_MATTER_KATAR });
/*     */           DispenserBlock.m_52672_((ItemLike)PEBlocks.NOVA_CATALYST, ((ProjectETNT)PEBlocks.NOVA_CATALYST.getBlock()).createDispenseItemBehavior());
/*     */           DispenserBlock.m_52672_((ItemLike)PEBlocks.NOVA_CATACLYSM, ((ProjectETNT)PEBlocks.NOVA_CATACLYSM.getBlock()).createDispenseItemBehavior());
/*     */           registerDispenseBehavior((DispenseItemBehavior)new OptionalDispenseItemBehavior()
/*     */               {
/*     */                 @NotNull
/*     */                 protected ItemStack m_7498_(@NotNull BlockSource source, @NotNull ItemStack stack)
/*     */                 {
/* 221 */                   Item item = stack.m_41720_(); if (item instanceof Arcana) { Arcana arcana = (Arcana)item;
/* 222 */                     if (arcana.getMode(stack) != 1) {
/*     */                       
/* 224 */                       m_123573_(false);
/* 225 */                       return super.m_7498_(source, stack);
/*     */                     }  }
/*     */                   
/* 228 */                   ServerLevel serverLevel = source.m_7727_();
/* 229 */                   m_123573_(true);
/* 230 */                   Direction direction = (Direction)source.m_6414_().m_61143_((Property)DispenserBlock.f_52659_);
/* 231 */                   BlockPos pos = source.m_7961_().m_121945_(direction);
/* 232 */                   BlockState state = serverLevel.m_8055_(pos);
/* 233 */                   if (BaseFireBlock.m_49255_((Level)serverLevel, pos, direction)) {
/* 234 */                     serverLevel.m_46597_(pos, BaseFireBlock.m_49245_((BlockGetter)serverLevel, pos));
/* 235 */                   } else if (CampfireBlock.m_51321_(state)) {
/* 236 */                     serverLevel.m_46597_(pos, (BlockState)state.m_61124_((Property)BlockStateProperties.f_61443_, Boolean.valueOf(true)));
/* 237 */                   } else if (state.isFlammable((BlockGetter)serverLevel, pos, direction.m_122424_())) {
/* 238 */                     state.onCaughtFire((Level)serverLevel, pos, direction.m_122424_(), null);
/* 239 */                     if (state.m_60734_() instanceof net.minecraft.world.level.block.TntBlock) {
/* 240 */                       serverLevel.m_7471_(pos, false);
/*     */                     }
/*     */                   } else {
/* 243 */                     m_123573_(false);
/*     */                   } 
/* 245 */                   return stack;
/*     */                 }
/*     */               }new ItemLike[] { (ItemLike)PEItems.IGNITION_RING, (ItemLike)PEItems.ARCANA_RING });
/*     */           
/*     */           DispenserBlock.m_52672_((ItemLike)PEItems.EVERTIDE_AMULET, (DispenseItemBehavior)new DefaultDispenseItemBehavior()
/*     */               {
/*     */                 @NotNull
/*     */                 public ItemStack m_7498_(@NotNull BlockSource source, @NotNull ItemStack stack)
/*     */                 {
/* 254 */                   ServerLevel serverLevel = source.m_7727_();
/* 255 */                   Direction direction = (Direction)source.m_6414_().m_61143_((Property)DispenserBlock.f_52659_);
/* 256 */                   BlockPos pos = source.m_7961_().m_121945_(direction);
/* 257 */                   BlockEntity blockEntity = WorldHelper.getBlockEntity((BlockGetter)serverLevel, pos);
/* 258 */                   Direction sideHit = direction.m_122424_();
/* 259 */                   if (blockEntity != null) {
/* 260 */                     Optional<IFluidHandler> capability = blockEntity.getCapability(ForgeCapabilities.FLUID_HANDLER, sideHit).resolve();
/* 261 */                     if (capability.isPresent()) {
/* 262 */                       ((IFluidHandler)capability.get()).fill(new FluidStack((Fluid)Fluids.f_76193_, 1000), IFluidHandler.FluidAction.EXECUTE);
/* 263 */                       return stack;
/*     */                     } 
/*     */                   } 
/* 266 */                   BlockState state = serverLevel.m_8055_(pos);
/* 267 */                   if (state.m_60734_() == Blocks.f_50256_) {
/* 268 */                     serverLevel.m_46597_(pos, (BlockState)Blocks.f_152476_.m_49966_().m_61124_((Property)LayeredCauldronBlock.f_153514_, Integer.valueOf(1)));
/* 269 */                     return stack;
/* 270 */                   }  if (state.m_60734_() == Blocks.f_152476_) {
/* 271 */                     if (!((LayeredCauldronBlock)state.m_60734_()).m_142596_(state)) {
/* 272 */                       serverLevel.m_46597_(pos, (BlockState)state.m_61124_((Property)LayeredCauldronBlock.f_153514_, Integer.valueOf(((Integer)state.m_61143_((Property)LayeredCauldronBlock.f_153514_)).intValue() + 1)));
/* 273 */                       return stack;
/*     */                     } 
/*     */                   } else {
/* 276 */                     WorldHelper.placeFluid(null, (Level)serverLevel, pos, Fluids.f_76193_, !ProjectEConfig.server.items.opEvertide.get());
/* 277 */                     serverLevel.m_6263_(null, pos.m_123341_(), pos.m_123342_(), pos.m_123343_(), (SoundEvent)PESoundEvents.WATER_MAGIC.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
/* 278 */                     return stack;
/*     */                   } 
/* 280 */                   return super.m_7498_(source, stack);
/*     */                 }
/*     */               });
/*     */           CauldronInteraction.f_175606_.put((Item)PEItems.EVERTIDE_AMULET.get(), ());
/*     */           CauldronInteraction.f_175607_.put((Item)PEItems.EVERTIDE_AMULET.get(), ());
/*     */           CauldronInteraction.f_175606_.put((Item)PEItems.VOLCANITE_AMULET.get(), ());
/*     */         });
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
/*     */   private static void registerDispenseBehavior(DispenseItemBehavior behavior, ItemLike... items) {
/* 309 */     for (ItemLike item : items) {
/* 310 */       DispenserBlock.m_52672_(item, behavior);
/*     */     }
/*     */   }
/*     */   
/*     */   private void imcQueue(InterModEnqueueEvent event) {
/* 315 */     WorldTransmutations.init();
/* 316 */     NSSSerializer.init();
/* 317 */     IntegrationHelper.sendIMCMessages(event);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void onConfigLoad(ModConfigEvent configEvent) {
/* 323 */     ModConfig config = configEvent.getConfig();
/*     */     
/* 325 */     if (config.getModId().equals("projecte") && config instanceof PEModConfig) { PEModConfig peConfig = (PEModConfig)config;
/* 326 */       peConfig.clearCache(configEvent); }
/*     */   
/*     */   }
/*     */   
/*     */   private void tagsUpdated(TagsUpdatedEvent event) {
/* 331 */     if (this.emcUpdateResourceManager != null) {
/* 332 */       long start = System.currentTimeMillis();
/*     */       
/* 334 */       AbstractNSSTag.clearCreatedTags();
/* 335 */       CustomEMCParser.init();
/*     */       try {
/* 337 */         EMCMappingHandler.map(this.emcUpdateResourceManager.serverResources(), this.emcUpdateResourceManager.registryAccess(), this.emcUpdateResourceManager.resourceManager());
/* 338 */         LOGGER.info("Registered {} EMC values. (took {} ms)", Integer.valueOf(EMCMappingHandler.getEmcMapSize()), Long.valueOf(System.currentTimeMillis() - start));
/* 339 */         PacketHandler.sendFragmentedEmcPacketToAll();
/* 340 */       } catch (Throwable t) {
/* 341 */         LOGGER.error("Error calculating EMC values", t);
/*     */       } 
/* 343 */       this.emcUpdateResourceManager = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addReloadListeners(AddReloadListenerEvent event) {
/* 348 */     event.addListener((PreparableReloadListener)(manager -> this.emcUpdateResourceManager = new EmcUpdateData(event.getServerResources(), event.getRegistryAccess(), manager)));
/*     */   }
/*     */   
/*     */   private void registerCommands(RegisterCommandsEvent event) {
/* 352 */     CommandBuildContext context = event.getBuildContext();
/* 353 */     event.getDispatcher().register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_("projecte")
/* 354 */         .requires(PEPermissions.COMMAND))
/* 355 */         .then((ArgumentBuilder)RemoveEmcCMD.register(context)))
/* 356 */         .then((ArgumentBuilder)ResetEmcCMD.register(context)))
/* 357 */         .then((ArgumentBuilder)SetEmcCMD.register(context)))
/* 358 */         .then((ArgumentBuilder)ShowBagCMD.register(context)))
/* 359 */         .then(EMCCMD.register(context)))
/* 360 */         .then(KnowledgeCMD.register(context)));
/*     */   }
/*     */ 
/*     */   
/*     */   private void serverStarting(ServerStartingEvent event) {
/* 365 */     if (!ThreadCheckUUID.hasRunServer()) {
/* 366 */       (new ThreadCheckUUID(true)).start();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void serverQuit(ServerStoppedEvent event) {
/* 372 */     CustomEMCParser.flush();
/* 373 */     TransmutationOffline.cleanAll();
/* 374 */     EMCMappingHandler.clearEmcMap();
/*     */   }
/*     */   private static final class EmcUpdateData extends Record { private final ReloadableServerResources serverResources; private final RegistryAccess registryAccess; private final ResourceManager resourceManager;
/* 377 */     private EmcUpdateData(ReloadableServerResources serverResources, RegistryAccess registryAccess, ResourceManager resourceManager) { this.serverResources = serverResources; this.registryAccess = registryAccess; this.resourceManager = resourceManager; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lmoze_intel/projecte/PECore$EmcUpdateData;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #377	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 377 */       //   0	7	0	this	Lmoze_intel/projecte/PECore$EmcUpdateData; } public ReloadableServerResources serverResources() { return this.serverResources; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/PECore$EmcUpdateData;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #377	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lmoze_intel/projecte/PECore$EmcUpdateData; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lmoze_intel/projecte/PECore$EmcUpdateData;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #377	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lmoze_intel/projecte/PECore$EmcUpdateData;
/* 377 */       //   0	8	1	o	Ljava/lang/Object; } public RegistryAccess registryAccess() { return this.registryAccess; } public ResourceManager resourceManager() { return this.resourceManager; }
/*     */      }
/*     */ 
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\PECore.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */