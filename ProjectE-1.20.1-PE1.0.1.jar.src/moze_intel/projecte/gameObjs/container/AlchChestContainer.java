/*    */ package moze_intel.projecte.gameObjs.container;
/*    */ import moze_intel.projecte.gameObjs.block_entities.AlchBlockEntityChest;
/*    */ import moze_intel.projecte.gameObjs.container.slots.InventoryContainerSlot;
/*    */ import moze_intel.projecte.gameObjs.registries.PEBlocks;
/*    */ import moze_intel.projecte.gameObjs.registries.PEContainerTypes;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.inventory.Slot;
/*    */ import net.minecraft.world.level.block.entity.BlockEntity;
/*    */ import net.minecraftforge.common.capabilities.ForgeCapabilities;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class AlchChestContainer extends EmcChestBlockEntityContainer<AlchBlockEntityChest> {
/*    */   public AlchChestContainer(int windowId, Inventory playerInv, AlchBlockEntityChest chest) {
/* 16 */     super(PEContainerTypes.ALCH_CHEST_CONTAINER, windowId, playerInv, chest);
/* 17 */     IItemHandler inv = (IItemHandler)this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).orElseThrow(NullPointerException::new);
/*    */     
/* 19 */     for (int i = 0; i < 8; i++) {
/* 20 */       for (int j = 0; j < 13; j++) {
/* 21 */         m_38897_((Slot)new InventoryContainerSlot(inv, j + i * 13, 12 + j * 18, 5 + i * 18));
/*    */       }
/*    */     } 
/* 24 */     addPlayerInventory(48, 152);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_6875_(@NotNull Player player) {
/* 29 */     return stillValid(player, (BlockEntity)this.blockEntity, PEBlocks.ALCHEMICAL_CHEST);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\AlchChestContainer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */