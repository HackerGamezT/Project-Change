/*    */ package moze_intel.projecte.emc.generator;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import moze_intel.projecte.api.mapper.generator.IValueGenerator;
/*    */ import moze_intel.projecte.shaded.org.apache.commons.math3.fraction.BigFraction;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BigFractionToLongGenerator<T>
/*    */   implements IValueGenerator<T, Long>
/*    */ {
/*    */   private final IValueGenerator<T, BigFraction> inner;
/*    */   
/*    */   public BigFractionToLongGenerator(IValueGenerator<T, BigFraction> inner) {
/* 18 */     this.inner = inner;
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<T, Long> generateValues() {
/* 23 */     Map<T, BigFraction> innerResult = this.inner.generateValues();
/* 24 */     Map<T, Long> myResult = new HashMap<>();
/* 25 */     for (Map.Entry<T, BigFraction> entry : innerResult.entrySet()) {
/* 26 */       BigFraction value = entry.getValue();
/* 27 */       if (value.longValue() > 0L) {
/* 28 */         myResult.put(entry.getKey(), Long.valueOf(value.longValue()));
/*    */       }
/*    */     } 
/* 31 */     return myResult;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\generator\BigFractionToLongGenerator.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */