/*    */ package moze_intel.projecte.gameObjs.container;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.container.inventory.EternalDensityInventory;
/*    */ import moze_intel.projecte.gameObjs.container.slots.SlotGhost;
/*    */ import moze_intel.projecte.gameObjs.container.slots.SlotPredicates;
/*    */ import moze_intel.projecte.gameObjs.registries.PEContainerTypes;
/*    */ import moze_intel.projecte.utils.ItemHelper;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.inventory.ClickType;
/*    */ import net.minecraft.world.inventory.Slot;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import net.minecraftforge.items.ItemHandlerHelper;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class EternalDensityContainer extends PEHandContainer {
/*    */   public final EternalDensityInventory inventory;
/*    */   
/*    */   public static EternalDensityContainer fromNetwork(int windowId, Inventory playerInv, FriendlyByteBuf data) {
/* 23 */     return new EternalDensityContainer(windowId, playerInv, (InteractionHand)data.m_130066_(InteractionHand.class), data.readByte(), null);
/*    */   }
/*    */   
/*    */   public EternalDensityContainer(int windowId, Inventory playerInv, InteractionHand hand, int selected, EternalDensityInventory gemInv) {
/* 27 */     super(PEContainerTypes.ETERNAL_DENSITY_CONTAINER, windowId, playerInv, hand, selected);
/* 28 */     this.inventory = (gemInv == null) ? new EternalDensityInventory(this.stack) : gemInv;
/* 29 */     for (int i = 0; i < 3; i++) {
/* 30 */       for (int j = 0; j < 3; j++) {
/* 31 */         m_38897_((Slot)new SlotGhost((IItemHandler)this.inventory, j + i * 3, 62 + j * 18, 26 + i * 18, SlotPredicates.HAS_EMC));
/*    */       }
/*    */     } 
/* 34 */     addPlayerInventory(8, 93);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack m_7648_(@NotNull Player player, int slotIndex) {
/* 40 */     if (slotIndex > 8) {
/* 41 */       Slot slot = tryGetSlot(slotIndex);
/* 42 */       if (slot != null) {
/* 43 */         ItemHandlerHelper.insertItem((IItemHandler)this.inventory, ItemHelper.getNormalizedStack(slot.m_7993_()), false);
/*    */       }
/*    */     } 
/* 46 */     return ItemStack.f_41583_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void clickPostValidate(int slotIndex, int button, @NotNull ClickType flag, @NotNull Player player) {
/* 51 */     Slot slot = tryGetSlot(slotIndex);
/* 52 */     if (slot instanceof SlotGhost && !slot.m_7993_().m_41619_()) {
/* 53 */       slot.m_5852_(ItemStack.f_41583_);
/*    */     } else {
/* 55 */       super.clickPostValidate(slotIndex, button, flag, player);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_5622_(@NotNull Slot slot) {
/* 61 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\EternalDensityContainer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */