/*    */ package moze_intel.projecte.network.packets.to_client;
/*    */ 
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.emc.EMCMappingHandler;
/*    */ import moze_intel.projecte.network.packets.IPEPacket;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class SyncEmcPKT
/*    */   implements IPEPacket {
/*    */   private final EmcPKTInfo[] data;
/*    */   
/*    */   public SyncEmcPKT(EmcPKTInfo[] data) {
/* 18 */     this.data = data;
/*    */   }
/*    */ 
/*    */   
/*    */   public void handle(NetworkEvent.Context context) {
/* 23 */     PECore.LOGGER.info("Receiving EMC data from server.");
/* 24 */     EMCMappingHandler.fromPacket(this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 29 */     buffer.m_130130_(this.data.length);
/* 30 */     for (EmcPKTInfo info : this.data) {
/* 31 */       buffer.writeRegistryIdUnsafe(ForgeRegistries.ITEMS, info.item);
/* 32 */       buffer.m_130079_(info.nbt());
/* 33 */       buffer.m_130103_(info.emc());
/*    */     } 
/*    */   }
/*    */   
/*    */   public static SyncEmcPKT decode(FriendlyByteBuf buffer) {
/* 38 */     int size = buffer.m_130242_();
/* 39 */     EmcPKTInfo[] data = new EmcPKTInfo[size];
/* 40 */     for (int i = 0; i < size; i++) {
/* 41 */       data[i] = new EmcPKTInfo((Item)buffer.readRegistryIdUnsafe(ForgeRegistries.ITEMS), buffer.m_130260_(), buffer.m_130258_());
/*    */     }
/* 43 */     return new SyncEmcPKT(data);
/*    */   } public static final class EmcPKTInfo extends Record { private final Item item; @Nullable
/*    */     private final CompoundTag nbt; private final long emc;
/* 46 */     public EmcPKTInfo(Item item, @Nullable CompoundTag nbt, long emc) { this.item = item; this.nbt = nbt; this.emc = emc; } public final String toString() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> toString : (Lmoze_intel/projecte/network/packets/to_client/SyncEmcPKT$EmcPKTInfo;)Ljava/lang/String;
/*    */       //   6: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #46	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/* 46 */       //   0	7	0	this	Lmoze_intel/projecte/network/packets/to_client/SyncEmcPKT$EmcPKTInfo; } public Item item() { return this.item; } public final int hashCode() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/network/packets/to_client/SyncEmcPKT$EmcPKTInfo;)I
/*    */       //   6: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #46	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lmoze_intel/projecte/network/packets/to_client/SyncEmcPKT$EmcPKTInfo; } public final boolean equals(Object o) { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: <illegal opcode> equals : (Lmoze_intel/projecte/network/packets/to_client/SyncEmcPKT$EmcPKTInfo;Ljava/lang/Object;)Z
/*    */       //   7: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #46	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	8	0	this	Lmoze_intel/projecte/network/packets/to_client/SyncEmcPKT$EmcPKTInfo;
/* 46 */       //   0	8	1	o	Ljava/lang/Object; } @Nullable public CompoundTag nbt() { return this.nbt; } public long emc() { return this.emc; }
/*    */      }
/*    */ 
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\packets\to_client\SyncEmcPKT.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */