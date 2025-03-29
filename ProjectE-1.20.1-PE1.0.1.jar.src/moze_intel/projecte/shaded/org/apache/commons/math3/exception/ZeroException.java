/*    */ package moze_intel.projecte.shaded.org.apache.commons.math3.exception;
/*    */ 
/*    */ import moze_intel.projecte.shaded.org.apache.commons.math3.exception.util.Localizable;
/*    */ import moze_intel.projecte.shaded.org.apache.commons.math3.exception.util.LocalizedFormats;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ZeroException
/*    */   extends MathIllegalNumberException
/*    */ {
/*    */   private static final long serialVersionUID = -1960874856936000015L;
/*    */   
/*    */   public ZeroException() {
/* 36 */     this((Localizable)LocalizedFormats.ZERO_NOT_ALLOWED, new Object[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ZeroException(Localizable specific, Object... arguments) {
/* 46 */     super(specific, INTEGER_ZERO, arguments);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\shaded\org\apache\commons\math3\exception\ZeroException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */