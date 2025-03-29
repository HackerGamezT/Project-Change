/*    */ package moze_intel.projecte.gameObjs.gui;
/*    */ 
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.gameObjs.container.RelayMK1Container;
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
/*    */ public class GUIRelayMK1
/*    */   extends GUIRelay<RelayMK1Container>
/*    */ {
/* 62 */   private static final ResourceLocation MK1_TEXTURE = PECore.rl("textures/gui/relay1.png");
/*    */   
/*    */   public GUIRelayMK1(RelayMK1Container container, Inventory invPlayer, Component title) {
/* 65 */     super(container, invPlayer, title, MK1_TEXTURE, 88, 24, 177, 64, 0, 0);
/* 66 */     this.f_97726_ = 175;
/* 67 */     this.f_97727_ = 176;
/* 68 */     this.f_97728_ = 10;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\gui\GUIRelay$GUIRelayMK1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */