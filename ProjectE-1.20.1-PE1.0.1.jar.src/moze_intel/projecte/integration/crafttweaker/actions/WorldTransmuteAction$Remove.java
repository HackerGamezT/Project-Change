/*     */ package moze_intel.projecte.integration.crafttweaker.actions;
/*     */ 
/*     */ import com.blamejared.crafttweaker.natives.block.ExpandBlockState;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import org.jetbrains.annotations.Nullable;
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
/*     */ public class Remove
/*     */   extends WorldTransmuteAction
/*     */ {
/*     */   public Remove(BlockState input, BlockState output, @Nullable BlockState sneakOutput) {
/*  79 */     super(input, output, sneakOutput);
/*     */   }
/*     */ 
/*     */   
/*     */   public void apply() {
/*  84 */     apply(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public String describe() {
/*  89 */     return (this.sneakOutput == null) ? ("Removing world transmutation recipe for: " + 
/*  90 */       ExpandBlockState.getCommandString(this.input) + " with output: " + 
/*  91 */       ExpandBlockState.getCommandString(this.output)) : ("Removing world transmutation recipe for: " + 
/*     */       
/*  93 */       ExpandBlockState.getCommandString(this.input) + " with output: " + 
/*  94 */       ExpandBlockState.getCommandString(this.output) + " and secondary output: " + ExpandBlockState.getCommandString(this.sneakOutput));
/*     */   }
/*     */ 
/*     */   
/*     */   public void undo() {
/*  99 */     apply(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public String describeUndo() {
/* 104 */     return (this.sneakOutput == null) ? ("Undoing removal of world transmutation recipe for: " + 
/* 105 */       ExpandBlockState.getCommandString(this.input) + " with output: " + 
/* 106 */       ExpandBlockState.getCommandString(this.output)) : ("Undoing removal of world transmutation recipe for: " + 
/*     */       
/* 108 */       ExpandBlockState.getCommandString(this.input) + " with output: " + 
/* 109 */       ExpandBlockState.getCommandString(this.output) + " and secondary output: " + ExpandBlockState.getCommandString(this.sneakOutput));
/*     */   }
/*     */ 
/*     */   
/*     */   public String systemName() {
/* 114 */     return "ProjectE";
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\crafttweaker\actions\WorldTransmuteAction$Remove.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */