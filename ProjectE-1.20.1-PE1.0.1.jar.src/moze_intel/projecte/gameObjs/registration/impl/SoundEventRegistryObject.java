/*    */ package moze_intel.projecte.gameObjs.registration.impl;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.registration.WrappedRegistryObject;
/*    */ import moze_intel.projecte.utils.text.ILangEntry;
/*    */ import net.minecraft.Util;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ 
/*    */ public class SoundEventRegistryObject<SOUND extends SoundEvent>
/*    */   extends WrappedRegistryObject<SOUND> implements ILangEntry {
/*    */   private final String translationKey;
/*    */   
/*    */   public SoundEventRegistryObject(RegistryObject<SOUND> registryObject) {
/* 14 */     super(registryObject);
/* 15 */     this.translationKey = Util.m_137492_("sound_event", this.registryObject.getId());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTranslationKey() {
/* 20 */     return this.translationKey;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registration\impl\SoundEventRegistryObject.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */