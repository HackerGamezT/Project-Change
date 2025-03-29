/*    */ package moze_intel.projecte.gameObjs.registration.impl;
/*    */ 
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import moze_intel.projecte.gameObjs.registration.WrappedRegistryObject;
/*    */ import net.minecraft.commands.synchronization.ArgumentTypeInfo;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ 
/*    */ public class ArgumentTypeInfoRegistryObject<TYPE extends ArgumentType<?>>
/*    */   extends WrappedRegistryObject<ArgumentTypeInfo<TYPE, ? extends ArgumentTypeInfo.Template<TYPE>>> {
/*    */   public ArgumentTypeInfoRegistryObject(RegistryObject<ArgumentTypeInfo<TYPE, ? extends ArgumentTypeInfo.Template<TYPE>>> registryObject) {
/* 11 */     super(registryObject);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registration\impl\ArgumentTypeInfoRegistryObject.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */