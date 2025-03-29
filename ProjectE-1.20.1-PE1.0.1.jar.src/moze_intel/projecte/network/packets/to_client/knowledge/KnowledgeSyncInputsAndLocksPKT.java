/*    */ package moze_intel.projecte.network.packets.to_client.knowledge;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
/*    */ import moze_intel.projecte.gameObjs.container.TransmutationContainer;
/*    */ import moze_intel.projecte.gameObjs.container.inventory.TransmutationInventory;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public final class KnowledgeSyncInputsAndLocksPKT extends Record implements IPEPacket {
/*    */   private final Map<Integer, ItemStack> stacksToSync;
/*    */   private final IKnowledgeProvider.TargetUpdateType updateTargets;
/*    */   
/* 17 */   public KnowledgeSyncInputsAndLocksPKT(Map<Integer, ItemStack> stacksToSync, IKnowledgeProvider.TargetUpdateType updateTargets) { this.stacksToSync = stacksToSync; this.updateTargets = updateTargets; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lmoze_intel/projecte/network/packets/to_client/knowledge/KnowledgeSyncInputsAndLocksPKT;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #17	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/* 17 */     //   0	7	0	this	Lmoze_intel/projecte/network/packets/to_client/knowledge/KnowledgeSyncInputsAndLocksPKT; } public Map<Integer, ItemStack> stacksToSync() { return this.stacksToSync; } public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/network/packets/to_client/knowledge/KnowledgeSyncInputsAndLocksPKT;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #17	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lmoze_intel/projecte/network/packets/to_client/knowledge/KnowledgeSyncInputsAndLocksPKT; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lmoze_intel/projecte/network/packets/to_client/knowledge/KnowledgeSyncInputsAndLocksPKT;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #17	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lmoze_intel/projecte/network/packets/to_client/knowledge/KnowledgeSyncInputsAndLocksPKT;
/* 17 */     //   0	8	1	o	Ljava/lang/Object; } public IKnowledgeProvider.TargetUpdateType updateTargets() { return this.updateTargets; }
/*    */ 
/*    */   
/*    */   public void handle(NetworkEvent.Context context) {
/* 21 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 22 */     if (player != null)
/* 23 */       player.getCapability(PECapabilities.KNOWLEDGE_CAPABILITY).ifPresent(cap -> {
/*    */             cap.receiveInputsAndLocks(this.stacksToSync); if (this.updateTargets != IKnowledgeProvider.TargetUpdateType.NONE) {
/*    */               AbstractContainerMenu patt1196$temp = player.f_36096_; if (patt1196$temp instanceof TransmutationContainer) {
/*    */                 TransmutationContainer container = (TransmutationContainer)patt1196$temp;
/*    */                 TransmutationInventory transmutationInventory = container.transmutationInventory;
/*    */                 if (this.updateTargets == IKnowledgeProvider.TargetUpdateType.ALL) {
/*    */                   transmutationInventory.updateClientTargets();
/*    */                 } else {
/*    */                   transmutationInventory.checkForUpdates();
/*    */                 } 
/*    */               } 
/*    */             } 
/*    */           }); 
/* 36 */     PECore.debugLog("** RECEIVED TRANSMUTATION INPUT AND LOCK DATA CLIENTSIDE **", new Object[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 41 */     buffer.m_130130_(this.stacksToSync.size());
/* 42 */     for (Map.Entry<Integer, ItemStack> entry : this.stacksToSync.entrySet()) {
/* 43 */       buffer.m_130130_(((Integer)entry.getKey()).intValue());
/* 44 */       buffer.m_130055_(entry.getValue());
/*    */     } 
/* 46 */     buffer.m_130068_((Enum)this.updateTargets);
/*    */   }
/*    */   
/*    */   public static KnowledgeSyncInputsAndLocksPKT decode(FriendlyByteBuf buffer) {
/* 50 */     int size = buffer.m_130242_();
/* 51 */     Map<Integer, ItemStack> syncedStacks = new HashMap<>(size);
/* 52 */     for (int i = 0; i < size; i++) {
/* 53 */       syncedStacks.put(Integer.valueOf(buffer.m_130242_()), buffer.m_130267_());
/*    */     }
/* 55 */     return new KnowledgeSyncInputsAndLocksPKT(syncedStacks, (IKnowledgeProvider.TargetUpdateType)buffer.m_130066_(IKnowledgeProvider.TargetUpdateType.class));
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\packets\to_client\knowledge\KnowledgeSyncInputsAndLocksPKT.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */