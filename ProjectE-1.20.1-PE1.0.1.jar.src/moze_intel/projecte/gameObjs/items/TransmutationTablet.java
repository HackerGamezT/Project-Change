/*    */ package moze_intel.projecte.gameObjs.items;
/*    */ import moze_intel.projecte.gameObjs.container.TransmutationContainer;
/*    */ import moze_intel.projecte.utils.text.PELang;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.InteractionResultHolder;
/*    */ import net.minecraft.world.MenuProvider;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraftforge.network.NetworkHooks;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class TransmutationTablet extends ItemPE {
/*    */   public TransmutationTablet(Item.Properties props) {
/* 21 */     super(props);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public InteractionResultHolder<ItemStack> m_7203_(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
/* 27 */     if (!level.f_46443_) {
/* 28 */       NetworkHooks.openScreen((ServerPlayer)player, new ContainerProvider(hand), buf -> {
/*    */             buf.writeBoolean(true);
/*    */             buf.m_130068_((Enum)hand);
/*    */             buf.writeByte((player.m_150109_()).f_35977_);
/*    */           });
/*    */     }
/* 34 */     return InteractionResultHolder.m_19090_(player.m_21120_(hand));
/*    */   }
/*    */   private static final class ContainerProvider extends Record implements MenuProvider { private final InteractionHand hand;
/* 37 */     private ContainerProvider(InteractionHand hand) { this.hand = hand; } public final String toString() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> toString : (Lmoze_intel/projecte/gameObjs/items/TransmutationTablet$ContainerProvider;)Ljava/lang/String;
/*    */       //   6: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #37	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/* 37 */       //   0	7	0	this	Lmoze_intel/projecte/gameObjs/items/TransmutationTablet$ContainerProvider; } public InteractionHand hand() { return this.hand; }
/*    */     public final int hashCode() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/gameObjs/items/TransmutationTablet$ContainerProvider;)I
/*    */       //   6: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #37	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lmoze_intel/projecte/gameObjs/items/TransmutationTablet$ContainerProvider; } public final boolean equals(Object o) {
/*    */       // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: <illegal opcode> equals : (Lmoze_intel/projecte/gameObjs/items/TransmutationTablet$ContainerProvider;Ljava/lang/Object;)Z
/*    */       //   7: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #37	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	8	0	this	Lmoze_intel/projecte/gameObjs/items/TransmutationTablet$ContainerProvider;
/*    */       //   0	8	1	o	Ljava/lang/Object;
/*    */     } public AbstractContainerMenu m_7208_(int windowId, @NotNull Inventory playerInventory, @NotNull Player player) {
/* 41 */       return (AbstractContainerMenu)new TransmutationContainer(windowId, playerInventory, this.hand, playerInventory.f_35977_);
/*    */     }
/*    */ 
/*    */     
/*    */     @NotNull
/*    */     public Component m_5446_() {
/* 47 */       return (Component)PELang.TRANSMUTATION_TRANSMUTE.translate(new Object[0]);
/*    */     } }
/*    */ 
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\TransmutationTablet.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */