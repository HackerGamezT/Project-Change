/*     */ package moze_intel.projecte.gameObjs.items;
/*     */ import it.unimi.dsi.fastutil.longs.LongArrayList;
/*     */ import it.unimi.dsi.fastutil.longs.LongComparators;
/*     */ import java.util.List;
/*     */ import java.util.function.Supplier;
/*     */ import moze_intel.projecte.gameObjs.registries.PEItems;
/*     */ import moze_intel.projecte.utils.EMCHelper;
/*     */ import moze_intel.projecte.utils.WorldHelper;
/*     */ import moze_intel.projecte.utils.text.ILangEntry;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.TooltipFlag;
/*     */ import net.minecraft.world.item.context.UseOnContext;
/*     */ import net.minecraft.world.item.crafting.Ingredient;
/*     */ import net.minecraft.world.item.crafting.RecipeType;
/*     */ import net.minecraft.world.item.crafting.SmeltingRecipe;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraftforge.common.util.NonNullLazy;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class DiviningRod extends ItemPE implements IItemMode {
/*     */   private final ILangEntry[] modes;
/*     */   
/*     */   public DiviningRod(Item.Properties props, ILangEntry... modeDesc) {
/*  36 */     super(props);
/*  37 */     this.modes = modeDesc;
/*  38 */     this.maxModes = this.modes.length;
/*  39 */     addItemCapability(moze_intel.projecte.capability.ModeChangerItemCapabilityWrapper::new);
/*     */   }
/*     */   private final int maxModes;
/*     */   
/*     */   @NotNull
/*     */   public InteractionResult m_6225_(UseOnContext ctx) {
/*  45 */     Player player = ctx.m_43723_();
/*  46 */     if (player == null) {
/*  47 */       return InteractionResult.FAIL;
/*     */     }
/*  49 */     Level level = ctx.m_43725_();
/*  50 */     if (level.f_46443_) {
/*  51 */       return InteractionResult.SUCCESS;
/*     */     }
/*  53 */     LongArrayList longArrayList = new LongArrayList();
/*  54 */     long totalEmc = 0L;
/*  55 */     int numBlocks = 0;
/*  56 */     int depth = getDepthFromMode(ctx.m_43722_());
/*     */     
/*  58 */     NonNullLazy<List<SmeltingRecipe>> furnaceRecipes = NonNullLazy.of(() -> level.m_7465_().m_44013_(RecipeType.f_44108_));
/*  59 */     for (BlockPos digPos : WorldHelper.getPositionsFromBox(WorldHelper.getDeepBox(ctx.m_8083_(), ctx.m_43719_(), depth))) {
/*  60 */       if (level.m_46859_(digPos)) {
/*     */         continue;
/*     */       }
/*  63 */       BlockState state = level.m_8055_(digPos);
/*  64 */       List<ItemStack> drops = Block.m_49874_(state, (ServerLevel)level, digPos, WorldHelper.getBlockEntity((BlockGetter)level, digPos), (Entity)player, ctx.m_43722_());
/*  65 */       if (drops.isEmpty()) {
/*     */         continue;
/*     */       }
/*  68 */       ItemStack blockStack = drops.get(0);
/*  69 */       long blockEmc = EMCHelper.getEmcValue(blockStack);
/*  70 */       if (blockEmc == 0L) {
/*  71 */         for (SmeltingRecipe furnaceRecipe : furnaceRecipes.get()) {
/*  72 */           if (((Ingredient)furnaceRecipe.m_7527_().get(0)).test(blockStack)) {
/*  73 */             long currentValue = EMCHelper.getEmcValue(furnaceRecipe.m_8043_(level.m_9598_()));
/*  74 */             if (currentValue != 0L) {
/*  75 */               if (!longArrayList.contains(currentValue)) {
/*  76 */                 longArrayList.add(currentValue);
/*     */               }
/*  78 */               totalEmc += currentValue;
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } else {
/*  84 */         if (!longArrayList.contains(blockEmc)) {
/*  85 */           longArrayList.add(blockEmc);
/*     */         }
/*  87 */         totalEmc += blockEmc;
/*     */       } 
/*  89 */       numBlocks++;
/*     */     } 
/*     */     
/*  92 */     if (numBlocks == 0) {
/*  93 */       return InteractionResult.FAIL;
/*     */     }
/*  95 */     player.m_213846_((Component)PELang.DIVINING_AVG_EMC.translate(new Object[] { Integer.valueOf(numBlocks), Long.valueOf(totalEmc / numBlocks) }));
/*  96 */     if (this == PEItems.MEDIUM_DIVINING_ROD.get() || this == PEItems.HIGH_DIVINING_ROD.get()) {
/*  97 */       long[] maxValues = new long[3];
/*  98 */       for (int i = 0; i < 3; i++) {
/*  99 */         maxValues[i] = 1L;
/*     */       }
/* 101 */       longArrayList.sort(LongComparators.OPPOSITE_COMPARATOR);
/* 102 */       int num = Math.min(longArrayList.size(), 3);
/* 103 */       for (int j = 0; j < num; j++) {
/* 104 */         maxValues[j] = longArrayList.getLong(j);
/*     */       }
/* 106 */       player.m_213846_((Component)PELang.DIVINING_MAX_EMC.translate(new Object[] { Long.valueOf(maxValues[0]) }));
/* 107 */       if (this == PEItems.HIGH_DIVINING_ROD.get()) {
/* 108 */         player.m_213846_((Component)PELang.DIVINING_SECOND_MAX.translate(new Object[] { Long.valueOf(maxValues[1]) }));
/* 109 */         player.m_213846_((Component)PELang.DIVINING_THIRD_MAX.translate(new Object[] { Long.valueOf(maxValues[2]) }));
/*     */       } 
/*     */     } 
/* 112 */     return InteractionResult.CONSUME;
/*     */   }
/*     */   
/*     */   private int getDepthFromMode(ItemStack stack) {
/* 116 */     byte mode = getMode(stack);
/* 117 */     if (mode < 0 || mode >= this.maxModes)
/*     */     {
/* 119 */       return 0; } 
/* 120 */     if (mode == 0)
/* 121 */       return 3; 
/* 122 */     if (mode == 1) {
/* 123 */       return 16;
/*     */     }
/* 125 */     return 64;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILangEntry[] getModeLangEntries() {
/* 130 */     return this.modes;
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_7373_(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltips, @NotNull TooltipFlag flags) {
/* 135 */     super.m_7373_(stack, level, tooltips, flags);
/* 136 */     tooltips.add(getToolTip(stack));
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\DiviningRod.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */