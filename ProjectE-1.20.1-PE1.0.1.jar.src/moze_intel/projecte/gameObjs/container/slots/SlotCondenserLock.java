/*    */ package moze_intel.projecte.gameObjs.container.slots;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import moze_intel.projecte.api.ItemInfo;
/*    */ import moze_intel.projecte.utils.ItemHelper;
/*    */ import net.minecraft.world.Container;
/*    */ import net.minecraft.world.SimpleContainer;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.inventory.Slot;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class SlotCondenserLock
/*    */   extends Slot
/*    */ {
/* 16 */   private static final Container emptyInventory = (Container)new SimpleContainer(0);
/*    */   
/*    */   private final Supplier<ItemInfo> lockInfo;
/*    */   
/*    */   public SlotCondenserLock(Supplier<ItemInfo> lockInfo, int index, int xPosition, int yPosition) {
/* 21 */     super(emptyInventory, index, xPosition, yPosition);
/* 22 */     this.lockInfo = lockInfo;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_5857_(@NotNull ItemStack stack) {
/* 27 */     if (!stack.m_41619_() && SlotPredicates.HAS_EMC.test(stack)) {
/* 28 */       m_5852_(ItemHelper.getNormalizedStack(stack));
/*    */     }
/* 30 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_8010_(@NotNull Player player) {
/* 35 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int m_6641_() {
/* 40 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack m_7993_() {
/* 46 */     ItemInfo lockInfo = this.lockInfo.get();
/* 47 */     return (lockInfo == null) ? ItemStack.f_41583_ : lockInfo.createStack();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_5852_(@NotNull ItemStack stack) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_40234_(@NotNull ItemStack oldStack, @NotNull ItemStack newStack) {}
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack m_6201_(int amount) {
/* 61 */     return m_7993_();
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\slots\SlotCondenserLock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */