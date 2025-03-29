/*     */ package moze_intel.projecte.config;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import moze_intel.projecte.PECore;
/*     */ import moze_intel.projecte.api.nbt.INBTProcessor;
/*     */ import moze_intel.projecte.config.value.CachedBooleanValue;
/*     */ import net.minecraftforge.common.ForgeConfigSpec;
/*     */ import net.minecraftforge.fml.config.ModConfig;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NBTProcessorConfig
/*     */   extends BasePEConfig
/*     */ {
/*     */   private static NBTProcessorConfig INSTANCE;
/*     */   private static final String ENABLED = "enabled";
/*     */   private static final String PERSISTENT = "persistent";
/*     */   private static final String MAIN_KEY = "processors";
/*     */   private final ForgeConfigSpec configSpec;
/*     */   
/*     */   public static void setup(@NotNull List<INBTProcessor> processors) {
/*  32 */     if (INSTANCE == null) {
/*  33 */       ProjectEConfig.registerConfig(INSTANCE = new NBTProcessorConfig(processors));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*  38 */   private final Map<String, ProcessorConfig> processorConfigs = new HashMap<>();
/*     */   
/*     */   private NBTProcessorConfig(@NotNull List<INBTProcessor> processors) {
/*  41 */     ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
/*  42 */     builder.comment(new String[] { "This config is used to control which NBT Processors get used, and which ones actually contribute to the persistent NBT data that gets saved to knowledge/copied in a condenser.", "To disable an NBT Processor set the 'enabled' option for it to false.", "To disable an NBT Processor from contributing to the persistent data set the 'persistent' option for it to false. Note: that if there is no persistent' config option, the NBT Processor never has any persistent data.", "The config options in this file are synced from server to client, as the processors get used dynamically to calculate/preview EMC values for items and are not included in the synced EMC mappings."
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  49 */         }).push("processors");
/*  50 */     for (INBTProcessor processor : processors) {
/*  51 */       this.processorConfigs.put(processor.getName(), new ProcessorConfig(this, builder, processor));
/*     */     }
/*  53 */     builder.pop();
/*  54 */     this.configSpec = builder.build();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isEnabled(INBTProcessor processor) {
/*  61 */     if (INSTANCE == null) {
/*  62 */       return true;
/*     */     }
/*  64 */     String name = processor.getName();
/*  65 */     ProcessorConfig processorConfig = INSTANCE.processorConfigs.get(name);
/*  66 */     if (processorConfig == null) {
/*  67 */       PECore.LOGGER.warn("Processor Config: '{}' is missing from the config.", name);
/*  68 */       return false;
/*     */     } 
/*  70 */     return processorConfig.enabled.get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean hasPersistent(INBTProcessor processor) {
/*  77 */     if (INSTANCE == null) {
/*  78 */       return false;
/*     */     }
/*  80 */     String name = processor.getName();
/*  81 */     ProcessorConfig processorConfig = INSTANCE.processorConfigs.get(name);
/*  82 */     if (processorConfig == null) {
/*  83 */       PECore.LOGGER.warn("Processor Config: '{}' is missing from the config.", name);
/*  84 */       return false;
/*  85 */     }  if (processorConfig.persistent == null) {
/*  86 */       if (processor.hasPersistentNBT()) {
/*  87 */         PECore.LOGGER.warn("Processor Config: '{}' has persistent NBT but is missing the config option.", name);
/*     */       }
/*  89 */       return false;
/*     */     } 
/*  91 */     return processorConfig.persistent.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFileName() {
/*  96 */     return "processing";
/*     */   }
/*     */ 
/*     */   
/*     */   public ForgeConfigSpec getConfigSpec() {
/* 101 */     return this.configSpec;
/*     */   }
/*     */ 
/*     */   
/*     */   public ModConfig.Type getConfigType() {
/* 106 */     return ModConfig.Type.SERVER;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addToContainer() {
/* 111 */     return false;
/*     */   }
/*     */   
/*     */   private static class ProcessorConfig
/*     */   {
/*     */     public final CachedBooleanValue enabled;
/*     */     @Nullable
/*     */     public final CachedBooleanValue persistent;
/*     */     
/*     */     private ProcessorConfig(IPEConfig config, ForgeConfigSpec.Builder builder, INBTProcessor processor) {
/* 121 */       builder.comment(processor.getDescription()).push(processor.getName());
/* 122 */       this.enabled = CachedBooleanValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder.define("enabled", processor.isAvailable()));
/* 123 */       if (processor.hasPersistentNBT()) {
/* 124 */         this.persistent = CachedBooleanValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder.define("persistent", processor.usePersistentNBT()));
/*     */       } else {
/* 126 */         this.persistent = null;
/*     */       } 
/* 128 */       builder.pop();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\config\NBTProcessorConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */