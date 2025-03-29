/*     */ package moze_intel.projecte.utils;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMultimap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import com.google.common.collect.Sets;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.Stream;
/*     */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*     */ import moze_intel.projecte.api.capabilities.item.IItemCharge;
/*     */ import moze_intel.projecte.api.capabilities.item.IModeChanger;
/*     */ import moze_intel.projecte.config.ProjectEConfig;
/*     */ import moze_intel.projecte.gameObjs.EnumMatterType;
/*     */ import moze_intel.projecte.gameObjs.PETags;
/*     */ import moze_intel.projecte.gameObjs.blocks.IMatterBlock;
/*     */ import moze_intel.projecte.gameObjs.items.ItemPE;
/*     */ import moze_intel.projecte.gameObjs.registries.PEDamageTypes;
/*     */ import moze_intel.projecte.gameObjs.registries.PESoundEvents;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.core.particles.ParticleTypes;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.stats.Stats;
/*     */ import net.minecraft.tags.TagKey;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.damagesource.DamageSource;
/*     */ import net.minecraft.world.entity.AgeableMob;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EquipmentSlot;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.Mob;
/*     */ import net.minecraft.world.entity.MobSpawnType;
/*     */ import net.minecraft.world.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.world.entity.animal.Sheep;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.DyeColor;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.context.UseOnContext;
/*     */ import net.minecraft.world.item.enchantment.Enchantments;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.ClipContext;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.LevelAccessor;
/*     */ import net.minecraft.world.level.ServerLevelAccessor;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.CampfireBlock;
/*     */ import net.minecraft.world.level.block.RotatedPillarBlock;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.block.state.properties.Property;
/*     */ import net.minecraft.world.level.gameevent.GameEvent;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraft.world.phys.BlockHitResult;
/*     */ import net.minecraft.world.phys.HitResult;
/*     */ import net.minecraftforge.common.IForgeShearable;
/*     */ import net.minecraftforge.common.ToolAction;
/*     */ import net.minecraftforge.common.ToolActions;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ public class ToolHelper
/*     */ {
/*  78 */   public static final ToolAction HAMMER_DIG = ToolAction.get("hammer_dig");
/*  79 */   public static final ToolAction KATAR_DIG = ToolAction.get("katar_dig");
/*  80 */   public static final ToolAction MORNING_STAR_DIG = ToolAction.get("morning_star_dig");
/*     */   
/*  82 */   public static final Set<ToolAction> DEFAULT_PE_HAMMER_ACTIONS = of(new ToolAction[] { HAMMER_DIG });
/*  83 */   public static final Set<ToolAction> DEFAULT_PE_KATAR_ACTIONS = of(new ToolAction[] { KATAR_DIG });
/*  84 */   public static final Set<ToolAction> DEFAULT_PE_MORNING_STAR_ACTIONS = of(new ToolAction[] { MORNING_STAR_DIG }); private static final Predicate<Entity> SHEARABLE; private static final Predicate<Entity> SLAY_MOB; private static final Predicate<Entity> SLAY_ALL;
/*     */   
/*     */   static {
/*  87 */     SHEARABLE = (entity -> (!entity.m_5833_() && entity instanceof IForgeShearable));
/*  88 */     SLAY_MOB = (entity -> (!entity.m_5833_() && entity instanceof net.minecraft.world.entity.monster.Enemy));
/*  89 */     SLAY_ALL = (entity -> (!entity.m_5833_() && (entity instanceof net.minecraft.world.entity.monster.Enemy || entity instanceof LivingEntity)));
/*     */   }
/*     */   private static Set<ToolAction> of(ToolAction... actions) {
/*  92 */     return (Set<ToolAction>)Stream.<ToolAction>of(actions).collect(Collectors.toCollection(Sets::newIdentityHashSet));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SafeVarargs
/*     */   public static InteractionResult performActions(InteractionResult firstAction, Supplier<InteractionResult>... secondaryActions) {
/*     */     int i;
/* 102 */     if (firstAction.m_19077_()) {
/* 103 */       return firstAction;
/*     */     }
/* 105 */     InteractionResult result = firstAction;
/* 106 */     boolean hasFailed = (result == InteractionResult.FAIL);
/* 107 */     for (Supplier<InteractionResult> secondaryAction : secondaryActions) {
/* 108 */       result = secondaryAction.get();
/* 109 */       if (result.m_19077_())
/*     */       {
/* 111 */         return result;
/*     */       }
/* 113 */       i = hasFailed & ((result == InteractionResult.FAIL) ? 1 : 0);
/*     */     } 
/* 115 */     if (i != 0)
/*     */     {
/* 117 */       return InteractionResult.FAIL;
/*     */     }
/* 119 */     return InteractionResult.PASS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static InteractionResult clearTagAOE(Level level, Player player, InteractionHand hand, ItemStack stack, long emcCost, TagKey<Block> tag) {
/* 126 */     if (ProjectEConfig.server.items.disableAllRadiusMining.get()) {
/* 127 */       return InteractionResult.PASS;
/*     */     }
/* 129 */     int charge = getCharge(stack);
/* 130 */     if (charge == 0) {
/* 131 */       return InteractionResult.PASS;
/*     */     }
/* 133 */     int scaled1 = 5 * charge;
/* 134 */     int scaled2 = 10 * charge;
/* 135 */     BlockPos corner1 = player.m_20183_().m_7918_(-scaled1, -scaled2, -scaled1);
/* 136 */     BlockPos corner2 = player.m_20183_().m_7918_(scaled1, scaled2, scaled1);
/* 137 */     boolean hasAction = false;
/* 138 */     List<ItemStack> drops = new ArrayList<>();
/* 139 */     for (BlockPos pos : WorldHelper.getPositionsFromBox(corner1, corner2)) {
/* 140 */       BlockState state = level.m_8055_(pos);
/* 141 */       if (state.m_204336_(tag)) {
/* 142 */         if (level.f_46443_) {
/* 143 */           return InteractionResult.SUCCESS;
/*     */         }
/*     */         
/* 146 */         pos = pos.m_7949_();
/* 147 */         if (PlayerHelper.hasBreakPermission((ServerPlayer)player, pos)) {
/* 148 */           if (ItemPE.consumeFuel(player, stack, emcCost, true)) {
/* 149 */             drops.addAll(Block.m_49874_(state, (ServerLevel)level, pos, WorldHelper.getBlockEntity((BlockGetter)level, pos), (Entity)player, stack));
/* 150 */             level.m_7471_(pos, false);
/* 151 */             hasAction = true;
/* 152 */             if (level.f_46441_.m_188503_(5) == 0) {
/* 153 */               ((ServerLevel)level).m_8767_((ParticleOptions)ParticleTypes.f_123755_, pos.m_123341_(), pos.m_123342_(), pos.m_123343_(), 2, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */             }
/*     */             
/*     */             continue;
/*     */           } 
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 162 */     if (hasAction) {
/* 163 */       WorldHelper.createLootDrop(drops, level, player.m_20185_(), player.m_20186_(), player.m_20189_());
/* 164 */       return InteractionResult.SUCCESS;
/*     */     } 
/* 166 */     return InteractionResult.PASS;
/*     */   }
/*     */   
/*     */   public static InteractionResult dowseCampfire(UseOnContext context, BlockState state) {
/* 170 */     Player player = context.m_43723_();
/* 171 */     if (player == null) {
/* 172 */       return InteractionResult.PASS;
/*     */     }
/* 174 */     if (state.m_60734_() instanceof CampfireBlock && ((Boolean)state.m_61143_((Property)CampfireBlock.f_51227_)).booleanValue()) {
/* 175 */       Level level = context.m_43725_();
/* 176 */       BlockPos pos = context.m_8083_();
/* 177 */       if (!level.m_5776_()) {
/* 178 */         level.m_5898_(null, 1009, pos, 0);
/*     */       }
/* 180 */       CampfireBlock.m_152749_((Entity)player, (LevelAccessor)level, pos, state);
/* 181 */       if (!level.m_5776_()) {
/* 182 */         level.m_7731_(pos, (BlockState)state.m_61124_((Property)CampfireBlock.f_51227_, Boolean.FALSE), 11);
/*     */       }
/* 184 */       return InteractionResult.m_19078_(level.f_46443_);
/*     */     } 
/* 186 */     return InteractionResult.PASS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static InteractionResult tillAOE(UseOnContext context, BlockState clickedState, long emcCost) {
/* 193 */     return useAOE(context, clickedState, emcCost, ToolActions.HOE_TILL, SoundEvents.f_11955_, -1, new HoeToolAOEData());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static InteractionResult flattenAOE(UseOnContext context, BlockState clickedState, long emcCost) {
/* 200 */     Direction sideHit = context.m_43719_();
/* 201 */     if (sideHit == Direction.DOWN)
/*     */     {
/* 203 */       return InteractionResult.PASS;
/*     */     }
/* 205 */     return useAOE(context, clickedState, emcCost, ToolActions.SHOVEL_FLATTEN, SoundEvents.f_12406_, -1, new ShovelToolAOEData());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static InteractionResult stripLogsAOE(UseOnContext context, BlockState clickedState, long emcCost) {
/* 212 */     return useAxeAOE(context, clickedState, emcCost, ToolActions.AXE_STRIP, SoundEvents.f_11688_, -1);
/*     */   }
/*     */   
/*     */   public static InteractionResult scrapeAOE(UseOnContext context, BlockState clickedState, long emcCost) {
/* 216 */     return useAxeAOE(context, clickedState, emcCost, ToolActions.AXE_SCRAPE, SoundEvents.f_144059_, 3005);
/*     */   }
/*     */   
/*     */   public static InteractionResult waxOffAOE(UseOnContext context, BlockState clickedState, long emcCost) {
/* 220 */     return useAxeAOE(context, clickedState, emcCost, ToolActions.AXE_WAX_OFF, SoundEvents.f_144060_, 3004);
/*     */   }
/*     */   
/*     */   private static InteractionResult useAxeAOE(UseOnContext context, BlockState clickedState, long emcCost, ToolAction action, SoundEvent sound, int particle) {
/* 224 */     return useAOE(context, clickedState, emcCost, action, sound, particle, new AxeToolAOEData());
/*     */   }
/*     */ 
/*     */   
/*     */   private static InteractionResult useAOE(UseOnContext context, BlockState clickedState, long emcCost, ToolAction action, SoundEvent sound, int particle, IToolAOEData toolAOEData) {
/* 229 */     Player player = context.m_43723_();
/* 230 */     if (player == null) {
/* 231 */       return InteractionResult.PASS;
/*     */     }
/* 233 */     Level level = context.m_43725_();
/* 234 */     BlockPos pos = context.m_8083_();
/* 235 */     if (!toolAOEData.isValid(level, pos, clickedState))
/*     */     {
/* 237 */       return InteractionResult.PASS;
/*     */     }
/* 239 */     BlockState modifiedState = clickedState.getToolModifiedState(context, action, false);
/* 240 */     if (modifiedState == null)
/*     */     {
/* 242 */       return InteractionResult.PASS; } 
/* 243 */     if (level.f_46443_) {
/* 244 */       return InteractionResult.SUCCESS;
/*     */     }
/*     */ 
/*     */     
/* 248 */     level.m_7731_(pos, modifiedState, 11);
/* 249 */     level.m_5594_(null, pos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);
/* 250 */     if (particle != -1) {
/* 251 */       level.m_5898_(null, particle, pos, 0);
/*     */     }
/* 253 */     ItemStack stack = context.m_43722_();
/* 254 */     int charge = getCharge(stack);
/* 255 */     if (charge > 0) {
/* 256 */       Direction side = context.m_43719_();
/* 257 */       toolAOEData.persistData(level, pos, clickedState, side);
/* 258 */       for (BlockPos newPos : toolAOEData.getTargetPositions(pos, side, charge)) {
/* 259 */         if (pos.equals(newPos)) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 265 */         BlockState state = level.m_8055_(newPos);
/*     */ 
/*     */ 
/*     */         
/* 269 */         UseOnContext adjustedContext = new UseOnContext(level, context.m_43723_(), context.m_43724_(), context.m_43722_(), new BlockHitResult(context.m_43720_().m_82520_((newPos.m_123341_() - pos.m_123341_()), (newPos.m_123342_() - pos.m_123342_()), (newPos.m_123343_() - pos.m_123343_())), context.m_43719_(), newPos, context.m_43721_()));
/* 270 */         if (toolAOEData.isValid(level, newPos, state) && modifiedState == state.getToolModifiedState(adjustedContext, action, true)) {
/* 271 */           if (ItemPE.consumeFuel(player, stack, emcCost, true)) {
/*     */ 
/*     */             
/* 274 */             newPos = newPos.m_7949_();
/*     */             
/* 276 */             state.getToolModifiedState(adjustedContext, action, false);
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 281 */             level.m_7731_(newPos, modifiedState, 11);
/* 282 */             if (particle != -1) {
/* 283 */               level.m_5898_(null, particle, newPos, 0);
/*     */             }
/*     */             
/*     */             continue;
/*     */           } 
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 292 */     level.m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)PESoundEvents.CHARGE.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
/* 293 */     return InteractionResult.CONSUME;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void digBasedOnMode(ItemStack stack, Level level, BlockPos pos, LivingEntity living, RayTracePointer tracePointer) {
/*     */     Player player;
/* 300 */     if (!level.f_46443_ && !ProjectEConfig.server.items.disableAllRadiusMining.get() && living instanceof Player) { player = (Player)living; }
/*     */     else
/*     */     { return; }
/* 303 */      byte mode = getMode(stack);
/* 304 */     if (mode == 0) {
/*     */       return;
/*     */     }
/*     */     
/* 308 */     HitResult mop = tracePointer.rayTrace(level, player, ClipContext.Fluid.NONE);
/* 309 */     if (mop instanceof BlockHitResult) { BlockHitResult result = (BlockHitResult)mop; if (result.m_6662_() != HitResult.Type.MISS && pos.equals(result.m_82425_())) {
/*     */ 
/*     */ 
/*     */         
/* 313 */         Direction sideHit = result.m_82434_();
/* 314 */         switch (mode)
/*     */         { case 1:
/*     */           
/*     */           case 2:
/* 318 */             switch (sideHit.m_122434_()) { default: throw new IncompatibleClassChangeError();
/*     */               case X: 
/* 320 */               case Y: switch (player.m_6350_().m_122434_()) { case X:
/*     */                   
/*     */                   case Z:
/*     */                    } 
/*     */               case Z:
/*     */                 break; } 
/*     */           case 3:
/*     */           
/*     */           default:
/* 329 */             break; }  AABB box = new AABB(pos, pos);
/*     */         
/* 331 */         List<ItemStack> drops = new ArrayList<>();
/* 332 */         for (BlockPos digPos : WorldHelper.getPositionsFromBox(box)) {
/* 333 */           if (level.m_46859_(digPos)) {
/*     */             continue;
/*     */           }
/* 336 */           BlockState state = level.m_8055_(digPos);
/* 337 */           if (state.m_60800_((BlockGetter)level, digPos) != -1.0F && stack.m_41735_(state)) {
/*     */             
/* 339 */             digPos = digPos.m_7949_();
/* 340 */             if (PlayerHelper.hasBreakPermission((ServerPlayer)player, digPos)) {
/* 341 */               drops.addAll(Block.m_49874_(state, (ServerLevel)level, digPos, WorldHelper.getBlockEntity((BlockGetter)level, digPos), (Entity)player, stack));
/* 342 */               level.m_7471_(digPos, false);
/*     */             } 
/*     */           } 
/*     */         } 
/* 346 */         WorldHelper.createLootDrop(drops, level, pos);
/*     */         return;
/*     */       }  }
/*     */   
/*     */   }
/*     */   
/*     */   public static InteractionResult digAOE(Level level, Player player, InteractionHand hand, ItemStack stack, BlockPos pos, Direction sideHit, boolean affectDepth, long emcCost) {
/* 353 */     if (ProjectEConfig.server.items.disableAllRadiusMining.get()) {
/* 354 */       return InteractionResult.PASS;
/*     */     }
/* 356 */     int charge = getCharge(stack);
/* 357 */     if (charge == 0) {
/* 358 */       return InteractionResult.PASS;
/*     */     }
/* 360 */     AABB box = affectDepth ? WorldHelper.getBroadDeepBox(pos, sideHit, charge) : WorldHelper.getFlatYBox(pos, charge);
/* 361 */     boolean hasAction = false;
/* 362 */     List<ItemStack> drops = new ArrayList<>();
/* 363 */     for (BlockPos newPos : WorldHelper.getPositionsFromBox(box)) {
/* 364 */       if (level.m_46859_(newPos)) {
/*     */         continue;
/*     */       }
/* 367 */       BlockState state = level.m_8055_(newPos);
/* 368 */       if (state.m_60800_((BlockGetter)level, newPos) != -1.0F && stack.m_41735_(state)) {
/* 369 */         if (level.f_46443_) {
/* 370 */           return InteractionResult.SUCCESS;
/*     */         }
/*     */         
/* 373 */         newPos = newPos.m_7949_();
/* 374 */         if (PlayerHelper.hasBreakPermission((ServerPlayer)player, newPos)) {
/* 375 */           if (ItemPE.consumeFuel(player, stack, emcCost, true)) {
/* 376 */             drops.addAll(Block.m_49874_(state, (ServerLevel)level, newPos, WorldHelper.getBlockEntity((BlockGetter)level, newPos), (Entity)player, stack));
/* 377 */             level.m_7471_(newPos, false);
/* 378 */             hasAction = true;
/*     */             
/*     */             continue;
/*     */           } 
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 386 */     if (hasAction) {
/* 387 */       WorldHelper.createLootDrop(drops, level, pos);
/* 388 */       player.m_9236_().m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)PESoundEvents.DESTRUCT.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
/* 389 */       return InteractionResult.SUCCESS;
/*     */     } 
/* 391 */     return InteractionResult.PASS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void attackWithCharge(ItemStack stack, LivingEntity damaged, LivingEntity damager, float baseDmg) {
/* 398 */     if (damager instanceof Player) { Player player = (Player)damager; if (!(damager.m_9236_()).f_46443_) {
/*     */         DamageSource dmg;
/*     */ 
/*     */         
/* 402 */         int charge = getCharge(stack);
/* 403 */         float totalDmg = baseDmg;
/* 404 */         if (charge > 0) {
/* 405 */           dmg = PEDamageTypes.BYPASS_ARMOR_PLAYER_ATTACK.source((LivingEntity)player);
/* 406 */           totalDmg += charge;
/*     */         } else {
/* 408 */           dmg = damager.m_269291_().m_269075_(player);
/*     */         } 
/* 410 */         damaged.m_6469_(dmg, totalDmg);
/*     */         return;
/*     */       }  }
/*     */   
/*     */   }
/*     */   
/*     */   public static void attackAOE(ItemStack stack, Player player, boolean slayAll, float damage, long emcCost, InteractionHand hand) {
/* 417 */     Level level = player.m_9236_();
/* 418 */     if (level.f_46443_) {
/*     */       return;
/*     */     }
/* 421 */     int charge = getCharge(stack);
/* 422 */     List<Entity> toAttack = level.m_6249_((Entity)player, player.m_20191_().m_82400_((2.5F * charge)), slayAll ? SLAY_ALL : SLAY_MOB);
/* 423 */     DamageSource src = PEDamageTypes.BYPASS_ARMOR_PLAYER_ATTACK.source((LivingEntity)player);
/* 424 */     boolean hasAction = false;
/* 425 */     for (Entity entity : toAttack) {
/* 426 */       if (ItemPE.consumeFuel(player, stack, emcCost, true)) {
/* 427 */         entity.m_6469_(src, damage);
/* 428 */         hasAction = true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 434 */     if (hasAction) {
/* 435 */       level.m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)PESoundEvents.CHARGE.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
/* 436 */       PlayerHelper.swingItem(player, hand);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static InteractionResult shearBlock(ItemStack stack, BlockPos pos, Player player) {
/* 444 */     Level level = player.m_9236_();
/* 445 */     Block block = level.m_8055_(pos).m_60734_();
/* 446 */     if (block instanceof IForgeShearable) { IForgeShearable target = (IForgeShearable)block;
/* 447 */       if (target.isShearable(stack, level, pos) && (level.f_46443_ || PlayerHelper.hasBreakPermission((ServerPlayer)player, pos))) {
/* 448 */         List<ItemStack> drops = target.onSheared(player, stack, level, pos, stack.getEnchantmentLevel(Enchantments.f_44987_));
/* 449 */         if (!drops.isEmpty()) {
/* 450 */           if (!level.f_46443_) {
/*     */             
/* 452 */             WorldHelper.createLootDrop(new ArrayList<>(drops), level, pos);
/* 453 */             player.m_6278_(Stats.f_12949_.m_12902_(block), 1);
/* 454 */             level.m_142346_((Entity)player, GameEvent.f_157781_, pos);
/*     */           } 
/*     */           
/* 457 */           return InteractionResult.SUCCESS;
/*     */         } 
/*     */       }  }
/*     */     
/* 461 */     return InteractionResult.PASS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static InteractionResult shearEntityAOE(Player player, InteractionHand hand, long emcCost) {
/* 468 */     Level level = player.m_9236_();
/* 469 */     ItemStack stack = player.m_21120_(hand);
/* 470 */     int fortune = stack.getEnchantmentLevel(Enchantments.f_44987_);
/* 471 */     int offset = (int)Math.pow(2.0D, (2 + getCharge(stack)));
/*     */     
/* 473 */     List<Entity> list = level.m_6443_(Entity.class, player.m_20191_().m_82377_(offset, offset / 2.0D, offset), SHEARABLE);
/* 474 */     boolean hasAction = false;
/* 475 */     List<ItemStack> drops = new ArrayList<>();
/* 476 */     for (Entity ent : list) {
/* 477 */       BlockPos entityPosition = ent.m_20183_();
/* 478 */       IForgeShearable target = (IForgeShearable)ent;
/* 479 */       if (target.isShearable(stack, level, entityPosition)) {
/* 480 */         if (level.f_46443_) {
/* 481 */           return InteractionResult.SUCCESS;
/*     */         }
/* 483 */         if (ItemPE.consumeFuel(player, stack, emcCost, true)) {
/* 484 */           List<ItemStack> entDrops = target.onSheared(player, stack, level, entityPosition, fortune);
/* 485 */           ent.m_146852_(GameEvent.f_157781_, (Entity)player);
/* 486 */           if (!entDrops.isEmpty()) {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 491 */             drops.addAll(entDrops);
/* 492 */             drops.addAll(entDrops);
/*     */           } 
/* 494 */           hasAction = true;
/*     */         } else {
/*     */           break;
/*     */         } 
/*     */       } 
/*     */       
/* 500 */       if (!level.f_46443_ && Math.random() < 0.01D) {
/* 501 */         Entity e = ent.m_6095_().m_20615_(level);
/* 502 */         if (e != null) {
/* 503 */           e.m_6034_(ent.m_20185_(), ent.m_20186_(), ent.m_20189_());
/* 504 */           if (e instanceof Mob) { Mob mob = (Mob)e;
/* 505 */             mob.m_6518_((ServerLevelAccessor)level, level.m_6436_(entityPosition), MobSpawnType.EVENT, null, null); }
/*     */           
/* 507 */           if (e instanceof Sheep) { Sheep sheep = (Sheep)e;
/* 508 */             sheep.m_29855_(DyeColor.m_41053_(MathUtils.randomIntInRange(0, 15))); }
/*     */           
/* 510 */           if (e instanceof AgeableMob) { AgeableMob mob = (AgeableMob)e;
/* 511 */             mob.m_146762_(-24000); }
/*     */           
/* 513 */           level.m_7967_(e);
/*     */         } 
/*     */       } 
/*     */     } 
/* 517 */     if (hasAction) {
/* 518 */       WorldHelper.createLootDrop(drops, level, player.m_20185_(), player.m_20186_(), player.m_20189_());
/* 519 */       return InteractionResult.SUCCESS;
/*     */     } 
/* 521 */     return InteractionResult.PASS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static InteractionResult tryVeinMine(Player player, ItemStack stack, BlockPos pos, Direction sideHit) {
/* 528 */     if (ProjectEConfig.server.items.disableAllRadiusMining.get()) {
/* 529 */       return InteractionResult.PASS;
/*     */     }
/* 531 */     Level level = player.m_9236_();
/* 532 */     BlockState target = level.m_8055_(pos);
/* 533 */     if (target.m_60800_((BlockGetter)level, pos) <= -1.0F || !stack.m_41735_(target)) {
/* 534 */       return InteractionResult.FAIL;
/*     */     }
/* 536 */     boolean hasAction = false;
/* 537 */     List<ItemStack> drops = new ArrayList<>();
/* 538 */     for (BlockPos newPos : WorldHelper.getPositionsFromBox(WorldHelper.getBroadDeepBox(pos, sideHit, getCharge(stack)))) {
/* 539 */       if (!level.m_46859_(newPos)) {
/* 540 */         BlockState state = level.m_8055_(newPos);
/* 541 */         if (target.m_60734_() == state.m_60734_()) {
/* 542 */           if (level.f_46443_) {
/* 543 */             return InteractionResult.SUCCESS;
/*     */           }
/*     */           
/* 546 */           if (WorldHelper.harvestVein(level, player, stack, newPos.m_7949_(), state.m_60734_(), drops, 0) > 0) {
/* 547 */             hasAction = true;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 552 */     if (hasAction) {
/* 553 */       WorldHelper.createLootDrop(drops, level, pos);
/* 554 */       level.m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)PESoundEvents.DESTRUCT.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
/* 555 */       return InteractionResult.SUCCESS;
/*     */     } 
/* 557 */     return InteractionResult.PASS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static InteractionResult mineOreVeinsInAOE(Player player, InteractionHand hand) {
/* 564 */     if (ProjectEConfig.server.items.disableAllRadiusMining.get()) {
/* 565 */       return InteractionResult.PASS;
/*     */     }
/* 567 */     Level level = player.m_9236_();
/* 568 */     ItemStack stack = player.m_21120_(hand);
/* 569 */     boolean hasAction = false;
/* 570 */     List<ItemStack> drops = new ArrayList<>();
/* 571 */     for (BlockPos pos : WorldHelper.getPositionsFromBox(player.m_20191_().m_82400_((getCharge(stack) + 3)))) {
/* 572 */       if (level.m_46859_(pos)) {
/*     */         continue;
/*     */       }
/* 575 */       BlockState state = level.m_8055_(pos);
/* 576 */       if (ItemHelper.isOre(state) && state.m_60800_((BlockGetter)level, pos) != -1.0F && stack.m_41735_(state)) {
/* 577 */         if (level.f_46443_) {
/* 578 */           return InteractionResult.SUCCESS;
/*     */         }
/*     */         
/* 581 */         if (WorldHelper.harvestVein(level, player, stack, pos.m_7949_(), state.m_60734_(), drops, 0) > 0) {
/* 582 */           hasAction = true;
/*     */         }
/*     */       } 
/*     */     } 
/* 586 */     if (hasAction) {
/* 587 */       WorldHelper.createLootDrop(drops, level, player.m_20185_(), player.m_20186_(), player.m_20189_());
/* 588 */       return InteractionResult.SUCCESS;
/*     */     } 
/* 590 */     return InteractionResult.PASS;
/*     */   }
/*     */   
/*     */   public static float getDestroySpeed(float parentDestroySpeed, EnumMatterType matterType, int charge) {
/* 594 */     if (parentDestroySpeed == 1.0F)
/*     */     {
/* 596 */       return parentDestroySpeed;
/*     */     }
/* 598 */     return parentDestroySpeed + matterType.getChargeModifier() * charge;
/*     */   }
/*     */   
/*     */   public static boolean canMatterMine(EnumMatterType matterType, Block block) {
/* 602 */     if (block instanceof IMatterBlock) { IMatterBlock matterBlock = (IMatterBlock)block; if (matterBlock.getMatterType().getMatterTier() <= matterType.getMatterTier()); }  return false;
/*     */   }
/*     */   
/*     */   private static int getCharge(ItemStack stack) {
/* 606 */     return ((Integer)stack.getCapability(PECapabilities.CHARGE_ITEM_CAPABILITY).map(itemCharge -> Integer.valueOf(itemCharge.getCharge(stack))).orElse(Integer.valueOf(0))).intValue();
/*     */   }
/*     */   
/*     */   private static byte getMode(ItemStack stack) {
/* 610 */     return ((Byte)stack.getCapability(PECapabilities.MODE_CHANGER_ITEM_CAPABILITY).map(itemMode -> Byte.valueOf(itemMode.getMode(stack))).orElse(Byte.valueOf((byte)0))).byteValue();
/*     */   }
/*     */ 
/*     */   
/*     */   private static interface IToolAOEData
/*     */   {
/*     */     boolean isValid(Level param1Level, BlockPos param1BlockPos, BlockState param1BlockState);
/*     */     
/*     */     default void persistData(Level level, BlockPos pos, BlockState state, Direction side) {}
/*     */     
/*     */     Iterable<BlockPos> getTargetPositions(BlockPos param1BlockPos, Direction param1Direction, int param1Int);
/*     */   }
/*     */   
/*     */   private static abstract class FlatToolAOEData
/*     */     implements IToolAOEData
/*     */   {
/*     */     public Iterable<BlockPos> getTargetPositions(BlockPos pos, Direction side, int radius) {
/* 627 */       return BlockPos.m_121940_(pos.m_7918_(-radius, 0, -radius), pos.m_7918_(radius, 0, radius));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class HoeToolAOEData
/*     */     extends FlatToolAOEData
/*     */   {
/*     */     public boolean isValid(Level level, BlockPos pos, BlockState state) {
/* 638 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class ShovelToolAOEData
/*     */     extends FlatToolAOEData
/*     */   {
/*     */     public boolean isValid(Level level, BlockPos pos, BlockState state) {
/* 646 */       BlockPos abovePos = pos.m_7494_();
/* 647 */       BlockState aboveState = level.m_8055_(abovePos);
/*     */       
/* 649 */       if (aboveState.m_60795_()) {
/* 650 */         return true;
/*     */       }
/*     */ 
/*     */       
/* 654 */       if (aboveState.m_204336_(PETags.Blocks.FARMING_OVERRIDE) || (aboveState.m_247087_() && aboveState.m_60734_() instanceof net.minecraftforge.common.IPlantable)) {
/* 655 */         return (aboveState.m_60819_().m_76178_() && !aboveState.m_60804_((BlockGetter)level, abovePos));
/*     */       }
/* 657 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class AxeToolAOEData
/*     */     implements IToolAOEData
/*     */   {
/*     */     @Nullable
/*     */     private Direction.Axis axis;
/*     */     private boolean isSet;
/*     */     
/*     */     public boolean isValid(Level level, BlockPos blockPos, BlockState state) {
/* 669 */       return (!this.isSet || this.axis == getAxis(state));
/*     */     }
/*     */ 
/*     */     
/*     */     public void persistData(Level level, BlockPos pos, BlockState state, Direction side) {
/* 674 */       this.axis = getAxis(state);
/* 675 */       this.isSet = true;
/*     */     }
/*     */ 
/*     */     
/*     */     public Iterable<BlockPos> getTargetPositions(BlockPos pos, Direction side, int radius) {
/* 680 */       return WorldHelper.getPositionsFromBox(WorldHelper.getBroadBox(pos, side, radius));
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     private Direction.Axis getAxis(BlockState state) {
/* 685 */       return state.m_61138_((Property)RotatedPillarBlock.f_55923_) ? (Direction.Axis)state.m_61143_((Property)RotatedPillarBlock.f_55923_) : null;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class ChargeAttributeCache
/*     */   {
/* 697 */     private static final UUID CHARGE_MODIFIER = UUID.fromString("69ADE509-46FF-3725-92AC-F59FB052BEC7");
/*     */ 
/*     */     
/* 700 */     private final Int2ObjectMap<Multimap<Attribute, AttributeModifier>> cachedMaps = (Int2ObjectMap<Multimap<Attribute, AttributeModifier>>)new Int2ObjectArrayMap();
/*     */ 
/*     */     
/*     */     public Multimap<Attribute, AttributeModifier> addChargeAttributeModifier(Multimap<Attribute, AttributeModifier> currentModifiers, @NotNull EquipmentSlot slot, ItemStack stack) {
/* 704 */       if (slot == EquipmentSlot.MAINHAND) {
/* 705 */         int charge = ToolHelper.getCharge(stack);
/* 706 */         if (charge > 0) {
/* 707 */           return (Multimap<Attribute, AttributeModifier>)this.cachedMaps.computeIfAbsent(charge, c -> {
/*     */                 ImmutableMultimap.Builder<Attribute, AttributeModifier> attributesBuilder = ImmutableMultimap.builder();
/*     */                 
/*     */                 attributesBuilder.putAll(currentModifiers);
/*     */                 attributesBuilder.put(Attributes.f_22281_, new AttributeModifier(CHARGE_MODIFIER, "Charge modifier", c, AttributeModifier.Operation.ADDITION));
/*     */                 return (Multimap)attributesBuilder.build();
/*     */               });
/*     */         }
/*     */       } 
/* 716 */       return currentModifiers;
/*     */     }
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface RayTracePointer {
/*     */     HitResult rayTrace(Level param1Level, Player param1Player, ClipContext.Fluid param1Fluid);
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\project\\utils\ToolHelper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */