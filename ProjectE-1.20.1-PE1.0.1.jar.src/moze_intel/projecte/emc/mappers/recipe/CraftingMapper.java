/*     */ package moze_intel.projecte.emc.mappers.recipe;
/*     */ 
/*     */ import com.electronwill.nightconfig.core.file.CommentedFileConfig;
/*     */ import com.mojang.logging.LogUtils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import moze_intel.projecte.PECore;
/*     */ import moze_intel.projecte.api.mapper.EMCMapper;
/*     */ import moze_intel.projecte.api.mapper.IEMCMapper;
/*     */ import moze_intel.projecte.api.mapper.collector.IMappingCollector;
/*     */ import moze_intel.projecte.api.mapper.recipe.INSSFakeGroupManager;
/*     */ import moze_intel.projecte.api.mapper.recipe.IRecipeTypeMapper;
/*     */ import moze_intel.projecte.api.nss.NSSFake;
/*     */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*     */ import moze_intel.projecte.emc.EMCMappingHandler;
/*     */ import moze_intel.projecte.utils.AnnotationHelper;
/*     */ import net.minecraft.core.RegistryAccess;
/*     */ import net.minecraft.resources.ResourceKey;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.server.ReloadableServerResources;
/*     */ import net.minecraft.server.packs.resources.ResourceManager;
/*     */ import net.minecraft.util.Tuple;
/*     */ import net.minecraft.world.item.crafting.Recipe;
/*     */ import net.minecraft.world.item.crafting.RecipeManager;
/*     */ import net.minecraft.world.item.crafting.RecipeType;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ 
/*     */ 
/*     */ @EMCMapper
/*     */ public class CraftingMapper
/*     */   implements IEMCMapper<NormalizedSimpleStack, Long>
/*     */ {
/*  37 */   private static final List<IRecipeTypeMapper> recipeMappers = new ArrayList<>();
/*     */   
/*     */   public static void loadMappers() {
/*  40 */     if (recipeMappers.isEmpty()) {
/*  41 */       recipeMappers.addAll(AnnotationHelper.getRecipeTypeMappers());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addMappings(IMappingCollector<NormalizedSimpleStack, Long> mapper, CommentedFileConfig config, ReloadableServerResources serverResources, RegistryAccess registryAccess, ResourceManager resourceManager) {
/*  49 */     NSSFake.setCurrentNamespace("craftingMapper");
/*  50 */     Map<ResourceLocation, RecipeCountInfo> recipeCount = new HashMap<>();
/*  51 */     Set<ResourceLocation> canNotMap = new HashSet<>();
/*  52 */     RecipeManager recipeManager = serverResources.m_206887_();
/*     */     
/*  54 */     NSSFakeGroupManager fakeGroupManager = new NSSFakeGroupManager();
/*  55 */     for (Map.Entry<ResourceKey<RecipeType<?>>, RecipeType<?>> entry : (Iterable<Map.Entry<ResourceKey<RecipeType<?>>, RecipeType<?>>>)ForgeRegistries.RECIPE_TYPES.getEntries()) {
/*  56 */       ResourceLocation typeRegistryName = ((ResourceKey)entry.getKey()).m_135782_();
/*  57 */       RecipeType<?> recipeType = entry.getValue();
/*  58 */       boolean wasHandled = false;
/*  59 */       List<? extends Recipe<?>> recipes = null;
/*  60 */       List<Recipe<?>> unhandled = new ArrayList<>();
/*  61 */       for (IRecipeTypeMapper recipeMapper : recipeMappers) {
/*  62 */         String configKey = getName() + "." + getName() + ".enabled";
/*  63 */         if (((Boolean)EMCMappingHandler.getOrSetDefault(config, configKey, recipeMapper.getDescription(), Boolean.valueOf(recipeMapper.isAvailable()))).booleanValue())
/*     */         {
/*  65 */           if (recipeMapper.canHandle(recipeType)) {
/*  66 */             if (recipes == null)
/*     */             {
/*     */ 
/*     */ 
/*     */               
/*  71 */               recipes = recipeManager.m_44013_(recipeType);
/*     */             }
/*  73 */             int numHandled = 0;
/*  74 */             for (Recipe<?> recipe : recipes) {
/*     */               try {
/*  76 */                 if (recipeMapper.handleRecipe(mapper, recipe, registryAccess, fakeGroupManager)) {
/*  77 */                   numHandled++; continue;
/*     */                 } 
/*  79 */                 unhandled.add(recipe);
/*     */               }
/*  81 */               catch (Exception e) {
/*  82 */                 PECore.LOGGER.error(LogUtils.FATAL_MARKER, "A fatal error occurred while trying to map the recipe: {}", recipe.m_6423_());
/*  83 */                 throw e;
/*     */               } 
/*     */             } 
/*  86 */             if (numHandled > 0 || recipes.isEmpty()) {
/*  87 */               if (recipeCount.containsKey(typeRegistryName)) {
/*  88 */                 ((RecipeCountInfo)recipeCount.get(typeRegistryName)).setUnhandled(unhandled);
/*     */               } else {
/*  90 */                 recipeCount.put(typeRegistryName, new RecipeCountInfo(recipes.size(), unhandled));
/*     */               } 
/*  92 */               wasHandled = true;
/*  93 */               if (unhandled.isEmpty()) {
/*     */                 break;
/*     */               }
/*     */ 
/*     */               
/*  98 */               recipes = unhandled;
/*  99 */               unhandled = new ArrayList<>();
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 106 */       if (!wasHandled)
/*     */       {
/*     */         
/* 109 */         canNotMap.add(typeRegistryName);
/*     */       }
/*     */     } 
/* 112 */     PECore.debugLog("CraftingMapper Statistics:", new Object[0]);
/* 113 */     for (Map.Entry<ResourceLocation, RecipeCountInfo> entry : recipeCount.entrySet()) {
/* 114 */       ResourceLocation typeRegistryName = entry.getKey();
/* 115 */       RecipeCountInfo countInfo = entry.getValue();
/* 116 */       int total = countInfo.getTotalRecipes();
/* 117 */       List<Recipe<?>> unhandled = countInfo.getUnhandled();
/* 118 */       PECore.debugLog("Found and handled {} of {} Recipes of Type {}", new Object[] { Integer.valueOf(total - unhandled.size()), Integer.valueOf(total), typeRegistryName });
/* 119 */       if (!unhandled.isEmpty()) {
/* 120 */         PECore.debugLog("Unhandled Recipes of Type {}:", new Object[] { typeRegistryName });
/* 121 */         for (Recipe<?> recipe : unhandled) {
/* 122 */           PECore.debugLog("Name: {}, Recipe class: {}", new Object[] { recipe.m_6423_(), recipe.getClass().getName() });
/*     */         } 
/*     */       } 
/*     */     } 
/* 126 */     for (ResourceLocation typeRegistryName : canNotMap) {
/* 127 */       PECore.debugLog("Could not map any Recipes of Type: {}", new Object[] { typeRegistryName });
/*     */     } 
/* 129 */     NSSFake.resetNamespace();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 134 */     return "CraftingMapper";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 139 */     return "Add Conversions for Crafting Recipes gathered from net.minecraft.item.crafting.RecipeManager";
/*     */   }
/*     */   
/*     */   private static class RecipeCountInfo
/*     */   {
/*     */     private final int totalRecipes;
/*     */     private List<Recipe<?>> unhandled;
/*     */     
/*     */     private RecipeCountInfo(int totalRecipes, List<Recipe<?>> unhandled) {
/* 148 */       this.totalRecipes = totalRecipes;
/* 149 */       this.unhandled = unhandled;
/*     */     }
/*     */     
/*     */     public int getTotalRecipes() {
/* 153 */       return this.totalRecipes;
/*     */     }
/*     */     
/*     */     public void setUnhandled(List<Recipe<?>> unhandled) {
/* 157 */       this.unhandled = unhandled;
/*     */     }
/*     */     
/*     */     public List<Recipe<?>> getUnhandled() {
/* 161 */       return this.unhandled;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class NSSFakeGroupManager
/*     */     implements INSSFakeGroupManager {
/* 167 */     private final Map<Set<NormalizedSimpleStack>, NormalizedSimpleStack> groups = new HashMap<>();
/*     */     
/*     */     private int fakeIndex;
/*     */     
/*     */     public Tuple<NormalizedSimpleStack, Boolean> getOrCreateFakeGroup(Set<NormalizedSimpleStack> normalizedSimpleStacks) {
/* 172 */       NormalizedSimpleStack stack = this.groups.get(normalizedSimpleStacks);
/* 173 */       if (stack == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 182 */         stack = NSSFake.create(Integer.toString(this.fakeIndex++));
/*     */ 
/*     */         
/* 185 */         this.groups.put(new HashSet<>(normalizedSimpleStacks), stack);
/* 186 */         return new Tuple(stack, Boolean.valueOf(true));
/*     */       } 
/* 188 */       return new Tuple(stack, Boolean.valueOf(false));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\mappers\recipe\CraftingMapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */