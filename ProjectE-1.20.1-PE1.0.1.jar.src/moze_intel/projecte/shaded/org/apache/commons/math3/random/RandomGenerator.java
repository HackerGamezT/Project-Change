package moze_intel.projecte.shaded.org.apache.commons.math3.random;

public interface RandomGenerator {
  void setSeed(int paramInt);
  
  void setSeed(int[] paramArrayOfint);
  
  void setSeed(long paramLong);
  
  void nextBytes(byte[] paramArrayOfbyte);
  
  int nextInt();
  
  int nextInt(int paramInt);
  
  long nextLong();
  
  boolean nextBoolean();
  
  float nextFloat();
  
  double nextDouble();
  
  double nextGaussian();
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\shaded\org\apache\commons\math3\random\RandomGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */