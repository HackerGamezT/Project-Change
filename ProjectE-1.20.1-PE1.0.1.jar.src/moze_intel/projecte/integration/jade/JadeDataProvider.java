/*    */ package moze_intel.projecte.integration.jade;
/*    */ 
/*    */ import moze_intel.projecte.config.ProjectEConfig;
/*    */ import moze_intel.projecte.utils.EMCHelper;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.level.ItemLike;
/*    */ import snownee.jade.api.BlockAccessor;
/*    */ import snownee.jade.api.IBlockComponentProvider;
/*    */ import snownee.jade.api.ITooltip;
/*    */ import snownee.jade.api.config.IPluginConfig;
/*    */ 
/*    */ public class JadeDataProvider implements IBlockComponentProvider {
/* 13 */   static final JadeDataProvider INSTANCE = new JadeDataProvider();
/*    */ 
/*    */   
/*    */   public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
/* 17 */     if (ProjectEConfig.server.misc.hwylaTOPDisplay.get()) {
/* 18 */       long value = EMCHelper.getEmcValue((ItemLike)accessor.getBlock());
/* 19 */       if (value > 0L) {
/* 20 */         tooltip.add(EMCHelper.getEmcTextComponent(value, 1));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getUid() {
/* 27 */     return PEJadeConstants.EMC_PROVIDER;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\jade\JadeDataProvider.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */