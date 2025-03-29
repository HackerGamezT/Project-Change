/*    */ package moze_intel.projecte.gameObjs.registration.impl;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Supplier;
/*    */ import moze_intel.projecte.gameObjs.registration.INamedEntry;
/*    */ import moze_intel.projecte.gameObjs.registration.WrappedDeferredRegister;
/*    */ import moze_intel.projecte.utils.WorldHelper;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.flag.FeatureFlags;
/*    */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*    */ import net.minecraft.world.inventory.MenuType;
/*    */ import net.minecraft.world.level.BlockGetter;
/*    */ import net.minecraft.world.level.block.entity.BlockEntity;
/*    */ import net.minecraftforge.fml.DistExecutor;
/*    */ import net.minecraftforge.network.IContainerFactory;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ 
/*    */ public class ContainerTypeDeferredRegister extends WrappedDeferredRegister<MenuType<?>> {
/*    */   public ContainerTypeDeferredRegister(String modid) {
/* 21 */     super(ForgeRegistries.MENU_TYPES, modid);
/*    */   }
/*    */ 
/*    */   
/*    */   public <CONTAINER extends AbstractContainerMenu, BE extends BlockEntity> ContainerTypeRegistryObject<CONTAINER> register(INamedEntry nameProvider, Class<BE> blockEntityClass, IBlockEntityContainerFactory<CONTAINER, BE> factory) {
/* 26 */     return register(nameProvider, (id, inv, buf) -> factory.create(id, inv, getBlockEntityFromBuf(buf, blockEntityClass)));
/*    */   }
/*    */   
/*    */   public <CONTAINER extends AbstractContainerMenu> ContainerTypeRegistryObject<CONTAINER> register(INamedEntry nameProvider, IContainerFactory<CONTAINER> factory) {
/* 30 */     return register(nameProvider.getInternalRegistryName(), factory);
/*    */   }
/*    */   
/*    */   public <CONTAINER extends AbstractContainerMenu> ContainerTypeRegistryObject<CONTAINER> register(String name, IContainerFactory<CONTAINER> factory) {
/* 34 */     return (ContainerTypeRegistryObject<CONTAINER>)register(name, () -> new MenuType((MenuType.MenuSupplier)factory, FeatureFlags.f_244377_), ContainerTypeRegistryObject::new);
/*    */   }
/*    */   
/*    */   private static <BE extends BlockEntity> BE getBlockEntityFromBuf(FriendlyByteBuf buf, Class<BE> type) {
/* 38 */     if (buf == null) {
/* 39 */       throw new IllegalArgumentException("Null packet buffer");
/*    */     }
/* 41 */     return (BE)DistExecutor.unsafeRunForDist(() -> (), () -> ());
/*    */   }
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface IBlockEntityContainerFactory<CONTAINER extends AbstractContainerMenu, BE extends BlockEntity> {
/*    */     CONTAINER create(int param1Int, Inventory param1Inventory, BE param1BE);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registration\impl\ContainerTypeDeferredRegister.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */