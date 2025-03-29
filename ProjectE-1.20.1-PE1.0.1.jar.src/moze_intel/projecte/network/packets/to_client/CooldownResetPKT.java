/*    */ package moze_intel.projecte.network.packets.to_client;
/*    */ 
/*    */ import moze_intel.projecte.network.packets.IPEPacket;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ 
/*    */ public class CooldownResetPKT
/*    */   implements IPEPacket
/*    */ {
/*    */   public void handle(NetworkEvent.Context context) {
/* 12 */     if ((Minecraft.m_91087_()).f_91074_ != null) {
/* 13 */       (Minecraft.m_91087_()).f_91074_.m_36334_();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {}
/*    */ 
/*    */   
/*    */   public static CooldownResetPKT decode(FriendlyByteBuf buffer) {
/* 22 */     return new CooldownResetPKT();
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\packets\to_client\CooldownResetPKT.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */