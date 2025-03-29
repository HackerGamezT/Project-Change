/*    */ package moze_intel.projecte.emc.mappers;
/*    */ 
/*    */ import com.electronwill.nightconfig.core.file.CommentedFileConfig;
/*    */ import com.google.common.collect.BiMap;
/*    */ import java.util.Collections;
/*    */ import java.util.Map;
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.api.mapper.EMCMapper;
/*    */ import moze_intel.projecte.api.mapper.IEMCMapper;
/*    */ import moze_intel.projecte.api.mapper.collector.IMappingCollector;
/*    */ import moze_intel.projecte.api.nss.NSSItem;
/*    */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*    */ import net.minecraft.core.RegistryAccess;
/*    */ import net.minecraft.server.ReloadableServerResources;
/*    */ import net.minecraft.server.packs.resources.ResourceManager;
/*    */ import net.minecraft.world.level.ItemLike;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.WeatheringCopper;
/*    */ 
/*    */ @EMCMapper
/*    */ public class WeatheredCopperMapper
/*    */   implements IEMCMapper<NormalizedSimpleStack, Long> {
/*    */   public void addMappings(IMappingCollector<NormalizedSimpleStack, Long> mapper, CommentedFileConfig config, ReloadableServerResources serverResources, RegistryAccess registryAccess, ResourceManager resourceManager) {
/* 24 */     int recipeCount = 0;
/* 25 */     for (Map.Entry<Block, Block> entry : (Iterable<Map.Entry<Block, Block>>)((BiMap)WeatheringCopper.f_154886_.get()).entrySet()) {
/*    */       
/* 27 */       NSSItem unweathered = NSSItem.createItem((ItemLike)entry.getKey());
/* 28 */       NSSItem weathered = NSSItem.createItem((ItemLike)entry.getValue());
/* 29 */       mapper.addConversion(1, weathered, Collections.singleton(unweathered));
/* 30 */       mapper.addConversion(1, unweathered, Collections.singleton(weathered));
/* 31 */       recipeCount += 2;
/*    */     } 
/* 33 */     PECore.debugLog("WeatheredCopperMapper Statistics:", new Object[0]);
/* 34 */     PECore.debugLog("Found {} Weathered Copper Conversions", new Object[] { Integer.valueOf(recipeCount) });
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 39 */     return "WeatheredCopperMapper";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 44 */     return "Add Conversions for all weathered copper variants";
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\mappers\WeatheredCopperMapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */