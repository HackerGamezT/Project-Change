/*     */ package moze_intel.projecte.gameObjs.items;
/*     */ 
/*     */ import com.mojang.logging.LogUtils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.function.Supplier;
/*     */ import moze_intel.projecte.PECore;
/*     */ import moze_intel.projecte.api.capabilities.item.IAlchBagItem;
/*     */ import moze_intel.projecte.api.capabilities.item.IAlchChestItem;
/*     */ import moze_intel.projecte.gameObjs.block_entities.EmcBlockEntity;
/*     */ import moze_intel.projecte.gameObjs.container.EternalDensityContainer;
/*     */ import moze_intel.projecte.gameObjs.container.inventory.EternalDensityInventory;
/*     */ import moze_intel.projecte.gameObjs.registries.PEItems;
/*     */ import moze_intel.projecte.integration.IntegrationHelper;
/*     */ import moze_intel.projecte.utils.ClientKeyHelper;
/*     */ import moze_intel.projecte.utils.EMCHelper;
/*     */ import moze_intel.projecte.utils.ItemHelper;
/*     */ import moze_intel.projecte.utils.PEKeybind;
/*     */ import moze_intel.projecte.utils.WorldHelper;
/*     */ import moze_intel.projecte.utils.text.ILangEntry;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import moze_intel.projecte.utils.text.TextComponentUtil;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.ListTag;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResultHolder;
/*     */ import net.minecraft.world.MenuProvider;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.Items;
/*     */ import net.minecraft.world.item.TooltipFlag;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraftforge.common.capabilities.ForgeCapabilities;
/*     */ import net.minecraftforge.common.util.Lazy;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import net.minecraftforge.items.ItemHandlerHelper;
/*     */ import net.minecraftforge.network.NetworkHooks;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class GemEternalDensity
/*     */   extends ItemPE implements IAlchBagItem, IAlchChestItem, IItemMode {
/*  56 */   private static final ILangEntry[] modes = new ILangEntry[] {
/*     */ 
/*     */ 
/*     */       
/*  60 */       null, null, null, null, PEItems.RED_MATTER
/*  61 */       ::getTranslationKey }; static { Objects.requireNonNull(Items.f_42416_); (new ILangEntry[5])[0] = Items.f_42416_::m_5524_; Objects.requireNonNull(Items.f_42417_); (new ILangEntry[5])[1] = Items.f_42417_::m_5524_; Objects.requireNonNull(Items.f_42415_); (new ILangEntry[5])[2] = Items.f_42415_::m_5524_; Objects.requireNonNull(PEItems.DARK_MATTER); (new ILangEntry[5])[3] = PEItems.DARK_MATTER::getTranslationKey; Objects.requireNonNull(PEItems.RED_MATTER); }
/*     */ 
/*     */   
/*     */   public GemEternalDensity(Item.Properties props) {
/*  65 */     super(props);
/*  66 */     addItemCapability(moze_intel.projecte.capability.AlchBagItemCapabilityWrapper::new);
/*  67 */     addItemCapability(moze_intel.projecte.capability.AlchChestItemCapabilityWrapper::new);
/*  68 */     addItemCapability(moze_intel.projecte.capability.ModeChangerItemCapabilityWrapper::new);
/*  69 */     addItemCapability("curios", IntegrationHelper.CURIO_CAP_SUPPLIER);
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_6883_(@NotNull ItemStack stack, Level level, @NotNull Entity entity, int slot, boolean isHeld) {
/*  74 */     if (!level.f_46443_ && entity instanceof Player) {
/*  75 */       entity.getCapability(ForgeCapabilities.ITEM_HANDLER, Direction.UP).ifPresent(inv -> condense(stack, inv));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean condense(ItemStack gem, IItemHandler inv) {
/*  83 */     if (!gem.m_41784_().m_128471_("Active") || ItemPE.getEmc(gem) == Long.MAX_VALUE) {
/*  84 */       return false;
/*     */     }
/*  86 */     ItemStack target = getTarget(gem);
/*  87 */     long targetEmc = EMCHelper.getEmcValue(target);
/*  88 */     if (targetEmc == 0L)
/*     */     {
/*  90 */       return false;
/*     */     }
/*  92 */     boolean hasChanged = false;
/*  93 */     boolean isWhitelist = ItemHelper.checkItemNBT(gem, "Whitelist");
/*  94 */     List<ItemStack> whitelist = getWhitelist(gem);
/*  95 */     for (int i = 0; i < inv.getSlots(); i++) {
/*  96 */       ItemStack stack = inv.getStackInSlot(i);
/*  97 */       if (stack.m_41619_()) {
/*     */         continue;
/*     */       }
/* 100 */       Lazy<Boolean> filtered = Lazy.of(() -> Boolean.valueOf(whitelist.stream().anyMatch(())));
/* 101 */       if (!stack.m_41753_())
/*     */       {
/* 103 */         if (!isWhitelist || !((Boolean)filtered.get()).booleanValue()) {
/*     */           continue;
/*     */         }
/*     */       }
/*     */       
/* 108 */       long emcValue = EMCHelper.getEmcValue(stack);
/* 109 */       if (emcValue != 0L && emcValue < targetEmc && !inv.extractItem(i, (stack.m_41613_() == 1) ? 1 : (stack.m_41613_() / 2), true).m_41619_())
/*     */       {
/*     */ 
/*     */         
/* 113 */         if (isWhitelist == ((Boolean)filtered.get()).booleanValue()) {
/* 114 */           ItemStack copy = inv.extractItem(i, (stack.m_41613_() == 1) ? 1 : (stack.m_41613_() / 2), false);
/* 115 */           addToList(gem, copy);
/* 116 */           ItemPE.addEmcToStack(gem, EMCHelper.getEmcValue(copy) * copy.m_41613_());
/* 117 */           hasChanged = true;
/*     */           break;
/*     */         }  } 
/*     */       continue;
/*     */     } 
/* 122 */     long value = EMCHelper.getEmcValue(target);
/* 123 */     if (value == 0L) {
/* 124 */       return hasChanged;
/*     */     }
/*     */     
/* 127 */     while (getEmc(gem) >= value) {
/* 128 */       ItemStack remain = ItemHandlerHelper.insertItemStacked(inv, target.m_41777_(), false);
/* 129 */       if (!remain.m_41619_()) {
/* 130 */         return false;
/*     */       }
/* 132 */       ItemPE.removeEmc(gem, value);
/* 133 */       setItems(gem, new ArrayList<>());
/* 134 */       hasChanged = true;
/*     */     } 
/* 136 */     return hasChanged;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResultHolder<ItemStack> m_7203_(Level level, Player player, @NotNull InteractionHand hand) {
/* 142 */     ItemStack stack = player.m_21120_(hand);
/* 143 */     if (!level.f_46443_) {
/* 144 */       if (player.m_36341_()) {
/* 145 */         CompoundTag nbt = stack.m_41784_();
/* 146 */         if (nbt.m_128471_("Active")) {
/* 147 */           List<ItemStack> items = getItems(stack);
/* 148 */           if (!items.isEmpty()) {
/* 149 */             WorldHelper.createLootDrop(items, level, player.m_20185_(), player.m_20186_(), player.m_20189_());
/* 150 */             setItems(stack, new ArrayList<>());
/* 151 */             ItemPE.setEmc(stack, 0L);
/*     */           } 
/* 153 */           nbt.m_128379_("Active", false);
/*     */         } else {
/* 155 */           nbt.m_128379_("Active", true);
/*     */         } 
/*     */       } else {
/* 158 */         NetworkHooks.openScreen((ServerPlayer)player, new ContainerProvider(hand, stack), buf -> {
/*     */               buf.m_130068_((Enum)hand);
/*     */               buf.writeByte((player.m_150109_()).f_35977_);
/*     */             });
/*     */       } 
/*     */     }
/* 164 */     return InteractionResultHolder.m_19090_(stack);
/*     */   }
/*     */   private static ItemStack getTarget(ItemStack stack) {
/*     */     GemEternalDensity gem;
/* 168 */     Item item = stack.m_41720_();
/* 169 */     if (item instanceof GemEternalDensity) { gem = (GemEternalDensity)item; }
/* 170 */     else { PECore.LOGGER.error(LogUtils.FATAL_MARKER, "Invalid gem of eternal density: {}", stack);
/* 171 */       return ItemStack.f_41583_; }
/*     */     
/* 173 */     byte target = gem.getMode(stack);
/* 174 */     switch (target) { case 0: 
/*     */       case 1: 
/*     */       case 2:
/*     */       
/*     */       case 3:
/*     */       
/*     */       case 4:
/* 181 */        }  PECore.LOGGER.error(LogUtils.FATAL_MARKER, "Invalid target for gem of eternal density: {}", Byte.valueOf(target));
/* 182 */     return ItemStack.f_41583_;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void setItems(ItemStack stack, List<ItemStack> list) {
/* 188 */     ListTag tList = new ListTag();
/* 189 */     for (ItemStack s : list) {
/* 190 */       CompoundTag nbt = new CompoundTag();
/* 191 */       s.m_41739_(nbt);
/* 192 */       tList.add(nbt);
/*     */     } 
/* 194 */     stack.m_41784_().m_128365_("Consumed", (Tag)tList);
/*     */   }
/*     */   
/*     */   private static List<ItemStack> getItems(ItemStack stack) {
/* 198 */     List<ItemStack> list = new ArrayList<>();
/* 199 */     if (stack.m_41782_()) {
/* 200 */       ListTag tList = stack.m_41784_().m_128437_("Consumed", 10);
/* 201 */       for (int i = 0; i < tList.size(); i++) {
/* 202 */         list.add(ItemStack.m_41712_(tList.m_128728_(i)));
/*     */       }
/*     */     } 
/* 205 */     return list;
/*     */   }
/*     */   
/*     */   private static void addToList(ItemStack gem, ItemStack stack) {
/* 209 */     List<ItemStack> list = getItems(gem);
/* 210 */     addToList(list, stack);
/* 211 */     setItems(gem, list);
/*     */   }
/*     */   
/*     */   private static void addToList(List<ItemStack> list, ItemStack stack) {
/* 215 */     boolean hasFound = false;
/* 216 */     for (ItemStack s : list) {
/* 217 */       if (s.m_41613_() < s.m_41741_() && ItemHandlerHelper.canItemStacksStack(s, stack)) {
/* 218 */         int remain = s.m_41741_() - s.m_41613_();
/* 219 */         if (stack.m_41613_() <= remain) {
/* 220 */           s.m_41769_(stack.m_41613_());
/* 221 */           hasFound = true;
/*     */           break;
/*     */         } 
/* 224 */         s.m_41769_(remain);
/* 225 */         stack.m_41774_(remain);
/*     */       } 
/*     */     } 
/*     */     
/* 229 */     if (!hasFound) {
/* 230 */       list.add(stack);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public CompoundTag getShareTag(ItemStack stack) {
/* 237 */     if (stack.m_41720_() instanceof GemEternalDensity) {
/*     */       
/* 239 */       CompoundTag nbt = stack.m_41783_();
/* 240 */       if (nbt == null || !nbt.m_128425_("Consumed", 9))
/*     */       {
/* 242 */         return nbt;
/*     */       }
/*     */       
/* 245 */       return ItemHelper.copyNBTSkipKey(nbt, "Consumed");
/*     */     } 
/* 247 */     return super.getShareTag(stack);
/*     */   }
/*     */   
/*     */   private static List<ItemStack> getWhitelist(ItemStack stack) {
/* 251 */     if (stack.m_41782_()) {
/* 252 */       CompoundTag compound = stack.m_41784_().m_128469_("Items");
/* 253 */       ListTag list = compound.m_128437_("Items", 10);
/* 254 */       List<ItemStack> result = new ArrayList<>(list.size());
/* 255 */       for (int i = 0; i < list.size(); i++) {
/* 256 */         ItemStack s = ItemStack.m_41712_(list.m_128728_(i));
/* 257 */         if (!s.m_41619_() && result.stream().noneMatch(r -> ItemHandlerHelper.canItemStacksStack(r, s)))
/*     */         {
/* 259 */           result.add(s);
/*     */         }
/*     */       } 
/* 262 */       return result;
/*     */     } 
/* 264 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILangEntry getModeSwitchEntry() {
/* 269 */     return (ILangEntry)PELang.DENSITY_MODE_TARGET;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILangEntry[] getModeLangEntries() {
/* 274 */     return modes;
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_7373_(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltips, @NotNull TooltipFlag flags) {
/* 279 */     super.m_7373_(stack, level, tooltips, flags);
/* 280 */     tooltips.add(PELang.TOOLTIP_GEM_DENSITY_1.translate(new Object[0]));
/* 281 */     if (stack.m_41782_()) {
/* 282 */       tooltips.add(PELang.TOOLTIP_GEM_DENSITY_2.translate(new Object[] { getModeLangEntry(stack) }));
/*     */     }
/* 284 */     tooltips.add(PELang.TOOLTIP_GEM_DENSITY_3.translate(new Object[] { ClientKeyHelper.getKeyName(PEKeybind.MODE) }));
/* 285 */     tooltips.add(PELang.TOOLTIP_GEM_DENSITY_4.translate(new Object[0]));
/* 286 */     tooltips.add(PELang.TOOLTIP_GEM_DENSITY_5.translate(new Object[0]));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean updateInAlchChest(@NotNull Level level, @NotNull BlockPos pos, @NotNull ItemStack stack) {
/* 291 */     if (!level.f_46443_ && ItemHelper.checkItemNBT(stack, "Active")) {
/* 292 */       EmcBlockEntity chest = (EmcBlockEntity)WorldHelper.getBlockEntity(EmcBlockEntity.class, (BlockGetter)level, pos, true);
/* 293 */       if (chest != null) {
/* 294 */         chest.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(inv -> {
/*     */               if (condense(stack, inv)) {
/*     */                 chest.m_6596_();
/*     */               }
/*     */             });
/*     */       }
/*     */     } 
/* 301 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean updateInAlchBag(@NotNull IItemHandler inv, @NotNull Player player, @NotNull ItemStack stack) {
/* 306 */     return (!(player.m_9236_()).f_46443_ && condense(stack, inv));
/*     */   }
/*     */   private static final class ContainerProvider extends Record implements MenuProvider { private final InteractionHand hand; private final ItemStack stack;
/* 309 */     private ContainerProvider(InteractionHand hand, ItemStack stack) { this.hand = hand; this.stack = stack; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lmoze_intel/projecte/gameObjs/items/GemEternalDensity$ContainerProvider;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #309	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 309 */       //   0	7	0	this	Lmoze_intel/projecte/gameObjs/items/GemEternalDensity$ContainerProvider; } public InteractionHand hand() { return this.hand; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/gameObjs/items/GemEternalDensity$ContainerProvider;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #309	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lmoze_intel/projecte/gameObjs/items/GemEternalDensity$ContainerProvider; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lmoze_intel/projecte/gameObjs/items/GemEternalDensity$ContainerProvider;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #309	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lmoze_intel/projecte/gameObjs/items/GemEternalDensity$ContainerProvider;
/* 309 */       //   0	8	1	o	Ljava/lang/Object; } public ItemStack stack() { return this.stack; }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public AbstractContainerMenu m_7208_(int windowId, @NotNull Inventory playerInventory, @NotNull Player player) {
/* 314 */       return (AbstractContainerMenu)new EternalDensityContainer(windowId, playerInventory, this.hand, playerInventory.f_35977_, new EternalDensityInventory(this.stack));
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Component m_5446_() {
/* 320 */       return (Component)TextComponentUtil.build(new Object[] { PEItems.GEM_OF_ETERNAL_DENSITY.get() });
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\GemEternalDensity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */