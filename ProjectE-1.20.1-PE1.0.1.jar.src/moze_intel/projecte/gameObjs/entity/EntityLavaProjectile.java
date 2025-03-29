/*     */ package moze_intel.projecte.gameObjs.entity;
/*     */ import moze_intel.projecte.gameObjs.items.ItemPE;
/*     */ import moze_intel.projecte.gameObjs.registries.PEItems;
/*     */ import moze_intel.projecte.utils.PlayerHelper;
/*     */ import moze_intel.projecte.utils.WorldHelper;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.network.protocol.Packet;
/*     */ import net.minecraft.network.protocol.game.ClientGamePacketListener;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.entity.projectile.ThrowableProjectile;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.material.Fluid;
/*     */ import net.minecraft.world.level.material.Fluids;
/*     */ import net.minecraft.world.level.storage.LevelData;
/*     */ import net.minecraft.world.phys.BlockHitResult;
/*     */ import net.minecraft.world.phys.EntityHitResult;
/*     */ import net.minecraft.world.phys.HitResult;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class EntityLavaProjectile extends NoGravityThrowableProjectile {
/*     */   public EntityLavaProjectile(EntityType<EntityLavaProjectile> type, Level level) {
/*  32 */     super((EntityType)type, level);
/*     */   }
/*     */   
/*     */   public EntityLavaProjectile(Player entity, Level level) {
/*  36 */     super((EntityType<? extends ThrowableProjectile>)PEEntityTypes.LAVA_PROJECTILE.get(), (LivingEntity)entity, level);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_8097_() {}
/*     */ 
/*     */   
/*     */   public void m_8119_() {
/*  45 */     super.m_8119_();
/*  46 */     if (!(m_9236_()).f_46443_ && m_6084_()) {
/*  47 */       Entity thrower = m_19749_();
/*  48 */       if (thrower instanceof ServerPlayer) { ServerPlayer player = (ServerPlayer)thrower;
/*  49 */         BlockPos.m_121990_(m_20183_().m_7918_(-3, -3, -3), m_20183_().m_7918_(3, 3, 3)).forEach(pos -> {
/*     */               if (m_9236_().m_46749_(pos)) {
/*     */                 BlockState state = m_9236_().m_8055_(pos);
/*     */                 
/*     */                 if (state.m_60819_().m_205070_(FluidTags.f_13131_)) {
/*     */                   pos = pos.m_7949_();
/*     */                   if (PlayerHelper.hasEditPermission(player, pos)) {
/*     */                     WorldHelper.drainFluid(m_9236_(), pos, state, (Fluid)Fluids.f_76193_);
/*     */                     m_9236_().m_5594_(null, pos, SoundEvents.f_11937_, SoundSource.BLOCKS, 0.5F, 2.6F + ((m_9236_()).f_46441_.m_188501_() - (m_9236_()).f_46441_.m_188501_()) * 0.8F);
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             }); }
/*     */       
/*  63 */       if (m_20186_() > 128.0D) {
/*  64 */         LevelData worldInfo = m_9236_().m_6106_();
/*  65 */         worldInfo.m_5565_(false);
/*  66 */         m_146870_();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_6532_(@NotNull HitResult result) {
/*  73 */     super.m_6532_(result);
/*  74 */     m_146870_();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_8060_(@NotNull BlockHitResult result) {
/*  79 */     super.m_8060_(result);
/*  80 */     if (!(m_9236_()).f_46443_) { Entity entity = m_19749_(); if (entity instanceof ServerPlayer) { ServerPlayer player = (ServerPlayer)entity;
/*  81 */         ItemStack found = PlayerHelper.findFirstItem((Player)player, (Item)PEItems.VOLCANITE_AMULET.get());
/*  82 */         if (!found.m_41619_() && ItemPE.consumeFuel((Player)player, found, 32L, true)) {
/*  83 */           WorldHelper.placeFluid(player, m_9236_(), result.m_82425_(), result.m_82434_(), Fluids.f_76195_, false);
/*     */         } }
/*     */        }
/*     */   
/*     */   }
/*     */   
/*     */   protected void m_5790_(@NotNull EntityHitResult result) {
/*  90 */     super.m_5790_(result);
/*  91 */     if (!(m_9236_()).f_46443_) { Entity entity = m_19749_(); if (entity instanceof Player) { Player player = (Player)entity;
/*  92 */         ItemStack found = PlayerHelper.findFirstItem(player, (Item)PEItems.VOLCANITE_AMULET.get());
/*  93 */         if (!found.m_41619_() && ItemPE.consumeFuel(player, found, 32L, true)) {
/*  94 */           Entity ent = result.m_82443_();
/*  95 */           ent.m_20254_(5);
/*  96 */           ent.m_6469_(m_9236_().m_269111_().m_269387_(), 5.0F);
/*     */         }  }
/*     */        }
/*     */   
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Packet<ClientGamePacketListener> m_5654_() {
/* 104 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_6128_() {
/* 109 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\entity\EntityLavaProjectile.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */