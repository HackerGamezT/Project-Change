/*     */ package moze_intel.projecte.gameObjs.items.tools;
/*     */ 
/*     */ import java.util.function.Consumer;
/*     */ import moze_intel.projecte.api.capabilities.item.IItemCharge;
/*     */ import moze_intel.projecte.capability.ChargeItemCapabilityWrapper;
/*     */ import moze_intel.projecte.capability.ItemCapability;
/*     */ import moze_intel.projecte.capability.ItemCapabilityWrapper;
/*     */ import moze_intel.projecte.gameObjs.EnumMatterType;
/*     */ import moze_intel.projecte.gameObjs.PETags;
/*     */ import moze_intel.projecte.gameObjs.items.IBarHelper;
/*     */ import moze_intel.projecte.utils.ItemHelper;
/*     */ import moze_intel.projecte.utils.ToolHelper;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.tags.BlockTags;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.InteractionResultHolder;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.ShearsItem;
/*     */ import net.minecraft.world.item.Tier;
/*     */ import net.minecraft.world.item.context.UseOnContext;
/*     */ import net.minecraft.world.item.enchantment.Enchantment;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraftforge.common.TierSortingRegistry;
/*     */ import net.minecraftforge.common.capabilities.ICapabilityProvider;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class PEShears extends ShearsItem implements IItemCharge, IBarHelper {
/*     */   private final EnumMatterType matterType;
/*     */   
/*     */   public PEShears(EnumMatterType matterType, int numCharges, Item.Properties props) {
/*  36 */     super(props);
/*  37 */     this.matterType = matterType;
/*  38 */     this.numCharges = numCharges;
/*     */   }
/*     */   private final int numCharges;
/*     */   
/*     */   public boolean m_8120_(@NotNull ItemStack stack) {
/*  43 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
/*  48 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
/*  53 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends net.minecraft.world.entity.LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
/*  58 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_142522_(@NotNull ItemStack stack) {
/*  63 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getWidthForBar(ItemStack stack) {
/*  68 */     return 1.0F - getChargePercent(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_142158_(@NotNull ItemStack stack) {
/*  73 */     return getScaledBarWidth(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_142159_(@NotNull ItemStack stack) {
/*  78 */     return getColorForBar(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public float m_8102_(@NotNull ItemStack stack, @NotNull BlockState state) {
/*  83 */     float speed = super.m_8102_(stack, state);
/*  84 */     if (speed == 1.0F && state.m_204336_(PETags.Blocks.MINEABLE_WITH_PE_SHEARS)) {
/*  85 */       speed = this.matterType.m_6624_();
/*     */     }
/*  87 */     return ToolHelper.getDestroySpeed(speed, this.matterType, getCharge(stack));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumCharges(@NotNull ItemStack stack) {
/*  92 */     return this.numCharges;
/*     */   }
/*     */ 
/*     */   
/*     */   public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
/*  97 */     return (ICapabilityProvider)new ItemCapabilityWrapper(stack, new ItemCapability[] { (ItemCapability)new ChargeItemCapabilityWrapper() });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCorrectToolForDrops(@NotNull ItemStack stack, BlockState state) {
/* 103 */     return (state.m_204336_(PETags.Blocks.MINEABLE_WITH_PE_SHEARS) && TierSortingRegistry.isCorrectTierForDrops((Tier)this.matterType, state));
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResultHolder<ItemStack> m_7203_(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
/* 109 */     return ItemHelper.actionResultFromType(ToolHelper.shearEntityAOE(player, hand, 0L), player.m_21120_(hand));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, Player player) {
/* 115 */     return ToolHelper.shearBlock(stack, pos, player).m_19077_();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResult m_6225_(UseOnContext context) {
/* 121 */     Player player = context.m_43723_();
/* 122 */     if (player != null) {
/* 123 */       Level level = context.m_43725_();
/* 124 */       BlockState state = level.m_8055_(context.m_8083_());
/* 125 */       if (state.m_204336_(BlockTags.f_13035_))
/*     */       {
/* 127 */         ToolHelper.clearTagAOE(level, player, context.m_43724_(), context.m_43722_(), 0L, BlockTags.f_13035_);
/*     */       }
/*     */     } 
/* 130 */     return InteractionResult.PASS;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\tools\PEShears.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */