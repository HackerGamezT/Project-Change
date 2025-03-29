package moze_intel.projecte.api.capabilities.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

public interface IAlchBagItem {
  boolean updateInAlchBag(@NotNull IItemHandler paramIItemHandler, @NotNull Player paramPlayer, @NotNull ItemStack paramItemStack);
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\capabilities\item\IAlchBagItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */