package moze_intel.projecte.api.nss;

import com.google.gson.JsonParseException;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface NSSCreator {
  @NotNull
  NormalizedSimpleStack create(String paramString) throws JsonParseException;
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\nss\NSSCreator.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */