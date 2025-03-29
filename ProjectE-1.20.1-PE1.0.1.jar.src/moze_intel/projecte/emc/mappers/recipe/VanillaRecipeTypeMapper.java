/*    */ package moze_intel.projecte.emc.mappers.recipe;
/*    */ 
/*    */ import moze_intel.projecte.api.mapper.recipe.RecipeTypeMapper;
/*    */ import net.minecraft.world.item.crafting.RecipeType;
/*    */ 
/*    */ @RecipeTypeMapper
/*    */ public class VanillaRecipeTypeMapper
/*    */   extends BaseRecipeTypeMapper
/*    */ {
/*    */   public String getName() {
/* 11 */     return "VanillaRecipeTypes";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 16 */     return "Maps the different vanilla recipe types.";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canHandle(RecipeType<?> recipeType) {
/* 21 */     return (recipeType == RecipeType.f_44107_ || recipeType == RecipeType.f_44108_ || recipeType == RecipeType.f_44109_ || recipeType == RecipeType.f_44110_ || recipeType == RecipeType.f_44111_ || recipeType == RecipeType.f_44112_);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\mappers\recipe\VanillaRecipeTypeMapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */