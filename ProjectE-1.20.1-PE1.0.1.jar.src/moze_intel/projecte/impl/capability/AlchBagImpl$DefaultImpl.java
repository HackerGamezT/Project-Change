/*    */ package moze_intel.projecte.impl.capability;
/*    */ 
/*    */ import java.util.EnumMap;
/*    */ import java.util.Map;
/*    */ import moze_intel.projecte.api.capabilities.IAlchBagProvider;
/*    */ import moze_intel.projecte.network.PacketHandler;
/*    */ import moze_intel.projecte.network.packets.IPEPacket;
/*    */ import moze_intel.projecte.network.packets.to_client.SyncBagDataPKT;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.nbt.Tag;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.item.DyeColor;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import net.minecraftforge.items.ItemStackHandler;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class DefaultImpl
/*    */   implements IAlchBagProvider
/*    */ {
/* 29 */   private final Map<DyeColor, ItemStackHandler> inventories = new EnumMap<>(DyeColor.class);
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public IItemHandler getBag(@NotNull DyeColor color) {
/* 34 */     if (!this.inventories.containsKey(color)) {
/* 35 */       this.inventories.put(color, new ItemStackHandler(104));
/*    */     }
/* 37 */     return (IItemHandler)this.inventories.get(color);
/*    */   }
/*    */ 
/*    */   
/*    */   public void sync(@Nullable DyeColor color, @NotNull ServerPlayer player) {
/* 42 */     PacketHandler.sendTo((IPEPacket)new SyncBagDataPKT(writeNBT(color)), player);
/*    */   }
/*    */   
/*    */   private CompoundTag writeNBT(DyeColor color) {
/* 46 */     CompoundTag ret = new CompoundTag();
/* 47 */     (new DyeColor[1])[0] = color; DyeColor[] colors = (color == null) ? DyeColor.values() : new DyeColor[1];
/* 48 */     for (DyeColor c : colors) {
/* 49 */       if (this.inventories.containsKey(c)) {
/* 50 */         ret.m_128365_(c.m_7912_(), (Tag)((ItemStackHandler)this.inventories.get(c)).serializeNBT());
/*    */       }
/*    */     } 
/* 53 */     return ret;
/*    */   }
/*    */ 
/*    */   
/*    */   public CompoundTag serializeNBT() {
/* 58 */     return writeNBT(null);
/*    */   }
/*    */ 
/*    */   
/*    */   public void deserializeNBT(CompoundTag nbt) {
/* 63 */     for (DyeColor e : DyeColor.values()) {
/* 64 */       if (nbt.m_128441_(e.m_7912_())) {
/* 65 */         ItemStackHandler inv = new ItemStackHandler(104);
/* 66 */         inv.deserializeNBT(nbt.m_128469_(e.m_7912_()));
/* 67 */         this.inventories.put(e, inv);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\impl\capability\AlchBagImpl$DefaultImpl.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */