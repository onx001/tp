package chessmaster;

import chessmaster.exceptions.ChessMasterException;
import chessmaster.game.ChessBoard;
import chessmaster.game.Move;
import chessmaster.parser.Parser;
import chessmaster.ui.TextUI;
import chessmaster.commands.Command;

/**
 * Main entry-point for ChessMaster application.
 */
public class ChessMaster {
    public static void main(String[] args) {

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

        boolean end = false;

        TextUI ui = new TextUI();
        ChessBoard board = new ChessBoard();
        Parser parser = new Parser();

        while (!end) {
            board.showChessBoard(ui);
            String userInputString = ui.getUserInput();
            try {
                Command command = parser.parseCommand(userInputString, board);
                end = command.execute();

                // TODO: Opponent player (AI) pick random move
                // Todo: board.executeMove(aiMove)
                // Todo: Check game state

            } catch (ChessMasterException e) {
                ui.printErorMessage(e);
            }
        }
    }
}
