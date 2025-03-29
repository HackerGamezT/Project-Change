/*    */ package moze_intel.projecte.gameObjs.items.armor;
/*    */ 
/*    */ import moze_intel.projecte.PECore;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraft.sounds.SoundEvents;
/*    */ import net.minecraft.tags.DamageTypeTags;
/*    */ import net.minecraft.world.damagesource.DamageSource;
/*    */ import net.minecraft.world.item.ArmorItem;
/*    */ import net.minecraft.world.item.ArmorMaterial;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.crafting.Ingredient;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class RMArmor
/*    */   extends PEArmor {
/*    */   public RMArmor(ArmorItem.Type armorType, Item.Properties props) {
/* 17 */     super(RMArmorMaterial.INSTANCE, armorType, props);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getFullSetBaseReduction() {
/* 22 */     return 0.9F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getMaxDamageAbsorb(ArmorItem.Type type, DamageSource source) {
/* 27 */     if (source.m_269533_(DamageTypeTags.f_268415_)) {
/* 28 */       return 500.0F;
/*    */     }
/* 30 */     if (type == ArmorItem.Type.BOOTS && source.m_269533_(DamageTypeTags.f_268549_))
/* 31 */       return 10.0F / getPieceEffectiveness(type); 
/* 32 */     if (type == ArmorItem.Type.HELMET && source.m_269533_(DamageTypeTags.f_268581_)) {
/* 33 */       return 10.0F / getPieceEffectiveness(type);
/*    */     }
/* 35 */     if (source.m_269533_(DamageTypeTags.f_268490_)) {
/* 36 */       return 0.0F;
/*    */     }
/*    */     
/* 39 */     if (type == ArmorItem.Type.HELMET || type == ArmorItem.Type.BOOTS) {
/* 40 */       return 250.0F;
/*    */     }
/* 42 */     return 350.0F;
/*    */   }
/*    */   
/*    */   private static class RMArmorMaterial
/*    */     implements ArmorMaterial {
/* 47 */     private static final RMArmorMaterial INSTANCE = new RMArmorMaterial();
/*    */ 
/*    */     
/*    */     public int m_266425_(@NotNull ArmorItem.Type type) {
/* 51 */       return 0;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public int m_7366_(@NotNull ArmorItem.Type type) {
/*    */       // Byte code:
/*    */       //   0: getstatic moze_intel/projecte/gameObjs/items/armor/RMArmor$1.$SwitchMap$net$minecraft$world$item$ArmorItem$Type : [I
/*    */       //   3: aload_1
/*    */       //   4: invokevirtual ordinal : ()I
/*    */       //   7: iaload
/*    */       //   8: tableswitch default -> 40, 1 -> 48, 2 -> 52, 3 -> 57, 4 -> 62
/*    */       //   40: new java/lang/IncompatibleClassChangeError
/*    */       //   43: dup
/*    */       //   44: invokespecial <init> : ()V
/*    */       //   47: athrow
/*    */       //   48: iconst_3
/*    */       //   49: goto -> 63
/*    */       //   52: bipush #6
/*    */       //   54: goto -> 63
/*    */       //   57: bipush #8
/*    */       //   59: goto -> 63
/*    */       //   62: iconst_3
/*    */       //   63: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #56	-> 0
/*    */       //   #57	-> 48
/*    */       //   #58	-> 52
/*    */       //   #59	-> 57
/*    */       //   #60	-> 62
/*    */       //   #56	-> 63
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	64	0	this	Lmoze_intel/projecte/gameObjs/items/armor/RMArmor$RMArmorMaterial;
/*    */       //   0	64	1	type	Lnet/minecraft/world/item/ArmorItem$Type;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public int m_6646_() {
/* 66 */       return 0;
/*    */     }
/*    */ 
/*    */     
/*    */     @NotNull
/*    */     public SoundEvent m_7344_() {
/* 72 */       return SoundEvents.f_11673_;
/*    */     }
/*    */ 
/*    */     
/*    */     @NotNull
/*    */     public Ingredient m_6230_() {
/* 78 */       return Ingredient.f_43901_;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     @NotNull
/*    */     public String m_6082_() {
/* 85 */       return PECore.rl("red_matter").toString();
/*    */     }
/*    */ 
/*    */     
/*    */     public float m_6651_() {
/* 90 */       return 2.0F;
/*    */     }
/*    */ 
/*    */     
/*    */     public float m_6649_() {
/* 95 */       return 0.2F;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\armor\RMArmor.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */