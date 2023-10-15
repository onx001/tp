package chessmaster;

import chessmaster.game.ChessBoard;
import chessmaster.ui.TextUI;

/**
 * Main entry-point for ChessMaster application.
 */
public class ChessMaster {
    public static void main(String[] args) {

        String logo = "░█████╗░██╗░░██╗███████╗░██████╗░██████╗   ███╗░░░███╗░█████╗░░██████╗████████╗███████╗██████╗░"
                + System.lineSeparator() +
                "██╔══██╗██║░░██║██╔════╝██╔════╝██╔════╝   ████╗░████║██╔══██╗██╔════╝╚══██╔══╝██╔════╝██╔══██╗"
                + System.lineSeparator() +
                "██║░░╚═╝███████║█████╗░░╚█████╗░╚█████╗░   ██╔████╔██║███████║╚█████╗░░░░██║░░░█████╗░░██████╔╝"
                + System.lineSeparator() +
                "██║░░██╗██╔══██║██╔══╝░░░╚═══██╗░╚═══██╗   ██║╚██╔╝██║██╔══██║░╚═══██╗░░░██║░░░██╔══╝░░██╔══██╗"
                + System.lineSeparator() +
                "╚█████╔╝██║░░██║███████╗██████╔╝██████╔╝   ██║░╚═╝░██║██║░░██║██████╔╝░░░██║░░░███████╗██║░░██║"
                + System.lineSeparator() +
                "░╚════╝░╚═╝░░╚═╝╚══════╝╚═════╝░╚═════╝░   ╚═╝░░░░░╚═╝╚═╝░░╚═╝╚═════╝░░░░╚═╝░░░╚══════╝╚═╝░░╚═╝"
                + System.lineSeparator();

        System.out.println(logo);

        TextUI ui = new TextUI();
        ChessBoard board = new ChessBoard();

        while (true) {
            board.showChessBoard(ui);
            String move = ui.getUserCommand();
            // TODO: Check if move is valid
            // TODO: Update chessboard (new position with chesspiece, old position empty)
            // TODO: Update ChessPiece (position)
            // TODO: Store updated board in text file
        }
    }
}
