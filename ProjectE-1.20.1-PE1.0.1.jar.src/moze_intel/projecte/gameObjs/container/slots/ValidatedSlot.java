/*    */ package moze_intel.projecte.gameObjs.container.slots;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class ValidatedSlot
/*    */   extends InventoryContainerSlot
/*    */ {
/*    */   private final Predicate<ItemStack> validator;
/*    */   
/*    */   public ValidatedSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition, Predicate<ItemStack> validator) {
/* 14 */     super(itemHandler, index, xPosition, yPosition);
/* 15 */     this.validator = validator;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_5857_(@NotNull ItemStack stack) {
/* 20 */     return (super.m_5857_(stack) && this.validator.test(stack));
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\slots\ValidatedSlot.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */