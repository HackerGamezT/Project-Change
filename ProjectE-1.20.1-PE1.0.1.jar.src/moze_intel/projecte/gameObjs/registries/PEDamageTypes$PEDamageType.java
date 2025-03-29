/*    */ package moze_intel.projecte.gameObjs.registries;
/*    */ 
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.utils.text.IHasTranslationKey;
/*    */ import net.minecraft.core.Holder;
/*    */ import net.minecraft.core.RegistryAccess;
/*    */ import net.minecraft.core.registries.Registries;
/*    */ import net.minecraft.resources.ResourceKey;
/*    */ import net.minecraft.world.damagesource.DamageSource;
/*    */ import net.minecraft.world.damagesource.DamageType;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.level.Level;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ public final class PEDamageType
/*    */   extends Record
/*    */   implements IHasTranslationKey
/*    */ {
/*    */   private final ResourceKey<DamageType> key;
/*    */   private final String msgId;
/*    */   private final float exhaustion;
/*    */   
/*    */   public ResourceKey<DamageType> key() {
/* 27 */     return this.key; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lmoze_intel/projecte/gameObjs/registries/PEDamageTypes$PEDamageType;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #27	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lmoze_intel/projecte/gameObjs/registries/PEDamageTypes$PEDamageType; } public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/gameObjs/registries/PEDamageTypes$PEDamageType;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #27	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lmoze_intel/projecte/gameObjs/registries/PEDamageTypes$PEDamageType; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lmoze_intel/projecte/gameObjs/registries/PEDamageTypes$PEDamageType;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #27	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lmoze_intel/projecte/gameObjs/registries/PEDamageTypes$PEDamageType;
/* 27 */     //   0	8	1	o	Ljava/lang/Object; } public String msgId() { return this.msgId; } public float exhaustion() { return this.exhaustion; }
/*    */ 
/*    */   
/* 30 */   public PEDamageType(ResourceKey<DamageType> key, String msgId, float exhaustion) { PEDamageTypes.INTERNAL_DAMAGE_TYPES.put(key.m_135782_().toString(), this);
/*    */     this.key = key;
/*    */     this.msgId = msgId;
/*    */     this.exhaustion = exhaustion; } private PEDamageType(String name, float exhaustion) {
/* 34 */     this(name, "projecte." + name, exhaustion);
/*    */   }
/*    */   
/*    */   private PEDamageType(String name, String msgId, float exhaustion) {
/* 38 */     this(ResourceKey.m_135785_(Registries.f_268580_, PECore.rl(name)), msgId, exhaustion);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public String getTranslationKey() {
/* 44 */     return "death.attack." + msgId();
/*    */   }
/*    */   
/*    */   public DamageSource source(@NotNull LivingEntity entity) {
/* 48 */     return source(entity.m_9236_(), entity);
/*    */   }
/*    */   
/*    */   public DamageSource source(Level level, @Nullable LivingEntity entity) {
/* 52 */     return source(level.m_9598_(), entity);
/*    */   }
/*    */   
/*    */   public DamageSource source(RegistryAccess registryAccess, @Nullable LivingEntity entity) {
/* 56 */     Holder.Reference<DamageType> damageTypeReference = registryAccess.m_175515_(Registries.f_268580_).m_246971_(key());
/* 57 */     return new DamageSource((Holder)damageTypeReference, (Entity)entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registries\PEDamageTypes$PEDamageType.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */