package moze_intel.projecte.api.block_entity;

import net.minecraft.world.phys.AABB;

public interface IDMPedestal {
  int getActivityCooldown();
  
  void setActivityCooldown(int paramInt);
  
  void decrementActivityCooldown();
  
  AABB getEffectBounds();
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\block_entity\IDMPedestal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */