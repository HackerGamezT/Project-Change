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
/*    */ public class TooManyEvaluationsException
/*    */   extends MaxCountExceededException
/*    */ {
/*    */   private static final long serialVersionUID = 4330003017885151975L;
/*    */   
/*    */   public TooManyEvaluationsException(Number max) {
/* 36 */     super(max);
/* 37 */     getContext().addMessage((Localizable)LocalizedFormats.EVALUATIONS, new Object[0]);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\shaded\org\apache\commons\math3\exception\TooManyEvaluationsException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */