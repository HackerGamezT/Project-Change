/*    */ package moze_intel.projecte.integration.top;
/*    */ 
/*    */ import java.util.function.Function;
/*    */ import mcjty.theoneprobe.api.IProbeHitData;
/*    */ import mcjty.theoneprobe.api.IProbeInfo;
/*    */ import mcjty.theoneprobe.api.IProbeInfoProvider;
/*    */ import mcjty.theoneprobe.api.ITheOneProbe;
/*    */ import mcjty.theoneprobe.api.ProbeMode;
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.config.ProjectEConfig;
/*    */ import moze_intel.projecte.utils.EMCHelper;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.ItemLike;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ 
/*    */ 
/*    */ public class PEProbeInfoProvider
/*    */   implements IProbeInfoProvider, Function<ITheOneProbe, Void>
/*    */ {
/*    */   public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level level, BlockState blockState, IProbeHitData data) {
/* 24 */     if (ProjectEConfig.server.misc.hwylaTOPDisplay.get()) {
/* 25 */       long value = EMCHelper.getEmcValue(new ItemStack((ItemLike)blockState.m_60734_()));
/* 26 */       if (value > 0L) {
/* 27 */         probeInfo.mcText(EMCHelper.getEmcTextComponent(value, 1));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getID() {
/* 34 */     return PECore.rl("emc");
/*    */   }
/*    */ 
/*    */   
/*    */   public Void apply(ITheOneProbe iTheOneProbe) {
/* 39 */     iTheOneProbe.registerProvider(this);
/* 40 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\top\PEProbeInfoProvider.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */