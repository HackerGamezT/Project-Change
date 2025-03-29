/*    */ package moze_intel.projecte.gameObjs;
/*    */ 
/*    */ import net.minecraft.util.StringRepresentable;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public enum EnumFuelType implements StringRepresentable {
/*  7 */   ALCHEMICAL_COAL("alchemical_coal", 6400),
/*  8 */   MOBIUS_FUEL("mobius_fuel", ALCHEMICAL_COAL.getBurnTime() * 4),
/*  9 */   AETERNALIS_FUEL("aeternalis_fuel", MOBIUS_FUEL.getBurnTime() * 4);
/*    */   
/*    */   private final String name;
/*    */   private final int burnTime;
/*    */   
/*    */   EnumFuelType(String name, int burnTime) {
/* 15 */     this.name = name;
/* 16 */     this.burnTime = burnTime;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public String m_7912_() {
/* 22 */     return this.name;
/*    */   }
/*    */   
/*    */   public int getBurnTime() {
/* 26 */     return this.burnTime;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 31 */     return m_7912_();
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\EnumFuelType.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */