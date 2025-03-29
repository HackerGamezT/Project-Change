/*    */ package moze_intel.projecte.gameObjs;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import moze_intel.projecte.PECore;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.tags.TagKey;
/*    */ import net.minecraft.util.StringRepresentable;
/*    */ import net.minecraft.world.item.Tier;
/*    */ import net.minecraft.world.item.Tiers;
/*    */ import net.minecraft.world.item.crafting.Ingredient;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.material.MapColor;
/*    */ import net.minecraftforge.common.TierSortingRegistry;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public enum EnumMatterType implements StringRepresentable, Tier {
/* 19 */   DARK_MATTER("dark_matter", 3.0F, 14.0F, 12.0F, 4, PETags.Blocks.NEEDS_DARK_MATTER_TOOL, (Tier)Tiers.NETHERITE, PECore.rl("red_matter"), MapColor.f_283927_),
/* 20 */   RED_MATTER("red_matter", 4.0F, 16.0F, 14.0F, 5, PETags.Blocks.NEEDS_RED_MATTER_TOOL, DARK_MATTER, null, MapColor.f_283913_);
/*    */   
/*    */   private final String name;
/*    */   
/*    */   private final float attackDamage;
/*    */   private final float efficiency;
/*    */   private final float chargeModifier;
/*    */   private final int harvestLevel;
/*    */   private final TagKey<Block> neededTag;
/*    */   private final MapColor mapColor;
/*    */   
/*    */   EnumMatterType(String name, float attackDamage, float efficiency, float chargeModifier, int harvestLevel, @Nullable TagKey<Block> neededTag, Tier previous, ResourceLocation next, MapColor mapColor) {
/* 32 */     this.name = name;
/* 33 */     this.attackDamage = attackDamage;
/* 34 */     this.efficiency = efficiency;
/* 35 */     this.chargeModifier = chargeModifier;
/* 36 */     this.harvestLevel = harvestLevel;
/* 37 */     this.neededTag = neededTag;
/* 38 */     this.mapColor = mapColor;
/* 39 */     TierSortingRegistry.registerTier(this, PECore.rl(name), List.of(previous), (next == null) ? Collections.emptyList() : List.<ResourceLocation>of(next));
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public String m_7912_() {
/* 45 */     return this.name;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 50 */     return m_7912_();
/*    */   }
/*    */ 
/*    */   
/*    */   public int m_6609_() {
/* 55 */     return 0;
/*    */   }
/*    */   
/*    */   public float getChargeModifier() {
/* 59 */     return this.chargeModifier;
/*    */   }
/*    */ 
/*    */   
/*    */   public float m_6624_() {
/* 64 */     return this.efficiency;
/*    */   }
/*    */ 
/*    */   
/*    */   public float m_6631_() {
/* 69 */     return this.attackDamage;
/*    */   }
/*    */ 
/*    */   
/*    */   public int m_6604_() {
/* 74 */     return this.harvestLevel;
/*    */   }
/*    */ 
/*    */   
/*    */   public int m_6601_() {
/* 79 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Ingredient m_6282_() {
/* 85 */     return Ingredient.f_43901_;
/*    */   }
/*    */   
/*    */   public MapColor getMapColor() {
/* 89 */     return this.mapColor;
/*    */   }
/*    */   
/*    */   public int getMatterTier() {
/* 93 */     return ordinal();
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public TagKey<Block> getTag() {
/* 99 */     return this.neededTag;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\EnumMatterType.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */