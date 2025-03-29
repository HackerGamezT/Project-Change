/*    */ package moze_intel.projecte.integration.top;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraftforge.fml.InterModComms;
/*    */ import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
/*    */ 
/*    */ public class TOPIntegration
/*    */ {
/*    */   public static void sendIMC(InterModEnqueueEvent event) {
/* 10 */     InterModComms.sendTo("theoneprobe", "getTheOneProbe", PEProbeInfoProvider::new);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\top\TOPIntegration.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */