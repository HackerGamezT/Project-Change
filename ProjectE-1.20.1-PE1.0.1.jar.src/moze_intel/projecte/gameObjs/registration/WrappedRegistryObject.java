/*    */ package moze_intel.projecte.gameObjs.registration;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class WrappedRegistryObject<T>
/*    */   implements Supplier<T>, INamedEntry {
/*    */   @NotNull
/*    */   protected RegistryObject<T> registryObject;
/*    */   
/*    */   protected WrappedRegistryObject(@NotNull RegistryObject<T> registryObject) {
/* 13 */     this.registryObject = registryObject;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public T get() {
/* 19 */     return (T)this.registryObject.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getInternalRegistryName() {
/* 24 */     return this.registryObject.getId().m_135815_();
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\registration\WrappedRegistryObject.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */