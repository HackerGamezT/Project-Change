/*    */ package moze_intel.projecte.config;
/*    */ 
/*    */ import com.electronwill.nightconfig.core.file.CommentedFileConfig;
/*    */ import java.nio.file.Path;
/*    */ import java.util.function.Function;
/*    */ import net.minecraftforge.fml.ModContainer;
/*    */ import net.minecraftforge.fml.config.ConfigFileTypeHandler;
/*    */ import net.minecraftforge.fml.config.IConfigSpec;
/*    */ import net.minecraftforge.fml.config.ModConfig;
/*    */ import net.minecraftforge.fml.event.config.ModConfigEvent;
/*    */ import net.minecraftforge.fml.loading.FMLPaths;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PEModConfig
/*    */   extends ModConfig
/*    */ {
/* 20 */   private static final PEConfigFileTypeHandler PE_TOML = new PEConfigFileTypeHandler();
/*    */   
/*    */   private final IPEConfig peConfig;
/*    */   
/*    */   public PEModConfig(ModContainer container, IPEConfig config) {
/* 25 */     super(config.getConfigType(), (IConfigSpec)config.getConfigSpec(), container, "ProjectE/" + config.getFileName() + ".toml");
/* 26 */     this.peConfig = config;
/*    */   }
/*    */ 
/*    */   
/*    */   public ConfigFileTypeHandler getHandler() {
/* 31 */     return PE_TOML;
/*    */   }
/*    */   
/*    */   public void clearCache(ModConfigEvent event) {
/* 35 */     this.peConfig.clearCache(event instanceof ModConfigEvent.Unloading);
/*    */   }
/*    */   
/*    */   private static class PEConfigFileTypeHandler
/*    */     extends ConfigFileTypeHandler
/*    */   {
/*    */     private static Path getPath(Path configBasePath) {
/* 42 */       if (configBasePath.endsWith("serverconfig")) {
/* 43 */         return FMLPaths.CONFIGDIR.get();
/*    */       }
/* 45 */       return configBasePath;
/*    */     }
/*    */ 
/*    */     
/*    */     public Function<ModConfig, CommentedFileConfig> reader(Path configBasePath) {
/* 50 */       return super.reader(getPath(configBasePath));
/*    */     }
/*    */ 
/*    */     
/*    */     public void unload(Path configBasePath, ModConfig config) {
/* 55 */       super.unload(getPath(configBasePath), config);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\config\PEModConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */