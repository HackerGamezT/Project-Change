/*     */ package moze_intel.projecte.events;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Set;
/*     */ import moze_intel.projecte.api.capabilities.IAlchBagProvider;
/*     */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*     */ import moze_intel.projecte.api.capabilities.item.IAlchBagItem;
/*     */ import moze_intel.projecte.gameObjs.container.AlchBagContainer;
/*     */ import moze_intel.projecte.gameObjs.items.AlchemicalBag;
/*     */ import moze_intel.projecte.gameObjs.items.IFireProtector;
/*     */ import moze_intel.projecte.handlers.CommonInternalAbilities;
/*     */ import moze_intel.projecte.handlers.InternalAbilities;
/*     */ import moze_intel.projecte.handlers.InternalTimers;
/*     */ import moze_intel.projecte.utils.PlayerHelper;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*     */ import net.minecraft.world.item.DyeColor;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.common.capabilities.ForgeCapabilities;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ 
/*     */ @EventBusSubscriber(modid = "projecte")
/*     */ public class TickEvents {
/*     */   @SubscribeEvent
/*     */   public static void playerTick(TickEvent.PlayerTickEvent event) {
/*  30 */     if (event.phase == TickEvent.Phase.END) {
/*  31 */       event.player.getCapability(PECapabilities.ALCH_BAG_CAPABILITY).ifPresent(provider -> {
/*     */             Set<DyeColor> colorsChanged = EnumSet.noneOf(DyeColor.class);
/*     */             
/*     */             for (DyeColor color : getBagColorsPresent(event.player)) {
/*     */               IItemHandler inv = provider.getBag(color);
/*     */               for (int i = 0; i < inv.getSlots(); i++) {
/*     */                 ItemStack current = inv.getStackInSlot(i);
/*     */                 if (!current.m_41619_()) {
/*     */                   current.getCapability(PECapabilities.ALCH_BAG_ITEM_CAPABILITY).ifPresent(());
/*     */                 }
/*     */               } 
/*     */             } 
/*     */             Player patt1923$temp = event.player;
/*     */             if (patt1923$temp instanceof ServerPlayer) {
/*     */               ServerPlayer serverPlayer = (ServerPlayer)patt1923$temp;
/*     */               for (DyeColor e : colorsChanged) {
/*     */                 AbstractContainerMenu patt2089$temp = serverPlayer.f_36096_;
/*     */                 if (patt2089$temp instanceof AlchBagContainer) {
/*     */                   AlchBagContainer container = (AlchBagContainer)patt2089$temp;
/*     */                   ItemStack heldItem = serverPlayer.m_21120_(container.hand);
/*     */                   Item patt2232$temp = heldItem.m_41720_();
/*     */                   if (patt2232$temp instanceof AlchemicalBag) {
/*     */                     AlchemicalBag bag = (AlchemicalBag)patt2232$temp;
/*     */                     if (bag.color == e) {
/*     */                       continue;
/*     */                     }
/*     */                   } 
/*     */                 } 
/*     */                 provider.sync(e, serverPlayer);
/*     */               } 
/*     */             } 
/*     */           });
/*  63 */       event.player.getCapability(CommonInternalAbilities.CAPABILITY).ifPresent(CommonInternalAbilities::tick);
/*  64 */       if (event.side.isServer()) {
/*  65 */         event.player.getCapability(InternalAbilities.CAPABILITY).ifPresent(InternalAbilities::tick);
/*  66 */         event.player.getCapability(InternalTimers.CAPABILITY).ifPresent(InternalTimers::tick);
/*  67 */         if (event.player.m_6060_() && shouldPlayerResistFire((ServerPlayer)event.player)) {
/*  68 */           event.player.m_20095_();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean shouldPlayerResistFire(ServerPlayer player) {
/*  75 */     for (ItemStack stack : player.m_6168_()) {
/*  76 */       if (!stack.m_41619_()) { Item item = stack.m_41720_(); if (item instanceof IFireProtector) { IFireProtector protector = (IFireProtector)item; if (protector.canProtectAgainstFire(stack, player))
/*  77 */             return true;  }
/*     */          }
/*     */     
/*  80 */     }  for (int i = 0; i < Inventory.m_36059_(); i++) {
/*  81 */       ItemStack stack = player.m_150109_().m_8020_(i);
/*  82 */       if (!stack.m_41619_()) { Item item = stack.m_41720_(); if (item instanceof IFireProtector) { IFireProtector protector = (IFireProtector)item; if (protector.canProtectAgainstFire(stack, player))
/*  83 */             return true;  }
/*     */          }
/*     */     
/*  86 */     }  IItemHandler curios = PlayerHelper.getCurios((Player)player);
/*  87 */     if (curios != null)
/*  88 */       for (int j = 0; j < curios.getSlots(); j++) {
/*  89 */         ItemStack stack = curios.getStackInSlot(j);
/*  90 */         if (!stack.m_41619_()) { Item item = stack.m_41720_(); if (item instanceof IFireProtector) { IFireProtector protector = (IFireProtector)item; if (protector.canProtectAgainstFire(stack, player))
/*  91 */               return true;  }
/*     */            }
/*     */       
/*     */       }  
/*  95 */     return false;
/*     */   }
/*     */   
/*     */   private static Set<DyeColor> getBagColorsPresent(Player player) {
/*  99 */     Set<DyeColor> bagsPresent = EnumSet.noneOf(DyeColor.class);
/* 100 */     player.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(inv -> {
/*     */           for (int i = 0; i < inv.getSlots(); i++) {
/*     */             ItemStack stack = inv.getStackInSlot(i); if (!stack.m_41619_()) {
/*     */               Item patt4219$temp = stack.m_41720_(); if (patt4219$temp instanceof AlchemicalBag) {
/*     */                 AlchemicalBag bag = (AlchemicalBag)patt4219$temp; bagsPresent.add(bag.color);
/*     */               } 
/*     */             } 
/*     */           } 
/* 108 */         }); return bagsPresent;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\events\TickEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */