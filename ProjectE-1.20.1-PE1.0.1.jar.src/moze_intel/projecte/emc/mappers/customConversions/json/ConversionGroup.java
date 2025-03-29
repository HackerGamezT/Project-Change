/*    */ package moze_intel.projecte.emc.mappers.customConversions.json;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ConversionGroup
/*    */ {
/*    */   public String comment;
/*  9 */   public final List<CustomConversion> conversions = new ArrayList<>();
/*    */   
/*    */   public static ConversionGroup merge(ConversionGroup left, ConversionGroup right) {
/* 12 */     ConversionGroup res = new ConversionGroup();
/* 13 */     res.conversions.addAll(left.conversions);
/* 14 */     res.conversions.addAll(right.conversions);
/* 15 */     return res;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\mappers\customConversions\json\ConversionGroup.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */