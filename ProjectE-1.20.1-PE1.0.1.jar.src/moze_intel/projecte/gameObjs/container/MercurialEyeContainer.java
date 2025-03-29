/*    */ package moze_intel.projecte.gameObjs.container;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.container.slots.SlotGhost;
/*    */ import moze_intel.projecte.gameObjs.container.slots.SlotPredicates;
/*    */ import moze_intel.projecte.gameObjs.container.slots.ValidatedSlot;
/*    */ import moze_intel.projecte.gameObjs.registries.PEContainerTypes;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.inventory.ClickType;
/*    */ import net.minecraft.world.inventory.Slot;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.common.capabilities.ForgeCapabilities;
/*    */ import net.minecraftforge.common.util.NonNullSupplier;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class MercurialEyeContainer extends PEHandContainer {
/*    */   public static MercurialEyeContainer fromNetwork(int windowId, Inventory playerInv, FriendlyByteBuf buf) {
/* 21 */     return new MercurialEyeContainer(windowId, playerInv, (InteractionHand)buf.m_130066_(InteractionHand.class), buf.readByte());
/*    */   }
/*    */   
/*    */   private final SlotGhost mercurialTarget;
/*    */   
/*    */   public MercurialEyeContainer(int windowId, Inventory playerInv, InteractionHand hand, int selected) {
/* 27 */     super(PEContainerTypes.MERCURIAL_EYE_CONTAINER, windowId, playerInv, hand, selected);
/* 28 */     IItemHandler handler = (IItemHandler)this.stack.getCapability(ForgeCapabilities.ITEM_HANDLER).orElseThrow(NullPointerException::new);
/*    */     
/* 30 */     m_38897_((Slot)new ValidatedSlot(handler, 0, 50, 26, SlotPredicates.EMC_HOLDER));
/*    */     
/* 32 */     m_38897_((Slot)(this.mercurialTarget = new SlotGhost(handler, 1, 104, 26, SlotPredicates.MERCURIAL_TARGET)));
/* 33 */     addPlayerInventory(6, 56);
/*    */   }
/*    */ 
/*    */   
/*    */   public void clickPostValidate(int slotId, int button, @NotNull ClickType flag, @NotNull Player player) {
/* 38 */     Slot slot = tryGetSlot(slotId);
/* 39 */     if (slot instanceof SlotGhost && !slot.m_7993_().m_41619_()) {
/* 40 */       slot.m_5852_(ItemStack.f_41583_);
/*    */     } else {
/* 42 */       super.clickPostValidate(slotId, button, flag, player);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack m_7648_(@NotNull Player player, int slotID) {
/* 50 */     if (slotID > 1 && !this.mercurialTarget.m_6657_()) {
/* 51 */       Slot currentSlot = (Slot)this.f_38839_.get(slotID);
/* 52 */       if (currentSlot == null || !currentSlot.m_6657_()) {
/* 53 */         return ItemStack.f_41583_;
/*    */       }
/* 55 */       ItemStack slotStack = currentSlot.m_7993_();
/* 56 */       if (!slotStack.m_41619_() && this.mercurialTarget.isValid(slotStack)) {
/* 57 */         this.mercurialTarget.m_5852_(slotStack);
/*    */         
/* 59 */         return ItemStack.f_41583_;
/*    */       } 
/*    */     } 
/* 62 */     return super.m_7648_(player, slotID);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\MercurialEyeContainer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */