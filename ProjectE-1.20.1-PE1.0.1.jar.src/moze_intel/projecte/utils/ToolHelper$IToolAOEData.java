package moze_intel.projecte.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

interface IToolAOEData {
  boolean isValid(Level paramLevel, BlockPos paramBlockPos, BlockState paramBlockState);
  
  default void persistData(Level level, BlockPos pos, BlockState state, Direction side) {}
  
  Iterable<BlockPos> getTargetPositions(BlockPos paramBlockPos, Direction paramDirection, int paramInt);
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\project\\utils\ToolHelper$IToolAOEData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */