//@@author TongZhengHong
package chessmaster;

import chessmaster.commands.AbortCommand;
import chessmaster.exceptions.ChessMasterException;
import chessmaster.game.ChessBoard;
import chessmaster.ui.TextUI;
import chessmaster.storage.Storage;
import chessmaster.game.ChessTile;
import chessmaster.game.Color;
import chessmaster.game.Game;

/**
 * Main entry-point for ChessMaster application.
 */
public class ChessMaster {

    private static final String FILE_PATH_STRING = "data/ChessMaster.txt";

    private TextUI ui;
    private ChessBoard board;
    private Storage storage;

    private int difficulty;
    private Color playerColor;
    private Color currentTurnColor = Color.WHITE;

    private ChessMaster() {
        ui = new TextUI();
        storage = new Storage(FILE_PATH_STRING);
        ui.printWelcomeMessage();

        try {
            playerColor = storage.loadPlayerColor();
            difficulty = storage.loadDifficulty();
            currentTurnColor = storage.loadCurrentColor();
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
        String input = ui.getUserInput(false);

        while (!input.equals("y") && !input.equals("n")) {
            ui.promptContinuePrevGame(true);
            input = ui.getUserInput(false);
        }
        
        if (input.equals("y")) {
            ui.printContinuePrevGame(playerColor.name(), difficulty);
            return false;
        } else {
            return true;
        }
    }

    //@@author TriciaBK
    private boolean shouldRestartGame() {
        ui.promptNewGame(false);
        String input = ui.getUserInput(false);
        while (!input.equals("y") && !input.equals("n")) {
            ui.promptNewGame(true);
            input = ui.getUserInput(false);
        }
        if (input.equals("y")) {
            ui.printRestartingGameMessage();
            return true;
        } else {
            return false;
        }
    }

    private void loadNewGame() {
        ui.promptStartingColor(false);
        String input = ui.getUserInput(false);

        while (!input.equals("b") && !input.equals("w")) {
            ui.promptStartingColor(true);
            input = ui.getUserInput(false);
        }
        
        playerColor = input.equals("b") ? Color.BLACK : Color.WHITE;
        board = new ChessBoard(playerColor);
        ui.printStartNewGame(playerColor.name());

        //@@author onx001
        ui.promptDifficulty(false);
        input = ui.getUserInput(false);
        while (!input.equals("1") && !input.equals("2") 
            && !input.equals("3")) {
            ui.promptDifficulty(true);
            input = ui.getUserInput(false);
        }
        difficulty = Integer.parseInt(input);
        board.setDifficulty(difficulty);
        currentTurnColor = Color.WHITE;

        try {
            storage.saveBoard(board, currentTurnColor);
        } catch (ChessMasterException e) {
            ui.printText(e.getMessage());
        }
    }

    private void run() {
        boolean shouldRestart = true;
        while (shouldRestart) {
            Game game = new Game(playerColor, currentTurnColor, board, storage, ui, difficulty);
            boolean restartMidGame = game.run();
            if (restartMidGame) {
                boolean shouldRestartGame = shouldRestartGame();
                if (shouldRestartGame) {
                    loadNewGame();
                } else {
                    shouldRestart = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        new ChessMaster().run();
    }
}
