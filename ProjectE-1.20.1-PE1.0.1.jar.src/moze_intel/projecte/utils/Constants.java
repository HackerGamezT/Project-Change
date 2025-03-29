/*    */ package moze_intel.projecte.utils;
/*    */ 
/*    */ import java.math.BigInteger;
/*    */ import java.text.NumberFormat;
/*    */ 
/*    */ public final class Constants
/*    */ {
/*  8 */   public static final NumberFormat EMC_FORMATTER = getFormatter();
/*    */   
/*    */   private static NumberFormat getFormatter() {
/* 11 */     NumberFormat format = NumberFormat.getInstance();
/*    */ 
/*    */ 
/*    */     
/* 15 */     format.setMaximumFractionDigits(1);
/* 16 */     return format;
/*    */   }
/*    */   
/* 19 */   public static final BigInteger MAX_EXACT_TRANSMUTATION_DISPLAY = BigInteger.valueOf(1000000000000L);
/* 20 */   public static final BigInteger MAX_INTEGER = BigInteger.valueOf(2147483647L);
/* 21 */   public static final BigInteger MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);
/*    */   
/* 23 */   public static final long[] MAX_KLEIN_EMC = new long[] { 50000L, 200000L, 800000L, 3200000L, 12800000L, 51200000L };
/*    */   
/* 25 */   public static final float[] EXPLOSIVE_LENS_RADIUS = new float[] { 4.0F, 8.0F, 12.0F, 16.0F, 16.0F, 16.0F, 16.0F, 16.0F };
/* 26 */   public static final long[] EXPLOSIVE_LENS_COST = new long[] { 384L, 768L, 1536L, 2304L, 2304L, 2304L, 2304L, 2304L };
/*    */   public static final long FREE_ARITHMETIC_VALUE = -9223372036854775808L;
/*    */   public static final long BLOCK_ENTITY_MAX_EMC = 9223372036854775807L;
/*    */   public static final int MAX_CONDENSER_PROGRESS = 102;
/*    */   public static final int MAX_VEIN_SIZE = 250;
/*    */   public static final String NBT_KEY_STORED_EMC = "StoredEMC";
/*    */   public static final String NBT_KEY_GEM_WHITELIST = "Whitelist";
/*    */   public static final String NBT_KEY_COOLDOWN = "Cooldown";
/*    */   public static final String NBT_KEY_ACTIVE = "Active";
/*    */   public static final String NBT_KEY_MODE = "Mode";
/*    */   public static final String NBT_KEY_STEP_ASSIST = "StepAssist";
/*    */   public static final String NBT_KEY_NIGHT_VISION = "NightVision";
/*    */   public static final String NBT_KEY_UNPROCESSED_EMC = "UnprocessedEMC";
/*    */   public static final String NBT_KEY_GEM_CONSUMED = "Consumed";
/*    */   public static final String NBT_KEY_GEM_ITEMS = "Items";
/*    */   public static final String NBT_KEY_TIME_MODE = "TimeMode";
/*    */   public static final String NBT_KEY_STORED_XP = "StoredXP";
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\project\\utils\Constants.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */