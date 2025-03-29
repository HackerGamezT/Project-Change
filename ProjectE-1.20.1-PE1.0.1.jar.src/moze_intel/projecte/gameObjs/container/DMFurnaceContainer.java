/*    */ package moze_intel.projecte.gameObjs.container;
/*    */ 
/*    */ import java.util.function.IntConsumer;
/*    */ import java.util.function.IntSupplier;
/*    */ import java.util.function.Predicate;
/*    */ import moze_intel.projecte.gameObjs.block_entities.DMFurnaceBlockEntity;
/*    */ import moze_intel.projecte.gameObjs.blocks.MatterFurnace;
/*    */ import moze_intel.projecte.gameObjs.container.slots.MatterFurnaceOutputSlot;
/*    */ import moze_intel.projecte.gameObjs.container.slots.SlotPredicates;
/*    */ import moze_intel.projecte.gameObjs.container.slots.ValidatedSlot;
/*    */ import moze_intel.projecte.gameObjs.registration.impl.BlockRegistryObject;
/*    */ import moze_intel.projecte.gameObjs.registration.impl.ContainerTypeRegistryObject;
/*    */ import moze_intel.projecte.gameObjs.registries.PEBlocks;
/*    */ import moze_intel.projecte.gameObjs.registries.PEContainerTypes;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.inventory.DataSlot;
/*    */ import net.minecraft.world.inventory.Slot;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.block.entity.BlockEntity;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class DMFurnaceContainer
/*    */   extends PEContainer {
/*    */   public DMFurnaceContainer(int windowId, Inventory playerInv, DMFurnaceBlockEntity furnace) {
/* 27 */     this(PEContainerTypes.DM_FURNACE_CONTAINER, windowId, playerInv, furnace);
/*    */   }
/*    */   public final DMFurnaceBlockEntity furnace;
/*    */   protected DMFurnaceContainer(ContainerTypeRegistryObject<? extends DMFurnaceContainer> type, int windowId, Inventory playerInv, DMFurnaceBlockEntity furnace) {
/* 31 */     super((ContainerTypeRegistryObject)type, windowId, playerInv);
/* 32 */     this.furnace = furnace;
/* 33 */     initSlots();
/* 34 */     addDataSlot(() -> this.furnace.furnaceCookTime, value -> this.furnace.furnaceCookTime = value);
/* 35 */     addDataSlot(() -> this.furnace.furnaceBurnTime, value -> this.furnace.furnaceBurnTime = value);
/* 36 */     addDataSlot(() -> this.furnace.currentItemBurnTime, value -> this.furnace.currentItemBurnTime = value);
/*    */   }
/*    */   
/*    */   private void addDataSlot(final IntSupplier getter, final IntConsumer setter) {
/* 40 */     m_38895_(new DataSlot()
/*    */         {
/*    */           public int m_6501_() {
/* 43 */             return getter.getAsInt();
/*    */           }
/*    */ 
/*    */           
/*    */           public void m_6422_(int value) {
/* 48 */             setter.accept(value);
/*    */           }
/*    */         });
/*    */   }
/*    */   
/*    */   void initSlots() {
/* 54 */     IItemHandler fuel = this.furnace.getFuel();
/* 55 */     IItemHandler input = this.furnace.getInput();
/* 56 */     IItemHandler output = this.furnace.getOutput();
/*    */ 
/*    */     
/* 59 */     m_38897_((Slot)new ValidatedSlot(fuel, 0, 49, 53, SlotPredicates.FURNACE_FUEL));
/*    */ 
/*    */     
/* 62 */     Predicate<ItemStack> inputPredicate = stack -> !this.furnace.getSmeltingResult(stack).m_41619_();
/* 63 */     m_38897_((Slot)new ValidatedSlot(input, 0, 49, 17, inputPredicate));
/*    */     
/* 65 */     int counter = 1;
/*    */     int i;
/* 67 */     for (i = 1; i >= 0; i--) {
/* 68 */       for (int j = 3; j >= 0; j--) {
/* 69 */         m_38897_((Slot)new ValidatedSlot(input, counter++, 13 + i * 18, 8 + j * 18, inputPredicate));
/*    */       }
/*    */     } 
/*    */     
/* 73 */     counter = output.getSlots() - 1;
/*    */ 
/*    */     
/* 76 */     m_38897_((Slot)new MatterFurnaceOutputSlot(this.playerInv.f_35978_, output, counter--, 109, 35));
/*    */ 
/*    */     
/* 79 */     for (i = 0; i < 2; i++) {
/* 80 */       for (int j = 0; j < 4; j++) {
/* 81 */         m_38897_((Slot)new MatterFurnaceOutputSlot(this.playerInv.f_35978_, output, counter--, 131 + i * 18, 8 + j * 18));
/*    */       }
/*    */     } 
/*    */     
/* 85 */     addPlayerInventory(8, 84);
/*    */   }
/*    */   
/*    */   protected BlockRegistryObject<MatterFurnace, ?> getValidBlock() {
/* 89 */     return PEBlocks.DARK_MATTER_FURNACE;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_6875_(@NotNull Player player) {
/* 94 */     return stillValid(player, (BlockEntity)this.furnace, getValidBlock());
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\DMFurnaceContainer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */