/*    */ package moze_intel.projecte.api.nss;
/*    */ 
/*    */ import com.google.common.base.Objects;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class NSSFake
/*    */   implements NormalizedSimpleStack
/*    */ {
/* 15 */   private static String currentNamespace = "";
/*    */   
/*    */   private final String namespace;
/*    */   private final String description;
/*    */   
/*    */   private NSSFake(String namespace, String description) {
/* 21 */     this.namespace = namespace;
/* 22 */     this.description = description;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void resetNamespace() {
/* 31 */     setCurrentNamespace("");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void setCurrentNamespace(@NotNull String ns) {
/* 42 */     currentNamespace = ns;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public static NormalizedSimpleStack create(String description) {
/* 50 */     return new NSSFake(currentNamespace, description);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/* 55 */     if (o instanceof NSSFake) { NSSFake fake = (NSSFake)o; if (this.description.equals(fake.description) && this.namespace.equals(fake.namespace)); }  return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 60 */     return Objects.hashCode(new Object[] { this.namespace, this.description });
/*    */   }
/*    */ 
/*    */   
/*    */   public String json() {
/* 65 */     return "FAKE|" + this.description;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 70 */     return "NSSFAKE:" + this.namespace + "/" + this.description;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\nss\NSSFake.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */