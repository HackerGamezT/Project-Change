/*    */ package moze_intel.projecte.gameObjs.block_entities;
/*    */ import moze_intel.projecte.api.capabilities.block_entity.IEmcStorage;
/*    */ import moze_intel.projecte.gameObjs.container.CondenserMK2Container;
/*    */ import moze_intel.projecte.gameObjs.container.slots.SlotPredicates;
/*    */ import moze_intel.projecte.gameObjs.registries.PEBlockEntityTypes;
/*    */ import moze_intel.projecte.gameObjs.registries.PEBlocks;
/*    */ import moze_intel.projecte.utils.EMCHelper;
/*    */ import moze_intel.projecte.utils.text.TextComponentUtil;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.nbt.Tag;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import net.minecraftforge.items.IItemHandlerModifiable;
/*    */ import net.minecraftforge.items.ItemStackHandler;
/*    */ import net.minecraftforge.items.wrapper.CombinedInvWrapper;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class CondenserMK2BlockEntity extends CondenserBlockEntity {
/*    */   public CondenserMK2BlockEntity(BlockPos pos, BlockState state) {
/* 26 */     super(PEBlockEntityTypes.CONDENSER_MK2, pos, state);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   protected IItemHandler createAutomationInventory() {
/* 32 */     IItemHandlerModifiable automationInput = new WrappedItemHandler((IItemHandlerModifiable)getInput(), WrappedItemHandler.WriteMode.IN)
/*    */       {
/*    */         @NotNull
/*    */         public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
/* 36 */           return (SlotPredicates.HAS_EMC.test(stack) && !CondenserMK2BlockEntity.this.isStackEqualToLock(stack)) ? super.insertItem(slot, stack, simulate) : stack;
/*    */         }
/*    */       };
/* 39 */     IItemHandlerModifiable automationOutput = new WrappedItemHandler((IItemHandlerModifiable)getOutput(), WrappedItemHandler.WriteMode.OUT);
/* 40 */     return (IItemHandler)new CombinedInvWrapper(new IItemHandlerModifiable[] { automationInput, automationOutput });
/*    */   }
/*    */ 
/*    */   
/*    */   protected ItemStackHandler createInput() {
/* 45 */     return new EmcBlockEntity.StackHandler(this, 42);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ItemStackHandler createOutput() {
/* 50 */     return new EmcBlockEntity.StackHandler(this, 42);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void condense() {
/* 55 */     while (hasSpace() && getStoredEmc() >= this.requiredEmc) {
/* 56 */       pushStack();
/* 57 */       forceExtractEmc(this.requiredEmc, IEmcStorage.EmcAction.EXECUTE);
/*    */     } 
/* 59 */     if (hasSpace()) {
/* 60 */       for (int i = 0; i < getInput().getSlots(); i++) {
/* 61 */         ItemStack stack = getInput().getStackInSlot(i);
/* 62 */         if (!stack.m_41619_()) {
/* 63 */           forceInsertEmc(EMCHelper.getEmcSellValue(stack) * stack.m_41613_(), IEmcStorage.EmcAction.EXECUTE);
/* 64 */           getInput().setStackInSlot(i, ItemStack.f_41583_);
/*    */           break;
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_142466_(@NotNull CompoundTag nbt) {
/* 73 */     super.m_142466_(nbt);
/* 74 */     getOutput().deserializeNBT(nbt.m_128469_("Output"));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void m_183515_(@NotNull CompoundTag tag) {
/* 79 */     super.m_183515_(tag);
/* 80 */     tag.m_128365_("Output", (Tag)getOutput().serializeNBT());
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractContainerMenu m_7208_(int windowId, @NotNull Inventory playerInv, @NotNull Player player) {
/* 85 */     return (AbstractContainerMenu)new CondenserMK2Container(windowId, playerInv, this);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Component m_5446_() {
/* 91 */     return (Component)TextComponentUtil.build(new Object[] { PEBlocks.CONDENSER_MK2 });
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\block_entities\CondenserMK2BlockEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */