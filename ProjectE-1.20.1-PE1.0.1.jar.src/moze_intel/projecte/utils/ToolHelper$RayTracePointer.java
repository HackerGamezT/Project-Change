package moze_intel.projecte.utils;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

@FunctionalInterface
public interface RayTracePointer {
  HitResult rayTrace(Level paramLevel, Player paramPlayer, ClipContext.Fluid paramFluid);
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\project\\utils\ToolHelper$RayTracePointer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */