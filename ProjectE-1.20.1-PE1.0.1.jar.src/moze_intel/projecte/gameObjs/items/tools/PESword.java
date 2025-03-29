/*     */ package moze_intel.projecte.gameObjs.items.tools;
/*     */ 
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Supplier;
/*     */ import moze_intel.projecte.api.capabilities.item.IExtraFunction;
/*     */ import moze_intel.projecte.api.capabilities.item.IItemCharge;
/*     */ import moze_intel.projecte.capability.ItemCapability;
/*     */ import moze_intel.projecte.capability.ItemCapabilityWrapper;
/*     */ import moze_intel.projecte.gameObjs.EnumMatterType;
/*     */ import moze_intel.projecte.gameObjs.PETags;
/*     */ import moze_intel.projecte.gameObjs.items.IBarHelper;
/*     */ import moze_intel.projecte.utils.PlayerHelper;
/*     */ import moze_intel.projecte.utils.ToolHelper;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EquipmentSlot;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.SwordItem;
/*     */ import net.minecraft.world.item.Tier;
/*     */ import net.minecraft.world.item.enchantment.Enchantment;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraftforge.common.TierSortingRegistry;
/*     */ import net.minecraftforge.common.capabilities.ICapabilityProvider;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class PESword
/*     */   extends SwordItem
/*     */   implements IExtraFunction, IItemCharge, IBarHelper {
/*  39 */   private final List<Supplier<ItemCapability<?>>> supportedCapabilities = new ArrayList<>();
/*  40 */   private final ToolHelper.ChargeAttributeCache attributeCache = new ToolHelper.ChargeAttributeCache();
/*     */   private final EnumMatterType matterType;
/*     */   private final int numCharges;
/*     */   
/*     */   public PESword(EnumMatterType matterType, int numCharges, int damage, Item.Properties props) {
/*  45 */     super((Tier)matterType, damage, -2.4F, props);
/*  46 */     this.matterType = matterType;
/*  47 */     this.numCharges = numCharges;
/*  48 */     addItemCapability(moze_intel.projecte.capability.ChargeItemCapabilityWrapper::new);
/*  49 */     addItemCapability(moze_intel.projecte.capability.ExtraFunctionItemCapabilityWrapper::new);
/*     */   }
/*     */   
/*     */   protected void addItemCapability(Supplier<ItemCapability<?>> capabilitySupplier) {
/*  53 */     this.supportedCapabilities.add(capabilitySupplier);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_8120_(@NotNull ItemStack stack) {
/*  58 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
/*  63 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
/*  68 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
/*  73 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_142522_(@NotNull ItemStack stack) {
/*  78 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getWidthForBar(ItemStack stack) {
/*  83 */     return 1.0F - getChargePercent(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_142158_(@NotNull ItemStack stack) {
/*  88 */     return getScaledBarWidth(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_142159_(@NotNull ItemStack stack) {
/*  93 */     return getColorForBar(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public float m_8102_(@NotNull ItemStack stack, @NotNull BlockState state) {
/*  98 */     float speed = super.m_8102_(stack, state);
/*  99 */     if (speed == 1.0F && state.m_204336_(PETags.Blocks.MINEABLE_WITH_PE_SWORD)) {
/* 100 */       speed = this.matterType.m_6624_();
/*     */     }
/* 102 */     return ToolHelper.getDestroySpeed(speed, this.matterType, getCharge(stack));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCorrectToolForDrops(@NotNull ItemStack stack, BlockState state) {
/* 108 */     return (state.m_204336_(PETags.Blocks.MINEABLE_WITH_PE_SWORD) && TierSortingRegistry.isCorrectTierForDrops((Tier)this.matterType, state));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumCharges(@NotNull ItemStack stack) {
/* 113 */     return this.numCharges;
/*     */   }
/*     */ 
/*     */   
/*     */   public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
/* 118 */     if (this.supportedCapabilities.isEmpty()) {
/* 119 */       return super.initCapabilities(stack, nbt);
/*     */     }
/* 121 */     return (ICapabilityProvider)new ItemCapabilityWrapper(stack, this.supportedCapabilities);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_7579_(@NotNull ItemStack stack, @NotNull LivingEntity damaged, @NotNull LivingEntity damager) {
/* 126 */     ToolHelper.attackWithCharge(stack, damaged, damager, 1.0F);
/* 127 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public AABB getSweepHitBox(@NotNull ItemStack stack, @NotNull Player player, @NotNull Entity target) {
/* 133 */     int charge = getCharge(stack);
/* 134 */     return target.m_20191_().m_82377_(charge, charge / 4.0D, charge);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean doExtraFunction(@NotNull ItemStack stack, @NotNull Player player, InteractionHand hand) {
/* 139 */     if (player.m_36403_(0.0F) == 1.0F) {
/* 140 */       ToolHelper.attackAOE(stack, player, slayAll(stack), m_43299_(), 0L, hand);
/* 141 */       PlayerHelper.resetCooldown(player);
/* 142 */       return true;
/*     */     } 
/* 144 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean slayAll(@NotNull ItemStack stack) {
/* 148 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Multimap<Attribute, AttributeModifier> getAttributeModifiers(@NotNull EquipmentSlot slot, ItemStack stack) {
/* 154 */     return this.attributeCache.addChargeAttributeModifier(super.getAttributeModifiers(slot, stack), slot, stack);
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\tools\PESword.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */