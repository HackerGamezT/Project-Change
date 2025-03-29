/*    */ package moze_intel.projecte.capability;
/*    */ 
/*    */ import java.util.List;
/*    */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*    */ import moze_intel.projecte.api.capabilities.item.IPedestalItem;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.entity.BlockEntity;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PedestalItemCapabilityWrapper
/*    */   extends BasicItemCapability<IPedestalItem>
/*    */   implements IPedestalItem
/*    */ {
/*    */   public Capability<IPedestalItem> getCapability() {
/* 19 */     return PECapabilities.PEDESTAL_ITEM_CAPABILITY;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public <PEDESTAL extends BlockEntity & moze_intel.projecte.api.block_entity.IDMPedestal> boolean updateInPedestal(@NotNull ItemStack stack, @NotNull Level level, @NotNull BlockPos pos, @NotNull PEDESTAL pedestal) {
/* 25 */     return getItem().updateInPedestal(stack, level, pos, (BlockEntity)pedestal);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public List<Component> getPedestalDescription() {
/* 31 */     return getItem().getPedestalDescription();
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\capability\PedestalItemCapabilityWrapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */