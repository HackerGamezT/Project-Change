/*    */ package moze_intel.projecte.gameObjs.container.slots.transmutation;
/*    */ 
/*    */ import java.math.BigInteger;
/*    */ import java.util.Collections;
/*    */ import java.util.Optional;
/*    */ import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
/*    */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*    */ import moze_intel.projecte.api.capabilities.block_entity.IEmcStorage;
/*    */ import moze_intel.projecte.api.capabilities.item.IItemEmcHolder;
/*    */ import moze_intel.projecte.gameObjs.container.inventory.TransmutationInventory;
/*    */ import moze_intel.projecte.gameObjs.container.slots.InventoryContainerSlot;
/*    */ import moze_intel.projecte.gameObjs.container.slots.SlotPredicates;
/*    */ import moze_intel.projecte.utils.EMCHelper;
/*    */ import moze_intel.projecte.utils.MathUtils;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class SlotInput extends InventoryContainerSlot {
/*    */   private final TransmutationInventory inv;
/*    */   
/*    */   public SlotInput(TransmutationInventory inv, int index, int x, int y) {
/* 23 */     super((IItemHandler)inv, index, x, y);
/* 24 */     this.inv = inv;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_5857_(@NotNull ItemStack stack) {
/* 29 */     return SlotPredicates.RELAY_INV.test(stack);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack m_6201_(int amount) {
/* 35 */     ItemStack stack = super.m_6201_(amount);
/*    */     
/* 37 */     if (!stack.m_41619_() && this.inv.isServer())
/*    */     {
/* 39 */       this.inv.syncChangedSlots(Collections.singletonList(Integer.valueOf(getSlotIndex())), IKnowledgeProvider.TargetUpdateType.IF_NEEDED);
/*    */     }
/* 41 */     return stack;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void initialize(@NotNull ItemStack stack) {
/* 47 */     super.initialize(stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_5852_(@NotNull ItemStack stack) {
/* 52 */     super.m_5852_(stack);
/* 53 */     if (this.inv.isServer()) {
/* 54 */       if (stack.m_41619_()) {
/* 55 */         this.inv.syncChangedSlots(Collections.singletonList(Integer.valueOf(getSlotIndex())), IKnowledgeProvider.TargetUpdateType.ALL);
/*    */       } else {
/* 57 */         if (EMCHelper.doesItemHaveEmc(stack)) {
/* 58 */           this.inv.handleKnowledge(stack);
/*    */         }
/* 60 */         Optional<IItemEmcHolder> capability = stack.getCapability(PECapabilities.EMC_HOLDER_ITEM_CAPABILITY).resolve();
/* 61 */         if (capability.isPresent()) {
/* 62 */           IItemEmcHolder emcHolder = capability.get();
/*    */           
/* 64 */           long shrunkenAvailableEMC = MathUtils.clampToLong(this.inv.provider.getEmc());
/*    */           
/* 66 */           long actualInserted = emcHolder.insertEmc(stack, shrunkenAvailableEMC, IEmcStorage.EmcAction.EXECUTE);
/* 67 */           if (actualInserted > 0L) {
/*    */ 
/*    */             
/* 70 */             this.inv.syncChangedSlots(Collections.singletonList(Integer.valueOf(getSlotIndex())), IKnowledgeProvider.TargetUpdateType.NONE);
/* 71 */             this.inv.removeEmc(BigInteger.valueOf(actualInserted));
/* 72 */           } else if (emcHolder.getStoredEmc(stack) > 0L) {
/*    */ 
/*    */             
/* 75 */             this.inv.syncChangedSlots(Collections.singletonList(Integer.valueOf(getSlotIndex())), IKnowledgeProvider.TargetUpdateType.ALL);
/*    */           }
/*    */           else {
/*    */             
/* 79 */             this.inv.syncChangedSlots(Collections.singletonList(Integer.valueOf(getSlotIndex())), IKnowledgeProvider.TargetUpdateType.NONE);
/*    */           } 
/*    */         } else {
/*    */           
/* 83 */           this.inv.syncChangedSlots(Collections.singletonList(Integer.valueOf(getSlotIndex())), IKnowledgeProvider.TargetUpdateType.NONE);
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int m_6641_() {
/* 91 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\slots\transmutation\SlotInput.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */