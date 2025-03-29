/*    */ package moze_intel.projecte.gameObjs.gui;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.gameObjs.container.CondenserContainer;
/*    */ import moze_intel.projecte.gameObjs.container.CondenserMK2Container;
/*    */ import moze_intel.projecte.utils.Constants;
/*    */ import moze_intel.projecte.utils.TransmutationEMCFormatter;
/*    */ import moze_intel.projecte.utils.text.PELang;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.client.renderer.GameRenderer;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public abstract class AbstractCondenserScreen<T extends CondenserContainer>
/*    */   extends PEContainerScreen<T> {
/*    */   public AbstractCondenserScreen(T condenser, Inventory playerInventory, Component title) {
/* 20 */     super(condenser, playerInventory, title);
/* 21 */     this.f_97726_ = 255;
/* 22 */     this.f_97727_ = 233;
/*    */   }
/*    */ 
/*    */   
/*    */   protected abstract ResourceLocation getTexture();
/*    */   
/*    */   protected void m_7286_(@NotNull GuiGraphics graphics, float partialTicks, int x, int y) {
/* 29 */     RenderSystem.setShader(GameRenderer::m_172817_);
/* 30 */     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
/* 31 */     RenderSystem.setShaderTexture(0, getTexture());
/*    */     
/* 33 */     graphics.m_280218_(getTexture(), this.f_97735_, this.f_97736_, 0, 0, this.f_97726_, this.f_97727_);
/*    */     
/* 35 */     int progress = ((CondenserContainer)this.f_97732_).getProgressScaled();
/* 36 */     graphics.m_280218_(getTexture(), this.f_97735_ + 33, this.f_97736_ + 10, 0, 235, progress, 10);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void m_280003_(@NotNull GuiGraphics graphics, int x, int y) {
/* 42 */     long toDisplay = Math.min(((CondenserContainer)this.f_97732_).displayEmc.get(), ((CondenserContainer)this.f_97732_).requiredEmc.get());
/* 43 */     Component emc = TransmutationEMCFormatter.formatEMC(Long.valueOf(toDisplay));
/* 44 */     graphics.m_280614_(this.f_96547_, emc, 140, 10, 4210752, false);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void m_280072_(@NotNull GuiGraphics graphics, int mouseX, int mouseY) {
/* 49 */     long toDisplay = Math.min(((CondenserContainer)this.f_97732_).displayEmc.get(), ((CondenserContainer)this.f_97732_).requiredEmc.get());
/*    */     
/* 51 */     if (toDisplay < 1.0E12D) {
/* 52 */       super.m_280072_(graphics, mouseX, mouseY);
/*    */       
/*    */       return;
/*    */     } 
/* 56 */     int emcLeft = 140 + this.f_97735_;
/* 57 */     int emcRight = emcLeft + 110;
/* 58 */     int emcTop = 6 + this.f_97736_;
/* 59 */     int emcBottom = emcTop + 15;
/*    */     
/* 61 */     if (mouseX > emcLeft && mouseX < emcRight && mouseY > emcTop && mouseY < emcBottom) {
/* 62 */       m_257404_((Component)PELang.EMC_TOOLTIP.translate(new Object[] { Constants.EMC_FORMATTER.format(toDisplay) }));
/*    */     } else {
/* 64 */       super.m_280072_(graphics, mouseX, mouseY);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static class MK1
/*    */     extends AbstractCondenserScreen<CondenserContainer> {
/*    */     public MK1(CondenserContainer condenser, Inventory playerInventory, Component title) {
/* 71 */       super(condenser, playerInventory, title);
/*    */     }
/*    */ 
/*    */     
/*    */     protected ResourceLocation getTexture() {
/* 76 */       return PECore.rl("textures/gui/condenser.png");
/*    */     }
/*    */   }
/*    */   
/*    */   public static class MK2
/*    */     extends AbstractCondenserScreen<CondenserMK2Container> {
/*    */     public MK2(CondenserMK2Container condenser, Inventory playerInventory, Component title) {
/* 83 */       super(condenser, playerInventory, title);
/*    */     }
/*    */ 
/*    */     
/*    */     protected ResourceLocation getTexture() {
/* 88 */       return PECore.rl("textures/gui/condenser_mk2.png");
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\gui\AbstractCondenserScreen.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */