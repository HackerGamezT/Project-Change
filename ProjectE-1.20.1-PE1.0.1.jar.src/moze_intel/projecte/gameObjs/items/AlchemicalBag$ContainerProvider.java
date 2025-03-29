/*    */ package moze_intel.projecte.gameObjs.items;
/*    */ 
/*    */ import moze_intel.projecte.api.capabilities.IAlchBagProvider;
/*    */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*    */ import moze_intel.projecte.gameObjs.container.AlchBagContainer;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.MenuProvider;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.common.util.NonNullSupplier;
/*    */ import net.minecraftforge.items.IItemHandlerModifiable;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class ContainerProvider
/*    */   implements MenuProvider
/*    */ {
/*    */   private final ItemStack stack;
/*    */   private final InteractionHand hand;
/*    */   
/*    */   private ContainerProvider(ItemStack stack, InteractionHand hand) {
/* 82 */     this.stack = stack;
/* 83 */     this.hand = hand;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public AbstractContainerMenu m_7208_(int windowId, @NotNull Inventory playerInventory, @NotNull Player player) {
/* 91 */     IItemHandlerModifiable inv = (IItemHandlerModifiable)((IAlchBagProvider)player.getCapability(PECapabilities.ALCH_BAG_CAPABILITY).orElseThrow(NullPointerException::new)).getBag(AlchemicalBag.this.color);
/* 92 */     return (AbstractContainerMenu)new AlchBagContainer(windowId, playerInventory, this.hand, inv, playerInventory.f_35977_, false);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Component m_5446_() {
/* 98 */     return this.stack.m_41786_();
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\AlchemicalBag$ContainerProvider.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */