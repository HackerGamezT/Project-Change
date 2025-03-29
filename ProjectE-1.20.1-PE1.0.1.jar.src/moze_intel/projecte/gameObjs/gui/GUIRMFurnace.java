/*    */ package moze_intel.projecte.gameObjs.gui;
/*    */ 
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.gameObjs.block_entities.RMFurnaceBlockEntity;
/*    */ import moze_intel.projecte.gameObjs.container.RMFurnaceContainer;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class GUIRMFurnace
/*    */   extends PEContainerScreen<RMFurnaceContainer> {
/* 14 */   private static final ResourceLocation texture = PECore.rl("textures/gui/rmfurnace.png");
/*    */   private final RMFurnaceBlockEntity furnace;
/*    */   
/*    */   public GUIRMFurnace(RMFurnaceContainer container, Inventory invPlayer, Component title) {
/* 18 */     super(container, invPlayer, title);
/* 19 */     this.f_97726_ = 209;
/* 20 */     this.f_97727_ = 165;
/* 21 */     this.furnace = (RMFurnaceBlockEntity)container.furnace;
/* 22 */     this.f_97728_ = 76;
/* 23 */     this.f_97730_ = 76;
/* 24 */     this.f_97731_ = this.f_97727_ - 94;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void m_7286_(@NotNull GuiGraphics graphics, float partialTicks, int x, int y) {
/* 29 */     graphics.m_280218_(texture, this.f_97735_, this.f_97736_, 0, 0, this.f_97726_, this.f_97727_);
/*    */ 
/*    */     
/* 32 */     if (this.furnace.isBurning()) {
/* 33 */       int i = this.furnace.getBurnTimeRemainingScaled(12);
/* 34 */       graphics.m_280218_(texture, this.f_97735_ + 66, this.f_97736_ + 38 + 10 - i, 210, 10 - i, 21, i + 2);
/*    */     } 
/* 36 */     int progress = this.furnace.getCookProgressScaled(24);
/* 37 */     graphics.m_280218_(texture, this.f_97735_ + 88, this.f_97736_ + 35, 210, 14, progress, 17);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\gui\GUIRMFurnace.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */