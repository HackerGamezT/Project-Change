/*    */ package moze_intel.projecte.gameObjs.registration.impl;
/*    */ 
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Supplier;
/*    */ import java.util.function.UnaryOperator;
/*    */ import moze_intel.projecte.gameObjs.registration.WrappedDeferredRegister;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ 
/*    */ public class ItemDeferredRegister
/*    */   extends WrappedDeferredRegister<Item> {
/*    */   public ItemDeferredRegister(String modid) {
/* 13 */     super(ForgeRegistries.ITEMS, modid);
/*    */   }
/*    */   
/*    */   public ItemRegistryObject<Item> register(String name) {
/* 17 */     return register(name, Item::new);
/*    */   }
/*    */   
/*    */   public ItemRegistryObject<Item> registerFireImmune(String name) {
/* 21 */     return registerFireImmune(name, Item::new);
/*    */   }
/*    */   
/*    */   public <ITEM extends Item> ItemRegistryObject<ITEM> register(String name, Function<Item.Properties, ITEM> sup) {
/* 25 */     return register(name, sup, UnaryOperator.identity());
/*    */   }
/*    */   
/*    */   public <ITEM extends Item> ItemRegistryObject<ITEM> registerFireImmune(String name, Function<Item.Properties, ITEM> sup) {
/* 29 */     return register(name, sup, Item.Properties::m_41486_);
/*    */   }
/*    */   
/*    */   public <ITEM extends Item> ItemRegistryObject<ITEM> registerNoStack(String name, Function<Item.Properties, ITEM> sup) {
/* 33 */     return register(name, sup, properties -> properties.m_41487_(1));
/*    */   }
/*    */   
/*    */   public <ITEM extends Item> ItemRegistryObject<ITEM> registerNoStackFireImmune(String name, Function<Item.Properties, ITEM> sup) {
/* 37 */     return register(name, sup, properties -> properties.m_41487_(1).m_41486_());
/*    */   }
/*    */   
/*    */   public <ITEM extends Item> ItemRegistryObject<ITEM> register(String name, Function<Item.Properties, ITEM> sup, UnaryOperator<Item.Properties> propertyModifier) {
/* 41 */     return register(name, () -> (Item)sup.apply(propertyModifier.apply(new Item.Properties())));
/*    */   }
/*    */   
/*    */   public <ITEM extends Item> ItemRegistryObject<ITEM> register(String name, Supplier<? extends ITEM> sup) {
/* 45 */     return (ItemRegistryObject<ITEM>)register(name, sup, ItemRegistryObject::new);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registration\impl\ItemDeferredRegister.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */