/*    */ package moze_intel.projecte.emc.mappers.recipe;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import moze_intel.projecte.api.mapper.recipe.RecipeTypeMapper;
/*    */ import net.minecraft.world.item.crafting.Ingredient;
/*    */ import net.minecraft.world.item.crafting.Recipe;
/*    */ import net.minecraft.world.item.crafting.RecipeType;
/*    */ import net.minecraft.world.item.crafting.SmithingTransformRecipe;
/*    */ import net.minecraft.world.item.crafting.SmithingTrimRecipe;
/*    */ 
/*    */ @RecipeTypeMapper
/*    */ public class SmithingRecipeMapper
/*    */   extends BaseRecipeTypeMapper
/*    */ {
/*    */   public String getName() {
/* 18 */     return "Smithing";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 23 */     return "Maps smithing recipes.";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canHandle(RecipeType<?> recipeType) {
/* 28 */     return (recipeType == RecipeType.f_44113_);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Collection<Ingredient> getIngredients(Recipe<?> recipe) {
/* 34 */     if (recipe instanceof SmithingTransformRecipe) { SmithingTransformRecipe transformRecipe = (SmithingTransformRecipe)recipe;
/* 35 */       return List.of(transformRecipe.f_265888_, transformRecipe.f_265907_, transformRecipe.f_265949_); }
/* 36 */      if (recipe instanceof SmithingTrimRecipe) { SmithingTrimRecipe trimRecipe = (SmithingTrimRecipe)recipe;
/* 37 */       return List.of(trimRecipe.f_266040_, trimRecipe.f_266053_, trimRecipe.f_265958_); }
/*    */     
/* 39 */     return Collections.emptyList();
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\mappers\recipe\SmithingRecipeMapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */