/*    */ package moze_intel.projecte.gameObjs.block_entities;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.container.RMFurnaceContainer;
/*    */ import moze_intel.projecte.gameObjs.registries.PEBlockEntityTypes;
/*    */ import moze_intel.projecte.utils.text.PELang;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class RMFurnaceBlockEntity
/*    */   extends DMFurnaceBlockEntity {
/*    */   public RMFurnaceBlockEntity(BlockPos pos, BlockState state) {
/* 17 */     super(PEBlockEntityTypes.RED_MATTER_FURNACE, pos, state, 3, 4);
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getInvSize() {
/* 22 */     return 13;
/*    */   }
/*    */ 
/*    */   
/*    */   protected float getOreDoubleChance() {
/* 27 */     return 1.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCookProgressScaled(int value) {
/* 32 */     return (this.furnaceCookTime + ((isBurning() && canSmelt()) ? 1 : 0)) * value / this.ticksBeforeSmelt;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public AbstractContainerMenu m_7208_(int windowId, @NotNull Inventory inv, @NotNull Player player) {
/* 38 */     return (AbstractContainerMenu)new RMFurnaceContainer(windowId, inv, this);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Component m_5446_() {
/* 44 */     return (Component)PELang.GUI_RED_MATTER_FURNACE.translate(new Object[0]);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\block_entities\RMFurnaceBlockEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */