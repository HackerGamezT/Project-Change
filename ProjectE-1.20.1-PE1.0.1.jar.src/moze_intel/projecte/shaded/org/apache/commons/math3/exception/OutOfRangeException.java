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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OutOfRangeException
/*    */   extends MathIllegalNumberException
/*    */ {
/*    */   private static final long serialVersionUID = 111601815794403609L;
/*    */   private final Number lo;
/*    */   private final Number hi;
/*    */   
/*    */   public OutOfRangeException(Number wrong, Number lo, Number hi) {
/* 45 */     this((Localizable)LocalizedFormats.OUT_OF_RANGE_SIMPLE, wrong, lo, hi);
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
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public OutOfRangeException(Localizable specific, Number wrong, Number lo, Number hi) {
/* 61 */     super(specific, wrong, new Object[] { lo, hi });
/* 62 */     this.lo = lo;
/* 63 */     this.hi = hi;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Number getLo() {
/* 70 */     return this.lo;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Number getHi() {
/* 76 */     return this.hi;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\shaded\org\apache\commons\math3\exception\OutOfRangeException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */