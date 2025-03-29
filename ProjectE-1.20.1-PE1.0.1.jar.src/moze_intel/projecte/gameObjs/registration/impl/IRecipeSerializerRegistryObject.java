/*    */ package moze_intel.projecte.gameObjs.registration.impl;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.registration.WrappedRegistryObject;
/*    */ import net.minecraft.world.item.crafting.Recipe;
/*    */ import net.minecraft.world.item.crafting.RecipeSerializer;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ 
/*    */ public class IRecipeSerializerRegistryObject<RECIPE extends Recipe<?>, SERIALIZER extends RecipeSerializer<RECIPE>>
/*    */   extends WrappedRegistryObject<SERIALIZER> {
/*    */   public IRecipeSerializerRegistryObject(RegistryObject<SERIALIZER> registryObject) {
/* 11 */     super(registryObject);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registration\impl\IRecipeSerializerRegistryObject.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */