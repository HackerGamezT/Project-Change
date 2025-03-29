/*    */ package moze_intel.projecte.gameObjs.registries;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.registration.impl.ArgumentTypeInfoDeferredRegister;
/*    */ import moze_intel.projecte.gameObjs.registration.impl.ArgumentTypeInfoRegistryObject;
/*    */ import moze_intel.projecte.network.commands.argument.ColorArgument;
/*    */ import moze_intel.projecte.network.commands.argument.NSSItemArgument;
/*    */ 
/*    */ 
/*    */ public class PEArgumentTypes
/*    */ {
/* 11 */   public static final ArgumentTypeInfoDeferredRegister ARGUMENT_TYPES = new ArgumentTypeInfoDeferredRegister("projecte");
/*    */   
/* 13 */   public static final ArgumentTypeInfoRegistryObject<ColorArgument> COLOR = ARGUMENT_TYPES.registerContextFree("color", ColorArgument.class, ColorArgument::color);
/* 14 */   public static final ArgumentTypeInfoRegistryObject<NSSItemArgument> NSS = ARGUMENT_TYPES.registerContextAware("nss", NSSItemArgument.class, NSSItemArgument::nss);
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registries\PEArgumentTypes.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */