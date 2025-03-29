/*     */ package moze_intel.projecte.network.commands.parser;
/*     */ import com.mojang.brigadier.ImmutableStringReader;
/*     */ import com.mojang.brigadier.Message;
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*     */ import com.mojang.brigadier.suggestion.Suggestions;
/*     */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*     */ import com.mojang.datafixers.util.Either;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.function.Function;
/*     */ import moze_intel.projecte.utils.RegistryUtils;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.commands.SharedSuggestionProvider;
/*     */ import net.minecraft.core.Holder;
/*     */ import net.minecraft.core.HolderLookup;
/*     */ import net.minecraft.core.HolderSet;
/*     */ import net.minecraft.core.registries.Registries;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.TagParser;
/*     */ import net.minecraft.resources.ResourceKey;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class NSSItemParser {
/*     */   private static final DynamicCommandExceptionType UNKNOWN_ITEM;
/*     */   private static final DynamicCommandExceptionType UNKNOWN_TAG;
/*     */   
/*     */   static {
/*  34 */     Objects.requireNonNull(PELang.UNKNOWN_ITEM); UNKNOWN_ITEM = new DynamicCommandExceptionType(xva$0 -> rec$.translate(new Object[] { xva$0 }));
/*  35 */     Objects.requireNonNull(PELang.UNKNOWN_TAG); UNKNOWN_TAG = new DynamicCommandExceptionType(xva$0 -> rec$.translate(new Object[] { xva$0 }));
/*  36 */   } private static final Function<SuggestionsBuilder, CompletableFuture<Suggestions>> SUGGEST_NOTHING = SuggestionsBuilder::buildFuture;
/*     */   
/*     */   private static final char SYNTAX_START_NBT = '{';
/*     */   
/*     */   private static final char SYNTAX_TAG = '#';
/*     */   private final HolderLookup<Item> items;
/*     */   private final StringReader reader;
/*     */   private Either<Holder<Item>, ResourceLocation> result;
/*     */   @Nullable
/*     */   private CompoundTag nbt;
/*  46 */   private Function<SuggestionsBuilder, CompletableFuture<Suggestions>> suggestions = SUGGEST_NOTHING;
/*     */   
/*     */   public NSSItemParser(HolderLookup<Item> items, StringReader readerIn) {
/*  49 */     this.items = items;
/*  50 */     this.reader = readerIn;
/*     */   }
/*     */   
/*     */   public static NSSItemResult parseResult(HolderLookup<Item> items, StringReader reader) throws CommandSyntaxException {
/*  54 */     int cursor = reader.getCursor();
/*     */     try {
/*  56 */       NSSItemParser nssItemParser = new NSSItemParser(items, reader);
/*  57 */       nssItemParser.parse();
/*  58 */       return (NSSItemResult)nssItemParser.result.map(item -> new ItemResult(item, nssItemParser.nbt), TagResult::new);
/*  59 */     } catch (CommandSyntaxException e) {
/*  60 */       reader.setCursor(cursor);
/*  61 */       throw e;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CompletableFuture<Suggestions> fillSuggestions(HolderLookup<Item> items, SuggestionsBuilder builder) {
/*  71 */     StringReader reader = new StringReader(builder.getInput());
/*  72 */     reader.setCursor(builder.getStart());
/*  73 */     NSSItemParser parser = new NSSItemParser(items, reader);
/*     */     try {
/*  75 */       parser.parse();
/*  76 */     } catch (CommandSyntaxException commandSyntaxException) {}
/*     */     
/*  78 */     return parser.suggestions.apply(builder.createOffset(reader.getCursor()));
/*     */   }
/*     */   
/*     */   private void parse() throws CommandSyntaxException {
/*  82 */     this.suggestions = this::suggestTagOrItem;
/*  83 */     int cursor = this.reader.getCursor();
/*  84 */     if (this.reader.canRead() && this.reader.peek() == '#') {
/*     */       
/*  86 */       this.reader.expect('#');
/*  87 */       this.suggestions = this::suggestTag;
/*  88 */       ResourceLocation name = ResourceLocation.m_135818_(this.reader);
/*  89 */       Optional<? extends HolderSet<Item>> tag = this.items.m_254901_(TagKey.m_203882_(Registries.f_256913_, name));
/*  90 */       tag.orElseThrow(() -> {
/*     */             this.reader.setCursor(cursor);
/*     */             
/*     */             return UNKNOWN_TAG.createWithContext((ImmutableStringReader)this.reader, name);
/*     */           });
/*  95 */       this.result = Either.right(name);
/*     */     } else {
/*     */       
/*  98 */       ResourceLocation name = ResourceLocation.m_135818_(this.reader);
/*  99 */       Optional<Holder.Reference<Item>> item = this.items.m_254902_(ResourceKey.m_135785_(Registries.f_256913_, name));
/* 100 */       this.result = Either.left(item.<Throwable>orElseThrow(() -> {
/*     */               this.reader.setCursor(cursor);
/*     */               return UNKNOWN_ITEM.createWithContext((ImmutableStringReader)this.reader, name);
/*     */             }));
/* 104 */       this.suggestions = this::suggestOpenNbt;
/* 105 */       if (this.reader.canRead() && this.reader.peek() == '{') {
/* 106 */         this.suggestions = SUGGEST_NOTHING;
/* 107 */         this.nbt = (new TagParser(this.reader)).m_129373_();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private CompletableFuture<Suggestions> suggestOpenNbt(SuggestionsBuilder builder) {
/* 113 */     if (builder.getRemaining().isEmpty()) {
/* 114 */       builder.suggest(String.valueOf('{'));
/*     */     }
/* 116 */     return builder.buildFuture();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private CompletableFuture<Suggestions> suggestTag(SuggestionsBuilder builder) {
/* 125 */     return SharedSuggestionProvider.m_205106_(this.items.m_214063_().map(reference -> reference.m_205839_().f_203868_()), builder, String.valueOf('#'));
/*     */   }
/*     */   
/*     */   private CompletableFuture<Suggestions> suggestItem(SuggestionsBuilder builder) {
/* 129 */     return SharedSuggestionProvider.m_82957_(this.items.m_214062_().map(reference -> reference.m_205785_().m_135782_()), builder);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private CompletableFuture<Suggestions> suggestTagOrItem(SuggestionsBuilder builder) {
/* 138 */     suggestTag(builder);
/* 139 */     return suggestItem(builder);
/*     */   }
/*     */   
/*     */   public static NSSItemResult resultOf(ItemStack stack) {
/* 143 */     return new ItemResult(stack.m_41720_(), stack.m_41783_());
/*     */   } public static interface NSSItemResult {
/*     */     String getStringRepresentation();
/*     */   } private static final class ItemResult extends Record implements NSSItemResult { private final Item item; @Nullable
/*     */     private final CompoundTag nbt; public final String toString() {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lmoze_intel/projecte/network/commands/parser/NSSItemParser$ItemResult;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #151	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lmoze_intel/projecte/network/commands/parser/NSSItemParser$ItemResult;
/*     */     } public final int hashCode() {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/network/commands/parser/NSSItemParser$ItemResult;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #151	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lmoze_intel/projecte/network/commands/parser/NSSItemParser$ItemResult;
/* 151 */     } private ItemResult(Item item, @Nullable CompoundTag nbt) { this.item = item; this.nbt = nbt; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lmoze_intel/projecte/network/commands/parser/NSSItemParser$ItemResult;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #151	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lmoze_intel/projecte/network/commands/parser/NSSItemParser$ItemResult;
/* 151 */       //   0	8	1	o	Ljava/lang/Object; } public Item item() { return this.item; } @Nullable public CompoundTag nbt() { return this.nbt; }
/*     */     
/*     */     public ItemResult(Holder<Item> item, @Nullable CompoundTag nbt) {
/* 154 */       this((Item)item.get(), nbt);
/*     */     }
/*     */ 
/*     */     
/*     */     public String getStringRepresentation() {
/* 159 */       String registryName = RegistryUtils.getName(this.item).toString();
/* 160 */       if (this.nbt == null) {
/* 161 */         return registryName;
/*     */       }
/* 163 */       return registryName + registryName;
/*     */     } }
/*     */   private static final class TagResult extends Record implements NSSItemResult { private final ResourceLocation tagName;
/*     */     
/* 167 */     private TagResult(ResourceLocation tagName) { this.tagName = tagName; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lmoze_intel/projecte/network/commands/parser/NSSItemParser$TagResult;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #167	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lmoze_intel/projecte/network/commands/parser/NSSItemParser$TagResult; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/network/commands/parser/NSSItemParser$TagResult;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #167	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lmoze_intel/projecte/network/commands/parser/NSSItemParser$TagResult; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lmoze_intel/projecte/network/commands/parser/NSSItemParser$TagResult;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #167	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lmoze_intel/projecte/network/commands/parser/NSSItemParser$TagResult;
/* 167 */       //   0	8	1	o	Ljava/lang/Object; } public ResourceLocation tagName() { return this.tagName; }
/*     */ 
/*     */     
/*     */     public String getStringRepresentation() {
/* 171 */       return "#" + this.tagName.toString();
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\commands\parser\NSSItemParser.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */