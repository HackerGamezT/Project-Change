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
/*    */ 
/*    */ public class NotStrictlyPositiveException
/*    */   extends NumberIsTooSmallException
/*    */ {
/*    */   private static final long serialVersionUID = -7824848630829852237L;
/*    */   
/*    */   public NotStrictlyPositiveException(Number value) {
/* 37 */     super(value, INTEGER_ZERO, false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NotStrictlyPositiveException(Localizable specific, Number value) {
/* 47 */     super(specific, value, INTEGER_ZERO, false);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\shaded\org\apache\commons\math3\exception\NotStrictlyPositiveException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */