/*     */ package moze_intel.projecte.gameObjs.items;
/*     */ import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Stream;
/*     */ import moze_intel.projecte.api.capabilities.item.IExtraFunction;
/*     */ import moze_intel.projecte.api.capabilities.item.IProjectileShooter;
/*     */ import moze_intel.projecte.gameObjs.container.PhilosStoneContainer;
/*     */ import moze_intel.projecte.gameObjs.entity.EntityMobRandomizer;
/*     */ import moze_intel.projecte.gameObjs.registries.PESoundEvents;
/*     */ import moze_intel.projecte.utils.PEKeybind;
/*     */ import moze_intel.projecte.utils.PlayerHelper;
/*     */ import moze_intel.projecte.utils.WorldTransmutations;
/*     */ import moze_intel.projecte.utils.text.ILangEntry;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.core.particles.ParticleTypes;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.MenuProvider;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*     */ import net.minecraft.world.inventory.ContainerLevelAccess;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.TooltipFlag;
/*     */ import net.minecraft.world.item.context.UseOnContext;
/*     */ import net.minecraft.world.level.ClipContext;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.BlockHitResult;
/*     */ import net.minecraft.world.phys.HitResult;
/*     */ import net.minecraftforge.network.NetworkHooks;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class PhilosophersStone extends ItemMode implements IProjectileShooter, IExtraFunction {
/*     */   public PhilosophersStone(Item.Properties props) {
/*  51 */     super(props, 4, new ILangEntry[] { (ILangEntry)PELang.MODE_PHILOSOPHER_1, (ILangEntry)PELang.MODE_PHILOSOPHER_2, (ILangEntry)PELang.MODE_PHILOSOPHER_3 });
/*  52 */     addItemCapability(moze_intel.projecte.capability.ExtraFunctionItemCapabilityWrapper::new);
/*  53 */     addItemCapability(moze_intel.projecte.capability.ProjectileShooterItemCapabilityWrapper::new);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasCraftingRemainingItem(ItemStack stack) {
/*  58 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getCraftingRemainingItem(ItemStack stack) {
/*  63 */     return stack.m_41777_();
/*     */   }
/*     */   
/*     */   public BlockHitResult getHitBlock(Player player) {
/*  67 */     return m_41435_(player.m_9236_(), player, player.m_36341_() ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResult m_6225_(UseOnContext ctx) {
/*  73 */     Player player = ctx.m_43723_();
/*  74 */     if (player == null) {
/*  75 */       return InteractionResult.FAIL;
/*     */     }
/*  77 */     BlockPos pos = ctx.m_8083_();
/*  78 */     Direction sideHit = ctx.m_43719_();
/*  79 */     Level level = ctx.m_43725_();
/*  80 */     ItemStack stack = ctx.m_43722_();
/*     */     
/*  82 */     if (level.f_46443_) {
/*  83 */       return InteractionResult.SUCCESS;
/*     */     }
/*     */     
/*  86 */     BlockHitResult rtr = getHitBlock(player);
/*  87 */     if (rtr.m_6662_() == HitResult.Type.BLOCK && !rtr.m_82425_().equals(pos)) {
/*  88 */       pos = rtr.m_82425_();
/*  89 */       sideHit = rtr.m_82434_();
/*     */     } 
/*  91 */     Map<BlockPos, BlockState> toChange = getChanges(level, pos, player, sideHit, getMode(stack), getCharge(stack));
/*  92 */     if (!toChange.isEmpty()) {
/*  93 */       for (Map.Entry<BlockPos, BlockState> entry : toChange.entrySet()) {
/*  94 */         BlockPos currentPos = entry.getKey();
/*  95 */         PlayerHelper.checkedReplaceBlock((ServerPlayer)player, currentPos, entry.getValue());
/*  96 */         if (level.f_46441_.m_188503_(8) == 0) {
/*  97 */           ((ServerLevel)level).m_8767_((ParticleOptions)ParticleTypes.f_123755_, currentPos.m_123341_(), (currentPos.m_123342_() + 1), currentPos.m_123343_(), 2, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */         }
/*     */       } 
/* 100 */       level.m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)PESoundEvents.TRANSMUTE.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
/*     */     } 
/* 102 */     return InteractionResult.SUCCESS;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shootProjectile(@NotNull Player player, @NotNull ItemStack stack, InteractionHand hand) {
/* 107 */     Level level = player.m_9236_();
/* 108 */     level.m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)PESoundEvents.TRANSMUTE.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
/* 109 */     EntityMobRandomizer ent = new EntityMobRandomizer(player, level);
/* 110 */     ent.m_37251_((Entity)player, player.m_146909_(), player.m_146908_(), 0.0F, 1.5F, 1.0F);
/* 111 */     level.m_7967_((Entity)ent);
/* 112 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean doExtraFunction(@NotNull ItemStack stack, @NotNull Player player, InteractionHand hand) {
/* 117 */     if (!(player.m_9236_()).f_46443_) {
/* 118 */       NetworkHooks.openScreen((ServerPlayer)player, new ContainerProvider(stack));
/*     */     }
/* 120 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_7373_(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltips, @NotNull TooltipFlag flags) {
/* 125 */     super.m_7373_(stack, level, tooltips, flags);
/* 126 */     tooltips.add(PELang.TOOLTIP_PHILOSTONE.translate(new Object[] { ClientKeyHelper.getKeyName(PEKeybind.EXTRA_FUNCTION) }));
/*     */   }
/*     */   public static Map<BlockPos, BlockState> getChanges(Level level, BlockPos pos, Player player, Direction sideHit, int mode, int charge) {
/*     */     Direction playerFacing;
/* 130 */     BlockState targeted = level.m_8055_(pos);
/* 131 */     boolean isSneaking = player.m_36341_();
/* 132 */     BlockState result = WorldTransmutations.getWorldTransmutation(targeted, isSneaking);
/* 133 */     if (result == null)
/*     */     {
/* 135 */       return Collections.emptyMap();
/*     */     }
/* 137 */     Stream<BlockPos> stream = null;
/* 138 */     switch (mode) {
/*     */       case 0:
/* 140 */         stream = BlockPos.m_121990_(pos.m_7918_(-charge, -charge, -charge), pos.m_7918_(charge, charge, charge)); break;
/*     */       case 1:
/* 142 */         if (sideHit == Direction.UP || sideHit == Direction.DOWN) {
/* 143 */           stream = BlockPos.m_121990_(pos.m_7918_(-charge, 0, -charge), pos.m_7918_(charge, 0, charge)); break;
/* 144 */         }  if (sideHit == Direction.EAST || sideHit == Direction.WEST) {
/* 145 */           stream = BlockPos.m_121990_(pos.m_7918_(0, -charge, -charge), pos.m_7918_(0, charge, charge)); break;
/* 146 */         }  if (sideHit == Direction.SOUTH || sideHit == Direction.NORTH) {
/* 147 */           stream = BlockPos.m_121990_(pos.m_7918_(-charge, -charge, 0), pos.m_7918_(charge, charge, 0));
/*     */         }
/*     */         break;
/*     */       case 2:
/* 151 */         playerFacing = player.m_6350_();
/* 152 */         if (playerFacing.m_122434_() == Direction.Axis.Z) {
/* 153 */           stream = BlockPos.m_121990_(pos.m_7918_(0, 0, -charge), pos.m_7918_(0, 0, charge)); break;
/* 154 */         }  if (playerFacing.m_122434_() == Direction.Axis.X) {
/* 155 */           stream = BlockPos.m_121990_(pos.m_7918_(-charge, 0, 0), pos.m_7918_(charge, 0, 0));
/*     */         }
/*     */         break;
/*     */     } 
/* 159 */     if (stream == null) {
/* 160 */       return Collections.emptyMap();
/*     */     }
/* 162 */     Object2ObjectArrayMap<BlockState, BlockState> object2ObjectArrayMap = new Object2ObjectArrayMap();
/* 163 */     object2ObjectArrayMap.put(targeted, result);
/* 164 */     Map<BlockPos, BlockState> changes = new HashMap<>();
/* 165 */     Block targetBlock = targeted.m_60734_();
/* 166 */     stream.forEach(currentPos -> {
/*     */           BlockState state = level.m_8055_(currentPos);
/*     */           
/*     */           if (state.m_60713_(targetBlock)) {
/*     */             BlockState actualResult;
/*     */             
/*     */             if (conversions.containsKey(state)) {
/*     */               actualResult = (BlockState)conversions.get(state);
/*     */             } else {
/*     */               conversions.put(state, actualResult = WorldTransmutations.getWorldTransmutation(state, isSneaking));
/*     */             } 
/*     */             if (actualResult != null) {
/*     */               changes.put(currentPos.m_7949_(), actualResult);
/*     */             }
/*     */           } 
/*     */         });
/* 182 */     return changes;
/*     */   }
/*     */   private static final class ContainerProvider extends Record implements MenuProvider { private final ItemStack stack;
/* 185 */     private ContainerProvider(ItemStack stack) { this.stack = stack; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lmoze_intel/projecte/gameObjs/items/PhilosophersStone$ContainerProvider;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #185	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 185 */       //   0	7	0	this	Lmoze_intel/projecte/gameObjs/items/PhilosophersStone$ContainerProvider; } public ItemStack stack() { return this.stack; }
/*     */     public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/gameObjs/items/PhilosophersStone$ContainerProvider;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #185	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lmoze_intel/projecte/gameObjs/items/PhilosophersStone$ContainerProvider; } public final boolean equals(Object o) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lmoze_intel/projecte/gameObjs/items/PhilosophersStone$ContainerProvider;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #185	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lmoze_intel/projecte/gameObjs/items/PhilosophersStone$ContainerProvider;
/*     */       //   0	8	1	o	Ljava/lang/Object;
/*     */     } @NotNull
/*     */     public AbstractContainerMenu m_7208_(int windowId, @NotNull Inventory playerInventory, @NotNull Player player) {
/* 190 */       return (AbstractContainerMenu)new PhilosStoneContainer(windowId, playerInventory, ContainerLevelAccess.m_39289_(player.m_9236_(), player.m_20183_()));
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Component m_5446_() {
/* 196 */       return this.stack.m_41786_();
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\PhilosophersStone.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */