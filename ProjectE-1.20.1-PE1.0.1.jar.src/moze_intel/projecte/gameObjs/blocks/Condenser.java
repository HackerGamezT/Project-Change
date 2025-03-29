/*    */ package moze_intel.projecte.gameObjs.blocks;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.block_entities.CondenserBlockEntity;
/*    */ import moze_intel.projecte.gameObjs.registration.impl.BlockEntityTypeRegistryObject;
/*    */ import moze_intel.projecte.gameObjs.registries.PEBlockEntityTypes;
/*    */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class Condenser extends AlchemicalChest {
/*    */   public Condenser(BlockBehaviour.Properties props) {
/* 11 */     super(props);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public BlockEntityTypeRegistryObject<? extends CondenserBlockEntity> getType() {
/* 17 */     return PEBlockEntityTypes.CONDENSER;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\blocks\Condenser.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */