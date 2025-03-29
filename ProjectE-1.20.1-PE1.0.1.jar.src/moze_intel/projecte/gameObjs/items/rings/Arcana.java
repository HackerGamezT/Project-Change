/*     */ package moze_intel.projecte.gameObjs.items.rings;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.function.Supplier;
/*     */ import moze_intel.projecte.api.capabilities.item.IExtraFunction;
/*     */ import moze_intel.projecte.api.capabilities.item.IProjectileShooter;
/*     */ import moze_intel.projecte.gameObjs.entity.EntityFireProjectile;
/*     */ import moze_intel.projecte.gameObjs.entity.EntitySWRGProjectile;
/*     */ import moze_intel.projecte.gameObjs.items.IFireProtector;
/*     */ import moze_intel.projecte.gameObjs.items.IFlightProvider;
/*     */ import moze_intel.projecte.gameObjs.items.IItemMode;
/*     */ import moze_intel.projecte.gameObjs.items.ItemPE;
/*     */ import moze_intel.projecte.gameObjs.registries.PESoundEvents;
/*     */ import moze_intel.projecte.integration.IntegrationHelper;
/*     */ import moze_intel.projecte.utils.ItemHelper;
/*     */ import moze_intel.projecte.utils.PlayerHelper;
/*     */ import moze_intel.projecte.utils.WorldHelper;
/*     */ import moze_intel.projecte.utils.text.ILangEntry;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.InteractionResultHolder;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.entity.projectile.Snowball;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.TooltipFlag;
/*     */ import net.minecraft.world.item.context.UseOnContext;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class Arcana extends ItemPE implements IItemMode, IFlightProvider, IFireProtector, IExtraFunction, IProjectileShooter {
/*  47 */   private static final ILangEntry[] modes = new ILangEntry[] { (ILangEntry)PELang.MODE_ARCANA_1, (ILangEntry)PELang.MODE_ARCANA_2, (ILangEntry)PELang.MODE_ARCANA_3, (ILangEntry)PELang.MODE_ARCANA_4 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Arcana(Item.Properties props) {
/*  55 */     super(props);
/*  56 */     addItemCapability(moze_intel.projecte.capability.ExtraFunctionItemCapabilityWrapper::new);
/*  57 */     addItemCapability(moze_intel.projecte.capability.ProjectileShooterItemCapabilityWrapper::new);
/*  58 */     addItemCapability(moze_intel.projecte.capability.ModeChangerItemCapabilityWrapper::new);
/*  59 */     addItemCapability("curios", IntegrationHelper.CURIO_CAP_SUPPLIER);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasCraftingRemainingItem(ItemStack stack) {
/*  64 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getCraftingRemainingItem(ItemStack stack) {
/*  69 */     return stack.m_41777_();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILangEntry[] getModeLangEntries() {
/*  74 */     return modes;
/*     */   }
/*     */   
/*     */   private void tick(ItemStack stack, Level level, ServerPlayer player) {
/*  78 */     if (ItemHelper.checkItemNBT(stack, "Active")) {
/*  79 */       switch (getMode(stack)) { case 0:
/*  80 */           WorldHelper.freezeInBoundingBox(level, player.m_20191_().m_82400_(5.0D), (Player)player, true); break;
/*  81 */         case 1: WorldHelper.igniteNearby(level, (Player)player); break;
/*  82 */         case 2: WorldHelper.growNearbyRandomly(true, level, player.m_20183_(), (Player)player); break;
/*  83 */         case 3: WorldHelper.repelEntitiesSWRG(level, player.m_20191_().m_82400_(5.0D), (Player)player);
/*     */           break; }
/*     */     
/*     */     }
/*     */   }
/*     */   
/*     */   public void m_6883_(@NotNull ItemStack stack, Level level, @NotNull Entity entity, int slot, boolean held) {
/*  90 */     if (!level.f_46443_ && slot < Inventory.m_36059_() && entity instanceof ServerPlayer) { ServerPlayer player = (ServerPlayer)entity;
/*  91 */       tick(stack, level, player); }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_7373_(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltips, @NotNull TooltipFlag flags) {
/*  97 */     super.m_7373_(stack, level, tooltips, flags);
/*  98 */     if (ItemHelper.checkItemNBT(stack, "Active")) {
/*  99 */       tooltips.add(getToolTip(stack));
/*     */     } else {
/* 101 */       tooltips.add(PELang.TOOLTIP_ARCANA_INACTIVE.translateColored(ChatFormatting.RED, new Object[0]));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResultHolder<ItemStack> m_7203_(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
/* 108 */     if (!level.f_46443_) {
/* 109 */       CompoundTag compound = player.m_21120_(hand).m_41784_();
/* 110 */       compound.m_128379_("Active", !compound.m_128471_("Active"));
/*     */     } 
/* 112 */     return InteractionResultHolder.m_19090_(player.m_21120_(hand));
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResult m_6225_(UseOnContext ctx) {
/* 118 */     if (getMode(ctx.m_43722_()) == 1) {
/* 119 */       InteractionResult result = WorldHelper.igniteBlock(ctx);
/* 120 */       if (result != InteractionResult.PASS) {
/* 121 */         return result;
/*     */       }
/*     */     } 
/* 124 */     return super.m_6225_(ctx);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doExtraFunction(@NotNull ItemStack stack, @NotNull Player player, InteractionHand hand) {
/* 130 */     Level level = player.m_9236_();
/* 131 */     if (level.f_46443_) {
/* 132 */       return true;
/*     */     }
/* 134 */     if (getMode(stack) == 1) {
/* 135 */       switch (player.m_6350_()) { case SOUTH:
/*     */         case NORTH:
/* 137 */           for (BlockPos pos : BlockPos.m_121940_(player.m_20183_().m_7918_(-30, -5, -3), player.m_20183_().m_7918_(30, 5, 3))) {
/* 138 */             if (level.m_46859_(pos))
/* 139 */               PlayerHelper.checkedPlaceBlock((ServerPlayer)player, pos.m_7949_(), Blocks.f_50083_.m_49966_()); 
/*     */           } 
/*     */           break;
/*     */         case WEST:
/*     */         case EAST:
/* 144 */           for (BlockPos pos : BlockPos.m_121940_(player.m_20183_().m_7918_(-3, -5, -30), player.m_20183_().m_7918_(3, 5, 30))) {
/* 145 */             if (level.m_46859_(pos)) {
/* 146 */               PlayerHelper.checkedPlaceBlock((ServerPlayer)player, pos.m_7949_(), Blocks.f_50083_.m_49966_());
/*     */             }
/*     */           } 
/*     */           break; }
/*     */       
/* 151 */       level.m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)PESoundEvents.POWER.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
/*     */     } 
/* 153 */     return true;
/*     */   } public boolean shootProjectile(@NotNull Player player, @NotNull ItemStack stack, InteractionHand hand) {
/*     */     Snowball snowball;
/*     */     EntityFireProjectile fire;
/*     */     EntitySWRGProjectile lightning;
/* 158 */     Level level = player.m_9236_();
/* 159 */     if (level.f_46443_) {
/* 160 */       return false;
/*     */     }
/* 162 */     switch (getMode(stack)) {
/*     */       case 0:
/* 164 */         snowball = new Snowball(level, (LivingEntity)player);
/* 165 */         snowball.m_37251_((Entity)player, player.m_146909_(), player.m_146908_(), 0.0F, 1.5F, 1.0F);
/* 166 */         level.m_7967_((Entity)snowball);
/* 167 */         snowball.m_5496_(SoundEvents.f_12473_, 1.0F, 1.0F);
/*     */         break;
/*     */       case 1:
/* 170 */         fire = new EntityFireProjectile(player, true, level);
/* 171 */         fire.m_37251_((Entity)player, player.m_146909_(), player.m_146908_(), 0.0F, 1.5F, 1.0F);
/* 172 */         level.m_7967_((Entity)fire);
/* 173 */         fire.m_5496_((SoundEvent)PESoundEvents.POWER.get(), 1.0F, 1.0F);
/*     */         break;
/*     */       case 3:
/* 176 */         lightning = new EntitySWRGProjectile(player, true, level);
/* 177 */         lightning.m_37251_((Entity)player, player.m_146909_(), player.m_146908_(), 0.0F, 1.5F, 1.0F);
/* 178 */         level.m_7967_((Entity)lightning);
/*     */         break;
/*     */     } 
/* 181 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canProtectAgainstFire(ItemStack stack, ServerPlayer player) {
/* 186 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canProvideFlight(ItemStack stack, ServerPlayer player) {
/* 191 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\rings\Arcana.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */