/*    */ package moze_intel.projecte.integration.curios;
/*    */ 
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ import top.theillusivec4.curios.api.CuriosApi;
/*    */ import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
/*    */ 
/*    */ public class CuriosIntegration
/*    */ {
/*    */   @Nullable
/*    */   public static IItemHandler getAll(LivingEntity living) {
/* 13 */     return CuriosApi.getCuriosInventory(living)
/* 14 */       .lazyMap(ICuriosItemHandler::getEquippedCurios)
/* 15 */       .resolve()
/* 16 */       .orElse(null);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\curios\CuriosIntegration.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */