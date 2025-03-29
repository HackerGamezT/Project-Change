/*     */ package moze_intel.projecte.shaded.org.apache.commons.math3.exception;
/*     */ 
/*     */ import moze_intel.projecte.shaded.org.apache.commons.math3.exception.util.Localizable;
/*     */ import moze_intel.projecte.shaded.org.apache.commons.math3.exception.util.LocalizedFormats;
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
/*     */ public class NoBracketingException
/*     */   extends MathIllegalArgumentException
/*     */ {
/*     */   private static final long serialVersionUID = -3629324471511904459L;
/*     */   private final double lo;
/*     */   private final double hi;
/*     */   private final double fLo;
/*     */   private final double fHi;
/*     */   
/*     */   public NoBracketingException(double lo, double hi, double fLo, double fHi) {
/*  50 */     this((Localizable)LocalizedFormats.SAME_SIGN_AT_ENDPOINTS, lo, hi, fLo, fHi, new Object[0]);
/*     */   }
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
/*     */   public NoBracketingException(Localizable specific, double lo, double hi, double fLo, double fHi, Object... args) {
/*  67 */     super(specific, new Object[] { Double.valueOf(lo), Double.valueOf(hi), Double.valueOf(fLo), Double.valueOf(fHi), args });
/*  68 */     this.lo = lo;
/*  69 */     this.hi = hi;
/*  70 */     this.fLo = fLo;
/*  71 */     this.fHi = fHi;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getLo() {
/*  80 */     return this.lo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getHi() {
/*  88 */     return this.hi;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getFLo() {
/*  96 */     return this.fLo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getFHi() {
/* 104 */     return this.fHi;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\shaded\org\apache\commons\math3\exception\NoBracketingException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */