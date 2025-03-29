/*    */ package moze_intel.projecte.utils;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ import moze_intel.projecte.api.ItemInfo;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.nbt.ListTag;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.item.Items;
/*    */ import net.minecraft.world.item.alchemy.Potion;
/*    */ import net.minecraft.world.item.alchemy.Potions;
/*    */ import net.minecraft.world.item.enchantment.Enchantment;
/*    */ import net.minecraft.world.level.ItemLike;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemInfoHelper
/*    */ {
/*    */   public static Map<Enchantment, Integer> getEnchantments(ItemInfo info) {
/* 26 */     CompoundTag tag = info.getNBT();
/* 27 */     if (tag == null) {
/* 28 */       return Collections.emptyMap();
/*    */     }
/* 30 */     String location = getEnchantTagLocation(info);
/* 31 */     if (!tag.m_128425_(location, 9)) {
/* 32 */       return Collections.emptyMap();
/*    */     }
/* 34 */     Map<Enchantment, Integer> map = new LinkedHashMap<>();
/* 35 */     ListTag enchantments = tag.m_128437_(location, 10);
/* 36 */     for (int i = 0; i < enchantments.size(); i++) {
/* 37 */       CompoundTag enchantNBT = enchantments.m_128728_(i);
/* 38 */       ResourceLocation enchantmentID = ResourceLocation.m_135820_(enchantNBT.m_128461_("id"));
/* 39 */       if (enchantmentID != null) {
/* 40 */         Enchantment enchantment = (Enchantment)ForgeRegistries.ENCHANTMENTS.getValue(enchantmentID);
/* 41 */         if (enchantment != null) {
/* 42 */           map.put(enchantment, Integer.valueOf(enchantNBT.m_128451_("lvl")));
/*    */         }
/*    */       } 
/*    */     } 
/* 46 */     return map;
/*    */   }
/*    */   
/*    */   public static String getEnchantTagLocation(@NotNull ItemInfo info) {
/* 50 */     return (info.getItem() == Items.f_42690_) ? "StoredEnchantments" : "Enchantments";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ItemInfo makeWithPotion(ItemInfo info, Potion potion) {
/* 57 */     CompoundTag nbt = info.getNBT();
/* 58 */     if (potion == Potions.f_43598_) {
/* 59 */       if (nbt != null && nbt.m_128441_("Potion")) {
/* 60 */         nbt.m_128473_("Potion");
/* 61 */         if (nbt.m_128456_()) {
/* 62 */           nbt = null;
/*    */         }
/*    */       } 
/*    */     } else {
/* 66 */       if (nbt == null) {
/* 67 */         nbt = new CompoundTag();
/*    */       }
/* 69 */       nbt.m_128359_("Potion", RegistryUtils.getName(potion).toString());
/*    */     } 
/* 71 */     return ItemInfo.fromItem((ItemLike)info.getItem(), nbt);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\project\\utils\ItemInfoHelper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */