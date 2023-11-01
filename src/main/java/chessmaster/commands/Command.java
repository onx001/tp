//@@author TongZhengHong
package chessmaster.commands;

import chessmaster.exceptions.ChessMasterException;
import chessmaster.game.ChessBoard;
import chessmaster.ui.TextUI;

public abstract class Command {
    public abstract CommandResult execute(ChessBoard board, TextUI ui) throws ChessMasterException;

    public boolean isMoveCommand() {
        return this instanceof MoveCommand;
    }
}
