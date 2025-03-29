/*    */ package moze_intel.projecte.handlers;
/*    */ 
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.capability.managing.BasicCapabilityResolver;
/*    */ import moze_intel.projecte.config.ProjectEConfig;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.CapabilityManager;
/*    */ import net.minecraftforge.common.capabilities.CapabilityToken;
/*    */ import net.minecraftforge.common.util.NonNullSupplier;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class InternalTimers {
/* 14 */   public static final Capability<InternalTimers> CAPABILITY = CapabilityManager.get(new CapabilityToken<InternalTimers>() {  }
/* 15 */     ); public static final ResourceLocation NAME = PECore.rl("internal_timers");
/*    */   
/* 17 */   private final Timer repair = new Timer();
/* 18 */   private final Timer heal = new Timer();
/* 19 */   private final Timer feed = new Timer();
/*    */   
/*    */   public void tick() {
/* 22 */     this.repair.tick();
/* 23 */     this.heal.tick();
/* 24 */     this.feed.tick();
/*    */   }
/*    */   
/*    */   public void activateRepair() {
/* 28 */     this.repair.shouldUpdate = (ProjectEConfig.server.cooldown.player.repair.get() != -1);
/*    */   }
/*    */   
/*    */   public void activateHeal() {
/* 32 */     this.heal.shouldUpdate = (ProjectEConfig.server.cooldown.player.heal.get() != -1);
/*    */   }
/*    */   
/*    */   public void activateFeed() {
/* 36 */     this.feed.shouldUpdate = (ProjectEConfig.server.cooldown.player.feed.get() != -1);
/*    */   }
/*    */   
/*    */   public boolean canRepair() {
/* 40 */     if (this.repair.tickCount == 0) {
/* 41 */       this.repair.tickCount = ProjectEConfig.server.cooldown.player.repair.get();
/* 42 */       this.repair.shouldUpdate = false;
/* 43 */       return true;
/*    */     } 
/* 45 */     return false;
/*    */   }
/*    */   
/*    */   public boolean canHeal() {
/* 49 */     if (this.heal.tickCount == 0) {
/* 50 */       this.heal.tickCount = ProjectEConfig.server.cooldown.player.heal.get();
/* 51 */       this.heal.shouldUpdate = false;
/* 52 */       return true;
/*    */     } 
/* 54 */     return false;
/*    */   }
/*    */   
/*    */   public boolean canFeed() {
/* 58 */     if (this.feed.tickCount == 0) {
/* 59 */       this.feed.tickCount = ProjectEConfig.server.cooldown.player.feed.get();
/* 60 */       this.feed.shouldUpdate = false;
/* 61 */       return true;
/*    */     } 
/* 63 */     return false;
/*    */   }
/*    */   
/*    */   public static class Provider
/*    */     extends BasicCapabilityResolver<InternalTimers> {
/*    */     public Provider() {
/* 69 */       super(InternalTimers::new);
/*    */     }
/*    */ 
/*    */     
/*    */     @NotNull
/*    */     public Capability<InternalTimers> getMatchingCapability() {
/* 75 */       return InternalTimers.CAPABILITY;
/*    */     }
/*    */   }
/*    */   
/*    */   private static class Timer
/*    */   {
/* 81 */     private int tickCount = 0;
/*    */     private boolean shouldUpdate = false;
/*    */     
/*    */     private void tick() {
/* 85 */       if (this.shouldUpdate) {
/* 86 */         if (this.tickCount > 0)
/*    */         {
/* 88 */           this.tickCount--;
/*    */         }
/* 90 */         this.shouldUpdate = false;
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\handlers\InternalTimers.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */