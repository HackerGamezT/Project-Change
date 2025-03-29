/*    */ package moze_intel.projecte.gameObjs.gui;
/*    */ 
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.gameObjs.container.RelayMK3Container;
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
/*    */ public class GUIRelayMK3
/*    */   extends GUIRelay<RelayMK3Container>
/*    */ {
/* 86 */   private static final ResourceLocation MK3_TEXTURE = PECore.rl("textures/gui/relay3.png");
/*    */   
/*    */   public GUIRelayMK3(RelayMK3Container container, Inventory invPlayer, Component title) {
/* 89 */     super(container, invPlayer, title, MK3_TEXTURE, 125, 39, 195, 105, 37, 15);
/* 90 */     this.f_97726_ = 212;
/* 91 */     this.f_97727_ = 194;
/* 92 */     this.f_97728_ = 38;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\gui\GUIRelay$GUIRelayMK3.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */