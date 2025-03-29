/*     */ package moze_intel.projecte.api.nss;
/*     */ 
/*     */ import com.mojang.datafixers.util.Either;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Function;
/*     */ import net.minecraft.core.HolderSet;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.tags.TagKey;
/*     */ import net.minecraft.world.level.material.Fluid;
/*     */ import net.minecraft.world.level.material.Fluids;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import net.minecraftforge.registries.tags.ITag;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class NSSFluid
/*     */   extends AbstractNBTNSSTag<Fluid>
/*     */ {
/*     */   private NSSFluid(@NotNull ResourceLocation resourceLocation, boolean isTag, @Nullable CompoundTag nbt) {
/*  24 */     super(resourceLocation, isTag, nbt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static NSSFluid createFluid(@NotNull FluidStack stack) {
/*  33 */     return createFluid(stack.getFluid(), stack.getTag());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static NSSFluid createFluid(@NotNull Fluid fluid) {
/*  41 */     return createFluid(fluid, (CompoundTag)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static NSSFluid createFluid(@NotNull Fluid fluid, @Nullable CompoundTag nbt) {
/*  49 */     if (fluid == Fluids.f_76191_) {
/*  50 */       throw new IllegalArgumentException("Can't make NSSFluid with an empty fluid");
/*     */     }
/*  52 */     ResourceLocation registryName = ForgeRegistries.FLUIDS.getKey(fluid);
/*  53 */     if (registryName == null) {
/*  54 */       throw new IllegalArgumentException("Can't make an NSSFluid with an unregistered fluid");
/*     */     }
/*     */     
/*  57 */     return createFluid(registryName, nbt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static NSSFluid createFluid(@NotNull ResourceLocation fluidID) {
/*  65 */     return createFluid(fluidID, (CompoundTag)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static NSSFluid createFluid(@NotNull ResourceLocation fluidID, @Nullable CompoundTag nbt) {
/*  73 */     return new NSSFluid(fluidID, false, nbt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static NSSFluid createTag(@NotNull ResourceLocation tagId) {
/*  81 */     return new NSSFluid(tagId, true, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static NSSFluid createTag(@NotNull TagKey<Fluid> tag) {
/*  89 */     return createTag(tag.f_203868_());
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isInstance(AbstractNSSTag<?> o) {
/*  94 */     return o instanceof NSSFluid;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getJsonPrefix() {
/* 100 */     return "FLUID|";
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getType() {
/* 106 */     return "Fluid";
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   protected Optional<Either<HolderSet.Named<Fluid>, ITag<Fluid>>> getTag() {
/* 112 */     return getTag(ForgeRegistries.FLUIDS);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Function<Fluid, NormalizedSimpleStack> createNew() {
/* 117 */     return NSSFluid::createFluid;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\nss\NSSFluid.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */