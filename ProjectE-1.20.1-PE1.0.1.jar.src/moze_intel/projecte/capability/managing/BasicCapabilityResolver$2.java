/*    */ package moze_intel.projecte.capability.managing;
/*    */ 
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.ForgeCapabilities;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
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
/*    */   null(IItemHandler constant) {
/* 29 */     super(constant);
/*    */   }
/*    */   @NotNull
/*    */   public Capability<IItemHandler> getMatchingCapability() {
/* 33 */     return ForgeCapabilities.ITEM_HANDLER;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\capability\managing\BasicCapabilityResolver$2.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */