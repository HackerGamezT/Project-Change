/*     */ package moze_intel.projecte.emc.collector;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.stream.Collectors;
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
/*     */ public class Conversion
/*     */ {
/*     */   public final T output;
/*     */   public final int outnumber;
/*     */   public final V value;
/*     */   public final Map<T, Integer> ingredientsWithAmount;
/*     */   public final A arithmeticForConversion;
/*     */   
/*     */   Conversion(MappingCollector this$0, T output, int outnumber, Map<T, Integer> ingredientsWithAmount, A arithmeticForConversion) {
/* 127 */     this(output, outnumber, ingredientsWithAmount, arithmeticForConversion, (V)MappingCollector.this.arithmetic.getZero());
/*     */   }
/*     */   
/*     */   Conversion(T output, int outnumber, Map<T, Integer> ingredientsWithAmount, A arithmeticForConversion, V value) {
/* 131 */     this.output = output;
/* 132 */     this.outnumber = outnumber;
/* 133 */     if (ingredientsWithAmount == null || ingredientsWithAmount.isEmpty()) {
/* 134 */       this.ingredientsWithAmount = Collections.emptyMap();
/*     */     } else {
/* 136 */       ImmutableMap.Builder<T, Integer> builder = ImmutableMap.builder();
/* 137 */       for (Map.Entry<T, Integer> ingredient : ingredientsWithAmount.entrySet()) {
/* 138 */         Integer amount = ingredient.getValue();
/* 139 */         if (amount == null)
/* 140 */           throw new IllegalArgumentException("ingredient amount value has to be != null"); 
/* 141 */         if (amount.intValue() != 0) {
/* 142 */           builder.put(ingredient.getKey(), amount);
/*     */         }
/*     */       } 
/* 145 */       this.ingredientsWithAmount = (Map<T, Integer>)builder.build();
/*     */     } 
/* 147 */     this.arithmeticForConversion = arithmeticForConversion;
/* 148 */     this.value = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 153 */     return "" + this.value + " + " + this.value + " => " + ingredientsToString() + "*" + this.outnumber;
/*     */   }
/*     */   
/*     */   private String ingredientsToString() {
/* 157 */     if (this.ingredientsWithAmount.isEmpty()) {
/* 158 */       return "nothing";
/*     */     }
/* 160 */     return this.ingredientsWithAmount.entrySet().stream().map(e -> "" + e.getValue() + "*" + e.getValue()).collect(Collectors.joining(" + "));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 165 */     if (o instanceof Conversion) { Conversion other = (Conversion)o; if (Objects.equals(this.output, other.output) && Objects.equals(this.value, other.value) && 
/* 166 */         Objects.equals(this.ingredientsWithAmount, other.ingredientsWithAmount)); }
/*     */     
/*     */     return false;
/*     */   }
/*     */   public int hashCode() {
/* 171 */     return Objects.hash(new Object[] { this.output, this.value, this.ingredientsWithAmount });
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\collector\MappingCollector$Conversion.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */