/*    */ package moze_intel.projecte.capability;
/*    */ 
/*    */ import net.minecraftforge.common.util.LazyOptional;
/*    */ 
/*    */ public abstract class BasicItemCapability<TYPE>
/*    */   extends ItemCapability<TYPE> {
/*  7 */   private final LazyOptional<TYPE> capability = LazyOptional.of(() -> this);
/*    */ 
/*    */   
/*    */   public LazyOptional<TYPE> getLazyCapability() {
/* 11 */     return this.capability;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\capability\BasicItemCapability.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */