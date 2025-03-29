/*    */ package moze_intel.projecte.gameObjs.container.slots;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import moze_intel.projecte.utils.ItemHelper;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import net.minecraftforge.items.SlotItemHandler;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class SlotGhost
/*    */   extends SlotItemHandler {
/*    */   private final Predicate<ItemStack> validator;
/*    */   
/*    */   public SlotGhost(IItemHandler inv, int slotIndex, int xPos, int yPos, Predicate<ItemStack> validator) {
/* 16 */     super(inv, slotIndex, xPos, yPos);
/* 17 */     this.validator = validator;
/*    */   }
/*    */   
/*    */   public boolean isValid(@NotNull ItemStack stack) {
/* 21 */     return this.validator.test(stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_5857_(@NotNull ItemStack stack) {
/* 26 */     if (!stack.m_41619_() && isValid(stack)) {
/* 27 */       m_5852_(stack);
/*    */     }
/* 29 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initialize(@NotNull ItemStack stack) {
/* 34 */     super.initialize(ItemHelper.getNormalizedStack(stack));
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_5852_(@NotNull ItemStack stack) {
/* 39 */     super.m_5852_(ItemHelper.getNormalizedStack(stack));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_8010_(Player player) {
/* 44 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int m_6641_() {
/* 49 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public int m_5866_(@NotNull ItemStack stack) {
/* 54 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\slots\SlotGhost.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */