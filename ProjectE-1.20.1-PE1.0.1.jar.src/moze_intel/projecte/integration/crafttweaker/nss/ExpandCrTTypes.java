/*     */ package moze_intel.projecte.integration.crafttweaker.nss;
/*     */ 
/*     */ import com.blamejared.crafttweaker.api.annotation.ZenRegister;
/*     */ import com.blamejared.crafttweaker.api.fluid.IFluidStack;
/*     */ import com.blamejared.crafttweaker.api.item.IItemStack;
/*     */ import com.blamejared.crafttweaker.api.tag.type.KnownTag;
/*     */ import com.blamejared.crafttweaker_annotations.annotations.TypedExpansion;
/*     */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.level.material.Fluid;
/*     */ import org.openzen.zencode.java.ZenCodeType.Caster;
/*     */ import org.openzen.zencode.java.ZenCodeType.Expansion;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExpandCrTTypes
/*     */ {
/*     */   @ZenRegister
/*     */   @TypedExpansion(Item.class)
/*     */   public static class ItemExpansion
/*     */   {
/*     */     @Caster(implicit = true)
/*     */     public static NormalizedSimpleStack asNormalizedSimpleStack(Item _this) {
/*  33 */       return CrTNSSResolver.fromItem(_this);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ZenRegister
/*     */   @TypedExpansion(IItemStack.class)
/*     */   public static class IItemStackExpansion
/*     */   {
/*     */     @Caster(implicit = true)
/*     */     public static NormalizedSimpleStack asNormalizedSimpleStack(IItemStack _this) {
/*  49 */       return CrTNSSResolver.fromItem(_this);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ZenRegister
/*     */   @Expansion("crafttweaker.api.tag.type.KnownTag<crafttweaker.api.item.ItemDefinition>")
/*     */   public static class ItemTagExpansion
/*     */   {
/*     */     @Caster(implicit = true)
/*     */     public static NormalizedSimpleStack asNormalizedSimpleStack(KnownTag<Item> _this) {
/*  65 */       return CrTNSSResolver.fromItemTag(_this);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ZenRegister
/*     */   @TypedExpansion(Fluid.class)
/*     */   public static class FluidExpansion
/*     */   {
/*     */     @Caster(implicit = true)
/*     */     public static NormalizedSimpleStack asNormalizedSimpleStack(Fluid _this) {
/*  81 */       return CrTNSSResolver.fromFluid(_this);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ZenRegister
/*     */   @TypedExpansion(IFluidStack.class)
/*     */   public static class IFluidStackExpansion
/*     */   {
/*     */     @Caster(implicit = true)
/*     */     public static NormalizedSimpleStack asNormalizedSimpleStack(IFluidStack _this) {
/*  97 */       return CrTNSSResolver.fromFluid(_this);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ZenRegister
/*     */   @Expansion("crafttweaker.api.tag.type.KnownTag<crafttweaker.api.fluid.Fluid>")
/*     */   public static class FluidTagExpansion
/*     */   {
/*     */     @Caster(implicit = true)
/*     */     public static NormalizedSimpleStack asNormalizedSimpleStack(KnownTag<Fluid> _this) {
/* 113 */       return CrTNSSResolver.fromFluidTag(_this);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\crafttweaker\nss\ExpandCrTTypes.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */