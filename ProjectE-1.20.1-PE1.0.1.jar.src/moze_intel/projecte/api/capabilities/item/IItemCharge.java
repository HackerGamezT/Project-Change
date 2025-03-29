/*    */ package moze_intel.projecte.api.capabilities.item;
/*    */ 
/*    */ import moze_intel.projecte.api.PESounds;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraft.sounds.SoundSource;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ public interface IItemCharge
/*    */ {
/*    */   public static final String KEY = "Charge";
/*    */   
/*    */   int getNumCharges(@NotNull ItemStack paramItemStack);
/*    */   
/*    */   default float getChargePercent(@NotNull ItemStack stack) {
/* 33 */     return getCharge(stack) / getNumCharges(stack);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default int getCharge(@NotNull ItemStack stack) {
/* 44 */     return stack.m_41784_().m_128451_("Charge");
/*    */   }
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
/*    */   default boolean changeCharge(@NotNull Player player, @NotNull ItemStack stack, @Nullable InteractionHand hand) {
/* 57 */     int currentCharge = getCharge(stack);
/* 58 */     int numCharges = getNumCharges(stack);
/*    */     
/* 60 */     if (player.m_36341_()) {
/* 61 */       if (currentCharge > 0) {
/* 62 */         player.m_9236_().m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)PESounds.UNCHARGE.get(), SoundSource.PLAYERS, 1.0F, 0.5F + 0.5F / numCharges * currentCharge);
/*    */         
/* 64 */         stack.m_41784_().m_128405_("Charge", currentCharge - 1);
/* 65 */         return true;
/*    */       } 
/* 67 */     } else if (currentCharge < numCharges) {
/* 68 */       player.m_9236_().m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)PESounds.CHARGE.get(), SoundSource.PLAYERS, 1.0F, 0.5F + 0.5F / numCharges * currentCharge);
/*    */       
/* 70 */       stack.m_41784_().m_128405_("Charge", currentCharge + 1);
/* 71 */       return true;
/*    */     } 
/* 73 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\capabilities\item\IItemCharge.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */