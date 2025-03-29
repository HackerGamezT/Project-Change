package moze_intel.projecte.shaded.org.apache.commons.math3.analysis.solvers;

public interface BracketedUnivariateSolver<FUNC extends moze_intel.projecte.shaded.org.apache.commons.math3.analysis.UnivariateFunction> extends BaseUnivariateSolver<FUNC> {
  double solve(int paramInt, FUNC paramFUNC, double paramDouble1, double paramDouble2, AllowedSolution paramAllowedSolution);
  
  double solve(int paramInt, FUNC paramFUNC, double paramDouble1, double paramDouble2, double paramDouble3, AllowedSolution paramAllowedSolution);
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\shaded\org\apache\commons\math3\analysis\solvers\BracketedUnivariateSolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */