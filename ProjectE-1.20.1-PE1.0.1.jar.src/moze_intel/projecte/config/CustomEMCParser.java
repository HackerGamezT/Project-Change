/*     */ package moze_intel.projecte.config;
/*     */ 
/*     */ import com.google.common.base.Charsets;
/*     */ import com.google.common.io.Files;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import com.mojang.logging.LogUtils;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import moze_intel.projecte.PECore;
/*     */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*     */ import moze_intel.projecte.emc.json.NSSSerializer;
/*     */ 
/*     */ 
/*     */ public final class CustomEMCParser
/*     */ {
/*  25 */   private static final Gson GSON = (new GsonBuilder()).registerTypeAdapter(NormalizedSimpleStack.class, NSSSerializer.INSTANCE).setPrettyPrinting().create();
/*  26 */   private static final File CONFIG = ProjectEConfig.CONFIG_DIR.resolve("custom_emc.json").toFile();
/*     */   public static CustomEMCFile currentEntries;
/*     */   
/*     */   public static class CustomEMCFile
/*     */   {
/*     */     public final List<CustomEMCParser.CustomEMCEntry> entries;
/*     */     
/*     */     public CustomEMCFile(List<CustomEMCParser.CustomEMCEntry> entries) {
/*  34 */       this.entries = entries;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class CustomEMCEntry
/*     */   {
/*     */     public final NormalizedSimpleStack item;
/*     */     public final long emc;
/*     */     
/*     */     private CustomEMCEntry(NormalizedSimpleStack item, long emc) {
/*  44 */       this.item = item;
/*  45 */       this.emc = emc;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object o) {
/*  50 */       return (o == this || (o instanceof CustomEMCEntry && this.item.equals(((CustomEMCEntry)o).item) && this.emc == ((CustomEMCEntry)o).emc));
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/*  55 */       int result = (this.item != null) ? this.item.hashCode() : 0;
/*  56 */       result = 31 * result + (int)(this.emc ^ this.emc >>> 32L);
/*  57 */       return result;
/*     */     }
/*     */   }
/*     */   
/*     */   private static boolean dirty = false;
/*     */ 
/*     */   
/*     */   public static void init() {
/*  65 */     flush();
/*     */     
/*  67 */     if (!CONFIG.exists()) {
/*     */       try {
/*  69 */         if (CONFIG.createNewFile()) {
/*  70 */           writeDefaultFile();
/*     */         }
/*  72 */       } catch (IOException e) {
/*  73 */         PECore.LOGGER.error(LogUtils.FATAL_MARKER, "Exception in file I/O: couldn't create custom configuration files.");
/*     */       } 
/*     */     }
/*     */     
/*  77 */     try { BufferedReader reader = new BufferedReader(new FileReader(CONFIG)); 
/*  78 */       try { currentEntries = (CustomEMCFile)GSON.fromJson(reader, CustomEMCFile.class);
/*  79 */         currentEntries.entries.removeIf(e -> (!(e.item instanceof moze_intel.projecte.api.nss.NSSItem) || e.emc < 0L));
/*  80 */         reader.close(); } catch (Throwable throwable) { try { reader.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (IOException|com.google.gson.JsonParseException e)
/*  81 */     { PECore.LOGGER.error(LogUtils.FATAL_MARKER, "Couldn't read custom emc file", e);
/*  82 */       currentEntries = new CustomEMCFile(new ArrayList<>()); }
/*     */   
/*     */   }
/*     */   
/*     */   private static NormalizedSimpleStack getNss(String str) {
/*  87 */     return NSSSerializer.INSTANCE.deserialize(str);
/*     */   }
/*     */   
/*     */   public static void addToFile(String toAdd, long emc) {
/*  91 */     NormalizedSimpleStack nss = getNss(toAdd);
/*  92 */     CustomEMCEntry entry = new CustomEMCEntry(nss, emc);
/*  93 */     int setAt = -1;
/*  94 */     for (int i = 0; i < currentEntries.entries.size(); i++) {
/*  95 */       if (((CustomEMCEntry)currentEntries.entries.get(i)).item.equals(nss)) {
/*  96 */         setAt = i;
/*     */         break;
/*     */       } 
/*     */     } 
/* 100 */     if (setAt == -1) {
/* 101 */       currentEntries.entries.add(entry);
/*     */     } else {
/* 103 */       currentEntries.entries.set(setAt, entry);
/*     */     } 
/* 105 */     dirty = true;
/*     */   }
/*     */   
/*     */   public static boolean removeFromFile(String toRemove) {
/* 109 */     NormalizedSimpleStack nss = getNss(toRemove);
/* 110 */     Iterator<CustomEMCEntry> iter = currentEntries.entries.iterator();
/* 111 */     boolean removed = false;
/* 112 */     while (iter.hasNext()) {
/* 113 */       if (((CustomEMCEntry)iter.next()).item.equals(nss)) {
/* 114 */         iter.remove();
/* 115 */         dirty = true;
/* 116 */         removed = true;
/*     */       } 
/*     */     } 
/* 119 */     return removed;
/*     */   }
/*     */   
/*     */   public static void flush() {
/* 123 */     if (dirty) {
/*     */       try {
/* 125 */         Files.asCharSink(CONFIG, Charsets.UTF_8, new com.google.common.io.FileWriteMode[0]).write(GSON.toJson(currentEntries));
/* 126 */       } catch (IOException e) {
/* 127 */         PECore.LOGGER.error("Failed to write custom EMC file", e);
/*     */       } 
/* 129 */       dirty = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void writeDefaultFile() {
/* 134 */     JsonObject elem = (JsonObject)GSON.toJsonTree(new CustomEMCFile(new ArrayList<>()));
/* 135 */     elem.add("__comment", (JsonElement)new JsonPrimitive("Use the in-game commands to edit this file"));
/*     */     try {
/* 137 */       Files.asCharSink(CONFIG, Charsets.UTF_8, new com.google.common.io.FileWriteMode[0]).write(GSON.toJson((JsonElement)elem));
/* 138 */     } catch (IOException e) {
/* 139 */       PECore.LOGGER.error("Failed to write default custom EMC file", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\config\CustomEMCParser.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */