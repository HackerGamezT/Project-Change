package moze_intel.projecte.capability;

import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IItemCapabilitySerializable extends INBTSerializable<Tag> {
  String getStorageKey();
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\capability\IItemCapabilitySerializable.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */