/*    */ package moze_intel.projecte.utils;
/*    */ 
/*    */ import java.math.BigInteger;
/*    */ import java.util.Random;
/*    */ import moze_intel.projecte.utils.text.PELang;
/*    */ import net.minecraft.network.chat.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class MathUtils
/*    */ {
/*    */   public static int parseInteger(String string) {
/*    */     try {
/* 16 */       return Integer.parseInt(string);
/* 17 */     } catch (NumberFormatException e) {
/* 18 */       return -1;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static int randomIntInRange(int min, int max) {
/* 23 */     Random rand = new Random();
/* 24 */     return rand.nextInt(max - min + 1) + min;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int scaleToRedstone(long currentAmount, long max) {
/* 31 */     double proportion = currentAmount / max;
/* 32 */     if (currentAmount <= 0L) {
/* 33 */       return 0;
/*    */     }
/* 35 */     if (currentAmount >= max) {
/* 36 */       return 15;
/*    */     }
/* 38 */     return (int)Math.round(proportion * 13.0D + 1.0D);
/*    */   }
/*    */   
/*    */   public static double tickToSec(int ticks) {
/* 42 */     return ticks / 20.0D;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Component tickToSecFormatted(int ticks) {
/* 50 */     double result = tickToSec(ticks);
/* 51 */     if (result == 0.0D) {
/* 52 */       return (Component)PELang.EVERY_TICK.translate(new Object[] { Double.valueOf(result) });
/*    */     }
/* 54 */     return (Component)PELang.SECONDS.translate(new Object[] { Double.valueOf(result) });
/*    */   }
/*    */   
/*    */   public static int secToTicks(double secs) {
/* 58 */     return (int)Math.round(secs * 20.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public static long clampToLong(BigInteger bigInt) {
/* 63 */     if (bigInt.compareTo(Constants.MAX_LONG) > 0) {
/* 64 */       return Long.MAX_VALUE;
/*    */     }
/*    */     
/* 67 */     return bigInt.longValueExact();
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\project\\utils\MathUtils.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */