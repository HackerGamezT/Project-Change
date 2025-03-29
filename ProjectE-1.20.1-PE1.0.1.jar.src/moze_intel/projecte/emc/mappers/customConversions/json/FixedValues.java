/*    */ package moze_intel.projecte.emc.mappers.customConversions.json;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*    */ 
/*    */ public class FixedValues {
/*    */   @SerializedName("before")
/* 12 */   public Map<NormalizedSimpleStack, Long> setValueBefore = new HashMap<>();
/*    */   @SerializedName("after")
/* 14 */   public Map<NormalizedSimpleStack, Long> setValueAfter = new HashMap<>();
/*    */   
/* 16 */   public List<CustomConversion> conversion = new ArrayList<>();
/*    */   
/*    */   public void merge(FixedValues other) {
/* 19 */     this.setValueBefore.putAll(other.setValueBefore);
/* 20 */     this.setValueAfter.putAll(other.setValueAfter);
/* 21 */     this.conversion.addAll(other.conversion);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\mappers\customConversions\json\FixedValues.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */