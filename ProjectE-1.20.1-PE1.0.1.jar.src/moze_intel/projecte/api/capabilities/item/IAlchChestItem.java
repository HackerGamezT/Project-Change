package moze_intel.projecte.api.capabilities.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public interface IAlchChestItem {
  boolean updateInAlchChest(@NotNull Level paramLevel, @NotNull BlockPos paramBlockPos, @NotNull ItemStack paramItemStack);
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\capabilities\item\IAlchChestItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */