package moze_intel.projecte.shaded.org.apache.commons.math3.random;

import java.util.Collection;
import moze_intel.projecte.shaded.org.apache.commons.math3.exception.NotANumberException;
import moze_intel.projecte.shaded.org.apache.commons.math3.exception.NotFiniteNumberException;
import moze_intel.projecte.shaded.org.apache.commons.math3.exception.NotStrictlyPositiveException;
import moze_intel.projecte.shaded.org.apache.commons.math3.exception.NumberIsTooLargeException;

@Deprecated
public interface RandomData {
  String nextHexString(int paramInt) throws NotStrictlyPositiveException;
  
  int nextInt(int paramInt1, int paramInt2) throws NumberIsTooLargeException;
  
  long nextLong(long paramLong1, long paramLong2) throws NumberIsTooLargeException;
  
  String nextSecureHexString(int paramInt) throws NotStrictlyPositiveException;
  
  int nextSecureInt(int paramInt1, int paramInt2) throws NumberIsTooLargeException;
  
  long nextSecureLong(long paramLong1, long paramLong2) throws NumberIsTooLargeException;
  
  long nextPoisson(double paramDouble) throws NotStrictlyPositiveException;
  
  double nextGaussian(double paramDouble1, double paramDouble2) throws NotStrictlyPositiveException;
  
  double nextExponential(double paramDouble) throws NotStrictlyPositiveException;
  
  double nextUniform(double paramDouble1, double paramDouble2) throws NumberIsTooLargeException, NotFiniteNumberException, NotANumberException;
  
  double nextUniform(double paramDouble1, double paramDouble2, boolean paramBoolean) throws NumberIsTooLargeException, NotFiniteNumberException, NotANumberException;
  
  int[] nextPermutation(int paramInt1, int paramInt2) throws NumberIsTooLargeException, NotStrictlyPositiveException;
  
  Object[] nextSample(Collection<?> paramCollection, int paramInt) throws NumberIsTooLargeException, NotStrictlyPositiveException;
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\shaded\org\apache\commons\math3\random\RandomData.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */