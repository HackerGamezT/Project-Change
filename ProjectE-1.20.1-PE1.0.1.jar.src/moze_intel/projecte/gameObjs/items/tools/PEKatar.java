/*     */ package moze_intel.projecte.gameObjs.items.tools;
/*     */ 
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.function.Supplier;
/*     */ import moze_intel.projecte.api.capabilities.item.IExtraFunction;
/*     */ import moze_intel.projecte.config.ProjectEConfig;
/*     */ import moze_intel.projecte.gameObjs.EnumMatterType;
/*     */ import moze_intel.projecte.gameObjs.PETags;
/*     */ import moze_intel.projecte.gameObjs.items.IItemMode;
/*     */ import moze_intel.projecte.utils.ItemHelper;
/*     */ import moze_intel.projecte.utils.PlayerHelper;
/*     */ import moze_intel.projecte.utils.ToolHelper;
/*     */ import moze_intel.projecte.utils.text.ILangEntry;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.tags.BlockTags;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.InteractionResultHolder;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EquipmentSlot;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.world.entity.item.ItemEntity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.TooltipFlag;
/*     */ import net.minecraft.world.item.UseAnim;
/*     */ import net.minecraft.world.item.context.UseOnContext;
/*     */ import net.minecraft.world.item.enchantment.Enchantments;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraftforge.common.IForgeShearable;
/*     */ import net.minecraftforge.common.ToolAction;
/*     */ import net.minecraftforge.common.ToolActions;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class PEKatar
/*     */   extends PETool
/*     */   implements IItemMode, IExtraFunction {
/*  48 */   private final ToolHelper.ChargeAttributeCache attributeCache = new ToolHelper.ChargeAttributeCache();
/*     */   private final ILangEntry[] modeDesc;
/*     */   
/*     */   public PEKatar(EnumMatterType matterType, int numCharges, Item.Properties props) {
/*  52 */     super(matterType, PETags.Blocks.MINEABLE_WITH_PE_KATAR, 19.0F, -2.4F, numCharges, props);
/*  53 */     this.modeDesc = new ILangEntry[] { (ILangEntry)PELang.MODE_KATAR_1, (ILangEntry)PELang.MODE_KATAR_2 };
/*  54 */     addItemCapability(moze_intel.projecte.capability.ModeChangerItemCapabilityWrapper::new);
/*  55 */     addItemCapability(moze_intel.projecte.capability.ExtraFunctionItemCapabilityWrapper::new);
/*     */   }
/*     */ 
/*     */   
/*     */   public ILangEntry[] getModeLangEntries() {
/*  60 */     return this.modeDesc;
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_7373_(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltips, @NotNull TooltipFlag flags) {
/*  65 */     super.m_7373_(stack, level, tooltips, flags);
/*  66 */     tooltips.add(getToolTip(stack));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
/*  71 */     return (ToolActions.DEFAULT_AXE_ACTIONS.contains(toolAction) || ToolActions.DEFAULT_SHEARS_ACTIONS.contains(toolAction) || ToolActions.DEFAULT_SWORD_ACTIONS
/*  72 */       .contains(toolAction) || ToolActions.DEFAULT_HOE_ACTIONS.contains(toolAction) || ToolHelper.DEFAULT_PE_KATAR_ACTIONS
/*  73 */       .contains(toolAction));
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public AABB getSweepHitBox(@NotNull ItemStack stack, @NotNull Player player, @NotNull Entity target) {
/*  79 */     int charge = getCharge(stack);
/*  80 */     return target.m_20191_().m_82377_(charge, charge / 4.0D, charge);
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getShortCutDestroySpeed(@NotNull ItemStack stack, @NotNull BlockState state) {
/*  85 */     float destroySpeed = super.getShortCutDestroySpeed(stack, state);
/*  86 */     if (destroySpeed == 1.0F)
/*     */     {
/*     */ 
/*     */       
/*  90 */       if (state.m_204336_(BlockTags.f_278398_)) {
/*  91 */         return 1.5F;
/*     */       }
/*     */     }
/*  94 */     return destroySpeed;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResult m_6225_(UseOnContext context) {
/* 100 */     Player player = context.m_43723_();
/* 101 */     if (player == null) {
/* 102 */       return InteractionResult.PASS;
/*     */     }
/* 104 */     Level level = context.m_43725_();
/* 105 */     BlockPos pos = context.m_8083_();
/* 106 */     BlockState state = level.m_8055_(pos);
/*     */ 
/*     */     
/* 109 */     return ToolHelper.performActions(ToolHelper.stripLogsAOE(context, state, 0L), new Supplier[] { () -> ToolHelper.scrapeAOE(context, state, 0L), () -> ToolHelper.waxOffAOE(context, state, 0L), () -> ToolHelper.tillAOE(context, state, 0L), () -> state.m_204336_(BlockTags.f_13106_) ? ToolHelper.clearTagAOE(level, player, context.m_43724_(), context.m_43722_(), 0L, BlockTags.f_13106_) : InteractionResult.PASS, () -> state.m_204336_(BlockTags.f_13035_) ? ToolHelper.clearTagAOE(level, player, context.m_43724_(), context.m_43722_(), 0L, BlockTags.f_13035_) : InteractionResult.PASS });
/*     */   }
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
/*     */   public boolean m_7579_(@NotNull ItemStack stack, @NotNull LivingEntity damaged, @NotNull LivingEntity damager) {
/* 131 */     ToolHelper.attackWithCharge(stack, damaged, damager, 1.0F);
/* 132 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, Player player) {
/* 138 */     return ToolHelper.shearBlock(stack, pos, player).m_19077_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResultHolder<ItemStack> m_7203_(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
/* 145 */     return ItemHelper.actionResultFromType(ToolHelper.shearEntityAOE(player, hand, 0L), player.m_21120_(hand));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean doExtraFunction(@NotNull ItemStack stack, @NotNull Player player, InteractionHand hand) {
/* 150 */     if (player.m_36403_(0.0F) == 1.0F) {
/* 151 */       ToolHelper.attackAOE(stack, player, (getMode(stack) == 1), ProjectEConfig.server.difficulty.katarDeathAura.get(), 0L, hand);
/* 152 */       PlayerHelper.resetCooldown(player);
/* 153 */       return true;
/*     */     } 
/* 155 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public UseAnim m_6164_(@NotNull ItemStack stack) {
/* 161 */     return UseAnim.BLOCK;
/*     */   }
/*     */ 
/*     */   
/*     */   public int m_8105_(@NotNull ItemStack stack) {
/* 166 */     return 72000;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Multimap<Attribute, AttributeModifier> getAttributeModifiers(@NotNull EquipmentSlot slot, ItemStack stack) {
/* 172 */     return this.attributeCache.addChargeAttributeModifier(super.getAttributeModifiers(slot, stack), slot, stack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResult m_6880_(@NotNull ItemStack stack, @NotNull Player player, @NotNull LivingEntity entity, @NotNull InteractionHand hand) {
/* 181 */     if (entity instanceof IForgeShearable) { IForgeShearable target = (IForgeShearable)entity;
/* 182 */       BlockPos pos = entity.m_20183_();
/* 183 */       if (target.isShearable(stack, entity.m_9236_(), pos)) {
/* 184 */         if (!(entity.m_9236_()).f_46443_) {
/* 185 */           List<ItemStack> drops = target.onSheared(player, stack, entity.m_9236_(), pos, stack.getEnchantmentLevel(Enchantments.f_44987_));
/* 186 */           Random rand = new Random();
/* 187 */           drops.forEach(d -> {
/*     */                 ItemEntity ent = entity.m_5552_(d, 1.0F);
/*     */                 
/*     */                 if (ent != null) {
/*     */                   ent.m_20256_(ent.m_20184_().m_82520_(((rand.nextFloat() - rand.nextFloat()) * 0.1F), (rand.nextFloat() * 0.05F), ((rand.nextFloat() - rand.nextFloat()) * 0.1F)));
/*     */                 }
/*     */               });
/*     */         } 
/* 195 */         return InteractionResult.SUCCESS;
/*     */       }  }
/*     */     
/* 198 */     return InteractionResult.PASS;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\tools\PEKatar.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */