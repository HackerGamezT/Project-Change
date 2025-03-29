/*     */ package moze_intel.projecte.gameObjs.items;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import moze_intel.projecte.api.capabilities.item.IItemCharge;
/*     */ import moze_intel.projecte.gameObjs.registries.PESoundEvents;
/*     */ import moze_intel.projecte.utils.WorldHelper;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.core.particles.ParticleTypes;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.context.UseOnContext;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class DestructionCatalyst extends ItemPE implements IItemCharge, IBarHelper {
/*     */   public DestructionCatalyst(Item.Properties props) {
/*  27 */     super(props);
/*  28 */     addItemCapability(moze_intel.projecte.capability.ChargeItemCapabilityWrapper::new);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResult m_6225_(UseOnContext ctx) {
/*  34 */     Player player = ctx.m_43723_();
/*  35 */     if (player == null) {
/*  36 */       return InteractionResult.FAIL;
/*     */     }
/*  38 */     Level level = ctx.m_43725_();
/*  39 */     if (level.f_46443_) {
/*  40 */       return InteractionResult.SUCCESS;
/*     */     }
/*  42 */     ItemStack stack = ctx.m_43722_();
/*  43 */     int numRows = calculateDepthFromCharge(stack);
/*  44 */     boolean hasAction = false;
/*  45 */     List<ItemStack> drops = new ArrayList<>();
/*  46 */     for (BlockPos pos : WorldHelper.getPositionsFromBox(WorldHelper.getDeepBox(ctx.m_8083_(), ctx.m_43719_(), --numRows))) {
/*  47 */       if (level.m_46859_(pos)) {
/*     */         continue;
/*     */       }
/*  50 */       BlockState state = level.m_8055_(pos);
/*  51 */       float hardness = state.m_60800_((BlockGetter)level, pos);
/*  52 */       if (hardness == -1.0F || hardness >= 50.0F) {
/*     */         continue;
/*     */       }
/*  55 */       if (!consumeFuel(player, stack, 8L, true)) {
/*     */         break;
/*     */       }
/*  58 */       hasAction = true;
/*     */       
/*  60 */       pos = pos.m_7949_();
/*  61 */       if (PlayerHelper.hasBreakPermission((ServerPlayer)player, pos)) {
/*  62 */         List<ItemStack> list = Block.m_49874_(state, (ServerLevel)level, pos, WorldHelper.getBlockEntity((BlockGetter)level, pos), (Entity)player, stack);
/*  63 */         drops.addAll(list);
/*  64 */         level.m_7471_(pos, false);
/*  65 */         if (level.f_46441_.m_188503_(8) == 0) {
/*  66 */           ((ServerLevel)level).m_8767_(level.f_46441_.m_188499_() ? (ParticleOptions)ParticleTypes.f_123759_ : (ParticleOptions)ParticleTypes.f_123755_, pos.m_123341_(), pos.m_123342_(), pos.m_123343_(), 2, 0.0D, 0.0D, 0.0D, 0.05D);
/*     */         }
/*     */       } 
/*     */     } 
/*  70 */     if (hasAction) {
/*  71 */       WorldHelper.createLootDrop(drops, level, ctx.m_8083_());
/*  72 */       level.m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)PESoundEvents.DESTRUCT.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
/*     */     } 
/*  74 */     return InteractionResult.CONSUME;
/*     */   }
/*     */   
/*     */   private int calculateDepthFromCharge(ItemStack stack) {
/*  78 */     int charge = getCharge(stack);
/*  79 */     if (charge <= 0) {
/*  80 */       return 1;
/*     */     }
/*  82 */     if (this instanceof CataliticLens) {
/*  83 */       return 8 + 8 * charge;
/*     */     }
/*  85 */     return (int)Math.pow(2.0D, (1 + charge));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumCharges(@NotNull ItemStack stack) {
/*  90 */     return 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_142522_(@NotNull ItemStack stack) {
/*  95 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getWidthForBar(ItemStack stack) {
/* 100 */     return 1.0F - getChargePercent(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_142158_(@NotNull ItemStack stack) {
/* 105 */     return getScaledBarWidth(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_142159_(@NotNull ItemStack stack) {
/* 110 */     return getColorForBar(stack);
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\DestructionCatalyst.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */