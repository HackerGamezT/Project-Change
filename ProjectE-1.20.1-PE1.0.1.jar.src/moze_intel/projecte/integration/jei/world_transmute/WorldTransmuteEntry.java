/*     */ package moze_intel.projecte.integration.jei.world_transmute;
/*     */ 
/*     */ import com.mojang.datafixers.util.Either;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import moze_intel.projecte.api.imc.WorldTransmutationEntry;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.LiquidBlock;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.IFluidBlock;
/*     */ 
/*     */ public class WorldTransmuteEntry {
/*     */   private static final class StateInfo extends Record {
/*     */     private final ItemStack item;
/*     */     private final FluidStack fluid;
/*     */     
/*  19 */     private StateInfo(ItemStack item, FluidStack fluid) { this.item = item; this.fluid = fluid; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lmoze_intel/projecte/integration/jei/world_transmute/WorldTransmuteEntry$StateInfo;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #19	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*  19 */       //   0	7	0	this	Lmoze_intel/projecte/integration/jei/world_transmute/WorldTransmuteEntry$StateInfo; } public ItemStack item() { return this.item; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/integration/jei/world_transmute/WorldTransmuteEntry$StateInfo;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #19	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lmoze_intel/projecte/integration/jei/world_transmute/WorldTransmuteEntry$StateInfo; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lmoze_intel/projecte/integration/jei/world_transmute/WorldTransmuteEntry$StateInfo;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #19	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lmoze_intel/projecte/integration/jei/world_transmute/WorldTransmuteEntry$StateInfo;
/*  19 */       //   0	8	1	o	Ljava/lang/Object; } public FluidStack fluid() { return this.fluid; }
/*     */     
/*     */     public boolean isEmpty() {
/*  22 */       return (this.item.m_41619_() && this.fluid.isEmpty());
/*     */     }
/*     */     
/*     */     public Either<ItemStack, FluidStack> toEither() {
/*  26 */       if (this.fluid.isEmpty()) {
/*  27 */         return Either.left(this.item);
/*     */       }
/*  29 */       return Either.right(this.fluid);
/*     */     }
/*     */   }
/*     */   
/*  33 */   private static final StateInfo EMPTY = new StateInfo(ItemStack.f_41583_, FluidStack.EMPTY);
/*     */   
/*     */   private final StateInfo input;
/*     */   private final StateInfo leftOutput;
/*     */   private final StateInfo rightOutput;
/*     */   
/*     */   public WorldTransmuteEntry(WorldTransmutationEntry transmutationEntry) {
/*  40 */     BlockState leftOutputState = transmutationEntry.result();
/*  41 */     BlockState rightOutputState = transmutationEntry.altResult();
/*  42 */     if (leftOutputState == rightOutputState)
/*     */     {
/*  44 */       rightOutputState = null;
/*     */     }
/*  46 */     this.input = createInfo(transmutationEntry.origin());
/*  47 */     this.leftOutput = createInfo(leftOutputState);
/*  48 */     this.rightOutput = createInfo(rightOutputState);
/*     */   }
/*     */   
/*     */   private StateInfo createInfo(@Nullable BlockState output) {
/*  52 */     if (output == null) {
/*  53 */       return EMPTY;
/*     */     }
/*  55 */     FluidStack outputFluid = fluidFromBlock(output.m_60734_());
/*  56 */     if (outputFluid.isEmpty()) {
/*  57 */       return new StateInfo(itemFromBlock(output.m_60734_(), output), outputFluid);
/*     */     }
/*  59 */     return new StateInfo(ItemStack.f_41583_, outputFluid);
/*     */   }
/*     */   
/*     */   private FluidStack fluidFromBlock(Block block) {
/*  63 */     if (block instanceof LiquidBlock) { LiquidBlock liquidBlock = (LiquidBlock)block;
/*  64 */       return new FluidStack((Fluid)liquidBlock.getFluid(), 1000); }
/*  65 */      if (block instanceof IFluidBlock) { IFluidBlock fluidBlock = (IFluidBlock)block;
/*  66 */       return new FluidStack(fluidBlock.getFluid(), 1000); }
/*     */     
/*  68 */     return FluidStack.EMPTY;
/*     */   }
/*     */ 
/*     */   
/*     */   private ItemStack itemFromBlock(Block block, BlockState state) {
/*     */     try {
/*  74 */       return block.getCloneItemStack(state, null, null, null, null);
/*  75 */     } catch (Exception e) {
/*     */       
/*  77 */       return new ItemStack((ItemLike)block);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isRenderable() {
/*  82 */     return (!this.input.isEmpty() && (!this.leftOutput.isEmpty() || !this.rightOutput.isEmpty()));
/*     */   }
/*     */   
/*     */   public Optional<Either<ItemStack, FluidStack>> getInput() {
/*  86 */     if (this.input.isEmpty()) {
/*  87 */       return Optional.empty();
/*     */     }
/*  89 */     return Optional.of(this.input.toEither());
/*     */   }
/*     */   
/*     */   public Iterable<Either<ItemStack, FluidStack>> getOutput() {
/*  93 */     List<Either<ItemStack, FluidStack>> outputs = new ArrayList<>();
/*  94 */     if (!this.leftOutput.isEmpty()) {
/*  95 */       outputs.add(this.leftOutput.toEither());
/*     */     }
/*  97 */     if (!this.rightOutput.isEmpty()) {
/*  98 */       outputs.add(this.rightOutput.toEither());
/*     */     }
/* 100 */     return outputs;
/*     */   }
/*     */   
/*     */   public ItemStack getInputItem() {
/* 104 */     return this.input.item();
/*     */   }
/*     */   
/*     */   public FluidStack getInputFluid() {
/* 108 */     return this.input.fluid();
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\jei\world_transmute\WorldTransmuteEntry.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */