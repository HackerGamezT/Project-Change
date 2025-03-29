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
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.tags.BlockTags;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.AxeItem;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.Tier;
/*     */ import net.minecraft.world.item.context.UseOnContext;
/*     */ import net.minecraft.world.item.enchantment.Enchantment;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraftforge.common.capabilities.ICapabilityProvider;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class PEAxe extends AxeItem implements IItemCharge, IBarHelper {
/*     */   private final EnumMatterType matterType;
/*     */   
/*     */   public PEAxe(EnumMatterType matterType, int numCharges, Item.Properties props) {
/*  30 */     super((Tier)matterType, 5.0F, -3.0F, props);
/*  31 */     this.matterType = matterType;
/*  32 */     this.numCharges = numCharges;
/*     */   }
/*     */   private final int numCharges;
/*     */   
/*     */   public boolean m_8120_(@NotNull ItemStack stack) {
/*  37 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
/*  42 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
/*  47 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends net.minecraft.world.entity.LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
/*  52 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_142522_(@NotNull ItemStack stack) {
/*  57 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getWidthForBar(ItemStack stack) {
/*  62 */     return 1.0F - getChargePercent(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_142158_(@NotNull ItemStack stack) {
/*  67 */     return getScaledBarWidth(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_142159_(@NotNull ItemStack stack) {
/*  72 */     return getColorForBar(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public float m_8102_(@NotNull ItemStack stack, @NotNull BlockState state) {
/*  77 */     return ToolHelper.getDestroySpeed(super.m_8102_(stack, state), this.matterType, getCharge(stack));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumCharges(@NotNull ItemStack stack) {
/*  82 */     return this.numCharges;
/*     */   }
/*     */ 
/*     */   
/*     */   public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
/*  87 */     return (ICapabilityProvider)new ItemCapabilityWrapper(stack, new ItemCapability[] { (ItemCapability)new ChargeItemCapabilityWrapper() });
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResult m_6225_(UseOnContext context) {
/*  93 */     Player player = context.m_43723_();
/*  94 */     if (player == null) {
/*  95 */       return InteractionResult.PASS;
/*     */     }
/*  97 */     Level level = context.m_43725_();
/*  98 */     BlockState state = level.m_8055_(context.m_8083_());
/*     */ 
/*     */     
/* 101 */     return ToolHelper.performActions(ToolHelper.stripLogsAOE(context, state, 0L), new Supplier[] { () -> ToolHelper.scrapeAOE(context, state, 0L), () -> ToolHelper.waxOffAOE(context, state, 0L), () -> state.m_204336_(BlockTags.f_13106_) ? ToolHelper.clearTagAOE(level, player, context.m_43724_(), context.m_43722_(), 0L, BlockTags.f_13106_) : InteractionResult.PASS });
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\tools\PEAxe.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */