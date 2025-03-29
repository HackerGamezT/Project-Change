/*    */ package moze_intel.projecte.shaded.org.apache.commons.math3.analysis.solvers;
/*    */ 
/*    */ import moze_intel.projecte.shaded.org.apache.commons.math3.analysis.UnivariateFunction;
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
/*    */ public abstract class AbstractUnivariateSolver
/*    */   extends BaseAbstractUnivariateSolver<UnivariateFunction>
/*    */   implements UnivariateSolver
/*    */ {
/*    */   protected AbstractUnivariateSolver(double absoluteAccuracy) {
/* 36 */     super(absoluteAccuracy);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected AbstractUnivariateSolver(double relativeAccuracy, double absoluteAccuracy) {
/* 46 */     super(relativeAccuracy, absoluteAccuracy);
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
/*    */   protected AbstractUnivariateSolver(double relativeAccuracy, double absoluteAccuracy, double functionValueAccuracy) {
/* 58 */     super(relativeAccuracy, absoluteAccuracy, functionValueAccuracy);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\shaded\org\apache\commons\math3\analysis\solvers\AbstractUnivariateSolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */