/*     */ package moze_intel.projecte;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.commands.CommandSource;
/*     */ import net.minecraft.commands.CommandSourceStack;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraftforge.server.permission.PermissionAPI;
/*     */ import net.minecraftforge.server.permission.events.PermissionGatherEvent;
/*     */ import net.minecraftforge.server.permission.nodes.PermissionDynamicContext;
/*     */ import net.minecraftforge.server.permission.nodes.PermissionDynamicContextKey;
/*     */ import net.minecraftforge.server.permission.nodes.PermissionNode;
/*     */ import net.minecraftforge.server.permission.nodes.PermissionType;
/*     */ import net.minecraftforge.server.permission.nodes.PermissionTypes;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ public class PEPermissions
/*     */ {
/*  22 */   private static final List<PermissionNode<?>> NODES_TO_REGISTER = new ArrayList<>(); private static final PermissionNode.PermissionResolver<Boolean> PLAYER_IS_OP; static {
/*  23 */     PLAYER_IS_OP = ((player, uuid, context) -> Boolean.valueOf((player != null && player.m_20310_(2))));
/*     */   }
/*     */   private static final PermissionNode.PermissionResolver<Boolean> ALWAYS_TRUE = (player, uuid, context) -> Boolean.valueOf(true); public static final CommandPermissionNode COMMAND;
/*     */   static {
/*  27 */     COMMAND = new CommandPermissionNode(node("command", PermissionTypes.BOOLEAN, (player, uuid, contexts) -> Boolean.valueOf(
/*  28 */             (player != null && player.m_20310_(0))), (PermissionDynamicContextKey<Boolean>[])new PermissionDynamicContextKey[0]), 0);
/*     */   }
/*  30 */   public static final CommandPermissionNode COMMAND_REMOVE_EMC = nodeOpCommand("remove_emc");
/*  31 */   public static final CommandPermissionNode COMMAND_RESET_EMC = nodeOpCommand("reset_emc");
/*  32 */   public static final CommandPermissionNode COMMAND_SET_EMC = nodeOpCommand("set_emc");
/*  33 */   public static final CommandPermissionNode COMMAND_SHOW_BAG = nodeOpCommand("show_bag");
/*  34 */   public static final CommandPermissionNode COMMAND_EMC = nodeOpCommand("emc");
/*  35 */   public static final CommandPermissionNode COMMAND_EMC_ADD = nodeSubCommand(COMMAND_EMC, "add");
/*  36 */   public static final CommandPermissionNode COMMAND_EMC_REMOVE = nodeSubCommand(COMMAND_EMC, "remove");
/*  37 */   public static final CommandPermissionNode COMMAND_EMC_SET = nodeSubCommand(COMMAND_EMC, "set");
/*  38 */   public static final CommandPermissionNode COMMAND_EMC_TEST = nodeSubCommand(COMMAND_EMC, "test");
/*  39 */   public static final CommandPermissionNode COMMAND_EMC_GET = nodeSubCommand(COMMAND_EMC, "get");
/*  40 */   public static final CommandPermissionNode COMMAND_KNOWLEDGE = nodeOpCommand("knowledge");
/*  41 */   public static final CommandPermissionNode COMMAND_KNOWLEDGE_CLEAR = nodeSubCommand(COMMAND_KNOWLEDGE, "clear");
/*  42 */   public static final CommandPermissionNode COMMAND_KNOWLEDGE_LEARN = nodeSubCommand(COMMAND_KNOWLEDGE, "learn");
/*  43 */   public static final CommandPermissionNode COMMAND_KNOWLEDGE_UNLEARN = nodeSubCommand(COMMAND_KNOWLEDGE, "unlearn");
/*  44 */   public static final CommandPermissionNode COMMAND_KNOWLEDGE_TEST = nodeSubCommand(COMMAND_KNOWLEDGE, "test");
/*     */   
/*     */   private static CommandPermissionNode nodeOpCommand(String nodeName) {
/*  47 */     PermissionNode<Boolean> node = node("command." + nodeName, PermissionTypes.BOOLEAN, PLAYER_IS_OP, (PermissionDynamicContextKey<Boolean>[])new PermissionDynamicContextKey[0]);
/*  48 */     return new CommandPermissionNode(node, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static CommandPermissionNode nodeSubCommand(CommandPermissionNode parent, String nodeName) {
/*  54 */     PermissionNode<Boolean> node = subNode(parent.node, nodeName, ALWAYS_TRUE);
/*  55 */     return new CommandPermissionNode(node, parent.fallbackLevel);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static <T> PermissionNode<T> subNode(PermissionNode<T> parent, String nodeName) {
/*  62 */     return subNode(parent, nodeName, (player, uuid, context) -> getPermission(player, uuid, parent, (PermissionDynamicContext<?>[])context));
/*     */   }
/*     */   
/*     */   private static <T> PermissionNode<T> subNode(PermissionNode<T> parent, String nodeName, ResultTransformer<T> defaultRestrictionIncrease) {
/*  66 */     return subNode(parent, nodeName, (player, uuid, context) -> {
/*     */           T result = getPermission(player, uuid, parent, (PermissionDynamicContext<?>[])context);
/*     */           return (PermissionNode.PermissionResolver)defaultRestrictionIncrease.transform(player, uuid, result, (PermissionDynamicContext<?>[])context);
/*     */         });
/*     */   }
/*     */   
/*     */   private static <T> PermissionNode<T> subNode(PermissionNode<T> parent, String nodeName, PermissionNode.PermissionResolver<T> defaultResolver) {
/*  73 */     String fullParentName = parent.getNodeName();
/*     */     
/*  75 */     String parentName = fullParentName.substring(fullParentName.indexOf('.') + 1);
/*  76 */     return node(parentName + "." + parentName, parent.getType(), defaultResolver, (PermissionDynamicContextKey<T>[])new PermissionDynamicContextKey[0]);
/*     */   }
/*     */   
/*     */   @SafeVarargs
/*     */   private static <T> PermissionNode<T> node(String nodeName, PermissionType<T> type, PermissionNode.PermissionResolver<T> defaultResolver, PermissionDynamicContextKey<T>... dynamics) {
/*  81 */     PermissionNode<T> node = new PermissionNode("projecte", nodeName, type, defaultResolver, (PermissionDynamicContextKey[])dynamics);
/*  82 */     NODES_TO_REGISTER.add(node);
/*  83 */     return node;
/*     */   }
/*     */   
/*     */   public static void registerPermissionNodes(PermissionGatherEvent.Nodes event) {
/*  87 */     event.addNodes(NODES_TO_REGISTER);
/*     */   }
/*     */   
/*     */   private static <T> T getPermission(@Nullable ServerPlayer player, UUID playerUUID, PermissionNode<T> node, PermissionDynamicContext<?>... context) {
/*  91 */     if (player == null) {
/*  92 */       return (T)PermissionAPI.getOfflinePermission(playerUUID, node, (PermissionDynamicContext[])context);
/*     */     }
/*  94 */     return (T)PermissionAPI.getPermission(player, node, (PermissionDynamicContext[])context);
/*     */   }
/*     */   public static final class CommandPermissionNode extends Record implements Predicate<CommandSourceStack> { private final PermissionNode<Boolean> node; private final int fallbackLevel;
/*  97 */     public CommandPermissionNode(PermissionNode<Boolean> node, int fallbackLevel) { this.node = node; this.fallbackLevel = fallbackLevel; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lmoze_intel/projecte/PEPermissions$CommandPermissionNode;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #97	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*  97 */       //   0	7	0	this	Lmoze_intel/projecte/PEPermissions$CommandPermissionNode; } public PermissionNode<Boolean> node() { return this.node; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/PEPermissions$CommandPermissionNode;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #97	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lmoze_intel/projecte/PEPermissions$CommandPermissionNode; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lmoze_intel/projecte/PEPermissions$CommandPermissionNode;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #97	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lmoze_intel/projecte/PEPermissions$CommandPermissionNode;
/*  97 */       //   0	8	1	o	Ljava/lang/Object; } public int fallbackLevel() { return this.fallbackLevel; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean test(CommandSourceStack source) {
/* 103 */       if (!source.m_6761_(this.fallbackLevel)) { CommandSource commandSource = source.f_81288_; if (commandSource instanceof ServerPlayer) { ServerPlayer player = (ServerPlayer)commandSource; if (((Boolean)PermissionAPI.getPermission(player, this.node, new PermissionDynamicContext[0])).booleanValue()); }  return false; }
/*     */     
/*     */     } }
/*     */ 
/*     */   
/*     */   @FunctionalInterface
/*     */   private static interface ResultTransformer<T> {
/*     */     T transform(@Nullable ServerPlayer param1ServerPlayer, UUID param1UUID, T param1T, PermissionDynamicContext<?>... param1VarArgs);
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\PEPermissions.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */