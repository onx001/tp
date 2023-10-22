package chessmaster;

import chessmaster.game.Game;
import chessmaster.pieces.ChessPiece;

/**
 * Main entry-point for ChessMaster application.
 */
public class ChessMaster {

    public static void main(String[] args) {
        Game game = new Game(ChessPiece.WHITE, "/data/newGame.txt");
        game.run();
    }
}
