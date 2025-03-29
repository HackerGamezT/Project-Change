/*    */ package moze_intel.projecte.integration.jade;
/*    */ 
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import snownee.jade.api.IWailaClientRegistration;
/*    */ import snownee.jade.api.IWailaPlugin;
/*    */ import snownee.jade.api.WailaPlugin;
/*    */ 
/*    */ @WailaPlugin
/*    */ public class PEJadePlugin
/*    */   implements IWailaPlugin
/*    */ {
/*    */   public void registerClient(IWailaClientRegistration registrar) {
/* 13 */     registrar.registerBlockComponent(JadeDataProvider.INSTANCE, Block.class);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\jade\PEJadePlugin.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */