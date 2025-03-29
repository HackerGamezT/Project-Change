/*    */ package moze_intel.projecte.events;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.world.entity.EquipmentSlot;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.client.event.ComputeFovModifierEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "projecte", value = {Dist.CLIENT})
/*    */ public class PlayerRender
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onFOVUpdateEvent(ComputeFovModifierEvent evt) {
/* 17 */     if (!evt.getPlayer().m_6844_(EquipmentSlot.FEET).m_41619_() && evt.getPlayer().m_6844_(EquipmentSlot.FEET).m_41720_() instanceof moze_intel.projecte.gameObjs.items.armor.GemFeet)
/* 18 */       evt.setNewFovModifier(evt.getNewFovModifier() - 0.5F * ((Double)(Minecraft.m_91087_()).f_91066_.m_231925_().m_231551_()).floatValue()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\events\PlayerRender.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */