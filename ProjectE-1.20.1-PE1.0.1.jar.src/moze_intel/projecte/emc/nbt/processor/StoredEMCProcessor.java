/*    */ package moze_intel.projecte.emc.nbt.processor;
/*    */ 
/*    */ import moze_intel.projecte.api.ItemInfo;
/*    */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*    */ import moze_intel.projecte.api.capabilities.item.IItemEmcHolder;
/*    */ import moze_intel.projecte.api.nbt.INBTProcessor;
/*    */ import moze_intel.projecte.api.nbt.NBTProcessor;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ @NBTProcessor
/*    */ public class StoredEMCProcessor
/*    */   implements INBTProcessor {
/*    */   public String getName() {
/* 15 */     return "StoredEMCProcessor";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 20 */     return "Increases the EMC value of the item to take into account any EMC the item has stored.";
/*    */   }
/*    */ 
/*    */   
/*    */   public long recalculateEMC(@NotNull ItemInfo info, long currentEMC) throws ArithmeticException {
/* 25 */     ItemStack stack = info.createStack();
/* 26 */     return ((Long)stack.getCapability(PECapabilities.EMC_HOLDER_ITEM_CAPABILITY).map(emcHolder -> Long.valueOf(Math.addExact(currentEMC, emcHolder.getStoredEmc(stack)))).orElse(Long.valueOf(currentEMC))).longValue();
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\nbt\processor\StoredEMCProcessor.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */