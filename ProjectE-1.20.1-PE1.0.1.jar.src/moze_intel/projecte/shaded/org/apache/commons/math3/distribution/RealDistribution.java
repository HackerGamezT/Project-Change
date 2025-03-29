package moze_intel.projecte.shaded.org.apache.commons.math3.distribution;

import moze_intel.projecte.shaded.org.apache.commons.math3.exception.NumberIsTooLargeException;
import moze_intel.projecte.shaded.org.apache.commons.math3.exception.OutOfRangeException;

public interface RealDistribution {
  double probability(double paramDouble);
  
  double density(double paramDouble);
  
  double cumulativeProbability(double paramDouble);
  
  @Deprecated
  double cumulativeProbability(double paramDouble1, double paramDouble2) throws NumberIsTooLargeException;
  
  double inverseCumulativeProbability(double paramDouble) throws OutOfRangeException;
  
  double getNumericalMean();
  
  double getNumericalVariance();
  
  double getSupportLowerBound();
  
  double getSupportUpperBound();
  
  @Deprecated
  boolean isSupportLowerBoundInclusive();
  
  @Deprecated
  boolean isSupportUpperBoundInclusive();
  
  boolean isSupportConnected();
  
  void reseedRandomGenerator(long paramLong);
  
  double sample();
  
  double[] sample(int paramInt);
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\shaded\org\apache\commons\math3\distribution\RealDistribution.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */