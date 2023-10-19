package chessmaster;

import chessmaster.exceptions.ChessMasterException;
import chessmaster.exceptions.LoadBoardException;
import chessmaster.exceptions.SaveBoardException;
import chessmaster.game.ChessBoard;
import chessmaster.game.Move;
import chessmaster.parser.Parser;
import chessmaster.ui.TextUI;
import chessmaster.storage.Storage;


/**
 * Main entry-point for ChessMaster application.
 */
public class ChessMaster {

    private ChessBoard board;
    private TextUI ui;
    private Storage storage;

    public ChessMaster(String filePath) {
        ui = new TextUI();
        board = new ChessBoard();
        storage = new Storage(filePath);

        try {
            board = storage.loadBoard();
        } catch (LoadBoardException e) {
            ui.printErorMessage(e);
        }
    }

    public void run(){
        boolean end = false;

        while (!end) {
            board.showChessBoard(ui);
            String userInputString = ui.getUserInput();
            if (Parser.isUserInputAbort(userInputString)) {
                break; // End the game if user aborts
            }
            if (Parser.isUserInputExit(userInputString)) {
                try {
                    storage.saveBoard(board);
                } catch (SaveBoardException e) {
                    ui.printErorMessage(e);
                }
                end = true;
            }

            try {
                Move move = Parser.parseMove(userInputString, board);
                board.executeMove(move);

                // TODO: Opponent player (AI) pick random move
                // Todo: board.executeMove(aiMove)
                // Todo: Check game state
                //board.checkEndState(); in chessboard

            } catch (ChessMasterException e) {
                ui.printErorMessage(e);
            }
        }


    }

    public static void main(String[] args) throws SaveBoardException, LoadBoardException {

        // String logo = "░█████╗░██╗░░██╗███████╗░██████╗░██████╗
        // ███╗░░░███╗░█████╗░░██████╗████████╗███████╗██████╗░"
        // + System.lineSeparator() +
        // "██╔══██╗██║░░██║██╔════╝██╔════╝██╔════╝
        // ████╗░████║██╔══██╗██╔════╝╚══██╔══╝██╔════╝██╔══██╗"
        // + System.lineSeparator() +
        // "██║░░╚═╝███████║█████╗░░╚█████╗░╚█████╗░
        // ██╔████╔██║███████║╚█████╗░░░░██║░░░█████╗░░██████╔╝"
        // + System.lineSeparator() +
        // "██║░░██╗██╔══██║██╔══╝░░░╚═══██╗░╚═══██╗
        // ██║╚██╔╝██║██╔══██║░╚═══██╗░░░██║░░░██╔══╝░░██╔══██╗"
        // + System.lineSeparator() +
        // "╚█████╔╝██║░░██║███████╗██████╔╝██████╔╝
        // ██║░╚═╝░██║██║░░██║██████╔╝░░░██║░░░███████╗██║░░██║"
        // + System.lineSeparator() +
        // "░╚════╝░╚═╝░░╚═╝╚══════╝╚═════╝░╚═════╝░
        // ╚═╝░░░░░╚═╝╚═╝░░╚═╝╚═════╝░░░░╚═╝░░░╚══════╝╚═╝░░╚═╝"
        // + System.lineSeparator();

        // System.out.println(logo);

        new ChessMaster("/data/game.txt").run();
    }
}
