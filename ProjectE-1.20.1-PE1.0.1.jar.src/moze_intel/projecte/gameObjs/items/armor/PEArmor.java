/*    */ package moze_intel.projecte.gameObjs.items.armor;
/*    */ 
/*    */ import java.util.function.Consumer;
/*    */ import net.minecraft.world.damagesource.DamageSource;
/*    */ import net.minecraft.world.item.ArmorItem;
/*    */ import net.minecraft.world.item.ArmorMaterial;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.item.enchantment.Enchantment;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public abstract class PEArmor
/*    */   extends ArmorItem
/*    */ {
/*    */   protected PEArmor(ArmorMaterial material, ArmorItem.Type armorPiece, Item.Properties props) {
/* 16 */     super(material, armorPiece, props);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_8120_(@NotNull ItemStack stack) {
/* 21 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
/* 26 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
/* 31 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public <T extends net.minecraft.world.entity.LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
/* 36 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract float getFullSetBaseReduction();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract float getMaxDamageAbsorb(ArmorItem.Type paramType, DamageSource paramDamageSource);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getPieceEffectiveness(ArmorItem.Type type) {
/* 56 */     if (type == ArmorItem.Type.BOOTS || type == ArmorItem.Type.HELMET)
/* 57 */       return 0.2F; 
/* 58 */     if (type == ArmorItem.Type.CHESTPLATE || type == ArmorItem.Type.LEGGINGS) {
/* 59 */       return 0.3F;
/*    */     }
/* 61 */     return 0.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\armor\PEArmor.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */