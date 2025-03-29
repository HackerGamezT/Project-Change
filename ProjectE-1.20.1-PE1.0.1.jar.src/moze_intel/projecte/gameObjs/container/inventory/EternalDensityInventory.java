/*    */ package moze_intel.projecte.gameObjs.container.inventory;
/*    */ 
/*    */ import moze_intel.projecte.network.PacketHandler;
/*    */ import moze_intel.projecte.network.packets.IPEPacket;
/*    */ import moze_intel.projecte.network.packets.to_server.UpdateGemModePKT;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.nbt.Tag;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.items.IItemHandlerModifiable;
/*    */ import net.minecraftforge.items.ItemStackHandler;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class EternalDensityInventory implements IItemHandlerModifiable {
/* 14 */   private final ItemStackHandler inventory = new ItemStackHandler(9);
/*    */   private boolean isInWhitelist;
/*    */   public final ItemStack invItem;
/*    */   
/*    */   public EternalDensityInventory(ItemStack stack) {
/* 19 */     this.invItem = stack;
/* 20 */     if (stack.m_41782_()) {
/* 21 */       readFromNBT(stack.m_41784_());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSlots() {
/* 27 */     return this.inventory.getSlots();
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack getStackInSlot(int slot) {
/* 33 */     return this.inventory.getStackInSlot(slot);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
/* 39 */     ItemStack ret = this.inventory.insertItem(slot, stack, simulate);
/* 40 */     writeBack();
/* 41 */     return ret;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack extractItem(int slot, int amount, boolean simulate) {
/* 47 */     ItemStack ret = this.inventory.extractItem(slot, amount, simulate);
/* 48 */     writeBack();
/* 49 */     return ret;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSlotLimit(int slot) {
/* 54 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isItemValid(int slot, @NotNull ItemStack stack) {
/* 59 */     return this.inventory.isItemValid(slot, stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setStackInSlot(int slot, @NotNull ItemStack stack) {
/* 64 */     this.inventory.setStackInSlot(slot, stack);
/* 65 */     writeBack();
/*    */   }
/*    */   
/*    */   private void writeBack() {
/* 69 */     for (int i = 0; i < this.inventory.getSlots(); i++) {
/* 70 */       if (this.inventory.getStackInSlot(i).m_41619_()) {
/* 71 */         this.inventory.setStackInSlot(i, ItemStack.f_41583_);
/*    */       }
/*    */     } 
/* 74 */     writeToNBT(this.invItem.m_41784_());
/*    */   }
/*    */   
/*    */   public void readFromNBT(CompoundTag nbt) {
/* 78 */     this.isInWhitelist = nbt.m_128471_("Whitelist");
/* 79 */     this.inventory.deserializeNBT(nbt.m_128469_("Items"));
/*    */   }
/*    */   
/*    */   public void writeToNBT(CompoundTag nbt) {
/* 83 */     nbt.m_128379_("Whitelist", this.isInWhitelist);
/* 84 */     nbt.m_128365_("Items", (Tag)this.inventory.serializeNBT());
/*    */   }
/*    */   
/*    */   public void changeMode() {
/* 88 */     this.isInWhitelist = !this.isInWhitelist;
/* 89 */     writeBack();
/* 90 */     PacketHandler.sendToServer((IPEPacket)new UpdateGemModePKT(this.isInWhitelist));
/*    */   }
/*    */   
/*    */   public boolean isWhitelistMode() {
/* 94 */     return this.isInWhitelist;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\inventory\EternalDensityInventory.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */