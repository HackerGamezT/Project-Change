/*     */ package moze_intel.projecte.network;
/*     */ 
/*     */ import io.netty.buffer.Unpooled;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Function;
/*     */ import moze_intel.projecte.PECore;
/*     */ import moze_intel.projecte.emc.EMCMappingHandler;
/*     */ import moze_intel.projecte.emc.FuelMapper;
/*     */ import moze_intel.projecte.network.packets.IPEPacket;
/*     */ import moze_intel.projecte.network.packets.to_client.CooldownResetPKT;
/*     */ import moze_intel.projecte.network.packets.to_client.SyncBagDataPKT;
/*     */ import moze_intel.projecte.network.packets.to_client.SyncEmcPKT;
/*     */ import moze_intel.projecte.network.packets.to_client.SyncFuelMapperPKT;
/*     */ import moze_intel.projecte.network.packets.to_client.UpdateCondenserLockPKT;
/*     */ import moze_intel.projecte.network.packets.to_client.UpdateWindowIntPKT;
/*     */ import moze_intel.projecte.network.packets.to_client.UpdateWindowLongPKT;
/*     */ import moze_intel.projecte.network.packets.to_client.knowledge.KnowledgeClearPKT;
/*     */ import moze_intel.projecte.network.packets.to_client.knowledge.KnowledgeSyncChangePKT;
/*     */ import moze_intel.projecte.network.packets.to_client.knowledge.KnowledgeSyncEmcPKT;
/*     */ import moze_intel.projecte.network.packets.to_client.knowledge.KnowledgeSyncInputsAndLocksPKT;
/*     */ import moze_intel.projecte.network.packets.to_client.knowledge.KnowledgeSyncPKT;
/*     */ import moze_intel.projecte.network.packets.to_client.knowledge.UpdateTransmutationTargetsPkt;
/*     */ import moze_intel.projecte.network.packets.to_server.KeyPressPKT;
/*     */ import moze_intel.projecte.network.packets.to_server.LeftClickArchangelPKT;
/*     */ import moze_intel.projecte.network.packets.to_server.SearchUpdatePKT;
/*     */ import moze_intel.projecte.network.packets.to_server.UpdateGemModePKT;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraftforge.network.NetworkDirection;
/*     */ import net.minecraftforge.network.NetworkRegistry;
/*     */ import net.minecraftforge.network.PacketDistributor;
/*     */ import net.minecraftforge.network.simple.SimpleChannel;
/*     */ import net.minecraftforge.server.ServerLifecycleHooks;
/*     */ 
/*     */ 
/*     */ public final class PacketHandler
/*     */ {
/*  39 */   private static final String PROTOCOL_VERSION = Integer.toString(4);
/*     */   
/*     */   static {
/*  42 */     Objects.requireNonNull(PROTOCOL_VERSION);
/*  43 */     Objects.requireNonNull(PROTOCOL_VERSION); } private static final SimpleChannel HANDLER = NetworkRegistry.ChannelBuilder.named(PECore.rl("main_channel")).clientAcceptedVersions(PROTOCOL_VERSION::equals).serverAcceptedVersions(PROTOCOL_VERSION::equals)
/*  44 */     .networkProtocolVersion(() -> PROTOCOL_VERSION)
/*  45 */     .simpleChannel();
/*     */   
/*     */   private static int index;
/*     */   
/*     */   public static void register() {
/*  50 */     registerClientToServer(KeyPressPKT.class, KeyPressPKT::decode);
/*  51 */     registerClientToServer(LeftClickArchangelPKT.class, LeftClickArchangelPKT::decode);
/*  52 */     registerClientToServer(SearchUpdatePKT.class, SearchUpdatePKT::decode);
/*  53 */     registerClientToServer(UpdateGemModePKT.class, UpdateGemModePKT::decode);
/*     */ 
/*     */     
/*  56 */     registerServerToClient(CooldownResetPKT.class, CooldownResetPKT::decode);
/*  57 */     registerServerToClient(KnowledgeClearPKT.class, KnowledgeClearPKT::decode);
/*  58 */     registerServerToClient(KnowledgeSyncPKT.class, KnowledgeSyncPKT::decode);
/*  59 */     registerServerToClient(KnowledgeSyncEmcPKT.class, KnowledgeSyncEmcPKT::decode);
/*  60 */     registerServerToClient(KnowledgeSyncInputsAndLocksPKT.class, KnowledgeSyncInputsAndLocksPKT::decode);
/*  61 */     registerServerToClient(KnowledgeSyncChangePKT.class, KnowledgeSyncChangePKT::decode);
/*  62 */     registerServerToClient(SyncBagDataPKT.class, SyncBagDataPKT::decode);
/*  63 */     registerServerToClient(SyncEmcPKT.class, SyncEmcPKT::decode);
/*  64 */     registerServerToClient(SyncFuelMapperPKT.class, SyncFuelMapperPKT::decode);
/*  65 */     registerServerToClient(UpdateCondenserLockPKT.class, UpdateCondenserLockPKT::decode);
/*  66 */     registerServerToClient(UpdateTransmutationTargetsPkt.class, UpdateTransmutationTargetsPkt::decode);
/*  67 */     registerServerToClient(UpdateWindowIntPKT.class, UpdateWindowIntPKT::decode);
/*  68 */     registerServerToClient(UpdateWindowLongPKT.class, UpdateWindowLongPKT::decode);
/*     */   }
/*     */   
/*     */   private static <MSG extends IPEPacket> void registerClientToServer(Class<MSG> type, Function<FriendlyByteBuf, MSG> decoder) {
/*  72 */     registerMessage(type, decoder, NetworkDirection.PLAY_TO_SERVER);
/*     */   }
/*     */   
/*     */   private static <MSG extends IPEPacket> void registerServerToClient(Class<MSG> type, Function<FriendlyByteBuf, MSG> decoder) {
/*  76 */     registerMessage(type, decoder, NetworkDirection.PLAY_TO_CLIENT);
/*     */   }
/*     */   
/*     */   private static <MSG extends IPEPacket> void registerMessage(Class<MSG> type, Function<FriendlyByteBuf, MSG> decoder, NetworkDirection networkDirection) {
/*  80 */     HANDLER.registerMessage(index++, type, IPEPacket::encode, decoder, IPEPacket::handle, Optional.of(networkDirection));
/*     */   }
/*     */   
/*     */   private static boolean isLocal(ServerPlayer player) {
/*  84 */     return player.f_8924_.m_7779_(player.m_36316_());
/*     */   }
/*     */   
/*     */   public static <MSG extends IPEPacket> void sendNonLocal(MSG msg, ServerPlayer player) {
/*  88 */     if (!isLocal(player)) {
/*  89 */       sendTo(msg, player);
/*     */     }
/*     */   }
/*     */   
/*     */   private static void sendFragmentedEmcPacket(ServerPlayer player, SyncEmcPKT pkt, SyncFuelMapperPKT fuelPkt) {
/*  94 */     if (!isLocal(player)) {
/*  95 */       sendTo(pkt, player);
/*  96 */       sendTo(fuelPkt, player);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void sendFragmentedEmcPacket(ServerPlayer player) {
/* 101 */     sendFragmentedEmcPacket(player, new SyncEmcPKT(serializeEmcData()), FuelMapper.getSyncPacket());
/*     */   }
/*     */   
/*     */   public static void sendFragmentedEmcPacketToAll() {
/* 105 */     if (ServerLifecycleHooks.getCurrentServer() != null) {
/* 106 */       SyncEmcPKT pkt = new SyncEmcPKT(serializeEmcData());
/* 107 */       SyncFuelMapperPKT fuelPkt = FuelMapper.getSyncPacket();
/* 108 */       for (ServerPlayer player : ServerLifecycleHooks.getCurrentServer().m_6846_().m_11314_()) {
/* 109 */         sendFragmentedEmcPacket(player, pkt, fuelPkt);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static SyncEmcPKT.EmcPKTInfo[] serializeEmcData() {
/* 115 */     SyncEmcPKT.EmcPKTInfo[] data = EMCMappingHandler.createPacketData();
/*     */     
/* 117 */     FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
/* 118 */     int index = buf.writerIndex();
/* 119 */     (new SyncEmcPKT(data)).encode(buf);
/* 120 */     PECore.debugLog("EMC data size: {} bytes", new Object[] { Integer.valueOf(buf.writerIndex() - index) });
/* 121 */     buf.release();
/* 122 */     return data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <MSG extends IPEPacket> void sendToServer(MSG msg) {
/* 129 */     HANDLER.sendToServer(msg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <MSG extends IPEPacket> void sendTo(MSG msg, ServerPlayer player) {
/* 136 */     if (!(player instanceof net.minecraftforge.common.util.FakePlayer))
/* 137 */       HANDLER.send(PacketDistributor.PLAYER.with(() -> player), msg); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\PacketHandler.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */