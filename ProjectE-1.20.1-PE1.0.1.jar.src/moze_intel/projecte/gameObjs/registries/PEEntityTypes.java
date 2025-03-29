/*    */ package moze_intel.projecte.gameObjs.registries;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.entity.EntityFireProjectile;
/*    */ import moze_intel.projecte.gameObjs.entity.EntityHomingArrow;
/*    */ import moze_intel.projecte.gameObjs.entity.EntityLavaProjectile;
/*    */ import moze_intel.projecte.gameObjs.entity.EntityLensProjectile;
/*    */ import moze_intel.projecte.gameObjs.entity.EntityMobRandomizer;
/*    */ import moze_intel.projecte.gameObjs.entity.EntityNovaCataclysmPrimed;
/*    */ import moze_intel.projecte.gameObjs.entity.EntityNovaCatalystPrimed;
/*    */ import moze_intel.projecte.gameObjs.entity.EntitySWRGProjectile;
/*    */ import moze_intel.projecte.gameObjs.entity.EntityWaterProjectile;
/*    */ import moze_intel.projecte.gameObjs.registration.impl.EntityTypeDeferredRegister;
/*    */ import moze_intel.projecte.gameObjs.registration.impl.EntityTypeRegistryObject;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.entity.MobCategory;
/*    */ 
/*    */ 
/*    */ public class PEEntityTypes
/*    */ {
/* 20 */   public static final EntityTypeDeferredRegister ENTITY_TYPES = new EntityTypeDeferredRegister("projecte");
/*    */   
/* 22 */   public static final EntityTypeRegistryObject<EntityFireProjectile> FIRE_PROJECTILE = ENTITY_TYPES.register("fire_projectile", EntityType.Builder.m_20704_(EntityFireProjectile::new, MobCategory.MISC).setTrackingRange(256).setUpdateInterval(10));
/* 23 */   public static final EntityTypeRegistryObject<EntityHomingArrow> HOMING_ARROW = ENTITY_TYPES.register("homing_arrow", EntityType.Builder.m_20704_(EntityHomingArrow::new, MobCategory.MISC).setTrackingRange(5).setUpdateInterval(20).setShouldReceiveVelocityUpdates(true));
/* 24 */   public static final EntityTypeRegistryObject<EntityLavaProjectile> LAVA_PROJECTILE = ENTITY_TYPES.register("lava_projectile", EntityType.Builder.m_20704_(EntityLavaProjectile::new, MobCategory.MISC).setTrackingRange(256).setUpdateInterval(10));
/* 25 */   public static final EntityTypeRegistryObject<EntityLensProjectile> LENS_PROJECTILE = ENTITY_TYPES.register("lens_projectile", EntityType.Builder.m_20704_(EntityLensProjectile::new, MobCategory.MISC).setTrackingRange(256).setUpdateInterval(10));
/* 26 */   public static final EntityTypeRegistryObject<EntityMobRandomizer> MOB_RANDOMIZER = ENTITY_TYPES.register("mob_randomizer", EntityType.Builder.m_20704_(EntityMobRandomizer::new, MobCategory.MISC).setTrackingRange(256).setUpdateInterval(10));
/* 27 */   public static final EntityTypeRegistryObject<EntityNovaCatalystPrimed> NOVA_CATALYST_PRIMED = ENTITY_TYPES.register("nova_catalyst_primed", EntityType.Builder.m_20704_(EntityNovaCatalystPrimed::new, MobCategory.MISC).setTrackingRange(10).setUpdateInterval(10));
/* 28 */   public static final EntityTypeRegistryObject<EntityNovaCataclysmPrimed> NOVA_CATACLYSM_PRIMED = ENTITY_TYPES.register("nova_cataclysm_primed", EntityType.Builder.m_20704_(EntityNovaCataclysmPrimed::new, MobCategory.MISC).setTrackingRange(10).setUpdateInterval(10));
/* 29 */   public static final EntityTypeRegistryObject<EntitySWRGProjectile> SWRG_PROJECTILE = ENTITY_TYPES.register("swrg_projectile", EntityType.Builder.m_20704_(EntitySWRGProjectile::new, MobCategory.MISC).setTrackingRange(256).setUpdateInterval(10));
/* 30 */   public static final EntityTypeRegistryObject<EntityWaterProjectile> WATER_PROJECTILE = ENTITY_TYPES.register("water_projectile", EntityType.Builder.m_20704_(EntityWaterProjectile::new, MobCategory.MISC).setTrackingRange(256).setUpdateInterval(10));
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registries\PEEntityTypes.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */