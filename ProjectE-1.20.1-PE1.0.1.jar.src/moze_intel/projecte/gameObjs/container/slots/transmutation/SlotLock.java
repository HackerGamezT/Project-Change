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
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class SlotLock extends InventoryContainerSlot {
/*    */   private final TransmutationInventory inv;
/*    */   
/*    */   public SlotLock(TransmutationInventory inv, int index, int x, int y) {
/* 22 */     super((IItemHandler)inv, index, x, y);
/* 23 */     this.inv = inv;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_5857_(@NotNull ItemStack stack) {
/* 28 */     return SlotPredicates.RELAY_INV.test(stack);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack m_6201_(int amount) {
/* 34 */     ItemStack stack = super.m_6201_(amount);
/*    */     
/* 36 */     if (!stack.m_41619_() && this.inv.isServer())
/*    */     {
/* 38 */       this.inv.syncChangedSlots(Collections.singletonList(Integer.valueOf(getSlotIndex())), IKnowledgeProvider.TargetUpdateType.ALL);
/*    */     }
/* 40 */     return stack;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void initialize(@NotNull ItemStack stack) {
/* 46 */     super.initialize(stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_5852_(@NotNull ItemStack stack) {
/* 51 */     super.m_5852_(stack);
/* 52 */     if (this.inv.isServer()) {
/* 53 */       if (stack.m_41619_()) {
/* 54 */         this.inv.syncChangedSlots(Collections.singletonList(Integer.valueOf(getSlotIndex())), IKnowledgeProvider.TargetUpdateType.ALL);
/*    */       } else {
/* 56 */         if (EMCHelper.doesItemHaveEmc(stack)) {
/* 57 */           this.inv.handleKnowledge(stack);
/*    */         }
/* 59 */         Optional<IItemEmcHolder> capability = stack.getCapability(PECapabilities.EMC_HOLDER_ITEM_CAPABILITY).resolve();
/* 60 */         if (capability.isPresent()) {
/* 61 */           IItemEmcHolder emcHolder = capability.get();
/* 62 */           long actualExtracted = emcHolder.extractEmc(stack, emcHolder.getStoredEmc(stack), IEmcStorage.EmcAction.EXECUTE);
/* 63 */           if (actualExtracted > 0L) {
/* 64 */             this.inv.syncChangedSlots(Collections.singletonList(Integer.valueOf(getSlotIndex())), IKnowledgeProvider.TargetUpdateType.NONE);
/* 65 */             this.inv.addEmc(BigInteger.valueOf(actualExtracted));
/*    */           } else {
/*    */             
/* 68 */             this.inv.syncChangedSlots(Collections.singletonList(Integer.valueOf(getSlotIndex())), IKnowledgeProvider.TargetUpdateType.ALL);
/*    */           } 
/*    */         } else {
/*    */           
/* 72 */           this.inv.syncChangedSlots(Collections.singletonList(Integer.valueOf(getSlotIndex())), IKnowledgeProvider.TargetUpdateType.ALL);
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int m_6641_() {
/* 80 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\slots\transmutation\SlotLock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */