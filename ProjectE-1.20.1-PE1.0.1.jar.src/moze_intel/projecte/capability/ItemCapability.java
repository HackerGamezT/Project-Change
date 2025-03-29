/*    */ package moze_intel.projecte.capability;
/*    */ 
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.util.LazyOptional;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ItemCapability<TYPE>
/*    */ {
/*    */   private ItemCapabilityWrapper wrapper;
/*    */   
/*    */   public void setWrapper(ItemCapabilityWrapper wrapper) {
/* 15 */     if (this.wrapper == null) {
/* 16 */       this.wrapper = wrapper;
/*    */     }
/*    */   }
/*    */   
/*    */   public abstract Capability<TYPE> getCapability();
/*    */   
/*    */   public abstract LazyOptional<TYPE> getLazyCapability();
/*    */   
/*    */   protected ItemStack getStack() {
/* 25 */     return this.wrapper.getItemStack();
/*    */   }
/*    */   
/*    */   protected TYPE getItem() {
/* 29 */     return (TYPE)getStack().m_41720_();
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\capability\ItemCapability.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */