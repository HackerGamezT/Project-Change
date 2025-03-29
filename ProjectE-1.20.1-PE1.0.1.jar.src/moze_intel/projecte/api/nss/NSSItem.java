/*     */ package moze_intel.projecte.api.nss;
/*     */ 
/*     */ import com.mojang.datafixers.util.Either;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Function;
/*     */ import net.minecraft.core.HolderSet;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.tags.TagKey;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.Items;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import net.minecraftforge.registries.tags.ITag;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class NSSItem
/*     */   extends AbstractNBTNSSTag<Item>
/*     */ {
/*     */   private NSSItem(@NotNull ResourceLocation resourceLocation, boolean isTag, @Nullable CompoundTag nbt) {
/*  25 */     super(resourceLocation, isTag, nbt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static NSSItem createItem(@NotNull ItemStack stack) {
/*  33 */     if (stack.m_41619_()) {
/*  34 */       throw new IllegalArgumentException("Can't make NSSItem with empty stack");
/*     */     }
/*  36 */     if (stack.m_41763_() && stack.m_41782_())
/*     */     {
/*     */ 
/*     */       
/*  40 */       if (stack.m_41784_().equals((new ItemStack((ItemLike)stack.m_41720_())).m_41783_()))
/*     */       {
/*  42 */         return createItem((ItemLike)stack.m_41720_(), (CompoundTag)null);
/*     */       }
/*     */     }
/*  45 */     return createItem((ItemLike)stack.m_41720_(), stack.m_41783_());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static NSSItem createItem(@NotNull ItemLike itemProvider) {
/*  53 */     return createItem(itemProvider, (CompoundTag)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static NSSItem createItem(@NotNull ItemLike itemProvider, @Nullable CompoundTag nbt) {
/*  61 */     Item item = itemProvider.m_5456_();
/*  62 */     if (item == Items.f_41852_) {
/*  63 */       throw new IllegalArgumentException("Can't make NSSItem with empty stack");
/*     */     }
/*  65 */     ResourceLocation registryName = ForgeRegistries.ITEMS.getKey(item);
/*  66 */     if (registryName == null) {
/*  67 */       throw new IllegalArgumentException("Can't make an NSSItem with an unregistered item");
/*     */     }
/*     */     
/*  70 */     return createItem(registryName, nbt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static NSSItem createItem(@NotNull ResourceLocation itemID) {
/*  78 */     return createItem(itemID, (CompoundTag)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static NSSItem createItem(@NotNull ResourceLocation itemID, @Nullable CompoundTag nbt) {
/*  86 */     return new NSSItem(itemID, false, nbt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static NSSItem createTag(@NotNull ResourceLocation tagId) {
/*  94 */     return new NSSItem(tagId, true, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static NSSItem createTag(@NotNull TagKey<Item> tag) {
/* 102 */     return createTag(tag.f_203868_());
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isInstance(AbstractNSSTag<?> o) {
/* 107 */     return o instanceof NSSItem;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getJsonPrefix() {
/* 114 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getType() {
/* 120 */     return "Item";
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   protected Optional<Either<HolderSet.Named<Item>, ITag<Item>>> getTag() {
/* 126 */     return getTag(ForgeRegistries.ITEMS);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Function<Item, NormalizedSimpleStack> createNew() {
/* 131 */     return NSSItem::createItem;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\nss\NSSItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */