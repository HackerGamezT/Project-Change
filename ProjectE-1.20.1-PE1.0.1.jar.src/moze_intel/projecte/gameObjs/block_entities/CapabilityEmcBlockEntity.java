/*    */ package moze_intel.projecte.gameObjs.block_entities;
/*    */ 
/*    */ import moze_intel.projecte.capability.managing.ICapabilityResolver;
/*    */ import moze_intel.projecte.gameObjs.registration.impl.BlockEntityTypeRegistryObject;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.core.Direction;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.ForgeCapabilities;
/*    */ import net.minecraftforge.common.util.LazyOptional;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public abstract class CapabilityEmcBlockEntity
/*    */   extends EmcBlockEntity
/*    */ {
/*    */   @Nullable
/*    */   protected ICapabilityResolver<IItemHandler> itemHandlerResolver;
/*    */   
/*    */   public CapabilityEmcBlockEntity(BlockEntityTypeRegistryObject<? extends CapabilityEmcBlockEntity> type, BlockPos pos, BlockState state) {
/* 22 */     super((BlockEntityTypeRegistryObject)type, pos, state);
/*    */   }
/*    */ 
/*    */   
/*    */   public CapabilityEmcBlockEntity(BlockEntityTypeRegistryObject<? extends CapabilityEmcBlockEntity> type, BlockPos pos, BlockState state, long maxAmount) {
/* 27 */     super((BlockEntityTypeRegistryObject)type, pos, state, maxAmount);
/*    */   }
/*    */ 
/*    */   
/*    */   public void invalidateCaps() {
/* 32 */     super.invalidateCaps();
/* 33 */     if (this.itemHandlerResolver != null)
/*    */     {
/* 35 */       this.itemHandlerResolver.invalidateAll();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, Direction side) {
/* 42 */     if (cap == ForgeCapabilities.ITEM_HANDLER && this.itemHandlerResolver != null)
/*    */     {
/* 44 */       return this.itemHandlerResolver.getCapabilityUnchecked(cap, side);
/*    */     }
/* 46 */     return super.getCapability(cap, side);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\block_entities\CapabilityEmcBlockEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */