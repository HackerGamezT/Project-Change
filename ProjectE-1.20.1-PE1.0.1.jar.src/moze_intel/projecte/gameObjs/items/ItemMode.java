/*    */ package moze_intel.projecte.gameObjs.items;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.function.Supplier;
/*    */ import moze_intel.projecte.api.capabilities.item.IItemCharge;
/*    */ import moze_intel.projecte.utils.text.ILangEntry;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.item.TooltipFlag;
/*    */ import net.minecraft.world.level.Level;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public abstract class ItemMode
/*    */   extends ItemPE implements IItemMode, IItemCharge, IBarHelper {
/*    */   private final int numCharge;
/*    */   private final ILangEntry[] modes;
/*    */   
/*    */   public ItemMode(Item.Properties props, int numCharge, ILangEntry... modeDescrp) {
/* 21 */     super(props);
/* 22 */     this.numCharge = numCharge;
/* 23 */     this.modes = modeDescrp;
/* 24 */     addItemCapability(moze_intel.projecte.capability.ChargeItemCapabilityWrapper::new);
/* 25 */     addItemCapability(moze_intel.projecte.capability.ModeChangerItemCapabilityWrapper::new);
/*    */   }
/*    */ 
/*    */   
/*    */   public ILangEntry[] getModeLangEntries() {
/* 30 */     return this.modes;
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_7373_(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltips, @NotNull TooltipFlag flags) {
/* 35 */     super.m_7373_(stack, level, tooltips, flags);
/* 36 */     tooltips.add(getToolTip(stack));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_142522_(@NotNull ItemStack stack) {
/* 41 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getWidthForBar(ItemStack stack) {
/* 46 */     return 1.0F - getChargePercent(stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public int m_142158_(@NotNull ItemStack stack) {
/* 51 */     return getScaledBarWidth(stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public int m_142159_(@NotNull ItemStack stack) {
/* 56 */     return getColorForBar(stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNumCharges(@NotNull ItemStack stack) {
/* 61 */     return this.numCharge;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\ItemMode.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */