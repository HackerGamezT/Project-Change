/*    */ package moze_intel.projecte.emc.arithmetic;
/*    */ 
/*    */ import moze_intel.projecte.shaded.org.apache.commons.math3.fraction.BigFraction;
/*    */ 
/*    */ public class HiddenBigFractionArithmetic
/*    */   extends FullBigFractionArithmetic
/*    */ {
/*    */   public BigFraction div(BigFraction a, long b) {
/*  9 */     BigFraction result = super.div(a, b);
/* 10 */     if (BigFraction.ZERO.compareTo(result) <= 0 && result.compareTo(BigFraction.ONE) < 0) {
/* 11 */       return result;
/*    */     }
/* 13 */     return new BigFraction(result.longValue());
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\arithmetic\HiddenBigFractionArithmetic.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */