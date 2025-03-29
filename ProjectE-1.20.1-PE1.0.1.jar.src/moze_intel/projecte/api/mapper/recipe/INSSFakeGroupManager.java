package moze_intel.projecte.api.mapper.recipe;

import java.util.Set;
import moze_intel.projecte.api.nss.NormalizedSimpleStack;
import net.minecraft.util.Tuple;

public interface INSSFakeGroupManager {
  Tuple<NormalizedSimpleStack, Boolean> getOrCreateFakeGroup(Set<NormalizedSimpleStack> paramSet);
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\mapper\recipe\INSSFakeGroupManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */