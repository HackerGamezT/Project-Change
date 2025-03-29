/*     */ package moze_intel.projecte.network.commands.parser;
/*     */ 
/*     */ import moze_intel.projecte.utils.RegistryUtils;
/*     */ import net.minecraft.core.Holder;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.world.item.Item;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class ItemResult
/*     */   extends Record
/*     */   implements NSSItemParser.NSSItemResult
/*     */ {
/*     */   private final Item item;
/*     */   @Nullable
/*     */   private final CompoundTag nbt;
/*     */   
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lmoze_intel/projecte/network/commands/parser/NSSItemParser$ItemResult;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #151	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lmoze_intel/projecte/network/commands/parser/NSSItemParser$ItemResult;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/network/commands/parser/NSSItemParser$ItemResult;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #151	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lmoze_intel/projecte/network/commands/parser/NSSItemParser$ItemResult;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lmoze_intel/projecte/network/commands/parser/NSSItemParser$ItemResult;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #151	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lmoze_intel/projecte/network/commands/parser/NSSItemParser$ItemResult;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   private ItemResult(Item item, @Nullable CompoundTag nbt) {
/* 151 */     this.item = item; this.nbt = nbt; } public Item item() { return this.item; } @Nullable public CompoundTag nbt() { return this.nbt; }
/*     */   
/*     */   public ItemResult(Holder<Item> item, @Nullable CompoundTag nbt) {
/* 154 */     this((Item)item.get(), nbt);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getStringRepresentation() {
/* 159 */     String registryName = RegistryUtils.getName(this.item).toString();
/* 160 */     if (this.nbt == null) {
/* 161 */       return registryName;
/*     */     }
/* 163 */     return registryName + registryName;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\commands\parser\NSSItemParser$ItemResult.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */