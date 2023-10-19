package chessmaster;

import chessmaster.exceptions.ChessMasterException;
import chessmaster.game.ChessBoard;
import chessmaster.game.Move;
import chessmaster.parser.Parser;
import chessmaster.ui.TextUI;
import chessmaster.commands.Command;
import chessmaster.game.Game;
import chessmaster.pieces.ChessPiece;

/**
 * Main entry-point for ChessMaster application.
 */
public class ChessMaster {
    public static void main(String[] args) {
        Game game = new Game("single", ChessPiece.WHITE);
        game.run();
    }
}
