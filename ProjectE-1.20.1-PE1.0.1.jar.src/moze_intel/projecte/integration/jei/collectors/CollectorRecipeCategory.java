/*    */ package moze_intel.projecte.integration.jei.collectors;
/*    */ import mezz.jei.api.constants.VanillaTypes;
/*    */ import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
/*    */ import mezz.jei.api.gui.drawable.IDrawable;
/*    */ import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
/*    */ import mezz.jei.api.helpers.IGuiHelper;
/*    */ import mezz.jei.api.ingredients.IIngredientType;
/*    */ import mezz.jei.api.recipe.IFocusGroup;
/*    */ import mezz.jei.api.recipe.RecipeIngredientRole;
/*    */ import mezz.jei.api.recipe.RecipeType;
/*    */ import mezz.jei.api.recipe.category.IRecipeCategory;
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.gameObjs.registries.PEBlocks;
/*    */ import moze_intel.projecte.utils.text.PELang;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.Font;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.network.chat.MutableComponent;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class CollectorRecipeCategory implements IRecipeCategory<FuelUpgradeRecipe> {
/* 24 */   public static final RecipeType<FuelUpgradeRecipe> RECIPE_TYPE = new RecipeType(PECore.rl("collector"), FuelUpgradeRecipe.class);
/*    */   private final IDrawable background;
/*    */   private final IDrawable arrow;
/*    */   private final IDrawable icon;
/*    */   
/*    */   public CollectorRecipeCategory(IGuiHelper guiHelper) {
/* 30 */     this.background = (IDrawable)guiHelper.createBlankDrawable(135, 48);
/* 31 */     this.arrow = (IDrawable)guiHelper.drawableBuilder(PECore.rl("textures/gui/arrow.png"), 0, 0, 22, 15).setTextureSize(32, 32).build();
/* 32 */     this.icon = guiHelper.createDrawableIngredient((IIngredientType)VanillaTypes.ITEM_STACK, new ItemStack((ItemLike)PEBlocks.COLLECTOR));
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public RecipeType<FuelUpgradeRecipe> getRecipeType() {
/* 38 */     return RECIPE_TYPE;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Component getTitle() {
/* 44 */     return (Component)PELang.JEI_COLLECTOR.translate(new Object[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public IDrawable getBackground() {
/* 50 */     return this.background;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public IDrawable getIcon() {
/* 56 */     return this.icon;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull FuelUpgradeRecipe recipe, @NotNull IFocusGroup focuses) {
/* 61 */     builder.addSlot(RecipeIngredientRole.INPUT, 16, 16)
/* 62 */       .addItemStack(recipe.input());
/* 63 */     builder.addSlot(RecipeIngredientRole.OUTPUT, 104, 16)
/* 64 */       .addItemStack(recipe.output());
/*    */   }
/*    */ 
/*    */   
/*    */   public void draw(FuelUpgradeRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView, @NotNull GuiGraphics graphics, double mouseX, double mouseY) {
/* 69 */     MutableComponent mutableComponent = PELang.EMC.translate(new Object[] { Long.valueOf(recipe.upgradeEMC()) });
/* 70 */     Font fontRenderer = (Minecraft.m_91087_()).f_91062_;
/* 71 */     int stringWidth = fontRenderer.m_92852_((FormattedText)mutableComponent);
/* 72 */     graphics.drawString(fontRenderer, mutableComponent.m_7532_(), (getBackground().getWidth() - stringWidth) / 2.0F, 5.0F, 8421504, false);
/* 73 */     this.arrow.draw(graphics, 55, 18);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\jei\collectors\CollectorRecipeCategory.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */