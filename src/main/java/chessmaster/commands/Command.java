package chessmaster.commands;

import chessmaster.exceptions.ChessMasterException;

public abstract class Command {
    public abstract CommandResult execute() throws ChessMasterException;
}
