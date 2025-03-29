/*    */ package moze_intel.projecte.gameObjs.registration.impl;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.registration.WrappedRegistryObject;
/*    */ import moze_intel.projecte.utils.text.IHasTranslationKey;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ 
/*    */ public class EntityTypeRegistryObject<ENTITY extends Entity>
/*    */   extends WrappedRegistryObject<EntityType<ENTITY>> implements IHasTranslationKey {
/*    */   public EntityTypeRegistryObject(RegistryObject<EntityType<ENTITY>> registryObject) {
/* 12 */     super(registryObject);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTranslationKey() {
/* 17 */     return ((EntityType)get()).m_20675_();
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registration\impl\EntityTypeRegistryObject.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */