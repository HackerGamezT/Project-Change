/*    */ package moze_intel.projecte.gameObjs.block_entities;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.EnumCollectorTier;
/*    */ import moze_intel.projecte.gameObjs.container.CollectorMK2Container;
/*    */ import moze_intel.projecte.gameObjs.registries.PEBlockEntityTypes;
/*    */ import moze_intel.projecte.gameObjs.registries.PEBlocks;
/*    */ import moze_intel.projecte.utils.text.TextComponentUtil;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class CollectorMK2BlockEntity
/*    */   extends CollectorMK1BlockEntity {
/*    */   public CollectorMK2BlockEntity(BlockPos pos, BlockState state) {
/* 19 */     super(PEBlockEntityTypes.COLLECTOR_MK2, pos, state, EnumCollectorTier.MK2);
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getInvSize() {
/* 24 */     return 12;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public AbstractContainerMenu m_7208_(int windowId, @NotNull Inventory playerInventory, @NotNull Player playerIn) {
/* 30 */     return (AbstractContainerMenu)new CollectorMK2Container(windowId, playerInventory, this);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Component m_5446_() {
/* 36 */     return (Component)TextComponentUtil.build(new Object[] { PEBlocks.COLLECTOR_MK2 });
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\block_entities\CollectorMK2BlockEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */