/*     */ package moze_intel.projecte.handlers;
/*     */ import java.util.UUID;
/*     */ import moze_intel.projecte.PECore;
/*     */ import moze_intel.projecte.config.ProjectEConfig;
/*     */ import moze_intel.projecte.gameObjs.items.IFlightProvider;
/*     */ import moze_intel.projecte.gameObjs.items.IStepAssister;
/*     */ import moze_intel.projecte.gameObjs.registries.PEItems;
/*     */ import moze_intel.projecte.utils.PlayerHelper;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeInstance;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.common.ForgeMod;
/*     */ import net.minecraftforge.common.capabilities.Capability;
/*     */ import net.minecraftforge.common.capabilities.CapabilityManager;
/*     */ import net.minecraftforge.common.capabilities.CapabilityToken;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public final class InternalAbilities {
/*  24 */   private static final UUID STEP_ASSIST_MODIFIER_UUID = UUID.fromString("4726C09D-FD86-46D0-92DD-49ED952A12D2");
/*  25 */   private static final AttributeModifier STEP_ASSIST = new AttributeModifier(STEP_ASSIST_MODIFIER_UUID, "Step Assist", 0.4D, AttributeModifier.Operation.ADDITION);
/*  26 */   public static final Capability<InternalAbilities> CAPABILITY = CapabilityManager.get(new CapabilityToken<InternalAbilities>() {  }
/*  27 */     ); public static final ResourceLocation NAME = PECore.rl("internal_abilities");
/*     */   
/*     */   private final ServerPlayer player;
/*     */   private boolean swrgOverride = false;
/*     */   private boolean gemArmorReady = false;
/*     */   private boolean hadFlightItem = false;
/*     */   private boolean wasFlyingGamemode = false;
/*     */   private boolean isFlyingGamemode = false;
/*     */   private boolean wasFlying = false;
/*  36 */   private int projectileCooldown = 0;
/*  37 */   private int gemChestCooldown = 0;
/*     */   
/*     */   public InternalAbilities(ServerPlayer player) {
/*  40 */     this.player = player;
/*     */   }
/*     */   
/*     */   public void resetProjectileCooldown() {
/*  44 */     this.projectileCooldown = ProjectEConfig.server.cooldown.player.projectile.get();
/*     */   }
/*     */   
/*     */   public int getProjectileCooldown() {
/*  48 */     return this.projectileCooldown;
/*     */   }
/*     */   
/*     */   public void resetGemCooldown() {
/*  52 */     this.gemChestCooldown = ProjectEConfig.server.cooldown.player.gemChest.get();
/*     */   }
/*     */   
/*     */   public int getGemCooldown() {
/*  56 */     return this.gemChestCooldown;
/*     */   }
/*     */   
/*     */   public void setGemState(boolean state) {
/*  60 */     this.gemArmorReady = state;
/*     */   }
/*     */   
/*     */   public boolean getGemState() {
/*  64 */     return this.gemArmorReady;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  69 */     if (this.projectileCooldown > 0) {
/*  70 */       this.projectileCooldown--;
/*     */     }
/*     */     
/*  73 */     if (this.gemChestCooldown > 0) {
/*  74 */       this.gemChestCooldown--;
/*     */     }
/*     */     
/*  77 */     if (!shouldPlayerFly()) {
/*  78 */       if (this.hadFlightItem) {
/*  79 */         if ((this.player.m_150110_()).f_35936_) {
/*  80 */           PlayerHelper.updateClientServerFlight(this.player, false);
/*     */         }
/*  82 */         this.hadFlightItem = false;
/*     */       } 
/*  84 */       this.wasFlyingGamemode = false;
/*  85 */       this.wasFlying = false;
/*     */     } else {
/*  87 */       if (!this.hadFlightItem) {
/*  88 */         if (!(this.player.m_150110_()).f_35936_) {
/*  89 */           PlayerHelper.updateClientServerFlight(this.player, true);
/*     */         }
/*  91 */         this.hadFlightItem = true;
/*  92 */       } else if (this.wasFlyingGamemode && !this.isFlyingGamemode) {
/*     */ 
/*     */ 
/*     */         
/*  96 */         PlayerHelper.updateClientServerFlight(this.player, true, this.wasFlying);
/*     */       } 
/*  98 */       this.wasFlyingGamemode = this.isFlyingGamemode;
/*  99 */       this.wasFlying = (this.player.m_150110_()).f_35935_;
/*     */     } 
/*     */     
/* 102 */     AttributeInstance attributeInstance = this.player.m_21051_((Attribute)ForgeMod.STEP_HEIGHT_ADDITION.get());
/* 103 */     if (attributeInstance != null) {
/* 104 */       AttributeModifier existing = attributeInstance.m_22111_(STEP_ASSIST_MODIFIER_UUID);
/* 105 */       if (shouldPlayerStep()) {
/* 106 */         if (existing == null)
/*     */         {
/* 108 */           attributeInstance.m_22118_(STEP_ASSIST);
/*     */         }
/* 110 */       } else if (existing != null) {
/*     */         
/* 112 */         attributeInstance.m_22130_(existing);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDimensionChange() {
/* 119 */     PlayerHelper.updateClientServerFlight(this.player, (this.player.m_150110_()).f_35936_);
/*     */   }
/*     */   
/*     */   private boolean shouldPlayerFly() {
/* 123 */     if (!hasSwrg()) {
/* 124 */       disableSwrgFlightOverride();
/*     */     }
/* 126 */     this.isFlyingGamemode = (this.player.m_7500_() || this.player.m_5833_());
/* 127 */     if (this.isFlyingGamemode || this.swrgOverride) {
/* 128 */       return true;
/*     */     }
/* 130 */     return PlayerHelper.checkArmorHotbarCurios((Player)this.player, stack -> { if (!stack.m_41619_()) { Item patt4692$temp = stack.m_41720_(); if (patt4692$temp instanceof IFlightProvider) {
/*     */               IFlightProvider provider = (IFlightProvider)patt4692$temp; if (provider.canProvideFlight(stack, this.player));
/*     */             }  }
/*     */            return false;
/* 134 */         }); } private boolean shouldPlayerStep() { return PlayerHelper.checkArmorHotbarCurios((Player)this.player, stack -> { if (!stack.m_41619_()) { Item patt4914$temp = stack.m_41720_(); if (patt4914$temp instanceof IStepAssister) {
/*     */               IStepAssister assister = (IStepAssister)patt4914$temp; if (assister.canAssistStep(stack, this.player));
/*     */             }  }
/*     */            return false;
/* 138 */         }); } private boolean hasSwrg() { return PlayerHelper.checkHotbarCurios((Player)this.player, stack -> (!stack.m_41619_() && stack.m_41720_() == PEItems.SWIFTWOLF_RENDING_GALE.get())); }
/*     */ 
/*     */   
/*     */   public void enableSwrgFlightOverride() {
/* 142 */     this.swrgOverride = true;
/*     */   }
/*     */   
/*     */   public void disableSwrgFlightOverride() {
/* 146 */     this.swrgOverride = false;
/*     */   }
/*     */   
/*     */   public static class Provider
/*     */     extends BasicCapabilityResolver<InternalAbilities> {
/*     */     public Provider(ServerPlayer player) {
/* 152 */       super(() -> new InternalAbilities(player));
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Capability<InternalAbilities> getMatchingCapability() {
/* 158 */       return InternalAbilities.CAPABILITY;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\handlers\InternalAbilities.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */