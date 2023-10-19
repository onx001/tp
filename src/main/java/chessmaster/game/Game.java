package chessmaster.game;

import chessmaster.parser.Parser;
import chessmaster.pieces.ChessPiece;
import chessmaster.ui.TextUI;

public class Game {

    private Player player;
    // public CPU cpu;

    private TextUI ui;
    private ChessBoard board;
    private Parser parser;


     String logo = "░█████╗░██╗░░██╗███████╗░██████╗░██████╗███╗░░░███╗░█████╗░░██████╗████████╗███████╗██████╗░"
     + System.lineSeparator() +
     "██╔══██╗██║░░██║██╔════╝██╔════╝██╔════╝████╗░████║██╔══██╗██╔════╝╚══██╔══╝██╔════╝██╔══██╗"
     + System.lineSeparator() +
     "██║░░╚═╝███████║█████╗░░╚█████╗░╚█████╗░██╔████╔██║███████║╚█████╗░░░░██║░░░█████╗░░██████╔╝"
     + System.lineSeparator() +
     "██║░░██╗██╔══██║██╔══╝░░░╚═══██╗░╚═══██╗██║╚██╔╝██║██╔══██║░╚═══██╗░░░██║░░░██╔══╝░░██╔══██╗"
     + System.lineSeparator() +
     "╚█████╔╝██║░░██║███████╗██████╔╝██████╔╝██║░╚═╝░██║██║░░██║██████╔╝░░░██║░░░███████╗██║░░██║"
     + System.lineSeparator() +
     "░╚════╝░╚═╝░░╚═╝╚══════╝╚═════╝░╚═════╝░╚═╝░░░░░╚═╝╚═╝░░╚═╝╚═════╝░░░░╚═╝░░░╚══════╝╚═╝░░╚═╝"
     + System.lineSeparator();

    public Game() {
        ui = new TextUI();
        board = new ChessBoard();
        parser = new Parser();
        this.player = new Player(ChessPiece.WHITE); // Need to ask user whether they want WHITE or BLACK start
    }

    public void run() {
        System.out.println(logo);

         while (true) {
            board.showChessBoard(ui);
            String move = ui.getUserCommand();
            parser.parseAndExecuteCommand(move, this.board);
         }
    }

}
