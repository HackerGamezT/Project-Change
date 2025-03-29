/*    */ package moze_intel.projecte.capability;
/*    */ 
/*    */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*    */ import moze_intel.projecte.api.capabilities.item.IModeChanger;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class ModeChangerItemCapabilityWrapper
/*    */   extends BasicItemCapability<IModeChanger>
/*    */   implements IModeChanger {
/*    */   public Capability<IModeChanger> getCapability() {
/* 16 */     return PECapabilities.MODE_CHANGER_ITEM_CAPABILITY;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte getMode(@NotNull ItemStack stack) {
/* 21 */     return getItem().getMode(stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean changeMode(@NotNull Player player, @NotNull ItemStack stack, @Nullable InteractionHand hand) {
/* 26 */     return getItem().changeMode(player, stack, hand);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\capability\ModeChangerItemCapabilityWrapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */