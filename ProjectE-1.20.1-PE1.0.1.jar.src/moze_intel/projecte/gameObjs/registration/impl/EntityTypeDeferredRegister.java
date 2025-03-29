/*    */ package moze_intel.projecte.gameObjs.registration.impl;
/*    */ 
/*    */ import java.util.function.Function;
/*    */ import moze_intel.projecte.gameObjs.registration.WrappedDeferredRegister;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ 
/*    */ public class EntityTypeDeferredRegister
/*    */   extends WrappedDeferredRegister<EntityType<?>> {
/*    */   public EntityTypeDeferredRegister(String modid) {
/* 11 */     super(ForgeRegistries.ENTITY_TYPES, modid);
/*    */   }
/*    */   
/*    */   public <ENTITY extends net.minecraft.world.entity.Entity> EntityTypeRegistryObject<ENTITY> register(String name, EntityType.Builder<ENTITY> builder) {
/* 15 */     return (EntityTypeRegistryObject<ENTITY>)register(name, () -> builder.m_20712_(name), EntityTypeRegistryObject::new);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registration\impl\EntityTypeDeferredRegister.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */