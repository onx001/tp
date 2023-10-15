package chessmaster.pieces;

abstract public class ChessPiece {

    public static final int BLACK = 0;
    public static final int WHITE = 1;

    protected int row;
    protected int col;
    protected int color;

    public ChessPiece(int row, int col, int color) {
        this.row = row;
        this.col = col;
        this.color = color;
    }

    public static int convertRowIndex(char row) {
        int value = Integer.parseInt(String.valueOf(row));
        return (value - 8) * -1;
    }

    public static int convertColIndex(char col) {
        String COLUMN = "abcdefgh";
        return COLUMN.indexOf(col);
    }
}
