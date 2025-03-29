/*    */ package moze_intel.projecte.rendering;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import com.mojang.math.Axis;
/*    */ import moze_intel.projecte.gameObjs.block_entities.DMPedestalBlockEntity;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.LevelRenderer;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
/*    */ import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.world.item.ItemDisplayContext;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.block.entity.BlockEntity;
/*    */ import net.minecraft.world.phys.AABB;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PedestalRenderer
/*    */   implements BlockEntityRenderer<DMPedestalBlockEntity>
/*    */ {
/*    */   public PedestalRenderer(BlockEntityRendererProvider.Context context) {}
/*    */   
/*    */   public void render(@NotNull DMPedestalBlockEntity pedestal, float partialTick, @NotNull PoseStack matrix, @NotNull MultiBufferSource renderer, int light, int overlayLight) {
/* 27 */     if (!pedestal.m_58901_() && pedestal.m_58904_() != null) {
/* 28 */       if (Minecraft.m_91087_().m_91290_().m_114377_()) {
/* 29 */         matrix.m_85836_();
/* 30 */         BlockPos pos = pedestal.m_58899_();
/* 31 */         AABB aabb = pedestal.getEffectBounds().m_82386_(-pos.m_123341_(), -pos.m_123342_(), -pos.m_123343_());
/* 32 */         VertexConsumer vertexBuilder = renderer.m_6299_(RenderType.m_110504_());
/* 33 */         LevelRenderer.m_109621_(matrix, vertexBuilder, aabb.f_82288_, aabb.f_82289_, aabb.f_82290_, aabb.f_82291_ + 1.0D, aabb.f_82292_ + 1.0D, aabb.f_82293_ + 1.0D, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 1.0F);
/*    */         
/* 35 */         matrix.m_85849_();
/*    */       } 
/* 37 */       ItemStack stack = pedestal.getInventory().getStackInSlot(0);
/* 38 */       if (!stack.m_41619_()) {
/* 39 */         matrix.m_85836_();
/* 40 */         matrix.m_85837_(0.5D, 0.7D, 0.5D);
/* 41 */         long gameTime = pedestal.m_58904_().m_46467_();
/* 42 */         matrix.m_85837_(0.0D, Mth.m_14031_(((float)gameTime + partialTick) / 10.0F) * 0.1D + 0.1D, 0.0D);
/* 43 */         matrix.m_85841_(0.75F, 0.75F, 0.75F);
/* 44 */         float angle = ((float)gameTime + partialTick) / 20.0F * 57.295776F;
/* 45 */         matrix.m_252781_(Axis.f_252436_.m_252977_(angle));
/* 46 */         Minecraft.m_91087_().m_91291_().m_269128_(stack, ItemDisplayContext.GROUND, light, overlayLight, matrix, renderer, pedestal.m_58904_(), (int)pedestal.m_58899_().m_121878_());
/* 47 */         matrix.m_85849_();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\rendering\PedestalRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */