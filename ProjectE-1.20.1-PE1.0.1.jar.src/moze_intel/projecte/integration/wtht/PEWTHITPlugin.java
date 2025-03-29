/*    */ package moze_intel.projecte.integration.wtht;
/*    */ 
/*    */ import mcp.mobius.waila.api.IRegistrar;
/*    */ import mcp.mobius.waila.api.IWailaPlugin;
/*    */ import mcp.mobius.waila.api.TooltipPosition;
/*    */ import mcp.mobius.waila.api.WailaPlugin;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ 
/*    */ 
/*    */ @WailaPlugin(id = "projecte")
/*    */ public class PEWTHITPlugin
/*    */   implements IWailaPlugin
/*    */ {
/*    */   public void register(IRegistrar registrar) {
/* 15 */     registrar.addComponent(WTHITDataProvider.INSTANCE, TooltipPosition.BODY, Block.class);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\wtht\PEWTHITPlugin.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */