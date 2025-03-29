package moze_intel.projecte.api.capabilities.item;

import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public interface IPedestalItem {
  <PEDESTAL extends net.minecraft.world.level.block.entity.BlockEntity & moze_intel.projecte.api.block_entity.IDMPedestal> boolean updateInPedestal(@NotNull ItemStack paramItemStack, @NotNull Level paramLevel, @NotNull BlockPos paramBlockPos, @NotNull PEDESTAL paramPEDESTAL);
  
  @NotNull
  List<Component> getPedestalDescription();
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\capabilities\item\IPedestalItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */