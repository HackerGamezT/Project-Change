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
/*    */ import java.util.Map;
/*    */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*    */ import moze_intel.projecte.emc.mappers.customConversions.CustomConversionMapper;
/*    */ 
/*    */ public class CustomConversionDeserializer
/*    */   implements JsonDeserializer<CustomConversion>
/*    */ {
/*    */   public CustomConversion deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/* 20 */     CustomConversion out = new CustomConversion();
/* 21 */     JsonObject o = json.getAsJsonObject();
/* 22 */     boolean foundOutput = false, foundIngredients = false;
/* 23 */     for (Map.Entry<String, JsonElement> entry : (Iterable<Map.Entry<String, JsonElement>>)o.entrySet()) {
/* 24 */       JsonElement element = entry.getValue();
/* 25 */       if ("count".equalsIgnoreCase(entry.getKey())) {
/* 26 */         out.count = element.getAsInt(); continue;
/* 27 */       }  if ("output".equals(entry.getKey())) {
/* 28 */         if (foundOutput) {
/* 29 */           throw new JsonParseException("Multiple values for output field");
/*    */         }
/* 31 */         foundOutput = true;
/* 32 */         out.output = (NormalizedSimpleStack)context.deserialize((JsonElement)new JsonPrimitive(element.getAsString()), NormalizedSimpleStack.class); continue;
/* 33 */       }  if ("ingredients".equals(entry.getKey())) {
/* 34 */         if (foundIngredients) {
/* 35 */           throw new JsonParseException("Multiple values for ingredient field");
/*    */         }
/* 37 */         foundIngredients = true;
/* 38 */         if (element.isJsonArray()) {
/* 39 */           Map<NormalizedSimpleStack, Integer> outMap = new HashMap<>();
/* 40 */           for (JsonElement e : element.getAsJsonArray()) {
/* 41 */             NormalizedSimpleStack v = (NormalizedSimpleStack)context.deserialize((JsonElement)new JsonPrimitive(e.getAsString()), NormalizedSimpleStack.class);
/* 42 */             outMap.merge(v, Integer.valueOf(1), Integer::sum);
/*    */           } 
/* 44 */           out.ingredients = outMap; continue;
/* 45 */         }  if (element.isJsonObject()) {
/* 46 */           out.ingredients = (Map<NormalizedSimpleStack, Integer>)CustomConversionMapper.GSON.fromJson(element, (new TypeToken<Map<NormalizedSimpleStack, Integer>>() {  }).getType()); continue;
/*    */         } 
/* 48 */         throw new JsonParseException("Could not parse ingredients!");
/*    */       } 
/* 50 */       if (((String)entry.getKey()).equalsIgnoreCase("propagateTags")) {
/* 51 */         out.propagateTags = element.getAsBoolean(); continue;
/*    */       } 
/* 53 */       throw new JsonParseException(String.format("Unknown Key: %s in Conversion with value %s", new Object[] { entry.getKey(), element }));
/*    */     } 
/*    */     
/* 56 */     if (!foundOutput)
/* 57 */       throw new JsonParseException("No output declared"); 
/* 58 */     if (!foundIngredients)
/* 59 */       throw new JsonParseException("No ingredients declared"); 
/* 60 */     if (out.count < 1) {
/* 61 */       throw new JsonParseException("Output count must be at least one");
/*    */     }
/* 63 */     return out;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\mappers\customConversions\json\CustomConversionDeserializer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */