/*    */ package moze_intel.projecte.utils;
/*    */ import com.google.common.collect.ImmutableBiMap;
/*    */ import com.google.common.collect.UnmodifiableIterator;
/*    */ import com.mojang.blaze3d.platform.InputConstants;
/*    */ import java.util.Objects;
/*    */ import moze_intel.projecte.network.PacketHandler;
/*    */ import moze_intel.projecte.network.packets.IPEPacket;
/*    */ import moze_intel.projecte.network.packets.to_server.KeyPressPKT;
/*    */ import moze_intel.projecte.utils.text.PELang;
/*    */ import moze_intel.projecte.utils.text.TextComponentUtil;
/*    */ import net.minecraft.client.KeyMapping;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
/*    */ import net.minecraftforge.client.settings.IKeyConflictContext;
/*    */ import net.minecraftforge.client.settings.KeyConflictContext;
/*    */ import net.minecraftforge.client.settings.KeyModifier;
/*    */ import net.minecraftforge.event.TickEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ @EventBusSubscriber(modid = "projecte", value = {Dist.CLIENT})
/*    */ public class ClientKeyHelper {
/* 24 */   private static ImmutableBiMap<KeyMapping, PEKeybind> mcToPe = ImmutableBiMap.of();
/* 25 */   private static ImmutableBiMap<PEKeybind, KeyMapping> peToMc = ImmutableBiMap.of();
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void keyPress(TickEvent.ClientTickEvent event) {
/* 29 */     for (UnmodifiableIterator<KeyMapping> unmodifiableIterator = mcToPe.keySet().iterator(); unmodifiableIterator.hasNext(); ) { KeyMapping k = unmodifiableIterator.next();
/* 30 */       while (k.m_90859_()) {
/* 31 */         PacketHandler.sendToServer((IPEPacket)new KeyPressPKT((PEKeybind)mcToPe.get(k)));
/*    */       } }
/*    */   
/*    */   }
/*    */   
/*    */   public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
/* 37 */     ImmutableBiMap.Builder<KeyMapping, PEKeybind> builder = ImmutableBiMap.builder();
/* 38 */     addKeyBinding(builder, PEKeybind.HELMET_TOGGLE, KeyModifier.SHIFT, 88);
/* 39 */     addKeyBinding(builder, PEKeybind.BOOTS_TOGGLE, KeyModifier.NONE, 88);
/* 40 */     addKeyBinding(builder, PEKeybind.CHARGE, KeyModifier.NONE, 86);
/* 41 */     addKeyBinding(builder, PEKeybind.EXTRA_FUNCTION, KeyModifier.NONE, 67);
/* 42 */     addKeyBinding(builder, PEKeybind.FIRE_PROJECTILE, KeyModifier.NONE, 82);
/* 43 */     addKeyBinding(builder, PEKeybind.MODE, KeyModifier.NONE, 71);
/* 44 */     mcToPe = builder.build();
/* 45 */     peToMc = mcToPe.inverse();
/* 46 */     Objects.requireNonNull(event); mcToPe.keySet().forEach(event::register);
/*    */   }
/*    */   
/*    */   private static void addKeyBinding(ImmutableBiMap.Builder<KeyMapping, PEKeybind> builder, PEKeybind keyBind, KeyModifier modifier, int keyCode) {
/* 50 */     builder.put(new KeyMapping(keyBind.getTranslationKey(), (IKeyConflictContext)KeyConflictContext.IN_GAME, modifier, InputConstants.Type.KEYSYM, keyCode, PELang.PROJECTE
/* 51 */           .getTranslationKey()), keyBind);
/*    */   }
/*    */   
/*    */   public static Component getKeyName(PEKeybind k) {
/* 55 */     if (peToMc.containsKey(k)) {
/* 56 */       return ((KeyMapping)peToMc.get(k)).m_90863_();
/*    */     }
/*    */     
/* 59 */     return (Component)TextComponentUtil.build(new Object[] { k });
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\project\\utils\ClientKeyHelper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */