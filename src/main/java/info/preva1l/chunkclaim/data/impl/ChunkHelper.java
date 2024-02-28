package info.preva1l.chunkclaim.data.impl;

import info.preva1l.chunkclaim.hooks.advancedserverzones.AdvancedServerZonesHook;
import info.preva1l.chunkclaim.hooks.worldguard.WorldGuardHook;
import lombok.experimental.UtilityClass;
import org.bukkit.Chunk;

@UtilityClass
public class ChunkHelper {
    public String getReadableCoords(Chunk chunk) {
        return chunk.getX() + ", " + chunk.getZ();
    }
    public boolean isClaimable(Chunk chunk) {
        return !AdvancedServerZonesHook.isChunkProtected(chunk) && !WorldGuardHook.isChunkInRestrictedRegion(chunk);
    }
}
