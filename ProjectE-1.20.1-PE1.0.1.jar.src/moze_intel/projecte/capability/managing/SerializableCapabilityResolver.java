/*    */ package moze_intel.projecte.capability.managing;
/*    */ 
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.nbt.Tag;
/*    */ import net.minecraftforge.common.capabilities.ICapabilitySerializable;
/*    */ import net.minecraftforge.common.util.INBTSerializable;
/*    */ 
/*    */ public abstract class SerializableCapabilityResolver<CAPABILITY extends INBTSerializable<CompoundTag>>
/*    */   extends BasicCapabilityResolver<CAPABILITY> implements ICapabilitySerializable<CompoundTag> {
/*    */   protected final CAPABILITY internal;
/*    */   
/*    */   protected SerializableCapabilityResolver(CAPABILITY internal) {
/* 13 */     super(internal);
/* 14 */     this.internal = internal;
/*    */   }
/*    */ 
/*    */   
/*    */   public CompoundTag serializeNBT() {
/* 19 */     return (CompoundTag)this.internal.serializeNBT();
/*    */   }
/*    */ 
/*    */   
/*    */   public void deserializeNBT(CompoundTag nbt) {
/* 24 */     this.internal.deserializeNBT((Tag)nbt);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\capability\managing\SerializableCapabilityResolver.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */