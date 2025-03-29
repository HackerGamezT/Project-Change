/*    */ package moze_intel.projecte.gameObjs.registration.impl;
/*    */ 
/*    */ import java.util.function.BiFunction;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Supplier;
/*    */ import moze_intel.projecte.gameObjs.registration.DoubleDeferredRegister;
/*    */ import net.minecraft.core.Direction;
/*    */ import net.minecraft.world.item.BlockItem;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.StandingAndWallBlockItem;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ 
/*    */ public class BlockDeferredRegister
/*    */   extends DoubleDeferredRegister<Block, Item>
/*    */ {
/*    */   public BlockDeferredRegister(String modid) {
/* 20 */     super(ForgeRegistries.BLOCKS, ForgeRegistries.ITEMS, modid);
/*    */   }
/*    */   
/*    */   public BlockRegistryObject<Block, BlockItem> register(String name, BlockBehaviour.Properties properties) {
/* 24 */     return registerDefaultProperties(name, () -> new Block(properties), BlockItem::new);
/*    */   }
/*    */   
/*    */   public <BLOCK extends Block> BlockRegistryObject<BLOCK, BlockItem> register(String name, Supplier<? extends BLOCK> blockSupplier) {
/* 28 */     return registerDefaultProperties(name, blockSupplier, BlockItem::new);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public <BLOCK extends Block, WALL_BLOCK extends Block> BlockRegistryObject.WallOrFloorBlockRegistryObject<BLOCK, WALL_BLOCK, StandingAndWallBlockItem> registerWallOrFloorItem(String name, Function<BlockBehaviour.Properties, BLOCK> blockSupplier, Function<BlockBehaviour.Properties, WALL_BLOCK> wallBlockSupplier, BlockBehaviour.Properties baseProperties) {
/* 34 */     RegistryObject<BLOCK> primaryObject = this.primaryRegister.register(name, () -> (Block)blockSupplier.apply(baseProperties));
/* 35 */     RegistryObject<WALL_BLOCK> wallObject = this.primaryRegister.register("wall_" + name, () -> (Block)wallBlockSupplier.apply(baseProperties.lootFrom((Supplier)primaryObject)));
/* 36 */     return new BlockRegistryObject.WallOrFloorBlockRegistryObject<>(primaryObject, wallObject, this.secondaryRegister.register(name, () -> new StandingAndWallBlockItem((Block)primaryObject.get(), (Block)wallObject.get(), new Item.Properties(), Direction.DOWN)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerDefaultProperties(String name, Supplier<? extends BLOCK> blockSupplier, BiFunction<BLOCK, Item.Properties, ITEM> itemCreator) {
/* 42 */     return register(name, blockSupplier, block -> (BlockItem)itemCreator.apply(block, new Item.Properties()));
/*    */   }
/*    */ 
/*    */   
/*    */   public <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> register(String name, Supplier<? extends BLOCK> blockSupplier, Function<BLOCK, ITEM> itemCreator) {
/* 47 */     return (BlockRegistryObject<BLOCK, ITEM>)register(name, blockSupplier, itemCreator, BlockRegistryObject::new);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registration\impl\BlockDeferredRegister.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */