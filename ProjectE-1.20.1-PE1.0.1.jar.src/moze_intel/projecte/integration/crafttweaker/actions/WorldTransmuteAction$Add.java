/*    */ package moze_intel.projecte.integration.crafttweaker.actions;
/*    */ 
/*    */ import com.blamejared.crafttweaker.natives.block.ExpandBlockState;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import org.jetbrains.annotations.Nullable;
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
/*    */ public class Add
/*    */   extends WorldTransmuteAction
/*    */ {
/*    */   public Add(BlockState input, BlockState output, @Nullable BlockState sneakOutput) {
/* 37 */     super(input, output, sneakOutput);
/*    */   }
/*    */ 
/*    */   
/*    */   public void apply() {
/* 42 */     apply(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public String describe() {
/* 47 */     return (this.sneakOutput == null) ? ("Adding world transmutation recipe for: " + 
/* 48 */       ExpandBlockState.getCommandString(this.input) + " with output: " + 
/* 49 */       ExpandBlockState.getCommandString(this.output)) : ("Adding world transmutation recipe for: " + 
/*    */       
/* 51 */       ExpandBlockState.getCommandString(this.input) + " with output: " + 
/* 52 */       ExpandBlockState.getCommandString(this.output) + " and secondary output: " + ExpandBlockState.getCommandString(this.sneakOutput));
/*    */   }
/*    */ 
/*    */   
/*    */   public void undo() {
/* 57 */     apply(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public String describeUndo() {
/* 62 */     return (this.sneakOutput == null) ? ("Undoing addition of world transmutation recipe for: " + 
/* 63 */       ExpandBlockState.getCommandString(this.input) + " with output: " + 
/* 64 */       ExpandBlockState.getCommandString(this.output)) : ("Undoing addition of world transmutation recipe for: " + 
/*    */       
/* 66 */       ExpandBlockState.getCommandString(this.input) + " with output: " + 
/* 67 */       ExpandBlockState.getCommandString(this.output) + " and secondary output: " + ExpandBlockState.getCommandString(this.sneakOutput));
/*    */   }
/*    */ 
/*    */   
/*    */   public String systemName() {
/* 72 */     return "ProjectE";
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\crafttweaker\actions\WorldTransmuteAction$Add.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */