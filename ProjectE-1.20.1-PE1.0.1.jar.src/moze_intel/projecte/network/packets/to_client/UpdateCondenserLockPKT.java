/*    */ package moze_intel.projecte.network.packets.to_client;
/*    */ 
/*    */ import moze_intel.projecte.api.ItemInfo;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public final class UpdateCondenserLockPKT extends Record implements IPEPacket {
/*    */   private final short windowId;
/*    */   @Nullable
/*    */   private final ItemInfo lockInfo;
/*    */   
/* 13 */   public UpdateCondenserLockPKT(short windowId, @Nullable ItemInfo lockInfo) { this.windowId = windowId; this.lockInfo = lockInfo; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lmoze_intel/projecte/network/packets/to_client/UpdateCondenserLockPKT;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #13	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/* 13 */     //   0	7	0	this	Lmoze_intel/projecte/network/packets/to_client/UpdateCondenserLockPKT; } public short windowId() { return this.windowId; } public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/network/packets/to_client/UpdateCondenserLockPKT;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #13	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lmoze_intel/projecte/network/packets/to_client/UpdateCondenserLockPKT; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lmoze_intel/projecte/network/packets/to_client/UpdateCondenserLockPKT;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #13	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lmoze_intel/projecte/network/packets/to_client/UpdateCondenserLockPKT;
/* 13 */     //   0	8	1	o	Ljava/lang/Object; } @Nullable public ItemInfo lockInfo() { return this.lockInfo; }
/*    */ 
/*    */   
/*    */   public void handle(NetworkEvent.Context context) {
/* 17 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 18 */     if (player != null) { AbstractContainerMenu abstractContainerMenu = player.f_36096_; if (abstractContainerMenu instanceof CondenserContainer) { CondenserContainer container = (CondenserContainer)abstractContainerMenu; if (player.f_36096_.f_38840_ == this.windowId)
/* 19 */           container.updateLockInfo(this.lockInfo);  }
/*    */        }
/*    */   
/*    */   }
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 25 */     buffer.writeShort(this.windowId);
/* 26 */     if (this.lockInfo == null) {
/* 27 */       buffer.writeBoolean(false);
/*    */     } else {
/* 29 */       buffer.writeBoolean(true);
/* 30 */       buffer.writeRegistryIdUnsafe(ForgeRegistries.ITEMS, this.lockInfo.getItem());
/* 31 */       buffer.m_130079_(this.lockInfo.getNBT());
/*    */     } 
/*    */   }
/*    */   
/*    */   public static UpdateCondenserLockPKT decode(FriendlyByteBuf buffer) {
/* 36 */     short windowId = buffer.readShort();
/* 37 */     ItemInfo lockInfo = null;
/* 38 */     if (buffer.readBoolean()) {
/* 39 */       lockInfo = ItemInfo.fromItem((ItemLike)buffer.readRegistryIdUnsafe(ForgeRegistries.ITEMS), buffer.m_130260_());
/*    */     }
/* 41 */     return new UpdateCondenserLockPKT(windowId, lockInfo);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\packets\to_client\UpdateCondenserLockPKT.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */