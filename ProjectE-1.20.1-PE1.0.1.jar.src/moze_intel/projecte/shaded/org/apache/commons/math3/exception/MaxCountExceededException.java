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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MaxCountExceededException
/*    */   extends MathIllegalStateException
/*    */ {
/*    */   private static final long serialVersionUID = 4330003017885151975L;
/*    */   private final Number max;
/*    */   
/*    */   public MaxCountExceededException(Number max) {
/* 41 */     this((Localizable)LocalizedFormats.MAX_COUNT_EXCEEDED, max, new Object[0]);
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
/*    */   public MaxCountExceededException(Localizable specific, Number max, Object... args) {
/* 53 */     getContext().addMessage(specific, new Object[] { max, args });
/* 54 */     this.max = max;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Number getMax() {
/* 61 */     return this.max;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\shaded\org\apache\commons\math3\exception\MaxCountExceededException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */