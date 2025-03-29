/*    */ package moze_intel.projecte.emc.nbt.processor;
/*    */ 
/*    */ import moze_intel.projecte.api.ItemInfo;
/*    */ import moze_intel.projecte.api.nbt.INBTProcessor;
/*    */ import moze_intel.projecte.api.nbt.NBTProcessor;
/*    */ import moze_intel.projecte.utils.EMCHelper;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.nbt.Tag;
/*    */ import net.minecraft.world.item.Items;
/*    */ import net.minecraft.world.level.ItemLike;
/*    */ import net.minecraft.world.level.block.entity.DecoratedPotBlockEntity;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ @NBTProcessor
/*    */ public class DecoratedPotProcessor
/*    */   implements INBTProcessor {
/*    */   public String getName() {
/* 19 */     return "DecoratedPotProcessor";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 24 */     return "Takes the different sherds into account for each decorated pot.";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasPersistentNBT() {
/* 29 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public long recalculateEMC(@NotNull ItemInfo info, long currentEMC) throws ArithmeticException {
/* 34 */     if (info.getItem() == Items.f_271478_) {
/* 35 */       CompoundTag tag = info.getNBT();
/* 36 */       if (tag != null && tag.m_128425_("BlockEntityTag", 10)) {
/* 37 */         CompoundTag beTag = tag.m_128469_("BlockEntityTag");
/* 38 */         DecoratedPotBlockEntity.Decorations decorations = DecoratedPotBlockEntity.Decorations.m_284207_(beTag);
/* 39 */         if (!decorations.equals(DecoratedPotBlockEntity.Decorations.f_283770_)) {
/*    */ 
/*    */ 
/*    */           
/* 43 */           long decorationEmc = decorations.m_284195_().mapToLong(EMCHelper::getEmcValue).reduce(0L, Math::addExact);
/*    */           
/* 45 */           return Math.addExact(currentEMC - EMCHelper.getEmcValue((ItemLike)Items.f_271478_), decorationEmc);
/*    */         } 
/*    */       } 
/*    */     } 
/* 49 */     return currentEMC;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public CompoundTag getPersistentNBT(@NotNull ItemInfo info) {
/* 55 */     if (info.getItem() == Items.f_271478_) {
/* 56 */       CompoundTag tag = info.getNBT();
/* 57 */       if (tag != null && tag.m_128425_("BlockEntityTag", 10)) {
/* 58 */         CompoundTag beTag = tag.m_128469_("BlockEntityTag");
/* 59 */         if (beTag.m_128425_("sherds", 9)) {
/* 60 */           CompoundTag toReturnIntermediary = new CompoundTag();
/*    */           
/* 62 */           toReturnIntermediary.m_128365_("sherds", (Tag)beTag.m_128437_("sherds", 8));
/* 63 */           CompoundTag toReturn = new CompoundTag();
/* 64 */           toReturn.m_128365_("BlockEntityTag", (Tag)toReturnIntermediary);
/* 65 */           return toReturn;
/*    */         } 
/*    */       } 
/*    */     } 
/* 69 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\nbt\processor\DecoratedPotProcessor.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */