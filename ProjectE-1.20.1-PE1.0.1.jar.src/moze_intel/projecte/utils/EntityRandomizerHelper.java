/*    */ package moze_intel.projecte.utils;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.gameObjs.PETags;
/*    */ import net.minecraft.tags.TagKey;
/*    */ import net.minecraft.util.RandomSource;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.entity.Mob;
/*    */ import net.minecraft.world.entity.animal.Rabbit;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.tags.ITag;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ public class EntityRandomizerHelper
/*    */ {
/*    */   @Nullable
/*    */   public static Mob getRandomEntity(Level level, Mob toRandomize) {
/* 22 */     EntityType<?> entType = toRandomize.m_6095_();
/* 23 */     boolean isPeaceful = entType.m_204039_(PETags.Entities.RANDOMIZER_PEACEFUL);
/* 24 */     boolean isHostile = entType.m_204039_(PETags.Entities.RANDOMIZER_HOSTILE);
/* 25 */     if (isPeaceful && isHostile)
/*    */     {
/*    */       
/* 28 */       if (toRandomize instanceof Rabbit) { Rabbit rabbit = (Rabbit)toRandomize; if (rabbit.m_28554_() == Rabbit.Variant.EVIL)
/*    */         {
/* 30 */           isPeaceful = false; }  }
/*    */     
/*    */     }
/* 33 */     if (isPeaceful)
/* 34 */       return createRandomEntity(level, (Entity)toRandomize, PETags.Entities.RANDOMIZER_PEACEFUL); 
/* 35 */     if (isHostile) {
/* 36 */       Mob ent = createRandomEntity(level, (Entity)toRandomize, PETags.Entities.RANDOMIZER_HOSTILE);
/* 37 */       if (ent instanceof Rabbit) { Rabbit rabbit = (Rabbit)ent;
/* 38 */         rabbit.m_28464_(Rabbit.Variant.EVIL); }
/*    */       
/* 40 */       return ent;
/*    */     } 
/* 42 */     return null;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   private static Mob createRandomEntity(Level level, Entity current, TagKey<EntityType<?>> type) {
/* 47 */     ITag<EntityType<?>> tag = LazyTagLookup.<TYPE>tagManager(ForgeRegistries.ENTITY_TYPES).getTag(type);
/* 48 */     EntityType<?> currentType = current.m_6095_();
/* 49 */     EntityType<?> newType = getRandomTagEntry(level.m_213780_(), (ITag)tag, currentType);
/* 50 */     if (currentType == newType)
/*    */     {
/* 52 */       return null;
/*    */     }
/* 54 */     Entity newEntity = newType.m_20615_(level);
/* 55 */     if (newEntity instanceof Mob)
/* 56 */       return (Mob)newEntity; 
/* 57 */     if (newEntity != null) {
/*    */       
/* 59 */       newEntity.m_146870_();
/*    */       
/* 61 */       PECore.LOGGER.warn("Invalid Entity type {} in mob randomizer tag {}. All entities in this tag are expected to be a mob.", RegistryUtils.getName(newType), type
/* 62 */           .f_203868_());
/*    */     } 
/* 64 */     return null;
/*    */   }
/*    */   private static <T> T getRandomTagEntry(RandomSource random, ITag<T> tag, T toExclude) {
/*    */     Optional<T> obj;
/* 68 */     int size = tag.size();
/* 69 */     if (size == 0 || (size == 1 && tag.contains(toExclude))) {
/* 70 */       return toExclude;
/*    */     }
/*    */     
/*    */     do {
/* 74 */       obj = tag.getRandomElement(random);
/* 75 */     } while (obj.isPresent() && obj.get().equals(toExclude));
/*    */     
/* 77 */     return obj.orElse(toExclude);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\project\\utils\EntityRandomizerHelper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */