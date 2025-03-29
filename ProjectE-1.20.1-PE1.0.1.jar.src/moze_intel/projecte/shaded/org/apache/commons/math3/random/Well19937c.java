/*     */ package moze_intel.projecte.shaded.org.apache.commons.math3.random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Well19937c
/*     */   extends AbstractWell
/*     */ {
/*     */   private static final long serialVersionUID = -7203498180754925124L;
/*     */   private static final int K = 19937;
/*     */   private static final int M1 = 70;
/*     */   private static final int M2 = 179;
/*     */   private static final int M3 = 449;
/*     */   
/*     */   public Well19937c() {
/*  56 */     super(19937, 70, 179, 449);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Well19937c(int seed) {
/*  63 */     super(19937, 70, 179, 449, seed);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Well19937c(int[] seed) {
/*  71 */     super(19937, 70, 179, 449, seed);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Well19937c(long seed) {
/*  78 */     super(19937, 70, 179, 449, seed);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int next(int bits) {
/*  85 */     int indexRm1 = this.iRm1[this.index];
/*  86 */     int indexRm2 = this.iRm2[this.index];
/*     */     
/*  88 */     int v0 = this.v[this.index];
/*  89 */     int vM1 = this.v[this.i1[this.index]];
/*  90 */     int vM2 = this.v[this.i2[this.index]];
/*  91 */     int vM3 = this.v[this.i3[this.index]];
/*     */     
/*  93 */     int z0 = Integer.MIN_VALUE & this.v[indexRm1] ^ Integer.MAX_VALUE & this.v[indexRm2];
/*  94 */     int z1 = v0 ^ v0 << 25 ^ vM1 ^ vM1 >>> 27;
/*  95 */     int z2 = vM2 >>> 9 ^ vM3 ^ vM3 >>> 1;
/*  96 */     int z3 = z1 ^ z2;
/*  97 */     int z4 = z0 ^ z1 ^ z1 << 9 ^ z2 ^ z2 << 21 ^ z3 ^ z3 >>> 21;
/*     */     
/*  99 */     this.v[this.index] = z3;
/* 100 */     this.v[indexRm1] = z4;
/* 101 */     this.v[indexRm2] = this.v[indexRm2] & Integer.MIN_VALUE;
/* 102 */     this.index = indexRm1;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 107 */     z4 ^= z4 << 7 & 0xE46E1700;
/* 108 */     z4 ^= z4 << 15 & 0x9B868000;
/*     */     
/* 110 */     return z4 >>> 32 - bits;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\shaded\org\apache\commons\math3\random\Well19937c.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */