/*    */ package moze_intel.projecte.emc.mappers;
/*    */ 
/*    */ import com.electronwill.nightconfig.core.file.CommentedFileConfig;
/*    */ import moze_intel.projecte.api.mapper.EMCMapper;
/*    */ import moze_intel.projecte.api.mapper.IEMCMapper;
/*    */ import moze_intel.projecte.api.mapper.collector.IMappingCollector;
/*    */ import moze_intel.projecte.api.nss.NSSItem;
/*    */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*    */ import moze_intel.projecte.gameObjs.PETags;
/*    */ import net.minecraft.core.RegistryAccess;
/*    */ import net.minecraft.server.ReloadableServerResources;
/*    */ import net.minecraft.server.packs.resources.ResourceManager;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.level.ItemLike;
/*    */ 
/*    */ @EMCMapper
/*    */ public class RawOreBlacklistMapper
/*    */   implements IEMCMapper<NormalizedSimpleStack, Long>
/*    */ {
/*    */   public void addMappings(IMappingCollector<NormalizedSimpleStack, Long> mapper, CommentedFileConfig config, ReloadableServerResources serverResources, RegistryAccess registryAccess, ResourceManager resourceManager) {
/* 21 */     for (Item rawOre : PETags.Items.RAW_ORES_LOOKUP.tag()) {
/* 22 */       NSSItem nssRawORe = NSSItem.createItem((ItemLike)rawOre);
/* 23 */       mapper.setValueBefore(nssRawORe, Long.valueOf(0L));
/* 24 */       mapper.setValueAfter(nssRawORe, Long.valueOf(0L));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 30 */     return "RawOresBlacklistMapper";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 35 */     return "Set EMC=0 for everything in the forge:raw_materials tag";
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\mappers\RawOreBlacklistMapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */