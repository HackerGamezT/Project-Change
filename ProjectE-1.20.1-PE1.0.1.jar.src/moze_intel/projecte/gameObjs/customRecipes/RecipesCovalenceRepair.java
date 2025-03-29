/*    */ package moze_intel.projecte.gameObjs.customRecipes;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import moze_intel.projecte.gameObjs.PETags;
/*    */ import moze_intel.projecte.gameObjs.registries.PERecipeSerializers;
/*    */ import moze_intel.projecte.utils.EMCHelper;
/*    */ import moze_intel.projecte.utils.ItemHelper;
/*    */ import net.minecraft.core.RegistryAccess;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.Container;
/*    */ import net.minecraft.world.inventory.CraftingContainer;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.item.crafting.CraftingBookCategory;
/*    */ import net.minecraft.world.item.crafting.CustomRecipe;
/*    */ import net.minecraft.world.item.crafting.RecipeSerializer;
/*    */ import net.minecraft.world.level.Level;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class RecipesCovalenceRepair extends CustomRecipe {
/*    */   public RecipesCovalenceRepair(ResourceLocation id, CraftingBookCategory category) {
/* 23 */     super(id, category);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   private RepairTargetInfo findIngredients(CraftingContainer inv) {
/* 28 */     List<ItemStack> dust = new ArrayList<>();
/* 29 */     ItemStack tool = ItemStack.f_41583_;
/* 30 */     for (int i = 0; i < inv.m_6643_(); i++) {
/* 31 */       ItemStack input = inv.m_8020_(i);
/* 32 */       if (!input.m_41619_()) {
/* 33 */         if (input.m_204117_(PETags.Items.COVALENCE_DUST)) {
/* 34 */           dust.add(input);
/* 35 */         } else if (tool.m_41619_() && ItemHelper.isRepairableDamagedItem(input)) {
/* 36 */           tool = input;
/*    */         } else {
/* 38 */           return null;
/*    */         } 
/*    */       }
/*    */     } 
/* 42 */     if (tool.m_41619_() || dust.isEmpty())
/*    */     {
/* 44 */       return null;
/*    */     }
/* 46 */     return new RepairTargetInfo(tool, dust.stream().mapToLong(EMCHelper::getEmcValue).sum());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean matches(@NotNull CraftingContainer inv, @NotNull Level level) {
/* 51 */     RepairTargetInfo targetInfo = findIngredients(inv);
/* 52 */     return (targetInfo != null && targetInfo.emcPerDurability <= targetInfo.dustEmc);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack assemble(@NotNull CraftingContainer inv, @NotNull RegistryAccess registryAccess) {
/* 58 */     RepairTargetInfo targetInfo = findIngredients(inv);
/* 59 */     if (targetInfo == null)
/*    */     {
/* 61 */       return ItemStack.f_41583_;
/*    */     }
/* 63 */     ItemStack output = targetInfo.tool.m_41777_();
/* 64 */     output.m_41721_((int)Math.max(output.m_41773_() - targetInfo.dustEmc / targetInfo.emcPerDurability, 0L));
/* 65 */     return output;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_8004_(int width, int height) {
/* 70 */     return (width > 1 || height > 1);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public RecipeSerializer<?> m_7707_() {
/* 76 */     return (RecipeSerializer)PERecipeSerializers.COVALENCE_REPAIR.get();
/*    */   }
/*    */   
/*    */   private static class RepairTargetInfo
/*    */   {
/*    */     private final ItemStack tool;
/*    */     private final long emcPerDurability;
/*    */     private final long dustEmc;
/*    */     
/*    */     public RepairTargetInfo(ItemStack tool, long dustEmc) {
/* 86 */       this.tool = tool;
/* 87 */       this.dustEmc = dustEmc;
/* 88 */       this.emcPerDurability = EMCHelper.getEMCPerDurability(tool);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\customRecipes\RecipesCovalenceRepair.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */