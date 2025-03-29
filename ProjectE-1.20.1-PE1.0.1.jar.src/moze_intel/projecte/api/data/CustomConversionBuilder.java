/*     */ package moze_intel.projecte.api.data;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import javax.annotation.ParametersAreNonnullByDefault;
/*     */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*     */ import net.minecraft.MethodsReturnNonnullByDefault;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ @ParametersAreNonnullByDefault
/*     */ @MethodsReturnNonnullByDefault
/*     */ public class CustomConversionBuilder implements CustomConversionBuilderNSSHelper {
/*     */   private static final long FREE_ARITHMETIC_VALUE = -9223372036854775808L;
/*  23 */   private final Map<String, ConversionGroupBuilder> groups = new LinkedHashMap<>();
/*  24 */   private final Map<NormalizedSimpleStack, Long> fixedValueBefore = new LinkedHashMap<>();
/*  25 */   private final Map<NormalizedSimpleStack, Long> fixedValueAfter = new LinkedHashMap<>();
/*  26 */   private final List<FixedValueConversionBuilder> fixedValueConversions = new ArrayList<>();
/*     */   private final ResourceLocation id;
/*     */   private boolean replace;
/*     */   @Nullable
/*     */   private String comment;
/*     */   
/*     */   CustomConversionBuilder(ResourceLocation id) {
/*  33 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CustomConversionBuilder comment(String comment) {
/*  42 */     validateComment(this.comment, comment, "Custom Conversion");
/*  43 */     this.comment = comment;
/*  44 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CustomConversionBuilder replace() {
/*  51 */     if (this.replace) {
/*  52 */       throw new RuntimeException("Replace has already been set, remove unnecessary call.");
/*     */     }
/*  54 */     this.replace = true;
/*  55 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConversionGroupBuilder group(String groupName) {
/*  64 */     Objects.requireNonNull(groupName, "Group name cannot be null.");
/*  65 */     if (this.groups.containsKey(groupName)) {
/*  66 */       throw new RuntimeException("Group with name '" + groupName + "' already exists.");
/*     */     }
/*  68 */     ConversionGroupBuilder builder = new ConversionGroupBuilder(this);
/*  69 */     this.groups.put(groupName, builder);
/*  70 */     return builder;
/*     */   }
/*     */ 
/*     */   
/*     */   public CustomConversionBuilder before(NormalizedSimpleStack stack, long emc) {
/*  75 */     return fixedValue(stack, emc, this.fixedValueBefore, "before");
/*     */   }
/*     */ 
/*     */   
/*     */   public CustomConversionBuilder before(NormalizedSimpleStack stack) {
/*  80 */     return fixedValue(stack, Long.MIN_VALUE, this.fixedValueBefore, "before");
/*     */   }
/*     */ 
/*     */   
/*     */   public CustomConversionBuilder after(NormalizedSimpleStack stack, long emc) {
/*  85 */     return fixedValue(stack, emc, this.fixedValueAfter, "after");
/*     */   }
/*     */ 
/*     */   
/*     */   public CustomConversionBuilder after(NormalizedSimpleStack stack) {
/*  90 */     return fixedValue(stack, Long.MIN_VALUE, this.fixedValueAfter, "after");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private CustomConversionBuilder fixedValue(NormalizedSimpleStack stack, long emc, Map<NormalizedSimpleStack, Long> fixedValues, String type) {
/*  97 */     Objects.requireNonNull(stack, "Normalized Simple Stack cannot be null.");
/*  98 */     if (emc < 1L && emc != Long.MIN_VALUE)
/*  99 */       throw new IllegalArgumentException("EMC value must be at least one."); 
/* 100 */     if (fixedValues.containsKey(stack)) {
/* 101 */       throw new RuntimeException("Fixed value " + type + " already set for '" + stack + "'.");
/*     */     }
/* 103 */     fixedValues.put(stack, Long.valueOf(emc));
/* 104 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public FixedValueConversionBuilder conversion(NormalizedSimpleStack output, int amount) {
/* 109 */     if (amount < 1) {
/* 110 */       throw new IllegalArgumentException("Output amount for fixed value conversions must be at least one.");
/*     */     }
/* 112 */     FixedValueConversionBuilder builder = new FixedValueConversionBuilder(output, amount);
/* 113 */     this.fixedValueConversions.add(builder);
/* 114 */     return builder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   JsonObject serialize() {
/* 121 */     JsonObject json = new JsonObject();
/* 122 */     if (this.comment != null) {
/* 123 */       json.addProperty("comment", this.comment);
/*     */     }
/* 125 */     if (this.replace) {
/* 126 */       json.addProperty("replace", Boolean.valueOf(true));
/*     */     }
/* 128 */     if (!this.groups.isEmpty()) {
/* 129 */       JsonObject jsonGroups = new JsonObject();
/* 130 */       for (Map.Entry<String, ConversionGroupBuilder> entry : this.groups.entrySet()) {
/* 131 */         String groupName = entry.getKey();
/* 132 */         ConversionGroupBuilder group = entry.getValue();
/* 133 */         JsonObject groupJson = group.serialize();
/* 134 */         validateNonEmpty(groupJson, group.hasComment(), "Group", groupName);
/* 135 */         jsonGroups.add(groupName, (JsonElement)groupJson);
/*     */       } 
/* 137 */       json.add("groups", (JsonElement)jsonGroups);
/*     */     } 
/* 139 */     if (!this.fixedValueBefore.isEmpty() || !this.fixedValueAfter.isEmpty() || !this.fixedValueConversions.isEmpty()) {
/* 140 */       JsonObject fixedValues = new JsonObject();
/* 141 */       if (!this.fixedValueBefore.isEmpty()) {
/* 142 */         fixedValues.add("before", (JsonElement)serializeFixedValues(this.fixedValueBefore));
/*     */       }
/* 144 */       if (!this.fixedValueAfter.isEmpty()) {
/* 145 */         fixedValues.add("after", (JsonElement)serializeFixedValues(this.fixedValueAfter));
/*     */       }
/* 147 */       if (!this.fixedValueConversions.isEmpty()) {
/* 148 */         fixedValues.add("conversion", (JsonElement)serializeConversions((List)this.fixedValueConversions));
/*     */       }
/* 150 */       json.add("values", (JsonElement)fixedValues);
/*     */     } 
/* 152 */     validateNonEmpty(json, (this.comment != null), "Custom conversion", this.id.toString());
/* 153 */     return json;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void validateNonEmpty(JsonObject json, boolean hasComment, String type, String name) {
/* 160 */     int elements = json.size();
/* 161 */     if (elements == 0)
/* 162 */       throw new RuntimeException(type + " '" + type + "' is empty and should be removed."); 
/* 163 */     if (elements == 1 && hasComment) {
/* 164 */       throw new RuntimeException(type + " '" + type + "' consists only of a comment and should be removed.");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static JsonObject serializeFixedValues(Map<NormalizedSimpleStack, Long> fixedValues) {
/* 172 */     JsonObject json = new JsonObject();
/* 173 */     for (Map.Entry<NormalizedSimpleStack, Long> entry : fixedValues.entrySet()) {
/* 174 */       String key = ((NormalizedSimpleStack)entry.getKey()).json();
/* 175 */       long emc = ((Long)entry.getValue()).longValue();
/* 176 */       if (emc == Long.MIN_VALUE) {
/* 177 */         json.addProperty(key, "free"); continue;
/*     */       } 
/* 179 */       json.addProperty(key, Long.valueOf(emc));
/*     */     } 
/*     */     
/* 182 */     return json;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static JsonArray serializeConversions(List<? extends ConversionBuilder<?>> conversions) {
/* 189 */     Set<JsonObject> addedConversions = new HashSet<>();
/* 190 */     JsonArray jsonConversions = new JsonArray();
/* 191 */     for (ConversionBuilder<?> conversion : conversions) {
/* 192 */       JsonObject jsonConversion = conversion.serialize();
/*     */ 
/*     */ 
/*     */       
/* 196 */       if (addedConversions.add(jsonConversion)) {
/* 197 */         jsonConversions.add((JsonElement)jsonConversion); continue;
/*     */       } 
/* 199 */       throw new RuntimeException("Duplicate conversion: " + conversion + ". This is likely a copy paste error and should be removed.");
/*     */     } 
/*     */     
/* 202 */     return jsonConversions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void validateComment(@Nullable String currentComment, String comment, String location) {
/* 209 */     Objects.requireNonNull(comment, "Comment defaults to null, remove unnecessary call.");
/* 210 */     if (currentComment != null)
/* 211 */       throw new RuntimeException(location + " Builder already has a comment declared."); 
/*     */   }
/*     */   
/*     */   public class FixedValueConversionBuilder
/*     */     extends ConversionBuilder<FixedValueConversionBuilder>
/*     */   {
/*     */     private FixedValueConversionBuilder(NormalizedSimpleStack output, int count) {
/* 218 */       super(output, count);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public CustomConversionBuilder end() {
/* 228 */       validateIngredients();
/* 229 */       return CustomConversionBuilder.this;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\data\CustomConversionBuilder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */