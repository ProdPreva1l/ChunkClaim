package info.preva1l.chunkclaim.hooks.worldguard;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import info.preva1l.chunkclaim.ChunkClaim;
import info.preva1l.chunkclaim.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;

public class WorldGuardHook {
    private static boolean enabled;

    public void register() {
        if (!Config.HOOK_WORLDGUARD_ENABLED.toBoolean()) {
            enabled = false;
            return;
        }
        WorldGuardPlugin worldGuard = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");
        if (worldGuard == null) {
            ChunkClaim.i().getLogger().warning("--------------------------------------");
            ChunkClaim.i().getLogger().warning("WorldGuard hook is enabled but the plugin was not found!");
            ChunkClaim.i().getLogger().warning("WorldGuard hook will not work as expected!");
            ChunkClaim.i().getLogger().warning("--------------------------------------");
            enabled = false;
            return;
        }
        enabled = true;
    }

    public static boolean isChunkInRestrictedRegion(Chunk chunk) {
        if (!enabled) {
            return false;
        }
        RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(chunk.getWorld()));
        if (regionManager == null) {
            return false;
        }
        int chunkX = chunk.getX();
        int chunkZ = chunk.getZ();
        int chunkMinX = chunkX << 4;
        int chunkMinZ = chunkZ << 4;
        int chunkMaxZ = (chunkZ << 4) + 15;
        BlockVector3 min = BlockVector3.at(chunkMinX, 0, chunkMinZ);
        BlockVector3 max = BlockVector3.at(chunkMinX, 10, chunkMaxZ);
        ProtectedRegion chunkRegion = new ProtectedCuboidRegion("dummy", min, max);
        ApplicableRegionSet set = regionManager.getApplicableRegions(chunkRegion);
        for (ProtectedRegion region : set.getRegions()) {
            if (Config.HOOK_WORLDGUARD_PROTECTED_REGIONS.toStringList().contains(region.getId())) {
                return true;
            }
        }
        return false;
    }
}
