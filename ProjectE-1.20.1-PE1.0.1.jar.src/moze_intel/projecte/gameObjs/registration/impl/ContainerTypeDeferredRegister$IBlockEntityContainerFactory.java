package moze_intel.projecte.gameObjs.registration.impl;

import net.minecraft.world.entity.player.Inventory;

@FunctionalInterface
public interface IBlockEntityContainerFactory<CONTAINER extends net.minecraft.world.inventory.AbstractContainerMenu, BE extends net.minecraft.world.level.block.entity.BlockEntity> {
  CONTAINER create(int paramInt, Inventory paramInventory, BE paramBE);
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registration\impl\ContainerTypeDeferredRegister$IBlockEntityContainerFactory.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */