/*    */ package moze_intel.projecte.emc.nbt.processor;
/*    */ 
/*    */ import moze_intel.projecte.api.ItemInfo;
/*    */ import moze_intel.projecte.api.nbt.INBTProcessor;
/*    */ import moze_intel.projecte.api.nbt.NBTProcessor;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ @NBTProcessor(priority = 2147483647)
/*    */ public class DamageProcessor
/*    */   implements INBTProcessor
/*    */ {
/*    */   public String getName() {
/* 15 */     return "DamageProcessor";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 20 */     return "Reduces the EMC value the more damaged an item is.";
/*    */   }
/*    */ 
/*    */   
/*    */   public long recalculateEMC(@NotNull ItemInfo info, long currentEMC) throws ArithmeticException {
/* 25 */     Item item = info.getItem();
/* 26 */     if (item.m_41465_()) {
/* 27 */       ItemStack fakeStack = info.createStack();
/* 28 */       int maxDamage = item.getMaxDamage(fakeStack);
/* 29 */       int damage = item.getDamage(fakeStack);
/* 30 */       if (damage > maxDamage)
/*    */       {
/* 32 */         throw new ArithmeticException();
/*    */       }
/*    */ 
/*    */       
/* 36 */       currentEMC = Math.multiplyExact(currentEMC, Math.addExact(maxDamage - damage, 1)) / maxDamage;
/*    */     } 
/* 38 */     return currentEMC;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\nbt\processor\DamageProcessor.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */