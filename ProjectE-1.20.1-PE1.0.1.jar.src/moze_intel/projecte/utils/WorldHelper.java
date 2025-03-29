/*     */ package moze_intel.projecte.utils;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Predicate;
/*     */ import moze_intel.projecte.PECore;
/*     */ import moze_intel.projecte.config.ProjectEConfig;
/*     */ import moze_intel.projecte.gameObjs.PETags;
/*     */ import moze_intel.projecte.gameObjs.registries.PESoundEvents;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.core.Holder;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.core.particles.ParticleTypes;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.tags.BlockTags;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraft.tags.TagKey;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.Container;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.WorldlyContainer;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.item.ItemEntity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.entity.projectile.Projectile;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.context.UseOnContext;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.Explosion;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.LevelAccessor;
/*     */ import net.minecraft.world.level.LevelReader;
/*     */ import net.minecraft.world.level.biome.Biome;
/*     */ import net.minecraft.world.level.biome.Biomes;
/*     */ import net.minecraft.world.level.block.BaseCoralWallFanBlock;
/*     */ import net.minecraft.world.level.block.BaseFireBlock;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.BonemealableBlock;
/*     */ import net.minecraft.world.level.block.BucketPickup;
/*     */ import net.minecraft.world.level.block.CampfireBlock;
/*     */ import net.minecraft.world.level.block.LiquidBlockContainer;
/*     */ import net.minecraft.world.level.block.NetherWartBlock;
/*     */ import net.minecraft.world.level.block.SnowLayerBlock;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.block.state.properties.BlockStateProperties;
/*     */ import net.minecraft.world.level.block.state.properties.Property;
/*     */ import net.minecraft.world.level.gameevent.GameEvent;
/*     */ import net.minecraft.world.level.material.FlowingFluid;
/*     */ import net.minecraft.world.level.material.Fluid;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.common.capabilities.ForgeCapabilities;
/*     */ import net.minecraftforge.event.level.ExplosionEvent;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.fluids.IFluidBlock;
/*     */ import net.minecraftforge.fluids.capability.IFluidHandler;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import net.minecraftforge.items.wrapper.InvWrapper;
/*     */ import net.minecraftforge.items.wrapper.SidedInvWrapper;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class WorldHelper
/*     */ {
/*     */   private static final Predicate<Entity> SWRG_REPEL_PREDICATE;
/*     */   private static final Predicate<Entity> INTERDICTION_REPEL_PREDICATE;
/*     */   private static final Predicate<Entity> INTERDICTION_REPEL_HOSTILE_PREDICATE;
/*     */   
/*     */   static {
/*  89 */     SWRG_REPEL_PREDICATE = (entity -> validRepelEntity(entity, PETags.Entities.BLACKLIST_SWRG));
/*  90 */     INTERDICTION_REPEL_PREDICATE = (entity -> validRepelEntity(entity, PETags.Entities.BLACKLIST_INTERDICTION));
/*     */     
/*  92 */     INTERDICTION_REPEL_HOSTILE_PREDICATE = (entity -> (validRepelEntity(entity, PETags.Entities.BLACKLIST_INTERDICTION) && (entity instanceof net.minecraft.world.entity.monster.Enemy || entity instanceof Projectile)));
/*     */   }
/*     */   
/*     */   public static void createLootDrop(List<ItemStack> drops, Level level, BlockPos pos) {
/*  96 */     createLootDrop(drops, level, pos.m_123341_(), pos.m_123342_(), pos.m_123343_());
/*     */   }
/*     */   
/*     */   public static void createLootDrop(List<ItemStack> drops, Level level, double x, double y, double z) {
/* 100 */     if (!drops.isEmpty()) {
/* 101 */       ItemHelper.compactItemListNoStacksize(drops);
/* 102 */       for (ItemStack drop : drops) {
/* 103 */         level.m_7967_((Entity)new ItemEntity(level, x, y, z, drop));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void createNovaExplosion(Level level, Entity exploder, double x, double y, double z, float power) {
/* 112 */     NovaExplosion explosion = new NovaExplosion(level, exploder, x, y, z, power, true, Explosion.BlockInteraction.DESTROY);
/* 113 */     if (!MinecraftForge.EVENT_BUS.post((Event)new ExplosionEvent.Start(level, explosion))) {
/* 114 */       explosion.m_46061_();
/* 115 */       explosion.m_46075_(true);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void drainFluid(Level level, BlockPos pos, BlockState state, Fluid toMatch) {
/* 120 */     Block block = state.m_60734_();
/* 121 */     if (block instanceof IFluidBlock) { IFluidBlock fluidBlock = (IFluidBlock)block; if (fluidBlock.getFluid().m_6212_(toMatch))
/*     */       
/*     */       { 
/*     */         
/* 125 */         fluidBlock.drain(level, pos, IFluidHandler.FluidAction.EXECUTE); return; }  }
/* 126 */      if (block instanceof BucketPickup) { BucketPickup bucketPickup = (BucketPickup)block;
/*     */ 
/*     */       
/* 129 */       bucketPickup.m_142598_((LevelAccessor)level, pos, state); }
/*     */   
/*     */   }
/*     */   
/*     */   public static void dropInventory(IItemHandler inv, Level level, BlockPos pos) {
/* 134 */     if (inv == null) {
/*     */       return;
/*     */     }
/* 137 */     for (int i = 0; i < inv.getSlots(); i++) {
/* 138 */       ItemStack stack = inv.getStackInSlot(i);
/* 139 */       if (!stack.m_41619_()) {
/* 140 */         level.m_7967_((Entity)new ItemEntity(level, pos.m_123341_(), pos.m_123342_(), pos.m_123343_(), stack));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void extinguishNearby(Level level, Player player) {
/* 146 */     BlockPos.m_121990_(player.m_20183_().m_7918_(-1, -1, -1), player.m_20183_().m_7918_(1, 1, 1)).forEach(pos -> {
/*     */           pos = pos.m_7949_();
/*     */           if (level.m_8055_(pos).m_60734_() == Blocks.f_50083_ && PlayerHelper.hasBreakPermission((ServerPlayer)player, pos)) {
/*     */             level.m_7471_(pos, false);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public static void freezeInBoundingBox(Level level, AABB box, Player player, boolean random) {
/* 155 */     for (BlockPos pos : getPositionsFromBox(box)) {
/* 156 */       BlockState state = level.m_8055_(pos);
/* 157 */       Block b = state.m_60734_();
/*     */       
/* 159 */       pos = pos.m_7949_();
/* 160 */       if (b == Blocks.f_49990_ && (!random || level.f_46441_.m_188503_(128) == 0)) {
/* 161 */         if (player != null) {
/* 162 */           PlayerHelper.checkedReplaceBlock((ServerPlayer)player, pos, Blocks.f_50126_.m_49966_()); continue;
/*     */         } 
/* 164 */         level.m_46597_(pos, Blocks.f_50126_.m_49966_()); continue;
/*     */       } 
/* 166 */       if (Block.m_49918_(state.m_60812_((BlockGetter)level, pos.m_7495_()), Direction.UP)) {
/* 167 */         BlockPos up = pos.m_7494_();
/* 168 */         BlockState stateUp = level.m_8055_(up);
/* 169 */         BlockState newState = null;
/*     */         
/* 171 */         if (stateUp.m_60795_() && (!random || level.f_46441_.m_188503_(128) == 0)) {
/* 172 */           newState = Blocks.f_50125_.m_49966_();
/* 173 */         } else if (stateUp.m_60734_() == Blocks.f_50125_ && ((Integer)stateUp.m_61143_((Property)SnowLayerBlock.f_56581_)).intValue() < 8 && level.f_46441_.m_188503_(512) == 0) {
/* 174 */           newState = (BlockState)stateUp.m_61124_((Property)SnowLayerBlock.f_56581_, Integer.valueOf(((Integer)stateUp.m_61143_((Property)SnowLayerBlock.f_56581_)).intValue() + 1));
/*     */         } 
/* 176 */         if (newState != null) {
/* 177 */           if (player != null) {
/* 178 */             PlayerHelper.checkedReplaceBlock((ServerPlayer)player, up, newState); continue;
/*     */           } 
/* 180 */           level.m_46597_(up, newState);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isLiquidContainerForFluid(BlockGetter level, BlockPos pos, BlockState state, Fluid fluid) {
/* 191 */     Block block = state.m_60734_(); if (block instanceof LiquidBlockContainer) { LiquidBlockContainer liquidBlockContainer = (LiquidBlockContainer)block; if (liquidBlockContainer.m_6044_(level, pos, state, fluid)); }  return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void placeFluid(@Nullable ServerPlayer player, Level level, BlockPos pos, Direction sideHit, FlowingFluid fluid, boolean checkWaterVaporize) {
/* 199 */     if (isLiquidContainerForFluid((BlockGetter)level, pos, level.m_8055_(pos), (Fluid)fluid)) {
/*     */       
/* 201 */       placeFluid(player, level, pos, fluid, checkWaterVaporize);
/*     */     } else {
/*     */       
/* 204 */       placeFluid(player, level, pos.m_121945_(sideHit), fluid, checkWaterVaporize);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void placeFluid(@Nullable ServerPlayer player, Level level, BlockPos pos, FlowingFluid fluid, boolean checkWaterVaporize) {
/* 214 */     BlockState blockState = level.m_8055_(pos);
/* 215 */     if (checkWaterVaporize && level.m_6042_().f_63857_() && fluid.m_205067_(FluidTags.f_13131_)) {
/* 216 */       level.m_5594_(null, pos, SoundEvents.f_11937_, SoundSource.PLAYERS, 0.5F, 2.6F + (level.f_46441_.m_188501_() - level.f_46441_.m_188501_()) * 0.8F);
/* 217 */       for (int l = 0; l < 8; l++) {
/* 218 */         level.m_7106_((ParticleOptions)ParticleTypes.f_123755_, pos.m_123341_() + Math.random(), pos.m_123342_() + Math.random(), pos.m_123343_() + Math.random(), 0.0D, 0.0D, 0.0D);
/*     */       }
/* 220 */     } else if (isLiquidContainerForFluid((BlockGetter)level, pos, blockState, (Fluid)fluid)) {
/* 221 */       ((LiquidBlockContainer)blockState.m_60734_()).m_7361_((LevelAccessor)level, pos, blockState, fluid.m_76068_(false));
/* 222 */       level.m_142346_((Entity)player, GameEvent.f_157769_, pos);
/*     */     } else {
/*     */       
/* 225 */       if (blockState.m_60722_((Fluid)fluid) && !blockState.m_278721_()) {
/* 226 */         level.m_46961_(pos, true);
/*     */       }
/* 228 */       if (player == null) {
/* 229 */         level.m_46597_(pos, fluid.m_76145_().m_76188_());
/* 230 */         level.m_142346_(null, GameEvent.f_157769_, pos);
/* 231 */       } else if (PlayerHelper.checkedPlaceBlock(player, pos, fluid.m_76145_().m_76188_())) {
/* 232 */         level.m_142346_((Entity)player, GameEvent.f_157769_, pos);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static IItemHandler getItemHandler(@NotNull BlockEntity blockEntity, @Nullable Direction direction) {
/* 243 */     Optional<IItemHandler> capability = blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, direction).resolve();
/* 244 */     if (capability.isPresent())
/* 245 */       return capability.get(); 
/* 246 */     if (blockEntity instanceof WorldlyContainer) { WorldlyContainer container = (WorldlyContainer)blockEntity;
/* 247 */       return (IItemHandler)new SidedInvWrapper(container, direction); }
/* 248 */      if (blockEntity instanceof Container) { Container container = (Container)blockEntity;
/* 249 */       return (IItemHandler)new InvWrapper(container); }
/*     */     
/* 251 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AABB getBroadDeepBox(BlockPos pos, Direction direction, int offset) {
/* 258 */     switch (direction) { default: throw new IncompatibleClassChangeError();case EAST: case WEST: case UP: case DOWN: case SOUTH: case NORTH: break; }  return 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 264 */       new AABB((pos.m_123341_() - offset), (pos.m_123342_() - offset), pos.m_123343_(), (pos.m_123341_() + offset), (pos.m_123342_() + offset), (pos.m_123343_() + offset));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AABB getDeepBox(BlockPos pos, Direction direction, int depth) {
/* 272 */     switch (direction) { default: throw new IncompatibleClassChangeError();case EAST: case WEST: case UP: case DOWN: case SOUTH: case NORTH: break; }  return 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 278 */       new AABB((pos.m_123341_() - 1), (pos.m_123342_() - 1), pos.m_123343_(), (pos.m_123341_() + 1), (pos.m_123342_() + 1), (pos.m_123343_() + depth));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AABB getBroadBox(BlockPos pos, Direction direction, int size) {
/* 286 */     switch (direction) { default: throw new IncompatibleClassChangeError();case EAST: case WEST: case UP: case DOWN: case SOUTH: case NORTH: break; }  return 
/*     */ 
/*     */       
/* 289 */       new AABB((pos.m_123341_() - size), (pos.m_123342_() - size), pos.m_123343_(), (pos.m_123341_() + size), (pos.m_123342_() + size), pos.m_123343_());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AABB getFlatYBox(BlockPos pos, int offset) {
/* 297 */     return new AABB((pos.m_123341_() - offset), pos.m_123342_(), (pos.m_123343_() - offset), (pos.m_123341_() + offset), pos.m_123342_(), (pos.m_123343_() + offset));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Iterable<BlockPos> getPositionsFromBox(AABB box) {
/* 304 */     return getPositionsFromBox(BlockPos.m_274561_(box.f_82288_, box.f_82289_, box.f_82290_), BlockPos.m_274561_(box.f_82291_, box.f_82292_, box.f_82293_));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Iterable<BlockPos> getPositionsFromBox(BlockPos corner1, BlockPos corner2) {
/* 311 */     return () -> BlockPos.m_121990_(corner1, corner2).iterator();
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<BlockEntity> getBlockEntitiesWithinAABB(Level level, AABB bBox) {
/* 316 */     List<BlockEntity> list = new ArrayList<>();
/* 317 */     for (BlockPos pos : getPositionsFromBox(bBox)) {
/* 318 */       BlockEntity blockEntity = getBlockEntity((BlockGetter)level, pos);
/* 319 */       if (blockEntity != null) {
/* 320 */         list.add(blockEntity);
/*     */       }
/*     */     } 
/* 323 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void gravitateEntityTowards(Entity ent, double x, double y, double z) {
/* 330 */     double dX = x - ent.m_20185_();
/* 331 */     double dY = y - ent.m_20186_();
/* 332 */     double dZ = z - ent.m_20189_();
/* 333 */     double dist = Math.sqrt(dX * dX + dY * dY + dZ * dZ);
/*     */     
/* 335 */     double vel = 1.0D - dist / 15.0D;
/* 336 */     if (vel > 0.0D) {
/* 337 */       vel *= vel;
/* 338 */       ent.m_20256_(ent.m_20184_().m_82520_(dX / dist * vel * 0.1D, dY / dist * vel * 0.2D, dZ / dist * vel * 0.1D));
/*     */     } 
/*     */   }
/*     */   public static void growNearbyRandomly(boolean harvest, Level level, BlockPos pos, Player player) {
/*     */     ServerLevel serverLevel;
/* 343 */     if (level instanceof ServerLevel) { serverLevel = (ServerLevel)level; }
/*     */     else
/*     */     { return; }
/* 346 */      boolean grewWater = false;
/* 347 */     int chance = harvest ? 16 : 32;
/* 348 */     for (BlockPos currentPos : getPositionsFromBox(pos.m_7918_(-5, -3, -5), pos.m_7918_(5, 3, 5))) {
/* 349 */       currentPos = currentPos.m_7949_();
/* 350 */       BlockState state = serverLevel.m_8055_(currentPos);
/* 351 */       Block crop = state.m_60734_();
/*     */ 
/*     */       
/* 354 */       if (crop instanceof net.minecraftforge.common.IForgeShearable || crop instanceof net.minecraft.world.level.block.FlowerBlock || crop instanceof net.minecraft.world.level.block.DoublePlantBlock || crop instanceof net.minecraft.world.level.block.RootsBlock || crop instanceof net.minecraft.world.level.block.NetherSproutsBlock || crop instanceof net.minecraft.world.level.block.HangingRootsBlock) {
/*     */         
/* 356 */         if (harvest) {
/* 357 */           harvestBlock((Level)serverLevel, currentPos, (ServerPlayer)player);
/*     */         }
/*     */         
/*     */         continue;
/*     */       } 
/* 362 */       if (crop instanceof BonemealableBlock) { BonemealableBlock growable = (BonemealableBlock)crop;
/* 363 */         if (!growable.m_7370_((LevelReader)serverLevel, currentPos, state, false)) {
/* 364 */           if (harvest && !state.m_204336_(PETags.Blocks.BLACKLIST_HARVEST) && (
/* 365 */             !leaveBottomBlock(crop) || serverLevel.m_8055_(currentPos.m_7495_()).m_60713_(crop)))
/*     */           {
/* 367 */             harvestBlock((Level)serverLevel, currentPos, (ServerPlayer)player); } 
/*     */           continue;
/*     */         } 
/* 370 */         if ((ProjectEConfig.server.items.harvBandGrass.get() || !isGrassLikeBlock(crop)) && 
/* 371 */           serverLevel.f_46441_.m_188503_(chance) == 0) {
/* 372 */           growable.m_214148_(serverLevel, serverLevel.f_46441_, currentPos, state);
/* 373 */           level.m_46796_(1505, currentPos, 0);
/*     */         } 
/*     */         
/*     */         continue; }
/*     */ 
/*     */       
/* 379 */       if (crop instanceof net.minecraftforge.common.IPlantable) {
/* 380 */         if (serverLevel.f_46441_.m_188503_(chance / 4) == 0) {
/* 381 */           for (int i = 0; i < (harvest ? 8 : 4); i++) {
/* 382 */             state.m_222972_(serverLevel, currentPos, serverLevel.f_46441_);
/*     */           }
/*     */         }
/* 385 */         if (harvest) {
/* 386 */           if (crop == Blocks.f_50130_ || crop == Blocks.f_50128_) {
/* 387 */             if (serverLevel.m_8055_(currentPos.m_7494_()).m_60713_(crop) && serverLevel.m_8055_(currentPos.m_6630_(2)).m_60713_(crop))
/* 388 */               for (int i = (crop == Blocks.f_50130_) ? 1 : 0; i < 3; i++)
/* 389 */                 harvestBlock((Level)serverLevel, currentPos.m_6630_(i), (ServerPlayer)player);  
/*     */             continue;
/*     */           } 
/* 392 */           if (crop == Blocks.f_50200_ && (
/* 393 */             (Integer)state.m_61143_((Property)NetherWartBlock.f_54967_)).intValue() == 3) {
/* 394 */             harvestBlock((Level)serverLevel, currentPos, (ServerPlayer)player);
/*     */           }
/*     */         } 
/*     */         
/*     */         continue;
/*     */       } 
/* 400 */       if (!grewWater && serverLevel.f_46441_.m_188503_(512) == 0 && growWaterPlant(serverLevel, currentPos, state, null)) {
/* 401 */         level.m_46796_(1505, currentPos, 0);
/* 402 */         grewWater = true;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean leaveBottomBlock(Block crop) {
/* 408 */     return (crop == Blocks.f_50576_ || crop == Blocks.f_50571_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isGrassLikeBlock(Block crop) {
/* 414 */     return (crop instanceof net.minecraft.world.level.block.GrassBlock || crop instanceof net.minecraft.world.level.block.NyliumBlock || crop instanceof net.minecraft.world.level.block.NetherrackBlock || crop instanceof net.minecraft.world.level.block.MossBlock);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void harvestBlock(Level level, BlockPos pos, @Nullable ServerPlayer player) {
/* 421 */     if (player == null || PlayerHelper.hasBreakPermission(player, pos)) {
/* 422 */       level.m_46953_(pos, true, (Entity)player);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean growWaterPlant(ServerLevel level, BlockPos pos, BlockState state, @Nullable Direction side) {
/* 428 */     boolean success = false;
/* 429 */     if (state.m_60713_(Blocks.f_49990_) && state.m_60819_().m_76186_() == 8) {
/* 430 */       RandomSource random = level.m_213780_();
/*     */       int i;
/* 432 */       label47: for (i = 0; i < 128; i++) {
/* 433 */         BlockPos blockpos = pos;
/* 434 */         for (int j = 0; j < i / 16; j++) {
/* 435 */           blockpos = blockpos.m_7918_(random.m_188503_(3) - 1, (random.m_188503_(3) - 1) * random.m_188503_(3) / 2, random
/* 436 */               .m_188503_(3) - 1);
/* 437 */           if (level.m_8055_(blockpos).m_60838_((BlockGetter)level, blockpos)) {
/*     */             continue label47;
/*     */           }
/*     */         } 
/* 441 */         BlockState newState = Blocks.f_50037_.m_49966_();
/* 442 */         Holder<Biome> biome = level.m_204166_(blockpos);
/* 443 */         if (biome.m_203565_(Biomes.f_48166_)) {
/* 444 */           if (i == 0 && side != null && side.m_122434_().m_122479_()) {
/* 445 */             newState = getRandomState(BlockTags.f_13052_, random, newState);
/* 446 */             if (newState.m_61138_((Property)BaseCoralWallFanBlock.f_49192_)) {
/* 447 */               newState = (BlockState)newState.m_61124_((Property)BaseCoralWallFanBlock.f_49192_, (Comparable)side);
/*     */             }
/* 449 */           } else if (random.m_188503_(4) == 0) {
/* 450 */             newState = getRandomState(BlockTags.f_13050_, random, newState);
/*     */           } 
/*     */         }
/* 453 */         if (newState.m_204338_(BlockTags.f_13052_, s -> s.m_61138_((Property)BaseCoralWallFanBlock.f_49192_))) {
/* 454 */           for (int k = 0; !newState.m_60710_((LevelReader)level, blockpos) && k < 4; k++) {
/* 455 */             newState = (BlockState)newState.m_61124_((Property)BaseCoralWallFanBlock.f_49192_, (Comparable)Direction.Plane.HORIZONTAL.m_235690_(random));
/*     */           }
/*     */         }
/* 458 */         if (newState.m_60710_((LevelReader)level, blockpos)) {
/* 459 */           BlockState stateToReplace = level.m_8055_(blockpos);
/* 460 */           if (stateToReplace.m_60713_(Blocks.f_49990_) && stateToReplace.m_60819_().m_76186_() == 8) {
/* 461 */             level.m_46597_(blockpos, newState);
/* 462 */             success = true;
/* 463 */           } else if (stateToReplace.m_60713_(Blocks.f_50037_) && random.m_188503_(10) == 0) {
/* 464 */             ((BonemealableBlock)Blocks.f_50037_).m_214148_(level, random, blockpos, stateToReplace);
/* 465 */             success = true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 470 */     return success;
/*     */   }
/*     */   
/*     */   private static BlockState getRandomState(TagKey<Block> key, RandomSource random, BlockState fallback) {
/* 474 */     return LazyTagLookup.<TYPE>tagManager(ForgeRegistries.BLOCKS).getTag(key)
/* 475 */       .getRandomElement(random)
/* 476 */       .map(Block::m_49966_)
/* 477 */       .orElse(fallback);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int harvestVein(Level level, Player player, ItemStack stack, BlockPos pos, Block target, List<ItemStack> currentDrops, int numMined) {
/* 484 */     if (numMined >= 250) {
/* 485 */       return numMined;
/*     */     }
/* 487 */     AABB b = new AABB((pos.m_123341_() - 1), (pos.m_123342_() - 1), (pos.m_123343_() - 1), (pos.m_123341_() + 1), (pos.m_123342_() + 1), (pos.m_123343_() + 1));
/* 488 */     for (BlockPos currentPos : getPositionsFromBox(b)) {
/* 489 */       BlockState currentState = level.m_8055_(currentPos);
/* 490 */       if (currentState.m_60734_() == target) {
/*     */         
/* 492 */         currentPos = currentPos.m_7949_();
/* 493 */         if (PlayerHelper.hasBreakPermission((ServerPlayer)player, currentPos)) {
/* 494 */           numMined++;
/* 495 */           currentDrops.addAll(Block.m_49874_(currentState, (ServerLevel)level, currentPos, getBlockEntity((BlockGetter)level, currentPos), (Entity)player, stack));
/* 496 */           level.m_7471_(currentPos, false);
/* 497 */           numMined = harvestVein(level, player, stack, currentPos, target, currentDrops, numMined);
/* 498 */           if (numMined >= 250) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 504 */     return numMined;
/*     */   }
/*     */   
/*     */   public static void igniteNearby(Level level, Player player) {
/* 508 */     for (BlockPos pos : BlockPos.m_121940_(player.m_20183_().m_7918_(-8, -5, -8), player.m_20183_().m_7918_(8, 5, 8))) {
/* 509 */       if (level.f_46441_.m_188503_(128) == 0 && level.m_46859_(pos)) {
/* 510 */         PlayerHelper.checkedPlaceBlock((ServerPlayer)player, pos.m_7949_(), Blocks.f_50083_.m_49966_());
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean validRepelEntity(Entity entity, TagKey<EntityType<?>> blacklistTag) {
/* 516 */     if (!entity.m_5833_() && !entity.m_6095_().m_204039_(blacklistTag)) {
/* 517 */       if (entity instanceof Projectile)
/*     */       {
/* 519 */         return !entity.m_20096_();
/*     */       }
/* 521 */       return entity instanceof net.minecraft.world.entity.Mob;
/*     */     } 
/* 523 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void repelEntitiesInterdiction(Level level, AABB effectBounds, double x, double y, double z) {
/* 530 */     Vec3 vec = new Vec3(x, y, z);
/* 531 */     Predicate<Entity> repelPredicate = ProjectEConfig.server.effects.interdictionMode.get() ? INTERDICTION_REPEL_HOSTILE_PREDICATE : INTERDICTION_REPEL_PREDICATE;
/* 532 */     for (Entity ent : level.m_6443_(Entity.class, effectBounds, repelPredicate)) {
/* 533 */       repelEntity(vec, ent);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void repelEntitiesSWRG(Level level, AABB effectBounds, Player player) {
/* 541 */     Vec3 playerVec = player.m_20182_();
/* 542 */     for (Entity ent : level.m_6443_(Entity.class, effectBounds, SWRG_REPEL_PREDICATE)) {
/* 543 */       if (ent instanceof Projectile) { Projectile projectile = (Projectile)ent;
/* 544 */         Entity owner = projectile.m_19749_();
/*     */ 
/*     */ 
/*     */         
/* 548 */         if ((level.m_5776_() && owner == null) || (owner != null && player.m_20148_().equals(owner.m_20148_()))) {
/*     */           continue;
/*     */         } }
/*     */       
/* 552 */       repelEntity(playerVec, ent);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void repelEntity(Vec3 vec, Entity entity) {
/* 557 */     Vec3 t = new Vec3(entity.m_20185_(), entity.m_20186_(), entity.m_20189_());
/* 558 */     Vec3 r = new Vec3(t.f_82479_ - vec.f_82479_, t.f_82480_ - vec.f_82480_, t.f_82481_ - vec.f_82481_);
/* 559 */     double distance = vec.m_82554_(t) + 0.1D;
/* 560 */     entity.m_20256_(entity.m_20184_().m_82549_(r.m_82490_(0.6666666666666666D / distance)));
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static InteractionResult igniteBlock(UseOnContext ctx) {
/* 565 */     Player player = ctx.m_43723_();
/* 566 */     if (player == null) {
/* 567 */       return InteractionResult.FAIL;
/*     */     }
/* 569 */     Level level = ctx.m_43725_();
/* 570 */     BlockPos pos = ctx.m_8083_();
/* 571 */     Direction side = ctx.m_43719_();
/* 572 */     BlockState state = level.m_8055_(pos);
/* 573 */     if (BaseFireBlock.m_49255_(level, pos, side)) {
/* 574 */       if (!level.f_46443_ && PlayerHelper.hasBreakPermission((ServerPlayer)player, pos)) {
/* 575 */         level.m_46597_(pos, BaseFireBlock.m_49245_((BlockGetter)level, pos));
/* 576 */         level.m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)PESoundEvents.POWER.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
/*     */       } 
/* 578 */     } else if (CampfireBlock.m_51321_(state)) {
/* 579 */       if (!level.f_46443_ && PlayerHelper.hasBreakPermission((ServerPlayer)player, pos)) {
/* 580 */         level.m_46597_(pos, (BlockState)state.m_61124_((Property)BlockStateProperties.f_61443_, Boolean.valueOf(true)));
/* 581 */         level.m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)PESoundEvents.POWER.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
/*     */       } 
/* 583 */     } else if (state.isFlammable((BlockGetter)level, pos, side)) {
/* 584 */       if (!level.f_46443_ && PlayerHelper.hasBreakPermission((ServerPlayer)player, pos)) {
/*     */         
/* 586 */         state.onCaughtFire(level, pos, side, (LivingEntity)player);
/* 587 */         if (state.m_60734_() instanceof net.minecraft.world.level.block.TntBlock) {
/* 588 */           level.m_7471_(pos, false);
/*     */         }
/* 590 */         level.m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)PESoundEvents.POWER.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
/*     */       } 
/*     */     } else {
/* 593 */       return InteractionResult.PASS;
/*     */     } 
/* 595 */     return InteractionResult.m_19078_(level.f_46443_);
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
/*     */   public static boolean isBlockLoaded(@Nullable BlockGetter world, @NotNull BlockPos pos) {
/* 609 */     if (world == null)
/* 610 */       return false; 
/* 611 */     if (world instanceof LevelReader) { LevelReader reader = (LevelReader)world;
/* 612 */       if (reader instanceof Level) { Level level = (Level)reader; if (!level.m_46739_(pos))
/* 613 */           return false;  }
/*     */       
/* 615 */       return reader.m_46805_(pos); }
/*     */     
/* 617 */     return true;
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
/*     */   @Nullable
/*     */   public static BlockEntity getBlockEntity(@Nullable BlockGetter level, @NotNull BlockPos pos) {
/* 632 */     if (!isBlockLoaded(level, pos))
/*     */     {
/* 634 */       return null;
/*     */     }
/* 636 */     return level.m_7702_(pos);
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
/*     */   @Nullable
/*     */   public static <BE extends BlockEntity> BE getBlockEntity(@NotNull Class<BE> clazz, @Nullable BlockGetter level, @NotNull BlockPos pos) {
/* 652 */     return getBlockEntity(clazz, level, pos, false);
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
/*     */   @Nullable
/*     */   public static <BE extends BlockEntity> BE getBlockEntity(@NotNull Class<BE> clazz, @Nullable BlockGetter level, @NotNull BlockPos pos, boolean logWrongType) {
/* 669 */     BlockEntity blockEntity = getBlockEntity(level, pos);
/* 670 */     if (blockEntity == null) {
/* 671 */       return null;
/*     */     }
/* 673 */     if (clazz.isInstance(blockEntity))
/* 674 */       return clazz.cast(blockEntity); 
/* 675 */     if (logWrongType) {
/* 676 */       PECore.LOGGER.warn("Unexpected block entity class at {}, expected {}, but found: {}", new Object[] { pos, clazz, blockEntity.getClass() });
/*     */     }
/* 678 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\project\\utils\WorldHelper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */