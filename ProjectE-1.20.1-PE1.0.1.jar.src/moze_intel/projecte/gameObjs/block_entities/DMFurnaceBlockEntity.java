/*     */ package moze_intel.projecte.gameObjs.block_entities;
/*     */ import java.util.Optional;
/*     */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*     */ import moze_intel.projecte.api.capabilities.block_entity.IEmcStorage;
/*     */ import moze_intel.projecte.api.capabilities.item.IItemEmcHolder;
/*     */ import moze_intel.projecte.capability.managing.BasicCapabilityResolver;
/*     */ import moze_intel.projecte.capability.managing.ICapabilityResolver;
/*     */ import moze_intel.projecte.capability.managing.SidedItemHandlerResolver;
/*     */ import moze_intel.projecte.gameObjs.container.DMFurnaceContainer;
/*     */ import moze_intel.projecte.gameObjs.container.slots.SlotPredicates;
/*     */ import moze_intel.projecte.gameObjs.registration.impl.BlockEntityTypeRegistryObject;
/*     */ import moze_intel.projecte.gameObjs.registries.PEBlockEntityTypes;
/*     */ import moze_intel.projecte.utils.WorldHelper;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.world.Container;
/*     */ import net.minecraft.world.MenuProvider;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.crafting.RecipeType;
/*     */ import net.minecraft.world.item.crafting.SmeltingRecipe;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.block.state.properties.Property;
/*     */ import net.minecraftforge.common.ForgeHooks;
/*     */ import net.minecraftforge.common.Tags;
/*     */ import net.minecraftforge.common.util.NonNullLazy;
/*     */ import net.minecraftforge.common.util.NonNullSupplier;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import net.minecraftforge.items.IItemHandlerModifiable;
/*     */ import net.minecraftforge.items.ItemHandlerHelper;
/*     */ import net.minecraftforge.items.ItemStackHandler;
/*     */ import net.minecraftforge.items.wrapper.CombinedInvWrapper;
/*     */ import net.minecraftforge.items.wrapper.RecipeWrapper;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class DMFurnaceBlockEntity extends CapabilityEmcBlockEntity implements MenuProvider {
/*  48 */   private final EmcBlockEntity.CompactableStackHandler inputInventory = new EmcBlockEntity.CompactableStackHandler(this, getInvSize()); private static final long EMC_CONSUMPTION = 2L;
/*  49 */   private final EmcBlockEntity.CompactableStackHandler outputInventory = new EmcBlockEntity.CompactableStackHandler(this, getInvSize());
/*  50 */   private final EmcBlockEntity.StackHandler fuelInv = new EmcBlockEntity.StackHandler(this, 1);
/*     */   protected final int ticksBeforeSmelt;
/*     */   private final int efficiencyBonus;
/*  53 */   private final RecipeWrapper dummyFurnace = new RecipeWrapper((IItemHandlerModifiable)new ItemStackHandler());
/*     */   public int furnaceBurnTime;
/*     */   public int currentItemBurnTime;
/*     */   public int furnaceCookTime;
/*     */   
/*     */   public DMFurnaceBlockEntity(BlockPos pos, BlockState state) {
/*  59 */     this(PEBlockEntityTypes.DARK_MATTER_FURNACE, pos, state, 10, 3);
/*     */   }
/*     */   
/*     */   protected DMFurnaceBlockEntity(BlockEntityTypeRegistryObject<? extends DMFurnaceBlockEntity> type, BlockPos pos, BlockState state, int ticksBeforeSmelt, int efficiencyBonus) {
/*  63 */     super((BlockEntityTypeRegistryObject)type, pos, state, 64L);
/*  64 */     this.ticksBeforeSmelt = ticksBeforeSmelt;
/*  65 */     this.efficiencyBonus = efficiencyBonus;
/*  66 */     this.itemHandlerResolver = (ICapabilityResolver<IItemHandler>)new DMFurnaceItemHandlerProvider();
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canProvideEmc() {
/*  71 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected long getEmcInsertLimit() {
/*  77 */     return 2L;
/*     */   }
/*     */   
/*     */   protected int getInvSize() {
/*  81 */     return 9;
/*     */   }
/*     */   
/*     */   protected float getOreDoubleChance() {
/*  85 */     return 0.5F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getRawOreDoubleChance() {
/*  90 */     return getOreDoubleChance() * 2.0F / 3.0F;
/*     */   }
/*     */   
/*     */   public int getCookProgressScaled(int value) {
/*  94 */     return this.furnaceCookTime * value / this.ticksBeforeSmelt;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public AbstractContainerMenu m_7208_(int windowId, @NotNull Inventory playerInv, @NotNull Player playerIn) {
/* 100 */     return (AbstractContainerMenu)new DMFurnaceContainer(windowId, playerInv, this);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Component m_5446_() {
/* 106 */     return (Component)PELang.GUI_DARK_MATTER_FURNACE.translate(new Object[0]);
/*     */   }
/*     */   
/*     */   public IItemHandler getFuel() {
/* 110 */     return (IItemHandler)this.fuelInv;
/*     */   }
/*     */   
/*     */   private ItemStack getFuelItem() {
/* 114 */     return this.fuelInv.getStackInSlot(0);
/*     */   }
/*     */   
/*     */   public IItemHandler getInput() {
/* 118 */     return (IItemHandler)this.inputInventory;
/*     */   }
/*     */   
/*     */   public IItemHandler getOutput() {
/* 122 */     return (IItemHandler)this.outputInventory;
/*     */   }
/*     */   
/*     */   public static void tickServer(Level level, BlockPos pos, BlockState state, DMFurnaceBlockEntity furnace) {
/* 126 */     boolean wasBurning = furnace.isBurning();
/* 127 */     int lastFurnaceBurnTime = furnace.furnaceBurnTime;
/* 128 */     int lastFurnaceCookTime = furnace.furnaceCookTime;
/* 129 */     if (furnace.isBurning()) {
/* 130 */       furnace.furnaceBurnTime--;
/*     */     }
/* 132 */     furnace.inputInventory.compact();
/* 133 */     furnace.outputInventory.compact();
/* 134 */     furnace.pullFromInventories();
/* 135 */     boolean canSmelt = furnace.canSmelt();
/* 136 */     ItemStack fuelItem = furnace.getFuelItem();
/* 137 */     if (canSmelt && !fuelItem.m_41619_()) {
/* 138 */       fuelItem.getCapability(PECapabilities.EMC_HOLDER_ITEM_CAPABILITY).ifPresent(emcHolder -> {
/*     */             long simulatedExtraction = emcHolder.extractEmc(fuelItem, 2L, IEmcStorage.EmcAction.SIMULATE);
/*     */             
/*     */             if (simulatedExtraction == 2L) {
/*     */               furnace.forceInsertEmc(emcHolder.extractEmc(fuelItem, simulatedExtraction, IEmcStorage.EmcAction.EXECUTE), IEmcStorage.EmcAction.EXECUTE);
/*     */             }
/*     */             furnace.markDirty(false);
/*     */           });
/*     */     }
/* 147 */     if (furnace.getStoredEmc() >= 2L) {
/* 148 */       furnace.furnaceBurnTime = 1;
/* 149 */       furnace.forceExtractEmc(2L, IEmcStorage.EmcAction.EXECUTE);
/*     */     } 
/*     */     
/* 152 */     if (canSmelt) {
/* 153 */       if (furnace.furnaceBurnTime == 0) {
/* 154 */         furnace.currentItemBurnTime = furnace.furnaceBurnTime = furnace.getItemBurnTime(fuelItem);
/* 155 */         if (furnace.isBurning() && !fuelItem.m_41619_()) {
/* 156 */           ItemStack copy = fuelItem.m_41777_();
/* 157 */           fuelItem.m_41774_(1);
/* 158 */           furnace.fuelInv.onContentsChanged(0);
/* 159 */           if (fuelItem.m_41619_()) {
/* 160 */             furnace.fuelInv.setStackInSlot(0, copy.m_41720_().getCraftingRemainingItem(copy));
/*     */           }
/* 162 */           furnace.markDirty(false);
/*     */         } 
/*     */       } 
/* 165 */       if (furnace.isBurning() && ++furnace.furnaceCookTime == furnace.ticksBeforeSmelt) {
/* 166 */         furnace.furnaceCookTime = 0;
/* 167 */         furnace.smeltItem();
/*     */       } 
/*     */     } 
/* 170 */     if (wasBurning != furnace.isBurning()) {
/* 171 */       if (state.m_60734_() instanceof MatterFurnace)
/*     */       {
/* 173 */         level.m_46597_(pos, (BlockState)state.m_61124_((Property)MatterFurnace.f_48684_, Boolean.valueOf(furnace.isBurning())));
/*     */       }
/* 175 */       furnace.m_6596_();
/*     */     } 
/* 177 */     furnace.pushToInventories();
/* 178 */     if (lastFurnaceBurnTime != furnace.furnaceBurnTime || lastFurnaceCookTime != furnace.furnaceCookTime) {
/* 179 */       furnace.markDirty(false);
/*     */     }
/* 181 */     furnace.updateComparators();
/*     */   }
/*     */   
/*     */   public boolean isBurning() {
/* 185 */     return (this.furnaceBurnTime > 0);
/*     */   }
/*     */   
/*     */   private void pullFromInventories() {
/* 189 */     BlockEntity blockEntity = WorldHelper.getBlockEntity((BlockGetter)this.f_58857_, this.f_58858_.m_7494_());
/* 190 */     if (blockEntity == null || blockEntity instanceof net.minecraft.world.level.block.entity.HopperBlockEntity || blockEntity instanceof net.minecraft.world.level.block.entity.DropperBlockEntity) {
/*     */       return;
/*     */     }
/* 193 */     IItemHandler handler = WorldHelper.getItemHandler(blockEntity, Direction.DOWN);
/* 194 */     if (handler == null) {
/*     */       return;
/*     */     }
/* 197 */     for (int i = 0; i < handler.getSlots(); i++) {
/* 198 */       ItemStack extractTest = handler.extractItem(i, 2147483647, true);
/* 199 */       if (!extractTest.m_41619_()) {
/*     */         
/* 201 */         EmcBlockEntity.StackHandler stackHandler = (AbstractFurnaceBlockEntity.m_58399_(extractTest) || extractTest.getCapability(PECapabilities.EMC_HOLDER_ITEM_CAPABILITY).isPresent()) ? this.fuelInv : this.inputInventory;
/* 202 */         transferItem((IItemHandler)stackHandler, i, extractTest, handler);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void pushToInventories() {
/* 208 */     if (this.outputInventory.isEmpty()) {
/*     */       return;
/*     */     }
/* 211 */     BlockEntity blockEntity = WorldHelper.getBlockEntity((BlockGetter)this.f_58857_, this.f_58858_.m_7495_());
/* 212 */     if (blockEntity == null || blockEntity instanceof net.minecraft.world.level.block.entity.HopperBlockEntity) {
/*     */       return;
/*     */     }
/* 215 */     IItemHandler targetInv = WorldHelper.getItemHandler(blockEntity, Direction.UP);
/* 216 */     if (targetInv == null) {
/*     */       return;
/*     */     }
/* 219 */     for (int i = 0; i < this.outputInventory.getSlots(); i++) {
/* 220 */       ItemStack extractTest = this.outputInventory.extractItem(i, 2147483647, true);
/* 221 */       if (!extractTest.m_41619_()) {
/* 222 */         transferItem(targetInv, i, extractTest, (IItemHandler)this.outputInventory);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void transferItem(IItemHandler targetInv, int i, ItemStack extractTest, IItemHandler outputInventory) {
/* 228 */     ItemStack remainderTest = ItemHandlerHelper.insertItemStacked(targetInv, extractTest, true);
/* 229 */     int successfullyTransferred = extractTest.m_41613_() - remainderTest.m_41613_();
/* 230 */     if (successfullyTransferred > 0) {
/* 231 */       ItemStack toInsert = outputInventory.extractItem(i, successfullyTransferred, false);
/* 232 */       ItemStack result = ItemHandlerHelper.insertItemStacked(targetInv, toInsert, false);
/* 233 */       assert result.m_41619_();
/*     */     } 
/*     */   }
/*     */   
/*     */   public ItemStack getSmeltingResult(ItemStack in) {
/* 238 */     this.dummyFurnace.m_6836_(0, in);
/* 239 */     Optional<SmeltingRecipe> recipe = this.f_58857_.m_7465_().m_44015_(RecipeType.f_44108_, (Container)this.dummyFurnace, this.f_58857_);
/* 240 */     this.dummyFurnace.m_6836_(0, ItemStack.f_41583_);
/* 241 */     return recipe.<ItemStack>map(r -> r.m_8043_(this.f_58857_.m_9598_())).orElse(ItemStack.f_41583_);
/*     */   }
/*     */   
/*     */   private void smeltItem() {
/* 245 */     ItemStack toSmelt = this.inputInventory.getStackInSlot(0);
/* 246 */     ItemStack smeltResult = getSmeltingResult(toSmelt).m_41777_();
/* 247 */     if (toSmelt.m_204117_(Tags.Items.ORES)) {
/* 248 */       if (this.f_58857_ != null && this.f_58857_.f_46441_.m_188501_() < getOreDoubleChance()) {
/* 249 */         smeltResult.m_41769_(smeltResult.m_41613_());
/*     */       }
/* 251 */     } else if (toSmelt.m_204117_(Tags.Items.RAW_MATERIALS) && 
/* 252 */       this.f_58857_ != null && this.f_58857_.f_46441_.m_188501_() < getRawOreDoubleChance()) {
/* 253 */       smeltResult.m_41769_(smeltResult.m_41613_());
/*     */     } 
/*     */     
/* 256 */     ItemHandlerHelper.insertItemStacked((IItemHandler)this.outputInventory, smeltResult, false);
/* 257 */     toSmelt.m_41774_(1);
/* 258 */     this.inputInventory.onContentsChanged(0);
/*     */   }
/*     */   
/*     */   protected boolean canSmelt() {
/* 262 */     ItemStack toSmelt = this.inputInventory.getStackInSlot(0);
/* 263 */     if (toSmelt.m_41619_()) {
/* 264 */       return false;
/*     */     }
/* 266 */     ItemStack smeltResult = getSmeltingResult(toSmelt);
/* 267 */     if (smeltResult.m_41619_()) {
/* 268 */       return false;
/*     */     }
/* 270 */     ItemStack currentSmelted = this.outputInventory.getStackInSlot(this.outputInventory.getSlots() - 1);
/* 271 */     if (currentSmelted.m_41619_())
/* 272 */       return true; 
/* 273 */     if (!ItemHandlerHelper.canItemStacksStack(smeltResult, currentSmelted)) {
/* 274 */       return false;
/*     */     }
/* 276 */     int result = currentSmelted.m_41613_() + smeltResult.m_41613_();
/* 277 */     return (result <= currentSmelted.m_41741_());
/*     */   }
/*     */   
/*     */   private int getItemBurnTime(ItemStack stack) {
/* 281 */     return ForgeHooks.getBurnTime(stack, RecipeType.f_44108_) * this.ticksBeforeSmelt / 200 * this.efficiencyBonus;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBurnTimeRemainingScaled(int value) {
/* 286 */     if (this.currentItemBurnTime == 0) {
/* 287 */       this.currentItemBurnTime = this.ticksBeforeSmelt;
/*     */     }
/* 289 */     return this.furnaceBurnTime * value / this.currentItemBurnTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_142466_(@NotNull CompoundTag nbt) {
/* 294 */     super.m_142466_(nbt);
/* 295 */     this.furnaceBurnTime = nbt.m_128451_("BurnTime");
/* 296 */     this.furnaceCookTime = nbt.m_128451_("CookTime");
/* 297 */     this.inputInventory.deserializeNBT(nbt.m_128469_("Input"));
/* 298 */     this.outputInventory.deserializeNBT(nbt.m_128469_("Output"));
/* 299 */     this.fuelInv.deserializeNBT(nbt.m_128469_("Fuel"));
/* 300 */     this.currentItemBurnTime = getItemBurnTime(getFuelItem());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_183515_(@NotNull CompoundTag tag) {
/* 305 */     super.m_183515_(tag);
/* 306 */     tag.m_128405_("BurnTime", this.furnaceBurnTime);
/* 307 */     tag.m_128405_("CookTime", this.furnaceCookTime);
/* 308 */     tag.m_128365_("Input", (Tag)this.inputInventory.serializeNBT());
/* 309 */     tag.m_128365_("Output", (Tag)this.outputInventory.serializeNBT());
/* 310 */     tag.m_128365_("Fuel", (Tag)this.fuelInv.serializeNBT());
/*     */   }
/*     */   
/*     */   private class DMFurnaceItemHandlerProvider
/*     */     extends SidedItemHandlerResolver {
/*     */     private final ICapabilityResolver<IItemHandler> joined;
/*     */     private final ICapabilityResolver<IItemHandler> automationInput;
/*     */     private final ICapabilityResolver<IItemHandler> automationOutput;
/*     */     private final ICapabilityResolver<IItemHandler> automationSides;
/*     */     
/*     */     protected DMFurnaceItemHandlerProvider() {
/* 321 */       NonNullLazy<IItemHandler> automationInput = NonNullLazy.of(() -> new WrappedItemHandler((IItemHandlerModifiable)DMFurnaceBlockEntity.this.inputInventory, WrappedItemHandler.WriteMode.IN)
/*     */           {
/*     */             @NotNull
/*     */             public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
/* 325 */               return !DMFurnaceBlockEntity.this.getSmeltingResult(stack).m_41619_() ? super.insertItem(slot, stack, simulate) : stack;
/*     */             }
/*     */           });
/* 328 */       NonNullLazy<IItemHandlerModifiable> automationFuel = NonNullLazy.of(() -> new WrappedItemHandler((IItemHandlerModifiable)DMFurnaceBlockEntity.this.fuelInv, WrappedItemHandler.WriteMode.IN)
/*     */           {
/*     */             @NotNull
/*     */             public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
/* 332 */               return SlotPredicates.FURNACE_FUEL.test(stack) ? super.insertItem(slot, stack, simulate) : stack;
/*     */             }
/*     */           });
/* 335 */       NonNullLazy<IItemHandler> automationOutput = NonNullLazy.of(() -> new WrappedItemHandler((IItemHandlerModifiable)DMFurnaceBlockEntity.this.outputInventory, WrappedItemHandler.WriteMode.OUT));
/* 336 */       this.joined = BasicCapabilityResolver.getBasicItemHandlerResolver(() -> new CombinedInvWrapper(new IItemHandlerModifiable[] { (IItemHandlerModifiable)automationInput.get(), (IItemHandlerModifiable)automationFuel.get(), (IItemHandlerModifiable)automationOutput.get() }));
/*     */       
/* 338 */       this.automationInput = BasicCapabilityResolver.getBasicItemHandlerResolver((NonNullSupplier)automationInput);
/* 339 */       this.automationOutput = BasicCapabilityResolver.getBasicItemHandlerResolver((NonNullSupplier)automationOutput);
/* 340 */       this.automationSides = BasicCapabilityResolver.getBasicItemHandlerResolver(() -> new CombinedInvWrapper(new IItemHandlerModifiable[] { (IItemHandlerModifiable)automationFuel.get(), (IItemHandlerModifiable)automationOutput.get() }));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected ICapabilityResolver<IItemHandler> getResolver(@Nullable Direction side) {
/* 346 */       if (side == null)
/* 347 */         return this.joined; 
/* 348 */       if (side == Direction.UP)
/* 349 */         return this.automationInput; 
/* 350 */       if (side == Direction.DOWN) {
/* 351 */         return this.automationOutput;
/*     */       }
/* 353 */       return this.automationSides;
/*     */     }
/*     */ 
/*     */     
/*     */     public void invalidateAll() {
/* 358 */       this.joined.invalidateAll();
/* 359 */       this.automationInput.invalidateAll();
/* 360 */       this.automationOutput.invalidateAll();
/* 361 */       this.automationSides.invalidateAll();
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
/*     */       return !DMFurnaceBlockEntity.this.getSmeltingResult(stack).m_41619_() ? super.insertItem(slot, stack, simulate) : stack;
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
/*     */       return SlotPredicates.FURNACE_FUEL.test(stack) ? super.insertItem(slot, stack, simulate) : stack;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\block_entities\DMFurnaceBlockEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */