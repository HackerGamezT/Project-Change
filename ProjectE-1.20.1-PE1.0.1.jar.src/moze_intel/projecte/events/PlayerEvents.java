/*     */ package moze_intel.projecte.events;
/*     */ import java.util.Optional;
/*     */ import moze_intel.projecte.PECore;
/*     */ import moze_intel.projecte.api.capabilities.IAlchBagProvider;
/*     */ import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
/*     */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*     */ import moze_intel.projecte.capability.managing.BasicCapabilityResolver;
/*     */ import moze_intel.projecte.gameObjs.items.AlchemicalBag;
/*     */ import moze_intel.projecte.gameObjs.items.armor.PEArmor;
/*     */ import moze_intel.projecte.handlers.CommonInternalAbilities;
/*     */ import moze_intel.projecte.handlers.InternalAbilities;
/*     */ import moze_intel.projecte.handlers.InternalTimers;
/*     */ import moze_intel.projecte.impl.TransmutationOffline;
/*     */ import moze_intel.projecte.impl.capability.AlchBagImpl;
/*     */ import moze_intel.projecte.impl.capability.KnowledgeImpl;
/*     */ import moze_intel.projecte.network.PacketHandler;
/*     */ import moze_intel.projecte.utils.PlayerHelper;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.network.chat.MutableComponent;
/*     */ import net.minecraft.network.protocol.Packet;
/*     */ import net.minecraft.network.protocol.game.ClientboundTakeItemEntityPacket;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.tags.DamageTypeTags;
/*     */ import net.minecraft.world.damagesource.DamageSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EquipmentSlot;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.ArmorItem;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraftforge.common.capabilities.ICapabilityProvider;
/*     */ import net.minecraftforge.event.AttachCapabilitiesEvent;
/*     */ import net.minecraftforge.event.entity.EntityEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingAttackEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*     */ import net.minecraftforge.eventbus.api.EventPriority;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import net.minecraftforge.items.ItemHandlerHelper;
/*     */ import net.minecraftforge.server.ServerLifecycleHooks;
/*     */ 
/*     */ @EventBusSubscriber(modid = "projecte")
/*     */ public class PlayerEvents {
/*     */   @SubscribeEvent
/*     */   public static void cloneEvent(PlayerEvent.Clone event) {
/*  57 */     Player original = event.getOriginal();
/*     */     
/*  59 */     original.reviveCaps();
/*  60 */     original.getCapability(PECapabilities.ALCH_BAG_CAPABILITY).ifPresent(old -> {
/*     */           CompoundTag bags = (CompoundTag)old.serializeNBT();
/*     */           event.getEntity().getCapability(PECapabilities.ALCH_BAG_CAPABILITY).ifPresent(());
/*     */         });
/*  64 */     original.getCapability(PECapabilities.KNOWLEDGE_CAPABILITY).ifPresent(old -> {
/*     */           CompoundTag knowledge = (CompoundTag)old.serializeNBT();
/*     */           
/*     */           event.getEntity().getCapability(PECapabilities.KNOWLEDGE_CAPABILITY).ifPresent(());
/*     */         });
/*  69 */     original.invalidateCaps();
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void respawnEvent(PlayerEvent.PlayerRespawnEvent event) {
/*  75 */     Player player = event.getEntity(); if (player instanceof ServerPlayer) { ServerPlayer serverPlayer = (ServerPlayer)player;
/*  76 */       serverPlayer.getCapability(PECapabilities.KNOWLEDGE_CAPABILITY).ifPresent(c -> c.sync(player));
/*  77 */       serverPlayer.getCapability(PECapabilities.ALCH_BAG_CAPABILITY).ifPresent(c -> c.sync(null, player)); }
/*     */   
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void playerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
/*  83 */     Player player = event.getEntity(); if (player instanceof ServerPlayer) { ServerPlayer serverPlayer = (ServerPlayer)player;
/*     */       
/*  85 */       serverPlayer.getCapability(PECapabilities.KNOWLEDGE_CAPABILITY).ifPresent(c -> c.sync(player));
/*  86 */       serverPlayer.getCapability(PECapabilities.ALCH_BAG_CAPABILITY).ifPresent(c -> c.sync(null, player)); }
/*     */     
/*  88 */     event.getEntity().getCapability(InternalAbilities.CAPABILITY).ifPresent(InternalAbilities::onDimensionChange);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void attachCaps(AttachCapabilitiesEvent<Entity> evt) {
/*  93 */     Object object = evt.getObject(); if (object instanceof Player) { Player player = (Player)object;
/*  94 */       attachCapability(evt, AlchBagImpl.Provider.NAME, (BasicCapabilityResolver<?>)new AlchBagImpl.Provider());
/*  95 */       attachCapability(evt, KnowledgeImpl.Provider.NAME, (BasicCapabilityResolver<?>)new KnowledgeImpl.Provider(player));
/*  96 */       attachCapability(evt, CommonInternalAbilities.NAME, (BasicCapabilityResolver<?>)new CommonInternalAbilities.Provider(player));
/*  97 */       if (player instanceof ServerPlayer) { ServerPlayer serverPlayer = (ServerPlayer)player;
/*  98 */         attachCapability(evt, InternalTimers.NAME, (BasicCapabilityResolver<?>)new InternalTimers.Provider());
/*  99 */         attachCapability(evt, InternalAbilities.NAME, (BasicCapabilityResolver<?>)new InternalAbilities.Provider(serverPlayer)); }
/*     */        }
/*     */   
/*     */   }
/*     */   
/*     */   private static void attachCapability(AttachCapabilitiesEvent<Entity> evt, ResourceLocation name, BasicCapabilityResolver<?> cap) {
/* 105 */     evt.addCapability(name, (ICapabilityProvider)cap);
/* 106 */     Objects.requireNonNull(cap); evt.addListener(cap::invalidateAll);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void playerConnect(PlayerEvent.PlayerLoggedInEvent event) {
/* 111 */     ServerPlayer player = (ServerPlayer)event.getEntity();
/* 112 */     PacketHandler.sendFragmentedEmcPacket(player);
/*     */     
/* 114 */     player.getCapability(PECapabilities.KNOWLEDGE_CAPABILITY).ifPresent(knowledge -> {
/*     */           knowledge.sync(player);
/*     */           
/*     */           PlayerHelper.updateScore(player, PlayerHelper.SCOREBOARD_EMC, knowledge.getEmc());
/*     */         });
/* 119 */     player.getCapability(PECapabilities.ALCH_BAG_CAPABILITY).ifPresent(c -> c.sync(null, player));
/*     */     
/* 121 */     PECore.debugLog("Sent knowledge and bag data to {}", new Object[] { player.m_7755_() });
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onConstruct(EntityEvent.EntityConstructing evt) {
/* 126 */     if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER && evt
/* 127 */       .getEntity() instanceof Player && !(evt.getEntity() instanceof net.minecraftforge.common.util.FakePlayer)) {
/* 128 */       TransmutationOffline.clear(evt.getEntity().m_20148_());
/* 129 */       PECore.debugLog("Clearing offline data cache in preparation to load online data", new Object[0]);
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onHighAlchemistJoin(PlayerEvent.PlayerLoggedInEvent evt) {
/* 135 */     if (PECore.uuids.contains(evt.getEntity().m_20148_().toString())) {
/* 136 */       MutableComponent mutableComponent = PELang.HIGH_ALCHEMIST.translateColored(ChatFormatting.BLUE, new Object[] { ChatFormatting.GOLD, evt.getEntity().m_5446_() });
/* 137 */       ServerLifecycleHooks.getCurrentServer().m_6846_().m_240416_((Component)mutableComponent, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.LOW)
/*     */   public static void pickupItem(EntityItemPickupEvent event) {
/* 143 */     Player player = event.getEntity();
/* 144 */     Level level = player.m_9236_();
/* 145 */     if (level.f_46443_) {
/*     */       return;
/*     */     }
/* 148 */     ItemStack bag = AlchemicalBag.getFirstBagWithSuctionItem(player, (player.m_150109_()).f_35974_);
/* 149 */     if (bag.m_41619_()) {
/*     */       return;
/*     */     }
/* 152 */     Optional<IAlchBagProvider> cap = player.getCapability(PECapabilities.ALCH_BAG_CAPABILITY).resolve();
/* 153 */     if (cap.isEmpty()) {
/*     */       return;
/*     */     }
/* 156 */     IItemHandler handler = ((IAlchBagProvider)cap.get()).getBag(((AlchemicalBag)bag.m_41720_()).color);
/* 157 */     ItemStack remainder = ItemHandlerHelper.insertItemStacked(handler, event.getItem().m_32055_(), false);
/* 158 */     if (remainder.m_41619_()) {
/* 159 */       event.getItem().m_146870_();
/* 160 */       level.m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), SoundEvents.f_12019_, SoundSource.PLAYERS, 0.2F, ((level.f_46441_.m_188501_() - level.f_46441_.m_188501_()) * 0.7F + 1.0F) * 2.0F);
/* 161 */       ((ServerPlayer)player).f_8906_.m_9829_((Packet)new ClientboundTakeItemEntityPacket(event.getItem().m_19879_(), player.m_19879_(), 1));
/*     */     } else {
/* 163 */       event.getItem().m_32045_(remainder);
/*     */     } 
/* 165 */     event.setCanceled(true);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onAttacked(LivingAttackEvent evt) {
/* 171 */     LivingEntity livingEntity = evt.getEntity(); if (livingEntity instanceof ServerPlayer) { ServerPlayer player = (ServerPlayer)livingEntity; if (evt.getSource().m_269533_(DamageTypeTags.f_268745_) && TickEvents.shouldPlayerResistFire(player)) {
/* 172 */         evt.setCanceled(true);
/*     */       } }
/*     */   
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onLivingHurt(LivingHurtEvent evt) {
/* 179 */     float damage = evt.getAmount();
/* 180 */     if (damage > 0.0F) {
/* 181 */       LivingEntity entityLiving = evt.getEntity();
/* 182 */       DamageSource source = evt.getSource();
/*     */ 
/*     */ 
/*     */       
/* 186 */       float totalPercentReduced = getReductionForSlot(entityLiving, source, EquipmentSlot.HEAD, damage) + getReductionForSlot(entityLiving, source, EquipmentSlot.CHEST, damage) + getReductionForSlot(entityLiving, source, EquipmentSlot.LEGS, damage) + getReductionForSlot(entityLiving, source, EquipmentSlot.FEET, damage);
/* 187 */       float damageAfter = (totalPercentReduced >= 1.0F) ? 0.0F : (damage - damage * totalPercentReduced);
/* 188 */       if (damageAfter <= 0.0F) {
/* 189 */         evt.setCanceled(true);
/* 190 */       } else if (damage != damageAfter) {
/* 191 */         evt.setAmount(damageAfter);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static float getReductionForSlot(LivingEntity entityLiving, DamageSource source, EquipmentSlot slot, float damage) {
/* 197 */     ItemStack armorStack = entityLiving.m_6844_(slot);
/* 198 */     Item item = armorStack.m_41720_(); if (item instanceof PEArmor) { PEArmor armorItem = (PEArmor)item;
/* 199 */       ArmorItem.Type type = armorItem.m_266204_();
/* 200 */       if (type.m_266308_() != slot)
/*     */       {
/* 202 */         return 0.0F;
/*     */       }
/*     */ 
/*     */       
/* 206 */       return Math.max(armorItem.getFullSetBaseReduction(), armorItem.getMaxDamageAbsorb(type, source) / damage) * armorItem.getPieceEffectiveness(type); }
/*     */     
/* 208 */     return 0.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\events\PlayerEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */