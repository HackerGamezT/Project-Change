/*    */ package moze_intel.projecte.api;
/*    */ 
/*    */ import moze_intel.projecte.api.proxy.IEMCProxy;
/*    */ import moze_intel.projecte.api.proxy.ITransmutationProxy;
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
/*    */ public final class ProjectEAPI
/*    */ {
/*    */   public static final String PROJECTE_MODID = "projecte";
/*    */   
/*    */   @Deprecated(forRemoval = true, since = "MC 1.20.1")
/*    */   public static IEMCProxy getEMCProxy() {
/* 22 */     return IEMCProxy.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated(forRemoval = true, since = "MC 1.20.1")
/*    */   public static ITransmutationProxy getTransmutationProxy() {
/* 34 */     return ITransmutationProxy.INSTANCE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\ProjectEAPI.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */