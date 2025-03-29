/*     */ package moze_intel.projecte.gameObjs.gui;
/*     */ 
/*     */ import java.math.BigInteger;
/*     */ import java.util.Locale;
/*     */ import moze_intel.projecte.PECore;
/*     */ import moze_intel.projecte.gameObjs.container.TransmutationContainer;
/*     */ import moze_intel.projecte.gameObjs.container.inventory.TransmutationInventory;
/*     */ import moze_intel.projecte.utils.Constants;
/*     */ import moze_intel.projecte.utils.TransmutationEMCFormatter;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.client.gui.GuiGraphics;
/*     */ import net.minecraft.client.gui.components.Button;
/*     */ import net.minecraft.client.gui.components.EditBox;
/*     */ import net.minecraft.client.gui.components.events.GuiEventListener;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class GUITransmutation
/*     */   extends PEContainerScreen<TransmutationContainer> {
/*  22 */   private static final ResourceLocation texture = PECore.rl("textures/gui/transmute.png");
/*     */   private final TransmutationInventory inv;
/*     */   private EditBox textBoxFilter;
/*     */   
/*     */   public GUITransmutation(TransmutationContainer container, Inventory invPlayer, Component title) {
/*  27 */     super(container, invPlayer, title);
/*  28 */     this.inv = container.transmutationInventory;
/*  29 */     this.f_97726_ = 228;
/*  30 */     this.f_97727_ = 196;
/*  31 */     this.f_97728_ = 6;
/*  32 */     this.f_97729_ = 8;
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_7856_() {
/*  37 */     super.m_7856_();
/*     */     
/*  39 */     this.textBoxFilter = (EditBox)m_7787_((GuiEventListener)new EditBox(this.f_96547_, this.f_97735_ + 88, this.f_97736_ + 8, 45, 10, (Component)Component.m_237119_()));
/*  40 */     this.textBoxFilter.m_94144_(this.inv.filter);
/*  41 */     this.textBoxFilter.m_94151_(this::updateFilter);
/*     */     
/*  43 */     m_142416_((GuiEventListener)Button.m_253074_((Component)Component.m_237113_("<"), b -> {
/*     */             if (this.inv.searchpage != 0) {
/*     */               this.inv.searchpage--;
/*     */             }
/*     */             this.inv.filter = this.textBoxFilter.m_94155_().toLowerCase(Locale.ROOT);
/*     */             this.inv.updateClientTargets();
/*  49 */           }).m_252794_(this.f_97735_ + 125, this.f_97736_ + 100)
/*  50 */         .m_253046_(14, 14)
/*  51 */         .m_253136_());
/*  52 */     m_142416_((GuiEventListener)Button.m_253074_((Component)Component.m_237113_(">"), b -> {
/*     */             if (this.inv.getKnowledgeSize() > 12) {
/*     */               this.inv.searchpage++;
/*     */             }
/*     */             this.inv.filter = this.textBoxFilter.m_94155_().toLowerCase(Locale.ROOT);
/*     */             this.inv.updateClientTargets();
/*  58 */           }).m_252794_(this.f_97735_ + 193, this.f_97736_ + 100)
/*  59 */         .m_253046_(14, 14)
/*  60 */         .m_253136_());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_7286_(@NotNull GuiGraphics graphics, float partialTicks, int mouseX, int mouseY) {
/*  65 */     graphics.m_280218_(texture, this.f_97735_, this.f_97736_, 0, 0, this.f_97726_, this.f_97727_);
/*  66 */     this.textBoxFilter.m_88315_(graphics, mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_280003_(@NotNull GuiGraphics graphics, int x, int y) {
/*  71 */     graphics.m_280614_(this.f_96547_, this.f_96539_, this.f_97728_, this.f_97729_, 4210752, false);
/*     */     
/*  73 */     BigInteger emcAmount = this.inv.getAvailableEmc();
/*  74 */     graphics.m_280614_(this.f_96547_, (Component)PELang.EMC_TOOLTIP.translate(new Object[] { "" }, ), 6, this.f_97727_ - 104, 4210752, false);
/*  75 */     Component emc = TransmutationEMCFormatter.formatEMC(emcAmount);
/*  76 */     graphics.m_280614_(this.f_96547_, emc, 6, this.f_97727_ - 94, 4210752, false);
/*     */     
/*  78 */     if (this.inv.learnFlag > 0) {
/*  79 */       graphics.m_280614_(this.f_96547_, (Component)PELang.TRANSMUTATION_LEARNED_1.translate(new Object[0]), 98, 30, 4210752, false);
/*  80 */       graphics.m_280614_(this.f_96547_, (Component)PELang.TRANSMUTATION_LEARNED_2.translate(new Object[0]), 99, 38, 4210752, false);
/*  81 */       graphics.m_280614_(this.f_96547_, (Component)PELang.TRANSMUTATION_LEARNED_3.translate(new Object[0]), 100, 46, 4210752, false);
/*  82 */       graphics.m_280614_(this.f_96547_, (Component)PELang.TRANSMUTATION_LEARNED_4.translate(new Object[0]), 101, 54, 4210752, false);
/*  83 */       graphics.m_280614_(this.f_96547_, (Component)PELang.TRANSMUTATION_LEARNED_5.translate(new Object[0]), 102, 62, 4210752, false);
/*  84 */       graphics.m_280614_(this.f_96547_, (Component)PELang.TRANSMUTATION_LEARNED_6.translate(new Object[0]), 103, 70, 4210752, false);
/*  85 */       graphics.m_280614_(this.f_96547_, (Component)PELang.TRANSMUTATION_LEARNED_7.translate(new Object[0]), 104, 78, 4210752, false);
/*  86 */       graphics.m_280614_(this.f_96547_, (Component)PELang.TRANSMUTATION_LEARNED_8.translate(new Object[0]), 107, 86, 4210752, false);
/*     */       
/*  88 */       this.inv.learnFlag--;
/*     */     } 
/*     */     
/*  91 */     if (this.inv.unlearnFlag > 0) {
/*  92 */       graphics.m_280614_(this.f_96547_, (Component)PELang.TRANSMUTATION_UNLEARNED_1.translate(new Object[0]), 97, 22, 4210752, false);
/*  93 */       graphics.m_280614_(this.f_96547_, (Component)PELang.TRANSMUTATION_UNLEARNED_2.translate(new Object[0]), 98, 30, 4210752, false);
/*  94 */       graphics.m_280614_(this.f_96547_, (Component)PELang.TRANSMUTATION_UNLEARNED_3.translate(new Object[0]), 99, 38, 4210752, false);
/*  95 */       graphics.m_280614_(this.f_96547_, (Component)PELang.TRANSMUTATION_UNLEARNED_4.translate(new Object[0]), 100, 46, 4210752, false);
/*  96 */       graphics.m_280614_(this.f_96547_, (Component)PELang.TRANSMUTATION_UNLEARNED_5.translate(new Object[0]), 101, 54, 4210752, false);
/*  97 */       graphics.m_280614_(this.f_96547_, (Component)PELang.TRANSMUTATION_UNLEARNED_6.translate(new Object[0]), 102, 62, 4210752, false);
/*  98 */       graphics.m_280614_(this.f_96547_, (Component)PELang.TRANSMUTATION_UNLEARNED_7.translate(new Object[0]), 103, 70, 4210752, false);
/*  99 */       graphics.m_280614_(this.f_96547_, (Component)PELang.TRANSMUTATION_UNLEARNED_8.translate(new Object[0]), 104, 78, 4210752, false);
/* 100 */       graphics.m_280614_(this.f_96547_, (Component)PELang.TRANSMUTATION_UNLEARNED_9.translate(new Object[0]), 107, 86, 4210752, false);
/*     */       
/* 102 */       this.inv.unlearnFlag--;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_181908_() {
/* 108 */     super.m_181908_();
/* 109 */     this.textBoxFilter.m_94120_();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_7933_(int keyCode, int scanCode, int modifiers) {
/* 114 */     if (this.textBoxFilter.m_93696_()) {
/*     */       
/* 116 */       if (keyCode == 256) {
/* 117 */         this.textBoxFilter.m_93692_(false);
/* 118 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 122 */       return this.textBoxFilter.m_7933_(keyCode, scanCode, modifiers);
/*     */     } 
/* 124 */     return super.m_7933_(keyCode, scanCode, modifiers);
/*     */   }
/*     */   
/*     */   private void updateFilter(String text) {
/* 128 */     String search = text.toLowerCase(Locale.ROOT);
/* 129 */     if (!this.inv.filter.equals(search)) {
/* 130 */       this.inv.filter = search;
/* 131 */       this.inv.searchpage = 0;
/* 132 */       this.inv.updateClientTargets();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_6375_(double x, double y, int mouseButton) {
/* 138 */     if (this.textBoxFilter.m_5953_(x, y)) {
/* 139 */       if (mouseButton == 1)
/*     */       {
/* 141 */         this.textBoxFilter.m_94144_("");
/*     */       }
/* 143 */     } else if (this.textBoxFilter.m_93696_() && (
/* 144 */       this.f_97734_ == null || (!this.f_97734_.m_6657_() && ((TransmutationContainer)this.f_97732_).m_142621_().m_41619_()))) {
/* 145 */       this.textBoxFilter.m_93692_(false);
/*     */     } 
/*     */     
/* 148 */     return super.m_6375_(x, y, mouseButton);
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_7861_() {
/* 153 */     super.m_7861_();
/* 154 */     this.inv.learnFlag = 0;
/* 155 */     this.inv.unlearnFlag = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_280072_(@NotNull GuiGraphics graphics, int mouseX, int mouseY) {
/* 160 */     BigInteger emcAmount = this.inv.getAvailableEmc();
/*     */     
/* 162 */     if (emcAmount.compareTo(Constants.MAX_EXACT_TRANSMUTATION_DISPLAY) < 0) {
/* 163 */       super.m_280072_(graphics, mouseX, mouseY);
/*     */       
/*     */       return;
/*     */     } 
/* 167 */     int emcLeft = this.f_97735_;
/* 168 */     int emcRight = emcLeft + 82;
/* 169 */     int emcTop = 95 + this.f_97736_;
/* 170 */     int emcBottom = emcTop + 15;
/*     */     
/* 172 */     if (mouseX > emcLeft && mouseX < emcRight && mouseY > emcTop && mouseY < emcBottom) {
/* 173 */       m_257404_((Component)PELang.EMC_TOOLTIP.translate(new Object[] { Constants.EMC_FORMATTER.format(emcAmount) }));
/*     */     } else {
/* 175 */       super.m_280072_(graphics, mouseX, mouseY);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\gui\GUITransmutation.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */