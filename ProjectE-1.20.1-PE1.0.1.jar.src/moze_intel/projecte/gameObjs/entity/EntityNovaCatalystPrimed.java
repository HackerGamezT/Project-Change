/*    */ package moze_intel.projecte.gameObjs.entity;
/*    */ import javax.annotation.Nullable;
/*    */ import moze_intel.projecte.gameObjs.registries.PEBlocks;
/*    */ import moze_intel.projecte.gameObjs.registries.PEEntityTypes;
/*    */ import moze_intel.projecte.utils.WorldHelper;
/*    */ import net.minecraft.network.protocol.Packet;
/*    */ import net.minecraft.network.protocol.game.ClientGamePacketListener;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.item.PrimedTnt;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.phys.HitResult;
/*    */ import net.minecraftforge.network.NetworkHooks;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class EntityNovaCatalystPrimed extends PrimedTnt {
/*    */   public EntityNovaCatalystPrimed(EntityType<EntityNovaCatalystPrimed> type, Level level) {
/* 20 */     super(type, level);
/* 21 */     m_32085_(m_32100_() / 4);
/*    */   }
/*    */   
/*    */   public EntityNovaCatalystPrimed(Level level, double x, double y, double z, LivingEntity placer) {
/* 25 */     super(level, x, y, z, placer);
/* 26 */     m_32085_(m_32100_() / 4);
/* 27 */     this.f_19850_ = true;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public EntityType<?> m_6095_() {
/* 33 */     return (EntityType)PEEntityTypes.NOVA_CATALYST_PRIMED.get();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void m_32103_() {
/* 38 */     WorldHelper.createNovaExplosion(m_9236_(), (Entity)this, m_20185_(), m_20186_(), m_20189_(), 16.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Packet<ClientGamePacketListener> m_5654_() {
/* 44 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getPickedResult(HitResult target) {
/* 49 */     return new ItemStack((ItemLike)PEBlocks.NOVA_CATALYST);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\entity\EntityNovaCatalystPrimed.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */