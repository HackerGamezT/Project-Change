/*    */ package moze_intel.projecte.gameObjs.items;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import moze_intel.projecte.api.capabilities.IAlchBagProvider;
/*    */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*    */ import moze_intel.projecte.gameObjs.container.AlchBagContainer;
/*    */ import moze_intel.projecte.utils.ItemHelper;
/*    */ import net.minecraft.core.NonNullList;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.InteractionResultHolder;
/*    */ import net.minecraft.world.MenuProvider;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*    */ import net.minecraft.world.item.DyeColor;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraftforge.common.util.NonNullSupplier;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import net.minecraftforge.items.IItemHandlerModifiable;
/*    */ import net.minecraftforge.network.NetworkHooks;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class AlchemicalBag
/*    */   extends ItemPE {
/*    */   public final DyeColor color;
/*    */   
/*    */   public AlchemicalBag(Item.Properties props, DyeColor color) {
/* 33 */     super(props);
/* 34 */     this.color = color;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public InteractionResultHolder<ItemStack> m_7203_(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
/* 40 */     if (!level.f_46443_) {
/* 41 */       NetworkHooks.openScreen((ServerPlayer)player, new ContainerProvider(player.m_21120_(hand), hand), buf -> {
/*    */             buf.m_130068_((Enum)hand);
/*    */             
/*    */             buf.writeByte((player.m_150109_()).f_35977_);
/*    */             buf.writeBoolean(false);
/*    */           });
/*    */     }
/* 48 */     return InteractionResultHolder.m_19090_(player.m_21120_(hand));
/*    */   }
/*    */   
/*    */   public static ItemStack getFirstBagWithSuctionItem(Player player, NonNullList<ItemStack> inventory) {
/* 52 */     Optional<IAlchBagProvider> cap = Optional.empty();
/* 53 */     for (ItemStack stack : inventory) {
/* 54 */       if (!stack.m_41619_()) { Item item = stack.m_41720_(); if (item instanceof AlchemicalBag) { AlchemicalBag bag = (AlchemicalBag)item;
/* 55 */           if (cap.isEmpty()) {
/* 56 */             cap = player.getCapability(PECapabilities.ALCH_BAG_CAPABILITY).resolve();
/* 57 */             if (cap.isEmpty()) {
/*    */               break;
/*    */             }
/*    */           } 
/*    */           
/* 62 */           IItemHandler inv = ((IAlchBagProvider)cap.get()).getBag(bag.color);
/* 63 */           for (int i = 0; i < inv.getSlots(); i++) {
/* 64 */             ItemStack ring = inv.getStackInSlot(i);
/* 65 */             if (!ring.m_41619_() && (ring.m_41720_() instanceof moze_intel.projecte.gameObjs.items.rings.BlackHoleBand || ring.m_41720_() instanceof moze_intel.projecte.gameObjs.items.rings.VoidRing) && 
/* 66 */               ItemHelper.checkItemNBT(ring, "Active")) {
/* 67 */               return stack;
/*    */             }
/*    */           }  }
/*    */          }
/*    */     
/*    */     } 
/* 73 */     return ItemStack.f_41583_;
/*    */   }
/*    */   
/*    */   private class ContainerProvider
/*    */     implements MenuProvider {
/*    */     private final ItemStack stack;
/*    */     private final InteractionHand hand;
/*    */     
/*    */     private ContainerProvider(ItemStack stack, InteractionHand hand) {
/* 82 */       this.stack = stack;
/* 83 */       this.hand = hand;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     @NotNull
/*    */     public AbstractContainerMenu m_7208_(int windowId, @NotNull Inventory playerInventory, @NotNull Player player) {
/* 91 */       IItemHandlerModifiable inv = (IItemHandlerModifiable)((IAlchBagProvider)player.getCapability(PECapabilities.ALCH_BAG_CAPABILITY).orElseThrow(NullPointerException::new)).getBag(AlchemicalBag.this.color);
/* 92 */       return (AbstractContainerMenu)new AlchBagContainer(windowId, playerInventory, this.hand, inv, playerInventory.f_35977_, false);
/*    */     }
/*    */ 
/*    */     
/*    */     @NotNull
/*    */     public Component m_5446_() {
/* 98 */       return this.stack.m_41786_();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\AlchemicalBag.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */