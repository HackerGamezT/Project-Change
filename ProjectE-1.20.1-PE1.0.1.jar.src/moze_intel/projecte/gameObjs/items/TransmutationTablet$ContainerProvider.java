/*    */ package moze_intel.projecte.gameObjs.items;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.container.TransmutationContainer;
/*    */ import moze_intel.projecte.utils.text.PELang;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.MenuProvider;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class ContainerProvider
/*    */   extends Record
/*    */   implements MenuProvider
/*    */ {
/*    */   private final InteractionHand hand;
/*    */   
/*    */   private ContainerProvider(InteractionHand hand) {
/* 37 */     this.hand = hand; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lmoze_intel/projecte/gameObjs/items/TransmutationTablet$ContainerProvider;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #37	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/* 37 */     //   0	7	0	this	Lmoze_intel/projecte/gameObjs/items/TransmutationTablet$ContainerProvider; } public InteractionHand hand() { return this.hand; }
/*    */   public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/gameObjs/items/TransmutationTablet$ContainerProvider;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #37	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lmoze_intel/projecte/gameObjs/items/TransmutationTablet$ContainerProvider; } public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lmoze_intel/projecte/gameObjs/items/TransmutationTablet$ContainerProvider;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #37	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lmoze_intel/projecte/gameObjs/items/TransmutationTablet$ContainerProvider;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   } public AbstractContainerMenu m_7208_(int windowId, @NotNull Inventory playerInventory, @NotNull Player player) {
/* 41 */     return (AbstractContainerMenu)new TransmutationContainer(windowId, playerInventory, this.hand, playerInventory.f_35977_);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Component m_5446_() {
/* 47 */     return (Component)PELang.TRANSMUTATION_TRANSMUTE.translate(new Object[0]);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\TransmutationTablet$ContainerProvider.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */