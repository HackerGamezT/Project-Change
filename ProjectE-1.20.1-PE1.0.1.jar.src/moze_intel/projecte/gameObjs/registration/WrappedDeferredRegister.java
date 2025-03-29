/*    */ package moze_intel.projecte.gameObjs.registration;
/*    */ 
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.core.Registry;
/*    */ import net.minecraft.resources.ResourceKey;
/*    */ import net.minecraftforge.eventbus.api.IEventBus;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.IForgeRegistry;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class WrappedDeferredRegister<T>
/*    */ {
/*    */   @NotNull
/*    */   protected final DeferredRegister<T> internal;
/*    */   
/*    */   protected WrappedDeferredRegister(@NotNull DeferredRegister<T> internal) {
/* 19 */     this.internal = internal;
/*    */   }
/*    */   
/*    */   protected WrappedDeferredRegister(IForgeRegistry<T> registry, String modid) {
/* 23 */     this(DeferredRegister.create(registry, modid));
/*    */   }
/*    */   
/*    */   protected WrappedDeferredRegister(ResourceKey<? extends Registry<T>> registryName, String modid) {
/* 27 */     this(DeferredRegister.create(registryName, modid));
/*    */   }
/*    */   
/*    */   protected <I extends T, W extends WrappedRegistryObject<I>> W register(String name, Supplier<? extends I> sup, Function<RegistryObject<I>, W> objectWrapper) {
/* 31 */     return objectWrapper.apply(this.internal.register(name, sup));
/*    */   }
/*    */   
/*    */   public void register(IEventBus bus) {
/* 35 */     this.internal.register(bus);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registration\WrappedDeferredRegister.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */