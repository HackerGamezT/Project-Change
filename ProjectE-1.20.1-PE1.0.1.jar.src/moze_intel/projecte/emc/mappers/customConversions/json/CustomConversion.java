/*    */ package moze_intel.projecte.emc.mappers.customConversions.json;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*    */ 
/*    */ public class CustomConversion
/*    */ {
/*  9 */   public int count = 1;
/*    */   public NormalizedSimpleStack output;
/*    */   public Map<NormalizedSimpleStack, Integer> ingredients;
/*    */   public transient boolean propagateTags = false;
/*    */   
/*    */   public static CustomConversion getFor(int count, NormalizedSimpleStack output, Map<NormalizedSimpleStack, Integer> ingredients) {
/* 15 */     CustomConversion conversion = new CustomConversion();
/* 16 */     conversion.count = count;
/* 17 */     conversion.output = output;
/* 18 */     conversion.ingredients = new HashMap<>();
/* 19 */     conversion.ingredients.putAll(ingredients);
/* 20 */     return conversion;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 25 */     return "{" + this.count + " * " + this.output + " = " + this.ingredients.toString() + "}";
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\mappers\customConversions\json\CustomConversion.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */