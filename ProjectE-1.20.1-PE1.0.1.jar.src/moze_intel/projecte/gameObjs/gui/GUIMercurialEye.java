/*    */ package moze_intel.projecte.gameObjs.gui;
/*    */ 
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.gameObjs.container.MercurialEyeContainer;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class GUIMercurialEye
/*    */   extends PEContainerScreen<MercurialEyeContainer> {
/* 13 */   private static final ResourceLocation texture = PECore.rl("textures/gui/mercurial_eye.png");
/*    */   public GUIMercurialEye(MercurialEyeContainer container, Inventory invPlayer, Component title) {
/* 15 */     super(container, invPlayer, title);
/* 16 */     this.f_97726_ = 171;
/* 17 */     this.f_97727_ = 134;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void m_7286_(@NotNull GuiGraphics graphics, float partialTicks, int x, int y) {
/* 22 */     graphics.m_280218_(texture, this.f_97735_, this.f_97736_, 0, 0, this.f_97726_, this.f_97727_);
/*    */   }
/*    */   
/*    */   protected void m_280003_(@NotNull GuiGraphics graphics, int x, int y) {}
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\gui\GUIMercurialEye.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */