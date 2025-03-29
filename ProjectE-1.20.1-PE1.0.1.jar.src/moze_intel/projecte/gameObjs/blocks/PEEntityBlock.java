/*    */ package moze_intel.projecte.gameObjs.blocks;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.registration.impl.BlockEntityTypeRegistryObject;
/*    */ import moze_intel.projecte.utils.WorldHelper;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.world.level.BlockGetter;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.EntityBlock;
/*    */ import net.minecraft.world.level.block.entity.BlockEntity;
/*    */ import net.minecraft.world.level.block.entity.BlockEntityTicker;
/*    */ import net.minecraft.world.level.block.entity.BlockEntityType;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public interface PEEntityBlock<BLOCK_ENTITY extends BlockEntity>
/*    */   extends EntityBlock {
/*    */   @Nullable
/*    */   BlockEntityTypeRegistryObject<? extends BLOCK_ENTITY> getType();
/*    */   
/*    */   @Nullable
/*    */   default BLOCK_ENTITY m_142194_(@NotNull BlockPos pos, @NotNull BlockState state) {
/* 23 */     BlockEntityTypeRegistryObject<? extends BLOCK_ENTITY> type = getType();
/* 24 */     return (type == null) ? null : (BLOCK_ENTITY)((BlockEntityType)type.get()).m_155264_(pos, state);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   default <T extends BlockEntity> BlockEntityTicker<T> m_142354_(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> blockEntityType) {
/* 30 */     BlockEntityTypeRegistryObject<? extends BLOCK_ENTITY> type = getType();
/* 31 */     if (type != null && blockEntityType == type.get()) {
/* 32 */       return type.getTicker(level.f_46443_);
/*    */     }
/* 34 */     return null;
/*    */   }
/*    */   
/*    */   default boolean triggerBlockEntityEvent(@NotNull BlockState state, Level level, BlockPos pos, int id, int param) {
/* 38 */     BlockEntity blockEntity = WorldHelper.getBlockEntity((BlockGetter)level, pos);
/* 39 */     return (blockEntity != null && blockEntity.m_7531_(id, param));
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\blocks\PEEntityBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */