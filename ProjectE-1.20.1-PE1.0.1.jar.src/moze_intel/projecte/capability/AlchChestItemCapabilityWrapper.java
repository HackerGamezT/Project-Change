/*    */ package moze_intel.projecte.capability;
/*    */ 
/*    */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*    */ import moze_intel.projecte.api.capabilities.item.IAlchChestItem;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class AlchChestItemCapabilityWrapper
/*    */   extends BasicItemCapability<IAlchChestItem>
/*    */   implements IAlchChestItem {
/*    */   public Capability<IAlchChestItem> getCapability() {
/* 15 */     return PECapabilities.ALCH_CHEST_ITEM_CAPABILITY;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean updateInAlchChest(@NotNull Level level, @NotNull BlockPos pos, @NotNull ItemStack stack) {
/* 20 */     return getItem().updateInAlchChest(level, pos, stack);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\capability\AlchChestItemCapabilityWrapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */