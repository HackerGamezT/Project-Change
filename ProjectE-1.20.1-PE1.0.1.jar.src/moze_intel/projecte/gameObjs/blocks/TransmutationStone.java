/*     */ package moze_intel.projecte.gameObjs.blocks;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.MenuProvider;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*     */ import net.minecraft.world.item.context.BlockPlaceContext;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.LevelAccessor;
/*     */ import net.minecraft.world.level.LevelReader;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.DirectionalBlock;
/*     */ import net.minecraft.world.level.block.Mirror;
/*     */ import net.minecraft.world.level.block.Rotation;
/*     */ import net.minecraft.world.level.block.SimpleWaterloggedBlock;
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
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class TransmutationStone extends DirectionalBlock implements SimpleWaterloggedBlock {
/*  39 */   private static final VoxelShape UP_SHAPE = Block.m_49796_(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);
/*  40 */   private static final VoxelShape DOWN_SHAPE = Block.m_49796_(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D);
/*  41 */   private static final VoxelShape NORTH_SHAPE = Block.m_49796_(0.0D, 0.0D, 12.0D, 16.0D, 16.0D, 16.0D);
/*  42 */   private static final VoxelShape SOUTH_SHAPE = Block.m_49796_(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 4.0D);
/*  43 */   private static final VoxelShape WEST_SHAPE = Block.m_49796_(12.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
/*  44 */   private static final VoxelShape EAST_SHAPE = Block.m_49796_(0.0D, 0.0D, 0.0D, 4.0D, 16.0D, 16.0D);
/*     */   
/*     */   public TransmutationStone(BlockBehaviour.Properties props) {
/*  47 */     super(props);
/*  48 */     m_49959_((BlockState)((BlockState)((BlockState)m_49965_().m_61090_()).m_61124_((Property)f_52588_, (Comparable)Direction.UP)).m_61124_((Property)BlockStateProperties.f_61362_, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_7926_(@NotNull StateDefinition.Builder<Block, BlockState> props) {
/*  53 */     super.m_7926_(props);
/*  54 */     props.m_61104_(new Property[] { (Property)f_52588_, (Property)BlockStateProperties.f_61362_ });
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean m_7357_(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull PathComputationType type) {
/*  60 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public VoxelShape m_5940_(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext ctx) {
/*  67 */     Direction facing = (Direction)state.m_61143_((Property)f_52588_);
/*  68 */     switch (facing) { default: throw new IncompatibleClassChangeError();case DOWN: case NORTH: case SOUTH: case WEST: case EAST: case UP: break; }  return 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  74 */       UP_SHAPE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public InteractionResult m_6227_(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult rtr) {
/*  83 */     if (!level.f_46443_) {
/*  84 */       NetworkHooks.openScreen((ServerPlayer)player, new ContainerProvider(), b -> b.writeBoolean(false));
/*     */     }
/*  86 */     return InteractionResult.m_19078_(level.f_46443_);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BlockState m_5573_(@NotNull BlockPlaceContext context) {
/*  92 */     BlockState state = super.m_5573_(context);
/*  93 */     return (state == null) ? null : (BlockState)((BlockState)state.m_61124_((Property)f_52588_, (Comparable)context.m_43719_())).m_61124_((Property)BlockStateProperties.f_61362_, Boolean.valueOf((context.m_43725_().m_6425_(context.m_8083_()).m_76152_() == Fluids.f_76193_)));
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public FluidState m_5888_(BlockState state) {
/* 100 */     return ((Boolean)state.m_61143_((Property)BlockStateProperties.f_61362_)).booleanValue() ? Fluids.f_76193_.m_76068_(false) : super.m_5888_(state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public BlockState m_7417_(@NotNull BlockState state, @NotNull Direction facing, @NotNull BlockState facingState, @NotNull LevelAccessor level, @NotNull BlockPos currentPos, @NotNull BlockPos facingPos) {
/* 108 */     if (((Boolean)state.m_61143_((Property)BlockStateProperties.f_61362_)).booleanValue()) {
/* 109 */       level.m_186469_(currentPos, (Fluid)Fluids.f_76193_, Fluids.f_76193_.m_6718_((LevelReader)level));
/*     */     }
/* 111 */     return super.m_7417_(state, facing, facingState, level, currentPos, facingPos);
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public BlockState m_6843_(BlockState state, Rotation rot) {
/* 118 */     return (BlockState)state.m_61124_((Property)f_52588_, (Comparable)rot.m_55954_((Direction)state.m_61143_((Property)f_52588_)));
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public BlockState m_6943_(BlockState state, Mirror mirrorIn) {
/* 125 */     return state.m_60717_(mirrorIn.m_54846_((Direction)state.m_61143_((Property)f_52588_)));
/*     */   }
/*     */   
/*     */   private static class ContainerProvider
/*     */     implements MenuProvider
/*     */   {
/*     */     public AbstractContainerMenu m_7208_(int windowId, @NotNull Inventory playerInventory, @NotNull Player player) {
/* 132 */       return (AbstractContainerMenu)new TransmutationContainer(windowId, playerInventory);
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Component m_5446_() {
/* 138 */       return (Component)PELang.TRANSMUTATION_TRANSMUTE.translate(new Object[0]);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\blocks\TransmutationStone.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */