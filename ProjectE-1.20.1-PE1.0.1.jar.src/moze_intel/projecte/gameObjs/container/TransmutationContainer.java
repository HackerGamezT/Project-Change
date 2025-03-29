/*     */ package moze_intel.projecte.gameObjs.container;
/*     */ 
/*     */ import java.math.BigInteger;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*     */ import moze_intel.projecte.gameObjs.container.inventory.TransmutationInventory;
/*     */ import moze_intel.projecte.gameObjs.container.slots.transmutation.SlotConsume;
/*     */ import moze_intel.projecte.gameObjs.container.slots.transmutation.SlotInput;
/*     */ import moze_intel.projecte.gameObjs.container.slots.transmutation.SlotLock;
/*     */ import moze_intel.projecte.gameObjs.container.slots.transmutation.SlotOutput;
/*     */ import moze_intel.projecte.gameObjs.container.slots.transmutation.SlotUnlearn;
/*     */ import moze_intel.projecte.gameObjs.registries.PEContainerTypes;
/*     */ import moze_intel.projecte.network.PacketHandler;
/*     */ import moze_intel.projecte.network.packets.IPEPacket;
/*     */ import moze_intel.projecte.network.packets.to_server.SearchUpdatePKT;
/*     */ import moze_intel.projecte.utils.EMCHelper;
/*     */ import moze_intel.projecte.utils.ItemHelper;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.inventory.ClickType;
/*     */ import net.minecraft.world.inventory.Slot;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.common.capabilities.ForgeCapabilities;
/*     */ import net.minecraftforge.common.util.NonNullSupplier;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import net.minecraftforge.items.ItemHandlerHelper;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class TransmutationContainer extends PEHandContainer {
/*  34 */   private final List<SlotInput> inputSlots = new ArrayList<>();
/*     */   public final TransmutationInventory transmutationInventory;
/*     */   private SlotUnlearn unlearn;
/*     */   
/*     */   public static TransmutationContainer fromNetwork(int windowId, Inventory playerInv, FriendlyByteBuf buf) {
/*  39 */     if (buf.readBoolean()) {
/*  40 */       return new TransmutationContainer(windowId, playerInv, (InteractionHand)buf.m_130066_(InteractionHand.class), buf.readByte());
/*     */     }
/*  42 */     return new TransmutationContainer(windowId, playerInv);
/*     */   }
/*     */   
/*     */   public TransmutationContainer(int windowId, Inventory playerInv) {
/*  46 */     super(PEContainerTypes.TRANSMUTATION_CONTAINER, windowId, playerInv, (InteractionHand)null, 0);
/*     */     
/*  48 */     this.transmutationInventory = new TransmutationInventory(this.playerInv.f_35978_);
/*  49 */     initSlots();
/*     */   }
/*     */   
/*     */   public TransmutationContainer(int windowId, Inventory playerInv, InteractionHand hand, int selected) {
/*  53 */     super(PEContainerTypes.TRANSMUTATION_CONTAINER, windowId, playerInv, hand, selected);
/*  54 */     this.transmutationInventory = new TransmutationInventory(this.playerInv.f_35978_);
/*  55 */     initSlots();
/*     */   }
/*     */ 
/*     */   
/*     */   private void initSlots() {
/*  60 */     m_38897_((Slot)new SlotInput(this.transmutationInventory, 0, 43, 23));
/*  61 */     m_38897_((Slot)new SlotInput(this.transmutationInventory, 1, 34, 41));
/*  62 */     m_38897_((Slot)new SlotInput(this.transmutationInventory, 2, 52, 41));
/*  63 */     m_38897_((Slot)new SlotInput(this.transmutationInventory, 3, 16, 50));
/*  64 */     m_38897_((Slot)new SlotInput(this.transmutationInventory, 4, 70, 50));
/*  65 */     m_38897_((Slot)new SlotInput(this.transmutationInventory, 5, 34, 59));
/*  66 */     m_38897_((Slot)new SlotInput(this.transmutationInventory, 6, 52, 59));
/*  67 */     m_38897_((Slot)new SlotInput(this.transmutationInventory, 7, 43, 77));
/*  68 */     m_38897_((Slot)new SlotLock(this.transmutationInventory, 8, 158, 50));
/*  69 */     m_38897_((Slot)new SlotConsume(this.transmutationInventory, 9, 107, 97));
/*  70 */     m_38897_((Slot)(this.unlearn = new SlotUnlearn(this.transmutationInventory, 10, 89, 97)));
/*  71 */     m_38897_((Slot)new SlotOutput(this.transmutationInventory, 11, 158, 9));
/*  72 */     m_38897_((Slot)new SlotOutput(this.transmutationInventory, 12, 176, 13));
/*  73 */     m_38897_((Slot)new SlotOutput(this.transmutationInventory, 13, 193, 30));
/*  74 */     m_38897_((Slot)new SlotOutput(this.transmutationInventory, 14, 199, 50));
/*  75 */     m_38897_((Slot)new SlotOutput(this.transmutationInventory, 15, 193, 70));
/*  76 */     m_38897_((Slot)new SlotOutput(this.transmutationInventory, 16, 176, 87));
/*  77 */     m_38897_((Slot)new SlotOutput(this.transmutationInventory, 17, 158, 91));
/*  78 */     m_38897_((Slot)new SlotOutput(this.transmutationInventory, 18, 140, 87));
/*  79 */     m_38897_((Slot)new SlotOutput(this.transmutationInventory, 19, 123, 70));
/*  80 */     m_38897_((Slot)new SlotOutput(this.transmutationInventory, 20, 116, 50));
/*  81 */     m_38897_((Slot)new SlotOutput(this.transmutationInventory, 21, 123, 30));
/*  82 */     m_38897_((Slot)new SlotOutput(this.transmutationInventory, 22, 140, 13));
/*  83 */     m_38897_((Slot)new SlotOutput(this.transmutationInventory, 23, 158, 31));
/*  84 */     m_38897_((Slot)new SlotOutput(this.transmutationInventory, 24, 177, 50));
/*  85 */     m_38897_((Slot)new SlotOutput(this.transmutationInventory, 25, 158, 69));
/*  86 */     m_38897_((Slot)new SlotOutput(this.transmutationInventory, 26, 139, 50));
/*  87 */     addPlayerInventory(35, 117);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   protected Slot m_38897_(@NotNull Slot slot) {
/*  93 */     if (slot instanceof SlotInput) { SlotInput input = (SlotInput)slot;
/*  94 */       this.inputSlots.add(input); }
/*     */     
/*  96 */     return super.m_38897_(slot);
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_6877_(@NotNull Player player) {
/* 101 */     super.m_6877_(player);
/* 102 */     if (player.m_6084_()) { if (player instanceof ServerPlayer) { ServerPlayer serverPlayer = (ServerPlayer)player; if (serverPlayer.m_9232_()) {
/* 103 */           player.m_36176_(this.unlearn.m_7993_(), false); return;
/*     */         }  }
/* 105 */        player.m_150109_().m_150079_(this.unlearn.m_7993_());
/*     */       return; }
/*     */     
/*     */     player.m_36176_(this.unlearn.m_7993_(), false);
/*     */   }
/*     */   @NotNull
/*     */   public ItemStack m_7648_(@NotNull Player player, int slotIndex) {
/* 112 */     if (slotIndex < 9 || slotIndex == 10)
/*     */     {
/* 114 */       return super.m_7648_(player, slotIndex);
/*     */     }
/* 116 */     Slot currentSlot = tryGetSlot(slotIndex);
/* 117 */     if (currentSlot == null || !currentSlot.m_6657_()) {
/* 118 */       return ItemStack.f_41583_;
/*     */     }
/* 120 */     if (slotIndex >= 11 && slotIndex <= 26) {
/* 121 */       ItemStack stack = currentSlot.m_7993_().m_41777_();
/*     */       
/* 123 */       long itemEmc = EMCHelper.getEmcValue(stack);
/*     */       
/* 125 */       if (itemEmc > 0L) {
/*     */         
/* 127 */         stack.m_41764_(stack.m_41741_());
/*     */         
/* 129 */         int stackSize = stack.m_41613_() - ItemHelper.simulateFit((player.m_150109_()).f_35974_, stack);
/* 130 */         if (stackSize > 0) {
/* 131 */           BigInteger availableEMC = this.transmutationInventory.getAvailableEmc();
/* 132 */           BigInteger emc = BigInteger.valueOf(itemEmc);
/* 133 */           BigInteger totalEmc = emc.multiply(BigInteger.valueOf(stackSize));
/* 134 */           if (totalEmc.compareTo(availableEMC) > 0) {
/*     */ 
/*     */             
/* 137 */             BigInteger numOperations = availableEMC.divide(emc);
/*     */ 
/*     */             
/* 140 */             stackSize = numOperations.intValueExact();
/* 141 */             totalEmc = emc.multiply(numOperations);
/* 142 */             if (stackSize <= 0) {
/* 143 */               return ItemStack.f_41583_;
/*     */             }
/*     */           } 
/*     */           
/* 147 */           stack.m_41764_(stackSize);
/* 148 */           IItemHandler inv = (IItemHandler)player.getCapability(ForgeCapabilities.ITEM_HANDLER).orElseThrow(NullPointerException::new);
/* 149 */           if (this.transmutationInventory.isServer()) {
/* 150 */             this.transmutationInventory.removeEmc(totalEmc);
/*     */           }
/* 152 */           ItemHandlerHelper.insertItemStacked(inv, stack, false);
/*     */         } 
/*     */       } 
/* 155 */     } else if (slotIndex > 26) {
/* 156 */       ItemStack slotStack = currentSlot.m_7993_();
/* 157 */       ItemStack stackToInsert = slotStack;
/* 158 */       if (stackToInsert.getCapability(PECapabilities.EMC_HOLDER_ITEM_CAPABILITY).isPresent()) {
/*     */ 
/*     */         
/* 161 */         stackToInsert = insertItem(this.inputSlots, stackToInsert, true);
/* 162 */         if (slotStack.m_41613_() == stackToInsert.m_41613_())
/*     */         {
/* 164 */           stackToInsert = insertItem(this.inputSlots, stackToInsert, false);
/*     */         }
/* 166 */         if (slotStack.m_41613_() != stackToInsert.m_41613_()) {
/* 167 */           return transferSuccess(currentSlot, player, slotStack, stackToInsert);
/*     */         }
/*     */       } 
/*     */       
/* 171 */       long emc = EMCHelper.getEmcSellValue(stackToInsert);
/* 172 */       if (emc > 0L || stackToInsert.m_41720_() instanceof moze_intel.projecte.gameObjs.items.Tome) {
/* 173 */         if (this.transmutationInventory.isServer()) {
/* 174 */           BigInteger emcBigInt = BigInteger.valueOf(emc);
/* 175 */           this.transmutationInventory.handleKnowledge(stackToInsert);
/* 176 */           this.transmutationInventory.addEmc(emcBigInt.multiply(BigInteger.valueOf(stackToInsert.m_41613_())));
/*     */         } 
/* 178 */         currentSlot.m_5852_(ItemStack.f_41583_);
/*     */       } 
/*     */     } 
/* 181 */     return ItemStack.f_41583_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clickPostValidate(int slotIndex, int dragType, @NotNull ClickType clickType, @NotNull Player player) {
/* 186 */     if ((player.m_9236_()).f_46443_ && this.transmutationInventory.getHandlerForSlot(slotIndex) == this.transmutationInventory.outputs) {
/* 187 */       Slot slot = tryGetSlot(slotIndex);
/* 188 */       if (slot != null) {
/* 189 */         PacketHandler.sendToServer((IPEPacket)new SearchUpdatePKT(this.transmutationInventory.getIndexFromSlot(slotIndex), slot.m_7993_()));
/*     */       }
/*     */     } 
/* 192 */     super.clickPostValidate(slotIndex, dragType, clickType, player);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_5622_(@NotNull Slot slot) {
/* 197 */     return (!(slot instanceof SlotConsume) && !(slot instanceof SlotUnlearn) && !(slot instanceof SlotInput) && !(slot instanceof SlotLock) && !(slot instanceof SlotOutput));
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\TransmutationContainer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */