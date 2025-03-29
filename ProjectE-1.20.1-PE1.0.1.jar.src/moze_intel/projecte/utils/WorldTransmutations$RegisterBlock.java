package moze_intel.projecte.utils;

import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

@FunctionalInterface
interface RegisterBlock {
  void register(Block paramBlock1, Block paramBlock2, @Nullable Block paramBlock3);
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\project\\utils\WorldTransmutations$RegisterBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */