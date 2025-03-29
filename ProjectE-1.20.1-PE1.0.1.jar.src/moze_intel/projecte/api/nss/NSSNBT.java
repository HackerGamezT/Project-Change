/*    */ package moze_intel.projecte.api.nss;
/*    */ 
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import org.jetbrains.annotations.Nullable;
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
/*    */ public interface NSSNBT
/*    */   extends NormalizedSimpleStack
/*    */ {
/*    */   @Nullable
/*    */   CompoundTag getNBT();
/*    */   
/*    */   default boolean hasNBT() {
/* 25 */     return (getNBT() != null);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\nss\NSSNBT.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */