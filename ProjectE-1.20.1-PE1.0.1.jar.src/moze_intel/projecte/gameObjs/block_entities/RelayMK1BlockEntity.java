/*     */ package moze_intel.projecte.gameObjs.block_entities;
/*     */ import java.util.Optional;
/*     */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*     */ import moze_intel.projecte.api.capabilities.block_entity.IEmcStorage;
/*     */ import moze_intel.projecte.api.capabilities.item.IItemEmcHolder;
/*     */ import moze_intel.projecte.capability.managing.BasicCapabilityResolver;
/*     */ import moze_intel.projecte.capability.managing.ICapabilityResolver;
/*     */ import moze_intel.projecte.capability.managing.SidedItemHandlerResolver;
/*     */ import moze_intel.projecte.gameObjs.EnumRelayTier;
/*     */ import moze_intel.projecte.gameObjs.container.RelayMK1Container;
/*     */ import moze_intel.projecte.gameObjs.container.slots.SlotPredicates;
/*     */ import moze_intel.projecte.gameObjs.registration.impl.BlockEntityTypeRegistryObject;
/*     */ import moze_intel.projecte.gameObjs.registries.PEBlockEntityTypes;
/*     */ import moze_intel.projecte.utils.EMCHelper;
/*     */ import moze_intel.projecte.utils.text.PELang;
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
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraftforge.common.util.NonNullLazy;
/*     */ import net.minecraftforge.common.util.NonNullSupplier;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import net.minecraftforge.items.IItemHandlerModifiable;
/*     */ import net.minecraftforge.items.ItemStackHandler;
/*     */ import net.minecraftforge.items.wrapper.CombinedInvWrapper;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class RelayMK1BlockEntity extends CapabilityEmcBlockEntity implements MenuProvider {
/*  38 */   private final ItemStackHandler output = new EmcBlockEntity.StackHandler(this, 1); private final EmcBlockEntity.CompactableStackHandler input;
/*     */   private final long chargeRate;
/*     */   private double bonusEMC;
/*     */   
/*     */   public RelayMK1BlockEntity(BlockPos pos, BlockState state) {
/*  43 */     this(PEBlockEntityTypes.RELAY, pos, state, 7, EnumRelayTier.MK1);
/*     */   }
/*     */   
/*     */   RelayMK1BlockEntity(BlockEntityTypeRegistryObject<? extends RelayMK1BlockEntity> type, BlockPos pos, BlockState state, int sizeInv, EnumRelayTier tier) {
/*  47 */     super((BlockEntityTypeRegistryObject)type, pos, state, tier.getStorage());
/*  48 */     this.chargeRate = tier.getChargeRate();
/*  49 */     this.input = new EmcBlockEntity.CompactableStackHandler(sizeInv)
/*     */       {
/*     */         @NotNull
/*     */         public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
/*  53 */           return SlotPredicates.RELAY_INV.test(stack) ? super.insertItem(slot, stack, simulate) : stack;
/*     */         }
/*     */       };
/*  56 */     this.itemHandlerResolver = (ICapabilityResolver<IItemHandler>)new RelayItemHandlerProvider();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRelay() {
/*  61 */     return true;
/*     */   }
/*     */   
/*     */   private ItemStack getCharging() {
/*  65 */     return this.output.getStackInSlot(0);
/*     */   }
/*     */   
/*     */   private ItemStack getBurn() {
/*  69 */     return this.input.getStackInSlot(0);
/*     */   }
/*     */   
/*     */   public IItemHandler getInput() {
/*  73 */     return (IItemHandler)this.input;
/*     */   }
/*     */   
/*     */   public IItemHandler getOutput() {
/*  77 */     return (IItemHandler)this.output;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean emcAffectsComparators() {
/*  82 */     return true;
/*     */   }
/*     */   
/*     */   public static void tickServer(Level level, BlockPos pos, BlockState state, RelayMK1BlockEntity relay) {
/*  86 */     relay.sendEmc();
/*  87 */     relay.input.compact();
/*  88 */     ItemStack stack = relay.getBurn();
/*  89 */     if (!stack.m_41619_()) {
/*  90 */       Optional<IItemEmcHolder> holderCapability = stack.getCapability(PECapabilities.EMC_HOLDER_ITEM_CAPABILITY).resolve();
/*  91 */       if (holderCapability.isPresent()) {
/*  92 */         IItemEmcHolder emcHolder = holderCapability.get();
/*  93 */         long simulatedVal = relay.forceInsertEmc(emcHolder.extractEmc(stack, relay.chargeRate, IEmcStorage.EmcAction.SIMULATE), IEmcStorage.EmcAction.SIMULATE);
/*  94 */         if (simulatedVal > 0L) {
/*  95 */           relay.forceInsertEmc(emcHolder.extractEmc(stack, simulatedVal, IEmcStorage.EmcAction.EXECUTE), IEmcStorage.EmcAction.EXECUTE);
/*     */         }
/*     */       } else {
/*  98 */         long emcVal = EMCHelper.getEmcSellValue(stack);
/*  99 */         if (emcVal > 0L && emcVal <= relay.getNeededEmc()) {
/* 100 */           relay.forceInsertEmc(emcVal, IEmcStorage.EmcAction.EXECUTE);
/* 101 */           relay.getBurn().m_41774_(1);
/* 102 */           relay.input.onContentsChanged(0);
/*     */         } 
/*     */       } 
/*     */     } 
/* 106 */     ItemStack chargeable = relay.getCharging();
/* 107 */     if (!chargeable.m_41619_() && relay.getStoredEmc() > 0L) {
/* 108 */       chargeable.getCapability(PECapabilities.EMC_HOLDER_ITEM_CAPABILITY).ifPresent(emcHolder -> {
/*     */             long actualSent = emcHolder.insertEmc(chargeable, Math.min(relay.getStoredEmc(), relay.chargeRate), IEmcStorage.EmcAction.EXECUTE);
/*     */             relay.forceExtractEmc(actualSent, IEmcStorage.EmcAction.EXECUTE);
/*     */           });
/*     */     }
/* 113 */     relay.updateComparators();
/*     */   }
/*     */   
/*     */   private void sendEmc() {
/* 117 */     if (getStoredEmc() == 0L) {
/*     */       return;
/*     */     }
/* 120 */     if (getStoredEmc() <= this.chargeRate) {
/* 121 */       sendToAllAcceptors(getStoredEmc());
/*     */     } else {
/* 123 */       sendToAllAcceptors(this.chargeRate);
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getItemChargeProportion() {
/* 128 */     ItemStack charging = getCharging();
/* 129 */     if (!charging.m_41619_()) {
/* 130 */       Optional<IItemEmcHolder> holderCapability = charging.getCapability(PECapabilities.EMC_HOLDER_ITEM_CAPABILITY).resolve();
/* 131 */       if (holderCapability.isPresent()) {
/* 132 */         IItemEmcHolder emcHolder = holderCapability.get();
/* 133 */         return emcHolder.getStoredEmc(charging) / emcHolder.getMaximumEmc(charging);
/*     */       } 
/*     */     } 
/* 136 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double getInputBurnProportion() {
/* 140 */     ItemStack burn = getBurn();
/* 141 */     if (burn.m_41619_()) {
/* 142 */       return 0.0D;
/*     */     }
/* 144 */     Optional<IItemEmcHolder> holderCapability = burn.getCapability(PECapabilities.EMC_HOLDER_ITEM_CAPABILITY).resolve();
/* 145 */     if (holderCapability.isPresent()) {
/* 146 */       IItemEmcHolder emcHolder = holderCapability.get();
/* 147 */       return emcHolder.getStoredEmc(burn) / emcHolder.getMaximumEmc(burn);
/*     */     } 
/* 149 */     return burn.m_41613_() / burn.m_41741_();
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_142466_(@NotNull CompoundTag nbt) {
/* 154 */     super.m_142466_(nbt);
/* 155 */     this.input.deserializeNBT(nbt.m_128469_("Input"));
/* 156 */     this.output.deserializeNBT(nbt.m_128469_("Output"));
/* 157 */     this.bonusEMC = nbt.m_128459_("BonusEMC");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_183515_(@NotNull CompoundTag tag) {
/* 162 */     super.m_183515_(tag);
/* 163 */     tag.m_128365_("Input", (Tag)this.input.serializeNBT());
/* 164 */     tag.m_128365_("Output", (Tag)this.output.serializeNBT());
/* 165 */     tag.m_128347_("BonusEMC", this.bonusEMC);
/*     */   }
/*     */   
/*     */   protected double getBonusToAdd() {
/* 169 */     return 0.05D;
/*     */   }
/*     */   
/*     */   public void addBonus() {
/* 173 */     this.bonusEMC += getBonusToAdd();
/* 174 */     if (this.bonusEMC >= 1.0D) {
/* 175 */       long emcToInsert = (long)this.bonusEMC;
/* 176 */       forceInsertEmc(emcToInsert, IEmcStorage.EmcAction.EXECUTE);
/*     */ 
/*     */       
/* 179 */       this.bonusEMC -= emcToInsert;
/*     */     } 
/* 181 */     markDirty(false);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public AbstractContainerMenu m_7208_(int windowId, @NotNull Inventory playerInventory, @NotNull Player player) {
/* 187 */     return (AbstractContainerMenu)new RelayMK1Container(windowId, playerInventory, this);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Component m_5446_() {
/* 193 */     return (Component)PELang.GUI_RELAY_MK1.translate(new Object[0]);
/*     */   }
/*     */   
/*     */   private class RelayItemHandlerProvider
/*     */     extends SidedItemHandlerResolver {
/*     */     private final ICapabilityResolver<IItemHandler> automationOutput;
/*     */     private final ICapabilityResolver<IItemHandler> automationInput;
/*     */     private final ICapabilityResolver<IItemHandler> joined;
/*     */     
/*     */     protected RelayItemHandlerProvider() {
/* 203 */       NonNullLazy<IItemHandler> automationInput = NonNullLazy.of(() -> new WrappedItemHandler((IItemHandlerModifiable)RelayMK1BlockEntity.this.input, WrappedItemHandler.WriteMode.IN));
/* 204 */       NonNullLazy<IItemHandler> automationOutput = NonNullLazy.of(() -> new WrappedItemHandler((IItemHandlerModifiable)RelayMK1BlockEntity.this.output, WrappedItemHandler.WriteMode.IN_OUT)
/*     */           {
/*     */             @NotNull
/*     */             public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
/* 208 */               return SlotPredicates.EMC_HOLDER.test(stack) ? super.insertItem(slot, stack, simulate) : stack;
/*     */             }
/*     */ 
/*     */             
/*     */             @NotNull
/*     */             public ItemStack extractItem(int slot, int amount, boolean simulate) {
/* 214 */               ItemStack stack = getStackInSlot(slot);
/* 215 */               if (!stack.m_41619_()) {
/* 216 */                 Optional<IItemEmcHolder> holderCapability = stack.getCapability(PECapabilities.EMC_HOLDER_ITEM_CAPABILITY).resolve();
/* 217 */                 if (holderCapability.isPresent()) {
/* 218 */                   IItemEmcHolder emcHolder = holderCapability.get();
/* 219 */                   if (emcHolder.getNeededEmc(stack) == 0L) {
/* 220 */                     return super.extractItem(slot, amount, simulate);
/*     */                   }
/* 222 */                   return ItemStack.f_41583_;
/*     */                 } 
/*     */               } 
/* 225 */               return super.extractItem(slot, amount, simulate);
/*     */             }
/*     */           });
/* 228 */       this.automationInput = BasicCapabilityResolver.getBasicItemHandlerResolver((NonNullSupplier)automationInput);
/* 229 */       this.automationOutput = BasicCapabilityResolver.getBasicItemHandlerResolver((NonNullSupplier)automationOutput);
/* 230 */       this.joined = BasicCapabilityResolver.getBasicItemHandlerResolver(() -> new CombinedInvWrapper(new IItemHandlerModifiable[] { (IItemHandlerModifiable)automationInput.get(), (IItemHandlerModifiable)automationOutput.get() }));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected ICapabilityResolver<IItemHandler> getResolver(@Nullable Direction side) {
/* 236 */       if (side == null)
/* 237 */         return this.joined; 
/* 238 */       if (side.m_122434_().m_122478_()) {
/* 239 */         return this.automationOutput;
/*     */       }
/* 241 */       return this.automationInput;
/*     */     }
/*     */ 
/*     */     
/*     */     public void invalidateAll() {
/* 246 */       this.joined.invalidateAll();
/* 247 */       this.automationInput.invalidateAll();
/* 248 */       this.automationOutput.invalidateAll();
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
/*     */       return SlotPredicates.EMC_HOLDER.test(stack) ? super.insertItem(slot, stack, simulate) : stack;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public ItemStack extractItem(int slot, int amount, boolean simulate) {
/*     */       ItemStack stack = getStackInSlot(slot);
/*     */       if (!stack.m_41619_()) {
/*     */         Optional<IItemEmcHolder> holderCapability = stack.getCapability(PECapabilities.EMC_HOLDER_ITEM_CAPABILITY).resolve();
/*     */         if (holderCapability.isPresent()) {
/*     */           IItemEmcHolder emcHolder = holderCapability.get();
/*     */           if (emcHolder.getNeededEmc(stack) == 0L)
/*     */             return super.extractItem(slot, amount, simulate); 
/*     */           return ItemStack.f_41583_;
/*     */         } 
/*     */       } 
/*     */       return super.extractItem(slot, amount, simulate);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\block_entities\RelayMK1BlockEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */