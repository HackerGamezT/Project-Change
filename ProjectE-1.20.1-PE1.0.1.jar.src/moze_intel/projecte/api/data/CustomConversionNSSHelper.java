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
/*     */ @ParametersAreNonnullByDefault
/*     */ @MethodsReturnNonnullByDefault
/*     */ interface CustomConversionNSSHelper<BUILDER extends ConversionBuilder<BUILDER>>
/*     */ {
/*     */   BUILDER conversion(NormalizedSimpleStack paramNormalizedSimpleStack, int paramInt);
/*     */   
/*     */   default BUILDER conversion(NormalizedSimpleStack output) {
/*  41 */     return conversion(output, 1);
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
/*     */   default BUILDER conversion(ItemStack output) {
/*  53 */     return conversion((NormalizedSimpleStack)NSSItem.createItem(output), output.m_41613_());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default BUILDER conversion(ItemLike output) {
/*  62 */     return conversion(output, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default BUILDER conversion(ItemLike output, int amount) {
/*  72 */     return conversion((NormalizedSimpleStack)NSSItem.createItem(output), amount);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default BUILDER conversion(TagKey<Item> output) {
/*  81 */     return conversion(output, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default BUILDER conversion(TagKey<Item> output, int amount) {
/*  91 */     return conversion((NormalizedSimpleStack)NSSItem.createTag(output), amount);
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
/*     */   default BUILDER conversion(FluidStack output) {
/* 103 */     return conversion((NormalizedSimpleStack)NSSFluid.createFluid(output), output.getAmount());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default BUILDER conversion(Fluid output) {
/* 112 */     return conversion(output, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default BUILDER conversion(Fluid output, int amount) {
/* 122 */     return conversion((NormalizedSimpleStack)NSSFluid.createFluid(output), amount);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default BUILDER conversionFluid(TagKey<Fluid> output) {
/* 133 */     return conversionFluid(output, 1);
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
/*     */   default BUILDER conversionFluid(TagKey<Fluid> output, int amount) {
/* 145 */     return conversion((NormalizedSimpleStack)NSSFluid.createTag(output), amount);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default BUILDER conversion(String fake) {
/* 154 */     return conversion(fake, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default BUILDER conversion(String fake, int amount) {
/* 164 */     return conversion(NSSFake.create(fake), amount);
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\data\CustomConversionNSSHelper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */