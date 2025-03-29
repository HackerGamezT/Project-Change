/*     */ package moze_intel.projecte.utils.text;
/*     */ 
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.network.chat.ClickEvent;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.network.chat.HoverEvent;
/*     */ import net.minecraft.network.chat.MutableComponent;
/*     */ import net.minecraft.network.chat.Style;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.material.Fluid;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TextComponentUtil
/*     */ {
/*     */   public static MutableComponent build(Object... components) {
/*  26 */     MutableComponent result = null;
/*  27 */     Style cachedStyle = Style.f_131099_;
/*  28 */     for (Object component : components) {
/*  29 */       if (component != null) {
/*     */ 
/*     */ 
/*     */         
/*  33 */         MutableComponent current = null;
/*  34 */         if (component instanceof IHasTextComponent) { IHasTextComponent hasTextComponent = (IHasTextComponent)component;
/*  35 */           current = hasTextComponent.getTextComponent().m_6881_(); }
/*  36 */         else if (component instanceof IHasTranslationKey) { IHasTranslationKey hasTranslationKey = (IHasTranslationKey)component;
/*  37 */           current = translate(hasTranslationKey.getTranslationKey(), new Object[0]); }
/*  38 */         else if (component instanceof Component) { Component c = (Component)component;
/*     */           
/*  40 */           current = c.m_6881_(); }
/*  41 */         else if (component instanceof ChatFormatting)
/*  42 */         { cachedStyle = cachedStyle.m_131157_((ChatFormatting)component); }
/*  43 */         else if (component instanceof ClickEvent)
/*  44 */         { cachedStyle = cachedStyle.m_131142_((ClickEvent)component); }
/*  45 */         else if (component instanceof HoverEvent)
/*  46 */         { cachedStyle = cachedStyle.m_131144_((HoverEvent)component); }
/*  47 */         else if (component instanceof Block) { Block block = (Block)component;
/*  48 */           current = translate(block.m_7705_(), new Object[0]); }
/*  49 */         else if (component instanceof Item) { Item item = (Item)component;
/*  50 */           current = translate(item.m_5524_(), new Object[0]); }
/*  51 */         else if (component instanceof ItemStack) { ItemStack stack = (ItemStack)component;
/*  52 */           current = stack.m_41786_().m_6881_(); }
/*  53 */         else if (component instanceof FluidStack) { FluidStack stack = (FluidStack)component;
/*  54 */           current = stack.getDisplayName().m_6881_(); }
/*  55 */         else if (component instanceof Fluid) { Fluid fluid = (Fluid)component;
/*  56 */           current = translate(fluid.getFluidType().getDescriptionId(), new Object[0]); }
/*     */         else
/*     */         
/*  59 */         { current = getString(component.toString()); }
/*     */         
/*  61 */         if (current != null) {
/*     */ 
/*     */ 
/*     */           
/*  65 */           if (!cachedStyle.m_131179_()) {
/*     */             
/*  67 */             current.m_6270_(cachedStyle);
/*  68 */             cachedStyle = Style.f_131099_;
/*     */           } 
/*  70 */           if (result == null) {
/*  71 */             result = current;
/*     */           } else {
/*  73 */             result.m_7220_((Component)current);
/*     */           } 
/*     */         } 
/*     */       } 
/*  77 */     }  return result;
/*     */   }
/*     */   
/*     */   public static MutableComponent getString(String component) {
/*  81 */     return Component.m_237113_(cleanString(component));
/*     */   }
/*     */   
/*     */   private static String cleanString(String component) {
/*  85 */     return component.replace(" ", " ");
/*     */   }
/*     */   
/*     */   public static MutableComponent translate(String key, Object... args) {
/*  89 */     return Component.m_237110_(key, args);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MutableComponent smartTranslate(String key, Object... components) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: arraylength
/*     */     //   2: ifne -> 14
/*     */     //   5: aload_0
/*     */     //   6: iconst_0
/*     */     //   7: anewarray java/lang/Object
/*     */     //   10: invokestatic translate : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/network/chat/MutableComponent;
/*     */     //   13: areturn
/*     */     //   14: new java/util/ArrayList
/*     */     //   17: dup
/*     */     //   18: invokespecial <init> : ()V
/*     */     //   21: astore_2
/*     */     //   22: getstatic net/minecraft/network/chat/Style.f_131099_ : Lnet/minecraft/network/chat/Style;
/*     */     //   25: astore_3
/*     */     //   26: aload_1
/*     */     //   27: astore #4
/*     */     //   29: aload #4
/*     */     //   31: arraylength
/*     */     //   32: istore #5
/*     */     //   34: iconst_0
/*     */     //   35: istore #6
/*     */     //   37: iload #6
/*     */     //   39: iload #5
/*     */     //   41: if_icmpge -> 530
/*     */     //   44: aload #4
/*     */     //   46: iload #6
/*     */     //   48: aaload
/*     */     //   49: astore #7
/*     */     //   51: aload #7
/*     */     //   53: ifnonnull -> 71
/*     */     //   56: aload_2
/*     */     //   57: aconst_null
/*     */     //   58: invokeinterface add : (Ljava/lang/Object;)Z
/*     */     //   63: pop
/*     */     //   64: getstatic net/minecraft/network/chat/Style.f_131099_ : Lnet/minecraft/network/chat/Style;
/*     */     //   67: astore_3
/*     */     //   68: goto -> 524
/*     */     //   71: aconst_null
/*     */     //   72: astore #8
/*     */     //   74: aload #7
/*     */     //   76: instanceof moze_intel/projecte/utils/text/IHasTextComponent
/*     */     //   79: ifeq -> 106
/*     */     //   82: aload #7
/*     */     //   84: checkcast moze_intel/projecte/utils/text/IHasTextComponent
/*     */     //   87: astore #9
/*     */     //   89: aload #9
/*     */     //   91: invokeinterface getTextComponent : ()Lnet/minecraft/network/chat/Component;
/*     */     //   96: invokeinterface m_6881_ : ()Lnet/minecraft/network/chat/MutableComponent;
/*     */     //   101: astore #8
/*     */     //   103: goto -> 454
/*     */     //   106: aload #7
/*     */     //   108: instanceof moze_intel/projecte/utils/text/IHasTranslationKey
/*     */     //   111: ifeq -> 140
/*     */     //   114: aload #7
/*     */     //   116: checkcast moze_intel/projecte/utils/text/IHasTranslationKey
/*     */     //   119: astore #10
/*     */     //   121: aload #10
/*     */     //   123: invokeinterface getTranslationKey : ()Ljava/lang/String;
/*     */     //   128: iconst_0
/*     */     //   129: anewarray java/lang/Object
/*     */     //   132: invokestatic translate : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/network/chat/MutableComponent;
/*     */     //   135: astore #8
/*     */     //   137: goto -> 454
/*     */     //   140: aload #7
/*     */     //   142: instanceof net/minecraft/world/level/block/Block
/*     */     //   145: ifeq -> 172
/*     */     //   148: aload #7
/*     */     //   150: checkcast net/minecraft/world/level/block/Block
/*     */     //   153: astore #11
/*     */     //   155: aload #11
/*     */     //   157: invokevirtual m_7705_ : ()Ljava/lang/String;
/*     */     //   160: iconst_0
/*     */     //   161: anewarray java/lang/Object
/*     */     //   164: invokestatic translate : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/network/chat/MutableComponent;
/*     */     //   167: astore #8
/*     */     //   169: goto -> 454
/*     */     //   172: aload #7
/*     */     //   174: instanceof net/minecraft/world/item/Item
/*     */     //   177: ifeq -> 204
/*     */     //   180: aload #7
/*     */     //   182: checkcast net/minecraft/world/item/Item
/*     */     //   185: astore #12
/*     */     //   187: aload #12
/*     */     //   189: invokevirtual m_5524_ : ()Ljava/lang/String;
/*     */     //   192: iconst_0
/*     */     //   193: anewarray java/lang/Object
/*     */     //   196: invokestatic translate : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/network/chat/MutableComponent;
/*     */     //   199: astore #8
/*     */     //   201: goto -> 454
/*     */     //   204: aload #7
/*     */     //   206: instanceof net/minecraft/world/item/ItemStack
/*     */     //   209: ifeq -> 234
/*     */     //   212: aload #7
/*     */     //   214: checkcast net/minecraft/world/item/ItemStack
/*     */     //   217: astore #13
/*     */     //   219: aload #13
/*     */     //   221: invokevirtual m_41786_ : ()Lnet/minecraft/network/chat/Component;
/*     */     //   224: invokeinterface m_6881_ : ()Lnet/minecraft/network/chat/MutableComponent;
/*     */     //   229: astore #8
/*     */     //   231: goto -> 454
/*     */     //   234: aload #7
/*     */     //   236: instanceof net/minecraftforge/fluids/FluidStack
/*     */     //   239: ifeq -> 264
/*     */     //   242: aload #7
/*     */     //   244: checkcast net/minecraftforge/fluids/FluidStack
/*     */     //   247: astore #14
/*     */     //   249: aload #14
/*     */     //   251: invokevirtual getDisplayName : ()Lnet/minecraft/network/chat/Component;
/*     */     //   254: invokeinterface m_6881_ : ()Lnet/minecraft/network/chat/MutableComponent;
/*     */     //   259: astore #8
/*     */     //   261: goto -> 454
/*     */     //   264: aload #7
/*     */     //   266: instanceof net/minecraft/world/level/material/Fluid
/*     */     //   269: ifeq -> 299
/*     */     //   272: aload #7
/*     */     //   274: checkcast net/minecraft/world/level/material/Fluid
/*     */     //   277: astore #15
/*     */     //   279: aload #15
/*     */     //   281: invokevirtual getFluidType : ()Lnet/minecraftforge/fluids/FluidType;
/*     */     //   284: invokevirtual getDescriptionId : ()Ljava/lang/String;
/*     */     //   287: iconst_0
/*     */     //   288: anewarray java/lang/Object
/*     */     //   291: invokestatic translate : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/network/chat/MutableComponent;
/*     */     //   294: astore #8
/*     */     //   296: goto -> 454
/*     */     //   299: aload #7
/*     */     //   301: instanceof net/minecraft/ChatFormatting
/*     */     //   304: ifeq -> 333
/*     */     //   307: aload #7
/*     */     //   309: checkcast net/minecraft/ChatFormatting
/*     */     //   312: astore #16
/*     */     //   314: aload_3
/*     */     //   315: aload #16
/*     */     //   317: invokestatic hasStyleType : (Lnet/minecraft/network/chat/Style;Lnet/minecraft/ChatFormatting;)Z
/*     */     //   320: ifne -> 333
/*     */     //   323: aload_3
/*     */     //   324: aload #16
/*     */     //   326: invokevirtual m_131157_ : (Lnet/minecraft/ChatFormatting;)Lnet/minecraft/network/chat/Style;
/*     */     //   329: astore_3
/*     */     //   330: goto -> 524
/*     */     //   333: aload #7
/*     */     //   335: instanceof net/minecraft/network/chat/ClickEvent
/*     */     //   338: ifeq -> 361
/*     */     //   341: aload_3
/*     */     //   342: invokevirtual m_131182_ : ()Lnet/minecraft/network/chat/ClickEvent;
/*     */     //   345: ifnonnull -> 361
/*     */     //   348: aload_3
/*     */     //   349: aload #7
/*     */     //   351: checkcast net/minecraft/network/chat/ClickEvent
/*     */     //   354: invokevirtual m_131142_ : (Lnet/minecraft/network/chat/ClickEvent;)Lnet/minecraft/network/chat/Style;
/*     */     //   357: astore_3
/*     */     //   358: goto -> 524
/*     */     //   361: aload #7
/*     */     //   363: instanceof net/minecraft/network/chat/HoverEvent
/*     */     //   366: ifeq -> 389
/*     */     //   369: aload_3
/*     */     //   370: invokevirtual m_131186_ : ()Lnet/minecraft/network/chat/HoverEvent;
/*     */     //   373: ifnonnull -> 389
/*     */     //   376: aload_3
/*     */     //   377: aload #7
/*     */     //   379: checkcast net/minecraft/network/chat/HoverEvent
/*     */     //   382: invokevirtual m_131144_ : (Lnet/minecraft/network/chat/HoverEvent;)Lnet/minecraft/network/chat/Style;
/*     */     //   385: astore_3
/*     */     //   386: goto -> 524
/*     */     //   389: aload_3
/*     */     //   390: invokevirtual m_131179_ : ()Z
/*     */     //   393: ifne -> 436
/*     */     //   396: aload #7
/*     */     //   398: instanceof net/minecraft/network/chat/Component
/*     */     //   401: ifeq -> 423
/*     */     //   404: aload #7
/*     */     //   406: checkcast net/minecraft/network/chat/Component
/*     */     //   409: astore #17
/*     */     //   411: aload #17
/*     */     //   413: invokeinterface m_6881_ : ()Lnet/minecraft/network/chat/MutableComponent;
/*     */     //   418: astore #8
/*     */     //   420: goto -> 454
/*     */     //   423: aload #7
/*     */     //   425: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   428: invokestatic getString : (Ljava/lang/String;)Lnet/minecraft/network/chat/MutableComponent;
/*     */     //   431: astore #8
/*     */     //   433: goto -> 454
/*     */     //   436: aload #7
/*     */     //   438: instanceof java/lang/String
/*     */     //   441: ifeq -> 454
/*     */     //   444: aload #7
/*     */     //   446: checkcast java/lang/String
/*     */     //   449: invokestatic cleanString : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   452: astore #7
/*     */     //   454: aload_3
/*     */     //   455: invokevirtual m_131179_ : ()Z
/*     */     //   458: ifne -> 498
/*     */     //   461: aload #8
/*     */     //   463: ifnonnull -> 478
/*     */     //   466: aload_2
/*     */     //   467: aload #7
/*     */     //   469: invokeinterface add : (Ljava/lang/Object;)Z
/*     */     //   474: pop
/*     */     //   475: goto -> 491
/*     */     //   478: aload_2
/*     */     //   479: aload #8
/*     */     //   481: aload_3
/*     */     //   482: invokevirtual m_6270_ : (Lnet/minecraft/network/chat/Style;)Lnet/minecraft/network/chat/MutableComponent;
/*     */     //   485: invokeinterface add : (Ljava/lang/Object;)Z
/*     */     //   490: pop
/*     */     //   491: getstatic net/minecraft/network/chat/Style.f_131099_ : Lnet/minecraft/network/chat/Style;
/*     */     //   494: astore_3
/*     */     //   495: goto -> 524
/*     */     //   498: aload #8
/*     */     //   500: ifnonnull -> 515
/*     */     //   503: aload_2
/*     */     //   504: aload #7
/*     */     //   506: invokeinterface add : (Ljava/lang/Object;)Z
/*     */     //   511: pop
/*     */     //   512: goto -> 524
/*     */     //   515: aload_2
/*     */     //   516: aload #8
/*     */     //   518: invokeinterface add : (Ljava/lang/Object;)Z
/*     */     //   523: pop
/*     */     //   524: iinc #6, 1
/*     */     //   527: goto -> 37
/*     */     //   530: aload_3
/*     */     //   531: invokevirtual m_131179_ : ()Z
/*     */     //   534: ifne -> 550
/*     */     //   537: aload_2
/*     */     //   538: aload_1
/*     */     //   539: aload_1
/*     */     //   540: arraylength
/*     */     //   541: iconst_1
/*     */     //   542: isub
/*     */     //   543: aaload
/*     */     //   544: invokeinterface add : (Ljava/lang/Object;)Z
/*     */     //   549: pop
/*     */     //   550: aload_0
/*     */     //   551: aload_2
/*     */     //   552: invokeinterface toArray : ()[Ljava/lang/Object;
/*     */     //   557: invokestatic translate : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/network/chat/MutableComponent;
/*     */     //   560: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #93	-> 0
/*     */     //   #95	-> 5
/*     */     //   #97	-> 14
/*     */     //   #98	-> 22
/*     */     //   #99	-> 26
/*     */     //   #100	-> 51
/*     */     //   #103	-> 56
/*     */     //   #104	-> 64
/*     */     //   #105	-> 68
/*     */     //   #107	-> 71
/*     */     //   #108	-> 74
/*     */     //   #109	-> 89
/*     */     //   #110	-> 106
/*     */     //   #111	-> 121
/*     */     //   #112	-> 140
/*     */     //   #113	-> 155
/*     */     //   #114	-> 172
/*     */     //   #115	-> 187
/*     */     //   #116	-> 204
/*     */     //   #117	-> 219
/*     */     //   #118	-> 234
/*     */     //   #119	-> 249
/*     */     //   #120	-> 264
/*     */     //   #121	-> 279
/*     */     //   #124	-> 299
/*     */     //   #126	-> 323
/*     */     //   #127	-> 330
/*     */     //   #128	-> 333
/*     */     //   #130	-> 348
/*     */     //   #131	-> 358
/*     */     //   #132	-> 361
/*     */     //   #134	-> 376
/*     */     //   #135	-> 386
/*     */     //   #136	-> 389
/*     */     //   #139	-> 396
/*     */     //   #141	-> 411
/*     */     //   #144	-> 423
/*     */     //   #146	-> 436
/*     */     //   #148	-> 444
/*     */     //   #150	-> 454
/*     */     //   #153	-> 461
/*     */     //   #154	-> 466
/*     */     //   #157	-> 478
/*     */     //   #159	-> 491
/*     */     //   #160	-> 498
/*     */     //   #162	-> 503
/*     */     //   #165	-> 515
/*     */     //   #99	-> 524
/*     */     //   #168	-> 530
/*     */     //   #171	-> 537
/*     */     //   #173	-> 550
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   89	17	9	hasTextComponent	Lmoze_intel/projecte/utils/text/IHasTextComponent;
/*     */     //   121	19	10	hasTranslationKey	Lmoze_intel/projecte/utils/text/IHasTranslationKey;
/*     */     //   155	17	11	block	Lnet/minecraft/world/level/block/Block;
/*     */     //   187	17	12	item	Lnet/minecraft/world/item/Item;
/*     */     //   219	15	13	stack	Lnet/minecraft/world/item/ItemStack;
/*     */     //   249	15	14	stack	Lnet/minecraftforge/fluids/FluidStack;
/*     */     //   279	20	15	fluid	Lnet/minecraft/world/level/material/Fluid;
/*     */     //   314	19	16	formatting	Lnet/minecraft/ChatFormatting;
/*     */     //   411	12	17	c	Lnet/minecraft/network/chat/Component;
/*     */     //   74	450	8	current	Lnet/minecraft/network/chat/MutableComponent;
/*     */     //   51	473	7	component	Ljava/lang/Object;
/*     */     //   0	561	0	key	Ljava/lang/String;
/*     */     //   0	561	1	components	[Ljava/lang/Object;
/*     */     //   22	539	2	args	Ljava/util/List;
/*     */     //   26	535	3	cachedStyle	Lnet/minecraft/network/chat/Style;
/*     */     // Local variable type table:
/*     */     //   start	length	slot	name	signature
/*     */     //   22	539	2	args	Ljava/util/List<Ljava/lang/Object;>;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean hasStyleType(Style current, ChatFormatting formatting) {
/* 177 */     switch (formatting) { case OBFUSCATED: case BOLD: case STRIKETHROUGH: case UNDERLINE: case ITALIC: case RESET:  }  return 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 184 */       (current.m_131135_() != null);
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\project\\utils\text\TextComponentUtil.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */