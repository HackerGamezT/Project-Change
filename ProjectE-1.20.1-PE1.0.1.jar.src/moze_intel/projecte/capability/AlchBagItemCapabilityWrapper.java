/*    */ package moze_intel.projecte.capability;
/*    */ 
/*    */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*    */ import moze_intel.projecte.api.capabilities.item.IAlchBagItem;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class AlchBagItemCapabilityWrapper
/*    */   extends BasicItemCapability<IAlchBagItem>
/*    */   implements IAlchBagItem {
/*    */   public Capability<IAlchBagItem> getCapability() {
/* 15 */     return PECapabilities.ALCH_BAG_ITEM_CAPABILITY;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean updateInAlchBag(@NotNull IItemHandler inv, @NotNull Player player, @NotNull ItemStack stack) {
/* 20 */     return getItem().updateInAlchBag(inv, player, stack);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\capability\AlchBagItemCapabilityWrapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */