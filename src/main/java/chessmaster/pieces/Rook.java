package chessmaster.pieces;

import java.util.ArrayList;

import chessmaster.game.ChessBoard;
import chessmaster.game.Coordinate;

public class Rook extends ChessPiece {
    public static final String ROOK_WHITE = "r"; // ♖
    public static final String ROOK_BLACK = "R"; // ♜

    public static final int[][] DIRECTIONS = {
        UP, DOWN, LEFT, RIGHT,
    };

    public Rook(int row, int col, int color) {
        super(row, col, color);
    }

    @Override
    public Coordinate[][] getAvailableCoordinates() {
        Coordinate[][] result = new Coordinate[DIRECTIONS.length][0];

        for (int dir = 0; dir < DIRECTIONS.length; dir++) {
            int offsetX = DIRECTIONS[dir][0];
            int offsetY = DIRECTIONS[dir][1];

            int multiplier = 1;
            ArrayList<Coordinate> possibleCoordInDirection = new ArrayList<>();
            while (multiplier < ChessBoard.SIZE && position.isOffsetWithinBoard(offsetX, offsetY)) {

                Coordinate possibleCoord = position.addOffsetToCoordinate(offsetX, offsetY);
                possibleCoordInDirection.add(possibleCoord);

                multiplier++;
                offsetX = DIRECTIONS[dir][0] * multiplier;
                offsetY = DIRECTIONS[dir][1] * multiplier;
            }

            // Convert arraylist to array
            result[dir] = possibleCoordInDirection.toArray(new Coordinate[0]);
        }

        return result;
    }

    @Override
    public String toString() {
        return color == ChessPiece.BLACK ? ROOK_BLACK : ROOK_WHITE;
    }
}
