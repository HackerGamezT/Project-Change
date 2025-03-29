/*     */ package moze_intel.projecte.emc.mappers;
/*     */ 
/*     */ import com.electronwill.nightconfig.core.file.CommentedFileConfig;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import moze_intel.projecte.PECore;
/*     */ import moze_intel.projecte.api.imc.CustomEMCRegistration;
/*     */ import moze_intel.projecte.api.mapper.EMCMapper;
/*     */ import moze_intel.projecte.api.mapper.EMCMapper.Instance;
/*     */ import moze_intel.projecte.api.mapper.IEMCMapper;
/*     */ import moze_intel.projecte.api.mapper.collector.IMappingCollector;
/*     */ import moze_intel.projecte.api.nss.NSSItem;
/*     */ import moze_intel.projecte.api.nss.NSSTag;
/*     */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*     */ import moze_intel.projecte.emc.EMCMappingHandler;
/*     */ import net.minecraft.core.RegistryAccess;
/*     */ import net.minecraft.server.ReloadableServerResources;
/*     */ import net.minecraft.server.packs.resources.ResourceManager;
/*     */ 
/*     */ @EMCMapper
/*     */ public class APICustomEMCMapper implements IEMCMapper<NormalizedSimpleStack, Long> {
/*     */   @Instance
/*  27 */   public static final APICustomEMCMapper INSTANCE = new APICustomEMCMapper();
/*     */   
/*     */   private static final int PRIORITY_MIN_VALUE = 0;
/*     */   
/*     */   private static final int PRIORITY_MAX_VALUE = 512;
/*     */   
/*     */   private static final int PRIORITY_DEFAULT_VALUE = 1;
/*     */   
/*  35 */   private final Map<String, Map<NormalizedSimpleStack, Long>> customEMCforMod = new HashMap<>();
/*     */   
/*     */   public void registerCustomEMC(String modid, CustomEMCRegistration customEMCRegistration) {
/*  38 */     NormalizedSimpleStack stack = customEMCRegistration.stack();
/*  39 */     if (stack == null) {
/*     */       return;
/*     */     }
/*  42 */     long emcValue = customEMCRegistration.value();
/*  43 */     if (emcValue < 0L) {
/*  44 */       emcValue = 0L;
/*     */     }
/*  46 */     PECore.debugLog("Mod: '{}' registered a custom EMC value of: '{}' for the NormalizedSimpleStack: '{}'", new Object[] { modid, Long.valueOf(emcValue), stack });
/*  47 */     ((Map<NormalizedSimpleStack, Long>)this.customEMCforMod.computeIfAbsent(modid, k -> new HashMap<>())).put(stack, Long.valueOf(emcValue));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  52 */     return "APICustomEMCMapper";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  57 */     return "Allows other mods to easily set EMC values using the ProjectEAPI";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addMappings(IMappingCollector<NormalizedSimpleStack, Long> mapper, CommentedFileConfig config, ReloadableServerResources serverResources, RegistryAccess registryAccess, ResourceManager resourceManager) {
/*  63 */     Map<String, Integer> priorityMap = new HashMap<>();
/*     */     
/*  65 */     for (String modId : this.customEMCforMod.keySet()) {
/*  66 */       String configKey = getName() + ".priority." + getName();
/*  67 */       int priority = ((Integer)EMCMappingHandler.getOrSetDefault(config, configKey, "Priority for this mod", Integer.valueOf(1))).intValue();
/*  68 */       priorityMap.put(modId, Integer.valueOf(priority));
/*     */     } 
/*     */     
/*  71 */     List<String> modIds = new ArrayList<>(this.customEMCforMod.keySet());
/*  72 */     Objects.requireNonNull(priorityMap); modIds.sort(Comparator.<String>comparingInt(priorityMap::get).reversed());
/*     */     
/*  74 */     for (String modId : modIds) {
/*  75 */       String modIdOrUnknown = (modId == null) ? "unknown mod" : modId;
/*  76 */       if (this.customEMCforMod.containsKey(modId)) {
/*  77 */         for (Map.Entry<NormalizedSimpleStack, Long> entry : (Iterable<Map.Entry<NormalizedSimpleStack, Long>>)((Map)this.customEMCforMod.get(modId)).entrySet()) {
/*  78 */           NormalizedSimpleStack normStack = entry.getKey();
/*  79 */           long emc = ((Long)entry.getValue()).longValue();
/*  80 */           if (isAllowedToSet(modId, normStack, Long.valueOf(emc), config)) {
/*  81 */             mapper.setValueBefore(normStack, Long.valueOf(emc));
/*  82 */             if (normStack instanceof NSSTag) { NSSTag nssTag = (NSSTag)normStack;
/*     */               
/*  84 */               nssTag.forEachElement(normalizedSimpleStack -> mapper.setValueBefore(normalizedSimpleStack, Long.valueOf(emc))); }
/*     */             
/*  86 */             PECore.debugLog("{} setting value for {} to {}", new Object[] { modIdOrUnknown, normStack, Long.valueOf(emc) }); continue;
/*     */           } 
/*  88 */           PECore.debugLog("Disallowed {} to set the value for {} to {}", new Object[] { modIdOrUnknown, normStack, Long.valueOf(emc) });
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isAllowedToSet(String modId, NormalizedSimpleStack stack, Long value, CommentedFileConfig config) {
/*     */     String resourceLocation;
/*  97 */     if (stack instanceof NSSItem) { NSSItem nssItem = (NSSItem)stack;
/*     */       
/*  99 */       resourceLocation = nssItem.getResourceLocation().toString(); }
/*     */     else
/* 101 */     { resourceLocation = "IntermediateFakeItemsUsedInRecipes:"; }
/*     */     
/* 103 */     String modForItem = resourceLocation.substring(0, resourceLocation.indexOf(':'));
/* 104 */     String configPath = String.format("permissions.%s.%s", new Object[] { modId, modForItem });
/* 105 */     String comment = String.format("Allow mod '%s' to set and or remove values for mod '%s'. Options: [both, set, remove, none]", new Object[] { modId, modForItem });
/* 106 */     String permission = (String)EMCMappingHandler.getOrSetDefault(config, configPath, comment, "both");
/* 107 */     if (permission.equals("both")) {
/* 108 */       return true;
/*     */     }
/* 110 */     if (value.longValue() == 0L) {
/* 111 */       return permission.equals("remove");
/*     */     }
/* 113 */     return permission.equals("set");
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\mappers\APICustomEMCMapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */