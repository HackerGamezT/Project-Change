/*     */ package moze_intel.projecte.network.commands;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import java.util.function.BooleanSupplier;
/*     */ import java.util.function.Predicate;
/*     */ import moze_intel.projecte.api.capabilities.IAlchBagProvider;
/*     */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*     */ import moze_intel.projecte.gameObjs.container.AlchBagContainer;
/*     */ import moze_intel.projecte.gameObjs.registries.PEItems;
/*     */ import moze_intel.projecte.impl.capability.AlchBagImpl;
/*     */ import moze_intel.projecte.network.commands.argument.ColorArgument;
/*     */ import moze_intel.projecte.utils.text.PELang;
/*     */ import moze_intel.projecte.utils.text.TextComponentUtil;
/*     */ import net.minecraft.commands.CommandBuildContext;
/*     */ import net.minecraft.commands.CommandSourceStack;
/*     */ import net.minecraft.commands.Commands;
/*     */ import net.minecraft.commands.arguments.EntityArgument;
/*     */ import net.minecraft.commands.arguments.UuidArgument;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.NbtIo;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.network.chat.MutableComponent;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.MenuProvider;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*     */ import net.minecraft.world.item.DyeColor;
/*     */ import net.minecraft.world.level.storage.LevelResource;
/*     */ import net.minecraftforge.common.util.NonNullSupplier;
/*     */ import net.minecraftforge.items.IItemHandlerModifiable;
/*     */ import net.minecraftforge.server.ServerLifecycleHooks;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class ShowBagCMD {
/*  48 */   private static final SimpleCommandExceptionType NOT_FOUND = new SimpleCommandExceptionType((Message)PELang.SHOWBAG_NOT_FOUND.translate(new Object[0]));
/*     */   
/*     */   public static LiteralArgumentBuilder<CommandSourceStack> register(CommandBuildContext context) {
/*  51 */     return (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.m_82127_("showbag")
/*  52 */       .requires((Predicate)PEPermissions.COMMAND_SHOW_BAG))
/*  53 */       .then(((RequiredArgumentBuilder)Commands.m_82129_("color", (ArgumentType)ColorArgument.color())
/*  54 */         .then(Commands.m_82129_("target", (ArgumentType)EntityArgument.m_91466_())
/*  55 */           .executes(ctx -> showBag(ctx, ColorArgument.getColor(ctx, "color"), EntityArgument.m_91474_(ctx, "target")))))
/*  56 */         .then(Commands.m_82129_("uuid", (ArgumentType)UuidArgument.m_113850_())
/*  57 */           .executes(ctx -> showBag(ctx, ColorArgument.getColor(ctx, "color"), UuidArgument.m_113853_(ctx, "uuid")))));
/*     */   }
/*     */   
/*     */   private static int showBag(CommandContext<CommandSourceStack> ctx, DyeColor color, ServerPlayer player) throws CommandSyntaxException {
/*  61 */     ServerPlayer senderPlayer = ((CommandSourceStack)ctx.getSource()).m_81375_();
/*  62 */     return showBag(senderPlayer, createContainer(senderPlayer, player, color));
/*     */   }
/*     */   
/*     */   private static int showBag(CommandContext<CommandSourceStack> ctx, DyeColor color, UUID uuid) throws CommandSyntaxException {
/*  66 */     ServerPlayer senderPlayer = ((CommandSourceStack)ctx.getSource()).m_81375_();
/*  67 */     return showBag(senderPlayer, createContainer(senderPlayer, uuid, color));
/*     */   }
/*     */   
/*     */   private static int showBag(ServerPlayer senderPlayer, MenuProvider container) {
/*  71 */     NetworkHooks.openScreen(senderPlayer, container, b -> {
/*     */           b.writeBoolean(false);
/*     */           b.writeBoolean(false);
/*     */         });
/*  75 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static MenuProvider createContainer(ServerPlayer sender, ServerPlayer target, DyeColor color) {
/*  81 */     IItemHandlerModifiable inv = (IItemHandlerModifiable)((IAlchBagProvider)target.getCapability(PECapabilities.ALCH_BAG_CAPABILITY).orElseThrow(NullPointerException::new)).getBag(color);
/*  82 */     MutableComponent mutableComponent = PELang.SHOWBAG_NAMED.translate(new Object[] { PEItems.getBag(color), target.m_5446_() });
/*  83 */     return getContainer(sender, (Component)mutableComponent, inv, false, () -> (target.m_6084_() && !target.m_9232_()));
/*     */   }
/*     */   private static MenuProvider createContainer(ServerPlayer sender, UUID target, DyeColor color) throws CommandSyntaxException {
/*     */     MutableComponent mutableComponent;
/*  87 */     MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
/*     */     
/*  89 */     IItemHandlerModifiable inv = loadOfflineBag(server, target, color);
/*     */     
/*  91 */     Optional<GameProfile> profileByUUID = server.m_129927_().m_11002_(target);
/*  92 */     if (profileByUUID.isPresent()) {
/*     */       
/*  94 */       mutableComponent = PELang.SHOWBAG_NAMED.translate(new Object[] { PEItems.getBag(color), ((GameProfile)profileByUUID.get()).getName() });
/*     */     } else {
/*  96 */       mutableComponent = TextComponentUtil.build(new Object[] { PEItems.getBag(color) });
/*     */     } 
/*  98 */     return getContainer(sender, (Component)mutableComponent, inv, true, () -> true);
/*     */   }
/*     */ 
/*     */   
/*     */   private static MenuProvider getContainer(final ServerPlayer sender, final Component name, final IItemHandlerModifiable inv, final boolean immutable, final BooleanSupplier canInteractWith) {
/* 103 */     return new MenuProvider()
/*     */       {
/*     */         @NotNull
/*     */         public Component m_5446_() {
/* 107 */           return name;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public AbstractContainerMenu m_7208_(int windowId, @NotNull Inventory playerInv, @NotNull Player player) {
/* 113 */           return (AbstractContainerMenu)new AlchBagContainer(windowId, sender.m_150109_(), InteractionHand.OFF_HAND, inv, 0, immutable)
/*     */             {
/*     */               public boolean m_6875_(@NotNull Player player) {
/* 116 */                 return canInteractWith.getAsBoolean();
/*     */               }
/*     */             };
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   private static IItemHandlerModifiable loadOfflineBag(MinecraftServer server, UUID playerUUID, DyeColor color) throws CommandSyntaxException {
/* 124 */     File playerData = server.m_129843_(LevelResource.f_78176_).toFile();
/* 125 */     if (playerData.exists()) {
/* 126 */       File player = new File(playerData, playerUUID.toString() + ".dat");
/* 127 */       if (player.exists() && player.isFile()) {
/* 128 */         try { FileInputStream in = new FileInputStream(player); 
/* 129 */           try { CompoundTag playerDat = NbtIo.m_128939_(in);
/* 130 */             CompoundTag bagProvider = playerDat.m_128469_("ForgeCaps").m_128469_(AlchBagImpl.Provider.NAME.toString());
/*     */             
/* 132 */             IAlchBagProvider provider = AlchBagImpl.getDefault();
/* 133 */             provider.deserializeNBT((Tag)bagProvider);
/*     */             
/* 135 */             IItemHandlerModifiable iItemHandlerModifiable = (IItemHandlerModifiable)provider.getBag(color);
/* 136 */             in.close(); return iItemHandlerModifiable; } catch (Throwable throwable) { try { in.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (IOException iOException) {}
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 141 */     throw NOT_FOUND.create();
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\commands\ShowBagCMD.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */