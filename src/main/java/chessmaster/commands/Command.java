package chessmaster.commands;

import chessmaster.game.Move;
import chessmaster.exceptions.InvalidMoveException;
import chessmaster.exceptions.NullPieceException;

public abstract class Command {
    public abstract boolean execute() throws NullPieceException, InvalidMoveException;
    public abstract Move getMove();
}
