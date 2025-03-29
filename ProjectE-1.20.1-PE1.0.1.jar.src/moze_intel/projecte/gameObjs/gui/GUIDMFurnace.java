/*    */ package moze_intel.projecte.gameObjs.gui;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.gameObjs.block_entities.DMFurnaceBlockEntity;
/*    */ import moze_intel.projecte.gameObjs.container.DMFurnaceContainer;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.client.renderer.GameRenderer;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class GUIDMFurnace
/*    */   extends PEContainerScreen<DMFurnaceContainer> {
/* 16 */   private static final ResourceLocation texture = PECore.rl("textures/gui/dmfurnace.png");
/*    */   private final DMFurnaceBlockEntity furnace;
/*    */   
/*    */   public GUIDMFurnace(DMFurnaceContainer container, Inventory invPlayer, Component title) {
/* 20 */     super(container, invPlayer, title);
/* 21 */     this.f_97726_ = 178;
/* 22 */     this.f_97727_ = 165;
/* 23 */     this.furnace = container.furnace;
/* 24 */     this.f_97728_ = 57;
/* 25 */     this.f_97730_ = 57;
/* 26 */     this.f_97731_ = this.f_97727_ - 94;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void m_7286_(@NotNull GuiGraphics graphics, float partialTicks, int x, int y) {
/* 31 */     RenderSystem.setShader(GameRenderer::m_172817_);
/* 32 */     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
/* 33 */     RenderSystem.setShaderTexture(0, texture);
/*    */     
/* 35 */     graphics.m_280218_(texture, this.f_97735_, this.f_97736_, 0, 0, this.f_97726_, this.f_97727_);
/*    */ 
/*    */     
/* 38 */     if (this.furnace.isBurning()) {
/* 39 */       int i = this.furnace.getBurnTimeRemainingScaled(12);
/* 40 */       graphics.m_280218_(texture, this.f_97735_ + 49, this.f_97736_ + 36 + 12 - i, 179, 12 - i, 14, i + 2);
/*    */     } 
/* 42 */     int progress = this.furnace.getCookProgressScaled(24);
/* 43 */     graphics.m_280218_(texture, this.f_97735_ + 73, this.f_97736_ + 34, 179, 14, progress + 1, 16);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\gui\GUIDMFurnace.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */