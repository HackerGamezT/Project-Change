/*    */ package moze_intel.projecte.rendering;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.math.Axis;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.block.BlockRenderDispatcher;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.TntMinecartRenderer;
/*    */ import net.minecraft.client.renderer.texture.TextureAtlas;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.item.PrimedTnt;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class NovaRenderer<T extends PrimedTnt>
/*    */   extends EntityRenderer<T> {
/*    */   private final BlockRenderDispatcher blockRenderer;
/*    */   private final Supplier<BlockState> stateSupplier;
/*    */   
/*    */   public NovaRenderer(EntityRendererProvider.Context context, Supplier<BlockState> stateSupplier) {
/* 25 */     super(context);
/* 26 */     this.blockRenderer = context.m_234597_();
/* 27 */     this.stateSupplier = stateSupplier;
/* 28 */     this.f_114477_ = 0.5F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(@NotNull T entity, float entityYaw, float partialTick, @NotNull PoseStack matrix, @NotNull MultiBufferSource renderer, int light) {
/* 33 */     matrix.m_85836_();
/* 34 */     matrix.m_85837_(0.0D, 0.5D, 0.0D);
/* 35 */     int fuse = entity.m_32100_();
/* 36 */     if (fuse - partialTick + 1.0F < 10.0F) {
/* 37 */       float f = 1.0F - (fuse - partialTick + 1.0F) / 10.0F;
/* 38 */       f = Mth.m_14036_(f, 0.0F, 1.0F);
/* 39 */       f *= f;
/* 40 */       f *= f;
/* 41 */       float f1 = 1.0F + f * 0.3F;
/* 42 */       matrix.m_85841_(f1, f1, f1);
/*    */     } 
/*    */     
/* 45 */     matrix.m_252781_(Axis.f_252436_.m_252977_(-90.0F));
/* 46 */     matrix.m_85837_(-0.5D, -0.5D, 0.5D);
/* 47 */     matrix.m_252781_(Axis.f_252436_.m_252977_(90.0F));
/* 48 */     TntMinecartRenderer.m_234661_(this.blockRenderer, this.stateSupplier.get(), matrix, renderer, light, (fuse / 5 % 2 == 0));
/* 49 */     matrix.m_85849_();
/* 50 */     super.m_7392_((Entity)entity, entityYaw, partialTick, matrix, renderer, light);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ResourceLocation getTextureLocation(@NotNull T entity) {
/* 56 */     return TextureAtlas.f_118259_;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\rendering\NovaRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */