/*     */ package moze_intel.projecte.rendering;
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import moze_intel.projecte.gameObjs.items.PhilosophersStone;
/*     */ import net.minecraft.client.Camera;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiGraphics;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlas;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.LiquidBlock;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.material.Fluid;
/*     */ import net.minecraft.world.phys.BlockHitResult;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraft.world.phys.shapes.CollisionContext;
/*     */ import net.minecraft.world.phys.shapes.VoxelShape;
/*     */ import net.minecraftforge.client.event.RenderHighlightEvent;
/*     */ import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
/*     */ import net.minecraftforge.client.gui.overlay.ForgeGui;
/*     */ import net.minecraftforge.client.gui.overlay.IGuiOverlay;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ import org.joml.Matrix4f;
/*     */ 
/*     */ public class TransmutationRenderingOverlay implements IGuiOverlay {
/*  34 */   private final Minecraft mc = Minecraft.m_91087_();
/*     */   @Nullable
/*     */   private BlockState transmutationResult;
/*     */   private long lastGameTime;
/*     */   
/*     */   public TransmutationRenderingOverlay() {
/*  40 */     MinecraftForge.EVENT_BUS.addListener(this::onOverlay);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(ForgeGui gui, GuiGraphics graphics, float partialTicks, int width, int height) {
/*  45 */     if (!this.mc.f_91066_.f_92062_ && this.transmutationResult != null) {
/*  46 */       Block block = this.transmutationResult.m_60734_(); if (block instanceof LiquidBlock) { LiquidBlock liquidBlock = (LiquidBlock)block;
/*  47 */         IClientFluidTypeExtensions properties = IClientFluidTypeExtensions.of((Fluid)liquidBlock.getFluid());
/*  48 */         int color = properties.getTintColor();
/*  49 */         float red = (color >> 16 & 0xFF) / 255.0F;
/*  50 */         float green = (color >> 8 & 0xFF) / 255.0F;
/*  51 */         float blue = (color & 0xFF) / 255.0F;
/*  52 */         float alpha = (color >> 24 & 0xFF) / 255.0F;
/*  53 */         TextureAtlasSprite sprite = this.mc.m_91258_(TextureAtlas.f_118259_).apply(properties.getStillTexture());
/*  54 */         graphics.m_280565_(1, 1, 0, 16, 16, sprite, red, green, blue, alpha); }
/*     */       
/*     */       else
/*     */       
/*  58 */       { graphics.m_280480_(new ItemStack((ItemLike)this.transmutationResult.m_60734_()), 1, 1); }
/*     */       
/*  60 */       long gameTime = (this.mc.f_91073_ == null) ? 0L : this.mc.f_91073_.m_46467_();
/*  61 */       if (this.lastGameTime != gameTime) {
/*     */ 
/*     */ 
/*     */         
/*  65 */         this.transmutationResult = null;
/*  66 */         this.lastGameTime = gameTime;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onOverlay(RenderHighlightEvent.Block event) { Player player;
/*  72 */     Camera activeRenderInfo = event.getCamera();
/*  73 */     Entity entity = activeRenderInfo.m_90592_(); if (entity instanceof Player) { player = (Player)entity; }
/*     */     else
/*     */     { return; }
/*  76 */      this.lastGameTime = (this.mc.f_91073_ == null) ? 0L : this.mc.f_91073_.m_46467_();
/*  77 */     Level level = player.m_9236_();
/*  78 */     ItemStack stack = player.m_21205_();
/*  79 */     if (stack.m_41619_()) {
/*  80 */       stack = player.m_21206_();
/*     */     }
/*  82 */     if (!stack.m_41619_()) { Item item = stack.m_41720_(); if (item instanceof PhilosophersStone) { PhilosophersStone philoStone = (PhilosophersStone)item;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  88 */         BlockHitResult rtr = philoStone.getHitBlock(player);
/*  89 */         if (rtr.m_6662_() == HitResult.Type.BLOCK) {
/*  90 */           BlockState current = level.m_8055_(rtr.m_82425_());
/*  91 */           this.transmutationResult = WorldTransmutations.getWorldTransmutation(current, player.m_36341_());
/*  92 */           if (this.transmutationResult != null) {
/*  93 */             Vec3 viewPosition = activeRenderInfo.m_90583_();
/*  94 */             int charge = philoStone.getCharge(stack);
/*  95 */             byte mode = philoStone.getMode(stack);
/*  96 */             float alpha = ProjectEConfig.client.pulsatingOverlay.get() ? (getPulseProportion() * 0.6F) : 0.35F;
/*  97 */             VertexConsumer builder = event.getMultiBufferSource().m_6299_(PERenderType.TRANSMUTATION_OVERLAY);
/*  98 */             PoseStack matrix = event.getPoseStack();
/*  99 */             matrix.m_85836_();
/* 100 */             matrix.m_85837_(-viewPosition.f_82479_, -viewPosition.f_82480_, -viewPosition.f_82481_);
/* 101 */             CollisionContext selectionContext = CollisionContext.m_82750_((Entity)player);
/* 102 */             for (BlockPos pos : PhilosophersStone.getChanges(level, rtr.m_82425_(), player, rtr.m_82434_(), mode, charge).keySet()) {
/* 103 */               BlockState state = level.m_8055_(pos);
/* 104 */               if (!state.m_60795_()) {
/* 105 */                 VoxelShape shape = state.m_60651_((BlockGetter)level, pos, selectionContext);
/* 106 */                 if (!shape.m_83281_()) {
/* 107 */                   matrix.m_85836_();
/* 108 */                   matrix.m_252880_(pos.m_123341_(), pos.m_123342_(), pos.m_123343_());
/* 109 */                   Matrix4f matrix4f = matrix.m_85850_().m_252922_();
/* 110 */                   shape.m_83286_((minX, minY, minZ, maxX, maxY, maxZ) -> addBox(builder, matrix4f, alpha, (float)minX, (float)minY, (float)minZ, (float)maxX, (float)maxY, (float)maxZ));
/*     */                   
/* 112 */                   matrix.m_85849_();
/*     */                 } 
/*     */               } 
/*     */             } 
/* 116 */             matrix.m_85849_();
/*     */           } 
/*     */         } else {
/* 119 */           this.transmutationResult = null;
/*     */         } 
/*     */         return; }
/*     */        }
/*     */     
/*     */     this.transmutationResult = null; } private void addBox(VertexConsumer builder, Matrix4f matrix4f, float alpha, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
/* 125 */     builder.m_252986_(matrix4f, minX, maxY, minZ).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_5752_();
/* 126 */     builder.m_252986_(matrix4f, maxX, maxY, minZ).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_5752_();
/* 127 */     builder.m_252986_(matrix4f, maxX, maxY, maxZ).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_5752_();
/* 128 */     builder.m_252986_(matrix4f, minX, maxY, maxZ).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_5752_();
/*     */ 
/*     */     
/* 131 */     builder.m_252986_(matrix4f, minX, minY, minZ).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_5752_();
/* 132 */     builder.m_252986_(matrix4f, maxX, minY, minZ).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_5752_();
/* 133 */     builder.m_252986_(matrix4f, maxX, minY, maxZ).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_5752_();
/* 134 */     builder.m_252986_(matrix4f, minX, minY, maxZ).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_5752_();
/*     */ 
/*     */     
/* 137 */     builder.m_252986_(matrix4f, maxX, maxY, maxZ).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_5752_();
/* 138 */     builder.m_252986_(matrix4f, minX, maxY, maxZ).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_5752_();
/* 139 */     builder.m_252986_(matrix4f, minX, minY, maxZ).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_5752_();
/* 140 */     builder.m_252986_(matrix4f, maxX, minY, maxZ).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_5752_();
/*     */ 
/*     */     
/* 143 */     builder.m_252986_(matrix4f, maxX, minY, minZ).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_5752_();
/* 144 */     builder.m_252986_(matrix4f, minX, minY, minZ).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_5752_();
/* 145 */     builder.m_252986_(matrix4f, minX, maxY, minZ).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_5752_();
/* 146 */     builder.m_252986_(matrix4f, maxX, maxY, minZ).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_5752_();
/*     */ 
/*     */     
/* 149 */     builder.m_252986_(matrix4f, minX, maxY, maxZ).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_5752_();
/* 150 */     builder.m_252986_(matrix4f, minX, maxY, minZ).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_5752_();
/* 151 */     builder.m_252986_(matrix4f, minX, minY, minZ).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_5752_();
/* 152 */     builder.m_252986_(matrix4f, minX, minY, maxZ).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_5752_();
/*     */ 
/*     */     
/* 155 */     builder.m_252986_(matrix4f, maxX, maxY, maxZ).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_5752_();
/* 156 */     builder.m_252986_(matrix4f, maxX, maxY, minZ).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_5752_();
/* 157 */     builder.m_252986_(matrix4f, maxX, minY, minZ).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_5752_();
/* 158 */     builder.m_252986_(matrix4f, maxX, minY, maxZ).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_5752_();
/*     */   }
/*     */   
/*     */   private float getPulseProportion() {
/* 162 */     return (float)(0.5D * Math.sin(System.currentTimeMillis() / 350.0D) + 0.5D);
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\rendering\TransmutationRenderingOverlay.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */