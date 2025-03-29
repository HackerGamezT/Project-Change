/*     */ package moze_intel.projecte.gameObjs.entity;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import moze_intel.projecte.gameObjs.registries.PEEntityTypes;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.core.particles.ParticleTypes;
/*     */ import net.minecraft.network.protocol.Packet;
/*     */ import net.minecraft.network.protocol.game.ClientGamePacketListener;
/*     */ import net.minecraft.network.syncher.EntityDataAccessor;
/*     */ import net.minecraft.network.syncher.EntityDataSerializers;
/*     */ import net.minecraft.network.syncher.SynchedEntityData;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.Mob;
/*     */ import net.minecraft.world.entity.projectile.AbstractArrow;
/*     */ import net.minecraft.world.entity.projectile.Arrow;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.Items;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.network.NetworkHooks;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class EntityHomingArrow extends Arrow {
/*  26 */   private static final EntityDataAccessor<Integer> DW_TARGET_ID = SynchedEntityData.m_135353_(EntityHomingArrow.class, EntityDataSerializers.f_135028_);
/*     */   
/*     */   private static final int NO_TARGET = -1;
/*  29 */   private int newTargetCooldown = 0;
/*     */   
/*     */   public EntityHomingArrow(EntityType<EntityHomingArrow> type, Level level) {
/*  32 */     super(type, level);
/*     */   }
/*     */   
/*     */   public EntityHomingArrow(Level level, LivingEntity shooter, float damage) {
/*  36 */     super(level, shooter);
/*  37 */     m_36781_(damage);
/*  38 */     this.f_36705_ = AbstractArrow.Pickup.CREATIVE_ONLY;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public EntityType<?> m_6095_() {
/*  44 */     return (EntityType)PEEntityTypes.HOMING_ARROW.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_8097_() {
/*  49 */     super.m_8097_();
/*  50 */     this.f_19804_.m_135372_(DW_TARGET_ID, Integer.valueOf(-1));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_7761_(@NotNull LivingEntity living) {
/*  55 */     super.m_7761_(living);
/*     */     
/*  57 */     living.f_19802_ = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_8119_() {
/*  62 */     if (!(m_9236_()).f_46443_ && this.f_19797_ > 3) {
/*  63 */       if (hasTarget() && (!getTarget().m_6084_() || this.f_36703_)) {
/*  64 */         this.f_19804_.m_135381_(DW_TARGET_ID, Integer.valueOf(-1));
/*     */       }
/*     */       
/*  67 */       if (!hasTarget() && !this.f_36703_ && this.newTargetCooldown <= 0) {
/*  68 */         findNewTarget();
/*     */       } else {
/*  70 */         this.newTargetCooldown--;
/*     */       } 
/*     */     } 
/*     */     
/*  74 */     if (this.f_19797_ > 3 && hasTarget() && !this.f_36703_) {
/*  75 */       double mX = m_20184_().m_7096_();
/*  76 */       double mY = m_20184_().m_7098_();
/*  77 */       double mZ = m_20184_().m_7094_();
/*  78 */       m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123744_, m_20185_() + mX / 4.0D, m_20186_() + mY / 4.0D, m_20189_() + mZ / 4.0D, -mX / 2.0D, -mY / 2.0D + 0.2D, -mZ / 2.0D);
/*  79 */       m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123744_, m_20185_() + mX / 4.0D, m_20186_() + mY / 4.0D, m_20189_() + mZ / 4.0D, -mX / 2.0D, -mY / 2.0D + 0.2D, -mZ / 2.0D);
/*  80 */       Mob mob = getTarget();
/*     */       
/*  82 */       Vec3 arrowLoc = new Vec3(m_20185_(), m_20186_(), m_20189_());
/*  83 */       Vec3 targetLoc = new Vec3(mob.m_20185_(), mob.m_20186_() + (mob.m_20206_() / 2.0F), mob.m_20189_());
/*     */ 
/*     */       
/*  86 */       Vec3 lookVec = targetLoc.m_82546_(arrowLoc);
/*     */       
/*  88 */       Vec3 arrowMotion = new Vec3(mX, mY, mZ);
/*     */ 
/*     */       
/*  91 */       double theta = wrap180Radian(angleBetween(arrowMotion, lookVec));
/*  92 */       theta = clampAbs(theta, 1.5707963267948966D);
/*     */ 
/*     */       
/*  95 */       Vec3 crossProduct = arrowMotion.m_82537_(lookVec).m_82541_();
/*     */ 
/*     */       
/*  98 */       Vec3 adjustedLookVec = transform(crossProduct, theta, arrowMotion);
/*     */ 
/*     */       
/* 101 */       m_6686_(adjustedLookVec.f_82479_, adjustedLookVec.f_82480_, adjustedLookVec.f_82481_, 1.0F, 0.0F);
/*     */     } 
/*     */     
/* 104 */     super.m_8119_();
/*     */   }
/*     */ 
/*     */   
/*     */   private Vec3 transform(Vec3 axis, double angle, Vec3 normal) {
/* 109 */     double m00 = 1.0D;
/* 110 */     double m01 = 0.0D;
/* 111 */     double m02 = 0.0D;
/*     */     
/* 113 */     double m10 = 0.0D;
/* 114 */     double m11 = 1.0D;
/* 115 */     double m12 = 0.0D;
/*     */     
/* 117 */     double m20 = 0.0D;
/* 118 */     double m21 = 0.0D;
/* 119 */     double m22 = 1.0D;
/* 120 */     double mag = Math.sqrt(axis.f_82479_ * axis.f_82479_ + axis.f_82480_ * axis.f_82480_ + axis.f_82481_ * axis.f_82481_);
/* 121 */     if (mag >= 1.0E-10D) {
/* 122 */       mag = 1.0D / mag;
/* 123 */       double ax = axis.f_82479_ * mag;
/* 124 */       double ay = axis.f_82480_ * mag;
/* 125 */       double az = axis.f_82481_ * mag;
/*     */       
/* 127 */       double sinTheta = Math.sin(angle);
/* 128 */       double cosTheta = Math.cos(angle);
/* 129 */       double t = 1.0D - cosTheta;
/*     */       
/* 131 */       double xz = ax * az;
/* 132 */       double xy = ax * ay;
/* 133 */       double yz = ay * az;
/*     */       
/* 135 */       m00 = t * ax * ax + cosTheta;
/* 136 */       m01 = t * xy - sinTheta * az;
/* 137 */       m02 = t * xz + sinTheta * ay;
/*     */       
/* 139 */       m10 = t * xy + sinTheta * az;
/* 140 */       m11 = t * ay * ay + cosTheta;
/* 141 */       m12 = t * yz - sinTheta * ax;
/*     */       
/* 143 */       m20 = t * xz - sinTheta * ay;
/* 144 */       m21 = t * yz + sinTheta * ax;
/* 145 */       m22 = t * az * az + cosTheta;
/*     */     } 
/* 147 */     return new Vec3(m00 * normal.f_82479_ + m01 * normal.f_82480_ + m02 * normal.f_82481_, m10 * normal.f_82479_ + m11 * normal.f_82480_ + m12 * normal.f_82481_, m20 * normal.f_82479_ + m21 * normal.f_82480_ + m22 * normal.f_82481_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   protected ItemStack m_7941_() {
/* 155 */     return new ItemStack((ItemLike)Items.f_42412_);
/*     */   }
/*     */   
/*     */   private void findNewTarget() {
/* 159 */     List<Mob> candidates = m_9236_().m_45976_(Mob.class, m_20191_().m_82377_(8.0D, 8.0D, 8.0D));
/*     */     
/* 161 */     if (!candidates.isEmpty()) {
/* 162 */       candidates.sort(Comparator.comparing(this::m_20280_, Double::compare));
/* 163 */       this.f_19804_.m_135381_(DW_TARGET_ID, Integer.valueOf(((Mob)candidates.get(0)).m_19879_()));
/*     */     } 
/*     */     
/* 166 */     this.newTargetCooldown = 5;
/*     */   }
/*     */   
/*     */   private Mob getTarget() {
/* 170 */     return (Mob)m_9236_().m_6815_(((Integer)this.f_19804_.m_135370_(DW_TARGET_ID)).intValue());
/*     */   }
/*     */   
/*     */   private boolean hasTarget() {
/* 174 */     return (getTarget() != null);
/*     */   }
/*     */   
/*     */   private double angleBetween(Vec3 v1, Vec3 v2) {
/* 178 */     double vDot = v1.m_82526_(v2) / v1.m_82553_() * v2.m_82553_();
/* 179 */     if (vDot < -1.0D) {
/* 180 */       vDot = -1.0D;
/*     */     }
/* 182 */     if (vDot > 1.0D) {
/* 183 */       vDot = 1.0D;
/*     */     }
/* 185 */     return Math.acos(vDot);
/*     */   }
/*     */   
/*     */   private double wrap180Radian(double radian) {
/* 189 */     radian %= 6.283185307179586D;
/* 190 */     while (radian >= Math.PI) {
/* 191 */       radian -= 6.283185307179586D;
/*     */     }
/* 193 */     while (radian < -3.141592653589793D) {
/* 194 */       radian += 6.283185307179586D;
/*     */     }
/* 196 */     return radian;
/*     */   }
/*     */   
/*     */   private double clampAbs(double param, double maxMagnitude) {
/* 200 */     if (Math.abs(param) > maxMagnitude)
/*     */     {
/* 202 */       if (param < 0.0D) {
/* 203 */         param = -Math.abs(maxMagnitude);
/*     */       } else {
/* 205 */         param = Math.abs(maxMagnitude);
/*     */       } 
/*     */     }
/* 208 */     return param;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Packet<ClientGamePacketListener> m_5654_() {
/* 214 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_6128_() {
/* 219 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\entity\EntityHomingArrow.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */