/*    */ package moze_intel.projecte.utils;
/*    */ 
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.alchemy.Potion;
/*    */ import net.minecraft.world.item.crafting.RecipeSerializer;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.IForgeRegistry;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RegistryUtils
/*    */ {
/*    */   public static ResourceLocation getName(Item element) {
/* 18 */     return getName(ForgeRegistries.ITEMS, element);
/*    */   }
/*    */   
/*    */   public static String getPath(Item element) {
/* 22 */     return getName(element).m_135815_();
/*    */   }
/*    */   
/*    */   public static ResourceLocation getName(Block element) {
/* 26 */     return getName(ForgeRegistries.BLOCKS, element);
/*    */   }
/*    */   
/*    */   public static String getPath(Block element) {
/* 30 */     return getName(element).m_135815_();
/*    */   }
/*    */   
/*    */   public static ResourceLocation getName(EntityType<?> element) {
/* 34 */     return getName(ForgeRegistries.ENTITY_TYPES, element);
/*    */   }
/*    */   
/*    */   public static ResourceLocation getName(Potion element) {
/* 38 */     return getName(ForgeRegistries.POTIONS, element);
/*    */   }
/*    */   
/*    */   public static ResourceLocation getName(RecipeSerializer<?> element) {
/* 42 */     return getName(ForgeRegistries.RECIPE_SERIALIZERS, element);
/*    */   }
/*    */   
/*    */   private static <T> ResourceLocation getName(IForgeRegistry<T> registry, T element) {
/* 46 */     return registry.getKey(element);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\project\\utils\RegistryUtils.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */