package chessmaster.commands;

import chessmaster.game.ChessBoard;
import chessmaster.ui.TextUI;


public class ShowCommand extends Command {

    public static final String SHOW_COMAMND_STRING = "show";

    private static final String SHOW_STRINGS = "Here is the current board state:";


    @Override
    public CommandResult execute(ChessBoard board) {
        TextUI.printText(SHOW_STRINGS);
        board.showChessBoard();
        return new CommandResult("Board state has been displayed.");
    }

}
