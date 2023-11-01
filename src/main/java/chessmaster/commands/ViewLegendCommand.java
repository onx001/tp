//@@author TriciaBK
package chessmaster.commands;

import chessmaster.game.ChessBoard;
import chessmaster.ui.TextUI;

public class ViewLegendCommand extends Command {

    public static final String VIEW_LEGEND_COMMAND_STRING = "legend";

    private static final String[] LEGEND_STRINGS = {
        "Black pieces:",
        "\"R\" represents a black rook.",
        "\"N\" represents a black knight.",
        "\"B\" represents a black bishop.",
        "\"Q\" represents a black queen.",
        "\"K\" represents a black king.",
        "\"P\" represents a black pawn.",
        "   ",
        "White pieces:",
        "\"r\" represents a white rook.",
        "\"n\" represents a white knight.",
        "\"b\" represents a white bishop.",
        "\"q\" represents a white queen.",
        "\"k\" represents a white king.",
        "\"p\" represents a white pawn"

    };

    @Override
    public CommandResult execute(ChessBoard board, TextUI ui) {
        return new CommandResult(LEGEND_STRINGS);
    }

}
