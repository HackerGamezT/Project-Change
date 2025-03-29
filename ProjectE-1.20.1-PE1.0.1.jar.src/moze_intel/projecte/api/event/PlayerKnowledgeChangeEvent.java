/*    */ package moze_intel.projecte.api.event;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerKnowledgeChangeEvent
/*    */   extends Event
/*    */ {
/*    */   private final UUID playerUUID;
/*    */   
/*    */   public PlayerKnowledgeChangeEvent(@NotNull Player player) {
/* 20 */     this(player.m_20148_());
/*    */   }
/*    */   
/*    */   public PlayerKnowledgeChangeEvent(@NotNull UUID playerUUID) {
/* 24 */     this.playerUUID = playerUUID;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public UUID getPlayerUUID() {
/* 32 */     return this.playerUUID;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\event\PlayerKnowledgeChangeEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */