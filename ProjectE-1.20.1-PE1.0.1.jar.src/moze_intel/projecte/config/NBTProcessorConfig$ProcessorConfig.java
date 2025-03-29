/*     */ package moze_intel.projecte.config;
/*     */ 
/*     */ import moze_intel.projecte.api.nbt.INBTProcessor;
/*     */ import moze_intel.projecte.config.value.CachedBooleanValue;
/*     */ import net.minecraftforge.common.ForgeConfigSpec;
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
/*     */ class ProcessorConfig
/*     */ {
/*     */   public final CachedBooleanValue enabled;
/*     */   @Nullable
/*     */   public final CachedBooleanValue persistent;
/*     */   
/*     */   private ProcessorConfig(IPEConfig config, ForgeConfigSpec.Builder builder, INBTProcessor processor) {
/* 121 */     builder.comment(processor.getDescription()).push(processor.getName());
/* 122 */     this.enabled = CachedBooleanValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder.define("enabled", processor.isAvailable()));
/* 123 */     if (processor.hasPersistentNBT()) {
/* 124 */       this.persistent = CachedBooleanValue.wrap(config, (ForgeConfigSpec.ConfigValue)builder.define("persistent", processor.usePersistentNBT()));
/*     */     } else {
/* 126 */       this.persistent = null;
/*     */     } 
/* 128 */     builder.pop();
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\config\NBTProcessorConfig$ProcessorConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */