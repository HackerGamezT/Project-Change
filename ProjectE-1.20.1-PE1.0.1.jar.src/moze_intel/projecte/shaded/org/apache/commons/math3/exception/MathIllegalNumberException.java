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
/*    */ public class MathIllegalNumberException
/*    */   extends MathIllegalArgumentException
/*    */ {
/* 32 */   protected static final Integer INTEGER_ZERO = Integer.valueOf(0);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static final long serialVersionUID = -7447085893598031110L;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private final Number argument;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected MathIllegalNumberException(Localizable pattern, Number wrong, Object... arguments) {
/* 50 */     super(pattern, new Object[] { wrong, arguments });
/* 51 */     this.argument = wrong;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Number getArgument() {
/* 58 */     return this.argument;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\shaded\org\apache\commons\math3\exception\MathIllegalNumberException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */