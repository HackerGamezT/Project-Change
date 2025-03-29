/*    */ package moze_intel.projecte.emc;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.Comparator;
/*    */ import java.util.List;
/*    */ import moze_intel.projecte.PECore;
/*    */ import moze_intel.projecte.gameObjs.PETags;
/*    */ import moze_intel.projecte.network.packets.to_client.SyncFuelMapperPKT;
/*    */ import moze_intel.projecte.utils.EMCHelper;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.ItemLike;
/*    */ 
/*    */ public final class FuelMapper {
/* 15 */   private static List<Item> FUEL_MAP = Collections.emptyList();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void loadMap() {
/* 24 */     FUEL_MAP = PETags.Items.COLLECTOR_FUEL_LOOKUP.tag().stream().filter(EMCHelper::doesItemHaveEmc).sorted(Comparator.comparing(EMCHelper::getEmcValue)).toList();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void setFuelMap(List<Item> map) {
/* 31 */     FUEL_MAP = List.copyOf(map);
/*    */   }
/*    */   
/*    */   public static SyncFuelMapperPKT getSyncPacket() {
/* 35 */     return new SyncFuelMapperPKT(FUEL_MAP);
/*    */   }
/*    */   
/*    */   public static boolean isStackFuel(ItemStack stack) {
/* 39 */     return FUEL_MAP.contains(stack.m_41720_());
/*    */   }
/*    */   
/*    */   public static boolean isStackMaxFuel(ItemStack stack) {
/* 43 */     return (FUEL_MAP.indexOf(stack.m_41720_()) == FUEL_MAP.size() - 1);
/*    */   }
/*    */   
/*    */   public static ItemStack getFuelUpgrade(ItemStack stack) {
/* 47 */     int index = FUEL_MAP.indexOf(stack.m_41720_());
/* 48 */     if (index == -1) {
/* 49 */       PECore.LOGGER.warn("Tried to upgrade invalid fuel: {}", stack);
/* 50 */       return ItemStack.f_41583_;
/*    */     } 
/* 52 */     int nextIndex = (index == FUEL_MAP.size() - 1) ? 0 : (index + 1);
/* 53 */     return new ItemStack((ItemLike)FUEL_MAP.get(nextIndex));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static List<Item> getFuelMap() {
/* 60 */     return FUEL_MAP;
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\FuelMapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */