/*    */ package moze_intel.projecte.capability.managing;
/*    */ 
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.ForgeCapabilities;
/*    */ import net.minecraftforge.common.util.NonNullSupplier;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class null
/*    */   extends BasicCapabilityResolver<IItemHandler>
/*    */ {
/*    */   null(NonNullSupplier<IItemHandler> supplier) {
/* 19 */     super(supplier);
/*    */   }
/*    */   @NotNull
/*    */   public Capability<IItemHandler> getMatchingCapability() {
/* 23 */     return ForgeCapabilities.ITEM_HANDLER;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\capability\managing\BasicCapabilityResolver$1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */