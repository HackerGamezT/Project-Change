/*    */ package moze_intel.projecte.capability;
/*    */ 
/*    */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*    */ import moze_intel.projecte.api.capabilities.item.IItemCharge;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class ChargeItemCapabilityWrapper
/*    */   extends BasicItemCapability<IItemCharge>
/*    */   implements IItemCharge {
/*    */   public Capability<IItemCharge> getCapability() {
/* 13 */     return PECapabilities.CHARGE_ITEM_CAPABILITY;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNumCharges(@NotNull ItemStack stack) {
/* 18 */     return getItem().getNumCharges(stack);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\capability\ChargeItemCapabilityWrapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */