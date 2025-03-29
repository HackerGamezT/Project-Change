/*    */ package moze_intel.projecte.gameObjs.items;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.EnumFuelType;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.item.crafting.RecipeType;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class AlchemicalFuel extends ItemPE {
/*    */   @NotNull
/*    */   private final EnumFuelType fuelType;
/*    */   
/*    */   public AlchemicalFuel(Item.Properties props, @NotNull EnumFuelType type) {
/* 15 */     super(props);
/* 16 */     this.fuelType = type;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBurnTime(ItemStack stack, @Nullable RecipeType<?> recipeType) {
/* 21 */     return this.fuelType.getBurnTime();
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\AlchemicalFuel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */