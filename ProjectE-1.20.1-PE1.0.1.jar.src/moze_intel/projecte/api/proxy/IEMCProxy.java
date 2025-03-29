/*     */ package moze_intel.projecte.api.proxy;
/*     */ 
/*     */ import java.util.Objects;
/*     */ import java.util.ServiceLoader;
/*     */ import moze_intel.projecte.api.ItemInfo;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.Items;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface IEMCProxy
/*     */ {
/*  18 */   public static final IEMCProxy INSTANCE = (IEMCProxy)ServiceLoader.<IEMCProxy>load(IEMCProxy.class).findFirst()
/*  19 */     .orElseThrow(() -> new IllegalStateException("No valid ServiceImpl for IEMCProxy found, ProjectE may be absent, damaged, or outdated"));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default boolean hasValue(@NotNull Block block) {
/*  33 */     return hasValue(((Block)Objects.<Block>requireNonNull(block)).m_5456_());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default boolean hasValue(@NotNull Item item) {
/*  48 */     return (Objects.requireNonNull(item) != Items.f_41852_ && hasValue(ItemInfo.fromItem((ItemLike)item)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default boolean hasValue(@NotNull ItemStack stack) {
/*  65 */     return (!((ItemStack)Objects.<ItemStack>requireNonNull(stack)).m_41619_() && hasValue(ItemInfo.fromStack(stack)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default boolean hasValue(@NotNull ItemInfo info) {
/*  80 */     return (getValue(Objects.<ItemInfo>requireNonNull(info)) > 0L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default long getValue(@NotNull Block block) {
/*  96 */     return getValue(((Block)Objects.<Block>requireNonNull(block)).m_5456_());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default long getValue(@NotNull Item item) {
/* 112 */     return (Objects.requireNonNull(item) == Items.f_41852_) ? 0L : getValue(ItemInfo.fromItem((ItemLike)item));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default long getValue(@NotNull ItemStack stack) {
/* 130 */     return ((ItemStack)Objects.<ItemStack>requireNonNull(stack)).m_41619_() ? 0L : getValue(ItemInfo.fromStack(stack));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default long getSellValue(@NotNull ItemStack stack) {
/* 162 */     return ((ItemStack)Objects.<ItemStack>requireNonNull(stack)).m_41619_() ? 0L : getSellValue(ItemInfo.fromStack(stack));
/*     */   }
/*     */   
/*     */   long getValue(@NotNull ItemInfo paramItemInfo);
/*     */   
/*     */   long getSellValue(@NotNull ItemInfo paramItemInfo);
/*     */   
/*     */   @NotNull
/*     */   ItemInfo getPersistentInfo(@NotNull ItemInfo paramItemInfo);
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\proxy\IEMCProxy.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */