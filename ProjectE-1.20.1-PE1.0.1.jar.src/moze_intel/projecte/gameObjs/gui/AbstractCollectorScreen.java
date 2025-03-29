/*     */ package moze_intel.projecte.gameObjs.gui;
/*     */ 
/*     */ import moze_intel.projecte.PECore;
/*     */ import moze_intel.projecte.gameObjs.container.CollectorMK1Container;
/*     */ import moze_intel.projecte.gameObjs.container.CollectorMK2Container;
/*     */ import moze_intel.projecte.gameObjs.container.CollectorMK3Container;
/*     */ import moze_intel.projecte.utils.Constants;
/*     */ import net.minecraft.client.gui.GuiGraphics;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public abstract class AbstractCollectorScreen<T extends CollectorMK1Container>
/*     */   extends PEContainerScreen<T> {
/*     */   public AbstractCollectorScreen(T container, Inventory invPlayer, Component title) {
/*  17 */     super(container, invPlayer, title);
/*     */   }
/*     */   
/*     */   protected abstract ResourceLocation getTexture();
/*     */   
/*     */   protected int getBonusXShift() {
/*  23 */     return 0;
/*     */   }
/*     */   
/*     */   protected int getTextureBonusXShift() {
/*  27 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_280003_(@NotNull GuiGraphics graphics, int x, int y) {
/*  33 */     graphics.m_280056_(this.f_96547_, Long.toString(((CollectorMK1Container)this.f_97732_).emc.get()), 60 + getBonusXShift(), 32, 4210752, false);
/*  34 */     long kleinCharge = ((CollectorMK1Container)this.f_97732_).kleinEmc.get();
/*  35 */     if (kleinCharge > 0L) {
/*  36 */       graphics.m_280056_(this.f_96547_, Constants.EMC_FORMATTER.format(kleinCharge), 60 + getBonusXShift(), 44, 4210752, false);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_7286_(@NotNull GuiGraphics graphics, float partialTicks, int x, int y) {
/*  42 */     graphics.m_280218_(getTexture(), this.f_97735_, this.f_97736_, 0, 0, this.f_97726_, this.f_97727_);
/*     */ 
/*     */     
/*  45 */     int progress = (int)(((CollectorMK1Container)this.f_97732_).sunLevel.m_6501_() * 12.0D / 16.0D);
/*  46 */     graphics.m_280218_(getTexture(), this.f_97735_ + 126 + getBonusXShift(), this.f_97736_ + 49 - progress, 177 + getTextureBonusXShift(), 13 - progress, 12, progress);
/*     */ 
/*     */     
/*  49 */     graphics.m_280218_(getTexture(), this.f_97735_ + 64 + getBonusXShift(), this.f_97736_ + 18, 0, 166, (int)(((CollectorMK1Container)this.f_97732_).emc.get() / ((CollectorMK1Container)this.f_97732_).collector.getMaximumEmc() * 48.0D), 10);
/*     */ 
/*     */     
/*  52 */     progress = (int)(((CollectorMK1Container)this.f_97732_).getKleinChargeProgress() * 48.0D);
/*  53 */     graphics.m_280218_(getTexture(), this.f_97735_ + 64 + getBonusXShift(), this.f_97736_ + 58, 0, 166, progress, 10);
/*     */ 
/*     */     
/*  56 */     progress = (int)(((CollectorMK1Container)this.f_97732_).getFuelProgress() * 24.0D);
/*  57 */     graphics.m_280218_(getTexture(), this.f_97735_ + 138 + getBonusXShift(), this.f_97736_ + 55 - progress, 176 + getTextureBonusXShift(), 38 - progress, 10, progress + 1);
/*     */   }
/*     */   
/*     */   public static class MK1
/*     */     extends AbstractCollectorScreen<CollectorMK1Container> {
/*     */     public MK1(CollectorMK1Container container, Inventory invPlayer, Component title) {
/*  63 */       super(container, invPlayer, title);
/*     */     }
/*     */ 
/*     */     
/*     */     protected ResourceLocation getTexture() {
/*  68 */       return PECore.rl("textures/gui/collector1.png");
/*     */     }
/*     */   }
/*     */   
/*     */   public static class MK2
/*     */     extends AbstractCollectorScreen<CollectorMK2Container> {
/*     */     public MK2(CollectorMK2Container container, Inventory invPlayer, Component title) {
/*  75 */       super(container, invPlayer, title);
/*  76 */       this.f_97726_ = 200;
/*  77 */       this.f_97727_ = 165;
/*     */     }
/*     */ 
/*     */     
/*     */     protected ResourceLocation getTexture() {
/*  82 */       return PECore.rl("textures/gui/collector2.png");
/*     */     }
/*     */ 
/*     */     
/*     */     protected int getBonusXShift() {
/*  87 */       return 16;
/*     */     }
/*     */ 
/*     */     
/*     */     protected int getTextureBonusXShift() {
/*  92 */       return 25;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class MK3
/*     */     extends AbstractCollectorScreen<CollectorMK3Container> {
/*     */     public MK3(CollectorMK3Container container, Inventory invPlayer, Component title) {
/*  99 */       super(container, invPlayer, title);
/* 100 */       this.f_97726_ = 218;
/* 101 */       this.f_97727_ = 165;
/*     */     }
/*     */ 
/*     */     
/*     */     protected ResourceLocation getTexture() {
/* 106 */       return PECore.rl("textures/gui/collector3.png");
/*     */     }
/*     */ 
/*     */     
/*     */     protected int getBonusXShift() {
/* 111 */       return 34;
/*     */     }
/*     */ 
/*     */     
/*     */     protected int getTextureBonusXShift() {
/* 116 */       return 43;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\gui\AbstractCollectorScreen.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */