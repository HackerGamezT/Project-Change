/*     */ package moze_intel.projecte.gameObjs.block_entities;
/*     */ import moze_intel.projecte.api.ItemInfo;
/*     */ import moze_intel.projecte.api.capabilities.block_entity.IEmcStorage;
/*     */ import moze_intel.projecte.api.event.PlayerAttemptCondenserSetEvent;
/*     */ import moze_intel.projecte.emc.EMCMappingHandler;
/*     */ import moze_intel.projecte.emc.nbt.NBTManager;
/*     */ import moze_intel.projecte.gameObjs.container.CondenserContainer;
/*     */ import moze_intel.projecte.gameObjs.container.slots.SlotPredicates;
/*     */ import moze_intel.projecte.gameObjs.registration.impl.BlockEntityTypeRegistryObject;
/*     */ import moze_intel.projecte.gameObjs.registries.PEBlockEntityTypes;
/*     */ import moze_intel.projecte.gameObjs.registries.PEBlocks;
/*     */ import moze_intel.projecte.utils.EMCHelper;
/*     */ import moze_intel.projecte.utils.text.TextComponentUtil;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import net.minecraftforge.items.IItemHandlerModifiable;
/*     */ import net.minecraftforge.items.ItemHandlerHelper;
/*     */ import net.minecraftforge.items.ItemStackHandler;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class CondenserBlockEntity extends EmcChestBlockEntity {
/*  33 */   protected final ItemStackHandler inputInventory = createInput();
/*  34 */   private final ItemStackHandler outputInventory = createOutput();
/*     */   
/*     */   @Nullable
/*     */   private ItemInfo lockInfo;
/*     */   private boolean isAcceptingEmc;
/*     */   public long displayEmc;
/*     */   public long requiredEmc;
/*  41 */   private int loadIndex = EMCMappingHandler.getLoadIndex() - 1;
/*     */   
/*     */   public CondenserBlockEntity(BlockPos pos, BlockState state) {
/*  44 */     this(PEBlockEntityTypes.CONDENSER, pos, state);
/*     */   }
/*     */   
/*     */   protected CondenserBlockEntity(BlockEntityTypeRegistryObject<? extends CondenserBlockEntity> type, BlockPos pos, BlockState state) {
/*  48 */     super((BlockEntityTypeRegistryObject)type, pos, state);
/*  49 */     this.itemHandlerResolver = BasicCapabilityResolver.getBasicItemHandlerResolver(this::createAutomationInventory);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canAcceptEmc() {
/*  54 */     return this.isAcceptingEmc;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canProvideEmc() {
/*  59 */     return false;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public final ItemInfo getLockInfo() {
/*  64 */     if (this.requiredEmc == 0L && (this.f_58857_ == null || !this.f_58857_.f_46443_))
/*     */     {
/*  66 */       return null;
/*     */     }
/*  68 */     return this.lockInfo;
/*     */   }
/*     */   
/*     */   public ItemStackHandler getInput() {
/*  72 */     return this.inputInventory;
/*     */   }
/*     */   
/*     */   public ItemStackHandler getOutput() {
/*  76 */     return this.outputInventory;
/*     */   }
/*     */   
/*     */   protected ItemStackHandler createInput() {
/*  80 */     return new EmcBlockEntity.StackHandler(this, 91);
/*     */   }
/*     */   
/*     */   protected ItemStackHandler createOutput() {
/*  84 */     return this.inputInventory;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   protected IItemHandler createAutomationInventory() {
/*  89 */     return (IItemHandler)new WrappedItemHandler((IItemHandlerModifiable)this.inputInventory, WrappedItemHandler.WriteMode.IN_OUT)
/*     */       {
/*     */         @NotNull
/*     */         public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
/*  93 */           return (SlotPredicates.HAS_EMC.test(stack) && !CondenserBlockEntity.this.isStackEqualToLock(stack)) ? super.insertItem(slot, stack, simulate) : stack;
/*     */         }
/*     */ 
/*     */         
/*     */         @NotNull
/*     */         public ItemStack extractItem(int slot, int max, boolean simulate) {
/*  99 */           if (!getStackInSlot(slot).m_41619_() && CondenserBlockEntity.this.isStackEqualToLock(getStackInSlot(slot))) {
/* 100 */             return super.extractItem(slot, max, simulate);
/*     */           }
/* 102 */           return ItemStack.f_41583_;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static void tickServer(Level level, BlockPos pos, BlockState state, CondenserBlockEntity condenser) {
/* 108 */     condenser.checkLockAndUpdate(false);
/* 109 */     condenser.displayEmc = condenser.getStoredEmc();
/* 110 */     if (condenser.getLockInfo() != null) {
/* 111 */       condenser.condense();
/*     */     }
/* 113 */     condenser.updateComparators();
/*     */   }
/*     */   
/*     */   private void checkLockAndUpdate(boolean force) {
/* 117 */     if (!force && this.loadIndex == EMCMappingHandler.getLoadIndex()) {
/*     */       return;
/*     */     }
/*     */     
/* 121 */     this.loadIndex = EMCMappingHandler.getLoadIndex();
/* 122 */     if (this.lockInfo != null) {
/* 123 */       long lockEmc = EMCHelper.getEmcValue(this.lockInfo);
/* 124 */       if (lockEmc > 0L) {
/* 125 */         if (this.requiredEmc != lockEmc) {
/* 126 */           this.requiredEmc = lockEmc;
/* 127 */           this.isAcceptingEmc = true;
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/* 134 */     this.displayEmc = 0L;
/* 135 */     this.requiredEmc = 0L;
/* 136 */     this.isAcceptingEmc = false;
/*     */   }
/*     */   
/*     */   protected void condense() {
/* 140 */     for (int i = 0; i < this.inputInventory.getSlots(); i++) {
/* 141 */       ItemStack stack = this.inputInventory.getStackInSlot(i);
/* 142 */       if (!stack.m_41619_() && !isStackEqualToLock(stack)) {
/* 143 */         this.inputInventory.extractItem(i, 1, false);
/* 144 */         forceInsertEmc(EMCHelper.getEmcSellValue(stack), IEmcStorage.EmcAction.EXECUTE);
/*     */         break;
/*     */       } 
/*     */     } 
/* 148 */     if (getStoredEmc() >= this.requiredEmc && hasSpace()) {
/* 149 */       forceExtractEmc(this.requiredEmc, IEmcStorage.EmcAction.EXECUTE);
/* 150 */       pushStack();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected final void pushStack() {
/* 155 */     ItemInfo lockInfo = getLockInfo();
/* 156 */     if (lockInfo != null) {
/* 157 */       ItemHandlerHelper.insertItemStacked((IItemHandler)this.outputInventory, lockInfo.createStack(), false);
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean hasSpace() {
/* 162 */     for (int i = 0; i < this.outputInventory.getSlots(); i++) {
/* 163 */       ItemStack stack = this.outputInventory.getStackInSlot(i);
/* 164 */       if (stack.m_41619_() || (isStackEqualToLock(stack) && stack.m_41613_() < stack.m_41741_())) {
/* 165 */         return true;
/*     */       }
/*     */     } 
/* 168 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isStackEqualToLock(ItemStack stack) {
/* 172 */     ItemInfo lockInfo = getLockInfo();
/* 173 */     if (lockInfo == null || stack.m_41619_()) {
/* 174 */       return false;
/*     */     }
/*     */     
/* 177 */     return lockInfo.equals(NBTManager.getPersistentInfo(ItemInfo.fromStack(stack)));
/*     */   }
/*     */   
/*     */   public void setLockInfoFromPacket(@Nullable ItemInfo lockInfo) {
/* 181 */     this.lockInfo = lockInfo;
/*     */   }
/*     */   
/*     */   public boolean attemptCondenserSet(Player player) {
/* 185 */     if (this.f_58857_ == null || this.f_58857_.f_46443_) {
/* 186 */       return false;
/*     */     }
/* 188 */     if (getLockInfo() == null) {
/* 189 */       ItemStack stack = player.f_36096_.m_142621_();
/* 190 */       if (!stack.m_41619_()) {
/* 191 */         ItemInfo sourceInfo = ItemInfo.fromStack(stack);
/* 192 */         ItemInfo reducedInfo = NBTManager.getPersistentInfo(sourceInfo);
/* 193 */         if (!MinecraftForge.EVENT_BUS.post((Event)new PlayerAttemptCondenserSetEvent(player, sourceInfo, reducedInfo))) {
/* 194 */           this.lockInfo = reducedInfo;
/* 195 */           checkLockAndUpdate(true);
/* 196 */           markDirty(false);
/* 197 */           return true;
/*     */         } 
/* 199 */         return false;
/*     */       } 
/*     */ 
/*     */       
/* 203 */       if (this.lockInfo == null) {
/* 204 */         return false;
/*     */       }
/*     */     } 
/* 207 */     this.lockInfo = null;
/* 208 */     checkLockAndUpdate(true);
/* 209 */     markDirty(false);
/* 210 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_142466_(@NotNull CompoundTag nbt) {
/* 215 */     super.m_142466_(nbt);
/* 216 */     this.inputInventory.deserializeNBT(nbt.m_128469_("Input"));
/* 217 */     this.lockInfo = ItemInfo.read(nbt.m_128469_("LockInfo"));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_183515_(@NotNull CompoundTag tag) {
/* 222 */     super.m_183515_(tag);
/* 223 */     tag.m_128365_("Input", (Tag)this.inputInventory.serializeNBT());
/* 224 */     if (this.lockInfo != null) {
/* 225 */       tag.m_128365_("LockInfo", (Tag)this.lockInfo.write(new CompoundTag()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractContainerMenu m_7208_(int windowId, @NotNull Inventory playerInventory, @NotNull Player playerIn) {
/* 231 */     return (AbstractContainerMenu)new CondenserContainer(windowId, playerInventory, this);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Component m_5446_() {
/* 237 */     return (Component)TextComponentUtil.build(new Object[] { PEBlocks.CONDENSER });
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\block_entities\CondenserBlockEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */