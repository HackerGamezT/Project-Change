/*     */ package moze_intel.projecte.gameObjs.items.tools;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Supplier;
/*     */ import moze_intel.projecte.api.capabilities.item.IItemCharge;
/*     */ import moze_intel.projecte.capability.ChargeItemCapabilityWrapper;
/*     */ import moze_intel.projecte.capability.ItemCapability;
/*     */ import moze_intel.projecte.capability.ItemCapabilityWrapper;
/*     */ import moze_intel.projecte.gameObjs.EnumMatterType;
/*     */ import moze_intel.projecte.gameObjs.items.IBarHelper;
/*     */ import moze_intel.projecte.utils.ToolHelper;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.ShovelItem;
/*     */ import net.minecraft.world.item.Tier;
/*     */ import net.minecraft.world.item.context.UseOnContext;
/*     */ import net.minecraft.world.item.enchantment.Enchantment;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraftforge.common.Tags;
/*     */ import net.minecraftforge.common.capabilities.ICapabilityProvider;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class PEShovel extends ShovelItem implements IItemCharge, IBarHelper {
/*     */   private final EnumMatterType matterType;
/*     */   
/*     */   public PEShovel(EnumMatterType matterType, int numCharges, Item.Properties props) {
/*  34 */     super((Tier)matterType, 2.0F, -3.0F, props);
/*  35 */     this.matterType = matterType;
/*  36 */     this.numCharges = numCharges;
/*     */   }
/*     */   private final int numCharges;
/*     */   
/*     */   public boolean m_8120_(@NotNull ItemStack stack) {
/*  41 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
/*  46 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
/*  51 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends net.minecraft.world.entity.LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
/*  56 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_142522_(@NotNull ItemStack stack) {
/*  61 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getWidthForBar(ItemStack stack) {
/*  66 */     return 1.0F - getChargePercent(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_142158_(@NotNull ItemStack stack) {
/*  71 */     return getScaledBarWidth(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_142159_(@NotNull ItemStack stack) {
/*  76 */     return getColorForBar(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public float m_8102_(@NotNull ItemStack stack, @NotNull BlockState state) {
/*  81 */     return ToolHelper.getDestroySpeed(super.m_8102_(stack, state), this.matterType, getCharge(stack));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumCharges(@NotNull ItemStack stack) {
/*  86 */     return this.numCharges;
/*     */   }
/*     */ 
/*     */   
/*     */   public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
/*  91 */     return (ICapabilityProvider)new ItemCapabilityWrapper(stack, new ItemCapability[] { (ItemCapability)new ChargeItemCapabilityWrapper() });
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResult m_6225_(UseOnContext context) {
/*  97 */     Player player = context.m_43723_();
/*  98 */     if (player == null) {
/*  99 */       return InteractionResult.PASS;
/*     */     }
/* 101 */     InteractionHand hand = context.m_43724_();
/* 102 */     Level level = context.m_43725_();
/* 103 */     BlockPos pos = context.m_8083_();
/* 104 */     Direction sideHit = context.m_43719_();
/* 105 */     ItemStack stack = context.m_43722_();
/* 106 */     BlockState state = level.m_8055_(pos);
/* 107 */     return ToolHelper.performActions(ToolHelper.flattenAOE(context, state, 0L), new Supplier[] {
/*     */ 
/*     */           
/* 110 */           () -> ToolHelper.dowseCampfire(context, state), () -> (state.m_204336_(Tags.Blocks.GRAVEL) || state.m_60734_() == Blocks.f_50129_) ? ToolHelper.tryVeinMine(player, stack, pos, sideHit) : InteractionResult.PASS, () -> ToolHelper.digAOE(level, player, hand, stack, pos, sideHit, false, 0L)
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\tools\PEShovel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */