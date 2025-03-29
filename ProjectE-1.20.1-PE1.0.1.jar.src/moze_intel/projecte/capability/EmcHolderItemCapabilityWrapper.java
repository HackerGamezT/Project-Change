/*    */ package moze_intel.projecte.capability;
/*    */ 
/*    */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*    */ import moze_intel.projecte.api.capabilities.block_entity.IEmcStorage;
/*    */ import moze_intel.projecte.api.capabilities.item.IItemEmcHolder;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class EmcHolderItemCapabilityWrapper
/*    */   extends BasicItemCapability<IItemEmcHolder>
/*    */   implements IItemEmcHolder
/*    */ {
/*    */   public Capability<IItemEmcHolder> getCapability() {
/* 15 */     return PECapabilities.EMC_HOLDER_ITEM_CAPABILITY;
/*    */   }
/*    */ 
/*    */   
/*    */   public long insertEmc(@NotNull ItemStack stack, long toInsert, IEmcStorage.EmcAction action) {
/* 20 */     return getItem().insertEmc(stack, toInsert, action);
/*    */   }
/*    */ 
/*    */   
/*    */   public long extractEmc(@NotNull ItemStack stack, long toExtract, IEmcStorage.EmcAction action) {
/* 25 */     return getItem().extractEmc(stack, toExtract, action);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public long getStoredEmc(@NotNull ItemStack stack) {
/* 31 */     return getItem().getStoredEmc(stack);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public long getMaximumEmc(@NotNull ItemStack stack) {
/* 37 */     return getItem().getMaximumEmc(stack);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\capability\EmcHolderItemCapabilityWrapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */