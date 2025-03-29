/*    */ package moze_intel.projecte.impl;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.api.imc.NSSCreatorInfo;
/*    */ import moze_intel.projecte.api.imc.WorldTransmutationEntry;
/*    */ import moze_intel.projecte.api.nss.NSSCreator;
/*    */ import moze_intel.projecte.emc.json.NSSSerializer;
/*    */ import moze_intel.projecte.emc.mappers.APICustomEMCMapper;
/*    */ import moze_intel.projecte.utils.WorldTransmutations;
/*    */ import net.minecraftforge.fml.InterModComms;
/*    */ import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
/*    */ 
/*    */ 
/*    */ public class IMCHandler
/*    */ {
/*    */   public static void handleMessages(InterModProcessEvent event) {
/* 21 */     List<WorldTransmutationEntry> entries = new ArrayList<>();
/* 22 */     event.getIMCStream("register_world_transmutation"::equals)
/* 23 */       .filter(msg -> msg.messageSupplier().get() instanceof WorldTransmutationEntry)
/* 24 */       .forEach(msg -> {
/*    */           WorldTransmutationEntry transmutationEntry = msg.messageSupplier().get();
/*    */           
/*    */           entries.add(transmutationEntry);
/*    */           
/*    */           if (transmutationEntry.altResult() == null) {
/*    */             PECore.debugLog("Mod: '{}' registered World Transmutation from: '{}', to: '{}'", new Object[] { msg.senderModId(), transmutationEntry.origin(), transmutationEntry.result() });
/*    */           } else {
/*    */             PECore.debugLog("Mod: '{}' registered World Transmutation from: '{}', to: '{}', with sneak output of: '{}'", new Object[] { msg.senderModId(), transmutationEntry.origin(), transmutationEntry.result(), transmutationEntry.altResult() });
/*    */           } 
/*    */         });
/* 35 */     WorldTransmutations.setWorldTransmutation(entries);
/*    */     
/* 37 */     event.getIMCStream("register_custom_emc"::equals)
/* 38 */       .filter(msg -> msg.messageSupplier().get() instanceof moze_intel.projecte.api.imc.CustomEMCRegistration)
/* 39 */       .forEach(msg -> APICustomEMCMapper.INSTANCE.registerCustomEMC(msg.senderModId(), msg.messageSupplier().get()));
/*    */ 
/*    */     
/* 42 */     Map<String, NSSCreator> creators = new HashMap<>();
/* 43 */     event.getIMCStream("register_nss_serializer"::equals)
/* 44 */       .filter(msg -> msg.messageSupplier().get() instanceof NSSCreatorInfo)
/* 45 */       .forEach(msg -> {
/*    */           NSSCreatorInfo creatorInfo = msg.messageSupplier().get();
/*    */           String key = creatorInfo.key();
/*    */           if (creators.containsKey(key)) {
/*    */             PECore.LOGGER.warn("Mod: '{}' tried to register NSS creator with key: '{}', but another mod already registered that key.", msg.senderModId(), key);
/*    */           } else {
/*    */             creators.put(key, creatorInfo.creator());
/*    */             PECore.debugLog("Mod: '{}' registered NSS creator with key: '{}'", new Object[] { msg.senderModId(), key });
/*    */           } 
/*    */         });
/* 55 */     NSSSerializer.INSTANCE.setCreators(creators);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\impl\IMCHandler.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */