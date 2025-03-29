/*    */ package moze_intel.projecte.api.nss;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
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
/*    */ public abstract class AbstractNBTNSSTag<TYPE>
/*    */   extends AbstractNSSTag<TYPE>
/*    */   implements NSSNBT
/*    */ {
/*    */   @Nullable
/*    */   private final CompoundTag nbt;
/*    */   
/*    */   protected AbstractNBTNSSTag(@NotNull ResourceLocation resourceLocation, boolean isTag, @Nullable CompoundTag nbt) {
/* 26 */     super(resourceLocation, isTag);
/* 27 */     this.nbt = (nbt != null && nbt.m_128456_()) ? null : nbt;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public CompoundTag getNBT() {
/* 33 */     return this.nbt;
/*    */   }
/*    */ 
/*    */   
/*    */   public String json() {
/* 38 */     String json = super.json();
/* 39 */     if (hasNBT()) {
/* 40 */       return json + json;
/*    */     }
/* 42 */     return json;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 47 */     String string = super.toString();
/* 48 */     if (hasNBT()) {
/* 49 */       return string + string;
/*    */     }
/* 51 */     return string;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/* 56 */     if (o == this) {
/* 57 */       return true;
/*    */     }
/* 59 */     return (super.equals(o) && Objects.equals(this.nbt, ((AbstractNBTNSSTag)o).nbt));
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 64 */     int code = super.hashCode();
/* 65 */     if (hasNBT()) {
/* 66 */       code = 31 * code + getNBT().hashCode();
/*    */     }
/* 68 */     return code;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\nss\AbstractNBTNSSTag.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */