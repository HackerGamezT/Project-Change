/*     */ package moze_intel.projecte.gameObjs.items.tools;
/*     */ import java.util.List;
/*     */ import java.util.function.Consumer;
/*     */ import moze_intel.projecte.api.capabilities.item.IItemCharge;
/*     */ import moze_intel.projecte.capability.ChargeItemCapabilityWrapper;
/*     */ import moze_intel.projecte.capability.ItemCapability;
/*     */ import moze_intel.projecte.capability.ItemCapabilityWrapper;
/*     */ import moze_intel.projecte.capability.ModeChangerItemCapabilityWrapper;
/*     */ import moze_intel.projecte.config.ProjectEConfig;
/*     */ import moze_intel.projecte.gameObjs.EnumMatterType;
/*     */ import moze_intel.projecte.gameObjs.items.IBarHelper;
/*     */ import moze_intel.projecte.gameObjs.items.IItemMode;
/*     */ import moze_intel.projecte.utils.ItemHelper;
/*     */ import moze_intel.projecte.utils.ToolHelper;
/*     */ import moze_intel.projecte.utils.text.ILangEntry;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.InteractionResultHolder;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.PickaxeItem;
/*     */ import net.minecraft.world.item.Tier;
/*     */ import net.minecraft.world.item.TooltipFlag;
/*     */ import net.minecraft.world.item.context.UseOnContext;
/*     */ import net.minecraft.world.item.enchantment.Enchantment;
/*     */ import net.minecraft.world.level.ClipContext;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.HitResult;
/*     */ import net.minecraftforge.common.capabilities.ICapabilityProvider;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class PEPickaxe extends PickaxeItem implements IItemCharge, IItemMode, IBarHelper {
/*     */   private final EnumMatterType matterType;
/*     */   
/*     */   public PEPickaxe(EnumMatterType matterType, int numCharges, Item.Properties props) {
/*  44 */     super((Tier)matterType, 4, -2.8F, props);
/*  45 */     this.modeDesc = new ILangEntry[] { (ILangEntry)PELang.MODE_PICK_1, (ILangEntry)PELang.MODE_PICK_2, (ILangEntry)PELang.MODE_PICK_3, (ILangEntry)PELang.MODE_PICK_4 };
/*  46 */     this.matterType = matterType;
/*  47 */     this.numCharges = numCharges;
/*     */   }
/*     */   private final ILangEntry[] modeDesc; private final int numCharges;
/*     */   
/*     */   public boolean m_8120_(@NotNull ItemStack stack) {
/*  52 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
/*  57 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
/*  62 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
/*  67 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_142522_(@NotNull ItemStack stack) {
/*  72 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getWidthForBar(ItemStack stack) {
/*  77 */     return 1.0F - getChargePercent(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_142158_(@NotNull ItemStack stack) {
/*  82 */     return getScaledBarWidth(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_142159_(@NotNull ItemStack stack) {
/*  87 */     return getColorForBar(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public float m_8102_(@NotNull ItemStack stack, @NotNull BlockState state) {
/*  92 */     if (ToolHelper.canMatterMine(this.matterType, state.m_60734_())) {
/*  93 */       return 1200000.0F;
/*     */     }
/*  95 */     return ToolHelper.getDestroySpeed(super.m_8102_(stack, state), this.matterType, getCharge(stack));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumCharges(@NotNull ItemStack stack) {
/* 100 */     return this.numCharges;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILangEntry[] getModeLangEntries() {
/* 105 */     return this.modeDesc;
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_7373_(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltips, @NotNull TooltipFlag flags) {
/* 110 */     super.m_7373_(stack, level, tooltips, flags);
/* 111 */     tooltips.add(getToolTip(stack));
/*     */   }
/*     */ 
/*     */   
/*     */   public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
/* 116 */     return (ICapabilityProvider)new ItemCapabilityWrapper(stack, new ItemCapability[] { (ItemCapability)new ChargeItemCapabilityWrapper(), (ItemCapability)new ModeChangerItemCapabilityWrapper() });
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResultHolder<ItemStack> m_7203_(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
/* 122 */     ItemStack stack = player.m_21120_(hand);
/* 123 */     if (ProjectEConfig.server.items.pickaxeAoeVeinMining.get())
/*     */     {
/* 125 */       return ItemHelper.actionResultFromType(ToolHelper.mineOreVeinsInAOE(player, hand), stack);
/*     */     }
/* 127 */     return InteractionResultHolder.m_19098_(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResult m_6225_(UseOnContext context) {
/* 133 */     Player player = context.m_43723_();
/* 134 */     if (player == null || ProjectEConfig.server.items.pickaxeAoeVeinMining.get())
/*     */     {
/*     */       
/* 137 */       return InteractionResult.PASS;
/*     */     }
/* 139 */     BlockPos pos = context.m_8083_();
/* 140 */     if (ItemHelper.isOre(context.m_43725_().m_8055_(pos))) {
/* 141 */       return ToolHelper.tryVeinMine(player, context.m_43722_(), pos, context.m_43719_());
/*     */     }
/* 143 */     return InteractionResult.PASS;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_6813_(@NotNull ItemStack stack, @NotNull Level level, @NotNull BlockState state, @NotNull BlockPos pos, @NotNull LivingEntity living) {
/* 148 */     ToolHelper.digBasedOnMode(stack, level, pos, living, (x$0, x$1, x$2) -> Item.m_41435_(x$0, x$1, x$2));
/* 149 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\tools\PEPickaxe.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */