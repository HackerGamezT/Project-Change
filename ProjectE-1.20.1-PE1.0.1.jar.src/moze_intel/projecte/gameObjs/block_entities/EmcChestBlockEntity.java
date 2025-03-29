/*    */ package moze_intel.projecte.gameObjs.block_entities;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.container.EmcChestBlockEntityContainer;
/*    */ import moze_intel.projecte.gameObjs.registration.impl.BlockEntityTypeRegistryObject;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.sounds.SoundEvents;
/*    */ import net.minecraft.sounds.SoundSource;
/*    */ import net.minecraft.world.MenuProvider;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.entity.ChestLidController;
/*    */ import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
/*    */ import net.minecraft.world.level.block.entity.LidBlockEntity;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public abstract class EmcChestBlockEntity
/*    */   extends CapabilityEmcBlockEntity
/*    */   implements LidBlockEntity, MenuProvider {
/* 21 */   private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter()
/*    */     {
/*    */       protected void m_142292_(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state) {
/* 24 */         level.m_6263_(null, pos.m_123341_() + 0.5D, pos.m_123342_() + 0.5D, pos.m_123343_() + 0.5D, SoundEvents.f_11749_, SoundSource.BLOCKS, 0.5F, level.f_46441_
/* 25 */             .m_188501_() * 0.1F + 0.9F);
/*    */       }
/*    */ 
/*    */       
/*    */       protected void m_142289_(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state) {
/* 30 */         level.m_6263_(null, pos.m_123341_() + 0.5D, pos.m_123342_() + 0.5D, pos.m_123343_() + 0.5D, SoundEvents.f_11747_, SoundSource.BLOCKS, 0.5F, level.f_46441_
/* 31 */             .m_188501_() * 0.1F + 0.9F);
/*    */       }
/*    */ 
/*    */       
/*    */       protected void m_142148_(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, int oldCount, int openCount) {
/* 36 */         level.m_7696_(pos, state.m_60734_(), 1, openCount);
/*    */       }
/*    */ 
/*    */       
/*    */       protected boolean m_142718_(Player player) {
/* 41 */         AbstractContainerMenu abstractContainerMenu = player.f_36096_; if (abstractContainerMenu instanceof EmcChestBlockEntityContainer) { EmcChestBlockEntityContainer<?> container = (EmcChestBlockEntityContainer)abstractContainerMenu; if (container.blockEntityMatches(EmcChestBlockEntity.this)); }  return false;
/*    */       }
/*    */     };
/* 44 */   private final ChestLidController chestLidController = new ChestLidController();
/*    */   
/*    */   protected EmcChestBlockEntity(BlockEntityTypeRegistryObject<? extends EmcChestBlockEntity> type, BlockPos pos, BlockState state) {
/* 47 */     super((BlockEntityTypeRegistryObject)type, pos, state);
/*    */   }
/*    */ 
/*    */   
/*    */   protected EmcChestBlockEntity(BlockEntityTypeRegistryObject<? extends CapabilityEmcBlockEntity> type, BlockPos pos, BlockState state, long maxAmount) {
/* 52 */     super(type, pos, state, maxAmount);
/*    */   }
/*    */   
/*    */   public static void lidAnimateTick(Level level, BlockPos pos, BlockState state, EmcChestBlockEntity chest) {
/* 56 */     chest.chestLidController.m_155374_();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_7531_(int id, int type) {
/* 61 */     if (id == 1) {
/* 62 */       this.chestLidController.m_155377_((type > 0));
/* 63 */       return true;
/*    */     } 
/* 65 */     return super.m_7531_(id, type);
/*    */   }
/*    */   
/*    */   public void startOpen(Player player) {
/* 69 */     if (!m_58901_() && !player.m_5833_() && this.f_58857_ != null) {
/* 70 */       this.openersCounter.m_155452_(player, this.f_58857_, m_58899_(), m_58900_());
/*    */     }
/*    */   }
/*    */   
/*    */   public void stopOpen(Player player) {
/* 75 */     if (!m_58901_() && !player.m_5833_() && this.f_58857_ != null) {
/* 76 */       this.openersCounter.m_155468_(player, this.f_58857_, m_58899_(), m_58900_());
/*    */     }
/*    */   }
/*    */   
/*    */   public void recheckOpen() {
/* 81 */     if (!m_58901_() && this.f_58857_ != null) {
/* 82 */       this.openersCounter.m_155476_(this.f_58857_, m_58899_(), m_58900_());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public float m_6683_(float partialTicks) {
/* 88 */     return this.chestLidController.m_155375_(partialTicks);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\block_entities\EmcChestBlockEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */