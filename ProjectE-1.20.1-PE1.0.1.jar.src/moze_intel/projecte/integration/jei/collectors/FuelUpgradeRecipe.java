/*   */ package moze_intel.projecte.integration.jei.collectors;
/*   */ public final class FuelUpgradeRecipe extends Record { private final ItemStack input;
/*   */   private final ItemStack output;
/*   */   private final long upgradeEMC;
/*   */   
/* 6 */   public long upgradeEMC() { return this.upgradeEMC; } public ItemStack output() { return this.output; } public ItemStack input() { return this.input; } public final boolean equals(Object o) { // Byte code:
/*   */     //   0: aload_0
/*   */     //   1: aload_1
/*   */     //   2: <illegal opcode> equals : (Lmoze_intel/projecte/integration/jei/collectors/FuelUpgradeRecipe;Ljava/lang/Object;)Z
/*   */     //   7: ireturn
/*   */     // Line number table:
/*   */     //   Java source line number -> byte code offset
/*   */     //   #6	-> 0
/*   */     // Local variable table:
/*   */     //   start	length	slot	name	descriptor
/*   */     //   0	8	0	this	Lmoze_intel/projecte/integration/jei/collectors/FuelUpgradeRecipe;
/* 6 */     //   0	8	1	o	Ljava/lang/Object; } public FuelUpgradeRecipe(ItemStack input, ItemStack output, long upgradeEMC) { this.input = input; this.output = output; this.upgradeEMC = upgradeEMC; }
/*   */   public final int hashCode() { // Byte code:
/*   */     //   0: aload_0
/*   */     //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/integration/jei/collectors/FuelUpgradeRecipe;)I
/*   */     //   6: ireturn
/*   */     // Line number table:
/*   */     //   Java source line number -> byte code offset
/*   */     //   #6	-> 0
/*   */     // Local variable table:
/*   */     //   start	length	slot	name	descriptor
/*   */     //   0	7	0	this	Lmoze_intel/projecte/integration/jei/collectors/FuelUpgradeRecipe; }
/*   */   public final String toString() { // Byte code:
/*   */     //   0: aload_0
/*   */     //   1: <illegal opcode> toString : (Lmoze_intel/projecte/integration/jei/collectors/FuelUpgradeRecipe;)Ljava/lang/String;
/*   */     //   6: areturn
/*   */     // Line number table:
/*   */     //   Java source line number -> byte code offset
/*   */     //   #6	-> 0
/*   */     // Local variable table:
/*   */     //   start	length	slot	name	descriptor
/*   */     //   0	7	0	this	Lmoze_intel/projecte/integration/jei/collectors/FuelUpgradeRecipe; } public FuelUpgradeRecipe(ItemStack input, ItemStack output) {
/* 9 */     this(input, output, EMCHelper.getEmcValue(output) - EMCHelper.getEmcValue(input));
/*   */   } }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\jei\collectors\FuelUpgradeRecipe.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */