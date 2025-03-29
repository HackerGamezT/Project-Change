/*    */ package moze_intel.projecte.gameObjs.registries;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.registration.impl.SoundEventDeferredRegister;
/*    */ import moze_intel.projecte.gameObjs.registration.impl.SoundEventRegistryObject;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ 
/*    */ 
/*    */ public class PESoundEvents
/*    */ {
/* 10 */   public static final SoundEventDeferredRegister SOUND_EVENTS = new SoundEventDeferredRegister("projecte");
/*    */   
/* 12 */   public static final SoundEventRegistryObject<SoundEvent> WIND_MAGIC = SOUND_EVENTS.register("windmagic");
/* 13 */   public static final SoundEventRegistryObject<SoundEvent> WATER_MAGIC = SOUND_EVENTS.register("watermagic");
/* 14 */   public static final SoundEventRegistryObject<SoundEvent> POWER = SOUND_EVENTS.register("power");
/* 15 */   public static final SoundEventRegistryObject<SoundEvent> HEAL = SOUND_EVENTS.register("heal");
/* 16 */   public static final SoundEventRegistryObject<SoundEvent> DESTRUCT = SOUND_EVENTS.register("destruct");
/* 17 */   public static final SoundEventRegistryObject<SoundEvent> CHARGE = SOUND_EVENTS.register("charge");
/* 18 */   public static final SoundEventRegistryObject<SoundEvent> UNCHARGE = SOUND_EVENTS.register("uncharge");
/* 19 */   public static final SoundEventRegistryObject<SoundEvent> TRANSMUTE = SOUND_EVENTS.register("transmute");
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registries\PESoundEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */