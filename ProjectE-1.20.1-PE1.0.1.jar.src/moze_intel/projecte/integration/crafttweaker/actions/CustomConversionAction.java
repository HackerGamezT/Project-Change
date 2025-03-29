/*    */ package moze_intel.projecte.integration.crafttweaker.actions;
/*    */ 
/*    */ import com.blamejared.crafttweaker.api.action.base.IUndoableAction;
/*    */ import java.util.Map;
/*    */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*    */ import moze_intel.projecte.integration.crafttweaker.mappers.CrTConversionEMCMapper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CustomConversionAction
/*    */   implements IUndoableAction
/*    */ {
/*    */   private final CrTConversionEMCMapper.CrTConversion conversion;
/*    */   
/*    */   public CustomConversionAction(NormalizedSimpleStack output, int amount, boolean propagateTags, boolean set, Map<NormalizedSimpleStack, Integer> ingredients) {
/* 17 */     this.conversion = new CrTConversionEMCMapper.CrTConversion(output, amount, propagateTags, set, ingredients);
/*    */   }
/*    */ 
/*    */   
/*    */   public void apply() {
/* 22 */     CrTConversionEMCMapper.addConversion(this.conversion);
/*    */   }
/*    */ 
/*    */   
/*    */   public String describe() {
/* 27 */     StringBuilder inputString = new StringBuilder();
/* 28 */     for (Map.Entry<NormalizedSimpleStack, Integer> entry : (Iterable<Map.Entry<NormalizedSimpleStack, Integer>>)this.conversion.ingredients().entrySet()) {
/* 29 */       if (!inputString.isEmpty())
/*    */       {
/* 31 */         inputString.append(", ");
/*    */       }
/* 33 */       int amount = ((Integer)entry.getValue()).intValue();
/* 34 */       if (amount > 1) {
/* 35 */         inputString.append(amount).append(" ");
/*    */       }
/* 37 */       inputString.append(entry.getKey());
/*    */     } 
/* 39 */     String description = "Added custom conversion creating '" + this.conversion.amount() + "' of " + this.conversion.output() + ", from: " + inputString;
/* 40 */     if (this.conversion.propagateTags() && this.conversion.output() instanceof moze_intel.projecte.api.nss.NSSTag) {
/* 41 */       description = description + "; propagating to elements of " + description;
/*    */     }
/* 43 */     return description;
/*    */   }
/*    */ 
/*    */   
/*    */   public void undo() {
/* 48 */     CrTConversionEMCMapper.removeConversion(this.conversion);
/*    */   }
/*    */ 
/*    */   
/*    */   public String describeUndo() {
/* 53 */     return "Undoing adding of custom conversion creating '" + this.conversion.amount() + "' of " + this.conversion.output();
/*    */   }
/*    */ 
/*    */   
/*    */   public String systemName() {
/* 58 */     return "ProjectE";
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\crafttweaker\actions\CustomConversionAction.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */