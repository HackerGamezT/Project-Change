package moze_intel.projecte.api.mapper.generator;

import java.util.Map;

public interface IValueGenerator<T, V extends Comparable<V>> {
  Map<T, V> generateValues();
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\mapper\generator\IValueGenerator.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */