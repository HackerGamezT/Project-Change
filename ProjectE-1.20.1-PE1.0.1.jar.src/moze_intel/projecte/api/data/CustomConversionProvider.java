/*    */ package moze_intel.projecte.api.data;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import java.nio.file.Path;
/*    */ import java.util.ArrayList;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Objects;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import java.util.concurrent.CompletionStage;
/*    */ import javax.annotation.ParametersAreNonnullByDefault;
/*    */ import net.minecraft.MethodsReturnNonnullByDefault;
/*    */ import net.minecraft.core.HolderLookup;
/*    */ import net.minecraft.data.CachedOutput;
/*    */ import net.minecraft.data.DataProvider;
/*    */ import net.minecraft.data.PackOutput;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ 
/*    */ 
/*    */ @ParametersAreNonnullByDefault
/*    */ @MethodsReturnNonnullByDefault
/*    */ public abstract class CustomConversionProvider
/*    */   implements DataProvider
/*    */ {
/* 26 */   private final Map<ResourceLocation, CustomConversionBuilder> customConversions = new LinkedHashMap<>();
/*    */   private final CompletableFuture<HolderLookup.Provider> lookupProvider;
/*    */   private final Path outputFolder;
/*    */   
/*    */   protected CustomConversionProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
/* 31 */     this.outputFolder = output.m_247566_(PackOutput.Target.DATA_PACK);
/* 32 */     this.lookupProvider = lookupProvider;
/*    */   }
/*    */ 
/*    */   
/*    */   public CompletableFuture<?> m_213708_(CachedOutput output) {
/* 37 */     return this.lookupProvider.thenApply(registries -> {
/*    */           this.customConversions.clear();
/*    */           addCustomConversions(registries);
/*    */           return registries;
/* 41 */         }).thenCompose(registries -> {
/*    */           List<CompletableFuture<?>> futures = new ArrayList<>();
/*    */           for (Map.Entry<ResourceLocation, CustomConversionBuilder> entry : this.customConversions.entrySet()) {
/*    */             ResourceLocation customConversion = entry.getKey();
/*    */             Path path = this.outputFolder.resolve(customConversion.m_135827_() + "/pe_custom_conversions/" + customConversion.m_135827_() + ".json");
/*    */             futures.add(DataProvider.m_253162_(output, (JsonElement)((CustomConversionBuilder)entry.getValue()).serialize(), path));
/*    */           } 
/*    */           return CompletableFuture.allOf((CompletableFuture<?>[])futures.toArray(()));
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected CustomConversionBuilder createConversionBuilder(ResourceLocation id) {
/* 67 */     Objects.requireNonNull(id, "Custom Conversion Builder ID cannot be null.");
/* 68 */     if (this.customConversions.containsKey(id)) {
/* 69 */       throw new RuntimeException("Custom conversion '" + id + "' has already been registered.");
/*    */     }
/* 71 */     CustomConversionBuilder conversionBuilder = new CustomConversionBuilder(id);
/* 72 */     this.customConversions.put(id, conversionBuilder);
/* 73 */     return conversionBuilder;
/*    */   }
/*    */ 
/*    */   
/*    */   public String m_6055_() {
/* 78 */     return "Custom EMC Conversions";
/*    */   }
/*    */   
/*    */   protected abstract void addCustomConversions(HolderLookup.Provider paramProvider);
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\data\CustomConversionProvider.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */