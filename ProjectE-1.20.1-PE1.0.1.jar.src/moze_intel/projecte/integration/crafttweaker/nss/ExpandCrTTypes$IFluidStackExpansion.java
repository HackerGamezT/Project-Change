/*    */ package moze_intel.projecte.integration.crafttweaker.nss;
/*    */ 
/*    */ import com.blamejared.crafttweaker.api.annotation.ZenRegister;
/*    */ import com.blamejared.crafttweaker.api.fluid.IFluidStack;
/*    */ import com.blamejared.crafttweaker_annotations.annotations.TypedExpansion;
/*    */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*    */ import org.openzen.zencode.java.ZenCodeType.Caster;
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
/*    */ @ZenRegister
/*    */ @TypedExpansion(IFluidStack.class)
/*    */ public class IFluidStackExpansion
/*    */ {
/*    */   @Caster(implicit = true)
/*    */   public static NormalizedSimpleStack asNormalizedSimpleStack(IFluidStack _this) {
/* 97 */     return CrTNSSResolver.fromFluid(_this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\crafttweaker\nss\ExpandCrTTypes$IFluidStackExpansion.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */