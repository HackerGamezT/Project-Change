/*    */ package moze_intel.projecte.handlers;
/*    */ 
/*    */ import moze_intel.projecte.capability.managing.BasicCapabilityResolver;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.util.NonNullSupplier;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ public class Provider
/*    */   extends BasicCapabilityResolver<InternalTimers>
/*    */ {
/*    */   public Provider() {
/* 69 */     super(InternalTimers::new);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Capability<InternalTimers> getMatchingCapability() {
/* 75 */     return InternalTimers.CAPABILITY;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\handlers\InternalTimers$Provider.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */