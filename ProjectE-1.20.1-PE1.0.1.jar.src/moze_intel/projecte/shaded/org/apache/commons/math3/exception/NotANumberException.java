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
/*    */ public class NotANumberException
/*    */   extends MathIllegalNumberException
/*    */ {
/*    */   private static final long serialVersionUID = 20120906L;
/*    */   
/*    */   public NotANumberException() {
/* 34 */     super((Localizable)LocalizedFormats.NAN_NOT_ALLOWED, Double.valueOf(Double.NaN), new Object[0]);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\shaded\org\apache\commons\math3\exception\NotANumberException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */