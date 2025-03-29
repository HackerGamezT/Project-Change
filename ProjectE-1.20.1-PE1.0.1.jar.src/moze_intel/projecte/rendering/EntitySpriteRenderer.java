/*    */ package moze_intel.projecte.rendering;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.joml.Matrix4f;
/*    */ 
/*    */ public class EntitySpriteRenderer<ENTITY extends Entity>
/*    */   extends EntityRenderer<ENTITY> {
/*    */   private final ResourceLocation texture;
/*    */   
/*    */   public EntitySpriteRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
/* 18 */     super(context);
/* 19 */     this.texture = texture;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ResourceLocation m_5478_(@NotNull ENTITY entity) {
/* 25 */     return this.texture;
/*    */   }
/*    */ 
/*    */   
/*    */   public void m_7392_(@NotNull ENTITY entity, float entityYaw, float partialTick, @NotNull PoseStack matrix, @NotNull MultiBufferSource renderer, int light) {
/* 30 */     matrix.m_85836_();
/* 31 */     matrix.m_252781_(this.f_114476_.m_253208_());
/* 32 */     matrix.m_85841_(0.5F, 0.5F, 0.5F);
/* 33 */     VertexConsumer builder = renderer.m_6299_(PERenderType.SPRITE_RENDERER.apply(m_5478_(entity)));
/* 34 */     Matrix4f matrix4f = matrix.m_85850_().m_252922_();
/* 35 */     builder.m_252986_(matrix4f, -1.0F, -1.0F, 0.0F).m_7421_(1.0F, 1.0F).m_5752_();
/* 36 */     builder.m_252986_(matrix4f, -1.0F, 1.0F, 0.0F).m_7421_(1.0F, 0.0F).m_5752_();
/* 37 */     builder.m_252986_(matrix4f, 1.0F, 1.0F, 0.0F).m_7421_(0.0F, 0.0F).m_5752_();
/* 38 */     builder.m_252986_(matrix4f, 1.0F, -1.0F, 0.0F).m_7421_(0.0F, 1.0F).m_5752_();
/* 39 */     matrix.m_85849_();
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\rendering\EntitySpriteRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */