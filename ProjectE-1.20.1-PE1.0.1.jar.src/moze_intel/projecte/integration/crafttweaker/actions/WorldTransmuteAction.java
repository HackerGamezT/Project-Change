/*     */ package moze_intel.projecte.integration.crafttweaker.actions;
/*     */ 
/*     */ import com.blamejared.crafttweaker.api.action.base.IUndoableAction;
/*     */ import com.blamejared.crafttweaker.natives.block.ExpandBlockState;
/*     */ import moze_intel.projecte.api.imc.WorldTransmutationEntry;
/*     */ import moze_intel.projecte.utils.WorldTransmutations;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public abstract class WorldTransmuteAction
/*     */   implements IUndoableAction {
/*     */   protected final BlockState input;
/*     */   protected final BlockState output;
/*     */   @Nullable
/*     */   protected final BlockState sneakOutput;
/*     */   
/*     */   private WorldTransmuteAction(BlockState input, BlockState output, @Nullable BlockState sneakOutput) {
/*  18 */     this.input = input;
/*  19 */     this.output = output;
/*  20 */     this.sneakOutput = sneakOutput;
/*     */   }
/*     */   
/*     */   protected void apply(boolean add) {
/*  24 */     if (add) {
/*  25 */       WorldTransmutations.register(this.input, this.output, this.sneakOutput);
/*     */     } else {
/*  27 */       WorldTransmutations.getWorldTransmutations().removeIf(entry -> {
/*     */             BlockState altOutput = (this.sneakOutput == null) ? this.output : this.sneakOutput;
/*  29 */             return (entry.origin() == this.input && entry.result() == this.output && entry.altResult() == altOutput);
/*     */           });
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class Add
/*     */     extends WorldTransmuteAction {
/*     */     public Add(BlockState input, BlockState output, @Nullable BlockState sneakOutput) {
/*  37 */       super(input, output, sneakOutput);
/*     */     }
/*     */ 
/*     */     
/*     */     public void apply() {
/*  42 */       apply(true);
/*     */     }
/*     */ 
/*     */     
/*     */     public String describe() {
/*  47 */       return (this.sneakOutput == null) ? ("Adding world transmutation recipe for: " + 
/*  48 */         ExpandBlockState.getCommandString(this.input) + " with output: " + 
/*  49 */         ExpandBlockState.getCommandString(this.output)) : ("Adding world transmutation recipe for: " + 
/*     */         
/*  51 */         ExpandBlockState.getCommandString(this.input) + " with output: " + 
/*  52 */         ExpandBlockState.getCommandString(this.output) + " and secondary output: " + ExpandBlockState.getCommandString(this.sneakOutput));
/*     */     }
/*     */ 
/*     */     
/*     */     public void undo() {
/*  57 */       apply(false);
/*     */     }
/*     */ 
/*     */     
/*     */     public String describeUndo() {
/*  62 */       return (this.sneakOutput == null) ? ("Undoing addition of world transmutation recipe for: " + 
/*  63 */         ExpandBlockState.getCommandString(this.input) + " with output: " + 
/*  64 */         ExpandBlockState.getCommandString(this.output)) : ("Undoing addition of world transmutation recipe for: " + 
/*     */         
/*  66 */         ExpandBlockState.getCommandString(this.input) + " with output: " + 
/*  67 */         ExpandBlockState.getCommandString(this.output) + " and secondary output: " + ExpandBlockState.getCommandString(this.sneakOutput));
/*     */     }
/*     */ 
/*     */     
/*     */     public String systemName() {
/*  72 */       return "ProjectE";
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Remove
/*     */     extends WorldTransmuteAction {
/*     */     public Remove(BlockState input, BlockState output, @Nullable BlockState sneakOutput) {
/*  79 */       super(input, output, sneakOutput);
/*     */     }
/*     */ 
/*     */     
/*     */     public void apply() {
/*  84 */       apply(false);
/*     */     }
/*     */ 
/*     */     
/*     */     public String describe() {
/*  89 */       return (this.sneakOutput == null) ? ("Removing world transmutation recipe for: " + 
/*  90 */         ExpandBlockState.getCommandString(this.input) + " with output: " + 
/*  91 */         ExpandBlockState.getCommandString(this.output)) : ("Removing world transmutation recipe for: " + 
/*     */         
/*  93 */         ExpandBlockState.getCommandString(this.input) + " with output: " + 
/*  94 */         ExpandBlockState.getCommandString(this.output) + " and secondary output: " + ExpandBlockState.getCommandString(this.sneakOutput));
/*     */     }
/*     */ 
/*     */     
/*     */     public void undo() {
/*  99 */       apply(true);
/*     */     }
/*     */ 
/*     */     
/*     */     public String describeUndo() {
/* 104 */       return (this.sneakOutput == null) ? ("Undoing removal of world transmutation recipe for: " + 
/* 105 */         ExpandBlockState.getCommandString(this.input) + " with output: " + 
/* 106 */         ExpandBlockState.getCommandString(this.output)) : ("Undoing removal of world transmutation recipe for: " + 
/*     */         
/* 108 */         ExpandBlockState.getCommandString(this.input) + " with output: " + 
/* 109 */         ExpandBlockState.getCommandString(this.output) + " and secondary output: " + ExpandBlockState.getCommandString(this.sneakOutput));
/*     */     }
/*     */ 
/*     */     
/*     */     public String systemName() {
/* 114 */       return "ProjectE";
/*     */     }
/*     */   }
/*     */   
/*     */   public static class RemoveAll
/*     */     implements IUndoableAction
/*     */   {
/*     */     public void apply() {
/* 122 */       WorldTransmutations.getWorldTransmutations().clear();
/*     */     }
/*     */ 
/*     */     
/*     */     public String describe() {
/* 127 */       return "Removing all world transmutation recipes";
/*     */     }
/*     */ 
/*     */     
/*     */     public void undo() {
/* 132 */       WorldTransmutations.resetWorldTransmutations();
/*     */     }
/*     */ 
/*     */     
/*     */     public String describeUndo() {
/* 137 */       return "Restored world transmutation recipes to default";
/*     */     }
/*     */ 
/*     */     
/*     */     public String systemName() {
/* 142 */       return "ProjectE";
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\crafttweaker\actions\WorldTransmuteAction.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */