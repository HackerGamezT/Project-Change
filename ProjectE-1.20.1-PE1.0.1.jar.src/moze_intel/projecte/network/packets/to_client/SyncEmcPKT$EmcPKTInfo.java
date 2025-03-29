/*    */ package moze_intel.projecte.network.packets.to_client;
/*    */ 
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.world.item.Item;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class EmcPKTInfo
/*    */   extends Record
/*    */ {
/*    */   private final Item item;
/*    */   @Nullable
/*    */   private final CompoundTag nbt;
/*    */   private final long emc;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lmoze_intel/projecte/network/packets/to_client/SyncEmcPKT$EmcPKTInfo;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #46	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lmoze_intel/projecte/network/packets/to_client/SyncEmcPKT$EmcPKTInfo;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/network/packets/to_client/SyncEmcPKT$EmcPKTInfo;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #46	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lmoze_intel/projecte/network/packets/to_client/SyncEmcPKT$EmcPKTInfo;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lmoze_intel/projecte/network/packets/to_client/SyncEmcPKT$EmcPKTInfo;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #46	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lmoze_intel/projecte/network/packets/to_client/SyncEmcPKT$EmcPKTInfo;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public EmcPKTInfo(Item item, @Nullable CompoundTag nbt, long emc) {
/* 46 */     this.item = item; this.nbt = nbt; this.emc = emc; } public Item item() { return this.item; } @Nullable public CompoundTag nbt() { return this.nbt; } public long emc() { return this.emc; }
/*    */ 
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\packets\to_client\SyncEmcPKT$EmcPKTInfo.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */