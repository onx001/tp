//@@author TriciaBK
package chessmaster.commands;

import chessmaster.game.Game;

public class HelpCommand extends Command {

    public static final String HELP_COMMAND_STRING = "help";

    private static final String[] HELP_STRINGS = { 
        "Here are the following commands to play:",
        "move\tMove piece",
        "\tFormat: move [column][row] [column][row]",
        "\te.g. move a2 a3",
        "moves\tShow available moves for a piece",
        "\tFormat: moves [column][row]",
        "\te.g. moves a2",
        "show\tShow the chessboard",
        "rules\tObtain a quick refresher on the rules of chess",
        "legend\tView pieces representation",
        "restart\tStart a new game",
        "history\tView history of all game moves",
        "abort\tAbort game",
    };

    @Override
    public CommandResult execute(Game game) {
        return new CommandResult(HELP_STRINGS);
    }

}
