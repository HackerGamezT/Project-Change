/*    */ package moze_intel.projecte.gameObjs.container;
/*    */ import moze_intel.projecte.gameObjs.block_entities.RelayMK1BlockEntity;
/*    */ import moze_intel.projecte.gameObjs.block_entities.RelayMK2BlockEntity;
/*    */ import moze_intel.projecte.gameObjs.blocks.Relay;
/*    */ import moze_intel.projecte.gameObjs.container.slots.SlotPredicates;
/*    */ import moze_intel.projecte.gameObjs.container.slots.ValidatedSlot;
/*    */ import moze_intel.projecte.gameObjs.registration.impl.BlockRegistryObject;
/*    */ import moze_intel.projecte.gameObjs.registries.PEBlocks;
/*    */ import moze_intel.projecte.gameObjs.registries.PEContainerTypes;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.inventory.Slot;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ 
/*    */ public class RelayMK2Container extends RelayMK1Container {
/*    */   public RelayMK2Container(int windowId, Inventory playerInv, RelayMK2BlockEntity relay) {
/* 16 */     super(PEContainerTypes.RELAY_MK2_CONTAINER, windowId, playerInv, (RelayMK1BlockEntity)relay);
/*    */   }
/*    */ 
/*    */   
/*    */   void initSlots() {
/* 21 */     IItemHandler input = this.relay.getInput();
/* 22 */     IItemHandler output = this.relay.getOutput();
/*    */     
/* 24 */     m_38897_((Slot)new ValidatedSlot(output, 0, 144, 44, SlotPredicates.EMC_HOLDER));
/*    */     
/* 26 */     m_38897_((Slot)new ValidatedSlot(input, 0, 84, 44, SlotPredicates.RELAY_INV));
/* 27 */     int counter = 1;
/*    */     
/* 29 */     for (int i = 2; i >= 0; i--) {
/* 30 */       for (int j = 3; j >= 0; j--) {
/* 31 */         m_38897_((Slot)new ValidatedSlot(input, counter++, 26 + i * 18, 18 + j * 18, SlotPredicates.RELAY_INV));
/*    */       }
/*    */     } 
/* 34 */     addPlayerInventory(16, 101);
/*    */   }
/*    */ 
/*    */   
/*    */   protected BlockRegistryObject<Relay, ?> getValidBlock() {
/* 39 */     return PEBlocks.RELAY_MK2;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\RelayMK2Container.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */