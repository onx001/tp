//@@author TongZhengHong
package chessmaster.commands;

import chessmaster.exceptions.ChessMasterException;
import chessmaster.game.Game;

public abstract class Command {
    public abstract CommandResult execute(Game game) throws ChessMasterException;

    public boolean isMoveCommand() {
        return this instanceof MoveCommand;
    }
}
