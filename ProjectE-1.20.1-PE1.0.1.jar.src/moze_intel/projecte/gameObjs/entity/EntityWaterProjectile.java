/*     */ package moze_intel.projecte.gameObjs.entity;
/*     */ import moze_intel.projecte.utils.PlayerHelper;
/*     */ import moze_intel.projecte.utils.WorldHelper;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.network.protocol.Packet;
/*     */ import net.minecraft.network.protocol.game.ClientGamePacketListener;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.entity.projectile.ThrowableProjectile;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.LevelAccessor;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.material.Fluid;
/*     */ import net.minecraft.world.level.material.FluidState;
/*     */ import net.minecraft.world.level.material.Fluids;
/*     */ import net.minecraft.world.level.storage.LevelData;
/*     */ import net.minecraft.world.phys.BlockHitResult;
/*     */ import net.minecraft.world.phys.EntityHitResult;
/*     */ import net.minecraft.world.phys.HitResult;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.level.BlockEvent;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.network.NetworkHooks;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class EntityWaterProjectile extends NoGravityThrowableProjectile {
/*     */   public EntityWaterProjectile(EntityType<EntityWaterProjectile> type, Level level) {
/*  35 */     super((EntityType)type, level);
/*     */   }
/*     */   
/*     */   public EntityWaterProjectile(Player entity, Level level) {
/*  39 */     super((EntityType<? extends ThrowableProjectile>)PEEntityTypes.WATER_PROJECTILE.get(), (LivingEntity)entity, level);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_8097_() {}
/*     */ 
/*     */   
/*     */   public void m_8119_() {
/*  48 */     super.m_8119_();
/*  49 */     if (!(m_9236_()).f_46443_ && m_6084_()) {
/*  50 */       Entity thrower = m_19749_();
/*  51 */       if (thrower instanceof ServerPlayer) { ServerPlayer player = (ServerPlayer)thrower;
/*  52 */         BlockPos.m_121990_(m_20183_().m_7918_(-3, -3, -3), m_20183_().m_7918_(3, 3, 3)).forEach(pos -> {
/*     */               BlockState state = m_9236_().m_8055_(pos);
/*     */               
/*     */               FluidState fluidState = state.m_60819_();
/*     */               
/*     */               if (fluidState.m_205070_(FluidTags.f_13132_)) {
/*     */                 pos = pos.m_7949_();
/*     */                 
/*     */                 if (state.m_60734_() instanceof net.minecraft.world.level.block.LiquidBlock) {
/*     */                   Block block = fluidState.m_76170_() ? Blocks.f_50080_ : Blocks.f_50652_;
/*     */                   BlockEvent.FluidPlaceBlockEvent event = new BlockEvent.FluidPlaceBlockEvent((LevelAccessor)m_9236_(), pos, pos, block.m_49966_());
/*     */                   if (!MinecraftForge.EVENT_BUS.post((Event)event)) {
/*     */                     PlayerHelper.checkedPlaceBlock(player, pos, event.getNewState());
/*     */                   }
/*     */                 } else {
/*     */                   WorldHelper.drainFluid(m_9236_(), pos, state, (Fluid)Fluids.f_76195_);
/*     */                 } 
/*     */                 m_5496_(SoundEvents.f_11909_, 0.5F, 2.6F + ((m_9236_()).f_46441_.m_188501_() - (m_9236_()).f_46441_.m_188501_()) * 0.8F);
/*     */               } 
/*     */             }); }
/*     */       
/*  73 */       if (m_20069_()) {
/*  74 */         m_146870_();
/*     */       }
/*  76 */       if (m_20186_() > 128.0D) {
/*  77 */         LevelData worldInfo = m_9236_().m_6106_();
/*  78 */         worldInfo.m_5565_(true);
/*  79 */         m_146870_();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_6532_(@NotNull HitResult result) {
/*  86 */     super.m_6532_(result);
/*  87 */     m_146870_();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_8060_(@NotNull BlockHitResult result) {
/*  92 */     super.m_8060_(result);
/*  93 */     if (!(m_9236_()).f_46443_) { Entity entity = m_19749_(); if (entity instanceof ServerPlayer) { ServerPlayer player = (ServerPlayer)entity;
/*  94 */         WorldHelper.placeFluid(player, m_9236_(), result.m_82425_(), result.m_82434_(), Fluids.f_76193_, !ProjectEConfig.server.items.opEvertide.get()); }
/*     */        }
/*     */   
/*     */   }
/*     */   
/*     */   protected void m_5790_(@NotNull EntityHitResult result) {
/* 100 */     super.m_5790_(result);
/* 101 */     if (!(m_9236_()).f_46443_) { Entity entity = m_19749_(); if (entity instanceof Player) { Player player = (Player)entity;
/* 102 */         Entity ent = result.m_82443_();
/* 103 */         if (ent.m_6060_()) {
/* 104 */           ent.m_20095_();
/*     */         }
/* 106 */         ent.m_5997_(m_20184_().m_7096_() * 2.0D, m_20184_().m_7098_() * 2.0D, m_20184_().m_7094_() * 2.0D); }
/*     */        }
/*     */   
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Packet<ClientGamePacketListener> m_5654_() {
/* 113 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_6128_() {
/* 118 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\entity\EntityWaterProjectile.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */