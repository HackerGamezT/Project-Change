/*     */ package moze_intel.projecte.gameObjs.items.rings;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.function.Supplier;
/*     */ import moze_intel.projecte.api.block_entity.IDMPedestal;
/*     */ import moze_intel.projecte.api.capabilities.item.IPedestalItem;
/*     */ import moze_intel.projecte.api.capabilities.item.IProjectileShooter;
/*     */ import moze_intel.projecte.config.ProjectEConfig;
/*     */ import moze_intel.projecte.gameObjs.entity.EntitySWRGProjectile;
/*     */ import moze_intel.projecte.gameObjs.items.IFlightProvider;
/*     */ import moze_intel.projecte.gameObjs.items.ItemPE;
/*     */ import moze_intel.projecte.gameObjs.registries.PESoundEvents;
/*     */ import moze_intel.projecte.handlers.InternalAbilities;
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
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResultHolder;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LightningBolt;
/*     */ import net.minecraft.world.entity.Mob;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.Level;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class SWRG
/*     */   extends ItemPE
/*     */   implements IPedestalItem, IFlightProvider, IProjectileShooter
/*     */ {
/*     */   public SWRG(Item.Properties props) {
/*  46 */     super(props);
/*  47 */     addItemCapability(moze_intel.projecte.capability.PedestalItemCapabilityWrapper::new);
/*  48 */     addItemCapability(moze_intel.projecte.capability.ProjectileShooterItemCapabilityWrapper::new);
/*  49 */     addItemCapability("curios", IntegrationHelper.CURIO_CAP_SUPPLIER);
/*     */   }
/*     */   
/*     */   private void tick(ItemStack stack, Player player) {
/*  53 */     CompoundTag nbt = stack.m_41784_();
/*  54 */     if (nbt.m_128451_("Mode") > 1)
/*     */     {
/*  56 */       WorldHelper.repelEntitiesSWRG(player.m_9236_(), player.m_20191_().m_82400_(5.0D), player);
/*     */     }
/*  58 */     if ((player.m_9236_()).f_46443_) {
/*     */       return;
/*     */     }
/*  61 */     ServerPlayer playerMP = (ServerPlayer)player;
/*  62 */     if (getEmc(stack) == 0L && !consumeFuel(player, stack, 64L, false)) {
/*  63 */       if (nbt.m_128451_("Mode") > 0) {
/*  64 */         changeMode(player, stack, 0);
/*     */       }
/*  66 */       if ((playerMP.m_150110_()).f_35936_) {
/*  67 */         playerMP.getCapability(InternalAbilities.CAPABILITY).ifPresent(InternalAbilities::disableSwrgFlightOverride);
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*  72 */     if (!(playerMP.m_150110_()).f_35936_) {
/*  73 */       playerMP.getCapability(InternalAbilities.CAPABILITY).ifPresent(InternalAbilities::enableSwrgFlightOverride);
/*     */     }
/*     */     
/*  76 */     if ((playerMP.m_150110_()).f_35935_) {
/*  77 */       if (!isFlyingEnabled(nbt)) {
/*  78 */         changeMode(player, stack, (nbt.m_128451_("Mode") == 0) ? 1 : 3);
/*     */       }
/*  80 */     } else if (isFlyingEnabled(nbt)) {
/*  81 */       changeMode(player, stack, (nbt.m_128451_("Mode") == 1) ? 0 : 2);
/*     */     } 
/*     */     
/*  84 */     float toRemove = 0.0F;
/*     */     
/*  86 */     if ((playerMP.m_150110_()).f_35935_) {
/*  87 */       toRemove = 0.32F;
/*     */     }
/*     */     
/*  90 */     if (nbt.m_128451_("Mode") == 2) {
/*  91 */       toRemove = 0.32F;
/*  92 */     } else if (nbt.m_128451_("Mode") == 3) {
/*  93 */       toRemove = 0.64F;
/*     */     } 
/*     */     
/*  96 */     removeEmc(stack, EMCHelper.removeFractionalEMC(stack, toRemove));
/*     */     
/*  98 */     playerMP.f_19789_ = 0.0F;
/*     */   }
/*     */   
/*     */   private boolean isFlyingEnabled(CompoundTag nbt) {
/* 102 */     return (nbt.m_128451_("Mode") == 1 || nbt.m_128451_("Mode") == 3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_6883_(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int invSlot, boolean isHeldItem) {
/* 107 */     if (invSlot < Inventory.m_36059_() && entity instanceof Player) { Player player = (Player)entity;
/* 108 */       tick(stack, player); }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResultHolder<ItemStack> m_7203_(Level level, Player player, @NotNull InteractionHand hand) {
/* 115 */     ItemStack stack = player.m_21120_(hand);
/* 116 */     if (!level.f_46443_) {
/* 117 */       switch (stack.m_41784_().m_128451_("Mode")) { case 0: 
/*     */         case 1: 
/*     */         case 2: 
/*     */         case 3: 
/*     */         default:
/* 122 */           break; }  int newMode = 0;
/*     */       
/* 124 */       changeMode(player, stack, newMode);
/*     */     } 
/* 126 */     return InteractionResultHolder.m_19090_(stack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeMode(Player player, ItemStack stack, int mode) {
/* 133 */     CompoundTag nbt = stack.m_41784_();
/* 134 */     int oldMode = nbt.m_128451_("Mode");
/* 135 */     if (mode == oldMode) {
/*     */       return;
/*     */     }
/* 138 */     nbt.m_128405_("Mode", mode);
/* 139 */     if (player == null) {
/*     */       return;
/*     */     }
/*     */     
/* 143 */     if (mode == 0 || oldMode == 3) {
/*     */       
/* 145 */       player.m_9236_().m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)PESoundEvents.UNCHARGE.get(), SoundSource.PLAYERS, 0.8F, 1.0F);
/* 146 */     } else if (oldMode == 0 || mode == 3) {
/*     */       
/* 148 */       player.m_9236_().m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)PESoundEvents.HEAL.get(), SoundSource.PLAYERS, 0.8F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canProvideFlight(ItemStack stack, ServerPlayer player) {
/* 156 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_142522_(@NotNull ItemStack stack) {
/* 161 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <PEDESTAL extends net.minecraft.world.level.block.entity.BlockEntity & IDMPedestal> boolean updateInPedestal(@NotNull ItemStack stack, @NotNull Level level, @NotNull BlockPos pos, @NotNull PEDESTAL pedestal) {
/* 167 */     if (!level.f_46443_ && ProjectEConfig.server.cooldown.pedestal.swrg.get() != -1) {
/* 168 */       if (((IDMPedestal)pedestal).getActivityCooldown() <= 0) {
/* 169 */         for (Mob living : level.m_6443_(Mob.class, ((IDMPedestal)pedestal).getEffectBounds(), ent -> { // Byte code:
/*     */               //   0: aload_0
/*     */               //   1: invokevirtual m_5833_ : ()Z
/*     */               //   4: ifne -> 30
/*     */               //   7: aload_0
/*     */               //   8: instanceof net/minecraft/world/entity/TamableAnimal
/*     */               //   11: ifeq -> 26
/*     */               //   14: aload_0
/*     */               //   15: checkcast net/minecraft/world/entity/TamableAnimal
/*     */               //   18: astore_1
/*     */               //   19: aload_1
/*     */               //   20: invokevirtual m_21824_ : ()Z
/*     */               //   23: ifne -> 30
/*     */               //   26: iconst_1
/*     */               //   27: goto -> 31
/*     */               //   30: iconst_0
/*     */               //   31: ireturn
/*     */               // Line number table:
/*     */               //   Java source line number -> byte code offset
/*     */               //   #170	-> 0
/*     */               // Local variable table:
/*     */               //   start	length	slot	name	descriptor
/*     */               //   19	7	1	tamableAnimal	Lnet/minecraft/world/entity/TamableAnimal;
/*     */               //   0	32	0	ent	Lnet/minecraft/world/entity/Mob;
/*     */             })) {
/* 171 */           LightningBolt lightning = (LightningBolt)EntityType.f_20465_.m_20615_(level);
/* 172 */           if (lightning != null) {
/* 173 */             lightning.m_20219_(living.m_20182_());
/* 174 */             level.m_7967_((Entity)lightning);
/*     */           } 
/*     */         } 
/* 177 */         ((IDMPedestal)pedestal).setActivityCooldown(ProjectEConfig.server.cooldown.pedestal.swrg.get());
/*     */       } else {
/* 179 */         ((IDMPedestal)pedestal).decrementActivityCooldown();
/*     */       } 
/*     */     }
/* 182 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<Component> getPedestalDescription() {
/* 188 */     List<Component> list = new ArrayList<>();
/* 189 */     if (ProjectEConfig.server.cooldown.pedestal.swrg.get() != -1) {
/* 190 */       list.add(PELang.PEDESTAL_SWRG_1.translateColored(ChatFormatting.BLUE, new Object[0]));
/* 191 */       list.add(PELang.PEDESTAL_SWRG_2.translateColored(ChatFormatting.BLUE, new Object[] { MathUtils.tickToSecFormatted(ProjectEConfig.server.cooldown.pedestal.swrg.get()) }));
/*     */     } 
/* 193 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shootProjectile(@NotNull Player player, @NotNull ItemStack stack, @Nullable InteractionHand hand) {
/* 198 */     EntitySWRGProjectile projectile = new EntitySWRGProjectile(player, false, player.m_9236_());
/* 199 */     projectile.m_37251_((Entity)player, player.m_146909_(), player.m_146908_(), 0.0F, 1.5F, 1.0F);
/* 200 */     player.m_9236_().m_7967_((Entity)projectile);
/* 201 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\rings\SWRG.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */