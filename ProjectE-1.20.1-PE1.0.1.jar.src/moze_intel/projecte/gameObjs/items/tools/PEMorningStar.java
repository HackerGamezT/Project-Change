/*     */ package moze_intel.projecte.gameObjs.items.tools;
/*     */ 
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.List;
/*     */ import java.util.function.Supplier;
/*     */ import moze_intel.projecte.config.ProjectEConfig;
/*     */ import moze_intel.projecte.gameObjs.EnumMatterType;
/*     */ import moze_intel.projecte.gameObjs.PETags;
/*     */ import moze_intel.projecte.gameObjs.items.IItemMode;
/*     */ import moze_intel.projecte.utils.ItemHelper;
/*     */ import moze_intel.projecte.utils.ToolHelper;
/*     */ import moze_intel.projecte.utils.text.ILangEntry;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.tags.BlockTags;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.InteractionResultHolder;
/*     */ import net.minecraft.world.entity.EquipmentSlot;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.TooltipFlag;
/*     */ import net.minecraft.world.item.context.UseOnContext;
/*     */ import net.minecraft.world.level.ClipContext;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.HitResult;
/*     */ import net.minecraftforge.common.Tags;
/*     */ import net.minecraftforge.common.ToolAction;
/*     */ import net.minecraftforge.common.ToolActions;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class PEMorningStar
/*     */   extends PETool implements IItemMode {
/*  43 */   private final ToolHelper.ChargeAttributeCache attributeCache = new ToolHelper.ChargeAttributeCache();
/*     */   private final ILangEntry[] modeDesc;
/*     */   
/*     */   public PEMorningStar(EnumMatterType matterType, int numCharges, Item.Properties props) {
/*  47 */     super(matterType, PETags.Blocks.MINEABLE_WITH_PE_MORNING_STAR, 16.0F, -3.0F, numCharges, props);
/*  48 */     this.modeDesc = new ILangEntry[] { (ILangEntry)PELang.MODE_MORNING_STAR_1, (ILangEntry)PELang.MODE_MORNING_STAR_2, (ILangEntry)PELang.MODE_MORNING_STAR_3, (ILangEntry)PELang.MODE_MORNING_STAR_4 };
/*  49 */     addItemCapability(moze_intel.projecte.capability.ModeChangerItemCapabilityWrapper::new);
/*     */   }
/*     */ 
/*     */   
/*     */   public ILangEntry[] getModeLangEntries() {
/*  54 */     return this.modeDesc;
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_7373_(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltips, @NotNull TooltipFlag flags) {
/*  59 */     super.m_7373_(stack, level, tooltips, flags);
/*  60 */     tooltips.add(getToolTip(stack));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
/*  65 */     return (ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction) || ToolActions.DEFAULT_SHOVEL_ACTIONS.contains(toolAction) || ToolHelper.DEFAULT_PE_HAMMER_ACTIONS
/*  66 */       .contains(toolAction) || ToolHelper.DEFAULT_PE_MORNING_STAR_ACTIONS.contains(toolAction));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_7579_(@NotNull ItemStack stack, @NotNull LivingEntity damaged, @NotNull LivingEntity damager) {
/*  71 */     ToolHelper.attackWithCharge(stack, damaged, damager, 1.0F);
/*  72 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m_6813_(@NotNull ItemStack stack, @NotNull Level level, @NotNull BlockState state, @NotNull BlockPos pos, @NotNull LivingEntity living) {
/*  77 */     ToolHelper.digBasedOnMode(stack, level, pos, living, (x$0, x$1, x$2) -> Item.m_41435_(x$0, x$1, x$2));
/*  78 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResult m_6225_(UseOnContext context) {
/*  84 */     Player player = context.m_43723_();
/*  85 */     if (player == null) {
/*  86 */       return InteractionResult.PASS;
/*     */     }
/*  88 */     InteractionHand hand = context.m_43724_();
/*  89 */     Level level = context.m_43725_();
/*  90 */     BlockPos pos = context.m_8083_();
/*  91 */     Direction sideHit = context.m_43719_();
/*  92 */     ItemStack stack = context.m_43722_();
/*  93 */     BlockState state = level.m_8055_(pos);
/*     */ 
/*     */     
/*  96 */     return ToolHelper.performActions(ToolHelper.flattenAOE(context, state, 0L), new Supplier[] {
/*     */ 
/*     */           
/*  99 */           () -> ToolHelper.dowseCampfire(context, state), () -> (state.m_204336_(Tags.Blocks.GRAVEL) || state.m_60734_() == Blocks.f_50129_) ? (ProjectEConfig.server.items.pickaxeAoeVeinMining.get() ? ToolHelper.digAOE(level, player, hand, stack, pos, sideHit, false, 0L) : ToolHelper.tryVeinMine(player, stack, pos, sideHit)) : InteractionResult.PASS, () -> 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 107 */           (ItemHelper.isOre(state) && !ProjectEConfig.server.items.pickaxeAoeVeinMining.get()) ? ToolHelper.tryVeinMine(player, stack, pos, sideHit) : InteractionResult.PASS, () -> ToolHelper.digAOE(level, player, hand, stack, pos, sideHit, 
/*     */ 
/*     */ 
/*     */             
/* 111 */             (!(state.m_60734_() instanceof net.minecraft.world.level.block.GrassBlock) && !state.m_204336_(BlockTags.f_13029_) && !state.m_204336_(BlockTags.f_144274_)), 0L)
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResultHolder<ItemStack> m_7203_(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
/* 118 */     ItemStack stack = player.m_21120_(hand);
/* 119 */     if (ProjectEConfig.server.items.pickaxeAoeVeinMining.get()) {
/* 120 */       return ItemHelper.actionResultFromType(ToolHelper.mineOreVeinsInAOE(player, hand), stack);
/*     */     }
/* 122 */     return InteractionResultHolder.m_19098_(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public float m_8102_(@NotNull ItemStack stack, @NotNull BlockState state) {
/* 127 */     return ToolHelper.canMatterMine(this.matterType, state.m_60734_()) ? 1200000.0F : (super.m_8102_(stack, state) + 48.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Multimap<Attribute, AttributeModifier> getAttributeModifiers(@NotNull EquipmentSlot slot, ItemStack stack) {
/* 133 */     return this.attributeCache.addChargeAttributeModifier(super.getAttributeModifiers(slot, stack), slot, stack);
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\tools\PEMorningStar.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */