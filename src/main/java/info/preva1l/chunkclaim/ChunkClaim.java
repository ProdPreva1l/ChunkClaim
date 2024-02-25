package info.preva1l.chunkclaim;

import info.preva1l.CacheHandler;
import info.preva1l.CollectionHelper;
import info.preva1l.SimpleMongoHelper;
import info.preva1l.chunkclaim.config.Config;
import info.preva1l.chunkclaim.config.Menus;
import info.preva1l.chunkclaim.data.impl.ClaimMemberStorage;
import info.preva1l.chunkclaim.data.settings.ClaimSettingsManager;
import info.preva1l.chunkclaim.hooks.HookManager;
import info.preva1l.chunkclaim.utils.BasicConfig;
import info.preva1l.chunkclaim.utils.commands.CommandManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class ChunkClaim extends JavaPlugin {
    private static ChunkClaim INSTANCE;

    private SimpleMongoHelper simpleMongoHelper;
    private CacheHandler cacheHandler;
    private CollectionHelper collectionHelper;

    ClaimMemberStorage claimMemberStorage;

    private BasicConfig configFile;
    private BasicConfig menusFile;


    private ClaimSettingsManager claimSettingsManager;
    private CommandManager commandManager;

    @Override
    public void onEnable() {
        INSTANCE = this;

        loadCommands();
        loadFiles();
        loadDatabase();
        loadStorage();

        new HookManager().registerHooks(this);
    }

    @Override
    public void onDisable() {

    }

    private void loadCommands() {
        commandManager = new CommandManager(this);

        commandManager.getLoadedCommands().forEach(command -> getLogger().info((command.getAssigned().async() ? "Async " : "") + "Command Loaded: " + command.getAssigned().name()));
    }

    private void loadFiles() {
        configFile = new BasicConfig(this, "config.yml");
        menusFile = new BasicConfig(this, "menus.yml");

        Config.loadDefault();
        Menus.loadDefault();
    }

    private void loadDatabase() {
        simpleMongoHelper = new SimpleMongoHelper(
                Config.MONGO_HOST.toString(),
                Config.MONGO_PORT.toInteger(),
                Config.MONGO_USERNAME.toString(),
                Config.MONGO_PASSWORD.toString(),
                Config.MONGO_DATABASE.toString(),
                Config.MONGO_AUTHDB.toString()
        );
        cacheHandler = new CacheHandler(simpleMongoHelper);
        collectionHelper = new CollectionHelper(simpleMongoHelper, cacheHandler);
    }

    private void loadStorage() {
        claimMemberStorage = new ClaimMemberStorage();

        claimSettingsManager = new ClaimSettingsManager();
    }

    public static ChunkClaim i() {
        return INSTANCE;
    }
}
