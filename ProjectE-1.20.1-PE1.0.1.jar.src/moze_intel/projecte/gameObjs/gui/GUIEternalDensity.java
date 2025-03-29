/*    */ package moze_intel.projecte.gameObjs.gui;
/*    */ 
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.gameObjs.container.EternalDensityContainer;
/*    */ import moze_intel.projecte.utils.text.PELang;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.client.gui.components.Button;
/*    */ import net.minecraft.client.gui.components.events.GuiEventListener;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class GUIEternalDensity extends PEContainerScreen<EternalDensityContainer> {
/* 15 */   private static final ResourceLocation texture = PECore.rl("textures/gui/eternal_density.png");
/*    */   
/*    */   public GUIEternalDensity(EternalDensityContainer container, Inventory inv, Component title) {
/* 18 */     super(container, inv, title);
/* 19 */     this.f_97726_ = 180;
/* 20 */     this.f_97727_ = 180;
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_7856_() {
/* 25 */     super.m_7856_();
/* 26 */     m_142416_((GuiEventListener)Button.m_253074_((Component)(((EternalDensityContainer)this.f_97732_).inventory.isWhitelistMode() ? PELang.WHITELIST : PELang.BLACKLIST).translate(new Object[0]), b -> {
/*    */             ((EternalDensityContainer)this.f_97732_).inventory.changeMode();
/*    */             b.m_93666_(((EternalDensityContainer)this.f_97732_).inventory.isWhitelistMode() ? (Component)PELang.WHITELIST.translate(new Object[0]) : (Component)PELang.BLACKLIST.translate(new Object[0]));
/* 29 */           }).m_252794_(this.f_97735_ + 62, this.f_97736_ + 4)
/* 30 */         .m_253046_(52, 20)
/* 31 */         .m_253136_());
/*    */   }
/*    */ 
/*    */   
/*    */   protected void m_7286_(@NotNull GuiGraphics graphics, float partialTicks, int x, int y) {
/* 36 */     graphics.m_280218_(texture, this.f_97735_, this.f_97736_, 0, 0, this.f_97726_, this.f_97727_);
/*    */   }
/*    */   
/*    */   protected void m_280003_(@NotNull GuiGraphics graphics, int x, int y) {}
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\gui\GUIEternalDensity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */