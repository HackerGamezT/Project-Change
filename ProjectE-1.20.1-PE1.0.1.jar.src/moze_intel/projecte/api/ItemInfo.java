/*     */ package moze_intel.projecte.api;
/*     */ 
/*     */ import java.util.Objects;
/*     */ import moze_intel.projecte.api.nss.NSSItem;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.tags.TagKey;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ItemInfo
/*     */ {
/*     */   @NotNull
/*     */   private final Item item;
/*     */   @Nullable
/*     */   private final CompoundTag nbt;
/*     */   
/*     */   private ItemInfo(@NotNull ItemLike item, @Nullable CompoundTag nbt) {
/*  31 */     this.item = item.m_5456_();
/*  32 */     this.nbt = (nbt != null && nbt.m_128456_()) ? null : nbt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ItemInfo fromItem(@NotNull ItemLike item, @Nullable CompoundTag nbt) {
/*  41 */     return new ItemInfo(item, nbt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ItemInfo fromItem(@NotNull ItemLike item) {
/*  50 */     return fromItem(item, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ItemInfo fromStack(@NotNull ItemStack stack) {
/*  59 */     return fromItem((ItemLike)stack.m_41720_(), stack.m_41783_());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static ItemInfo fromNSS(@NotNull NSSItem stack) {
/*  69 */     if (stack.representsTag()) {
/*  70 */       return null;
/*     */     }
/*  72 */     Item item = (Item)ForgeRegistries.ITEMS.getValue(stack.getResourceLocation());
/*  73 */     if (item == null) {
/*  74 */       return null;
/*     */     }
/*  76 */     return fromItem((ItemLike)item, stack.getNBT());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static ItemInfo read(@NotNull CompoundTag nbt) {
/*  88 */     if (nbt.m_128425_("item", 8)) {
/*  89 */       ResourceLocation registryName = ResourceLocation.m_135820_(nbt.m_128461_("item"));
/*  90 */       if (registryName == null) {
/*  91 */         return null;
/*     */       }
/*  93 */       Item item = (Item)ForgeRegistries.ITEMS.getValue(registryName);
/*  94 */       if (item == null) {
/*  95 */         return null;
/*     */       }
/*  97 */       if (nbt.m_128425_("nbt", 10)) {
/*  98 */         return fromItem((ItemLike)item, nbt.m_128469_("nbt"));
/*     */       }
/* 100 */       return fromItem((ItemLike)item, null);
/*     */     } 
/* 102 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Item getItem() {
/* 110 */     return this.item;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public CompoundTag getNBT() {
/* 121 */     return (this.nbt == null) ? null : this.nbt.m_6426_();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasNBT() {
/* 130 */     return (this.nbt != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean is(TagKey<Item> tag) {
/* 141 */     return getItem().m_204114_().m_203656_(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack createStack() {
/* 148 */     ItemStack stack = new ItemStack((ItemLike)this.item);
/* 149 */     CompoundTag nbt = getNBT();
/* 150 */     if (nbt != null)
/*     */     {
/* 152 */       stack.m_41751_(nbt);
/*     */     }
/* 154 */     return stack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundTag write(@NotNull CompoundTag nbt) {
/* 161 */     nbt.m_128359_("item", getRegistryName().toString());
/* 162 */     if (this.nbt != null) {
/* 163 */       nbt.m_128365_("nbt", (Tag)this.nbt);
/*     */     }
/* 165 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 170 */     int code = this.item.hashCode();
/* 171 */     if (this.nbt != null) {
/* 172 */       code = 31 * code + this.nbt.hashCode();
/*     */     }
/* 174 */     return code;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 179 */     if (o == this)
/* 180 */       return true; 
/* 181 */     if (o instanceof ItemInfo) { ItemInfo other = (ItemInfo)o;
/* 182 */       return (this.item == other.item && Objects.equals(this.nbt, other.nbt)); }
/*     */     
/* 184 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 189 */     if (this.nbt != null) {
/* 190 */       return "" + getRegistryName() + " " + getRegistryName();
/*     */     }
/* 192 */     return getRegistryName().toString();
/*     */   }
/*     */   
/*     */   private ResourceLocation getRegistryName() {
/* 196 */     return ForgeRegistries.ITEMS.getKey(this.item);
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\ItemInfo.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */