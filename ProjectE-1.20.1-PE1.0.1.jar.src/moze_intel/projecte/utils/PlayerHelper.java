/*     */ package moze_intel.projecte.utils;
/*     */ import java.math.BigInteger;
/*     */ import java.util.Arrays;
/*     */ import java.util.function.Predicate;
/*     */ import moze_intel.projecte.integration.curios.CuriosIntegration;
/*     */ import moze_intel.projecte.network.PacketHandler;
/*     */ import moze_intel.projecte.network.packets.IPEPacket;
/*     */ import moze_intel.projecte.network.packets.to_client.CooldownResetPKT;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.network.protocol.Packet;
/*     */ import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.ClipContext;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.LevelAccessor;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.BlockHitResult;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraft.world.scores.Score;
/*     */ import net.minecraft.world.scores.criteria.ObjectiveCriteria;
/*     */ import net.minecraftforge.common.ForgeHooks;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.common.util.BlockSnapshot;
/*     */ import net.minecraftforge.event.level.BlockEvent;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.fml.ModList;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import net.minecraftforge.server.ServerLifecycleHooks;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public final class PlayerHelper {
/*  44 */   public static final ObjectiveCriteria SCOREBOARD_EMC = new ReadOnlyScoreCriteria("projecte:emc_score");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean checkedPlaceBlock(ServerPlayer player, BlockPos pos, BlockState state) {
/*  52 */     if (!hasEditPermission(player, pos)) {
/*  53 */       return false;
/*     */     }
/*  55 */     Level level = player.m_9236_();
/*  56 */     BlockSnapshot before = BlockSnapshot.create(level.m_46472_(), (LevelAccessor)level, pos);
/*  57 */     level.m_46597_(pos, state);
/*  58 */     BlockEvent.EntityPlaceEvent evt = new BlockEvent.EntityPlaceEvent(before, Blocks.f_50016_.m_49966_(), (Entity)player);
/*  59 */     MinecraftForge.EVENT_BUS.post((Event)evt);
/*  60 */     if (evt.isCanceled()) {
/*  61 */       level.restoringBlockSnapshots = true;
/*  62 */       before.restore(true, false);
/*  63 */       level.restoringBlockSnapshots = false;
/*     */       
/*  65 */       return false;
/*     */     } 
/*     */     
/*  68 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean checkedReplaceBlock(ServerPlayer player, BlockPos pos, BlockState state) {
/*  72 */     return (hasBreakPermission(player, pos) && checkedPlaceBlock(player, pos, state));
/*     */   }
/*     */   
/*     */   public static ItemStack findFirstItem(Player player, Item consumeFrom) {
/*  76 */     return (player.m_150109_()).f_35974_.stream().filter(s -> (!s.m_41619_() && s.m_41720_() == consumeFrom)).findFirst().orElse(ItemStack.f_41583_);
/*     */   }
/*     */   
/*     */   public static boolean checkArmorHotbarCurios(Player player, Predicate<ItemStack> checker) {
/*  80 */     return ((player.m_150109_()).f_35975_.stream().anyMatch(checker) || checkHotbarCurios(player, checker));
/*     */   }
/*     */   
/*     */   public static boolean checkHotbarCurios(Player player, Predicate<ItemStack> checker) {
/*  84 */     for (int i = 0; i < Inventory.m_36059_(); i++) {
/*  85 */       if (checker.test(player.m_150109_().m_8020_(i))) {
/*  86 */         return true;
/*     */       }
/*     */     } 
/*  89 */     if (checker.test(player.m_21206_())) {
/*  90 */       return true;
/*     */     }
/*  92 */     IItemHandler curios = getCurios(player);
/*  93 */     if (curios != null) {
/*  94 */       for (int j = 0; j < curios.getSlots(); j++) {
/*  95 */         if (checker.test(curios.getStackInSlot(j))) {
/*  96 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/* 100 */     return false;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static IItemHandler getCurios(Player player) {
/* 105 */     if (ModList.get().isLoaded("curios")) {
/* 106 */       return CuriosIntegration.getAll((LivingEntity)player);
/*     */     }
/* 108 */     return null;
/*     */   }
/*     */   
/*     */   public static BlockHitResult getBlockLookingAt(Player player, double maxDistance) {
/* 112 */     Pair<Vec3, Vec3> vecs = getLookVec(player, maxDistance);
/* 113 */     ClipContext ctx = new ClipContext((Vec3)vecs.getLeft(), (Vec3)vecs.getRight(), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, (Entity)player);
/* 114 */     return player.m_9236_().m_45547_(ctx);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Pair<Vec3, Vec3> getLookVec(Player player, double maxDistance) {
/* 122 */     Vec3 look = player.m_20252_(1.0F);
/* 123 */     Vec3 playerPos = new Vec3(player.m_20185_(), player.m_20186_() + player.m_20192_(), player.m_20189_());
/* 124 */     Vec3 src = playerPos.m_82520_(0.0D, player.m_20192_(), 0.0D);
/* 125 */     Vec3 dest = src.m_82520_(look.f_82479_ * maxDistance, look.f_82480_ * maxDistance, look.f_82481_ * maxDistance);
/* 126 */     return (Pair<Vec3, Vec3>)ImmutablePair.of(src, dest);
/*     */   }
/*     */   
/*     */   public static boolean hasBreakPermission(ServerPlayer player, BlockPos pos) {
/* 130 */     return (hasEditPermission(player, pos) && ForgeHooks.onBlockBreakEvent(player.m_9236_(), player.f_8941_.m_9290_(), player, pos) != -1);
/*     */   }
/*     */   
/*     */   public static boolean hasEditPermission(ServerPlayer player, BlockPos pos) {
/* 134 */     if (ServerLifecycleHooks.getCurrentServer().m_7762_((ServerLevel)player.m_9236_(), pos, (Player)player)) {
/* 135 */       return false;
/*     */     }
/* 137 */     return Arrays.<Direction>stream(Direction.values()).allMatch(e -> player.m_36204_(pos, e, ItemStack.f_41583_));
/*     */   }
/*     */   
/*     */   public static void resetCooldown(Player player) {
/* 141 */     player.m_36334_();
/* 142 */     PacketHandler.sendTo((IPEPacket)new CooldownResetPKT(), (ServerPlayer)player);
/*     */   }
/*     */   
/*     */   public static void swingItem(Player player, InteractionHand hand) {
/* 146 */     Level level = player.m_9236_(); if (level instanceof ServerLevel) { ServerLevel serverLevel = (ServerLevel)level;
/* 147 */       serverLevel.m_7726_().m_8394_((Entity)player, (Packet)new ClientboundAnimatePacket((Entity)player, (hand == InteractionHand.MAIN_HAND) ? 0 : 3)); }
/*     */   
/*     */   }
/*     */   
/*     */   public static void updateClientServerFlight(ServerPlayer player, boolean allowFlying) {
/* 152 */     updateClientServerFlight(player, allowFlying, (allowFlying && (player.m_150110_()).f_35935_));
/*     */   }
/*     */   
/*     */   public static void updateClientServerFlight(ServerPlayer player, boolean allowFlying, boolean isFlying) {
/* 156 */     (player.m_150110_()).f_35936_ = allowFlying;
/* 157 */     (player.m_150110_()).f_35935_ = isFlying;
/* 158 */     player.m_6885_();
/*     */   }
/*     */   
/*     */   public static void updateScore(ServerPlayer player, ObjectiveCriteria objective, BigInteger value) {
/* 162 */     updateScore(player, objective, (value.compareTo(Constants.MAX_INTEGER) > 0) ? Integer.MAX_VALUE : value.intValueExact());
/*     */   }
/*     */   
/*     */   public static void updateScore(ServerPlayer player, ObjectiveCriteria objective, int value) {
/* 166 */     player.m_36329_().m_83427_(objective, player.m_6302_(), score -> score.m_83402_(value));
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\project\\utils\PlayerHelper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */