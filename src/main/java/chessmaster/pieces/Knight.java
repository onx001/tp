package chessmaster.pieces;

import chessmaster.game.Coordinate;

public class Knight extends ChessPiece {
    public static final String KNIGHT_WHITE = "n"; // ♘
    public static final String KNIGHT_BLACK = "N"; // ♞

    public static final int[][] directions = {
        UP_UP_LEFT, UP_UP_RIGHT, DOWN_DOWN_LEFT, DOWN_DOWN_RIGHT,
        LEFT_UP_LEFT, LEFT_DOWN_LEFT, RIGHT_UP_RIGHT, RIGHT_DOWN_RIGHT,
    };

    public Knight(int row, int col, int color) {
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
        return color == ChessPiece.BLACK ? KNIGHT_BLACK : KNIGHT_WHITE;
    }
}
