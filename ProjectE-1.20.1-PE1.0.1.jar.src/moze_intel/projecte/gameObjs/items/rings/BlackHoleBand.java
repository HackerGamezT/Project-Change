/*     */ package moze_intel.projecte.gameObjs.items.rings;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.EnumMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Supplier;
/*     */ import moze_intel.projecte.api.block_entity.IDMPedestal;
/*     */ import moze_intel.projecte.api.capabilities.item.IAlchBagItem;
/*     */ import moze_intel.projecte.api.capabilities.item.IAlchChestItem;
/*     */ import moze_intel.projecte.api.capabilities.item.IPedestalItem;
/*     */ import moze_intel.projecte.gameObjs.block_entities.EmcBlockEntity;
/*     */ import moze_intel.projecte.integration.IntegrationHelper;
/*     */ import moze_intel.projecte.utils.ItemHelper;
/*     */ import moze_intel.projecte.utils.WorldHelper;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.InteractionResultHolder;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.item.ItemEntity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.ClipContext;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.LevelAccessor;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.BucketPickup;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraft.world.phys.BlockHitResult;
/*     */ import net.minecraft.world.phys.HitResult;
/*     */ import net.minecraftforge.common.capabilities.ForgeCapabilities;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import net.minecraftforge.items.ItemHandlerHelper;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class BlackHoleBand extends PEToggleItem implements IAlchBagItem, IAlchChestItem, IPedestalItem {
/*     */   public BlackHoleBand(Item.Properties props) {
/*  50 */     super(props);
/*  51 */     addItemCapability(moze_intel.projecte.capability.AlchBagItemCapabilityWrapper::new);
/*  52 */     addItemCapability(moze_intel.projecte.capability.AlchChestItemCapabilityWrapper::new);
/*  53 */     addItemCapability(moze_intel.projecte.capability.PedestalItemCapabilityWrapper::new);
/*  54 */     addItemCapability("curios", IntegrationHelper.CURIO_CAP_SUPPLIER);
/*     */   }
/*     */   
/*     */   private InteractionResult tryPickupFluid(Level level, Player player, ItemStack stack) {
/*  58 */     BlockHitResult result = m_41435_(level, player, ClipContext.Fluid.SOURCE_ONLY);
/*  59 */     if (result.m_6662_() != HitResult.Type.BLOCK) {
/*  60 */       return InteractionResult.PASS;
/*     */     }
/*  62 */     BlockPos fluidPos = result.m_82425_();
/*  63 */     BlockState state = level.m_8055_(fluidPos);
/*  64 */     if (level.m_7966_(player, fluidPos) && player.m_36204_(fluidPos, result.m_82434_(), stack)) { Block block = state.m_60734_(); if (block instanceof BucketPickup) { BucketPickup pickup = (BucketPickup)block;
/*  65 */         Optional<SoundEvent> sound = pickup.getPickupSound(state);
/*  66 */         ItemStack itemStack = pickup.m_142598_((LevelAccessor)level, fluidPos, state);
/*  67 */         if (!itemStack.m_41619_()) {
/*  68 */           sound.ifPresent(soundEvent -> player.m_9236_().m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), soundEvent, SoundSource.PLAYERS, 1.0F, 1.0F));
/*     */           
/*  70 */           return InteractionResult.m_19078_(level.f_46443_);
/*     */         }  }
/*     */        }
/*  73 */      return InteractionResult.PASS;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResultHolder<ItemStack> m_7203_(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
/*  79 */     ItemStack stack = player.m_21120_(hand);
/*  80 */     InteractionResult result = tryPickupFluid(level, player, stack);
/*  81 */     if (!result.m_19077_() && changeMode(player, stack, hand)) {
/*  82 */       result = InteractionResult.m_19078_(level.f_46443_);
/*     */     }
/*  84 */     return ItemHelper.actionResultFromType(result, stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_6883_(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int slot, boolean held) {
/*  89 */     if (entity instanceof Player) { Player player = (Player)entity; if (ItemHelper.checkItemNBT(stack, "Active")) {
/*  90 */         for (ItemEntity item : level.m_45976_(ItemEntity.class, player.m_20191_().m_82400_(7.0D))) {
/*  91 */           if (ItemHelper.simulateFit((player.m_150109_()).f_35974_, item.m_32055_()) < item.m_32055_().m_41613_()) {
/*  92 */             WorldHelper.gravitateEntityTowards((Entity)item, player.m_20185_(), player.m_20186_(), player.m_20189_());
/*     */           }
/*     */         } 
/*     */       } }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public <PEDESTAL extends BlockEntity & IDMPedestal> boolean updateInPedestal(@NotNull ItemStack stack, @NotNull Level level, @NotNull BlockPos pos, @NotNull PEDESTAL pedestal) {
/* 101 */     Map<Direction, IItemHandler> nearbyHandlers = new EnumMap<>(Direction.class);
/* 102 */     for (ItemEntity item : level.m_6443_(ItemEntity.class, ((IDMPedestal)pedestal).getEffectBounds(), ent -> (!ent.m_5833_() && ent.m_6084_()))) {
/* 103 */       WorldHelper.gravitateEntityTowards((Entity)item, pos.m_123341_() + 0.5D, pos.m_123342_() + 0.5D, pos.m_123343_() + 0.5D);
/* 104 */       if (!level.f_46443_ && item.m_20275_(pos.m_123341_() + 0.5D, pos.m_123342_() + 0.5D, pos.m_123343_() + 0.5D) < 1.21D) {
/* 105 */         for (Direction dir : Direction.values()) {
/*     */           
/* 107 */           IItemHandler inv = nearbyHandlers.computeIfAbsent(dir, direction -> {
/*     */                 BlockEntity candidate = WorldHelper.getBlockEntity((BlockGetter)level, pos.m_121945_(dir));
/*     */ 
/*     */                 
/*     */                 return (candidate == null) ? null : WorldHelper.getItemHandler(candidate, dir);
/*     */               });
/*     */           
/* 114 */           ItemStack result = ItemHandlerHelper.insertItemStacked(inv, item.m_32055_(), false);
/* 115 */           if (result.m_41619_()) {
/* 116 */             item.m_146870_();
/*     */             break;
/*     */           } 
/* 119 */           item.m_32045_(result);
/*     */         } 
/*     */       }
/*     */     } 
/* 123 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<Component> getPedestalDescription() {
/* 129 */     return Lists.newArrayList((Object[])new Component[] { (Component)PELang.PEDESTAL_BLACK_HOLE_BAND_1.translateColored(ChatFormatting.BLUE, new Object[0]), (Component)PELang.PEDESTAL_BLACK_HOLE_BAND_2
/* 130 */           .translateColored(ChatFormatting.BLUE, new Object[0]) });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean updateInAlchChest(@NotNull Level level, @NotNull BlockPos pos, @NotNull ItemStack stack) {
/* 135 */     if (ItemHelper.checkItemNBT(stack, "Active")) {
/* 136 */       EmcBlockEntity chest = (EmcBlockEntity)WorldHelper.getBlockEntity(EmcBlockEntity.class, (BlockGetter)level, pos, true);
/* 137 */       if (chest != null) {
/* 138 */         int x = pos.m_123341_();
/* 139 */         int y = pos.m_123342_();
/* 140 */         int z = pos.m_123343_();
/* 141 */         AABB aabb = new AABB((x - 5), (y - 5), (z - 5), (x + 5), (y + 5), (z + 5));
/* 142 */         double centeredX = x + 0.5D;
/* 143 */         double centeredY = y + 0.5D;
/* 144 */         double centeredZ = z + 0.5D;
/* 145 */         for (ItemEntity e : level.m_6443_(ItemEntity.class, aabb, ent -> (!ent.m_5833_() && ent.m_6084_()))) {
/* 146 */           WorldHelper.gravitateEntityTowards((Entity)e, centeredX, centeredY, centeredZ);
/* 147 */           if (!level.f_46443_ && e.m_20275_(centeredX, centeredY, centeredZ) < 1.21D) {
/* 148 */             chest.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(inv -> {
/*     */                   ItemStack result = ItemHandlerHelper.insertItemStacked(inv, e.m_32055_(), false);
/*     */                   if (!result.m_41619_()) {
/*     */                     e.m_32045_(result);
/*     */                   } else {
/*     */                     e.m_146870_();
/*     */                   } 
/*     */                 });
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 160 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean updateInAlchBag(@NotNull IItemHandler inv, @NotNull Player player, @NotNull ItemStack stack) {
/* 165 */     if (ItemHelper.checkItemNBT(stack, "Active")) {
/* 166 */       for (ItemEntity e : player.m_9236_().m_45976_(ItemEntity.class, player.m_20191_().m_82400_(5.0D))) {
/* 167 */         WorldHelper.gravitateEntityTowards((Entity)e, player.m_20185_(), player.m_20186_(), player.m_20189_());
/*     */       }
/*     */     }
/* 170 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\rings\BlackHoleBand.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */