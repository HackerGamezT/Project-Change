/*     */ package moze_intel.projecte.gameObjs.block_entities;
/*     */ import java.util.Optional;
/*     */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*     */ import moze_intel.projecte.api.capabilities.block_entity.IEmcStorage;
/*     */ import moze_intel.projecte.api.capabilities.item.IItemEmcHolder;
/*     */ import moze_intel.projecte.capability.managing.BasicCapabilityResolver;
/*     */ import moze_intel.projecte.capability.managing.ICapabilityResolver;
/*     */ import moze_intel.projecte.capability.managing.SidedItemHandlerResolver;
/*     */ import moze_intel.projecte.emc.FuelMapper;
/*     */ import moze_intel.projecte.gameObjs.EnumCollectorTier;
/*     */ import moze_intel.projecte.gameObjs.container.CollectorMK1Container;
/*     */ import moze_intel.projecte.gameObjs.container.slots.SlotPredicates;
/*     */ import moze_intel.projecte.gameObjs.registration.impl.BlockEntityTypeRegistryObject;
/*     */ import moze_intel.projecte.gameObjs.registries.PEBlockEntityTypes;
/*     */ import moze_intel.projecte.gameObjs.registries.PEBlocks;
/*     */ import moze_intel.projecte.utils.EMCHelper;
/*     */ import moze_intel.projecte.utils.ItemHelper;
/*     */ import moze_intel.projecte.utils.WorldHelper;
/*     */ import moze_intel.projecte.utils.text.TextComponentUtil;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.world.MenuProvider;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraftforge.common.util.NonNullLazy;
/*     */ import net.minecraftforge.common.util.NonNullSupplier;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import net.minecraftforge.items.IItemHandlerModifiable;
/*     */ import net.minecraftforge.items.ItemHandlerHelper;
/*     */ import net.minecraftforge.items.ItemStackHandler;
/*     */ import net.minecraftforge.items.wrapper.CombinedInvWrapper;
/*     */ import net.minecraftforge.items.wrapper.RangedWrapper;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class CollectorMK1BlockEntity extends CapabilityEmcBlockEntity implements MenuProvider {
/*  45 */   private final ItemStackHandler input = new EmcBlockEntity.StackHandler(getInvSize())
/*     */     {
/*     */       protected void onContentsChanged(int slot) {
/*  48 */         super.onContentsChanged(slot);
/*  49 */         CollectorMK1BlockEntity.this.needsCompacting = true;
/*     */       }
/*     */     };
/*  52 */   private final EmcBlockEntity.StackHandler auxSlots = new EmcBlockEntity.StackHandler(3)
/*     */     {
/*     */       protected void onContentsChanged(int slot) {
/*  55 */         super.onContentsChanged(slot);
/*  56 */         if (slot == 0)
/*  57 */           CollectorMK1BlockEntity.this.needsCompacting = true; 
/*     */       }
/*     */     };
/*     */   
/*  61 */   private final CombinedInvWrapper toSort = new CombinedInvWrapper(new IItemHandlerModifiable[] { (IItemHandlerModifiable)new RangedWrapper((IItemHandlerModifiable)this.auxSlots, 0, 1), (IItemHandlerModifiable)this.input });
/*     */   
/*     */   public static final int UPGRADING_SLOT = 0;
/*     */   
/*     */   public static final int UPGRADE_SLOT = 1;
/*     */   public static final int LOCK_SLOT = 2;
/*     */   private final long emcGen;
/*     */   private boolean hasChargeableItem;
/*     */   private boolean hasFuel;
/*     */   private double unprocessedEMC;
/*     */   private boolean needsCompacting = true;
/*     */   
/*     */   public CollectorMK1BlockEntity(BlockPos pos, BlockState state) {
/*  74 */     this(PEBlockEntityTypes.COLLECTOR, pos, state, EnumCollectorTier.MK1);
/*     */   }
/*     */   
/*     */   public CollectorMK1BlockEntity(BlockEntityTypeRegistryObject<? extends CollectorMK1BlockEntity> type, BlockPos pos, BlockState state, EnumCollectorTier tier) {
/*  78 */     super((BlockEntityTypeRegistryObject)type, pos, state, tier.getStorage());
/*  79 */     this.emcGen = tier.getGenRate();
/*  80 */     this.itemHandlerResolver = (ICapabilityResolver<IItemHandler>)new CollectorItemHandlerProvider();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canAcceptEmc() {
/*  86 */     return (this.hasFuel || this.hasChargeableItem);
/*     */   }
/*     */   
/*     */   public IItemHandler getInput() {
/*  90 */     return (IItemHandler)this.input;
/*     */   }
/*     */   
/*     */   public IItemHandler getAux() {
/*  94 */     return (IItemHandler)this.auxSlots;
/*     */   }
/*     */   
/*     */   protected int getInvSize() {
/*  98 */     return 8;
/*     */   }
/*     */   
/*     */   private ItemStack getUpgraded() {
/* 102 */     return this.auxSlots.getStackInSlot(1);
/*     */   }
/*     */   
/*     */   private ItemStack getLock() {
/* 106 */     return this.auxSlots.getStackInSlot(2);
/*     */   }
/*     */   
/*     */   private ItemStack getUpgrading() {
/* 110 */     return this.auxSlots.getStackInSlot(0);
/*     */   }
/*     */   
/*     */   public void clearLocked() {
/* 114 */     this.auxSlots.setStackInSlot(2, ItemStack.f_41583_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean emcAffectsComparators() {
/* 119 */     return true;
/*     */   }
/*     */   
/*     */   public static void tickServer(Level level, BlockPos pos, BlockState state, CollectorMK1BlockEntity collector) {
/* 123 */     if (collector.needsCompacting) {
/* 124 */       ItemHelper.compactInventory((IItemHandlerModifiable)collector.toSort);
/* 125 */       collector.needsCompacting = false;
/*     */     } 
/* 127 */     collector.checkFuelOrKlein();
/* 128 */     collector.updateEmc();
/* 129 */     collector.rotateUpgraded();
/* 130 */     collector.updateComparators();
/*     */   }
/*     */   
/*     */   private void rotateUpgraded() {
/* 134 */     ItemStack upgraded = getUpgraded();
/* 135 */     if (!upgraded.m_41619_() && (
/* 136 */       getLock().m_41619_() || upgraded.m_41720_() != getLock().m_41720_() || upgraded.m_41613_() >= upgraded.m_41741_())) {
/* 137 */       this.auxSlots.setStackInSlot(1, ItemHandlerHelper.insertItemStacked((IItemHandler)this.input, upgraded.m_41777_(), false));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void checkFuelOrKlein() {
/* 143 */     ItemStack upgrading = getUpgrading();
/* 144 */     if (!upgrading.m_41619_()) {
/* 145 */       Optional<IItemEmcHolder> emcHolder = upgrading.getCapability(PECapabilities.EMC_HOLDER_ITEM_CAPABILITY).resolve();
/* 146 */       if (emcHolder.isPresent()) {
/* 147 */         if (((IItemEmcHolder)emcHolder.get()).getNeededEmc(upgrading) > 0L) {
/* 148 */           this.hasChargeableItem = true;
/* 149 */           this.hasFuel = false;
/*     */         } else {
/* 151 */           this.hasChargeableItem = false;
/*     */         } 
/*     */       } else {
/* 154 */         this.hasFuel = true;
/* 155 */         this.hasChargeableItem = false;
/*     */       } 
/*     */     } else {
/* 158 */       this.hasFuel = false;
/* 159 */       this.hasChargeableItem = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateEmc() {
/* 164 */     if (!hasMaxedEmc()) {
/* 165 */       this.unprocessedEMC += ((float)this.emcGen * getSunLevel() / 320.0F);
/* 166 */       if (this.unprocessedEMC >= 1.0D)
/*     */       {
/* 168 */         this.unprocessedEMC -= forceInsertEmc((long)this.unprocessedEMC, IEmcStorage.EmcAction.EXECUTE);
/*     */       }
/*     */       
/* 171 */       markDirty(false);
/*     */     } 
/*     */     
/* 174 */     if (getStoredEmc() > 0L) {
/* 175 */       ItemStack upgrading = getUpgrading();
/* 176 */       if (this.hasChargeableItem) {
/* 177 */         upgrading.getCapability(PECapabilities.EMC_HOLDER_ITEM_CAPABILITY).ifPresent(emcHolder -> {
/*     */               long actualInserted = emcHolder.insertEmc(upgrading, Math.min(getStoredEmc(), this.emcGen), IEmcStorage.EmcAction.EXECUTE);
/*     */               forceExtractEmc(actualInserted, IEmcStorage.EmcAction.EXECUTE);
/*     */             });
/* 181 */       } else if (this.hasFuel) {
/* 182 */         if (FuelMapper.getFuelUpgrade(upgrading).m_41619_()) {
/* 183 */           this.auxSlots.setStackInSlot(0, ItemStack.f_41583_);
/*     */         }
/*     */         
/* 186 */         ItemStack result = getLock().m_41619_() ? FuelMapper.getFuelUpgrade(upgrading) : getLock().m_41777_();
/*     */         
/* 188 */         long upgradeCost = EMCHelper.getEmcValue(result) - EMCHelper.getEmcValue(upgrading);
/*     */         
/* 190 */         if (upgradeCost >= 0L && getStoredEmc() >= upgradeCost) {
/* 191 */           ItemStack upgrade = getUpgraded();
/*     */           
/* 193 */           if (getUpgraded().m_41619_()) {
/* 194 */             forceExtractEmc(upgradeCost, IEmcStorage.EmcAction.EXECUTE);
/* 195 */             this.auxSlots.setStackInSlot(1, result);
/* 196 */             upgrading.m_41774_(1);
/* 197 */           } else if (result.m_41720_() == upgrade.m_41720_() && upgrade.m_41613_() < upgrade.m_41741_()) {
/* 198 */             forceExtractEmc(upgradeCost, IEmcStorage.EmcAction.EXECUTE);
/* 199 */             getUpgraded().m_41769_(1);
/* 200 */             upgrading.m_41774_(1);
/* 201 */             this.auxSlots.onContentsChanged(1);
/*     */           } 
/*     */         } 
/*     */       } else {
/*     */         
/* 206 */         long toSend = (getStoredEmc() < this.emcGen) ? getStoredEmc() : this.emcGen;
/* 207 */         sendToAllAcceptors(toSend);
/* 208 */         sendRelayBonus();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public long getEmcToNextGoal() {
/*     */     long targetEmc;
/* 215 */     ItemStack lock = getLock();
/* 216 */     ItemStack upgrading = getUpgrading();
/*     */     
/* 218 */     if (lock.m_41619_()) {
/* 219 */       targetEmc = EMCHelper.getEmcValue(FuelMapper.getFuelUpgrade(upgrading));
/*     */     } else {
/* 221 */       targetEmc = EMCHelper.getEmcValue(lock);
/*     */     } 
/* 223 */     return Math.max(targetEmc - EMCHelper.getEmcValue(upgrading), 0L);
/*     */   }
/*     */   
/*     */   public long getItemCharge() {
/* 227 */     ItemStack upgrading = getUpgrading();
/* 228 */     if (!upgrading.m_41619_()) {
/* 229 */       return ((Long)upgrading.getCapability(PECapabilities.EMC_HOLDER_ITEM_CAPABILITY).map(emcHolder -> Long.valueOf(emcHolder.getStoredEmc(upgrading))).orElse(Long.valueOf(-1L))).longValue();
/*     */     }
/* 231 */     return -1L;
/*     */   }
/*     */   
/*     */   public double getItemChargeProportion() {
/* 235 */     ItemStack upgrading = getUpgrading();
/* 236 */     long charge = getItemCharge();
/* 237 */     if (upgrading.m_41619_() || charge <= 0L) {
/* 238 */       return -1.0D;
/*     */     }
/* 240 */     Optional<IItemEmcHolder> emcHolder = upgrading.getCapability(PECapabilities.EMC_HOLDER_ITEM_CAPABILITY).resolve();
/* 241 */     if (emcHolder.isPresent()) {
/* 242 */       long max = ((IItemEmcHolder)emcHolder.get()).getMaximumEmc(upgrading);
/* 243 */       if (charge >= max) {
/* 244 */         return 1.0D;
/*     */       }
/* 246 */       return charge / max;
/*     */     } 
/* 248 */     return -1.0D;
/*     */   }
/*     */   
/*     */   public int getSunLevel() {
/* 252 */     if (this.f_58857_.m_6042_().f_63857_()) {
/* 253 */       return 16;
/*     */     }
/* 255 */     return this.f_58857_.m_46803_(this.f_58858_.m_7494_()) + 1;
/*     */   }
/*     */   public double getFuelProgress() {
/*     */     long reqEmc;
/* 259 */     if (getUpgrading().m_41619_() || !FuelMapper.isStackFuel(getUpgrading())) {
/* 260 */       return 0.0D;
/*     */     }
/*     */     
/* 263 */     if (!getLock().m_41619_()) {
/* 264 */       reqEmc = EMCHelper.getEmcValue(getLock()) - EMCHelper.getEmcValue(getUpgrading());
/* 265 */       if (reqEmc < 0L) {
/* 266 */         return 0.0D;
/*     */       }
/*     */     } else {
/* 269 */       if (FuelMapper.getFuelUpgrade(getUpgrading()).m_41619_()) {
/* 270 */         this.auxSlots.setStackInSlot(0, ItemStack.f_41583_);
/* 271 */         return 0.0D;
/*     */       } 
/* 273 */       reqEmc = EMCHelper.getEmcValue(FuelMapper.getFuelUpgrade(getUpgrading())) - EMCHelper.getEmcValue(getUpgrading());
/*     */     } 
/* 275 */     if (getStoredEmc() >= reqEmc) {
/* 276 */       return 1.0D;
/*     */     }
/* 278 */     return getStoredEmc() / reqEmc;
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_142466_(@NotNull CompoundTag nbt) {
/* 283 */     super.m_142466_(nbt);
/* 284 */     this.input.deserializeNBT(nbt.m_128469_("Input"));
/* 285 */     this.auxSlots.deserializeNBT(nbt.m_128469_("AuxSlots"));
/* 286 */     this.unprocessedEMC = nbt.m_128459_("UnprocessedEMC");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_183515_(@NotNull CompoundTag tag) {
/* 291 */     super.m_183515_(tag);
/* 292 */     tag.m_128365_("Input", (Tag)this.input.serializeNBT());
/* 293 */     tag.m_128365_("AuxSlots", (Tag)this.auxSlots.serializeNBT());
/* 294 */     tag.m_128347_("UnprocessedEMC", this.unprocessedEMC);
/*     */   }
/*     */   
/*     */   private void sendRelayBonus() {
/* 298 */     for (Direction dir : Direction.values()) {
/* 299 */       RelayMK1BlockEntity relay = (RelayMK1BlockEntity)WorldHelper.getBlockEntity(RelayMK1BlockEntity.class, (BlockGetter)this.f_58857_, this.f_58858_.m_121945_(dir));
/* 300 */       if (relay != null)
/*     */       {
/* 302 */         relay.addBonus();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public AbstractContainerMenu m_7208_(int windowId, @NotNull Inventory playerInventory, @NotNull Player playerIn) {
/* 310 */     return (AbstractContainerMenu)new CollectorMK1Container(windowId, playerInventory, this);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Component m_5446_() {
/* 316 */     return (Component)TextComponentUtil.build(new Object[] { PEBlocks.COLLECTOR });
/*     */   }
/*     */   
/*     */   private class CollectorItemHandlerProvider
/*     */     extends SidedItemHandlerResolver {
/*     */     private final ICapabilityResolver<IItemHandler> automationAuxSlots;
/*     */     private final ICapabilityResolver<IItemHandler> automationInput;
/*     */     private final ICapabilityResolver<IItemHandler> joined;
/*     */     
/*     */     protected CollectorItemHandlerProvider() {
/* 326 */       NonNullLazy<IItemHandler> automationInput = NonNullLazy.of(() -> new WrappedItemHandler((IItemHandlerModifiable)CollectorMK1BlockEntity.this.input, WrappedItemHandler.WriteMode.IN)
/*     */           {
/*     */             @NotNull
/*     */             public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
/* 330 */               return SlotPredicates.COLLECTOR_INV.test(stack) ? super.insertItem(slot, stack, simulate) : stack;
/*     */             }
/*     */           });
/* 333 */       NonNullLazy<IItemHandler> automationAuxSlots = NonNullLazy.of(() -> new WrappedItemHandler((IItemHandlerModifiable)CollectorMK1BlockEntity.this.auxSlots, WrappedItemHandler.WriteMode.OUT)
/*     */           {
/*     */             @NotNull
/*     */             public ItemStack extractItem(int slot, int count, boolean simulate) {
/* 337 */               if (slot == 1) {
/* 338 */                 return super.extractItem(slot, count, simulate);
/*     */               }
/* 340 */               return ItemStack.f_41583_;
/*     */             }
/*     */           });
/* 343 */       this.automationInput = BasicCapabilityResolver.getBasicItemHandlerResolver((NonNullSupplier)automationInput);
/* 344 */       this.automationAuxSlots = BasicCapabilityResolver.getBasicItemHandlerResolver((NonNullSupplier)automationAuxSlots);
/* 345 */       this.joined = BasicCapabilityResolver.getBasicItemHandlerResolver(() -> new CombinedInvWrapper(new IItemHandlerModifiable[] { (IItemHandlerModifiable)automationInput.get(), (IItemHandlerModifiable)automationAuxSlots.get() }));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected ICapabilityResolver<IItemHandler> getResolver(@Nullable Direction side) {
/* 351 */       if (side == null)
/* 352 */         return this.joined; 
/* 353 */       if (side.m_122434_().m_122478_()) {
/* 354 */         return this.automationAuxSlots;
/*     */       }
/* 356 */       return this.automationInput;
/*     */     }
/*     */ 
/*     */     
/*     */     public void invalidateAll() {
/* 361 */       this.joined.invalidateAll();
/* 362 */       this.automationInput.invalidateAll();
/* 363 */       this.automationAuxSlots.invalidateAll();
/*     */     }
/*     */   }
/*     */   
/*     */   class null extends WrappedItemHandler {
/*     */     null(IItemHandlerModifiable compose, WrappedItemHandler.WriteMode mode) {
/*     */       super(compose, mode);
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
/*     */       return SlotPredicates.COLLECTOR_INV.test(stack) ? super.insertItem(slot, stack, simulate) : stack;
/*     */     }
/*     */   }
/*     */   
/*     */   class null extends WrappedItemHandler {
/*     */     null(IItemHandlerModifiable compose, WrappedItemHandler.WriteMode mode) {
/*     */       super(compose, mode);
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public ItemStack extractItem(int slot, int count, boolean simulate) {
/*     */       if (slot == 1)
/*     */         return super.extractItem(slot, count, simulate); 
/*     */       return ItemStack.f_41583_;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\block_entities\CollectorMK1BlockEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */