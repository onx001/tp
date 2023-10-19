package chessmaster;

import chessmaster.game.Game;

/**
 * Main entry-point for ChessMaster application.
 */
public class ChessMaster {
    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}
