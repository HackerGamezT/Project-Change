/*     */ package moze_intel.projecte.gameObjs.items.rings;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.function.Supplier;
/*     */ import moze_intel.projecte.api.block_entity.IDMPedestal;
/*     */ import moze_intel.projecte.api.capabilities.item.IItemCharge;
/*     */ import moze_intel.projecte.api.capabilities.item.IPedestalItem;
/*     */ import moze_intel.projecte.config.ProjectEConfig;
/*     */ import moze_intel.projecte.gameObjs.items.IBarHelper;
/*     */ import moze_intel.projecte.gameObjs.registries.PESoundEvents;
/*     */ import moze_intel.projecte.integration.IntegrationHelper;
/*     */ import moze_intel.projecte.utils.ItemHelper;
/*     */ import moze_intel.projecte.utils.MathUtils;
/*     */ import moze_intel.projecte.utils.WorldHelper;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResultHolder;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class Zero
/*     */   extends PEToggleItem
/*     */   implements IPedestalItem, IItemCharge, IBarHelper {
/*     */   public Zero(Item.Properties props) {
/*  37 */     super(props);
/*  38 */     addItemCapability(moze_intel.projecte.capability.PedestalItemCapabilityWrapper::new);
/*  39 */     addItemCapability(moze_intel.projecte.capability.ChargeItemCapabilityWrapper::new);
/*  40 */     addItemCapability("curios", IntegrationHelper.CURIO_CAP_SUPPLIER);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasCraftingRemainingItem(ItemStack stack) {
/*  45 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getCraftingRemainingItem(ItemStack stack) {
/*  50 */     return stack.m_41777_();
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_6883_(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int slot, boolean held) {
/*  55 */     super.m_6883_(stack, level, entity, slot, held);
/*  56 */     if (!level.f_46443_ && entity instanceof Player && slot < Inventory.m_36059_() && ItemHelper.checkItemNBT(stack, "Active")) {
/*     */       
/*  58 */       AABB box = new AABB(entity.m_20185_() - 3.0D, entity.m_20186_() - 3.0D, entity.m_20189_() - 3.0D, entity.m_20185_() + 3.0D, entity.m_20186_() + 3.0D, entity.m_20189_() + 3.0D);
/*  59 */       WorldHelper.freezeInBoundingBox(level, box, (Player)entity, true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResultHolder<ItemStack> m_7203_(Level level, Player player, @NotNull InteractionHand hand) {
/*  67 */     ItemStack stack = player.m_21120_(hand);
/*  68 */     if (!level.f_46443_) {
/*  69 */       int offset = 3 + getCharge(stack);
/*  70 */       AABB box = player.m_20191_().m_82400_(offset);
/*  71 */       level.m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)PESoundEvents.POWER.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
/*  72 */       WorldHelper.freezeInBoundingBox(level, box, player, false);
/*     */     } 
/*  74 */     return InteractionResultHolder.m_19090_(stack);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <PEDESTAL extends net.minecraft.world.level.block.entity.BlockEntity & IDMPedestal> boolean updateInPedestal(@NotNull ItemStack stack, @NotNull Level level, @NotNull BlockPos pos, @NotNull PEDESTAL pedestal) {
/*  80 */     if (!level.f_46443_ && ProjectEConfig.server.cooldown.pedestal.zero.get() != -1) {
/*  81 */       if (((IDMPedestal)pedestal).getActivityCooldown() == 0) {
/*  82 */         AABB aabb = ((IDMPedestal)pedestal).getEffectBounds();
/*  83 */         WorldHelper.freezeInBoundingBox(level, aabb, null, false);
/*  84 */         for (Entity ent : level.m_6443_(Entity.class, aabb, e -> (!e.m_5833_() && e.m_6060_()))) {
/*  85 */           ent.m_20095_();
/*     */         }
/*  87 */         ((IDMPedestal)pedestal).setActivityCooldown(ProjectEConfig.server.cooldown.pedestal.zero.get());
/*     */       } else {
/*  89 */         ((IDMPedestal)pedestal).decrementActivityCooldown();
/*     */       } 
/*     */     }
/*  92 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<Component> getPedestalDescription() {
/*  99 */     List<Component> list = new ArrayList<>();
/* 100 */     if (ProjectEConfig.server.cooldown.pedestal.zero.get() != -1) {
/* 101 */       list.add(PELang.PEDESTAL_ZERO_1.translateColored(ChatFormatting.BLUE, new Object[0]));
/* 102 */       list.add(PELang.PEDESTAL_ZERO_2.translateColored(ChatFormatting.BLUE, new Object[0]));
/* 103 */       list.add(PELang.PEDESTAL_ZERO_3.translateColored(ChatFormatting.BLUE, new Object[] { MathUtils.tickToSecFormatted(ProjectEConfig.server.cooldown.pedestal.zero.get()) }));
/*     */     } 
/* 105 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumCharges(@NotNull ItemStack stack) {
/* 110 */     return 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_142522_(@NotNull ItemStack stack) {
/* 115 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getWidthForBar(ItemStack stack) {
/* 120 */     return 1.0F - getChargePercent(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_142158_(@NotNull ItemStack stack) {
/* 125 */     return getScaledBarWidth(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_142159_(@NotNull ItemStack stack) {
/* 130 */     return getColorForBar(stack);
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\rings\Zero.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */