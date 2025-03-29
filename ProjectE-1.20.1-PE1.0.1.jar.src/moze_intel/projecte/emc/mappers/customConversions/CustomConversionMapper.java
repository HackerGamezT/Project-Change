/*     */ package moze_intel.projecte.emc.mappers.customConversions;
/*     */ 
/*     */ import com.electronwill.nightconfig.core.file.CommentedFileConfig;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonParseException;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import moze_intel.projecte.PECore;
/*     */ import moze_intel.projecte.api.mapper.EMCMapper;
/*     */ import moze_intel.projecte.api.mapper.IEMCMapper;
/*     */ import moze_intel.projecte.api.mapper.collector.IMappingCollector;
/*     */ import moze_intel.projecte.api.nss.NSSFake;
/*     */ import moze_intel.projecte.api.nss.NSSTag;
/*     */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*     */ import moze_intel.projecte.emc.json.NSSSerializer;
/*     */ import moze_intel.projecte.emc.mappers.customConversions.json.ConversionGroup;
/*     */ import moze_intel.projecte.emc.mappers.customConversions.json.CustomConversion;
/*     */ import moze_intel.projecte.emc.mappers.customConversions.json.CustomConversionDeserializer;
/*     */ import moze_intel.projecte.emc.mappers.customConversions.json.CustomConversionFile;
/*     */ import moze_intel.projecte.emc.mappers.customConversions.json.FixedValues;
/*     */ import moze_intel.projecte.emc.mappers.customConversions.json.FixedValuesDeserializer;
/*     */ import net.minecraft.core.RegistryAccess;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.server.ReloadableServerResources;
/*     */ import net.minecraft.server.packs.resources.Resource;
/*     */ import net.minecraft.server.packs.resources.ResourceManager;
/*     */ 
/*     */ @EMCMapper
/*     */ public class CustomConversionMapper implements IEMCMapper<NormalizedSimpleStack, Long> {
/*  36 */   public static final Gson GSON = (new GsonBuilder())
/*  37 */     .registerTypeAdapter(CustomConversion.class, new CustomConversionDeserializer())
/*  38 */     .registerTypeAdapter(FixedValues.class, new FixedValuesDeserializer())
/*  39 */     .registerTypeAdapter(NormalizedSimpleStack.class, NSSSerializer.INSTANCE)
/*  40 */     .setPrettyPrinting()
/*  41 */     .create();
/*     */ 
/*     */   
/*     */   public String getName() {
/*  45 */     return "CustomConversionMapper";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  50 */     return "Loads json files within datapacks (data/<domain>/pe_custom_conversions/*.json) to add values and conversions";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addMappings(IMappingCollector<NormalizedSimpleStack, Long> mapper, CommentedFileConfig config, ReloadableServerResources serverResources, RegistryAccess registryAccess, ResourceManager resourceManager) {
/*  56 */     Map<ResourceLocation, CustomConversionFile> files = load(resourceManager);
/*  57 */     for (CustomConversionFile file : files.values()) {
/*  58 */       addMappingsFromFile(file, mapper);
/*     */     }
/*     */   }
/*     */   
/*     */   private static Map<ResourceLocation, CustomConversionFile> load(ResourceManager resourceManager) {
/*  63 */     Map<ResourceLocation, CustomConversionFile> loading = new HashMap<>();
/*     */     
/*  65 */     String folder = "pe_custom_conversions";
/*  66 */     String extension = ".json";
/*  67 */     int folderLength = folder.length();
/*  68 */     int extensionLength = extension.length();
/*     */ 
/*     */     
/*  71 */     for (Map.Entry<ResourceLocation, List<Resource>> entry : (Iterable<Map.Entry<ResourceLocation, List<Resource>>>)resourceManager.m_214160_(folder, n -> n.m_135815_().endsWith(extension)).entrySet()) {
/*     */       
/*  73 */       ResourceLocation file = entry.getKey();
/*  74 */       ResourceLocation conversionId = new ResourceLocation(file.m_135827_(), file.m_135815_().substring(folderLength + 1, file.m_135815_().length() - extensionLength));
/*     */       
/*  76 */       PECore.LOGGER.info("Considering file {}, ID {}", file, conversionId);
/*  77 */       NSSFake.setCurrentNamespace(conversionId.toString());
/*     */ 
/*     */       
/*     */       try {
/*  81 */         for (Resource resource : entry.getValue()) {
/*     */           CustomConversionFile result; 
/*  83 */           try { Reader reader = resource.m_215508_(); 
/*  84 */             try { result = parseJson(reader);
/*  85 */               if (reader != null) reader.close();  } catch (Throwable throwable) { if (reader != null) try { reader.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (JsonParseException ex)
/*  86 */           { PECore.LOGGER.error("Malformed JSON", (Throwable)ex);
/*     */             continue; }
/*     */           
/*  89 */           loading.merge(conversionId, result, CustomConversionFile::merge);
/*     */         } 
/*  91 */       } catch (IOException e) {
/*  92 */         PECore.LOGGER.error("Could not load resource {}", file, e);
/*     */       } 
/*     */     } 
/*  95 */     NSSFake.resetNamespace();
/*  96 */     return loading;
/*     */   }
/*     */   
/*     */   private static void addMappingsFromFile(CustomConversionFile file, IMappingCollector<NormalizedSimpleStack, Long> mapper) {
/* 100 */     for (Map.Entry<String, ConversionGroup> entry : (Iterable<Map.Entry<String, ConversionGroup>>)file.groups.entrySet()) {
/* 101 */       PECore.debugLog("Adding conversions from group '{}' with comment '{}'", new Object[] { entry.getKey(), ((ConversionGroup)entry.getValue()).comment });
/* 102 */       for (CustomConversion conversion : ((ConversionGroup)entry.getValue()).conversions) {
/* 103 */         mapper.addConversion(conversion.count, conversion.output, conversion.ingredients);
/*     */       }
/*     */     } 
/*     */     Iterator<Map.Entry<NormalizedSimpleStack, Long>> iterator;
/* 107 */     for (iterator = file.values.setValueBefore.entrySet().iterator(); iterator.hasNext(); ) { Map.Entry<NormalizedSimpleStack, Long> entry = iterator.next();
/* 108 */       NormalizedSimpleStack something = entry.getKey();
/* 109 */       mapper.setValueBefore(something, entry.getValue());
/* 110 */       if (something instanceof NSSTag) { NSSTag nssTag = (NSSTag)something;
/*     */         
/* 112 */         nssTag.forEachElement(normalizedSimpleStack -> mapper.setValueBefore(normalizedSimpleStack, (Long)entry.getValue())); }
/*     */        }
/*     */ 
/*     */     
/* 116 */     for (iterator = file.values.setValueAfter.entrySet().iterator(); iterator.hasNext(); ) { Map.Entry<NormalizedSimpleStack, Long> entry = iterator.next();
/* 117 */       NormalizedSimpleStack something = entry.getKey();
/* 118 */       mapper.setValueAfter(something, entry.getValue());
/* 119 */       if (something instanceof NSSTag) { NSSTag nssTag = (NSSTag)something;
/*     */         
/* 121 */         nssTag.forEachElement(normalizedSimpleStack -> mapper.setValueAfter(normalizedSimpleStack, (Long)entry.getValue())); }
/*     */        }
/*     */ 
/*     */     
/* 125 */     for (iterator = file.values.conversion.iterator(); iterator.hasNext(); ) { CustomConversion conversion = (CustomConversion)iterator.next();
/* 126 */       NormalizedSimpleStack out = conversion.output;
/* 127 */       if (conversion.propagateTags && out instanceof NSSTag) { NSSTag nssTag = (NSSTag)out;
/* 128 */         nssTag.forEachElement(normalizedSimpleStack -> mapper.setValueFromConversion(conversion.count, normalizedSimpleStack, conversion.ingredients)); }
/*     */       
/* 130 */       mapper.setValueFromConversion(conversion.count, out, conversion.ingredients); }
/*     */   
/*     */   }
/*     */   
/*     */   public static CustomConversionFile parseJson(Reader json) {
/* 135 */     return (CustomConversionFile)GSON.fromJson(new BufferedReader(json), CustomConversionFile.class);
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\mappers\customConversions\CustomConversionMapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */