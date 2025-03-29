/*    */ package moze_intel.projecte.shaded.org.apache.commons.math3.exception;
/*    */ 
/*    */ import moze_intel.projecte.shaded.org.apache.commons.math3.exception.util.Localizable;
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
/*    */ 
/*    */ public class NotPositiveException
/*    */   extends NumberIsTooSmallException
/*    */ {
/*    */   private static final long serialVersionUID = -2250556892093726375L;
/*    */   
/*    */   public NotPositiveException(Number value) {
/* 36 */     super(value, INTEGER_ZERO, true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NotPositiveException(Localizable specific, Number value) {
/* 46 */     super(specific, value, INTEGER_ZERO, true);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\shaded\org\apache\commons\math3\exception\NotPositiveException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */