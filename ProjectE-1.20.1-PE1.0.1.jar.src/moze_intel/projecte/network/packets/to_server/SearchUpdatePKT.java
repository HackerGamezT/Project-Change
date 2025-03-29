/*    */ package moze_intel.projecte.network.packets.to_server;
/*    */ 
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public final class SearchUpdatePKT extends Record implements IPEPacket {
/*    */   private final int slot;
/*    */   private final ItemStack itemStack;
/*    */   
/* 10 */   public int slot() { return this.slot; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lmoze_intel/projecte/network/packets/to_server/SearchUpdatePKT;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #10	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lmoze_intel/projecte/network/packets/to_server/SearchUpdatePKT; } public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/network/packets/to_server/SearchUpdatePKT;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #10	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lmoze_intel/projecte/network/packets/to_server/SearchUpdatePKT; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lmoze_intel/projecte/network/packets/to_server/SearchUpdatePKT;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #10	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lmoze_intel/projecte/network/packets/to_server/SearchUpdatePKT;
/* 10 */     //   0	8	1	o	Ljava/lang/Object; } public ItemStack itemStack() { return this.itemStack; }
/*    */   
/*    */   public SearchUpdatePKT(int slot, ItemStack itemStack) {
/* 13 */     itemStack = itemStack.m_41777_();
/*    */     this.slot = slot;
/*    */     this.itemStack = itemStack;
/*    */   }
/*    */   public void handle(NetworkEvent.Context context) {
/* 18 */     ServerPlayer serverPlayer = context.getSender();
/* 19 */     if (serverPlayer != null) { AbstractContainerMenu abstractContainerMenu = ((Player)serverPlayer).f_36096_; if (abstractContainerMenu instanceof TransmutationContainer) { TransmutationContainer container = (TransmutationContainer)abstractContainerMenu;
/* 20 */         container.transmutationInventory.writeIntoOutputSlot(this.slot, this.itemStack); }
/*    */        }
/*    */   
/*    */   }
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 26 */     buffer.m_130130_(this.slot);
/* 27 */     buffer.m_130055_(this.itemStack);
/*    */   }
/*    */   
/*    */   public static SearchUpdatePKT decode(FriendlyByteBuf buffer) {
/* 31 */     return new SearchUpdatePKT(buffer.m_130242_(), buffer.m_130267_());
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\packets\to_server\SearchUpdatePKT.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */