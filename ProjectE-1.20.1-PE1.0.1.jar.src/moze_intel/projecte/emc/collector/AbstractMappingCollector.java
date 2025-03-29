/*    */ package moze_intel.projecte.emc.collector;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import moze_intel.projecte.api.mapper.arithmetic.IValueArithmetic;
/*    */ import moze_intel.projecte.api.mapper.collector.IExtendedMappingCollector;
/*    */ 
/*    */ public abstract class AbstractMappingCollector<T, V extends Comparable<V>, A extends IValueArithmetic<?>>
/*    */   implements IExtendedMappingCollector<T, V, A> {
/*    */   private final A defaultArithmetic;
/*    */   
/*    */   AbstractMappingCollector(A defaultArithmetic) {
/* 13 */     this.defaultArithmetic = defaultArithmetic;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addConversion(int outnumber, T output, Iterable<T> ingredients) {
/* 18 */     addConversion(outnumber, output, listToMapOfCounts(ingredients));
/*    */   }
/*    */ 
/*    */   
/*    */   public void addConversion(int outnumber, T output, Iterable<T> ingredients, A arithmeticForConversion) {
/* 23 */     addConversion(outnumber, output, listToMapOfCounts(ingredients), (IValueArithmetic)arithmeticForConversion);
/*    */   }
/*    */   
/*    */   private Map<T, Integer> listToMapOfCounts(Iterable<T> iterable) {
/* 27 */     Map<T, Integer> map = new HashMap<>();
/* 28 */     for (T ingredient : iterable) {
/* 29 */       map.merge(ingredient, Integer.valueOf(1), Integer::sum);
/*    */     }
/* 31 */     return map;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setValueFromConversion(int outnumber, T something, Iterable<T> ingredients) {
/* 36 */     setValueFromConversion(outnumber, something, listToMapOfCounts(ingredients));
/*    */   }
/*    */ 
/*    */   
/*    */   public abstract void setValueFromConversion(int paramInt, T paramT, Map<T, Integer> paramMap);
/*    */ 
/*    */   
/*    */   public void addConversion(int outnumber, T output, Map<T, Integer> ingredientsWithAmount) {
/* 44 */     addConversion(outnumber, output, ingredientsWithAmount, (IValueArithmetic)getArithmetic());
/*    */   }
/*    */ 
/*    */   
/*    */   public A getArithmetic() {
/* 49 */     return this.defaultArithmetic;
/*    */   }
/*    */   
/*    */   public void finishCollection() {}
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\collector\AbstractMappingCollector.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */