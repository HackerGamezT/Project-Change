/*    */ package moze_intel.projecte.network.packets.to_server;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.items.rings.ArchangelSmite;
/*    */ import moze_intel.projecte.network.packets.IPEPacket;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ 
/*    */ public class LeftClickArchangelPKT implements IPEPacket {
/*    */   public void handle(NetworkEvent.Context context) {
/* 14 */     ServerPlayer serverPlayer = context.getSender();
/* 15 */     if (serverPlayer != null) {
/* 16 */       ItemStack main = serverPlayer.m_21205_();
/* 17 */       if (!main.m_41619_()) { Item item = main.m_41720_(); if (item instanceof ArchangelSmite) { ArchangelSmite archangelSmite = (ArchangelSmite)item;
/* 18 */           archangelSmite.fireVolley(main, (Player)serverPlayer); }
/*    */          }
/*    */     
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {}
/*    */   
/*    */   public static LeftClickArchangelPKT decode(FriendlyByteBuf buffer) {
/* 28 */     return new LeftClickArchangelPKT();
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\packets\to_server\LeftClickArchangelPKT.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */