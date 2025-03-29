/*    */ package moze_intel.projecte.impl;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import java.util.UUID;
/*    */ import java.util.function.Supplier;
/*    */ import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
/*    */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*    */ import moze_intel.projecte.api.proxy.ITransmutationProxy;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraftforge.common.util.NonNullSupplier;
/*    */ import net.minecraftforge.fml.DistExecutor;
/*    */ import net.minecraftforge.fml.util.thread.SidedThreadGroups;
/*    */ import net.minecraftforge.server.ServerLifecycleHooks;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class TransmutationProxyImpl implements ITransmutationProxy {
/*    */   @NotNull
/*    */   public IKnowledgeProvider getKnowledgeProviderFor(@NotNull UUID playerUUID) {
/* 20 */     if (Thread.currentThread().getThreadGroup() != SidedThreadGroups.SERVER) {
/* 21 */       return (IKnowledgeProvider)DistExecutor.unsafeRunForDist(() -> (), () -> ());
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 28 */     Preconditions.checkNotNull(playerUUID);
/* 29 */     Preconditions.checkNotNull(ServerLifecycleHooks.getCurrentServer(), "Server must be running to query knowledge!");
/* 30 */     Player player = findOnlinePlayer(playerUUID);
/* 31 */     if (player != null) {
/* 32 */       return (IKnowledgeProvider)player.getCapability(PECapabilities.KNOWLEDGE_CAPABILITY).orElseThrow(NullPointerException::new);
/*    */     }
/* 34 */     return TransmutationOffline.forPlayer(playerUUID);
/*    */   }
/*    */ 
/*    */   
/*    */   private Player findOnlinePlayer(UUID playerUUID) {
/* 39 */     for (Player player : ServerLifecycleHooks.getCurrentServer().m_6846_().m_11314_()) {
/* 40 */       if (player.m_20148_().equals(playerUUID)) {
/* 41 */         return player;
/*    */       }
/*    */     } 
/* 44 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\impl\TransmutationProxyImpl.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */