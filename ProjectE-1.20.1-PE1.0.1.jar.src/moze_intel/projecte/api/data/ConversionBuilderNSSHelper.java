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
/*     */ @ParametersAreNonnullByDefault
/*     */ @MethodsReturnNonnullByDefault
/*     */ interface ConversionBuilderNSSHelper<BUILDER extends ConversionBuilder<BUILDER>>
/*     */ {
/*     */   BUILDER ingredient(NormalizedSimpleStack paramNormalizedSimpleStack, int paramInt);
/*     */   
/*     */   default BUILDER ingredient(NormalizedSimpleStack input) {
/*  39 */     return ingredient(input, 1);
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
/*     */   default BUILDER ingredient(ItemStack input) {
/*  51 */     return ingredient((NormalizedSimpleStack)NSSItem.createItem(input), input.m_41613_());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default BUILDER ingredient(ItemLike input) {
/*  60 */     return ingredient(input, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default BUILDER ingredient(ItemLike input, int amount) {
/*  70 */     return ingredient((NormalizedSimpleStack)NSSItem.createItem(input), amount);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default BUILDER ingredient(TagKey<Item> input) {
/*  79 */     return ingredient(input, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default BUILDER ingredient(TagKey<Item> input, int amount) {
/*  89 */     return ingredient((NormalizedSimpleStack)NSSItem.createTag(input), amount);
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
/*     */   default BUILDER ingredient(FluidStack input) {
/* 101 */     return ingredient((NormalizedSimpleStack)NSSFluid.createFluid(input), input.getAmount());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default BUILDER ingredient(Fluid input) {
/* 110 */     return ingredient(input, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default BUILDER ingredient(Fluid input, int amount) {
/* 120 */     return ingredient((NormalizedSimpleStack)NSSFluid.createFluid(input), amount);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default BUILDER ingredientFluid(TagKey<Fluid> input) {
/* 131 */     return ingredientFluid(input, 1);
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
/*     */   default BUILDER ingredientFluid(TagKey<Fluid> input, int amount) {
/* 143 */     return ingredient((NormalizedSimpleStack)NSSFluid.createTag(input), amount);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default BUILDER ingredient(String fake) {
/* 152 */     return ingredient(fake, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default BUILDER ingredient(String fake, int amount) {
/* 162 */     return ingredient(NSSFake.create(fake), amount);
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\data\ConversionBuilderNSSHelper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */