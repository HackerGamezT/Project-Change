/*     */ package moze_intel.projecte.gameObjs.items.rings;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.function.Supplier;
/*     */ import moze_intel.projecte.api.block_entity.IDMPedestal;
/*     */ import moze_intel.projecte.api.capabilities.item.IPedestalItem;
/*     */ import moze_intel.projecte.config.ProjectEConfig;
/*     */ import moze_intel.projecte.utils.EMCHelper;
/*     */ import moze_intel.projecte.utils.MathUtils;
/*     */ import moze_intel.projecte.utils.WorldHelper;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.core.NonNullList;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.Items;
/*     */ import net.minecraft.world.item.context.UseOnContext;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.LevelReader;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.BonemealableBlock;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraftforge.common.IPlantable;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class HarvestGoddess
/*     */   extends PEToggleItem
/*     */   implements IPedestalItem {
/*     */   public HarvestGoddess(Item.Properties props) {
/*  41 */     super(props);
/*  42 */     addItemCapability(moze_intel.projecte.capability.PedestalItemCapabilityWrapper::new);
/*     */   }
/*     */   
/*     */   public void m_6883_(@NotNull ItemStack stack, Level level, @NotNull Entity entity, int slot, boolean held) {
/*     */     Player player;
/*  47 */     if (!level.f_46443_ && slot < Inventory.m_36059_() && entity instanceof Player) { player = (Player)entity; }
/*     */     else
/*     */     { return; }
/*  50 */      super.m_6883_(stack, level, entity, slot, held);
/*  51 */     CompoundTag nbt = stack.m_41784_();
/*  52 */     if (nbt.m_128471_("Active")) {
/*  53 */       long storedEmc = getEmc(stack);
/*  54 */       if (storedEmc == 0L && !consumeFuel(player, stack, 64L, true)) {
/*  55 */         nbt.m_128379_("Active", false);
/*     */       } else {
/*  57 */         WorldHelper.growNearbyRandomly(true, level, player.m_20183_(), player);
/*  58 */         removeEmc(stack, EMCHelper.removeFractionalEMC(stack, 0.3199999928474426D));
/*     */       } 
/*     */     } else {
/*  61 */       WorldHelper.growNearbyRandomly(false, level, player.m_20183_(), player);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResult m_6225_(UseOnContext ctx) {
/*  68 */     Level level = ctx.m_43725_();
/*  69 */     Player player = ctx.m_43723_();
/*  70 */     BlockPos pos = ctx.m_8083_();
/*  71 */     Direction side = ctx.m_43719_();
/*  72 */     if (level.f_46443_ || player == null || !player.m_36204_(pos, side, ctx.m_43722_())) {
/*  73 */       return InteractionResult.FAIL;
/*     */     }
/*  75 */     if (player.m_36341_()) {
/*  76 */       for (int i = 0; i < (player.m_150109_()).f_35974_.size(); i++) {
/*  77 */         ItemStack stack = (ItemStack)(player.m_150109_()).f_35974_.get(i);
/*  78 */         if (!stack.m_41619_() && stack.m_41613_() >= 4 && stack.m_41720_() == Items.f_42499_) {
/*  79 */           if (useBoneMeal(level, pos, side)) {
/*  80 */             player.m_150109_().m_7407_(i, 4);
/*  81 */             player.f_36095_.m_38946_();
/*  82 */             return InteractionResult.CONSUME;
/*     */           } 
/*     */           break;
/*     */         } 
/*     */       } 
/*  87 */     } else if (plantSeeds(level, player, pos)) {
/*  88 */       return InteractionResult.CONSUME;
/*     */     } 
/*  90 */     return InteractionResult.FAIL;
/*     */   }
/*     */   
/*     */   private boolean useBoneMeal(Level level, BlockPos pos, Direction side) {
/*  94 */     if (level instanceof ServerLevel) { ServerLevel serverLevel = (ServerLevel)level;
/*  95 */       boolean result = false;
/*  96 */       for (BlockPos currentPos : BlockPos.m_121940_(pos.m_7918_(-15, 0, -15), pos.m_7918_(15, 0, 15))) {
/*  97 */         currentPos = currentPos.m_7949_();
/*  98 */         BlockState state = serverLevel.m_8055_(currentPos);
/*  99 */         Block block = state.m_60734_(); if (block instanceof BonemealableBlock) { BonemealableBlock growable = (BonemealableBlock)block; if (growable.m_7370_((LevelReader)serverLevel, currentPos, state, false) && growable
/* 100 */             .m_214167_((Level)serverLevel, serverLevel.f_46441_, currentPos, state))
/* 101 */           { growable.m_214148_(serverLevel, serverLevel.f_46441_, currentPos, state);
/* 102 */             level.m_46796_(1505, currentPos, 0);
/* 103 */             result = true; continue; }  }
/* 104 */          if (WorldHelper.growWaterPlant(serverLevel, currentPos, state, side)) {
/* 105 */           level.m_46796_(1505, currentPos, 0);
/* 106 */           result = true;
/*     */         } 
/*     */       } 
/* 109 */       return result; }
/*     */     
/* 111 */     return false;
/*     */   }
/*     */   
/*     */   private boolean plantSeeds(Level level, Player player, BlockPos pos) {
/* 115 */     List<StackWithSlot> seeds = getAllSeeds((player.m_150109_()).f_35974_);
/* 116 */     if (seeds.isEmpty()) {
/* 117 */       return false;
/*     */     }
/* 119 */     boolean result = false;
/* 120 */     for (BlockPos currentPos : BlockPos.m_121940_(pos.m_7918_(-8, 0, -8), pos.m_7918_(8, 0, 8))) {
/* 121 */       if (level.m_46859_(currentPos)) {
/*     */         continue;
/*     */       }
/* 124 */       BlockState state = level.m_8055_(currentPos);
/*     */       
/* 126 */       currentPos = currentPos.m_7949_();
/* 127 */       for (int i = 0; i < seeds.size(); i++) {
/* 128 */         StackWithSlot s = seeds.get(i);
/* 129 */         if (state.canSustainPlant((BlockGetter)level, currentPos, Direction.UP, s.plantable) && level.m_46859_(currentPos.m_7494_())) {
/* 130 */           level.m_46597_(currentPos.m_7494_(), s.plantable.getPlant((BlockGetter)level, currentPos.m_7494_()));
/* 131 */           player.m_150109_().m_7407_(s.slot, 1);
/* 132 */           player.f_36095_.m_38946_();
/* 133 */           s.count--;
/* 134 */           if (s.count == 0) {
/* 135 */             seeds.remove(i);
/* 136 */             if (seeds.isEmpty())
/*     */             {
/* 138 */               return true;
/*     */             }
/*     */           } 
/* 141 */           if (!result) {
/* 142 */             result = true;
/*     */           }
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 149 */     return result;
/*     */   }
/*     */   
/*     */   private List<StackWithSlot> getAllSeeds(NonNullList<ItemStack> inv) {
/* 153 */     List<StackWithSlot> result = new ArrayList<>();
/* 154 */     for (int i = 0; i < inv.size(); i++) {
/* 155 */       ItemStack stack = (ItemStack)inv.get(i);
/* 156 */       if (!stack.m_41619_()) {
/* 157 */         Item item = stack.m_41720_();
/* 158 */         if (item instanceof IPlantable) {
/* 159 */           result.add(new StackWithSlot(stack, i, (IPlantable)item));
/*     */         } else {
/* 161 */           Block block = Block.m_49814_(item);
/* 162 */           if (block instanceof IPlantable) {
/* 163 */             result.add(new StackWithSlot(stack, i, (IPlantable)block));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 168 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <PEDESTAL extends net.minecraft.world.level.block.entity.BlockEntity & IDMPedestal> boolean updateInPedestal(@NotNull ItemStack stack, @NotNull Level level, @NotNull BlockPos pos, @NotNull PEDESTAL pedestal) {
/* 174 */     if (!level.f_46443_ && ProjectEConfig.server.cooldown.pedestal.harvest.get() != -1) {
/* 175 */       if (((IDMPedestal)pedestal).getActivityCooldown() == 0) {
/* 176 */         WorldHelper.growNearbyRandomly(true, level, pos, null);
/* 177 */         ((IDMPedestal)pedestal).setActivityCooldown(ProjectEConfig.server.cooldown.pedestal.harvest.get());
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
/* 189 */     if (ProjectEConfig.server.cooldown.pedestal.harvest.get() != -1) {
/* 190 */       list.add(PELang.PEDESTAL_HARVEST_GODDESS_1.translateColored(ChatFormatting.BLUE, new Object[0]));
/* 191 */       list.add(PELang.PEDESTAL_HARVEST_GODDESS_2.translateColored(ChatFormatting.BLUE, new Object[0]));
/* 192 */       list.add(PELang.PEDESTAL_HARVEST_GODDESS_3.translateColored(ChatFormatting.BLUE, new Object[] { MathUtils.tickToSecFormatted(ProjectEConfig.server.cooldown.pedestal.harvest.get()) }));
/*     */     } 
/* 194 */     return list;
/*     */   }
/*     */   
/*     */   private static class StackWithSlot
/*     */   {
/*     */     public final IPlantable plantable;
/*     */     public final int slot;
/*     */     public int count;
/*     */     
/*     */     public StackWithSlot(ItemStack stack, int slot, IPlantable plantable) {
/* 204 */       this.slot = slot;
/* 205 */       this.count = stack.m_41613_();
/* 206 */       this.plantable = plantable;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\rings\HarvestGoddess.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */