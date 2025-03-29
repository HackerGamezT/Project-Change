/*     */ package moze_intel.projecte.gameObjs.block_entities;
/*     */ 
/*     */ import java.util.stream.IntStream;
/*     */ import moze_intel.projecte.utils.ItemHelper;
/*     */ import net.minecraftforge.items.IItemHandlerModifiable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CompactableStackHandler
/*     */   extends EmcBlockEntity.StackHandler
/*     */ {
/*     */   private boolean needsCompacting = true;
/*     */   private boolean empty;
/*     */   
/*     */   protected CompactableStackHandler(int size) {
/* 156 */     super(size);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onContentsChanged(int slot) {
/* 161 */     super.onContentsChanged(slot);
/* 162 */     this.needsCompacting = true;
/*     */   }
/*     */   
/*     */   public void compact() {
/* 166 */     if (this.needsCompacting) {
/* 167 */       if (EmcBlockEntity.access$000(EmcBlockEntity.this) != null && !(EmcBlockEntity.access$100(EmcBlockEntity.this)).f_46443_) {
/* 168 */         this.empty = ItemHelper.compactInventory((IItemHandlerModifiable)this);
/*     */       }
/* 170 */       this.needsCompacting = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onLoad() {
/* 176 */     super.onLoad();
/* 177 */     this.empty = IntStream.range(0, getSlots()).allMatch(slot -> getStackInSlot(slot).m_41619_());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 184 */     return this.empty;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\block_entities\EmcBlockEntity$CompactableStackHandler.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */