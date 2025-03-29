/*    */ package moze_intel.projecte.impl;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import moze_intel.projecte.api.ItemInfo;
/*    */ import moze_intel.projecte.api.proxy.IEMCProxy;
/*    */ import moze_intel.projecte.emc.nbt.NBTManager;
/*    */ import moze_intel.projecte.utils.EMCHelper;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EMCProxyImpl
/*    */   implements IEMCProxy
/*    */ {
/*    */   public long getValue(@NotNull ItemInfo info) {
/* 16 */     return EMCHelper.getEmcValue(Objects.<ItemInfo>requireNonNull(info));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public long getSellValue(@NotNull ItemInfo info) {
/* 22 */     return EMCHelper.getEmcSellValue(Objects.<ItemInfo>requireNonNull(info));
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemInfo getPersistentInfo(@NotNull ItemInfo info) {
/* 28 */     return NBTManager.getPersistentInfo(info);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\impl\EMCProxyImpl.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */