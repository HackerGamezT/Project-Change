/*    */ package moze_intel.projecte.network.packets.to_client;
/*    */ 
/*    */ import java.util.List;
/*    */ import moze_intel.projecte.network.packets.IPEPacket;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ 
/*    */ public final class SyncFuelMapperPKT extends Record implements IPEPacket {
/*    */   private final List<Item> items;
/*    */   
/* 12 */   public SyncFuelMapperPKT(List<Item> items) { this.items = items; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lmoze_intel/projecte/network/packets/to_client/SyncFuelMapperPKT;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #12	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/* 12 */     //   0	7	0	this	Lmoze_intel/projecte/network/packets/to_client/SyncFuelMapperPKT; } public List<Item> items() { return this.items; }
/*    */   public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/network/packets/to_client/SyncFuelMapperPKT;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #12	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lmoze_intel/projecte/network/packets/to_client/SyncFuelMapperPKT; } public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lmoze_intel/projecte/network/packets/to_client/SyncFuelMapperPKT;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #12	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lmoze_intel/projecte/network/packets/to_client/SyncFuelMapperPKT;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   } public void handle(NetworkEvent.Context context) {
/* 16 */     FuelMapper.setFuelMap(this.items);
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 21 */     buffer.m_130130_(this.items.size());
/* 22 */     for (Item item : this.items) {
/* 23 */       buffer.writeRegistryIdUnsafe(ForgeRegistries.ITEMS, item);
/*    */     }
/*    */   }
/*    */   
/*    */   public static SyncFuelMapperPKT decode(FriendlyByteBuf buffer) {
/* 28 */     int size = buffer.m_130242_();
/* 29 */     List<Item> items = new ArrayList<>(size);
/* 30 */     for (int i = 0; i < size; i++) {
/* 31 */       items.add((Item)buffer.readRegistryIdUnsafe(ForgeRegistries.ITEMS));
/*    */     }
/* 33 */     return new SyncFuelMapperPKT(items);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\packets\to_client\SyncFuelMapperPKT.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */