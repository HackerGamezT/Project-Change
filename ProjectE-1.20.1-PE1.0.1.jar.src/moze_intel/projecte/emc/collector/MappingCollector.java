/*     */ package moze_intel.projecte.emc.collector;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.stream.Collectors;
/*     */ import moze_intel.projecte.PECore;
/*     */ 
/*     */ public abstract class MappingCollector<T, V extends Comparable<V>, A extends IValueArithmetic<V>> extends AbstractMappingCollector<T, V, A> {
/*     */   private static final boolean DEBUG_GRAPHMAPPER = false;
/*     */   protected final A arithmetic;
/*     */   protected final Map<T, Conversion> overwriteConversion;
/*     */   protected final Map<T, Set<Conversion>> conversionsFor;
/*     */   private final Map<T, Set<Conversion>> usedIn;
/*     */   protected final Map<T, V> fixValueBeforeInherit;
/*     */   protected final Map<T, V> fixValueAfterInherit;
/*     */   
/*  21 */   protected MappingCollector(A arithmetic) { super(arithmetic);
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
/*  35 */     this.overwriteConversion = new HashMap<>();
/*  36 */     this.conversionsFor = new HashMap<>();
/*  37 */     this.usedIn = new HashMap<>();
/*  38 */     this.fixValueBeforeInherit = new HashMap<>();
/*  39 */     this.fixValueAfterInherit = new HashMap<>();
/*     */     this.arithmetic = arithmetic; }
/*     */    protected static void debugFormat(String format, Object... args) {} private Set<Conversion> getConversionsFor(T something) {
/*  42 */     return this.conversionsFor.computeIfAbsent(something, t -> new LinkedHashSet());
/*     */   } protected static void debugPrintln(String s) {
/*     */     debugFormat(s, new Object[0]);
/*     */   } protected Set<Conversion> getUsesFor(T something) {
/*  46 */     return this.usedIn.computeIfAbsent(something, t -> new LinkedHashSet());
/*     */   }
/*     */   
/*     */   private void addConversionToIngredientUsages(Conversion conversion) {
/*  50 */     for (T ingredient : conversion.ingredientsWithAmount.keySet()) {
/*  51 */       Set<Conversion> usesForIngredient = getUsesFor(ingredient);
/*  52 */       usesForIngredient.add(conversion);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addConversion(int outnumber, T output, Map<T, Integer> ingredientsWithAmount, A arithmeticForConversion) {
/*  58 */     if (output == null || ingredientsWithAmount.containsKey(null)) {
/*  59 */       PECore.debugLog("Ignoring Recipe because of invalid ingredient or output: {} -> {}x{}", new Object[] { ingredientsWithAmount, Integer.valueOf(outnumber), output });
/*     */       return;
/*     */     } 
/*  62 */     if (outnumber <= 0) {
/*  63 */       throw new IllegalArgumentException("outnumber has to be > 0!");
/*     */     }
/*     */     
/*  66 */     Conversion conversion = new Conversion(output, outnumber, ingredientsWithAmount, arithmeticForConversion, (V)this.arithmetic.getZero());
/*  67 */     if (!getConversionsFor(output).add(conversion)) {
/*     */       return;
/*     */     }
/*  70 */     addConversionToIngredientUsages(conversion);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValueBefore(T something, V value) {
/*  75 */     if (something == null) {
/*     */       return;
/*     */     }
/*  78 */     if (this.fixValueBeforeInherit.containsKey(something)) {
/*  79 */       PECore.debugLog("Overwriting fixValueBeforeInherit for {} from: {} to {}", new Object[] { something, this.fixValueBeforeInherit.get(something), value });
/*     */     }
/*  81 */     this.fixValueBeforeInherit.put(something, value);
/*  82 */     this.fixValueAfterInherit.remove(something);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValueAfter(T something, V value) {
/*  87 */     if (something == null) {
/*     */       return;
/*     */     }
/*  90 */     if (this.fixValueAfterInherit.containsKey(something)) {
/*  91 */       PECore.debugLog("Overwriting fixValueAfterInherit for {} from: {} to {}", new Object[] { something, this.fixValueAfterInherit.get(something), value });
/*     */     }
/*  93 */     this.fixValueAfterInherit.put(something, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValueFromConversion(int outnumber, T something, Map<T, Integer> ingredientsWithAmount) {
/*  98 */     if (something == null || ingredientsWithAmount.containsKey(null)) {
/*  99 */       PECore.debugLog("Ignoring setValueFromConversion because of invalid ingredient or output: {} -> {}x{}", new Object[] { ingredientsWithAmount, Integer.valueOf(outnumber), something });
/*     */       return;
/*     */     } 
/* 102 */     if (outnumber <= 0) {
/* 103 */       throw new IllegalArgumentException("outnumber has to be > 0!");
/*     */     }
/* 105 */     Conversion conversion = new Conversion(something, outnumber, ingredientsWithAmount, this.arithmetic);
/* 106 */     if (this.overwriteConversion.containsKey(something)) {
/* 107 */       Conversion oldConversion = this.overwriteConversion.get(something);
/* 108 */       PECore.debugLog("Overwriting setValueFromConversion {} with {}", new Object[] { this.overwriteConversion.get(something), conversion });
/* 109 */       for (T ingredient : oldConversion.ingredientsWithAmount.keySet()) {
/* 110 */         getUsesFor(ingredient).remove(oldConversion);
/*     */       }
/*     */     } 
/* 113 */     addConversionToIngredientUsages(conversion);
/* 114 */     this.overwriteConversion.put(something, conversion);
/*     */   }
/*     */ 
/*     */   
/*     */   protected class Conversion
/*     */   {
/*     */     public final T output;
/*     */     public final int outnumber;
/*     */     public final V value;
/*     */     public final Map<T, Integer> ingredientsWithAmount;
/*     */     public final A arithmeticForConversion;
/*     */     
/*     */     Conversion(T output, int outnumber, Map<T, Integer> ingredientsWithAmount, A arithmeticForConversion) {
/* 127 */       this(output, outnumber, ingredientsWithAmount, arithmeticForConversion, (V)MappingCollector.this.arithmetic.getZero());
/*     */     }
/*     */     
/*     */     Conversion(T output, int outnumber, Map<T, Integer> ingredientsWithAmount, A arithmeticForConversion, V value) {
/* 131 */       this.output = output;
/* 132 */       this.outnumber = outnumber;
/* 133 */       if (ingredientsWithAmount == null || ingredientsWithAmount.isEmpty()) {
/* 134 */         this.ingredientsWithAmount = Collections.emptyMap();
/*     */       } else {
/* 136 */         ImmutableMap.Builder<T, Integer> builder = ImmutableMap.builder();
/* 137 */         for (Map.Entry<T, Integer> ingredient : ingredientsWithAmount.entrySet()) {
/* 138 */           Integer amount = ingredient.getValue();
/* 139 */           if (amount == null)
/* 140 */             throw new IllegalArgumentException("ingredient amount value has to be != null"); 
/* 141 */           if (amount.intValue() != 0) {
/* 142 */             builder.put(ingredient.getKey(), amount);
/*     */           }
/*     */         } 
/* 145 */         this.ingredientsWithAmount = (Map<T, Integer>)builder.build();
/*     */       } 
/* 147 */       this.arithmeticForConversion = arithmeticForConversion;
/* 148 */       this.value = value;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 153 */       return "" + this.value + " + " + this.value + " => " + ingredientsToString() + "*" + this.outnumber;
/*     */     }
/*     */     
/*     */     private String ingredientsToString() {
/* 157 */       if (this.ingredientsWithAmount.isEmpty()) {
/* 158 */         return "nothing";
/*     */       }
/* 160 */       return this.ingredientsWithAmount.entrySet().stream().map(e -> "" + e.getValue() + "*" + e.getValue()).collect(Collectors.joining(" + "));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object o) {
/* 165 */       if (o instanceof Conversion) { Conversion other = (Conversion)o; if (Objects.equals(this.output, other.output) && Objects.equals(this.value, other.value) && 
/* 166 */           Objects.equals(this.ingredientsWithAmount, other.ingredientsWithAmount)); }
/*     */       
/*     */       return false;
/*     */     }
/*     */     public int hashCode() {
/* 171 */       return Objects.hash(new Object[] { this.output, this.value, this.ingredientsWithAmount });
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\collector\MappingCollector.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */