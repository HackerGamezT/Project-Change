/*   */ package moze_intel.projecte.api.imc;
/*   */ public final class CustomEMCRegistration extends Record { private final NormalizedSimpleStack stack; private final long value;
/*   */   public final String toString() {
/*   */     // Byte code:
/*   */     //   0: aload_0
/*   */     //   1: <illegal opcode> toString : (Lmoze_intel/projecte/api/imc/CustomEMCRegistration;)Ljava/lang/String;
/*   */     //   6: areturn
/*   */     // Line number table:
/*   */     //   Java source line number -> byte code offset
/*   */     //   #9	-> 0
/*   */     // Local variable table:
/*   */     //   start	length	slot	name	descriptor
/*   */     //   0	7	0	this	Lmoze_intel/projecte/api/imc/CustomEMCRegistration;
/*   */   }
/*   */   public final int hashCode() {
/*   */     // Byte code:
/*   */     //   0: aload_0
/*   */     //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/api/imc/CustomEMCRegistration;)I
/*   */     //   6: ireturn
/*   */     // Line number table:
/*   */     //   Java source line number -> byte code offset
/*   */     //   #9	-> 0
/*   */     // Local variable table:
/*   */     //   start	length	slot	name	descriptor
/*   */     //   0	7	0	this	Lmoze_intel/projecte/api/imc/CustomEMCRegistration;
/*   */   }
/* 9 */   public CustomEMCRegistration(NormalizedSimpleStack stack, long value) { this.stack = stack; this.value = value; } public final boolean equals(Object o) { // Byte code:
/*   */     //   0: aload_0
/*   */     //   1: aload_1
/*   */     //   2: <illegal opcode> equals : (Lmoze_intel/projecte/api/imc/CustomEMCRegistration;Ljava/lang/Object;)Z
/*   */     //   7: ireturn
/*   */     // Line number table:
/*   */     //   Java source line number -> byte code offset
/*   */     //   #9	-> 0
/*   */     // Local variable table:
/*   */     //   start	length	slot	name	descriptor
/*   */     //   0	8	0	this	Lmoze_intel/projecte/api/imc/CustomEMCRegistration;
/* 9 */     //   0	8	1	o	Ljava/lang/Object; } public NormalizedSimpleStack stack() { return this.stack; } public long value() { return this.value; }
/*   */    }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\imc\CustomEMCRegistration.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */