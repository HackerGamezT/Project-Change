/*    */ package moze_intel.projecte.rendering;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*    */ import com.mojang.math.Axis;
/*    */ import java.util.function.Predicate;
/*    */ import java.util.function.Supplier;
/*    */ import moze_intel.projecte.gameObjs.block_entities.EmcChestBlockEntity;
/*    */ import moze_intel.projecte.gameObjs.registration.impl.BlockRegistryObject;
/*    */ import net.minecraft.client.model.geom.ModelLayers;
/*    */ import net.minecraft.client.model.geom.ModelPart;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
/*    */ import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
/*    */ import net.minecraft.core.Direction;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.entity.BlockEntity;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.level.block.state.properties.BlockStateProperties;
/*    */ import net.minecraft.world.level.block.state.properties.Property;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class ChestRenderer
/*    */   implements BlockEntityRenderer<EmcChestBlockEntity> {
/*    */   private final ModelPart lid;
/*    */   private final ModelPart bottom;
/*    */   private final ModelPart lock;
/*    */   private final Predicate<Block> blockChecker;
/*    */   private final ResourceLocation texture;
/*    */   
/*    */   public ChestRenderer(BlockEntityRendererProvider.Context context, ResourceLocation texture, Supplier<BlockRegistryObject<?, ?>> type) {
/* 34 */     this.texture = texture;
/* 35 */     this.blockChecker = (block -> (block == ((BlockRegistryObject)type.get()).getBlock()));
/* 36 */     ModelPart modelpart = context.m_173582_(ModelLayers.f_171275_);
/* 37 */     this.bottom = modelpart.m_171324_("bottom");
/* 38 */     this.lid = modelpart.m_171324_("lid");
/* 39 */     this.lock = modelpart.m_171324_("lock");
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(@NotNull EmcChestBlockEntity chest, float partialTick, @NotNull PoseStack matrix, @NotNull MultiBufferSource renderer, int light, int overlayLight) {
/* 44 */     matrix.m_85836_();
/* 45 */     if (chest.m_58904_() != null && !chest.m_58901_()) {
/* 46 */       BlockState state = chest.m_58904_().m_8055_(chest.m_58899_());
/* 47 */       if (this.blockChecker.test(state.m_60734_())) {
/* 48 */         matrix.m_85837_(0.5D, 0.5D, 0.5D);
/* 49 */         matrix.m_252781_(Axis.f_252436_.m_252977_(-((Direction)state.m_61143_((Property)BlockStateProperties.f_61374_)).m_122435_()));
/* 50 */         matrix.m_85837_(-0.5D, -0.5D, -0.5D);
/*    */       } 
/*    */     } 
/* 53 */     float lidAngle = 1.0F - chest.m_6683_(partialTick);
/* 54 */     lidAngle = 1.0F - lidAngle * lidAngle * lidAngle;
/* 55 */     VertexConsumer builder = renderer.m_6299_(RenderType.m_110452_(this.texture));
/* 56 */     this.lid.f_104203_ = -(lidAngle * 1.5707964F);
/* 57 */     this.lock.f_104203_ = this.lid.f_104203_;
/* 58 */     this.lid.m_104301_(matrix, builder, light, overlayLight);
/* 59 */     this.lock.m_104301_(matrix, builder, light, overlayLight);
/* 60 */     this.bottom.m_104301_(matrix, builder, light, overlayLight);
/* 61 */     matrix.m_85849_();
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\rendering\ChestRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */