/*     */ package moze_intel.projecte.gameObjs.customRecipes;
/*     */ 
/*     */ import com.google.gson.JsonObject;
/*     */ import moze_intel.projecte.gameObjs.items.KleinStar;
/*     */ import moze_intel.projecte.gameObjs.registries.PERecipeSerializers;
/*     */ import net.minecraft.core.NonNullList;
/*     */ import net.minecraft.core.RegistryAccess;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.Container;
/*     */ import net.minecraft.world.inventory.CraftingContainer;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.crafting.CraftingBookCategory;
/*     */ import net.minecraft.world.item.crafting.CraftingRecipe;
/*     */ import net.minecraft.world.item.crafting.Ingredient;
/*     */ import net.minecraft.world.item.crafting.Recipe;
/*     */ import net.minecraft.world.item.crafting.RecipeSerializer;
/*     */ import net.minecraft.world.item.crafting.ShapelessRecipe;
/*     */ import net.minecraft.world.level.Level;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class RecipeShapelessKleinStar
/*     */   implements CraftingRecipe {
/*     */   public RecipeShapelessKleinStar(ShapelessRecipe compose) {
/*  25 */     this.compose = compose;
/*     */   }
/*     */   private final ShapelessRecipe compose;
/*     */   
/*     */   @NotNull
/*     */   public ResourceLocation m_6423_() {
/*  31 */     return this.compose.m_6423_();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public RecipeSerializer<?> m_7707_() {
/*  37 */     return (RecipeSerializer)PERecipeSerializers.KLEIN.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean matches(@NotNull CraftingContainer inv, @NotNull Level worldIn) {
/*  42 */     return this.compose.m_5818_(inv, worldIn);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack assemble(@NotNull CraftingContainer inv, @NotNull RegistryAccess registryAccess) {
/*  48 */     ItemStack result = this.compose.m_5874_(inv, registryAccess);
/*  49 */     long storedEMC = 0L;
/*  50 */     for (int i = 0; i < inv.m_6643_(); i++) {
/*  51 */       ItemStack stack = inv.m_8020_(i);
/*  52 */       if (!stack.m_41619_() && stack.m_41720_() instanceof KleinStar) {
/*  53 */         storedEMC += KleinStar.getEmc(stack);
/*     */       }
/*     */     } 
/*  56 */     if (storedEMC != 0L && result.m_41720_() instanceof KleinStar) {
/*  57 */       KleinStar.setEmc(result, storedEMC);
/*     */     }
/*  59 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_8004_(int width, int height) {
/*  64 */     return this.compose.m_8004_(width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack m_8043_(@NotNull RegistryAccess registryAccess) {
/*  70 */     return this.compose.m_8043_(registryAccess);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public NonNullList<ItemStack> getRemainingItems(@NotNull CraftingContainer inv) {
/*  76 */     return this.compose.m_7457_((Container)inv);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public NonNullList<Ingredient> m_7527_() {
/*  82 */     return this.compose.m_7527_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_5598_() {
/*  88 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String m_6076_() {
/*  94 */     return this.compose.m_6076_();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack m_8042_() {
/* 100 */     return this.compose.m_8042_();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_142505_() {
/* 105 */     return this.compose.m_142505_();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public CraftingBookCategory m_245232_() {
/* 111 */     return this.compose.m_245232_();
/*     */   }
/*     */   
/*     */   public static class Serializer
/*     */     implements RecipeSerializer<RecipeShapelessKleinStar>
/*     */   {
/*     */     @NotNull
/*     */     public RecipeShapelessKleinStar fromJson(@NotNull ResourceLocation recipeId, @NotNull JsonObject json) {
/* 119 */       return new RecipeShapelessKleinStar((ShapelessRecipe)RecipeSerializer.f_44077_.m_6729_(recipeId, json));
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public RecipeShapelessKleinStar fromNetwork(@NotNull ResourceLocation recipeId, @NotNull FriendlyByteBuf buffer) {
/* 125 */       return new RecipeShapelessKleinStar((ShapelessRecipe)RecipeSerializer.f_44077_.m_8005_(recipeId, buffer));
/*     */     }
/*     */ 
/*     */     
/*     */     public void toNetwork(@NotNull FriendlyByteBuf buffer, RecipeShapelessKleinStar recipe) {
/* 130 */       RecipeSerializer.f_44077_.m_6178_(buffer, (Recipe)recipe.compose);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\customRecipes\RecipeShapelessKleinStar.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */