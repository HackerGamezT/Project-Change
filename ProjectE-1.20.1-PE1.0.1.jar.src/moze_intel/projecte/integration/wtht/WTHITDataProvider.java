/*    */ package moze_intel.projecte.integration.wtht;
/*    */ 
/*    */ import mcp.mobius.waila.api.IBlockAccessor;
/*    */ import mcp.mobius.waila.api.IBlockComponentProvider;
/*    */ import mcp.mobius.waila.api.IPluginConfig;
/*    */ import mcp.mobius.waila.api.ITooltip;
/*    */ import moze_intel.projecte.config.ProjectEConfig;
/*    */ import moze_intel.projecte.utils.EMCHelper;
/*    */ import net.minecraft.world.level.ItemLike;
/*    */ 
/*    */ public class WTHITDataProvider implements IBlockComponentProvider {
/* 12 */   static final WTHITDataProvider INSTANCE = new WTHITDataProvider();
/*    */ 
/*    */   
/*    */   public void appendBody(ITooltip tooltip, IBlockAccessor accessor, IPluginConfig config) {
/* 16 */     if (ProjectEConfig.server.misc.hwylaTOPDisplay.get()) {
/* 17 */       long value = EMCHelper.getEmcValue((ItemLike)accessor.getBlock());
/* 18 */       if (value > 0L)
/* 19 */         tooltip.addLine(EMCHelper.getEmcTextComponent(value, 1)); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\wtht\WTHITDataProvider.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */