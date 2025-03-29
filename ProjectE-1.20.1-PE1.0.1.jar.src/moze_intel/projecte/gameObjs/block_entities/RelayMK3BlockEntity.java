/*    */ package moze_intel.projecte.gameObjs.block_entities;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.EnumRelayTier;
/*    */ import moze_intel.projecte.gameObjs.container.RelayMK3Container;
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
/*    */ public class RelayMK3BlockEntity
/*    */   extends RelayMK1BlockEntity {
/*    */   public RelayMK3BlockEntity(BlockPos pos, BlockState state) {
/* 18 */     super(PEBlockEntityTypes.RELAY_MK3, pos, state, 21, EnumRelayTier.MK3);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public AbstractContainerMenu m_7208_(int windowId, @NotNull Inventory playerInventory, @NotNull Player player) {
/* 24 */     return (AbstractContainerMenu)new RelayMK3Container(windowId, playerInventory, this);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Component m_5446_() {
/* 30 */     return (Component)PELang.GUI_RELAY_MK3.translate(new Object[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   protected double getBonusToAdd() {
/* 35 */     return 0.5D;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\block_entities\RelayMK3BlockEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */