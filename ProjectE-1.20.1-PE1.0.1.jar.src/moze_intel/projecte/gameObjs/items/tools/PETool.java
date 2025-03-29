/*     */ package moze_intel.projecte.gameObjs.items.tools;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Supplier;
/*     */ import moze_intel.projecte.api.capabilities.item.IItemCharge;
/*     */ import moze_intel.projecte.capability.ItemCapability;
/*     */ import moze_intel.projecte.capability.ItemCapabilityWrapper;
/*     */ import moze_intel.projecte.gameObjs.EnumMatterType;
/*     */ import moze_intel.projecte.gameObjs.items.IBarHelper;
/*     */ import moze_intel.projecte.utils.ToolHelper;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.tags.TagKey;
/*     */ import net.minecraft.world.item.DiggerItem;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.Tier;
/*     */ import net.minecraft.world.item.enchantment.Enchantment;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraftforge.common.capabilities.ICapabilityProvider;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public abstract class PETool
/*     */   extends DiggerItem implements IItemCharge, IBarHelper {
/*  27 */   private final List<Supplier<ItemCapability<?>>> supportedCapabilities = new ArrayList<>();
/*     */   protected final EnumMatterType matterType;
/*     */   private final int numCharges;
/*     */   
/*     */   public PETool(EnumMatterType matterType, TagKey<Block> blocks, float damage, float attackSpeed, int numCharges, Item.Properties props) {
/*  32 */     super(damage, attackSpeed, (Tier)matterType, blocks, props);
/*  33 */     this.matterType = matterType;
/*  34 */     this.numCharges = numCharges;
/*  35 */     addItemCapability(moze_intel.projecte.capability.ChargeItemCapabilityWrapper::new);
/*     */   }
/*     */   
/*     */   protected void addItemCapability(Supplier<ItemCapability<?>> capabilitySupplier) {
/*  39 */     this.supportedCapabilities.add(capabilitySupplier);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_8120_(@NotNull ItemStack stack) {
/*  44 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
/*  49 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
/*  54 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends net.minecraft.world.entity.LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
/*  59 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_142522_(@NotNull ItemStack stack) {
/*  64 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getWidthForBar(ItemStack stack) {
/*  69 */     return 1.0F - getChargePercent(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_142158_(@NotNull ItemStack stack) {
/*  74 */     return getScaledBarWidth(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_142159_(@NotNull ItemStack stack) {
/*  79 */     return getColorForBar(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public float m_8102_(@NotNull ItemStack stack, @NotNull BlockState state) {
/*  84 */     return ToolHelper.getDestroySpeed(getShortCutDestroySpeed(stack, state), this.matterType, getCharge(stack));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumCharges(@NotNull ItemStack stack) {
/*  89 */     return this.numCharges;
/*     */   }
/*     */ 
/*     */   
/*     */   public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
/*  94 */     if (this.supportedCapabilities.isEmpty()) {
/*  95 */       return super.initCapabilities(stack, nbt);
/*     */     }
/*  97 */     return (ICapabilityProvider)new ItemCapabilityWrapper(stack, this.supportedCapabilities);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getShortCutDestroySpeed(@NotNull ItemStack stack, @NotNull BlockState state) {
/* 106 */     return super.m_8102_(stack, state);
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\tools\PETool.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */