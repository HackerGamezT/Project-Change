/*    */ package moze_intel.projecte.shaded.org.apache.commons.math3.exception;
/*    */ 
/*    */ import moze_intel.projecte.shaded.org.apache.commons.math3.exception.util.ExceptionContext;
/*    */ import moze_intel.projecte.shaded.org.apache.commons.math3.exception.util.ExceptionContextProvider;
/*    */ import moze_intel.projecte.shaded.org.apache.commons.math3.exception.util.Localizable;
/*    */ import moze_intel.projecte.shaded.org.apache.commons.math3.exception.util.LocalizedFormats;
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
/*    */ public class MathIllegalStateException
/*    */   extends IllegalStateException
/*    */   implements ExceptionContextProvider
/*    */ {
/*    */   private static final long serialVersionUID = -6024911025449780478L;
/*    */   private final ExceptionContext context;
/*    */   
/*    */   public MathIllegalStateException(Localizable pattern, Object... args) {
/* 46 */     this.context = new ExceptionContext(this);
/* 47 */     this.context.addMessage(pattern, args);
/*    */   }
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
/*    */   public MathIllegalStateException(Throwable cause, Localizable pattern, Object... args) {
/* 60 */     super(cause);
/* 61 */     this.context = new ExceptionContext(this);
/* 62 */     this.context.addMessage(pattern, args);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MathIllegalStateException() {
/* 69 */     this((Localizable)LocalizedFormats.ILLEGAL_STATE, new Object[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public ExceptionContext getContext() {
/* 74 */     return this.context;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getMessage() {
/* 80 */     return this.context.getMessage();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLocalizedMessage() {
/* 86 */     return this.context.getLocalizedMessage();
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\shaded\org\apache\commons\math3\exception\MathIllegalStateException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */