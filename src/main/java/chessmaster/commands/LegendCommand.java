//@@author TriciaBK
package chessmaster.commands;

import chessmaster.game.Game;
import chessmaster.pieces.Bishop;
import chessmaster.pieces.King;
import chessmaster.pieces.Knight;
import chessmaster.pieces.Pawn;
import chessmaster.pieces.Queen;
import chessmaster.pieces.Rook;

public class LegendCommand extends Command {

    public static final String LEGEND_COMMAND_STRING = "legend";

    private static final String[] LEGEND_STRINGS = {
        "Black pieces:",
        String.format("\"%s\" represents a black rook.", Rook.ROOK_BLACK),
        String.format("\"%s\" represents a black knight.", Knight.KNIGHT_BLACK),
        String.format("\"%s\" represents a black bishop.", Bishop.BISHOP_BLACK),
        String.format("\"%s\" represents a black queen.", Queen.QUEEN_BLACK),
        String.format("\"%s\" represents a black king.", King.KING_BLACK),
        String.format("\"%s\" represents a black pawn.", Pawn.PAWN_BLACK),
        "   ",
        "White pieces:",
        String.format("\"%s\" represents a white rook.", Rook.ROOK_WHITE),
        String.format("\"%s\" represents a white knight.", Knight.KNIGHT_WHITE),
        String.format("\"%s\" represents a white bishop.", Bishop.BISHOP_WHITE),
        String.format("\"%s\" represents a white queen.", Queen.QUEEN_WHITE),
        String.format("\"%s\" represents a white king.", King.KING_WHITE),
        String.format("\"%s\" represents a white pawn.", Pawn.PAWN_WHITE),
    };

    @Override
    public CommandResult execute(Game game) {
        return new CommandResult(LEGEND_STRINGS);
    }
}
