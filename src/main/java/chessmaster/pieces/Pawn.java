package chessmaster.pieces;

import chessmaster.game.ChessBoard;
import chessmaster.game.Color;
import chessmaster.game.Coordinate;

public class Pawn extends ChessPiece {
    public static final String PAWN_WHITE = "p"; // ♙
    public static final String PAWN_BLACK = "P"; // ♟

    public static final int[][] DIRECTIONS_UP = {
        UP_LEFT, UP_RIGHT, UP, UP_UP,
    };
    public static final int[][] DIRECTIONS_DOWN = {
        DOWN_LEFT, DOWN_RIGHT, DOWN, DOWN_DOWN,
    };

    protected static int points = 10;
    protected static int[][] boardWeight = 
        {{0,0,0,0,0,0,0,0},
        {5,5,5,5,5,5,5,5},
        {1,1,2,3,3,2,1,1},
        {1,1,1,4,4,1,1,1},
        {0,0,0,3,3,0,0,0},
        {1,-1,-1,0,0,-1,-1,1},
        {1,1,1,-2,-2,1,1,1},
        {0,0,0,0,0,0,0,0}};
    protected boolean enPassed = false;

    public Pawn(int row, int col, Color color) {
        super(row, col, color);
        this.setPoints(points);
        this.setBoardWeight(boardWeight);
        assert color != Color.EMPTY : "Pawn piece should have either black or white color";
    }

    @Override
    public Coordinate[] getPseudoLegalCoordinates(ChessBoard board) {
        Coordinate[][] result = new Coordinate[DIRECTIONS_UP.length][0];
        int[][] directions = board.isPieceFriendly(this) ? DIRECTIONS_UP : DIRECTIONS_DOWN;
        
        boolean canEnPassant = false;
        Coordinate enPassantCoor = null;

        for (int dir = 0; dir < DIRECTIONS_UP.length; dir++) {
            int offsetX = directions[dir][0];
            int offsetY = directions[dir][1];

            if (!position.isOffsetWithinBoard(offsetX, offsetY)) {
                continue; // Possible coordinate out of board
            }

            Coordinate newCoor = position.addOffsetToCoordinate(offsetX, offsetY);
            ChessPiece destPiece = board.getPieceAtCoor(newCoor);
            boolean isThisPlayerEat = directions[dir] == UP_LEFT || directions[dir] == UP_RIGHT;
            boolean isOpponentEat = directions[dir] == DOWN_LEFT || directions[dir] == DOWN_RIGHT;

            if (isThisPlayerEat || isOpponentEat) {

                if (board.hasEnPassant()) {
                    enPassantCoor = board.getEnPassantCoor();
                    ChessPiece enPassantPiece = board.getEnPassantPiece();
                    canEnPassant = newCoor.equals(enPassantCoor) && isOpponent(enPassantPiece);
                }



                // Diagonal move: Destination tile has opponent piece
                if ( (!destPiece.isEmptyPiece() && isOpponent(destPiece)) || canEnPassant) {
                    result[dir] = new Coordinate[]{ newCoor };
                }

                
            
            } else if (directions[dir] == UP || directions[dir] == DOWN) {
                // Normal move: when destination tile is empty
                if (destPiece.isEmptyPiece()) {
                    result[dir] = new Coordinate[]{ newCoor };
                }

            } else if (directions[dir] == UP_UP || directions[dir] == DOWN_DOWN) {
                // Double move: first move AND when destination empty AND no blocking piece
                Coordinate blockPos = board.isPieceFriendly(this)
                    ? position.addOffsetToCoordinate(UP[0], UP[1])
                    : position.addOffsetToCoordinate(DOWN[0], DOWN[1]);
                ChessPiece blockPiece = board.getPieceAtCoor(blockPos);

                if (!hasMoved && blockPiece.isEmptyPiece() && destPiece.isEmptyPiece()) {
                    result[dir] = new Coordinate[]{ newCoor };
                }

            }
        }

        return flattenArray(result);
    }

    @Override
    public String toString() {
        return color == Color.BLACK ? PAWN_BLACK : PAWN_WHITE;
    }
}
