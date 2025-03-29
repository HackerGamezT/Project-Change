/*    */ package moze_intel.projecte.utils;
/*    */ 
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.utils.text.PELang;
/*    */ import moze_intel.projecte.utils.text.TextComponentUtil;
/*    */ import net.minecraft.Util;
/*    */ import net.minecraft.network.chat.Component;
/*    */ 
/*    */ public class TransmutationEMCFormatter {
/*    */   private static final int MAX_POSTFIX_INDEX = 17;
/*    */   
/*    */   public static Component formatEMC(Number emc) {
/*    */     double value;
/* 14 */     String emcAsString = emc.toString();
/* 15 */     int length = emcAsString.length();
/* 16 */     int splits = (length - 1) / 3;
/* 17 */     if (splits < 4) {
/* 18 */       return (Component)TextComponentUtil.getString(Constants.EMC_FORMATTER.format(emc));
/*    */     }
/* 20 */     int postfixIndex = splits - 4;
/* 21 */     if (postfixIndex > 17)
/*    */     {
/*    */ 
/*    */ 
/*    */       
/* 26 */       return (Component)PELang.EMC_TOO_MUCH.translate(new Object[0]);
/*    */     }
/*    */     
/* 29 */     int extraDigits = length % 3;
/*    */     
/* 31 */     if (extraDigits == 0) {
/* 32 */       value = Double.parseDouble(emcAsString.substring(0, 3) + "." + emcAsString.substring(0, 3));
/* 33 */     } else if (extraDigits == 1) {
/* 34 */       value = Double.parseDouble("" + emcAsString.charAt(0) + "." + emcAsString.charAt(0));
/*    */     } else {
/* 36 */       value = Double.parseDouble(emcAsString.substring(0, 2) + "." + emcAsString.substring(0, 2));
/*    */     } 
/* 38 */     return (Component)TextComponentUtil.smartTranslate(Util.m_137492_("emc", PECore.rl("postfix." + postfixIndex)), new Object[] { Constants.EMC_FORMATTER.format(value) });
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\project\\utils\TransmutationEMCFormatter.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */