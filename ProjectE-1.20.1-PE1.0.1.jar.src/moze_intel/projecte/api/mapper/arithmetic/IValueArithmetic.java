package moze_intel.projecte.api.mapper.arithmetic;

public interface IValueArithmetic<T extends Comparable<T>> {
  boolean isZero(T paramT);
  
  T getZero();
  
  T add(T paramT1, T paramT2);
  
  T mul(long paramLong, T paramT);
  
  T div(T paramT, long paramLong);
  
  T getFree();
  
  boolean isFree(T paramT);
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\mapper\arithmetic\IValueArithmetic.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */