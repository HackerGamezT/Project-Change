/*    */ package moze_intel.projecte.config;
/*    */ 
/*    */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CustomEMCEntry
/*    */ {
/*    */   public final NormalizedSimpleStack item;
/*    */   public final long emc;
/*    */   
/*    */   private CustomEMCEntry(NormalizedSimpleStack item, long emc) {
/* 44 */     this.item = item;
/* 45 */     this.emc = emc;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/* 50 */     return (o == this || (o instanceof CustomEMCEntry && this.item.equals(((CustomEMCEntry)o).item) && this.emc == ((CustomEMCEntry)o).emc));
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 55 */     int result = (this.item != null) ? this.item.hashCode() : 0;
/* 56 */     result = 31 * result + (int)(this.emc ^ this.emc >>> 32L);
/* 57 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\config\CustomEMCParser$CustomEMCEntry.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */