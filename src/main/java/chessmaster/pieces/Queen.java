package chessmaster.pieces;

import java.util.ArrayList;

import chessmaster.exceptions.NullPieceException;
import chessmaster.game.ChessBoard;
import chessmaster.game.Coordinate;

public class Queen extends ChessPiece {
    public static final String QUEEN_WHITE = "q"; // ♕
    public static final String QUEEN_BLACK = "Q"; // ♛

    public static final int[][] DIRECTIONS = {
        UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT,
    };

    public Queen(int row, int col, int color) {
        super(row, col, color);
    }

    @Override
    public Coordinate[][] getAvailableCoordinates(ChessBoard board) {
        Coordinate[][] result = new Coordinate[DIRECTIONS.length][0];

        for (int dir = 0; dir < DIRECTIONS.length; dir++) {
            int offsetX = DIRECTIONS[dir][0];
            int offsetY = DIRECTIONS[dir][1];
            boolean isBlocked = false;

            int multiplier = 1;
            ArrayList<Coordinate> possibleCoordInDirection = new ArrayList<>();
            while (multiplier < ChessBoard.SIZE && position.isOffsetWithinBoard(offsetX, offsetY) && !isBlocked) {

                Coordinate possibleCoord = position.addOffsetToCoordinate(offsetX, offsetY);
                try {
                    ChessPiece destPiece = board.getPieceAtCoor(possibleCoord);
                    if (!destPiece.getType().equals(EmptyPiece.EMPTY_PIECE)) {
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
                } catch (NullPieceException e) {
                    e.printStackTrace();
                }
            }

            // Convert arraylist to array
            result[dir] = possibleCoordInDirection.toArray(new Coordinate[0]);
        }

        return result;
    }

    @Override
    public String toString() {
        return color == ChessPiece.BLACK ? QUEEN_BLACK : QUEEN_WHITE;
    }

    @Override
    public String getType() {
        return QUEEN_WHITE;
    }
}
