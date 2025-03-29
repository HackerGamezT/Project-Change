/*    */ package moze_intel.projecte.gameObjs.block_entities;
/*    */ 
/*    */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*    */ import moze_intel.projecte.api.capabilities.item.IAlchChestItem;
/*    */ import moze_intel.projecte.capability.managing.BasicCapabilityResolver;
/*    */ import moze_intel.projecte.gameObjs.container.AlchChestContainer;
/*    */ import moze_intel.projecte.gameObjs.registries.PEBlockEntityTypes;
/*    */ import moze_intel.projecte.gameObjs.registries.PEBlocks;
/*    */ import moze_intel.projecte.utils.text.TextComponentUtil;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class AlchBlockEntityChest extends EmcChestBlockEntity {
/* 23 */   private final EmcBlockEntity.StackHandler inventory = new EmcBlockEntity.StackHandler(104)
/*    */     {
/*    */       public void onContentsChanged(int slot) {
/* 26 */         super.onContentsChanged(slot);
/* 27 */         if (AlchBlockEntityChest.this.f_58857_ != null && !AlchBlockEntityChest.this.f_58857_.f_46443_)
/* 28 */           AlchBlockEntityChest.this.inventoryChanged = true; 
/*    */       }
/*    */     };
/*    */   
/*    */   private boolean inventoryChanged;
/*    */   
/*    */   public AlchBlockEntityChest(BlockPos pos, BlockState state) {
/* 35 */     super(PEBlockEntityTypes.ALCHEMICAL_CHEST, pos, state, 1000L);
/* 36 */     this.itemHandlerResolver = BasicCapabilityResolver.getBasicItemHandlerResolver((IItemHandler)this.inventory);
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_142466_(@NotNull CompoundTag nbt) {
/* 41 */     super.m_142466_(nbt);
/* 42 */     this.inventory.deserializeNBT(nbt);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void m_183515_(@NotNull CompoundTag tag) {
/* 47 */     super.m_183515_(tag);
/* 48 */     tag.m_128391_(this.inventory.serializeNBT());
/*    */   }
/*    */   
/*    */   public static void tickClient(Level level, BlockPos pos, BlockState state, AlchBlockEntityChest alchChest) {
/* 52 */     for (int i = 0; i < alchChest.inventory.getSlots(); i++) {
/* 53 */       ItemStack stack = alchChest.inventory.getStackInSlot(i);
/* 54 */       if (!stack.m_41619_()) {
/* 55 */         stack.getCapability(PECapabilities.ALCH_CHEST_ITEM_CAPABILITY).ifPresent(alchChestItem -> alchChestItem.updateInAlchChest(level, pos, stack));
/*    */       }
/*    */     } 
/* 58 */     EmcChestBlockEntity.lidAnimateTick(level, pos, state, alchChest);
/*    */   }
/*    */   
/*    */   public static void tickServer(Level level, BlockPos pos, BlockState state, AlchBlockEntityChest alchChest) {
/* 62 */     for (int i = 0; i < alchChest.inventory.getSlots(); i++) {
/* 63 */       ItemStack stack = alchChest.inventory.getStackInSlot(i);
/* 64 */       if (!stack.m_41619_()) {
/* 65 */         int slotId = i;
/* 66 */         stack.getCapability(PECapabilities.ALCH_CHEST_ITEM_CAPABILITY).ifPresent(alchChestItem -> {
/*    */               if (alchChestItem.updateInAlchChest(level, pos, stack)) {
/*    */                 alchChest.inventory.onContentsChanged(slotId);
/*    */               }
/*    */             });
/*    */       } 
/*    */     } 
/* 73 */     if (alchChest.inventoryChanged) {
/*    */       
/* 75 */       alchChest.inventoryChanged = false;
/* 76 */       level.m_7260_(pos, state, state, 2);
/*    */     } 
/* 78 */     alchChest.updateComparators();
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public AbstractContainerMenu m_7208_(int windowId, @NotNull Inventory playerInventory, @NotNull Player playerIn) {
/* 84 */     return (AbstractContainerMenu)new AlchChestContainer(windowId, playerInventory, this);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Component m_5446_() {
/* 90 */     return (Component)TextComponentUtil.build(new Object[] { PEBlocks.ALCHEMICAL_CHEST });
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\block_entities\AlchBlockEntityChest.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */