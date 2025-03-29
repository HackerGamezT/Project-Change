/*    */ package moze_intel.projecte.gameObjs.registries;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import moze_intel.projecte.gameObjs.customRecipes.PhiloStoneSmeltingRecipe;
/*    */ import moze_intel.projecte.gameObjs.customRecipes.RecipeShapelessKleinStar;
/*    */ import moze_intel.projecte.gameObjs.customRecipes.RecipesCovalenceRepair;
/*    */ import moze_intel.projecte.gameObjs.registration.impl.IRecipeSerializerDeferredRegister;
/*    */ import moze_intel.projecte.gameObjs.registration.impl.IRecipeSerializerRegistryObject;
/*    */ import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
/*    */ 
/*    */ public class PERecipeSerializers
/*    */ {
/* 13 */   public static final IRecipeSerializerDeferredRegister RECIPE_SERIALIZERS = new IRecipeSerializerDeferredRegister("projecte");
/*    */   
/* 15 */   public static final IRecipeSerializerRegistryObject<RecipesCovalenceRepair, SimpleCraftingRecipeSerializer<RecipesCovalenceRepair>> COVALENCE_REPAIR = RECIPE_SERIALIZERS.register("covalence_repair", () -> new SimpleCraftingRecipeSerializer(RecipesCovalenceRepair::new));
/* 16 */   public static final IRecipeSerializerRegistryObject<RecipeShapelessKleinStar, RecipeShapelessKleinStar.Serializer> KLEIN = RECIPE_SERIALIZERS.register("crafting_shapeless_kleinstar", moze_intel.projecte.gameObjs.customRecipes.RecipeShapelessKleinStar.Serializer::new);
/* 17 */   public static final IRecipeSerializerRegistryObject<PhiloStoneSmeltingRecipe, SimpleCraftingRecipeSerializer<PhiloStoneSmeltingRecipe>> PHILO_STONE_SMELTING = RECIPE_SERIALIZERS.register("philo_stone_smelting", () -> new SimpleCraftingRecipeSerializer(PhiloStoneSmeltingRecipe::new));
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registries\PERecipeSerializers.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */