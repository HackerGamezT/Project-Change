/*    */ package moze_intel.projecte.network.commands;
/*    */ import com.mojang.brigadier.Message;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*    */ import java.util.function.Predicate;
/*    */ import moze_intel.projecte.PEPermissions;
/*    */ import moze_intel.projecte.config.CustomEMCParser;
/*    */ import moze_intel.projecte.network.commands.argument.NSSItemArgument;
/*    */ import moze_intel.projecte.network.commands.parser.NSSItemParser;
/*    */ import moze_intel.projecte.utils.text.PELang;
/*    */ import net.minecraft.commands.CommandBuildContext;
/*    */ import net.minecraft.commands.CommandSourceStack;
/*    */ import net.minecraft.commands.Commands;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public class RemoveEmcCMD {
/* 22 */   private static final SimpleCommandExceptionType EMPTY_STACK = new SimpleCommandExceptionType((Message)PELang.COMMAND_NO_ITEM.translate(new Object[0]));
/*    */   
/*    */   public static LiteralArgumentBuilder<CommandSourceStack> register(CommandBuildContext context) {
/* 25 */     return (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_("removeemc")
/* 26 */       .requires((Predicate)PEPermissions.COMMAND_REMOVE_EMC))
/* 27 */       .then(Commands.m_82129_("item", (ArgumentType)NSSItemArgument.nss(context))
/* 28 */         .executes(ctx -> removeEmc(ctx, NSSItemArgument.getNSS(ctx, "item")))))
/* 29 */       .executes(ctx -> removeEmc(ctx, getHeldStack(ctx)));
/*    */   }
/*    */   
/*    */   private static int removeEmc(CommandContext<CommandSourceStack> ctx, NSSItemParser.NSSItemResult stack) {
/* 33 */     String toRemove = stack.getStringRepresentation();
/* 34 */     CustomEMCParser.addToFile(toRemove, 0L);
/* 35 */     ((CommandSourceStack)ctx.getSource()).m_288197_(() -> PELang.COMMAND_REMOVE_SUCCESS.translate(new Object[] { toRemove }, ), true);
/* 36 */     Objects.requireNonNull(PELang.RELOAD_NOTICE); ((CommandSourceStack)ctx.getSource()).m_288197_(() -> rec$.translate(new Object[0]), true);
/* 37 */     return 1;
/*    */   }
/*    */   
/*    */   public static NSSItemParser.NSSItemResult getHeldStack(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
/* 41 */     ServerPlayer player = ((CommandSourceStack)ctx.getSource()).m_81375_();
/* 42 */     ItemStack stack = player.m_21205_();
/* 43 */     if (stack.m_41619_()) {
/* 44 */       stack = player.m_21206_();
/*    */     }
/* 46 */     if (stack.m_41619_()) {
/* 47 */       throw EMPTY_STACK.create();
/*    */     }
/* 49 */     return NSSItemParser.resultOf(stack);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\commands\RemoveEmcCMD.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */