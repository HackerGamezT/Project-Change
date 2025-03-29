/*    */ package moze_intel.projecte.gameObjs.items.rings;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.function.Supplier;
/*    */ import moze_intel.projecte.api.capabilities.item.IAlchBagItem;
/*    */ import moze_intel.projecte.api.capabilities.item.IAlchChestItem;
/*    */ import moze_intel.projecte.api.capabilities.item.IExtraFunction;
/*    */ import moze_intel.projecte.api.capabilities.item.IPedestalItem;
/*    */ import moze_intel.projecte.gameObjs.items.GemEternalDensity;
/*    */ import moze_intel.projecte.gameObjs.registries.PEItems;
/*    */ import moze_intel.projecte.utils.PlayerHelper;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.core.Position;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.sounds.SoundEvents;
/*    */ import net.minecraft.sounds.SoundSource;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.entity.BlockEntity;
/*    */ import net.minecraft.world.phys.BlockHitResult;
/*    */ import net.minecraft.world.phys.HitResult;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.event.entity.EntityTeleportEvent;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import net.minecraftforge.items.IItemHandler;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class VoidRing extends GemEternalDensity implements IPedestalItem, IExtraFunction {
/*    */   public VoidRing(Item.Properties props) {
/* 34 */     super(props);
/* 35 */     addItemCapability(moze_intel.projecte.capability.PedestalItemCapabilityWrapper::new);
/* 36 */     addItemCapability(moze_intel.projecte.capability.ExtraFunctionItemCapabilityWrapper::new);
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_6883_(@NotNull ItemStack stack, Level level, @NotNull Entity entity, int slot, boolean isHeld) {
/* 41 */     super.m_6883_(stack, level, entity, slot, isHeld);
/* 42 */     ((BlackHoleBand)PEItems.BLACK_HOLE_BAND.get()).m_6883_(stack, level, entity, slot, isHeld);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public <PEDESTAL extends BlockEntity & moze_intel.projecte.api.block_entity.IDMPedestal> boolean updateInPedestal(@NotNull ItemStack stack, @NotNull Level level, @NotNull BlockPos pos, @NotNull PEDESTAL pedestal) {
/* 48 */     return ((IPedestalItem)PEItems.BLACK_HOLE_BAND.get()).updateInPedestal(stack, level, pos, (BlockEntity)pedestal);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public List<Component> getPedestalDescription() {
/* 54 */     return ((IPedestalItem)PEItems.BLACK_HOLE_BAND.get()).getPedestalDescription();
/*    */   }
/*    */   
/*    */   public boolean doExtraFunction(@NotNull ItemStack stack, @NotNull Player player, InteractionHand hand) {
/*    */     BlockPos c;
/* 59 */     if (player.m_36335_().m_41519_((Item)this)) {
/* 60 */       return false;
/*    */     }
/* 62 */     BlockHitResult lookingAt = PlayerHelper.getBlockLookingAt(player, 64.0D);
/*    */     
/* 64 */     if (lookingAt.m_6662_() == HitResult.Type.MISS) {
/* 65 */       c = BlockPos.m_274446_((Position)PlayerHelper.getLookVec(player, 32.0D).getRight());
/*    */     } else {
/* 67 */       c = lookingAt.m_82425_();
/*    */     } 
/* 69 */     EntityTeleportEvent event = new EntityTeleportEvent((Entity)player, c.m_123341_(), c.m_123342_(), c.m_123343_());
/* 70 */     if (!MinecraftForge.EVENT_BUS.post((Event)event)) {
/* 71 */       if (player.m_20159_()) {
/* 72 */         player.m_8127_();
/*    */       }
/* 74 */       player.m_6021_(event.getTargetX(), event.getTargetY(), event.getTargetZ());
/* 75 */       player.m_9236_().m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), SoundEvents.f_11852_, SoundSource.PLAYERS, 1.0F, 1.0F);
/* 76 */       player.f_19789_ = 0.0F;
/* 77 */       player.m_36335_().m_41524_((Item)this, 10);
/* 78 */       return true;
/*    */     } 
/* 80 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean updateInAlchBag(@NotNull IItemHandler inv, @NotNull Player player, @NotNull ItemStack stack) {
/* 86 */     return super.updateInAlchBag(inv, player, stack) | ((IAlchBagItem)PEItems.BLACK_HOLE_BAND.get()).updateInAlchBag(inv, player, stack);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean updateInAlchChest(@NotNull Level level, @NotNull BlockPos pos, @NotNull ItemStack stack) {
/* 92 */     return super.updateInAlchChest(level, pos, stack) | ((IAlchChestItem)PEItems.BLACK_HOLE_BAND.get()).updateInAlchChest(level, pos, stack);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\rings\VoidRing.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */