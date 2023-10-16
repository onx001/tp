package chessmaster.pieces;

import chessmaster.game.Coordinate;
import chessmaster.game.ChessTile;

public class Pawn extends ChessPiece {
    public static final String PAWN_WHITE = "p"; // ♙
    public static final String PAWN_BLACK = "P"; // ♟

    public static final int[][] DIRECTIONS = {
        UP_LEFT, UP_RIGHT, UP, UP_UP,
    };

    public Pawn(int row, int col, int color) {
        super(row, col, color);
    }

    @Override
    public Coordinate[][] getAvailableCoordinates(ChessTile[][] board) {
        
        Coordinate[][] result = new Coordinate[DIRECTIONS.length][0];

        for (int dir = 0; dir < DIRECTIONS.length; dir++) {
            int offsetX = DIRECTIONS[dir][0];
            int offsetY = DIRECTIONS[dir][1];

            switch (dir){
                case 0:
                    if (position.isOffsetWithinBoard(offsetX, offsetY)) {
                        ChessPiece destPiece = board[position.getY() + offsetY][position.getX() + offsetX].getChessPiece();
                        if (destPiece != null && destPiece.getColour() != this.color) {
                            result[dir] = new Coordinate[] { position.addOffsetToCoordinate(offsetX, offsetY) };
                        }
                    }
                    break;
                case 1:
                    if (position.isOffsetWithinBoard(offsetX, offsetY)) {
                        ChessPiece destPiece = board[position.getY() + offsetY][position.getX() + offsetX].getChessPiece();
                        if (destPiece != null && destPiece.getColour() != this.color) {
                            result[dir] = new Coordinate[] { position.addOffsetToCoordinate(offsetX, offsetY) };
                        }
                    }
                    break;
                case 2:
                    if (position.isOffsetWithinBoard(offsetX, offsetY)) {
                        ChessPiece destPiece = board[position.getY() + offsetY][position.getX() + offsetX].getChessPiece();
                        if (destPiece == null) {
                            result[dir] = new Coordinate[] { position.addOffsetToCoordinate(offsetX, offsetY) };
                        }
                    }
                    break;
                case 3:
                    if (position.isOffsetWithinBoard(offsetX, offsetY)) {
                        ChessPiece destPiece = board[position.getY() + offsetY][position.getX() + offsetX].getChessPiece();
                        ChessPiece blockPiece = board[position.getY() - 1][position.getX()].getChessPiece();
                        if (destPiece == null && !hasMoved && blockPiece == null) {
                            result[dir] = new Coordinate[] { position.addOffsetToCoordinate(offsetX, offsetY) };
                        }
                    }
                    break;

                default:
                    break;
            }
        }
    

        return result;

    }
    


    @Override
    public String toString() {
        return color == ChessPiece.BLACK ? PAWN_BLACK : PAWN_WHITE;
    }
}
