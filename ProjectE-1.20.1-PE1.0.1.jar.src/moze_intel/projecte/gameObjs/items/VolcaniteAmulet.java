/*     */ package moze_intel.projecte.gameObjs.items;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Supplier;
/*     */ import moze_intel.projecte.api.block_entity.IDMPedestal;
/*     */ import moze_intel.projecte.api.capabilities.item.IProjectileShooter;
/*     */ import moze_intel.projecte.config.ProjectEConfig;
/*     */ import moze_intel.projecte.gameObjs.entity.EntityLavaProjectile;
/*     */ import moze_intel.projecte.gameObjs.registries.PESoundEvents;
/*     */ import moze_intel.projecte.integration.IntegrationHelper;
/*     */ import moze_intel.projecte.utils.MathUtils;
/*     */ import moze_intel.projecte.utils.PEKeybind;
/*     */ import moze_intel.projecte.utils.PlayerHelper;
/*     */ import moze_intel.projecte.utils.WorldHelper;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.TooltipFlag;
/*     */ import net.minecraft.world.item.context.UseOnContext;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.material.Fluid;
/*     */ import net.minecraft.world.level.material.Fluids;
/*     */ import net.minecraft.world.level.storage.LevelData;
/*     */ import net.minecraft.world.level.storage.ServerLevelData;
/*     */ import net.minecraftforge.common.capabilities.ForgeCapabilities;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.capability.IFluidHandler;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class VolcaniteAmulet extends ItemPE implements IProjectileShooter, IPedestalItem, IFireProtector {
/*     */   public VolcaniteAmulet(Item.Properties props) {
/*  47 */     super(props);
/*  48 */     addItemCapability(moze_intel.projecte.capability.PedestalItemCapabilityWrapper::new);
/*  49 */     addItemCapability(moze_intel.projecte.capability.ProjectileShooterItemCapabilityWrapper::new);
/*  50 */     addItemCapability("curios", IntegrationHelper.CURIO_CAP_SUPPLIER);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasCraftingRemainingItem(ItemStack stack) {
/*  55 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getCraftingRemainingItem(ItemStack stack) {
/*  60 */     return stack.m_41777_();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResult m_6225_(UseOnContext ctx) {
/*  66 */     Level level = ctx.m_43725_();
/*  67 */     Player player = ctx.m_43723_();
/*  68 */     BlockPos pos = ctx.m_8083_();
/*  69 */     ItemStack stack = ctx.m_43722_();
/*  70 */     if (player != null && !level.f_46443_ && PlayerHelper.hasEditPermission((ServerPlayer)player, pos) && consumeFuel(player, stack, 32L, true)) {
/*  71 */       BlockEntity blockEntity = WorldHelper.getBlockEntity((BlockGetter)level, pos);
/*  72 */       Direction sideHit = ctx.m_43719_();
/*  73 */       if (blockEntity != null) {
/*  74 */         Optional<IFluidHandler> capability = blockEntity.getCapability(ForgeCapabilities.FLUID_HANDLER, sideHit).resolve();
/*  75 */         if (capability.isPresent()) {
/*  76 */           ((IFluidHandler)capability.get()).fill(new FluidStack((Fluid)Fluids.f_76195_, 1000), IFluidHandler.FluidAction.EXECUTE);
/*  77 */           return InteractionResult.CONSUME;
/*     */         } 
/*     */       } 
/*  80 */       WorldHelper.placeFluid((ServerPlayer)player, level, pos, sideHit, Fluids.f_76195_, false);
/*  81 */       level.m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)PESoundEvents.TRANSMUTE.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
/*     */     } 
/*  83 */     return InteractionResult.m_19078_(level.f_46443_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shootProjectile(@NotNull Player player, @NotNull ItemStack stack, InteractionHand hand) {
/*  88 */     player.m_9236_().m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)PESoundEvents.TRANSMUTE.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
/*  89 */     EntityLavaProjectile ent = new EntityLavaProjectile(player, player.m_9236_());
/*  90 */     ent.m_37251_((Entity)player, player.m_146909_(), player.m_146908_(), 0.0F, 1.5F, 1.0F);
/*  91 */     player.m_9236_().m_7967_((Entity)ent);
/*  92 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_7373_(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltips, @NotNull TooltipFlag flags) {
/*  97 */     super.m_7373_(stack, level, tooltips, flags);
/*  98 */     tooltips.add(PELang.TOOLTIP_VOLCANITE_1.translate(new Object[] { ClientKeyHelper.getKeyName(PEKeybind.FIRE_PROJECTILE) }));
/*  99 */     tooltips.add(PELang.TOOLTIP_VOLCANITE_2.translate(new Object[0]));
/* 100 */     tooltips.add(PELang.TOOLTIP_VOLCANITE_3.translate(new Object[0]));
/* 101 */     tooltips.add(PELang.TOOLTIP_VOLCANITE_4.translate(new Object[0]));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <PEDESTAL extends BlockEntity & IDMPedestal> boolean updateInPedestal(@NotNull ItemStack stack, @NotNull Level level, @NotNull BlockPos pos, @NotNull PEDESTAL pedestal) {
/* 107 */     if (!level.f_46443_ && ProjectEConfig.server.cooldown.pedestal.volcanite.get() != -1) {
/* 108 */       if (((IDMPedestal)pedestal).getActivityCooldown() == 0) {
/* 109 */         LevelData levelData = level.m_6106_(); if (levelData instanceof ServerLevelData) { ServerLevelData worldInfo = (ServerLevelData)levelData;
/* 110 */           worldInfo.m_6399_(0);
/* 111 */           worldInfo.m_6398_(0);
/* 112 */           worldInfo.m_5565_(false);
/* 113 */           worldInfo.m_5557_(false); }
/*     */         
/* 115 */         ((IDMPedestal)pedestal).setActivityCooldown(ProjectEConfig.server.cooldown.pedestal.volcanite.get());
/*     */       } else {
/* 117 */         ((IDMPedestal)pedestal).decrementActivityCooldown();
/*     */       } 
/*     */     }
/* 120 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<Component> getPedestalDescription() {
/* 126 */     List<Component> list = new ArrayList<>();
/* 127 */     if (ProjectEConfig.server.cooldown.pedestal.volcanite.get() != -1) {
/* 128 */       list.add(PELang.PEDESTAL_VOLCANITE_1.translateColored(ChatFormatting.BLUE, new Object[0]));
/* 129 */       list.add(PELang.PEDESTAL_VOLCANITE_2.translateColored(ChatFormatting.BLUE, new Object[] { MathUtils.tickToSecFormatted(ProjectEConfig.server.cooldown.pedestal.volcanite.get()) }));
/*     */     } 
/* 131 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canProtectAgainstFire(ItemStack stack, ServerPlayer player) {
/* 136 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\VolcaniteAmulet.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */