package moze_intel.projecte.api.nss;

import java.util.function.Consumer;

public interface NSSTag extends NormalizedSimpleStack {
  boolean representsTag();
  
  void forEachElement(Consumer<NormalizedSimpleStack> paramConsumer);
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\nss\NSSTag.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */