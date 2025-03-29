/*     */ package moze_intel.projecte.integration.crafttweaker;
/*     */ 
/*     */ import com.blamejared.crafttweaker.api.CraftTweakerAPI;
/*     */ import com.blamejared.crafttweaker.api.action.base.IAction;
/*     */ import com.blamejared.crafttweaker.api.annotation.ZenRegister;
/*     */ import com.blamejared.crafttweaker_annotations.annotations.Document;
/*     */ import java.util.Arrays;
/*     */ import java.util.Map;
/*     */ import java.util.stream.Collectors;
/*     */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*     */ import moze_intel.projecte.integration.crafttweaker.actions.CustomConversionAction;
/*     */ import org.openzen.zencode.java.ZenCodeType.Method;
/*     */ import org.openzen.zencode.java.ZenCodeType.Name;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ZenRegister
/*     */ @Document("mods/ProjectE/CustomConversion")
/*     */ @Name("mods.projecte.CustomConversion")
/*     */ public class CrTCustomConversion
/*     */ {
/*     */   @Method
/*     */   public static void addConversion(NormalizedSimpleStack stack, int amount, Map<NormalizedSimpleStack, Integer> ingredients) {
/*  31 */     addConversion(stack, amount, stack instanceof moze_intel.projecte.api.nss.NSSTag, ingredients);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Method
/*     */   public static void addConversion(NormalizedSimpleStack stack, int amount, boolean propagateTags, Map<NormalizedSimpleStack, Integer> ingredients) {
/*  44 */     if (propagateTags && !(stack instanceof moze_intel.projecte.api.nss.NSSTag)) {
/*  45 */       throw new IllegalArgumentException("Propagate Tags should always be false if the output is not a tag.");
/*     */     }
/*  47 */     CraftTweakerAPI.apply((IAction)new CustomConversionAction(stack, amount, propagateTags, false, ingredients));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Method
/*     */   public static void addConversion(NormalizedSimpleStack stack, int amount, NormalizedSimpleStack... ingredients) {
/*  59 */     addConversion(stack, amount, stack instanceof moze_intel.projecte.api.nss.NSSTag, ingredients);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Method
/*     */   public static void addConversion(NormalizedSimpleStack stack, int amount, boolean propagateTags, NormalizedSimpleStack... ingredients) {
/*  72 */     if (ingredients.length == 0) {
/*  73 */       throw new IllegalArgumentException("No ingredients specified for conversion.");
/*     */     }
/*  75 */     addConversion(stack, amount, propagateTags, (Map<NormalizedSimpleStack, Integer>)Arrays.<NormalizedSimpleStack>stream(ingredients).collect(Collectors.toMap(ingredient -> ingredient, ingredient -> Integer.valueOf(1), Integer::sum)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Method
/*     */   public static void setConversion(NormalizedSimpleStack stack, int amount, Map<NormalizedSimpleStack, Integer> ingredients) {
/*  88 */     setConversion(stack, amount, stack instanceof moze_intel.projecte.api.nss.NSSTag, ingredients);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Method
/*     */   public static void setConversion(NormalizedSimpleStack stack, int amount, boolean propagateTags, Map<NormalizedSimpleStack, Integer> ingredients) {
/* 102 */     if (propagateTags && !(stack instanceof moze_intel.projecte.api.nss.NSSTag)) {
/* 103 */       throw new IllegalArgumentException("Propagate Tags should always be false if the output is not a tag.");
/*     */     }
/* 105 */     CraftTweakerAPI.apply((IAction)new CustomConversionAction(stack, amount, propagateTags, true, ingredients));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Method
/*     */   public static void setConversion(NormalizedSimpleStack stack, int amount, NormalizedSimpleStack... ingredients) {
/* 118 */     setConversion(stack, amount, stack instanceof moze_intel.projecte.api.nss.NSSTag, ingredients);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Method
/*     */   public static void setConversion(NormalizedSimpleStack stack, int amount, boolean propagateTags, NormalizedSimpleStack... ingredients) {
/* 132 */     if (ingredients.length == 0) {
/* 133 */       throw new IllegalArgumentException("No ingredients specified for conversion.");
/*     */     }
/* 135 */     setConversion(stack, amount, propagateTags, (Map<NormalizedSimpleStack, Integer>)Arrays.<NormalizedSimpleStack>stream(ingredients).collect(Collectors.toMap(ingredient -> ingredient, ingredient -> Integer.valueOf(1), Integer::sum)));
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\crafttweaker\CrTCustomConversion.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */