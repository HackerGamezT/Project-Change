/*    */ package moze_intel.projecte.utils;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ public final class Comparators {
/*    */   static {
/*  9 */     ITEMSTACK_ASCENDING = ((o1, o2) -> 
/* 10 */       (o1.m_41619_() && o2.m_41619_()) ? 0 : (o1.m_41619_() ? 1 : (o2.m_41619_() ? -1 : ((o1.m_41720_() != o2.m_41720_()) ? (o1.m_41613_() - o2.m_41613_()) : (Item.m_41393_(o1.m_41720_()) - Item.m_41393_(o2.m_41720_()))))));
/*    */   }
/*    */   
/*    */   public static final Comparator<ItemStack> ITEMSTACK_ASCENDING;
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\project\\utils\Comparators.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */