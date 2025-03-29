/*    */ package moze_intel.projecte.gameObjs.blocks;
/*    */ import moze_intel.projecte.gameObjs.EnumMatterType;
/*    */ import moze_intel.projecte.gameObjs.block_entities.DMFurnaceBlockEntity;
/*    */ import moze_intel.projecte.gameObjs.registration.impl.BlockEntityTypeRegistryObject;
/*    */ import moze_intel.projecte.gameObjs.registries.PEBlockEntityTypes;
/*    */ import moze_intel.projecte.utils.WorldHelper;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.MenuProvider;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.level.BlockGetter;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.AbstractFurnaceBlock;
/*    */ import net.minecraft.world.level.block.entity.BlockEntity;
/*    */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraftforge.common.capabilities.ForgeCapabilities;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import net.minecraftforge.items.ItemHandlerHelper;
/*    */ import net.minecraftforge.network.NetworkHooks;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class MatterFurnace extends AbstractFurnaceBlock implements IMatterBlock, PEEntityBlock<DMFurnaceBlockEntity> {
/*    */   public MatterFurnace(BlockBehaviour.Properties props, EnumMatterType type) {
/* 26 */     super(props);
/* 27 */     this.matterType = type;
/*    */   }
/*    */   private final EnumMatterType matterType;
/*    */   
/*    */   @Nullable
/*    */   public BlockEntityTypeRegistryObject<? extends DMFurnaceBlockEntity> getType() {
/* 33 */     return (this.matterType == EnumMatterType.RED_MATTER) ? PEBlockEntityTypes.RED_MATTER_FURNACE : PEBlockEntityTypes.DARK_MATTER_FURNACE;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void m_7137_(Level level, @NotNull BlockPos pos, @NotNull Player player) {
/* 38 */     if (!level.f_46443_) {
/* 39 */       DMFurnaceBlockEntity furnace = (DMFurnaceBlockEntity)WorldHelper.getBlockEntity(DMFurnaceBlockEntity.class, (BlockGetter)level, pos, true);
/* 40 */       if (furnace != null) {
/* 41 */         NetworkHooks.openScreen((ServerPlayer)player, (MenuProvider)furnace, pos);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public void m_6810_(BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState newState, boolean isMoving) {
/* 49 */     if (state.m_60734_() != newState.m_60734_()) {
/* 50 */       BlockEntity furnace = WorldHelper.getBlockEntity((BlockGetter)level, pos);
/* 51 */       if (furnace != null) {
/* 52 */         furnace.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(inv -> WorldHelper.dropInventory(inv, level, pos));
/*    */       }
/* 54 */       super.m_6810_(state, level, pos, newState, isMoving);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int m_6782_(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos) {
/* 60 */     BlockEntity blockEntity = WorldHelper.getBlockEntity((BlockGetter)level, pos);
/* 61 */     if (blockEntity != null) {
/* 62 */       return ((Integer)blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).map(ItemHandlerHelper::calcRedstoneFromInventory).orElse(Integer.valueOf(0))).intValue();
/*    */     }
/* 64 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumMatterType getMatterType() {
/* 69 */     return this.matterType;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\blocks\MatterFurnace.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */