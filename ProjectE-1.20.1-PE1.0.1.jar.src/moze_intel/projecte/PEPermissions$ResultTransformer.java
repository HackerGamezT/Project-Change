package moze_intel.projecte;

import java.util.UUID;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.server.permission.nodes.PermissionDynamicContext;
import org.jetbrains.annotations.Nullable;

@FunctionalInterface
interface ResultTransformer<T> {
  T transform(@Nullable ServerPlayer paramServerPlayer, UUID paramUUID, T paramT, PermissionDynamicContext<?>... paramVarArgs);
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\PEPermissions$ResultTransformer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */