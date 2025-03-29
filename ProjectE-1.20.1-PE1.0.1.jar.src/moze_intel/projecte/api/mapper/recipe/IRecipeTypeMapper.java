/*    */ package moze_intel.projecte.api.mapper.recipe;
/*    */ 
/*    */ import moze_intel.projecte.api.mapper.collector.IMappingCollector;
/*    */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*    */ import net.minecraft.core.RegistryAccess;
/*    */ import net.minecraft.world.item.crafting.Recipe;
/*    */ import net.minecraft.world.item.crafting.RecipeType;
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
/*    */ public interface IRecipeTypeMapper
/*    */ {
/*    */   String getName();
/*    */   
/*    */   String getDescription();
/*    */   
/*    */   default boolean isAvailable() {
/* 36 */     return true;
/*    */   }
/*    */   
/*    */   boolean canHandle(RecipeType<?> paramRecipeType);
/*    */   
/*    */   boolean handleRecipe(IMappingCollector<NormalizedSimpleStack, Long> paramIMappingCollector, Recipe<?> paramRecipe, RegistryAccess paramRegistryAccess, INSSFakeGroupManager paramINSSFakeGroupManager);
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\mapper\recipe\IRecipeTypeMapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */