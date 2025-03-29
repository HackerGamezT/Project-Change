/*    */ package moze_intel.projecte.gameObjs.entity;
/*    */ 
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.projectile.ThrowableProjectile;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ 
/*    */ public abstract class NoGravityThrowableProjectile
/*    */   extends ThrowableProjectile {
/*    */   protected NoGravityThrowableProjectile(EntityType<? extends ThrowableProjectile> type, Level level) {
/* 12 */     super(type, level);
/*    */   }
/*    */   
/*    */   protected NoGravityThrowableProjectile(EntityType<? extends ThrowableProjectile> type, LivingEntity shooter, Level level) {
/* 16 */     super(type, shooter, level);
/*    */   }
/*    */ 
/*    */   
/*    */   public float m_7139_() {
/* 21 */     return 0.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_8119_() {
/* 26 */     super.m_8119_();
/* 27 */     if (!(m_9236_()).f_46443_ && (
/* 28 */       this.f_19797_ > 400 || m_20184_().equals(Vec3.f_82478_) || !m_9236_().m_46749_(m_20183_())))
/* 29 */       m_146870_(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\entity\NoGravityThrowableProjectile.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */