package moze_intel.projecte.api.capabilities.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IExtraFunction {
  boolean doExtraFunction(@NotNull ItemStack paramItemStack, @NotNull Player paramPlayer, @Nullable InteractionHand paramInteractionHand);
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\capabilities\item\IExtraFunction.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */