/*   */ package moze_intel.projecte.api.imc;
/*   */ public final class NSSCreatorInfo extends Record { private final String key; private final NSSCreator creator;
/*   */   public final String toString() {
/*   */     // Byte code:
/*   */     //   0: aload_0
/*   */     //   1: <illegal opcode> toString : (Lmoze_intel/projecte/api/imc/NSSCreatorInfo;)Ljava/lang/String;
/*   */     //   6: areturn
/*   */     // Line number table:
/*   */     //   Java source line number -> byte code offset
/*   */     //   #9	-> 0
/*   */     // Local variable table:
/*   */     //   start	length	slot	name	descriptor
/*   */     //   0	7	0	this	Lmoze_intel/projecte/api/imc/NSSCreatorInfo;
/*   */   }
/*   */   public final int hashCode() {
/*   */     // Byte code:
/*   */     //   0: aload_0
/*   */     //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/api/imc/NSSCreatorInfo;)I
/*   */     //   6: ireturn
/*   */     // Line number table:
/*   */     //   Java source line number -> byte code offset
/*   */     //   #9	-> 0
/*   */     // Local variable table:
/*   */     //   start	length	slot	name	descriptor
/*   */     //   0	7	0	this	Lmoze_intel/projecte/api/imc/NSSCreatorInfo;
/*   */   }
/* 9 */   public NSSCreatorInfo(String key, NSSCreator creator) { this.key = key; this.creator = creator; } public final boolean equals(Object o) { // Byte code:
/*   */     //   0: aload_0
/*   */     //   1: aload_1
/*   */     //   2: <illegal opcode> equals : (Lmoze_intel/projecte/api/imc/NSSCreatorInfo;Ljava/lang/Object;)Z
/*   */     //   7: ireturn
/*   */     // Line number table:
/*   */     //   Java source line number -> byte code offset
/*   */     //   #9	-> 0
/*   */     // Local variable table:
/*   */     //   start	length	slot	name	descriptor
/*   */     //   0	8	0	this	Lmoze_intel/projecte/api/imc/NSSCreatorInfo;
/* 9 */     //   0	8	1	o	Ljava/lang/Object; } public String key() { return this.key; } public NSSCreator creator() { return this.creator; }
/*   */    }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\imc\NSSCreatorInfo.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */