/*     */ package moze_intel.projecte.gameObjs.block_entities;
/*     */ import java.util.Optional;
/*     */ import moze_intel.projecte.api.block_entity.IDMPedestal;
/*     */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*     */ import moze_intel.projecte.api.capabilities.item.IPedestalItem;
/*     */ import moze_intel.projecte.capability.managing.BasicCapabilityResolver;
/*     */ import moze_intel.projecte.gameObjs.registries.PEBlockEntityTypes;
/*     */ import moze_intel.projecte.gameObjs.registries.PESoundEvents;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.core.particles.ParticleTypes;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import net.minecraftforge.items.IItemHandlerModifiable;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class DMPedestalBlockEntity extends CapabilityEmcBlockEntity implements IDMPedestal {
/*  26 */   private final EmcBlockEntity.StackHandler inventory = new EmcBlockEntity.StackHandler(1)
/*     */     {
/*     */       public void onContentsChanged(int slot) {
/*  29 */         super.onContentsChanged(slot);
/*  30 */         if (DMPedestalBlockEntity.this.f_58857_ != null && !DMPedestalBlockEntity.this.f_58857_.f_46443_) {
/*     */           
/*  32 */           BlockState state = DMPedestalBlockEntity.this.m_58900_();
/*  33 */           DMPedestalBlockEntity.this.f_58857_.m_7260_(DMPedestalBlockEntity.this.f_58858_, state, state, 8);
/*     */         } 
/*     */       }
/*     */     };
/*     */   private static final int RANGE = 4;
/*  38 */   private int particleCooldown = 10; private boolean isActive = false;
/*  39 */   private int activityCooldown = 0;
/*     */   public boolean previousRedstoneState = false;
/*     */   
/*     */   public DMPedestalBlockEntity(BlockPos pos, BlockState state) {
/*  43 */     super(PEBlockEntityTypes.DARK_MATTER_PEDESTAL, pos, state, 1000L);
/*  44 */     this.itemHandlerResolver = BasicCapabilityResolver.getBasicItemHandlerResolver((IItemHandler)this.inventory);
/*     */   }
/*     */   
/*     */   public static void tickClient(Level level, BlockPos pos, BlockState state, DMPedestalBlockEntity pedestal) {
/*  48 */     if (pedestal.getActive()) {
/*  49 */       ItemStack stack = pedestal.inventory.getStackInSlot(0);
/*  50 */       if (stack.m_41619_()) {
/*  51 */         pedestal.setActive(false);
/*     */       } else {
/*  53 */         Optional<IPedestalItem> capability = stack.getCapability(PECapabilities.PEDESTAL_ITEM_CAPABILITY).resolve();
/*  54 */         if (capability.isPresent()) {
/*  55 */           ((IPedestalItem)capability.get()).updateInPedestal(stack, level, pos, (BlockEntity)pedestal);
/*  56 */           if (pedestal.particleCooldown <= 0) {
/*  57 */             pedestal.spawnParticleTypes();
/*  58 */             pedestal.particleCooldown = 10;
/*     */           } else {
/*  60 */             pedestal.particleCooldown--;
/*     */           } 
/*     */         } else {
/*  63 */           pedestal.setActive(false);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void tickServer(Level level, BlockPos pos, BlockState state, DMPedestalBlockEntity pedestal) {
/*  70 */     if (pedestal.getActive()) {
/*  71 */       ItemStack stack = pedestal.inventory.getStackInSlot(0);
/*  72 */       if (stack.m_41619_()) {
/*  73 */         pedestal.setActive(false);
/*     */       } else {
/*  75 */         Optional<IPedestalItem> capability = stack.getCapability(PECapabilities.PEDESTAL_ITEM_CAPABILITY).resolve();
/*  76 */         if (capability.isPresent()) {
/*  77 */           if (((IPedestalItem)capability.get()).updateInPedestal(stack, level, pos, (BlockEntity)pedestal)) {
/*  78 */             pedestal.inventory.onContentsChanged(0);
/*     */           }
/*     */         } else {
/*  81 */           pedestal.setActive(false);
/*     */         } 
/*     */       } 
/*     */     } 
/*  85 */     pedestal.updateComparators();
/*     */   }
/*     */   
/*     */   private void spawnParticleTypes() {
/*  89 */     int x = this.f_58858_.m_123341_();
/*  90 */     int y = this.f_58858_.m_123342_();
/*  91 */     int z = this.f_58858_.m_123343_();
/*  92 */     this.f_58857_.m_7106_((ParticleOptions)ParticleTypes.f_123744_, x + 0.2D, y + 0.3D, z + 0.2D, 0.0D, 0.0D, 0.0D);
/*  93 */     this.f_58857_.m_7106_((ParticleOptions)ParticleTypes.f_123744_, x + 0.2D, y + 0.3D, z + 0.5D, 0.0D, 0.0D, 0.0D);
/*  94 */     this.f_58857_.m_7106_((ParticleOptions)ParticleTypes.f_123744_, x + 0.2D, y + 0.3D, z + 0.8D, 0.0D, 0.0D, 0.0D);
/*  95 */     this.f_58857_.m_7106_((ParticleOptions)ParticleTypes.f_123744_, x + 0.5D, y + 0.3D, z + 0.2D, 0.0D, 0.0D, 0.0D);
/*  96 */     this.f_58857_.m_7106_((ParticleOptions)ParticleTypes.f_123744_, x + 0.5D, y + 0.3D, z + 0.8D, 0.0D, 0.0D, 0.0D);
/*  97 */     this.f_58857_.m_7106_((ParticleOptions)ParticleTypes.f_123744_, x + 0.8D, y + 0.3D, z + 0.2D, 0.0D, 0.0D, 0.0D);
/*  98 */     this.f_58857_.m_7106_((ParticleOptions)ParticleTypes.f_123744_, x + 0.8D, y + 0.3D, z + 0.5D, 0.0D, 0.0D, 0.0D);
/*  99 */     this.f_58857_.m_7106_((ParticleOptions)ParticleTypes.f_123744_, x + 0.8D, y + 0.3D, z + 0.8D, 0.0D, 0.0D, 0.0D);
/* 100 */     RandomSource rand = this.f_58857_.f_46441_;
/* 101 */     for (int i = 0; i < 3; i++) {
/* 102 */       int j = rand.m_188503_(2) * 2 - 1;
/* 103 */       int k = rand.m_188503_(2) * 2 - 1;
/* 104 */       double d0 = this.f_58858_.m_123341_() + 0.5D + 0.25D * j;
/* 105 */       double d1 = (this.f_58858_.m_123342_() + rand.m_188501_());
/* 106 */       double d2 = this.f_58858_.m_123343_() + 0.5D + 0.25D * k;
/* 107 */       double d3 = (rand.m_188501_() * j);
/* 108 */       double d4 = (rand.m_188501_() - 0.5D) * 0.125D;
/* 109 */       double d5 = (rand.m_188501_() * k);
/* 110 */       this.f_58857_.m_7106_((ParticleOptions)ParticleTypes.f_123760_, d0, d1, d2, d3, d4, d5);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getActivityCooldown() {
/* 116 */     return this.activityCooldown;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActivityCooldown(int cooldown) {
/* 121 */     if (this.activityCooldown != cooldown) {
/* 122 */       this.activityCooldown = cooldown;
/* 123 */       markDirty(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void decrementActivityCooldown() {
/* 129 */     this.activityCooldown--;
/* 130 */     markDirty(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public AABB getEffectBounds() {
/* 135 */     return new AABB(this.f_58858_.m_7918_(-4, -4, -4), this.f_58858_.m_7918_(4, 4, 4));
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_142466_(@NotNull CompoundTag nbt) {
/* 140 */     super.m_142466_(nbt);
/* 141 */     this.inventory.deserializeNBT(nbt);
/* 142 */     setActive(nbt.m_128471_("isActive"));
/* 143 */     this.activityCooldown = nbt.m_128451_("activityCooldown");
/* 144 */     this.previousRedstoneState = nbt.m_128471_("powered");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_183515_(@NotNull CompoundTag tag) {
/* 149 */     super.m_183515_(tag);
/* 150 */     tag.m_128391_(this.inventory.serializeNBT());
/* 151 */     tag.m_128379_("isActive", getActive());
/* 152 */     tag.m_128405_("activityCooldown", this.activityCooldown);
/* 153 */     tag.m_128379_("powered", this.previousRedstoneState);
/*     */   }
/*     */   
/*     */   public boolean getActive() {
/* 157 */     return this.isActive;
/*     */   }
/*     */   
/*     */   public void setActive(boolean newState) {
/* 161 */     if (newState != getActive() && this.f_58857_ != null) {
/* 162 */       if (newState) {
/* 163 */         this.f_58857_.m_5594_(null, this.f_58858_, (SoundEvent)PESoundEvents.CHARGE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
/* 164 */         for (int i = 0; i < this.f_58857_.f_46441_.m_188503_(35) + 10; i++) {
/* 165 */           this.f_58857_.m_7106_((ParticleOptions)ParticleTypes.f_123771_, this.f_58858_
/* 166 */               .m_123341_() + 0.5D + this.f_58857_.f_46441_.m_188583_() * 0.12999999523162842D, (this.f_58858_
/* 167 */               .m_123342_() + 1) + this.f_58857_.f_46441_.m_188583_() * 0.12999999523162842D, this.f_58858_
/* 168 */               .m_123343_() + 0.5D + this.f_58857_.f_46441_.m_188583_() * 0.12999999523162842D, 0.0D, 0.0D, 0.0D);
/*     */         }
/*     */       } else {
/*     */         
/* 172 */         this.f_58857_.m_5594_(null, this.f_58858_, (SoundEvent)PESoundEvents.UNCHARGE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
/* 173 */         for (int i = 0; i < this.f_58857_.f_46441_.m_188503_(35) + 10; i++) {
/* 174 */           this.f_58857_.m_7106_((ParticleOptions)ParticleTypes.f_123762_, this.f_58858_
/* 175 */               .m_123341_() + 0.5D + this.f_58857_.f_46441_.m_188583_() * 0.12999999523162842D, (this.f_58858_
/* 176 */               .m_123342_() + 1) + this.f_58857_.f_46441_.m_188583_() * 0.12999999523162842D, this.f_58858_
/* 177 */               .m_123343_() + 0.5D + this.f_58857_.f_46441_.m_188583_() * 0.12999999523162842D, 0.0D, 0.0D, 0.0D);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 182 */     this.isActive = newState;
/* 183 */     m_6596_();
/*     */   }
/*     */   
/*     */   public IItemHandlerModifiable getInventory() {
/* 187 */     return (IItemHandlerModifiable)this.inventory;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\block_entities\DMPedestalBlockEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */