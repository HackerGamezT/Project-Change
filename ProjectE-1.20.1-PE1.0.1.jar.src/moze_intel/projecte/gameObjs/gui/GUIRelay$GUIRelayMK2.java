/*    */ package moze_intel.projecte.gameObjs.gui;
/*    */ 
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.gameObjs.container.RelayMK2Container;
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
/*    */ public class GUIRelayMK2
/*    */   extends GUIRelay<RelayMK2Container>
/*    */ {
/* 74 */   private static final ResourceLocation MK2_TEXTURE = PECore.rl("textures/gui/relay2.png");
/*    */   
/*    */   public GUIRelayMK2(RelayMK2Container container, Inventory invPlayer, Component title) {
/* 77 */     super(container, invPlayer, title, MK2_TEXTURE, 107, 25, 183, 86, 17, 1);
/* 78 */     this.f_97726_ = 193;
/* 79 */     this.f_97727_ = 182;
/* 80 */     this.f_97728_ = 28;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\gui\GUIRelay$GUIRelayMK2.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */