package chessmaster.pieces;

public class ChessPiece {
    private int row;
    private int col;

    public static int convertRowIndex(char row) {
        int value = Integer.parseInt(String.valueOf(row));
        return (value - 8) * -1;
    }

    public static int convertColIndex(char col) {
        String COLUMN = "abcdefgh";
        return COLUMN.indexOf(col);
    }
}
