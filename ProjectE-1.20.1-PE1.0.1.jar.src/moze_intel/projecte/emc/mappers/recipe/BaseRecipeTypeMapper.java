/*     */ package moze_intel.projecte.emc.mappers.recipe;
/*     */ 
/*     */ import com.mojang.logging.LogUtils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import moze_intel.projecte.PECore;
/*     */ import moze_intel.projecte.api.mapper.collector.IMappingCollector;
/*     */ import moze_intel.projecte.api.mapper.recipe.INSSFakeGroupManager;
/*     */ import moze_intel.projecte.api.mapper.recipe.IRecipeTypeMapper;
/*     */ import moze_intel.projecte.api.nss.NSSItem;
/*     */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*     */ import moze_intel.projecte.emc.IngredientMap;
/*     */ import moze_intel.projecte.utils.RegistryUtils;
/*     */ import net.minecraft.core.RegistryAccess;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.util.Tuple;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.crafting.Ingredient;
/*     */ import net.minecraft.world.item.crafting.Recipe;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BaseRecipeTypeMapper
/*     */   implements IRecipeTypeMapper
/*     */ {
/*     */   public boolean handleRecipe(IMappingCollector<NormalizedSimpleStack, Long> mapper, Recipe<?> recipe, RegistryAccess registryAccess, INSSFakeGroupManager fakeGroupManager) {
/*  40 */     ItemStack recipeOutput = recipe.m_8043_(registryAccess);
/*  41 */     if (recipeOutput.m_41619_())
/*     */     {
/*  43 */       return false;
/*     */     }
/*  45 */     Collection<Ingredient> ingredientsChecked = getIngredientsChecked(recipe);
/*  46 */     if (ingredientsChecked == null)
/*     */     {
/*     */       
/*  49 */       return true;
/*     */     }
/*  51 */     ResourceLocation recipeID = recipe.m_6423_();
/*  52 */     List<Tuple<NormalizedSimpleStack, List<IngredientMap<NormalizedSimpleStack>>>> dummyGroupInfos = new ArrayList<>();
/*  53 */     IngredientMap<NormalizedSimpleStack> ingredientMap = new IngredientMap();
/*  54 */     for (Ingredient recipeItem : ingredientsChecked) {
/*  55 */       ItemStack[] matches = getMatchingStacks(recipeItem, recipeID);
/*  56 */       if (matches == null)
/*     */       {
/*     */         
/*  59 */         return addConversionsAndReturn(mapper, dummyGroupInfos, true); } 
/*  60 */       if (matches.length == 1) {
/*     */         
/*  62 */         if (matches[0].m_41619_())
/*     */         {
/*     */           
/*  65 */           return addConversionsAndReturn(mapper, dummyGroupInfos, false); } 
/*  66 */         if (addIngredient(ingredientMap, matches[0].m_41777_(), recipeID))
/*     */         {
/*     */           
/*  69 */           return addConversionsAndReturn(mapper, dummyGroupInfos, true); }  continue;
/*     */       } 
/*  71 */       if (matches.length > 0) {
/*  72 */         Set<NormalizedSimpleStack> rawNSSMatches = new HashSet<>();
/*  73 */         List<ItemStack> stacks = new ArrayList<>();
/*  74 */         for (ItemStack match : matches) {
/*  75 */           if (!match.m_41619_()) {
/*     */             
/*  77 */             rawNSSMatches.add(NSSItem.createItem(match));
/*  78 */             stacks.add(match);
/*     */           } 
/*     */         } 
/*  81 */         int count = stacks.size();
/*  82 */         if (count == 0)
/*     */         {
/*     */           
/*  85 */           return addConversionsAndReturn(mapper, dummyGroupInfos, false); } 
/*  86 */         if (count == 1) {
/*     */           
/*  88 */           if (addIngredient(ingredientMap, ((ItemStack)stacks.get(0)).m_41777_(), recipeID))
/*     */           {
/*     */             
/*  91 */             return addConversionsAndReturn(mapper, dummyGroupInfos, true);
/*     */           }
/*     */           continue;
/*     */         } 
/*  95 */         Tuple<NormalizedSimpleStack, Boolean> group = fakeGroupManager.getOrCreateFakeGroup(rawNSSMatches);
/*  96 */         NormalizedSimpleStack dummy = (NormalizedSimpleStack)group.m_14418_();
/*  97 */         ingredientMap.addIngredient(dummy, 1);
/*  98 */         if (((Boolean)group.m_14419_()).booleanValue()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 104 */           List<IngredientMap<NormalizedSimpleStack>> groupIngredientMaps = new ArrayList<>();
/* 105 */           for (ItemStack stack : stacks) {
/* 106 */             IngredientMap<NormalizedSimpleStack> groupIngredientMap = new IngredientMap();
/*     */ 
/*     */             
/* 109 */             if (addIngredient(groupIngredientMap, stack.m_41777_(), recipeID))
/*     */             {
/*     */               
/* 112 */               return addConversionsAndReturn(mapper, dummyGroupInfos, true);
/*     */             }
/* 114 */             groupIngredientMaps.add(groupIngredientMap);
/*     */           } 
/* 116 */           dummyGroupInfos.add(new Tuple(dummy, groupIngredientMaps));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 121 */     mapper.addConversion(recipeOutput.m_41613_(), NSSItem.createItem(recipeOutput), ingredientMap.getMap());
/* 122 */     return addConversionsAndReturn(mapper, dummyGroupInfos, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean addConversionsAndReturn(IMappingCollector<NormalizedSimpleStack, Long> mapper, List<Tuple<NormalizedSimpleStack, List<IngredientMap<NormalizedSimpleStack>>>> dummyGroupInfos, boolean returnValue) {
/* 133 */     for (Tuple<NormalizedSimpleStack, List<IngredientMap<NormalizedSimpleStack>>> dummyGroupInfo : dummyGroupInfos) {
/* 134 */       for (IngredientMap<NormalizedSimpleStack> groupIngredientMap : (Iterable<IngredientMap<NormalizedSimpleStack>>)dummyGroupInfo.m_14419_()) {
/* 135 */         mapper.addConversion(1, dummyGroupInfo.m_14418_(), groupIngredientMap.getMap());
/*     */       }
/*     */     } 
/* 138 */     return returnValue;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private ItemStack[] getMatchingStacks(Ingredient ingredient, ResourceLocation recipeID) {
/*     */     try {
/* 144 */       return ingredient.m_43908_();
/* 145 */     } catch (Exception e) {
/* 146 */       if (isTagException(e)) {
/* 147 */         PECore.LOGGER.error(LogUtils.FATAL_MARKER, "Error mapping recipe {}. Ingredient of type: {} crashed when getting the matching stacks due to not properly deserializing and handling tags. Please report this to the ingredient's creator.", new Object[] { recipeID, ingredient
/*     */               
/* 149 */               .getClass().getName(), e });
/*     */       } else {
/* 151 */         PECore.LOGGER.error(LogUtils.FATAL_MARKER, "Error mapping recipe {}. Ingredient of type: {} crashed when getting the matching stacks. Please report this to the ingredient's creator.", new Object[] { recipeID, ingredient
/* 152 */               .getClass().getName(), e });
/*     */       } 
/* 154 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean addIngredient(IngredientMap<NormalizedSimpleStack> ingredientMap, ItemStack stack, ResourceLocation recipeID) {
/* 160 */     Item item = stack.m_41720_();
/* 161 */     boolean hasContainerItem = false;
/*     */ 
/*     */     
/*     */     try {
/* 165 */       hasContainerItem = item.hasCraftingRemainingItem(stack);
/* 166 */       if (hasContainerItem)
/*     */       {
/* 168 */         ingredientMap.addIngredient(NSSItem.createItem(item.getCraftingRemainingItem(stack)), -1);
/*     */       }
/* 170 */     } catch (Exception e) {
/* 171 */       ResourceLocation itemName = RegistryUtils.getName(item);
/* 172 */       if (hasContainerItem) {
/* 173 */         if (isTagException(e)) {
/* 174 */           PECore.LOGGER.error(LogUtils.FATAL_MARKER, "Error mapping recipe {}. Item: {} reported that it has a container item, but errors when trying to get the container item due to not properly deserializing and handling tags. Please report this to {}.", new Object[] { recipeID, itemName, itemName
/*     */                 
/* 176 */                 .m_135827_(), e });
/*     */         } else {
/* 178 */           PECore.LOGGER.error(LogUtils.FATAL_MARKER, "Error mapping recipe {}. Item: {} reported that it has a container item, but errors when trying to get the container item based on the stack in the recipe. Please report this to {}.", new Object[] { recipeID, itemName, itemName
/*     */                 
/* 180 */                 .m_135827_(), e });
/*     */         } 
/* 182 */       } else if (isTagException(e)) {
/* 183 */         PECore.LOGGER.error(LogUtils.FATAL_MARKER, "Error mapping recipe {}. Item: {} crashed when checking if the stack has a container item, due to not properly deserializing and handling tags. Please report this to {}.", new Object[] { recipeID, itemName, itemName
/*     */               
/* 185 */               .m_135827_(), e });
/*     */       } else {
/* 187 */         PECore.LOGGER.error(LogUtils.FATAL_MARKER, "Error mapping recipe {}. Item: {} crashed when checking if the stack in the recipe has a container item. Please report this to {}.", new Object[] { recipeID, itemName, itemName
/* 188 */               .m_135827_(), e });
/*     */       } 
/*     */ 
/*     */       
/* 192 */       return true;
/*     */     } 
/* 194 */     ingredientMap.addIngredient(NSSItem.createItem(stack), 1);
/* 195 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isTagException(Exception e) {
/* 199 */     return (e instanceof IllegalStateException && e.getMessage().matches("Tag \\S*:\\S* used before it was bound"));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private Collection<Ingredient> getIngredientsChecked(Recipe<?> recipe) {
/*     */     try {
/* 205 */       return getIngredients(recipe);
/* 206 */     } catch (Exception e) {
/* 207 */       ResourceLocation recipeID = recipe.m_6423_();
/* 208 */       if (isTagException(e)) {
/* 209 */         PECore.LOGGER.error(LogUtils.FATAL_MARKER, "Error mapping recipe {}. Failed to get ingredients due to the recipe not properly deserializing and handling tags. Please report this to {}.", new Object[] { recipeID, recipeID
/* 210 */               .m_135827_(), e });
/*     */       } else {
/* 212 */         PECore.LOGGER.error(LogUtils.FATAL_MARKER, "Error mapping recipe {}. Failed to get ingredients. Please report this to {}.", new Object[] { recipeID, recipeID.m_135827_(), e });
/*     */       } 
/*     */       
/* 215 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Collection<Ingredient> getIngredients(Recipe<?> recipe) {
/* 220 */     return (Collection<Ingredient>)recipe.m_7527_();
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\mappers\recipe\BaseRecipeTypeMapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */