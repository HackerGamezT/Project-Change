package moze_intel.projecte.api.mapper.recipe;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RecipeTypeMapper {
  int priority() default 0;
  
  String[] requiredMods() default {""};
  
  @Target({ElementType.FIELD})
  @Retention(RetentionPolicy.RUNTIME)
  public static @interface Instance {}
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\api\mapper\recipe\RecipeTypeMapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */