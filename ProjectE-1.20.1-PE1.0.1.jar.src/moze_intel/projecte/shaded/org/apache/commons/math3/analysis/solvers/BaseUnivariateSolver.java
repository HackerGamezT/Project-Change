package moze_intel.projecte.shaded.org.apache.commons.math3.analysis.solvers;

import moze_intel.projecte.shaded.org.apache.commons.math3.exception.MathIllegalArgumentException;
import moze_intel.projecte.shaded.org.apache.commons.math3.exception.TooManyEvaluationsException;

public interface BaseUnivariateSolver<FUNC extends moze_intel.projecte.shaded.org.apache.commons.math3.analysis.UnivariateFunction> {
  int getMaxEvaluations();
  
  int getEvaluations();
  
  double getAbsoluteAccuracy();
  
  double getRelativeAccuracy();
  
  double getFunctionValueAccuracy();
  
  double solve(int paramInt, FUNC paramFUNC, double paramDouble1, double paramDouble2) throws MathIllegalArgumentException, TooManyEvaluationsException;
  
  double solve(int paramInt, FUNC paramFUNC, double paramDouble1, double paramDouble2, double paramDouble3) throws MathIllegalArgumentException, TooManyEvaluationsException;
  
  double solve(int paramInt, FUNC paramFUNC, double paramDouble);
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\shaded\org\apache\commons\math3\analysis\solvers\BaseUnivariateSolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */