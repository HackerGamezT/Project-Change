/*    */ package moze_intel.projecte.gameObjs.registration.impl;
/*    */ 
/*    */ import java.util.function.Function;
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.gameObjs.registration.WrappedDeferredRegister;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ 
/*    */ public class SoundEventDeferredRegister extends WrappedDeferredRegister<SoundEvent> {
/*    */   public SoundEventDeferredRegister(String modid) {
/* 11 */     super(ForgeRegistries.SOUND_EVENTS, modid);
/*    */   }
/*    */   
/*    */   public SoundEventRegistryObject<SoundEvent> register(String name) {
/* 15 */     return (SoundEventRegistryObject<SoundEvent>)register(name, () -> SoundEvent.m_262824_(PECore.rl(name)), SoundEventRegistryObject::new);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registration\impl\SoundEventDeferredRegister.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */