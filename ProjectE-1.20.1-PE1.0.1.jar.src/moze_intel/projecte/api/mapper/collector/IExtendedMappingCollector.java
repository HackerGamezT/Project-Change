package moze_intel.projecte.api.mapper.collector;

import java.util.Map;

public interface IExtendedMappingCollector<T, V extends Comparable<V>, A extends moze_intel.projecte.api.mapper.arithmetic.IValueArithmetic<?>> extends IMappingCollector<T, V> {
  void addConversion(int paramInt, T paramT, Map<T, Integer> paramMap, A paramA);
  
  void addConversion(int paramInt, T paramT, Iterable<T> paramIterable, A paramA);
  
  A getArithmetic();
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\mapper\collector\IExtendedMappingCollector.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */