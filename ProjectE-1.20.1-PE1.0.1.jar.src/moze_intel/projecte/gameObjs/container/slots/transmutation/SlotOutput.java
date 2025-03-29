/*    */ package moze_intel.projecte.gameObjs.container.slots.transmutation;
/*    */ 
/*    */ import java.math.BigInteger;
/*    */ import moze_intel.projecte.gameObjs.container.inventory.TransmutationInventory;
/*    */ import moze_intel.projecte.gameObjs.container.slots.InventoryContainerSlot;
/*    */ import moze_intel.projecte.utils.EMCHelper;
/*    */ import moze_intel.projecte.utils.ItemHelper;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class SlotOutput extends InventoryContainerSlot {
/*    */   private final TransmutationInventory inv;
/*    */   
/*    */   public SlotOutput(TransmutationInventory inv, int index, int x, int y) {
/* 17 */     super((IItemHandler)inv, index, x, y);
/* 18 */     this.inv = inv;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void m_6405_(int amount) {
/* 23 */     m_6201_(amount);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack m_6201_(int amount) {
/* 29 */     ItemStack stack = ItemHelper.size(m_7993_(), amount);
/* 30 */     BigInteger emcValue = BigInteger.valueOf(EMCHelper.getEmcValue(stack));
/* 31 */     if (amount > 1) {
/* 32 */       emcValue = emcValue.multiply(BigInteger.valueOf(amount));
/*    */     }
/* 34 */     if (emcValue.compareTo(this.inv.getAvailableEmc()) > 0)
/*    */     {
/*    */       
/* 37 */       return ItemStack.f_41583_;
/*    */     }
/* 39 */     if (this.inv.isServer()) {
/* 40 */       this.inv.removeEmc(emcValue);
/*    */     }
/* 42 */     return stack;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void initialize(@NotNull ItemStack stack) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_5852_(@NotNull ItemStack stack) {}
/*    */ 
/*    */   
/*    */   public boolean m_5857_(@NotNull ItemStack stack) {
/* 55 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_8010_(Player player) {
/* 60 */     return (!m_6657_() || BigInteger.valueOf(EMCHelper.getEmcValue(m_7993_())).compareTo(this.inv.getAvailableEmc()) <= 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\slots\transmutation\SlotOutput.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */