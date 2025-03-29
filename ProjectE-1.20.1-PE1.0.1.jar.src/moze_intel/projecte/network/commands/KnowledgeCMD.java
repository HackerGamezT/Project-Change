/*     */ package moze_intel.projecte.network.commands;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.builder.ArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Predicate;
/*     */ import moze_intel.projecte.PEPermissions;
/*     */ import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
/*     */ import moze_intel.projecte.emc.nbt.NBTManager;
/*     */ import moze_intel.projecte.network.PacketHandler;
/*     */ import moze_intel.projecte.network.packets.IPEPacket;
/*     */ import moze_intel.projecte.network.packets.to_client.knowledge.KnowledgeClearPKT;
/*     */ import moze_intel.projecte.utils.EMCHelper;
/*     */ import moze_intel.projecte.utils.text.ILangEntry;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.commands.CommandBuildContext;
/*     */ import net.minecraft.commands.CommandSourceStack;
/*     */ import net.minecraft.commands.Commands;
/*     */ import net.minecraft.commands.arguments.EntityArgument;
/*     */ import net.minecraft.commands.arguments.item.ItemArgument;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class KnowledgeCMD {
/*     */   private enum ActionType {
/*  32 */     LEARN,
/*  33 */     UNLEARN,
/*  34 */     TEST;
/*     */   }
/*     */   
/*     */   public static ArgumentBuilder<CommandSourceStack, ?> register(CommandBuildContext context) {
/*  38 */     return ((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_("knowledge")
/*  39 */       .requires((Predicate)PEPermissions.COMMAND_KNOWLEDGE))
/*  40 */       .then(subCommandClear()))
/*  41 */       .then(((LiteralArgumentBuilder)Commands.m_82127_("learn")
/*  42 */         .requires((Predicate)PEPermissions.COMMAND_KNOWLEDGE_LEARN))
/*  43 */         .then(executeWithParameters(ActionType.LEARN, context))))
/*     */       
/*  45 */       .then(((LiteralArgumentBuilder)Commands.m_82127_("unlearn")
/*  46 */         .requires((Predicate)PEPermissions.COMMAND_KNOWLEDGE_UNLEARN))
/*  47 */         .then(executeWithParameters(ActionType.UNLEARN, context))))
/*     */       
/*  49 */       .then(((LiteralArgumentBuilder)Commands.m_82127_("test")
/*  50 */         .requires((Predicate)PEPermissions.COMMAND_KNOWLEDGE_TEST))
/*  51 */         .then(executeWithParameters(ActionType.TEST, context)));
/*     */   }
/*     */ 
/*     */   
/*     */   private static ArgumentBuilder<CommandSourceStack, ?> executeWithParameters(ActionType actionType, CommandBuildContext context) {
/*  56 */     return Commands.m_82129_("player", (ArgumentType)EntityArgument.m_91466_())
/*  57 */       .then(Commands.m_82129_("item", (ArgumentType)ItemArgument.m_235279_(context))
/*  58 */         .executes(ctx -> handle(ctx, actionType)));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static IKnowledgeProvider getProvider(ServerPlayer player) {
/*  63 */     Optional<IKnowledgeProvider> cap = player.getCapability(PECapabilities.KNOWLEDGE_CAPABILITY).resolve();
/*  64 */     return cap.orElse(null);
/*     */   }
/*     */   
/*     */   private static ArgumentBuilder<CommandSourceStack, ?> subCommandClear() {
/*  68 */     return ((LiteralArgumentBuilder)Commands.m_82127_("clear")
/*  69 */       .requires((Predicate)PEPermissions.COMMAND_KNOWLEDGE_CLEAR))
/*  70 */       .then(Commands.m_82129_("targets", (ArgumentType)EntityArgument.m_91470_())
/*  71 */         .executes(ctx -> {
/*     */             CommandSourceStack source = (CommandSourceStack)ctx.getSource();
/*     */             int successCount = 0;
/*     */             for (ServerPlayer player : EntityArgument.m_91477_(ctx, "targets")) {
/*     */               IKnowledgeProvider provider = getProvider(player);
/*     */               if (provider == null) {
/*     */                 source.m_81352_((Component)PELang.COMMAND_PROVIDER_FAIL.translate(new Object[] { player.m_5446_() }));
/*     */                 continue;
/*     */               } 
/*     */               if (provider.getKnowledge().isEmpty()) {
/*     */                 source.m_81352_((Component)PELang.COMMAND_KNOWLEDGE_CLEAR_FAIL.translate(new Object[] { player.m_5446_() }));
/*     */                 continue;
/*     */               } 
/*     */               provider.clearKnowledge();
/*     */               PacketHandler.sendTo((IPEPacket)new KnowledgeClearPKT(), player);
/*     */               source.m_288197_((), true);
/*     */               successCount++;
/*     */             } 
/*     */             return successCount;
/*     */           }));
/*     */   }
/*     */   
/*     */   private static int handle(CommandContext<CommandSourceStack> ctx, ActionType action) throws CommandSyntaxException {
/*  94 */     ServerPlayer player = EntityArgument.m_91474_(ctx, "player");
/*  95 */     CommandSourceStack source = (CommandSourceStack)ctx.getSource();
/*  96 */     IKnowledgeProvider provider = getProvider(player);
/*  97 */     if (provider == null) {
/*  98 */       source.m_81352_((Component)PELang.COMMAND_PROVIDER_FAIL.translate(new Object[] { player.m_5446_() }));
/*  99 */       return 0;
/*     */     } 
/* 101 */     ItemStack item = new ItemStack((ItemLike)ItemArgument.m_120963_(ctx, "item").m_120979_());
/*     */     
/* 103 */     if (!EMCHelper.doesItemHaveEmc(item)) {
/* 104 */       source.m_81352_((Component)PELang.COMMAND_KNOWLEDGE_INVALID.translate(new Object[] { item.m_41611_() }));
/* 105 */       return 0;
/*     */     } 
/*     */     
/* 108 */     switch (action) {
/*     */       case LEARN:
/* 110 */         if (provider.hasKnowledge(item)) {
/* 111 */           return failure(source, (ILangEntry)PELang.COMMAND_KNOWLEDGE_LEARN_FAIL, (Player)player, item);
/*     */         }
/* 113 */         provider.addKnowledge(item);
/* 114 */         source.m_288197_(() -> PELang.COMMAND_KNOWLEDGE_LEARN_SUCCESS.translateColored(ChatFormatting.GREEN, new Object[] { player.m_5446_(), item.m_41611_() }), true);
/*     */         break;
/*     */       case UNLEARN:
/* 117 */         if (!provider.hasKnowledge(item)) {
/* 118 */           return failure(source, (ILangEntry)PELang.COMMAND_KNOWLEDGE_UNLEARN_FAIL, (Player)player, item);
/*     */         }
/* 120 */         provider.removeKnowledge(item);
/* 121 */         source.m_288197_(() -> PELang.COMMAND_KNOWLEDGE_UNLEARN_SUCCESS.translateColored(ChatFormatting.GREEN, new Object[] { player.m_5446_(), item.m_41611_() }), true);
/*     */         break;
/*     */       case TEST:
/* 124 */         if (provider.hasKnowledge(item)) {
/* 125 */           source.m_288197_(() -> PELang.COMMAND_KNOWLEDGE_TEST_SUCCESS.translateColored(ChatFormatting.GREEN, new Object[] { player.m_5446_(), item.m_41611_() }), true);
/* 126 */           return 1;
/*     */         } 
/* 128 */         return failure(source, (ILangEntry)PELang.COMMAND_KNOWLEDGE_TEST_FAIL, (Player)player, item);
/*     */     } 
/*     */     
/* 131 */     provider.syncKnowledgeChange(player, NBTManager.getPersistentInfo(ItemInfo.fromStack(item)), (action == ActionType.LEARN));
/*     */     
/* 133 */     return 1;
/*     */   }
/*     */   
/*     */   private static int failure(CommandSourceStack source, ILangEntry failureMessage, Player player, ItemStack item) {
/* 137 */     source.m_81352_((Component)failureMessage.translate(new Object[] { player.m_5446_(), item.m_41611_() }));
/* 138 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\commands\KnowledgeCMD.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */