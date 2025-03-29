/*     */ package moze_intel.projecte.network.packets.to_server;
/*     */ 
/*     */ import java.util.Optional;
/*     */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*     */ import moze_intel.projecte.api.capabilities.item.IExtraFunction;
/*     */ import moze_intel.projecte.api.capabilities.item.IItemCharge;
/*     */ import moze_intel.projecte.api.capabilities.item.IModeChanger;
/*     */ import moze_intel.projecte.api.capabilities.item.IProjectileShooter;
/*     */ import moze_intel.projecte.gameObjs.items.armor.GemChest;
/*     */ import moze_intel.projecte.gameObjs.items.armor.GemHelmet;
/*     */ import moze_intel.projecte.handlers.InternalAbilities;
/*     */ import moze_intel.projecte.utils.PEKeybind;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.entity.EquipmentSlot;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ 
/*     */ public final class KeyPressPKT extends Record implements IPEPacket {
/*     */   private final PEKeybind key;
/*     */   
/*  25 */   public KeyPressPKT(PEKeybind key) { this.key = key; } public final String toString() { // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lmoze_intel/projecte/network/packets/to_server/KeyPressPKT;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #25	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*  25 */     //   0	7	0	this	Lmoze_intel/projecte/network/packets/to_server/KeyPressPKT; } public PEKeybind key() { return this.key; }
/*     */   public final int hashCode() { // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/network/packets/to_server/KeyPressPKT;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #25	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lmoze_intel/projecte/network/packets/to_server/KeyPressPKT; } public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lmoze_intel/projecte/network/packets/to_server/KeyPressPKT;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #25	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lmoze_intel/projecte/network/packets/to_server/KeyPressPKT;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   } public void handle(NetworkEvent.Context context) {
/*  29 */     ServerPlayer player = context.getSender();
/*  30 */     if (player == null || player.m_5833_()) {
/*     */       return;
/*     */     }
/*  33 */     if (this.key == PEKeybind.HELMET_TOGGLE) {
/*  34 */       ItemStack helm = player.m_6844_(EquipmentSlot.HEAD);
/*  35 */       if (!helm.m_41619_() && helm.m_41720_() instanceof GemHelmet)
/*  36 */         GemHelmet.toggleNightVision(helm, (Player)player); 
/*     */       return;
/*     */     } 
/*  39 */     if (this.key == PEKeybind.BOOTS_TOGGLE) {
/*  40 */       ItemStack boots = player.m_6844_(EquipmentSlot.FEET);
/*  41 */       if (!boots.m_41619_() && boots.m_41720_() instanceof GemFeet) {
/*  42 */         ((GemFeet)boots.m_41720_()).toggleStepAssist(boots, (Player)player);
/*     */       }
/*     */       return;
/*     */     } 
/*  46 */     Optional<InternalAbilities> cap = player.getCapability(InternalAbilities.CAPABILITY).resolve();
/*  47 */     if (cap.isEmpty()) {
/*     */       return;
/*     */     }
/*  50 */     InternalAbilities internalAbilities = cap.get();
/*  51 */     for (InteractionHand hand : InteractionHand.values()) {
/*  52 */       ItemStack stack = player.m_21120_(hand);
/*  53 */       switch (this.key) {
/*     */         case CHARGE:
/*  55 */           if (tryPerformCapability(stack, PECapabilities.CHARGE_ITEM_CAPABILITY, capability -> capability.changeCharge((Player)player, stack, hand)))
/*     */             return; 
/*  57 */           if (hand == InteractionHand.MAIN_HAND && isSafe(stack) && GemArmorBase.hasAnyPiece((Player)player)) {
/*  58 */             internalAbilities.setGemState(!internalAbilities.getGemState());
/*  59 */             PELang pELang = internalAbilities.getGemState() ? PELang.GEM_ACTIVATE : PELang.GEM_DEACTIVATE;
/*  60 */             player.m_213846_((Component)pELang.translate(new Object[0]));
/*     */             return;
/*     */           } 
/*     */           break;
/*     */         case EXTRA_FUNCTION:
/*  65 */           if (tryPerformCapability(stack, PECapabilities.EXTRA_FUNCTION_ITEM_CAPABILITY, capability -> capability.doExtraFunction(stack, (Player)player, hand)))
/*     */             return; 
/*  67 */           if (hand == InteractionHand.MAIN_HAND && isSafe(stack) && internalAbilities.getGemState()) {
/*  68 */             ItemStack chestplate = player.m_6844_(EquipmentSlot.CHEST);
/*  69 */             if (!chestplate.m_41619_()) { Item item = chestplate.m_41720_(); if (item instanceof GemChest) { GemChest chest = (GemChest)item; if (internalAbilities.getGemCooldown() == 0) {
/*  70 */                   chest.doExplode((Player)player);
/*  71 */                   internalAbilities.resetGemCooldown(); return;
/*     */                 }  }
/*     */                }
/*     */           
/*     */           }  break;
/*     */         case FIRE_PROJECTILE:
/*  77 */           if (!stack.m_41619_() && internalAbilities.getProjectileCooldown() == 0 && 
/*  78 */             tryPerformCapability(stack, PECapabilities.PROJECTILE_SHOOTER_ITEM_CAPABILITY, capability -> capability.shootProjectile((Player)player, stack, hand))) {
/*  79 */             PlayerHelper.swingItem((Player)player, hand);
/*  80 */             internalAbilities.resetProjectileCooldown();
/*     */           } 
/*  82 */           if (hand == InteractionHand.MAIN_HAND && isSafe(stack) && internalAbilities.getGemState()) {
/*  83 */             ItemStack helmet = player.m_6844_(EquipmentSlot.HEAD);
/*  84 */             if (!helmet.m_41619_()) { Item item = helmet.m_41720_(); if (item instanceof GemHelmet) { GemHelmet gemHelmet = (GemHelmet)item;
/*  85 */                 gemHelmet.doZap((Player)player); return; }
/*     */                }
/*     */           
/*     */           } 
/*     */           break;
/*     */         case MODE:
/*  91 */           if (tryPerformCapability(stack, PECapabilities.MODE_CHANGER_ITEM_CAPABILITY, capability -> capability.changeMode((Player)player, stack, hand))) {
/*     */             return;
/*     */           }
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static <CAPABILITY> boolean tryPerformCapability(ItemStack stack, Capability<CAPABILITY> capability, NonNullPredicate<CAPABILITY> perform) {
/* 100 */     return (!stack.m_41619_() && stack.getCapability(capability).filter(perform).isPresent());
/*     */   }
/*     */   
/*     */   private static boolean isSafe(ItemStack stack) {
/* 104 */     return (ProjectEConfig.server.misc.unsafeKeyBinds.get() || stack.m_41619_());
/*     */   }
/*     */ 
/*     */   
/*     */   public void encode(FriendlyByteBuf buffer) {
/* 109 */     buffer.m_130068_((Enum)this.key);
/*     */   }
/*     */   
/*     */   public static KeyPressPKT decode(FriendlyByteBuf buf) {
/* 113 */     return new KeyPressPKT((PEKeybind)buf.m_130066_(PEKeybind.class));
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\packets\to_server\KeyPressPKT.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */