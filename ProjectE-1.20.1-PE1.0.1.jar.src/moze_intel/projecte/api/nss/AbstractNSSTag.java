/*     */ package moze_intel.projecte.api.nss;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.mojang.datafixers.util.Either;
/*     */ import java.util.HashSet;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Function;
/*     */ import java.util.stream.Stream;
/*     */ import net.minecraft.core.Holder;
/*     */ import net.minecraft.core.HolderSet;
/*     */ import net.minecraft.core.Registry;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.tags.TagKey;
/*     */ import net.minecraftforge.registries.IForgeRegistry;
/*     */ import net.minecraftforge.registries.tags.ITag;
/*     */ import net.minecraftforge.registries.tags.ITagManager;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractNSSTag<TYPE>
/*     */   implements NSSTag
/*     */ {
/*  31 */   private static final Set<NSSTag> createdTags = new HashSet<>();
/*     */   
/*     */   @NotNull
/*     */   private final ResourceLocation resourceLocation;
/*     */   
/*     */   private final boolean isTag;
/*     */   
/*     */   public static Set<NSSTag> getAllCreatedTags() {
/*  39 */     return (Set<NSSTag>)ImmutableSet.copyOf(createdTags);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void clearCreatedTags() {
/*  48 */     createdTags.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractNSSTag(@NotNull ResourceLocation resourceLocation, boolean isTag) {
/*  56 */     this.resourceLocation = resourceLocation;
/*  57 */     this.isTag = isTag;
/*  58 */     if (isTag) {
/*  59 */       createdTags.add(this);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ResourceLocation getResourceLocation() {
/*  68 */     return this.resourceLocation;
/*     */   }
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
/*     */   protected final Optional<Either<HolderSet.Named<TYPE>, ITag<TYPE>>> getTag(Registry<TYPE> registry) {
/* 102 */     if (representsTag()) {
/* 103 */       return registry.m_203431_(TagKey.m_203882_(registry.m_123023_(), getResourceLocation())).map(Either::left);
/*     */     }
/* 105 */     return Optional.empty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final Optional<Either<HolderSet.Named<TYPE>, ITag<TYPE>>> getTag(IForgeRegistry<? extends TYPE> registry) {
/* 113 */     if (representsTag()) {
/* 114 */       ITagManager<? extends TYPE> tags = registry.tags();
/* 115 */       if (tags != null) {
/* 116 */         return Optional.of(Either.right(tags.getTag(tags.createTagKey(getResourceLocation()))));
/*     */       }
/*     */     } 
/* 119 */     return Optional.empty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean representsTag() {
/* 126 */     return this.isTag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void forEachElement(Consumer<NormalizedSimpleStack> consumer) {
/* 131 */     getTag().ifPresent(tag -> ((Stream<TYPE>)tag.map((), ITag::stream)).<NormalizedSimpleStack>map(createNew()).forEach(consumer));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String json() {
/* 139 */     if (representsTag()) {
/* 140 */       return getJsonPrefix() + "#" + getJsonPrefix();
/*     */     }
/* 142 */     return getJsonPrefix() + getJsonPrefix();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 147 */     if (representsTag()) {
/* 148 */       return getType() + " Tag: " + getType();
/*     */     }
/* 150 */     return getType() + ": " + getType();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 155 */     if (o == this) {
/* 156 */       return true;
/*     */     }
/* 158 */     if (o instanceof AbstractNSSTag) { AbstractNSSTag<?> other = (AbstractNSSTag)o; if (isInstance(other))
/* 159 */         return (representsTag() == other.representsTag() && getResourceLocation().equals(other.getResourceLocation()));  }
/*     */     
/* 161 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 166 */     if (representsTag()) {
/* 167 */       return 31 + this.resourceLocation.hashCode();
/*     */     }
/* 169 */     return this.resourceLocation.hashCode();
/*     */   }
/*     */   
/*     */   protected abstract boolean isInstance(AbstractNSSTag<?> paramAbstractNSSTag);
/*     */   
/*     */   @NotNull
/*     */   protected abstract String getType();
/*     */   
/*     */   @NotNull
/*     */   protected abstract String getJsonPrefix();
/*     */   
/*     */   @NotNull
/*     */   protected abstract Optional<Either<HolderSet.Named<TYPE>, ITag<TYPE>>> getTag();
/*     */   
/*     */   protected abstract Function<TYPE, NormalizedSimpleStack> createNew();
/*     */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\nss\AbstractNSSTag.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */