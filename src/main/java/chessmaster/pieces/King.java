package chessmaster.pieces;

public class King extends ChessPiece {
    public static final String KING_WHITE = "k"; // ♔
    public static final String KING_BLACK = "K"; // ♚

    public King(int row, int col, int color) {
        super(row, col, color);
    }

    @Override
    public String toString() {
        return color == ChessPiece.BLACK ? KING_BLACK : KING_WHITE;
    }

}
