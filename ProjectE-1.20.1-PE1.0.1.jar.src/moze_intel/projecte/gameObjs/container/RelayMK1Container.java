/*    */ package moze_intel.projecte.gameObjs.container;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.block_entities.RelayMK1BlockEntity;
/*    */ import moze_intel.projecte.gameObjs.blocks.Relay;
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
/*    */ import net.minecraft.world.level.block.entity.BlockEntity;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class RelayMK1Container extends PEContainer {
/* 20 */   private final DataSlot kleinChargeProgress = DataSlot.m_39401_(); public final RelayMK1BlockEntity relay;
/* 21 */   private final DataSlot inputBurnProgress = DataSlot.m_39401_();
/* 22 */   public final PEContainer.BoxedLong emc = new PEContainer.BoxedLong();
/*    */   
/*    */   public RelayMK1Container(int windowId, Inventory playerInv, RelayMK1BlockEntity relay) {
/* 25 */     this(PEContainerTypes.RELAY_MK1_CONTAINER, windowId, playerInv, relay);
/*    */   }
/*    */   
/*    */   protected RelayMK1Container(ContainerTypeRegistryObject<? extends RelayMK1Container> type, int windowId, Inventory playerInv, RelayMK1BlockEntity relay) {
/* 29 */     super((ContainerTypeRegistryObject)type, windowId, playerInv);
/* 30 */     this.longFields.add(this.emc);
/* 31 */     m_38895_(this.kleinChargeProgress);
/* 32 */     m_38895_(this.inputBurnProgress);
/* 33 */     this.relay = relay;
/* 34 */     initSlots();
/*    */   }
/*    */   
/*    */   void initSlots() {
/* 38 */     IItemHandler input = this.relay.getInput();
/* 39 */     IItemHandler output = this.relay.getOutput();
/*    */     
/* 41 */     m_38897_((Slot)new ValidatedSlot(output, 0, 127, 43, SlotPredicates.EMC_HOLDER));
/*    */     
/* 43 */     m_38897_((Slot)new ValidatedSlot(input, 0, 67, 43, SlotPredicates.RELAY_INV));
/* 44 */     int counter = 1;
/*    */     
/* 46 */     for (int i = 1; i >= 0; i--) {
/* 47 */       for (int j = 2; j >= 0; j--) {
/* 48 */         m_38897_((Slot)new ValidatedSlot(input, counter++, 27 + i * 18, 17 + j * 18, SlotPredicates.RELAY_INV));
/*    */       }
/*    */     } 
/* 51 */     addPlayerInventory(8, 95);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void broadcastPE(boolean all) {
/* 56 */     this.emc.set(this.relay.getStoredEmc());
/* 57 */     this.kleinChargeProgress.m_6422_((int)(this.relay.getItemChargeProportion() * 8000.0D));
/* 58 */     this.inputBurnProgress.m_6422_((int)(this.relay.getInputBurnProportion() * 8000.0D));
/* 59 */     super.broadcastPE(all);
/*    */   }
/*    */   
/*    */   protected BlockRegistryObject<Relay, ?> getValidBlock() {
/* 63 */     return PEBlocks.RELAY;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_6875_(@NotNull Player player) {
/* 68 */     return stillValid(player, (BlockEntity)this.relay, getValidBlock());
/*    */   }
/*    */   
/*    */   public double getKleinChargeProgress() {
/* 72 */     return this.kleinChargeProgress.m_6501_() / 8000.0D;
/*    */   }
/*    */   
/*    */   public double getInputBurnProgress() {
/* 76 */     return this.inputBurnProgress.m_6501_() / 8000.0D;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\RelayMK1Container.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */