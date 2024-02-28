package info.preva1l.chunkclaim.commands;

import info.preva1l.chunkclaim.guis.ClaimGUI;
import info.preva1l.chunkclaim.utils.commands.Command;
import info.preva1l.chunkclaim.utils.commands.CommandArgs;
import info.preva1l.chunkclaim.utils.commands.CommandArguments;

public class ClaimCommand extends Command {
    @CommandArgs(name = "claim", aliases = {"land", "lands", "town", "claims"}, permission = "chunkclaim.claim")
    public void execute(CommandArguments command) {
        new ClaimGUI(command.getPlayer()).open(command.getPlayer());
    }
}
