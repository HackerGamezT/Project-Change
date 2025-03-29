/*    */ package moze_intel.projecte.network.packets.to_client;
/*    */ 
/*    */ import moze_intel.projecte.api.capabilities.IAlchBagProvider;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ 
/*    */ public final class SyncBagDataPKT extends Record implements IPEPacket {
/*    */   private final CompoundTag nbt;
/*    */   
/* 11 */   public SyncBagDataPKT(CompoundTag nbt) { this.nbt = nbt; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lmoze_intel/projecte/network/packets/to_client/SyncBagDataPKT;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/* 11 */     //   0	7	0	this	Lmoze_intel/projecte/network/packets/to_client/SyncBagDataPKT; } public CompoundTag nbt() { return this.nbt; }
/*    */   public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/network/packets/to_client/SyncBagDataPKT;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lmoze_intel/projecte/network/packets/to_client/SyncBagDataPKT; } public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lmoze_intel/projecte/network/packets/to_client/SyncBagDataPKT;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lmoze_intel/projecte/network/packets/to_client/SyncBagDataPKT;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   } public void handle(NetworkEvent.Context context) {
/* 15 */     if ((Minecraft.m_91087_()).f_91074_ != null) {
/* 16 */       (Minecraft.m_91087_()).f_91074_.getCapability(PECapabilities.ALCH_BAG_CAPABILITY).ifPresent(cap -> cap.deserializeNBT((Tag)this.nbt));
/*    */     }
/* 18 */     PECore.debugLog("** RECEIVED BAGS CLIENTSIDE **", new Object[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 23 */     buffer.m_130079_(this.nbt);
/*    */   }
/*    */   
/*    */   public static SyncBagDataPKT decode(FriendlyByteBuf buffer) {
/* 27 */     return new SyncBagDataPKT(buffer.m_130260_());
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\packets\to_client\SyncBagDataPKT.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */