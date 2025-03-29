/*    */ package moze_intel.projecte.gameObjs.container;
/*    */ import java.util.Objects;
/*    */ import java.util.function.Predicate;
/*    */ import moze_intel.projecte.api.ItemInfo;
/*    */ import moze_intel.projecte.gameObjs.block_entities.CondenserBlockEntity;
/*    */ import moze_intel.projecte.gameObjs.blocks.Condenser;
/*    */ import moze_intel.projecte.gameObjs.container.slots.SlotCondenserLock;
/*    */ import moze_intel.projecte.gameObjs.container.slots.SlotPredicates;
/*    */ import moze_intel.projecte.gameObjs.container.slots.ValidatedSlot;
/*    */ import moze_intel.projecte.gameObjs.registration.impl.BlockRegistryObject;
/*    */ import moze_intel.projecte.gameObjs.registration.impl.ContainerTypeRegistryObject;
/*    */ import moze_intel.projecte.gameObjs.registries.PEContainerTypes;
/*    */ import moze_intel.projecte.network.packets.IPEPacket;
/*    */ import moze_intel.projecte.network.packets.to_client.UpdateCondenserLockPKT;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.inventory.ClickType;
/*    */ import net.minecraft.world.inventory.Slot;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.block.entity.BlockEntity;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import net.minecraftforge.items.ItemStackHandler;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class CondenserContainer extends EmcChestBlockEntityContainer<CondenserBlockEntity> {
/* 27 */   public final PEContainer.BoxedLong displayEmc = new PEContainer.BoxedLong();
/* 28 */   public final PEContainer.BoxedLong requiredEmc = new PEContainer.BoxedLong();
/*    */   @Nullable
/*    */   private ItemInfo lastLockInfo;
/*    */   
/*    */   public CondenserContainer(int windowId, Inventory playerInv, CondenserBlockEntity condenser) {
/* 33 */     this(PEContainerTypes.CONDENSER_CONTAINER, windowId, playerInv, condenser);
/*    */   }
/*    */   
/*    */   protected CondenserContainer(ContainerTypeRegistryObject<? extends CondenserContainer> type, int windowId, Inventory playerInv, CondenserBlockEntity condenser) {
/* 37 */     super((ContainerTypeRegistryObject)type, windowId, playerInv, condenser);
/* 38 */     this.longFields.add(this.displayEmc);
/* 39 */     this.longFields.add(this.requiredEmc);
/* 40 */     initSlots();
/*    */   }
/*    */   
/*    */   protected void initSlots() {
/* 44 */     Objects.requireNonNull(this.blockEntity); m_38897_((Slot)new SlotCondenserLock(this.blockEntity::getLockInfo, 0, 12, 6));
/* 45 */     Predicate<ItemStack> validator = s -> (SlotPredicates.HAS_EMC.test(s) && !this.blockEntity.isStackEqualToLock(s));
/* 46 */     ItemStackHandler itemStackHandler = this.blockEntity.getInput();
/* 47 */     for (int i = 0; i < 7; i++) {
/* 48 */       for (int j = 0; j < 13; j++) {
/* 49 */         m_38897_((Slot)new ValidatedSlot((IItemHandler)itemStackHandler, j + i * 13, 12 + j * 18, 26 + i * 18, validator));
/*    */       }
/*    */     } 
/* 52 */     addPlayerInventory(48, 154);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void broadcastPE(boolean all) {
/* 57 */     this.displayEmc.set(this.blockEntity.displayEmc);
/* 58 */     this.requiredEmc.set(this.blockEntity.requiredEmc);
/* 59 */     ItemInfo lockInfo = this.blockEntity.getLockInfo();
/* 60 */     if (all || !Objects.equals(lockInfo, this.lastLockInfo)) {
/* 61 */       this.lastLockInfo = lockInfo;
/* 62 */       syncDataChange((IPEPacket)new UpdateCondenserLockPKT((short)this.f_38840_, lockInfo));
/*    */     } 
/* 64 */     super.broadcastPE(all);
/*    */   }
/*    */   
/*    */   protected BlockRegistryObject<? extends Condenser, ?> getValidBlock() {
/* 68 */     return PEBlocks.CONDENSER;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_6875_(@NotNull Player player) {
/* 73 */     return stillValid(player, (BlockEntity)this.blockEntity, getValidBlock());
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_150399_(int slot, int button, @NotNull ClickType flag, @NotNull Player player) {
/* 78 */     if (slot == 0) {
/* 79 */       if (this.blockEntity.attemptCondenserSet(player)) {
/* 80 */         m_38946_();
/*    */       }
/*    */     } else {
/* 83 */       super.m_150399_(slot, button, flag, player);
/*    */     } 
/*    */   }
/*    */   
/*    */   public int getProgressScaled() {
/* 88 */     if (this.requiredEmc.get() == 0L) {
/* 89 */       return 0;
/*    */     }
/* 91 */     if (this.displayEmc.get() >= this.requiredEmc.get()) {
/* 92 */       return 102;
/*    */     }
/* 94 */     return (int)(102.0D * this.displayEmc.get() / this.requiredEmc.get());
/*    */   }
/*    */   
/*    */   public void updateLockInfo(@Nullable ItemInfo lockInfo) {
/* 98 */     this.blockEntity.setLockInfoFromPacket(lockInfo);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\CondenserContainer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */