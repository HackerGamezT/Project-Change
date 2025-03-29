/*    */ package moze_intel.projecte.network.commands.argument;
/*    */ 
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.suggestion.Suggestions;
/*    */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import moze_intel.projecte.network.commands.parser.NSSItemParser;
/*    */ import net.minecraft.commands.CommandBuildContext;
/*    */ import net.minecraft.core.HolderLookup;
/*    */ import net.minecraft.core.registries.Registries;
/*    */ import net.minecraft.world.item.Item;
/*    */ 
/*    */ 
/*    */ public class NSSItemArgument
/*    */   implements ArgumentType<NSSItemParser.NSSItemResult>
/*    */ {
/* 22 */   private static final Collection<String> EXAMPLES = Arrays.asList(new String[] { "stick", "minecraft:stick", "minecraft:stick{foo=bar}", "#minecraft:wool" });
/*    */   
/*    */   private final HolderLookup<Item> items;
/*    */   
/*    */   private NSSItemArgument(CommandBuildContext context) {
/* 27 */     this.items = context.m_227133_(Registries.f_256913_);
/*    */   }
/*    */   
/*    */   public static NSSItemArgument nss(CommandBuildContext context) {
/* 31 */     return new NSSItemArgument(context);
/*    */   }
/*    */ 
/*    */   
/*    */   public NSSItemParser.NSSItemResult parse(StringReader reader) throws CommandSyntaxException {
/* 36 */     return NSSItemParser.parseResult(this.items, reader);
/*    */   }
/*    */   
/*    */   public static <S> NSSItemParser.NSSItemResult getNSS(CommandContext<S> context, String name) {
/* 40 */     return (NSSItemParser.NSSItemResult)context.getArgument(name, NSSItemParser.NSSItemResult.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
/* 45 */     return NSSItemParser.fillSuggestions(this.items, builder);
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 50 */     return EXAMPLES;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\commands\argument\NSSItemArgument.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */