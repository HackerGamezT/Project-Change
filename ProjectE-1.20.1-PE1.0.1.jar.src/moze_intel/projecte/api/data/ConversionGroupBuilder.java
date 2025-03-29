/*    */ package moze_intel.projecte.api.data;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.annotation.ParametersAreNonnullByDefault;
/*    */ import moze_intel.projecte.api.nss.NormalizedSimpleStack;
/*    */ import net.minecraft.MethodsReturnNonnullByDefault;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ @ParametersAreNonnullByDefault
/*    */ @MethodsReturnNonnullByDefault
/*    */ public class ConversionGroupBuilder
/*    */   implements CustomConversionNSSHelper<ConversionGroupBuilder.GroupConversionBuilder>
/*    */ {
/*    */   private final CustomConversionBuilder customConversionBuilder;
/* 20 */   private final List<GroupConversionBuilder> conversions = new ArrayList<>();
/*    */   @Nullable
/*    */   private String comment;
/*    */   
/*    */   ConversionGroupBuilder(CustomConversionBuilder customConversionBuilder) {
/* 25 */     this.customConversionBuilder = customConversionBuilder;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ConversionGroupBuilder comment(String comment) {
/* 34 */     CustomConversionBuilder.validateComment(this.comment, comment, "Group");
/* 35 */     this.comment = comment;
/* 36 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public GroupConversionBuilder conversion(NormalizedSimpleStack output, int amount) {
/* 41 */     if (amount < 1) {
/* 42 */       throw new IllegalArgumentException("Output amount for fixed value conversions must be at least one.");
/*    */     }
/* 44 */     GroupConversionBuilder builder = new GroupConversionBuilder(output, amount);
/* 45 */     this.conversions.add(builder);
/* 46 */     return builder;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CustomConversionBuilder end() {
/* 53 */     return this.customConversionBuilder;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   boolean hasComment() {
/* 60 */     return (this.comment != null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   JsonObject serialize() {
/* 67 */     JsonObject json = new JsonObject();
/* 68 */     if (this.comment != null) {
/* 69 */       json.addProperty("comment", this.comment);
/*    */     }
/* 71 */     if (!this.conversions.isEmpty())
/*    */     {
/*    */       
/* 74 */       json.add("conversions", (JsonElement)CustomConversionBuilder.serializeConversions((List)this.conversions));
/*    */     }
/* 76 */     return json;
/*    */   }
/*    */   
/*    */   public class GroupConversionBuilder
/*    */     extends ConversionBuilder<GroupConversionBuilder> {
/*    */     private GroupConversionBuilder(NormalizedSimpleStack output, int count) {
/* 82 */       super(output, count);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public ConversionGroupBuilder end() {
/* 92 */       validateIngredients();
/* 93 */       return ConversionGroupBuilder.this;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\data\ConversionGroupBuilder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */