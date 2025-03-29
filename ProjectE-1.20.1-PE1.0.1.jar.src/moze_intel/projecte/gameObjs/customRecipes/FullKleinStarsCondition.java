/*    */ package moze_intel.projecte.gameObjs.customRecipes;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.config.ProjectEConfig;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraftforge.common.crafting.conditions.ICondition;
/*    */ import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
/*    */ 
/*    */ public class FullKleinStarsCondition
/*    */   implements ICondition {
/* 12 */   public static final FullKleinStarsCondition INSTANCE = new FullKleinStarsCondition();
/* 13 */   private static final ResourceLocation ID = PECore.rl("full_klein_stars");
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
/* 25 */     return ProjectEConfig.common.fullKleinStars.get();
/*    */   }
/*    */   
/* 28 */   public static final IConditionSerializer<FullKleinStarsCondition> SERIALIZER = new IConditionSerializer<FullKleinStarsCondition>()
/*    */     {
/*    */       public void write(JsonObject json, FullKleinStarsCondition value) {}
/*    */ 
/*    */ 
/*    */       
/*    */       public FullKleinStarsCondition read(JsonObject json) {
/* 35 */         return FullKleinStarsCondition.INSTANCE;
/*    */       }
/*    */ 
/*    */       
/*    */       public ResourceLocation getID() {
/* 40 */         return FullKleinStarsCondition.ID;
/*    */       }
/*    */     };
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\customRecipes\FullKleinStarsCondition.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */