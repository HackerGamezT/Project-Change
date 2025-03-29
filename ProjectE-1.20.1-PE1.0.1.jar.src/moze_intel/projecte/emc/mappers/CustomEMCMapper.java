/*    */ package moze_intel.projecte.emc.mappers;
/*    */ 
/*    */ import com.electronwill.nightconfig.core.file.CommentedFileConfig;
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.api.mapper.EMCMapper;
/*    */ import moze_intel.projecte.api.mapper.IEMCMapper;
/*    */ import moze_intel.projecte.api.mapper.collector.IMappingCollector;
/*    */ import moze_intel.projecte.api.nss.NSSTag;
/*    */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*    */ import moze_intel.projecte.config.CustomEMCParser;
/*    */ import net.minecraft.core.RegistryAccess;
/*    */ import net.minecraft.server.ReloadableServerResources;
/*    */ import net.minecraft.server.packs.resources.ResourceManager;
/*    */ 
/*    */ 
/*    */ @EMCMapper
/*    */ public class CustomEMCMapper
/*    */   implements IEMCMapper<NormalizedSimpleStack, Long>
/*    */ {
/*    */   public void addMappings(IMappingCollector<NormalizedSimpleStack, Long> mapper, CommentedFileConfig config, ReloadableServerResources serverResources, RegistryAccess registryAccess, ResourceManager resourceManager) {
/* 21 */     for (CustomEMCParser.CustomEMCEntry entry : CustomEMCParser.currentEntries.entries) {
/* 22 */       PECore.debugLog("Adding custom EMC value for {}: {}", new Object[] { entry.item, Long.valueOf(entry.emc) });
/* 23 */       mapper.setValueBefore(entry.item, Long.valueOf(entry.emc));
/* 24 */       NormalizedSimpleStack normalizedSimpleStack = entry.item; if (normalizedSimpleStack instanceof NSSTag) { NSSTag nssTag = (NSSTag)normalizedSimpleStack;
/*    */         
/* 26 */         nssTag.forEachElement(normalizedSimpleStack -> mapper.setValueBefore(normalizedSimpleStack, Long.valueOf(entry.emc))); }
/*    */     
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 33 */     return "CustomEMCMapper";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 38 */     return "Uses the `custom_emc.json` File to add EMC values.";
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\mappers\CustomEMCMapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */