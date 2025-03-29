/*    */ package moze_intel.projecte.emc.mappers.recipe;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import moze_intel.projecte.api.mapper.collector.IMappingCollector;
/*    */ import moze_intel.projecte.api.mapper.recipe.INSSFakeGroupManager;
/*    */ import moze_intel.projecte.api.mapper.recipe.RecipeTypeMapper;
/*    */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*    */ import net.minecraft.core.RegistryAccess;
/*    */ import net.minecraft.world.item.crafting.Ingredient;
/*    */ import net.minecraft.world.item.crafting.Recipe;
/*    */ import net.minecraft.world.item.crafting.RecipeType;
/*    */ import net.minecraft.world.item.crafting.SmithingTransformRecipe;
/*    */ import net.minecraft.world.item.crafting.SmithingTrimRecipe;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @RecipeTypeMapper(priority = -2147483648)
/*    */ public class FallbackRecipeTypeMapper
/*    */   extends BaseRecipeTypeMapper
/*    */ {
/*    */   public String getName() {
/* 24 */     return "FallbackRecipeType";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 29 */     return "Fallback for default handling of recipes that extend ICraftingRecipe, AbstractCookingRecipe, SingleItemRecipe, or SmithingRecipe. This will catch modded extensions of the vanilla recipe classes, and if the VanillaRecipeTypes mapper is disabled, this mapper will still catch the vanilla recipes.";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canHandle(RecipeType<?> recipeType) {
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean handleRecipe(IMappingCollector<NormalizedSimpleStack, Long> mapper, Recipe<?> recipe, RegistryAccess registryAccess, INSSFakeGroupManager fakeGroupManager) {
/* 42 */     if (recipe instanceof net.minecraft.world.item.crafting.CraftingRecipe || recipe instanceof net.minecraft.world.item.crafting.AbstractCookingRecipe || recipe instanceof net.minecraft.world.item.crafting.SingleItemRecipe || recipe instanceof SmithingTransformRecipe || recipe instanceof SmithingTrimRecipe)
/*    */     {
/*    */       
/* 45 */       return super.handleRecipe(mapper, recipe, registryAccess, fakeGroupManager);
/*    */     }
/* 47 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Collection<Ingredient> getIngredients(Recipe<?> recipe) {
/* 52 */     Collection<Ingredient> ingredients = super.getIngredients(recipe);
/* 53 */     if (ingredients.isEmpty()) {
/*    */ 
/*    */       
/* 56 */       if (recipe instanceof SmithingTransformRecipe) { SmithingTransformRecipe transformRecipe = (SmithingTransformRecipe)recipe;
/* 57 */         return List.of(transformRecipe.f_265888_, transformRecipe.f_265907_, transformRecipe.f_265949_); }
/* 58 */        if (recipe instanceof SmithingTrimRecipe) { SmithingTrimRecipe trimRecipe = (SmithingTrimRecipe)recipe;
/* 59 */         return List.of(trimRecipe.f_266040_, trimRecipe.f_266053_, trimRecipe.f_265958_); }
/*    */     
/*    */     } 
/* 62 */     return ingredients;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\mappers\recipe\FallbackRecipeTypeMapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */