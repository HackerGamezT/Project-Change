/*     */ package moze_intel.projecte.gameObjs.items.rings;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import java.util.function.Supplier;
/*     */ import moze_intel.projecte.api.block_entity.IDMPedestal;
/*     */ import moze_intel.projecte.api.capabilities.item.IPedestalItem;
/*     */ import moze_intel.projecte.utils.ItemHelper;
/*     */ import moze_intel.projecte.utils.WorldHelper;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResultHolder;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.ExperienceOrb;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.TooltipFlag;
/*     */ import net.minecraft.world.level.Level;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class MindStone
/*     */   extends PEToggleItem
/*     */   implements IPedestalItem {
/*     */   private static final int TRANSFER_RATE = 50;
/*     */   
/*     */   public MindStone(Item.Properties props) {
/*  33 */     super(props);
/*  34 */     addItemCapability(moze_intel.projecte.capability.PedestalItemCapabilityWrapper::new);
/*     */   }
/*     */   
/*     */   public void m_6883_(@NotNull ItemStack stack, Level level, @NotNull Entity entity, int slot, boolean held) {
/*     */     Player player;
/*  39 */     if (!level.f_46443_ && slot < Inventory.m_36059_() && entity instanceof Player) { player = (Player)entity; }
/*     */     else
/*     */     { return; }
/*  42 */      super.m_6883_(stack, level, entity, slot, held);
/*  43 */     if (ItemHelper.checkItemNBT(stack, "Active") && getXP(player) > 0) {
/*  44 */       int toAdd = Math.min(getXP(player), 50);
/*  45 */       addStoredXP(stack, toAdd);
/*  46 */       removeXP(player, 50);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InteractionResultHolder<ItemStack> m_7203_(Level level, Player player, @NotNull InteractionHand hand) {
/*  53 */     ItemStack stack = player.m_21120_(hand);
/*  54 */     if (!level.f_46443_ && !stack.m_41784_().m_128471_("Active") && getStoredXP(stack) != 0) {
/*  55 */       int toAdd = removeStoredXP(stack, 50);
/*  56 */       if (toAdd > 0) {
/*  57 */         addXP(player, toAdd);
/*     */       }
/*     */     } 
/*  60 */     return InteractionResultHolder.m_19090_(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_7373_(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltips, @NotNull TooltipFlag flags) {
/*  65 */     super.m_7373_(stack, level, tooltips, flags);
/*  66 */     if (stack.m_41782_()) {
/*  67 */       tooltips.add(PELang.TOOLTIP_STORED_XP.translateColored(ChatFormatting.DARK_GREEN, new Object[] { ChatFormatting.GREEN, String.format("%,d", new Object[] { Integer.valueOf(getStoredXP(stack)) }) }));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void removeXP(Player player, int amount) {
/*  73 */     int totalExperience = getXP(player) - amount;
/*  74 */     if (totalExperience < 0) {
/*  75 */       player.f_36079_ = 0;
/*  76 */       player.f_36078_ = 0;
/*  77 */       player.f_36080_ = 0.0F;
/*     */     } else {
/*  79 */       player.f_36079_ = totalExperience;
/*  80 */       player.f_36078_ = getLvlForXP(totalExperience);
/*  81 */       player.f_36080_ = (totalExperience - getXPForLvl(player.f_36078_)) / player.m_36323_();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addXP(Player player, int amount) {
/*  86 */     int experiencetotal = getXP(player) + amount;
/*  87 */     player.f_36079_ = experiencetotal;
/*  88 */     player.f_36078_ = getLvlForXP(experiencetotal);
/*  89 */     player.f_36080_ = (experiencetotal - getXPForLvl(player.f_36078_)) / player.m_36323_();
/*     */   }
/*     */   
/*     */   private int getXP(Player player) {
/*  93 */     return (int)(getXPForLvl(player.f_36078_) + player.f_36080_ * player.m_36323_());
/*     */   }
/*     */ 
/*     */   
/*     */   private int getXPForLvl(int level) {
/*  98 */     if (level < 0) {
/*  99 */       return Integer.MAX_VALUE;
/*     */     }
/*     */     
/* 102 */     if (level <= 16) {
/* 103 */       return level * level + 6 * level;
/*     */     }
/*     */     
/* 106 */     if (level <= 31) {
/* 107 */       return (int)((level * level) * 2.5D - 40.5D * level + 360.0D);
/*     */     }
/*     */     
/* 110 */     return (int)((level * level) * 4.5D - 162.5D * level + 2220.0D);
/*     */   }
/*     */   
/*     */   private int getLvlForXP(int totalXP) {
/* 114 */     int result = 0;
/*     */     
/* 116 */     while (getXPForLvl(result) <= totalXP) {
/* 117 */       result++;
/*     */     }
/*     */     
/* 120 */     return --result;
/*     */   }
/*     */   
/*     */   private int getStoredXP(ItemStack stack) {
/* 124 */     return stack.m_41782_() ? stack.m_41784_().m_128451_("StoredXP") : 0;
/*     */   }
/*     */   
/*     */   private void setStoredXP(ItemStack stack, int XP) {
/* 128 */     stack.m_41784_().m_128405_("StoredXP", XP);
/*     */   }
/*     */   
/*     */   private void addStoredXP(ItemStack stack, int XP) {
/* 132 */     long result = getStoredXP(stack) + XP;
/* 133 */     if (result > 2147483647L) {
/* 134 */       result = 2147483647L;
/*     */     }
/* 136 */     setStoredXP(stack, (int)result);
/*     */   }
/*     */   
/*     */   private int removeStoredXP(ItemStack stack, int XP) {
/* 140 */     int result, returnResult, currentXP = getStoredXP(stack);
/*     */ 
/*     */ 
/*     */     
/* 144 */     if (currentXP < XP) {
/* 145 */       result = 0;
/* 146 */       returnResult = currentXP;
/*     */     } else {
/* 148 */       result = currentXP - XP;
/* 149 */       returnResult = XP;
/*     */     } 
/*     */     
/* 152 */     setStoredXP(stack, result);
/* 153 */     return returnResult;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <PEDESTAL extends net.minecraft.world.level.block.entity.BlockEntity & IDMPedestal> boolean updateInPedestal(@NotNull ItemStack stack, @NotNull Level level, @NotNull BlockPos pos, @NotNull PEDESTAL pedestal) {
/* 159 */     boolean sucked = false;
/* 160 */     for (ExperienceOrb orb : level.m_45976_(ExperienceOrb.class, ((IDMPedestal)pedestal).getEffectBounds())) {
/* 161 */       WorldHelper.gravitateEntityTowards((Entity)orb, pos.m_123341_() + 0.5D, pos.m_123342_() + 0.5D, pos.m_123343_() + 0.5D);
/* 162 */       if (!level.f_46443_ && orb.m_20275_(pos.m_123341_() + 0.5D, pos.m_123342_() + 0.5D, pos.m_123343_() + 0.5D) < 1.21D) {
/* 163 */         suckXP(orb, stack);
/* 164 */         sucked = true;
/*     */       } 
/*     */     } 
/* 167 */     return sucked;
/*     */   }
/*     */   
/*     */   private void suckXP(ExperienceOrb orb, ItemStack mindStone) {
/* 171 */     long l = getStoredXP(mindStone);
/* 172 */     if (l + orb.f_20770_ > 2147483647L) {
/* 173 */       orb.f_20770_ = (int)(l + orb.f_20770_ - 2147483647L);
/* 174 */       setStoredXP(mindStone, 2147483647);
/*     */     } else {
/* 176 */       addStoredXP(mindStone, orb.f_20770_);
/* 177 */       orb.m_146870_();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<Component> getPedestalDescription() {
/* 184 */     return Lists.newArrayList((Object[])new Component[] { (Component)PELang.PEDESTAL_MIND_STONE.translateColored(ChatFormatting.BLUE, new Object[0]) });
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\items\rings\MindStone.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */