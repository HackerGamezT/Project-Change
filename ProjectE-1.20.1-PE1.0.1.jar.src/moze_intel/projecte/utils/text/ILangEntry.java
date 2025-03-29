/*    */ package moze_intel.projecte.utils.text;
/*    */ 
/*    */ import net.minecraft.ChatFormatting;
/*    */ import net.minecraft.network.chat.MutableComponent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface ILangEntry
/*    */   extends IHasTranslationKey
/*    */ {
/*    */   MutableComponent translate(Object... args) {
/* 17 */     return TextComponentUtil.smartTranslate(getTranslationKey(), args);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   MutableComponent translateColored(ChatFormatting color, Object... args) {
/* 25 */     return TextComponentUtil.build(new Object[] { color, translate(args) });
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\project\\utils\text\ILangEntry.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */