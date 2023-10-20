package chessmaster.pieces;

import chessmaster.exceptions.NullPieceException;
import chessmaster.game.ChessBoard;
import chessmaster.game.Coordinate;

public class Pawn extends ChessPiece {
    public static final String PAWN_WHITE = "p"; // ♙
    public static final String PAWN_BLACK = "P"; // ♟
    public static final int[][] DIRECTIONSUP = {
        UP_LEFT, UP_RIGHT, UP, UP_UP,
    };
    public static final int[][] DIRECTIONSDOWN = {
        DOWN_LEFT, DOWN_RIGHT, DOWN, DOWN_DOWN,
    };
    protected boolean enPassed = false;

    private int[][] directions;
    private int checkBlockDir;



    public Pawn(int row, int col, int color) {
        super(row, col, color);
    }

    @Override
    public Coordinate[][] getAvailableCoordinates(ChessBoard board) {

        
        if (this.color == ChessPiece.WHITE){
            directions = DIRECTIONSDOWN;
            checkBlockDir = -1;
        } else {
            directions = DIRECTIONSUP;
            checkBlockDir = 1;
        } 

        Coordinate[][] result = new Coordinate[directions.length][0];

        for (int dir = 0; dir < directions.length; dir++) {
            int offsetX = directions[dir][0];
            int offsetY = directions[dir][1];

            try {
                switch (dir) {
                case 0:
                    if (position.isOffsetWithinBoard(offsetX, offsetY)) {
                        ChessPiece destPiece = board.getPieceAtCoor(position
                                .addOffsetToCoordinate(offsetX,offsetY));
                        if (destPiece.getType().equalsIgnoreCase(EmptyPiece.EMPTY_PIECE)
                                && destPiece.getColour() != this.color) {
                            result[dir] = new Coordinate[]{position.addOffsetToCoordinate(offsetX,offsetY)};
                        }
                    }
                    break;
                case 1:
                    if (position.isOffsetWithinBoard(offsetX, offsetY)) {
                        ChessPiece destPiece = board.getPieceAtCoor(position
                                .addOffsetToCoordinate(offsetX,offsetY));
                        if (destPiece.getType().equalsIgnoreCase(EmptyPiece.EMPTY_PIECE)
                                && destPiece.getColour() != this.color) {
                            result[dir] = new Coordinate[]{position.addOffsetToCoordinate(offsetX,offsetY)};
                        }
                    }
                    break;
                case 2:
                    if (position.isOffsetWithinBoard(offsetX, offsetY)) {
                        ChessPiece destPiece = board.getPieceAtCoor(position
                                .addOffsetToCoordinate(offsetX,offsetY));
                        if (destPiece.getType().equalsIgnoreCase(EmptyPiece.EMPTY_PIECE)
                                && destPiece.getColour() != this.color) {
                            result[dir] = new Coordinate[]{position.addOffsetToCoordinate(offsetX,offsetY)};
                        }
                    }
                    break;
                case 3:
                    if (position.isOffsetWithinBoard(offsetX, offsetY)) {
                        ChessPiece destPiece = board.getPieceAtCoor(position
                                .addOffsetToCoordinate(offsetX,offsetY));
                        Coordinate blockPos = position.addOffsetToCoordinate(0, checkBlockDir * -1);
                        ChessPiece blockPiece = board.getPieceAtCoor(blockPos);
                        if (destPiece.getType().equalsIgnoreCase(EmptyPiece.EMPTY_PIECE)
                                && !hasMoved
                                && blockPiece.getType().equalsIgnoreCase(EmptyPiece.EMPTY_PIECE)) {
                            result[dir] = new Coordinate[]{position.addOffsetToCoordinate(offsetX,offsetY)};
                        }
                    }
                    break;

                default:
                    break;
                }
            } catch (NullPieceException e) {
                e.printStackTrace();
            }
        }
    

        return result;

    }

    @Override
    public String toString() {
        return color == ChessPiece.BLACK ? PAWN_BLACK : PAWN_WHITE;
    }

    @Override
    public String getType() {
        return PAWN_WHITE;
    }
}
