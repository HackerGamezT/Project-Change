/*    */ package moze_intel.projecte.gameObjs.block_entities;
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
/* 23 */     super(size);
/*    */   }
/*    */   public void onContentsChanged(int slot) {
/* 26 */     super.onContentsChanged(slot);
/* 27 */     if (AlchBlockEntityChest.access$000(AlchBlockEntityChest.this) != null && !(AlchBlockEntityChest.access$100(AlchBlockEntityChest.this)).f_46443_)
/* 28 */       AlchBlockEntityChest.this.inventoryChanged = true; 
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\block_entities\AlchBlockEntityChest$1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */