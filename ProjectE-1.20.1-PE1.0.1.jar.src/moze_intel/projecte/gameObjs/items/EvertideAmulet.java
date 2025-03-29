/*     */ package moze_intel.projecte.gameObjs.items;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Supplier;
/*     */ import moze_intel.projecte.api.block_entity.IDMPedestal;
/*     */ import moze_intel.projecte.api.capabilities.item.IProjectileShooter;
/*     */ import moze_intel.projecte.capability.BasicItemCapability;
/*     */ import moze_intel.projecte.config.ProjectEConfig;
/*     */ import moze_intel.projecte.gameObjs.entity.EntityWaterProjectile;
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
/*     */ import net.minecraft.tags.FluidTags;
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
/*     */ import net.minecraftforge.common.capabilities.Capability;
/*     */ import net.minecraftforge.common.capabilities.ForgeCapabilities;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.capability.IFluidHandler;
/*     */ import net.minecraftforge.fluids.capability.IFluidHandlerItem;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class EvertideAmulet extends ItemPE implements IProjectileShooter, IPedestalItem {
/*     */   public EvertideAmulet(Item.Properties props) {
/*  51 */     super(props);
/*  52 */     addItemCapability(moze_intel.projecte.capability.PedestalItemCapabilityWrapper::new);
/*  53 */     addItemCapability(InfiniteFluidHandler::new);
/*  54 */     addItemCapability(moze_intel.projecte.capability.ProjectileShooterItemCapabilityWrapper::new);
/*  55 */     addItemCapability("curios", IntegrationHelper.CURIO_CAP_SUPPLIER);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasCraftingRemainingItem(ItemStack stack) {
/*  60 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getCraftingRemainingItem(ItemStack stack) {
/*  65 */     return stack.m_41777_();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResult m_6225_(UseOnContext ctx) {
/*  71 */     Player player = ctx.m_43723_();
/*  72 */     if (player == null) {
/*  73 */       return InteractionResult.FAIL;
/*     */     }
/*  75 */     Level level = ctx.m_43725_();
/*  76 */     BlockPos pos = ctx.m_8083_();
/*  77 */     if (!level.f_46443_ && PlayerHelper.hasEditPermission((ServerPlayer)player, pos)) {
/*  78 */       BlockEntity blockEntity = WorldHelper.getBlockEntity((BlockGetter)level, pos);
/*  79 */       Direction sideHit = ctx.m_43719_();
/*  80 */       if (blockEntity != null) {
/*  81 */         Optional<IFluidHandler> capability = blockEntity.getCapability(ForgeCapabilities.FLUID_HANDLER, sideHit).resolve();
/*  82 */         if (capability.isPresent()) {
/*  83 */           ((IFluidHandler)capability.get()).fill(new FluidStack((Fluid)Fluids.f_76193_, 1000), IFluidHandler.FluidAction.EXECUTE);
/*  84 */           return InteractionResult.CONSUME;
/*     */         } 
/*     */       } 
/*  87 */       WorldHelper.placeFluid((ServerPlayer)player, level, pos, sideHit, Fluids.f_76193_, !ProjectEConfig.server.items.opEvertide.get());
/*  88 */       level.m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)PESoundEvents.WATER_MAGIC.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
/*     */     } 
/*  90 */     return InteractionResult.m_19078_(level.f_46443_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shootProjectile(@NotNull Player player, @NotNull ItemStack stack, InteractionHand hand) {
/*  95 */     Level level = player.m_9236_();
/*  96 */     if (ProjectEConfig.server.items.opEvertide.get() || !level.m_6042_().f_63857_()) {
/*  97 */       level.m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)PESoundEvents.WATER_MAGIC.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
/*  98 */       EntityWaterProjectile ent = new EntityWaterProjectile(player, level);
/*  99 */       ent.m_37251_((Entity)player, player.m_146909_(), player.m_146908_(), 0.0F, 1.5F, 1.0F);
/* 100 */       level.m_7967_((Entity)ent);
/* 101 */       return true;
/*     */     } 
/* 103 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_7373_(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltips, @NotNull TooltipFlag flags) {
/* 108 */     super.m_7373_(stack, level, tooltips, flags);
/* 109 */     tooltips.add(PELang.TOOLTIP_EVERTIDE_1.translate(new Object[] { ClientKeyHelper.getKeyName(PEKeybind.FIRE_PROJECTILE) }));
/* 110 */     tooltips.add(PELang.TOOLTIP_EVERTIDE_2.translate(new Object[0]));
/* 111 */     tooltips.add(PELang.TOOLTIP_EVERTIDE_3.translate(new Object[0]));
/* 112 */     tooltips.add(PELang.TOOLTIP_EVERTIDE_4.translate(new Object[0]));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <PEDESTAL extends BlockEntity & IDMPedestal> boolean updateInPedestal(@NotNull ItemStack stack, @NotNull Level level, @NotNull BlockPos pos, @NotNull PEDESTAL pedestal) {
/* 118 */     if (!level.f_46443_ && ProjectEConfig.server.cooldown.pedestal.evertide.get() != -1) {
/* 119 */       if (((IDMPedestal)pedestal).getActivityCooldown() == 0) {
/* 120 */         LevelData levelData = level.m_6106_(); if (levelData instanceof ServerLevelData) { ServerLevelData worldInfo = (ServerLevelData)levelData;
/* 121 */           int i = (300 + level.f_46441_.m_188503_(600)) * 20;
/* 122 */           worldInfo.m_6399_(i);
/* 123 */           worldInfo.m_6398_(i);
/* 124 */           worldInfo.m_5565_(true); }
/*     */         
/* 126 */         ((IDMPedestal)pedestal).setActivityCooldown(ProjectEConfig.server.cooldown.pedestal.evertide.get());
/*     */       } else {
/* 128 */         ((IDMPedestal)pedestal).decrementActivityCooldown();
/*     */       } 
/*     */     }
/* 131 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<Component> getPedestalDescription() {
/* 137 */     List<Component> list = new ArrayList<>();
/* 138 */     if (ProjectEConfig.server.cooldown.pedestal.evertide.get() != -1) {
/* 139 */       list.add(PELang.PEDESTAL_EVERTIDE_1.translateColored(ChatFormatting.BLUE, new Object[0]));
/* 140 */       list.add(PELang.PEDESTAL_EVERTIDE_2.translateColored(ChatFormatting.BLUE, new Object[] { MathUtils.tickToSecFormatted(ProjectEConfig.server.cooldown.pedestal.evertide.get()) }));
/*     */     } 
/* 142 */     return list;
/*     */   }
/*     */   
/*     */   private static class InfiniteFluidHandler
/*     */     extends BasicItemCapability<IFluidHandlerItem>
/*     */     implements IFluidHandlerItem {
/*     */     @NotNull
/*     */     public ItemStack getContainer() {
/* 150 */       return getStack();
/*     */     }
/*     */ 
/*     */     
/*     */     public int getTanks() {
/* 155 */       return 1;
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public FluidStack getFluidInTank(int tank) {
/* 161 */       return (tank == 0) ? new FluidStack((Fluid)Fluids.f_76193_, 2147483647) : FluidStack.EMPTY;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getTankCapacity(int tank) {
/* 166 */       return (tank == 0) ? Integer.MAX_VALUE : 0;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
/* 171 */       return stack.getFluid().m_205067_(FluidTags.f_13131_);
/*     */     }
/*     */ 
/*     */     
/*     */     public int fill(FluidStack resource, IFluidHandler.FluidAction action) {
/* 176 */       if (resource.getFluid().m_205067_(FluidTags.f_13131_)) {
/* 177 */         return resource.getAmount();
/*     */       }
/* 179 */       return 0;
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public FluidStack drain(FluidStack resource, IFluidHandler.FluidAction action) {
/* 185 */       if (resource.getFluid().m_205067_(FluidTags.f_13131_)) {
/* 186 */         return resource;
/*     */       }
/* 188 */       return FluidStack.EMPTY;
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public FluidStack drain(int maxDrain, IFluidHandler.FluidAction action) {
/* 194 */       return new FluidStack((Fluid)Fluids.f_76193_, maxDrain);
/*     */     }
/*     */ 
/*     */     
/*     */     public Capability<IFluidHandlerItem> getCapability() {
/* 199 */       return ForgeCapabilities.FLUID_HANDLER_ITEM;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\EvertideAmulet.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */