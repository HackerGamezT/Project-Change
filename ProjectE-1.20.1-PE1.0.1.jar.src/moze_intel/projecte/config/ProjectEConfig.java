/*    */ package moze_intel.projecte.config;
/*    */ 
/*    */ import java.nio.file.Path;
/*    */ import moze_intel.projecte.PECore;
/*    */ import net.minecraftforge.fml.loading.FMLPaths;
/*    */ 
/*    */ public class ProjectEConfig
/*    */ {
/*    */   public static final Path CONFIG_DIR;
/* 10 */   public static final ServerConfig server = new ServerConfig();
/* 11 */   public static final CommonConfig common = new CommonConfig();
/* 12 */   public static final ClientConfig client = new ClientConfig();
/*    */   
/*    */   static {
/* 15 */     CONFIG_DIR = FMLPaths.getOrCreateGameRelativePath(FMLPaths.CONFIGDIR.get().resolve("ProjectE"));
/*    */   }
/*    */   
/*    */   public static void register() {
/* 19 */     registerConfig(server);
/* 20 */     registerConfig(common);
/* 21 */     registerConfig(client);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void registerConfig(IPEConfig config) {
/* 28 */     PEModConfig peModConfig = new PEModConfig(PECore.MOD_CONTAINER, config);
/* 29 */     if (config.addToContainer())
/* 30 */       PECore.MOD_CONTAINER.addConfig(peModConfig); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\config\ProjectEConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */