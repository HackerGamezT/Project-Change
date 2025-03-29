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
/*    */ public class DimensionMismatchException
/*    */   extends MathIllegalNumberException
/*    */ {
/*    */   private static final long serialVersionUID = -8415396756375798143L;
/*    */   private final int dimension;
/*    */   
/*    */   public DimensionMismatchException(Localizable specific, int wrong, int expected) {
/* 43 */     super(specific, Integer.valueOf(wrong), new Object[] { Integer.valueOf(expected) });
/* 44 */     this.dimension = expected;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DimensionMismatchException(int wrong, int expected) {
/* 55 */     this((Localizable)LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, wrong, expected);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDimension() {
/* 62 */     return this.dimension;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\shaded\org\apache\commons\math3\exception\DimensionMismatchException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */