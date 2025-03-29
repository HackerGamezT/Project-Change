/*    */ package moze_intel.projecte.network.packets.to_client.knowledge;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.container.TransmutationContainer;
/*    */ import moze_intel.projecte.network.packets.IPEPacket;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ 
/*    */ public class UpdateTransmutationTargetsPkt
/*    */   implements IPEPacket {
/*    */   public void handle(NetworkEvent.Context context) {
/* 14 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 15 */     if (player != null) { AbstractContainerMenu abstractContainerMenu = player.f_36096_; if (abstractContainerMenu instanceof TransmutationContainer) { TransmutationContainer container = (TransmutationContainer)abstractContainerMenu;
/* 16 */         container.transmutationInventory.updateClientTargets(); }
/*    */        }
/*    */   
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {}
/*    */   
/*    */   public static UpdateTransmutationTargetsPkt decode(FriendlyByteBuf buffer) {
/* 25 */     return new UpdateTransmutationTargetsPkt();
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\packets\to_client\knowledge\UpdateTransmutationTargetsPkt.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */