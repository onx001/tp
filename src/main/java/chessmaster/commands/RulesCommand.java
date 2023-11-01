//@@author TriciaBK
package chessmaster.commands;

import chessmaster.game.ChessBoard;
import chessmaster.ui.TextUI;

public class RulesCommand extends Command {

    public static final String RULES_COMAMND_STRING = "rules";

    private static final String[] RULES_STRINGS = {
        "Here are simple chess rules to get you started:",
        "",
        "Piece movement:",
        "   Pawns (P) move forward one square but capture diagonally.",
        "   Rooks (R) move horizontally and vertically any number of squares.",
        "   Knights (N) move in an L-shape: two squares in one direction and one square perpendicular.",
        "   Bishops (B) move diagonally any number of squares.",
        "   Queens (Q) move horizontally, vertically, and diagonally any number of squares.",
        "   Kings (K) move one square in any direction.",
        "",
        "Special Rules:",
        "   Castling - King and rook move simultaneously to safeguard the king.",
        "   En Passant - Pawn capturing when moving two squares from starting position.",
        "   Pawn Promotion - Promote a pawn to another piece (except king) upon reaching the back rank.",
        "",
        "Objective:",
        "   Game ends when one player's king is in checkmate, under attack and can't escape capture.",
        "   The delivering player wins the game.",
    };


    @Override
    public CommandResult execute(ChessBoard board, TextUI ui) {
        return new CommandResult(RULES_STRINGS);
    }

}
