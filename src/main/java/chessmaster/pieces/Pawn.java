package chessmaster.pieces;

import chessmaster.game.Coordinate;

public class Pawn extends ChessPiece {
    public static final String PAWN_WHITE = "p"; // ♙
    public static final String PAWN_BLACK = "P"; // ♟

    public static final int[][] directions = {
        UP_LEFT, UP_RIGHT, UP, UP_UP,
    };

    public Pawn(int row, int col, int color) {
        super(row, col, color);
    }

    @Override
    public Coordinate[][] getAvailablCoordinates() {
        Coordinate[][] result = new Coordinate[directions.length][0];

        for (int dir = 0; dir < directions.length; dir++) {
            int offsetX = directions[dir][0];
            int offsetY = directions[dir][1];

            if (position.isOffsetWithinBoard(offsetX, offsetY)) {
                Coordinate dest = position.addOffsetToCoordinate(offsetX, offsetY);
                result[dir] = new Coordinate[]{ dest };
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return color == ChessPiece.BLACK ? PAWN_BLACK : PAWN_WHITE;
    }
}
