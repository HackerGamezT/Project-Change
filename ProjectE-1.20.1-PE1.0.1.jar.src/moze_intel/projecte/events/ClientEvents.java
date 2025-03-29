/*    */ package moze_intel.projecte.events;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import moze_intel.projecte.gameObjs.entity.EntitySWRGProjectile;
/*    */ import moze_intel.projecte.gameObjs.sound.MovingSoundSWRG;
/*    */ import moze_intel.projecte.network.commands.client.DumpMissingEmc;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.resources.sounds.SoundInstance;
/*    */ import net.minecraft.commands.CommandBuildContext;
/*    */ import net.minecraft.commands.Commands;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.client.event.RegisterClientCommandsEvent;
/*    */ import net.minecraftforge.event.entity.EntityJoinLevelEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ @EventBusSubscriber(modid = "projecte", value = {Dist.CLIENT})
/*    */ public class ClientEvents {
/*    */   @SubscribeEvent
/*    */   public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
/* 21 */     Minecraft mc = Minecraft.m_91087_();
/* 22 */     Entity entity = event.getEntity(); if (entity instanceof EntitySWRGProjectile) { EntitySWRGProjectile projectile = (EntitySWRGProjectile)entity; if (mc.f_91067_.m_91600_())
/* 23 */         mc.m_91106_().m_120367_((SoundInstance)new MovingSoundSWRG(projectile, event.getLevel().m_213780_()));  }
/*    */   
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void registerClientCommands(RegisterClientCommandsEvent event) {
/* 29 */     CommandBuildContext context = event.getBuildContext();
/*    */     
/* 31 */     event.getDispatcher().register((LiteralArgumentBuilder)Commands.m_82127_("projecte")
/* 32 */         .then(DumpMissingEmc.register(context)));
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\events\ClientEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */