/*    */ package moze_intel.projecte.gameObjs.container;
/*    */ import moze_intel.projecte.gameObjs.block_entities.CollectorMK1BlockEntity;
/*    */ import moze_intel.projecte.gameObjs.block_entities.CollectorMK2BlockEntity;
/*    */ import moze_intel.projecte.gameObjs.blocks.Collector;
/*    */ import moze_intel.projecte.gameObjs.container.slots.SlotGhost;
/*    */ import moze_intel.projecte.gameObjs.container.slots.SlotPredicates;
/*    */ import moze_intel.projecte.gameObjs.container.slots.ValidatedSlot;
/*    */ import moze_intel.projecte.gameObjs.registration.impl.BlockRegistryObject;
/*    */ import moze_intel.projecte.gameObjs.registries.PEBlocks;
/*    */ import moze_intel.projecte.gameObjs.registries.PEContainerTypes;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.inventory.Slot;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ 
/*    */ public class CollectorMK2Container extends CollectorMK1Container {
/*    */   public CollectorMK2Container(int windowId, Inventory playerInv, CollectorMK2BlockEntity collector) {
/* 17 */     super(PEContainerTypes.COLLECTOR_MK2_CONTAINER, windowId, playerInv, (CollectorMK1BlockEntity)collector);
/*    */   }
/*    */ 
/*    */   
/*    */   void initSlots() {
/* 22 */     IItemHandler aux = this.collector.getAux();
/* 23 */     IItemHandler main = this.collector.getInput();
/*    */ 
/*    */     
/* 26 */     m_38897_((Slot)new ValidatedSlot(aux, 0, 140, 58, SlotPredicates.COLLECTOR_INV));
/* 27 */     int counter = 0;
/*    */     
/* 29 */     for (int i = 2; i >= 0; i--) {
/* 30 */       for (int j = 3; j >= 0; j--) {
/* 31 */         m_38897_((Slot)new ValidatedSlot(main, counter++, 18 + i * 18, 8 + j * 18, SlotPredicates.COLLECTOR_INV));
/*    */       }
/*    */     } 
/*    */     
/* 35 */     m_38897_((Slot)new ValidatedSlot(aux, 1, 140, 13, SlotPredicates.ALWAYS_FALSE));
/*    */     
/* 37 */     m_38897_((Slot)new SlotGhost(aux, 2, 169, 36, SlotPredicates.COLLECTOR_LOCK));
/* 38 */     addPlayerInventory(20, 84);
/*    */   }
/*    */ 
/*    */   
/*    */   protected BlockRegistryObject<Collector, ?> getValidBlock() {
/* 43 */     return PEBlocks.COLLECTOR_MK2;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\CollectorMK2Container.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */