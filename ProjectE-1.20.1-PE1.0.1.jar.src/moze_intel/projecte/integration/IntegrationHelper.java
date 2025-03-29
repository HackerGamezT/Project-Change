/*    */ package moze_intel.projecte.integration;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import moze_intel.projecte.capability.ItemCapability;
/*    */ import moze_intel.projecte.integration.top.TOPIntegration;
/*    */ import net.minecraftforge.fml.ModList;
/*    */ import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IntegrationHelper
/*    */ {
/*    */   public static final String CURIO_MODID = "curios";
/*    */   public static final String TOP_MODID = "theoneprobe";
/*    */   public static final Supplier<Supplier<ItemCapability<?>>> CURIO_CAP_SUPPLIER = () -> moze_intel.projecte.integration.curios.CurioItemCapability::new;
/*    */   
/*    */   public static void sendIMCMessages(InterModEnqueueEvent event) {
/* 19 */     ModList modList = ModList.get();
/* 20 */     if (modList.isLoaded("theoneprobe"))
/* 21 */       TOPIntegration.sendIMC(event); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\IntegrationHelper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */