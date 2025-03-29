/*    */ package moze_intel.projecte.capability;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.core.Direction;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.nbt.Tag;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.ICapabilitySerializable;
/*    */ import net.minecraftforge.common.util.LazyOptional;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class ItemCapabilityWrapper implements ICapabilitySerializable<CompoundTag> {
/*    */   private final ItemCapability<?>[] capabilities;
/*    */   private final ItemStack itemStack;
/*    */   
/*    */   public ItemCapabilityWrapper(ItemStack stack, List<Supplier<ItemCapability<?>>> capabilities) {
/* 20 */     this.itemStack = stack;
/* 21 */     this.capabilities = (ItemCapability<?>[])new ItemCapability[capabilities.size()];
/* 22 */     for (int i = 0; i < capabilities.size(); i++) {
/* 23 */       ItemCapability<?> cap = ((Supplier<ItemCapability>)capabilities.get(i)).get();
/* 24 */       this.capabilities[i] = cap;
/* 25 */       cap.setWrapper(this);
/*    */     } 
/*    */   }
/*    */   
/*    */   public ItemCapabilityWrapper(ItemStack stack, ItemCapability<?>... capabilities) {
/* 30 */     this.itemStack = stack;
/* 31 */     this.capabilities = capabilities;
/* 32 */     for (ItemCapability<?> cap : this.capabilities) {
/* 33 */       cap.setWrapper(this);
/*    */     }
/*    */   }
/*    */   
/*    */   protected ItemStack getItemStack() {
/* 38 */     return this.itemStack;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction side) {
/* 44 */     for (ItemCapability<?> cap : this.capabilities) {
/* 45 */       if (capability == cap.getCapability()) {
/* 46 */         return cap.getLazyCapability().cast();
/*    */       }
/*    */     } 
/* 49 */     return LazyOptional.empty();
/*    */   }
/*    */ 
/*    */   
/*    */   public CompoundTag serializeNBT() {
/* 54 */     CompoundTag serializedNBT = new CompoundTag();
/* 55 */     for (ItemCapability<?> cap : this.capabilities) {
/* 56 */       if (cap instanceof IItemCapabilitySerializable) { IItemCapabilitySerializable serializableCap = (IItemCapabilitySerializable)cap;
/* 57 */         serializedNBT.m_128365_(serializableCap.getStorageKey(), serializableCap.serializeNBT()); }
/*    */     
/*    */     } 
/* 60 */     return serializedNBT;
/*    */   }
/*    */ 
/*    */   
/*    */   public void deserializeNBT(CompoundTag nbt) {
/* 65 */     for (ItemCapability<?> cap : this.capabilities) {
/* 66 */       if (cap instanceof IItemCapabilitySerializable) { IItemCapabilitySerializable serializableCap = (IItemCapabilitySerializable)cap; if (nbt.m_128441_(serializableCap.getStorageKey()))
/* 67 */           serializableCap.deserializeNBT(nbt.m_128423_(serializableCap.getStorageKey()));  }
/*    */     
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\capability\ItemCapabilityWrapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */