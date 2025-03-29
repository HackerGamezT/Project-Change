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
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class SoulStone
/*    */   extends PEToggleItem implements IPedestalItem {
/*    */   public SoulStone(Item.Properties props) {
/* 32 */     super(props);
/* 33 */     addItemCapability(moze_intel.projecte.capability.PedestalItemCapabilityWrapper::new);
/* 34 */     addItemCapability("curios", IntegrationHelper.CURIO_CAP_SUPPLIER);
/*    */   }
/*    */   
/*    */   public void m_6883_(@NotNull ItemStack stack, Level level, @NotNull Entity entity, int slot, boolean held) {
/*    */     Player player;
/* 39 */     if (!level.f_46443_ && slot < Inventory.m_36059_() && entity instanceof Player) { player = (Player)entity; }
/*    */     else
/*    */     { return; }
/* 42 */      super.m_6883_(stack, level, entity, slot, held);
/* 43 */     CompoundTag nbt = stack.m_41784_();
/* 44 */     if (nbt.m_128471_("Active")) {
/* 45 */       if (getEmc(stack) < 64L && !consumeFuel(player, stack, 64L, false)) {
/* 46 */         nbt.m_128379_("Active", false);
/*    */       } else {
/* 48 */         player.getCapability(InternalTimers.CAPABILITY, null).ifPresent(timers -> {
/*    */               timers.activateHeal();
/*    */               if (player.m_21223_() < player.m_21233_() && timers.canHeal()) {
/*    */                 level.m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)PESoundEvents.HEAL.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
/*    */                 player.m_5634_(2.0F);
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
/* 63 */     if (!level.f_46443_ && ProjectEConfig.server.cooldown.pedestal.soul.get() != -1) {
/* 64 */       if (((IDMPedestal)pedestal).getActivityCooldown() == 0) {
/* 65 */         for (ServerPlayer player : level.m_6443_(ServerPlayer.class, ((IDMPedestal)pedestal).getEffectBounds(), ent -> 
/* 66 */             (!ent.m_5833_() && ent.m_21223_() < ent.m_21233_()))) {
/* 67 */           level.m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)PESoundEvents.HEAL.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
/* 68 */           player.m_5634_(1.0F);
/*    */         } 
/* 70 */         ((IDMPedestal)pedestal).setActivityCooldown(ProjectEConfig.server.cooldown.pedestal.soul.get());
/*    */       } else {
/* 72 */         ((IDMPedestal)pedestal).decrementActivityCooldown();
/*    */       } 
/*    */     }
/* 75 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public List<Component> getPedestalDescription() {
/* 81 */     List<Component> list = new ArrayList<>();
/* 82 */     if (ProjectEConfig.server.cooldown.pedestal.soul.get() != -1) {
/* 83 */       list.add(PELang.PEDESTAL_SOUL_STONE_1.translateColored(ChatFormatting.BLUE, new Object[0]));
/* 84 */       list.add(PELang.PEDESTAL_SOUL_STONE_2.translateColored(ChatFormatting.BLUE, new Object[] { MathUtils.tickToSecFormatted(ProjectEConfig.server.cooldown.pedestal.soul.get()) }));
/*    */     } 
/* 86 */     return list;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\rings\SoulStone.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */