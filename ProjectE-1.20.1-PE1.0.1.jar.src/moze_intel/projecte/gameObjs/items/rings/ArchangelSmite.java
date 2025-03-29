/*     */ package moze_intel.projecte.gameObjs.items.rings;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.function.Supplier;
/*     */ import moze_intel.projecte.PECore;
/*     */ import moze_intel.projecte.api.block_entity.IDMPedestal;
/*     */ import moze_intel.projecte.api.capabilities.item.IPedestalItem;
/*     */ import moze_intel.projecte.config.ProjectEConfig;
/*     */ import moze_intel.projecte.gameObjs.entity.EntityHomingArrow;
/*     */ import moze_intel.projecte.network.PacketHandler;
/*     */ import moze_intel.projecte.network.packets.IPEPacket;
/*     */ import moze_intel.projecte.network.packets.to_server.LeftClickArchangelPKT;
/*     */ import moze_intel.projecte.utils.EMCHelper;
/*     */ import moze_intel.projecte.utils.MathUtils;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResultHolder;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.Mob;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.Items;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.common.util.FakePlayerFactory;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class ArchangelSmite extends PEToggleItem implements IPedestalItem {
/*     */   public ArchangelSmite(Item.Properties props) {
/*  41 */     super(props);
/*  42 */     MinecraftForge.EVENT_BUS.addListener(this::emptyLeftClick);
/*  43 */     MinecraftForge.EVENT_BUS.addListener(this::leftClickBlock);
/*  44 */     addItemCapability(moze_intel.projecte.capability.PedestalItemCapabilityWrapper::new);
/*     */   }
/*     */   
/*     */   public void fireVolley(ItemStack stack, Player player) {
/*  48 */     for (int i = 0; i < 10; i++) {
/*  49 */       fireArrow(stack, player.m_9236_(), (LivingEntity)player, 4.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private void emptyLeftClick(PlayerInteractEvent.LeftClickEmpty evt) {
/*  54 */     PacketHandler.sendToServer((IPEPacket)new LeftClickArchangelPKT());
/*     */   }
/*     */   
/*     */   private void leftClickBlock(PlayerInteractEvent.LeftClickBlock evt) {
/*  58 */     if (!(evt.getLevel()).f_46443_ && evt.getUseItem() != Event.Result.DENY && !evt.getItemStack().m_41619_() && evt.getItemStack().m_41720_() == this) {
/*  59 */       fireVolley(evt.getItemStack(), evt.getEntity());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
/*  65 */     if (!(player.m_9236_()).f_46443_) {
/*  66 */       fireVolley(stack, player);
/*     */     }
/*  68 */     return super.onLeftClickEntity(stack, player, entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_6883_(@NotNull ItemStack stack, Level level, @NotNull Entity entity, int invSlot, boolean isSelected) {
/*  73 */     if (!level.f_46443_ && getMode(stack) == 1 && entity instanceof LivingEntity) {
/*  74 */       fireArrow(stack, level, (LivingEntity)entity, 1.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResultHolder<ItemStack> m_7203_(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
/*  81 */     if (!level.f_46443_) {
/*  82 */       fireArrow(player.m_21120_(hand), level, (LivingEntity)player, 1.0F);
/*     */     }
/*  84 */     return InteractionResultHolder.m_19090_(player.m_21120_(hand));
/*     */   }
/*     */   
/*     */   private void fireArrow(ItemStack ring, Level level, LivingEntity shooter, float inaccuracy) {
/*  88 */     EntityHomingArrow arrow = new EntityHomingArrow(level, shooter, 2.0F);
/*  89 */     if (shooter instanceof Player) { Player player = (Player)shooter; if (consumeFuel(player, ring, EMCHelper.getEmcValue((ItemLike)Items.f_42412_), true))
/*  90 */       { arrow.m_37251_((Entity)shooter, shooter.m_146909_(), shooter.m_146908_(), 0.0F, 3.0F, inaccuracy);
/*  91 */         level.m_6263_(null, shooter.m_20185_(), shooter.m_20186_(), shooter.m_20189_(), SoundEvents.f_11687_, SoundSource.PLAYERS, 1.0F, 1.0F / (level.f_46441_.m_188501_() * 0.4F + 1.2F));
/*  92 */         level.m_7967_((Entity)arrow); return; }  return; }  arrow.m_37251_((Entity)shooter, shooter.m_146909_(), shooter.m_146908_(), 0.0F, 3.0F, inaccuracy); level.m_6263_(null, shooter.m_20185_(), shooter.m_20186_(), shooter.m_20189_(), SoundEvents.f_11687_, SoundSource.PLAYERS, 1.0F, 1.0F / (level.f_46441_.m_188501_() * 0.4F + 1.2F)); level.m_7967_((Entity)arrow);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <PEDESTAL extends net.minecraft.world.level.block.entity.BlockEntity & IDMPedestal> boolean updateInPedestal(@NotNull ItemStack stack, @NotNull Level level, @NotNull BlockPos pos, @NotNull PEDESTAL pedestal) {
/*  99 */     if (!level.f_46443_ && ProjectEConfig.server.cooldown.pedestal.archangel.get() != -1) {
/* 100 */       if (((IDMPedestal)pedestal).getActivityCooldown() == 0) {
/* 101 */         if (!level.m_45976_(Mob.class, ((IDMPedestal)pedestal).getEffectBounds()).isEmpty()) {
/* 102 */           double centeredX = pos.m_123341_() + 0.5D;
/* 103 */           double centeredY = pos.m_123342_() + 0.5D;
/* 104 */           double centeredZ = pos.m_123343_() + 0.5D;
/* 105 */           for (int i = 0; i < 3; i++) {
/* 106 */             EntityHomingArrow arrow = new EntityHomingArrow(level, (LivingEntity)FakePlayerFactory.get((ServerLevel)level, PECore.FAKEPLAYER_GAMEPROFILE), 2.0F);
/* 107 */             arrow.m_20343_(centeredX, centeredY + 2.0D, centeredZ);
/* 108 */             arrow.m_20334_(0.0D, 1.0D, 0.0D);
/* 109 */             arrow.m_5496_(SoundEvents.f_11687_, 1.0F, 1.0F / (level.f_46441_.m_188501_() * 0.4F + 1.2F) + 0.5F);
/* 110 */             level.m_7967_((Entity)arrow);
/*     */           } 
/*     */         } 
/* 113 */         ((IDMPedestal)pedestal).setActivityCooldown(ProjectEConfig.server.cooldown.pedestal.archangel.get());
/*     */       } else {
/* 115 */         ((IDMPedestal)pedestal).decrementActivityCooldown();
/*     */       } 
/*     */     }
/* 118 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<Component> getPedestalDescription() {
/* 124 */     List<Component> list = new ArrayList<>();
/* 125 */     if (ProjectEConfig.server.cooldown.pedestal.archangel.get() != -1) {
/* 126 */       list.add(PELang.PEDESTAL_ARCHANGEL_1.translateColored(ChatFormatting.BLUE, new Object[0]));
/* 127 */       list.add(PELang.PEDESTAL_ARCHANGEL_2.translateColored(ChatFormatting.BLUE, new Object[] { MathUtils.tickToSecFormatted(ProjectEConfig.server.cooldown.pedestal.archangel.get()) }));
/*     */     } 
/* 129 */     return list;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\rings\ArchangelSmite.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */