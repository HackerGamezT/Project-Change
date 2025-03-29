/*    */ package moze_intel.projecte.network.commands.client;
/*    */ import com.mojang.brigadier.builder.ArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import java.util.HashSet;
/*    */ import java.util.Objects;
/*    */ import java.util.Set;
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.api.ItemInfo;
/*    */ import moze_intel.projecte.utils.EMCHelper;
/*    */ import moze_intel.projecte.utils.text.PELang;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.commands.CommandBuildContext;
/*    */ import net.minecraft.commands.CommandSourceStack;
/*    */ import net.minecraft.commands.Commands;
/*    */ import net.minecraft.core.HolderLookup;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.flag.FeatureFlagSet;
/*    */ import net.minecraft.world.item.CreativeModeTab;
/*    */ import net.minecraft.world.item.CreativeModeTabs;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.item.Items;
/*    */ import net.minecraftforge.common.CreativeModeTabRegistry;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ 
/*    */ public class DumpMissingEmc {
/*    */   public static ArgumentBuilder<CommandSourceStack, ?> register(CommandBuildContext context) {
/* 29 */     return Commands.m_82127_("dumpmissingemc")
/* 30 */       .executes(DumpMissingEmc::execute);
/*    */   }
/*    */   
/*    */   private static int execute(CommandContext<CommandSourceStack> ctx) {
/* 34 */     CommandSourceStack source = (CommandSourceStack)ctx.getSource();
/* 35 */     Set<Item> allItems = new HashSet<>(ForgeRegistries.ITEMS.getValues());
/* 36 */     allItems.remove(Items.f_41852_);
/* 37 */     Set<ItemInfo> missing = new HashSet<>();
/* 38 */     CreativeModeTab tab = CreativeModeTabRegistry.getTab(CreativeModeTabs.f_256750_.m_135782_());
/* 39 */     if (tab != null) {
/* 40 */       if (tab.m_261235_().isEmpty())
/*    */       {
/* 42 */         initTab(tab);
/*    */       }
/*    */       
/* 45 */       for (ItemStack stack : tab.m_261235_()) {
/* 46 */         if (!stack.m_41619_()) {
/* 47 */           ItemInfo itemInfo = ItemInfo.fromStack(stack);
/* 48 */           if (EMCHelper.getEmcValue(itemInfo) == 0L) {
/* 49 */             missing.add(itemInfo); continue;
/*    */           } 
/* 51 */           allItems.remove(stack.m_41720_());
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 56 */     for (Item item : allItems) {
/*    */ 
/*    */       
/* 59 */       ItemInfo itemInfo = ItemInfo.fromItem((ItemLike)item);
/* 60 */       if (EMCHelper.getEmcValue(itemInfo) == 0L) {
/* 61 */         missing.add(itemInfo);
/*    */       }
/*    */     } 
/* 64 */     int missingCount = missing.size();
/* 65 */     if (missingCount == 0) {
/* 66 */       Objects.requireNonNull(PELang.DUMP_MISSING_EMC_NONE_MISSING); source.m_288197_(() -> rec$.translate(new Object[0]), true);
/*    */     } else {
/* 68 */       if (missingCount == 1) {
/* 69 */         Objects.requireNonNull(PELang.DUMP_MISSING_EMC_ONE_MISSING); source.m_288197_(() -> rec$.translate(new Object[0]), true);
/*    */       } else {
/* 71 */         source.m_288197_(() -> PELang.DUMP_MISSING_EMC_MULTIPLE_MISSING.translate(new Object[] { Integer.valueOf(missingCount) }, ), true);
/*    */       } 
/* 73 */       for (ItemInfo itemInfo : missing) {
/* 74 */         PECore.LOGGER.info(itemInfo.toString());
/*    */       }
/*    */     } 
/* 77 */     return missingCount;
/*    */   }
/*    */   
/*    */   private static void initTab(CreativeModeTab tab) {
/* 81 */     Minecraft minecraft = Minecraft.m_91087_();
/* 82 */     if (minecraft.f_91073_ != null) {
/*    */ 
/*    */       
/* 85 */       FeatureFlagSet features = Optional.<LocalPlayer>ofNullable(minecraft.f_91074_).map(p -> p.f_108617_.m_247016_()).orElse(FeatureFlags.f_244332_);
/* 86 */       boolean hasPermissions = (((Boolean)minecraft.f_91066_.m_257871_().m_231551_()).booleanValue() || (minecraft.f_91074_ != null && minecraft.f_91074_.m_36337_()));
/* 87 */       CreativeModeTab.ItemDisplayParameters displayParameters = new CreativeModeTab.ItemDisplayParameters(features, hasPermissions, (HolderLookup.Provider)minecraft.f_91073_.m_9598_());
/*    */       try {
/* 89 */         tab.m_269498_(displayParameters);
/* 90 */       } catch (RuntimeException|LinkageError runtimeException) {}
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\commands\client\DumpMissingEmc.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */