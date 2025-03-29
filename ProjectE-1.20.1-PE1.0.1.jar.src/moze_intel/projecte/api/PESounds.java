/*    */ package moze_intel.projecte.api;
/*    */ 
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ 
/*    */ public final class PESounds
/*    */ {
/* 10 */   public static RegistryObject<SoundEvent> WIND = get("windmagic");
/* 11 */   public static RegistryObject<SoundEvent> WATER = get("watermagic");
/* 12 */   public static RegistryObject<SoundEvent> POWER = get("power");
/* 13 */   public static RegistryObject<SoundEvent> HEAL = get("heal");
/* 14 */   public static RegistryObject<SoundEvent> DESTRUCT = get("destruct");
/* 15 */   public static RegistryObject<SoundEvent> CHARGE = get("charge");
/* 16 */   public static RegistryObject<SoundEvent> UNCHARGE = get("uncharge");
/* 17 */   public static RegistryObject<SoundEvent> TRANSMUTE = get("transmute");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static RegistryObject<SoundEvent> get(String name) {
/* 23 */     return RegistryObject.create(new ResourceLocation("projecte", name), ForgeRegistries.SOUND_EVENTS);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\PESounds.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */