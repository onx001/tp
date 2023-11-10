package chessmaster.pieces;

import java.util.ArrayList;

import chessmaster.game.ChessBoard;
import chessmaster.game.Color;
import chessmaster.game.Coordinate;

public class Rook extends ChessPiece {
    public static final String ROOK_WHITE = "r"; // ♖
    public static final String ROOK_BLACK = "R"; // ♜

    public static final int[][] DIRECTIONS = {
        UP, DOWN, LEFT, RIGHT,
    };

    protected static int points = 50;
    protected static int[][] boardWeight =
        {{0,0,0,0,0,0,0,0},
        {1,2,2,2,2,2,2,1},
        {-1,0,0,0,0,0,0,-1},
        {-1,0,0,0,0,0,0,-1},
        {-1,0,0,0,0,0,0,-1},
        {-1,0,0,0,0,0,0,-1},
        {-1,0,0,0,0,0,0,-1},
        {0,0,0,1,1,0,0,0}};

    public Rook(int row, int col, Color color) {
        super(row, col, color);
        this.setPoints(points);
        this.setBoardWeight(boardWeight);
        assert color != Color.EMPTY : "Rook piece should have either black or white color";
    }

    @Override
    public Coordinate[] getPseudoLegalCoordinates(ChessBoard board) {
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

        return flattenArray(result);
    }

    @Override
    public String toString() {
        return color == Color.BLACK ? ROOK_BLACK : ROOK_WHITE;
    }
}
