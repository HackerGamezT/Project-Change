/*    */ package moze_intel.projecte.integration.crafttweaker.nss;
/*    */ 
/*    */ import com.blamejared.crafttweaker.api.annotation.ZenRegister;
/*    */ import com.blamejared.crafttweaker_annotations.annotations.Document;
/*    */ import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
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
/*    */ @ZenRegister
/*    */ @Document("mods/ProjectE/NormalizedSimpleStack")
/*    */ @NativeTypeRegistration(value = NormalizedSimpleStack.class, zenCodeName = "mods.projecte.NormalizedSimpleStack")
/*    */ public class CrTExpandNormalizedSimpleStack
/*    */ {
/*    */   @Caster
/*    */   public static String asString(NormalizedSimpleStack internal) {
/* 25 */     return internal.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\crafttweaker\nss\CrTExpandNormalizedSimpleStack.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */