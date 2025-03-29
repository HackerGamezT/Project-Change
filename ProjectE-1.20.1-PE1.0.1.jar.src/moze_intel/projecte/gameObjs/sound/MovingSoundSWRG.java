/*    */ package moze_intel.projecte.gameObjs.sound;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.entity.EntitySWRGProjectile;
/*    */ import moze_intel.projecte.gameObjs.registries.PESoundEvents;
/*    */ import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraft.sounds.SoundSource;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.util.RandomSource;
/*    */ 
/*    */ public class MovingSoundSWRG
/*    */   extends AbstractTickableSoundInstance
/*    */ {
/*    */   private final EntitySWRGProjectile swrgProjectile;
/* 15 */   private float distance = 0.0F;
/*    */   
/*    */   public MovingSoundSWRG(EntitySWRGProjectile swrgProjectile, RandomSource random) {
/* 18 */     super((SoundEvent)PESoundEvents.WIND_MAGIC.get(), SoundSource.WEATHER, random);
/* 19 */     this.swrgProjectile = swrgProjectile;
/* 20 */     this.f_119575_ = this.swrgProjectile.m_20185_();
/* 21 */     this.f_119576_ = this.swrgProjectile.m_20186_();
/* 22 */     this.f_119577_ = this.swrgProjectile.m_20189_();
/* 23 */     this.f_119573_ = 0.6F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_7788_() {
/* 28 */     if (this.swrgProjectile.m_213877_()) {
/* 29 */       m_119609_();
/*    */     } else {
/* 31 */       this.f_119575_ = this.swrgProjectile.m_20185_();
/* 32 */       this.f_119576_ = this.swrgProjectile.m_20186_();
/* 33 */       this.f_119577_ = this.swrgProjectile.m_20189_();
/* 34 */       float f = (float)this.swrgProjectile.m_20184_().m_165924_();
/*    */       
/* 36 */       if (f >= 0.01F) {
/* 37 */         this.distance = Mth.m_14036_(this.distance + 0.0025F, 0.0F, 1.0F);
/* 38 */         this.f_119573_ = Mth.m_14179_(Mth.m_14036_(f, 0.0F, 0.5F), 0.0F, 0.7F);
/*    */       } else {
/* 40 */         this.distance = 0.0F;
/* 41 */         this.f_119573_ = 0.0F;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\sound\MovingSoundSWRG.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */