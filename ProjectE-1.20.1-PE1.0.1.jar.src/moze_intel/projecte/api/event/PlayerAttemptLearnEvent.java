/*    */ package moze_intel.projecte.api.event;
/*    */ 
/*    */ import moze_intel.projecte.api.ItemInfo;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Cancelable
/*    */ public class PlayerAttemptLearnEvent
/*    */   extends Event
/*    */ {
/*    */   private final Player player;
/*    */   private final ItemInfo sourceInfo;
/*    */   private final ItemInfo reducedInfo;
/*    */   
/*    */   public PlayerAttemptLearnEvent(@NotNull Player player, @NotNull ItemInfo sourceInfo, @NotNull ItemInfo reducedInfo) {
/* 24 */     this.player = player;
/* 25 */     this.sourceInfo = sourceInfo;
/* 26 */     this.reducedInfo = reducedInfo;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Player getPlayer() {
/* 34 */     return this.player;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemInfo getSourceInfo() {
/* 42 */     return this.sourceInfo;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemInfo getReducedInfo() {
/* 52 */     return this.reducedInfo;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\event\PlayerAttemptLearnEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */