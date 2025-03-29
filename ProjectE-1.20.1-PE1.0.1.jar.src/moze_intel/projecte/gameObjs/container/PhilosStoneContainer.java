/*    */ package moze_intel.projecte.gameObjs.container;
/*    */ 
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.inventory.ContainerLevelAccess;
/*    */ import net.minecraft.world.inventory.CraftingMenu;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PhilosStoneContainer
/*    */   extends CraftingMenu
/*    */ {
/*    */   public PhilosStoneContainer(int windowId, Inventory invPlayer, ContainerLevelAccess worldPosCallable) {
/* 19 */     super(windowId, invPlayer, worldPosCallable);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_6875_(@NotNull Player player) {
/* 24 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\PhilosStoneContainer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */