/*    */ package moze_intel.projecte.gameObjs.registration.impl;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.registration.WrappedRegistryObject;
/*    */ import net.minecraft.world.level.block.entity.BlockEntity;
/*    */ import net.minecraft.world.level.block.entity.BlockEntityTicker;
/*    */ import net.minecraft.world.level.block.entity.BlockEntityType;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class BlockEntityTypeRegistryObject<BE extends BlockEntity>
/*    */   extends WrappedRegistryObject<BlockEntityType<BE>> {
/*    */   @Nullable
/*    */   private BlockEntityTicker<BE> clientTicker;
/*    */   @Nullable
/*    */   private BlockEntityTicker<BE> serverTicker;
/*    */   
/*    */   public BlockEntityTypeRegistryObject(RegistryObject<BlockEntityType<BE>> registryObject) {
/* 18 */     super(registryObject);
/*    */   }
/*    */ 
/*    */   
/*    */   BlockEntityTypeRegistryObject<BE> setRegistryObject(RegistryObject<BlockEntityType<BE>> registryObject) {
/* 23 */     this.registryObject = registryObject;
/* 24 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   BlockEntityTypeRegistryObject<BE> clientTicker(BlockEntityTicker<BE> ticker) {
/* 29 */     this.clientTicker = ticker;
/* 30 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   BlockEntityTypeRegistryObject<BE> serverTicker(BlockEntityTicker<BE> ticker) {
/* 35 */     this.serverTicker = ticker;
/* 36 */     return this;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public BlockEntityTicker<BE> getTicker(boolean isClient) {
/* 41 */     return isClient ? this.clientTicker : this.serverTicker;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registration\impl\BlockEntityTypeRegistryObject.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */