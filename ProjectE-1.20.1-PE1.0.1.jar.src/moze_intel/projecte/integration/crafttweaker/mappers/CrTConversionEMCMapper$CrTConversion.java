/*    */ package moze_intel.projecte.integration.crafttweaker.mappers;
/*    */ 
/*    */ import java.util.Map;
/*    */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
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
/*    */ public final class CrTConversion
/*    */   extends Record
/*    */ {
/*    */   private final NormalizedSimpleStack output;
/*    */   private final int amount;
/*    */   private final boolean propagateTags;
/*    */   private final boolean set;
/*    */   private final Map<NormalizedSimpleStack, Integer> ingredients;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lmoze_intel/projecte/integration/crafttweaker/mappers/CrTConversionEMCMapper$CrTConversion;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #63	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lmoze_intel/projecte/integration/crafttweaker/mappers/CrTConversionEMCMapper$CrTConversion;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/integration/crafttweaker/mappers/CrTConversionEMCMapper$CrTConversion;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #63	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lmoze_intel/projecte/integration/crafttweaker/mappers/CrTConversionEMCMapper$CrTConversion;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lmoze_intel/projecte/integration/crafttweaker/mappers/CrTConversionEMCMapper$CrTConversion;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #63	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lmoze_intel/projecte/integration/crafttweaker/mappers/CrTConversionEMCMapper$CrTConversion;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public CrTConversion(NormalizedSimpleStack output, int amount, boolean propagateTags, boolean set, Map<NormalizedSimpleStack, Integer> ingredients) {
/* 63 */     this.output = output; this.amount = amount; this.propagateTags = propagateTags; this.set = set; this.ingredients = ingredients; } public NormalizedSimpleStack output() { return this.output; } public int amount() { return this.amount; } public boolean propagateTags() { return this.propagateTags; } public boolean set() { return this.set; } public Map<NormalizedSimpleStack, Integer> ingredients() { return this.ingredients; }
/*    */ 
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\crafttweaker\mappers\CrTConversionEMCMapper$CrTConversion.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */