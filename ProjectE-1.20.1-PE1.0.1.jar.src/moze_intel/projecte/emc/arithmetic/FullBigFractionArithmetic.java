/*    */ package moze_intel.projecte.emc.arithmetic;
/*    */ 
/*    */ import moze_intel.projecte.api.mapper.arithmetic.IValueArithmetic;
/*    */ import moze_intel.projecte.shaded.org.apache.commons.math3.fraction.BigFraction;
/*    */ import moze_intel.projecte.utils.Constants;
/*    */ 
/*    */ public class FullBigFractionArithmetic
/*    */   implements IValueArithmetic<BigFraction>
/*    */ {
/*    */   public boolean isZero(BigFraction value) {
/* 11 */     return BigFraction.ZERO.equals(value);
/*    */   }
/*    */ 
/*    */   
/*    */   public BigFraction getZero() {
/* 16 */     return BigFraction.ZERO;
/*    */   }
/*    */ 
/*    */   
/*    */   public BigFraction add(BigFraction a, BigFraction b) {
/* 21 */     if (isFree(a)) {
/* 22 */       return b;
/*    */     }
/* 24 */     if (isFree(b)) {
/* 25 */       return a;
/*    */     }
/* 27 */     return a.add(b);
/*    */   }
/*    */ 
/*    */   
/*    */   public BigFraction mul(long a, BigFraction b) {
/* 32 */     if (isFree(b)) {
/* 33 */       return getFree();
/*    */     }
/* 35 */     return b.multiply(a);
/*    */   }
/*    */ 
/*    */   
/*    */   public BigFraction div(BigFraction a, long b) {
/* 40 */     if (isFree(a)) {
/* 41 */       return getFree();
/*    */     }
/* 43 */     if (b == 0L) {
/* 44 */       return BigFraction.ZERO;
/*    */     }
/* 46 */     BigFraction result = a.divide(b);
/* 47 */     if (result.getNumerator().compareTo(Constants.MAX_LONG) > 0 || result.getDenominator().compareTo(Constants.MAX_LONG) > 0)
/*    */     {
/* 49 */       return BigFraction.ZERO;
/*    */     }
/* 51 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public BigFraction getFree() {
/* 56 */     return new BigFraction(Long.MIN_VALUE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isFree(BigFraction value) {
/* 61 */     return (value.getNumeratorAsLong() == Long.MIN_VALUE);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\arithmetic\FullBigFractionArithmetic.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */