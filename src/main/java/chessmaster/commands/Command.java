package chessmaster.commands;

import chessmaster.exceptions.ChessMasterException;
import chessmaster.game.ChessBoard;

public abstract class Command {
    public abstract CommandResult execute(ChessBoard board) throws ChessMasterException;
}
