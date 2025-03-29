/*    */ package moze_intel.projecte.gameObjs.blocks;
/*    */ import moze_intel.projecte.utils.WorldHelper;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.core.Direction;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.item.context.BlockPlaceContext;
/*    */ import net.minecraft.world.level.BlockGetter;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.Mirror;
/*    */ import net.minecraft.world.level.block.Rotation;
/*    */ import net.minecraft.world.level.block.entity.BlockEntity;
/*    */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.level.block.state.StateDefinition;
/*    */ import net.minecraft.world.level.block.state.properties.DirectionProperty;
/*    */ import net.minecraft.world.level.block.state.properties.Property;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public abstract class BlockDirection extends Block {
/* 23 */   public static final DirectionProperty FACING = HorizontalDirectionalBlock.f_54117_;
/*    */   
/*    */   public BlockDirection(BlockBehaviour.Properties props) {
/* 26 */     super(props);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void m_7926_(@NotNull StateDefinition.Builder<Block, BlockState> props) {
/* 31 */     super.m_7926_(props);
/* 32 */     props.m_61104_(new Property[] { (Property)FACING });
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public BlockState m_5573_(BlockPlaceContext ctx) {
/* 38 */     if (ctx.m_43723_() != null) {
/* 39 */       return (BlockState)m_49966_().m_61124_((Property)FACING, (Comparable)ctx.m_43723_().m_6350_().m_122424_());
/*    */     }
/* 41 */     return m_49966_();
/*    */   }
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public void m_6810_(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState newState, boolean isMoving) {
/* 47 */     if (state.m_60734_() != newState.m_60734_()) {
/* 48 */       BlockEntity blockEntity = WorldHelper.getBlockEntity((BlockGetter)level, pos);
/* 49 */       if (blockEntity != null) {
/* 50 */         blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(inv -> WorldHelper.dropInventory(inv, level, pos));
/*    */       }
/* 52 */       super.m_6810_(state, level, pos, newState, isMoving);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public void m_6256_(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player) {
/* 59 */     if (!level.f_46443_) {
/* 60 */       ItemStack stack = player.m_21205_();
/* 61 */       if (!stack.m_41619_() && stack.m_41720_() instanceof moze_intel.projecte.gameObjs.items.PhilosophersStone) {
/* 62 */         level.m_46597_(pos, (BlockState)level.m_8055_(pos).m_61124_((Property)FACING, (Comparable)player.m_6350_().m_122424_()));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   @NotNull
/*    */   public BlockState m_6843_(BlockState state, Rotation rot) {
/* 71 */     return (BlockState)state.m_61124_((Property)FACING, (Comparable)rot.m_55954_((Direction)state.m_61143_((Property)FACING)));
/*    */   }
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   @NotNull
/*    */   public BlockState m_6943_(BlockState state, Mirror mirrorIn) {
/* 78 */     return state.m_60717_(mirrorIn.m_54846_((Direction)state.m_61143_((Property)FACING)));
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\blocks\BlockDirection.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */