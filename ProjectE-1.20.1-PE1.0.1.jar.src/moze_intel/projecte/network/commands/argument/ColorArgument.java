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
/*    */ import java.util.List;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import net.minecraft.commands.SharedSuggestionProvider;
/*    */ import net.minecraft.world.item.DyeColor;
/*    */ 
/*    */ public class ColorArgument
/*    */   implements ArgumentType<DyeColor> {
/* 18 */   private static final List<String> EXAMPLES = Arrays.asList(new String[] { "red", "brown", "light_gray" });
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ColorArgument color() {
/* 24 */     return new ColorArgument();
/*    */   }
/*    */ 
/*    */   
/*    */   public DyeColor parse(StringReader reader) throws CommandSyntaxException {
/* 29 */     String s = reader.readUnquotedString();
/* 30 */     for (DyeColor c : DyeColor.values()) {
/* 31 */       if (c.m_7912_().equals(s)) {
/* 32 */         return c;
/*    */       }
/*    */     } 
/* 35 */     throw net.minecraft.commands.arguments.ColorArgument.f_85459_.create(s);
/*    */   }
/*    */   
/*    */   public static <S> DyeColor getColor(CommandContext<S> context, String name) {
/* 39 */     return (DyeColor)context.getArgument(name, DyeColor.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
/* 44 */     return SharedSuggestionProvider.m_82981_(Arrays.<DyeColor>stream(DyeColor.values()).map(DyeColor::m_7912_), builder);
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 49 */     return EXAMPLES;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\commands\argument\ColorArgument.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */