/*     */ package moze_intel.projecte.shaded.org.apache.commons.math3.distribution;
/*     */ 
/*     */ import moze_intel.projecte.shaded.org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import moze_intel.projecte.shaded.org.apache.commons.math3.exception.OutOfRangeException;
/*     */ import moze_intel.projecte.shaded.org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import moze_intel.projecte.shaded.org.apache.commons.math3.random.RandomGenerator;
/*     */ import moze_intel.projecte.shaded.org.apache.commons.math3.random.Well19937c;
/*     */ import moze_intel.projecte.shaded.org.apache.commons.math3.util.FastMath;
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
/*     */ public class CauchyDistribution
/*     */   extends AbstractRealDistribution
/*     */ {
/*     */   public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9D;
/*     */   private static final long serialVersionUID = 8589540077390120676L;
/*     */   private final double median;
/*     */   private final double scale;
/*     */   private final double solverAbsoluteAccuracy;
/*     */   
/*     */   public CauchyDistribution() {
/*  53 */     this(0.0D, 1.0D);
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
/*     */   public CauchyDistribution(double median, double scale) {
/*  70 */     this(median, scale, 1.0E-9D);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CauchyDistribution(double median, double scale, double inverseCumAccuracy) {
/*  93 */     this((RandomGenerator)new Well19937c(), median, scale, inverseCumAccuracy);
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
/*     */   public CauchyDistribution(RandomGenerator rng, double median, double scale) {
/* 106 */     this(rng, median, scale, 1.0E-9D);
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
/*     */ 
/*     */   
/*     */   public CauchyDistribution(RandomGenerator rng, double median, double scale, double inverseCumAccuracy) {
/* 125 */     super(rng);
/* 126 */     if (scale <= 0.0D) {
/* 127 */       throw new NotStrictlyPositiveException(LocalizedFormats.SCALE, Double.valueOf(scale));
/*     */     }
/* 129 */     this.scale = scale;
/* 130 */     this.median = median;
/* 131 */     this.solverAbsoluteAccuracy = inverseCumAccuracy;
/*     */   }
/*     */ 
/*     */   
/*     */   public double cumulativeProbability(double x) {
/* 136 */     return 0.5D + FastMath.atan((x - this.median) / this.scale) / Math.PI;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getMedian() {
/* 145 */     return this.median;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getScale() {
/* 154 */     return this.scale;
/*     */   }
/*     */ 
/*     */   
/*     */   public double density(double x) {
/* 159 */     double dev = x - this.median;
/* 160 */     return 0.3183098861837907D * this.scale / (dev * dev + this.scale * this.scale);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double inverseCumulativeProbability(double p) throws OutOfRangeException {
/*     */     double ret;
/* 172 */     if (p < 0.0D || p > 1.0D)
/* 173 */       throw new OutOfRangeException(Double.valueOf(p), Integer.valueOf(0), Integer.valueOf(1)); 
/* 174 */     if (p == 0.0D) {
/* 175 */       ret = Double.NEGATIVE_INFINITY;
/* 176 */     } else if (p == 1.0D) {
/* 177 */       ret = Double.POSITIVE_INFINITY;
/*     */     } else {
/* 179 */       ret = this.median + this.scale * FastMath.tan(Math.PI * (p - 0.5D));
/*     */     } 
/* 181 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected double getSolverAbsoluteAccuracy() {
/* 187 */     return this.solverAbsoluteAccuracy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getNumericalMean() {
/* 198 */     return Double.NaN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getNumericalVariance() {
/* 209 */     return Double.NaN;
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
/*     */   public double getSupportLowerBound() {
/* 221 */     return Double.NEGATIVE_INFINITY;
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
/*     */   public double getSupportUpperBound() {
/* 233 */     return Double.POSITIVE_INFINITY;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSupportLowerBoundInclusive() {
/* 238 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSupportUpperBoundInclusive() {
/* 243 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSupportConnected() {
/* 254 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\shaded\org\apache\commons\math3\distribution\CauchyDistribution.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */