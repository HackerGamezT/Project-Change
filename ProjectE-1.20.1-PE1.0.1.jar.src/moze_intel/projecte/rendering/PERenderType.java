/*    */ package moze_intel.projecte.rendering;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.DefaultVertexFormat;
/*    */ import com.mojang.blaze3d.vertex.VertexFormat;
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.Util;
/*    */ import net.minecraft.client.renderer.RenderStateShard;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ 
/*    */ public class PERenderType extends RenderType {
/*    */   public static final Function<ResourceLocation, RenderType> SPRITE_RENDERER;
/*    */   public static final Function<ResourceLocation, RenderType> YEU_RENDERER;
/*    */   
/*    */   private PERenderType(String name, VertexFormat format, VertexFormat.Mode drawMode, int bufferSize, boolean useDelegate, boolean needsSorting, Runnable setupTask, Runnable clearTask) {
/* 16 */     super(name, format, drawMode, bufferSize, useDelegate, needsSorting, setupTask, clearTask);
/*    */   }
/*    */   static {
/* 19 */     SPRITE_RENDERER = Util.m_143827_(resourceLocation -> {
/*    */           RenderType.CompositeState state = RenderType.CompositeState.m_110628_().m_173292_(RenderStateShard.f_173102_).m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(resourceLocation, false, false)).m_110691_(true);
/*    */ 
/*    */           
/*    */           return (RenderType)m_173215_("projecte_sprite_renderer", DefaultVertexFormat.f_85817_, VertexFormat.Mode.QUADS, 256, true, false, state);
/*    */         });
/*    */ 
/*    */     
/* 27 */     YEU_RENDERER = Util.m_143827_(resourceLocation -> {
/*    */           RenderType.CompositeState state = RenderType.CompositeState.m_110628_().m_173292_(RenderStateShard.f_173101_).m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(resourceLocation, false, false)).m_110685_(f_110139_).m_110661_(f_110110_).m_110691_(true);
/*    */           return (RenderType)m_173215_("projecte_yeu_renderer", DefaultVertexFormat.f_85818_, VertexFormat.Mode.QUADS, 256, true, false, state);
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 37 */   public static final RenderType TRANSMUTATION_OVERLAY = (RenderType)m_173215_("projecte_transmutation_overlay", DefaultVertexFormat.f_85815_, VertexFormat.Mode.QUADS, 256, true, false, 
/* 38 */       RenderType.CompositeState.m_110628_()
/* 39 */       .m_173292_(f_173104_)
/* 40 */       .m_110685_(f_110139_)
/* 41 */       .m_110661_(f_110110_)
/* 42 */       .m_110687_(f_110115_)
/* 43 */       .m_110669_(f_110118_)
/* 44 */       .m_110691_(true));
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\rendering\PERenderType.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */