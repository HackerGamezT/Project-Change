/*     */ package moze_intel.projecte.emc;
/*     */ import com.electronwill.nightconfig.core.file.CommentedFileConfig;
/*     */ import com.mojang.logging.LogUtils;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import moze_intel.projecte.PECore;
/*     */ import moze_intel.projecte.api.ItemInfo;
/*     */ import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
/*     */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*     */ import moze_intel.projecte.api.event.EMCRemapEvent;
/*     */ import moze_intel.projecte.api.mapper.IEMCMapper;
/*     */ import moze_intel.projecte.api.mapper.arithmetic.IValueArithmetic;
/*     */ import moze_intel.projecte.api.mapper.collector.IExtendedMappingCollector;
/*     */ import moze_intel.projecte.api.mapper.collector.IMappingCollector;
/*     */ import moze_intel.projecte.api.nss.NSSItem;
/*     */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*     */ import moze_intel.projecte.config.ProjectEConfig;
/*     */ import moze_intel.projecte.emc.arithmetic.HiddenBigFractionArithmetic;
/*     */ import moze_intel.projecte.emc.collector.DumpToFileCollector;
/*     */ import moze_intel.projecte.emc.collector.LongToBigFractionCollector;
/*     */ import moze_intel.projecte.emc.generator.BigFractionToLongGenerator;
/*     */ import moze_intel.projecte.emc.mappers.TagMapper;
/*     */ import moze_intel.projecte.emc.pregenerated.PregeneratedEMC;
/*     */ import moze_intel.projecte.impl.capability.KnowledgeImpl;
/*     */ import moze_intel.projecte.network.PacketHandler;
/*     */ import moze_intel.projecte.network.packets.IPEPacket;
/*     */ import moze_intel.projecte.network.packets.to_client.SyncEmcPKT;
/*     */ import moze_intel.projecte.network.packets.to_client.knowledge.UpdateTransmutationTargetsPkt;
/*     */ import moze_intel.projecte.shaded.org.apache.commons.math3.fraction.BigFraction;
/*     */ import moze_intel.projecte.utils.AnnotationHelper;
/*     */ import net.minecraft.core.RegistryAccess;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.server.ReloadableServerResources;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.server.packs.resources.ResourceManager;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.server.ServerLifecycleHooks;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public final class EMCMappingHandler {
/*  51 */   private static final List<IEMCMapper<NormalizedSimpleStack, Long>> mappers = new ArrayList<>();
/*  52 */   private static final Map<ItemInfo, Long> emc = new HashMap<>();
/*  53 */   private static int loadIndex = -1;
/*     */ 
/*     */   
/*     */   public static void loadMappers() {
/*  57 */     if (mappers.isEmpty()) {
/*     */       
/*  59 */       mappers.addAll(AnnotationHelper.getEMCMappers());
/*     */ 
/*     */       
/*  62 */       mappers.add(new TagMapper());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static <T> T getOrSetDefault(CommentedFileConfig config, String key, String comment, T defaultValue) {
/*  67 */     T val = (T)config.get(key);
/*  68 */     if (val == null) {
/*  69 */       val = defaultValue;
/*  70 */       config.set(key, val);
/*  71 */       config.setComment(key, comment);
/*     */     } 
/*  73 */     return val;
/*     */   }
/*     */   
/*     */   public static void map(ReloadableServerResources serverResources, RegistryAccess registryAccess, ResourceManager resourceManager) {
/*     */     DumpToFileCollector dumpToFileCollector;
/*  78 */     clearEmcMap();
/*  79 */     SimpleGraphMapper<NormalizedSimpleStack, BigFraction, IValueArithmetic<BigFraction>> mapper = (SimpleGraphMapper)new SimpleGraphMapper<>(new HiddenBigFractionArithmetic());
/*  80 */     BigFractionToLongGenerator bigFractionToLongGenerator = new BigFractionToLongGenerator(mapper);
/*  81 */     LongToBigFractionCollector longToBigFractionCollector = new LongToBigFractionCollector((IExtendedMappingCollector)mapper);
/*     */     
/*  83 */     Path path = ProjectEConfig.CONFIG_DIR.resolve("mapping.toml");
/*     */     try {
/*  85 */       if (path.toFile().createNewFile()) {
/*  86 */         PECore.debugLog("Created mapping.toml", new Object[0]);
/*     */       }
/*  88 */     } catch (IOException ex) {
/*  89 */       PECore.LOGGER.error("Couldn't create mapping.toml", ex);
/*     */     } 
/*     */     
/*  92 */     CommentedFileConfig config = (CommentedFileConfig)CommentedFileConfig.builder(path).build();
/*  93 */     config.load();
/*     */     
/*  95 */     boolean dumpToFile = ((Boolean)getOrSetDefault(config, "general.dumpEverythingToFile", "Want to take a look at the internals of EMC Calculation? Enable this to write all the conversions and setValue-Commands to config/ProjectE/mappingdump.json", Boolean.valueOf(false))).booleanValue();
/*  96 */     boolean shouldUsePregenerated = ((Boolean)getOrSetDefault(config, "general.pregenerate", "When the next EMC mapping occurs write the results to config/ProjectE/pregenerated_emc.json and only ever run the mapping again when that file does not exist, this setting is set to false, or an error occurred parsing that file.", 
/*  97 */         Boolean.valueOf(false))).booleanValue();
/*  98 */     boolean logFoundExploits = ((Boolean)getOrSetDefault(config, "general.logEMCExploits", "Log known EMC Exploits. This can not and will not find all possible exploits. This will only find exploits that result in fixed/custom emc values that the algorithm did not overwrite. Exploits that derive from conversions that are unknown to ProjectE will not be found.", 
/*     */         
/* 100 */         Boolean.valueOf(true))).booleanValue();
/*     */     
/* 102 */     if (dumpToFile) {
/* 103 */       dumpToFileCollector = new DumpToFileCollector(ProjectEConfig.CONFIG_DIR.resolve("mappingdump.json").toFile(), (IExtendedMappingCollector)longToBigFractionCollector);
/*     */     }
/*     */     
/* 106 */     File pregeneratedEmcFile = Paths.get("config", new String[] { "ProjectE", "pregenerated_emc.json" }).toFile();
/*     */     Map<NormalizedSimpleStack, Long> graphMapperValues;
/* 108 */     if (shouldUsePregenerated && pregeneratedEmcFile.canRead() && PregeneratedEMC.tryRead(pregeneratedEmcFile, graphMapperValues = new HashMap<>())) {
/* 109 */       PECore.LOGGER.info("Loaded {} values from pregenerated EMC File", Integer.valueOf(graphMapperValues.size()));
/*     */     } else {
/* 111 */       SimpleGraphMapper.setLogFoundExploits(logFoundExploits);
/*     */       
/* 113 */       PECore.debugLog("Starting to collect Mappings...", new Object[0]);
/* 114 */       for (IEMCMapper<NormalizedSimpleStack, Long> emcMapper : mappers) {
/*     */         try {
/* 116 */           if (((Boolean)getOrSetDefault(config, "enabledMappers." + emcMapper.getName(), emcMapper.getDescription(), Boolean.valueOf(emcMapper.isAvailable()))).booleanValue()) {
/* 117 */             DumpToFileCollector.currentGroupName = emcMapper.getName();
/* 118 */             emcMapper.addMappings((IMappingCollector)dumpToFileCollector, config, serverResources, registryAccess, resourceManager);
/* 119 */             PECore.debugLog("Collected Mappings from " + emcMapper.getClass().getName(), new Object[0]);
/*     */           } 
/* 121 */         } catch (Exception e) {
/* 122 */           PECore.LOGGER.error(LogUtils.FATAL_MARKER, "Exception during Mapping Collection from Mapper {}. PLEASE REPORT THIS! EMC VALUES MIGHT BE INCONSISTENT!", emcMapper
/* 123 */               .getClass().getName(), e);
/*     */         } 
/*     */       } 
/* 126 */       DumpToFileCollector.currentGroupName = "NSSHelper";
/*     */       
/* 128 */       PECore.debugLog("Mapping Collection finished", new Object[0]);
/* 129 */       dumpToFileCollector.finishCollection();
/*     */       
/* 131 */       PECore.debugLog("Starting to generate Values:", new Object[0]);
/*     */       
/* 133 */       config.save();
/* 134 */       config.close();
/*     */       
/* 136 */       graphMapperValues = bigFractionToLongGenerator.generateValues();
/* 137 */       PECore.debugLog("Generated Values...", new Object[0]);
/*     */       
/* 139 */       filterEMCMap(graphMapperValues);
/*     */       
/* 141 */       if (shouldUsePregenerated) {
/*     */         
/*     */         try {
/* 144 */           PregeneratedEMC.write(pregeneratedEmcFile, graphMapperValues);
/* 145 */           PECore.debugLog("Wrote Pregen-file!", new Object[0]);
/* 146 */         } catch (IOException e) {
/* 147 */           PECore.LOGGER.error("Failed to write Pregen-file", e);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 152 */     for (Map.Entry<NormalizedSimpleStack, Long> entry : graphMapperValues.entrySet()) {
/* 153 */       NSSItem normStackItem = (NSSItem)entry.getKey();
/* 154 */       ItemInfo obj = ItemInfo.fromNSS(normStackItem);
/* 155 */       if (obj != null) {
/* 156 */         emc.put(obj, entry.getValue()); continue;
/*     */       } 
/* 158 */       PECore.LOGGER.warn("Could not add EMC value for {}, item does not exist!", normStackItem.getResourceLocation());
/*     */     } 
/*     */ 
/*     */     
/* 162 */     fireEmcRemapEvent();
/*     */   }
/*     */ 
/*     */   
/*     */   private static void fireEmcRemapEvent() {
/* 167 */     FuelMapper.loadMap();
/* 168 */     loadIndex++;
/* 169 */     MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
/* 170 */     if (server != null)
/* 171 */       for (Iterator<ServerPlayer> iterator = server.m_6846_().m_11314_().iterator(); iterator.hasNext(); ) { ServerPlayer player = iterator.next();
/* 172 */         player.getCapability(PECapabilities.KNOWLEDGE_CAPABILITY).ifPresent(knowledge -> {
/*     */               if (knowledge instanceof KnowledgeImpl.DefaultImpl) {
/*     */                 KnowledgeImpl.DefaultImpl impl = (KnowledgeImpl.DefaultImpl)knowledge;
/*     */                 if (impl.pruneStaleKnowledge()) {
/*     */                   knowledge.sync(player);
/*     */                   return;
/*     */                 } 
/*     */               } 
/*     */               if (player.f_36096_ instanceof moze_intel.projecte.gameObjs.container.TransmutationContainer)
/*     */                 PacketHandler.sendTo((IPEPacket)new UpdateTransmutationTargetsPkt(), player); 
/*     */             }); }
/*     */        
/* 184 */     MinecraftForge.EVENT_BUS.post((Event)new EMCRemapEvent());
/*     */   }
/*     */   
/*     */   public static int getLoadIndex() {
/* 188 */     return loadIndex;
/*     */   }
/*     */   
/*     */   private static void filterEMCMap(Map<NormalizedSimpleStack, Long> map) {
/* 192 */     map.entrySet().removeIf(e -> {
/*     */           Object patt9021$temp = e.getKey(); if (patt9021$temp instanceof NSSItem) {
/*     */             NSSItem nssItem = (NSSItem)patt9021$temp; if (nssItem.representsTag() || ((Long)e.getValue()).longValue() <= 0L); return false;
/*     */           } 
/* 196 */         }); } public static int getEmcMapSize() { return emc.size(); }
/*     */ 
/*     */   
/*     */   public static boolean hasEmcValue(@NotNull ItemInfo info) {
/* 200 */     return emc.containsKey(info);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getStoredEmcValue(@NotNull ItemInfo info) {
/* 208 */     return ((Long)emc.getOrDefault(info, Long.valueOf(0L))).longValue();
/*     */   }
/*     */   
/*     */   public static void clearEmcMap() {
/* 212 */     emc.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Set<ItemInfo> getMappedItems() {
/* 219 */     return new HashSet<>(emc.keySet());
/*     */   }
/*     */   
/*     */   public static void fromPacket(SyncEmcPKT.EmcPKTInfo[] data) {
/* 223 */     emc.clear();
/* 224 */     for (SyncEmcPKT.EmcPKTInfo info : data) {
/* 225 */       emc.put(ItemInfo.fromItem((ItemLike)info.item(), info.nbt()), Long.valueOf(info.emc()));
/*     */     }
/*     */   }
/*     */   
/*     */   public static SyncEmcPKT.EmcPKTInfo[] createPacketData() {
/* 230 */     SyncEmcPKT.EmcPKTInfo[] ret = new SyncEmcPKT.EmcPKTInfo[emc.size()];
/* 231 */     int i = 0;
/* 232 */     for (Map.Entry<ItemInfo, Long> entry : emc.entrySet()) {
/* 233 */       ItemInfo info = entry.getKey();
/* 234 */       ret[i] = new SyncEmcPKT.EmcPKTInfo(info.getItem(), info.getNBT(), ((Long)entry.getValue()).longValue());
/* 235 */       i++;
/*     */     } 
/* 237 */     return ret;
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\EMCMappingHandler.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */