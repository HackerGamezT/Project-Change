/*    */ package moze_intel.projecte.gameObjs.blocks;
/*    */ 
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.core.BlockSource;
/*    */ import net.minecraft.core.Direction;
/*    */ import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
/*    */ import net.minecraft.core.dispenser.DispenseItemBehavior;
/*    */ import net.minecraft.sounds.SoundEvents;
/*    */ import net.minecraft.sounds.SoundSource;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.item.PrimedTnt;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.BlockGetter;
/*    */ import net.minecraft.world.level.Explosion;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.DispenserBlock;
/*    */ import net.minecraft.world.level.block.TntBlock;
/*    */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.level.block.state.properties.Property;
/*    */ import net.minecraft.world.level.gameevent.GameEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class ProjectETNT extends TntBlock {
/*    */   public ProjectETNT(BlockBehaviour.Properties properties, TNTEntityCreator tntEntityCreator) {
/* 28 */     super(properties);
/* 29 */     this.tntEntityCreator = tntEntityCreator;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
/* 34 */     return 100;
/*    */   }
/*    */   private final TNTEntityCreator tntEntityCreator;
/*    */   
/*    */   public void onCaughtFire(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @Nullable Direction side, @Nullable LivingEntity igniter) {
/* 39 */     if (!level.f_46443_) {
/* 40 */       createAndAddEntity(level, pos, igniter);
/* 41 */       level.m_142346_((Entity)igniter, GameEvent.f_157776_, pos);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void createAndAddEntity(@NotNull Level level, @NotNull BlockPos pos, @Nullable LivingEntity igniter) {
/* 46 */     PrimedTnt tnt = this.tntEntityCreator.create(level, (pos.m_123341_() + 0.5F), pos.m_123342_(), (pos.m_123343_() + 0.5F), igniter);
/* 47 */     level.m_7967_((Entity)tnt);
/* 48 */     level.m_6263_(null, tnt.m_20185_(), tnt.m_20186_(), tnt.m_20189_(), SoundEvents.f_12512_, SoundSource.BLOCKS, 1.0F, 1.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public DispenseItemBehavior createDispenseItemBehavior() {
/* 53 */     return (DispenseItemBehavior)new DefaultDispenseItemBehavior()
/*    */       {
/*    */         @NotNull
/*    */         protected ItemStack m_7498_(@NotNull BlockSource source, @NotNull ItemStack stack) {
/* 57 */           BlockPos blockpos = source.m_7961_().m_121945_((Direction)source.m_6414_().m_61143_((Property)DispenserBlock.f_52659_));
/* 58 */           ProjectETNT.this.createAndAddEntity((Level)source.m_7727_(), blockpos, null);
/* 59 */           source.m_7727_().m_142346_(null, GameEvent.f_157810_, blockpos);
/* 60 */           stack.m_41774_(1);
/* 61 */           return stack;
/*    */         }
/*    */       };
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_7592_(Level level, @NotNull BlockPos pos, @NotNull Explosion explosion) {
/* 68 */     if (!level.f_46443_) {
/* 69 */       PrimedTnt tnt = this.tntEntityCreator.create(level, (pos.m_123341_() + 0.5F), pos.m_123342_(), (pos.m_123343_() + 0.5F), explosion.m_252906_());
/* 70 */       int fuse = tnt.m_32100_();
/* 71 */       tnt.m_32085_((short)(level.f_46441_.m_188503_(fuse / 4) + fuse / 8));
/* 72 */       level.m_7967_((Entity)tnt);
/*    */     } 
/*    */   }
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface TNTEntityCreator {
/*    */     PrimedTnt create(Level param1Level, double param1Double1, double param1Double2, double param1Double3, @Nullable LivingEntity param1LivingEntity);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\blocks\ProjectETNT.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */