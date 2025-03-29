/*    */ package moze_intel.projecte.events;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
/*    */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*    */ import moze_intel.projecte.api.capabilities.item.IItemEmcHolder;
/*    */ import moze_intel.projecte.api.capabilities.item.IPedestalItem;
/*    */ import moze_intel.projecte.config.ProjectEConfig;
/*    */ import moze_intel.projecte.utils.Constants;
/*    */ import moze_intel.projecte.utils.EMCHelper;
/*    */ import moze_intel.projecte.utils.text.PELang;
/*    */ import net.minecraft.ChatFormatting;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.screens.Screen;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.tags.TagKey;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.event.entity.player.ItemTooltipEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ @EventBusSubscriber(modid = "projecte", value = {Dist.CLIENT})
/*    */ public class ToolTipEvent {
/*    */   @SubscribeEvent
/*    */   public static void tTipEvent(ItemTooltipEvent event) {
/* 30 */     ItemStack current = event.getItemStack();
/* 31 */     if (current.m_41619_()) {
/*    */       return;
/*    */     }
/* 34 */     LocalPlayer localPlayer = (Minecraft.m_91087_()).f_91074_;
/* 35 */     if (ProjectEConfig.client.pedestalToolTips.get()) {
/* 36 */       current.getCapability(PECapabilities.PEDESTAL_ITEM_CAPABILITY).ifPresent(pedestalItem -> {
/*    */             event.getToolTip().add(PELang.PEDESTAL_ON.translateColored(ChatFormatting.DARK_PURPLE, new Object[0]));
/*    */             
/*    */             List<Component> description = pedestalItem.getPedestalDescription();
/*    */             if (description.isEmpty()) {
/*    */               event.getToolTip().add(PELang.PEDESTAL_DISABLED.translateColored(ChatFormatting.RED, new Object[0]));
/*    */             } else {
/*    */               event.getToolTip().addAll(description);
/*    */             } 
/*    */           });
/*    */     }
/* 47 */     if (ProjectEConfig.client.tagToolTips.get()) {
/* 48 */       current.m_204131_().forEach(tag -> event.getToolTip().add(Component.m_237113_("#" + tag.f_203868_())));
/*    */     }
/*    */     
/* 51 */     if (ProjectEConfig.client.emcToolTips.get() && (!ProjectEConfig.client.shiftEmcToolTips.get() || Screen.m_96638_())) {
/* 52 */       long value = EMCHelper.getEmcValue(current);
/* 53 */       if (value > 0L) {
/* 54 */         event.getToolTip().add(EMCHelper.getEmcTextComponent(value, 1));
/* 55 */         if (current.m_41613_() > 1) {
/* 56 */           event.getToolTip().add(EMCHelper.getEmcTextComponent(value, current.m_41613_()));
/*    */         }
/* 58 */         if (localPlayer != null && (!ProjectEConfig.client.shiftLearnedToolTips.get() || Screen.m_96638_())) {
/* 59 */           if (((Boolean)localPlayer.getCapability(PECapabilities.KNOWLEDGE_CAPABILITY).map(k -> Boolean.valueOf(k.hasKnowledge(current))).orElse(Boolean.valueOf(false))).booleanValue()) {
/* 60 */             event.getToolTip().add(PELang.EMC_HAS_KNOWLEDGE.translateColored(ChatFormatting.YELLOW, new Object[0]));
/*    */           } else {
/* 62 */             event.getToolTip().add(PELang.EMC_NO_KNOWLEDGE.translateColored(ChatFormatting.RED, new Object[0]));
/*    */           } 
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 68 */     if (current.m_41782_()) {
/*    */       long value;
/* 70 */       CompoundTag tag = current.m_41784_();
/* 71 */       if (tag.m_128425_("StoredEMC", 4)) {
/* 72 */         value = tag.m_128454_("StoredEMC");
/*    */       } else {
/* 74 */         Optional<IItemEmcHolder> holderCapability = current.getCapability(PECapabilities.EMC_HOLDER_ITEM_CAPABILITY).resolve();
/* 75 */         if (holderCapability.isPresent()) {
/* 76 */           value = ((IItemEmcHolder)holderCapability.get()).getStoredEmc(current);
/*    */         } else {
/*    */           return;
/*    */         } 
/*    */       } 
/* 81 */       event.getToolTip().add(PELang.EMC_STORED.translateColored(ChatFormatting.YELLOW, new Object[] { ChatFormatting.WHITE, Constants.EMC_FORMATTER.format(value) }));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\events\ToolTipEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */