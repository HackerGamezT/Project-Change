/*     */ package moze_intel.projecte.utils;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectListIterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.Util;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.NonNullList;
/*     */ import net.minecraft.core.Vec3i;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.core.particles.ParticleTypes;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.Explosion;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.storage.loot.LootParams;
/*     */ import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class NovaExplosion
/*     */   extends Explosion {
/*     */   private final Level level;
/*     */   
/*     */   public NovaExplosion(Level level, @Nullable Entity entity, double x, double y, double z, float radius, boolean causesFire, Explosion.BlockInteraction mode) {
/*  33 */     super(level, entity, null, null, x, y, z, radius, causesFire, mode);
/*  34 */     this.level = level;
/*  35 */     this.mode = mode;
/*  36 */     this.size = radius;
/*  37 */     this.x = x;
/*  38 */     this.y = y;
/*  39 */     this.z = z;
/*     */   }
/*     */   private final Explosion.BlockInteraction mode; private final double x;
/*     */   private final double y;
/*     */   
/*     */   public void m_46075_(boolean spawnParticles) {
/*  45 */     if (this.level.f_46443_) {
/*  46 */       this.level.m_7785_(this.x, this.y, this.z, SoundEvents.f_11913_, SoundSource.BLOCKS, 4.0F, (1.0F + (this.level.f_46441_.m_188501_() - this.level.f_46441_.m_188501_()) * 0.2F) * 0.7F, false);
/*     */     }
/*  48 */     boolean hasExplosionMode = (this.mode != Explosion.BlockInteraction.KEEP);
/*  49 */     if (spawnParticles) {
/*  50 */       if (hasExplosionMode && this.size >= 2.0F) {
/*  51 */         this.level.m_7106_((ParticleOptions)ParticleTypes.f_123812_, this.x, this.y, this.z, 1.0D, 0.0D, 0.0D);
/*     */       } else {
/*  53 */         this.level.m_7106_((ParticleOptions)ParticleTypes.f_123813_, this.x, this.y, this.z, 1.0D, 0.0D, 0.0D);
/*     */       } 
/*     */     }
/*  56 */     if (hasExplosionMode) {
/*  57 */       NonNullList<ItemStack> allDrops = NonNullList.m_122779_();
/*  58 */       List<BlockPos> toBlow = m_46081_();
/*  59 */       ObjectArrayList<BlockPos> to = (ObjectArrayList<BlockPos>)toBlow, affectedBlockPositions = (toBlow instanceof ObjectArrayList) ? to : new ObjectArrayList(toBlow);
/*  60 */       Util.m_214673_(affectedBlockPositions, this.level.f_46441_);
/*  61 */       for (ObjectListIterator<BlockPos> objectListIterator = affectedBlockPositions.iterator(); objectListIterator.hasNext(); ) { BlockPos pos = objectListIterator.next();
/*  62 */         BlockState state = this.level.m_8055_(pos);
/*  63 */         if (!state.m_60795_()) {
/*  64 */           if (spawnParticles) {
/*  65 */             double adjustedX = (pos.m_123341_() + this.level.f_46441_.m_188501_());
/*  66 */             double adjustedY = (pos.m_123342_() + this.level.f_46441_.m_188501_());
/*  67 */             double adjustedZ = (pos.m_123343_() + this.level.f_46441_.m_188501_());
/*  68 */             double diffX = adjustedX - this.x;
/*  69 */             double diffY = adjustedY - this.y;
/*  70 */             double diffZ = adjustedZ - this.z;
/*  71 */             double diff = Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
/*  72 */             diffX /= diff;
/*  73 */             diffY /= diff;
/*  74 */             diffZ /= diff;
/*  75 */             double d7 = 0.5D / (diff / this.size + 0.1D);
/*  76 */             d7 *= (this.level.f_46441_.m_188501_() * this.level.f_46441_.m_188501_() + 0.3F);
/*  77 */             diffX *= d7;
/*  78 */             diffY *= d7;
/*  79 */             diffZ *= d7;
/*  80 */             this.level.m_7106_((ParticleOptions)ParticleTypes.f_123759_, (adjustedX + this.x) / 2.0D, (adjustedY + this.y) / 2.0D, (adjustedZ + this.z) / 2.0D, diffX, diffY, diffZ);
/*  81 */             this.level.m_7106_((ParticleOptions)ParticleTypes.f_123762_, adjustedX, adjustedY, adjustedZ, diffX, diffY, diffZ);
/*     */           } 
/*     */           
/*  84 */           pos = pos.m_7949_();
/*  85 */           this.level.m_46473_().m_6180_("explosion_blocks");
/*  86 */           Level level = this.level; if (level instanceof ServerLevel) { ServerLevel serverLevel = (ServerLevel)level; if (state.canDropFromExplosion((BlockGetter)this.level, pos, this)) {
/*  87 */               BlockEntity blockEntity = state.m_155947_() ? WorldHelper.getBlockEntity((BlockGetter)serverLevel, pos) : null;
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*  92 */               LootParams.Builder builder = (new LootParams.Builder(serverLevel)).m_287286_(LootContextParams.f_81460_, Vec3.m_82512_((Vec3i)pos)).m_287286_(LootContextParams.f_81463_, ItemStack.f_41583_).m_287289_(LootContextParams.f_81462_, blockEntity).m_287289_(LootContextParams.f_81455_, getExploder());
/*  93 */               if (this.mode == Explosion.BlockInteraction.DESTROY_WITH_DECAY) {
/*  94 */                 builder.m_287286_(LootContextParams.f_81464_, Float.valueOf(this.size));
/*     */               }
/*     */ 
/*     */               
/*  98 */               allDrops.addAll(state.m_287290_(builder));
/*     */             }  }
/* 100 */            state.onBlockExploded(this.level, pos, this);
/* 101 */           this.level.m_46473_().m_7238_();
/*     */         }  }
/*     */ 
/*     */ 
/*     */       
/* 106 */       LivingEntity placer = m_252906_();
/* 107 */       if (placer == null) {
/* 108 */         WorldHelper.createLootDrop((List<ItemStack>)allDrops, this.level, this.x, this.y, this.z);
/*     */       } else {
/* 110 */         WorldHelper.createLootDrop((List<ItemStack>)allDrops, this.level, placer.m_20183_());
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private final double z;
/*     */   private final float size;
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\project\\utils\NovaExplosion.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */