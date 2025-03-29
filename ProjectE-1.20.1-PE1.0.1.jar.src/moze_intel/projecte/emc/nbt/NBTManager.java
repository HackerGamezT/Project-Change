/*    */ package moze_intel.projecte.emc.nbt;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import moze_intel.projecte.api.ItemInfo;
/*    */ import moze_intel.projecte.api.nbt.INBTProcessor;
/*    */ import moze_intel.projecte.config.NBTProcessorConfig;
/*    */ import moze_intel.projecte.emc.EMCMappingHandler;
/*    */ import moze_intel.projecte.gameObjs.PETags;
/*    */ import moze_intel.projecte.utils.AnnotationHelper;
/*    */ import moze_intel.projecte.utils.ItemHelper;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.world.level.ItemLike;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class NBTManager
/*    */ {
/* 18 */   private static final List<INBTProcessor> processors = new ArrayList<>();
/*    */   
/*    */   public static void loadProcessors() {
/* 21 */     if (processors.isEmpty()) {
/* 22 */       processors.addAll(AnnotationHelper.getNBTProcessors());
/*    */       
/* 24 */       NBTProcessorConfig.setup(processors);
/*    */     } 
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static ItemInfo getPersistentInfo(@NotNull ItemInfo info) {
/* 30 */     if (!info.hasNBT() || info.is(PETags.Items.NBT_WHITELIST) || EMCMappingHandler.hasEmcValue(info))
/*    */     {
/* 32 */       return info;
/*    */     }
/*    */     
/* 35 */     List<CompoundTag> persistentNBT = new ArrayList<>();
/* 36 */     for (INBTProcessor processor : processors) {
/* 37 */       if (NBTProcessorConfig.isEnabled(processor) && processor.hasPersistentNBT() && NBTProcessorConfig.hasPersistent(processor)) {
/* 38 */         CompoundTag nbt = processor.getPersistentNBT(info);
/* 39 */         if (nbt != null) {
/* 40 */           persistentNBT.add(nbt);
/*    */         }
/*    */       } 
/*    */     } 
/* 44 */     return ItemInfo.fromItem((ItemLike)info.getItem(), ItemHelper.recombineNBT(persistentNBT));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static long getEmcValue(@NotNull ItemInfo info) {
/* 51 */     long emcValue = EMCMappingHandler.getStoredEmcValue(info);
/* 52 */     if (!info.hasNBT())
/*    */     {
/* 54 */       return emcValue; } 
/* 55 */     if (emcValue == 0L) {
/*    */       
/* 57 */       emcValue = EMCMappingHandler.getStoredEmcValue(ItemInfo.fromItem((ItemLike)info.getItem()));
/* 58 */       if (emcValue == 0L)
/*    */       {
/* 60 */         return 0L;
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 65 */     for (INBTProcessor processor : processors) {
/* 66 */       if (NBTProcessorConfig.isEnabled(processor)) {
/*    */         try {
/* 68 */           emcValue = processor.recalculateEMC(info, emcValue);
/* 69 */         } catch (ArithmeticException e) {
/*    */           
/* 71 */           return emcValue;
/*    */         } 
/* 73 */         if (emcValue <= 0L)
/*    */         {
/* 75 */           return 0L;
/*    */         }
/*    */       } 
/*    */     } 
/* 79 */     return emcValue;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\nbt\NBTManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */