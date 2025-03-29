/*     */ package moze_intel.projecte.api.data;
/*     */ 
/*     */ import javax.annotation.ParametersAreNonnullByDefault;
/*     */ import moze_intel.projecte.api.nss.NSSFake;
/*     */ import moze_intel.projecte.api.nss.NSSFluid;
/*     */ import moze_intel.projecte.api.nss.NSSItem;
/*     */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*     */ import net.minecraft.MethodsReturnNonnullByDefault;
/*     */ import net.minecraft.tags.TagKey;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraft.world.level.material.Fluid;
/*     */ import net.minecraftforge.fluids.FluidStack;
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
/*     */ @ParametersAreNonnullByDefault
/*     */ @MethodsReturnNonnullByDefault
/*     */ interface CustomConversionBuilderNSSHelper
/*     */   extends CustomConversionNSSHelper<CustomConversionBuilder.FixedValueConversionBuilder>
/*     */ {
/*     */   CustomConversionBuilder before(NormalizedSimpleStack paramNormalizedSimpleStack, long paramLong);
/*     */   
/*     */   CustomConversionBuilder before(NormalizedSimpleStack paramNormalizedSimpleStack);
/*     */   
/*     */   CustomConversionBuilder after(NormalizedSimpleStack paramNormalizedSimpleStack, long paramLong);
/*     */   
/*     */   CustomConversionBuilder after(NormalizedSimpleStack paramNormalizedSimpleStack);
/*     */   
/*     */   default CustomConversionBuilder before(ItemStack stack, long emc) {
/*  66 */     return before((NormalizedSimpleStack)NSSItem.createItem(stack), emc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default CustomConversionBuilder before(ItemLike itemProvider, long emc) {
/*  76 */     return before((NormalizedSimpleStack)NSSItem.createItem(itemProvider), emc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default CustomConversionBuilder before(TagKey<Item> tag, long emc) {
/*  86 */     return before((NormalizedSimpleStack)NSSItem.createTag(tag), emc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default CustomConversionBuilder before(ItemStack stack) {
/*  98 */     return before((NormalizedSimpleStack)NSSItem.createItem(stack));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default CustomConversionBuilder before(ItemLike itemProvider) {
/* 107 */     return before((NormalizedSimpleStack)NSSItem.createItem(itemProvider));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default CustomConversionBuilder before(TagKey<Item> tag) {
/* 116 */     return before((NormalizedSimpleStack)NSSItem.createTag(tag));
/*     */   }
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
/*     */   default CustomConversionBuilder after(ItemStack stack, long emc) {
/* 129 */     return after((NormalizedSimpleStack)NSSItem.createItem(stack), emc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default CustomConversionBuilder after(ItemLike itemProvider, long emc) {
/* 139 */     return after((NormalizedSimpleStack)NSSItem.createItem(itemProvider), emc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default CustomConversionBuilder after(TagKey<Item> tag, long emc) {
/* 149 */     return after((NormalizedSimpleStack)NSSItem.createTag(tag), emc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default CustomConversionBuilder after(ItemStack stack) {
/* 161 */     return after((NormalizedSimpleStack)NSSItem.createItem(stack));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default CustomConversionBuilder after(ItemLike itemProvider) {
/* 170 */     return after((NormalizedSimpleStack)NSSItem.createItem(itemProvider));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default CustomConversionBuilder after(TagKey<Item> tag) {
/* 179 */     return after((NormalizedSimpleStack)NSSItem.createTag(tag));
/*     */   }
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
/*     */   default CustomConversionBuilder before(FluidStack stack, long emc) {
/* 192 */     return before((NormalizedSimpleStack)NSSFluid.createFluid(stack), emc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default CustomConversionBuilder before(Fluid fluid, long emc) {
/* 202 */     return before((NormalizedSimpleStack)NSSFluid.createFluid(fluid), emc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default CustomConversionBuilder beforeFluid(TagKey<Fluid> tag, long emc) {
/* 214 */     return before((NormalizedSimpleStack)NSSFluid.createTag(tag), emc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default CustomConversionBuilder before(FluidStack stack) {
/* 226 */     return before((NormalizedSimpleStack)NSSFluid.createFluid(stack));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default CustomConversionBuilder before(Fluid fluid) {
/* 235 */     return before((NormalizedSimpleStack)NSSFluid.createFluid(fluid));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default CustomConversionBuilder beforeFluid(TagKey<Fluid> tag) {
/* 246 */     return before((NormalizedSimpleStack)NSSFluid.createTag(tag));
/*     */   }
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
/*     */   default CustomConversionBuilder after(FluidStack stack, long emc) {
/* 259 */     return after((NormalizedSimpleStack)NSSFluid.createFluid(stack), emc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default CustomConversionBuilder after(Fluid fluid, long emc) {
/* 269 */     return after((NormalizedSimpleStack)NSSFluid.createFluid(fluid), emc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default CustomConversionBuilder afterFluid(TagKey<Fluid> tag, long emc) {
/* 281 */     return after((NormalizedSimpleStack)NSSFluid.createTag(tag), emc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default CustomConversionBuilder after(FluidStack stack) {
/* 293 */     return after((NormalizedSimpleStack)NSSFluid.createFluid(stack));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default CustomConversionBuilder after(Fluid fluid) {
/* 302 */     return after((NormalizedSimpleStack)NSSFluid.createFluid(fluid));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default CustomConversionBuilder afterFluid(TagKey<Fluid> tag) {
/* 313 */     return after((NormalizedSimpleStack)NSSFluid.createTag(tag));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default CustomConversionBuilder before(String fake, long emc) {
/* 323 */     return before(NSSFake.create(fake), emc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default CustomConversionBuilder before(String fake) {
/* 332 */     return before(NSSFake.create(fake));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default CustomConversionBuilder after(String fake, long emc) {
/* 342 */     return after(NSSFake.create(fake), emc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default CustomConversionBuilder after(String fake) {
/* 351 */     return after(NSSFake.create(fake));
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\data\CustomConversionBuilderNSSHelper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */