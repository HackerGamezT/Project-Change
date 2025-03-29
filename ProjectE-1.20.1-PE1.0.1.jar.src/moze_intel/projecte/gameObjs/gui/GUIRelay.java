/*    */ package moze_intel.projecte.gameObjs.gui;
/*    */ 
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.gameObjs.container.RelayMK1Container;
/*    */ import moze_intel.projecte.gameObjs.container.RelayMK2Container;
/*    */ import moze_intel.projecte.gameObjs.container.RelayMK3Container;
/*    */ import moze_intel.projecte.utils.Constants;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class GUIRelay<CONTAINER extends RelayMK1Container>
/*    */   extends PEContainerScreen<CONTAINER>
/*    */ {
/*    */   private final ResourceLocation texture;
/*    */   private final int emcX;
/*    */   private final int emcY;
/*    */   private final int vOffset;
/*    */   private final int emcBarShift;
/*    */   private final int shiftX;
/*    */   private final int shiftY;
/*    */   
/*    */   protected GUIRelay(CONTAINER container, Inventory invPlayer, Component title, ResourceLocation texture, int emcX, int emcY, int vOffset, int emcBarShift, int shiftX, int shiftY) {
/* 26 */     super(container, invPlayer, title);
/* 27 */     this.texture = texture;
/* 28 */     this.emcX = emcX;
/* 29 */     this.emcY = emcY;
/* 30 */     this.vOffset = vOffset;
/* 31 */     this.emcBarShift = emcBarShift;
/* 32 */     this.shiftX = shiftX;
/* 33 */     this.shiftY = shiftY;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void m_280003_(@NotNull GuiGraphics graphics, int x, int y) {
/* 38 */     graphics.m_280614_(this.f_96547_, this.f_96539_, this.f_97728_, this.f_97729_, 4210752, false);
/*    */     
/* 40 */     graphics.m_280056_(this.f_96547_, Constants.EMC_FORMATTER.format(((RelayMK1Container)this.f_97732_).emc.get()), this.emcX, this.emcY, 4210752, false);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void m_7286_(@NotNull GuiGraphics graphics, float partialTicks, int x, int y) {
/* 45 */     graphics.m_280218_(this.texture, this.f_97735_, this.f_97736_, 0, 0, this.f_97726_, this.f_97727_);
/*    */ 
/*    */     
/* 48 */     int progress = (int)(((RelayMK1Container)this.f_97732_).emc.get() / ((RelayMK1Container)this.f_97732_).relay.getMaximumEmc() * 102.0D);
/* 49 */     graphics.m_280218_(this.texture, this.f_97735_ + this.emcBarShift, this.f_97736_ + 6, 30, this.vOffset, progress, 10);
/*    */ 
/*    */     
/* 52 */     progress = (int)(((RelayMK1Container)this.f_97732_).getKleinChargeProgress() * 30.0D);
/* 53 */     graphics.m_280218_(this.texture, this.f_97735_ + 116 + this.shiftX, this.f_97736_ + 67 + this.shiftY, 0, this.vOffset, progress, 10);
/*    */ 
/*    */     
/* 56 */     progress = (int)(((RelayMK1Container)this.f_97732_).getInputBurnProgress() * 30.0D);
/* 57 */     graphics.m_280218_(this.texture, this.f_97735_ + 64 + this.shiftX, this.f_97736_ + 67 + this.shiftY, 0, this.vOffset, progress, 10);
/*    */   }
/*    */   
/*    */   public static class GUIRelayMK1
/*    */     extends GUIRelay<RelayMK1Container> {
/* 62 */     private static final ResourceLocation MK1_TEXTURE = PECore.rl("textures/gui/relay1.png");
/*    */     
/*    */     public GUIRelayMK1(RelayMK1Container container, Inventory invPlayer, Component title) {
/* 65 */       super(container, invPlayer, title, MK1_TEXTURE, 88, 24, 177, 64, 0, 0);
/* 66 */       this.f_97726_ = 175;
/* 67 */       this.f_97727_ = 176;
/* 68 */       this.f_97728_ = 10;
/*    */     }
/*    */   }
/*    */   
/*    */   public static class GUIRelayMK2
/*    */     extends GUIRelay<RelayMK2Container> {
/* 74 */     private static final ResourceLocation MK2_TEXTURE = PECore.rl("textures/gui/relay2.png");
/*    */     
/*    */     public GUIRelayMK2(RelayMK2Container container, Inventory invPlayer, Component title) {
/* 77 */       super(container, invPlayer, title, MK2_TEXTURE, 107, 25, 183, 86, 17, 1);
/* 78 */       this.f_97726_ = 193;
/* 79 */       this.f_97727_ = 182;
/* 80 */       this.f_97728_ = 28;
/*    */     }
/*    */   }
/*    */   
/*    */   public static class GUIRelayMK3
/*    */     extends GUIRelay<RelayMK3Container> {
/* 86 */     private static final ResourceLocation MK3_TEXTURE = PECore.rl("textures/gui/relay3.png");
/*    */     
/*    */     public GUIRelayMK3(RelayMK3Container container, Inventory invPlayer, Component title) {
/* 89 */       super(container, invPlayer, title, MK3_TEXTURE, 125, 39, 195, 105, 37, 15);
/* 90 */       this.f_97726_ = 212;
/* 91 */       this.f_97727_ = 194;
/* 92 */       this.f_97728_ = 38;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\gui\GUIRelay.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */