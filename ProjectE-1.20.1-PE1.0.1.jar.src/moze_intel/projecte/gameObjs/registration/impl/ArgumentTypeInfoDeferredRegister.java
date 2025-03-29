/*    */ package moze_intel.projecte.gameObjs.registration.impl;
/*    */ 
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Supplier;
/*    */ import moze_intel.projecte.gameObjs.registration.WrappedDeferredRegister;
/*    */ import net.minecraft.commands.CommandBuildContext;
/*    */ import net.minecraft.commands.synchronization.ArgumentTypeInfo;
/*    */ import net.minecraft.commands.synchronization.ArgumentTypeInfos;
/*    */ import net.minecraft.commands.synchronization.SingletonArgumentInfo;
/*    */ import net.minecraft.core.registries.Registries;
/*    */ 
/*    */ public class ArgumentTypeInfoDeferredRegister
/*    */   extends WrappedDeferredRegister<ArgumentTypeInfo<?, ?>>
/*    */ {
/*    */   public ArgumentTypeInfoDeferredRegister(String modid) {
/* 16 */     super(Registries.f_256982_, modid);
/*    */   }
/*    */   
/*    */   public <TYPE extends com.mojang.brigadier.arguments.ArgumentType<?>> ArgumentTypeInfoRegistryObject<TYPE> registerContextFree(String name, Class<TYPE> argumentClass, Supplier<TYPE> constructor) {
/* 20 */     return register(name, argumentClass, () -> SingletonArgumentInfo.m_235451_(constructor));
/*    */   }
/*    */ 
/*    */   
/*    */   public <TYPE extends com.mojang.brigadier.arguments.ArgumentType<?>> ArgumentTypeInfoRegistryObject<TYPE> registerContextAware(String name, Class<TYPE> argumentClass, Function<CommandBuildContext, TYPE> constructor) {
/* 25 */     return register(name, argumentClass, () -> SingletonArgumentInfo.m_235449_(constructor));
/*    */   }
/*    */ 
/*    */   
/*    */   public <TYPE extends com.mojang.brigadier.arguments.ArgumentType<?>> ArgumentTypeInfoRegistryObject<TYPE> register(String name, Class<TYPE> argumentClass, Supplier<ArgumentTypeInfo<TYPE, ? extends ArgumentTypeInfo.Template<TYPE>>> sup) {
/* 30 */     return (ArgumentTypeInfoRegistryObject<TYPE>)register(name, () -> ArgumentTypeInfos.registerByClass(argumentClass, sup.get()), ArgumentTypeInfoRegistryObject::new);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registration\impl\ArgumentTypeInfoDeferredRegister.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */