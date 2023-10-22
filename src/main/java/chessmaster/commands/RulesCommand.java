package chessmaster.commands;

public class RulesCommand extends Command {

    public static final String RULES_COMAMND_STRING = "rules";

    private static final String[] RULES_STRINGS = {
            "Here are some simple rules to get you started with chess: \n" +
            "\n" +
            "Movement:\n" +
            "   Pawns move forward one square but capture diagonally.\n" +
            "   Rooks move horizontally and vertically any number of squares.\n" +
            "   Knights move in an L-shape: two squares in one direction and one square perpendicular to that.\n" +
            "   Bishops move diagonally any number of squares.\n" +
            "   Queens move horizontally, vertically, and diagonally any number of squares.\n" +
            "   Kings move one square in any direction.\n" +
            "\n" +
            "Special Rules:\n" +
            "   Castling - Under certain conditions, the king and one of the rooks can move simultaneously. Castling is a way to safeguard the king and connect the rooks.\n" +
            "   En Passant - If a pawn moves two squares forward from its starting position and lands beside an opponent's pawn, the opponent has the option to capture the first pawn as if it had moved only one square forward.\n" +
            "   Pawn Promotion - When a pawn reaches the opponent's back rank, it can be promoted to any other piece (except a king), typically a queen.\n" +
            "\n" +
            "Objective:\n" +
            "The game ends when one player's king is in checkmate, meaning it is under attack and cannot escape capture.\n" +
            "The player delivering checkmate wins the game.\n"
    };

    @Override
    public CommandResult execute() {
        return new CommandResult(RULES_STRINGS);
    }

}
