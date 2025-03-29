/*    */ package moze_intel.projecte.integration.crafttweaker;
/*    */ 
/*    */ import com.blamejared.crafttweaker.api.CraftTweakerAPI;
/*    */ import com.blamejared.crafttweaker.api.action.base.IAction;
/*    */ import com.blamejared.crafttweaker.api.annotation.ZenRegister;
/*    */ import com.blamejared.crafttweaker_annotations.annotations.Document;
/*    */ import moze_intel.projecte.integration.crafttweaker.actions.WorldTransmuteAction;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import org.openzen.zencode.java.ZenCodeType.Method;
/*    */ import org.openzen.zencode.java.ZenCodeType.Name;
/*    */ import org.openzen.zencode.java.ZenCodeType.Optional;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @ZenRegister
/*    */ @Document("mods/ProjectE/WorldTransmutation")
/*    */ @Name("mods.projecte.WorldTransmutation")
/*    */ public class WorldTransmutation
/*    */ {
/*    */   @Method
/*    */   public static void add(BlockState input, BlockState output, @Optional BlockState sneakOutput) {
/* 27 */     CraftTweakerAPI.apply((IAction)new WorldTransmuteAction.Add(input, output, sneakOutput));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Method
/*    */   public static void remove(BlockState input, BlockState output, @Optional BlockState sneakOutput) {
/* 39 */     CraftTweakerAPI.apply((IAction)new WorldTransmuteAction.Remove(input, output, sneakOutput));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Method
/*    */   public static void removeAll() {
/* 47 */     CraftTweakerAPI.apply((IAction)new WorldTransmuteAction.RemoveAll());
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\crafttweaker\WorldTransmutation.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */