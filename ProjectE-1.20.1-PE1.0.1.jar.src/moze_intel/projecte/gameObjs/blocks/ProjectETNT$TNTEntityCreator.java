package moze_intel.projecte.gameObjs.blocks;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface TNTEntityCreator {
  PrimedTnt create(Level paramLevel, double paramDouble1, double paramDouble2, double paramDouble3, @Nullable LivingEntity paramLivingEntity);
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\blocks\ProjectETNT$TNTEntityCreator.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */