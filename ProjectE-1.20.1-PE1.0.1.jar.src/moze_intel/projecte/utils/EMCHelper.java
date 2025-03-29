/*     */ package moze_intel.projecte.utils;
/*     */ 
/*     */ import java.math.BigInteger;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import moze_intel.projecte.api.ItemInfo;
/*     */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*     */ import moze_intel.projecte.api.capabilities.block_entity.IEmcStorage;
/*     */ import moze_intel.projecte.api.capabilities.item.IItemEmcHolder;
/*     */ import moze_intel.projecte.config.ProjectEConfig;
/*     */ import moze_intel.projecte.emc.FuelMapper;
/*     */ import moze_intel.projecte.emc.nbt.NBTManager;
/*     */ import moze_intel.projecte.gameObjs.items.KleinStar;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraftforge.common.capabilities.ForgeCapabilities;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import org.jetbrains.annotations.NotNull;
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
/*     */ public final class EMCHelper
/*     */ {
/*     */   public static long consumePlayerFuel(Player player, long minFuel) {
/*  39 */     if (player.m_7500_() || minFuel == 0L) {
/*  40 */       return minFuel;
/*     */     }
/*  42 */     IItemHandler curios = PlayerHelper.getCurios(player);
/*  43 */     if (curios != null) {
/*  44 */       for (int i = 0; i < curios.getSlots(); i++) {
/*  45 */         long actualExtracted = tryExtract(curios.getStackInSlot(i), minFuel);
/*  46 */         if (actualExtracted > 0L) {
/*  47 */           player.f_36096_.m_38946_();
/*  48 */           return actualExtracted;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*  53 */     ItemStack offhand = player.m_21206_();
/*     */     
/*  55 */     if (!offhand.m_41619_()) {
/*  56 */       long actualExtracted = tryExtract(offhand, minFuel);
/*  57 */       if (actualExtracted > 0L) {
/*  58 */         player.f_36096_.m_38946_();
/*  59 */         return actualExtracted;
/*     */       } 
/*     */     } 
/*     */     
/*  63 */     Optional<IItemHandler> itemHandlerCap = player.getCapability(ForgeCapabilities.ITEM_HANDLER).resolve();
/*  64 */     if (itemHandlerCap.isPresent()) {
/*     */       
/*  66 */       IItemHandler inv = itemHandlerCap.get();
/*  67 */       Map<Integer, Integer> map = new LinkedHashMap<>();
/*  68 */       boolean metRequirement = false;
/*  69 */       long emcConsumed = 0L;
/*  70 */       for (int i = 0; i < inv.getSlots(); i++) {
/*  71 */         ItemStack stack = inv.getStackInSlot(i);
/*  72 */         if (!stack.m_41619_()) {
/*     */ 
/*     */           
/*  75 */           long actualExtracted = tryExtract(stack, minFuel);
/*  76 */           if (actualExtracted > 0L) {
/*  77 */             player.f_36096_.m_38946_();
/*  78 */             return actualExtracted;
/*  79 */           }  if (!metRequirement && 
/*  80 */             FuelMapper.isStackFuel(stack)) {
/*  81 */             long emc = getEmcValue(stack);
/*  82 */             int toRemove = (int)Math.ceil((minFuel - emcConsumed) / emc);
/*     */             
/*  84 */             if (stack.m_41613_() >= toRemove) {
/*  85 */               map.put(Integer.valueOf(i), Integer.valueOf(toRemove));
/*  86 */               emcConsumed += emc * toRemove;
/*  87 */               metRequirement = true;
/*     */             } else {
/*  89 */               map.put(Integer.valueOf(i), Integer.valueOf(stack.m_41613_()));
/*  90 */               emcConsumed += emc * stack.m_41613_();
/*  91 */               if (emcConsumed >= minFuel) {
/*  92 */                 metRequirement = true;
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*  98 */       if (metRequirement) {
/*  99 */         for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
/* 100 */           inv.extractItem(((Integer)entry.getKey()).intValue(), ((Integer)entry.getValue()).intValue(), false);
/*     */         }
/* 102 */         player.f_36096_.m_38946_();
/* 103 */         return emcConsumed;
/*     */       } 
/*     */     } 
/* 106 */     return -1L;
/*     */   }
/*     */   
/*     */   private static long tryExtract(@NotNull ItemStack stack, long minFuel) {
/* 110 */     if (stack.m_41619_()) {
/* 111 */       return 0L;
/*     */     }
/* 113 */     Optional<IItemEmcHolder> holderCapability = stack.getCapability(PECapabilities.EMC_HOLDER_ITEM_CAPABILITY).resolve();
/* 114 */     if (holderCapability.isPresent()) {
/* 115 */       IItemEmcHolder emcHolder = holderCapability.get();
/* 116 */       long simulatedExtraction = emcHolder.extractEmc(stack, minFuel, IEmcStorage.EmcAction.SIMULATE);
/* 117 */       if (simulatedExtraction == minFuel) {
/* 118 */         return emcHolder.extractEmc(stack, simulatedExtraction, IEmcStorage.EmcAction.EXECUTE);
/*     */       }
/*     */     } 
/* 121 */     return 0L;
/*     */   }
/*     */   
/*     */   public static boolean doesItemHaveEmc(ItemInfo info) {
/* 125 */     return (getEmcValue(info) > 0L);
/*     */   }
/*     */   
/*     */   public static boolean doesItemHaveEmc(ItemStack stack) {
/* 129 */     return (getEmcValue(stack) > 0L);
/*     */   }
/*     */   
/*     */   public static boolean doesItemHaveEmc(ItemLike item) {
/* 133 */     return (getEmcValue(item) > 0L);
/*     */   }
/*     */ 
/*     */   
/*     */   public static long getEmcValue(ItemLike item) {
/* 138 */     return (item == null) ? 0L : getEmcValue(ItemInfo.fromItem(item));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getEmcValue(ItemStack stack) {
/* 146 */     return stack.m_41619_() ? 0L : getEmcValue(ItemInfo.fromStack(stack));
/*     */   }
/*     */ 
/*     */   
/*     */   public static long getEmcValue(ItemInfo info) {
/* 151 */     return NBTManager.getEmcValue(info);
/*     */   }
/*     */ 
/*     */   
/*     */   public static long getEmcSellValue(ItemStack stack) {
/* 156 */     return stack.m_41619_() ? 0L : getEmcSellValue(ItemInfo.fromStack(stack));
/*     */   }
/*     */ 
/*     */   
/*     */   public static long getEmcSellValue(ItemInfo info) {
/* 161 */     return getEmcSellValue(getEmcValue(info));
/*     */   }
/*     */ 
/*     */   
/*     */   public static long getEmcSellValue(long originalValue) {
/* 166 */     if (originalValue == 0L) {
/* 167 */       return 0L;
/*     */     }
/* 169 */     long emc = (long)Math.floor(originalValue * ProjectEConfig.server.difficulty.covalenceLoss.get());
/* 170 */     if (emc < 1L) {
/* 171 */       if (ProjectEConfig.server.difficulty.covalenceLossRounding.get()) {
/* 172 */         emc = 1L;
/*     */       } else {
/* 174 */         emc = 0L;
/*     */       } 
/*     */     }
/* 177 */     return emc;
/*     */   } public static Component getEmcTextComponent(long emc, int stackSize) {
/*     */     PELang pELang;
/*     */     String value, sell;
/* 181 */     if (ProjectEConfig.server.difficulty.covalenceLoss.get() == 1.0D) {
/*     */       PELang pELang1;
/*     */       String str;
/* 184 */       if (stackSize > 1) {
/* 185 */         pELang1 = PELang.EMC_STACK_TOOLTIP;
/* 186 */         str = Constants.EMC_FORMATTER.format(BigInteger.valueOf(emc).multiply(BigInteger.valueOf(stackSize)));
/*     */       } else {
/* 188 */         pELang1 = PELang.EMC_TOOLTIP;
/* 189 */         str = Constants.EMC_FORMATTER.format(emc);
/*     */       } 
/* 191 */       return (Component)pELang1.translateColored(ChatFormatting.YELLOW, new Object[] { ChatFormatting.WHITE, str });
/*     */     } 
/*     */     
/* 194 */     long emcSellValue = getEmcSellValue(emc);
/*     */ 
/*     */ 
/*     */     
/* 198 */     if (stackSize > 1) {
/* 199 */       pELang = PELang.EMC_STACK_TOOLTIP_WITH_SELL;
/* 200 */       BigInteger bigIntStack = BigInteger.valueOf(stackSize);
/* 201 */       value = Constants.EMC_FORMATTER.format(BigInteger.valueOf(emc).multiply(bigIntStack));
/* 202 */       sell = Constants.EMC_FORMATTER.format(BigInteger.valueOf(emcSellValue).multiply(bigIntStack));
/*     */     } else {
/* 204 */       pELang = PELang.EMC_TOOLTIP_WITH_SELL;
/* 205 */       value = Constants.EMC_FORMATTER.format(emc);
/* 206 */       sell = Constants.EMC_FORMATTER.format(emcSellValue);
/*     */     } 
/* 208 */     return (Component)pELang.translateColored(ChatFormatting.YELLOW, new Object[] { ChatFormatting.WHITE, value, ChatFormatting.BLUE, sell });
/*     */   }
/*     */ 
/*     */   
/*     */   public static long getKleinStarMaxEmc(ItemStack stack) {
/* 213 */     Item item = stack.m_41720_(); if (item instanceof KleinStar) { KleinStar star = (KleinStar)item;
/* 214 */       return Constants.MAX_KLEIN_EMC[star.tier.ordinal()]; }
/*     */     
/* 216 */     return Constants.MAX_KLEIN_EMC[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public static long getEMCPerDurability(ItemStack stack) {
/* 221 */     if (stack.m_41619_())
/* 222 */       return 0L; 
/* 223 */     if (stack.m_41763_()) {
/* 224 */       ItemStack stackCopy = stack.m_41777_();
/* 225 */       stackCopy.m_41721_(0);
/* 226 */       long emc = (long)Math.ceil(getEmcValue(stackCopy) / stack.m_41776_());
/* 227 */       return Math.max(emc, 1L);
/*     */     } 
/* 229 */     return 1L;
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
/*     */   public static long removeFractionalEMC(ItemStack stack, double amount) {
/* 242 */     CompoundTag nbt = stack.m_41784_();
/* 243 */     double unprocessedEMC = nbt.m_128459_("UnprocessedEMC");
/* 244 */     unprocessedEMC += amount;
/* 245 */     long toRemove = (long)unprocessedEMC;
/* 246 */     unprocessedEMC -= toRemove;
/* 247 */     nbt.m_128347_("UnprocessedEMC", unprocessedEMC);
/* 248 */     return toRemove;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\project\\utils\EMCHelper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */