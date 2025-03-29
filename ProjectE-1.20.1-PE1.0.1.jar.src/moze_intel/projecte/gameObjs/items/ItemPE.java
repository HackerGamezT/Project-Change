/*     */ package moze_intel.projecte.gameObjs.items;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.function.Supplier;
/*     */ import moze_intel.projecte.capability.ItemCapability;
/*     */ import moze_intel.projecte.capability.ItemCapabilityWrapper;
/*     */ import moze_intel.projecte.utils.EMCHelper;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.common.capabilities.ICapabilityProvider;
/*     */ import net.minecraftforge.fml.ModList;
/*     */ 
/*     */ 
/*     */ public class ItemPE
/*     */   extends Item
/*     */ {
/*  20 */   private final List<Supplier<ItemCapability<?>>> supportedCapabilities = new ArrayList<>();
/*     */   
/*     */   public ItemPE(Item.Properties props) {
/*  23 */     super(props);
/*     */   }
/*     */   
/*     */   protected void addItemCapability(Supplier<ItemCapability<?>> capabilitySupplier) {
/*  27 */     this.supportedCapabilities.add(capabilitySupplier);
/*     */   }
/*     */   
/*     */   protected void addItemCapability(String modid, Supplier<Supplier<ItemCapability<?>>> capabilitySupplier) {
/*  31 */     if (ModList.get().isLoaded(modid)) {
/*  32 */       this.supportedCapabilities.add(capabilitySupplier.get());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
/*  38 */     if (oldStack.m_41720_() != newStack.m_41720_()) {
/*  39 */       return true;
/*     */     }
/*  41 */     if (oldStack.m_41782_() && newStack.m_41782_()) {
/*  42 */       CompoundTag newTag = newStack.m_41784_();
/*  43 */       CompoundTag oldTag = oldStack.m_41784_();
/*     */       
/*  45 */       boolean diffActive = (oldTag.m_128441_("Active") && newTag.m_128441_("Active") && !oldTag.m_128423_("Active").equals(newTag.m_128423_("Active")));
/*     */       
/*  47 */       boolean diffMode = (oldTag.m_128441_("Mode") && newTag.m_128441_("Mode") && !oldTag.m_128423_("Mode").equals(newTag.m_128423_("Mode")));
/*  48 */       return (diffActive || diffMode);
/*     */     } 
/*  50 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
/*  55 */     if (this.supportedCapabilities.isEmpty()) {
/*  56 */       return super.initCapabilities(stack, nbt);
/*     */     }
/*  58 */     return (ICapabilityProvider)new ItemCapabilityWrapper(stack, this.supportedCapabilities);
/*     */   }
/*     */ 
/*     */   
/*     */   public static long getEmc(ItemStack stack) {
/*  63 */     return stack.m_41782_() ? stack.m_41783_().m_128454_("StoredEMC") : 0L;
/*     */   }
/*     */   
/*     */   public static void setEmc(ItemStack stack, long amount) {
/*  67 */     setEmc(stack.m_41784_(), amount);
/*     */   }
/*     */   
/*     */   public static void setEmc(CompoundTag nbt, long amount) {
/*  71 */     nbt.m_128356_("StoredEMC", amount);
/*     */   }
/*     */   
/*     */   public static void addEmcToStack(ItemStack stack, long amount) {
/*  75 */     if (amount > 0L) {
/*  76 */       setEmc(stack, getEmc(stack) + amount);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void removeEmc(ItemStack stack, long amount) {
/*  81 */     if (amount > 0L) {
/*  82 */       setEmc(stack, Math.max(getEmc(stack) - amount, 0L));
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean consumeFuel(Player player, ItemStack stack, long amount, boolean shouldRemove) {
/*  87 */     if (amount <= 0L) {
/*  88 */       return true;
/*     */     }
/*  90 */     long current = getEmc(stack);
/*  91 */     if (current < amount) {
/*  92 */       long consume = EMCHelper.consumePlayerFuel(player, amount - current);
/*  93 */       if (consume == -1L) {
/*  94 */         return false;
/*     */       }
/*  96 */       addEmcToStack(stack, consume);
/*     */     } 
/*  98 */     if (shouldRemove) {
/*  99 */       removeEmc(stack, amount);
/*     */     }
/* 101 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\ItemPE.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */