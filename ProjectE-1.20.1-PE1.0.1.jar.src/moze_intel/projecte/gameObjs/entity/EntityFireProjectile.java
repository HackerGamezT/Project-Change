/*     */ package moze_intel.projecte.gameObjs.entity;
/*     */ 
/*     */ import moze_intel.projecte.gameObjs.items.ItemPE;
/*     */ import moze_intel.projecte.gameObjs.registries.PEEntityTypes;
/*     */ import moze_intel.projecte.gameObjs.registries.PEItems;
/*     */ import moze_intel.projecte.utils.PlayerHelper;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.protocol.Packet;
/*     */ import net.minecraft.network.protocol.game.ClientGamePacketListener;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.entity.projectile.ThrowableProjectile;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.phys.BlockHitResult;
/*     */ import net.minecraft.world.phys.EntityHitResult;
/*     */ import net.minecraft.world.phys.HitResult;
/*     */ import net.minecraftforge.network.NetworkHooks;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class EntityFireProjectile
/*     */   extends NoGravityThrowableProjectile {
/*     */   public EntityFireProjectile(EntityType<EntityFireProjectile> type, Level level) {
/*  31 */     super((EntityType)type, level);
/*     */   }
/*     */   private boolean fromArcana = false;
/*     */   public EntityFireProjectile(Player entity, boolean fromArcana, Level level) {
/*  35 */     super((EntityType<? extends ThrowableProjectile>)PEEntityTypes.FIRE_PROJECTILE.get(), (LivingEntity)entity, level);
/*  36 */     this.fromArcana = fromArcana;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_6532_(@NotNull HitResult result) {
/*  41 */     super.m_6532_(result);
/*  42 */     m_146870_();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_8060_(@NotNull BlockHitResult result) {
/*  47 */     super.m_8060_(result);
/*  48 */     if (!(m_9236_()).f_46443_) { Entity entity = m_19749_(); if (entity instanceof ServerPlayer) { ServerPlayer player = (ServerPlayer)entity;
/*  49 */         BlockPos pos = result.m_82425_();
/*  50 */         Block block = m_9236_().m_8055_(pos).m_60734_();
/*  51 */         if (block == Blocks.f_50080_) {
/*  52 */           m_9236_().m_46597_(pos, Blocks.f_49991_.m_49966_());
/*  53 */         } else if (block == Blocks.f_49992_) {
/*  54 */           BlockPos.m_121990_(pos.m_7918_(-2, -2, -2), pos.m_7918_(2, 2, 2)).forEach(currentPos -> {
/*     */                 if (m_9236_().m_8055_(currentPos).m_60734_() == Blocks.f_49992_) {
/*     */                   PlayerHelper.checkedPlaceBlock(player, pos.m_7949_(), Blocks.f_50058_.m_49966_());
/*     */                 }
/*     */               });
/*     */         } else {
/*  60 */           BlockPos.m_121990_(pos.m_7918_(-1, -1, -1), pos.m_7918_(1, 1, 1)).forEach(currentPos -> {
/*     */                 if (m_9236_().m_46859_(currentPos)) {
/*     */                   PlayerHelper.checkedPlaceBlock(player, currentPos.m_7949_(), Blocks.f_50083_.m_49966_());
/*     */                 }
/*     */               });
/*     */         }  }
/*     */        }
/*     */   
/*     */   }
/*     */   
/*     */   protected void m_5790_(@NotNull EntityHitResult result) {
/*  71 */     super.m_5790_(result);
/*  72 */     if (!(m_9236_()).f_46443_) { Entity entity = m_19749_(); if (entity instanceof Player) { Player player = (Player)entity;
/*  73 */         ItemStack found = PlayerHelper.findFirstItem(player, this.fromArcana ? (Item)PEItems.ARCANA_RING.get() : (Item)PEItems.IGNITION_RING.get());
/*  74 */         if (!found.m_41619_() && ItemPE.consumeFuel(player, found, 32L, true)) {
/*  75 */           Entity ent = result.m_82443_();
/*  76 */           ent.m_20254_(5);
/*  77 */           ent.m_6469_(m_9236_().m_269111_().m_269387_(), 5.0F);
/*     */         }  }
/*     */        }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_8097_() {}
/*     */ 
/*     */   
/*     */   public void m_7378_(@NotNull CompoundTag compound) {
/*  88 */     super.m_7378_(compound);
/*  89 */     this.fromArcana = compound.m_128471_("fromArcana");
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_7380_(@NotNull CompoundTag compound) {
/*  94 */     super.m_7380_(compound);
/*  95 */     compound.m_128379_("fromArcana", this.fromArcana);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Packet<ClientGamePacketListener> m_5654_() {
/* 101 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_6128_() {
/* 106 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\entity\EntityFireProjectile.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */