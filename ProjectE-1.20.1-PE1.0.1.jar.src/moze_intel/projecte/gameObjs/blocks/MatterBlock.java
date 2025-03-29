/*    */ package moze_intel.projecte.gameObjs.blocks;
/*    */ 
/*    */ import moze_intel.projecte.gameObjs.EnumMatterType;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*    */ 
/*    */ public class MatterBlock extends Block implements IMatterBlock {
/*    */   public final EnumMatterType matterType;
/*    */   
/*    */   public MatterBlock(BlockBehaviour.Properties props, EnumMatterType type) {
/* 11 */     super(props);
/* 12 */     this.matterType = type;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumMatterType getMatterType() {
/* 17 */     return this.matterType;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\blocks\MatterBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */