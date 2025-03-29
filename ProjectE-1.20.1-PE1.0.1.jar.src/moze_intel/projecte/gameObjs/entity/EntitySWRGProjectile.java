/*     */ package moze_intel.projecte.gameObjs.entity;
/*     */ import moze_intel.projecte.gameObjs.items.ItemPE;
/*     */ import moze_intel.projecte.gameObjs.registries.PEEntityTypes;
/*     */ import moze_intel.projecte.gameObjs.registries.PEItems;
/*     */ import moze_intel.projecte.utils.PlayerHelper;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Vec3i;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.protocol.Packet;
/*     */ import net.minecraft.network.protocol.game.ClientGamePacketListener;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LightningBolt;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.entity.projectile.ThrowableProjectile;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.storage.LevelData;
/*     */ import net.minecraft.world.level.storage.ServerLevelData;
/*     */ import net.minecraft.world.phys.BlockHitResult;
/*     */ import net.minecraft.world.phys.EntityHitResult;
/*     */ import net.minecraft.world.phys.HitResult;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.network.NetworkHooks;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class EntitySWRGProjectile extends NoGravityThrowableProjectile {
/*     */   public EntitySWRGProjectile(EntityType<EntitySWRGProjectile> type, Level level) {
/*  32 */     super((EntityType)type, level);
/*     */   }
/*     */   private boolean fromArcana = false;
/*     */   public EntitySWRGProjectile(Player player, boolean fromArcana, Level level) {
/*  36 */     super((EntityType<? extends ThrowableProjectile>)PEEntityTypes.SWRG_PROJECTILE.get(), (LivingEntity)player, level);
/*  37 */     this.fromArcana = fromArcana;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_8097_() {}
/*     */ 
/*     */   
/*     */   public void m_8119_() {
/*  46 */     super.m_8119_();
/*  47 */     if (m_6084_()) {
/*     */       
/*  49 */       double inverse = 1.0D / (m_20069_() ? 0.8D : 0.99D);
/*  50 */       m_20256_(m_20184_().m_82490_(inverse));
/*  51 */       if (!(m_9236_()).f_46443_ && m_6084_() && m_20186_() > m_9236_().m_151558_() && m_9236_().m_46471_()) {
/*  52 */         LevelData levelData = m_9236_().m_6106_(); if (levelData instanceof ServerLevelData) { ServerLevelData serverLevelData = (ServerLevelData)levelData;
/*  53 */           serverLevelData.m_5557_(true); }
/*     */         
/*  55 */         m_146870_();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_6532_(@NotNull HitResult result) {
/*  62 */     super.m_6532_(result);
/*  63 */     m_146870_();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_8060_(@NotNull BlockHitResult result) {
/*  68 */     super.m_8060_(result);
/*  69 */     if (!(m_9236_()).f_46443_) { Entity entity = m_19749_(); if (entity instanceof ServerPlayer) { ServerPlayer player = (ServerPlayer)entity;
/*  70 */         ItemStack found = PlayerHelper.findFirstItem((Player)player, this.fromArcana ? (Item)PEItems.ARCANA_RING.get() : (Item)PEItems.SWIFTWOLF_RENDING_GALE.get());
/*  71 */         if (!found.m_41619_() && ItemPE.consumeFuel((Player)player, found, 768L, true)) {
/*  72 */           BlockPos pos = result.m_82425_();
/*  73 */           LightningBolt lightning = (LightningBolt)EntityType.f_20465_.m_20615_(m_9236_());
/*  74 */           if (lightning != null) {
/*  75 */             lightning.m_20219_(Vec3.m_82512_((Vec3i)pos));
/*  76 */             lightning.m_20879_(player);
/*  77 */             m_9236_().m_7967_((Entity)lightning);
/*     */           } 
/*  79 */           if (m_9236_().m_46470_()) {
/*  80 */             for (int i = 0; i < 3; i++) {
/*  81 */               LightningBolt bonus = (LightningBolt)EntityType.f_20465_.m_20615_(m_9236_());
/*  82 */               if (bonus != null) {
/*  83 */                 bonus.m_6027_(pos.m_123341_() + 0.5D + (m_9236_()).f_46441_.m_188583_(), pos.m_123342_() + 0.5D + (m_9236_()).f_46441_.m_188583_(), pos
/*  84 */                     .m_123343_() + 0.5D + (m_9236_()).f_46441_.m_188583_());
/*  85 */                 bonus.m_20879_(player);
/*  86 */                 m_9236_().m_7967_((Entity)bonus);
/*     */               } 
/*     */             } 
/*     */           }
/*     */         }  }
/*     */        }
/*     */   
/*     */   }
/*     */   
/*     */   protected void m_5790_(@NotNull EntityHitResult result) {
/*  96 */     super.m_5790_(result);
/*  97 */     if (!(m_9236_()).f_46443_) { Entity entity = result.m_82443_(); if (entity instanceof LivingEntity) { LivingEntity e = (LivingEntity)entity; entity = m_19749_(); if (entity instanceof Player) { Player player = (Player)entity;
/*  98 */           ItemStack found = PlayerHelper.findFirstItem(player, this.fromArcana ? (Item)PEItems.ARCANA_RING.get() : (Item)PEItems.SWIFTWOLF_RENDING_GALE.get());
/*  99 */           if (!found.m_41619_() && ItemPE.consumeFuel(player, found, 64L, true)) {
/*     */             
/* 101 */             e.m_6469_(m_9236_().m_269111_().m_269075_(player), 1.0F);
/*     */ 
/*     */             
/* 104 */             boolean oldOnGround = e.m_20096_();
/* 105 */             e.m_6853_(true);
/* 106 */             e.m_147240_(5.0D, -m_20184_().m_7096_() * 0.25D, -m_20184_().m_7094_() * 0.25D);
/* 107 */             e.m_6853_(oldOnGround);
/* 108 */             e.m_20256_(e.m_20184_().m_82542_(1.0D, 3.0D, 1.0D));
/*     */           }  }
/*     */          }
/*     */        }
/*     */   
/*     */   }
/*     */   public void m_7378_(@NotNull CompoundTag compound) {
/* 115 */     super.m_7378_(compound);
/* 116 */     this.fromArcana = compound.m_128471_("fromArcana");
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_7380_(@NotNull CompoundTag compound) {
/* 121 */     super.m_7380_(compound);
/* 122 */     compound.m_128379_("fromArcana", this.fromArcana);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Packet<ClientGamePacketListener> m_5654_() {
/* 128 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_6128_() {
/* 133 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\entity\EntitySWRGProjectile.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */