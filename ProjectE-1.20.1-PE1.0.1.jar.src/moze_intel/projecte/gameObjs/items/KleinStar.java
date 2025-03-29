/*     */ package moze_intel.projecte.gameObjs.items;
/*     */ 
/*     */ import java.util.function.Supplier;
/*     */ import moze_intel.projecte.api.capabilities.block_entity.IEmcStorage;
/*     */ import moze_intel.projecte.api.capabilities.item.IItemEmcHolder;
/*     */ import moze_intel.projecte.integration.IntegrationHelper;
/*     */ import moze_intel.projecte.utils.EMCHelper;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResultHolder;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraftforge.fml.loading.FMLEnvironment;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class KleinStar
/*     */   extends ItemPE implements IItemEmcHolder, IBarHelper {
/*     */   public final EnumKleinTier tier;
/*     */   
/*     */   public KleinStar(Item.Properties props, EnumKleinTier tier) {
/*  22 */     super(props);
/*  23 */     this.tier = tier;
/*  24 */     addItemCapability(moze_intel.projecte.capability.EmcHolderItemCapabilityWrapper::new);
/*  25 */     addItemCapability("curios", IntegrationHelper.CURIO_CAP_SUPPLIER);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_142522_(@NotNull ItemStack stack) {
/*  30 */     return stack.m_41782_();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getWidthForBar(ItemStack stack) {
/*  35 */     long starEmc = getEmc(stack);
/*  36 */     if (starEmc == 0L) {
/*  37 */       return 1.0F;
/*     */     }
/*  39 */     return (float)(1.0D - starEmc / EMCHelper.getKleinStarMaxEmc(stack));
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_142158_(@NotNull ItemStack stack) {
/*  44 */     return getScaledBarWidth(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_142159_(@NotNull ItemStack stack) {
/*  49 */     return getColorForBar(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResultHolder<ItemStack> m_7203_(Level level, Player player, @NotNull InteractionHand hand) {
/*  55 */     ItemStack stack = player.m_21120_(hand);
/*  56 */     if (!level.f_46443_ && !FMLEnvironment.production) {
/*  57 */       setEmc(stack, EMCHelper.getKleinStarMaxEmc(stack));
/*  58 */       return InteractionResultHolder.m_19090_(stack);
/*     */     } 
/*  60 */     return InteractionResultHolder.m_19098_(stack);
/*     */   }
/*     */   
/*     */   public enum EnumKleinTier {
/*  64 */     EIN("ein"),
/*  65 */     ZWEI("zwei"),
/*  66 */     DREI("drei"),
/*  67 */     VIER("vier"),
/*  68 */     SPHERE("sphere"),
/*  69 */     OMEGA("omega");
/*     */     
/*     */     public final String name;
/*     */     
/*     */     EnumKleinTier(String name) {
/*  74 */       this.name = name;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long insertEmc(@NotNull ItemStack stack, long toInsert, IEmcStorage.EmcAction action) {
/*  82 */     if (toInsert < 0L) {
/*  83 */       return extractEmc(stack, -toInsert, action);
/*     */     }
/*  85 */     long toAdd = Math.min(getNeededEmc(stack), toInsert);
/*  86 */     if (action.execute()) {
/*  87 */       ItemPE.addEmcToStack(stack, toAdd);
/*     */     }
/*  89 */     return toAdd;
/*     */   }
/*     */ 
/*     */   
/*     */   public long extractEmc(@NotNull ItemStack stack, long toExtract, IEmcStorage.EmcAction action) {
/*  94 */     if (toExtract < 0L) {
/*  95 */       return insertEmc(stack, -toExtract, action);
/*     */     }
/*  97 */     long storedEmc = getStoredEmc(stack);
/*  98 */     long toRemove = Math.min(storedEmc, toExtract);
/*  99 */     if (action.execute()) {
/* 100 */       ItemPE.setEmc(stack, storedEmc - toRemove);
/*     */     }
/* 102 */     return toRemove;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getStoredEmc(@NotNull ItemStack stack) {
/* 108 */     return ItemPE.getEmc(stack);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getMaximumEmc(@NotNull ItemStack stack) {
/* 114 */     return EMCHelper.getKleinStarMaxEmc(stack);
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\KleinStar.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */