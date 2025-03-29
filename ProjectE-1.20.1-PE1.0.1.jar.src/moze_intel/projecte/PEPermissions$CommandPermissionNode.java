/*     */ package moze_intel.projecte;
/*     */ 
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.commands.CommandSource;
/*     */ import net.minecraft.commands.CommandSourceStack;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraftforge.server.permission.PermissionAPI;
/*     */ import net.minecraftforge.server.permission.nodes.PermissionNode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class CommandPermissionNode
/*     */   extends Record
/*     */   implements Predicate<CommandSourceStack>
/*     */ {
/*     */   private final PermissionNode<Boolean> node;
/*     */   private final int fallbackLevel;
/*     */   
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lmoze_intel/projecte/PEPermissions$CommandPermissionNode;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #97	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lmoze_intel/projecte/PEPermissions$CommandPermissionNode;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/PEPermissions$CommandPermissionNode;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #97	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lmoze_intel/projecte/PEPermissions$CommandPermissionNode;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lmoze_intel/projecte/PEPermissions$CommandPermissionNode;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #97	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lmoze_intel/projecte/PEPermissions$CommandPermissionNode;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public CommandPermissionNode(PermissionNode<Boolean> node, int fallbackLevel) {
/*  97 */     this.node = node; this.fallbackLevel = fallbackLevel; } public PermissionNode<Boolean> node() { return this.node; } public int fallbackLevel() { return this.fallbackLevel; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean test(CommandSourceStack source) {
/* 103 */     if (!source.m_6761_(this.fallbackLevel)) { CommandSource commandSource = source.f_81288_; if (commandSource instanceof ServerPlayer) { ServerPlayer player = (ServerPlayer)commandSource; if (((Boolean)PermissionAPI.getPermission(player, this.node, new net.minecraftforge.server.permission.nodes.PermissionDynamicContext[0])).booleanValue()); }  return false; }
/*     */   
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\PEPermissions$CommandPermissionNode.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */