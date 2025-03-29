/*     */ package moze_intel.projecte.emc.mappers.recipe;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import moze_intel.projecte.api.mapper.recipe.INSSFakeGroupManager;
/*     */ import moze_intel.projecte.api.nss.NSSFake;
/*     */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*     */ import net.minecraft.util.Tuple;
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
/*     */ class NSSFakeGroupManager
/*     */   implements INSSFakeGroupManager
/*     */ {
/* 167 */   private final Map<Set<NormalizedSimpleStack>, NormalizedSimpleStack> groups = new HashMap<>();
/*     */   
/*     */   private int fakeIndex;
/*     */   
/*     */   public Tuple<NormalizedSimpleStack, Boolean> getOrCreateFakeGroup(Set<NormalizedSimpleStack> normalizedSimpleStacks) {
/* 172 */     NormalizedSimpleStack stack = this.groups.get(normalizedSimpleStacks);
/* 173 */     if (stack == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 182 */       stack = NSSFake.create(Integer.toString(this.fakeIndex++));
/*     */ 
/*     */       
/* 185 */       this.groups.put(new HashSet<>(normalizedSimpleStacks), stack);
/* 186 */       return new Tuple(stack, Boolean.valueOf(true));
/*     */     } 
/* 188 */     return new Tuple(stack, Boolean.valueOf(false));
/*     */   }
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\emc\mappers\recipe\CraftingMapper$NSSFakeGroupManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */