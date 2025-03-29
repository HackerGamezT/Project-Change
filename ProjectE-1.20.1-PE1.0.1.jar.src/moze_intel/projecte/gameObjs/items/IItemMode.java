/*    */ package moze_intel.projecte.gameObjs.items;
/*    */ 
/*    */ import moze_intel.projecte.api.capabilities.item.IModeChanger;
/*    */ import moze_intel.projecte.utils.text.ILangEntry;
/*    */ import moze_intel.projecte.utils.text.PELang;
/*    */ import net.minecraft.ChatFormatting;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public interface IItemMode
/*    */   extends IModeChanger
/*    */ {
/*    */   ILangEntry[] getModeLangEntries();
/*    */   
/*    */   default byte getModeCount() {
/* 19 */     return (byte)(getModeLangEntries()).length;
/*    */   }
/*    */   
/*    */   default ILangEntry getModeLangEntry(ItemStack stack) {
/* 23 */     ILangEntry[] langEntries = getModeLangEntries();
/* 24 */     byte mode = getMode(stack);
/* 25 */     if (mode < 0 || mode >= langEntries.length) {
/* 26 */       return (ILangEntry)PELang.INVALID_MODE;
/*    */     }
/* 28 */     return langEntries[mode];
/*    */   }
/*    */ 
/*    */   
/*    */   default byte getMode(@NotNull ItemStack stack) {
/* 33 */     return stack.m_41782_() ? stack.m_41784_().m_128445_("Mode") : 0;
/*    */   }
/*    */ 
/*    */   
/*    */   default boolean changeMode(@NotNull Player player, @NotNull ItemStack stack, InteractionHand hand) {
/* 38 */     byte numModes = getModeCount();
/* 39 */     if (numModes < 2)
/*    */     {
/* 41 */       return false;
/*    */     }
/*    */     
/* 44 */     stack.m_41784_().m_128344_("Mode", (byte)((getMode(stack) + 1) % numModes));
/* 45 */     player.m_213846_((Component)getModeSwitchEntry().translate(new Object[] { getModeLangEntry(stack) }));
/* 46 */     return true;
/*    */   }
/*    */   
/*    */   default ILangEntry getModeSwitchEntry() {
/* 50 */     return (ILangEntry)PELang.MODE_SWITCH;
/*    */   }
/*    */   
/*    */   default Component getToolTip(ItemStack stack) {
/* 54 */     return (Component)PELang.CURRENT_MODE.translate(new Object[] { ChatFormatting.AQUA, getModeLangEntry(stack) });
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\IItemMode.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */