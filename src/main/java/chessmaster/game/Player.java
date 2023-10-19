package chessmaster.game;

import chessmaster.pieces.ChessPiece;

import java.util.ArrayList;

public class Player {

    private ArrayList<Move> moves;
    private ArrayList<ChessPiece> pieces;
    private int colour;

    public Player(int colour) {
        this.moves = new ArrayList<>();
        this.pieces = new ArrayList<>();
        this.colour = colour;

    }

    public void addMove(Move move) {
        this.moves.add(move);
    }

    public void initialisePieces(ChessBoard board) {
        for (int row = 0; row < ChessBoard.SIZE; row++) {
            for (int col = 0; col < ChessBoard.SIZE; col++) {
                ChessPiece piece = board.getPieceAtCoor(new Coordinate(col, row));
                if (piece.getColour() == this.colour) {
                    this.pieces.add(piece);
                }
            }
        }
    }





}
