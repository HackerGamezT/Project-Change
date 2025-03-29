/*    */ package moze_intel.projecte.gameObjs.items.blocks;
/*    */ 
/*    */ import java.util.List;
/*    */ import moze_intel.projecte.config.ProjectEConfig;
/*    */ import moze_intel.projecte.gameObjs.EnumRelayTier;
/*    */ import moze_intel.projecte.gameObjs.blocks.Relay;
/*    */ import moze_intel.projecte.utils.Constants;
/*    */ import moze_intel.projecte.utils.text.PELang;
/*    */ import net.minecraft.ChatFormatting;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.item.BlockItem;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.item.TooltipFlag;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class RelayItem
/*    */   extends BlockItem {
/*    */   public RelayItem(Relay block, Item.Properties props) {
/* 23 */     super((Block)block, props);
/* 24 */     this.tier = block.getTier();
/*    */   }
/*    */   private final EnumRelayTier tier;
/*    */   
/*    */   public void m_7373_(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltips, @NotNull TooltipFlag flags) {
/* 29 */     super.m_7373_(stack, level, tooltips, flags);
/* 30 */     if (ProjectEConfig.client.statToolTips.get()) {
/* 31 */       tooltips.add(PELang.EMC_MAX_OUTPUT_RATE.translateColored(ChatFormatting.DARK_PURPLE, new Object[] { ChatFormatting.BLUE, Constants.EMC_FORMATTER.format(this.tier.getChargeRate()) }));
/* 32 */       tooltips.add(PELang.EMC_MAX_STORAGE.translateColored(ChatFormatting.DARK_PURPLE, new Object[] { ChatFormatting.BLUE, Constants.EMC_FORMATTER.format(this.tier.getStorage()) }));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\blocks\RelayItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */