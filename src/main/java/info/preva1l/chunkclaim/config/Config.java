package info.preva1l.chunkclaim.config;

import com.google.common.collect.ImmutableList;
import info.preva1l.chunkclaim.ChunkClaim;
import info.preva1l.chunkclaim.utils.BasicConfig;
import info.preva1l.chunkclaim.utils.Text;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor
public enum Config {
    DEBUG("debug", false),
    SERVER_NAME("server-name", "server-01"),
    /*
        Hooks
     */
    HOOK_ASZ("hooks.advanced-server-zones", false),

    HOOK_WORLDGUARD_ENABLED("hooks.worldguard.enabled", false),
    HOOK_WORLDGUARD_PROTECTED_REGIONS("hooks.worldguard.protected-regions", Collections.singletonList("")),

    /*
        Database
     */
    MONGO_HOST("mongo.host", "127.0.0.1"),
    MONGO_PORT("mongo.port", 27017),
    MONGO_USERNAME("mongo.username", "username"),
    MONGO_PASSWORD("mongo.password", "password"),
    MONGO_DATABASE("mongo.database", "database"),
    MONGO_AUTHDB("mongo.auth-db", "authDB"),
    ;

    private final String path;
    private final Object defaultValue;

    public String toString() {
        String str = ChunkClaim.i().getConfigFile().getString(path);
        if (str.equals(path)) {
            return defaultValue.toString();
        }
        return str;
    }

    public String toFormattedString() {
        String str = ChunkClaim.i().getConfigFile().getString(path);
        if (str.equals(path)) {
            return Text.message(defaultValue.toString());
        }
        return Text.message(str);
    }

    public List<String> toStringList() {
        List<String> str = ChunkClaim.i().getConfigFile().getStringList(path);
        if (str.isEmpty() || str.get(0).equals(path)) {
            return (List<String>) defaultValue;
        }
        if (str.get(0).equals("null")) {
            return ImmutableList.of();
        }
        return str;
    }

    public List<String> toLore() {
        List<String> str = ChunkClaim.i().getConfigFile().getStringList(path);
        if (str.isEmpty() || str.get(0).equals(path)) {
            return Text.lore((List<String>) defaultValue);
        }
        if (str.get(0).equals("null")) {
            return ImmutableList.of();
        }
        return Text.lore(str);
    }

    public boolean toBoolean() {
        return Boolean.parseBoolean(toString());
    }

    public int toInteger() {
        return Integer.parseInt(toString());
    }

    public double toDouble() {
        return Double.parseDouble(toString());
    }

    public static void loadDefault() {
        BasicConfig configFile = ChunkClaim.i().getConfigFile();

        for (Config config : Config.values()) {
            String path = config.getPath();
            String str = configFile.getString(path);
            if (str.equals(path)) {
                configFile.getConfiguration().set(path, config.getDefaultValue());
            }
        }

        configFile.save();
        configFile.load();
    }
}
