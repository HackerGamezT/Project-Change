/*     */ package moze_intel.projecte.gameObjs.items.rings;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.function.Supplier;
/*     */ import moze_intel.projecte.api.block_entity.IDMPedestal;
/*     */ import moze_intel.projecte.api.capabilities.item.IPedestalItem;
/*     */ import moze_intel.projecte.api.capabilities.item.IProjectileShooter;
/*     */ import moze_intel.projecte.config.ProjectEConfig;
/*     */ import moze_intel.projecte.gameObjs.entity.EntityFireProjectile;
/*     */ import moze_intel.projecte.gameObjs.items.IFireProtector;
/*     */ import moze_intel.projecte.integration.IntegrationHelper;
/*     */ import moze_intel.projecte.utils.EMCHelper;
/*     */ import moze_intel.projecte.utils.MathUtils;
/*     */ import moze_intel.projecte.utils.WorldHelper;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.damagesource.DamageSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.Mob;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.context.UseOnContext;
/*     */ import net.minecraft.world.level.Level;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class Ignition
/*     */   extends PEToggleItem
/*     */   implements IPedestalItem, IFireProtector, IProjectileShooter
/*     */ {
/*     */   public Ignition(Item.Properties props) {
/*  40 */     super(props);
/*  41 */     addItemCapability(moze_intel.projecte.capability.PedestalItemCapabilityWrapper::new);
/*  42 */     addItemCapability(moze_intel.projecte.capability.ProjectileShooterItemCapabilityWrapper::new);
/*  43 */     addItemCapability("curios", IntegrationHelper.CURIO_CAP_SUPPLIER);
/*     */   }
/*     */   
/*     */   public void m_6883_(@NotNull ItemStack stack, Level level, @NotNull Entity entity, int inventorySlot, boolean held) {
/*     */     Player player;
/*  48 */     if (!level.f_46443_ && inventorySlot < Inventory.m_36059_() && entity instanceof Player) { player = (Player)entity; }
/*     */     else
/*     */     { return; }
/*  51 */      super.m_6883_(stack, level, entity, inventorySlot, held);
/*  52 */     CompoundTag nbt = stack.m_41784_();
/*  53 */     if (nbt.m_128471_("Active")) {
/*  54 */       if (getEmc(stack) == 0L && !consumeFuel(player, stack, 64L, false)) {
/*  55 */         nbt.m_128379_("Active", false);
/*     */       } else {
/*  57 */         WorldHelper.igniteNearby(level, player);
/*  58 */         removeEmc(stack, EMCHelper.removeFractionalEMC(stack, 0.3199999928474426D));
/*     */       } 
/*     */     } else {
/*  61 */       WorldHelper.extinguishNearby(level, player);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResult m_6225_(@NotNull UseOnContext ctx) {
/*  68 */     return WorldHelper.igniteBlock(ctx);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <PEDESTAL extends net.minecraft.world.level.block.entity.BlockEntity & IDMPedestal> boolean updateInPedestal(@NotNull ItemStack stack, @NotNull Level level, @NotNull BlockPos pos, @NotNull PEDESTAL pedestal) {
/*  74 */     if (!level.f_46443_ && ProjectEConfig.server.cooldown.pedestal.ignition.get() != -1) {
/*  75 */       if (((IDMPedestal)pedestal).getActivityCooldown() == 0) {
/*  76 */         DamageSource fire = level.m_269111_().m_269387_();
/*  77 */         for (Mob living : level.m_45976_(Mob.class, ((IDMPedestal)pedestal).getEffectBounds())) {
/*  78 */           living.m_6469_(fire, 3.0F);
/*  79 */           living.m_20254_(8);
/*     */         } 
/*  81 */         ((IDMPedestal)pedestal).setActivityCooldown(ProjectEConfig.server.cooldown.pedestal.ignition.get());
/*     */       } else {
/*  83 */         ((IDMPedestal)pedestal).decrementActivityCooldown();
/*     */       } 
/*     */     }
/*  86 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<Component> getPedestalDescription() {
/*  92 */     List<Component> list = new ArrayList<>();
/*  93 */     if (ProjectEConfig.server.cooldown.pedestal.ignition.get() != -1) {
/*  94 */       list.add(PELang.PEDESTAL_IGNITION_1.translateColored(ChatFormatting.BLUE, new Object[0]));
/*  95 */       list.add(PELang.PEDESTAL_IGNITION_2.translateColored(ChatFormatting.BLUE, new Object[] { MathUtils.tickToSecFormatted(ProjectEConfig.server.cooldown.pedestal.ignition.get()) }));
/*     */     } 
/*  97 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shootProjectile(@NotNull Player player, @NotNull ItemStack stack, InteractionHand hand) {
/* 102 */     Level level = player.m_9236_();
/* 103 */     if (level.f_46443_) {
/* 104 */       return false;
/*     */     }
/* 106 */     EntityFireProjectile fire = new EntityFireProjectile(player, false, level);
/* 107 */     fire.m_37251_((Entity)player, player.m_146909_(), player.m_146908_(), 0.0F, 1.5F, 1.0F);
/* 108 */     level.m_7967_((Entity)fire);
/* 109 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canProtectAgainstFire(ItemStack stack, ServerPlayer player) {
/* 114 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\rings\Ignition.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */