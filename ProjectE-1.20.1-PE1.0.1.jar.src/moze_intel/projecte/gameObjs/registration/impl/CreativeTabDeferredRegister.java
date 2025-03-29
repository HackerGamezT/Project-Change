/*    */ package moze_intel.projecte.gameObjs.registration.impl;
/*    */ import java.util.function.Consumer;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.UnaryOperator;
/*    */ import moze_intel.projecte.utils.text.ILangEntry;
/*    */ import net.minecraft.core.registries.Registries;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.item.CreativeModeTab;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.ItemLike;
/*    */ import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
/*    */ import net.minecraftforge.eventbus.api.IEventBus;
/*    */ 
/*    */ public class CreativeTabDeferredRegister extends WrappedDeferredRegister<CreativeModeTab> {
/*    */   private final Consumer<BuildCreativeModeTabContentsEvent> addToExistingTabs;
/*    */   private final String modid;
/*    */   
/*    */   public CreativeTabDeferredRegister(String modid, Consumer<BuildCreativeModeTabContentsEvent> addToExistingTabs) {
/* 19 */     super(Registries.f_279569_, modid);
/* 20 */     this.modid = modid;
/* 21 */     this.addToExistingTabs = addToExistingTabs;
/*    */   }
/*    */ 
/*    */   
/*    */   public void register(IEventBus bus) {
/* 26 */     super.register(bus);
/* 27 */     bus.addListener(this.addToExistingTabs);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CreativeTabRegistryObject registerMain(ILangEntry title, ItemLike icon, UnaryOperator<CreativeModeTab.Builder> operator) {
/* 34 */     return register(this.modid, title, icon, operator);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CreativeTabRegistryObject register(String name, ILangEntry title, ItemLike icon, UnaryOperator<CreativeModeTab.Builder> operator) {
/* 41 */     return (CreativeTabRegistryObject)register(name, () -> { CreativeModeTab.Builder builder = CreativeModeTab.builder().m_257941_((Component)title.translate(new Object[0])).m_257737_(()); return ((CreativeModeTab.Builder)operator.apply(builder)).m_257652_(); }CreativeTabRegistryObject::new);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registration\impl\CreativeTabDeferredRegister.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */