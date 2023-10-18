package chessmaster.pieces;

import java.util.ArrayList;

import chessmaster.game.ChessBoard;
import chessmaster.game.ChessTile;
import chessmaster.game.Coordinate;

public class Bishop extends ChessPiece {
    public static final String BISHOP_WHITE = "b"; // ♗
    public static final String BISHOP_BLACK = "B"; // ♝

    public static final int[][] DIRECTIONS = {
        UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT,
    };

    public Bishop(int row, int col, int color) {
        super(row, col, color);
    }

    @Override
    public Coordinate[][] getAvailableCoordinates(ChessTile[][] board) {
        Coordinate[][] result = new Coordinate[DIRECTIONS.length][0];

        for (int dir = 0; dir < DIRECTIONS.length; dir++) {
            int offsetX = DIRECTIONS[dir][0];
            int offsetY = DIRECTIONS[dir][1];

            int multiplier = 1;
            ArrayList<Coordinate> possibleCoordInDirection = new ArrayList<>();
            boolean isBlocked = false;
            while (multiplier < ChessBoard.SIZE && position.isOffsetWithinBoard(offsetX, offsetY) && !isBlocked) {

                Coordinate possibleCoord = position.addOffsetToCoordinate(offsetX, offsetY);
                ChessPiece destPiece = board[possibleCoord.getY()][possibleCoord.getX()].getChessPiece();
                if (destPiece != null) {
                    if (destPiece.getColour() != this.color) {
                        possibleCoordInDirection.add(possibleCoord);
                    }
                    isBlocked = true;
                } else {
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
        return color == ChessPiece.BLACK ? BISHOP_BLACK : BISHOP_WHITE;
    }
    

}
