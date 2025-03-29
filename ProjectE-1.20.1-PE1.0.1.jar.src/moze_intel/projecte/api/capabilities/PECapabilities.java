/*    */ package moze_intel.projecte.api.capabilities;
/*    */ 
/*    */ import moze_intel.projecte.api.capabilities.block_entity.IEmcStorage;
/*    */ import moze_intel.projecte.api.capabilities.item.IAlchBagItem;
/*    */ import moze_intel.projecte.api.capabilities.item.IAlchChestItem;
/*    */ import moze_intel.projecte.api.capabilities.item.IExtraFunction;
/*    */ import moze_intel.projecte.api.capabilities.item.IItemCharge;
/*    */ import moze_intel.projecte.api.capabilities.item.IItemEmcHolder;
/*    */ import moze_intel.projecte.api.capabilities.item.IModeChanger;
/*    */ import moze_intel.projecte.api.capabilities.item.IPedestalItem;
/*    */ import moze_intel.projecte.api.capabilities.item.IProjectileShooter;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.CapabilityManager;
/*    */ import net.minecraftforge.common.capabilities.CapabilityToken;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PECapabilities
/*    */ {
/* 24 */   public static final Capability<IEmcStorage> EMC_STORAGE_CAPABILITY = CapabilityManager.get(new CapabilityToken<IEmcStorage>()
/*    */       {
/*    */       
/*    */       });
/*    */   
/* 29 */   public static final Capability<IAlchBagProvider> ALCH_BAG_CAPABILITY = CapabilityManager.get(new CapabilityToken<IAlchBagProvider>()
/*    */       {
/*    */       
/*    */       });
/*    */   
/* 34 */   public static final Capability<IKnowledgeProvider> KNOWLEDGE_CAPABILITY = CapabilityManager.get(new CapabilityToken<IKnowledgeProvider>()
/*    */       {
/*    */       
/*    */       });
/*    */   
/* 39 */   public static final Capability<IAlchBagItem> ALCH_BAG_ITEM_CAPABILITY = CapabilityManager.get(new CapabilityToken<IAlchBagItem>()
/*    */       {
/*    */       
/*    */       });
/*    */   
/* 44 */   public static final Capability<IAlchChestItem> ALCH_CHEST_ITEM_CAPABILITY = CapabilityManager.get(new CapabilityToken<IAlchChestItem>()
/*    */       {
/*    */       
/*    */       });
/*    */   
/* 49 */   public static final Capability<IExtraFunction> EXTRA_FUNCTION_ITEM_CAPABILITY = CapabilityManager.get(new CapabilityToken<IExtraFunction>()
/*    */       {
/*    */       
/*    */       });
/*    */   
/* 54 */   public static final Capability<IItemCharge> CHARGE_ITEM_CAPABILITY = CapabilityManager.get(new CapabilityToken<IItemCharge>()
/*    */       {
/*    */       
/*    */       });
/*    */   
/* 59 */   public static final Capability<IItemEmcHolder> EMC_HOLDER_ITEM_CAPABILITY = CapabilityManager.get(new CapabilityToken<IItemEmcHolder>()
/*    */       {
/*    */       
/*    */       });
/*    */   
/* 64 */   public static final Capability<IModeChanger> MODE_CHANGER_ITEM_CAPABILITY = CapabilityManager.get(new CapabilityToken<IModeChanger>()
/*    */       {
/*    */       
/*    */       });
/*    */   
/* 69 */   public static final Capability<IPedestalItem> PEDESTAL_ITEM_CAPABILITY = CapabilityManager.get(new CapabilityToken<IPedestalItem>()
/*    */       {
/*    */       
/*    */       });
/*    */   
/* 74 */   public static final Capability<IProjectileShooter> PROJECTILE_SHOOTER_ITEM_CAPABILITY = CapabilityManager.get(new CapabilityToken<IProjectileShooter>() {
/*    */       
/*    */       });
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\capabilities\PECapabilities.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */