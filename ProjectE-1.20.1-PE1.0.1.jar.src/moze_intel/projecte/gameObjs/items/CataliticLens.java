/*    */ package moze_intel.projecte.gameObjs.items;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import moze_intel.projecte.api.capabilities.item.IProjectileShooter;
/*    */ import moze_intel.projecte.gameObjs.registries.PEItems;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class CataliticLens extends DestructionCatalyst implements IProjectileShooter {
/*    */   public CataliticLens(Item.Properties props) {
/* 14 */     super(props);
/* 15 */     addItemCapability(moze_intel.projecte.capability.ProjectileShooterItemCapabilityWrapper::new);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shootProjectile(@NotNull Player player, @NotNull ItemStack stack, InteractionHand hand) {
/* 20 */     return ((HyperkineticLens)PEItems.HYPERKINETIC_LENS.get()).shootProjectile(player, stack, hand);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNumCharges(@NotNull ItemStack stack) {
/* 25 */     return 7;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\CataliticLens.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */