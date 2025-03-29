/*    */ package moze_intel.projecte.gameObjs.customRecipes;
/*    */ 
/*    */ import moze_intel.projecte.utils.EMCHelper;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class RepairTargetInfo
/*    */ {
/*    */   private final ItemStack tool;
/*    */   private final long emcPerDurability;
/*    */   private final long dustEmc;
/*    */   
/*    */   public RepairTargetInfo(ItemStack tool, long dustEmc) {
/* 86 */     this.tool = tool;
/* 87 */     this.dustEmc = dustEmc;
/* 88 */     this.emcPerDurability = EMCHelper.getEMCPerDurability(tool);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\customRecipes\RecipesCovalenceRepair$RepairTargetInfo.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */