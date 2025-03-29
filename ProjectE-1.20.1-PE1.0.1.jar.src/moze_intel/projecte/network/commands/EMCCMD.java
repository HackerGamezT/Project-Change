/*     */ package moze_intel.projecte.network.commands;
/*     */ 
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.StringArgumentType;
/*     */ import com.mojang.brigadier.builder.ArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.math.BigInteger;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Predicate;
/*     */ import moze_intel.projecte.PEPermissions;
/*     */ import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
/*     */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*     */ import moze_intel.projecte.utils.TransmutationEMCFormatter;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import moze_intel.projecte.utils.text.TextComponentUtil;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.commands.CommandBuildContext;
/*     */ import net.minecraft.commands.CommandSourceStack;
/*     */ import net.minecraft.commands.Commands;
/*     */ import net.minecraft.commands.arguments.EntityArgument;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.network.chat.MutableComponent;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ 
/*     */ public class EMCCMD {
/*     */   private enum ActionType {
/*  29 */     ADD,
/*  30 */     REMOVE,
/*  31 */     SET,
/*  32 */     GET,
/*  33 */     TEST;
/*     */   }
/*     */   
/*     */   public static ArgumentBuilder<CommandSourceStack, ?> register(CommandBuildContext context) {
/*  37 */     return ((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_("emc")
/*  38 */       .requires((Predicate)PEPermissions.COMMAND_EMC))
/*  39 */       .then(((LiteralArgumentBuilder)Commands.m_82127_("add")
/*  40 */         .requires((Predicate)PEPermissions.COMMAND_EMC_ADD))
/*  41 */         .then(executeWithParameters(ActionType.ADD))))
/*     */       
/*  43 */       .then(((LiteralArgumentBuilder)Commands.m_82127_("remove")
/*  44 */         .requires((Predicate)PEPermissions.COMMAND_EMC_REMOVE))
/*  45 */         .then(executeWithParameters(ActionType.REMOVE))))
/*     */       
/*  47 */       .then(((LiteralArgumentBuilder)Commands.m_82127_("set")
/*  48 */         .requires((Predicate)PEPermissions.COMMAND_EMC_SET))
/*  49 */         .then(executeWithParameters(ActionType.SET))))
/*     */       
/*  51 */       .then(((LiteralArgumentBuilder)Commands.m_82127_("test")
/*  52 */         .requires((Predicate)PEPermissions.COMMAND_EMC_TEST))
/*  53 */         .then(executeWithParameters(ActionType.TEST))))
/*     */       
/*  55 */       .then(((LiteralArgumentBuilder)Commands.m_82127_("get")
/*  56 */         .requires((Predicate)PEPermissions.COMMAND_EMC_GET))
/*  57 */         .then(Commands.m_82129_("player", (ArgumentType)EntityArgument.m_91466_())
/*  58 */           .executes(ctx -> handle(ctx, ActionType.GET))));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static ArgumentBuilder<CommandSourceStack, ?> executeWithParameters(ActionType actionType) {
/*  64 */     return Commands.m_82129_("player", (ArgumentType)EntityArgument.m_91466_())
/*  65 */       .then(Commands.m_82129_("value", (ArgumentType)StringArgumentType.string())
/*  66 */         .executes(ctx -> handle(ctx, actionType)));
/*     */   }
/*     */ 
/*     */   
/*     */   private static MutableComponent formatEMC(BigInteger emc) {
/*  71 */     return TextComponentUtil.build(new Object[] { ChatFormatting.GRAY, TransmutationEMCFormatter.formatEMC(emc) });
/*     */   }
/*     */   private static int handle(CommandContext<CommandSourceStack> ctx, ActionType action) throws CommandSyntaxException {
/*     */     MutableComponent mutableComponent3, mutableComponent2, mutableComponent1;
/*  75 */     CommandSourceStack source = (CommandSourceStack)ctx.getSource();
/*  76 */     ServerPlayer player = EntityArgument.m_91474_(ctx, "player");
/*  77 */     Optional<IKnowledgeProvider> cap = player.getCapability(PECapabilities.KNOWLEDGE_CAPABILITY).resolve();
/*  78 */     if (cap.isEmpty()) {
/*  79 */       source.m_81352_((Component)PELang.COMMAND_PROVIDER_FAIL.translate(new Object[] { player.m_5446_() }));
/*  80 */       return 0;
/*     */     } 
/*  82 */     IKnowledgeProvider provider = cap.get();
/*  83 */     if (action == ActionType.GET) {
/*  84 */       source.m_288197_(() -> PELang.COMMAND_EMC_GET_SUCCESS.translate(new Object[] { player.m_5446_(), formatEMC(provider.getEmc()) }, ), true);
/*  85 */       return 1;
/*     */     } 
/*  87 */     String val = StringArgumentType.getString(ctx, "value");
/*  88 */     BigInteger value = null;
/*     */     
/*     */     try {
/*  91 */       value = new BigInteger(val);
/*  92 */       if (value.compareTo(BigInteger.ZERO) < 0)
/*  93 */         switch (action) { case ADD:
/*     */           case REMOVE:
/*  95 */             action = (action == ActionType.ADD) ? ActionType.REMOVE : ActionType.ADD;
/*  96 */             value = value.abs(); break;
/*     */           case SET: case TEST:
/*  98 */             value = null;
/*     */             break; }
/*     */          
/* 101 */     } catch (NumberFormatException numberFormatException) {}
/*     */     
/* 103 */     if (value == null) {
/* 104 */       source.m_81352_((Component)PELang.COMMAND_EMC_INVALID.translate(new Object[] { val }));
/* 105 */       return 0;
/*     */     } 
/*     */     
/* 108 */     BigInteger newEMC = provider.getEmc();
/* 109 */     switch (action) {
/*     */       case ADD:
/* 111 */         newEMC = newEMC.add(value);
/* 112 */         mutableComponent3 = PELang.COMMAND_EMC_ADD_SUCCESS.translate(new Object[] { formatEMC(value), player.m_5446_(), formatEMC(newEMC) });
/* 113 */         source.m_288197_(() -> message, true);
/*     */         break;
/*     */       case REMOVE:
/* 116 */         newEMC = newEMC.subtract(value);
/* 117 */         if (newEMC.compareTo(BigInteger.ZERO) < 0) {
/* 118 */           source.m_81352_((Component)PELang.COMMAND_EMC_NEGATIVE.translate(new Object[] { formatEMC(value), player.m_5446_() }));
/* 119 */           return 0;
/*     */         } 
/* 121 */         mutableComponent2 = PELang.COMMAND_EMC_REMOVE_SUCCESS.translate(new Object[] { formatEMC(value), player.m_5446_(), formatEMC(newEMC) });
/* 122 */         source.m_288197_(() -> message, true);
/*     */         break;
/*     */       case SET:
/* 125 */         newEMC = value;
/* 126 */         mutableComponent1 = PELang.COMMAND_EMC_SET_SUCCESS.translate(new Object[] { player.m_5446_(), formatEMC(value) });
/* 127 */         source.m_288197_(() -> message, true);
/*     */         break;
/*     */       case TEST:
/* 130 */         if (newEMC.compareTo(value) >= 0) {
/* 131 */           MutableComponent mutableComponent = PELang.COMMAND_EMC_TEST_SUCCESS.translateColored(ChatFormatting.GREEN, new Object[] { player.m_5446_(), formatEMC(value) });
/* 132 */           source.m_288197_(() -> message, true);
/* 133 */           return 1;
/*     */         } 
/*     */         
/* 136 */         source.m_81352_((Component)PELang.COMMAND_EMC_TEST_FAIL.translate(new Object[] { player.m_5446_(), formatEMC(value) }));
/* 137 */         return 0;
/*     */     } 
/*     */ 
/*     */     
/* 141 */     provider.setEmc(newEMC);
/* 142 */     provider.syncEmc(player);
/* 143 */     return 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\commands\EMCCMD.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */