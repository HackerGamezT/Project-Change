/*    */ package moze_intel.projecte.gameObjs.registration.impl;
/*    */ 
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Supplier;
/*    */ import moze_intel.projecte.gameObjs.registration.WrappedDeferredRegister;
/*    */ import net.minecraft.world.item.crafting.RecipeSerializer;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ 
/*    */ public class IRecipeSerializerDeferredRegister
/*    */   extends WrappedDeferredRegister<RecipeSerializer<?>> {
/*    */   public IRecipeSerializerDeferredRegister(String modid) {
/* 12 */     super(ForgeRegistries.RECIPE_SERIALIZERS, modid);
/*    */   }
/*    */ 
/*    */   
/*    */   public <RECIPE extends net.minecraft.world.item.crafting.Recipe<?>, SERIALIZER extends RecipeSerializer<RECIPE>> IRecipeSerializerRegistryObject<RECIPE, SERIALIZER> register(String name, Supplier<SERIALIZER> sup) {
/* 17 */     return (IRecipeSerializerRegistryObject<RECIPE, SERIALIZER>)register(name, sup, IRecipeSerializerRegistryObject::new);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registration\impl\IRecipeSerializerDeferredRegister.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */