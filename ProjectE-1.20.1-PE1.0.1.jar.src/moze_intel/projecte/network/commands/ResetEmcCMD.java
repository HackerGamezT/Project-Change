/*    */ package moze_intel.projecte.network.commands;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.util.function.Predicate;
/*    */ import moze_intel.projecte.PEPermissions;
/*    */ import moze_intel.projecte.config.CustomEMCParser;
/*    */ import moze_intel.projecte.network.commands.argument.NSSItemArgument;
/*    */ import moze_intel.projecte.network.commands.parser.NSSItemParser;
/*    */ import moze_intel.projecte.utils.text.PELang;
/*    */ import net.minecraft.commands.CommandBuildContext;
/*    */ import net.minecraft.commands.CommandRuntimeException;
/*    */ import net.minecraft.commands.CommandSourceStack;
/*    */ import net.minecraft.commands.Commands;
/*    */ import net.minecraft.network.chat.Component;
/*    */ 
/*    */ public class ResetEmcCMD {
/*    */   public static LiteralArgumentBuilder<CommandSourceStack> register(CommandBuildContext context) {
/* 19 */     return (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_("resetemc")
/* 20 */       .requires((Predicate)PEPermissions.COMMAND_RESET_EMC))
/* 21 */       .then(Commands.m_82129_("item", (ArgumentType)NSSItemArgument.nss(context))
/* 22 */         .executes(ctx -> resetEmc(ctx, NSSItemArgument.getNSS(ctx, "item")))))
/* 23 */       .executes(ctx -> resetEmc(ctx, RemoveEmcCMD.getHeldStack(ctx)));
/*    */   }
/*    */   
/*    */   private static int resetEmc(CommandContext<CommandSourceStack> ctx, NSSItemParser.NSSItemResult stack) {
/* 27 */     String toReset = stack.getStringRepresentation();
/* 28 */     if (CustomEMCParser.removeFromFile(toReset)) {
/* 29 */       ((CommandSourceStack)ctx.getSource()).m_288197_(() -> PELang.COMMAND_RESET_SUCCESS.translate(new Object[] { toReset }, ), true);
/* 30 */       Objects.requireNonNull(PELang.RELOAD_NOTICE); ((CommandSourceStack)ctx.getSource()).m_288197_(() -> rec$.translate(new Object[0]), true);
/* 31 */       return 1;
/*    */     } 
/* 33 */     throw new CommandRuntimeException(PELang.COMMAND_INVALID_ITEM.translate(new Object[] { toReset }));
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\commands\ResetEmcCMD.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */