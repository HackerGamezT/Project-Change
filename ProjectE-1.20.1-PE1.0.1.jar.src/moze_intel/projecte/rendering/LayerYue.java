/*    */ package moze_intel.projecte.rendering;
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import com.mojang.math.Axis;
/*    */ import java.util.UUID;
/*    */ import moze_intel.projecte.PECore;
/*    */ import net.minecraft.client.model.PlayerModel;
/*    */ import net.minecraft.client.player.AbstractClientPlayer;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*    */ import net.minecraft.client.renderer.entity.player.PlayerRenderer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraftforge.fml.loading.FMLEnvironment;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.joml.Matrix4f;
/*    */ 
/*    */ public class LayerYue extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
/* 20 */   private static final UUID SIN_UUID = UUID.fromString("5f86012c-ca4b-451a-989c-8fab167af647");
/* 21 */   private static final UUID CLAR_UUID = UUID.fromString("e5c59746-9cf7-4940-a849-d09e1f1efc13");
/* 22 */   private static final ResourceLocation HEART_LOC = PECore.rl("textures/models/heartcircle.png");
/* 23 */   private static final ResourceLocation YUE_LOC = PECore.rl("textures/models/yuecircle.png");
/*    */   
/*    */   private final PlayerRenderer render;
/*    */   
/*    */   public LayerYue(PlayerRenderer renderer) {
/* 28 */     super((RenderLayerParent)renderer);
/* 29 */     this.render = renderer;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(@NotNull PoseStack matrix, @NotNull MultiBufferSource renderer, int light, @NotNull AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 35 */     if (player.m_20145_()) {
/*    */       return;
/*    */     }
/* 38 */     if (!FMLEnvironment.production || SIN_UUID.equals(player.m_20148_()) || CLAR_UUID.equals(player.m_20148_())) {
/* 39 */       matrix.m_85836_();
/* 40 */       ((PlayerModel)this.render.m_7200_()).f_103378_.m_104299_(matrix);
/* 41 */       double yShift = -0.498D;
/* 42 */       if (player.m_6047_()) {
/*    */         
/* 44 */         matrix.m_252781_(Axis.f_252529_.m_252977_(-28.64789F));
/* 45 */         yShift = -0.44D;
/*    */       } 
/* 47 */       matrix.m_252781_(Axis.f_252403_.m_252977_(180.0F));
/* 48 */       matrix.m_85841_(3.0F, 3.0F, 3.0F);
/* 49 */       matrix.m_85837_(-0.5D, yShift, -0.5D);
/* 50 */       VertexConsumer builder = renderer.m_6299_(PERenderType.YEU_RENDERER.apply(CLAR_UUID.equals(player.m_20148_()) ? HEART_LOC : YUE_LOC));
/* 51 */       Matrix4f matrix4f = matrix.m_85850_().m_252922_();
/* 52 */       builder.m_252986_(matrix4f, 0.0F, 0.0F, 0.0F).m_6122_(0, 255, 0, 255).m_7421_(0.0F, 0.0F).m_5752_();
/* 53 */       builder.m_252986_(matrix4f, 0.0F, 0.0F, 1.0F).m_6122_(0, 255, 0, 255).m_7421_(0.0F, 1.0F).m_5752_();
/* 54 */       builder.m_252986_(matrix4f, 1.0F, 0.0F, 1.0F).m_6122_(0, 255, 0, 255).m_7421_(1.0F, 1.0F).m_5752_();
/* 55 */       builder.m_252986_(matrix4f, 1.0F, 0.0F, 0.0F).m_6122_(0, 255, 0, 255).m_7421_(1.0F, 0.0F).m_5752_();
/* 56 */       matrix.m_85849_();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\rendering\LayerYue.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */