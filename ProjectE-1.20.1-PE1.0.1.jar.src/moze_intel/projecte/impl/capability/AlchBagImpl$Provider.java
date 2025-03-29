/*    */ package moze_intel.projecte.impl.capability;
/*    */ 
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.api.capabilities.IAlchBagProvider;
/*    */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*    */ import moze_intel.projecte.capability.managing.SerializableCapabilityResolver;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.util.INBTSerializable;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ public class Provider
/*    */   extends SerializableCapabilityResolver<IAlchBagProvider>
/*    */ {
/* 75 */   public static final ResourceLocation NAME = PECore.rl("alch_bags");
/*    */   
/*    */   public Provider() {
/* 78 */     super((INBTSerializable)AlchBagImpl.getDefault());
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Capability<IAlchBagProvider> getMatchingCapability() {
/* 84 */     return PECapabilities.ALCH_BAG_CAPABILITY;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\impl\capability\AlchBagImpl$Provider.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */