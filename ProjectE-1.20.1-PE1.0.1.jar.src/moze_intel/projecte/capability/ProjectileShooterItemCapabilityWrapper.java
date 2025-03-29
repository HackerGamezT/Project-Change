/*    */ package moze_intel.projecte.capability;
/*    */ 
/*    */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*    */ import moze_intel.projecte.api.capabilities.item.IProjectileShooter;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class ProjectileShooterItemCapabilityWrapper
/*    */   extends BasicItemCapability<IProjectileShooter>
/*    */   implements IProjectileShooter {
/*    */   public Capability<IProjectileShooter> getCapability() {
/* 16 */     return PECapabilities.PROJECTILE_SHOOTER_ITEM_CAPABILITY;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shootProjectile(@NotNull Player player, @NotNull ItemStack stack, @Nullable InteractionHand hand) {
/* 21 */     return getItem().shootProjectile(player, stack, hand);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\capability\ProjectileShooterItemCapabilityWrapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */