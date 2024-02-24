package info.preva1l.chunkclaim.hooks.advancedserverzones;

import info.preva1l.chunkclaim.ChunkClaim;
import info.preva1l.chunkclaim.config.Config;
import lol.arch.survival.api.RegionsAPI;
import org.bukkit.Chunk;

public class AdvancedServerZonesHook {
    private static boolean enabled;
    public void register(ChunkClaim plugin) {
        if (!Config.HOOK_ASZ.toBoolean()) {
            enabled = false;
            return;
        }
        if (plugin.getServer().getPluginManager().getPlugin("AdvancedServerZones") == null) {
            ChunkClaim.i().getLogger().warning("--------------------------------------");
            ChunkClaim.i().getLogger().warning("AdvancedServerZones hook is enabled but the plugin was not found!");
            ChunkClaim.i().getLogger().warning("AdvancedServerZones hook will not work as expected!");
            ChunkClaim.i().getLogger().warning("--------------------------------------");
            enabled = false;
            return;
        }
        enabled = true;
        ChunkClaim.i().getLogger().info("AdvancedServerZones hook enabled!");
    }
    public static boolean isChunkProtected(Chunk chunk) {
        if (!enabled) return false;
        return RegionsAPI.isChunkProtected(chunk);
    }
}
