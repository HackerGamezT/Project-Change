/*    */ package moze_intel.projecte.gameObjs.block_entities;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.container.EmcChestBlockEntityContainer;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.sounds.SoundEvents;
/*    */ import net.minecraft.sounds.SoundSource;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class null
/*    */   extends ContainerOpenersCounter
/*    */ {
/*    */   protected void m_142292_(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state) {
/* 24 */     level.m_6263_(null, pos.m_123341_() + 0.5D, pos.m_123342_() + 0.5D, pos.m_123343_() + 0.5D, SoundEvents.f_11749_, SoundSource.BLOCKS, 0.5F, level.f_46441_
/* 25 */         .m_188501_() * 0.1F + 0.9F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void m_142289_(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state) {
/* 30 */     level.m_6263_(null, pos.m_123341_() + 0.5D, pos.m_123342_() + 0.5D, pos.m_123343_() + 0.5D, SoundEvents.f_11747_, SoundSource.BLOCKS, 0.5F, level.f_46441_
/* 31 */         .m_188501_() * 0.1F + 0.9F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void m_142148_(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, int oldCount, int openCount) {
/* 36 */     level.m_7696_(pos, state.m_60734_(), 1, openCount);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean m_142718_(Player player) {
/* 41 */     AbstractContainerMenu abstractContainerMenu = player.f_36096_; if (abstractContainerMenu instanceof EmcChestBlockEntityContainer) { EmcChestBlockEntityContainer<?> container = (EmcChestBlockEntityContainer)abstractContainerMenu; if (container.blockEntityMatches(EmcChestBlockEntity.this)); }  return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\block_entities\EmcChestBlockEntity$1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */