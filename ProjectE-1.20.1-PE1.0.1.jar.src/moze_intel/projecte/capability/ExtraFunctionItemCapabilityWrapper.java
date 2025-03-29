/*    */ package moze_intel.projecte.capability;
/*    */ 
/*    */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*    */ import moze_intel.projecte.api.capabilities.item.IExtraFunction;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class ExtraFunctionItemCapabilityWrapper
/*    */   extends BasicItemCapability<IExtraFunction>
/*    */   implements IExtraFunction {
/*    */   public Capability<IExtraFunction> getCapability() {
/* 16 */     return PECapabilities.EXTRA_FUNCTION_ITEM_CAPABILITY;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean doExtraFunction(@NotNull ItemStack stack, @NotNull Player player, @Nullable InteractionHand hand) {
/* 21 */     return getItem().doExtraFunction(stack, player, hand);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\capability\ExtraFunctionItemCapabilityWrapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */