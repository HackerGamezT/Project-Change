/*     */ package moze_intel.projecte.api.data;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import moze_intel.projecte.api.nss.NSSTag;
/*     */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*     */ import net.minecraft.MethodsReturnNonnullByDefault;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ @MethodsReturnNonnullByDefault
/*     */ public class ConversionBuilder<BUILDER extends ConversionBuilder<BUILDER>>
/*     */   implements ConversionBuilderNSSHelper<BUILDER>
/*     */ {
/*  18 */   private final Map<NormalizedSimpleStack, Integer> ingredients = new LinkedHashMap<>();
/*     */   private final NormalizedSimpleStack output;
/*     */   private final int outputAmount;
/*     */   private boolean propagateTags;
/*     */   
/*     */   ConversionBuilder(@NotNull NormalizedSimpleStack output, int outputAmount) {
/*  24 */     this.output = output;
/*  25 */     this.outputAmount = outputAmount;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  30 */     return "" + this.output + " " + this.output;
/*     */   }
/*     */ 
/*     */   
/*     */   private BUILDER getThis() {
/*  35 */     return (BUILDER)this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BUILDER propagateTags() {
/*  42 */     if (this.propagateTags)
/*  43 */       throw new RuntimeException("Propagate tags has already been set, remove unnecessary call."); 
/*  44 */     NormalizedSimpleStack normalizedSimpleStack = this.output; if (normalizedSimpleStack instanceof NSSTag) { NSSTag nssTag = (NSSTag)normalizedSimpleStack; if (!nssTag.representsTag())
/*  45 */         throw new RuntimeException("Propagate tags can only be enabled for conversion outputs that are tags.");  }
/*     */     
/*  47 */     this.propagateTags = true;
/*  48 */     return getThis();
/*     */   }
/*     */ 
/*     */   
/*     */   public BUILDER ingredient(@NotNull NormalizedSimpleStack input, int amount) {
/*  53 */     if (this.ingredients.containsKey(input))
/*  54 */       throw new RuntimeException("Conversion already contains ingredient '" + input + "', merge identical ingredients."); 
/*  55 */     if (amount == 0)
/*     */     {
/*  57 */       throw new RuntimeException("Conversion for empty ingredient '" + input + "' should be removed.");
/*     */     }
/*  59 */     this.ingredients.put(input, Integer.valueOf(amount));
/*  60 */     return getThis();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void validateIngredients() {
/*  67 */     if (this.ingredients.isEmpty()) {
/*  68 */       throw new RuntimeException("Conversion does not contain any ingredients.");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   JsonObject serialize() {
/*  77 */     validateIngredients();
/*  78 */     JsonObject json = new JsonObject();
/*  79 */     if (this.propagateTags) {
/*  80 */       json.addProperty("propagateTags", Boolean.valueOf(true));
/*     */     }
/*  82 */     json.addProperty("output", this.output.json());
/*  83 */     if (this.outputAmount != 1) {
/*  84 */       json.addProperty("count", Integer.valueOf(this.outputAmount));
/*     */     }
/*  86 */     if (this.ingredients.values().stream().allMatch(value -> (value.intValue() == 1))) {
/*     */       
/*  88 */       JsonArray jsonIngredients = new JsonArray();
/*  89 */       for (NormalizedSimpleStack stack : this.ingredients.keySet()) {
/*  90 */         jsonIngredients.add(stack.json());
/*     */       }
/*  92 */       json.add("ingredients", (JsonElement)jsonIngredients);
/*     */     } else {
/*     */       
/*  95 */       JsonObject jsonIngredients = new JsonObject();
/*  96 */       for (Map.Entry<NormalizedSimpleStack, Integer> entry : this.ingredients.entrySet()) {
/*  97 */         jsonIngredients.addProperty(((NormalizedSimpleStack)entry.getKey()).json(), entry.getValue());
/*     */       }
/*  99 */       json.add("ingredients", (JsonElement)jsonIngredients);
/*     */     } 
/* 101 */     return json;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\data\ConversionBuilder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */