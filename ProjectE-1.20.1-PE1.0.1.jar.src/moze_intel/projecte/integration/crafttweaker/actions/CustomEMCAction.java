/*    */ package moze_intel.projecte.integration.crafttweaker.actions;
/*    */ 
/*    */ import com.blamejared.crafttweaker.api.action.base.IUndoableAction;
/*    */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*    */ import moze_intel.projecte.integration.crafttweaker.mappers.CrTCustomEMCMapper;
/*    */ import moze_intel.projecte.utils.Constants;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class CustomEMCAction
/*    */   implements IUndoableAction
/*    */ {
/*    */   @NotNull
/*    */   private final NormalizedSimpleStack stack;
/*    */   private final long emc;
/*    */   
/*    */   public CustomEMCAction(@NotNull NormalizedSimpleStack stack, long emc) {
/* 17 */     this.stack = stack;
/* 18 */     this.emc = emc;
/*    */   }
/*    */ 
/*    */   
/*    */   public void apply() {
/* 23 */     CrTCustomEMCMapper.registerCustomEMC(this.stack, this.emc);
/*    */   }
/*    */ 
/*    */   
/*    */   public String describe() {
/* 28 */     return "Registered emc value of '" + Constants.EMC_FORMATTER.format(this.emc) + "' for: " + this.stack;
/*    */   }
/*    */ 
/*    */   
/*    */   public void undo() {
/* 33 */     CrTCustomEMCMapper.unregisterNSS(this.stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public String describeUndo() {
/* 38 */     return "Undoing emc registration for: " + this.stack;
/*    */   }
/*    */ 
/*    */   
/*    */   public String systemName() {
/* 43 */     return "ProjectE";
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\crafttweaker\actions\CustomEMCAction.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */