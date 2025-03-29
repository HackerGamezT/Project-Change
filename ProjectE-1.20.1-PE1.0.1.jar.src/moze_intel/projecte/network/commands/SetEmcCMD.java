/*    */ package moze_intel.projecte.network.commands;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.LongArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import moze_intel.projecte.config.CustomEMCParser;
/*    */ import moze_intel.projecte.network.commands.argument.NSSItemArgument;
/*    */ import moze_intel.projecte.network.commands.parser.NSSItemParser;
/*    */ import moze_intel.projecte.utils.text.PELang;
/*    */ import net.minecraft.commands.CommandBuildContext;
/*    */ import net.minecraft.commands.CommandSourceStack;
/*    */ import net.minecraft.commands.Commands;
/*    */ import net.minecraft.network.chat.Component;
/*    */ 
/*    */ public class SetEmcCMD {
/*    */   public static LiteralArgumentBuilder<CommandSourceStack> register(CommandBuildContext context) {
/* 19 */     return (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.m_82127_("setemc")
/* 20 */       .requires((Predicate)PEPermissions.COMMAND_SET_EMC))
/* 21 */       .then(((RequiredArgumentBuilder)Commands.m_82129_("emc", (ArgumentType)LongArgumentType.longArg(0L, Long.MAX_VALUE))
/* 22 */         .then(Commands.m_82129_("item", (ArgumentType)NSSItemArgument.nss(context))
/* 23 */           .executes(ctx -> setEmc(ctx, NSSItemArgument.getNSS(ctx, "item"), LongArgumentType.getLong(ctx, "emc")))))
/* 24 */         .executes(ctx -> setEmc(ctx, RemoveEmcCMD.getHeldStack(ctx), LongArgumentType.getLong(ctx, "emc"))));
/*    */   }
/*    */ 
/*    */   
/*    */   private static int setEmc(CommandContext<CommandSourceStack> ctx, NSSItemParser.NSSItemResult stack, long emc) {
/* 29 */     String toSet = stack.getStringRepresentation();
/* 30 */     CustomEMCParser.addToFile(toSet, emc);
/* 31 */     ((CommandSourceStack)ctx.getSource()).m_288197_(() -> PELang.COMMAND_SET_SUCCESS.translate(new Object[] { toSet, Long.valueOf(emc) }, ), true);
/* 32 */     Objects.requireNonNull(PELang.RELOAD_NOTICE); ((CommandSourceStack)ctx.getSource()).m_288197_(() -> rec$.translate(new Object[0]), true);
/* 33 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\commands\SetEmcCMD.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */