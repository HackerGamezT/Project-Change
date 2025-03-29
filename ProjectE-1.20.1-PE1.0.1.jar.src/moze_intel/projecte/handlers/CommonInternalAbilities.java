/*     */ package moze_intel.projecte.handlers;
/*     */ 
/*     */ import moze_intel.projecte.PECore;
/*     */ import moze_intel.projecte.capability.managing.BasicCapabilityResolver;
/*     */ import moze_intel.projecte.gameObjs.registries.PEItems;
/*     */ import moze_intel.projecte.utils.PlayerHelper;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraft.world.entity.EquipmentSlot;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeInstance;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.material.FluidState;
/*     */ import net.minecraftforge.common.capabilities.Capability;
/*     */ import net.minecraftforge.common.capabilities.CapabilityManager;
/*     */ import net.minecraftforge.common.capabilities.CapabilityToken;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ public class CommonInternalAbilities
/*     */ {
/*  25 */   public static final Capability<CommonInternalAbilities> CAPABILITY = CapabilityManager.get(new CapabilityToken<CommonInternalAbilities>() {  }
/*  26 */     ); public static final ResourceLocation NAME = PECore.rl("common_internal_abilities");
/*  27 */   private static final AttributeModifier WATER_SPEED_BOOST = new AttributeModifier("Walk on water speed boost", 0.15D, AttributeModifier.Operation.ADDITION);
/*  28 */   private static final AttributeModifier LAVA_SPEED_BOOST = new AttributeModifier("Walk on lava speed boost", 0.15D, AttributeModifier.Operation.ADDITION);
/*     */   
/*     */   private final Player player;
/*     */   
/*     */   public CommonInternalAbilities(Player player) {
/*  33 */     this.player = player;
/*     */   }
/*     */   
/*     */   public void tick() {
/*  37 */     boolean applyWaterSpeed = false;
/*  38 */     boolean applyLavaSpeed = false;
/*  39 */     WalkOnType waterWalkOnType = canWalkOnWater();
/*  40 */     WalkOnType lavaWalkOnType = canWalkOnLava();
/*  41 */     if (waterWalkOnType.canWalk() || lavaWalkOnType.canWalk()) {
/*  42 */       int x = (int)Math.floor(this.player.m_20185_());
/*  43 */       int y = (int)(this.player.m_20186_() - this.player.m_6049_());
/*  44 */       int z = (int)Math.floor(this.player.m_20189_());
/*  45 */       BlockPos pos = new BlockPos(x, y, z);
/*  46 */       FluidState below = this.player.m_9236_().m_6425_(pos.m_7495_());
/*  47 */       boolean water = (waterWalkOnType.canWalk() && below.m_205070_(FluidTags.f_13131_));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  52 */       boolean lava = (lavaWalkOnType.canWalk() && below.m_205070_(FluidTags.f_13132_));
/*  53 */       if ((water || lava) && this.player.m_9236_().m_46859_(pos)) {
/*  54 */         if (!this.player.m_6144_()) {
/*  55 */           this.player.m_20256_(this.player.m_20184_().m_82542_(1.0D, 0.0D, 1.0D));
/*  56 */           this.player.f_19789_ = 0.0F;
/*  57 */           this.player.m_6853_(true);
/*     */         } 
/*  59 */         applyWaterSpeed = (water && waterWalkOnType == WalkOnType.ABLE_WITH_SPEED);
/*  60 */         applyLavaSpeed = (lava && lavaWalkOnType == WalkOnType.ABLE_WITH_SPEED);
/*  61 */       } else if (!(this.player.m_9236_()).f_46443_ && 
/*  62 */         waterWalkOnType.canWalk() && this.player.m_20069_()) {
/*     */         
/*  64 */         this.player.m_20301_(this.player.m_6062_());
/*     */       } 
/*     */     } 
/*     */     
/*  68 */     if (!(this.player.m_9236_()).f_46443_) {
/*  69 */       AttributeInstance attribute = this.player.m_21051_(Attributes.f_22279_);
/*  70 */       if (attribute != null) {
/*  71 */         updateSpeed(attribute, applyWaterSpeed, WATER_SPEED_BOOST);
/*  72 */         updateSpeed(attribute, applyLavaSpeed, LAVA_SPEED_BOOST);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateSpeed(AttributeInstance attribute, boolean apply, AttributeModifier speedModifier) {
/*  78 */     if (apply) {
/*  79 */       if (!attribute.m_22109_(speedModifier)) {
/*  80 */         attribute.m_22118_(speedModifier);
/*     */       }
/*  82 */     } else if (attribute.m_22109_(speedModifier)) {
/*  83 */       attribute.m_22130_(speedModifier);
/*     */     } 
/*     */   }
/*     */   
/*     */   private WalkOnType canWalkOnWater() {
/*  88 */     if (PlayerHelper.checkHotbarCurios(this.player, stack -> (!stack.m_41619_() && stack.m_41720_() == PEItems.EVERTIDE_AMULET.get()))) {
/*  89 */       return WalkOnType.ABLE_WITH_SPEED;
/*     */     }
/*  91 */     ItemStack helmet = this.player.m_6844_(EquipmentSlot.HEAD);
/*  92 */     return (!helmet.m_41619_() && helmet.m_41720_() == PEItems.GEM_HELMET.get()) ? WalkOnType.ABLE : WalkOnType.UNABLE;
/*     */   }
/*     */   
/*     */   private WalkOnType canWalkOnLava() {
/*  96 */     if (PlayerHelper.checkHotbarCurios(this.player, stack -> (!stack.m_41619_() && stack.m_41720_() == PEItems.VOLCANITE_AMULET.get()))) {
/*  97 */       return WalkOnType.ABLE_WITH_SPEED;
/*     */     }
/*  99 */     ItemStack chestplate = this.player.m_6844_(EquipmentSlot.CHEST);
/* 100 */     return (!chestplate.m_41619_() && chestplate.m_41720_() == PEItems.GEM_CHESTPLATE.get()) ? WalkOnType.ABLE : WalkOnType.UNABLE;
/*     */   }
/*     */   
/*     */   private enum WalkOnType {
/* 104 */     ABLE,
/* 105 */     ABLE_WITH_SPEED,
/* 106 */     UNABLE;
/*     */     
/*     */     public boolean canWalk() {
/* 109 */       return (this != UNABLE);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Provider
/*     */     extends BasicCapabilityResolver<CommonInternalAbilities> {
/*     */     public Provider(Player player) {
/* 116 */       super(() -> new CommonInternalAbilities(player));
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Capability<CommonInternalAbilities> getMatchingCapability() {
/* 122 */       return CommonInternalAbilities.CAPABILITY;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\handlers\CommonInternalAbilities.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */