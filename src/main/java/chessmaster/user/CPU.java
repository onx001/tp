package chessmaster.user;

import chessmaster.game.ChessBoard;
import chessmaster.game.Move;
import chessmaster.pieces.ChessPiece;

import java.util.Random;

public class CPU extends Player {

    private final Random rand = new Random();

    public CPU(int colour) {
        super(colour);
    }

    public Move getRandomMove(ChessBoard board) {
        // 1. Get a random piece that
        //    - isn't captured
        //    - has possible legal moves
        ChessPiece randomPiece = getRandomPiece();
        while (randomPiece.getCaptured()) { //|| randomPiece.getPossibleMoves().size() == 0) {
            randomPiece = getRandomPiece();
        }

        Move randomMove = getRandomMoveFromPiece(randomPiece);

        return randomMove;
    }

    private ChessPiece getRandomPiece() {
        return this.pieces.get(rand.nextInt(pieces.size()));
    }

    private Move getRandomMoveFromPiece(ChessPiece piece) {

        // TODO

        return new Move();
    }

}
