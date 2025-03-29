/*    */ package moze_intel.projecte.emc.nbt.processor;
/*    */ 
/*    */ import java.util.Map;
/*    */ import moze_intel.projecte.api.ItemInfo;
/*    */ import moze_intel.projecte.api.nbt.INBTProcessor;
/*    */ import moze_intel.projecte.api.nbt.NBTProcessor;
/*    */ import moze_intel.projecte.utils.ItemInfoHelper;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.nbt.ListTag;
/*    */ import net.minecraft.nbt.Tag;
/*    */ import net.minecraft.world.item.enchantment.Enchantment;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ @NBTProcessor
/*    */ public class EnchantmentProcessor
/*    */   implements INBTProcessor
/*    */ {
/*    */   private static final long ENCH_EMC_BONUS = 5000L;
/*    */   
/*    */   public String getName() {
/* 22 */     return "EnchantmentProcessor";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 27 */     return "Increases the EMC value to take into account any enchantments on an item.";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAvailable() {
/* 33 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasPersistentNBT() {
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean usePersistentNBT() {
/* 44 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public long recalculateEMC(@NotNull ItemInfo info, long currentEMC) throws ArithmeticException {
/* 49 */     Map<Enchantment, Integer> enchants = ItemInfoHelper.getEnchantments(info);
/* 50 */     for (Map.Entry<Enchantment, Integer> entry : enchants.entrySet()) {
/* 51 */       int rarityWeight = ((Enchantment)entry.getKey()).m_44699_().m_44716_();
/* 52 */       if (rarityWeight > 0) {
/* 53 */         currentEMC = Math.addExact(currentEMC, Math.multiplyExact(5000L / rarityWeight, ((Integer)entry.getValue()).intValue()));
/*    */       }
/*    */     } 
/* 56 */     return currentEMC;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public CompoundTag getPersistentNBT(@NotNull ItemInfo info) {
/* 62 */     CompoundTag tag = info.getNBT();
/* 63 */     if (tag == null) {
/* 64 */       return null;
/*    */     }
/* 66 */     String location = ItemInfoHelper.getEnchantTagLocation(info);
/* 67 */     if (!tag.m_128425_(location, 9)) {
/* 68 */       return null;
/*    */     }
/* 70 */     CompoundTag toReturn = new CompoundTag();
/* 71 */     ListTag enchantments = tag.m_128437_(location, 10);
/*    */     
/* 73 */     toReturn.m_128365_(location, (Tag)enchantments);
/* 74 */     return toReturn;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\nbt\processor\EnchantmentProcessor.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */