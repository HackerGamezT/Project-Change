/*    */ package moze_intel.projecte.network.packets.to_client;
/*    */ 
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ 
/*    */ public final class UpdateWindowIntPKT extends Record implements IPEPacket {
/*    */   private final short windowId;
/*    */   private final short propId;
/*    */   private final int propVal;
/*    */   
/* 11 */   public UpdateWindowIntPKT(short windowId, short propId, int propVal) { this.windowId = windowId; this.propId = propId; this.propVal = propVal; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lmoze_intel/projecte/network/packets/to_client/UpdateWindowIntPKT;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/* 11 */     //   0	7	0	this	Lmoze_intel/projecte/network/packets/to_client/UpdateWindowIntPKT; } public short windowId() { return this.windowId; } public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/network/packets/to_client/UpdateWindowIntPKT;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lmoze_intel/projecte/network/packets/to_client/UpdateWindowIntPKT; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lmoze_intel/projecte/network/packets/to_client/UpdateWindowIntPKT;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lmoze_intel/projecte/network/packets/to_client/UpdateWindowIntPKT;
/* 11 */     //   0	8	1	o	Ljava/lang/Object; } public short propId() { return this.propId; } public int propVal() { return this.propVal; }
/*    */ 
/*    */   
/*    */   public void handle(NetworkEvent.Context context) {
/* 15 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 16 */     if (player != null) { AbstractContainerMenu abstractContainerMenu = player.f_36096_; if (abstractContainerMenu instanceof PEContainer) { PEContainer container = (PEContainer)abstractContainerMenu; if (player.f_36096_.f_38840_ == this.windowId)
/* 17 */           container.updateProgressBarInt(this.propId, this.propVal);  }
/*    */        }
/*    */   
/*    */   }
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 23 */     buffer.writeShort(this.windowId);
/* 24 */     buffer.writeShort(this.propId);
/* 25 */     buffer.m_130130_(this.propVal);
/*    */   }
/*    */   
/*    */   public static UpdateWindowIntPKT decode(FriendlyByteBuf buffer) {
/* 29 */     return new UpdateWindowIntPKT(buffer.readShort(), buffer.readShort(), buffer.m_130242_());
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\packets\to_client\UpdateWindowIntPKT.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */