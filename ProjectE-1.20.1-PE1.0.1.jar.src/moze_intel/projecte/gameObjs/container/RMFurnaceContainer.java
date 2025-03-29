/*    */ package moze_intel.projecte.gameObjs.container;
/*    */ import java.util.function.Predicate;
/*    */ import moze_intel.projecte.gameObjs.block_entities.DMFurnaceBlockEntity;
/*    */ import moze_intel.projecte.gameObjs.block_entities.RMFurnaceBlockEntity;
/*    */ import moze_intel.projecte.gameObjs.blocks.MatterFurnace;
/*    */ import moze_intel.projecte.gameObjs.container.slots.MatterFurnaceOutputSlot;
/*    */ import moze_intel.projecte.gameObjs.container.slots.SlotPredicates;
/*    */ import moze_intel.projecte.gameObjs.container.slots.ValidatedSlot;
/*    */ import moze_intel.projecte.gameObjs.registration.impl.BlockRegistryObject;
/*    */ import moze_intel.projecte.gameObjs.registries.PEBlocks;
/*    */ import moze_intel.projecte.gameObjs.registries.PEContainerTypes;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.inventory.Slot;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ 
/*    */ public class RMFurnaceContainer extends DMFurnaceContainer {
/*    */   public RMFurnaceContainer(int windowId, Inventory playerInv, RMFurnaceBlockEntity furnace) {
/* 19 */     super(PEContainerTypes.RM_FURNACE_CONTAINER, windowId, playerInv, (DMFurnaceBlockEntity)furnace);
/*    */   }
/*    */ 
/*    */   
/*    */   void initSlots() {
/* 24 */     IItemHandler fuel = this.furnace.getFuel();
/* 25 */     IItemHandler input = this.furnace.getInput();
/* 26 */     IItemHandler output = this.furnace.getOutput();
/*    */ 
/*    */     
/* 29 */     m_38897_((Slot)new ValidatedSlot(fuel, 0, 65, 53, SlotPredicates.FURNACE_FUEL));
/*    */     
/* 31 */     Predicate<ItemStack> inputPredicate = stack -> !this.furnace.getSmeltingResult(stack).m_41619_();
/*    */     
/* 33 */     m_38897_((Slot)new ValidatedSlot(input, 0, 65, 17, inputPredicate));
/*    */     
/* 35 */     int counter = 1;
/*    */     int i;
/* 37 */     for (i = 2; i >= 0; i--) {
/* 38 */       for (int j = 3; j >= 0; j--) {
/* 39 */         m_38897_((Slot)new ValidatedSlot(input, counter++, 11 + i * 18, 8 + j * 18, inputPredicate));
/*    */       }
/*    */     } 
/*    */     
/* 43 */     counter = output.getSlots() - 1;
/*    */ 
/*    */     
/* 46 */     m_38897_((Slot)new MatterFurnaceOutputSlot(this.playerInv.f_35978_, output, counter--, 125, 35));
/*    */ 
/*    */     
/* 49 */     for (i = 0; i < 3; i++) {
/* 50 */       for (int j = 0; j < 4; j++) {
/* 51 */         m_38897_((Slot)new MatterFurnaceOutputSlot(this.playerInv.f_35978_, output, counter--, 147 + i * 18, 8 + j * 18));
/*    */       }
/*    */     } 
/*    */     
/* 55 */     addPlayerInventory(24, 84);
/*    */   }
/*    */ 
/*    */   
/*    */   protected BlockRegistryObject<MatterFurnace, ?> getValidBlock() {
/* 60 */     return PEBlocks.RED_MATTER_FURNACE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\RMFurnaceContainer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */