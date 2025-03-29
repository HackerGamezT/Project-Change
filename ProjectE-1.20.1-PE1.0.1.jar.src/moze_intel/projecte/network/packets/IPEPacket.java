/*    */ package moze_intel.projecte.network.packets;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ 
/*    */ 
/*    */ public interface IPEPacket
/*    */ {
/*    */   void handle(NetworkEvent.Context paramContext);
/*    */   
/*    */   void encode(FriendlyByteBuf paramFriendlyByteBuf);
/*    */   
/*    */   static <PACKET extends IPEPacket> void handle(PACKET message, Supplier<NetworkEvent.Context> ctx) {
/* 15 */     NetworkEvent.Context context = ctx.get();
/* 16 */     context.enqueueWork(() -> message.handle(context));
/* 17 */     context.setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\packets\IPEPacket.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */