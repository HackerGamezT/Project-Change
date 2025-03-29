/*    */ package moze_intel.projecte.gameObjs;
/*    */ 
/*    */ import net.minecraft.util.StringRepresentable;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public enum EnumCollectorTier
/*    */   implements StringRepresentable {
/*  8 */   MK1("collector_mk1", 4L, 10000L),
/*  9 */   MK2("collector_mk2", 12L, 30000L),
/* 10 */   MK3("collector_mk3", 40L, 60000L);
/*    */   
/*    */   private final String name;
/*    */   private final long genRate;
/*    */   private final long storage;
/*    */   
/*    */   EnumCollectorTier(String name, long genRate, long storage) {
/* 17 */     this.name = name;
/* 18 */     this.genRate = genRate;
/* 19 */     this.storage = storage;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public String m_7912_() {
/* 25 */     return this.name;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getGenRate() {
/* 30 */     return this.genRate;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getStorage() {
/* 35 */     return this.storage;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 40 */     return m_7912_();
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\EnumCollectorTier.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */