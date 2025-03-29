/*     */ package moze_intel.projecte.emc.json;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSerializer;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import moze_intel.projecte.api.imc.NSSCreatorInfo;
/*     */ import moze_intel.projecte.api.nss.NSSCreator;
/*     */ import moze_intel.projecte.api.nss.NSSFake;
/*     */ import moze_intel.projecte.api.nss.NSSFluid;
/*     */ import moze_intel.projecte.api.nss.NSSItem;
/*     */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*     */ import net.minecraft.ResourceLocationException;
/*     */ import net.minecraft.nbt.TagParser;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraftforge.fml.InterModComms;
/*     */ 
/*     */ 
/*     */ public class NSSSerializer
/*     */   implements JsonSerializer<NormalizedSimpleStack>, JsonDeserializer<NormalizedSimpleStack>
/*     */ {
/*  30 */   public static final NSSSerializer INSTANCE = new NSSSerializer(); public static final NSSCreator itemCreator;
/*     */   public static final NSSCreator fluidCreator;
/*  32 */   public static final NSSCreator fakeCreator = NSSFake::create;
/*     */   static {
/*  34 */     itemCreator = (itemName -> {
/*     */         if (itemName.startsWith("#")) {
/*     */           return (NormalizedSimpleStack)NSSItem.createTag(getResourceLocation(itemName.substring(1), "item tag"));
/*     */         }
/*     */         int nbtStart = itemName.indexOf('{');
/*     */         ResourceLocation resourceLocation = getResourceLocation((nbtStart == -1) ? itemName : itemName.substring(0, nbtStart), "item");
/*     */         if (nbtStart == -1) {
/*     */           return (NormalizedSimpleStack)NSSItem.createItem(resourceLocation);
/*     */         }
/*     */         String nbtAsString = itemName.substring(nbtStart);
/*     */         try {
/*     */           return (NormalizedSimpleStack)NSSItem.createItem(resourceLocation, TagParser.m_129359_(nbtAsString));
/*  46 */         } catch (CommandSyntaxException e) {
/*     */           throw new JsonParseException("Malformed NBT compound", e);
/*     */         } 
/*     */       });
/*     */     
/*  51 */     fluidCreator = (fluidName -> {
/*     */         if (fluidName.startsWith("#")) {
/*     */           return (NormalizedSimpleStack)NSSFluid.createTag(getResourceLocation(fluidName.substring(1), "fluid tag"));
/*     */         }
/*     */         int nbtStart = fluidName.indexOf('{');
/*     */         ResourceLocation resourceLocation = getResourceLocation((nbtStart == -1) ? fluidName : fluidName.substring(0, nbtStart), "fluid");
/*     */         if (nbtStart == -1) {
/*     */           return (NormalizedSimpleStack)NSSFluid.createFluid(resourceLocation);
/*     */         }
/*     */         String nbtAsString = fluidName.substring(nbtStart);
/*     */         try {
/*     */           return (NormalizedSimpleStack)NSSFluid.createFluid(resourceLocation, TagParser.m_129359_(nbtAsString));
/*  63 */         } catch (CommandSyntaxException e) {
/*     */           throw new JsonParseException("Malformed NBT compound", e);
/*     */         } 
/*     */       });
/*     */   }
/*     */   private static ResourceLocation getResourceLocation(String s, String type) throws JsonParseException {
/*     */     try {
/*  70 */       return new ResourceLocation(s);
/*  71 */     } catch (ResourceLocationException e) {
/*  72 */       throw new JsonParseException("Malformed " + type + " ID", e);
/*     */     } 
/*     */   }
/*     */   
/*  76 */   private Map<String, NSSCreator> creators = Collections.emptyMap();
/*     */ 
/*     */   
/*     */   public void setCreators(Map<String, NSSCreator> creators) {
/*  80 */     this.creators = (Map<String, NSSCreator>)ImmutableMap.copyOf(creators);
/*     */   }
/*     */ 
/*     */   
/*     */   public NormalizedSimpleStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/*  85 */     return deserialize(json.getAsString());
/*     */   }
/*     */   
/*     */   public NormalizedSimpleStack deserialize(String s) {
/*  89 */     if (s.contains("|")) {
/*  90 */       String[] parts = s.split("\\|");
/*  91 */       String key = parts[0];
/*  92 */       if (this.creators.containsKey(key)) {
/*  93 */         return ((NSSCreator)this.creators.get(key)).create(parts[1]);
/*     */       }
/*     */     } 
/*     */     
/*  97 */     return itemCreator.create(s);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonElement serialize(NormalizedSimpleStack src, Type typeOfSrc, JsonSerializationContext context) {
/* 102 */     return (JsonElement)new JsonPrimitive(src.json());
/*     */   }
/*     */   
/*     */   public static void init() {
/* 106 */     registerDefault("FAKE", fakeCreator);
/* 107 */     registerDefault("ITEM", itemCreator);
/* 108 */     registerDefault("FLUID", fluidCreator);
/*     */   }
/*     */   
/*     */   private static void registerDefault(String key, NSSCreator creator) {
/* 112 */     InterModComms.sendTo("projecte", "register_nss_serializer", () -> new NSSCreatorInfo(key, creator));
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\json\NSSSerializer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */