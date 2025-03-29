/*    */ package moze_intel.projecte.gameObjs.container.slots.transmutation;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.container.inventory.TransmutationInventory;
/*    */ import moze_intel.projecte.gameObjs.container.slots.InventoryContainerSlot;
/*    */ import moze_intel.projecte.utils.EMCHelper;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class SlotUnlearn
/*    */   extends InventoryContainerSlot {
/*    */   private final TransmutationInventory inv;
/*    */   
/*    */   public SlotUnlearn(TransmutationInventory inv, int index, int x, int y) {
/* 15 */     super((IItemHandler)inv, index, x, y);
/* 16 */     this.inv = inv;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_5857_(@NotNull ItemStack stack) {
/* 21 */     return (!m_6657_() && (EMCHelper.doesItemHaveEmc(stack) || stack.m_41720_() instanceof moze_intel.projecte.gameObjs.items.Tome));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void initialize(@NotNull ItemStack stack) {
/* 27 */     super.initialize(stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_5852_(@NotNull ItemStack stack) {
/* 32 */     if (this.inv.isServer() && !stack.m_41619_()) {
/* 33 */       this.inv.handleUnlearn(stack.m_41777_());
/*    */     }
/* 35 */     super.m_5852_(stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public int m_6641_() {
/* 40 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\slots\transmutation\SlotUnlearn.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */