/*    */ package moze_intel.projecte.gameObjs.items.blocks;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.EnumFuelType;
/*    */ import net.minecraft.world.item.BlockItem;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.item.crafting.RecipeType;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class ItemFuelBlock extends BlockItem {
/*    */   @NotNull
/*    */   private final EnumFuelType type;
/*    */   
/*    */   public ItemFuelBlock(Block block, Item.Properties props, @NotNull EnumFuelType type) {
/* 17 */     super(block, props);
/* 18 */     this.type = type;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBurnTime(ItemStack stack, @Nullable RecipeType<?> recipeType) {
/* 23 */     int burnTime = this.type.getBurnTime();
/* 24 */     return (burnTime == -1) ? -1 : (burnTime * 9);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\blocks\ItemFuelBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */