package chessmaster.pieces;

import chessmaster.game.Coordinate;

public class King extends ChessPiece {
    public static final String KING_WHITE = "k"; // ♔
    public static final String KING_BLACK = "K"; // ♚

    public static final int[][] directions = {
        UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT,
    };

    public King(int row, int col, int color) {
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
        return color == ChessPiece.BLACK ? KING_BLACK : KING_WHITE;
    }

}
