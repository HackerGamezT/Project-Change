/*    */ package moze_intel.projecte.gameObjs.items.armor;
/*    */ 
/*    */ import java.util.List;
/*    */ import moze_intel.projecte.config.ProjectEConfig;
/*    */ import moze_intel.projecte.handlers.InternalTimers;
/*    */ import moze_intel.projecte.utils.ClientKeyHelper;
/*    */ import moze_intel.projecte.utils.ItemHelper;
/*    */ import moze_intel.projecte.utils.PEKeybind;
/*    */ import moze_intel.projecte.utils.PlayerHelper;
/*    */ import moze_intel.projecte.utils.text.PELang;
/*    */ import net.minecraft.ChatFormatting;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.core.Vec3i;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.effect.MobEffectInstance;
/*    */ import net.minecraft.world.effect.MobEffects;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.entity.LightningBolt;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.ArmorItem;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.item.TooltipFlag;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.phys.BlockHitResult;
/*    */ import net.minecraft.world.phys.HitResult;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class GemHelmet extends GemArmorBase {
/*    */   public GemHelmet(Item.Properties props) {
/* 36 */     super(ArmorItem.Type.HELMET, props);
/*    */   }
/*    */   
/*    */   public static void toggleNightVision(ItemStack helm, Player player) {
/*    */     boolean value;
/* 41 */     CompoundTag helmetTag = helm.m_41784_();
/* 42 */     if (helmetTag.m_128425_("NightVision", 1)) {
/* 43 */       value = !helmetTag.m_128471_("NightVision");
/* 44 */       helmetTag.m_128379_("NightVision", value);
/*    */     } else {
/*    */       
/* 47 */       helmetTag.m_128379_("NightVision", true);
/* 48 */       value = true;
/*    */     } 
/* 50 */     if (value) {
/* 51 */       player.m_213846_((Component)PELang.NIGHT_VISION.translate(new Object[] { ChatFormatting.GREEN, PELang.GEM_ENABLED }));
/*    */     } else {
/* 53 */       player.m_213846_((Component)PELang.NIGHT_VISION.translate(new Object[] { ChatFormatting.RED, PELang.GEM_DISABLED }));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_7373_(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltips, @NotNull TooltipFlag flags) {
/* 59 */     super.m_7373_(stack, level, tooltips, flags);
/* 60 */     tooltips.add(PELang.GEM_LORE_HELM.translate(new Object[0]));
/* 61 */     tooltips.add(PELang.NIGHT_VISION_PROMPT.translate(new Object[] { ClientKeyHelper.getKeyName(PEKeybind.HELMET_TOGGLE) }));
/* 62 */     if (ItemHelper.checkItemNBT(stack, "NightVision")) {
/* 63 */       tooltips.add(PELang.NIGHT_VISION.translate(new Object[] { ChatFormatting.GREEN, PELang.GEM_ENABLED }));
/*    */     } else {
/* 65 */       tooltips.add(PELang.NIGHT_VISION.translate(new Object[] { ChatFormatting.RED, PELang.GEM_DISABLED }));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onArmorTick(ItemStack stack, Level level, Player player) {
/* 71 */     if (!level.f_46443_) {
/* 72 */       player.getCapability(InternalTimers.CAPABILITY).ifPresent(handler -> {
/*    */             handler.activateHeal();
/*    */             
/*    */             if (player.m_21223_() < player.m_21233_() && handler.canHeal()) {
/*    */               player.m_5634_(2.0F);
/*    */             }
/*    */           });
/* 79 */       if (ItemHelper.checkItemNBT(stack, "NightVision")) {
/* 80 */         player.m_7292_(new MobEffectInstance(MobEffects.f_19611_, 220, 0, true, false));
/*    */       } else {
/* 82 */         player.m_21195_(MobEffects.f_19611_);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void doZap(Player player) {
/* 88 */     if (ProjectEConfig.server.difficulty.offensiveAbilities.get()) {
/* 89 */       BlockHitResult strikeResult = PlayerHelper.getBlockLookingAt(player, 120.0D);
/* 90 */       if (strikeResult.m_6662_() != HitResult.Type.MISS) {
/* 91 */         BlockPos strikePos = strikeResult.m_82425_();
/* 92 */         Level level = player.m_9236_();
/* 93 */         LightningBolt lightning = (LightningBolt)EntityType.f_20465_.m_20615_(level);
/* 94 */         if (lightning != null) {
/* 95 */           lightning.m_20219_(Vec3.m_82512_((Vec3i)strikePos));
/* 96 */           lightning.m_20879_((ServerPlayer)player);
/* 97 */           level.m_7967_((Entity)lightning);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\armor\GemHelmet.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */