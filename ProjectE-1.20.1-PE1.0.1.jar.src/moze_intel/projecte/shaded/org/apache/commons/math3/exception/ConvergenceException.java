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
/*    */ public class ConvergenceException
/*    */   extends MathIllegalStateException
/*    */ {
/*    */   private static final long serialVersionUID = 4330003017885151975L;
/*    */   
/*    */   public ConvergenceException() {
/* 36 */     this((Localizable)LocalizedFormats.CONVERGENCE_FAILED, new Object[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ConvergenceException(Localizable pattern, Object... args) {
/* 48 */     getContext().addMessage(pattern, args);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\shaded\org\apache\commons\math3\exception\ConvergenceException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */