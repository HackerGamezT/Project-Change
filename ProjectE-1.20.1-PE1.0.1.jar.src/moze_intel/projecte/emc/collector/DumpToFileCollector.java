/*    */ package moze_intel.projecte.emc.collector;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.util.Map;
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.api.mapper.arithmetic.IValueArithmetic;
/*    */ import moze_intel.projecte.api.mapper.collector.IExtendedMappingCollector;
/*    */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*    */ import moze_intel.projecte.emc.mappers.customConversions.json.ConversionGroup;
/*    */ import moze_intel.projecte.emc.mappers.customConversions.json.CustomConversion;
/*    */ import moze_intel.projecte.emc.mappers.customConversions.json.CustomConversionFile;
/*    */ 
/*    */ public class DumpToFileCollector<A extends IValueArithmetic<?>>
/*    */   extends AbstractMappingCollector<NormalizedSimpleStack, Long, A> {
/* 16 */   public static String currentGroupName = "default";
/* 17 */   private final CustomConversionFile out = new CustomConversionFile();
/*    */   private final IExtendedMappingCollector<NormalizedSimpleStack, Long, A> inner;
/*    */   private final File file;
/*    */   
/*    */   public DumpToFileCollector(File f, IExtendedMappingCollector<NormalizedSimpleStack, Long, A> inner) {
/* 22 */     super((A)inner.getArithmetic());
/* 23 */     this.file = f;
/* 24 */     this.inner = inner;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setValueFromConversion(int outnumber, NormalizedSimpleStack something, Map<NormalizedSimpleStack, Integer> ingredientsWithAmount) {
/* 29 */     this.inner.setValueFromConversion(outnumber, something, ingredientsWithAmount);
/* 30 */     if (something == null || ingredientsWithAmount.containsKey(null)) {
/*    */       return;
/*    */     }
/* 33 */     this.out.values.conversion.add(CustomConversion.getFor(outnumber, something, ingredientsWithAmount));
/*    */   }
/*    */ 
/*    */   
/*    */   public void addConversion(int outnumber, NormalizedSimpleStack output, Map<NormalizedSimpleStack, Integer> ingredientsWithAmount, A arithmeticForConversion) {
/* 38 */     this.inner.addConversion(outnumber, output, ingredientsWithAmount, (IValueArithmetic)arithmeticForConversion);
/* 39 */     if (output == null || ingredientsWithAmount.containsKey(null)) {
/*    */       return;
/*    */     }
/* 42 */     if (!this.out.groups.containsKey(currentGroupName)) {
/* 43 */       this.out.groups.put(currentGroupName, new ConversionGroup());
/*    */     }
/* 45 */     ConversionGroup group = (ConversionGroup)this.out.groups.get(currentGroupName);
/* 46 */     group.conversions.add(CustomConversion.getFor(outnumber, output, ingredientsWithAmount));
/*    */   }
/*    */ 
/*    */   
/*    */   public void setValueBefore(NormalizedSimpleStack something, Long value) {
/* 51 */     this.inner.setValueBefore(something, value);
/* 52 */     if (something == null) {
/*    */       return;
/*    */     }
/* 55 */     this.out.values.setValueBefore.put(something, value);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setValueAfter(NormalizedSimpleStack something, Long value) {
/* 60 */     this.inner.setValueAfter(something, value);
/* 61 */     if (something == null) {
/*    */       return;
/*    */     }
/* 64 */     this.out.values.setValueAfter.put(something, value);
/*    */   }
/*    */ 
/*    */   
/*    */   public void finishCollection() {
/*    */     try {
/* 70 */       this.out.write(this.file);
/* 71 */     } catch (IOException e) {
/* 72 */       PECore.LOGGER.error("Failed to dump to file", e);
/*    */     } 
/* 74 */     this.inner.finishCollection();
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\collector\DumpToFileCollector.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */