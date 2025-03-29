/*    */ package moze_intel.projecte.gameObjs.container;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.container.slots.HotBarSlot;
/*    */ import moze_intel.projecte.gameObjs.registration.impl.ContainerTypeRegistryObject;
/*    */ import net.minecraft.world.Container;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.inventory.ClickType;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PEHandContainer extends PEContainer {
/*    */   public final InteractionHand hand;
/*    */   private final int selected;
/*    */   protected final ItemStack stack;
/*    */   
/*    */   protected PEHandContainer(ContainerTypeRegistryObject<? extends PEHandContainer> typeRO, int windowId, Inventory playerInv, InteractionHand hand, int selected) {
/* 19 */     super((ContainerTypeRegistryObject)typeRO, windowId, playerInv);
/* 20 */     this.hand = hand;
/* 21 */     this.selected = selected;
/* 22 */     if (this.hand == null) {
/* 23 */       this.stack = ItemStack.f_41583_;
/*    */     } else {
/* 25 */       this.stack = (this.hand == InteractionHand.OFF_HAND) ? this.playerInv.f_35978_.m_21206_() : this.playerInv.m_8020_(selected);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected HotBarSlot createHotBarSlot(@NotNull Inventory inv, int index, int x, int y) {
/* 32 */     if (this.hand == InteractionHand.MAIN_HAND && index == this.selected) {
/* 33 */       return new HotBarSlot((Container)inv, index, x, y)
/*    */         {
/*    */           public boolean m_8010_(@NotNull Player player) {
/* 36 */             return false;
/*    */           }
/*    */         };
/*    */     }
/* 40 */     return super.createHotBarSlot(inv, index, x, y);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_6875_(@NotNull Player player) {
/* 45 */     return (this.hand == null || (!this.stack.m_41619_() && player.m_21120_(this.hand).m_150930_(this.stack.m_41720_())));
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_150399_(int slotId, int dragType, @NotNull ClickType clickType, @NotNull Player player) {
/* 50 */     if (clickType == ClickType.SWAP) {
/* 51 */       if (this.hand == InteractionHand.OFF_HAND && dragType == 40) {
/*    */         return;
/*    */       }
/* 54 */       if (this.hand == InteractionHand.MAIN_HAND && dragType >= 0 && dragType < Inventory.m_36059_())
/*    */       {
/* 56 */         if (!((HotBarSlot)this.hotBarSlots.get(dragType)).m_8010_(player)) {
/*    */           return;
/*    */         }
/*    */       }
/*    */     } 
/* 61 */     clickPostValidate(slotId, dragType, clickType, player);
/*    */   }
/*    */   
/*    */   public void clickPostValidate(int slotId, int dragType, @NotNull ClickType clickType, @NotNull Player player) {
/* 65 */     super.m_150399_(slotId, dragType, clickType, player);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\PEHandContainer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */