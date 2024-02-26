package info.preva1l.chunkclaim.data.models;

import info.preva1l.chunkclaim.hooks.worldguard.WorldGuardHook;
import lol.arch.survival.api.RegionsAPI;
import org.bukkit.Chunk;

public interface ClaimableChunk extends Chunk {
    default boolean isClaimable() {
        return !RegionsAPI.isChunkProtected(this) && !WorldGuardHook.isChunkInRestrictedRegion(this);
    }
}
