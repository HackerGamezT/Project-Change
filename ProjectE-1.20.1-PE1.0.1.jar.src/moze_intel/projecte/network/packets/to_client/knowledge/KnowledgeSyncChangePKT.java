/*    */ package moze_intel.projecte.network.packets.to_client.knowledge;
/*    */ 
/*    */ import moze_intel.projecte.api.ItemInfo;
/*    */ import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
/*    */ import moze_intel.projecte.gameObjs.container.TransmutationContainer;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*    */ 
/*    */ public final class KnowledgeSyncChangePKT extends Record implements IPEPacket {
/*    */   private final ItemInfo change;
/*    */   private final boolean learned;
/*    */   
/* 14 */   public KnowledgeSyncChangePKT(ItemInfo change, boolean learned) { this.change = change; this.learned = learned; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lmoze_intel/projecte/network/packets/to_client/knowledge/KnowledgeSyncChangePKT;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #14	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/* 14 */     //   0	7	0	this	Lmoze_intel/projecte/network/packets/to_client/knowledge/KnowledgeSyncChangePKT; } public ItemInfo change() { return this.change; } public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/network/packets/to_client/knowledge/KnowledgeSyncChangePKT;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #14	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lmoze_intel/projecte/network/packets/to_client/knowledge/KnowledgeSyncChangePKT; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lmoze_intel/projecte/network/packets/to_client/knowledge/KnowledgeSyncChangePKT;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #14	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lmoze_intel/projecte/network/packets/to_client/knowledge/KnowledgeSyncChangePKT;
/* 14 */     //   0	8	1	o	Ljava/lang/Object; } public boolean learned() { return this.learned; }
/*    */ 
/*    */   
/*    */   public void handle(NetworkEvent.Context context) {
/* 18 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 19 */     if (player != null)
/* 20 */       player.getCapability(PECapabilities.KNOWLEDGE_CAPABILITY).ifPresent(cap -> { if (this.learned) { if (!cap.hasKnowledge(this.change) && cap.addKnowledge(this.change)) {
/*    */                 AbstractContainerMenu patt985$temp = player.f_36096_; if (patt985$temp instanceof TransmutationContainer) {
/*    */                   TransmutationContainer container = (TransmutationContainer)patt985$temp; container.transmutationInventory.itemLearned();
/*    */                 } 
/*    */               }  }
/*    */             else if (cap.hasKnowledge(this.change) && cap.removeKnowledge(this.change))
/*    */             { AbstractContainerMenu patt1188$temp = player.f_36096_; if (patt1188$temp instanceof TransmutationContainer) {
/*    */                 TransmutationContainer container = (TransmutationContainer)patt1188$temp; container.transmutationInventory.itemUnlearned();
/*    */               }  }
/*    */           
/* 30 */           });  PECore.debugLog("** RECEIVED TRANSMUTATION KNOWLEDGE CHANGE DATA CLIENTSIDE **", new Object[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 35 */     buffer.writeRegistryIdUnsafe(ForgeRegistries.ITEMS, this.change.getItem());
/* 36 */     buffer.m_130079_(this.change.getNBT());
/* 37 */     buffer.writeBoolean(this.learned);
/*    */   }
/*    */   
/*    */   public static KnowledgeSyncChangePKT decode(FriendlyByteBuf buffer) {
/* 41 */     return new KnowledgeSyncChangePKT(ItemInfo.fromItem((ItemLike)buffer.readRegistryIdUnsafe(ForgeRegistries.ITEMS), buffer.m_130260_()), buffer.readBoolean());
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\packets\to_client\knowledge\KnowledgeSyncChangePKT.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */