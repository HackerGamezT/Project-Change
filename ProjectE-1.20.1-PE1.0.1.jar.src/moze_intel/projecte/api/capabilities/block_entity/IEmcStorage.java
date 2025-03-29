/*     */ package moze_intel.projecte.api.capabilities.block_entity;
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
/*     */ public interface IEmcStorage
/*     */ {
/*     */   long getStoredEmc();
/*     */   
/*     */   long getMaximumEmc();
/*     */   
/*     */   public enum EmcAction
/*     */   {
/*  24 */     EXECUTE,
/*  25 */     SIMULATE;
/*     */     
/*     */     public boolean execute() {
/*  28 */       return (this == EXECUTE);
/*     */     }
/*     */     
/*     */     public boolean simulate() {
/*  32 */       return (this == SIMULATE);
/*     */     }
/*     */     
/*     */     public EmcAction combine(boolean execute) {
/*  36 */       return get((execute && execute()));
/*     */     }
/*     */     
/*     */     public static EmcAction get(boolean execute) {
/*  40 */       return execute ? EXECUTE : SIMULATE;
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default long getNeededEmc() {
/*  69 */     return Math.max(0L, getMaximumEmc() - getStoredEmc());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default boolean hasMaxedEmc() {
/*  78 */     return (getStoredEmc() >= getMaximumEmc());
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
/*     */   long extractEmc(long paramLong, EmcAction paramEmcAction);
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
/*     */   long insertEmc(long paramLong, EmcAction paramEmcAction);
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
/*     */   default boolean isRelay() {
/* 113 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\capabilities\block_entity\IEmcStorage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */