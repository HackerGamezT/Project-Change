/*    */ package moze_intel.projecte.integration.jei.world_transmute;
/*    */ 
/*    */ final class StateInfo extends Record {
/*    */   private final ItemStack item;
/*    */   private final FluidStack fluid;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lmoze_intel/projecte/integration/jei/world_transmute/WorldTransmuteEntry$StateInfo;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #19	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lmoze_intel/projecte/integration/jei/world_transmute/WorldTransmuteEntry$StateInfo;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/integration/jei/world_transmute/WorldTransmuteEntry$StateInfo;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #19	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lmoze_intel/projecte/integration/jei/world_transmute/WorldTransmuteEntry$StateInfo;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lmoze_intel/projecte/integration/jei/world_transmute/WorldTransmuteEntry$StateInfo;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #19	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lmoze_intel/projecte/integration/jei/world_transmute/WorldTransmuteEntry$StateInfo;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/* 19 */   private StateInfo(ItemStack item, FluidStack fluid) { this.item = item; this.fluid = fluid; } public ItemStack item() { return this.item; } public FluidStack fluid() { return this.fluid; }
/*    */   
/*    */   public boolean isEmpty() {
/* 22 */     return (this.item.m_41619_() && this.fluid.isEmpty());
/*    */   }
/*    */   
/*    */   public Either<ItemStack, FluidStack> toEither() {
/* 26 */     if (this.fluid.isEmpty()) {
/* 27 */       return Either.left(this.item);
/*    */     }
/* 29 */     return Either.right(this.fluid);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\jei\world_transmute\WorldTransmuteEntry$StateInfo.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */