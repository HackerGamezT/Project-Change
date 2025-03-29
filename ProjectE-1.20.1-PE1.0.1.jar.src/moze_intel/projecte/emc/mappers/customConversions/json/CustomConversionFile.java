/*    */ package moze_intel.projecte.emc.mappers.customConversions.json;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileWriter;
/*    */ import java.io.IOException;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import moze_intel.projecte.emc.mappers.customConversions.CustomConversionMapper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CustomConversionFile
/*    */ {
/*    */   public boolean replace = false;
/*    */   public String comment;
/* 17 */   public final Map<String, ConversionGroup> groups = new HashMap<>();
/* 18 */   public final FixedValues values = new FixedValues();
/*    */   
/*    */   public static CustomConversionFile merge(CustomConversionFile left, CustomConversionFile right) {
/* 21 */     if (right.replace) {
/* 22 */       return right;
/*    */     }
/*    */     
/* 25 */     for (Map.Entry<String, ConversionGroup> e : right.groups.entrySet()) {
/* 26 */       left.groups.merge(e.getKey(), e.getValue(), ConversionGroup::merge);
/*    */     }
/*    */     
/* 29 */     left.values.merge(right.values);
/* 30 */     return left;
/*    */   }
/*    */   
/*    */   public void write(File file) throws IOException {
/* 34 */     FileWriter fileWriter = new FileWriter(file); try {
/* 35 */       CustomConversionMapper.GSON.toJson(this, fileWriter);
/* 36 */       fileWriter.close();
/*    */     } catch (Throwable throwable) {
/*    */       try {
/*    */         fileWriter.close();
/*    */       } catch (Throwable throwable1) {
/*    */         throwable.addSuppressed(throwable1);
/*    */       } 
/*    */       throw throwable;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\mappers\customConversions\json\CustomConversionFile.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */