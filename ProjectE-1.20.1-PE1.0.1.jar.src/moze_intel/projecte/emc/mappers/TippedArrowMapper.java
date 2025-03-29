/*    */ package moze_intel.projecte.emc.mappers;
/*    */ 
/*    */ import com.electronwill.nightconfig.core.file.CommentedFileConfig;
/*    */ import java.util.HashMap;
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
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.item.Items;
/*    */ import net.minecraft.world.item.alchemy.Potion;
/*    */ import net.minecraft.world.item.alchemy.PotionUtils;
/*    */ import net.minecraft.world.level.ItemLike;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ 
/*    */ @EMCMapper
/*    */ public class TippedArrowMapper
/*    */   implements IEMCMapper<NormalizedSimpleStack, Long>
/*    */ {
/*    */   public void addMappings(IMappingCollector<NormalizedSimpleStack, Long> mapper, CommentedFileConfig config, ReloadableServerResources serverResources, RegistryAccess registryAccess, ResourceManager resourceManager) {
/* 27 */     int recipeCount = 0;
/* 28 */     NSSItem nssArrow = NSSItem.createItem((ItemLike)Items.f_42412_);
/* 29 */     for (Potion potionType : ForgeRegistries.POTIONS.getValues()) {
/* 30 */       Map<NormalizedSimpleStack, Integer> ingredientsWithAmount = new HashMap<>();
/* 31 */       ingredientsWithAmount.put(nssArrow, Integer.valueOf(8));
/* 32 */       ingredientsWithAmount.put(NSSItem.createItem(PotionUtils.m_43549_(new ItemStack((ItemLike)Items.f_42739_), potionType)), Integer.valueOf(1));
/* 33 */       mapper.addConversion(8, NSSItem.createItem(PotionUtils.m_43549_(new ItemStack((ItemLike)Items.f_42738_), potionType)), ingredientsWithAmount);
/* 34 */       recipeCount++;
/*    */     } 
/* 36 */     PECore.debugLog("TippedArrowMapper Statistics:", new Object[0]);
/* 37 */     PECore.debugLog("Found {} Tipped Arrow Recipes", new Object[] { Integer.valueOf(recipeCount) });
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 42 */     return "TippedArrowMapper";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 47 */     return "Add Conversions for all lingering potions to arrow recipes";
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\mappers\TippedArrowMapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */