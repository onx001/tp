package chessmaster.commands;

import chessmaster.game.ChessBoard;

public class RulesCommand extends Command {

    public static final String RULES_COMAMND_STRING = "rules";

    private static final String[] RULES_STRINGS = {
        "Here are some simple rules to get you started with chess: ",
        "",
        "Movement:",
        "   Pawns move forward one square but capture diagonally.",
        "   Rooks move horizontally and vertically any number of squares.",
        "   Knights move in an L-shape: two squares in one direction and one square perpendicular to that.",
        "   Bishops move diagonally any number of squares.",
        "   Queens move horizontally, vertically, and diagonally any number of squares.",
        "   Kings move one square in any direction.",
        "",
        "Special Rules:",
        "   Castling - Under certain conditions, the king and one of the rooks can move simultaneously. " + 
            "Castling is a way to safeguard the king and connect the rooks.",
        "   En Passant - If a pawn moves two squares forward from its starting position and lands beside " + 
            "an opponent's pawn, the opponent has the option to capture the first pawn as if it had moved only " + 
            "one square forward.",
        "   Pawn Promotion - When a pawn reaches the opponent's back rank, it can be promoted to any other piece " + 
            "(except a king), typically a queen.",
        "",
        "Objective:",
        "The game ends when one player's king is in checkmate, meaning it is under attack and cannot escape capture.",
        "The player delivering checkmate wins the game."
    };

    @Override
    public CommandResult execute(ChessBoard board) {
        return new CommandResult(RULES_STRINGS);
    }

}
