package info.preva1l.chunkclaim.config;

import com.google.common.collect.ImmutableList;
import info.preva1l.chunkclaim.ChunkClaim;
import info.preva1l.chunkclaim.utils.BasicConfig;
import info.preva1l.chunkclaim.utils.Text;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public enum Lang {
    PREFIX("prefix", "&aChunk Claim &8\u00BB"),

    /*
        Errors
     */
    ERROR_NO_PERMISSION("errors.no-permission", "&cInsufficient Permission"),
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

        for (Lang config : Lang.values()) {
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