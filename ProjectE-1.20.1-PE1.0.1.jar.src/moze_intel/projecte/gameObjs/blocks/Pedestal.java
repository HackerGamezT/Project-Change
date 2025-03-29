/*     */ package moze_intel.projecte.gameObjs.blocks;
/*     */ import java.util.List;
/*     */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*     */ import moze_intel.projecte.gameObjs.EnumMatterType;
/*     */ import moze_intel.projecte.gameObjs.block_entities.DMPedestalBlockEntity;
/*     */ import moze_intel.projecte.gameObjs.registration.impl.BlockEntityTypeRegistryObject;
/*     */ import moze_intel.projecte.gameObjs.registries.PEBlockEntityTypes;
/*     */ import moze_intel.projecte.utils.WorldHelper;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.TooltipFlag;
/*     */ import net.minecraft.world.item.context.BlockPlaceContext;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.LevelAccessor;
/*     */ import net.minecraft.world.level.LevelReader;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.SimpleWaterloggedBlock;
/*     */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.block.state.StateDefinition;
/*     */ import net.minecraft.world.level.block.state.properties.BlockStateProperties;
/*     */ import net.minecraft.world.level.block.state.properties.Property;
/*     */ import net.minecraft.world.level.material.Fluid;
/*     */ import net.minecraft.world.level.material.FluidState;
/*     */ import net.minecraft.world.level.material.Fluids;
/*     */ import net.minecraft.world.phys.shapes.CollisionContext;
/*     */ import net.minecraft.world.phys.shapes.Shapes;
/*     */ import net.minecraft.world.phys.shapes.VoxelShape;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class Pedestal extends Block implements SimpleWaterloggedBlock, PEEntityBlock<DMPedestalBlockEntity>, IMatterBlock {
/*  41 */   private static final VoxelShape SHAPE = Shapes.m_83110_(
/*  42 */       Block.m_49796_(3.0D, 0.0D, 3.0D, 13.0D, 2.0D, 13.0D), 
/*  43 */       Shapes.m_83110_(
/*  44 */         Block.m_49796_(6.0D, 2.0D, 6.0D, 10.0D, 9.0D, 10.0D), 
/*  45 */         Block.m_49796_(5.0D, 9.0D, 5.0D, 11.0D, 10.0D, 11.0D)));
/*     */ 
/*     */ 
/*     */   
/*     */   public Pedestal(BlockBehaviour.Properties props) {
/*  50 */     super(props);
/*  51 */     m_49959_((BlockState)((BlockState)m_49965_().m_61090_()).m_61124_((Property)BlockStateProperties.f_61362_, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_7926_(@NotNull StateDefinition.Builder<Block, BlockState> props) {
/*  56 */     super.m_7926_(props);
/*  57 */     props.m_61104_(new Property[] { (Property)BlockStateProperties.f_61362_ });
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean m_7357_(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull PathComputationType type) {
/*  63 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public VoxelShape m_5940_(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext ctx) {
/*  70 */     return SHAPE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean dropItem(Level level, BlockPos pos) {
/*  77 */     DMPedestalBlockEntity pedestal = (DMPedestalBlockEntity)WorldHelper.getBlockEntity(DMPedestalBlockEntity.class, (BlockGetter)level, pos);
/*  78 */     if (pedestal != null) {
/*  79 */       ItemStack stack = pedestal.getInventory().getStackInSlot(0);
/*  80 */       if (!stack.m_41619_()) {
/*  81 */         pedestal.getInventory().setStackInSlot(0, ItemStack.f_41583_);
/*  82 */         level.m_7967_((Entity)new ItemEntity(level, pos.m_123341_(), pos.m_123342_() + 0.8D, pos.m_123343_(), stack));
/*  83 */         return true;
/*     */       } 
/*     */     } 
/*  86 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void m_6810_(BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState newState, boolean isMoving) {
/*  92 */     if (state.m_60734_() != newState.m_60734_()) {
/*  93 */       dropItem(level, pos);
/*  94 */       super.m_6810_(state, level, pos, newState, isMoving);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void m_6256_(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player) {
/* 101 */     if (!level.f_46443_) {
/* 102 */       dropItem(level, pos);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
/* 108 */     if (player.m_7500_() && dropItem(level, pos)) {
/*     */ 
/*     */ 
/*     */       
/* 112 */       level.m_7260_(pos, state, state, 8);
/* 113 */       return false;
/*     */     } 
/* 115 */     return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public InteractionResult m_6227_(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult rtr) {
/* 123 */     if (!level.f_46443_) {
/* 124 */       DMPedestalBlockEntity pedestal = (DMPedestalBlockEntity)WorldHelper.getBlockEntity(DMPedestalBlockEntity.class, (BlockGetter)level, pos, true);
/* 125 */       if (pedestal == null) {
/* 126 */         return InteractionResult.FAIL;
/*     */       }
/* 128 */       ItemStack item = pedestal.getInventory().getStackInSlot(0);
/* 129 */       ItemStack stack = player.m_21120_(hand);
/* 130 */       if (stack.m_41619_() && !item.m_41619_()) {
/* 131 */         item.getCapability(PECapabilities.PEDESTAL_ITEM_CAPABILITY).ifPresent(pedestalItem -> {
/*     */               pedestal.setActive(!pedestal.getActive());
/*     */               level.m_7260_(pos, state, state, 8);
/*     */             });
/* 135 */       } else if (!stack.m_41619_() && item.m_41619_()) {
/* 136 */         pedestal.getInventory().setStackInSlot(0, stack.m_41620_(1));
/* 137 */         if (stack.m_41613_() <= 0) {
/* 138 */           player.m_21008_(hand, ItemStack.f_41583_);
/*     */         }
/*     */       } 
/*     */     } 
/* 142 */     return InteractionResult.m_19078_(level.f_46443_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void m_6861_(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Block neighbor, @NotNull BlockPos neighborPos, boolean isMoving) {
/* 149 */     boolean hasSignal = level.m_276867_(pos);
/* 150 */     DMPedestalBlockEntity ped = (DMPedestalBlockEntity)WorldHelper.getBlockEntity(DMPedestalBlockEntity.class, (BlockGetter)level, pos);
/* 151 */     if (ped != null && ped.previousRedstoneState != hasSignal) {
/* 152 */       if (hasSignal) {
/* 153 */         ItemStack stack = ped.getInventory().getStackInSlot(0);
/* 154 */         if (!stack.m_41619_() && stack.getCapability(PECapabilities.PEDESTAL_ITEM_CAPABILITY).isPresent()) {
/* 155 */           ped.setActive(!ped.getActive());
/* 156 */           level.m_7260_(pos, state, state, 11);
/*     */         } 
/*     */       } 
/* 159 */       ped.previousRedstoneState = hasSignal;
/* 160 */       ped.markDirty(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean m_7278_(@NotNull BlockState state) {
/* 167 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public int m_6782_(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos) {
/* 173 */     DMPedestalBlockEntity pedestal = (DMPedestalBlockEntity)WorldHelper.getBlockEntity(DMPedestalBlockEntity.class, (BlockGetter)level, pos);
/* 174 */     if (pedestal != null) {
/* 175 */       ItemStack stack = pedestal.getInventory().getStackInSlot(0);
/* 176 */       if (!stack.m_41619_()) {
/* 177 */         if (stack.getCapability(PECapabilities.PEDESTAL_ITEM_CAPABILITY).isPresent()) {
/* 178 */           return pedestal.getActive() ? 15 : 10;
/*     */         }
/* 180 */         return 5;
/*     */       } 
/*     */     } 
/* 183 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BlockEntityTypeRegistryObject<DMPedestalBlockEntity> getType() {
/* 189 */     return PEBlockEntityTypes.DARK_MATTER_PEDESTAL;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean m_8133_(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, int id, int param) {
/* 195 */     super.m_8133_(state, level, pos, id, param);
/* 196 */     return triggerBlockEntityEvent(state, level, pos, id, param);
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_5871_(@NotNull ItemStack stack, @Nullable BlockGetter level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flags) {
/* 201 */     super.m_5871_(stack, level, tooltip, flags);
/* 202 */     tooltip.add(PELang.PEDESTAL_TOOLTIP1.translate(new Object[0]));
/* 203 */     tooltip.add(PELang.PEDESTAL_TOOLTIP2.translate(new Object[0]));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BlockState m_5573_(@NotNull BlockPlaceContext context) {
/* 209 */     BlockState state = super.m_5573_(context);
/* 210 */     return (state == null) ? null : (BlockState)state.m_61124_((Property)BlockStateProperties.f_61362_, Boolean.valueOf((context.m_43725_().m_6425_(context.m_8083_()).m_76152_() == Fluids.f_76193_)));
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public FluidState m_5888_(BlockState state) {
/* 217 */     return ((Boolean)state.m_61143_((Property)BlockStateProperties.f_61362_)).booleanValue() ? Fluids.f_76193_.m_76068_(false) : super.m_5888_(state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public BlockState m_7417_(@NotNull BlockState state, @NotNull Direction facing, @NotNull BlockState facingState, @NotNull LevelAccessor level, @NotNull BlockPos currentPos, @NotNull BlockPos facingPos) {
/* 225 */     if (((Boolean)state.m_61143_((Property)BlockStateProperties.f_61362_)).booleanValue()) {
/* 226 */       level.m_186469_(currentPos, (Fluid)Fluids.f_76193_, Fluids.f_76193_.m_6718_((LevelReader)level));
/*     */     }
/* 228 */     return super.m_7417_(state, facing, facingState, level, currentPos, facingPos);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumMatterType getMatterType() {
/* 233 */     return EnumMatterType.DARK_MATTER;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\blocks\Pedestal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */