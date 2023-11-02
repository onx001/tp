//@@author TongZhengHong
package chessmaster;

import chessmaster.exceptions.ChessMasterException;
import chessmaster.exceptions.LoadBoardException;
import chessmaster.exceptions.SaveBoardException;
import chessmaster.game.ChessBoard;
import chessmaster.game.Move;
import chessmaster.parser.Parser;
import chessmaster.ui.TextUI;
import chessmaster.storage.Storage;
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

    private TextUI ui;
    private ChessBoard board;
    private Storage storage;
    private Color playerColor;
    private int difficulty;

    private boolean shouldCPUStart = false;

    private ChessMaster() {
        ui = new TextUI();
        storage = new Storage(FILE_PATH_STRING);
        ui.printWelcomeMessage();

        try {
            playerColor = storage.loadPlayerColor();
            difficulty = storage.loadDifficulty();
            ChessTile[][] existingBoard = storage.loadBoard();
            board = new ChessBoard(playerColor, existingBoard);
            board.setDifficulty(difficulty);

            if (shouldStartNewGame()) {
                loadNewGame();
            }

        } catch (ChessMasterException e) {
            ui.printLoadBoardError();
            loadNewGame();
        }
    }

    private boolean shouldStartNewGame() {
        ui.promptContinuePrevGame(false);
        String input = ui.getUserInput();

        while (!input.equals("y") && !input.equals("n")) {
            ui.promptContinuePrevGame(true);
            input = ui.getUserInput();
        }
        
        if (input.equals("y")) {
            ui.printContinuePrevGame(playerColor.name());
            return false;
        } else {
            return true;
        }
    }

    private void loadNewGame() {

        ui.promptStartingColor(false);
        String input = ui.getUserInput();

        while (!input.equals("b") && !input.equals("w")) {
            ui.promptStartingColor(true);
            input = ui.getUserInput();
        }
        
        playerColor = input.equals("b") ? Color.BLACK : Color.WHITE;
        board = new ChessBoard(playerColor);
        ui.printStartNewGame(playerColor.name());

        //@@author onx001
        ui.promptDifficulty(false);
        input = ui.getUserInput();
        while (!input.equals("1") && !input.equals("2") 
            && !input.equals("3") && !input.equals("4")) {
            ui.promptDifficulty(true);
            input = ui.getUserInput();
        }
        difficulty = Integer.parseInt(input);
        board.setDifficulty(difficulty);

        //@@author TongZhengHong


        if (playerColor.isBlack()) {
            shouldCPUStart = true;
        }
    }

    private void run() {   
        Game game = new Game(playerColor, board, storage, ui, difficulty);
        if (shouldCPUStart) {
            game.cpuFirstMove();
        }
        game.run();
    }

    public static void main(String[] args) {
        new ChessMaster().run();
    }
}
