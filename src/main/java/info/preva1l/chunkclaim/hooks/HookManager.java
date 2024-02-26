package info.preva1l.chunkclaim.hooks;

import info.preva1l.chunkclaim.ChunkClaim;
import info.preva1l.chunkclaim.hooks.advancedserverzones.AdvancedServerZonesHook;
import info.preva1l.chunkclaim.hooks.worldguard.WorldGuardHook;

public class HookManager {

    AdvancedServerZonesHook advancedServerZonesHook;
    WorldGuardHook worldGuardHook;

    public void registerHooks(ChunkClaim plugin) {

         advancedServerZonesHook = new AdvancedServerZonesHook();
         advancedServerZonesHook.register(plugin);

        worldGuardHook = new WorldGuardHook();
        worldGuardHook.register();
    }
}
