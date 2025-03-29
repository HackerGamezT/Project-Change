/*    */ package moze_intel.projecte.gameObjs.items.tools;
/*    */ 
/*    */ import java.util.function.Consumer;
/*    */ import moze_intel.projecte.api.capabilities.item.IItemCharge;
/*    */ import moze_intel.projecte.capability.ChargeItemCapabilityWrapper;
/*    */ import moze_intel.projecte.capability.ItemCapability;
/*    */ import moze_intel.projecte.capability.ItemCapabilityWrapper;
/*    */ import moze_intel.projecte.gameObjs.EnumMatterType;
/*    */ import moze_intel.projecte.gameObjs.items.IBarHelper;
/*    */ import moze_intel.projecte.utils.ToolHelper;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.world.InteractionResult;
/*    */ import net.minecraft.world.item.HoeItem;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.item.Tier;
/*    */ import net.minecraft.world.item.context.UseOnContext;
/*    */ import net.minecraft.world.item.enchantment.Enchantment;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraftforge.common.capabilities.ICapabilityProvider;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PEHoe extends HoeItem implements IItemCharge, IBarHelper {
/*    */   private final EnumMatterType matterType;
/*    */   
/*    */   public PEHoe(EnumMatterType matterType, int numCharges, Item.Properties props) {
/* 27 */     super((Tier)matterType, (int)-matterType.m_6631_(), matterType.getMatterTier(), props);
/* 28 */     this.matterType = matterType;
/* 29 */     this.numCharges = numCharges;
/*    */   }
/*    */   private final int numCharges;
/*    */   
/*    */   public boolean m_8120_(@NotNull ItemStack stack) {
/* 34 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
/* 39 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
/* 44 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public <T extends net.minecraft.world.entity.LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
/* 49 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_142522_(@NotNull ItemStack stack) {
/* 54 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getWidthForBar(ItemStack stack) {
/* 59 */     return 1.0F - getChargePercent(stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public int m_142158_(@NotNull ItemStack stack) {
/* 64 */     return getScaledBarWidth(stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public int m_142159_(@NotNull ItemStack stack) {
/* 69 */     return getColorForBar(stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public float m_8102_(@NotNull ItemStack stack, @NotNull BlockState state) {
/* 74 */     return ToolHelper.getDestroySpeed(super.m_8102_(stack, state), this.matterType, getCharge(stack));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNumCharges(@NotNull ItemStack stack) {
/* 79 */     return this.numCharges;
/*    */   }
/*    */ 
/*    */   
/*    */   public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
/* 84 */     return (ICapabilityProvider)new ItemCapabilityWrapper(stack, new ItemCapability[] { (ItemCapability)new ChargeItemCapabilityWrapper() });
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public InteractionResult m_6225_(@NotNull UseOnContext context) {
/* 90 */     return ToolHelper.tillAOE(context, context.m_43725_().m_8055_(context.m_8083_()), 0L);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\tools\PEHoe.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */