/*     */ package moze_intel.projecte.integration.crafttweaker.nss;
/*     */ 
/*     */ import com.blamejared.crafttweaker.api.annotation.ZenRegister;
/*     */ import com.blamejared.crafttweaker.api.fluid.IFluidStack;
/*     */ import com.blamejared.crafttweaker.api.item.IItemStack;
/*     */ import com.blamejared.crafttweaker.api.tag.type.KnownTag;
/*     */ import com.blamejared.crafttweaker_annotations.annotations.Document;
/*     */ import com.google.gson.JsonParseException;
/*     */ import moze_intel.projecte.api.nss.NSSFluid;
/*     */ import moze_intel.projecte.api.nss.NSSItem;
/*     */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*     */ import moze_intel.projecte.emc.json.NSSSerializer;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.Items;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraft.world.level.material.Fluid;
/*     */ import net.minecraft.world.level.material.Fluids;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import org.openzen.zencode.java.ZenCodeType.Method;
/*     */ import org.openzen.zencode.java.ZenCodeType.Name;
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
/*     */ @Document("mods/ProjectE/NSSResolver")
/*     */ @Name("mods.projecte.NSSResolver")
/*     */ public class CrTNSSResolver
/*     */ {
/*     */   @Method
/*     */   public static NormalizedSimpleStack deserialize(String representation) {
/*     */     try {
/*  38 */       return NSSSerializer.INSTANCE.deserialize(representation);
/*  39 */     } catch (JsonParseException e) {
/*  40 */       throw new IllegalArgumentException("Error deserializing NSS string representation", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Method
/*     */   public static NormalizedSimpleStack fromItem(Item item) {
/*  53 */     if (item == Items.f_41852_) {
/*  54 */       throw new IllegalArgumentException("Cannot make an NSS Representation from the empty item.");
/*     */     }
/*  56 */     return (NormalizedSimpleStack)NSSItem.createItem((ItemLike)item);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Method
/*     */   public static NormalizedSimpleStack fromItem(IItemStack stack) {
/*  68 */     if (stack.isEmpty()) {
/*  69 */       throw new IllegalArgumentException("Cannot make an NSS Representation from an empty item stack.");
/*     */     }
/*  71 */     return (NormalizedSimpleStack)NSSItem.createItem(stack.getInternal());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Method
/*     */   public static NormalizedSimpleStack fromItemTag(KnownTag<Item> tag) {
/*  83 */     if (tag.exists()) {
/*  84 */       return (NormalizedSimpleStack)NSSItem.createTag(tag.id());
/*     */     }
/*  86 */     throw new IllegalArgumentException("Item tag " + tag.getCommandString() + " does not exist.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Method
/*     */   public static NormalizedSimpleStack fromFluid(IFluidStack stack) {
/*  98 */     if (stack.isEmpty()) {
/*  99 */       throw new IllegalArgumentException("Cannot make an NSS Representation from an empty fluid stack.");
/*     */     }
/* 101 */     return (NormalizedSimpleStack)NSSFluid.createFluid((FluidStack)stack.getInternal());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Method
/*     */   public static NormalizedSimpleStack fromFluid(Fluid fluid) {
/* 113 */     if (fluid == Fluids.f_76191_) {
/* 114 */       throw new IllegalArgumentException("Cannot make an NSS Representation from the empty fluid.");
/*     */     }
/* 116 */     return (NormalizedSimpleStack)NSSFluid.createFluid(fluid);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Method
/*     */   public static NormalizedSimpleStack fromFluidTag(KnownTag<Fluid> tag) {
/* 128 */     if (tag.exists()) {
/* 129 */       return (NormalizedSimpleStack)NSSFluid.createTag(tag.id());
/*     */     }
/* 131 */     throw new IllegalArgumentException("Fluid tag " + tag.getCommandString() + " does not exist.");
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\crafttweaker\nss\CrTNSSResolver.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */