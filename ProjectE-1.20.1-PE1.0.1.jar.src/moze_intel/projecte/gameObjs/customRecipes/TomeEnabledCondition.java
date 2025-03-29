/*    */ package moze_intel.projecte.gameObjs.customRecipes;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.config.ProjectEConfig;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraftforge.common.crafting.conditions.ICondition;
/*    */ import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
/*    */ 
/*    */ public class TomeEnabledCondition
/*    */   implements ICondition {
/* 12 */   public static final TomeEnabledCondition INSTANCE = new TomeEnabledCondition();
/* 13 */   private static final ResourceLocation ID = PECore.rl("tome_enabled");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getID() {
/* 20 */     return ID;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean test(ICondition.IContext context) {
/* 25 */     return ProjectEConfig.common.craftableTome.get();
/*    */   }
/*    */   
/* 28 */   public static final IConditionSerializer<TomeEnabledCondition> SERIALIZER = new IConditionSerializer<TomeEnabledCondition>()
/*    */     {
/*    */       public void write(JsonObject json, TomeEnabledCondition value) {}
/*    */ 
/*    */ 
/*    */       
/*    */       public TomeEnabledCondition read(JsonObject json) {
/* 35 */         return TomeEnabledCondition.INSTANCE;
/*    */       }
/*    */ 
/*    */       
/*    */       public ResourceLocation getID() {
/* 40 */         return TomeEnabledCondition.ID;
/*    */       }
/*    */     };
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\customRecipes\TomeEnabledCondition.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */