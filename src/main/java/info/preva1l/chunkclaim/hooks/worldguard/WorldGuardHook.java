package info.preva1l.chunkclaim.hooks.worldguard;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.RegionQuery;
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
        return Config.HOOK_WORLDGUARD_PROTECTED_REGIONS.toStringList().contains(getRegionAtLocation(chunk.getBlock(1,1,1).getLocation()));
    }

    private static String getRegionAtLocation(Location loc) {
        if (!enabled) {
            return null;
        }

        RegionQuery query = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery();

        ApplicableRegionSet applicableRegions = query.getApplicableRegions(BukkitAdapter.adapt(loc));

        if (applicableRegions.size() > 0) {
            return applicableRegions.iterator().next().getId();
        } else {
            return null;
        }
    }
}
