/*     */ package moze_intel.projecte;
/*     */ 
/*     */ import java.util.Optional;
/*     */ import moze_intel.projecte.config.ProjectEConfig;
/*     */ import moze_intel.projecte.gameObjs.registries.PESoundEvents;
/*     */ import moze_intel.projecte.utils.WorldHelper;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.BlockSource;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.DispenserBlock;
/*     */ import net.minecraft.world.level.block.LayeredCauldronBlock;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.block.state.properties.Property;
/*     */ import net.minecraft.world.level.material.Fluid;
/*     */ import net.minecraft.world.level.material.Fluids;
/*     */ import net.minecraftforge.common.capabilities.ForgeCapabilities;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.capability.IFluidHandler;
/*     */ import org.jetbrains.annotations.NotNull;
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
/*     */ class null
/*     */   extends DefaultDispenseItemBehavior
/*     */ {
/*     */   @NotNull
/*     */   public ItemStack m_7498_(@NotNull BlockSource source, @NotNull ItemStack stack) {
/* 254 */     ServerLevel serverLevel = source.m_7727_();
/* 255 */     Direction direction = (Direction)source.m_6414_().m_61143_((Property)DispenserBlock.f_52659_);
/* 256 */     BlockPos pos = source.m_7961_().m_121945_(direction);
/* 257 */     BlockEntity blockEntity = WorldHelper.getBlockEntity((BlockGetter)serverLevel, pos);
/* 258 */     Direction sideHit = direction.m_122424_();
/* 259 */     if (blockEntity != null) {
/* 260 */       Optional<IFluidHandler> capability = blockEntity.getCapability(ForgeCapabilities.FLUID_HANDLER, sideHit).resolve();
/* 261 */       if (capability.isPresent()) {
/* 262 */         ((IFluidHandler)capability.get()).fill(new FluidStack((Fluid)Fluids.f_76193_, 1000), IFluidHandler.FluidAction.EXECUTE);
/* 263 */         return stack;
/*     */       } 
/*     */     } 
/* 266 */     BlockState state = serverLevel.m_8055_(pos);
/* 267 */     if (state.m_60734_() == Blocks.f_50256_) {
/* 268 */       serverLevel.m_46597_(pos, (BlockState)Blocks.f_152476_.m_49966_().m_61124_((Property)LayeredCauldronBlock.f_153514_, Integer.valueOf(1)));
/* 269 */       return stack;
/* 270 */     }  if (state.m_60734_() == Blocks.f_152476_) {
/* 271 */       if (!((LayeredCauldronBlock)state.m_60734_()).m_142596_(state)) {
/* 272 */         serverLevel.m_46597_(pos, (BlockState)state.m_61124_((Property)LayeredCauldronBlock.f_153514_, Integer.valueOf(((Integer)state.m_61143_((Property)LayeredCauldronBlock.f_153514_)).intValue() + 1)));
/* 273 */         return stack;
/*     */       } 
/*     */     } else {
/* 276 */       WorldHelper.placeFluid(null, (Level)serverLevel, pos, Fluids.f_76193_, !ProjectEConfig.server.items.opEvertide.get());
/* 277 */       serverLevel.m_6263_(null, pos.m_123341_(), pos.m_123342_(), pos.m_123343_(), (SoundEvent)PESoundEvents.WATER_MAGIC.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
/* 278 */       return stack;
/*     */     } 
/* 280 */     return super.m_7498_(source, stack);
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\PECore$2.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */