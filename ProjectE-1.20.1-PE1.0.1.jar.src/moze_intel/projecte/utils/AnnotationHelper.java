/*     */ package moze_intel.projecte.utils;
/*     */ 
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.function.Function;
/*     */ import moze_intel.projecte.PECore;
/*     */ import moze_intel.projecte.api.mapper.EMCMapper;
/*     */ import moze_intel.projecte.api.mapper.IEMCMapper;
/*     */ import moze_intel.projecte.api.mapper.recipe.IRecipeTypeMapper;
/*     */ import moze_intel.projecte.api.mapper.recipe.RecipeTypeMapper;
/*     */ import moze_intel.projecte.api.nbt.INBTProcessor;
/*     */ import moze_intel.projecte.api.nbt.NBTProcessor;
/*     */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*     */ import net.minecraftforge.fml.ModList;
/*     */ import net.minecraftforge.forgespi.language.ModFileScanData;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ import org.objectweb.asm.Type;
/*     */ 
/*     */ 
/*     */ public class AnnotationHelper
/*     */ {
/*  31 */   private static final Type MAPPER_TYPE = Type.getType(EMCMapper.class);
/*  32 */   private static final Type RECIPE_TYPE_MAPPER_TYPE = Type.getType(RecipeTypeMapper.class);
/*  33 */   private static final Type NBT_PROCESSOR_TYPE = Type.getType(NBTProcessor.class);
/*     */   
/*     */   public static List<INBTProcessor> getNBTProcessors() {
/*  36 */     ModList modList = ModList.get();
/*  37 */     List<INBTProcessor> nbtProcessors = new ArrayList<>();
/*  38 */     Map<INBTProcessor, Integer> priorities = new HashMap<>();
/*  39 */     for (ModFileScanData scanData : modList.getAllScanData()) {
/*  40 */       for (ModFileScanData.AnnotationData data : scanData.getAnnotations()) {
/*  41 */         if (NBT_PROCESSOR_TYPE.equals(data.annotationType()) && checkRequiredMods(data)) {
/*     */           
/*  43 */           INBTProcessor processor = getNBTProcessor(data.memberName());
/*  44 */           if (processor != null) {
/*  45 */             int priority = getPriority(data);
/*  46 */             nbtProcessors.add(processor);
/*  47 */             priorities.put(processor, Integer.valueOf(priority));
/*  48 */             PECore.LOGGER.info("Found and loaded NBT Processor: {}, with priority {}", processor.getName(), Integer.valueOf(priority));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*  53 */     Objects.requireNonNull(priorities); nbtProcessors.sort(Collections.reverseOrder(Comparator.comparing(priorities::get)));
/*  54 */     return nbtProcessors;
/*     */   }
/*     */   
/*     */   public static List<IRecipeTypeMapper> getRecipeTypeMappers() {
/*  58 */     ModList modList = ModList.get();
/*  59 */     List<IRecipeTypeMapper> recipeTypeMappers = new ArrayList<>();
/*  60 */     Map<IRecipeTypeMapper, Integer> priorities = new HashMap<>();
/*  61 */     for (ModFileScanData scanData : modList.getAllScanData()) {
/*  62 */       for (ModFileScanData.AnnotationData data : scanData.getAnnotations()) {
/*  63 */         if (RECIPE_TYPE_MAPPER_TYPE.equals(data.annotationType()) && checkRequiredMods(data)) {
/*     */           
/*  65 */           IRecipeTypeMapper mapper = getRecipeTypeMapper(data.memberName());
/*  66 */           if (mapper != null) {
/*  67 */             int priority = getPriority(data);
/*  68 */             recipeTypeMappers.add(mapper);
/*  69 */             priorities.put(mapper, Integer.valueOf(priority));
/*  70 */             PECore.LOGGER.info("Found and loaded RecipeType Mapper: {}, with priority {}", mapper.getName(), Integer.valueOf(priority));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*  75 */     Objects.requireNonNull(priorities); recipeTypeMappers.sort(Collections.reverseOrder(Comparator.comparing(priorities::get)));
/*  76 */     return recipeTypeMappers;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<IEMCMapper<NormalizedSimpleStack, Long>> getEMCMappers() {
/*  81 */     ModList modList = ModList.get();
/*  82 */     List<IEMCMapper<NormalizedSimpleStack, Long>> emcMappers = new ArrayList<>();
/*  83 */     Map<IEMCMapper<NormalizedSimpleStack, Long>, Integer> priorities = new HashMap<>();
/*  84 */     for (ModFileScanData scanData : modList.getAllScanData()) {
/*  85 */       for (ModFileScanData.AnnotationData data : scanData.getAnnotations()) {
/*  86 */         if (MAPPER_TYPE.equals(data.annotationType()) && checkRequiredMods(data)) {
/*     */           
/*  88 */           IEMCMapper<?, ?> mapper = getEMCMapper(data.memberName());
/*  89 */           if (mapper != null) {
/*     */             try {
/*  91 */               IEMCMapper<?, ?> iEMCMapper = mapper;
/*  92 */               int priority = getPriority(data);
/*  93 */               emcMappers.add(iEMCMapper);
/*  94 */               priorities.put(iEMCMapper, Integer.valueOf(priority));
/*  95 */               PECore.LOGGER.info("Found and loaded EMC mapper: {}, with priority {}", mapper.getName(), Integer.valueOf(priority));
/*  96 */             } catch (ClassCastException e) {
/*  97 */               PECore.LOGGER.error("{}: Is not a mapper for {}, to {}", new Object[] { mapper.getClass(), NormalizedSimpleStack.class, Long.class, e });
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 103 */     Objects.requireNonNull(priorities); emcMappers.sort(Collections.reverseOrder(Comparator.comparing(priorities::get)));
/* 104 */     return emcMappers;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static IEMCMapper<?, ?> getEMCMapper(String className) {
/* 109 */     return createOrGetInstance(className, (Class)IEMCMapper.class, (Class)EMCMapper.Instance.class, IEMCMapper::getName);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static IRecipeTypeMapper getRecipeTypeMapper(String className) {
/* 114 */     return createOrGetInstance(className, IRecipeTypeMapper.class, (Class)RecipeTypeMapper.Instance.class, IRecipeTypeMapper::getName);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static INBTProcessor getNBTProcessor(String className) {
/* 119 */     return createOrGetInstance(className, INBTProcessor.class, (Class)NBTProcessor.Instance.class, INBTProcessor::getName);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private static <T> T createOrGetInstance(String className, Class<T> baseClass, Class<? extends Annotation> instanceAnnotation, Function<T, String> nameFunction) {
/*     */     try {
/* 126 */       Class<? extends T> subClass = Class.forName(className).asSubclass(baseClass);
/*     */       
/* 128 */       Field[] fields = subClass.getDeclaredFields();
/* 129 */       for (Field field : fields) {
/* 130 */         if (field.isAnnotationPresent(instanceAnnotation)) {
/* 131 */           if (Modifier.isStatic(field.getModifiers())) {
/*     */             try {
/* 133 */               Object fieldValue = field.get((Object)null);
/* 134 */               if (baseClass.isInstance(fieldValue)) {
/* 135 */                 T instance = (T)fieldValue;
/* 136 */                 PECore.debugLog("Found specified {} instance for: {}. Using it rather than creating a new instance.", new Object[] { baseClass.getSimpleName(), nameFunction
/* 137 */                       .apply(instance) });
/* 138 */                 return instance;
/*     */               } 
/* 140 */               PECore.LOGGER.error("{} annotation found on non {} field: {}", new Object[] { instanceAnnotation.getSimpleName(), baseClass.getSimpleName(), field });
/* 141 */               return null;
/*     */             }
/* 143 */             catch (IllegalAccessException e) {
/* 144 */               PECore.LOGGER.error("{} annotation found on inaccessible field: {}", instanceAnnotation.getSimpleName(), field);
/* 145 */               return null;
/*     */             } 
/*     */           }
/* 148 */           PECore.LOGGER.error("{} annotation found on non static field: {}", instanceAnnotation.getSimpleName(), field);
/* 149 */           return null;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 154 */       return subClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
/* 155 */     } catch (ClassNotFoundException|InstantiationException|IllegalAccessException|LinkageError|java.lang.reflect.InvocationTargetException|NoSuchMethodException e) {
/* 156 */       PECore.LOGGER.error("Failed to load: {}", className, e);
/*     */       
/* 158 */       return null;
/*     */     } 
/*     */   }
/*     */   private static boolean checkRequiredMods(ModFileScanData.AnnotationData data) {
/* 162 */     Map<String, Object> annotationData = data.annotationData();
/* 163 */     if (annotationData.containsKey("requiredMods")) {
/*     */       
/* 165 */       List<String> requiredMods = (List<String>)annotationData.get("requiredMods");
/* 166 */       if (requiredMods.stream().anyMatch(modid -> !ModList.get().isLoaded(modid))) {
/* 167 */         PECore.debugLog("Skipped checking class {}, as its required mods ({}) are not loaded.", new Object[] { data.memberName(), Arrays.toString(requiredMods.toArray()) });
/* 168 */         return false;
/*     */       } 
/*     */     } 
/* 171 */     return true;
/*     */   }
/*     */   
/*     */   private static int getPriority(ModFileScanData.AnnotationData data) {
/* 175 */     Map<String, Object> annotationData = data.annotationData();
/* 176 */     if (annotationData.containsKey("priority")) {
/* 177 */       return ((Integer)annotationData.get("priority")).intValue();
/*     */     }
/* 179 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\project\\utils\AnnotationHelper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */