/*     */ package moze_intel.projecte.gameObjs.items.armor;
/*     */ 
/*     */ import moze_intel.projecte.PECore;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.tags.DamageTypeTags;
/*     */ import net.minecraft.world.damagesource.DamageSource;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.ArmorItem;
/*     */ import net.minecraft.world.item.ArmorMaterial;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.crafting.Ingredient;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public abstract class GemArmorBase extends PEArmor {
/*     */   public GemArmorBase(ArmorItem.Type armorType, Item.Properties props) {
/*  18 */     super(GemArmorMaterial.INSTANCE, armorType, props);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getFullSetBaseReduction() {
/*  23 */     return 0.9F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getMaxDamageAbsorb(ArmorItem.Type type, DamageSource source) {
/*  28 */     if (source.m_269533_(DamageTypeTags.f_268415_)) {
/*  29 */       return 750.0F;
/*     */     }
/*  31 */     if (type == ArmorItem.Type.BOOTS && source.m_269533_(DamageTypeTags.f_268549_))
/*  32 */       return 15.0F / getPieceEffectiveness(type); 
/*  33 */     if (type == ArmorItem.Type.HELMET && source.m_269533_(DamageTypeTags.f_268581_)) {
/*  34 */       return 15.0F / getPieceEffectiveness(type);
/*     */     }
/*  36 */     if (source.m_269533_(DamageTypeTags.f_268490_)) {
/*  37 */       return 0.0F;
/*     */     }
/*     */     
/*  40 */     if (type == ArmorItem.Type.HELMET || type == ArmorItem.Type.BOOTS) {
/*  41 */       return 400.0F;
/*     */     }
/*  43 */     return 500.0F;
/*     */   }
/*     */   
/*     */   public static boolean hasAnyPiece(Player player) {
/*  47 */     return (player.m_150109_()).f_35975_.stream().anyMatch(i -> (!i.m_41619_() && i.m_41720_() instanceof GemArmorBase));
/*     */   }
/*     */   
/*     */   public static boolean hasFullSet(Player player) {
/*  51 */     return (player.m_150109_()).f_35975_.stream().noneMatch(i -> (i.m_41619_() || !(i.m_41720_() instanceof GemArmorBase)));
/*     */   }
/*     */   
/*     */   private static class GemArmorMaterial
/*     */     implements ArmorMaterial {
/*  56 */     private static final GemArmorMaterial INSTANCE = new GemArmorMaterial();
/*     */ 
/*     */     
/*     */     public int m_266425_(@NotNull ArmorItem.Type type) {
/*  60 */       return 0;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int m_7366_(@NotNull ArmorItem.Type type) {
/*     */       // Byte code:
/*     */       //   0: getstatic moze_intel/projecte/gameObjs/items/armor/GemArmorBase$1.$SwitchMap$net$minecraft$world$item$ArmorItem$Type : [I
/*     */       //   3: aload_1
/*     */       //   4: invokevirtual ordinal : ()I
/*     */       //   7: iaload
/*     */       //   8: tableswitch default -> 40, 1 -> 48, 2 -> 52, 3 -> 57, 4 -> 62
/*     */       //   40: new java/lang/IncompatibleClassChangeError
/*     */       //   43: dup
/*     */       //   44: invokespecial <init> : ()V
/*     */       //   47: athrow
/*     */       //   48: iconst_3
/*     */       //   49: goto -> 63
/*     */       //   52: bipush #6
/*     */       //   54: goto -> 63
/*     */       //   57: bipush #8
/*     */       //   59: goto -> 63
/*     */       //   62: iconst_3
/*     */       //   63: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #65	-> 0
/*     */       //   #66	-> 48
/*     */       //   #67	-> 52
/*     */       //   #68	-> 57
/*     */       //   #69	-> 62
/*     */       //   #65	-> 63
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	64	0	this	Lmoze_intel/projecte/gameObjs/items/armor/GemArmorBase$GemArmorMaterial;
/*     */       //   0	64	1	type	Lnet/minecraft/world/item/ArmorItem$Type;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int m_6646_() {
/*  75 */       return 0;
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public SoundEvent m_7344_() {
/*  81 */       return SoundEvents.f_11673_;
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Ingredient m_6230_() {
/*  87 */       return Ingredient.f_43901_;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public String m_6082_() {
/*  94 */       return PECore.rl("gem_armor").toString();
/*     */     }
/*     */ 
/*     */     
/*     */     public float m_6651_() {
/*  99 */       return 2.0F;
/*     */     }
/*     */ 
/*     */     
/*     */     public float m_6649_() {
/* 104 */       return 0.25F;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\armor\GemArmorBase.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */