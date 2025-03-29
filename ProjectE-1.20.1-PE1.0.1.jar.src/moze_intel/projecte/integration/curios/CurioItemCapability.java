/*    */ package moze_intel.projecte.integration.curios;
/*    */ 
/*    */ import moze_intel.projecte.capability.BasicItemCapability;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import top.theillusivec4.curios.api.CuriosCapability;
/*    */ import top.theillusivec4.curios.api.SlotContext;
/*    */ import top.theillusivec4.curios.api.type.capability.ICurio;
/*    */ 
/*    */ public class CurioItemCapability
/*    */   extends BasicItemCapability<ICurio> implements ICurio {
/*    */   public Capability<ICurio> getCapability() {
/* 14 */     return CuriosCapability.ITEM;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getStack() {
/* 19 */     return super.getStack();
/*    */   }
/*    */ 
/*    */   
/*    */   public void curioTick(SlotContext context) {
/* 24 */     if (!context.cosmetic())
/* 25 */       getStack().m_41666_(context.entity().m_9236_(), (Entity)context.entity(), context.index(), false); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\curios\CurioItemCapability.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */