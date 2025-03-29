/*     */ package moze_intel.projecte;
/*     */ 
/*     */ import moze_intel.projecte.gameObjs.items.rings.Arcana;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.BlockSource;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.BaseFireBlock;
/*     */ import net.minecraft.world.level.block.CampfireBlock;
/*     */ import net.minecraft.world.level.block.DispenserBlock;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.block.state.properties.BlockStateProperties;
/*     */ import net.minecraft.world.level.block.state.properties.Property;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class null
/*     */   extends OptionalDispenseItemBehavior
/*     */ {
/*     */   @NotNull
/*     */   protected ItemStack m_7498_(@NotNull BlockSource source, @NotNull ItemStack stack) {
/* 221 */     Item item = stack.m_41720_(); if (item instanceof Arcana) { Arcana arcana = (Arcana)item;
/* 222 */       if (arcana.getMode(stack) != 1) {
/*     */         
/* 224 */         m_123573_(false);
/* 225 */         return super.m_7498_(source, stack);
/*     */       }  }
/*     */     
/* 228 */     ServerLevel serverLevel = source.m_7727_();
/* 229 */     m_123573_(true);
/* 230 */     Direction direction = (Direction)source.m_6414_().m_61143_((Property)DispenserBlock.f_52659_);
/* 231 */     BlockPos pos = source.m_7961_().m_121945_(direction);
/* 232 */     BlockState state = serverLevel.m_8055_(pos);
/* 233 */     if (BaseFireBlock.m_49255_((Level)serverLevel, pos, direction)) {
/* 234 */       serverLevel.m_46597_(pos, BaseFireBlock.m_49245_((BlockGetter)serverLevel, pos));
/* 235 */     } else if (CampfireBlock.m_51321_(state)) {
/* 236 */       serverLevel.m_46597_(pos, (BlockState)state.m_61124_((Property)BlockStateProperties.f_61443_, Boolean.valueOf(true)));
/* 237 */     } else if (state.isFlammable((BlockGetter)serverLevel, pos, direction.m_122424_())) {
/* 238 */       state.onCaughtFire((Level)serverLevel, pos, direction.m_122424_(), null);
/* 239 */       if (state.m_60734_() instanceof net.minecraft.world.level.block.TntBlock) {
/* 240 */         serverLevel.m_7471_(pos, false);
/*     */       }
/*     */     } else {
/* 243 */       m_123573_(false);
/*     */     } 
/* 245 */     return stack;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\PECore$1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */