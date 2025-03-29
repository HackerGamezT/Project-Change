/*    */ package moze_intel.projecte.gameObjs.items.armor;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import moze_intel.projecte.utils.WorldHelper;
/*    */ import moze_intel.projecte.utils.text.PELang;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.item.TooltipFlag;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.phys.AABB;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class GemLegs extends GemArmorBase {
/*    */   private final Map<Integer, Long> lastJumpTracker;
/*    */   
/* 25 */   public GemLegs(Item.Properties props) { super(ArmorItem.Type.LEGGINGS, props);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 35 */     this.lastJumpTracker = new HashMap<>();
/*    */     MinecraftForge.EVENT_BUS.addListener(this::onJump); }
/*    */   
/* 38 */   private void onJump(LivingEvent.LivingJumpEvent evt) { LivingEntity livingEntity = evt.getEntity(); if (livingEntity instanceof Player) { Player player = (Player)livingEntity; if ((player.m_9236_()).f_46443_)
/* 39 */         this.lastJumpTracker.put(Integer.valueOf(player.m_19879_()), Long.valueOf(player.m_9236_().m_46467_()));  }
/*    */      } public void m_7373_(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltips, @NotNull TooltipFlag flags) {
/*    */     super.m_7373_(stack, level, tooltips, flags);
/*    */     tooltips.add(PELang.GEM_LORE_LEGS.translate(new Object[0]));
/*    */   } private boolean jumpedRecently(Player player) {
/* 44 */     return (this.lastJumpTracker.containsKey(Integer.valueOf(player.m_19879_())) && player.m_9236_().m_46467_() - ((Long)this.lastJumpTracker.get(Integer.valueOf(player.m_19879_()))).longValue() < 5L);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onArmorTick(ItemStack stack, Level level, Player player) {
/* 49 */     if (level.f_46443_ && 
/* 50 */       player.m_36341_() && !player.m_20096_() && player.m_20184_().m_7098_() > -8.0D && !jumpedRecently(player)) {
/* 51 */       player.m_20256_(player.m_20184_().m_82520_(0.0D, -0.3199999928474426D, 0.0D));
/*    */     }
/*    */     
/* 54 */     if (player.m_36341_()) {
/*    */       
/* 56 */       AABB box = new AABB(player.m_20185_() - 3.5D, player.m_20186_() - 3.5D, player.m_20189_() - 3.5D, player.m_20185_() + 3.5D, player.m_20186_() + 3.5D, player.m_20189_() + 3.5D);
/* 57 */       WorldHelper.repelEntitiesSWRG(level, box, player);
/* 58 */       if (!level.f_46443_ && player.m_20184_().m_7098_() < -0.08D) {
/* 59 */         List<Entity> entities = player.m_9236_().m_6249_((Entity)player, player.m_20191_().m_82383_(player.m_20184_()).m_82400_(2.0D), entity -> entity instanceof LivingEntity);
/*    */         
/* 61 */         for (Entity e : entities) {
/* 62 */           if (e.m_6087_())
/* 63 */             e.m_6469_(level.m_269111_().m_269075_(player), (float)-player.m_20184_().m_7098_() * 6.0F); 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\armor\GemLegs.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */