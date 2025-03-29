/*    */ package moze_intel.projecte.gameObjs.items;
/*    */ 
/*    */ import java.util.List;
/*    */ import moze_intel.projecte.utils.text.PELang;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.item.TooltipFlag;
/*    */ import net.minecraft.world.level.Level;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class Tome extends ItemPE {
/*    */   public Tome(Item.Properties props) {
/* 15 */     super(props);
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_7373_(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltips, @NotNull TooltipFlag flags) {
/* 20 */     super.m_7373_(stack, level, tooltips, flags);
/* 21 */     tooltips.add(PELang.TOOLTIP_TOME.translate(new Object[0]));
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\Tome.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */