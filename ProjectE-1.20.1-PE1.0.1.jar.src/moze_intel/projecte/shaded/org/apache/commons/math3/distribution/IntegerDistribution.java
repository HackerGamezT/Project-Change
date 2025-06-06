package moze_intel.projecte.shaded.org.apache.commons.math3.distribution;

import moze_intel.projecte.shaded.org.apache.commons.math3.exception.NumberIsTooLargeException;
import moze_intel.projecte.shaded.org.apache.commons.math3.exception.OutOfRangeException;

public interface IntegerDistribution {
  double probability(int paramInt);
  
  double cumulativeProbability(int paramInt);
  
  double cumulativeProbability(int paramInt1, int paramInt2) throws NumberIsTooLargeException;
  
  int inverseCumulativeProbability(double paramDouble) throws OutOfRangeException;
  
  double getNumericalMean();
  
  double getNumericalVariance();
  
  int getSupportLowerBound();
  
  int getSupportUpperBound();
  
  boolean isSupportConnected();
  
  void reseedRandomGenerator(long paramLong);
  
  int sample();
  
  int[] sample(int paramInt);
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\shaded\org\apache\commons\math3\distribution\IntegerDistribution.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */