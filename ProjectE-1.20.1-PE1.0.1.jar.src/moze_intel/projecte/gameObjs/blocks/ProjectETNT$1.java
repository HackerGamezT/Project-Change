/*    */ package moze_intel.projecte.gameObjs.blocks;
/*    */ 
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.core.BlockSource;
/*    */ import net.minecraft.core.Direction;
/*    */ import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.DispenserBlock;
/*    */ import net.minecraft.world.level.block.state.properties.Property;
/*    */ import net.minecraft.world.level.gameevent.GameEvent;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class null
/*    */   extends DefaultDispenseItemBehavior
/*    */ {
/*    */   @NotNull
/*    */   protected ItemStack m_7498_(@NotNull BlockSource source, @NotNull ItemStack stack) {
/* 57 */     BlockPos blockpos = source.m_7961_().m_121945_((Direction)source.m_6414_().m_61143_((Property)DispenserBlock.f_52659_));
/* 58 */     ProjectETNT.this.createAndAddEntity((Level)source.m_7727_(), blockpos, null);
/* 59 */     source.m_7727_().m_142346_(null, GameEvent.f_157810_, blockpos);
/* 60 */     stack.m_41774_(1);
/* 61 */     return stack;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\blocks\ProjectETNT$1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */