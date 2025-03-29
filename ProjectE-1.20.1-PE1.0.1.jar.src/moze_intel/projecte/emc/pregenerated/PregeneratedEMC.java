/*    */ package moze_intel.projecte.emc.pregenerated;
/*    */ 
/*    */ import com.google.common.reflect.TypeToken;
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.GsonBuilder;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.BufferedWriter;
/*    */ import java.io.File;
/*    */ import java.io.FileReader;
/*    */ import java.io.FileWriter;
/*    */ import java.io.IOException;
/*    */ import java.lang.reflect.Type;
/*    */ import java.util.Map;
/*    */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*    */ import moze_intel.projecte.emc.json.NSSSerializer;
/*    */ 
/*    */ public class PregeneratedEMC
/*    */ {
/* 19 */   private static final Gson gson = (new GsonBuilder())
/* 20 */     .registerTypeAdapter(NormalizedSimpleStack.class, NSSSerializer.INSTANCE)
/* 21 */     .enableComplexMapKeySerialization().setPrettyPrinting().create();
/*    */   
/*    */   public static boolean tryRead(File f, Map<NormalizedSimpleStack, Long> map) {
/*    */     try {
/* 25 */       Map<NormalizedSimpleStack, Long> m = read(f);
/* 26 */       map.clear();
/* 27 */       map.putAll(m);
/* 28 */       return true;
/* 29 */     } catch (IOException e) {
/* 30 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private static Map<NormalizedSimpleStack, Long> read(File file) throws IOException {
/* 36 */     Type type = (new TypeToken<Map<NormalizedSimpleStack, Long>>() {  }).getType();
/* 37 */     BufferedReader reader = new BufferedReader(new FileReader(file)); 
/* 38 */     try { Map<NormalizedSimpleStack, Long> map = (Map<NormalizedSimpleStack, Long>)gson.fromJson(reader, type);
/* 39 */       map.remove(null);
/* 40 */       Map<NormalizedSimpleStack, Long> map1 = map;
/* 41 */       reader.close(); return map1; }
/*    */     catch (Throwable throwable) { try { reader.close(); }
/*    */       catch (Throwable throwable1)
/*    */       { throwable.addSuppressed(throwable1); }
/*    */        throw throwable; }
/* 46 */      } public static void write(File file, Map<NormalizedSimpleStack, Long> map) throws IOException { Type type = (new TypeToken<Map<NormalizedSimpleStack, Integer>>() {  }).getType();
/* 47 */     BufferedWriter writer = new BufferedWriter(new FileWriter(file)); try {
/* 48 */       gson.toJson(map, type, writer);
/* 49 */       writer.close();
/*    */     } catch (Throwable throwable) {
/*    */       try {
/*    */         writer.close();
/*    */       } catch (Throwable throwable1) {
/*    */         throwable.addSuppressed(throwable1);
/*    */       } 
/*    */       throw throwable;
/*    */     }  }
/*    */ 
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\pregenerated\PregeneratedEMC.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */