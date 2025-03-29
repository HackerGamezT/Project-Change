/*    */ package moze_intel.projecte.gameObjs.container;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.block_entities.EmcChestBlockEntity;
/*    */ import moze_intel.projecte.gameObjs.registration.impl.ContainerTypeRegistryObject;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public abstract class EmcChestBlockEntityContainer<BE extends EmcChestBlockEntity>
/*    */   extends PEContainer {
/*    */   protected final BE blockEntity;
/*    */   
/*    */   protected EmcChestBlockEntityContainer(ContainerTypeRegistryObject<? extends EmcChestBlockEntityContainer<BE>> typeRO, int windowId, Inventory playerInv, BE blockEntity) {
/* 14 */     super((ContainerTypeRegistryObject)typeRO, windowId, playerInv);
/* 15 */     this.blockEntity = blockEntity;
/* 16 */     this.blockEntity.startOpen(playerInv.f_35978_);
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_6877_(@NotNull Player player) {
/* 21 */     super.m_6877_(player);
/* 22 */     this.blockEntity.stopOpen(player);
/*    */   }
/*    */   
/*    */   public boolean blockEntityMatches(EmcChestBlockEntity chest) {
/* 26 */     return (chest == this.blockEntity);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\EmcChestBlockEntityContainer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */