/*    */ package moze_intel.projecte.gameObjs.container;
/*    */ import java.util.function.Predicate;
/*    */ import moze_intel.projecte.gameObjs.block_entities.CondenserMK2BlockEntity;
/*    */ import moze_intel.projecte.gameObjs.blocks.Condenser;
/*    */ import moze_intel.projecte.gameObjs.container.slots.SlotCondenserLock;
/*    */ import moze_intel.projecte.gameObjs.container.slots.SlotPredicates;
/*    */ import moze_intel.projecte.gameObjs.container.slots.ValidatedSlot;
/*    */ import moze_intel.projecte.gameObjs.registration.impl.BlockRegistryObject;
/*    */ import moze_intel.projecte.gameObjs.registries.PEBlocks;
/*    */ import moze_intel.projecte.gameObjs.registries.PEContainerTypes;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.inventory.Slot;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import net.minecraftforge.items.ItemStackHandler;
/*    */ 
/*    */ public class CondenserMK2Container extends CondenserContainer {
/*    */   public CondenserMK2Container(int windowId, Inventory playerInv, CondenserMK2BlockEntity condenser) {
/* 19 */     super(PEContainerTypes.CONDENSER_MK2_CONTAINER, windowId, playerInv, (CondenserBlockEntity)condenser);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void initSlots() {
/* 24 */     Objects.requireNonNull(this.blockEntity); m_38897_((Slot)new SlotCondenserLock(this.blockEntity::getLockInfo, 0, 12, 6));
/*    */     
/* 26 */     ItemStackHandler itemStackHandler1 = this.blockEntity.getInput();
/* 27 */     Predicate<ItemStack> validator = s -> (SlotPredicates.HAS_EMC.test(s) && !this.blockEntity.isStackEqualToLock(s));
/* 28 */     for (int i = 0; i < 7; i++) {
/* 29 */       for (int k = 0; k < 6; k++) {
/* 30 */         m_38897_((Slot)new ValidatedSlot((IItemHandler)itemStackHandler1, k + i * 6, 12 + k * 18, 26 + i * 18, validator));
/*    */       }
/*    */     } 
/*    */     
/* 34 */     ItemStackHandler itemStackHandler2 = this.blockEntity.getOutput();
/* 35 */     for (int j = 0; j < 7; j++) {
/* 36 */       for (int k = 0; k < 6; k++) {
/* 37 */         m_38897_((Slot)new ValidatedSlot((IItemHandler)itemStackHandler2, k + j * 6, 138 + k * 18, 26 + j * 18, SlotPredicates.ALWAYS_FALSE));
/*    */       }
/*    */     } 
/* 40 */     addPlayerInventory(48, 154);
/*    */   }
/*    */ 
/*    */   
/*    */   protected BlockRegistryObject<? extends Condenser, ?> getValidBlock() {
/* 45 */     return PEBlocks.CONDENSER_MK2;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\CondenserMK2Container.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */