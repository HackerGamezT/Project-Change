/*    */ package moze_intel.projecte.api.imc;
/*    */ 
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ 
/*    */ public final class WorldTransmutationEntry extends Record {
/*    */   private final BlockState origin;
/*    */   private final BlockState result;
/*    */   private final BlockState altResult;
/*    */   
/* 10 */   public BlockState altResult() { return this.altResult; } public BlockState result() { return this.result; } public BlockState origin() { return this.origin; }
/*    */   
/*    */   public WorldTransmutationEntry(BlockState origin, BlockState result, BlockState altResult) {
/* 13 */     altResult = (altResult == null) ? result : altResult;
/*    */     this.origin = origin;
/*    */     this.result = result;
/*    */     this.altResult = altResult;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lmoze_intel/projecte/api/imc/WorldTransmutationEntry;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #10	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lmoze_intel/projecte/api/imc/WorldTransmutationEntry;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/api/imc/WorldTransmutationEntry;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #10	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lmoze_intel/projecte/api/imc/WorldTransmutationEntry;
/*    */   }
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lmoze_intel/projecte/api/imc/WorldTransmutationEntry;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #10	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lmoze_intel/projecte/api/imc/WorldTransmutationEntry;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\imc\WorldTransmutationEntry.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */