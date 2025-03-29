/*    */ package moze_intel.projecte.gameObjs.registration.impl;
/*    */ 
/*    */ import net.minecraft.world.item.StandingAndWallBlockItem;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WallOrFloorBlockRegistryObject<BLOCK extends Block, WALL_BLOCK extends Block, ITEM extends StandingAndWallBlockItem>
/*    */   extends BlockRegistryObject<BLOCK, ITEM>
/*    */ {
/*    */   @NotNull
/*    */   private final RegistryObject<WALL_BLOCK> wallRO;
/*    */   
/*    */   public WallOrFloorBlockRegistryObject(RegistryObject<BLOCK> blockRegistryObject, RegistryObject<WALL_BLOCK> wallBlockRegistryObject, RegistryObject<ITEM> itemRegistryObject) {
/* 43 */     super(blockRegistryObject, itemRegistryObject);
/* 44 */     this.wallRO = wallBlockRegistryObject;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public WALL_BLOCK getWallBlock() {
/* 49 */     return (WALL_BLOCK)this.wallRO.get();
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registration\impl\BlockRegistryObject$WallOrFloorBlockRegistryObject.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */