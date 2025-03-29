/*    */ package moze_intel.projecte.gameObjs.items.rings;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.function.Supplier;
/*    */ import moze_intel.projecte.api.block_entity.IDMPedestal;
/*    */ import moze_intel.projecte.api.capabilities.item.IPedestalItem;
/*    */ import moze_intel.projecte.config.ProjectEConfig;
/*    */ import moze_intel.projecte.gameObjs.registries.PESoundEvents;
/*    */ import moze_intel.projecte.handlers.InternalTimers;
/*    */ import moze_intel.projecte.integration.IntegrationHelper;
/*    */ import moze_intel.projecte.utils.MathUtils;
/*    */ import moze_intel.projecte.utils.text.PELang;
/*    */ import net.minecraft.ChatFormatting;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraft.sounds.SoundSource;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.gameevent.GameEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class BodyStone
/*    */   extends PEToggleItem implements IPedestalItem {
/*    */   public BodyStone(Item.Properties props) {
/* 33 */     super(props);
/* 34 */     addItemCapability(moze_intel.projecte.capability.PedestalItemCapabilityWrapper::new);
/* 35 */     addItemCapability("curios", IntegrationHelper.CURIO_CAP_SUPPLIER);
/*    */   }
/*    */   
/*    */   public void m_6883_(@NotNull ItemStack stack, Level level, @NotNull Entity entity, int slot, boolean held) {
/*    */     Player player;
/* 40 */     if (!level.f_46443_ && slot < Inventory.m_36059_() && entity instanceof Player) { player = (Player)entity; }
/*    */     else
/*    */     { return; }
/* 43 */      super.m_6883_(stack, level, entity, slot, held);
/* 44 */     CompoundTag nbt = stack.m_41784_();
/* 45 */     if (nbt.m_128471_("Active")) {
/* 46 */       long itemEmc = getEmc(stack);
/* 47 */       if (itemEmc < 64L && !consumeFuel(player, stack, 64L, false)) {
/* 48 */         nbt.m_128379_("Active", false);
/*    */       } else {
/* 50 */         player.getCapability(InternalTimers.CAPABILITY, null).ifPresent(timers -> {
/*    */               timers.activateFeed();
/*    */               if (player.m_36324_().m_38721_() && timers.canFeed()) {
/*    */                 level.m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)PESoundEvents.HEAL.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
/*    */                 player.m_36324_().m_38707_(2, 10.0F);
/*    */                 player.m_146850_(GameEvent.f_157806_);
/*    */                 removeEmc(stack, 64L);
/*    */               } 
/*    */             });
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public <PEDESTAL extends net.minecraft.world.level.block.entity.BlockEntity & IDMPedestal> boolean updateInPedestal(@NotNull ItemStack stack, @NotNull Level level, @NotNull BlockPos pos, @NotNull PEDESTAL pedestal) {
/* 66 */     if (!level.f_46443_ && ProjectEConfig.server.cooldown.pedestal.body.get() != -1) {
/* 67 */       if (((IDMPedestal)pedestal).getActivityCooldown() == 0) {
/* 68 */         for (ServerPlayer player : level.m_45976_(ServerPlayer.class, ((IDMPedestal)pedestal).getEffectBounds())) {
/* 69 */           if (player.m_36324_().m_38721_()) {
/* 70 */             level.m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)PESoundEvents.HEAL.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
/* 71 */             player.m_36324_().m_38707_(1, 1.0F);
/*    */           } 
/*    */         } 
/* 74 */         ((IDMPedestal)pedestal).setActivityCooldown(ProjectEConfig.server.cooldown.pedestal.body.get());
/*    */       } else {
/* 76 */         ((IDMPedestal)pedestal).decrementActivityCooldown();
/*    */       } 
/*    */     }
/* 79 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public List<Component> getPedestalDescription() {
/* 85 */     List<Component> list = new ArrayList<>();
/* 86 */     if (ProjectEConfig.server.cooldown.pedestal.body.get() != -1) {
/* 87 */       list.add(PELang.PEDESTAL_BODY_STONE_1.translateColored(ChatFormatting.BLUE, new Object[0]));
/* 88 */       list.add(PELang.PEDESTAL_BODY_STONE_2.translateColored(ChatFormatting.BLUE, new Object[] { MathUtils.tickToSecFormatted(ProjectEConfig.server.cooldown.pedestal.body.get()) }));
/*    */     } 
/* 90 */     return list;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\rings\BodyStone.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */