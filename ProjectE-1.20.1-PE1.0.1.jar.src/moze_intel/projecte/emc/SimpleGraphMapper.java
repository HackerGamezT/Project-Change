/*     */ package moze_intel.projecte.emc;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import moze_intel.projecte.PECore;
/*     */ import moze_intel.projecte.api.mapper.arithmetic.IValueArithmetic;
/*     */ import moze_intel.projecte.api.mapper.generator.IValueGenerator;
/*     */ import moze_intel.projecte.emc.collector.MappingCollector;
/*     */ 
/*     */ public class SimpleGraphMapper<T, V extends Comparable<V>, A extends IValueArithmetic<V>>
/*     */   extends MappingCollector<T, V, A>
/*     */   implements IValueGenerator<T, V> {
/*     */   private static final boolean OVERWRITE_FIXED_VALUES = false;
/*     */   private final V ZERO;
/*     */   private static boolean logFoundExploits = true;
/*     */   
/*     */   public SimpleGraphMapper(A arithmetic) {
/*  19 */     super((IValueArithmetic)arithmetic);
/*  20 */     this.ZERO = (V)arithmetic.getZero();
/*     */   }
/*     */   
/*     */   private static <K, V extends Comparable<V>> boolean hasSmallerOrEqual(Map<K, V> m, K key, V value) {
/*  24 */     return (m.containsKey(key) && ((Comparable<V>)m.get(key)).compareTo(value) <= 0);
/*     */   }
/*     */   
/*     */   private static <K, V extends Comparable<V>> boolean hasSmaller(Map<K, V> m, K key, V value) {
/*  28 */     return (m.containsKey(key) && ((Comparable<V>)m.get(key)).compareTo(value) < 0);
/*     */   }
/*     */   
/*     */   static void setLogFoundExploits(boolean log) {
/*  32 */     logFoundExploits = log;
/*     */   }
/*     */   
/*     */   private static <K, V extends Comparable<V>> boolean updateMapWithMinimum(Map<K, V> m, K key, V value) {
/*  36 */     if (!hasSmaller(m, key, value)) {
/*     */       
/*  38 */       m.put(key, value);
/*  39 */       return true;
/*     */     } 
/*  41 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean canOverride(T something, V value) {
/*  48 */     if (this.fixValueBeforeInherit.containsKey(something)) {
/*  49 */       return (((Comparable<V>)this.fixValueBeforeInherit.get(something)).compareTo(value) == 0);
/*     */     }
/*  51 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<T, V> generateValues() {
/*  56 */     Map<T, V> values = new HashMap<>();
/*     */ 
/*     */     
/*  59 */     Map<T, V> changedValues = new HashMap<>();
/*  60 */     Map<T, Object> reasonForChange = new HashMap<>();
/*     */     
/*  62 */     for (Map.Entry<T, V> entry : (Iterable<Map.Entry<T, V>>)this.fixValueBeforeInherit.entrySet()) {
/*  63 */       changedValues.put(entry.getKey(), entry.getValue());
/*  64 */       reasonForChange.put(entry.getKey(), "fixValueBefore");
/*     */     } 
/*     */     
/*  67 */     while (!changedValues.isEmpty()) {
/*  68 */       while (!changedValues.isEmpty()) {
/*     */         
/*  70 */         Map<T, V> nextChangedValues = new HashMap<>();
/*     */         
/*  72 */         debugPrintln("Loop");
/*  73 */         for (Map.Entry<T, V> entry : changedValues.entrySet()) {
/*  74 */           if (canOverride(entry.getKey(), entry.getValue()) && updateMapWithMinimum(values, entry.getKey(), entry.getValue())) {
/*     */             
/*  76 */             debugFormat("Set Value for {} to {} because {}", new Object[] { entry.getKey(), entry.getValue(), reasonForChange.get(entry.getKey()) });
/*     */             
/*  78 */             for (MappingCollector<T, V, A>.Conversion conversion : getUsesFor(entry.getKey())) {
/*  79 */               if (this.overwriteConversion.containsKey(conversion.output) && this.overwriteConversion.get(conversion.output) != conversion) {
/*     */                 continue;
/*     */               }
/*     */ 
/*     */               
/*  84 */               Comparable<V> comparable = conversion.arithmeticForConversion.div((Comparable)valueForConversion(values, conversion), conversion.outnumber);
/*  85 */               if (comparable.compareTo(this.ZERO) > 0 || conversion.arithmeticForConversion.isFree(comparable))
/*     */               {
/*  87 */                 if (!hasSmallerOrEqual(values, (T)conversion.output, (V)comparable))
/*     */                 {
/*  89 */                   if (updateMapWithMinimum(nextChangedValues, (T)conversion.output, (V)comparable))
/*     */                   {
/*  91 */                     reasonForChange.put((T)conversion.output, entry.getKey());
/*     */                   }
/*     */                 }
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/*  99 */         changedValues = nextChangedValues;
/*     */       } 
/*     */       
/* 102 */       for (Map.Entry<T, Set<MappingCollector<T, V, A>.Conversion>> entry : (Iterable<Map.Entry<T, Set<MappingCollector<T, V, A>.Conversion>>>)this.conversionsFor.entrySet()) {
/* 103 */         Comparable<V> comparable; V minConversionValue = null;
/*     */         
/* 105 */         for (MappingCollector<T, V, A>.Conversion conversion : entry.getValue()) {
/*     */ 
/*     */           
/* 108 */           V ingredientValue = valueForConversion(values, conversion);
/*     */           
/* 110 */           Comparable<V> comparable1 = conversion.arithmeticForConversion.div((Comparable)ingredientValue, conversion.outnumber);
/*     */           
/* 112 */           Comparable comparable2 = (Comparable)values.getOrDefault(entry.getKey(), this.ZERO);
/*     */ 
/*     */           
/* 115 */           if ((comparable1.compareTo(this.ZERO) > 0 || conversion.arithmeticForConversion.isFree(comparable1)) && (
/* 116 */             minConversionValue == null || minConversionValue.compareTo(comparable1) > 0)) {
/* 117 */             comparable = comparable1;
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 122 */           if (this.ZERO.compareTo(ingredientValue) < 0 && comparable1.compareTo((V)comparable2) < 0) {
/* 123 */             if (this.overwriteConversion.containsKey(conversion.output) && this.overwriteConversion.get(conversion.output) != conversion) {
/* 124 */               if (logFoundExploits)
/* 125 */                 PECore.LOGGER.warn("EMC Exploit: \"{}\" ingredient cost: {} value of result: {} setValueFromConversion: {}", new Object[] { conversion, ingredientValue, comparable2, this.overwriteConversion.get(conversion.output) });  continue;
/*     */             } 
/* 127 */             if (canOverride(entry.getKey(), this.ZERO)) {
/* 128 */               debugFormat("Setting {} to 0 because result ({}) > cost ({}): {}", new Object[] { entry.getKey(), comparable2, ingredientValue, conversion });
/* 129 */               changedValues.put((T)conversion.output, this.ZERO);
/* 130 */               reasonForChange.put((T)conversion.output, "exploit recipe"); continue;
/* 131 */             }  if (logFoundExploits) {
/* 132 */               PECore.LOGGER.warn("EMC Exploit: ingredients ({}) cost {} but output value is {}", new Object[] { conversion, ingredientValue, comparable2 });
/*     */             }
/*     */           } 
/*     */         } 
/* 136 */         if (comparable == null || comparable.equals(this.ZERO))
/*     */         {
/* 138 */           if (values.containsKey(entry.getKey()) && !((Comparable)values.get(entry.getKey())).equals(this.ZERO) && canOverride(entry.getKey(), this.ZERO) && !hasSmaller(values, entry.getKey(), this.ZERO)) {
/*     */             
/* 140 */             debugFormat("Removing Value for {} because it does not have any nonzero-conversions anymore.", new Object[] { entry.getKey() });
/* 141 */             changedValues.put(entry.getKey(), this.ZERO);
/* 142 */             reasonForChange.put(entry.getKey(), "all conversions dead");
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/* 147 */     debugPrintln("");
/* 148 */     values.putAll(this.fixValueAfterInherit);
/*     */     
/* 150 */     values.entrySet().removeIf(something -> this.arithmetic.isFree((Comparable)something.getValue()));
/* 151 */     return values;
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
/*     */   private V valueForConversion(Map<T, V> values, MappingCollector<T, V, A>.Conversion conversion) {
/*     */     try {
/* 164 */       return valueForConversionUnsafe(values, conversion);
/* 165 */     } catch (ArithmeticException e) {
/* 166 */       PECore.LOGGER.warn("Could not calculate value for {}: {}", conversion.toString(), e.toString());
/* 167 */       return this.ZERO;
/* 168 */     } catch (Exception e) {
/* 169 */       PECore.LOGGER.warn("Could not calculate value for {}: {}", new Object[] { conversion.toString(), e, e });
/* 170 */       return this.ZERO;
/*     */     } 
/*     */   }
/*     */   
/*     */   private V valueForConversionUnsafe(Map<T, V> values, MappingCollector<T, V, A>.Conversion conversion) {
/* 175 */     Comparable<V> comparable = conversion.value;
/* 176 */     boolean allIngredientsAreFree = true;
/* 177 */     boolean hasPositiveIngredientValues = false;
/* 178 */     for (Map.Entry<T, Integer> entry : (Iterable<Map.Entry<T, Integer>>)conversion.ingredientsWithAmount.entrySet()) {
/* 179 */       if (values.containsKey(entry.getKey())) {
/*     */ 
/*     */         
/* 182 */         Comparable<V> comparable1 = conversion.arithmeticForConversion.mul(((Integer)entry.getValue()).intValue(), (Comparable)values.get(entry.getKey()));
/* 183 */         if (comparable1.compareTo(this.ZERO) != 0) {
/* 184 */           if (!conversion.arithmeticForConversion.isFree(comparable1)) {
/* 185 */             comparable = conversion.arithmeticForConversion.add(comparable, comparable1);
/* 186 */             if (comparable1.compareTo(this.ZERO) > 0 && ((Integer)entry.getValue()).intValue() > 0) {
/* 187 */               hasPositiveIngredientValues = true;
/*     */             }
/* 189 */             allIngredientsAreFree = false;
/*     */           } 
/*     */           continue;
/*     */         } 
/* 193 */         return this.ZERO;
/*     */       } 
/*     */ 
/*     */       
/* 197 */       return this.ZERO;
/*     */     } 
/*     */ 
/*     */     
/* 201 */     if (allIngredientsAreFree || (hasPositiveIngredientValues && comparable.compareTo(this.ZERO) <= 0)) {
/* 202 */       return (V)conversion.arithmeticForConversion.getFree();
/*     */     }
/* 204 */     return (V)comparable;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\SimpleGraphMapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */