/*    */ package moze_intel.projecte.gameObjs.container;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.block_entities.CollectorMK1BlockEntity;
/*    */ import moze_intel.projecte.gameObjs.blocks.Collector;
/*    */ import moze_intel.projecte.gameObjs.container.slots.SlotGhost;
/*    */ import moze_intel.projecte.gameObjs.container.slots.SlotPredicates;
/*    */ import moze_intel.projecte.gameObjs.container.slots.ValidatedSlot;
/*    */ import moze_intel.projecte.gameObjs.registration.impl.BlockRegistryObject;
/*    */ import moze_intel.projecte.gameObjs.registration.impl.ContainerTypeRegistryObject;
/*    */ import moze_intel.projecte.gameObjs.registries.PEBlocks;
/*    */ import moze_intel.projecte.gameObjs.registries.PEContainerTypes;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.inventory.ClickType;
/*    */ import net.minecraft.world.inventory.DataSlot;
/*    */ import net.minecraft.world.inventory.Slot;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.block.entity.BlockEntity;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class CollectorMK1Container extends PEContainer {
/*    */   public final CollectorMK1BlockEntity collector;
/* 24 */   public final DataSlot sunLevel = DataSlot.m_39401_();
/* 25 */   public final PEContainer.BoxedLong emc = new PEContainer.BoxedLong();
/* 26 */   private final DataSlot kleinChargeProgress = DataSlot.m_39401_();
/* 27 */   private final DataSlot fuelProgress = DataSlot.m_39401_();
/* 28 */   public final PEContainer.BoxedLong kleinEmc = new PEContainer.BoxedLong();
/*    */   
/*    */   public CollectorMK1Container(int windowId, Inventory playerInv, CollectorMK1BlockEntity collector) {
/* 31 */     this(PEContainerTypes.COLLECTOR_MK1_CONTAINER, windowId, playerInv, collector);
/*    */   }
/*    */   
/*    */   protected CollectorMK1Container(ContainerTypeRegistryObject<? extends CollectorMK1Container> type, int windowId, Inventory playerInv, CollectorMK1BlockEntity collector) {
/* 35 */     super((ContainerTypeRegistryObject)type, windowId, playerInv);
/* 36 */     this.longFields.add(this.emc);
/* 37 */     m_38895_(this.sunLevel);
/* 38 */     m_38895_(this.kleinChargeProgress);
/* 39 */     m_38895_(this.fuelProgress);
/* 40 */     this.longFields.add(this.kleinEmc);
/* 41 */     this.collector = collector;
/* 42 */     initSlots();
/*    */   }
/*    */   
/*    */   void initSlots() {
/* 46 */     IItemHandler aux = this.collector.getAux();
/* 47 */     IItemHandler main = this.collector.getInput();
/*    */ 
/*    */     
/* 50 */     m_38897_((Slot)new ValidatedSlot(aux, 0, 124, 58, SlotPredicates.COLLECTOR_INV));
/* 51 */     int counter = 0;
/*    */     
/* 53 */     for (int i = 1; i >= 0; i--) {
/* 54 */       for (int j = 3; j >= 0; j--) {
/* 55 */         m_38897_((Slot)new ValidatedSlot(main, counter++, 20 + i * 18, 8 + j * 18, SlotPredicates.COLLECTOR_INV));
/*    */       }
/*    */     } 
/*    */     
/* 59 */     m_38897_((Slot)new ValidatedSlot(aux, 1, 124, 13, SlotPredicates.ALWAYS_FALSE));
/*    */     
/* 61 */     m_38897_((Slot)new SlotGhost(aux, 2, 153, 36, SlotPredicates.COLLECTOR_LOCK));
/* 62 */     addPlayerInventory(8, 84);
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_150399_(int slotID, int button, @NotNull ClickType flag, @NotNull Player player) {
/* 67 */     Slot slot = tryGetSlot(slotID);
/* 68 */     if (slot instanceof SlotGhost && !slot.m_7993_().m_41619_()) {
/* 69 */       slot.m_5852_(ItemStack.f_41583_);
/*    */     } else {
/* 71 */       super.m_150399_(slotID, button, flag, player);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void broadcastPE(boolean all) {
/* 77 */     this.emc.set(this.collector.getStoredEmc());
/* 78 */     this.sunLevel.m_6422_(this.collector.getSunLevel());
/* 79 */     this.kleinChargeProgress.m_6422_((int)(this.collector.getItemChargeProportion() * 8000.0D));
/* 80 */     this.fuelProgress.m_6422_((int)(this.collector.getFuelProgress() * 8000.0D));
/* 81 */     this.kleinEmc.set(this.collector.getItemCharge());
/* 82 */     super.broadcastPE(all);
/*    */   }
/*    */   
/*    */   protected BlockRegistryObject<Collector, ?> getValidBlock() {
/* 86 */     return PEBlocks.COLLECTOR;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_6875_(@NotNull Player player) {
/* 91 */     return stillValid(player, (BlockEntity)this.collector, getValidBlock());
/*    */   }
/*    */   
/*    */   public double getKleinChargeProgress() {
/* 95 */     return this.kleinChargeProgress.m_6501_() / 8000.0D;
/*    */   }
/*    */   
/*    */   public double getFuelProgress() {
/* 99 */     return this.fuelProgress.m_6501_() / 8000.0D;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\CollectorMK1Container.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */