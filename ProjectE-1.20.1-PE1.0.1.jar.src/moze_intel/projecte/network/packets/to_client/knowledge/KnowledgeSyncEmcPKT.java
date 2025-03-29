/*    */ package moze_intel.projecte.network.packets.to_client.knowledge;
/*    */ 
/*    */ import java.math.BigInteger;
/*    */ import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
/*    */ import moze_intel.projecte.gameObjs.container.TransmutationContainer;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*    */ 
/*    */ public final class KnowledgeSyncEmcPKT extends Record implements IPEPacket {
/*    */   private final BigInteger emc;
/*    */   
/* 13 */   public KnowledgeSyncEmcPKT(BigInteger emc) { this.emc = emc; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lmoze_intel/projecte/network/packets/to_client/knowledge/KnowledgeSyncEmcPKT;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #13	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/* 13 */     //   0	7	0	this	Lmoze_intel/projecte/network/packets/to_client/knowledge/KnowledgeSyncEmcPKT; } public BigInteger emc() { return this.emc; }
/*    */   public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/network/packets/to_client/knowledge/KnowledgeSyncEmcPKT;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #13	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lmoze_intel/projecte/network/packets/to_client/knowledge/KnowledgeSyncEmcPKT; } public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lmoze_intel/projecte/network/packets/to_client/knowledge/KnowledgeSyncEmcPKT;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #13	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lmoze_intel/projecte/network/packets/to_client/knowledge/KnowledgeSyncEmcPKT;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   } public void handle(NetworkEvent.Context context) {
/* 17 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 18 */     if (player != null)
/* 19 */       player.getCapability(PECapabilities.KNOWLEDGE_CAPABILITY).ifPresent(cap -> {
/*    */             cap.setEmc(this.emc); AbstractContainerMenu patt842$temp = player.f_36096_;
/*    */             if (patt842$temp instanceof TransmutationContainer) {
/*    */               TransmutationContainer container = (TransmutationContainer)patt842$temp;
/*    */               container.transmutationInventory.updateClientTargets();
/*    */             } 
/*    */           }); 
/* 26 */     PECore.debugLog("** RECEIVED TRANSMUTATION EMC DATA CLIENTSIDE **", new Object[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 31 */     buffer.m_130070_(this.emc.toString());
/*    */   }
/*    */   
/*    */   public static KnowledgeSyncEmcPKT decode(FriendlyByteBuf buffer) {
/* 35 */     String emc = buffer.m_130277_();
/* 36 */     return new KnowledgeSyncEmcPKT(emc.isEmpty() ? BigInteger.ZERO : new BigInteger(emc));
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\packets\to_client\knowledge\KnowledgeSyncEmcPKT.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */