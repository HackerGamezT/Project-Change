/*     */ package moze_intel.projecte.gameObjs.items;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.Supplier;
/*     */ import moze_intel.projecte.api.block_entity.IDMPedestal;
/*     */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*     */ import moze_intel.projecte.api.capabilities.item.IAlchBagItem;
/*     */ import moze_intel.projecte.api.capabilities.item.IAlchChestItem;
/*     */ import moze_intel.projecte.api.capabilities.item.IPedestalItem;
/*     */ import moze_intel.projecte.config.ProjectEConfig;
/*     */ import moze_intel.projecte.gameObjs.block_entities.EmcBlockEntity;
/*     */ import moze_intel.projecte.handlers.InternalTimers;
/*     */ import moze_intel.projecte.integration.IntegrationHelper;
/*     */ import moze_intel.projecte.utils.ItemHelper;
/*     */ import moze_intel.projecte.utils.MathUtils;
/*     */ import moze_intel.projecte.utils.PlayerHelper;
/*     */ import moze_intel.projecte.utils.WorldHelper;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraftforge.common.capabilities.ForgeCapabilities;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class RepairTalisman extends ItemPE implements IAlchBagItem, IAlchChestItem, IPedestalItem {
/*     */   private static final Predicate<ItemStack> CAN_REPAIR_ITEM;
/*     */   
/*     */   static {
/*  40 */     CAN_REPAIR_ITEM = (stack -> (!stack.m_41619_() && !stack.getCapability(PECapabilities.MODE_CHANGER_ITEM_CAPABILITY).isPresent() && ItemHelper.isRepairableDamagedItem(stack)));
/*     */   }
/*     */ 
/*     */   
/*     */   public RepairTalisman(Item.Properties props) {
/*  45 */     super(props);
/*  46 */     addItemCapability(moze_intel.projecte.capability.AlchBagItemCapabilityWrapper::new);
/*  47 */     addItemCapability(moze_intel.projecte.capability.AlchChestItemCapabilityWrapper::new);
/*  48 */     addItemCapability(moze_intel.projecte.capability.PedestalItemCapabilityWrapper::new);
/*  49 */     addItemCapability("curios", IntegrationHelper.CURIO_CAP_SUPPLIER);
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_6883_(@NotNull ItemStack stack, Level level, @NotNull Entity entity, int invSlot, boolean isSelected) {
/*  54 */     if (!level.f_46443_ && entity instanceof Player) { Player player = (Player)entity;
/*  55 */       player.getCapability(InternalTimers.CAPABILITY).ifPresent(timers -> {
/*     */             timers.activateRepair();
/*     */             if (timers.canRepair()) {
/*     */               repairAllItems(player);
/*     */             }
/*     */           }); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <PEDESTAL extends net.minecraft.world.level.block.entity.BlockEntity & IDMPedestal> boolean updateInPedestal(@NotNull ItemStack stack, @NotNull Level level, @NotNull BlockPos pos, @NotNull PEDESTAL pedestal) {
/*  67 */     if (!level.f_46443_ && ProjectEConfig.server.cooldown.pedestal.repair.get() != -1) {
/*  68 */       if (((IDMPedestal)pedestal).getActivityCooldown() == 0) {
/*  69 */         level.m_45976_(ServerPlayer.class, ((IDMPedestal)pedestal).getEffectBounds()).forEach(RepairTalisman::repairAllItems);
/*  70 */         ((IDMPedestal)pedestal).setActivityCooldown(ProjectEConfig.server.cooldown.pedestal.repair.get());
/*     */       } else {
/*  72 */         ((IDMPedestal)pedestal).decrementActivityCooldown();
/*     */       } 
/*     */     }
/*  75 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<Component> getPedestalDescription() {
/*  81 */     List<Component> list = new ArrayList<>();
/*  82 */     if (ProjectEConfig.server.cooldown.pedestal.repair.get() != -1) {
/*  83 */       list.add(PELang.PEDESTAL_REPAIR_TALISMAN_1.translateColored(ChatFormatting.BLUE, new Object[0]));
/*  84 */       list.add(PELang.PEDESTAL_REPAIR_TALISMAN_2.translateColored(ChatFormatting.BLUE, new Object[] { MathUtils.tickToSecFormatted(ProjectEConfig.server.cooldown.pedestal.repair.get()) }));
/*     */     } 
/*  86 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean updateInAlchChest(@NotNull Level level, @NotNull BlockPos pos, @NotNull ItemStack stack) {
/*  91 */     if (!level.f_46443_) {
/*  92 */       EmcBlockEntity chest = (EmcBlockEntity)WorldHelper.getBlockEntity(EmcBlockEntity.class, (BlockGetter)level, pos, true);
/*  93 */       if (chest != null) {
/*  94 */         CompoundTag nbt = stack.m_41784_();
/*  95 */         byte coolDown = nbt.m_128445_("Cooldown");
/*  96 */         if (coolDown > 0) {
/*  97 */           nbt.m_128344_("Cooldown", (byte)(coolDown - 1));
/*     */         } else {
/*  99 */           chest.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(inv -> {
/*     */                 if (repairAllItems(inv, CAN_REPAIR_ITEM)) {
/*     */                   nbt.m_128344_("Cooldown", (byte)19);
/*     */                   
/*     */                   chest.markDirty(false);
/*     */                 } 
/*     */               });
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 110 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean updateInAlchBag(@NotNull IItemHandler inv, @NotNull Player player, @NotNull ItemStack stack) {
/* 115 */     if ((player.m_9236_()).f_46443_) {
/* 116 */       return false;
/*     */     }
/* 118 */     CompoundTag nbt = stack.m_41784_();
/* 119 */     byte coolDown = nbt.m_128445_("Cooldown");
/* 120 */     if (coolDown > 0) {
/* 121 */       nbt.m_128344_("Cooldown", (byte)(coolDown - 1));
/* 122 */     } else if (repairAllItems(inv, CAN_REPAIR_ITEM)) {
/* 123 */       nbt.m_128344_("Cooldown", (byte)19);
/* 124 */       return true;
/*     */     } 
/* 126 */     return false;
/*     */   }
/*     */   
/*     */   private static void repairAllItems(Player player) {
/* 130 */     Predicate<ItemStack> canRepairPlayerItem = CAN_REPAIR_ITEM.and(stack -> (stack != player.m_21205_() || !player.f_20911_));
/* 131 */     player.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(inv -> repairAllItems(inv, canRepairPlayerItem));
/* 132 */     IItemHandler curios = PlayerHelper.getCurios(player);
/* 133 */     if (curios != null) {
/* 134 */       repairAllItems(curios, canRepairPlayerItem);
/*     */     }
/*     */   }
/*     */   
/*     */   private static boolean repairAllItems(IItemHandler inv, Predicate<ItemStack> canRepairStack) {
/* 139 */     boolean hasAction = false;
/* 140 */     for (int i = 0; i < inv.getSlots(); i++) {
/* 141 */       ItemStack invStack = inv.getStackInSlot(i);
/* 142 */       if (canRepairStack.test(invStack)) {
/* 143 */         invStack.m_41721_(invStack.m_41773_() - 1);
/* 144 */         if (!hasAction) {
/* 145 */           hasAction = true;
/*     */         }
/*     */       } 
/*     */     } 
/* 149 */     return hasAction;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\RepairTalisman.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */