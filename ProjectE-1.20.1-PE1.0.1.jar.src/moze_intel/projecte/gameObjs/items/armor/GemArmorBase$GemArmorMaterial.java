/*     */ package moze_intel.projecte.gameObjs.items.armor;
/*     */ 
/*     */ import moze_intel.projecte.PECore;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.world.item.ArmorItem;
/*     */ import net.minecraft.world.item.ArmorMaterial;
/*     */ import net.minecraft.world.item.crafting.Ingredient;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class GemArmorMaterial
/*     */   implements ArmorMaterial
/*     */ {
/*  56 */   private static final GemArmorMaterial INSTANCE = new GemArmorMaterial();
/*     */ 
/*     */   
/*     */   public int m_266425_(@NotNull ArmorItem.Type type) {
/*  60 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_7366_(@NotNull ArmorItem.Type type) {
/*  65 */     switch (GemArmorBase.null.$SwitchMap$net$minecraft$world$item$ArmorItem$Type[type.ordinal()]) { default: throw new IncompatibleClassChangeError();case 1: case 2: case 3: case 4: break; }  return 
/*     */ 
/*     */ 
/*     */       
/*  69 */       3;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int m_6646_() {
/*  75 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public SoundEvent m_7344_() {
/*  81 */     return SoundEvents.f_11673_;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Ingredient m_6230_() {
/*  87 */     return Ingredient.f_43901_;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String m_6082_() {
/*  94 */     return PECore.rl("gem_armor").toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public float m_6651_() {
/*  99 */     return 2.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float m_6649_() {
/* 104 */     return 0.25F;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\armor\GemArmorBase$GemArmorMaterial.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */