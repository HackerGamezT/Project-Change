/*    */ package moze_intel.projecte.gameObjs.gui;
/*    */ 
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.gameObjs.container.AlchBagContainer;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class AlchBagScreen
/*    */   extends PEContainerScreen<AlchBagContainer> {
/* 13 */   private static final ResourceLocation texture = PECore.rl("textures/gui/alchchest.png");
/*    */   
/*    */   public AlchBagScreen(AlchBagContainer container, Inventory invPlayer, Component title) {
/* 16 */     super(container, invPlayer, title);
/* 17 */     this.f_97726_ = 255;
/* 18 */     this.f_97727_ = 230;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void m_7286_(@NotNull GuiGraphics graphics, float partialTicks, int x, int y) {
/* 23 */     graphics.m_280218_(texture, this.f_97735_, this.f_97736_, 0, 0, this.f_97726_, this.f_97727_);
/*    */   }
/*    */   
/*    */   protected void m_280003_(@NotNull GuiGraphics graphics, int x, int y) {}
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\gui\AlchBagScreen.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */