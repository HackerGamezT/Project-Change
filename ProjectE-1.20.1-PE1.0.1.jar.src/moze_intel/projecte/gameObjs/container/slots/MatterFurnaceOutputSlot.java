/*    */ package moze_intel.projecte.gameObjs.container.slots;
/*    */ 
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.event.ForgeEventFactory;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class MatterFurnaceOutputSlot
/*    */   extends InventoryContainerSlot
/*    */ {
/*    */   private final Player player;
/*    */   private int removeCount;
/*    */   
/*    */   public MatterFurnaceOutputSlot(Player player, IItemHandler itemHandler, int index, int xPosition, int yPosition) {
/* 16 */     super(itemHandler, index, xPosition, yPosition);
/* 17 */     this.player = player;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_5857_(@NotNull ItemStack stack) {
/* 22 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack m_6201_(int amount) {
/* 28 */     if (m_6657_()) {
/* 29 */       this.removeCount += Math.min(amount, m_7993_().m_41613_());
/*    */     }
/* 31 */     return super.m_6201_(amount);
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_142406_(@NotNull Player player, @NotNull ItemStack stack) {
/* 36 */     m_5845_(stack);
/* 37 */     super.m_142406_(player, stack);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void m_7169_(@NotNull ItemStack stack, int pAmount) {
/* 42 */     this.removeCount += pAmount;
/* 43 */     m_5845_(stack);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void m_5845_(ItemStack stack) {
/* 48 */     stack.m_41678_(this.player.m_9236_(), this.player, this.removeCount);
/* 49 */     this.removeCount = 0;
/* 50 */     ForgeEventFactory.firePlayerSmeltedEvent(this.player, stack);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\slots\MatterFurnaceOutputSlot.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */