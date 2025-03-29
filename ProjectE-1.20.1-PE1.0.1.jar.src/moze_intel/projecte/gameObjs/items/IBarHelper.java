/*    */ package moze_intel.projecte.gameObjs.items;
/*    */ 
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public interface IBarHelper
/*    */ {
/*    */   float getWidthForBar(ItemStack paramItemStack);
/*    */   
/*    */   default int getScaledBarWidth(ItemStack stack) {
/* 11 */     return Math.round(13.0F - 13.0F * getWidthForBar(stack));
/*    */   }
/*    */   
/*    */   default int getColorForBar(ItemStack stack) {
/* 15 */     return Mth.m_14169_(Math.max(0.0F, 1.0F - getWidthForBar(stack)) / 3.0F, 1.0F, 1.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\IBarHelper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */