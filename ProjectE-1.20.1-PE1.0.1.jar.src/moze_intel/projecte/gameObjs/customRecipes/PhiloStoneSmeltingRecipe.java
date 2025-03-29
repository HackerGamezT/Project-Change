/*     */ package moze_intel.projecte.gameObjs.customRecipes;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import moze_intel.projecte.gameObjs.registries.PERecipeSerializers;
/*     */ import net.minecraft.core.RegistryAccess;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.tags.ItemTags;
/*     */ import net.minecraft.world.Container;
/*     */ import net.minecraft.world.SimpleContainer;
/*     */ import net.minecraft.world.inventory.CraftingContainer;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.crafting.CraftingBookCategory;
/*     */ import net.minecraft.world.item.crafting.CustomRecipe;
/*     */ import net.minecraft.world.item.crafting.RecipeSerializer;
/*     */ import net.minecraft.world.item.crafting.RecipeType;
/*     */ import net.minecraft.world.item.crafting.SmeltingRecipe;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraftforge.server.ServerLifecycleHooks;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class PhiloStoneSmeltingRecipe
/*     */   extends CustomRecipe {
/*     */   public PhiloStoneSmeltingRecipe(ResourceLocation id, CraftingBookCategory category) {
/*  29 */     super(id, category);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean matches(@NotNull CraftingContainer inv, @NotNull Level level) {
/*  35 */     return !getMatchingRecipes(inv, level).isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack assemble(@NotNull CraftingContainer inv, @NotNull RegistryAccess registryAccess) {
/*  41 */     Set<SmeltingRecipe> matchingRecipes = getMatchingRecipes(inv, (Level)ServerLifecycleHooks.getCurrentServer().m_129783_());
/*  42 */     if (matchingRecipes.isEmpty()) {
/*  43 */       return ItemStack.f_41583_;
/*     */     }
/*     */ 
/*     */     
/*  47 */     ItemStack output = ((SmeltingRecipe)matchingRecipes.stream().findFirst().get()).m_8043_(registryAccess).m_41777_();
/*  48 */     output.m_41764_(output.m_41613_() * 7);
/*  49 */     return output;
/*     */   }
/*     */   
/*     */   private Set<SmeltingRecipe> getMatchingRecipes(CraftingContainer inv, @NotNull Level level) {
/*  53 */     List<ItemStack> philoStones = new ArrayList<>();
/*  54 */     List<ItemStack> coals = new ArrayList<>();
/*  55 */     List<ItemStack> allItems = new ArrayList<>();
/*  56 */     for (int i = 0; i < inv.m_6643_(); i++) {
/*  57 */       ItemStack stack = inv.m_8020_(i);
/*  58 */       if (!stack.m_41619_()) {
/*  59 */         Item item = stack.m_41720_();
/*  60 */         allItems.add(stack);
/*  61 */         if (allItems.size() > 9)
/*     */         {
/*  63 */           return Collections.emptySet();
/*     */         }
/*  65 */         if (item instanceof moze_intel.projecte.gameObjs.items.PhilosophersStone) {
/*  66 */           philoStones.add(stack);
/*     */         }
/*  68 */         if (stack.m_204117_(ItemTags.f_13160_)) {
/*  69 */           coals.add(stack);
/*     */         }
/*     */       } 
/*     */     } 
/*  73 */     if (allItems.size() == 9)
/*     */     {
/*  75 */       for (ItemStack philoStone : philoStones) {
/*  76 */         for (ItemStack coal : coals) {
/*     */ 
/*     */           
/*  79 */           if (philoStone != coal) {
/*  80 */             Set<SmeltingRecipe> matchingRecipes = new HashSet<>();
/*  81 */             for (ItemStack stack : allItems) {
/*     */               
/*  83 */               if (stack != philoStone && stack != coal) {
/*     */                 
/*  85 */                 SimpleContainer furnaceInput = new SimpleContainer(new ItemStack[] { stack });
/*  86 */                 if (matchingRecipes.isEmpty()) {
/*     */ 
/*     */                   
/*  89 */                   if (!matchingRecipes.addAll(level.m_7465_().m_44056_(RecipeType.f_44108_, (Container)furnaceInput, level)))
/*  90 */                     return Collections.emptySet();  continue;
/*     */                 } 
/*  92 */                 if (matchingRecipes.removeIf(recipe -> !recipe.m_5818_((Container)furnaceInput, level)))
/*     */                 {
/*  94 */                   if (matchingRecipes.isEmpty())
/*     */                   {
/*  96 */                     return Collections.emptySet();
/*     */                   }
/*     */                 }
/*     */               } 
/*     */             } 
/* 101 */             if (!matchingRecipes.isEmpty())
/*     */             {
/* 103 */               return matchingRecipes;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/* 109 */     return Collections.emptySet();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_8004_(int width, int height) {
/* 114 */     return (width * height >= 9);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public RecipeSerializer<?> m_7707_() {
/* 120 */     return (RecipeSerializer)PERecipeSerializers.PHILO_STONE_SMELTING.get();
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\customRecipes\PhiloStoneSmeltingRecipe.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */