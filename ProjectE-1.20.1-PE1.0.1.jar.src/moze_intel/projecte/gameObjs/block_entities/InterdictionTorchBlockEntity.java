/*    */ package moze_intel.projecte.gameObjs.block_entities;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.registries.PEBlockEntityTypes;
/*    */ import moze_intel.projecte.utils.WorldHelper;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.entity.BlockEntity;
/*    */ import net.minecraft.world.level.block.entity.BlockEntityType;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.phys.AABB;
/*    */ 
/*    */ public class InterdictionTorchBlockEntity extends BlockEntity {
/*    */   public InterdictionTorchBlockEntity(BlockPos pos, BlockState state) {
/* 14 */     super((BlockEntityType)PEBlockEntityTypes.INTERDICTION_TORCH.get(), pos, state);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void tick(Level level, BlockPos pos, BlockState state, InterdictionTorchBlockEntity torch) {
/* 20 */     WorldHelper.repelEntitiesInterdiction(level, new AABB(pos.m_7918_(-8, -8, -8), pos.m_7918_(8, 8, 8)), pos
/* 21 */         .m_123341_() + 0.5D, pos.m_123342_() + 0.5D, pos.m_123343_() + 0.5D);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\block_entities\InterdictionTorchBlockEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */