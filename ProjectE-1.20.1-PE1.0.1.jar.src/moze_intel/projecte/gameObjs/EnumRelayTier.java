/*    */ package moze_intel.projecte.gameObjs;
/*    */ 
/*    */ import net.minecraft.util.StringRepresentable;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public enum EnumRelayTier
/*    */   implements StringRepresentable {
/*  8 */   MK1("relay_mk1", 64L, 100000L),
/*  9 */   MK2("relay_mk2", 192L, 1000000L),
/* 10 */   MK3("relay_mk3", 640L, 10000000L);
/*    */   
/*    */   private final String name;
/*    */   private final long chargeRate;
/*    */   private final long storage;
/*    */   
/*    */   EnumRelayTier(String name, long chargeRate, long storage) {
/* 17 */     this.name = name;
/* 18 */     this.chargeRate = chargeRate;
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
/*    */   public long getChargeRate() {
/* 30 */     return this.chargeRate;
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


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\EnumRelayTier.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */