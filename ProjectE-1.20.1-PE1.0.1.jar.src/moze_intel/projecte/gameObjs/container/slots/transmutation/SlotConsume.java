/*    */ package moze_intel.projecte.gameObjs.container.slots.transmutation;
/*    */ 
/*    */ import java.math.BigInteger;
/*    */ import moze_intel.projecte.gameObjs.container.inventory.TransmutationInventory;
/*    */ import moze_intel.projecte.gameObjs.container.slots.InventoryContainerSlot;
/*    */ import moze_intel.projecte.utils.EMCHelper;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class SlotConsume
/*    */   extends InventoryContainerSlot {
/*    */   private final TransmutationInventory inv;
/*    */   
/*    */   public SlotConsume(TransmutationInventory inv, int index, int x, int y) {
/* 16 */     super((IItemHandler)inv, index, x, y);
/* 17 */     this.inv = inv;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void initialize(@NotNull ItemStack stack) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_5852_(@NotNull ItemStack stack) {
/* 27 */     if (this.inv.isServer() && !stack.m_41619_()) {
/* 28 */       this.inv.handleKnowledge(stack);
/* 29 */       this.inv.addEmc(BigInteger.valueOf(EMCHelper.getEmcSellValue(stack)).multiply(BigInteger.valueOf(stack.m_41613_())));
/* 30 */       m_6654_();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_5857_(@NotNull ItemStack stack) {
/* 36 */     return (EMCHelper.doesItemHaveEmc(stack) || stack.m_41720_() instanceof moze_intel.projecte.gameObjs.items.Tome);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\slots\transmutation\SlotConsume.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */