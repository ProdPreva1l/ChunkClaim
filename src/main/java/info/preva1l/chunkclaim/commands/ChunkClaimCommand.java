package info.preva1l.chunkclaim.commands;

import info.preva1l.chunkclaim.ChunkClaim;
import info.preva1l.chunkclaim.config.Lang;
import info.preva1l.chunkclaim.utils.Text;
import info.preva1l.chunkclaim.utils.commands.Command;
import info.preva1l.chunkclaim.utils.commands.CommandArgs;
import info.preva1l.chunkclaim.utils.commands.CommandArguments;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

public class ChunkClaimCommand extends Command {
    @CommandArgs(name = "chunkclaim", aliases = {"cc"}, inGameOnly = false)
    public void execute(CommandArguments command) {
        CommandSender sender = command.getSender();
        SubCommand subCommand;
        try {
            subCommand = SubCommand.valueOf(command.getArgs().length > 0 ? command.getArgs()[0] : "info");
        } catch (EnumConstantNotPresentException e) {
            sender.sendMessage(Text.message("&cSub-command not found!"));
            return;
        }

        switch(subCommand) {
            case RELOAD:
                if (!sender.hasPermission("chunkclaim.reload")) {
                    sender.sendMessage(Lang.ERROR_NO_PERMISSION.toFormattedString());
                    return;
                }
                reload();
                sender.sendMessage(Text.message("&aConfig Reloaded!"));
                return;
            case HELP:
                sender.sendMessage(Text.message("&aChunkClaim &fby Preva1l &aCommands"));
                sender.sendMessage(Text.message("\uE212"));
                return;
            case INFO:
                PluginDescriptionFile pdf = plugin.getDescription();
                sender.sendMessage(Text.message("&aChunkClaim &fby Preva1l"));
                sender.sendMessage(Text.message("&aVersion: " + pdf.getVersion()));
                sender.sendMessage(Text.message("&aDiscord: preva1l"));
        }
    }

    private void reload() {
        ChunkClaim.i().getConfigFile().load();
        ChunkClaim.i().getMenusFile().load();
    }

    private enum SubCommand {
        RELOAD,
        HELP,
        INFO
    }
}