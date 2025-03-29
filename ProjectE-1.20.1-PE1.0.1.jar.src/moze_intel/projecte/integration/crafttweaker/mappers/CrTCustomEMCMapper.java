/*    */ package moze_intel.projecte.integration.crafttweaker.mappers;
/*    */ 
/*    */ import com.electronwill.nightconfig.core.file.CommentedFileConfig;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.api.mapper.EMCMapper;
/*    */ import moze_intel.projecte.api.mapper.IEMCMapper;
/*    */ import moze_intel.projecte.api.mapper.collector.IMappingCollector;
/*    */ import moze_intel.projecte.api.nss.NSSTag;
/*    */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*    */ import net.minecraft.core.RegistryAccess;
/*    */ import net.minecraft.server.ReloadableServerResources;
/*    */ import net.minecraft.server.packs.resources.ResourceManager;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ @EMCMapper(requiredMods = {"crafttweaker"})
/*    */ public class CrTCustomEMCMapper
/*    */   implements IEMCMapper<NormalizedSimpleStack, Long> {
/* 20 */   private static final Map<NormalizedSimpleStack, Long> customEmcValues = new HashMap<>();
/*    */   
/*    */   public static void registerCustomEMC(@NotNull NormalizedSimpleStack stack, long emcValue) {
/* 23 */     customEmcValues.put(stack, Long.valueOf(emcValue));
/*    */   }
/*    */   
/*    */   public static void unregisterNSS(@NotNull NormalizedSimpleStack stack) {
/* 27 */     customEmcValues.remove(stack);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void addMappings(IMappingCollector<NormalizedSimpleStack, Long> mapper, CommentedFileConfig config, ReloadableServerResources serverResources, RegistryAccess registryAccess, ResourceManager resourceManager) {
/* 33 */     for (Map.Entry<NormalizedSimpleStack, Long> entry : customEmcValues.entrySet()) {
/* 34 */       NormalizedSimpleStack normStack = entry.getKey();
/* 35 */       long value = ((Long)entry.getValue()).longValue();
/* 36 */       mapper.setValueBefore(normStack, Long.valueOf(value));
/* 37 */       if (normStack instanceof NSSTag) { NSSTag nssTag = (NSSTag)normStack;
/*    */         
/* 39 */         nssTag.forEachElement(normalizedSimpleStack -> mapper.setValueBefore(normalizedSimpleStack, Long.valueOf(value))); }
/*    */       
/* 41 */       PECore.debugLog("CraftTweaker setting value for {} to {}", new Object[] { normStack, Long.valueOf(value) });
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 47 */     return "CrTCustomEMCMapper";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 52 */     return "Allows setting EMC values through CraftTweaker. This behaves similarly to if someone used the custom emc file instead.";
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\crafttweaker\mappers\CrTCustomEMCMapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */