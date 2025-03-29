package moze_intel.projecte.api.mapper.collector;

import java.util.Map;

public interface IMappingCollector<T, V extends Comparable<V>> {
  void addConversion(int paramInt, T paramT, Map<T, Integer> paramMap);
  
  void addConversion(int paramInt, T paramT, Iterable<T> paramIterable);
  
  void setValueBefore(T paramT, V paramV);
  
  void setValueAfter(T paramT, V paramV);
  
  void setValueFromConversion(int paramInt, T paramT, Iterable<T> paramIterable);
  
  void setValueFromConversion(int paramInt, T paramT, Map<T, Integer> paramMap);
  
  void finishCollection();
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\mapper\collector\IMappingCollector.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */