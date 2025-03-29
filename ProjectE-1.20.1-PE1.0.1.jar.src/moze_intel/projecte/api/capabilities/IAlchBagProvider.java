package moze_intel.projecte.api.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

public interface IAlchBagProvider extends INBTSerializable<CompoundTag> {
  @NotNull
  IItemHandler getBag(@NotNull DyeColor paramDyeColor);
  
  void sync(DyeColor paramDyeColor, @NotNull ServerPlayer paramServerPlayer);
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\capabilities\IAlchBagProvider.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */