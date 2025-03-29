/*    */ package moze_intel.projecte.gameObjs.registration;
/*    */ 
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class DoubleWrappedRegistryObject<PRIMARY, SECONDARY>
/*    */   implements INamedEntry {
/*    */   @NotNull
/*    */   private final RegistryObject<PRIMARY> primaryRO;
/*    */   @NotNull
/*    */   private final RegistryObject<SECONDARY> secondaryRO;
/*    */   
/*    */   public DoubleWrappedRegistryObject(@NotNull RegistryObject<PRIMARY> primaryRO, @NotNull RegistryObject<SECONDARY> secondaryRO) {
/* 14 */     this.primaryRO = primaryRO;
/* 15 */     this.secondaryRO = secondaryRO;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public PRIMARY getPrimary() {
/* 20 */     return (PRIMARY)this.primaryRO.get();
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public SECONDARY getSecondary() {
/* 25 */     return (SECONDARY)this.secondaryRO.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getInternalRegistryName() {
/* 30 */     return this.primaryRO.getId().m_135815_();
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registration\DoubleWrappedRegistryObject.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */