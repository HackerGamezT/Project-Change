/*    */ package moze_intel.projecte.gameObjs.registration.impl;
/*    */ import java.util.Objects;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Supplier;
/*    */ import moze_intel.projecte.gameObjs.registration.WrappedRegistryObject;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.entity.BlockEntity;
/*    */ import net.minecraft.world.level.block.entity.BlockEntityTicker;
/*    */ import net.minecraft.world.level.block.entity.BlockEntityType;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class BlockEntityTypeDeferredRegister extends WrappedDeferredRegister<BlockEntityType<?>> {
/*    */   public BlockEntityTypeDeferredRegister(String modid) {
/* 15 */     super(ForgeRegistries.BLOCK_ENTITY_TYPES, modid);
/*    */   }
/*    */   
/*    */   public <BE extends BlockEntity> BlockEntityTypeBuilder<BE> builder(BlockRegistryObject<?, ?> block, BlockEntityType.BlockEntitySupplier<? extends BE> factory) {
/* 19 */     return new BlockEntityTypeBuilder<>(block, factory);
/*    */   }
/*    */   
/*    */   public class BlockEntityTypeBuilder<BE extends BlockEntity>
/*    */   {
/*    */     private final BlockRegistryObject<?, ?> block;
/*    */     private final BlockEntityType.BlockEntitySupplier<? extends BE> factory;
/*    */     @Nullable
/*    */     private BlockEntityTicker<BE> clientTicker;
/*    */     @Nullable
/*    */     private BlockEntityTicker<BE> serverTicker;
/*    */     
/*    */     private BlockEntityTypeBuilder(BlockRegistryObject<?, ?> block, BlockEntityType.BlockEntitySupplier<? extends BE> factory) {
/* 32 */       this.block = block;
/* 33 */       this.factory = factory;
/*    */     }
/*    */     
/*    */     public BlockEntityTypeBuilder<BE> clientTicker(BlockEntityTicker<BE> ticker) {
/* 37 */       if (this.clientTicker != null) {
/* 38 */         throw new IllegalStateException("Client ticker may only be set once.");
/*    */       }
/* 40 */       this.clientTicker = ticker;
/* 41 */       return this;
/*    */     }
/*    */     
/*    */     public BlockEntityTypeBuilder<BE> serverTicker(BlockEntityTicker<BE> ticker) {
/* 45 */       if (this.serverTicker != null) {
/* 46 */         throw new IllegalStateException("Server ticker may only be set once.");
/*    */       }
/* 48 */       this.serverTicker = ticker;
/* 49 */       return this;
/*    */     }
/*    */     
/*    */     public BlockEntityTypeBuilder<BE> commonTicker(BlockEntityTicker<BE> ticker) {
/* 53 */       return clientTicker(ticker).serverTicker(ticker);
/*    */     }
/*    */ 
/*    */     
/*    */     public BlockEntityTypeRegistryObject<BE> build() {
/* 58 */       BlockEntityTypeRegistryObject<BE> registryObject = new BlockEntityTypeRegistryObject<>(null);
/* 59 */       registryObject.clientTicker(this.clientTicker).serverTicker(this.serverTicker);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 68 */       Objects.requireNonNull(registryObject); return (BlockEntityTypeRegistryObject<BE>)BlockEntityTypeDeferredRegister.this.register(this.block.getInternalRegistryName(), () -> { Block[] validBlocks; BlockRegistryObject<?, ?> patt2485$temp = this.block; if (patt2485$temp instanceof BlockRegistryObject.WallOrFloorBlockRegistryObject) { BlockRegistryObject.WallOrFloorBlockRegistryObject<?, ?, ?> wallOrFloorBlock = (BlockRegistryObject.WallOrFloorBlockRegistryObject)patt2485$temp; validBlocks = new Block[] { (Block)this.block.getBlock(), (Block)wallOrFloorBlock.getWallBlock() }, ; } else { validBlocks = new Block[] { (Block)this.block.getBlock() }, ; }  return BlockEntityType.Builder.m_155273_(this.factory, validBlocks).m_58966_(null); }registryObject::setRegistryObject);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registration\impl\BlockEntityTypeDeferredRegister.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */