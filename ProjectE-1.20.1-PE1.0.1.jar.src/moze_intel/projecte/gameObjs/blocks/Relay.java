/*    */ package moze_intel.projecte.gameObjs.blocks;
/*    */ import moze_intel.projecte.gameObjs.EnumRelayTier;
/*    */ import moze_intel.projecte.gameObjs.block_entities.RelayMK1BlockEntity;
/*    */ import moze_intel.projecte.gameObjs.registration.impl.BlockEntityTypeRegistryObject;
/*    */ import moze_intel.projecte.gameObjs.registries.PEBlockEntityTypes;
/*    */ import moze_intel.projecte.utils.MathUtils;
/*    */ import moze_intel.projecte.utils.WorldHelper;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.InteractionResult;
/*    */ import net.minecraft.world.MenuProvider;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.level.BlockGetter;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.phys.BlockHitResult;
/*    */ import net.minecraftforge.network.NetworkHooks;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class Relay extends BlockDirection implements PEEntityBlock<RelayMK1BlockEntity> {
/*    */   private final EnumRelayTier tier;
/*    */   
/*    */   public Relay(EnumRelayTier tier, BlockBehaviour.Properties props) {
/* 26 */     super(props);
/* 27 */     this.tier = tier;
/*    */   }
/*    */   
/*    */   public EnumRelayTier getTier() {
/* 31 */     return this.tier;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   @NotNull
/*    */   public InteractionResult m_6227_(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult rtr) {
/* 39 */     if (level.f_46443_) {
/* 40 */       return InteractionResult.SUCCESS;
/*    */     }
/* 42 */     RelayMK1BlockEntity relay = (RelayMK1BlockEntity)WorldHelper.getBlockEntity(RelayMK1BlockEntity.class, (BlockGetter)level, pos, true);
/* 43 */     if (relay != null) {
/* 44 */       NetworkHooks.openScreen((ServerPlayer)player, (MenuProvider)relay, pos);
/*    */     }
/* 46 */     return InteractionResult.CONSUME;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public BlockEntityTypeRegistryObject<? extends RelayMK1BlockEntity> getType() {
/* 52 */     switch (this.tier) { default: throw new IncompatibleClassChangeError();case MK1: case MK2: case MK3: break; }  return 
/*    */ 
/*    */       
/* 55 */       PEBlockEntityTypes.RELAY_MK3;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public boolean m_8133_(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, int id, int param) {
/* 62 */     super.m_8133_(state, level, pos, id, param);
/* 63 */     return triggerBlockEntityEvent(state, level, pos, id, param);
/*    */   }
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public boolean m_7278_(@NotNull BlockState state) {
/* 69 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public int m_6782_(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos) {
/* 75 */     RelayMK1BlockEntity relay = (RelayMK1BlockEntity)WorldHelper.getBlockEntity(RelayMK1BlockEntity.class, (BlockGetter)level, pos, true);
/* 76 */     if (relay == null) {
/* 77 */       return 0;
/*    */     }
/* 79 */     return MathUtils.scaleToRedstone(relay.getStoredEmc(), relay.getMaximumEmc());
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\blocks\Relay.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */