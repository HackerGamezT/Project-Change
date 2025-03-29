/*    */ package moze_intel.projecte.integration.crafttweaker;
/*    */ 
/*    */ import com.blamejared.crafttweaker.api.CraftTweakerAPI;
/*    */ import com.blamejared.crafttweaker.api.action.base.IAction;
/*    */ import com.blamejared.crafttweaker.api.annotation.ZenRegister;
/*    */ import com.blamejared.crafttweaker_annotations.annotations.Document;
/*    */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*    */ import moze_intel.projecte.integration.crafttweaker.actions.CustomEMCAction;
/*    */ import org.openzen.zencode.java.ZenCodeType.Method;
/*    */ import org.openzen.zencode.java.ZenCodeType.Name;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @ZenRegister
/*    */ @Document("mods/ProjectE/CustomEMC")
/*    */ @Name("mods.projecte.CustomEMC")
/*    */ public class CrTCustomEMC
/*    */ {
/*    */   @Method
/*    */   public static void setEMCValue(NormalizedSimpleStack stack, long emc) {
/* 26 */     if (emc < 0L) {
/* 27 */       throw new IllegalArgumentException("EMC cannot be set to a negative number. Was set to: " + emc);
/*    */     }
/* 29 */     CraftTweakerAPI.apply((IAction)new CustomEMCAction(stack, emc));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Method
/*    */   public static void removeEMCValue(NormalizedSimpleStack stack) {
/* 41 */     CraftTweakerAPI.apply((IAction)new CustomEMCAction(stack, 0L));
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\crafttweaker\CrTCustomEMC.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */