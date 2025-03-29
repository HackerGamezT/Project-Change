/*    */ package moze_intel.projecte.gameObjs.container.slots;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import moze_intel.projecte.api.capabilities.PECapabilities;
/*    */ import moze_intel.projecte.emc.FuelMapper;
/*    */ import moze_intel.projecte.utils.EMCHelper;
/*    */ import moze_intel.projecte.utils.ItemHelper;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ 
/*    */ public final class SlotPredicates {
/*    */   public static final Predicate<ItemStack> ALWAYS_FALSE = input -> false;
/*    */   
/*    */   static {
/* 16 */     HAS_EMC = (input -> (!input.m_41619_() && EMCHelper.doesItemHaveEmc(input)));
/*    */     
/* 18 */     COLLECTOR_LOCK = (input -> (!input.m_41619_() && FuelMapper.isStackFuel(input)));
/*    */     
/* 20 */     COLLECTOR_INV = (input -> ((!input.m_41619_() && input.getCapability(PECapabilities.EMC_HOLDER_ITEM_CAPABILITY).isPresent()) || (FuelMapper.isStackFuel(input) && !FuelMapper.isStackMaxFuel(input))));
/*    */ 
/*    */ 
/*    */     
/* 24 */     EMC_HOLDER = (input -> (!input.m_41619_() && input.getCapability(PECapabilities.EMC_HOLDER_ITEM_CAPABILITY).isPresent()));
/*    */ 
/*    */     
/* 27 */     RELAY_INV = (input -> (EMC_HOLDER.test(input) || HAS_EMC.test(input)));
/*    */     
/* 29 */     FURNACE_FUEL = (input -> (EMC_HOLDER.test(input) || (!input.m_41619_() && AbstractFurnaceBlockEntity.m_58399_(input))));
/*    */     
/* 31 */     MERCURIAL_TARGET = (input -> {
/*    */         if (input.m_41619_()) {
/*    */           return false;
/*    */         }
/*    */         BlockState state = ItemHelper.stackToState(input, null);
/* 36 */         return (state != null && !state.m_155947_() && EMCHelper.doesItemHaveEmc(input));
/*    */       });
/*    */   }
/*    */   
/*    */   public static final Predicate<ItemStack> HAS_EMC;
/*    */   public static final Predicate<ItemStack> COLLECTOR_LOCK;
/*    */   public static final Predicate<ItemStack> COLLECTOR_INV;
/*    */   public static final Predicate<ItemStack> EMC_HOLDER;
/*    */   public static final Predicate<ItemStack> RELAY_INV;
/*    */   public static final Predicate<ItemStack> FURNACE_FUEL;
/*    */   public static final Predicate<ItemStack> MERCURIAL_TARGET;
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\gameObjs\container\slots\SlotPredicates.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */