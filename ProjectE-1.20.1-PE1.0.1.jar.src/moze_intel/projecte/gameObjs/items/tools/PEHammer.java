/*    */ package moze_intel.projecte.gameObjs.items.tools;
/*    */ 
/*    */ import com.google.common.collect.Multimap;
/*    */ import moze_intel.projecte.gameObjs.EnumMatterType;
/*    */ import moze_intel.projecte.gameObjs.PETags;
/*    */ import moze_intel.projecte.utils.ToolHelper;
/*    */ import net.minecraft.world.InteractionResult;
/*    */ import net.minecraft.world.entity.EquipmentSlot;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.world.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.item.context.UseOnContext;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraftforge.common.ToolAction;
/*    */ import net.minecraftforge.common.ToolActions;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PEHammer
/*    */   extends PETool {
/* 23 */   private final ToolHelper.ChargeAttributeCache attributeCache = new ToolHelper.ChargeAttributeCache();
/*    */   
/*    */   public PEHammer(EnumMatterType matterType, int numCharges, Item.Properties props) {
/* 26 */     super(matterType, PETags.Blocks.MINEABLE_WITH_PE_HAMMER, 10.0F, -3.0F, numCharges, props);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_7579_(@NotNull ItemStack stack, @NotNull LivingEntity damaged, @NotNull LivingEntity damager) {
/* 31 */     ToolHelper.attackWithCharge(stack, damaged, damager, 1.0F);
/* 32 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
/* 37 */     return (ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction) || ToolHelper.DEFAULT_PE_HAMMER_ACTIONS.contains(toolAction));
/*    */   }
/*    */ 
/*    */   
/*    */   public float m_8102_(@NotNull ItemStack stack, @NotNull BlockState state) {
/* 42 */     return ToolHelper.canMatterMine(this.matterType, state.m_60734_()) ? 1200000.0F : super.m_8102_(stack, state);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Multimap<Attribute, AttributeModifier> getAttributeModifiers(@NotNull EquipmentSlot slot, ItemStack stack) {
/* 48 */     return this.attributeCache.addChargeAttributeModifier(super.getAttributeModifiers(slot, stack), slot, stack);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public InteractionResult m_6225_(UseOnContext context) {
/* 54 */     Player player = context.m_43723_();
/* 55 */     if (player == null) {
/* 56 */       return InteractionResult.PASS;
/*    */     }
/* 58 */     return ToolHelper.digAOE(context.m_43725_(), player, context.m_43724_(), context.m_43722_(), context.m_8083_(), context.m_43719_(), true, 0L);
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\tools\PEHammer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */