/*    */ package moze_intel.projecte.gameObjs.gui;
/*    */ 
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public abstract class PEContainerScreen<T extends AbstractContainerMenu>
/*    */   extends AbstractContainerScreen<T> {
/*    */   public boolean switchingToJEI;
/*    */   
/*    */   public PEContainerScreen(T container, Inventory invPlayer, Component title) {
/* 15 */     super((AbstractContainerMenu)container, invPlayer, title);
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_88315_(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
/* 20 */     m_280273_(graphics);
/* 21 */     super.m_88315_(graphics, mouseX, mouseY, partialTicks);
/* 22 */     m_280072_(graphics, mouseX, mouseY);
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_7861_() {
/* 27 */     if (!this.switchingToJEI)
/*    */     {
/*    */ 
/*    */       
/* 31 */       super.m_7861_();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_7856_() {
/* 41 */     this.switchingToJEI = false;
/* 42 */     super.m_7856_();
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\gui\PEContainerScreen.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */