/*    */ package moze_intel.projecte.network;
/*    */ 
/*    */ import com.mojang.logging.LogUtils;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ import java.net.URL;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import moze_intel.projecte.PECore;
/*    */ 
/*    */ public class ThreadCheckUUID
/*    */   extends Thread {
/*    */   private static boolean hasRunServer = false;
/*    */   private static boolean hasRunClient = false;
/*    */   private static final String uuidURL = "https://raw.githubusercontent.com/sinkillerj/ProjectE/mc1.14.x/haUUID.txt";
/*    */   private final boolean isServerSide;
/*    */   
/*    */   public ThreadCheckUUID(boolean isServer) {
/* 20 */     this.isServerSide = isServer;
/* 21 */     setName("ProjectE UUID Checker " + (isServer ? "Server" : "Client"));
/*    */   }
/*    */   
/*    */   public void run() {
/*    */     
/* 26 */     try { BufferedReader reader = new BufferedReader(new InputStreamReader((new URL("https://raw.githubusercontent.com/sinkillerj/ProjectE/mc1.14.x/haUUID.txt")).openStream())); 
/* 27 */       try { String line = reader.readLine();
/*    */         
/* 29 */         if (line == null) {
/* 30 */           PECore.LOGGER.error(LogUtils.FATAL_MARKER, "UUID check failed!");
/* 31 */           throw new IOException("No data from github UUID list!");
/*    */         } 
/*    */         
/* 34 */         List<String> uuids = new ArrayList<>();
/*    */         
/* 36 */         while ((line = reader.readLine()) != null && 
/* 37 */           !line.startsWith("###UUID")) {
/*    */ 
/*    */ 
/*    */           
/* 41 */           if (!line.isEmpty()) {
/* 42 */             uuids.add(line);
/*    */           }
/*    */         } 
/*    */         
/* 46 */         PECore.uuids.addAll(uuids);
/* 47 */         reader.close(); } catch (Throwable throwable) { try { reader.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (IOException e)
/* 48 */     { PECore.LOGGER.error(LogUtils.FATAL_MARKER, "Caught exception in UUID Checker thread!", e); }
/*    */     finally
/* 50 */     { if (this.isServerSide) {
/* 51 */         hasRunServer = true;
/*    */       } else {
/* 53 */         hasRunClient = true;
/*    */       }  }
/*    */   
/*    */   }
/*    */   
/*    */   public static boolean hasRunServer() {
/* 59 */     return hasRunServer;
/*    */   }
/*    */   
/*    */   public static boolean hasRunClient() {
/* 63 */     return hasRunClient;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\network\ThreadCheckUUID.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */