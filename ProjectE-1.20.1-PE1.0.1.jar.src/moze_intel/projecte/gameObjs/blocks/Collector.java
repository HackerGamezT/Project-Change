/*     */ package moze_intel.projecte.gameObjs.blocks;
/*     */ import java.util.Optional;
/*     */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*     */ import moze_intel.projecte.api.capabilities.item.IItemEmcHolder;
/*     */ import moze_intel.projecte.gameObjs.EnumCollectorTier;
/*     */ import moze_intel.projecte.gameObjs.block_entities.CollectorMK1BlockEntity;
/*     */ import moze_intel.projecte.gameObjs.registration.impl.BlockEntityTypeRegistryObject;
/*     */ import moze_intel.projecte.gameObjs.registries.PEBlockEntityTypes;
/*     */ import moze_intel.projecte.utils.MathUtils;
/*     */ import moze_intel.projecte.utils.WorldHelper;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.MenuProvider;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.BlockHitResult;
/*     */ import net.minecraftforge.common.capabilities.ForgeCapabilities;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import net.minecraftforge.network.NetworkHooks;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class Collector extends BlockDirection implements PEEntityBlock<CollectorMK1BlockEntity> {
/*     */   private final EnumCollectorTier tier;
/*     */   
/*     */   public Collector(EnumCollectorTier tier, BlockBehaviour.Properties props) {
/*  33 */     super(props);
/*  34 */     this.tier = tier;
/*     */   }
/*     */   
/*     */   public EnumCollectorTier getTier() {
/*  38 */     return this.tier;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public InteractionResult m_6227_(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
/*  46 */     if (level.f_46443_) {
/*  47 */       return InteractionResult.SUCCESS;
/*     */     }
/*  49 */     CollectorMK1BlockEntity collector = (CollectorMK1BlockEntity)WorldHelper.getBlockEntity(CollectorMK1BlockEntity.class, (BlockGetter)level, pos, true);
/*  50 */     if (collector != null) {
/*  51 */       NetworkHooks.openScreen((ServerPlayer)player, (MenuProvider)collector, pos);
/*     */     }
/*  53 */     return InteractionResult.CONSUME;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BlockEntityTypeRegistryObject<? extends CollectorMK1BlockEntity> getType() {
/*  59 */     switch (this.tier) { default: throw new IncompatibleClassChangeError();case MK1: case MK2: case MK3: break; }  return 
/*     */ 
/*     */       
/*  62 */       PEBlockEntityTypes.COLLECTOR_MK3;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean m_8133_(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, int id, int param) {
/*  69 */     super.m_8133_(state, level, pos, id, param);
/*  70 */     return triggerBlockEntityEvent(state, level, pos, id, param);
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean m_7278_(@NotNull BlockState state) {
/*  76 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public int m_6782_(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos) {
/*  82 */     CollectorMK1BlockEntity collector = (CollectorMK1BlockEntity)WorldHelper.getBlockEntity(CollectorMK1BlockEntity.class, (BlockGetter)level, pos, true);
/*  83 */     if (collector == null)
/*     */     {
/*  85 */       return super.m_6782_(state, level, pos);
/*     */     }
/*  87 */     Optional<IItemHandler> cap = collector.getCapability(ForgeCapabilities.ITEM_HANDLER, Direction.UP).resolve();
/*  88 */     if (cap.isEmpty())
/*     */     {
/*  90 */       return super.m_6782_(state, level, pos);
/*     */     }
/*  92 */     ItemStack charging = ((IItemHandler)cap.get()).getStackInSlot(0);
/*  93 */     if (!charging.m_41619_()) {
/*  94 */       Optional<IItemEmcHolder> holderCapability = charging.getCapability(PECapabilities.EMC_HOLDER_ITEM_CAPABILITY).resolve();
/*  95 */       if (holderCapability.isPresent()) {
/*  96 */         IItemEmcHolder emcHolder = holderCapability.get();
/*  97 */         return MathUtils.scaleToRedstone(emcHolder.getStoredEmc(charging), emcHolder.getMaximumEmc(charging));
/*     */       } 
/*  99 */       return MathUtils.scaleToRedstone(collector.getStoredEmc(), collector.getEmcToNextGoal());
/*     */     } 
/* 101 */     return MathUtils.scaleToRedstone(collector.getStoredEmc(), collector.getMaximumEmc());
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void m_6810_(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState newState, boolean isMoving) {
/* 107 */     if (state.m_60734_() != newState.m_60734_()) {
/* 108 */       CollectorMK1BlockEntity ent = (CollectorMK1BlockEntity)WorldHelper.getBlockEntity(CollectorMK1BlockEntity.class, (BlockGetter)level, pos);
/* 109 */       if (ent != null)
/*     */       {
/* 111 */         ent.clearLocked();
/*     */       }
/* 113 */       super.m_6810_(state, level, pos, newState, isMoving);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\blocks\Collector.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */