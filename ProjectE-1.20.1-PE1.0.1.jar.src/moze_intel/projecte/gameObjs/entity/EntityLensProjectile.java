/*    */ package moze_intel.projecte.gameObjs.entity;
/*    */ import moze_intel.projecte.gameObjs.registries.PEEntityTypes;
/*    */ import moze_intel.projecte.utils.Constants;
/*    */ import moze_intel.projecte.utils.WorldHelper;
/*    */ import net.minecraft.core.particles.ParticleOptions;
/*    */ import net.minecraft.core.particles.ParticleTypes;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.network.protocol.Packet;
/*    */ import net.minecraft.network.protocol.game.ClientGamePacketListener;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.sounds.SoundEvents;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.entity.projectile.ThrowableProjectile;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.gameevent.GameEvent;
/*    */ import net.minecraft.world.phys.HitResult;
/*    */ import net.minecraftforge.network.NetworkHooks;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class EntityLensProjectile extends NoGravityThrowableProjectile {
/*    */   public EntityLensProjectile(EntityType<EntityLensProjectile> type, Level level) {
/* 25 */     super((EntityType)type, level);
/*    */   }
/*    */   private int charge;
/*    */   public EntityLensProjectile(Player entity, int charge, Level level) {
/* 29 */     super((EntityType<? extends ThrowableProjectile>)PEEntityTypes.LENS_PROJECTILE.get(), (LivingEntity)entity, level);
/* 30 */     this.charge = charge;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void m_8097_() {}
/*    */ 
/*    */   
/*    */   public void m_8119_() {
/* 39 */     super.m_8119_();
/* 40 */     if (!(m_9236_()).f_46443_ && m_6084_() && m_20069_()) {
/* 41 */       m_5496_(SoundEvents.f_11909_, 0.7F, 1.6F + (this.f_19796_.m_188501_() - this.f_19796_.m_188501_()) * 0.4F);
/* 42 */       ((ServerLevel)m_9236_()).m_8767_((ParticleOptions)ParticleTypes.f_123755_, m_20185_(), m_20186_(), m_20189_(), 2, 0.0D, 0.0D, 0.0D, 0.0D);
/* 43 */       m_146870_();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void m_6532_(@NotNull HitResult result) {
/* 49 */     if (!(m_9236_()).f_46443_) {
/* 50 */       WorldHelper.createNovaExplosion(m_9236_(), m_19749_(), m_20185_(), m_20186_(), m_20189_(), Constants.EXPLOSIVE_LENS_RADIUS[this.charge]);
/*    */     }
/* 52 */     m_146852_(GameEvent.f_157777_, m_19749_());
/* 53 */     m_146870_();
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_7380_(@NotNull CompoundTag nbt) {
/* 58 */     super.m_7380_(nbt);
/* 59 */     nbt.m_128405_("Charge", this.charge);
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_7378_(@NotNull CompoundTag nbt) {
/* 64 */     super.m_7378_(nbt);
/* 65 */     this.charge = nbt.m_128451_("Charge");
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Packet<ClientGamePacketListener> m_5654_() {
/* 71 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_6128_() {
/* 76 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\entity\EntityLensProjectile.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */