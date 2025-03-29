/*    */ package moze_intel.projecte.utils;
/*    */ 
/*    */ import java.util.Locale;
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.utils.text.IHasTranslationKey;
/*    */ import net.minecraft.Util;
/*    */ 
/*    */ public enum PEKeybind implements IHasTranslationKey {
/*  9 */   HELMET_TOGGLE,
/* 10 */   BOOTS_TOGGLE,
/* 11 */   CHARGE,
/* 12 */   EXTRA_FUNCTION,
/* 13 */   FIRE_PROJECTILE,
/* 14 */   MODE;
/*    */   
/*    */   private final String translationKey;
/*    */   
/*    */   PEKeybind() {
/* 19 */     this.translationKey = Util.m_137492_("key", PECore.rl(name().toLowerCase(Locale.ROOT)));
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTranslationKey() {
/* 24 */     return this.translationKey;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\project\\utils\PEKeybind.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */