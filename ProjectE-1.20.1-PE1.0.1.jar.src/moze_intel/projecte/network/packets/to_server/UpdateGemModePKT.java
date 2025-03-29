/*    */ package moze_intel.projecte.network.packets.to_server;
/*    */ 
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ 
/*    */ public final class UpdateGemModePKT extends Record implements IPEPacket {
/*    */   private final boolean mode;
/*    */   
/* 11 */   public UpdateGemModePKT(boolean mode) { this.mode = mode; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lmoze_intel/projecte/network/packets/to_server/UpdateGemModePKT;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/* 11 */     //   0	7	0	this	Lmoze_intel/projecte/network/packets/to_server/UpdateGemModePKT; } public boolean mode() { return this.mode; }
/*    */   public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/network/packets/to_server/UpdateGemModePKT;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lmoze_intel/projecte/network/packets/to_server/UpdateGemModePKT; } public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lmoze_intel/projecte/network/packets/to_server/UpdateGemModePKT;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lmoze_intel/projecte/network/packets/to_server/UpdateGemModePKT;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   } public void handle(NetworkEvent.Context context) {
/* 15 */     ServerPlayer serverPlayer = context.getSender();
/* 16 */     if (serverPlayer != null) {
/* 17 */       ItemStack stack = serverPlayer.m_21205_();
/* 18 */       if (stack.m_41619_()) {
/* 19 */         stack = serverPlayer.m_21206_();
/*    */       }
/* 21 */       if (!stack.m_41619_() && stack.m_41720_() instanceof moze_intel.projecte.gameObjs.items.GemEternalDensity)
/*    */       {
/* 23 */         stack.m_41784_().m_128379_("Whitelist", this.mode);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 30 */     buffer.writeBoolean(this.mode);
/*    */   }
/*    */   
/*    */   public static UpdateGemModePKT decode(FriendlyByteBuf buffer) {
/* 34 */     return new UpdateGemModePKT(buffer.readBoolean());
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\packets\to_server\UpdateGemModePKT.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */