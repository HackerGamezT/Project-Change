/*    */ package moze_intel.projecte.network.packets.to_client.knowledge;
/*    */ 
/*    */ import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
/*    */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*    */ import moze_intel.projecte.gameObjs.container.TransmutationContainer;
/*    */ import moze_intel.projecte.network.packets.IPEPacket;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ 
/*    */ public class KnowledgeClearPKT implements IPEPacket {
/*    */   public void handle(NetworkEvent.Context context) {
/* 15 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 16 */     if (player != null) {
/* 17 */       player.getCapability(PECapabilities.KNOWLEDGE_CAPABILITY).ifPresent(cap -> {
/*    */             cap.clearKnowledge();
/*    */             AbstractContainerMenu patt764$temp = player.f_36096_;
/*    */             if (patt764$temp instanceof TransmutationContainer) {
/*    */               TransmutationContainer container = (TransmutationContainer)patt764$temp;
/*    */               container.transmutationInventory.updateClientTargets();
/*    */             } 
/*    */           });
/*    */     }
/*    */   }
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {}
/*    */   
/*    */   public static KnowledgeClearPKT decode(FriendlyByteBuf buffer) {
/* 31 */     return new KnowledgeClearPKT();
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\packets\to_client\knowledge\KnowledgeClearPKT.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */