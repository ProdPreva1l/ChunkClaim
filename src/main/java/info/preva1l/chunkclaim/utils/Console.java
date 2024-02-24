package info.preva1l.chunkclaim.utils;

import info.preva1l.chunkclaim.ChunkClaim;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;

@UtilityClass
public class Console {
    private final ChunkClaim plugin = ChunkClaim.i();
    public void info(String message) {
        plugin.getLogger().info(message);
    }
    public void warn(String message) {
        plugin.getLogger().warning(message);
    }
    public void severe(String message) {
        plugin.getLogger().severe(message);
    }
    public void debug(String message) {
        plugin.getLogger().severe("[DEBUG] " + message);
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (player.hasPermission("chunkclaim.debug")) {
                player.sendMessage(Text.message("&c[DEBUG] " + message));
            }
        });
    }
}
