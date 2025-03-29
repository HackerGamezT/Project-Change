/*    */ package moze_intel.projecte.gameObjs.items.armor;
/*    */ 
/*    */ import moze_intel.projecte.PECore;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraft.sounds.SoundEvents;
/*    */ import net.minecraft.world.item.ArmorItem;
/*    */ import net.minecraft.world.item.ArmorMaterial;
/*    */ import net.minecraft.world.item.crafting.Ingredient;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class DMArmorMaterial
/*    */   implements ArmorMaterial
/*    */ {
/* 47 */   private static final DMArmorMaterial INSTANCE = new DMArmorMaterial();
/*    */ 
/*    */   
/*    */   public int m_266425_(@NotNull ArmorItem.Type type) {
/* 51 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public int m_7366_(@NotNull ArmorItem.Type type) {
/* 56 */     switch (DMArmor.null.$SwitchMap$net$minecraft$world$item$ArmorItem$Type[type.ordinal()]) { default: throw new IncompatibleClassChangeError();case 1: case 2: case 3: case 4: break; }  return 
/*    */ 
/*    */ 
/*    */       
/* 60 */       3;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int m_6646_() {
/* 66 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public SoundEvent m_7344_() {
/* 72 */     return SoundEvents.f_11673_;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Ingredient m_6230_() {
/* 78 */     return Ingredient.f_43901_;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public String m_6082_() {
/* 85 */     return PECore.rl("dark_matter").toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public float m_6651_() {
/* 90 */     return 2.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float m_6649_() {
/* 95 */     return 0.1F;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\armor\DMArmor$DMArmorMaterial.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */