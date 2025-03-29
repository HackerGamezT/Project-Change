/*     */ package moze_intel.projecte.gameObjs.items.rings;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.function.Supplier;
/*     */ import moze_intel.projecte.api.block_entity.IDMPedestal;
/*     */ import moze_intel.projecte.api.capabilities.item.IItemCharge;
/*     */ import moze_intel.projecte.api.capabilities.item.IPedestalItem;
/*     */ import moze_intel.projecte.config.ProjectEConfig;
/*     */ import moze_intel.projecte.gameObjs.PETags;
/*     */ import moze_intel.projecte.gameObjs.items.IBarHelper;
/*     */ import moze_intel.projecte.utils.EMCHelper;
/*     */ import moze_intel.projecte.utils.ItemHelper;
/*     */ import moze_intel.projecte.utils.WorldHelper;
/*     */ import moze_intel.projecte.utils.text.ILangEntry;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.util.profiling.ProfilerFiller;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResultHolder;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.Mob;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.TooltipFlag;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.ChunkPos;
/*     */ import net.minecraft.world.level.GameRules;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.entity.TickingBlockEntity;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.chunk.LevelChunk;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TimeWatch
/*     */   extends PEToggleItem
/*     */   implements IPedestalItem, IItemCharge, IBarHelper
/*     */ {
/*     */   public TimeWatch(Item.Properties props) {
/*  53 */     super(props);
/*  54 */     addItemCapability(moze_intel.projecte.capability.PedestalItemCapabilityWrapper::new);
/*  55 */     addItemCapability(moze_intel.projecte.capability.ChargeItemCapabilityWrapper::new);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResultHolder<ItemStack> m_7203_(Level level, Player player, @NotNull InteractionHand hand) {
/*  61 */     ItemStack stack = player.m_21120_(hand);
/*  62 */     if (!level.f_46443_) {
/*  63 */       if (!ProjectEConfig.server.items.enableTimeWatch.get()) {
/*  64 */         player.m_213846_((Component)PELang.TIME_WATCH_DISABLED.translate(new Object[0]));
/*  65 */         return InteractionResultHolder.m_19100_(stack);
/*     */       } 
/*  67 */       byte current = getTimeBoost(stack);
/*  68 */       setTimeBoost(stack, (byte)((current == 2) ? 0 : (current + 1)));
/*  69 */       player.m_213846_((Component)PELang.TIME_WATCH_MODE_SWITCH.translate(new Object[] { getTimeName(stack) }));
/*     */     } 
/*  71 */     return InteractionResultHolder.m_19090_(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_6883_(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int invSlot, boolean isHeld) {
/*  76 */     super.m_6883_(stack, level, entity, invSlot, isHeld);
/*  77 */     if (entity instanceof Player) { Player player = (Player)entity; if (invSlot < Inventory.m_36059_() && ProjectEConfig.server.items.enableTimeWatch.get()) {
/*     */         int bonusTicks;
/*     */         float mobSlowdown;
/*  80 */         byte timeControl = getTimeBoost(stack);
/*  81 */         if (!level.f_46443_ && level.m_46469_().m_46207_(GameRules.f_46140_)) {
/*  82 */           ServerLevel serverWorld = (ServerLevel)level;
/*  83 */           if (timeControl == 1) {
/*  84 */             serverWorld.m_8615_(Math.min(level.m_46468_() + (getCharge(stack) + 1) * 4L, Long.MAX_VALUE));
/*  85 */           } else if (timeControl == 2) {
/*  86 */             long l = (getCharge(stack) + 1);
/*  87 */             if (level.m_46468_() - l * 4L < 0L) {
/*  88 */               serverWorld.m_8615_(0L);
/*     */             } else {
/*  90 */               serverWorld.m_8615_(level.m_46468_() - l * 4L);
/*     */             } 
/*     */           } 
/*     */         } 
/*  94 */         if (level.f_46443_ || !ItemHelper.checkItemNBT(stack, "Active")) {
/*     */           return;
/*     */         }
/*  97 */         long reqEmc = EMCHelper.removeFractionalEMC(stack, getEmcPerTick(getCharge(stack)));
/*  98 */         if (!consumeFuel(player, stack, reqEmc, true)) {
/*     */           return;
/*     */         }
/* 101 */         int charge = getCharge(stack);
/*     */ 
/*     */         
/* 104 */         if (charge == 0) {
/* 105 */           bonusTicks = 8;
/* 106 */           mobSlowdown = 0.25F;
/* 107 */         } else if (charge == 1) {
/* 108 */           bonusTicks = 12;
/* 109 */           mobSlowdown = 0.16F;
/*     */         } else {
/* 111 */           bonusTicks = 16;
/* 112 */           mobSlowdown = 0.12F;
/*     */         } 
/* 114 */         AABB bBox = player.m_20191_().m_82400_(8.0D);
/* 115 */         speedUpBlockEntities(level, bonusTicks, bBox);
/* 116 */         speedUpRandomTicks(level, bonusTicks, bBox);
/* 117 */         slowMobs(level, bBox, mobSlowdown);
/*     */         return;
/*     */       }  }
/*     */      } private void slowMobs(Level level, AABB bBox, double mobSlowdown) {
/* 121 */     if (bBox == null) {
/*     */       return;
/*     */     }
/*     */     
/* 125 */     for (Mob ent : level.m_45976_(Mob.class, bBox)) {
/* 126 */       ent.m_20256_(ent.m_20184_().m_82542_(mobSlowdown, 1.0D, mobSlowdown));
/*     */     }
/*     */   }
/*     */   
/*     */   private void speedUpBlockEntities(Level level, int bonusTicks, AABB bBox) {
/* 131 */     if (bBox == null || bonusTicks == 0) {
/*     */       return;
/*     */     }
/*     */     
/* 135 */     for (BlockEntity blockEntity : WorldHelper.getBlockEntitiesWithinAABB(level, bBox)) {
/* 136 */       if (!blockEntity.m_58901_() && !PETags.BlockEntities.BLACKLIST_TIME_WATCH_LOOKUP.contains(blockEntity.m_58903_())) {
/* 137 */         BlockPos pos = blockEntity.m_58899_();
/* 138 */         if (level.m_183438_(ChunkPos.m_151388_(pos))) {
/* 139 */           LevelChunk chunk = level.m_46745_(pos);
/* 140 */           LevelChunk.RebindableTickingBlockEntityWrapper tickingWrapper = (LevelChunk.RebindableTickingBlockEntityWrapper)chunk.f_156362_.get(pos);
/* 141 */           if (tickingWrapper != null && !tickingWrapper.m_142220_()) {
/* 142 */             TickingBlockEntity tickingBlockEntity = tickingWrapper.f_156444_; if (tickingBlockEntity instanceof LevelChunk.BoundTickingBlockEntity) { LevelChunk.BoundTickingBlockEntity tickingBE = (LevelChunk.BoundTickingBlockEntity)tickingBlockEntity;
/*     */ 
/*     */               
/* 145 */               if (chunk.m_156410_(pos)) {
/* 146 */                 ProfilerFiller profiler = level.m_46473_();
/* 147 */                 Objects.requireNonNull(tickingWrapper); profiler.m_6521_(tickingWrapper::m_142280_);
/* 148 */                 BlockState state = chunk.m_8055_(pos);
/* 149 */                 if (blockEntity.m_58903_().m_155262_(state)) {
/* 150 */                   for (int j = 0; j < bonusTicks; j++) {
/* 151 */                     tickingBE.f_156429_.m_155252_(level, pos, state, blockEntity);
/*     */                   }
/*     */                 }
/* 154 */                 profiler.m_7238_();
/*     */               } 
/*     */               continue; }
/*     */             
/* 158 */             for (int i = 0; i < bonusTicks; i++) {
/* 159 */               tickingWrapper.m_142224_();
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void speedUpRandomTicks(Level level, int bonusTicks, AABB bBox) {
/*     */     ServerLevel serverLevel;
/* 169 */     if (bBox != null && bonusTicks != 0 && level instanceof ServerLevel) { serverLevel = (ServerLevel)level; }
/*     */     else
/*     */     { return; }
/*     */     
/* 173 */     for (BlockPos pos : WorldHelper.getPositionsFromBox(bBox)) {
/* 174 */       if (WorldHelper.isBlockLoaded((BlockGetter)serverLevel, pos)) {
/* 175 */         BlockState state = serverLevel.m_8055_(pos);
/* 176 */         Block block = state.m_60734_();
/* 177 */         if (state.m_60823_() && !state.m_204336_(PETags.Blocks.BLACKLIST_TIME_WATCH) && !(block instanceof net.minecraft.world.level.block.LiquidBlock) && !(block instanceof net.minecraft.world.level.block.BonemealableBlock) && !(block instanceof net.minecraftforge.common.IPlantable)) {
/*     */ 
/*     */           
/* 180 */           pos = pos.m_7949_();
/* 181 */           for (int i = 0; i < bonusTicks; i++) {
/* 182 */             state.m_222972_(serverLevel, pos, serverLevel.f_46441_);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private ILangEntry getTimeName(ItemStack stack) {
/* 190 */     byte mode = getTimeBoost(stack);
/* 191 */     switch (mode) { case 0: case 1: case 2:  }  return 
/*     */ 
/*     */ 
/*     */       
/* 195 */       (ILangEntry)PELang.INVALID_MODE;
/*     */   }
/*     */ 
/*     */   
/*     */   private byte getTimeBoost(ItemStack stack) {
/* 200 */     return stack.m_41782_() ? stack.m_41784_().m_128445_("TimeMode") : 0;
/*     */   }
/*     */   
/*     */   private void setTimeBoost(ItemStack stack, byte time) {
/* 204 */     stack.m_41784_().m_128344_("TimeMode", (byte)Mth.m_14045_(time, 0, 2));
/*     */   }
/*     */   
/*     */   public double getEmcPerTick(int charge) {
/* 208 */     return (charge + 2) / 2.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_7373_(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltips, @NotNull TooltipFlag flags) {
/* 213 */     super.m_7373_(stack, level, tooltips, flags);
/* 214 */     tooltips.add(PELang.TOOLTIP_TIME_WATCH_1.translate(new Object[0]));
/* 215 */     tooltips.add(PELang.TOOLTIP_TIME_WATCH_2.translate(new Object[0]));
/* 216 */     if (stack.m_41782_()) {
/* 217 */       tooltips.add(PELang.TIME_WATCH_MODE.translate(new Object[] { getTimeName(stack) }));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <PEDESTAL extends BlockEntity & IDMPedestal> boolean updateInPedestal(@NotNull ItemStack stack, @NotNull Level level, @NotNull BlockPos pos, @NotNull PEDESTAL pedestal) {
/* 225 */     if (!level.f_46443_ && ProjectEConfig.server.items.enableTimeWatch.get()) {
/* 226 */       AABB bBox = ((IDMPedestal)pedestal).getEffectBounds();
/* 227 */       if (ProjectEConfig.server.effects.timePedBonus.get() > 0) {
/* 228 */         speedUpBlockEntities(level, ProjectEConfig.server.effects.timePedBonus.get(), bBox);
/* 229 */         speedUpRandomTicks(level, ProjectEConfig.server.effects.timePedBonus.get(), bBox);
/*     */       } 
/* 231 */       if (ProjectEConfig.server.effects.timePedMobSlowness.get() < 1.0D) {
/* 232 */         slowMobs(level, bBox, ProjectEConfig.server.effects.timePedMobSlowness.get());
/*     */       }
/*     */     } 
/* 235 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<Component> getPedestalDescription() {
/* 241 */     List<Component> list = new ArrayList<>();
/* 242 */     if (ProjectEConfig.server.effects.timePedBonus.get() > 0) {
/* 243 */       list.add(PELang.PEDESTAL_TIME_WATCH_1.translateColored(ChatFormatting.BLUE, new Object[] { Integer.valueOf(ProjectEConfig.server.effects.timePedBonus.get()) }));
/*     */     }
/* 245 */     if (ProjectEConfig.server.effects.timePedMobSlowness.get() < 1.0D) {
/* 246 */       list.add(PELang.PEDESTAL_TIME_WATCH_2.translateColored(ChatFormatting.BLUE, new Object[] { String.format("%.3f", new Object[] { Double.valueOf(ProjectEConfig.server.effects.timePedMobSlowness.get()) }) }));
/*     */     }
/* 248 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumCharges(@NotNull ItemStack stack) {
/* 253 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_142522_(@NotNull ItemStack stack) {
/* 258 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getWidthForBar(ItemStack stack) {
/* 263 */     return 1.0F - getChargePercent(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_142158_(@NotNull ItemStack stack) {
/* 268 */     return getScaledBarWidth(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_142159_(@NotNull ItemStack stack) {
/* 273 */     return getColorForBar(stack);
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\rings\TimeWatch.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */