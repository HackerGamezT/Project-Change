/*    */ package moze_intel.projecte.integration.crafttweaker.mappers;
/*    */ 
/*    */ import com.electronwill.nightconfig.core.file.CommentedFileConfig;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.api.mapper.EMCMapper;
/*    */ import moze_intel.projecte.api.mapper.IEMCMapper;
/*    */ import moze_intel.projecte.api.mapper.collector.IMappingCollector;
/*    */ import moze_intel.projecte.api.nss.NSSTag;
/*    */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*    */ import net.minecraft.core.RegistryAccess;
/*    */ import net.minecraft.server.ReloadableServerResources;
/*    */ import net.minecraft.server.packs.resources.ResourceManager;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ @EMCMapper(requiredMods = {"crafttweaker"})
/*    */ public class CrTConversionEMCMapper
/*    */   implements IEMCMapper<NormalizedSimpleStack, Long> {
/* 21 */   private static final List<CrTConversion> storedConversions = new ArrayList<>();
/*    */   
/*    */   public static void addConversion(@NotNull CrTConversion conversion) {
/* 24 */     storedConversions.add(conversion);
/*    */   }
/*    */   
/*    */   public static void removeConversion(@NotNull CrTConversion conversion) {
/* 28 */     storedConversions.remove(conversion);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void addMappings(IMappingCollector<NormalizedSimpleStack, Long> mapper, CommentedFileConfig config, ReloadableServerResources serverResources, RegistryAccess registryAccess, ResourceManager resourceManager) {
/* 34 */     for (CrTConversion apiConversion : storedConversions) {
/* 35 */       if (apiConversion.propagateTags) { NormalizedSimpleStack normalizedSimpleStack = apiConversion.output; if (normalizedSimpleStack instanceof NSSTag) { NSSTag output = (NSSTag)normalizedSimpleStack;
/* 36 */           output.forEachElement(normalizedSimpleStack -> {
/*    */                 if (apiConversion.set) {
/*    */                   mapper.setValueFromConversion(apiConversion.amount, normalizedSimpleStack, apiConversion.ingredients);
/*    */                 } else {
/*    */                   mapper.addConversion(apiConversion.amount, normalizedSimpleStack, apiConversion.ingredients);
/*    */                 } 
/*    */               }); }
/*    */          }
/* 44 */        if (apiConversion.set) {
/* 45 */         mapper.setValueFromConversion(apiConversion.amount, apiConversion.output, apiConversion.ingredients);
/*    */       } else {
/* 47 */         mapper.addConversion(apiConversion.amount, apiConversion.output, apiConversion.ingredients);
/*    */       } 
/* 49 */       PECore.debugLog("CraftTweaker adding conversion for {}", new Object[] { apiConversion.output });
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 55 */     return "CrTConversionEMCMapper";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 60 */     return "Allows adding custom conversions through CraftTweaker. This behaves similarly to if someone used a custom conversion file instead.";
/*    */   }
/*    */   public static final class CrTConversion extends Record { private final NormalizedSimpleStack output; private final int amount; private final boolean propagateTags; private final boolean set; private final Map<NormalizedSimpleStack, Integer> ingredients;
/* 63 */     public CrTConversion(NormalizedSimpleStack output, int amount, boolean propagateTags, boolean set, Map<NormalizedSimpleStack, Integer> ingredients) { this.output = output; this.amount = amount; this.propagateTags = propagateTags; this.set = set; this.ingredients = ingredients; } public final String toString() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> toString : (Lmoze_intel/projecte/integration/crafttweaker/mappers/CrTConversionEMCMapper$CrTConversion;)Ljava/lang/String;
/*    */       //   6: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #63	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/* 63 */       //   0	7	0	this	Lmoze_intel/projecte/integration/crafttweaker/mappers/CrTConversionEMCMapper$CrTConversion; } public NormalizedSimpleStack output() { return this.output; } public final int hashCode() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> hashCode : (Lmoze_intel/projecte/integration/crafttweaker/mappers/CrTConversionEMCMapper$CrTConversion;)I
/*    */       //   6: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #63	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lmoze_intel/projecte/integration/crafttweaker/mappers/CrTConversionEMCMapper$CrTConversion; } public final boolean equals(Object o) { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: <illegal opcode> equals : (Lmoze_intel/projecte/integration/crafttweaker/mappers/CrTConversionEMCMapper$CrTConversion;Ljava/lang/Object;)Z
/*    */       //   7: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #63	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	8	0	this	Lmoze_intel/projecte/integration/crafttweaker/mappers/CrTConversionEMCMapper$CrTConversion;
/* 63 */       //   0	8	1	o	Ljava/lang/Object; } public int amount() { return this.amount; } public boolean propagateTags() { return this.propagateTags; } public boolean set() { return this.set; } public Map<NormalizedSimpleStack, Integer> ingredients() { return this.ingredients; }
/*    */      }
/*    */ 
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\integration\crafttweaker\mappers\CrTConversionEMCMapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */