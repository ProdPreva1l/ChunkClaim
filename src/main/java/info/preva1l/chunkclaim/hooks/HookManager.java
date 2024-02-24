package info.preva1l.chunkclaim.hooks;

import info.preva1l.chunkclaim.ChunkClaim;
import info.preva1l.chunkclaim.hooks.advancedserverzones.AdvancedServerZonesHook;

public class HookManager {

    AdvancedServerZonesHook advancedServerZonesHook;

    public void registerHooks(ChunkClaim plugin) {

         advancedServerZonesHook = new AdvancedServerZonesHook();
         advancedServerZonesHook.register(plugin);
    }
}
