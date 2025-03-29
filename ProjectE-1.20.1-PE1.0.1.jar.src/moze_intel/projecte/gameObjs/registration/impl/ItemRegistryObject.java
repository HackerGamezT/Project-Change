/*    */ package moze_intel.projecte.gameObjs.registration.impl;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.registration.WrappedRegistryObject;
/*    */ import moze_intel.projecte.utils.RegistryUtils;
/*    */ import moze_intel.projecte.utils.text.IHasTranslationKey;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.level.ItemLike;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class ItemRegistryObject<ITEM extends Item>
/*    */   extends WrappedRegistryObject<ITEM> implements ItemLike, IHasTranslationKey {
/*    */   public ItemRegistryObject(RegistryObject<ITEM> registryObject) {
/* 15 */     super(registryObject);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ITEM m_5456_() {
/* 21 */     return (ITEM)get();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTranslationKey() {
/* 26 */     return ((Item)get()).m_5524_();
/*    */   }
/*    */   
/*    */   public ResourceLocation getRegistryName() {
/* 30 */     return RegistryUtils.getName((Item)get());
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registration\impl\ItemRegistryObject.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */