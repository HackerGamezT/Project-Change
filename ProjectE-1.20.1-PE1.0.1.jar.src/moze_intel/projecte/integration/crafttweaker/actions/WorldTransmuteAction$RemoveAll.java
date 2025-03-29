/*     */ package moze_intel.projecte.integration.crafttweaker.actions;
/*     */ 
/*     */ import com.blamejared.crafttweaker.api.action.base.IUndoableAction;
/*     */ import moze_intel.projecte.utils.WorldTransmutations;
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
/*     */ public class RemoveAll
/*     */   implements IUndoableAction
/*     */ {
/*     */   public void apply() {
/* 122 */     WorldTransmutations.getWorldTransmutations().clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String describe() {
/* 127 */     return "Removing all world transmutation recipes";
/*     */   }
/*     */ 
/*     */   
/*     */   public void undo() {
/* 132 */     WorldTransmutations.resetWorldTransmutations();
/*     */   }
/*     */ 
/*     */   
/*     */   public String describeUndo() {
/* 137 */     return "Restored world transmutation recipes to default";
/*     */   }
/*     */ 
/*     */   
/*     */   public String systemName() {
/* 142 */     return "ProjectE";
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\crafttweaker\actions\WorldTransmuteAction$RemoveAll.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */