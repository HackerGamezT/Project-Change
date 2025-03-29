/*    */ package moze_intel.projecte.gameObjs.block_entities;
/*    */ 
/*    */ import net.minecraft.world.level.block.state.BlockState;
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
/*    */ class null
/*    */   extends EmcBlockEntity.StackHandler
/*    */ {
/*    */   null(int size) {
/* 26 */     super(size);
/*    */   }
/*    */   public void onContentsChanged(int slot) {
/* 29 */     super.onContentsChanged(slot);
/* 30 */     if (DMPedestalBlockEntity.access$000(DMPedestalBlockEntity.this) != null && !(DMPedestalBlockEntity.access$100(DMPedestalBlockEntity.this)).f_46443_) {
/*    */       
/* 32 */       BlockState state = DMPedestalBlockEntity.this.m_58900_();
/* 33 */       DMPedestalBlockEntity.access$300(DMPedestalBlockEntity.this).m_7260_(DMPedestalBlockEntity.access$200(DMPedestalBlockEntity.this), state, state, 8);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\block_entities\DMPedestalBlockEntity$1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */