/*     */ package moze_intel.projecte.integration.jei.world_transmute;
/*     */ import com.mojang.datafixers.util.Either;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import mezz.jei.api.constants.VanillaTypes;
/*     */ import mezz.jei.api.forge.ForgeTypes;
/*     */ import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
/*     */ import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
/*     */ import mezz.jei.api.gui.drawable.IDrawable;
/*     */ import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
/*     */ import mezz.jei.api.helpers.IGuiHelper;
/*     */ import mezz.jei.api.ingredients.IIngredientType;
/*     */ import mezz.jei.api.recipe.IFocusGroup;
/*     */ import mezz.jei.api.recipe.RecipeIngredientRole;
/*     */ import mezz.jei.api.recipe.RecipeType;
/*     */ import mezz.jei.api.recipe.category.IRecipeCategory;
/*     */ import moze_intel.projecte.PECore;
/*     */ import moze_intel.projecte.api.imc.WorldTransmutationEntry;
/*     */ import moze_intel.projecte.gameObjs.registries.PEItems;
/*     */ import moze_intel.projecte.utils.WorldTransmutations;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.client.gui.GuiGraphics;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class WorldTransmuteRecipeCategory implements IRecipeCategory<WorldTransmuteEntry> {
/*  32 */   public static final RecipeType<WorldTransmuteEntry> RECIPE_TYPE = new RecipeType(PECore.rl("world_transmutation"), WorldTransmuteEntry.class);
/*     */   private final IDrawable background;
/*     */   private final IDrawable arrow;
/*     */   private final IDrawable icon;
/*     */   
/*     */   public WorldTransmuteRecipeCategory(IGuiHelper guiHelper) {
/*  38 */     this.background = (IDrawable)guiHelper.createBlankDrawable(135, 48);
/*  39 */     this.arrow = (IDrawable)guiHelper.drawableBuilder(PECore.rl("textures/gui/arrow.png"), 0, 0, 22, 15).setTextureSize(32, 32).build();
/*  40 */     this.icon = guiHelper.createDrawableIngredient((IIngredientType)VanillaTypes.ITEM_STACK, new ItemStack((ItemLike)PEItems.PHILOSOPHERS_STONE));
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public RecipeType<WorldTransmuteEntry> getRecipeType() {
/*  46 */     return RECIPE_TYPE;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Component getTitle() {
/*  52 */     return (Component)PELang.WORLD_TRANSMUTE.translate(new Object[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public IDrawable getBackground() {
/*  58 */     return this.background;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public IDrawable getIcon() {
/*  64 */     return this.icon;
/*     */   }
/*     */ 
/*     */   
/*     */   public void draw(@NotNull WorldTransmuteEntry recipe, @NotNull IRecipeSlotsView recipeSlotsView, @NotNull GuiGraphics graphics, double mouseX, double mouseY) {
/*  69 */     this.arrow.draw(graphics, 55, 18);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull WorldTransmuteEntry recipe, @NotNull IFocusGroup focuses) {
/*  74 */     recipe.getInput().ifPresent(recipeInput -> recipeInput.ifLeft(()).ifRight(()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     int xPos = 96;
/*  83 */     for (Either<ItemStack, FluidStack> output : recipe.getOutput()) {
/*  84 */       IRecipeSlotBuilder slot = builder.addSlot(RecipeIngredientRole.OUTPUT, xPos, 16);
/*  85 */       Objects.requireNonNull(slot); output.ifLeft(slot::addItemStack)
/*  86 */         .ifRight(input -> ((IRecipeSlotBuilder)slot.addIngredient((IIngredientType)ForgeTypes.FLUID_STACK, input)).setFluidRenderer(1000L, false, 16, 16));
/*     */ 
/*     */ 
/*     */       
/*  90 */       xPos += 16;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<Component> getTooltipStrings(@NotNull WorldTransmuteEntry recipe, @NotNull IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
/*  97 */     if (mouseX > 67.0D && mouseX < 107.0D && mouseY > 18.0D && mouseY < 38.0D) {
/*  98 */       return (List)Collections.singletonList(PELang.WORLD_TRANSMUTE_DESCRIPTION.translate(new Object[0]));
/*     */     }
/* 100 */     return Collections.emptyList();
/*     */   }
/*     */   
/*     */   public static List<WorldTransmuteEntry> getAllTransmutations() {
/* 104 */     List<WorldTransmutationEntry> allWorldTransmutations = WorldTransmutations.getWorldTransmutations();
/*     */ 
/*     */     
/* 107 */     List<WorldTransmuteEntry> visible = new ArrayList<>();
/* 108 */     allWorldTransmutations.forEach(entry -> {
/*     */           WorldTransmuteEntry e = new WorldTransmuteEntry(entry);
/*     */           
/*     */           if (e.isRenderable()) {
/*     */             boolean alreadyHas;
/*     */             FluidStack inputFluid = e.getInputFluid();
/*     */             if (inputFluid.isEmpty()) {
/*     */               ItemStack inputItem = e.getInputItem();
/*     */               alreadyHas = visible.stream().map(WorldTransmuteEntry::getInputItem).anyMatch(());
/*     */             } else {
/*     */               alreadyHas = visible.stream().map(WorldTransmuteEntry::getInputFluid).anyMatch(());
/*     */             } 
/*     */             if (!alreadyHas) {
/*     */               visible.add(e);
/*     */             }
/*     */           } 
/*     */         });
/* 125 */     return visible;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\jei\world_transmute\WorldTransmuteRecipeCategory.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */