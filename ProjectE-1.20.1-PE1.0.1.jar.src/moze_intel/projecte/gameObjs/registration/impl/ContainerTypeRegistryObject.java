/*    */ package moze_intel.projecte.gameObjs.registration.impl;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.registration.WrappedRegistryObject;
/*    */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*    */ import net.minecraft.world.inventory.MenuType;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ 
/*    */ public class ContainerTypeRegistryObject<CONTAINER extends AbstractContainerMenu>
/*    */   extends WrappedRegistryObject<MenuType<CONTAINER>> {
/*    */   public ContainerTypeRegistryObject(RegistryObject<MenuType<CONTAINER>> registryObject) {
/* 11 */     super(registryObject);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registration\impl\ContainerTypeRegistryObject.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */