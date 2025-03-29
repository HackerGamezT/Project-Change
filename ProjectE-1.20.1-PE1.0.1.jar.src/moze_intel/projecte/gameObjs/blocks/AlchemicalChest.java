/*     */ package moze_intel.projecte.gameObjs.blocks;
/*     */ import moze_intel.projecte.gameObjs.block_entities.EmcChestBlockEntity;
/*     */ import moze_intel.projecte.gameObjs.registration.impl.BlockEntityTypeRegistryObject;
/*     */ import moze_intel.projecte.gameObjs.registries.PEBlockEntityTypes;
/*     */ import moze_intel.projecte.utils.WorldHelper;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.stats.Stats;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.MenuProvider;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.context.BlockPlaceContext;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.LevelAccessor;
/*     */ import net.minecraft.world.level.LevelReader;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.RenderShape;
/*     */ import net.minecraft.world.level.block.SimpleWaterloggedBlock;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.block.state.StateDefinition;
/*     */ import net.minecraft.world.level.block.state.properties.BlockStateProperties;
/*     */ import net.minecraft.world.level.block.state.properties.Property;
/*     */ import net.minecraft.world.level.material.Fluid;
/*     */ import net.minecraft.world.level.material.FluidState;
/*     */ import net.minecraft.world.level.material.Fluids;
/*     */ import net.minecraft.world.phys.BlockHitResult;
/*     */ import net.minecraft.world.phys.shapes.CollisionContext;
/*     */ import net.minecraft.world.phys.shapes.VoxelShape;
/*     */ import net.minecraftforge.common.capabilities.ForgeCapabilities;
/*     */ import net.minecraftforge.items.ItemHandlerHelper;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class AlchemicalChest extends BlockDirection implements SimpleWaterloggedBlock, PEEntityBlock<EmcChestBlockEntity> {
/*  42 */   private static final VoxelShape SHAPE = Block.m_49796_(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
/*     */   
/*     */   public AlchemicalChest(BlockBehaviour.Properties props) {
/*  45 */     super(props);
/*  46 */     m_49959_((BlockState)((BlockState)((BlockState)m_49965_().m_61090_()).m_61124_((Property)FACING, (Comparable)Direction.NORTH)).m_61124_((Property)BlockStateProperties.f_61362_, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_7926_(@NotNull StateDefinition.Builder<Block, BlockState> props) {
/*  51 */     super.m_7926_(props);
/*  52 */     props.m_61104_(new Property[] { (Property)BlockStateProperties.f_61362_ });
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean m_7357_(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull PathComputationType type) {
/*  58 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public VoxelShape m_5940_(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext ctx) {
/*  65 */     return SHAPE;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public RenderShape m_7514_(@NotNull BlockState state) {
/*  72 */     return RenderShape.ENTITYBLOCK_ANIMATED;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public InteractionResult m_6227_(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult rtr) {
/*  80 */     if (level.f_46443_) {
/*  81 */       return InteractionResult.SUCCESS;
/*     */     }
/*  83 */     EmcChestBlockEntity chest = (EmcChestBlockEntity)WorldHelper.getBlockEntity(EmcChestBlockEntity.class, (BlockGetter)level, pos, true);
/*  84 */     if (chest != null) {
/*  85 */       NetworkHooks.openScreen((ServerPlayer)player, (MenuProvider)chest, pos);
/*  86 */       player.m_36220_(Stats.f_12968_);
/*  87 */       PiglinAi.m_34873_(player, true);
/*     */     } 
/*  89 */     return InteractionResult.CONSUME;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BlockEntityTypeRegistryObject<? extends EmcChestBlockEntity> getType() {
/*  95 */     return PEBlockEntityTypes.ALCHEMICAL_CHEST;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean m_8133_(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, int id, int param) {
/* 101 */     super.m_8133_(state, level, pos, id, param);
/* 102 */     return triggerBlockEntityEvent(state, level, pos, id, param);
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void m_213897_(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
/* 108 */     EmcChestBlockEntity chest = (EmcChestBlockEntity)WorldHelper.getBlockEntity(EmcChestBlockEntity.class, (BlockGetter)level, pos);
/* 109 */     if (chest != null) {
/* 110 */       chest.recheckOpen();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean m_7278_(@NotNull BlockState state) {
/* 117 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public int m_6782_(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos) {
/* 123 */     BlockEntity blockEntity = WorldHelper.getBlockEntity((BlockGetter)level, pos);
/* 124 */     if (blockEntity != null) {
/* 125 */       return ((Integer)blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).map(ItemHandlerHelper::calcRedstoneFromInventory).orElse(Integer.valueOf(0))).intValue();
/*     */     }
/* 127 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public BlockState m_5573_(BlockPlaceContext context) {
/* 133 */     return (BlockState)super.m_5573_(context).m_61124_((Property)BlockStateProperties.f_61362_, Boolean.valueOf((context.m_43725_().m_6425_(context.m_8083_()).m_76152_() == Fluids.f_76193_)));
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public FluidState m_5888_(BlockState state) {
/* 140 */     return ((Boolean)state.m_61143_((Property)BlockStateProperties.f_61362_)).booleanValue() ? Fluids.f_76193_.m_76068_(false) : super.m_5888_(state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public BlockState m_7417_(@NotNull BlockState state, @NotNull Direction facing, @NotNull BlockState facingState, @NotNull LevelAccessor level, @NotNull BlockPos currentPos, @NotNull BlockPos facingPos) {
/* 148 */     if (((Boolean)state.m_61143_((Property)BlockStateProperties.f_61362_)).booleanValue()) {
/* 149 */       level.m_186469_(currentPos, (Fluid)Fluids.f_76193_, Fluids.f_76193_.m_6718_((LevelReader)level));
/*     */     }
/* 151 */     return super.m_7417_(state, facing, facingState, level, currentPos, facingPos);
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\blocks\AlchemicalChest.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */