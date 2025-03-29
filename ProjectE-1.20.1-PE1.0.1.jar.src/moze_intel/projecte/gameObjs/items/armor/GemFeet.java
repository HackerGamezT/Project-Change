/*     */ package moze_intel.projecte.gameObjs.items.armor;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMultimap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Supplier;
/*     */ import moze_intel.projecte.gameObjs.items.IFlightProvider;
/*     */ import moze_intel.projecte.gameObjs.items.IStepAssister;
/*     */ import moze_intel.projecte.utils.ClientKeyHelper;
/*     */ import moze_intel.projecte.utils.ItemHelper;
/*     */ import moze_intel.projecte.utils.PEKeybind;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.entity.EquipmentSlot;
/*     */ import net.minecraft.world.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.ArmorItem;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.TooltipFlag;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraftforge.fml.DistExecutor;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class GemFeet
/*     */   extends GemArmorBase
/*     */   implements IFlightProvider, IStepAssister
/*     */ {
/*  37 */   private static final UUID MODIFIER = UUID.fromString("A4334312-DFF8-4582-9F4F-62AD0C070475");
/*     */   
/*     */   private final Multimap<Attribute, AttributeModifier> attributes;
/*     */   
/*     */   public GemFeet(Item.Properties props) {
/*  42 */     super(ArmorItem.Type.BOOTS, props);
/*  43 */     ImmutableMultimap.Builder<Attribute, AttributeModifier> attributesBuilder = ImmutableMultimap.builder();
/*  44 */     attributesBuilder.putAll(m_7167_(EquipmentSlot.FEET));
/*  45 */     attributesBuilder.put(Attributes.f_22279_, new AttributeModifier(MODIFIER, "Armor modifier", 1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL));
/*  46 */     this.attributes = (Multimap<Attribute, AttributeModifier>)attributesBuilder.build();
/*     */   }
/*     */   
/*     */   public void toggleStepAssist(ItemStack boots, Player player) {
/*     */     boolean value;
/*  51 */     CompoundTag bootsTag = boots.m_41784_();
/*  52 */     if (bootsTag.m_128425_("StepAssist", 1)) {
/*  53 */       value = !bootsTag.m_128471_("StepAssist");
/*  54 */       bootsTag.m_128379_("StepAssist", value);
/*     */     } else {
/*     */       
/*  57 */       bootsTag.m_128379_("StepAssist", true);
/*  58 */       value = true;
/*     */     } 
/*  60 */     if (value) {
/*  61 */       player.m_213846_((Component)PELang.STEP_ASSIST.translate(new Object[] { ChatFormatting.GREEN, PELang.GEM_ENABLED }));
/*     */     } else {
/*  63 */       player.m_213846_((Component)PELang.STEP_ASSIST.translate(new Object[] { ChatFormatting.RED, PELang.GEM_DISABLED }));
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean isJumpPressed() {
/*  68 */     return ((Boolean)DistExecutor.unsafeRunForDist(() -> (), () -> ())).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onArmorTick(ItemStack stack, Level level, Player player) {
/*  73 */     if (!level.f_46443_) {
/*  74 */       ServerPlayer playerMP = (ServerPlayer)player;
/*  75 */       playerMP.f_19789_ = 0.0F;
/*     */     } else {
/*  77 */       if (!(player.m_150110_()).f_35935_ && isJumpPressed()) {
/*  78 */         player.m_20256_(player.m_20184_().m_82520_(0.0D, 0.1D, 0.0D));
/*     */       }
/*  80 */       if (!player.m_20096_()) {
/*  81 */         if (player.m_20184_().m_7098_() <= 0.0D) {
/*  82 */           player.m_20256_(player.m_20184_().m_82542_(1.0D, 0.9D, 1.0D));
/*     */         }
/*  84 */         if (!(player.m_150110_()).f_35935_) {
/*  85 */           if (player.f_20902_ < 0.0F) {
/*  86 */             player.m_20256_(player.m_20184_().m_82542_(0.9D, 1.0D, 0.9D));
/*  87 */           } else if (player.f_20902_ > 0.0F && player.m_20184_().m_82556_() < 3.0D) {
/*  88 */             player.m_20256_(player.m_20184_().m_82542_(1.1D, 1.0D, 1.1D));
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_7373_(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltips, @NotNull TooltipFlag flags) {
/*  97 */     super.m_7373_(stack, level, tooltips, flags);
/*  98 */     tooltips.add(PELang.GEM_LORE_FEET.translate(new Object[0]));
/*  99 */     tooltips.add(PELang.STEP_ASSIST_PROMPT.translate(new Object[] { ClientKeyHelper.getKeyName(PEKeybind.BOOTS_TOGGLE) }));
/* 100 */     if (ItemHelper.checkItemNBT(stack, "StepAssist")) {
/* 101 */       tooltips.add(PELang.STEP_ASSIST.translate(new Object[] { ChatFormatting.GREEN, PELang.GEM_ENABLED }));
/*     */     } else {
/* 103 */       tooltips.add(PELang.STEP_ASSIST.translate(new Object[] { ChatFormatting.RED, PELang.GEM_DISABLED }));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Multimap<Attribute, AttributeModifier> getAttributeModifiers(@NotNull EquipmentSlot slot, ItemStack stack) {
/* 110 */     return (slot == EquipmentSlot.FEET) ? this.attributes : super.getAttributeModifiers(slot, stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canProvideFlight(ItemStack stack, ServerPlayer player) {
/* 115 */     return (player.m_6844_(EquipmentSlot.FEET) == stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canAssistStep(ItemStack stack, ServerPlayer player) {
/* 120 */     return (player.m_6844_(EquipmentSlot.FEET) == stack && ItemHelper.checkItemNBT(stack, "StepAssist"));
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\armor\GemFeet.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */