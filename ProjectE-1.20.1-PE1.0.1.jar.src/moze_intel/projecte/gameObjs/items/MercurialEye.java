/*     */ package moze_intel.projecte.gameObjs.items;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.function.Supplier;
/*     */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*     */ import moze_intel.projecte.api.capabilities.block_entity.IEmcStorage;
/*     */ import moze_intel.projecte.api.capabilities.item.IItemEmcHolder;
/*     */ import moze_intel.projecte.gameObjs.container.MercurialEyeContainer;
/*     */ import moze_intel.projecte.gameObjs.registries.PESoundEvents;
/*     */ import moze_intel.projecte.utils.EMCHelper;
/*     */ import moze_intel.projecte.utils.ItemHelper;
/*     */ import moze_intel.projecte.utils.PlayerHelper;
/*     */ import moze_intel.projecte.utils.WorldHelper;
/*     */ import moze_intel.projecte.utils.text.ILangEntry;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.core.NonNullList;
/*     */ import net.minecraft.core.Position;
/*     */ import net.minecraft.core.Vec3i;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.InteractionResultHolder;
/*     */ import net.minecraft.world.MenuProvider;
/*     */ import net.minecraft.world.SimpleMenuProvider;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.context.BlockPlaceContext;
/*     */ import net.minecraft.world.item.context.UseOnContext;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraft.world.phys.BlockHitResult;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraft.world.phys.shapes.VoxelShape;
/*     */ import net.minecraftforge.common.capabilities.ForgeCapabilities;
/*     */ import net.minecraftforge.common.util.LazyOptional;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import net.minecraftforge.items.ItemStackHandler;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class MercurialEye extends ItemMode implements IExtraFunction {
/*     */   private static final int CREATION_MODE = 0;
/*     */   private static final int EXTENSION_MODE = 1;
/*     */   private static final int EXTENSION_MODE_CLASSIC = 2;
/*     */   
/*     */   public MercurialEye(Item.Properties props) {
/*  65 */     super(props, 4, new ILangEntry[] { (ILangEntry)PELang.MODE_MERCURIAL_EYE_1, (ILangEntry)PELang.MODE_MERCURIAL_EYE_2, (ILangEntry)PELang.MODE_MERCURIAL_EYE_3, (ILangEntry)PELang.MODE_MERCURIAL_EYE_4, (ILangEntry)PELang.MODE_MERCURIAL_EYE_5, (ILangEntry)PELang.MODE_MERCURIAL_EYE_6 });
/*     */     
/*  67 */     addItemCapability(moze_intel.projecte.capability.ExtraFunctionItemCapabilityWrapper::new);
/*  68 */     addItemCapability(EyeInventoryHandler::new);
/*     */   }
/*     */   private static final int TRANSMUTATION_MODE = 3; private static final int TRANSMUTATION_MODE_CLASSIC = 4; private static final int PILLAR_MODE = 5;
/*     */   
/*     */   public boolean doExtraFunction(@NotNull ItemStack stack, @NotNull Player player, InteractionHand hand) {
/*  73 */     int selected = (player.m_150109_()).f_35977_;
/*  74 */     SimpleMenuProvider simpleMenuProvider = new SimpleMenuProvider((id, inv, pl) -> new MercurialEyeContainer(id, inv, hand, selected), stack.m_41786_());
/*  75 */     NetworkHooks.openScreen((ServerPlayer)player, (MenuProvider)simpleMenuProvider, b -> {
/*     */           b.m_130068_((Enum)hand);
/*     */           b.writeByte(selected);
/*     */         });
/*  79 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResult m_6225_(UseOnContext ctx) {
/*  85 */     ItemStack stack = ctx.m_43722_();
/*  86 */     return (ctx.m_43725_()).f_46443_ ? InteractionResult.SUCCESS : formBlocks(stack, ctx.m_43723_(), ctx.m_43724_(), ctx.m_8083_(), ctx.m_43719_());
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResultHolder<ItemStack> m_7203_(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
/*  92 */     ItemStack stack = player.m_21120_(hand);
/*  93 */     if (getMode(stack) == 0) {
/*  94 */       if (level.f_46443_) {
/*  95 */         return InteractionResultHolder.m_19090_(stack);
/*     */       }
/*  97 */       Vec3 eyeVec = new Vec3(player.m_20185_(), player.m_20186_() + player.m_20192_(), player.m_20189_());
/*  98 */       Vec3 lookVec = player.m_20154_();
/*     */       
/* 100 */       Vec3 targVec = eyeVec.m_82520_(lookVec.f_82479_ * 2.0D, lookVec.f_82480_ * 2.0D, lookVec.f_82481_ * 2.0D);
/* 101 */       return ItemHelper.actionResultFromType(formBlocks(stack, player, hand, BlockPos.m_274446_((Position)targVec), (Direction)null), stack);
/*     */     } 
/* 103 */     return InteractionResultHolder.m_19098_(stack);
/*     */   }
/*     */   
/*     */   private void playNoEMCSound(Player player) {
/* 107 */     player.m_9236_().m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)PESoundEvents.UNCHARGE.get(), SoundSource.PLAYERS, 1.0F, 1.0F); } private InteractionResult formBlocks(ItemStack eye, Player player, InteractionHand hand, BlockPos startingPos, @Nullable Direction facing) { BlockState newState;
/*     */     long newBlockEmc;
/*     */     BlockPlaceContext context;
/*     */     BlockHitResult hitResult;
/* 111 */     Optional<IItemHandler> inventoryCapability = eye.getCapability(ForgeCapabilities.ITEM_HANDLER).resolve();
/* 112 */     if (inventoryCapability.isEmpty()) {
/* 113 */       return InteractionResult.FAIL;
/*     */     }
/* 115 */     IItemHandler inventory = inventoryCapability.get();
/* 116 */     ItemStack klein = inventory.getStackInSlot(0);
/* 117 */     if (klein.m_41619_() || !klein.getCapability(PECapabilities.EMC_HOLDER_ITEM_CAPABILITY).isPresent()) {
/* 118 */       playNoEMCSound(player);
/* 119 */       return InteractionResult.FAIL;
/*     */     } 
/*     */     
/* 122 */     Level level = player.m_9236_();
/* 123 */     BlockState startingState = level.m_8055_(startingPos);
/* 124 */     long startingBlockEmc = EMCHelper.getEmcValue(new ItemStack((ItemLike)startingState.m_60734_()));
/* 125 */     ItemStack target = inventory.getStackInSlot(1);
/*     */ 
/*     */     
/* 128 */     byte mode = getMode(eye);
/*     */ 
/*     */     
/* 131 */     if (facing == null) {
/* 132 */       hitResult = new BlockHitResult(Vec3.m_82512_((Vec3i)startingPos), Direction.UP, startingPos, true);
/*     */     } else {
/* 134 */       hitResult = new BlockHitResult(Vec3.m_82512_((Vec3i)startingPos.m_121945_(facing)), facing, startingPos, false);
/*     */     } 
/* 136 */     if (!target.m_41619_()) {
/* 137 */       context = new BlockPlaceContext(level, player, hand, target.m_41777_(), hitResult);
/* 138 */       newState = ItemHelper.stackToState(target, context);
/* 139 */       newBlockEmc = EMCHelper.getEmcValue(target);
/* 140 */       if (newBlockEmc == 0L)
/*     */       {
/* 142 */         return InteractionResult.FAIL;
/*     */       }
/* 144 */     } else if (startingBlockEmc != 0L && (mode == 1 || mode == 2)) {
/*     */       
/* 146 */       newState = startingState;
/* 147 */       newBlockEmc = startingBlockEmc;
/* 148 */       context = new BlockPlaceContext(level, player, hand, new ItemStack((ItemLike)newState.m_60734_()), hitResult);
/*     */     } else {
/* 150 */       return InteractionResult.FAIL;
/*     */     } 
/* 152 */     if (newState == null || newState.m_60795_()) {
/* 153 */       return InteractionResult.FAIL;
/*     */     }
/*     */     
/* 156 */     NonNullList<ItemStack> drops = NonNullList.m_122779_();
/* 157 */     int charge = getCharge(eye);
/* 158 */     int hitTargets = 0;
/* 159 */     if (mode == 0) {
/* 160 */       if (facing != null && (!context.m_7058_() || (player.m_36341_() && !startingState.m_60795_()))) {
/* 161 */         BlockPos offsetPos = startingPos.m_121945_(facing);
/* 162 */         BlockState offsetState = level.m_8055_(offsetPos);
/* 163 */         if (!offsetState.m_60629_(context)) {
/* 164 */           return InteractionResult.FAIL;
/*     */         }
/* 166 */         long offsetBlockEmc = EMCHelper.getEmcValue(new ItemStack((ItemLike)offsetState.m_60734_()));
/*     */         
/* 168 */         if (doBlockPlace(player, offsetState, offsetPos, newState, eye, offsetBlockEmc, newBlockEmc, drops)) {
/* 169 */           hitTargets++;
/*     */         }
/* 171 */       } else if (doBlockPlace(player, startingState, startingPos, newState, eye, startingBlockEmc, newBlockEmc, drops)) {
/*     */         
/* 173 */         hitTargets++;
/*     */       } 
/* 175 */     } else if (mode == 5) {
/*     */       
/* 177 */       hitTargets += fillGaps(eye, player, level, context, startingState, newState, newBlockEmc, getCorners(startingPos, facing, 1, 3 * charge + 2), drops);
/* 178 */     } else if (mode == 2) {
/*     */       
/* 180 */       hitTargets += fillGaps(eye, player, level, context, startingState, newState, newBlockEmc, getCorners(startingPos, facing, charge, 0), drops);
/* 181 */     } else if (mode == 4) {
/*     */       
/* 183 */       Pair<BlockPos, BlockPos> corners = getCorners(startingPos, facing, charge, 0);
/* 184 */       for (BlockPos pos : WorldHelper.getPositionsFromBox(new AABB((BlockPos)corners.getLeft(), (BlockPos)corners.getRight()))) {
/* 185 */         BlockState placedState = level.m_8055_(pos);
/*     */         
/* 187 */         if (placedState == startingState && doBlockPlace(player, placedState, pos.m_7949_(), newState, eye, startingBlockEmc, newBlockEmc, drops)) {
/* 188 */           hitTargets++;
/*     */         }
/*     */       } 
/*     */     } else {
/* 192 */       if (startingState.m_60795_() || facing == null) {
/* 193 */         return InteractionResult.FAIL;
/*     */       }
/*     */       
/* 196 */       LinkedList<BlockPos> possibleBlocks = new LinkedList<>();
/* 197 */       Set<BlockPos> visited = new HashSet<>();
/* 198 */       possibleBlocks.add(startingPos);
/* 199 */       visited.add(startingPos);
/*     */       
/* 201 */       int side = 2 * charge + 1;
/* 202 */       int size = side * side;
/* 203 */       int totalTries = size * 4;
/* 204 */       for (int attemptedTargets = 0; attemptedTargets < totalTries && !possibleBlocks.isEmpty(); attemptedTargets++) {
/* 205 */         BlockPos pos = possibleBlocks.poll();
/* 206 */         BlockState checkState = level.m_8055_(pos);
/* 207 */         if (startingState == checkState) {
/*     */ 
/*     */           
/* 210 */           BlockPos offsetPos = pos.m_121945_(facing);
/* 211 */           BlockState offsetState = level.m_8055_(offsetPos);
/* 212 */           if (!offsetState.m_60783_((BlockGetter)level, offsetPos, facing)) {
/* 213 */             boolean hit = false;
/* 214 */             if (mode == 1) {
/* 215 */               VoxelShape cbBox = startingState.m_60812_((BlockGetter)level, offsetPos);
/* 216 */               if (level.m_5450_(null, cbBox)) {
/* 217 */                 long offsetBlockEmc = EMCHelper.getEmcValue((ItemLike)offsetState.m_60734_());
/* 218 */                 hit = doBlockPlace(player, offsetState, offsetPos, newState, eye, offsetBlockEmc, newBlockEmc, drops);
/*     */               } 
/* 220 */             } else if (mode == 3) {
/* 221 */               hit = doBlockPlace(player, checkState, pos, newState, eye, startingBlockEmc, newBlockEmc, drops);
/*     */             } 
/*     */             
/* 224 */             if (hit) {
/* 225 */               hitTargets++;
/* 226 */               if (hitTargets >= size) {
/*     */                 break;
/*     */               }
/* 229 */               for (Direction e : Direction.values()) {
/* 230 */                 if (facing.m_122434_() != e.m_122434_()) {
/* 231 */                   BlockPos offset = pos.m_121945_(e);
/* 232 */                   if (visited.add(offset)) {
/* 233 */                     possibleBlocks.offer(offset);
/*     */                   }
/* 235 */                   BlockPos offsetOpposite = pos.m_121945_(e.m_122424_());
/* 236 */                   if (visited.add(offsetOpposite)) {
/* 237 */                     possibleBlocks.offer(offsetOpposite);
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 246 */     if (hitTargets > 0) {
/* 247 */       level.m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)PESoundEvents.POWER.get(), SoundSource.PLAYERS, 0.8F, 2.0F / (charge / getNumCharges(eye) + 2.0F));
/* 248 */       if (!drops.isEmpty())
/*     */       {
/* 250 */         WorldHelper.createLootDrop((List)drops, player.m_9236_(), startingPos);
/*     */       }
/*     */     } 
/* 253 */     return InteractionResult.CONSUME; }
/*     */ 
/*     */   
/*     */   private boolean doBlockPlace(Player player, BlockState oldState, BlockPos placePos, BlockState newState, ItemStack eye, long oldEMC, long newEMC, NonNullList<ItemStack> drops) {
/* 257 */     Optional<IItemHandler> inventoryCapability = eye.getCapability(ForgeCapabilities.ITEM_HANDLER).resolve();
/* 258 */     if (inventoryCapability.isEmpty()) {
/* 259 */       return false;
/*     */     }
/* 261 */     IItemHandler inventory = inventoryCapability.get();
/* 262 */     ItemStack klein = inventory.getStackInSlot(0);
/* 263 */     if (klein.m_41619_()) {
/* 264 */       playNoEMCSound(player);
/* 265 */       return false;
/*     */     } 
/* 267 */     Optional<IItemEmcHolder> holderCapability = klein.getCapability(PECapabilities.EMC_HOLDER_ITEM_CAPABILITY).resolve();
/* 268 */     if (holderCapability.isEmpty()) {
/* 269 */       playNoEMCSound(player);
/* 270 */       return false;
/* 271 */     }  if (oldState == newState)
/* 272 */       return false; 
/* 273 */     if (ItemPE.getEmc(klein) < newEMC - oldEMC) {
/* 274 */       playNoEMCSound(player);
/* 275 */       return false;
/* 276 */     }  if (WorldHelper.getBlockEntity((BlockGetter)player.m_9236_(), placePos) != null) {
/* 277 */       return false;
/*     */     }
/*     */     
/* 280 */     if (oldEMC == 0L && oldState.m_60800_((BlockGetter)player.m_9236_(), placePos) == -1.0F)
/*     */     {
/* 282 */       return false;
/*     */     }
/*     */     
/* 285 */     if (PlayerHelper.checkedReplaceBlock((ServerPlayer)player, placePos, newState)) {
/* 286 */       IItemEmcHolder emcHolder = holderCapability.get();
/* 287 */       if (oldEMC == 0L) {
/*     */         
/* 289 */         drops.addAll(Block.m_49874_(oldState, ((ServerPlayer)player).m_284548_(), placePos, null, (Entity)player, eye));
/* 290 */         emcHolder.extractEmc(klein, newEMC, IEmcStorage.EmcAction.EXECUTE);
/* 291 */       } else if (oldEMC > newEMC) {
/* 292 */         emcHolder.insertEmc(klein, oldEMC - newEMC, IEmcStorage.EmcAction.EXECUTE);
/* 293 */       } else if (oldEMC < newEMC) {
/* 294 */         emcHolder.extractEmc(klein, newEMC - oldEMC, IEmcStorage.EmcAction.EXECUTE);
/*     */       } 
/* 296 */       return true;
/*     */     } 
/* 298 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private int fillGaps(ItemStack eye, Player player, Level level, BlockPlaceContext context, BlockState startingState, BlockState newState, long newBlockEmc, Pair<BlockPos, BlockPos> corners, NonNullList<ItemStack> drops) {
/* 303 */     int hitTargets = 0;
/* 304 */     for (BlockPos pos : WorldHelper.getPositionsFromBox(new AABB((BlockPos)corners.getLeft(), (BlockPos)corners.getRight()))) {
/* 305 */       VoxelShape bb = startingState.m_60812_((BlockGetter)level, pos);
/* 306 */       if (level.m_5450_(null, bb)) {
/* 307 */         BlockPlaceContext adjustedContext = BlockPlaceContext.m_43644_(context, pos, context.m_43719_());
/* 308 */         if (adjustedContext.m_7058_()) {
/* 309 */           BlockState placeState = level.m_8055_(pos);
/*     */           
/* 311 */           long placeBlockEmc = EMCHelper.getEmcValue((ItemLike)placeState.m_60734_());
/*     */           
/* 313 */           if (doBlockPlace(player, placeState, pos.m_7949_(), newState, eye, placeBlockEmc, newBlockEmc, drops)) {
/* 314 */             hitTargets++;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 319 */     return hitTargets;
/*     */   }
/*     */   
/*     */   private Pair<BlockPos, BlockPos> getCorners(BlockPos startingPos, Direction facing, int strength, int depth) {
/* 323 */     if (facing == null) {
/* 324 */       return (Pair<BlockPos, BlockPos>)new ImmutablePair(startingPos, startingPos);
/*     */     }
/* 326 */     BlockPos start = startingPos;
/* 327 */     BlockPos end = startingPos;
/* 328 */     switch (facing) {
/*     */       case UP:
/* 330 */         start = start.m_7918_(-strength, -depth, -strength);
/* 331 */         end = end.m_7918_(strength, 0, strength);
/*     */         break;
/*     */       case DOWN:
/* 334 */         start = start.m_7918_(-strength, 0, -strength);
/* 335 */         end = end.m_7918_(strength, depth, strength);
/*     */         break;
/*     */       case SOUTH:
/* 338 */         start = start.m_7918_(-strength, -strength, -depth);
/* 339 */         end = end.m_7918_(strength, strength, 0);
/*     */         break;
/*     */       case NORTH:
/* 342 */         start = start.m_7918_(-strength, -strength, 0);
/* 343 */         end = end.m_7918_(strength, strength, depth);
/*     */         break;
/*     */       case EAST:
/* 346 */         start = start.m_7918_(-depth, -strength, -strength);
/* 347 */         end = end.m_7918_(0, strength, strength);
/*     */         break;
/*     */       case WEST:
/* 350 */         start = start.m_7918_(0, -strength, -strength);
/* 351 */         end = end.m_7918_(depth, strength, strength);
/*     */         break;
/*     */     } 
/* 354 */     return (Pair<BlockPos, BlockPos>)new ImmutablePair(start, end);
/*     */   }
/*     */   
/*     */   private static class EyeInventoryHandler
/*     */     extends ItemCapability<IItemHandler> implements IItemCapabilitySerializable {
/* 359 */     private final ItemStackHandler inv = new ItemStackHandler(2);
/* 360 */     private final LazyOptional<IItemHandler> invInst = LazyOptional.of(() -> this.inv);
/*     */ 
/*     */     
/*     */     public Tag serializeNBT() {
/* 364 */       return (Tag)this.inv.serializeNBT();
/*     */     }
/*     */ 
/*     */     
/*     */     public void deserializeNBT(Tag nbt) {
/* 369 */       if (nbt instanceof CompoundTag) { CompoundTag tag = (CompoundTag)nbt;
/* 370 */         this.inv.deserializeNBT(tag); }
/*     */     
/*     */     }
/*     */ 
/*     */     
/*     */     public Capability<IItemHandler> getCapability() {
/* 376 */       return ForgeCapabilities.ITEM_HANDLER;
/*     */     }
/*     */ 
/*     */     
/*     */     public LazyOptional<IItemHandler> getLazyCapability() {
/* 381 */       return this.invInst;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getStorageKey() {
/* 386 */       return "EyeInventory";
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\MercurialEye.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */