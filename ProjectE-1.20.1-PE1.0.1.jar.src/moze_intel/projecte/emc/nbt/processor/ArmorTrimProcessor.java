/*    */ package moze_intel.projecte.emc.nbt.processor;
/*    */ import com.mojang.serialization.DynamicOps;
/*    */ import moze_intel.projecte.api.ItemInfo;
/*    */ import moze_intel.projecte.api.nbt.NBTProcessor;
/*    */ import moze_intel.projecte.utils.EMCHelper;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.core.HolderLookup;
/*    */ import net.minecraft.core.RegistryAccess;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.nbt.NbtOps;
/*    */ import net.minecraft.nbt.Tag;
/*    */ import net.minecraft.resources.RegistryOps;
/*    */ import net.minecraft.tags.ItemTags;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.armortrim.ArmorTrim;
/*    */ import net.minecraft.world.item.armortrim.TrimMaterial;
/*    */ import net.minecraft.world.level.ItemLike;
/*    */ import net.minecraftforge.fml.loading.FMLEnvironment;
/*    */ import net.minecraftforge.server.ServerLifecycleHooks;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ @NBTProcessor
/*    */ public class ArmorTrimProcessor implements INBTProcessor {
/*    */   public String getName() {
/* 26 */     return "ArmorTrimProcessor";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 31 */     return "Calculates EMC value of trimmed armor.";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasPersistentNBT() {
/* 36 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public long recalculateEMC(@NotNull ItemInfo info, long currentEMC) throws ArithmeticException {
/* 41 */     if (info.is(ItemTags.f_265942_)) {
/* 42 */       CompoundTag tag = info.getNBT();
/* 43 */       if (tag != null && tag.m_128425_("Trim", 10)) {
/* 44 */         RegistryAccess.Frozen frozen; CompoundTag compoundtag = tag.m_128469_("Trim");
/*    */         
/* 46 */         if (FMLEnvironment.dist.isClient()) {
/*    */           
/* 48 */           RegistryAccess registryAccess = ((Minecraft.m_91087_()).f_91073_ == null) ? null : (Minecraft.m_91087_()).f_91073_.m_9598_();
/*    */         } else {
/* 50 */           frozen = (ServerLifecycleHooks.getCurrentServer() == null) ? null : ServerLifecycleHooks.getCurrentServer().m_206579_();
/*    */         } 
/* 52 */         if (frozen == null)
/*    */         {
/* 54 */           return 0L;
/*    */         }
/*    */ 
/*    */         
/* 58 */         ArmorTrim armortrim = ArmorTrim.f_265985_.parse((DynamicOps)RegistryOps.m_255058_((DynamicOps)NbtOps.f_128958_, (HolderLookup.Provider)frozen), compoundtag).result().orElse(null);
/* 59 */         if (armortrim != null) {
/* 60 */           Item material = (Item)((TrimMaterial)armortrim.m_266210_().m_203334_()).f_265970_().m_203334_();
/* 61 */           Item template = (Item)((TrimPattern)armortrim.m_266429_().m_203334_()).f_265847_().m_203334_();
/* 62 */           return Math.addExact(
/* 63 */               Math.addExact(currentEMC, EMCHelper.getEmcValue((ItemLike)material)), 
/* 64 */               EMCHelper.getEmcValue((ItemLike)template));
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 69 */     return currentEMC;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public CompoundTag getPersistentNBT(@NotNull ItemInfo info) {
/* 75 */     if (info.is(ItemTags.f_265942_)) {
/* 76 */       CompoundTag tag = info.getNBT();
/* 77 */       if (tag != null && tag.m_128425_("Trim", 10)) {
/*    */         
/* 79 */         CompoundTag toReturn = new CompoundTag();
/* 80 */         toReturn.m_128365_("Trim", (Tag)tag.m_128469_("Trim"));
/* 81 */         return toReturn;
/*    */       } 
/*    */     } 
/* 84 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\nbt\processor\ArmorTrimProcessor.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */