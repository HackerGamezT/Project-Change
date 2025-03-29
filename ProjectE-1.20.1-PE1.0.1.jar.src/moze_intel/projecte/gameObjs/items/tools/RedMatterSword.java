/*    */ package moze_intel.projecte.gameObjs.items.tools;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.function.Supplier;
/*    */ import moze_intel.projecte.gameObjs.EnumMatterType;
/*    */ import moze_intel.projecte.gameObjs.items.IItemMode;
/*    */ import moze_intel.projecte.utils.text.ILangEntry;
/*    */ import moze_intel.projecte.utils.text.PELang;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.item.TooltipFlag;
/*    */ import net.minecraft.world.level.Level;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class RedMatterSword extends PESword implements IItemMode {
/*    */   private final ILangEntry[] modeDesc;
/*    */   
/*    */   public RedMatterSword(Item.Properties props) {
/* 21 */     super(EnumMatterType.RED_MATTER, 3, 12, props);
/* 22 */     this.modeDesc = new ILangEntry[] { (ILangEntry)PELang.MODE_RED_SWORD_1, (ILangEntry)PELang.MODE_RED_SWORD_2 };
/* 23 */     addItemCapability(moze_intel.projecte.capability.ModeChangerItemCapabilityWrapper::new);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean slayAll(@NotNull ItemStack stack) {
/* 28 */     return (getMode(stack) == 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public ILangEntry[] getModeLangEntries() {
/* 33 */     return this.modeDesc;
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_7373_(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltips, @NotNull TooltipFlag flags) {
/* 38 */     super.m_7373_(stack, level, tooltips, flags);
/* 39 */     tooltips.add(getToolTip(stack));
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\tools\RedMatterSword.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */