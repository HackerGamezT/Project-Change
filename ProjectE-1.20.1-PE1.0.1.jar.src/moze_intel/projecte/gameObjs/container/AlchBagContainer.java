/*    */ package moze_intel.projecte.gameObjs.container;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.container.slots.HotBarSlot;
/*    */ import moze_intel.projecte.gameObjs.container.slots.InventoryContainerSlot;
/*    */ import moze_intel.projecte.gameObjs.container.slots.MainInventorySlot;
/*    */ import moze_intel.projecte.gameObjs.registries.PEContainerTypes;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.world.Container;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.inventory.ClickType;
/*    */ import net.minecraft.world.inventory.Slot;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import net.minecraftforge.items.IItemHandlerModifiable;
/*    */ import net.minecraftforge.items.ItemStackHandler;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class AlchBagContainer extends PEHandContainer {
/*    */   public static AlchBagContainer fromNetwork(int windowId, Inventory playerInv, FriendlyByteBuf buf) {
/* 22 */     return new AlchBagContainer(windowId, playerInv, (InteractionHand)buf.m_130066_(InteractionHand.class), (IItemHandlerModifiable)new ItemStackHandler(104), buf.readByte(), buf.readBoolean());
/*    */   }
/*    */   private final boolean immutable;
/*    */   public AlchBagContainer(int windowId, Inventory playerInv, InteractionHand hand, IItemHandlerModifiable invBag, int selected, boolean immutable) {
/* 26 */     super(PEContainerTypes.ALCH_BAG_CONTAINER, windowId, playerInv, hand, selected);
/* 27 */     this.immutable = immutable;
/*    */     
/* 29 */     for (int i = 0; i < 8; i++) {
/* 30 */       for (int j = 0; j < 13; j++) {
/* 31 */         m_38897_((Slot)createContainerSlot(invBag, j + i * 13, 12 + j * 18, 5 + i * 18));
/*    */       }
/*    */     } 
/* 34 */     addPlayerInventory(48, 152);
/*    */   }
/*    */   
/*    */   private InventoryContainerSlot createContainerSlot(IItemHandlerModifiable inv, int index, int x, int y) {
/* 38 */     if (this.immutable) {
/* 39 */       return new InventoryContainerSlot((IItemHandler)inv, index, x, y)
/*    */         {
/*    */           public boolean m_8010_(@NotNull Player player) {
/* 42 */             return false;
/*    */           }
/*    */ 
/*    */           
/*    */           public boolean m_5857_(@NotNull ItemStack stack) {
/* 47 */             return false;
/*    */           }
/*    */         };
/*    */     }
/* 51 */     return new InventoryContainerSlot((IItemHandler)inv, index, x, y);
/*    */   }
/*    */ 
/*    */   
/*    */   protected MainInventorySlot createMainInventorySlot(@NotNull Inventory inv, int index, int x, int y) {
/* 56 */     if (this.immutable) {
/* 57 */       return new MainInventorySlot((Container)inv, index, x, y)
/*    */         {
/*    */           public boolean m_8010_(@NotNull Player player) {
/* 60 */             return false;
/*    */           }
/*    */ 
/*    */           
/*    */           public boolean m_5857_(@NotNull ItemStack stack) {
/* 65 */             return false;
/*    */           }
/*    */         };
/*    */     }
/* 69 */     return super.createMainInventorySlot(inv, index, x, y);
/*    */   }
/*    */ 
/*    */   
/*    */   protected HotBarSlot createHotBarSlot(@NotNull Inventory inv, int index, int x, int y) {
/* 74 */     if (this.immutable) {
/* 75 */       return new HotBarSlot((Container)inv, index, x, y)
/*    */         {
/*    */           public boolean m_8010_(@NotNull Player player) {
/* 78 */             return false;
/*    */           }
/*    */ 
/*    */           
/*    */           public boolean m_5857_(@NotNull ItemStack stack) {
/* 83 */             return false;
/*    */           }
/*    */         };
/*    */     }
/* 87 */     return super.createHotBarSlot(inv, index, x, y);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack m_7648_(@NotNull Player player, int slotIndex) {
/* 93 */     return this.immutable ? ItemStack.f_41583_ : super.m_7648_(player, slotIndex);
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_150399_(int slotId, int dragType, @NotNull ClickType clickType, @NotNull Player player) {
/* 98 */     if (!this.immutable)
/* 99 */       super.m_150399_(slotId, dragType, clickType, player); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\AlchBagContainer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */