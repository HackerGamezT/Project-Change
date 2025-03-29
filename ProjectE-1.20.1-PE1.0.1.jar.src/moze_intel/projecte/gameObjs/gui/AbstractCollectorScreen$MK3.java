/*     */ package moze_intel.projecte.gameObjs.gui;
/*     */ 
/*     */ import moze_intel.projecte.PECore;
/*     */ import moze_intel.projecte.gameObjs.container.CollectorMK3Container;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MK3
/*     */   extends AbstractCollectorScreen<CollectorMK3Container>
/*     */ {
/*     */   public MK3(CollectorMK3Container container, Inventory invPlayer, Component title) {
/*  99 */     super(container, invPlayer, title);
/* 100 */     this.f_97726_ = 218;
/* 101 */     this.f_97727_ = 165;
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation getTexture() {
/* 106 */     return PECore.rl("textures/gui/collector3.png");
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getBonusXShift() {
/* 111 */     return 34;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getTextureBonusXShift() {
/* 116 */     return 43;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\gui\AbstractCollectorScreen$MK3.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */