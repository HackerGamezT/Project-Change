/*    */ package moze_intel.projecte.gameObjs.gui;
/*    */ 
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.gameObjs.container.CollectorMK2Container;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MK2
/*    */   extends AbstractCollectorScreen<CollectorMK2Container>
/*    */ {
/*    */   public MK2(CollectorMK2Container container, Inventory invPlayer, Component title) {
/* 75 */     super(container, invPlayer, title);
/* 76 */     this.f_97726_ = 200;
/* 77 */     this.f_97727_ = 165;
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation getTexture() {
/* 82 */     return PECore.rl("textures/gui/collector2.png");
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getBonusXShift() {
/* 87 */     return 16;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getTextureBonusXShift() {
/* 92 */     return 25;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\gui\AbstractCollectorScreen$MK2.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */