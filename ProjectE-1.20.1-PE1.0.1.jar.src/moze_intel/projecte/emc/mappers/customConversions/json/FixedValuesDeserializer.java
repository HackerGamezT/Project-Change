/*    */ package moze_intel.projecte.emc.mappers.customConversions.json;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonPrimitive;
/*    */ import com.google.gson.reflect.TypeToken;
/*    */ import java.lang.reflect.Type;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Locale;
/*    */ import java.util.Map;
/*    */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*    */ 
/*    */ 
/*    */ public class FixedValuesDeserializer
/*    */   implements JsonDeserializer<FixedValues>
/*    */ {
/*    */   public FixedValues deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/* 22 */     FixedValues fixed = new FixedValues();
/* 23 */     JsonObject o = json.getAsJsonObject();
/* 24 */     for (Map.Entry<String, JsonElement> entry : (Iterable<Map.Entry<String, JsonElement>>)o.entrySet()) {
/* 25 */       String key = entry.getKey();
/* 26 */       switch (key) { case "before":
/* 27 */           fixed.setValueBefore = parseSetValueMap(((JsonElement)entry.getValue()).getAsJsonObject(), context); continue;
/* 28 */         case "after": fixed.setValueAfter = parseSetValueMap(((JsonElement)entry.getValue()).getAsJsonObject(), context); continue;
/* 29 */         case "conversion": fixed.conversion = (List<CustomConversion>)context.deserialize((JsonElement)((JsonElement)entry.getValue()).getAsJsonArray(), (new TypeToken<List<CustomConversion>>() {  }).getType()); continue; }
/* 30 */        throw new JsonParseException(String.format("Can not parse \"%s\":%s in fixedValues", new Object[] { key, entry.getValue() }));
/*    */     } 
/*    */     
/* 33 */     return fixed;
/*    */   }
/*    */   
/*    */   private Map<NormalizedSimpleStack, Long> parseSetValueMap(JsonObject o, JsonDeserializationContext context) {
/* 37 */     Map<NormalizedSimpleStack, Long> out = new HashMap<>();
/* 38 */     for (Map.Entry<String, JsonElement> entry : (Iterable<Map.Entry<String, JsonElement>>)o.entrySet()) {
/* 39 */       JsonPrimitive primitive = ((JsonElement)entry.getValue()).getAsJsonPrimitive();
/* 40 */       if (primitive.isNumber()) {
/* 41 */         long value = primitive.getAsLong();
/* 42 */         if (value < 1L) {
/* 43 */           throw new JsonParseException("EMC value must be at least one.");
/*    */         }
/* 45 */         out.put((NormalizedSimpleStack)context.deserialize((JsonElement)new JsonPrimitive(entry.getKey()), NormalizedSimpleStack.class), Long.valueOf(value)); continue;
/*    */       } 
/* 47 */       if (primitive.isString() && 
/* 48 */         primitive.getAsString().toLowerCase(Locale.ROOT).equals("free")) {
/* 49 */         out.put((NormalizedSimpleStack)context.deserialize((JsonElement)new JsonPrimitive(entry.getKey()), NormalizedSimpleStack.class), Long.valueOf(Long.MIN_VALUE));
/*    */         
/*    */         continue;
/*    */       } 
/* 53 */       throw new JsonParseException("Could not parse " + o + " into 'free' or integer.");
/*    */     } 
/* 55 */     return out;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\mappers\customConversions\json\FixedValuesDeserializer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */