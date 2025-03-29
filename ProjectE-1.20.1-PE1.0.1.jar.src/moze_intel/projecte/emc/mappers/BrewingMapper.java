/*     */ package moze_intel.projecte.emc.mappers;
/*     */ 
/*     */ import com.electronwill.nightconfig.core.file.CommentedFileConfig;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import moze_intel.projecte.PECore;
/*     */ import moze_intel.projecte.api.ItemInfo;
/*     */ import moze_intel.projecte.api.mapper.EMCMapper;
/*     */ import moze_intel.projecte.api.mapper.IEMCMapper;
/*     */ import moze_intel.projecte.api.mapper.collector.IMappingCollector;
/*     */ import moze_intel.projecte.api.nss.NSSFluid;
/*     */ import moze_intel.projecte.api.nss.NSSItem;
/*     */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*     */ import moze_intel.projecte.utils.ItemInfoHelper;
/*     */ import net.minecraft.core.RegistryAccess;
/*     */ import net.minecraft.server.ReloadableServerResources;
/*     */ import net.minecraft.server.packs.resources.ResourceManager;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.Items;
/*     */ import net.minecraft.world.item.alchemy.Potion;
/*     */ import net.minecraft.world.item.alchemy.PotionBrewing;
/*     */ import net.minecraft.world.item.alchemy.PotionUtils;
/*     */ import net.minecraft.world.item.alchemy.Potions;
/*     */ import net.minecraft.world.item.crafting.Ingredient;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraftforge.common.brewing.BrewingRecipe;
/*     */ import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
/*     */ import net.minecraftforge.common.brewing.IBrewingRecipe;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ @EMCMapper
/*     */ public class BrewingMapper
/*     */   implements IEMCMapper<NormalizedSimpleStack, Long>
/*     */ {
/*  41 */   private static final Set<ItemInfo> allReagents = new HashSet<>();
/*  42 */   private static final Set<ItemInfo> allInputs = new HashSet<>();
/*     */   
/*     */   private static int totalConversions;
/*     */   
/*     */   private static int totalPotionItems;
/*     */ 
/*     */   
/*     */   private static boolean mapAllReagents() {
/*  50 */     int conversionCount = PotionBrewing.f_43495_.size() + PotionBrewing.f_43494_.size();
/*  51 */     if (totalConversions == conversionCount) {
/*  52 */       return true;
/*     */     }
/*  54 */     allReagents.clear();
/*  55 */     addReagents(PotionBrewing.f_43495_);
/*  56 */     addReagents(PotionBrewing.f_43494_);
/*  57 */     totalConversions = conversionCount;
/*  58 */     return true;
/*     */   }
/*     */   
/*     */   private static <T> void addReagents(List<PotionBrewing.Mix<T>> conversions) {
/*  62 */     for (PotionBrewing.Mix<T> conversion : conversions) {
/*  63 */       for (ItemStack r : conversion.f_43533_.m_43908_()) {
/*  64 */         allReagents.add(ItemInfo.fromStack(r));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void mapAllInputs() {
/*  73 */     int count = PotionBrewing.f_43496_.size();
/*  74 */     if (totalPotionItems == count) {
/*     */       return;
/*     */     }
/*  77 */     allInputs.clear();
/*  78 */     Set<ItemInfo> inputs = new HashSet<>();
/*  79 */     for (Ingredient potionItem : PotionBrewing.f_43496_) {
/*  80 */       ItemStack[] matchingStacks = getMatchingStacks(potionItem);
/*  81 */       if (matchingStacks != null)
/*     */       {
/*     */         
/*  84 */         for (ItemStack input : matchingStacks) {
/*  85 */           inputs.add(ItemInfo.fromStack(input));
/*     */         }
/*     */       }
/*     */     } 
/*  89 */     for (Potion potion : ForgeRegistries.POTIONS.getValues()) {
/*  90 */       for (ItemInfo input : inputs) {
/*  91 */         allInputs.add(ItemInfoHelper.makeWithPotion(input, potion));
/*     */       }
/*     */     } 
/*  94 */     totalPotionItems = count;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addMappings(IMappingCollector<NormalizedSimpleStack, Long> mapper, CommentedFileConfig config, ReloadableServerResources serverResources, RegistryAccess registryAccess, ResourceManager resourceManager) {
/* 100 */     boolean vanillaRetrieved = mapAllReagents();
/* 101 */     if (vanillaRetrieved) {
/* 102 */       mapAllInputs();
/*     */     }
/*     */ 
/*     */     
/* 106 */     Map<NormalizedSimpleStack, Integer> waterIngredients = new HashMap<>();
/* 107 */     waterIngredients.put(NSSItem.createItem((ItemLike)Items.f_42590_), Integer.valueOf(1));
/* 108 */     waterIngredients.put(NSSFluid.createTag(FluidTags.f_13131_), Integer.valueOf(333));
/* 109 */     mapper.addConversion(1, NSSItem.createItem(PotionUtils.m_43549_(new ItemStack((ItemLike)Items.f_42589_), Potions.f_43599_)), waterIngredients);
/*     */     
/* 111 */     Set<Class<?>> canNotMap = new HashSet<>();
/* 112 */     int recipeCount = 0;
/* 113 */     List<IBrewingRecipe> recipes = BrewingRecipeRegistry.getRecipes();
/* 114 */     for (IBrewingRecipe recipe : recipes) {
/* 115 */       if (recipe instanceof BrewingRecipe) { BrewingRecipe brewingRecipe = (BrewingRecipe)recipe;
/* 116 */         ItemStack[] validInputs = getMatchingStacks(brewingRecipe.getInput());
/* 117 */         ItemStack[] validReagents = getMatchingStacks(brewingRecipe.getIngredient());
/* 118 */         if (validInputs == null || validReagents == null) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */         
/* 123 */         ItemStack output = brewingRecipe.getOutput();
/* 124 */         NSSItem nSSItem = NSSItem.createItem(output);
/* 125 */         for (ItemStack validInput : validInputs) {
/* 126 */           NSSItem nSSItem1 = NSSItem.createItem(validInput);
/* 127 */           for (ItemStack validReagent : validReagents) {
/* 128 */             Map<NormalizedSimpleStack, Integer> ingredientsWithAmount = new HashMap<>();
/* 129 */             ingredientsWithAmount.put(nSSItem1, Integer.valueOf(3));
/* 130 */             ingredientsWithAmount.put(NSSItem.createItem(validReagent), Integer.valueOf(validReagent.m_41613_()));
/*     */ 
/*     */             
/* 133 */             mapper.addConversion(3 * output.m_41613_(), nSSItem, ingredientsWithAmount);
/* 134 */             recipeCount++;
/*     */           } 
/*     */         }  continue; }
/* 137 */        if (recipe instanceof net.minecraftforge.common.brewing.VanillaBrewingRecipe) {
/* 138 */         if (!vanillaRetrieved) {
/*     */           
/* 140 */           canNotMap.add(recipe.getClass());
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 146 */         for (ItemInfo inputInfo : allInputs) {
/* 147 */           ItemStack validInput = inputInfo.createStack();
/* 148 */           NSSItem nSSItem = NSSItem.createItem(validInput);
/* 149 */           for (ItemInfo reagentInfo : allReagents) {
/* 150 */             ItemStack validReagent = reagentInfo.createStack();
/* 151 */             ItemStack output = recipe.getOutput(validInput, validReagent);
/* 152 */             if (!output.m_41619_()) {
/* 153 */               Map<NormalizedSimpleStack, Integer> ingredientsWithAmount = new HashMap<>();
/* 154 */               ingredientsWithAmount.put(nSSItem, Integer.valueOf(3));
/* 155 */               ingredientsWithAmount.put(NSSItem.createItem(validReagent), Integer.valueOf(1));
/*     */               
/* 157 */               mapper.addConversion(3 * output.m_41613_(), NSSItem.createItem(output), ingredientsWithAmount);
/* 158 */               recipeCount++;
/*     */             } 
/*     */           } 
/*     */         }  continue;
/*     */       } 
/* 163 */       canNotMap.add(recipe.getClass());
/*     */     } 
/*     */ 
/*     */     
/* 167 */     PECore.debugLog("BrewingMapper Statistics:", new Object[0]);
/* 168 */     PECore.debugLog("Found {} Brewing Recipes", new Object[] { Integer.valueOf(recipeCount) });
/* 169 */     for (Class<?> c : canNotMap) {
/* 170 */       PECore.debugLog("Could not map Brewing Recipes with Type: {}", new Object[] { c.getName() });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 176 */     return "BrewingMapper";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 181 */     return "Add Conversions for Brewing Recipes";
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static ItemStack[] getMatchingStacks(Ingredient ingredient) {
/*     */     try {
/* 187 */       return ingredient.m_43908_();
/* 188 */     } catch (Exception e) {
/* 189 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\mappers\BrewingMapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */