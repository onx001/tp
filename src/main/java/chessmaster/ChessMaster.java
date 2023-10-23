package chessmaster;

import chessmaster.exceptions.ChessMasterException;
import chessmaster.game.ChessBoard;
import chessmaster.game.ChessTile;
import chessmaster.game.Color;
import chessmaster.game.Game;
import chessmaster.storage.Storage;
import chessmaster.ui.TextUI;

/**
 * Main entry-point for ChessMaster application.
 */
public class ChessMaster {

    private static final String FILE_PATH_STRING = "data/ChessMaster.txt";

    private ChessBoard board;
    private Storage storage;
    private Color playerColor;
    private Game game;

    private ChessMaster() {
        TextUI.printWelcomeMessage();
        storage = new Storage(FILE_PATH_STRING);

        try {
            playerColor = storage.loadPlayerColor();
            ChessTile[][] existingBoard = storage.loadBoard();
            board = new ChessBoard(existingBoard);

            if (shouldStartNewGame()) {
                loadNewGame();
            }

        } catch (ChessMasterException e) {
            TextUI.printLoadBoardError();
            loadNewGame();
        }
    }

    private boolean shouldStartNewGame() {
        TextUI.promptContinuePrevGame(false);
        String input = TextUI.getUserInput();

        while (!input.equals("y") && !input.equals("n")) {
            TextUI.promptContinuePrevGame(true);
            input = TextUI.getUserInput();
        }
        
        if (input.equals("y")) {
            TextUI.printContinuePrevGame(playerColor.name());
            return false;
        } else {
            return true;
        }
    }

    private void loadNewGame() {
        TextUI.promptStartingColor(false);
        String input = TextUI.getUserInput();

        while (!input.equals("b") && !input.equals("w")) {
            TextUI.promptStartingColor(true);
            input = TextUI.getUserInput();
        }
        
        playerColor = input.equals("b") ? Color.BLACK : Color.WHITE;
        board = new ChessBoard(playerColor);
        TextUI.printStartNewGame(playerColor.name());
        game = new Game(playerColor, board, storage);

        if (playerColor.isBlack()){
            game.CPUFirstMove();
        }
    }

    private void run() {   
        new Game(playerColor, board, storage).run();
    }

    public static void main(String[] args) {
        new ChessMaster().run();
    }
}
