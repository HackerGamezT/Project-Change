/*     */ package moze_intel.projecte.api.block_entity;
/*     */ 
/*     */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*     */ import moze_intel.projecte.api.capabilities.block_entity.IEmcStorage;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.entity.BlockEntityType;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraftforge.common.capabilities.Capability;
/*     */ import net.minecraftforge.common.util.LazyOptional;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BaseEmcBlockEntity
/*     */   extends BlockEntity
/*     */   implements IEmcStorage
/*     */ {
/*     */   private LazyOptional<IEmcStorage> emcStorageCapability;
/*     */   private long maximumEMC;
/*     */   private long currentEMC;
/*     */   
/*     */   protected BaseEmcBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
/*  31 */     super(type, pos, state);
/*  32 */     setMaximumEMC(Long.MAX_VALUE);
/*     */   }
/*     */   
/*     */   public final void setMaximumEMC(long max) {
/*  36 */     this.maximumEMC = max;
/*  37 */     if (getStoredEmc() > getMaximumEmc()) {
/*  38 */       this.currentEMC = getMaximumEmc();
/*  39 */       storedEmcChanged();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getStoredEmc() {
/*  46 */     return this.currentEMC;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getMaximumEmc() {
/*  52 */     return this.maximumEMC;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected long getEmcInsertLimit() {
/*  60 */     return getNeededEmc();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected long getEmcExtractLimit() {
/*  68 */     return getStoredEmc();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canAcceptEmc() {
/*  75 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canProvideEmc() {
/*  82 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public long extractEmc(long toExtract, IEmcStorage.EmcAction action) {
/*  87 */     if (toExtract < 0L) {
/*  88 */       return insertEmc(-toExtract, action);
/*     */     }
/*  90 */     if (canProvideEmc()) {
/*  91 */       return forceExtractEmc(Math.min(getEmcExtractLimit(), toExtract), action);
/*     */     }
/*  93 */     return 0L;
/*     */   }
/*     */ 
/*     */   
/*     */   public long insertEmc(long toAccept, IEmcStorage.EmcAction action) {
/*  98 */     if (toAccept < 0L) {
/*  99 */       return extractEmc(-toAccept, action);
/*     */     }
/* 101 */     if (canAcceptEmc()) {
/* 102 */       return forceInsertEmc(Math.min(getEmcInsertLimit(), toAccept), action);
/*     */     }
/* 104 */     return 0L;
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
/*     */ 
/*     */ 
/*     */   
/*     */   protected long forceExtractEmc(long toExtract, IEmcStorage.EmcAction action) {
/* 119 */     if (toExtract < 0L) {
/* 120 */       return forceInsertEmc(-toExtract, action);
/*     */     }
/* 122 */     long toRemove = Math.min(getStoredEmc(), toExtract);
/* 123 */     if (action.execute()) {
/* 124 */       this.currentEMC -= toRemove;
/* 125 */       storedEmcChanged();
/*     */     } 
/* 127 */     return toRemove;
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
/*     */ 
/*     */ 
/*     */   
/*     */   protected long forceInsertEmc(long toAccept, IEmcStorage.EmcAction action) {
/* 142 */     if (toAccept < 0L) {
/* 143 */       return forceExtractEmc(-toAccept, action);
/*     */     }
/* 145 */     long toAdd = Math.min(getNeededEmc(), toAccept);
/* 146 */     if (action.execute()) {
/* 147 */       this.currentEMC += toAdd;
/* 148 */       storedEmcChanged();
/*     */     } 
/* 150 */     return toAdd;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void storedEmcChanged() {
/* 157 */     m_6596_();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_183515_(@NotNull CompoundTag tag) {
/* 162 */     super.m_183515_(tag);
/* 163 */     if (getStoredEmc() > getMaximumEmc()) {
/* 164 */       this.currentEMC = getMaximumEmc();
/*     */     }
/* 166 */     tag.m_128356_("EMC", getStoredEmc());
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_142466_(@NotNull CompoundTag tag) {
/* 171 */     super.m_142466_(tag);
/* 172 */     long set = tag.m_128454_("EMC");
/* 173 */     if (set > getMaximumEmc()) {
/* 174 */       set = getMaximumEmc();
/*     */     }
/* 176 */     this.currentEMC = set;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
/* 182 */     if (cap == PECapabilities.EMC_STORAGE_CAPABILITY) {
/* 183 */       if (this.emcStorageCapability == null || !this.emcStorageCapability.isPresent())
/*     */       {
/* 185 */         this.emcStorageCapability = LazyOptional.of(() -> this);
/*     */       }
/* 187 */       return this.emcStorageCapability.cast();
/*     */     } 
/* 189 */     return super.getCapability(cap, side);
/*     */   }
/*     */ 
/*     */   
/*     */   public void invalidateCaps() {
/* 194 */     super.invalidateCaps();
/* 195 */     if (this.emcStorageCapability != null && this.emcStorageCapability.isPresent()) {
/* 196 */       this.emcStorageCapability.invalidate();
/* 197 */       this.emcStorageCapability = null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\block_entity\BaseEmcBlockEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */