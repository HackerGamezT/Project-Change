/*    */ package moze_intel.projecte.gameObjs.items;
/*    */ import java.util.function.Supplier;
/*    */ import moze_intel.projecte.api.capabilities.item.IItemCharge;
/*    */ import moze_intel.projecte.api.capabilities.item.IProjectileShooter;
/*    */ import moze_intel.projecte.gameObjs.entity.EntityLensProjectile;
/*    */ import moze_intel.projecte.gameObjs.registries.PESoundEvents;
/*    */ import moze_intel.projecte.utils.Constants;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraft.sounds.SoundSource;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.InteractionResultHolder;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.Level;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class HyperkineticLens extends ItemPE implements IProjectileShooter, IItemCharge, IBarHelper {
/*    */   public HyperkineticLens(Item.Properties props) {
/* 21 */     super(props);
/* 22 */     addItemCapability(moze_intel.projecte.capability.ChargeItemCapabilityWrapper::new);
/* 23 */     addItemCapability(moze_intel.projecte.capability.ProjectileShooterItemCapabilityWrapper::new);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public InteractionResultHolder<ItemStack> m_7203_(Level level, Player player, @NotNull InteractionHand hand) {
/* 29 */     ItemStack stack = player.m_21120_(hand);
/* 30 */     if (!level.f_46443_) {
/* 31 */       shootProjectile(player, stack, hand);
/*    */     }
/* 33 */     return InteractionResultHolder.m_19090_(stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shootProjectile(@NotNull Player player, @NotNull ItemStack stack, InteractionHand hand) {
/* 38 */     Level level = player.m_9236_();
/* 39 */     long requiredEmc = Constants.EXPLOSIVE_LENS_COST[getCharge(stack)];
/* 40 */     if (!consumeFuel(player, stack, requiredEmc, true)) {
/* 41 */       return false;
/*    */     }
/* 43 */     level.m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)PESoundEvents.POWER.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
/* 44 */     EntityLensProjectile ent = new EntityLensProjectile(player, getCharge(stack), level);
/* 45 */     ent.m_37251_((Entity)player, player.m_146909_(), player.m_146908_(), 0.0F, 1.5F, 1.0F);
/* 46 */     level.m_7967_((Entity)ent);
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNumCharges(@NotNull ItemStack stack) {
/* 52 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_142522_(@NotNull ItemStack stack) {
/* 57 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getWidthForBar(ItemStack stack) {
/* 62 */     return 1.0F - getChargePercent(stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public int m_142158_(@NotNull ItemStack stack) {
/* 67 */     return getScaledBarWidth(stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public int m_142159_(@NotNull ItemStack stack) {
/* 72 */     return getColorForBar(stack);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\HyperkineticLens.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */