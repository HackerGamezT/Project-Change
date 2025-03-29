/*    */ package moze_intel.projecte.api.proxy;
/*    */ 
/*    */ import java.util.ServiceLoader;
/*    */ import java.util.UUID;
/*    */ import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface ITransmutationProxy
/*    */ {
/* 13 */   public static final ITransmutationProxy INSTANCE = (ITransmutationProxy)ServiceLoader.<ITransmutationProxy>load(ITransmutationProxy.class).findFirst()
/* 14 */     .orElseThrow(() -> new IllegalStateException("No valid ServiceImpl for ITransmutationProxy found, ProjectE may be absent, damaged, or outdated"));
/*    */   
/*    */   @NotNull
/*    */   IKnowledgeProvider getKnowledgeProviderFor(@NotNull UUID paramUUID);
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\proxy\ITransmutationProxy.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */