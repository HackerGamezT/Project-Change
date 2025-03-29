/*    */ package moze_intel.projecte.emc;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class IngredientMap<T>
/*    */ {
/*  9 */   private final Map<T, Integer> ingredients = new HashMap<>();
/*    */   
/*    */   public void addIngredient(T thing, int amount) {
/* 12 */     this.ingredients.merge(thing, Integer.valueOf(amount), Integer::sum);
/*    */   }
/*    */   
/*    */   public Map<T, Integer> getMap() {
/* 16 */     return Maps.newHashMap(this.ingredients);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 21 */     return this.ingredients.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\IngredientMap.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */