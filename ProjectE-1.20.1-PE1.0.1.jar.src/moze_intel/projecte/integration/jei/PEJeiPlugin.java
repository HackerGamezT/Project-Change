/*    */ package moze_intel.projecte.integration.jei;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import mezz.jei.api.IModPlugin;
/*    */ import mezz.jei.api.JeiPlugin;
/*    */ import mezz.jei.api.constants.RecipeTypes;
/*    */ import mezz.jei.api.recipe.RecipeType;
/*    */ import mezz.jei.api.recipe.category.IRecipeCategory;
/*    */ import mezz.jei.api.registration.IGuiHandlerRegistration;
/*    */ import mezz.jei.api.registration.IRecipeCatalystRegistration;
/*    */ import mezz.jei.api.registration.IRecipeCategoryRegistration;
/*    */ import mezz.jei.api.registration.IRecipeRegistration;
/*    */ import mezz.jei.api.registration.IRecipeTransferRegistration;
/*    */ import mezz.jei.api.runtime.IJeiRuntime;
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.emc.FuelMapper;
/*    */ import moze_intel.projecte.gameObjs.container.PhilosStoneContainer;
/*    */ import moze_intel.projecte.gameObjs.gui.AbstractCollectorScreen;
/*    */ import moze_intel.projecte.gameObjs.gui.GUIDMFurnace;
/*    */ import moze_intel.projecte.gameObjs.registries.PEBlocks;
/*    */ import moze_intel.projecte.gameObjs.registries.PEItems;
/*    */ import moze_intel.projecte.integration.jei.collectors.CollectorRecipeCategory;
/*    */ import moze_intel.projecte.integration.jei.collectors.FuelUpgradeRecipe;
/*    */ import moze_intel.projecte.integration.jei.world_transmute.WorldTransmuteRecipeCategory;
/*    */ import moze_intel.projecte.utils.EMCHelper;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.inventory.MenuType;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.ItemLike;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ @JeiPlugin
/*    */ public class PEJeiPlugin implements IModPlugin {
/* 35 */   private static final ResourceLocation UID = PECore.rl("main");
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ResourceLocation getPluginUid() {
/* 40 */     return UID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerCategories(IRecipeCategoryRegistration registry) {
/* 45 */     registry.addRecipeCategories(new IRecipeCategory[] { (IRecipeCategory)new WorldTransmuteRecipeCategory(registry.getJeiHelpers().getGuiHelper()) });
/* 46 */     registry.addRecipeCategories(new IRecipeCategory[] { (IRecipeCategory)new CollectorRecipeCategory(registry.getJeiHelpers().getGuiHelper()) });
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
/* 51 */     registration.addRecipeTransferHandler(PhilosStoneContainer.class, MenuType.f_39968_, RecipeTypes.CRAFTING, 1, 9, 10, 36);
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
/* 56 */     registry.addRecipeCatalyst(new ItemStack((ItemLike)PEItems.PHILOSOPHERS_STONE), new RecipeType[] { RecipeTypes.CRAFTING, WorldTransmuteRecipeCategory.RECIPE_TYPE });
/* 57 */     registry.addRecipeCatalyst(new ItemStack((ItemLike)PEBlocks.COLLECTOR), new RecipeType[] { CollectorRecipeCategory.RECIPE_TYPE });
/* 58 */     registry.addRecipeCatalyst(new ItemStack((ItemLike)PEBlocks.COLLECTOR_MK2), new RecipeType[] { CollectorRecipeCategory.RECIPE_TYPE });
/* 59 */     registry.addRecipeCatalyst(new ItemStack((ItemLike)PEBlocks.COLLECTOR_MK3), new RecipeType[] { CollectorRecipeCategory.RECIPE_TYPE });
/* 60 */     registry.addRecipeCatalyst(new ItemStack((ItemLike)PEBlocks.DARK_MATTER_FURNACE), new RecipeType[] { RecipeTypes.SMELTING, RecipeTypes.FUELING });
/* 61 */     registry.addRecipeCatalyst(new ItemStack((ItemLike)PEBlocks.RED_MATTER_FURNACE), new RecipeType[] { RecipeTypes.SMELTING, RecipeTypes.FUELING });
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerGuiHandlers(IGuiHandlerRegistration registry) {
/* 66 */     registry.addRecipeClickArea(GUIDMFurnace.class, 73, 34, 25, 16, new RecipeType[] { RecipeTypes.SMELTING, RecipeTypes.FUELING });
/* 67 */     registry.addRecipeClickArea(GUIRMFurnace.class, 88, 35, 25, 17, new RecipeType[] { RecipeTypes.SMELTING, RecipeTypes.FUELING });
/* 68 */     registry.addRecipeClickArea(AbstractCollectorScreen.MK1.class, 138, 31, 10, 24, new RecipeType[] { CollectorRecipeCategory.RECIPE_TYPE });
/* 69 */     registry.addRecipeClickArea(AbstractCollectorScreen.MK2.class, 154, 31, 10, 24, new RecipeType[] { CollectorRecipeCategory.RECIPE_TYPE });
/* 70 */     registry.addRecipeClickArea(AbstractCollectorScreen.MK3.class, 172, 31, 10, 24, new RecipeType[] { CollectorRecipeCategory.RECIPE_TYPE });
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerRecipes(IRecipeRegistration registry) {
/* 75 */     registry.addRecipes(WorldTransmuteRecipeCategory.RECIPE_TYPE, WorldTransmuteRecipeCategory.getAllTransmutations());
/*    */   }
/*    */ 
/*    */   
/*    */   public void onRuntimeAvailable(@NotNull IJeiRuntime jeiRuntime) {
/* 80 */     List<FuelUpgradeRecipe> recipes = new ArrayList<>();
/* 81 */     for (Item i : FuelMapper.getFuelMap()) {
/* 82 */       ItemStack stack = new ItemStack((ItemLike)i);
/* 83 */       ItemStack fuelUpgrade = FuelMapper.getFuelUpgrade(stack);
/* 84 */       if (EMCHelper.getEmcValue(stack) <= EMCHelper.getEmcValue(fuelUpgrade)) {
/* 85 */         recipes.add(new FuelUpgradeRecipe(stack, fuelUpgrade));
/*    */       }
/*    */     } 
/* 88 */     jeiRuntime.getRecipeManager().addRecipes(CollectorRecipeCategory.RECIPE_TYPE, recipes);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\jei\PEJeiPlugin.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */