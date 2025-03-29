/*     */ package moze_intel.projecte.network.commands;
/*     */ 
/*     */ import java.util.function.BooleanSupplier;
/*     */ import moze_intel.projecte.gameObjs.container.AlchBagContainer;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.MenuProvider;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*     */ import net.minecraftforge.items.IItemHandlerModifiable;
/*     */ import org.jetbrains.annotations.NotNull;
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
/*     */ class null
/*     */   implements MenuProvider
/*     */ {
/*     */   @NotNull
/*     */   public Component m_5446_() {
/* 107 */     return name;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractContainerMenu m_7208_(int windowId, @NotNull Inventory playerInv, @NotNull Player player) {
/* 113 */     return (AbstractContainerMenu)new AlchBagContainer(windowId, sender.m_150109_(), InteractionHand.OFF_HAND, inv, 0, immutable)
/*     */       {
/*     */         public boolean m_6875_(@NotNull Player player) {
/* 116 */           return canInteractWith.getAsBoolean();
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\commands\ShowBagCMD$1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */