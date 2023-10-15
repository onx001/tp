package chessmaster.pieces;

public class King extends ChessPiece {
    public static final String KING_KEY = "k";

    public static final String KING_WHITE = "k"; // ♔
    public static final String KING_BLACK = "K"; // ♚

    public King(int row, int col, int player) {
        super(row, col, player);
    }

    @Override
    public String toString() {
        return color == 1 ? KING_BLACK : KING_WHITE;
    }

    public void checkMove(int x, int y) {
        // ! Loop through all available moves
        // Check if x and y is one of them
    }
}
