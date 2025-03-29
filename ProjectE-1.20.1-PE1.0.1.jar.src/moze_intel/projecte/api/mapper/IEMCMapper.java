/*    */ package moze_intel.projecte.api.mapper;
/*    */ 
/*    */ import com.electronwill.nightconfig.core.file.CommentedFileConfig;
/*    */ import moze_intel.projecte.api.mapper.collector.IMappingCollector;
/*    */ import net.minecraft.core.RegistryAccess;
/*    */ import net.minecraft.server.ReloadableServerResources;
/*    */ import net.minecraft.server.packs.resources.ResourceManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IEMCMapper<T, V extends Comparable<V>>
/*    */ {
/*    */   String getName();
/*    */   
/*    */   String getDescription();
/*    */   
/*    */   default boolean isAvailable() {
/* 38 */     return true;
/*    */   }
/*    */   
/*    */   void addMappings(IMappingCollector<T, V> paramIMappingCollector, CommentedFileConfig paramCommentedFileConfig, ReloadableServerResources paramReloadableServerResources, RegistryAccess paramRegistryAccess, ResourceManager paramResourceManager);
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\mapper\IEMCMapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */