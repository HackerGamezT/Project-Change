/*    */ package moze_intel.projecte.emc.mappers;
/*    */ 
/*    */ import com.electronwill.nightconfig.core.file.CommentedFileConfig;
/*    */ import java.util.Collections;
/*    */ import moze_intel.projecte.api.mapper.IEMCMapper;
/*    */ import moze_intel.projecte.api.mapper.collector.IMappingCollector;
/*    */ import moze_intel.projecte.api.nss.AbstractNSSTag;
/*    */ import moze_intel.projecte.api.nss.NSSTag;
/*    */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*    */ import net.minecraft.core.RegistryAccess;
/*    */ import net.minecraft.server.ReloadableServerResources;
/*    */ import net.minecraft.server.packs.resources.ResourceManager;
/*    */ 
/*    */ public class TagMapper
/*    */   implements IEMCMapper<NormalizedSimpleStack, Long>
/*    */ {
/*    */   public void addMappings(IMappingCollector<NormalizedSimpleStack, Long> mapper, CommentedFileConfig config, ReloadableServerResources serverResources, RegistryAccess registryAccess, ResourceManager resourceManager) {
/* 18 */     AbstractNSSTag.getAllCreatedTags().forEach(stack -> stack.forEachElement(()));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 28 */     return "TagMapper";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 33 */     return "Adds back and forth conversions of objects and their Tag variant. (EMC values assigned to tags will not behave properly if this mapper is disabled)";
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\mappers\TagMapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */