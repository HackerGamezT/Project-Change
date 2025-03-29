/*     */ package moze_intel.projecte.gameObjs.container;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import moze_intel.projecte.gameObjs.container.slots.HotBarSlot;
/*     */ import moze_intel.projecte.gameObjs.container.slots.IInsertableSlot;
/*     */ import moze_intel.projecte.gameObjs.container.slots.InventoryContainerSlot;
/*     */ import moze_intel.projecte.gameObjs.container.slots.MainInventorySlot;
/*     */ import moze_intel.projecte.gameObjs.registration.impl.BlockRegistryObject;
/*     */ import moze_intel.projecte.gameObjs.registration.impl.ContainerTypeRegistryObject;
/*     */ import moze_intel.projecte.network.PacketHandler;
/*     */ import moze_intel.projecte.network.packets.IPEPacket;
/*     */ import moze_intel.projecte.network.packets.to_client.UpdateWindowIntPKT;
/*     */ import moze_intel.projecte.network.packets.to_client.UpdateWindowLongPKT;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.Container;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*     */ import net.minecraft.world.inventory.DataSlot;
/*     */ import net.minecraft.world.inventory.MenuType;
/*     */ import net.minecraft.world.inventory.Slot;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public abstract class PEContainer extends AbstractContainerMenu {
/*  29 */   protected final List<InventoryContainerSlot> inventoryContainerSlots = new ArrayList<>();
/*  30 */   protected final List<MainInventorySlot> mainInventorySlots = new ArrayList<>();
/*  31 */   protected final List<HotBarSlot> hotBarSlots = new ArrayList<>();
/*     */ 
/*     */   
/*  34 */   private final List<DataSlot> intFields = new ArrayList<>();
/*  35 */   protected final List<BoxedLong> longFields = new ArrayList<>();
/*     */   protected final Inventory playerInv;
/*     */   
/*     */   protected PEContainer(ContainerTypeRegistryObject<? extends PEContainer> typeRO, int id, Inventory playerInv) {
/*  39 */     super((MenuType)typeRO.get(), id);
/*  40 */     this.playerInv = playerInv;
/*     */   }
/*     */   
/*     */   protected void addPlayerInventory(int xStart, int yStart) {
/*  44 */     int slotSize = 18;
/*  45 */     int rows = 3;
/*     */     int i;
/*  47 */     for (i = 0; i < rows; i++) {
/*  48 */       for (int j = 0; j < 9; j++) {
/*  49 */         m_38897_((Slot)createMainInventorySlot(this.playerInv, j + i * 9 + 9, xStart + j * slotSize, yStart + i * slotSize));
/*     */       }
/*     */     } 
/*  52 */     yStart = yStart + slotSize * rows + 4;
/*     */     
/*  54 */     for (i = 0; i < Inventory.m_36059_(); i++) {
/*  55 */       m_38897_((Slot)createHotBarSlot(this.playerInv, i, xStart + i * slotSize, yStart));
/*     */     }
/*     */   }
/*     */   
/*     */   protected MainInventorySlot createMainInventorySlot(@NotNull Inventory inv, int index, int x, int y) {
/*  60 */     return new MainInventorySlot((Container)inv, index, x, y);
/*     */   }
/*     */   
/*     */   protected HotBarSlot createHotBarSlot(@NotNull Inventory inv, int index, int x, int y) {
/*  64 */     return new HotBarSlot((Container)inv, index, x, y);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   protected Slot m_38897_(@NotNull Slot slot) {
/*  70 */     super.m_38897_(slot);
/*  71 */     if (slot instanceof InventoryContainerSlot) { InventoryContainerSlot containerSlot = (InventoryContainerSlot)slot;
/*  72 */       this.inventoryContainerSlots.add(containerSlot); }
/*  73 */     else if (slot instanceof MainInventorySlot) { MainInventorySlot inventorySlot = (MainInventorySlot)slot;
/*  74 */       this.mainInventorySlots.add(inventorySlot); }
/*  75 */     else if (slot instanceof HotBarSlot) { HotBarSlot hotBarSlot = (HotBarSlot)slot;
/*  76 */       this.hotBarSlots.add(hotBarSlot); }
/*     */     
/*  78 */     return slot;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Slot tryGetSlot(int slotId) {
/*  83 */     if (slotId >= 0 && slotId < this.f_38839_.size()) {
/*  84 */       return m_38853_(slotId);
/*     */     }
/*  86 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack m_7648_(@NotNull Player player, int slotID) {
/*  99 */     Slot currentSlot = (Slot)this.f_38839_.get(slotID);
/* 100 */     if (currentSlot == null || !currentSlot.m_6657_()) {
/* 101 */       return ItemStack.f_41583_;
/*     */     }
/* 103 */     ItemStack slotStack = currentSlot.m_7993_();
/* 104 */     ItemStack stackToInsert = slotStack;
/* 105 */     if (currentSlot instanceof InventoryContainerSlot) {
/*     */       
/* 107 */       stackToInsert = insertItem(this.hotBarSlots, stackToInsert, true);
/* 108 */       stackToInsert = insertItem(this.mainInventorySlots, stackToInsert, true);
/*     */ 
/*     */ 
/*     */       
/* 112 */       stackToInsert = insertItem(this.hotBarSlots, stackToInsert, false);
/* 113 */       stackToInsert = insertItem(this.mainInventorySlots, stackToInsert, false);
/*     */     }
/*     */     else {
/*     */       
/* 117 */       stackToInsert = insertItem(this.inventoryContainerSlots, stackToInsert, true);
/* 118 */       if (slotStack.m_41613_() == stackToInsert.m_41613_()) {
/*     */ 
/*     */         
/* 121 */         stackToInsert = insertItem(this.inventoryContainerSlots, stackToInsert, false);
/* 122 */         if (slotStack.m_41613_() == stackToInsert.m_41613_())
/*     */         {
/* 124 */           if (currentSlot instanceof MainInventorySlot) {
/* 125 */             stackToInsert = insertItem(this.hotBarSlots, stackToInsert, true);
/* 126 */             stackToInsert = insertItem(this.hotBarSlots, stackToInsert, false);
/* 127 */           } else if (currentSlot instanceof HotBarSlot) {
/* 128 */             stackToInsert = insertItem(this.mainInventorySlots, stackToInsert, true);
/* 129 */             stackToInsert = insertItem(this.mainInventorySlots, stackToInsert, false);
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/* 134 */     if (stackToInsert.m_41613_() == slotStack.m_41613_())
/*     */     {
/* 136 */       return ItemStack.f_41583_;
/*     */     }
/*     */     
/* 139 */     return transferSuccess(currentSlot, player, slotStack, stackToInsert);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   protected ItemStack transferSuccess(@NotNull Slot currentSlot, @NotNull Player player, @NotNull ItemStack slotStack, @NotNull ItemStack stackToInsert) {
/* 144 */     int difference = slotStack.m_41613_() - stackToInsert.m_41613_();
/* 145 */     ItemStack newStack = currentSlot.m_6201_(difference);
/* 146 */     currentSlot.m_142406_(player, newStack);
/* 147 */     return newStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static <SLOT extends Slot & IInsertableSlot> ItemStack insertItem(List<SLOT> slots, @NotNull ItemStack stack, boolean ignoreEmpty) {
/* 159 */     if (stack.m_41619_())
/*     */     {
/*     */       
/* 162 */       return stack;
/*     */     }
/* 164 */     for (Slot slot : slots) {
/* 165 */       if (ignoreEmpty != slot.m_6657_()) {
/*     */         continue;
/*     */       }
/*     */       
/* 169 */       stack = ((IInsertableSlot)slot).insertItem(stack, false);
/* 170 */       if (stack.m_41619_()) {
/*     */         break;
/*     */       }
/*     */     } 
/* 174 */     return stack;
/*     */   }
/*     */   
/*     */   protected static boolean stillValid(Player player, BlockEntity blockEntity, BlockRegistryObject<?, ?> blockRO) {
/* 178 */     BlockPos pos = blockEntity.m_58899_();
/* 179 */     return (player.m_9236_().m_8055_(pos).m_60734_() == blockRO.getBlock() && player
/* 180 */       .m_20275_(pos.m_123341_() + 0.5D, pos.m_123342_() + 0.5D, pos.m_123343_() + 0.5D) <= 64.0D);
/*     */   }
/*     */   
/*     */   public final void updateProgressBarLong(int idx, long data) {
/* 184 */     ((BoxedLong)this.longFields.get(idx)).set(data);
/*     */   }
/*     */   
/*     */   public final void updateProgressBarInt(int idx, int data) {
/* 188 */     ((DataSlot)this.intFields.get(idx)).m_6422_(data);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   protected DataSlot m_38895_(@NotNull DataSlot referenceHolder) {
/* 194 */     this.intFields.add(referenceHolder);
/* 195 */     return referenceHolder;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void broadcastPE(boolean all) {
/*     */     int i;
/* 202 */     for (i = 0; i < this.longFields.size(); i++) {
/* 203 */       BoxedLong boxedLong = this.longFields.get(i);
/*     */       
/* 205 */       if (boxedLong.isDirty() || all) {
/* 206 */         syncDataChange((IPEPacket)new UpdateWindowLongPKT((short)this.f_38840_, (short)i, boxedLong.get()));
/*     */       }
/*     */     } 
/* 209 */     for (i = 0; i < this.intFields.size(); i++) {
/* 210 */       DataSlot referenceHolder = this.intFields.get(i);
/*     */       
/* 212 */       if (referenceHolder.m_39409_() || all) {
/* 213 */         syncDataChange((IPEPacket)new UpdateWindowIntPKT((short)this.f_38840_, (short)i, referenceHolder.m_6501_()));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_38946_() {
/* 220 */     super.m_38946_();
/* 221 */     broadcastPE(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_150429_() {
/* 226 */     super.m_150429_();
/* 227 */     broadcastPE(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncDataChange(IPEPacket packet) {
/* 233 */     Player player = this.playerInv.f_35978_; if (player instanceof ServerPlayer) { ServerPlayer serverPlayer = (ServerPlayer)player;
/* 234 */       PacketHandler.sendTo(packet, serverPlayer); }
/*     */   
/*     */   }
/*     */   
/*     */   public static class BoxedLong
/*     */   {
/*     */     private long inner;
/*     */     private boolean dirty = false;
/*     */     
/*     */     public long get() {
/* 244 */       return this.inner;
/*     */     }
/*     */     
/*     */     public void set(long v) {
/* 248 */       if (v != this.inner) {
/* 249 */         this.inner = v;
/* 250 */         this.dirty = true;
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean isDirty() {
/* 255 */       boolean ret = this.dirty;
/* 256 */       this.dirty = false;
/* 257 */       return ret;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\PEContainer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */