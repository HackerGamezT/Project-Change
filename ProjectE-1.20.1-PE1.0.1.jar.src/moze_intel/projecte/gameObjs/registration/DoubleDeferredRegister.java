/*    */ package moze_intel.projecte.gameObjs.registration;
/*    */ 
/*    */ import java.util.function.BiFunction;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraftforge.eventbus.api.IEventBus;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.IForgeRegistry;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class DoubleDeferredRegister<PRIMARY, SECONDARY>
/*    */ {
/*    */   @NotNull
/*    */   protected final DeferredRegister<PRIMARY> primaryRegister;
/*    */   @NotNull
/*    */   protected final DeferredRegister<SECONDARY> secondaryRegister;
/*    */   
/*    */   public DoubleDeferredRegister(IForgeRegistry<PRIMARY> primaryRegistry, IForgeRegistry<SECONDARY> secondaryRegistry, String modid) {
/* 20 */     this.primaryRegister = DeferredRegister.create(primaryRegistry, modid);
/* 21 */     this.secondaryRegister = DeferredRegister.create(secondaryRegistry, modid);
/*    */   }
/*    */ 
/*    */   
/*    */   public <P extends PRIMARY, S extends SECONDARY, W extends DoubleWrappedRegistryObject<P, S>> W register(String name, Supplier<? extends P> primarySupplier, Supplier<? extends S> secondarySupplier, BiFunction<RegistryObject<P>, RegistryObject<S>, W> objectWrapper) {
/* 26 */     return objectWrapper.apply(this.primaryRegister.register(name, primarySupplier), this.secondaryRegister.register(name, secondarySupplier));
/*    */   }
/*    */ 
/*    */   
/*    */   public <P extends PRIMARY, S extends SECONDARY, W extends DoubleWrappedRegistryObject<P, S>> W register(String name, Supplier<? extends P> primarySupplier, Function<P, S> secondarySupplier, BiFunction<RegistryObject<P>, RegistryObject<S>, W> objectWrapper) {
/* 31 */     RegistryObject<P> primaryObject = this.primaryRegister.register(name, primarySupplier);
/* 32 */     return objectWrapper.apply(primaryObject, this.secondaryRegister.register(name, () -> secondarySupplier.apply(primaryObject.get())));
/*    */   }
/*    */   
/*    */   public void register(IEventBus bus) {
/* 36 */     this.primaryRegister.register(bus);
/* 37 */     this.secondaryRegister.register(bus);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registration\DoubleDeferredRegister.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */