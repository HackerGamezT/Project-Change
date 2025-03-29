/*     */ package moze_intel.projecte.gameObjs.block_entities;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.stream.IntStream;
/*     */ import moze_intel.projecte.api.block_entity.BaseEmcBlockEntity;
/*     */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*     */ import moze_intel.projecte.api.capabilities.block_entity.IEmcStorage;
/*     */ import moze_intel.projecte.gameObjs.registration.impl.BlockEntityTypeRegistryObject;
/*     */ import moze_intel.projecte.utils.ItemHelper;
/*     */ import moze_intel.projecte.utils.WorldHelper;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.protocol.Packet;
/*     */ import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.entity.BlockEntityType;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraftforge.items.IItemHandlerModifiable;
/*     */ import net.minecraftforge.items.ItemStackHandler;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public abstract class EmcBlockEntity extends BaseEmcBlockEntity {
/*     */   public EmcBlockEntity(BlockEntityTypeRegistryObject<? extends EmcBlockEntity> type, BlockPos pos, BlockState state) {
/*  28 */     this(type, pos, state, Long.MAX_VALUE);
/*     */   }
/*     */ 
/*     */   
/*     */   public EmcBlockEntity(BlockEntityTypeRegistryObject<? extends EmcBlockEntity> type, BlockPos pos, BlockState state, long maxAmount) {
/*  33 */     super((BlockEntityType)type.get(), pos, state);
/*  34 */     setMaximumEMC(maxAmount);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean updateComparators;
/*     */   
/*     */   protected void updateComparators() {
/*  41 */     if (this.updateComparators) {
/*  42 */       BlockState state = m_58900_();
/*  43 */       if (!state.m_60795_()) {
/*  44 */         this.f_58857_.m_46717_(this.f_58858_, state.m_60734_());
/*     */       }
/*  46 */       this.updateComparators = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean emcAffectsComparators() {
/*  51 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void storedEmcChanged() {
/*  56 */     markDirty(emcAffectsComparators());
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_6596_() {
/*  61 */     markDirty(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void markDirty(boolean recheckComparators) {
/*  67 */     if (this.f_58857_ != null) {
/*  68 */       if (this.f_58857_.m_46805_(this.f_58858_)) {
/*  69 */         this.f_58857_.m_46745_(this.f_58858_).m_8092_(true);
/*     */       }
/*  71 */       if (recheckComparators && !this.f_58857_.f_46443_) {
/*  72 */         this.updateComparators = true;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public final CompoundTag m_5995_() {
/*  81 */     return m_187482_();
/*     */   }
/*     */ 
/*     */   
/*     */   public final ClientboundBlockEntityDataPacket getUpdatePacket() {
/*  86 */     return ClientboundBlockEntityDataPacket.m_195640_((BlockEntity)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected long sendToAllAcceptors(long emc) {
/*  98 */     if (this.f_58857_ == null || !canProvideEmc())
/*     */     {
/* 100 */       return 0L;
/*     */     }
/* 102 */     emc = Math.min(getEmcExtractLimit(), emc);
/* 103 */     long sentEmc = 0L;
/* 104 */     List<IEmcStorage> targets = new ArrayList<>();
/* 105 */     for (Direction dir : Direction.values()) {
/* 106 */       BlockPos neighboringPos = this.f_58858_.m_121945_(dir);
/*     */       
/* 108 */       if (this.f_58857_.m_46749_(neighboringPos)) {
/* 109 */         BlockEntity neighboringBE = WorldHelper.getBlockEntity((BlockGetter)this.f_58857_, neighboringPos);
/* 110 */         if (neighboringBE != null) {
/* 111 */           neighboringBE.getCapability(PECapabilities.EMC_STORAGE_CAPABILITY, dir.m_122424_()).ifPresent(theirEmcStorage -> {
/*     */                 if (!isRelay() || !theirEmcStorage.isRelay()) {
/*     */                   if (theirEmcStorage.insertEmc(1L, IEmcStorage.EmcAction.SIMULATE) > 0L) {
/*     */                     targets.add(theirEmcStorage);
/*     */                   }
/*     */                 }
/*     */               });
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 124 */     if (!targets.isEmpty()) {
/* 125 */       long emcPer = emc / targets.size();
/* 126 */       for (IEmcStorage target : targets) {
/* 127 */         long emcCanProvide = extractEmc(emcPer, IEmcStorage.EmcAction.SIMULATE);
/* 128 */         long acceptedEmc = target.insertEmc(emcCanProvide, IEmcStorage.EmcAction.EXECUTE);
/* 129 */         extractEmc(acceptedEmc, IEmcStorage.EmcAction.EXECUTE);
/* 130 */         sentEmc += acceptedEmc;
/*     */       } 
/*     */     } 
/* 133 */     return sentEmc;
/*     */   }
/*     */   
/*     */   protected class StackHandler
/*     */     extends ItemStackHandler {
/*     */     protected StackHandler(int size) {
/* 139 */       super(size);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void onContentsChanged(int slot) {
/* 144 */       super.onContentsChanged(slot);
/* 145 */       EmcBlockEntity.this.m_6596_();
/*     */     }
/*     */   }
/*     */   
/*     */   protected class CompactableStackHandler
/*     */     extends StackHandler
/*     */   {
/*     */     private boolean needsCompacting = true;
/*     */     private boolean empty;
/*     */     
/*     */     protected CompactableStackHandler(int size) {
/* 156 */       super(size);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void onContentsChanged(int slot) {
/* 161 */       super.onContentsChanged(slot);
/* 162 */       this.needsCompacting = true;
/*     */     }
/*     */     
/*     */     public void compact() {
/* 166 */       if (this.needsCompacting) {
/* 167 */         if (EmcBlockEntity.this.f_58857_ != null && !EmcBlockEntity.this.f_58857_.f_46443_) {
/* 168 */           this.empty = ItemHelper.compactInventory((IItemHandlerModifiable)this);
/*     */         }
/* 170 */         this.needsCompacting = false;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected void onLoad() {
/* 176 */       super.onLoad();
/* 177 */       this.empty = IntStream.range(0, getSlots()).allMatch(slot -> getStackInSlot(slot).m_41619_());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isEmpty() {
/* 184 */       return this.empty;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\block_entities\EmcBlockEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */