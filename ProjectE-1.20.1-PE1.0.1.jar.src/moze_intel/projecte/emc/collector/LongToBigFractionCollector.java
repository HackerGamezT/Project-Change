/*    */ package moze_intel.projecte.emc.collector;
/*    */ 
/*    */ import java.util.Map;
/*    */ import moze_intel.projecte.api.mapper.arithmetic.IValueArithmetic;
/*    */ import moze_intel.projecte.api.mapper.collector.IExtendedMappingCollector;
/*    */ import moze_intel.projecte.shaded.org.apache.commons.math3.fraction.BigFraction;
/*    */ 
/*    */ public class LongToBigFractionCollector<T, A extends IValueArithmetic<?>>
/*    */   extends AbstractMappingCollector<T, Long, A> {
/*    */   private final IExtendedMappingCollector<T, BigFraction, A> inner;
/*    */   
/*    */   public LongToBigFractionCollector(IExtendedMappingCollector<T, BigFraction, A> inner) {
/* 13 */     super((A)inner.getArithmetic());
/* 14 */     this.inner = inner;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setValueFromConversion(int outnumber, T something, Map<T, Integer> ingredientsWithAmount) {
/* 19 */     this.inner.setValueFromConversion(outnumber, something, ingredientsWithAmount);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addConversion(int outnumber, T output, Map<T, Integer> ingredientsWithAmount, A arithmeticForConversion) {
/* 24 */     this.inner.addConversion(outnumber, output, ingredientsWithAmount, (IValueArithmetic)arithmeticForConversion);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setValueBefore(T something, Long value) {
/* 29 */     this.inner.setValueBefore(something, (Comparable)new BigFraction(value.longValue()));
/*    */   }
/*    */ 
/*    */   
/*    */   public void setValueAfter(T something, Long value) {
/* 34 */     this.inner.setValueAfter(something, (Comparable)new BigFraction(value.longValue()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\collector\LongToBigFractionCollector.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */