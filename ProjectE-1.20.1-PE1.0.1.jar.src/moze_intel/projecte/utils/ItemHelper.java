/*     */ package moze_intel.projecte.utils;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.core.NonNullList;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.InteractionResultHolder;
/*     */ import net.minecraft.world.item.BlockItem;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.context.BlockPlaceContext;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraftforge.common.Tags;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import net.minecraftforge.items.IItemHandlerModifiable;
/*     */ import net.minecraftforge.items.ItemHandlerHelper;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ItemHelper
/*     */ {
/*     */   public static InteractionResultHolder<ItemStack> actionResultFromType(InteractionResult type, ItemStack stack) {
/*  30 */     switch (type) { case SUCCESS: case CONSUME: case FAIL:  }  return 
/*     */ 
/*     */ 
/*     */       
/*  34 */       InteractionResultHolder.m_19098_(stack);
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
/*     */   
/*     */   public static boolean checkItemNBT(ItemStack stack, String key) {
/*  47 */     return (stack.m_41782_() && stack.m_41784_().m_128471_(key));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean compactInventory(IItemHandlerModifiable inventory) {
/*  56 */     List<ItemStack> temp = new ArrayList<>();
/*  57 */     for (int i = 0; i < inventory.getSlots(); i++) {
/*  58 */       ItemStack stackInSlot = inventory.getStackInSlot(i);
/*  59 */       if (!stackInSlot.m_41619_()) {
/*  60 */         temp.add(stackInSlot);
/*  61 */         inventory.setStackInSlot(i, ItemStack.f_41583_);
/*     */       } 
/*     */     } 
/*  64 */     for (ItemStack s : temp) {
/*  65 */       ItemHandlerHelper.insertItemStacked((IItemHandler)inventory, s, false);
/*     */     }
/*  67 */     return temp.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void compactItemListNoStacksize(List<ItemStack> list) {
/*  74 */     for (int i = 0; i < list.size(); i++) {
/*  75 */       ItemStack s = list.get(i);
/*  76 */       if (!s.m_41619_()) {
/*  77 */         for (int j = i + 1; j < list.size(); j++) {
/*  78 */           ItemStack s1 = list.get(j);
/*  79 */           if (ItemHandlerHelper.canItemStacksStack(s, s1)) {
/*  80 */             s.m_41769_(s1.m_41613_());
/*  81 */             list.set(j, ItemStack.f_41583_);
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*  86 */     list.removeIf(ItemStack::m_41619_);
/*  87 */     list.sort(Comparators.ITEMSTACK_ASCENDING);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static CompoundTag copyNBTSkipKey(@NotNull CompoundTag nbt, @NotNull String keyToSkip) {
/*  98 */     CompoundTag copiedNBT = new CompoundTag();
/*  99 */     for (String key : nbt.m_128431_()) {
/* 100 */       if (keyToSkip.equals(key)) {
/*     */         continue;
/*     */       }
/* 103 */       Tag innerNBT = nbt.m_128423_(key);
/* 104 */       if (innerNBT != null)
/*     */       {
/* 106 */         copiedNBT.m_128365_(key, innerNBT.m_6426_());
/*     */       }
/*     */     } 
/* 109 */     if (copiedNBT.m_128456_()) {
/* 110 */       return null;
/*     */     }
/* 112 */     return copiedNBT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ItemStack getNormalizedStack(ItemStack stack) {
/* 119 */     return stack.m_255036_(1);
/*     */   }
/*     */   
/*     */   public static IItemHandlerModifiable immutableCopy(IItemHandler toCopy) {
/* 123 */     final List<ItemStack> list = new ArrayList<>(toCopy.getSlots());
/* 124 */     for (int i = 0; i < toCopy.getSlots(); i++) {
/* 125 */       list.add(toCopy.getStackInSlot(i));
/*     */     }
/* 127 */     return new IItemHandlerModifiable()
/*     */       {
/*     */         public void setStackInSlot(int slot, @NotNull ItemStack stack) {}
/*     */ 
/*     */ 
/*     */         
/*     */         public int getSlots() {
/* 134 */           return list.size();
/*     */         }
/*     */ 
/*     */         
/*     */         @NotNull
/*     */         public ItemStack getStackInSlot(int slot) {
/* 140 */           return list.get(slot);
/*     */         }
/*     */ 
/*     */         
/*     */         @NotNull
/*     */         public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
/* 146 */           return stack;
/*     */         }
/*     */ 
/*     */         
/*     */         @NotNull
/*     */         public ItemStack extractItem(int slot, int amount, boolean simulate) {
/* 152 */           return ItemStack.f_41583_;
/*     */         }
/*     */ 
/*     */         
/*     */         public int getSlotLimit(int slot) {
/* 157 */           return getStackInSlot(slot).m_41741_();
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean isItemValid(int slot, @NotNull ItemStack stack) {
/* 162 */           return true;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static boolean isOre(BlockState state) {
/* 168 */     return state.m_204336_(Tags.Blocks.ORES);
/*     */   }
/*     */   
/*     */   public static boolean isRepairableDamagedItem(ItemStack stack) {
/* 172 */     return (stack.m_41763_() && stack.isRepairable() && stack.m_41773_() > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int simulateFit(NonNullList<ItemStack> inv, ItemStack stack) {
/* 179 */     int remainder = stack.m_41613_();
/* 180 */     for (ItemStack invStack : inv) {
/* 181 */       if (invStack.m_41619_())
/*     */       {
/* 183 */         return 0;
/*     */       }
/* 185 */       if (ItemHandlerHelper.canItemStacksStack(stack, invStack)) {
/* 186 */         int amountSlotNeeds = invStack.m_41741_() - invStack.m_41613_();
/*     */         
/* 188 */         if (amountSlotNeeds > 0) {
/* 189 */           if (remainder <= amountSlotNeeds)
/*     */           {
/* 191 */             return 0;
/*     */           }
/*     */           
/* 194 */           remainder -= amountSlotNeeds;
/*     */         } 
/*     */       } 
/*     */     } 
/* 198 */     return remainder;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static CompoundTag recombineNBT(List<CompoundTag> pieces) {
/* 203 */     if (pieces.isEmpty()) {
/* 204 */       return null;
/*     */     }
/* 206 */     CompoundTag combinedNBT = pieces.get(0);
/* 207 */     for (int i = 1; i < pieces.size(); i++) {
/* 208 */       combinedNBT = combinedNBT.m_128391_(pieces.get(i));
/*     */     }
/* 210 */     return combinedNBT;
/*     */   }
/*     */   
/*     */   public static ItemStack size(ItemStack stack, int size) {
/* 214 */     if (size <= 0 || stack.m_41619_()) {
/* 215 */       return ItemStack.f_41583_;
/*     */     }
/* 217 */     return stack.m_255036_(size);
/*     */   }
/*     */   
/*     */   public static BlockState stackToState(ItemStack stack, @Nullable BlockPlaceContext context) {
/* 221 */     Item item = stack.m_41720_(); if (item instanceof BlockItem) { BlockItem blockItem = (BlockItem)item;
/* 222 */       if (context == null) {
/* 223 */         return blockItem.m_40614_().m_49966_();
/*     */       }
/* 225 */       return blockItem.m_40614_().m_5573_(context); }
/*     */     
/* 227 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\project\\utils\ItemHelper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */