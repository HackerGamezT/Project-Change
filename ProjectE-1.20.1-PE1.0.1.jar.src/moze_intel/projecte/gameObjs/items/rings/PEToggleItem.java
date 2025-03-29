/*    */ package moze_intel.projecte.gameObjs.items.rings;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import moze_intel.projecte.api.capabilities.item.IModeChanger;
/*    */ import moze_intel.projecte.gameObjs.items.ItemPE;
/*    */ import moze_intel.projecte.gameObjs.registries.PESoundEvents;
/*    */ import moze_intel.projecte.utils.ItemHelper;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraft.sounds.SoundSource;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public abstract class PEToggleItem extends ItemPE implements IModeChanger {
/*    */   public PEToggleItem(Item.Properties props) {
/* 19 */     super(props);
/* 20 */     addItemCapability(moze_intel.projecte.capability.ModeChangerItemCapabilityWrapper::new);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_142522_(@NotNull ItemStack stack) {
/* 25 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte getMode(@NotNull ItemStack stack) {
/* 30 */     return ItemHelper.checkItemNBT(stack, "Active") ? 1 : 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean changeMode(@NotNull Player player, @NotNull ItemStack stack, InteractionHand hand) {
/* 35 */     CompoundTag nbt = stack.m_41784_();
/* 36 */     boolean isActive = nbt.m_128471_("Active");
/* 37 */     player.m_9236_().m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), isActive ? (SoundEvent)PESoundEvents.UNCHARGE.get() : (SoundEvent)PESoundEvents.HEAL.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
/* 38 */     nbt.m_128379_("Active", !isActive);
/* 39 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\rings\PEToggleItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */