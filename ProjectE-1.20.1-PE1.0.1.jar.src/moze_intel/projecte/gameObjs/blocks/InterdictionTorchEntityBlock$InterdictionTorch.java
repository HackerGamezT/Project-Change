/*    */ package moze_intel.projecte.gameObjs.blocks;
/*    */ 
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.core.particles.ParticleOptions;
/*    */ import net.minecraft.core.particles.ParticleTypes;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.TorchBlock;
/*    */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InterdictionTorch
/*    */   extends TorchBlock
/*    */   implements InterdictionTorchEntityBlock
/*    */ {
/*    */   public InterdictionTorch(BlockBehaviour.Properties props) {
/* 26 */     super(props, (ParticleOptions)ParticleTypes.f_123745_);
/*    */   }
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public boolean m_8133_(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, int id, int param) {
/* 32 */     super.m_8133_(state, level, pos, id, param);
/* 33 */     return triggerBlockEntityEvent(state, level, pos, id, param);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\blocks\InterdictionTorchEntityBlock$InterdictionTorch.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */