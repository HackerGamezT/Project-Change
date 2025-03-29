/*    */ package moze_intel.projecte.gameObjs.entity;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.registries.PEEntityTypes;
/*    */ import net.minecraft.core.particles.ParticleOptions;
/*    */ import net.minecraft.core.particles.ParticleTypes;
/*    */ import net.minecraft.network.protocol.Packet;
/*    */ import net.minecraft.network.protocol.game.ClientGamePacketListener;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.entity.projectile.ThrowableProjectile;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.phys.EntityHitResult;
/*    */ import net.minecraft.world.phys.HitResult;
/*    */ import net.minecraftforge.network.NetworkHooks;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityMobRandomizer
/*    */   extends NoGravityThrowableProjectile
/*    */ {
/*    */   public EntityMobRandomizer(EntityType<EntityMobRandomizer> type, Level level) {
/* 28 */     super((EntityType)type, level);
/*    */   }
/*    */   
/*    */   public EntityMobRandomizer(Player entity, Level level) {
/* 32 */     super((EntityType<? extends ThrowableProjectile>)PEEntityTypes.MOB_RANDOMIZER.get(), (LivingEntity)entity, level);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void m_8097_() {}
/*    */ 
/*    */   
/*    */   public void m_8119_() {
/* 41 */     super.m_8119_();
/* 42 */     if (!(m_9236_()).f_46443_ && m_6084_() && m_20069_()) {
/* 43 */       m_146870_();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected void m_6532_(@NotNull HitResult result) {
/* 49 */     if ((m_9236_()).f_46443_) {
/* 50 */       for (int i = 0; i < 4; i++) {
/* 51 */         m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123760_, m_20185_(), m_20186_() + this.f_19796_.m_188500_() * 2.0D, m_20189_(), this.f_19796_.m_188583_(), 0.0D, this.f_19796_.m_188583_());
/*    */       }
/*    */     }
/* 54 */     if (!m_20069_()) {
/* 55 */       super.m_6532_(result);
/*    */     }
/* 57 */     m_146870_();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void m_5790_(@NotNull EntityHitResult result) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: invokespecial m_5790_ : (Lnet/minecraft/world/phys/EntityHitResult;)V
/*    */     //   5: aload_0
/*    */     //   6: invokevirtual m_9236_ : ()Lnet/minecraft/world/level/Level;
/*    */     //   9: getfield f_46443_ : Z
/*    */     //   12: ifne -> 209
/*    */     //   15: aload_1
/*    */     //   16: invokevirtual m_82443_ : ()Lnet/minecraft/world/entity/Entity;
/*    */     //   19: astore #4
/*    */     //   21: aload #4
/*    */     //   23: instanceof net/minecraft/world/entity/Mob
/*    */     //   26: ifeq -> 209
/*    */     //   29: aload #4
/*    */     //   31: checkcast net/minecraft/world/entity/Mob
/*    */     //   34: astore_2
/*    */     //   35: aload_0
/*    */     //   36: invokevirtual m_19749_ : ()Lnet/minecraft/world/entity/Entity;
/*    */     //   39: astore #4
/*    */     //   41: aload #4
/*    */     //   43: instanceof net/minecraft/world/entity/player/Player
/*    */     //   46: ifeq -> 209
/*    */     //   49: aload #4
/*    */     //   51: checkcast net/minecraft/world/entity/player/Player
/*    */     //   54: astore_3
/*    */     //   55: aload_0
/*    */     //   56: invokevirtual m_9236_ : ()Lnet/minecraft/world/level/Level;
/*    */     //   59: checkcast net/minecraft/server/level/ServerLevel
/*    */     //   62: astore #4
/*    */     //   64: aload #4
/*    */     //   66: aload_2
/*    */     //   67: invokestatic getRandomEntity : (Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/Mob;)Lnet/minecraft/world/entity/Mob;
/*    */     //   70: astore #5
/*    */     //   72: aload #5
/*    */     //   74: ifnull -> 209
/*    */     //   77: aload_3
/*    */     //   78: ldc2_w 384
/*    */     //   81: invokestatic consumePlayerFuel : (Lnet/minecraft/world/entity/player/Player;J)J
/*    */     //   84: ldc2_w -1
/*    */     //   87: lcmp
/*    */     //   88: ifeq -> 209
/*    */     //   91: aload #5
/*    */     //   93: aload_2
/*    */     //   94: invokevirtual m_20185_ : ()D
/*    */     //   97: aload_2
/*    */     //   98: invokevirtual m_20186_ : ()D
/*    */     //   101: aload_2
/*    */     //   102: invokevirtual m_20189_ : ()D
/*    */     //   105: aload_2
/*    */     //   106: invokevirtual m_146908_ : ()F
/*    */     //   109: aload_2
/*    */     //   110: invokevirtual m_146909_ : ()F
/*    */     //   113: invokevirtual m_7678_ : (DDDFF)V
/*    */     //   116: aload #5
/*    */     //   118: instanceof net/minecraft/world/entity/animal/Rabbit
/*    */     //   121: ifeq -> 157
/*    */     //   124: aload #5
/*    */     //   126: checkcast net/minecraft/world/entity/animal/Rabbit
/*    */     //   129: astore #7
/*    */     //   131: aload #7
/*    */     //   133: invokevirtual m_28554_ : ()Lnet/minecraft/world/entity/animal/Rabbit$Variant;
/*    */     //   136: getstatic net/minecraft/world/entity/animal/Rabbit$Variant.EVIL : Lnet/minecraft/world/entity/animal/Rabbit$Variant;
/*    */     //   139: if_acmpne -> 157
/*    */     //   142: new net/minecraft/world/entity/animal/Rabbit$RabbitGroupData
/*    */     //   145: dup
/*    */     //   146: getstatic net/minecraft/world/entity/animal/Rabbit$Variant.EVIL : Lnet/minecraft/world/entity/animal/Rabbit$Variant;
/*    */     //   149: invokespecial <init> : (Lnet/minecraft/world/entity/animal/Rabbit$Variant;)V
/*    */     //   152: astore #6
/*    */     //   154: goto -> 160
/*    */     //   157: aconst_null
/*    */     //   158: astore #6
/*    */     //   160: aload #5
/*    */     //   162: aload #4
/*    */     //   164: aload #4
/*    */     //   166: aload #5
/*    */     //   168: invokevirtual m_20183_ : ()Lnet/minecraft/core/BlockPos;
/*    */     //   171: invokevirtual m_6436_ : (Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/DifficultyInstance;
/*    */     //   174: getstatic net/minecraft/world/entity/MobSpawnType.CONVERSION : Lnet/minecraft/world/entity/MobSpawnType;
/*    */     //   177: aload #6
/*    */     //   179: aconst_null
/*    */     //   180: invokestatic onFinalizeSpawn : (Lnet/minecraft/world/entity/Mob;Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/world/entity/MobSpawnType;Lnet/minecraft/world/entity/SpawnGroupData;Lnet/minecraft/nbt/CompoundTag;)Lnet/minecraft/world/entity/SpawnGroupData;
/*    */     //   183: pop
/*    */     //   184: aload #4
/*    */     //   186: aload #5
/*    */     //   188: invokevirtual m_8860_ : (Lnet/minecraft/world/entity/Entity;)Z
/*    */     //   191: pop
/*    */     //   192: aload #5
/*    */     //   194: invokevirtual isAddedToWorld : ()Z
/*    */     //   197: ifeq -> 209
/*    */     //   200: aload #5
/*    */     //   202: invokevirtual m_21373_ : ()V
/*    */     //   205: aload_2
/*    */     //   206: invokevirtual m_146870_ : ()V
/*    */     //   209: return
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #62	-> 0
/*    */     //   #63	-> 5
/*    */     //   #64	-> 55
/*    */     //   #65	-> 64
/*    */     //   #68	-> 72
/*    */     //   #69	-> 91
/*    */     //   #71	-> 116
/*    */     //   #74	-> 142
/*    */     //   #76	-> 157
/*    */     //   #78	-> 160
/*    */     //   #79	-> 184
/*    */     //   #80	-> 192
/*    */     //   #81	-> 200
/*    */     //   #83	-> 205
/*    */     //   #87	-> 209
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   154	3	6	data	Lnet/minecraft/world/entity/SpawnGroupData;
/*    */     //   131	26	7	rabbit	Lnet/minecraft/world/entity/animal/Rabbit;
/*    */     //   160	49	6	data	Lnet/minecraft/world/entity/SpawnGroupData;
/*    */     //   64	145	4	level	Lnet/minecraft/server/level/ServerLevel;
/*    */     //   72	137	5	randomized	Lnet/minecraft/world/entity/Mob;
/*    */     //   35	174	2	ent	Lnet/minecraft/world/entity/Mob;
/*    */     //   55	154	3	player	Lnet/minecraft/world/entity/player/Player;
/*    */     //   0	210	0	this	Lmoze_intel/projecte/gameObjs/entity/EntityMobRandomizer;
/*    */     //   0	210	1	result	Lnet/minecraft/world/phys/EntityHitResult;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Packet<ClientGamePacketListener> m_5654_() {
/* 92 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_6128_() {
/* 97 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\entity\EntityMobRandomizer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */