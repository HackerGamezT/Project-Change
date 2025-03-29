/*     */ package moze_intel.projecte.integration.crafttweaker.nss;
/*     */ 
/*     */ import com.blamejared.crafttweaker.api.annotation.ZenRegister;
/*     */ import com.blamejared.crafttweaker.api.tag.type.KnownTag;
/*     */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ZenRegister
/*     */ @Expansion("crafttweaker.api.tag.type.KnownTag<crafttweaker.api.fluid.Fluid>")
/*     */ public class FluidTagExpansion
/*     */ {
/*     */   @Caster(implicit = true)
/*     */   public static NormalizedSimpleStack asNormalizedSimpleStack(KnownTag<Fluid> _this) {
/* 113 */     return CrTNSSResolver.fromFluidTag(_this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\crafttweaker\nss\ExpandCrTTypes$FluidTagExpansion.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */