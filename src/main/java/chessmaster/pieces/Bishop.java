package chessmaster.pieces;

import java.util.ArrayList;

import chessmaster.game.ChessBoard;
import chessmaster.game.Color;
import chessmaster.game.Coordinate;

public class Bishop extends ChessPiece {
    public static final String BISHOP_WHITE = "b"; // ♗
    public static final String BISHOP_BLACK = "B"; // ♝

    public static final int[][] DIRECTIONS = {
        UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT,
    };

    public Bishop(int row, int col, Color color) {
        super(row, col, color);
    }

    @Override
    public Coordinate[][] getAvailableCoordinates(ChessBoard board) {
        Coordinate[][] result = new Coordinate[DIRECTIONS.length][0];

        for (int dir = 0; dir < DIRECTIONS.length; dir++) {
            int offsetX = DIRECTIONS[dir][0];
            int offsetY = DIRECTIONS[dir][1];

            int multiplier = 1;
            boolean isBlocked = false;
            ArrayList<Coordinate> possibleCoordInDirection = new ArrayList<>();
            
            while (!isBlocked && multiplier < ChessBoard.SIZE && position.isOffsetWithinBoard(offsetX, offsetY)) {
                Coordinate possibleCoord = position.addOffsetToCoordinate(offsetX, offsetY);
                ChessPiece destPiece = board.getPieceAtCoor(possibleCoord);

                isBlocked = !destPiece.isEmptyPiece();
                if (destPiece.isEmptyPiece() || isOpponent(destPiece)) {
                    possibleCoordInDirection.add(possibleCoord);
                } 

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
        return color == Color.BLACK ? BISHOP_BLACK : BISHOP_WHITE;
    }
}
