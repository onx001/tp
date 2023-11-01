//@@author TriciaBK
package chessmaster.commands;

import chessmaster.game.ChessBoard;
import chessmaster.ui.TextUI;

public class ViewLegendCommand extends Command {

    public static final String VIEW_LEGEND_COMMAND_STRING = "legend";

    private static final String[] LEGEND_STRINGS = {
        "Black pieces:",
        "\"r\" represents a black rook.",
        "\"n\" represents a black knight.",
        "\"b\" represents a black bishop.",
        "\"q\" represents a black queen.",
        "\"k\" represents a black king.",
        "\"p\" represents a black pawn.",
        "White pieces:",
        "\"R\" represents a white rook.",
        "\"N\" represents a white knight.",
        "\"B\" represents a white bishop.",
        "\"Q\" represents a white queen.",
        "\"K\" represents a white king.",
        "\"P\" represents a white pawn"

    };

    @Override
    public CommandResult execute(ChessBoard board, TextUI ui) {
        return new CommandResult(LEGEND_STRINGS);
    }

}
