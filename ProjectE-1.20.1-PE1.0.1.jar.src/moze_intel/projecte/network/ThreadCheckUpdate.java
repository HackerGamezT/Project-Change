/*    */ package moze_intel.projecte.network;
/*    */ 
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.utils.text.PELang;
/*    */ import moze_intel.projecte.utils.text.TextComponentUtil;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.network.chat.ClickEvent;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.event.entity.EntityJoinLevelEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.VersionChecker;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.fml.loading.FMLConfig;
/*    */ import net.minecraftforge.forgespi.language.IModInfo;
/*    */ import org.apache.maven.artifact.versioning.ComparableVersion;
/*    */ 
/*    */ @EventBusSubscriber(modid = "projecte", value = {Dist.CLIENT})
/*    */ public class ThreadCheckUpdate extends Thread {
/*    */   private static final String curseURL = "https://www.curseforge.com/minecraft/mc-mods/projecte/files";
/* 22 */   private static volatile ComparableVersion target = null;
/*    */   private static volatile boolean hasSentMessage = false;
/*    */   
/*    */   public ThreadCheckUpdate() {
/* 26 */     setName("ProjectE Update Checker Notifier");
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/* 31 */     if (!FMLConfig.getBoolConfigValue(FMLConfig.ConfigValue.VERSION_CHECK)) {
/*    */       return;
/*    */     }
/*    */     
/* 35 */     IModInfo info = PECore.MOD_CONTAINER.getModInfo();
/* 36 */     VersionChecker.CheckResult result = null;
/*    */     
/* 38 */     int tries = 0;
/*    */     do {
/* 40 */       VersionChecker.CheckResult res = VersionChecker.getResult(info);
/* 41 */       if (res.status() != VersionChecker.Status.PENDING) {
/* 42 */         result = res;
/*    */       }
/*    */       try {
/* 45 */         Thread.sleep(1000L);
/* 46 */       } catch (InterruptedException interruptedException) {}
/*    */       
/* 48 */       tries++;
/* 49 */     } while (result == null && tries < 10);
/*    */     
/* 51 */     if (result == null) {
/* 52 */       PECore.LOGGER.info("Update check failed.");
/*    */       
/*    */       return;
/*    */     } 
/* 56 */     if (result.status() == VersionChecker.Status.OUTDATED) {
/* 57 */       target = result.target();
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void worldLoad(EntityJoinLevelEvent evt) {
/* 63 */     Entity entity = evt.getEntity(); if (entity instanceof LocalPlayer) { LocalPlayer player = (LocalPlayer)entity; if (target != null && !hasSentMessage) {
/* 64 */         hasSentMessage = true;
/* 65 */         player.m_213846_((Component)PELang.UPDATE_AVAILABLE.translate(new Object[] { target }));
/* 66 */         player.m_213846_((Component)PELang.UPDATE_GET_IT.translate(new Object[0]));
/* 67 */         player.m_213846_((Component)TextComponentUtil.build(new Object[] { new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/projecte/files"), "https://www.curseforge.com/minecraft/mc-mods/projecte/files" }));
/*    */       }  }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\ThreadCheckUpdate.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */