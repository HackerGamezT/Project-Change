/*    */ package moze_intel.projecte.utils;
/*    */ 
/*    */ import net.minecraft.tags.TagKey;
/*    */ import net.minecraftforge.registries.tags.ITag;
/*    */ 
/*    */ public final class LazyTagLookup<TYPE> extends Record {
/*    */   private final TagKey<TYPE> key;
/*    */   private final Lazy<ITag<TYPE>> lazyTag;
/*    */   
/* 10 */   public LazyTagLookup(TagKey<TYPE> key, Lazy<ITag<TYPE>> lazyTag) { this.key = key; this.lazyTag = lazyTag; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lmoze_intel/projecte/utils/LazyTagLookup;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #10	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lmoze_intel/projecte/utils/LazyTagLookup;
/*    */     // Local variable type table:
/*    */     //   start	length	slot	name	signature
/* 10 */     //   0	7	0	this	Lmoze_intel/projecte/utils/LazyTagLookup<TTYPE;>; } public TagKey<TYPE> key() { return this.key; } public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/utils/LazyTagLookup;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #10	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lmoze_intel/projecte/utils/LazyTagLookup;
/*    */     // Local variable type table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	7	0	this	Lmoze_intel/projecte/utils/LazyTagLookup<TTYPE;>; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lmoze_intel/projecte/utils/LazyTagLookup;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #10	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lmoze_intel/projecte/utils/LazyTagLookup;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */     // Local variable type table:
/*    */     //   start	length	slot	name	signature
/* 10 */     //   0	8	0	this	Lmoze_intel/projecte/utils/LazyTagLookup<TTYPE;>; } public Lazy<ITag<TYPE>> lazyTag() { return this.lazyTag; }
/*    */   
/*    */   public static <TYPE> LazyTagLookup<TYPE> create(IForgeRegistry<TYPE> registry, TagKey<TYPE> key) {
/* 13 */     return new LazyTagLookup<>(key, Lazy.of(() -> tagManager(registry).getTag(key)));
/*    */   }
/*    */   
/*    */   public ITag<TYPE> tag() {
/* 17 */     return (ITag<TYPE>)this.lazyTag.get();
/*    */   }
/*    */   
/*    */   public boolean contains(TYPE element) {
/* 21 */     return tag().contains(element);
/*    */   }
/*    */   
/*    */   public boolean isEmpty() {
/* 25 */     return tag().isEmpty();
/*    */   }
/*    */   
/*    */   public static <TYPE> ITagManager<TYPE> tagManager(IForgeRegistry<TYPE> registry) {
/* 29 */     ITagManager<TYPE> tags = registry.tags();
/* 30 */     if (tags == null) {
/* 31 */       throw new IllegalStateException("Expected " + registry.getRegistryName() + " to have tags.");
/*    */     }
/* 33 */     return tags;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\project\\utils\LazyTagLookup.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */